package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.WoDeBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.ghdingdan.GHDOrderActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.qiangdan.GongYingDuanQiangDanActivity;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PollingService;
import com.mingmen.mayi.mayibanjia.utils.PollingUtils;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/30.
 */

public class GongYingDuanShouYeActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_yue)
    TextView tvYue;
    @BindView(R.id.ll_qiangdan)
    LinearLayout llQiangdan;
    @BindView(R.id.ll_dingdan)
    LinearLayout llDingdan;
    @BindView(R.id.ll_shangpinguanli)
    LinearLayout llShangpinguanli;
    @BindView(R.id.ll_yonghupingjia)
    LinearLayout llYonghupingjia;
    @BindView(R.id.ll_caiwuduizhang)
    LinearLayout llCaiwuduizhang;
    @BindView(R.id.ll_shezhi)
    LinearLayout llShezhi;
    @BindView(R.id.ll_tejiashangpin)
    LinearLayout llTejiashangpin;
    @BindView(R.id.tv_tianjiashangpin)
    TextView tvTianjiashangpin;
    @BindView(R.id.tv_dingdan)
    TextView tvDingdan;
    @BindView(R.id.tv_spll)
    TextView tvSpll;
    @BindView(R.id.tv_spsc)
    TextView tvSpsc;
    @BindView(R.id.tv_dpgz)
    TextView tvDpgz;
    @BindView(R.id.tv_ysh)
    TextView tvYsh;
    @BindView(R.id.tv_dfh)
    TextView tvDfh;
    @BindView(R.id.tv_ywc)
    TextView tvYwc;
    private Context mContext;
    private WoDeBean woDeBean;
    public static GongYingDuanShouYeActivity instance = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_gongyingduanshouye;
    }

    @Override
    protected void initData() {
        mContext = GongYingDuanShouYeActivity.this;
        ivBack.setVisibility(View.GONE);
        tvTitle.setText("店铺运营");
        if (!PollingUtils.isOpen) {
            PollingUtils.startPollingService(mContext, 1, PollingService.class, PollingService.ACTION);
        }
        instance = this;
        getwode();
//        getData();
    }


    @OnClick({R.id.ll_qiangdan, R.id.ll_dingdan, R.id.ll_shangpinguanli, R.id.ll_yonghupingjia, R.id.ll_caiwuduizhang, R.id.ll_shezhi, R.id.ll_tejiashangpin,
            R.id.tv_tianjiashangpin, R.id.ll_yue,R.id.ll_daifahuo, R.id.ll_yishouhuo, R.id.ll_yiwancheng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_qiangdan:
//                Intent qiangdan = new Intent(mContext, QiangDanActivity.class);
                Intent qiangdan = new Intent(mContext, GongYingDuanQiangDanActivity.class);
                startActivity(qiangdan);
                break;
            case R.id.ll_dingdan://订单入口
                Intent gonghuodingdan = new Intent(mContext, GHDOrderActivity.class);
                gonghuodingdan.putExtra("ye",0);
                startActivity(gonghuodingdan);
                break;
            case R.id.ll_shangpinguanli:
                Intent sp = new Intent(mContext, ShangPinGuanLiActivity.class);
                sp.putExtra("goods", "0");
                startActivity(sp);
                break;
            case R.id.ll_yonghupingjia:
                break;
            case R.id.ll_caiwuduizhang:
                break;
            case R.id.ll_shezhi:
                Intent shezhi = new Intent(mContext, GongYingDuanSheZhiActivity.class);
                startActivity(shezhi);
                finish();
                break;
            case R.id.ll_tejiashangpin:
                Intent tejia = new Intent(mContext, ShangPinGuanLiActivity.class);
                tejia.putExtra("goods", "1");
                startActivity(tejia);
                break;
            case R.id.tv_tianjiashangpin:
                Intent caigouintent = new Intent(mContext, FaBuShangPinActivity.class);
                caigouintent.putExtra("state", "0");
                startActivity(caigouintent);
                break;
            case R.id.ll_yue://余额
                Jump_intent(YueActivity.class, new Bundle());
                break;
            case R.id.ll_daifahuo:
                Intent daifahuo = new Intent(mContext, GHDOrderActivity.class);
                daifahuo.putExtra("ye",1);
                startActivity(daifahuo);
                break;
            case R.id.ll_yishouhuo:
                Intent yishouhuo = new Intent(mContext, GHDOrderActivity.class);
                yishouhuo.putExtra("ye",2);
                startActivity(yishouhuo);
                break;
            case R.id.ll_yiwancheng:
                Intent yiwancheng = new Intent(mContext, GHDOrderActivity.class);
                yiwancheng.putExtra("ye",3);
                startActivity(yiwancheng);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Stop polling service
        System.out.println("Stop polling service...");
        PollingUtils.stopPollingService(this, PollingService.class, PollingService.ACTION);
    }

    //    private void getData() {
//        HttpManager.getInstance()
//                .with(mContext)
//                .setObservable(
//                        RetrofitManager
//                                .getService()
//                                .getOrderNumber(PreferenceUtils.getString(MyApplication.mContext, "token","")))
//                .setDataListener(new HttpDataListener<String>() {
//                    @Override
//                    public void onNext(String data) {
//                        if(TextUtils.isEmpty(data)||data.equals("0")){
//                            tvDingdan.setVisibility(View.GONE);
//                        } else {
//                            tvDingdan.setText(data);
//                            tvDingdan.setVisibility(View.VISIBLE);
//                        }
//
//                    }
//                });
//    }
    private void getwode() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getGydShouye(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<WoDeBean>() {
                    @Override
                    public void onNext(WoDeBean data) {
                        woDeBean = data;
                        initView();

                    }
                }, false);
    }

    private void initView() {
//        Glide.with(mContext).load(woDeBean.getPhoto()).into(ivTouxiang);
        tvYue.setText(woDeBean.getMoney() + "");
        tvDfh.setVisibility(woDeBean.getStay_delivery() == 0 ? View.GONE : View.VISIBLE);
        tvDfh.setText(woDeBean.getStay_delivery() + "");
        tvYsh.setVisibility(woDeBean.getAlready_delivery() == 0 ? View.GONE : View.VISIBLE);
        tvYsh.setText(woDeBean.getAlready_delivery() + "");
        tvYwc.setVisibility(woDeBean.getAlready_complete() == 0 ? View.GONE : View.VISIBLE);
        tvYwc.setText(woDeBean.getAlready_complete() + "");
        tvSpll.setText(woDeBean.getLiulan() + "");
        tvSpsc.setText(woDeBean.getSc() + "");
        tvDpgz.setText(woDeBean.getGuanzhu() + "");

//        tvDianpuguanzhu.setText(woDeBean.getGuanzhu());
//        tvLiulanjilu.setText(woDeBean.getLiulan());
//        if (Integer.parseInt(woDeBean.getStay_payment())==0){
//            tvDaifukuan.setVisibility(View.GONE);
//        }else{
//            tvDaifukuan.setVisibility(View.VISIBLE);
//            tvDaifukuan.setText(woDeBean.getStay_payment());
//        }
//        if (Integer.parseInt(woDeBean.getStay_delivery())==0){
//            tvDaifahuo.setVisibility(View.GONE);
//        }else{
//            tvDaifahuo.setVisibility(View.VISIBLE);
//            tvDaifahuo.setText(woDeBean.getStay_delivery());
//        }
//        if (Integer.parseInt(woDeBean.getStay_ecipient())==0){
//            tvDaishouhuo.setVisibility(View.GONE);
//        }else{
//            tvDaishouhuo.setVisibility(View.VISIBLE);
//            tvDaishouhuo.setText(woDeBean.getStay_ecipient());
//        }
//        if (Integer.parseInt(woDeBean.getAlready_ecipient())==0){
//            tvYishouhuo.setVisibility(View.GONE);
//        }else{
//            tvYishouhuo.setVisibility(View.VISIBLE);
//            tvYishouhuo.setText(woDeBean.getAlready_ecipient());
//        }
//        if (Integer.parseInt(woDeBean.getAlready_complete())==0){
//            tvYiwancheng.setVisibility(View.GONE);
//        }else{
//            tvYiwancheng.setVisibility(View.VISIBLE);
//            tvYiwancheng.setText(woDeBean.getAlready_complete());
//        }

//        tvMingzi.setText(woDeBean.getCompany_name());
//        tvZhanghao.setText(woDeBean.getTelephone());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
