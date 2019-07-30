package com.mingmen.mayi.mayibanjia.ui.fragment.sijigerenzhongxin;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.GerenzhongxinBean;
import com.mingmen.mayi.mayibanjia.bean.SijiGerenzhongxinBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.ChangePwdActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.JieSuanJieGuoActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.JuJueLiShiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.WuLiuGrzxActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.YunFeiJieSuanActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.base.BaseFragment;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/7/16/016.
 */

public class SijiGeRenZhongXinFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_qiye)
    TextView tvQiye;
    @BindView(R.id.tv_jiedan)
    TextView tvJiedan;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_cheleixing)
    TextView tvCheleixing;
    @BindView(R.id.tv_chepaihao)
    TextView tvChepaihao;
    @BindView(R.id.ll_jszhxx)
    LinearLayout llJszhxx;
    @BindView(R.id.ll_jjlsjl)
    LinearLayout llJjlsjl;
    @BindView(R.id.ll_ggmm)
    LinearLayout llGgmm;
    @BindView(R.id.btn_exit_login)
    Button btnExitLogin;

    private View viewSPYXFragment;
    private Context mContext;
    private ConfirmDialog confirmDialog;
    private String type = "";

    @Override
    protected View getSuccessView() {
        viewSPYXFragment = View.inflate(MyApplication.mContext, R.layout.fragment_gerenzhongxin_siji, null);
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

    public void getWode() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .wuliuSijiWode(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<SijiGerenzhongxinBean>() {
                    @Override
                    public void onNext(SijiGerenzhongxinBean bean) {
                        tvQiye.setText(bean.getLogistics());
                        tvName.setText(bean.getNew_driver_name());
                        tvPhone.setText(bean.getNew_driver_phone());
                        type = bean.getCars_type();
                        if(bean.getCars_type().equals("0")){
                            tvJiedan.setText("停止接单");
                        } else {
                            tvJiedan.setText("开始接单");
                        }
                        tvCheleixing.setText(bean.getNew_wl_cars_type_name());
                        tvChepaihao.setText(bean.getNew_plate_number());

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
    private void updateState() {
        type = type.equals("0")?"2":"0";
        Log.e( "updateState: ", type);
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .isJiedan(PreferenceUtils.getString(MyApplication.mContext, "token", ""),type))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        ToastUtil.showToastLong("切换成功");
                        if(type.equals("0")){
                            tvJiedan.setText("停止接单");
                        } else {
                            tvJiedan.setText("开始接单");
                        }
                    }
                });
    }

    private void setIntent(String state) {
        Intent it = new Intent(mContext, YunFeiJieSuanActivity.class);
        it.putExtra("state", state);
        startActivity(it);
    }

    @OnClick({R.id.tv_jiedan, R.id.ll_jszhxx, R.id.ll_jjlsjl, R.id.ll_ggmm, R.id.btn_exit_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_jiedan:
                confirmDialog.showDialog("是否确定切换状态");
                confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmDialog.dismiss();
                        updateState();
                    }
                });
                confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmDialog.dismiss();
                    }
                });

                break;
            case R.id.ll_jjlsjl:
                Jump_intent(JuJueLiShiActivity.class,new Bundle());
                break;
            case R.id.ll_jszhxx:
                Jump_intent(WuLiuGrzxActivity.class, new Bundle());
                break;
            case R.id.ll_ggmm:
                Jump_intent(ChangePwdActivity.class, new Bundle());
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
}
