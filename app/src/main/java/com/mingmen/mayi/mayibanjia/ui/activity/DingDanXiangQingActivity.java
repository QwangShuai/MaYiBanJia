package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.DdxqBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.DdXqShichangAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.qrCode.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DingDanXiangQingActivity extends BaseActivity {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.iv_sangedian)
    ImageView ivSangedian;
    @BindView(R.id.iv_state)
    ImageView ivState;
    @BindView(R.id.tv_dingdan_bianhao)
    TextView tvDingdanBianhao;
    @BindView(R.id.tv_xiadan_riqi)
    TextView tvXiadanRiqi;
    @BindView(R.id.tv_zhifufangshi)
    TextView tvZhifufangshi;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.btn_zaici_goumai)
    Button btnZaiciGoumai;
    @BindView(R.id.btn_shanchu_dingdan)
    Button btnShanchuDingdan;
    @BindView(R.id.btn_quxiao_dingdan)
    Button btnQuxiaoDingdan;
    @BindView(R.id.btn_liji_fukuan)
    Button btnLijiFukuan;
    @BindView(R.id.tv_yaoqiushijian)
    TextView tvYaoqiushijian;
    @BindView(R.id.rv_shichang)
    RecyclerView rvShichang;
    @BindView(R.id.ll_daifukuan)
    LinearLayout llDaifukuan;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_dizhi)
    TextView tvDizhi;
    @BindView(R.id.tv_zong0)
    TextView tvZong0;
    @BindView(R.id.tv_zong1)
    TextView tvZong1;
    @BindView(R.id.tv_yunfei0)
    TextView tvYunfei0;
    @BindView(R.id.tv_yunfei1)
    TextView tvYunfei1;
    @BindView(R.id.tv_heji0)
    TextView tvHeji0;
    @BindView(R.id.tv_heji1)
    TextView tvHeji1;
    @BindView(R.id.tv_sp_number)
    TextView tvSpNumber;
    @BindView(R.id.btn_queren_shouhuo)
    Button btnQuerenShouhuo;
    @BindView(R.id.tv_fujiafei)
    TextView tvFujiafei;
    @BindView(R.id.tv_fujiafei1)
    TextView tvFujiafei1;
    @BindView(R.id.btn_queren_fukuan)
    Button btnQuerenFukuan;
    @BindView(R.id.rl_fujiafei)
    RelativeLayout rlFujiafei;

    private Context mContext;
    private String order_id = "";
    private List<MultiItemEntity> mList = new ArrayList<>();
    private DdXqShichangAdapter adapter;
    private ConfirmDialog dialog;
    private String zongjia = "0";
    private String yue = "0";
    private final static int SCANNIN_GREQUEST_CODE = 1;
    private String company_id;
    public static DingDanXiangQingActivity instance = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ding_dan_xiang_qing;
    }

    @Override
    protected void initData() {
        mContext = DingDanXiangQingActivity.this;
        instance = DingDanXiangQingActivity.this;
        order_id = getIntent().getStringExtra("orderID");
//        setShowView();
        getOrderXiangqing();
    }

    private void getOrderXiangqing() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getOrderXiangqing(PreferenceUtils.getString(MyApplication.mContext, "token", ""), order_id))
                .setDataListener(new HttpDataListener<DdxqBean>() {
                    @Override
                    public void onNext(DdxqBean data) {
                        Log.e("1122", new Gson().toJson(data));
                        if (data.getState().equals("401")) {
                            tvState.setText("等待卖家付款");
                            llDaifukuan.setVisibility(View.VISIBLE);
                            ivState.setImageResource(R.mipmap.daifukuan_ddxq);
                        } else if (data.getState().equals("402")) {
                            ivState.setImageResource(R.mipmap.gouwuche);
//                            btnZaiciGoumai.setVisibility(View.VISIBLE);
                            tvState.setText("待卖家发货");
                        } else if (data.getState().equals("404")) {
                            ivState.setImageResource(R.mipmap.gouwuche);
//                            btnZaiciGoumai.setVisibility(View.VISIBLE);
                            btnQuerenShouhuo.setVisibility(View.VISIBLE);
                            tvState.setText("卖家已发货");
                        } else if (data.getState().equals("406")) {
                            ivState.setImageResource(R.mipmap.yiwancheng);
                            tvState.setText("卖家已收货");
                        } else {
                            ivState.setImageResource(R.mipmap.yiwancheng);
                            btnShanchuDingdan.setVisibility(View.VISIBLE);
                            tvState.setText("已完成");
                        }
                        company_id = data.getCompany_id();
                        tvYaoqiushijian.setText("要求送达时间:" + data.getSon_name());
                        tvName.setText(data.getLinman());
                        tvPhone.setText(data.getDianhua());
                        tvDizhi.setText("地址:" + data.getDizhi());
                        tvSpNumber.setText(data.getSp() + "    合计:");
                        tvDingdanBianhao.setText("订单编号:" + data.getOrder_number());
                        tvXiadanRiqi.setText("下单日期:" + data.getCreate_time());
                        zongjia = data.getTotal();
                        setJiaGeShowView(tvZong0, tvZong1, data.getTotal_price() + "");
                        setJiaGeShowView(tvYunfei0, tvYunfei1, data.getFreight_fee() + "");
                        if (TextUtils.isEmpty(data.getAppend_money()+"") || data.getAppend_money() == null) {
                            rlFujiafei.setVisibility(View.GONE);
                        } else {
                            setJiaGeShowView(tvFujiafei, tvFujiafei1, data.getAppend_money() + "");
                        }

                        setJiaGeShowView(tvHeji0, tvHeji1, data.getTotal() + "");
                        mList.clear();
                        mList.addAll(getAdapterData(data));
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false) {
                            @Override
                            public boolean canScrollVertically() {
                                return false;
                            }
                        };
                        adapter = new DdXqShichangAdapter(mList, DingDanXiangQingActivity.this);
                        adapter.expandAll();
                        rvShichang.setLayoutManager(linearLayoutManager);
                        rvShichang.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });
    }
    private List<MultiItemEntity> getAdapterData(DdxqBean deDdxqBean) {//三级列表数据源
        List<MultiItemEntity> data = new ArrayList<>();
        for (int i = 0; i < deDdxqBean.getMarket().size(); i++) {
            DdxqBean.MarketBean item0 = deDdxqBean.getMarket().get(i);
            for (int j = 0; j < item0.getDplist().size(); j++) {
                DdxqBean.MarketBean.DplistBean item1 = item0.getDplist().get(j);
                for (int k = 0; k < item1.getListsp().size(); k++) {
                    DdxqBean.MarketBean.DplistBean.ListspBean item2 = item1.getListsp().get(k);
                    if(k==item1.getListsp().size()-1){
                        item2.setEnd(true);
                        item2.setShu(item1.getShu());
                        item2.setDpprice(item1.getDpprice());
                    } else {
                        item2.setEnd(false);
                    }
                    item1.addSubItem(item2);
                }
                item0.addSubItem(item1);
            }
            data.add(item0);
        }
        return data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_fanhui, R.id.iv_sangedian, R.id.btn_zaici_goumai, R.id.btn_shanchu_dingdan, R.id.btn_quxiao_dingdan,
            R.id.btn_liji_fukuan, R.id.btn_queren_shouhuo, R.id.btn_queren_fukuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.iv_sangedian:
                break;
            case R.id.btn_zaici_goumai:
                break;
            case R.id.btn_shanchu_dingdan:
                dialog = new ConfirmDialog(mContext,
                        mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
                dialog.showDialog("是否确认删除订单");
                dialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        delOrder();
                    }
                });
                dialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                break;
            case R.id.btn_quxiao_dingdan:
                dialog = new ConfirmDialog(mContext,
                        mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
                dialog.showDialog("是否确认取消订单");
                dialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        delOrder();
                    }
                });
                dialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                break;
            case R.id.btn_liji_fukuan:
                getyue();
                break;
            case R.id.btn_queren_shouhuo:
                dialog = new ConfirmDialog(mContext,
                        mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
                dialog.showDialog("是否确认收货");
                dialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        querenshouhuo();
                    }
                });
                dialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                break;
            case R.id.btn_queren_fukuan://支付订单中全部商品

                break;
        }
    }

    public void setShowView() {
//        btnZaiciGoumai.setVisibility(View.INVISIBLE);
        btnShanchuDingdan.setVisibility(View.INVISIBLE);
        llDaifukuan.setVisibility(View.INVISIBLE);
    }

    public void setJiaGeShowView(TextView tv1, TextView tv2, String zongjia) {
        Log.e("总价",zongjia);
        if (zongjia.contains(".")) {
            Log.e("总价",zongjia+"包含");
            tv1.setText(zongjia.split("\\.")[0] + ".");
            tv2.setText(zongjia.split("\\.")[1]);
        } else {
            Log.e("总价",zongjia+"不包含");
            tv1.setText(zongjia + ".");
            tv2.setText("00");
        }
    }

    private void delOrder() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .delOrder(PreferenceUtils.getString(MyApplication.mContext, "token", ""), order_id))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String s) {
                        ToastUtil.showToast("订单删除成功");
                        finish();
                    }
                });
    }
    private void querenshouhuo() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .querenshouhuo(PreferenceUtils.getString(MyApplication.mContext, "token", ""), order_id))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String s) {
                        ToastUtil.showToast("确认收货成功");
                        getOrderXiangqing();
                    }
                });
    }
    public void confirmOrder() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .confirmOrder(PreferenceUtils.getString(MyApplication.mContext, "token", ""), order_id,company_id))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String s) {
                        ToastUtil.showToast("支付成功");
                        getOrderXiangqing();
                    }
                });
    }

    private void pay() {
        Intent intent = new Intent(mContext, XuanZeZhiFuFangShiActivity.class);
        intent.putExtra("dingdanid", order_id);
        intent.putExtra("dingdanleixing", "1");
//        intent.putExtra("yuezhifu",shiyongyue);
        intent.putExtra("zongjia", zongjia);
        intent.putExtra("yue", yue);
        startActivity(intent);
        finish();
    }

    private void getyue() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .chaxunyue(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<Double>() {
                    @Override
                    public void onNext(Double data) {
                        yue = data + "";
                        pay();
                    }
                });
    }

    public void saomiaoQrCode() {
        Intent intent = new Intent();
        intent.setClass(mContext, CaptureActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
    }

    public void updateQrCode(String id) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .updateQrCode(PreferenceUtils.getString(MyApplication.mContext, "token", ""), id, "", "1", ""))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        getOrderXiangqing();
                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        if (resultCode == CaptureActivity.RESULT_CODE_QR_SCAN) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString(CaptureActivity.INTENT_EXTRA_KEY_QR_SCAN);
            Log.e("678678", scanResult);
            updateQrCode(scanResult);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getOrderXiangqing();
    }

}
