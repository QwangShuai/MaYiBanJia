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
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.utils.AppManager;

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
}

