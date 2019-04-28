package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.SqscFuzeren;
import com.mingmen.mayi.mayibanjia.bean.YwyBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShichangFuzerenActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_dqr_no)
    TextView tvDqrNo;
    @BindView(R.id.ll_dqr)
    LinearLayout llDqr;
    @BindView(R.id.tv_yjdd_no)
    TextView tvYjddNo;
    @BindView(R.id.ll_yjdd)
    LinearLayout llYjdd;
    @BindView(R.id.tv_dqh_no)
    TextView tvDqhNo;
    @BindView(R.id.ll_dqh)
    LinearLayout llDqh;
    @BindView(R.id.tv_dsh_no)
    TextView tvDshNo;
    @BindView(R.id.ll_dsh)
    LinearLayout llDsh;
    @BindView(R.id.tv_ywcsh_no)
    TextView tvYwcshNo;
    @BindView(R.id.tv_zcldd_no)
    TextView tvZclddNo;
    @BindView(R.id.ll_ywcsh)
    LinearLayout llYwcsh;
    @BindView(R.id.tv_ywy)
    TextView tvYwy;
    @BindView(R.id.ll_ywy)
    LinearLayout llYwy;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.tv_dizhi)
    TextView tvDizhi;
    @BindView(R.id.ll_dizhi)
    LinearLayout llDizhi;
    @BindView(R.id.ll_show)
    LinearLayout llShow;
    @BindView(R.id.ll_pwd)
    LinearLayout llPwd;
    @BindView(R.id.btn_exit)
    Button btnExit;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    private boolean isShow;
    private Context mContext;
    private ConfirmDialog confirmDialog;

    private Timer timer;
    private int i = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shichang_fuzeren;
    }

    @Override
    protected void initData() {
        ivBack.setVisibility(View.GONE);
        mContext = this;
        tvTitle.setText("市场负责人");
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        refreshLayout.setColorSchemeResources(R.color.zangqing, R.color.zangqing,
                R.color.zangqing, R.color.zangqing);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showScfzr();
                refreshLayout.setRefreshing(false);
            }
        });
        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.e("run: ","走"+ i );
                i++;
                showScfzr();
            }
        },0,60*1000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_title, R.id.iv_back, R.id.tv_right, R.id.ll_dqr, R.id.ll_yjdd, R.id.ll_dqh,
            R.id.ll_dsh, R.id.ll_ywcsh, R.id.ll_ywy, R.id.ll_phone, R.id.ll_dizhi, R.id.ll_show,
            R.id.ll_pwd, R.id.btn_exit,R.id.ll_zcldd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_title:
                break;
            case R.id.iv_back:
                break;
            case R.id.tv_right:
                break;
            case R.id.ll_zcldd:
                JumpScwl("1407","0");
                break;
            case R.id.ll_dqr:
                JumpScwl("1401","1");
                break;
            case R.id.ll_yjdd:
                JumpScwl("1406","");
                break;
            case R.id.ll_dqh:
                JumpScwl("1401","0");
                break;
            case R.id.ll_dsh:
                JumpScwl("1402","");
                break;
            case R.id.ll_ywcsh:
                JumpScwl("1403","");
                break;
            case R.id.ll_ywy:
                if (isShow) {
                    isShow = false;
                    llShow.setVisibility(View.GONE);
                } else {
                    isShow = true;
                    llShow.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.ll_phone:
                break;
            case R.id.ll_dizhi:
                break;
            case R.id.ll_show:
                break;
            case R.id.ll_pwd:
                Intent it = new Intent(mContext, WoDeZhangHuActivity.class);
                startActivity(it);
                break;
            case R.id.btn_exit:
                confirmDialog.showDialog("是否确定退出当前账号");
                confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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

    private void showScfzr() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getSqscFuzeren(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<SqscFuzeren>() {
                    @Override
                    public void onNext(SqscFuzeren bean) {

                        initView(bean);

                    }
                },false);
    }

    private void exitLogin() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .exitLogin(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        confirmDialog.dismiss();
                        goLogin(mContext,"login");
                    }
                });
    }
    @Override
    protected void onResume() {
        super.onResume();
        showScfzr();
    }
    public void initView(SqscFuzeren bean) {
        tvYwy.setText(bean.getName());
        tvPhone.setText(bean.getTelephone());
        tvDizhi.setText(bean.getSpecific_address());

        tvYjddNo.setVisibility(bean.getYjcount() == 0 ? View.GONE : View.VISIBLE);
        tvYjddNo.setText(bean.getYjcount() + "");
        tvDqrNo.setVisibility(bean.getWait_count() == 0 ? View.GONE : View.VISIBLE);
        tvDqrNo.setText(bean.getWait_count() + "");
        tvDqhNo.setVisibility(bean.getWaitQh_count() == 0 ? View.GONE : View.VISIBLE);
        tvDqhNo.setText(bean.getWaitQh_count() + "");
        tvDshNo.setVisibility(bean.getWaitSH_count() == 0 ? View.GONE : View.VISIBLE);
        tvDshNo.setText(bean.getWaitSH_count() + "");
        tvYwcshNo.setVisibility(bean.getWaitWC_count() == 0 ? View.GONE : View.VISIBLE);
        tvYwcshNo.setText(bean.getWaitWC_count() + "");
        tvZclddNo.setVisibility(bean.getZcl_count() == 0 ? View.GONE : View.VISIBLE);
        tvZclddNo.setText(bean.getZcl_count() + "");
    }

    private void JumpScwl(String state,String teshu){
        Intent it = new Intent(mContext,ShichangWuliuActivity.class);
        it.putExtra("type",state);
        it.putExtra("isShichang",teshu);
        startActivity(it);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
