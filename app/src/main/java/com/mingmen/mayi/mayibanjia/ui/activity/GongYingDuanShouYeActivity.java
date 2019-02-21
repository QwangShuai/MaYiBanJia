package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.PhoneBean;
import com.mingmen.mayi.mayibanjia.bean.WoDeBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.PhoneDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.ghdingdan.GHDOrderActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.qiangdan.GongYingDuanQiangDanActivity;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.view.CircleImageView;
import com.mingmen.mayi.mayibanjia.utils.AppManager;
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

    @BindView(R.id.iv_touxiang)
    CircleImageView ivTouxiang;
    @BindView(R.id.tv_dianming)
    TextView tvDianming;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.ll_state_qiehuan)
    LinearLayout llStateQiehuan;
    @BindView(R.id.tv_qiehuan)
    TextView tvQiehuan;
    @BindView(R.id.ll_qiehuan)
    LinearLayout llQiehuan;
    @BindView(R.id.tv_yue)
    TextView tvYue;
    @BindView(R.id.tv_daishoukuan)
    TextView tvDaishoukuan;
    @BindView(R.id.ll_yue)
    LinearLayout llYue;
    @BindView(R.id.tv_spll)
    TextView tvSpll;
    @BindView(R.id.tv_spsc)
    TextView tvSpsc;
    @BindView(R.id.tv_dpgz)
    TextView tvDpgz;
    @BindView(R.id.ll_daidabao)
    LinearLayout llDaidabao;
    @BindView(R.id.tv_ddb)
    TextView tvDdb;
    @BindView(R.id.ll_daifahuo)
    LinearLayout llDaifahuo;
    @BindView(R.id.tv_dfh)
    TextView tvDfh;
    @BindView(R.id.ll_yishouhuo)
    LinearLayout llYishouhuo;
    @BindView(R.id.tv_ysh)
    TextView tvYsh;
    @BindView(R.id.ll_daiqueren)
    LinearLayout llDaiqueren;
    @BindView(R.id.tv_dqr)
    TextView tvDqr;
    @BindView(R.id.ll_yiwancheng)
    LinearLayout llYiwancheng;
    @BindView(R.id.tv_ywc)
    TextView tvYwc;
    @BindView(R.id.ll_daiqiangdan)
    LinearLayout llDaiqiangdan;
    @BindView(R.id.tv_dqd)
    TextView tvDqd;
    @BindView(R.id.ll_qiangdanzhong)
    LinearLayout llQiangdanzhong;
    @BindView(R.id.tv_qdz)
    TextView tvQdz;
    @BindView(R.id.ll_qiangdanshibai)
    LinearLayout llQiangdanshibai;
    @BindView(R.id.tv_qdsb)
    TextView tvQdsb;
    @BindView(R.id.ll_qiangdanchenggong)
    LinearLayout llQiangdanchenggong;
    @BindView(R.id.tv_qdcg)
    TextView tvQdcg;
    @BindView(R.id.ll_shangpinguanli)
    LinearLayout llShangpinguanli;
    @BindView(R.id.ll_tejiashangpin)
    LinearLayout llTejiashangpin;
    @BindView(R.id.ll_yonghupingjia)
    LinearLayout llYonghupingjia;
    @BindView(R.id.ll_zhanghuyuanquan)
    LinearLayout llZhanghuyuanquan;
    @BindView(R.id.ll_lianxikefu)
    LinearLayout llLianxikefu;
    @BindView(R.id.ll_bangzhuyufankui)
    LinearLayout llBangzhuyufankui;
    @BindView(R.id.rb_pingfen)
    RatingBar rbPingfen;

    private Context mContext;
    private WoDeBean woDeBean;
    private ConfirmDialog confirmDialog;
    private String type = "0";
    public static GongYingDuanShouYeActivity instance = null;
    private long exitTime = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_gongyingduanshouye;
    }

    @Override
    protected void initData() {
        mContext = GongYingDuanShouYeActivity.this;
        if (!PollingUtils.isOpen) {
            Log.e( "initData: ","aaa" );
            PollingUtils.startPollingService(mContext, 1, PollingService.class, PollingService.ACTION);
        }
        if (PreferenceUtils.getInt(mContext, "random_id", 1) == 3) {
            llQiehuan.setVisibility(View.GONE);
        }
        instance = this;
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        getwode();
//        getData();
    }


    @OnClick({ R.id.ll_shangpinguanli, R.id.ll_yonghupingjia, R.id.ll_tejiashangpin,R.id.ll_daidabao,R.id.ll_daiqueren,
            R.id.ll_yue, R.id.ll_daifahuo, R.id.ll_yishouhuo, R.id.ll_yiwancheng, R.id.ll_qiehuan, R.id.iv_touxiang, R.id.tv_dianming,
            R.id.ll_state_qiehuan,R.id.ll_daiqiangdan,R.id.ll_qiangdanzhong,R.id.ll_qiangdanshibai,R.id.ll_qiangdanchenggong,
    R.id.ll_zhanghuyuanquan,R.id.ll_lianxikefu,R.id.ll_bangzhuyufankui})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_zhanghuyuanquan:
                Intent it = new Intent(mContext,WoDeZhangHuActivity.class);
                startActivity(it);
                break;
            case R.id.ll_lianxikefu:
                getPhone();
                break;
            case R.id.ll_bangzhuyufankui:

                break;
            case R.id.ll_daiqiangdan:
                Intent daiqiangdan = new Intent(mContext, GongYingDuanQiangDanActivity.class);
                daiqiangdan.putExtra("ye",1);
                startActivity(daiqiangdan);
                break;
            case R.id.ll_qiangdanzhong:
                Intent qiangdanzhong = new Intent(mContext, GongYingDuanQiangDanActivity.class);
                qiangdanzhong.putExtra("ye",2);
                startActivity(qiangdanzhong);
                break;
            case R.id.ll_qiangdanshibai:
                Intent qiangdanshibai = new Intent(mContext, GongYingDuanQiangDanActivity.class);
                qiangdanshibai.putExtra("ye",3);
                startActivity(qiangdanshibai);
                break;
            case R.id.ll_qiangdanchenggong:
                Intent qiangdanchenggong = new Intent(mContext, GongYingDuanQiangDanActivity.class);
                qiangdanchenggong.putExtra("ye",4);
                startActivity(qiangdanchenggong);
                break;
            case R.id.ll_shangpinguanli:
                Intent sp = new Intent(mContext, ShangPinGuanLiActivity.class);
                sp.putExtra("goods", "0");
                startActivity(sp);
                break;
            case R.id.ll_yonghupingjia:
                break;
            case R.id.ll_tejiashangpin:
                Intent tejia = new Intent(mContext, ShangPinGuanLiActivity.class);
                tejia.putExtra("goods", "1");
                startActivity(tejia);
                break;
            case R.id.ll_yue://余额
                Jump_intent(YueActivity.class, new Bundle());
                break;
            case R.id.ll_daidabao:
                Intent daidabao = new Intent(mContext, GHDOrderActivity.class);
                daidabao.putExtra("ye", 1);
                startActivity(daidabao);
                break;
            case R.id.ll_daifahuo:
                Intent daifahuo = new Intent(mContext, GHDOrderActivity.class);
                daifahuo.putExtra("ye", 2);
                startActivity(daifahuo);
                break;
            case R.id.ll_yishouhuo:
                Intent yishouhuo = new Intent(mContext, GHDOrderActivity.class);
                yishouhuo.putExtra("ye", 3);
                startActivity(yishouhuo);
                break;
            case R.id.ll_daiqueren:
                Intent daiqueren = new Intent(mContext, GHDOrderActivity.class);
                daiqueren.putExtra("ye", 4);
                startActivity(daiqueren);
                break;
            case R.id.ll_yiwancheng:
                Intent yiwancheng = new Intent(mContext, GHDOrderActivity.class);
                yiwancheng.putExtra("ye", 5);
                startActivity(yiwancheng);
                break;
            case R.id.ll_qiehuan:
                qiehuan();
                break;
            case R.id.iv_touxiang:
                Intent shezhi = new Intent(mContext, GongYingDuanSheZhiActivity.class);
                startActivity(shezhi);
                finish();
                break;
            case R.id.tv_dianming:
                Intent shezhi2 = new Intent(mContext, GongYingDuanSheZhiActivity.class);
                startActivity(shezhi2);
                finish();
                break;
            case R.id.ll_state_qiehuan:
                confirmDialog.showDialog("是否切换营业状态");
                confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmDialog.dismiss();
                        qiehuanState();
                    }
                });
                confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmDialog.dismiss();
                    }
                });
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Stop polling service
        Log.e("onDestroy: ","服务开始停止" );
        PollingUtils.isOpen = false;
        PollingUtils.stopPollingService(mContext, PollingService.class, PollingService.ACTION);
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
        Glide.with(mContext).load(woDeBean.getPhoto()).into(ivTouxiang);
        tvDianming.setText(woDeBean.getCompany_name() + "");
        type = woDeBean.getBusiness_state() + "";
        tvState.setText(type.equals("0") ? "营业中" : "休息中");
        tvYue.setText(woDeBean.getMoney() + "");
        tvDfh.setVisibility(woDeBean.getStay_delivery() == 0 ? View.GONE : View.VISIBLE);
        tvDfh.setText(woDeBean.getStay_delivery() + "");
        tvYsh.setVisibility(woDeBean.getAlready_delivery() == 0 ? View.GONE : View.VISIBLE);
        tvYsh.setText(woDeBean.getAlready_delivery() + "");
        tvYwc.setVisibility(woDeBean.getAlready_complete() == 0 ? View.GONE : View.VISIBLE);
        tvYwc.setText(woDeBean.getAlready_complete() + "");
        tvDqr.setVisibility(woDeBean.getWait_qr()==0?View.GONE : View.VISIBLE);
        tvDqr.setText(woDeBean.getWait_qr()+"");
        tvDdb.setVisibility(woDeBean.getWait_db()==0?View.GONE : View.VISIBLE);
        tvDdb.setText(woDeBean.getWait_db()+"");
        tvQdz.setVisibility(woDeBean.getQiangdan_z()==0?View.GONE : View.VISIBLE);
        tvQdz.setText(woDeBean.getQiangdan_z()+"");
        tvQdcg.setVisibility(woDeBean.getQiangdan_cg()==0?View.GONE : View.VISIBLE);
        tvQdz.setText(woDeBean.getQiangdan_cg()+"");
        tvQdsb.setVisibility(woDeBean.getQiangdan_sbai()==0?View.GONE : View.VISIBLE);
        tvQdsb.setText(woDeBean.getQiangdan_sbai()+"");
        tvDaishoukuan.setVisibility(woDeBean.getWait_money()==0?View.GONE : View.VISIBLE);
        tvDaishoukuan.setText("待收款 "+woDeBean.getWait_money()+"");
        tvSpll.setText(woDeBean.getLiulan() + "");
        tvSpsc.setText(woDeBean.getSc() + "");
        tvDpgz.setText(woDeBean.getGuanzhu() + "");
        tvDqd.setVisibility(woDeBean.getQiangdan()==0?View.GONE : View.VISIBLE);
        tvDqd.setText(woDeBean.getQiangdan()+ "");
        rbPingfen.setRating(woDeBean.getEvaluation());
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

    @Override
    protected void onResume() {
        super.onResume();
        getwode();
    }

    private void qiehuan() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .qiehuan(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String bean) {
                        Intent it = new Intent(mContext, MainActivity.class);
                        it.putExtra("tosome", 3);
                        startActivity(it);
                        AppManager.getAppManager().finishActivity();
                    }
                });
    }

    private void qiehuanState() {
        final String state = type.equals("0") ? "1" : "0";
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .changeYingyeState(PreferenceUtils.getString(MyApplication.mContext, "token", ""), state))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String bean) {
                        Log.e("我的数据", bean);
                        tvState.setText(bean.equals("0") ? "营业中" : "休息中");
                        type = bean;
                    }
                });
    }
    public void getPhone() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getPhone(PreferenceUtils.getString(MyApplication.mContext, "token", ""), ""))
                .setDataListener(new HttpDataListener<PhoneBean>() {

                    @Override
                    public void onNext(PhoneBean bean) {
                        new PhoneDialog().setData(bean.getKefu(),
                                bean.getYewuyuan(), bean.getKefuphone(), bean.getYwyphone()).show(getSupportFragmentManager());
                    }
                }, false);

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        exit();
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            AppManager.getAppManager().AppExit(mContext);
//            System.exit(0);
        }
    }

}
