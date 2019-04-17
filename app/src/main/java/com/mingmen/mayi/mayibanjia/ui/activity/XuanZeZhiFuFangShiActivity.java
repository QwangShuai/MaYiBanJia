package com.mingmen.mayi.mayibanjia.ui.activity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.lzj.pass.dialog.PayPassDialog;
import com.lzj.pass.dialog.PayPassView;
import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.app.UMConfig;
import com.mingmen.mayi.mayibanjia.bean.PostZFBBean;
import com.mingmen.mayi.mayibanjia.bean.WXPayBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dingdan.DingDanActivity;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.ClickUtil;
import com.mingmen.mayi.mayibanjia.utils.PayResult;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.mingmen.mayi.mayibanjia.app.MyApplication.mContext;

/**
 * Created by Administrator on 2018/8/23.
 */

public class XuanZeZhiFuFangShiActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_lijizhifu)
    TextView tvLijizhifu;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_xuzhifujine)
    TextView tvXuzhifujine;
    @BindView(R.id.tv_yuezhifujine)
    TextView tvYuezhifujine;
    //    @BindView(R.id.ll_yuezhifu)
//    LinearLayout llYuezhifu;
    @BindView(R.id.iv_zhifubao)
    ImageView ivZhifubao;
    @BindView(R.id.ll_zhifubao)
    LinearLayout llZhifubao;
    @BindView(R.id.iv_weixin)
    ImageView ivWeixin;
    @BindView(R.id.ll_weixin)
    LinearLayout llWeixin;
    @BindView(R.id.iv_yue)
    ImageView ivYue;

    private int zhifufangshi = 0;
    private static final int SDK_PAY_FLAG = 1;
    private boolean yuezhifu;
    private String dingdanleixing;
    private String dingdanid;
    private String yue;
    private String zongjia;
