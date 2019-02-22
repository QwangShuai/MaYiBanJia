package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.google.gson.Gson;
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
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.WuLiuDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppManager;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.qrCode.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SiJiActivity extends BaseActivity {
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.rv_peisong)
    RecyclerView rv_peisong;
    @BindView(R.id.iv_sangedian)
    ImageView ivSangedian;

    private Context mContext;
    private SiJiPeiSongDialog titleDialog;
    private PopupWindow tuichupop;
    private ConfirmDialog confirmDialog;
    private SiJiPeiSongAdapter adapter;
    private final static int SCANNIN_GREQUEST_CODE = 1;
    private int count = 1;
    private List<WuLiuBean> mList= new ArrayList<WuLiuBean>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_si_ji;
    }

    @Override
    protected void initData() {
        mContext = SiJiActivity.this;
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        getPeiSong("");
    }

    @OnClick({R.id.ll_title, R.id.iv_sangedian})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.ll_title:
                titleDialog.show(getSupportFragmentManager());
                break;
            case R.id.iv_sangedian:
                showTuiChuPop();
                break;
        }
    }

    public void getPeiSong(final String type) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getWuliu(PreferenceUtils.getString(MyApplication.mContext, "token", ""), "", count + "", type))
                .setDataListener(new HttpDataListener<WuLiuObjBean<WuLiuBean>>() {
                    @Override
                    public void onNext(WuLiuObjBean<WuLiuBean> bean) {
                        mList.clear();
//                        adapter.notifyDataSetChanged();
                        mList = bean.getDdList();
                        adapter = new SiJiPeiSongAdapter(mContext,mList,SiJiActivity.this,type);
                        rv_peisong.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                        rv_peisong.setAdapter(adapter);
                        count++;
                        showTongji(bean.getState4()+"",bean.getState1()+"",bean.getState2()+"",bean.getState3()+"");
                    }
                });
    }
    public void getShuaXinPeiSong(final String type) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getWuliu(PreferenceUtils.getString(MyApplication.mContext, "token", ""), "", "1", type))
                .setDataListener(new HttpDataListener<WuLiuObjBean<WuLiuBean>>() {
                    @Override
                    public void onNext(WuLiuObjBean<WuLiuBean> bean) {
                        mList = bean.getDdList();
                        adapter = new SiJiPeiSongAdapter(mContext,mList,SiJiActivity.this,type);
                        rv_peisong.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                        rv_peisong.setAdapter(adapter);
                        showTongji(bean.getState4()+"",bean.getState1()+"",bean.getState2()+"",bean.getState3()+"");
                    }
                });
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
                Intent it = new Intent(mContext,WoDeZhangHuActivity.class);
                startActivity(it);
                tuichupop.dismiss();
            }
        });
        tuichupop.setOutsideTouchable(true);
        tuichupop.setBackgroundDrawable(new BitmapDrawable());
        tuichupop.showAsDropDown(ivSangedian);
    }

    public void showTongji(String zong, String wfc, String yfc, String ybg) {//显示上方统计
        titleDialog = new SiJiPeiSongDialog()
                .setActivity(SiJiActivity.this)
                .init("全部状态(" + zong + ")", "待取货(" + wfc + ")", "待送达(" + yfc + ")", "已完成(" + ybg + ")")
                .setTop(AppUtil.dip2px(44));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getShuaXinPeiSong("");
    }
    public void updateQrCode(String id){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .updateQrCode(PreferenceUtils.getString(MyApplication.mContext, "token",""),id,"","1",""))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        getShuaXinPeiSong("");
                    }
                });
    }

    public void saomiaoQrCode(){
//        this.id = id;
        Intent intent = new Intent();
        intent.setClass(mContext, CaptureActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        if (resultCode == CaptureActivity.RESULT_CODE_QR_SCAN) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString(CaptureActivity.INTENT_EXTRA_KEY_QR_SCAN);
            Log.e("678678",scanResult);
            updateQrCode(scanResult);
        }
    }

    private void exitLogin() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .exitLogin(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
//                        PreferenceUtils.clear(MyApplication.mContext);
//                        PreferenceUtils.putBoolean(MyApplication.mContext,"isLogin",false);
//                        PreferenceUtils.remove(MyApplication.mContext,"juese");
//                        Intent intent = new Intent(mContext, LoginActivity.class);
//                        startActivity(intent);
                        confirmDialog.dismiss();
//                        tuichupop.dismiss();
//                        AppManager.getAppManager().finishAllActivity();
                        goLogin(mContext,"login");
                    }
                });
    }
}
