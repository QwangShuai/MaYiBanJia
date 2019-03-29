package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.WuLiuBean;
import com.mingmen.mayi.mayibanjia.bean.WuLiuObjBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.WuLiuFenPeiAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.WuLiuDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.wuliujingli.JingliAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.wuliusiji.SijiAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.view.PagerSlidingTabStrip;
import com.mingmen.mayi.mayibanjia.utils.AppManager;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class WuLiuActivity extends BaseActivity {
    @BindView(R.id.ll_title)
    LinearLayout llTitle;;
    @BindView(R.id.iv_sangedian)
    ImageView ivSangedian;
    @BindView(R.id.tabs_dingdan)
    PagerSlidingTabStrip tabsDingdan;
    @BindView(R.id.vp_dingdan)
    ViewPager vpDingdan;

    private Context mContext;
    private PopupWindow tuichupop;
    private ConfirmDialog confirmDialog;
    private JingliAdapter adapter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_wu_liu;
    }

    @Override
    protected void initData() {
        mContext=WuLiuActivity.this;
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        adapter =new JingliAdapter(getSupportFragmentManager(),mContext);
        vpDingdan.setAdapter(adapter);
        tabsDingdan.setViewPager(vpDingdan);
        vpDingdan.setOffscreenPageLimit(0);
        vpDingdan.setCurrentItem(0);
    }
    @OnClick({R.id.ll_title,R.id.iv_sangedian})
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.ll_title:
                break;
            case R.id.iv_sangedian:
                showTuiChuPop();
                break;
        }
    }
    private void showTuiChuPop() {
        View view = View.inflate(mContext, R.layout.pop_jiesuan, null);
        tuichupop = new PopupWindow(view);

        WindowManager wm1 = this.getWindowManager();
        int width = wm1.getDefaultDisplay().getWidth();
        int height = wm1.getDefaultDisplay().getHeight();
        tuichupop.setWidth(AppUtil.dip2px(130));
        tuichupop.setHeight(AppUtil.dip2px(150));
        LinearLayout ll_tuichu = view.findViewById(R.id.ll_tuichu);
        LinearLayout ll_yunfei = view.findViewById(R.id.ll_jiesuan);
        LinearLayout ll_jiesuanjieguo = view.findViewById(R.id.ll_jiesuanjieguo);
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
        ll_jiesuanjieguo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext,JieSuanJieGuoActivity.class));
            }
        });
        ll_yunfei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext,YunFeiJieSuanActivity.class));
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
                        goLogin(mContext,"login");
                    }
                });
    }
}
