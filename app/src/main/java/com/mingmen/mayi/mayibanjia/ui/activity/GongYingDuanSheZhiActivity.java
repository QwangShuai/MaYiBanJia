package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppManager;
import com.mingmen.mayi.mayibanjia.utils.PollingService;
import com.mingmen.mayi.mayibanjia.utils.PollingUtils;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/7.
 */

public class GongYingDuanSheZhiActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_tuichu)
    TextView tvTuichu;
    private Context mContext;
    private ConfirmDialog confirmDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_gongyingduanshezhi;
    }

    @Override
    protected void initData() {
        tvTitle.setText("账户信息");
        mContext=GongYingDuanSheZhiActivity.this;
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
    }


    @OnClick({R.id.iv_back, R.id.tv_tuichu,R.id.ll_zhanghu,R.id.ll_mendian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBack();
                break;
            case R.id.tv_tuichu:
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
//            case R.id.tv_qiehuan:
//                qiehuan();
//                break;
            case R.id.ll_zhanghu:
                Intent it = new Intent(mContext,WoDeZhangHuActivity.class);
                startActivity(it);
                break;
            case R.id.ll_mendian:
                Intent it_mendian = new Intent(mContext,MenDianXinXiActivity.class);
                startActivity(it_mendian);
                break;
        }
    }

//    private void qiehuan(){
//            HttpManager.getInstance()
//                    .with(mContext)
//                    .setObservable(RetrofitManager.getService()
//                            .qiehuan(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
//                    .setDataListener(new HttpDataListener<String>() {
//                        @Override
//                        public void onNext(String bean) {
//                            Intent it = new Intent(mContext, MainActivity.class);
//                            it.putExtra("tosome",3);
//                            startActivity(it);
//                            finish();
//                        }
//                    });
//    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        onBack();
    }
    private void onBack(){
        Intent it = new Intent(mContext,GongYingDuanShouYeActivity.class);
        startActivity(it);
        finish();
    }
    private void exitLogin() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .exitLogin(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        PreferenceUtils.putBoolean(MyApplication.mContext,"isLogin",false);
                        PreferenceUtils.clear(MyApplication.mContext);
                        Intent intent = new Intent(mContext, LoginActivity.class);
                        startActivity(intent);
                        confirmDialog.dismiss();
                        PollingUtils.stopPollingService(mContext,PollingService.class, PollingService.ACTION);
                        GongYingDuanShouYeActivity.instance.finish();
                        AppManager.getAppManager().finishAllActivity();
                    }
                });
    }
}
