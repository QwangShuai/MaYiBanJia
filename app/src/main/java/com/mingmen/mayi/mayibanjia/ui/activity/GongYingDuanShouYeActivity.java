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
import com.mingmen.mayi.mayibanjia.bean.ZhangHuRenZhengBean;
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
import com.mingmen.mayi.mayibanjia.utils.GlideUtils;
import com.mingmen.mayi.mayibanjia.utils.JumpUtil;
import com.mingmen.mayi.mayibanjia.utils.PollingService;
import com.mingmen.mayi.mayibanjia.utils.PollingUtils;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

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
    @BindView(R.id.ll_yfsz_gyd)
    LinearLayout ll_yfsz_gyd;
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
    @BindView(R.id.ll_yonghupingjia)
    LinearLayout llYonghupingjia;
    @BindView(R.id.ll_zhanghuyuanquan)
    LinearLayout llZhanghuyuanquan;
    @BindView(R.id.ll_lianxikefu)
    LinearLayout llLianxikefu;
    @BindView(R.id.ll_bangzhuyufankui)
    LinearLayout llBangzhuyufankui;
    @BindView(R.id.ll_tianjiashangpin)
    LinearLayout llTianjiashangpin;
    @BindView(R.id.rb_pingfen)
    RatingBar rbPingfen;
    @BindView(R.id.tv_tixian)
    TextView tvTixian;
    @BindView(R.id.tv_cwbb)
    TextView tvCwbb;
    @BindView(R.id.ll_shangjia)
    LinearLayout llShangjia;
    @BindView(R.id.tv_sj)
    TextView tvSj;
    @BindView(R.id.ll_xiajia)
    LinearLayout llXiajia;
    @BindView(R.id.tv_xj)
    TextView tvXj;
    @BindView(R.id.ll_daishenhe)
    LinearLayout llDaishenhe;
    @BindView(R.id.tv_dsh)
    TextView tvDsh;
    @BindView(R.id.ll_yinhangzhanghao)
    LinearLayout llYinhangzhanghao;
    @BindView(R.id.ll_zizhirenzheng)
    LinearLayout llZizhirenzheng;

    private Context mContext;
    private WoDeBean woDeBean;
    private ConfirmDialog confirmDialog;
    private String type = "0";
    private String close_type = "0";
    public static GongYingDuanShouYeActivity instance = null;
    private long exitTime = 0;
    private String sh_state = "待审核";
    private String yue = "0";

    @Override
    public int getLayoutId() {
        return R.layout.activity_gongyingduanshouye;
    }

    @Override
    protected void initData() {
        mContext = GongYingDuanShouYeActivity.this;
        instance = this;
        if (!PollingUtils.isOpen) {
            PollingUtils.startPollingService(GongYingDuanShouYeActivity.instance, 1, PollingService.class, PollingService.ACTION);
        }
//        if (PreferenceUtils.getInt(mContext, "random_id", 1) == 3) {
//            llQiehuan.setVisibility(View.GONE);
//        }
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        getwode();
        getRenzheng();
//        getData();
    }


    @OnClick({R.id.rl_shezhi, R.id.ll_yonghupingjia,R.id.tv_gy,
            R.id.ll_daidabao, R.id.ll_daiqueren, R.id.tv_yue, R.id.ll_daifahuo,
            R.id.ll_yishouhuo, R.id.ll_yiwancheng, R.id.ll_qiehuan, R.id.iv_touxiang,
            R.id.tv_dianming, R.id.ll_state_qiehuan, R.id.ll_daiqiangdan, R.id.ll_qiangdanzhong,
            R.id.ll_qiangdanshibai, R.id.ll_qiangdanchenggong, R.id.ll_zhanghuyuanquan,
            R.id.ll_lianxikefu, R.id.ll_bangzhuyufankui, R.id.ll_yinhangzhanghao,
            R.id.ll_zizhirenzheng, R.id.ll_cwbb, R.id.ll_tixian, R.id.ll_tianjiashangpin,
            R.id.ll_shangjia,R.id.ll_xiajia,R.id.ll_daishenhe,R.id.ll_tejiashangpin_gyd,
            R.id.ll_putongshangpin_gyd,R.id.ll_yfsz_gyd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_gy:
//                qiehuan();
                break;
            case R.id.rl_shezhi:
                Bundle bundle = new Bundle();
                bundle.putString("khd","gy");
                Jump_intent(SqscShezhiActivity.class,bundle);
                break;
            case R.id.ll_tianjiashangpin:
                //添加商品
                Intent intent = new Intent(mContext, FaBuShangPinActivity.class);
                intent.putExtra("state", "0");
                intent.putExtra("goods", "0");
                startActivity(intent);
                break;
            case R.id.ll_shangjia:
                Intent  it_shangjia = new Intent(mContext, ShangPinGuanLiActivity.class);
                it_shangjia.putExtra("to_gl",1);
                it_shangjia.putExtra("goods", "0");
                startActivity(it_shangjia);
                break;
            case R.id.ll_xiajia:
                Intent  it_xiajia = new Intent(mContext, ShangPinGuanLiActivity.class);
                it_xiajia.putExtra("to_gl",2);
                it_xiajia.putExtra("goods", "0");
                startActivity(it_xiajia);
                break;
            case R.id.ll_tejiashangpin_gyd:
                getDianpuRenzheng("1");
                break;
            case R.id.ll_putongshangpin_gyd:
                getDianpuRenzheng("0");
                break;
            case R.id.ll_daishenhe:
                Intent  it_daishenhe = new Intent(mContext, ShangPinGuanLiActivity.class);
                it_daishenhe.putExtra("to_gl",3);
                it_daishenhe.putExtra("goods", "0");
                startActivity(it_daishenhe);
                break;

            case R.id.ll_zizhirenzheng:
//                Intent it_zz = new Intent(mContext, ZiZhiRenZhengActivity.class);
//                Intent it_zz = new Intent(mContext, ZzrzGydActivity.class);
//                it_zz.putExtra("id", "");
//                it_zz.putExtra("state", sh_state);
//                it_zz.putExtra("yemian", "1");
//                startActivity(it_zz);
                break;
            case R.id.ll_yinhangzhanghao:
                Jump_intent(YinHangKaActivity.class, new Bundle());
                break;
            case R.id.ll_zhanghuyuanquan:
                Intent it = new Intent(mContext, WoDeZhangHuActivity.class);
                startActivity(it);
                break;
            case R.id.ll_lianxikefu:
                getPhone();
                break;
            case R.id.ll_bangzhuyufankui:
                startActivity(new Intent(mContext, YiJianFanKuiActivity.class));
                break;
            case R.id.ll_yfsz_gyd://运费设置
                startActivity(new Intent(mContext, YunfeiShezhiActivity.class));
                break;
            case R.id.ll_daiqiangdan:
                Intent daiqiangdan = new Intent(mContext, GongYingDuanQiangDanActivity.class);
                daiqiangdan.putExtra("ye", 1);
                startActivity(daiqiangdan);
                break;
            case R.id.ll_qiangdanzhong:
                Intent qiangdanzhong = new Intent(mContext, GongYingDuanQiangDanActivity.class);
                qiangdanzhong.putExtra("ye", 2);
                startActivity(qiangdanzhong);
                break;
            case R.id.ll_qiangdanshibai:
                Intent qiangdanshibai = new Intent(mContext, GongYingDuanQiangDanActivity.class);
                qiangdanshibai.putExtra("ye", 3);
                startActivity(qiangdanshibai);
                break;
            case R.id.ll_qiangdanchenggong:
                Intent qiangdanchenggong = new Intent(mContext, GongYingDuanQiangDanActivity.class);
                qiangdanchenggong.putExtra("ye", 4);
                startActivity(qiangdanchenggong);
                break;
            case R.id.ll_yonghupingjia:
                //查看评价
                Jump_intent(YongHuPingJiaActivity.class,new Bundle());
                break;
            case R.id.tv_yue://余额
                Jump_intent(YueActivity.class, new Bundle());
                break;
            case R.id.ll_tixian://提现
                Bundle tixian = new Bundle();
                tixian.putString("yue", yue);
                Jump_intent(TiXianActivity.class, tixian);
                break;
            case R.id.ll_cwbb://财务报表
                Jump_intent(JiaoYiLiuShuiActivity.class, new Bundle());
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
//                qiehuan();
                String tiShi = "";
                if (close_type.equals("0")) {
                    tiShi = "是否关闭食时达";
                } else {
                    tiShi = "是否开启食时达";
                }
                confirmDialog.showDialog(tiShi);
                confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmDialog.dismiss();
                        qiehuanSsd();
                    }
                });
                confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmDialog.dismiss();
                    }
                });
                break;
            case R.id.iv_touxiang:
