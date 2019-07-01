package com.mingmen.mayi.mayibanjia.ui.fragment.wode;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.PhoneBean;
import com.mingmen.mayi.mayibanjia.bean.WoDeBean;
import com.mingmen.mayi.mayibanjia.bean.ZiZhangHuDetailsBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.CaiGouDanActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.ChengBenKaListActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.DianPuGuanZhuActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.JiaoYiLiuShuiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.LiuLanJiLuActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.ShouCangListActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.ShouHuoDiZhiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.SqscShezhiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.TiXianActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.WoDePingJiaActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.YiJianFanKuiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.YinHangKaActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.YueActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.ZiZhangHuActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.PhoneDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dingdan.DingDanActivity;
import com.mingmen.mayi.mayibanjia.ui.base.BaseFragment;
import com.mingmen.mayi.mayibanjia.ui.view.CircleImageView;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.GlideUtils;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.tv_dpgz)
    TextView tvDianpuguanzhu;
    @BindView(R.id.ll_zzh)
    LinearLayout llZzh;
    @BindView(R.id.ll_yue)
    LinearLayout llYue;
    //    @BindView(R.id.ll_dingdan)
//    LinearLayout llDingdan;
    @BindView(R.id.tv_yue)
    TextView tvYue;
    @BindView(R.id.tv_spsc)
    TextView tvShoucangshu;
    @BindView(R.id.ll_shoucang)
    LinearLayout llShoucang;
    @BindView(R.id.ll_spll)
    LinearLayout llSpll;
    @BindView(R.id.ll_guanzhu)
    LinearLayout llGuanzhu;
    @BindView(R.id.tv_spll)
    TextView tvLiulanjilu;
    @BindView(R.id.ll_cg)
    LinearLayout llCg;
    @BindView(R.id.ll_dd)
    LinearLayout llDd;
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
    @BindView(R.id.tv_yiwancheng)
    TextView tvYiwancheng;
    @BindView(R.id.rl_yishouhuo)
    RelativeLayout rlYishouhuo;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.ll_pingjia)
    RelativeLayout llPingjia;
    @BindView(R.id.rl_yinhang)
    RelativeLayout rlYinhang;
    @BindView(R.id.rl_changjianwenti)
    RelativeLayout rlChangjianwenti;
    @BindView(R.id.rl_kefu)
    RelativeLayout rlKefu;
    @BindView(R.id.rl_yijian)
    RelativeLayout rlYijian;
    @BindView(R.id.rl_daishenhe)
    RelativeLayout rlDaishenhe;
    @BindView(R.id.tv_daishenhe)
    TextView tvDaishenhe;
    @BindView(R.id.rl_daitijiao)
    RelativeLayout rlDaitijiao;
    @BindView(R.id.tv_daitijiao)
    TextView tvDaitijiao;
    @BindView(R.id.rl_weitongguo)
    RelativeLayout rlWeitongguo;
    @BindView(R.id.tv_weitongguo)
    TextView tvWeitongguo;
    @BindView(R.id.rl_tongguo)
    RelativeLayout rlTongguo;
    @BindView(R.id.rl_yiwancheng)
    RelativeLayout rlYiwancheng;
    @BindView(R.id.tv_tongguo)
    TextView tvTongguo;
    @BindView(R.id.iv_wqx_dfk)
    ImageView ivWqxDfk;
    @BindView(R.id.iv_wqx_dfh)
    ImageView ivWqxDfh;
    @BindView(R.id.iv_wqx_dsh)
    ImageView ivWqxDsh;
    @BindView(R.id.iv_wqx_ysh)
    ImageView ivWqxYsh;
    @BindView(R.id.iv_wqx_ywc)
    ImageView ivWqxYwc;
    Unbinder unbinder;
    @BindView(R.id.daifukuan)
    ImageView daifukuan;
    @BindView(R.id.daifahuo)
    ImageView daifahuo;
    @BindView(R.id.daishouhuo)
    ImageView daishouhuo;
    @BindView(R.id.yishouhuo)
    ImageView yishouhuo;
    @BindView(R.id.yiwancheng)
    ImageView yiwancheng;
    @BindView(R.id.daishenhe)
    ImageView daishenhe;
    @BindView(R.id.daitijiao)
    ImageView daitijiao;
    @BindView(R.id.weitongguo)
    ImageView weitongguo;
    @BindView(R.id.tongguo)
    ImageView tongguo;
    @BindView(R.id.iv_wqx_zzh)
    ImageView ivWqxZzh;
    @BindView(R.id.rl_zizhanghu)
    RelativeLayout rlZizhanghu;
    @BindView(R.id.iv_wqx_shdi)
    ImageView ivWqxShdi;
    @BindView(R.id.rl_shouhuodizhi)
    RelativeLayout rlShouhuodizhi;
    @BindView(R.id.iv_wqx_yhzh)
    ImageView ivWqxYhzh;
    @BindView(R.id.iv_wqx_wdpj)
    ImageView ivWqxWdpj;
    @BindView(R.id.iv_wqx_cjwt)
    ImageView ivWqxCjwt;
    @BindView(R.id.iv_wqx_kfdh)
    ImageView ivWqxKfdh;
    @BindView(R.id.iv_wqx_yjfk)
    ImageView ivWqxYjfk;
    @BindView(R.id.yiquxiao)
    ImageView yiquxiao;
    @BindView(R.id.tv_yiquxiao)
    TextView tvYiquxiao;
    @BindView(R.id.iv_wqx_yqx)
    ImageView ivWqxYqx;
    @BindView(R.id.rl_yiquxiao)
    RelativeLayout rlYiquxiao;
    @BindView(R.id.iv_wqx_cbk)
    ImageView ivWqxCbk;
    @BindView(R.id.rl_chengbenka)
    RelativeLayout rlChengbenka;
    @BindView(R.id.tv_mingzi)
    TextView tvMingzi;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rl_shezhi)
    RelativeLayout rlShezhi;
    @BindView(R.id.tv_gy)
    TextView tvGy;
    @BindView(R.id.tv_shenfen)
    TextView tvShenfen;
    @BindView(R.id.tv_cwbb)
    TextView tvCwbb;
    @BindView(R.id.ll_cwbb)
    LinearLayout llCwbb;
    @BindView(R.id.tv_tixian)
    TextView tvTixian;
    @BindView(R.id.ll_tixian)
    LinearLayout llTixian;
    @BindView(R.id.tv_daishoukuan)
    TextView tvDaishoukuan;
    private View viewSPYXFragment;
    private Context mContext;
    private WoDeBean woDeBean;
    private String roleDdType = "";
    private String roleCgType = "";
    private String yue = "0";
    private List<ZiZhangHuDetailsBean.RoleListBean> role = new ArrayList<>();


    @Override
    protected View getSuccessView() {
        mContext = getActivity();
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
        if (!PreferenceUtils.getString(MyApplication.mContext, "host_account_type", "").equals("0")) {
            role = PreferenceUtils.getQuanxianList(MyApplication.mContext, "quanxian");
            llTixian.setEnabled(false);
            rlYiquxiao.setVisibility(View.GONE);
            llDd.setVisibility(View.GONE);
            llCg.setVisibility(View.GONE);
            int mysize = role == null ? 0 : role.size();
            if (mysize != 0) {
                for (int i = 0; i < role.size(); i++) {
                    if (role.get(i).getRole_id().equals("1")) {
                        roleDdType += role.get(i).getRole_id();
                        llDd.setVisibility(View.VISIBLE);
                    } else if (role.get(i).getRole_id().equals("4")) {
                        llDd.setVisibility(View.VISIBLE);
                        roleDdType += role.get(i).getRole_id();
                    } else if (role.get(i).getRole_id().equals("2")) {
                        llCg.setVisibility(View.VISIBLE);
                        roleCgType += role.get(i).getRole_id();
                    } else if (role.get(i).getRole_id().equals("3")) {
                        llCg.setVisibility(View.VISIBLE);
                        roleCgType += role.get(i).getRole_id();
                    } else if (role.get(i).getRole_id().equals("5")) {
                        llCg.setVisibility(View.VISIBLE);
                        rlYiquxiao.setVisibility(View.VISIBLE);
                        roleCgType += role.get(i).getRole_id();
                    }
                }
                setShowView();
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
        View view = View.inflate(mContext, R.layout.pop_tuichu, null);
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getwode(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<WoDeBean>() {
                    @Override
                    public void onNext(WoDeBean data) {
                        woDeBean = data;
//                        if (!data.getRole().isEmpty()) {
//                            if (data.getRole().equals("1")) {
//                                llQiehuan.setVisibility(View.GONE);//这一句即隐藏布局LinearLayout区域
//                            }
//                        }

                        initView();
                    }
                }, isxianshi);
    }

    private void initView() {
        GlideUtils.cachePhoto(getActivity(), ivTouxiang, woDeBean.getPhoto());
        if (PreferenceUtils.getString(MyApplication.mContext, "host_account_type", "").equals("0")) {
            tvYue.setText(woDeBean.getMoney());
            llZzh.setVisibility(View.GONE);
        } else {
            llZzh.setVisibility(View.VISIBLE);
        }
        tvName.setText(PreferenceUtils.getString(MyApplication.mContext, "name", ""));
        tvPhone.setText(StringUtil.getPhone());
        yue = woDeBean.getMoney() + "";
        tvShoucangshu.setText(woDeBean.getSc() + "");
        tvDianpuguanzhu.setText(woDeBean.getGuanzhu() + "");
        tvLiulanjilu.setText(woDeBean.getLiulan() + "");
        if (woDeBean.getStay_payment() == 0) {
            tvDaifukuan.setVisibility(View.GONE);
        } else {
            tvDaifukuan.setVisibility(View.VISIBLE);
            tvDaifukuan.setText(woDeBean.getStay_payment() + "");
        }
        if (woDeBean.getStay_delivery() == 0) {
            tvDaifahuo.setVisibility(View.GONE);
        } else {
            tvDaifahuo.setVisibility(View.VISIBLE);
            tvDaifahuo.setText(woDeBean.getStay_delivery() + "");
        }
        if (woDeBean.getStay_ecipient() == 0) {
            tvDaishouhuo.setVisibility(View.GONE);
        } else {
            tvDaishouhuo.setVisibility(View.VISIBLE);
            tvDaishouhuo.setText(woDeBean.getStay_ecipient() + "");
        }
        if (woDeBean.getAlready_ecipient() == 0) {
            tvYishouhuo.setVisibility(View.GONE);
        } else {
            tvYishouhuo.setVisibility(View.VISIBLE);
            tvYishouhuo.setText(woDeBean.getAlready_ecipient() + "");
        }
        if (woDeBean.getQx_dingdan() == 0) {
            tvYiquxiao.setVisibility(View.GONE);
        } else {
            tvYiquxiao.setVisibility(View.VISIBLE);
            tvYiquxiao.setText(woDeBean.getQx_dingdan() + "");
        }
        if (woDeBean.getAlready_complete() == 0) {
            tvYiwancheng.setVisibility(View.GONE);
        } else {
            tvYiwancheng.setVisibility(View.VISIBLE);
            tvYiwancheng.setText(woDeBean.getAlready_complete() + "");
        }
        if (woDeBean.getWait_sh() == 0) {
            tvDaishenhe.setVisibility(View.GONE);
        } else {
            tvDaishenhe.setVisibility(View.VISIBLE);
            tvDaishenhe.setText(woDeBean.getWait_sh() + "");
        }
        if (woDeBean.getWait_tj() == 0) {
            tvDaitijiao.setVisibility(View.GONE);
        } else {
            tvDaitijiao.setVisibility(View.VISIBLE);
            tvDaitijiao.setText(woDeBean.getWait_tj() + "");
        }
        if (woDeBean.getShenhe_sbai() == 0) {
            tvWeitongguo.setVisibility(View.GONE);
        } else {
            tvWeitongguo.setVisibility(View.VISIBLE);
            tvWeitongguo.setText(woDeBean.getShenhe_sbai() + "");
        }
        if (woDeBean.getShenhe_cg() == 0) {
            tvTongguo.setVisibility(View.GONE);
        } else {
            tvTongguo.setVisibility(View.VISIBLE);
            tvTongguo.setText(woDeBean.getShenhe_cg() + "");
        }

        tvMingzi.setText(woDeBean.getCompany_name());
    }

    public void Jump_intent(Class<?> cla, Bundle bundle) {
        Intent intent = new Intent(getContext(), cla);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @OnClick({R.id.rl_shezhi, R.id.iv_touxiang, R.id.ll_shoucang, R.id.rl_daifukuan, R.id.ll_cwbb,
            R.id.rl_daifahuo, R.id.rl_daishouhuo, R.id.rl_yishouhuo, R.id.rl_yiwancheng, R.id.rl_shouhuodizhi, R.id.rl_yijian,
            R.id.rl_kefu, R.id.ll_guanzhu, R.id.ll_spll, R.id.rl_yinhang, R.id.ll_tixian,
            R.id.tv_yue, R.id.ll_pingjia, R.id.rl_zizhanghu, R.id.rl_chengbenka, R.id.rl_yiquxiao,
            R.id.rl_daishenhe, R.id.rl_daitijiao, R.id.rl_weitongguo, R.id.rl_tongguo, R.id.ll_zzh})
//            ,R.id.rl_jueseguanli})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_shezhi:
                Bundle ct = new Bundle();
                ct.putString("khd", "ct");
                Jump_intent(SqscShezhiActivity.class, ct);
                break;
            case R.id.rl_daishenhe:
                Intent daishenhe = new Intent(mContext, CaiGouDanActivity.class);
                daishenhe.putExtra("type", "902");
                mContext.startActivity(daishenhe);
                break;
            case R.id.ll_cwbb:
                Jump_intent(JiaoYiLiuShuiActivity.class, new Bundle());
                break;
            case R.id.ll_tixian:
                Bundle tixian = new Bundle();
                tixian.putString("yue", yue);
                Jump_intent(TiXianActivity.class, tixian);
                break;
            case R.id.rl_daitijiao:
                Intent daitijiao = new Intent(mContext, CaiGouDanActivity.class);
                daitijiao.putExtra("type", "904");
                mContext.startActivity(daitijiao);
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
            case R.id.rl_chengbenka:
//                ToastUtil.showToastLong("功能开发中");
                Jump_intent(ChengBenKaListActivity.class, new Bundle());
                break;
            case R.id.ll_shoucang://商品收藏
                Jump_intent(ShouCangListActivity.class, new Bundle());
                break;
            case R.id.iv_touxiang:
//                Log.e("touxiang", "dian touxiang");
//                Bundle sz = new Bundle();
//                sz.putString("khd","ct");
//                Jump_intent(SqscShezhiActivity.class,sz );
                break;
            case R.id.ll_zzh:
                if(woDeBean!=null&&StringUtil.isValid(woDeBean.getPart())){
                    showDialog(mContext,woDeBean.getPart());
                }
                break;
            case R.id.rl_daifukuan:
                Bundle daifukuan = new Bundle();
                daifukuan.putString("roleDdType", roleDdType);
                if (roleDdType.equals("1") || roleDdType.equals("14") || roleDdType.equals("41")) {
                    daifukuan.putInt("to_shop", 0);
                } else {
                    daifukuan.putInt("to_shop", 1);
                }
                Jump_intent(DingDanActivity.class, daifukuan);
                break;
            case R.id.rl_yiquxiao:
                Bundle yiquxiao = new Bundle();
                yiquxiao.putInt("to_shop", 2);
                Jump_intent(DingDanActivity.class, yiquxiao);
                break;
            case R.id.rl_daifahuo:
                Bundle daifahuo = new Bundle();
                daifahuo.putInt("to_shop", 3);
                Jump_intent(DingDanActivity.class, daifahuo);
                break;
            case R.id.rl_daishouhuo:
                Bundle daishouhuo = new Bundle();
                daishouhuo.putString("roleDdType", roleDdType);
                if (roleDdType.equals("4")) {
                    daishouhuo.putInt("to_shop", 0);
                } else if (roleDdType.equals("14") || roleDdType.equals("41")) {
                    daishouhuo.putInt("to_shop", 1);
                } else {
                    daishouhuo.putInt("to_shop", 4);
                }

                Jump_intent(DingDanActivity.class, daishouhuo);
                break;
            case R.id.rl_yishouhuo:
                Bundle yishouhuo = new Bundle();
                yishouhuo.putString("roleDdType", roleDdType);
                if (roleDdType.equals("4")) {
                    yishouhuo.putInt("to_shop", 2);
                } else if (roleDdType.equals("14") || roleDdType.equals("41")) {
                    yishouhuo.putInt("to_shop", 2);
                } else {
                    yishouhuo.putInt("to_shop", 5);
                }

                Jump_intent(DingDanActivity.class, yishouhuo);
                break;
            case R.id.rl_yiwancheng:
                Bundle yiwancheng = new Bundle();
                yiwancheng.putString("roleDdType", roleDdType);
                if (roleDdType.equals("1")) {
                    yiwancheng.putInt("to_shop", 2);
                } else if (roleDdType.equals("14") || roleDdType.equals("41")) {
                    yiwancheng.putInt("to_shop", 4);
                } else {
                    yiwancheng.putInt("to_shop", 6);
                }

                Jump_intent(DingDanActivity.class, yiwancheng);
                break;
            case R.id.rl_shouhuodizhi://收货地址
                Bundle bundle = new Bundle();
                bundle.putString("rukou", "wode");
                Jump_intent(ShouHuoDiZhiActivity.class, bundle);
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
            case R.id.ll_spll://浏览记录
                Jump_intent(LiuLanJiLuActivity.class, new Bundle());
                break;
            case R.id.rl_yinhang:
                Jump_intent(YinHangKaActivity.class, new Bundle());
                break;
            case R.id.tv_yue:
                Jump_intent(YueActivity.class, new Bundle());
                break;
            case R.id.ll_pingjia:
                Jump_intent(WoDePingJiaActivity.class, new Bundle());
                break;
            case R.id.rl_zizhanghu:
                Bundle zzh = new Bundle();
                zzh.putString("name", woDeBean.getCompany_name());
                zzh.putString("id", woDeBean.getCompany_id());
                Jump_intent(ZiZhangHuActivity.class, zzh);
                break;
//            case R.id.rl_jueseguanli:
//                Bundle jsgl  = new Bundle();
//                jsgl.putString("name",woDeBean.getCompany_name());
//                jsgl.putString("id",woDeBean.getCompany_id());
//                Jump_intent(JueSeGuanLiActivity.class,jsgl);
//                break;
        }
    }

    public void getPhone() {
        HttpManager.getInstance()
                .with(getActivity())
                .setObservable(RetrofitManager.getService()
                        .getPhone(PreferenceUtils.getString(MyApplication.mContext, "token", ""), ""))
                .setDataListener(new HttpDataListener<PhoneBean>() {

                    @Override
                    public void onNext(PhoneBean bean) {
                        new PhoneDialog().setData(bean.getKefu(),
                                bean.getYewuyuan(), bean.getKefuphone(), bean.getYwyphone()).show(getActivity().getSupportFragmentManager());
                    }
                }, false);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setShowView() {
        tvYue.setEnabled(false);
        tvYue.setText("****");
        tvYue.setTextColor(mContext.getResources().getColor(R.color.zicolor));
        ivWqxZzh.setVisibility(View.VISIBLE);
        ivWqxShdi.setVisibility(View.VISIBLE);
        ivWqxYhzh.setVisibility(View.VISIBLE);
        ivWqxWdpj.setVisibility(View.VISIBLE);
        ivWqxCjwt.setVisibility(View.VISIBLE);
//        ivWqxYjfk.setVisibility(View.VISIBLE);

        llShoucang.setEnabled(false);
        llGuanzhu.setEnabled(false);
        llSpll.setEnabled(false);
//        ivTouxiang.setEnabled(false);
        rlZizhanghu.setEnabled(false);
        rlShouhuodizhi.setEnabled(false);
        rlYinhang.setEnabled(false);
        llPingjia.setEnabled(false);
        rlChangjianwenti.setEnabled(false);
//        rlYijian.setEnabled(false);
//        for (int i=0;i<role.size();i++){
        if (roleDdType.equals("14") || roleDdType.equals("41")) {
            ivWqxDfh.setVisibility(View.VISIBLE);
            rlDaifahuo.setEnabled(false);
        } else if (roleDdType.equals("1")) {
            ivWqxDfh.setVisibility(View.VISIBLE);
            ivWqxDsh.setVisibility(View.VISIBLE);
            ivWqxYsh.setVisibility(View.VISIBLE);

            rlDaifahuo.setEnabled(false);
            rlDaishouhuo.setEnabled(false);
            rlYishouhuo.setEnabled(false);
        } else if (roleDdType.equals("4")) {
            ivWqxDfh.setVisibility(View.VISIBLE);
            ivWqxDfk.setVisibility(View.VISIBLE);
            ivWqxYwc.setVisibility(View.VISIBLE);

            rlDaifukuan.setEnabled(false);
            rlDaifahuo.setEnabled(false);
            rlYiwancheng.setEnabled(false);
        }
        if (roleCgType.equals("25") || roleCgType.equals("52")) {//全部权限
//            rlShouhuodizhi.setEnabled(true);
            ivWqxShdi.setVisibility(View.VISIBLE);
        } else if (roleCgType.equals("5")) {//无审批权限
            PreferenceUtils.putString(MyApplication.mContext, "isShenPi", roleCgType);
//            rlShouhuodizhi.setEnabled(true);
            ivWqxShdi.setVisibility(View.VISIBLE);
        } else if (roleCgType.equals("2")) {//全部权限

        }
//        }

    }

}
