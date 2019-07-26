package com.mingmen.mayi.mayibanjia.ui.fragment.gerenzhongxin;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CheliangBean;
import com.mingmen.mayi.mayibanjia.bean.GerenzhongxinBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.ChangePwdActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.JieSuanJieGuoActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.WuLiuGrzxActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.YunFeiJieSuanActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.wuliujingli.JingliAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.base.BaseFragment;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.List;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/7/16/016.
 */

public class GeRenZhongXinFragment extends BaseFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_qiye)
    TextView tvQiye;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_dizhi)
    TextView tvDizhi;
    @BindView(R.id.tv_wjs)
    TextView tvWjs;
    @BindView(R.id.rl_weijiesuan)
    RelativeLayout rlWeijiesuan;
    @BindView(R.id.tv_jsz)
    TextView tvJsz;
    @BindView(R.id.rl_jiesuanzhong)
    RelativeLayout rlJiesuanzhong;
    @BindView(R.id.tv_yjs)
    TextView tvYjs;
    @BindView(R.id.rl_yijiesuan)
    RelativeLayout rlYijiesuan;
    @BindView(R.id.tv_jsjg)
    TextView tvJsjg;
    @BindView(R.id.rl_jiesuanjieguo)
    RelativeLayout rlJiesuanjieguo;
    @BindView(R.id.ll_jszhxx)
    LinearLayout llJszhxx;
    @BindView(R.id.ll_ggmm)
    LinearLayout llGgmm;
    @BindView(R.id.btn_exit_login)
    Button btnExitLogin;
    Unbinder unbinder;

    private View viewSPYXFragment;
    private Context mContext;
    private ConfirmDialog confirmDialog;

    @Override
    protected View getSuccessView() {
        viewSPYXFragment = View.inflate(MyApplication.mContext, R.layout.fragment_gerenzhongxin, null);
        ButterKnife.bind(this, viewSPYXFragment);
        return viewSPYXFragment;
    }

    @Override
    protected void loadData() {
        mContext = getActivity();
        tvTitle.setText("个人中心");
        ivBack.setVisibility(View.GONE);
        if (AppUtil.isConnNet()) {
            stateLayout.showSuccessView();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getActivity().getWindow();
                //设置状态栏颜
                window.setStatusBarColor(getResources().getColor(R.color.beijing));
            }
        } else {
            stateLayout.showErrorView();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getActivity().getWindow();
                //设置状态栏颜
                window.setStatusBarColor(getResources().getColor(R.color.white));
            }
        }
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        initView();
        getWode();
    }

    private void initView() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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

    @OnClick({R.id.rl_weijiesuan, R.id.rl_jiesuanzhong, R.id.rl_yijiesuan, R.id.rl_jiesuanjieguo,
            R.id.ll_jszhxx, R.id.ll_ggmm, R.id.btn_exit_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_weijiesuan:
                setIntent("0");
                break;
            case R.id.rl_jiesuanzhong:
                setIntent("1");
                break;
            case R.id.rl_yijiesuan:
                setIntent("2");
                break;
            case R.id.rl_jiesuanjieguo:
//                setIntent("3");
                Jump_intent(JieSuanJieGuoActivity.class,new Bundle());
                break;
            case R.id.ll_jszhxx:
                Jump_intent(WuLiuGrzxActivity.class,new Bundle());
                break;
            case R.id.ll_ggmm:
                Jump_intent(ChangePwdActivity.class,new Bundle());
                break;
            case R.id.btn_exit_login:
                confirmDialog.showDialog("是否确定退出当前账号");
                confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmDialog.dismiss();
                        exitLogin();
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

    //查询购物车
    public void getWode() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .wuliuWode(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<GerenzhongxinBean>() {
                    @Override
                    public void onNext(GerenzhongxinBean bean) {
                        tvQiye.setText(bean.getCompany_name());
                        tvName.setText(bean.getName());
                        tvPhone.setText(bean.getTelephone());
                        tvDizhi.setText(bean.getDizhi());
                        if(StringUtil.isValid(bean.getCount())&&!"0".equals(bean.getCount())){
                            tvWjs.setText(bean.getCount());
                            tvWjs.setVisibility(View.VISIBLE);
                        } else {
                            tvWjs.setVisibility(View.GONE);
                        }

                    }
                });
    }
    private void exitLogin() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .exitLogin(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        BaseActivity.goLogin(mContext, "login");
                    }
                });
    }

    private void setIntent(String state){
        Intent it = new Intent(mContext, YunFeiJieSuanActivity.class);
        it.putExtra("state",state);
        startActivity(it);
    }
}
