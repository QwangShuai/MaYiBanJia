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


import com.mingmen.mayi.mayibanjia.ui.activity.adapter.HeDanShiChangAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.TiJiaoDingDanDianPuAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.TiJiaoDingDanShichangAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.MyMath;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
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
    @BindView(R.id.tv_hdmc)
    TextView tvHdmc;
    //    @BindView(R.id.tv_yue)
//    TextView tvYue;
//    @BindView(R.id.iv_xuanzeyue)
//    ImageView ivXuanzeyue;
    private Context mContext;
    private List<QueRenDingDanShangPinBean.MarketlistBean> shichangdata = new ArrayList<>();
    private List<QueRenDingDanShangPinBean.MarketlistBean> hedandata = new ArrayList<>();
    private String zongjia;
    private TiJiaoDingDanShichangAdapter adapter;
//    private HeDanShiChangAdapter adapter;
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
    private String ct_buy_final_id = "";
    private String spID = "";
    private String number = "";
    private int count = 0;
    private int shichangCount = 0;
    private ConfirmDialog confirmDialog;
    //    private boolean shiyongyue=false;
    public static QueRenDingDanActivity instance = null;
    private List<AllMarket> shichangList = new ArrayList<>();
    private List<String> commodity_id_list = new ArrayList<>();
    private List<String> son_order_id_list = new ArrayList<>();

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

        ct_buy_final_id = getIntent().getStringExtra("ct_buy_final_id");
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
        if(StringUtil.isValid(ct_buy_final_id)){
            adapter = new TiJiaoDingDanShichangAdapter(mContext, hedandata,1);
        } else {
            adapter = new TiJiaoDingDanShichangAdapter(mContext, shichangdata,0);
        }

        rvTijiaodingdan.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvTijiaodingdan.setNestedScrollingEnabled(false);
        rvTijiaodingdan.setAdapter(adapter);
        getaddressList();
        getsplist();
        getyue();
    }

    private void tijiaodingdan() {
        List<QueRenDingDanShangPinBean.MarketlistBean> list = shichangdata;
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
        Log.e("我的",new Gson().toJson(shichangList));
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
        if(!StringUtil.isValid(ct_buy_final_id)){
            List<QueRenDingDanShangPinBean.MarketlistBean> list = shichangdata;
            for (int i = 0; i < list.size(); i++) {
                for (int j=0;j<list.get(i).getDplist().size();j++){
                    remarke += list.get(i).getDplist().get(j).getRemark() + ",";
                }
            }
            remarke = remarke.substring(0, remarke.length() - 1);
        }
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .caigoutijiaodingdan(PreferenceUtils.getString(MyApplication.mContext, "token", ""), hejijine+"", "", yunfei+"", yue, dizhi.getAddress_id(), songdashijianid,
                                        son_order_id, commodity_id, remarke,new Gson().toJson(shichangList),tsyq,ct_buy_final_id))
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
                        ShenPiActivity.instance.finish();
                        startActivity(intent);
                        finish();
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
                                .getsplist(PreferenceUtils.getString(MyApplication.mContext, "token", ""), son_order_id, commodity_id, lujingtype, shopping_id, company_id,ct_buy_final_id))
                .setDataListener(new HttpDataListener<QueRenDingDanShangPinBean>() {
                    @Override
                    public void onNext(QueRenDingDanShangPinBean data) {
                        shichangCount = data==null?0:data.getMarketlist().size();
                        spID = "";
                        number = "";
                        if(StringUtil.isValid(ct_buy_final_id)){
                            tvHdmc.setText(data.getCt_buy_final_name());
                            tvHdmc.setVisibility(View.VISIBLE);
                            for(int i=0;i<data.getMarketlist().size();i++){
                                for (int j=0;j < data.getMarketlist().get(i).getCgzhulist().size();j++){
                                    for(int m = 0;m<data.getMarketlist().get(i).getCgzhulist().get(j).getCgzilist().size();m++){
                                        if(i==0&&j==0&&m==0){
                                            spID +=  data.getMarketlist().get(i).getCgzhulist().get(j).getCgzilist().get(m).getCommodity_id();
                                            number +=  data.getMarketlist().get(i).getCgzhulist().get(j).getCgzilist().get(m).getCount();
                                        } else {
                                            spID += ","+  data.getMarketlist().get(i).getCgzhulist().get(j).getCgzilist().get(m).getCommodity_id();
                                            number += ","+  data.getMarketlist().get(i).getCgzhulist().get(j).getCgzilist().get(m).getCount();
                                        }

                                        if (i == data.getMarketlist().size() - 1 && j == data.getMarketlist().get(i).getCgzhulist().size() - 1&&m==data.getMarketlist().get(i).getCgzhulist().get(j).getCgzilist().size()-1) {
                                            getYunFei();
                                        }
                                    }
                                }
                            }
                            hedandata.clear();
                            hedandata.addAll(data.getMarketlist());
                            adapter.notifyDataSetChanged();
                        } else {
                            for (int k=0;k<data.getMarketlist().size();k++){
                                for (int i = 0; i < data.getMarketlist().get(k).getDplist().size(); i++) {//i是店铺
                                    for (int j = 0; j < data.getMarketlist().get(k).getDplist().get(i).getList().size(); j++) {
                                        if (k==0&&i==0&&j == 0) {//j是商品
                                            spID += data.getMarketlist().get(k).getDplist().get(i).getList().get(j).getCommodity_id();
                                            number += data.getMarketlist().get(k).getDplist().get(i).getList().get(j).getCount();
                                        } else {
                                            spID += "," + data.getMarketlist().get(k).getDplist().get(i).getList().get(j).getCommodity_id();
                                            number += "," + data.getMarketlist().get(k).getDplist().get(i).getList().get(j).getCount();
                                        }
                                        if (k == data.getMarketlist().size() - 1 && i == data.getMarketlist().get(k).getDplist().size() - 1&&j==data.getMarketlist().get(k).getDplist().get(i).getList().size()-1) {
                                            getYunFei();
                                        }
                                    }
                                }
                            }
                            shichangdata.clear();
                            shichangdata.addAll(data.getMarketlist());
                            adapter.notifyDataSetChanged();
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
                                    Log.e("执行中"+i+"==",data.get(i).getAddress_id());
                                    break;
                                } else if(i==data.size()-1){
                                    dizhi = data.get(0);
                                    initdizhi();
                                    Log.e("执行结束",data.get(i).getAddress_id());
                                    break;
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
                                .getsongdashijian(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
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
                    number = "";
                    yunfei = 0.0;
                    zongzhong = 0.0;
                    String dizhijson = data.getStringExtra("dizhi");
                    Log.e("dizhijson", dizhijson);
                    dizhi = gson.fromJson(dizhijson, AddressListBean.class);
                    count = 1;
                    initdizhi();
                    getsplist();
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }

    private void initdizhi() {

        tvShouhuorenming.setText(dizhi.getLinkman());
        tvShouhuorendianhua.setText(dizhi.getContact_type());
        if (Integer.parseInt(dizhi.getDefault_address()) != 0) {
            tvMoren.setVisibility(View.GONE);
            tvDizhi.setText(dizhi.getProvince_name() + dizhi.getCity_name() + dizhi.getRegion_name()+ dizhi.getStreet_name() + dizhi.getSpecific_address());
        } else {
            Log.e("213213",dizhi.getLinkman()+"--"+dizhi.getContact_type()+"--"+dizhi.getProvince_name() + dizhi.getCity_name() + dizhi.getRegion_name() + dizhi.getSpecific_address());
            tvMoren.setVisibility(View.VISIBLE);
            SpannableStringBuilder span = new SpannableStringBuilder("黑龙江省" + dizhi.getProvince_name() + dizhi.getCity_name() + dizhi.getRegion_name()+ dizhi.getStreet_name() + dizhi.getSpecific_address());
            span.setSpan(new ForegroundColorSpan(Color.TRANSPARENT), 0, 4,
                    Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            tvDizhi.setText(span);
            tvWudizhi.setVisibility(View.GONE);
            llYoudizhi.setVisibility(View.VISIBLE);
        }
    }

    public void getYunFei() {
        if(count==0){
            ToastUtil.showToast("请添加收货地址");
        } else {
            HttpManager.getInstance()
                    .with(mContext)
                    .setObservable(RetrofitManager.getService()
                            .getYunFei(PreferenceUtils.getString(MyApplication.mContext, "token", ""),spID, dizhi.getAddress_id(), number,Integer.parseInt(lujingtype)))
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

    public String getJson(String data){
        List<String> list = new ArrayList<>();
        for (int i = 0;i<data.split(",").length;i++){
            list.add(i,data.split(",")[i]);
        }
        return new Gson().toJson(list);
    }
}
