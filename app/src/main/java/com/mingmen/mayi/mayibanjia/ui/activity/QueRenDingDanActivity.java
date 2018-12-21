package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.AddressListBean;
import com.mingmen.mayi.mayibanjia.bean.AllMarket;
import com.mingmen.mayi.mayibanjia.bean.GWCDianPuShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.QueRenDingDanShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.ReUserOrder;
import com.mingmen.mayi.mayibanjia.bean.SongDaShiJianBean;
import com.mingmen.mayi.mayibanjia.bean.YunFeiBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;


import com.mingmen.mayi.mayibanjia.ui.activity.adapter.TiJiaoDingDanDianPuAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.TiJiaoDingDanShichangAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.MyMath;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.SinglePicker;

/**
 * Created by Administrator on 2018/8/9.
 */

public class QueRenDingDanActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_shouhuorenming)
    TextView tvShouhuorenming;
    @BindView(R.id.tv_shouhuorendianhua)
    TextView tvShouhuorendianhua;
    @BindView(R.id.tv_moren)
    TextView tvMoren;
    @BindView(R.id.tv_dizhi)
    TextView tvDizhi;
    @BindView(R.id.tv_songdashijian)
    TextView tvSongdashijian;
    @BindView(R.id.tv_wudizhi)
    TextView tvWudizhi;
    @BindView(R.id.ll_songdashijian)
    LinearLayout llSongdashijian;
    @BindView(R.id.ll_xuanzedizhi)
    LinearLayout llXuanzedizhi;
    @BindView(R.id.ll_youdizhi)
    LinearLayout llYoudizhi;
    @BindView(R.id.rv_tijiaodingdan)
    RecyclerView rvTijiaodingdan;
    @BindView(R.id.tv_spjine)
    TextView tvSpjine;
    @BindView(R.id.tv_yunfei)
    TextView tvYunfei;
    @BindView(R.id.tv_zhongliang)
    TextView tvZhongliang;
    @BindView(R.id.tv_hejijine)
    TextView tvHejijine;
    @BindView(R.id.tv_tijiaodingdan)
    TextView tvTijiaodingdan;
    //    @BindView(R.id.tv_yue)
//    TextView tvYue;
//    @BindView(R.id.iv_xuanzeyue)
//    ImageView ivXuanzeyue;
    private Context mContext;
    private List<QueRenDingDanShangPinBean> shichangdata = new ArrayList<>();
    private String zongjia;
    private TiJiaoDingDanShichangAdapter adapter;
    private AddressListBean dizhi;
    private ArrayList<SongDaShiJianBean> songdashijianlist;
    private String songdashijianname;
    private String songdashijianid = "";
    private Double yunfei = 0.00;
    private Double zongzhong = 0.00;
    private Double hejijine = 0.00;
    private String shopping_id = "";
    private String remarke = "";
    private String tsyq = "";
    private String yue = "";
    private String[] selectedId;
    private String company_id = "";
    private String commodity_id = "";
    private String son_order_id = "";
    private String lujingtype = "";
    private String spID = "";
    private String number = "";
    private int count = 0;
    private int shichangCount = 0;
    private ConfirmDialog confirmDialog;
    //    private boolean shiyongyue=false;
    public static QueRenDingDanActivity instance = null;
    private List<AllMarket> shichangList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_querendingdan;
    }

    @Override
    protected void initData() {
        mContext = QueRenDingDanActivity.this;
        tvTitle.setText("确认订单");
        lujingtype = getIntent().getStringExtra("lujingtype");
        instance = QueRenDingDanActivity.this;


        company_id = getIntent().getStringExtra("company_id");
        company_id = company_id.substring(0, company_id.length() - 1);

        zongjia = getIntent().getStringExtra("zongjia");

        if (Integer.parseInt(lujingtype) == 2) {
            son_order_id = getIntent().getStringExtra("son_order_id");
            son_order_id = son_order_id.substring(0, son_order_id.length() - 1);
            commodity_id = getIntent().getStringExtra("commodity_id");
            commodity_id = commodity_id.substring(0, commodity_id.length() - 1);
        } else {
            shopping_id = getIntent().getStringExtra("shopping_id");
            shopping_id = shopping_id.substring(0, shopping_id.length() - 1);
        }


        Log.e("zongjia", zongjia + "");
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        tvSpjine.setText(zongjia);
        getaddressList();
        getsplist();
        getyue();
    }

    private void tijiaodingdan() {
        List<QueRenDingDanShangPinBean> list = shichangdata;
        for (int i = 0; i < list.size(); i++) {
            for (int j=0;j<list.get(i).getDplist().size();j++){
                remarke += list.get(i).getDplist().get(j).getRemark() + ",";
            }

        }
        remarke = remarke.substring(0, remarke.length() - 1);

//        if (fangshi==1){
//            //weixin
//        }else if (fangshi==2){
//            //zhifubao
//        }else if (fangshi==3){
//            //yue
//        }
        Log.e("shopping_id,shopping_id", shopping_id + "---" + remarke);
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .tijiaodingdan(PreferenceUtils.getString(MyApplication.mContext, "token", ""), hejijine+"", "", yunfei+"", yue, dizhi.getAddress_id(), songdashijianid, shopping_id, remarke,new Gson().toJson(shichangList)))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        Intent intent = new Intent(mContext, XuanZeZhiFuFangShiActivity.class);
                        intent.putExtra("dingdanid", data);
                        intent.putExtra("dingdanleixing", "2");
                        intent.putExtra("zongjia", hejijine+"");
                        intent.putExtra("yue", yue);
                        intent.putExtra("yemian", "0");
                        startActivity(intent);
                        finish();
                    }
                });
