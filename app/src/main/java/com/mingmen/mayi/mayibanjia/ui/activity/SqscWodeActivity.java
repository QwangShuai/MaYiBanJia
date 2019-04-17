package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.SqscWodeBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dingdan.DingDanActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.ghdingdan.GHDOrderActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.qiangdan.GongYingDuanQiangDanActivity;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.view.CircleImageView;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SqscWodeActivity extends BaseActivity {

    @BindView(R.id.rl_shezhi)
    RelativeLayout rlShezhi;
    @BindView(R.id.iv_touxiang)
    CircleImageView ivTouxiang;
    @BindView(R.id.tv_dianming)
    TextView tvDianming;
    @BindView(R.id.rb_pingfen)
    RatingBar rbPingfen;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.ll_state_qiehuan)
    LinearLayout llStateQiehuan;
    @BindView(R.id.tv_sqsc)
    TextView tvSqsc;
    @BindView(R.id.tv_qiehuan)
    TextView tvQiehuan;
    @BindView(R.id.ll_qiehuan)
    LinearLayout llQiehuan;
    @BindView(R.id.tv_yue)
    TextView tvYue;
    @BindView(R.id.tv_tixian)
    TextView tvTixian;
    @BindView(R.id.tv_daishoukuan)
    TextView tvDaishoukuan;
    @BindView(R.id.tv_cwbb)
    TextView tvCwbb;
    @BindView(R.id.ll_yue)
    LinearLayout llYue;
    @BindView(R.id.tv_spll)
    TextView tvSpll;
    @BindView(R.id.ll_spll)
    LinearLayout llSpll;
    @BindView(R.id.tv_spsc)
    TextView tvSpsc;
    @BindView(R.id.ll_spsc)
    LinearLayout llSpsc;
    @BindView(R.id.tv_dpgz)
    TextView tvDpgz;
    @BindView(R.id.ll_spgz)
    LinearLayout llSpgz;
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
    @BindView(R.id.ll_dd)
    LinearLayout llDd;
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
    @BindView(R.id.ll_tianjiashangpin)
    LinearLayout llTianjiashangpin;
    @BindView(R.id.ll_tejiashangpin_gyd)
    LinearLayout llTejiashangpinGyd;
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
    @BindView(R.id.ll_shenheshibai)
    LinearLayout llShenheshibai;
    @BindView(R.id.tv_shsb)
    TextView tvShsb;
    @BindView(R.id.tv_spll_ct)
    TextView tvSpllCt;
    @BindView(R.id.tv_spsc_ct)
    TextView tvSpscCt;
    @BindView(R.id.tv_dpgz_ct)
    TextView tvDpgzCt;
    @BindView(R.id.facaigou)
    ImageView facaigou;
    @BindView(R.id.rl_facaigou)
    RelativeLayout rlFacaigou;
    @BindView(R.id.daitijiao)
    ImageView daitijiao;
    @BindView(R.id.tv_daitijiao)
    TextView tvDaitijiao;
    @BindView(R.id.rl_daitijiao)
    RelativeLayout rlDaitijiao;
    @BindView(R.id.daishenhe)
    ImageView daishenhe;
    @BindView(R.id.tv_daishenhe)
    TextView tvDaishenhe;
    @BindView(R.id.rl_daishenhe)
    RelativeLayout rlDaishenhe;
    @BindView(R.id.weitongguo)
    ImageView weitongguo;
    @BindView(R.id.tv_weitongguo)
    TextView tvWeitongguo;
    @BindView(R.id.rl_weitongguo)
    RelativeLayout rlWeitongguo;
    @BindView(R.id.tongguo)
    ImageView tongguo;
    @BindView(R.id.tv_tongguo)
    TextView tvTongguo;
    @BindView(R.id.rl_tongguo)
    RelativeLayout rlTongguo;
    @BindView(R.id.daifukuan_ct)
    ImageView daifukuanCt;
    @BindView(R.id.tv_daifukuan_ct)
    TextView tvDaifukuanCt;
    @BindView(R.id.iv_wqx_dfk_ct)
    ImageView ivWqxDfkCt;
    @BindView(R.id.rl_daifukuan_ct)
    RelativeLayout rlDaifukuanCt;
    @BindView(R.id.daifahuo_ct)
    ImageView daifahuoCt;
    @BindView(R.id.tv_daifahuo_ct)
    TextView tvDaifahuoCt;
    @BindView(R.id.iv_wqx_dfh_ct)
    ImageView ivWqxDfhCt;
    @BindView(R.id.rl_daifahuo_ct)
    RelativeLayout rlDaifahuoCt;
    @BindView(R.id.daishouhuo_ct)
    ImageView daishouhuoCt;
    @BindView(R.id.tv_daishouhuo_ct)
    TextView tvDaishouhuoCt;
    @BindView(R.id.iv_wqx_dsh_ct)
    ImageView ivWqxDshCt;
    @BindView(R.id.rl_daishouhuo_ct)
    RelativeLayout rlDaishouhuoCt;
    @BindView(R.id.yishouhuo_ct)
    ImageView yishouhuoCt;
    @BindView(R.id.tv_yishouhuo_ct)
    TextView tvYishouhuoCt;
    @BindView(R.id.iv_wqx_ysh_ct)
    ImageView ivWqxYshCt;
    @BindView(R.id.rl_yishouhuo_ct)
    RelativeLayout rlYishouhuoCt;
    @BindView(R.id.yiwancheng_ct)
    ImageView yiwanchengCt;
    @BindView(R.id.tv_yiwancheng_ct)
    TextView tvYiwanchengCt;
    @BindView(R.id.iv_wqx_ywc_ct)
    ImageView ivWqxYwcCt;
    @BindView(R.id.rl_yiwancheng_ct)
    RelativeLayout rlYiwanchengCt;
    @BindView(R.id.ll_cg)
    LinearLayout llCg;
    @BindView(R.id.ll_fcg)
    LinearLayout llFcg;

    private String yue = "0";
    private Context mContext;
    private String type = "0";
    private String close_type = "0";
    private long exitTime = 0;
    private ConfirmDialog confirmDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_sqsc_wode;
    }

    @Override
    protected void initData() {
        mContext = this;
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));

        getShow();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_shezhi, R.id.ll_state_qiehuan, R.id.ll_qiehuan, R.id.tv_yue, R.id.tv_tixian,
            R.id.tv_daishoukuan, R.id.tv_cwbb, R.id.tv_spll, R.id.tv_spsc, R.id.tv_dpgz,
            R.id.ll_daiqiangdan, R.id.ll_qiangdanzhong,
            R.id.ll_qiangdanshibai, R.id.ll_qiangdanchenggong, R.id.ll_tianjiashangpin,
            R.id.ll_shangjia, R.id.ll_xiajia, R.id.ll_daishenhe, R.id.ll_shenheshibai,
            R.id.rl_facaigou, R.id.rl_daitijiao, R.id.rl_daishenhe, R.id.rl_weitongguo,
            R.id.rl_tongguo, R.id.rl_daifukuan_ct, R.id.rl_daifahuo_ct, R.id.rl_daishouhuo_ct,
            R.id.rl_yishouhuo_ct, R.id.rl_yiwancheng_ct, R.id.ll_daidabao, R.id.ll_tejiashangpin_gyd,
            R.id.ll_daifahuo, R.id.ll_yishouhuo, R.id.ll_daiqueren, R.id.ll_yiwancheng, R.id.rl_tj,
            R.id.rl_qbcp, R.id.rl_gwc, R.id.ll_putongshangpin_gyd, R.id.btn_go_main})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_shezhi:
                Jump_intent(SqscShezhiActivity.class,new Bundle());
                break;
            case R.id.btn_go_main:
                Jump_intent(MainActivity.class,new Bundle());
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
            case R.id.ll_qiehuan:
                String tiShi = "";
                if (close_type.equals("0")) {
                    tiShi = "是否关闭实时达";
                } else {
                    tiShi = "是否开启实时达";
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
            case R.id.tv_yue:
                Jump_intent(YueActivity.class, new Bundle());
                break;
            case R.id.ll_putongshangpin_gyd:
                Intent  it_pt = new Intent(mContext, ShangPinGuanLiActivity.class);
                it_pt.putExtra("goods","0");
                startActivity(it_pt);
                break;
            case R.id.tv_tixian:
                Bundle bundle = new Bundle();
                bundle.putString("yue", yue);
                Jump_intent(TiXianActivity.class, bundle);
                break;
            case R.id.tv_daishoukuan:
                break;
            case R.id.tv_cwbb:
                Jump_intent(JiaoYiLiuShuiActivity.class, new Bundle());
                break;
            case R.id.tv_spll:
                break;
            case R.id.tv_spsc:
                break;
            case R.id.tv_dpgz:
                break;
            case R.id.rl_tj:
                Bundle bd = new Bundle();
                bd.putString("tejia","1");
                Jump_intent(QuanBuCaiPinActivity.class, bd);
                break;
            case R.id.rl_qbcp:
                Jump_intent(QuanBuCaiPinActivity.class, new Bundle());
                break;
            case R.id.rl_gwc:
                Jump_intent(GouWuCheActivity.class, new Bundle());
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
            case R.id.ll_tianjiashangpin://添加商品
                //添加商品
                Intent intent = new Intent(mContext, FaBuShangPinActivity.class);
                intent.putExtra("state", "0");
                intent.putExtra("goods", "0");
                startActivity(intent);
                break;
            case R.id.ll_tejiashangpin_gyd://特价商品
                Intent it_tj = new Intent(mContext, ShangPinGuanLiActivity.class);
                it_tj.putExtra("goods", "1");
                startActivity(it_tj);
                break;
            case R.id.ll_shangjia://上架
                Intent it_shangjia = new Intent(mContext, ShangPinGuanLiActivity.class);
                it_shangjia.putExtra("to_gl", 1);
                it_shangjia.putExtra("goods", "0");
                startActivity(it_shangjia);
                break;
            case R.id.ll_xiajia://下架
                Intent it_xiajia = new Intent(mContext, ShangPinGuanLiActivity.class);
                it_xiajia.putExtra("to_gl", 2);
                it_xiajia.putExtra("goods", "0");
                startActivity(it_xiajia);
                break;
            case R.id.ll_daishenhe://
                Intent it_daishenhe = new Intent(mContext, ShangPinGuanLiActivity.class);
                it_daishenhe.putExtra("to_gl", 3);
                it_daishenhe.putExtra("goods", "0");
                startActivity(it_daishenhe);
                break;
            case R.id.ll_shenheshibai:
                Intent it_shenheshibai = new Intent(mContext, ShangPinGuanLiActivity.class);
                it_shenheshibai.putExtra("to_gl", 4);
                it_shenheshibai.putExtra("goods", "0");
                startActivity(it_shenheshibai);
                break;
            case R.id.rl_facaigou:
                Intent caigouintent = new Intent(mContext, FCGDiQuXuanZeActivity.class);
                startActivity(caigouintent);
                break;
            case R.id.rl_daitijiao:
                Intent daitijiao = new Intent(mContext, CaiGouDanActivity.class);
                daitijiao.putExtra("type", "904");
                mContext.startActivity(daitijiao);
                break;
            case R.id.rl_daishenhe:
                Intent daishenhe = new Intent(mContext, CaiGouDanActivity.class);
                daishenhe.putExtra("type", "902");
                mContext.startActivity(daishenhe);
                break;
            case R.id.rl_weitongguo:
                Intent weitongguo = new Intent(mContext, CaiGouDanActivity.class);
                weitongguo.putExtra("type", "903");
                mContext.startActivity(weitongguo);
                break;
            case R.id.rl_tongguo:
                Intent tongguo = new Intent(mContext, CaiGouDanActivity.class);
                tongguo.putExtra("type", "901");
                mContext.startActivity(tongguo);
                break;
            case R.id.rl_daifukuan_ct:
                Bundle daifukuan = new Bundle();
                daifukuan.putInt("to_shop", 1);
                Jump_intent(DingDanActivity.class, daifukuan);
                break;
            case R.id.rl_daifahuo_ct:
                Bundle daifahuo_ct = new Bundle();
                daifahuo_ct.putInt("to_shop", 2);
                Jump_intent(DingDanActivity.class, daifahuo_ct);
                break;
            case R.id.rl_daishouhuo_ct:
                Bundle daishouhuo = new Bundle();
                daishouhuo.putInt("to_shop", 3);
                Jump_intent(DingDanActivity.class, daishouhuo);
                break;
            case R.id.rl_yishouhuo_ct:
                Bundle yishouhuo_ct = new Bundle();
                yishouhuo_ct.putInt("to_shop", 4);
                Jump_intent(DingDanActivity.class, yishouhuo_ct);
                break;
            case R.id.rl_yiwancheng_ct:
                Bundle yiwancheng_ct = new Bundle();
                yiwancheng_ct.putInt("to_shop", 5);
                Jump_intent(DingDanActivity.class, yiwancheng_ct);
                break;
        }
    }

    private void getShow() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getSqscWode(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<SqscWodeBean>() {
                    @Override
                    public void onNext(SqscWodeBean data) {
                        initView(data);
                    }
                });
    }

    public void initView(SqscWodeBean bean) {
        tvSpsc.setText(bean.getSc() + "");
        tvDpgz.setText(bean.getGuanzhu() + "");
        tvSpll.setText(bean.getLiulan() + "");
        Glide.with(mContext).load(bean.getPhoto()).into(ivTouxiang);
        tvDianming.setText(bean.getCompany_name() + "");
        type = bean.getBusiness_state() + "";
        tvState.setText(type.equals("0") ? "营业中" : "已关店");
        tvYue.setText(bean.getMoney() + "");
        yue = bean.getMoney() + "";
        if (StringUtil.isValid(bean.getRealtime()) && bean.getRealtime().equals("0")) {
            tvQiehuan.setText("已开启实时达");
            close_type = "0";
        } else {
            tvQiehuan.setText("已关闭实时达");
            close_type = "1";
        }
        tvDfh.setVisibility(bean.getWait_fh() == 0 ? View.GONE : View.VISIBLE);
        tvDfh.setText(bean.getWait_fh() + "");
        tvYsh.setVisibility(bean.getAlready_delivery() == 0 ? View.GONE : View.VISIBLE);
        tvYsh.setText(bean.getAlready_delivery() + "");
        tvYwc.setVisibility(bean.getAlready_complete() == 0 ? View.GONE : View.VISIBLE);
        tvYwc.setText(bean.getAlready_complete() + "");
        tvDqr.setVisibility(bean.getWait_qr() == 0 ? View.GONE : View.VISIBLE);
        tvDqr.setText(bean.getWait_qr() + "");
        tvDdb.setVisibility(bean.getWait_db() == 0 ? View.GONE : View.VISIBLE);
        tvDdb.setText(bean.getWait_db() + "");
        tvQdz.setVisibility(bean.getQiangdan_z() == 0 ? View.GONE : View.VISIBLE);
        tvQdz.setText(bean.getQiangdan_z() + "");
        tvQdcg.setVisibility(bean.getQiangdan_cg() == 0 ? View.GONE : View.VISIBLE);
        tvQdz.setText(bean.getQiangdan_cg() + "");
        tvQdsb.setVisibility(bean.getQiangdan_sbai() == 0 ? View.GONE : View.VISIBLE);
        tvQdsb.setText(bean.getQiangdan_sbai() + "");
//        tvDaishoukuan.setVisibility(bean.getWait_money() == 0 ? View.GONE : View.VISIBLE);
        tvDaishoukuan.setText("待收款 " + bean.getWait_money() + "");
        tvSpll.setText(bean.getLiulan() + "");
        tvSpsc.setText(bean.getSc() + "");
        tvDpgz.setText(bean.getGuanzhu() + "");
        tvDqd.setVisibility(bean.getQiangdan() == 0 ? View.GONE : View.VISIBLE);
        tvDqd.setText(bean.getQiangdan() + "");
        tvSj.setVisibility(bean.getShelves() == 0 ? View.GONE : View.VISIBLE);
        tvSj.setText(bean.getShelves() + "");
        tvXj.setVisibility(bean.getSold_out() == 0 ? View.GONE : View.VISIBLE);
        tvXj.setText(bean.getSold_out() + "");
        tvDsh.setVisibility(bean.getWait_audit() == 0 ? View.GONE : View.VISIBLE);
        tvDsh.setText(bean.getWait_audit() + "");
        rbPingfen.setRating(bean.getEvaluation());
        if (bean.getWait_sh() == 0) {
            tvDaishenhe.setVisibility(View.GONE);
        } else {
            tvDaishenhe.setVisibility(View.VISIBLE);
            tvDaishenhe.setText(bean.getWait_sh() + "");
        }
        if (bean.getWait_tj() == 0) {
            tvDaitijiao.setVisibility(View.GONE);
        } else {
            tvDaitijiao.setVisibility(View.VISIBLE);
            tvDaitijiao.setText(bean.getWait_tj() + "");
        }
        if (bean.getShenhe_sbai() == 0) {
            tvWeitongguo.setVisibility(View.GONE);
        } else {
            tvWeitongguo.setVisibility(View.VISIBLE);
            tvWeitongguo.setText(bean.getShenhe_sbai() + "");
        }
        if (bean.getShenhe_cg() == 0) {
            tvTongguo.setVisibility(View.GONE);
        } else {
            tvTongguo.setVisibility(View.VISIBLE);
            tvTongguo.setText(bean.getShenhe_cg() + "");
        }
        tvDianming.setText(bean.getCompany_name());
        Glide.with(mContext).load(bean.getPhoto()).into(ivTouxiang);
        tvDianming.setText(bean.getCompany_name() + "");
        type = bean.getBusiness_state() + "";
        tvState.setText(type.equals("0") ? "营业中" : "已关店");
        tvYue.setText(bean.getMoney() + "");
        if (StringUtil.isValid(bean.getRealtime()) && bean.getRealtime().equals("0")) {
            tvQiehuan.setText("已开启实时达");
            close_type = "0";
        } else {
            tvQiehuan.setText("已关闭实时达");
            close_type = "1";
        }
        tvDdb.setText(bean.getWait_db() + "");
        tvQdz.setVisibility(bean.getQiangdan_z() == 0 ? View.GONE : View.VISIBLE);
        tvQdz.setText(bean.getQiangdan_z() + "");
        tvQdcg.setVisibility(bean.getQiangdan_cg() == 0 ? View.GONE : View.VISIBLE);
        tvQdz.setText(bean.getQiangdan_cg() + "");
        tvQdsb.setVisibility(bean.getQiangdan_sbai() == 0 ? View.GONE : View.VISIBLE);
        tvQdsb.setText(bean.getQiangdan_sbai() + "");
        tvSpll.setText(bean.getLiulan() + "");
        tvSpsc.setText(bean.getSc() + "");
        tvDpgz.setText(bean.getGuanzhu() + "");
        tvDqd.setVisibility(bean.getQiangdan() == 0 ? View.GONE : View.VISIBLE);
        tvDqd.setText(bean.getQiangdan() + "");
        tvShsb.setVisibility(bean.getAudit_shibai() == 0 ? View.GONE : View.VISIBLE);
        tvShsb.setText(bean.getAudit_shibai() + "");
        tvSj.setVisibility(bean.getShelves() == 0 ? View.GONE : View.VISIBLE);
        tvSj.setText(bean.getShelves() + "");
        tvXj.setVisibility(bean.getSold_out() == 0 ? View.GONE : View.VISIBLE);
        tvXj.setText(bean.getSold_out() + "");
        tvDsh.setVisibility(bean.getWait_audit() == 0 ? View.GONE : View.VISIBLE);
        tvDsh.setText(bean.getWait_audit() + "");
        rbPingfen.setRating(bean.getEvaluation());
        tvDaifukuanCt.setVisibility(bean.getStay_payment()==0?View.GONE:View.VISIBLE);
        tvDaifukuanCt.setText(bean.getStay_payment()+"");
        tvDaifahuoCt.setVisibility(bean.getStay_delivery()==0?View.GONE:View.VISIBLE);
        tvDaifahuoCt.setText(bean.getStay_delivery()+"");
        tvDaishouhuoCt.setVisibility(bean.getStay_ecipient()==0?View.GONE:View.VISIBLE);
        tvDaishouhuoCt.setText(bean.getStay_ecipient()+"");
        tvYishouhuoCt.setVisibility(bean.getAlready_ecipient()==0?View.GONE:View.VISIBLE);
        tvYishouhuoCt.setText(bean.getAlready_ecipient()+"");
        tvYiwanchengCt.setVisibility(bean.getAlready_complete()==0?View.GONE:View.VISIBLE);
        tvYiwanchengCt.setText(bean.getAlready_complete()+"");
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
                        tvQiehuan.setText(bean.equals("0") ? "已开启实时达" : "已关闭实时达");
                        close_type = bean;
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

    @Override
    protected void onResume() {
        getShow();
        super.onResume();
    }
}