//    private String zhifujine;
    private IWXAPI api;
    public static XuanZeZhiFuFangShiActivity instance = null;
    public Context mContext;
    private ConfirmDialog confirmDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_xuanzezhifufangshi;
    }

    @Override
    protected void initData() {

        tvTitle.setText("在线支付");
        mContext = this;
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        api = WXAPIFactory.createWXAPI(this, null);
        dingdanid = getIntent().getStringExtra("dingdanid");
        zongjia = getIntent().getStringExtra("zongjia");
        dingdanleixing = getIntent().getStringExtra("dingdanleixing");
        yue = getIntent().getStringExtra("yue");
        instance = this;
//        yuezhifu = getIntent().getBooleanExtra("yuezhifu", false);
//        dingdanid="cf99cbc265c84dcfa26b4dc3d444cc2c";
//        dingdanleixing="2";
//        zongjia="0.01";
//        yue="0.01";
//        yuezhifu=false;

//        if (yuezhifu) {
//            if (Double.parseDouble(yue)>Double.parseDouble(zongjia)){
//                tijiaozhifu();
//            }else{
//
//                llYuezhifu.setVisibility(View.VISIBLE);
//                tvYuezhifujine.setText(yue);
//                zhifujine = MyMath.subBigDecType(new BigDecimal(zongjia), new BigDecimal(yue)) + "";
//                tvXuzhifujine.setText(zhifujine);
//
//            }
//        } else {
//
//            llYuezhifu.setVisibility(View.GONE);
//        zhifujine = Double.valueOf(zongjia)*100+"";
        tvXuzhifujine.setText(zongjia);
//        }

    }

    @OnClick({R.id.iv_back, R.id.ll_zhifubao, R.id.ll_weixin, R.id.ll_yue, R.id.tv_lijizhifu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_zhifubao:
                //选择支付宝支付
                ivZhifubao.setSelected(true);
                ivYue.setSelected(false);
                ivWeixin.setSelected(false);
//                if (yuezhifu) {
//                    zhifufangshi = 4;
//                } else {
                zhifufangshi = 3;
//                }
                break;
            case R.id.ll_weixin:
                //选择微信支付
                ivWeixin.setSelected(true);
                ivYue.setSelected(false);
                ivZhifubao.setSelected(false);
//                if (yuezhifu) {
//                    zhifufangshi = 5;
//                } else {
                zhifufangshi = 2;
//                }
                break;
            case R.id.ll_yue:
                //余额支付
                ivYue.setSelected(true);
                ivWeixin.setSelected(false);
                ivZhifubao.setSelected(false);
//                if (yuezhifu) {
//                    zhifufangshi = 5;
//                } else {
                zhifufangshi = 1;
//                }
                break;
            case R.id.tv_lijizhifu:
                if (!ClickUtil.isFastDoubleClick()) {
                    if (zhifufangshi == 0) {
                        ToastUtil.showToast("请选择支付方式");
                    } else if(zhifufangshi == 1){



                        confirmDialog.showDialog("是否确认使用余额支付");
                        confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                confirmDialog.dismiss();
                                final PayPassDialog payDialog = new PayPassDialog(mContext);
                                payDialog.getPayViewPass()
                                        .setPayClickListener(new PayPassView.OnPayClickListener() {
                                            @Override
                                            public void onPassFinish(String s) {
                                                tijiaozhifu();
                                            }
                                            @Override
                                            public void onPayClose() {
                                                payDialog.dismiss();
                                            }

                                            @Override
                                            public void onPayForget() {
                                                ToastUtil.showToastLong("你是个傻子吧");
                                            }
                                        });
//                                tijiaozhifu();
                            }
                        });
                        confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                confirmDialog.dismiss();
                            }
                        });
                    } else {
                        tijiaozhifu();
                    }
                }
                break;
        }
    }

    private void tijiaozhifu() {
        if (zhifufangshi == 2) {
            HttpManager.getInstance()
                    .with(mContext)
                    .setObservable(
                            RetrofitManager
                                    .getService()//支付方式  1余额支付 2微信支付 3支付宝支付
                                    .getWXPay(PreferenceUtils.getString(MyApplication.mContext, "token", ""),dingdanid, Double.valueOf(zongjia)*100+""))
                    .setDataListener(new HttpDataListener<WXPayBean>() {
                        @Override
                        public void onNext(WXPayBean data) {
                            wxPay(data);
                        }
                    }, false);
        } else {
            updateZhifu();
        }
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    Log.e( "handleMessage: ", resultInfo );
//                    PostZFBBean bean = new Gson().fromJson(resultInfo,PostZFBBean.class);
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(XuanZeZhiFuFangShiActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        postZFB(resultInfo);

                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(XuanZeZhiFuFangShiActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(XuanZeZhiFuFangShiActivity.this, DingDanActivity.class));
                        finish();
                    }
                    break;
                }
            }
        }

        ;
    };

    private void zhifubaozhifu(final String orderInfo) {
        // orderInfo  订单加签信息
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(XuanZeZhiFuFangShiActivity.this);//调用支付接口
                Map<String, String> result = alipay.payV2(orderInfo, true);//支付结果
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    public void wxPay(WXPayBean model) {
        api.registerApp(UMConfig.WECHAT_APPID);
        PayReq req = new PayReq();
        req.appId = model.getAppid();
        req.partnerId = model.getPartnerid();
        req.prepayId = model.getPrepayid();
        req.nonceStr = model.getNoncestr();
        req.timeStamp = model.getTimestamp() + "";
        req.packageValue = model.getPackageX();
        req.sign = model.getSign();
        req.extData = "app data";
        api.sendReq(req);
//        finish();
    }

    public void updateZhifu(){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()//支付方式  1余额支付 2微信支付 3支付宝支付
                                .tijiaozhifu(PreferenceUtils.getString(MyApplication.mContext, "token", ""), zhifufangshi + "", dingdanid, dingdanleixing))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        switch (zhifufangshi) {
                            case 1:
                                if (Double.parseDouble(yue) > Double.parseDouble(zongjia)) {
                                    ToastUtil.showToast("支付成功");
                                    Intent intent = new Intent(mContext, DingDanActivity.class);
                                    intent.putExtra("to_shop", 0);
                                    startActivity(intent);
                                    finish();
//                                       QueRenDingDanActivity.instance.finish();
                                }else{
                                    ToastUtil.showToast("余额不足请选择其他支付方式");
                                }
                                break;
                            case 2:
                                Intent intent = new Intent(mContext, DingDanActivity.class);
                                intent.putExtra("to_shop", 0);
                                startActivity(intent);
                                finish();
                                break;
                            case 3:
                                zhifubaozhifu(data);
                                break;
                        }


                    }
                }, false);
    }
    public void postZFB(String message){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()//支付方式  1余额支付 2微信支付 3支付宝支付
                                .postZFB(PreferenceUtils.getString(MyApplication.mContext, "token", ""), message))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        Intent intent = new Intent(mContext, DingDanActivity.class);
                        intent.putExtra("to_shop", 0);
                        startActivity(intent);
                        finish();
                    }
                }, false);
    }
}