//
    }

    private void caigoutijiaodingdan() {
        List<QueRenDingDanShangPinBean> list = shichangdata;
        for (int i = 0; i < list.size(); i++) {
            for (int j=0;j<list.get(i).getDplist().size();j++){
                remarke += list.get(i).getDplist().get(j).getRemark() + ",";
            }

        }
        remarke = remarke.substring(0, remarke.length() - 1);
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .caigoutijiaodingdan(PreferenceUtils.getString(MyApplication.mContext, "token", ""), hejijine+"", "", yunfei+"", yue, dizhi.getAddress_id(), songdashijianid, son_order_id, commodity_id, remarke,new Gson().toJson(shichangList),tsyq))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        Log.e("tijiaodingdan", gson.toJson(data) + "---");
                        Intent intent = new Intent(mContext, XuanZeZhiFuFangShiActivity.class);
                        intent.putExtra("dingdanid", data);
                        intent.putExtra("dingdanleixing", "1");
//                    intent.putExtra("yuezhifu",shiyongyue);
                        intent.putExtra("zongjia", hejijine+"");
                        intent.putExtra("yue", yue);
                        startActivity(intent);
                    }
                });
//
    }

    private void getsplist() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getsplist(PreferenceUtils.getString(MyApplication.mContext, "token", ""), son_order_id, commodity_id, lujingtype, shopping_id, company_id))
                .setDataListener(new HttpDataListener<List<QueRenDingDanShangPinBean>>() {
                    @Override
                    public void onNext(List<QueRenDingDanShangPinBean> data) {
                        Log.e("getsplist", gson.toJson(data));
                        shichangdata = new ArrayList<QueRenDingDanShangPinBean>();
                        shichangdata.addAll(data);
                        adapter = new TiJiaoDingDanShichangAdapter(mContext, shichangdata);
                        rvTijiaodingdan.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                        rvTijiaodingdan.setNestedScrollingEnabled(false);
                        rvTijiaodingdan.setAdapter(adapter);
                        shichangCount = data==null?0:data.size();
                        for (int k=0;k<data.size();k++){
                            for (int i = 0; i < data.get(k).getDplist().size(); i++) {//i是店铺
                                for (int j = 0; j < data.get(k).getDplist().get(i).getList().size(); j++) {
                                    if (k==0&&i==0&&j == 0) {//j是商品
                                        spID += data.get(k).getDplist().get(i).getList().get(j).getCommodity_id();
                                        number += data.get(k).getDplist().get(i).getList().get(j).getCount();
                                    } else {
                                        spID += "," + data.get(k).getDplist().get(i).getList().get(j).getCommodity_id();
                                        number += "," + data.get(k).getDplist().get(i).getList().get(j).getCount();
                                    }
                                    if (k == data.size() - 1 && i == data.get(k).getDplist().size() - 1&&j==data.get(k).getDplist().get(i).getList().size()-1) {
                                        getYunFei();
                                    }
                                }
                            }
                        }


                    }
                });
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
                        Log.e("data", data + "---");
                        yue = data + "";
                        Log.e("我的余额",yue);
