package com.mingmen.mayi.mayibanjia.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.ui.activity.LoginActivity;
import com.mingmen.mayi.mayibanjia.ui.view.XCFlowLayout;
import com.mingmen.mayi.mayibanjia.utils.AppManager;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by Administrator on 2018/7/4/004.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public InputMethodManager imm;
    public Bundle bundle;
    public Gson gson;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayoutId());
        //透明状态栏
        StatusBarCompat.translucentStatusBar(this);
//        //SDK >= 21 时, 取消状态栏的阴影
//        StatusBarCompat.translucentStatusBar(this,true);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            //设置状态栏颜
//            window.setStatusBarColor(getZhuangTaiLanColor());
//        }
        ButterKnife.bind(this);
        bundle=new Bundle();
        gson=new Gson();
        //初始化
        imm = (InputMethodManager) this
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        initData();
        AppManager.getAppManager().addActivity(this);
    }


    public abstract int getLayoutId();

    protected abstract void initData();

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation==1) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//强制竖屏
        }else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//强制竖屏
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

    // 点击EditText以外的任何区域隐藏键盘
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v != null) {
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        }

        return super.dispatchTouchEvent(ev);
    }
    public void Jump_intent(Class<?> cla, Bundle bundle) {
        Intent intent = new Intent(this, cla);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
    public void initShangpinChildViews(Context mContext,XCFlowLayout xcfShangpinlishisousuo, String[] mList) {
        xcfShangpinlishisousuo.removeAllViews();
        List<TextView> tvs = new ArrayList();
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = AppUtil.dip2px(12);
        lp.rightMargin = AppUtil.dip2px(0);
        lp.topMargin = AppUtil.dip2px(12);
        lp.bottomMargin = 0;
        for (int i = 0; i < mList.length; i++) {
            TextView view = new TextView(mContext);
            view.setText(mList[i]);
            view.setTextColor(mContext.getResources().getColor(R.color.zangqing));
            view.setTextSize(12);
            view.setPadding(AppUtil.dip2px(12), AppUtil.dip2px(6), AppUtil.dip2px(12), AppUtil.dip2px(6));
            view.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_hollow_zangqing_3));
            tvs.add(view);
            xcfShangpinlishisousuo.addView(view, lp);
        }
    }

    public static void goLogin(){
        MyApplication.mContext.startActivity(new Intent(MyApplication.mContext, LoginActivity.class),new Bundle());
    }
}

