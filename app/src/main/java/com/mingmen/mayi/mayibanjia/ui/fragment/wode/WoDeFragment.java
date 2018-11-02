package com.mingmen.mayi.mayibanjia.ui.fragment.wode;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.PhoneBean;
import com.mingmen.mayi.mayibanjia.bean.WoDeBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.CaiGouDanActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.DianPuGuanZhuActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.GongYingDuanSheZhiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.LiuLanJiLuActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.ShouCangListActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.ShouHuoDiZhiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.YiJianFanKuiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.YinHangKaActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.ZhangHuXinXiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.PhoneDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dingdan.DingDanActivity;
import com.mingmen.mayi.mayibanjia.ui.base.BaseFragment;
import com.mingmen.mayi.mayibanjia.ui.view.CircleImageView;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by Administrator on 2018/7/16/016.
 */

public class WoDeFragment extends BaseFragment {

    @BindView(R.id.iv_touxiang)
    CircleImageView ivTouxiang;
    @BindView(R.id.iv_tongzhi)
    ImageView ivTongzhi;
    @BindView(R.id.iv_mingpian)
    ImageView ivMingpian;
    @BindView(R.id.tv_tongzhi)
    TextView tvTongzhi;
    @BindView(R.id.rl_tongzhi)
    RelativeLayout rlTongzhi;
    @BindView(R.id.tv_dianpuguanzhu)
    TextView tvDianpuguanzhu;
    @BindView(R.id.ll_yue)
    LinearLayout llYue;
    @BindView(R.id.ll_dingdan)
    LinearLayout llDingdan;
    @BindView(R.id.rl_shouhuodizhi)
    RelativeLayout rlShouhuodizhi;
    @BindView(R.id.rl_xuqiudan)
    RelativeLayout rlXuqiudan;
    @BindView(R.id.ll_gongju)
    LinearLayout llGongju;
    @BindView(R.id.ll_bangzhuzhongxin)
    LinearLayout llBangzhuzhongxin;
    @BindView(R.id.ll_qiehuan)
    LinearLayout llQiehuan;
    @BindView(R.id.tv_mingzi)
    TextView tvMingzi;
    @BindView(R.id.tv_zhanghao)
    TextView tvZhanghao;
    @BindView(R.id.tv_yue)
    TextView tvYue;
    @BindView(R.id.tv_shoucangshu)
    TextView tvShoucangshu;
    @BindView(R.id.ll_shoucang)
    LinearLayout llShoucang;
    @BindView(R.id.ll_guanzhu)
    LinearLayout llGuanzhu;
    @BindView(R.id.tv_liulanjilu)
    TextView tvLiulanjilu;
    @BindView(R.id.ll_liulanjilu)
    LinearLayout llLiulanjilu;
    @BindView(R.id.tv_daifukuan)
    TextView tvDaifukuan;
    @BindView(R.id.rl_daifukuan)
    RelativeLayout rlDaifukuan;
    @BindView(R.id.tv_daifahuo)
    TextView tvDaifahuo;
    @BindView(R.id.rl_daifahuo)
    RelativeLayout rlDaifahuo;
    @BindView(R.id.tv_daishouhuo)
    TextView tvDaishouhuo;
    @BindView(R.id.rl_daishouhuo)
    RelativeLayout rlDaishouhuo;
    @BindView(R.id.tv_yishouhuo)
    TextView tvYishouhuo;
    @BindView(R.id.rl_yishouhuo)
    RelativeLayout rlYishouhuo;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.ll_pingjia)
    RelativeLayout llPingjia;
    @BindView(R.id.rl_yinhang)
    RelativeLayout rlYinhang;
    @BindView(R.id.tv_xuqiudan)
    TextView tvXuqiudan;
    @BindView(R.id.rl_changjianwenti)
    RelativeLayout rlChangjianwenti;
    @BindView(R.id.rl_kefu)
    RelativeLayout rlKefu;
    @BindView(R.id.rl_yijian)
    RelativeLayout rlYijian;
    private View viewSPYXFragment;
    private Context mContext;
    private WoDeBean woDeBean;

    @Override
    protected View getSuccessView() {
        mContext=getActivity();
        viewSPYXFragment = View.inflate(mContext, R.layout.fragment_wode, null);
        ButterKnife.bind(this, viewSPYXFragment);
        return viewSPYXFragment;
    }

    @Override
    protected void loadData() {

        if (AppUtil.isConnNet()) {
            stateLayout.showSuccessView();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getActivity().getWindow();
                //设置状态栏颜
                window.setStatusBarColor(getResources().getColor(R.color.zangqing));
            }
        } else {
            stateLayout.showErrorView();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getActivity().getWindow();
                //设置状态栏颜
                window.setStatusBarColor(getResources().getColor(R.color.white));
            }
        }
        getwode(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        getwode(false);
    }

    private void getwode(boolean isxianshi) {
         HttpManager.getInstance()
         .with(mContext)
                .setObservable(
            RetrofitManager
                    .getService()
                    .getwode(PreferenceUtils.getString(MyApplication.mContext, "token","")))
            .setDataListener(new HttpDataListener<WoDeBean>() {
        @Override
        public void onNext(WoDeBean data) {
            Log.e("data",new Gson().toJson(data)+"---");
            woDeBean=data;
            initView();
        }
     },isxianshi);
    }

    private void initView() {
        Glide.with(getActivity()).load(woDeBean.getPhoto()).into(ivTouxiang);
        tvYue.setText(woDeBean.getMoney());
        tvShoucangshu.setText(woDeBean.getSc());
        tvDianpuguanzhu.setText(woDeBean.getGuanzhu());
        tvLiulanjilu.setText(woDeBean.getLiulan());
        if (Integer.parseInt(woDeBean.getStay_payment())==0){
            tvDaifukuan.setVisibility(View.GONE);
        }else{
            tvDaifukuan.setVisibility(View.VISIBLE);
            tvDaifukuan.setText(woDeBean.getStay_payment());
        }
        if (Integer.parseInt(woDeBean.getStay_delivery())==0){
            tvDaifahuo.setVisibility(View.GONE);
        }else{
            tvDaifahuo.setVisibility(View.VISIBLE);
            tvDaifahuo.setText(woDeBean.getStay_delivery());
        }
        if (Integer.parseInt(woDeBean.getStay_ecipient())==0){
            tvDaishouhuo.setVisibility(View.GONE);
        }else{
            tvDaishouhuo.setVisibility(View.VISIBLE);
            tvDaishouhuo.setText(woDeBean.getStay_ecipient());
        }
        if (Integer.parseInt(woDeBean.getAlready_ecipient())==0){
            tvYishouhuo.setVisibility(View.GONE);
        }else{
            tvYishouhuo.setVisibility(View.VISIBLE);
            tvYishouhuo.setText(woDeBean.getAlready_ecipient());
        }

        tvMingzi.setText(woDeBean.getCompany_name());
        tvZhanghao.setText(woDeBean.getTelephone());
    }
    public void Jump_intent(Class<?> cla, Bundle bundle) {
        Intent intent = new Intent(getContext(), cla);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @OnClick({R.id.iv_tongzhi, R.id.iv_touxiang,R.id.ll_shoucang, R.id.iv_mingpian, R.id.rl_dingdan, R.id.rl_daifukuan,
            R.id.rl_daifahuo, R.id.rl_daishouhuo, R.id.rl_yishouhuo, R.id.rl_shouhuodizhi, R.id.rl_xuqiudan,R.id.rl_yijian,
            R.id.rl_kefu,R.id.ll_guanzhu,R.id.ll_liulanjilu,R.id.rl_yinhang,R.id.tv_qiehuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_tongzhi:
                break;
            case R.id.ll_shoucang://商品收藏
                Jump_intent(ShouCangListActivity.class, new Bundle());
                break;
            case R.id.iv_touxiang:
                Log.e("touxiang", "dian touxiang");
                Jump_intent(ZhangHuXinXiActivity.class, new Bundle());
                break;
            case R.id.iv_mingpian:
                Jump_intent(ZhangHuXinXiActivity.class, new Bundle());
                break;
            case R.id.rl_dingdan:
                Bundle quanbu = new Bundle();
                quanbu.putInt("to_shop", 0);
                Jump_intent(DingDanActivity.class, quanbu);
                break;
            case R.id.rl_daifukuan:
                Bundle daifukuan = new Bundle();
                daifukuan.putInt("to_shop", 1);
                Jump_intent(DingDanActivity.class, daifukuan);
                break;
            case R.id.rl_daifahuo:
                Bundle daifahuo = new Bundle();
                daifahuo.putInt("to_shop", 2);
                Jump_intent(DingDanActivity.class, daifahuo);
                break;
            case R.id.rl_daishouhuo:
                Bundle daishouhuo = new Bundle();
                daishouhuo.putInt("to_shop", 3);
                Jump_intent(DingDanActivity.class, daishouhuo);
                break;
            case R.id.rl_yishouhuo:
                Bundle yishouhuo = new Bundle();
                yishouhuo.putInt("to_shop", 4);
                Jump_intent(DingDanActivity.class, yishouhuo);
                break;
            case R.id.rl_shouhuodizhi://收货地址
                Bundle bundle = new Bundle();
                bundle.putString("rukou", "wode");
                Jump_intent(ShouHuoDiZhiActivity.class, bundle);
                break;
            case R.id.rl_xuqiudan://采购单
                Jump_intent(CaiGouDanActivity.class, new Bundle());
                break;
            case R.id.rl_yijian://意见反馈
                startActivity(new Intent(getActivity(), YiJianFanKuiActivity.class));
                break;
            case R.id.rl_kefu://客服
                getPhone();
                break;
            case R.id.ll_guanzhu://店铺关注列表
                Jump_intent(DianPuGuanZhuActivity.class, new Bundle());
                break;
            case R.id.ll_liulanjilu://店铺关注列表
                Jump_intent(LiuLanJiLuActivity.class, new Bundle());
                break;
            case R.id.rl_yinhang:
                Jump_intent(YinHangKaActivity.class, new Bundle());
                break;
            case R.id.tv_qiehuan:
                qiehuan();
                break;
        }
    }
    public void getPhone(){
        HttpManager.getInstance()
                .with(getActivity())
                .setObservable(RetrofitManager.getService()
                        .getPhone(PreferenceUtils.getString(MyApplication.mContext, "token", ""),""))
                .setDataListener(new HttpDataListener<PhoneBean>() {

                    @Override
                    public void onNext(PhoneBean bean) {
                        new PhoneDialog().setData(bean.getKefu(),
                                bean.getYewuyuan(),bean.getKefuphone(),bean.getYwyphone()).show(getActivity().getSupportFragmentManager());
                    }
                },false);

    }
    private void qiehuan(){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .qiehuan(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String bean) {
                        Intent it = new Intent(mContext, GongYingDuanSheZhiActivity.class);
                        startActivity(it);
                        getActivity().finish();
                    }
                });
    }
}