//                        tvYue.setText(yue);
                    }
                });
    }

    private void getaddressList() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getaddresslist(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<List<AddressListBean>>() {
                    @Override
                    public void onNext(final List<AddressListBean> data) {
                        if (data.size() > 0) {
                            count = data.size();
                            llYoudizhi.setVisibility(View.VISIBLE);
                            tvWudizhi.setVisibility(View.GONE);
                            for (int i = 0; i < data.size(); i++) {
                                if ("0".equals(data.get(i).getDefault_address())) {
                                    dizhi = data.get(i);
                                    initdizhi();
                                } else if(i==data.size()-1){
                                    dizhi = data.get(0);
                                    initdizhi();
                                }
                            }
                        } else {
                            llYoudizhi.setVisibility(View.GONE);
                            tvWudizhi.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    //送达时间
    private void getsongdashijian() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getsongdashijian())
                .setDataListener(new HttpDataListener<List<SongDaShiJianBean>>() {
                    @Override
                    public void onNext(List<SongDaShiJianBean> data) {
                        Log.e("data", data + "---");
                        songdashijianlist = new ArrayList<SongDaShiJianBean>();
                        if (data.size() > 0) {
                            songdashijianlist.addAll(data);
                            final SinglePicker<SongDaShiJianBean> picker = new SinglePicker<SongDaShiJianBean>(QueRenDingDanActivity.this, songdashijianlist);
                            picker.setCanceledOnTouchOutside(false);
                            picker.setSelectedIndex(1);
                            picker.setCycleDisable(false);
                            picker.setOnItemPickListener(new SinglePicker.OnItemPickListener<SongDaShiJianBean>() {
                                @Override
                                public void onItemPicked(int index, SongDaShiJianBean item) {
                                    songdashijianname = item.getSon_name();
                                    songdashijianid = item.getSon_number();
                                    Log.e("songdashijianname", songdashijianname + "===" + songdashijianid);
                                    tvSongdashijian.setText(songdashijianname);
                                    picker.dismiss();
                                }
                            });
                            picker.show();
                        } else {
                            ToastUtil.showToast("目前送达时间无法选择");
                        }

                    }
                });

    }

    @OnClick({R.id.iv_back, R.id.ll_songdashijian, R.id.ll_xuanzedizhi, R.id.tv_tijiaodingdan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_songdashijian:
                //选择送达时间
                getsongdashijian();
                break;
//            case R.id.iv_xuanzeyue:
//                //选择使用yue付款
//                if (shiyongyue){
//                    shiyongyue=false;
//                    ivXuanzeyue.setSelected(shiyongyue);
//                }else{
//                    shiyongyue=true;
//                    ivXuanzeyue.setSelected(shiyongyue);
//                }
//                break;
            case R.id.ll_xuanzedizhi:
                //选择配送地址
                Intent intent = new Intent(mContext, ShouHuoDiZhiActivity.class);
                bundle.putString("rukou", "xuanze");
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_tijiaodingdan:
                if (yunfei==0.00) {
                    ToastUtil.showToast("超重,暂无物流接单,请联系客服");
                } else {
                    //提交订单
                    Calendar calendar = Calendar.getInstance();
                    int hour = calendar.get(Calendar.HOUR_OF_DAY);
                    Log.e("hour", hour + "-");
//                    if (hour > 18 || hour < 3) {
//                        ToastUtil.showToast("晚6点后无法下单");
//                    } else {
                        if ("".equals(songdashijianid)) {
                            ToastUtil.showToast("请选择送达时间后再提交订单");
                        } else if (dizhi == null) {
                            ToastUtil.showToast("请选择收货地址后提交订单");
                        } else {

                                if(shichangCount>1){
                                    confirmDialog.showDialog("购买了"+shichangCount+"个市场的商品，将产生高额运费，是否确认提交订单");
                                } else {
                                    confirmDialog.showDialog("是否确认提交订单");
                                }

                                confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (Integer.parseInt(lujingtype) == 1) {
                                            tijiaodingdan();
                                        } else {
                                            caigoutijiaodingdan();
                                        }
                                    }
                                });
                                confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        confirmDialog.dismiss();
                                    }
                                });
