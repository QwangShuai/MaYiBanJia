package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.WuLiuBean;
import com.mingmen.mayi.mayibanjia.bean.WuLiuObjBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.SiJiPeiSongAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.SiJiPeiSongDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.wuliusiji.SijiAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.view.PagerSlidingTabStrip;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.qrCode.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SiJiActivity extends BaseActivity {
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.iv_sangedian)
    ImageView ivSangedian;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tabs_dingdan)
    PagerSlidingTabStrip tabsDingdan;
    @BindView(R.id.vp_dingdan)
    ViewPager vpDingdan;

    private Context mContext;
    private PopupWindow tuichupop;
    private ConfirmDialog confirmDialog;
    private SijiAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_si_ji;
    }

    @Override
    protected void initData() {
        mContext = SiJiActivity.this;
        if (StringUtil.isValid(getIntent().getStringExtra("ywy"))) {
            ivBack.setVisibility(View.VISIBLE);
            ivSangedian.setVisibility(View.GONE);
        }
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        adapter =new SijiAdapter(getSupportFragmentManager(),mContext);
        vpDingdan.setAdapter(adapter);
        tabsDingdan.setViewPager(vpDingdan);
        vpDingdan.setOffscreenPageLimit(0);
        vpDingdan.setCurrentItem(0);
    }

    @OnClick({R.id.ll_title, R.id.iv_sangedian,R.id.iv_back})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.ll_title:
                break;
            case R.id.iv_sangedian:
                showTuiChuPop();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }


    private void showTuiChuPop() {
        View view = View.inflate(mContext, R.layout.pop_change_pwd, null);
        tuichupop = new PopupWindow(view);

        WindowManager wm1 = this.getWindowManager();
        int width = wm1.getDefaultDisplay().getWidth();
        int height = wm1.getDefaultDisplay().getHeight();
        tuichupop.setWidth(AppUtil.dip2px(130));
        tuichupop.setHeight(AppUtil.dip2px(100));
        LinearLayout ll_tuichu = view.findViewById(R.id.ll_tuichu);
        LinearLayout ll_change_pwd = view.findViewById(R.id.ll_change_pwd);
        ll_tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        tuichupop.dismiss();
                    }
                });
            }
        });
        ll_change_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(mContext, WoDeZhangHuActivity.class);
                startActivity(it);
                tuichupop.dismiss();
            }
        });
        tuichupop.setOutsideTouchable(true);
        tuichupop.setBackgroundDrawable(new BitmapDrawable());
        tuichupop.showAsDropDown(ivSangedian);
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
                        goLogin(mContext, "login");
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