//                Intent shezhi = new Intent(mContext, SqscShezhiActivity.class);
//                shezhi.putExtra("khd","gy");
//                startActivity(shezhi);
//                finish();
                break;
            case R.id.tv_dianming:
                Intent shezhi2 = new Intent(mContext, SqscShezhiActivity.class);
                shezhi2.putExtra("khd","gy");
                startActivity(shezhi2);
//                finish();
                break;
            case R.id.ll_state_qiehuan:
                String close_tiShi = "";
                if (type.equals("0")) {
                    close_tiShi = "是否关店";
                } else {
                    close_tiShi = "是否开始营业";
                }
                confirmDialog.showDialog(close_tiShi);
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
        Log.e("onDestroy: ", "服务开始停止");
        PollingUtils.isOpen = false;
        PollingUtils.stopPollingService(GongYingDuanShouYeActivity.instance, PollingService.class, PollingService.ACTION);
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
        if(StringUtil.isValid(woDeBean.getIstrue_ssd())&&"0".equals(woDeBean.getIstrue_ssd())){
            llQiehuan.setVisibility(View.VISIBLE);
        } else {
            llQiehuan.setVisibility(View.GONE);
        }
        GlideUtils.cachePhoto(mContext,ivTouxiang,woDeBean.getPhoto());
        tvDianming.setText(woDeBean.getCompany_name() + "");
        type = woDeBean.getBusiness_state() + "";
        tvState.setText(type.equals("0") ? "营业中" : "已关店");
        tvYue.setText(woDeBean.getMoney() + "");
        yue = woDeBean.getMoney() + "";
        if(StringUtil.isValid(woDeBean.getRealtime())&&woDeBean.getRealtime().equals("0")){
            tvQiehuan.setText("已开启食时达");
            close_type = "0";
        } else {
            tvQiehuan.setText("已关闭食时达");
            close_type = "1";
        }
        tvDfh.setVisibility(woDeBean.getStay_delivery() == 0 ? View.GONE : View.VISIBLE);
        tvDfh.setText(woDeBean.getStay_delivery() + "");
        tvYsh.setVisibility(woDeBean.getAlready_delivery() == 0 ? View.GONE : View.VISIBLE);
        tvYsh.setText(woDeBean.getAlready_delivery() + "");
        tvYwc.setVisibility(woDeBean.getAlready_complete() == 0 ? View.GONE : View.VISIBLE);
        tvYwc.setText(woDeBean.getAlready_complete() + "");
        tvDqr.setVisibility(woDeBean.getWait_qr() == 0 ? View.GONE : View.VISIBLE);
        tvDqr.setText(woDeBean.getWait_qr() + "");
        tvDdb.setVisibility(woDeBean.getWait_db() == 0 ? View.GONE : View.VISIBLE);
        tvDdb.setText(woDeBean.getWait_db() + "");
        tvQdz.setVisibility(woDeBean.getQiangdan_z() == 0 ? View.GONE : View.VISIBLE);
        tvQdz.setText(woDeBean.getQiangdan_z() + "");
        tvQdcg.setVisibility(woDeBean.getQiangdan_cg() == 0 ? View.GONE : View.VISIBLE);
        tvQdz.setText(woDeBean.getQiangdan_cg() + "");
        tvQdsb.setVisibility(woDeBean.getQiangdan_sbai() == 0 ? View.GONE : View.VISIBLE);
        tvQdsb.setText(woDeBean.getQiangdan_sbai() + "");
        tvDaishoukuan.setVisibility(woDeBean.getWait_money() == 0 ? View.GONE : View.VISIBLE);
        tvDaishoukuan.setText("待收款 " + woDeBean.getWait_money() + "");
        tvSpll.setText(woDeBean.getLiulan() + "");
        tvSpsc.setText(woDeBean.getSc() + "");
        tvDpgz.setText(woDeBean.getGuanzhu() + "");
        tvDqd.setVisibility(woDeBean.getQiangdan() == 0 ? View.GONE : View.VISIBLE);
        tvDqd.setText(woDeBean.getQiangdan() + "");
        tvSj.setVisibility(woDeBean.getShelves() == 0 ? View.GONE : View.VISIBLE);
        tvSj.setText(woDeBean.getShelves() + "");
        tvXj.setVisibility(woDeBean.getSold_out() == 0 ? View.GONE : View.VISIBLE);
        tvXj.setText(woDeBean.getSold_out() + "");
        tvDsh.setVisibility(woDeBean.getWait_audit() == 0 ? View.GONE : View.VISIBLE);
        tvDsh.setText(woDeBean.getWait_audit() + "");
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
        getRenzheng();
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
                        tvState.setText(bean.equals("0") ? "营业中" : "已关店");
                        type = bean;
                    }
                });
    }

    private void qiehuanSsd() {
        final String state = close_type.equals("0") ? "1" : "0";
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .qiehuanSsd(PreferenceUtils.getString(MyApplication.mContext, "token", ""), state))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String bean) {
                        Log.e("我的数据", bean);
                        tvQiehuan.setText(bean.equals("0") ? "已开启食时达" : "已关闭食时达");
                        close_type = bean;
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

    private void getRenzheng() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getGerenrenzheng(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<ZhangHuRenZhengBean>() {
                    @Override
                    public void onNext(ZhangHuRenZhengBean bean) {
                        sh_state = bean.getZz();
                        Log.e("onNext: ", bean.getZz());
//                        tvShenhe.setText(bean.getZz().toString());
                    }
                });
    }

    private void getDianpuRenzheng(final String canshu) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .yanzhengDianpuWeigui(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        Intent  it = new Intent(mContext, ShangPinGuanLiActivity.class);
                        it.putExtra("goods",canshu);
                        startActivity(it);
                    }
                });
    }

}