//                            Intent intent1=new Intent(mContext,XuanZeZhiFuFangShiActivity.class);
////                            intent.putExtra("dingdanid",);
//                            intent.putExtra("yuezhifu",shiyongyue);
//                            intent.putExtra("zongjia",zongjia);
//                            intent.putExtra("yue",yue);
//                            startActivity(intent1);

//                        }
                    }
                }

                break;

        }
    }


    //获得返回的结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == 1 & data != null) {
                    String dizhijson = data.getStringExtra("dizhi");
                    Log.e("dizhijson", dizhijson);
                    dizhi = gson.fromJson(dizhijson, AddressListBean.class);
                    initdizhi();
                }
                break;
        }
    }

    private void initdizhi() {
        Log.e("213213",dizhi.getLinkman()+"--"+dizhi.getContact_type()+"--"+dizhi.getProvince_name() + dizhi.getCity_name() + dizhi.getRegion_name() + dizhi.getSpecific_address());
        tvShouhuorenming.setText(dizhi.getLinkman());
        tvShouhuorendianhua.setText(dizhi.getContact_type());
        if (Integer.parseInt(dizhi.getDefault_address()) != 0) {
            tvMoren.setVisibility(View.GONE);
            tvDizhi.setText(dizhi.getProvince_name() + dizhi.getCity_name() + dizhi.getRegion_name() + dizhi.getSpecific_address());
        } else {
            tvMoren.setVisibility(View.VISIBLE);
            SpannableStringBuilder span = new SpannableStringBuilder("黑龙江省" + dizhi.getProvince_name() + dizhi.getCity_name() + dizhi.getRegion_name() + dizhi.getSpecific_address());
            span.setSpan(new ForegroundColorSpan(Color.TRANSPARENT), 0, 4,
                    Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            tvDizhi.setText(span);
        }
    }

    public void getYunFei() {
        if(count==0){
            ToastUtil.showToast("请添加收货地址");
        } else {
            HttpManager.getInstance()
                    .with(mContext)
                    .setObservable(RetrofitManager.getService()
                            .getYunFei(spID, dizhi.getAddress_id(), number))
                    .setDataListener(new HttpDataListener<List<YunFeiBean>>() {
                        @Override
                        public void onNext(List<YunFeiBean> o) {
                            adapter.setYunfei(o);
                            Log.e("21212","走不到了啊");
                            hejijine = Double.valueOf(zongjia);
                            for (int i=0;i<o.size();i++ ){
                                hejijine += o.get(i).getMoney();
                                yunfei +=o.get(i).getMoney();
                                zongzhong += o.get(i).getSumZL();
                                AllMarket bean = new AllMarket();
                                bean.setMarket_id(o.get(i).getMark_id());
                                bean.setFreight_fee(o.get(i).getMoney());
                                bean.setTotal_weight(o.get(i).getSumZL());
                                shichangList.add(bean);
                            }
                            tvHejijine.setText(hejijine + "");
                            tvZhongliang.setText(+zongzhong+"斤)");
                            tvYunfei.setText(yunfei+"");
                        }
                    });
        }

    }
}
