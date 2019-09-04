package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.BanbenUpdateBean;
import com.mingmen.mayi.mayibanjia.bean.UpdateBean;
import com.mingmen.mayi.mayibanjia.http.URL;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.GlideUtils;
import com.mingmen.mayi.mayibanjia.utils.update.CProgressDialogUtils;
import com.mingmen.mayi.mayibanjia.utils.update.HProgressDialogUtils;
import com.xuexiang.xupdate.XUpdate;
import com.xuexiang.xupdate.entity.UpdateEntity;
import com.xuexiang.xupdate.proxy.IUpdateParser;
import com.xuexiang.xupdate.proxy.IUpdatePrompter;
import com.xuexiang.xupdate.proxy.IUpdateProxy;
import com.xuexiang.xupdate.proxy.impl.DefaultUpdateChecker;
import com.xuexiang.xupdate.service.OnFileDownloadListener;
import com.xuexiang.xupdate.utils.UpdateUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.iv_welcome)
    ImageView ivWelcome;

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initData() {

//        GlideUtils.cachePhoto(WelcomeActivity.this,ivWelcome,"http://ceshi.canchengxiang.com/images/welcome.png");
        HttpManager.getInstance()
                .with(WelcomeActivity.this)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .updateBanben())
                .setDataListener(new HttpDataListener<BanbenUpdateBean>() {
                    @Override
                    public void onNext(BanbenUpdateBean bean) {
                        Log.e("onNext: ", bean.getVersionName() + "======" + AppUtil.getVersion());
                        if (!bean.getVersionName().equals(AppUtil.getVersion())) {
                            XUpdate.newBuild(WelcomeActivity.this)
                                    .isWifiOnly(false)
                                    .updateUrl(URL.url + URL.update_url)
                                    .updateChecker(new DefaultUpdateChecker() {
                                        @Override
                                        public void onBeforeCheck() {
                                            super.onBeforeCheck();
                                            CProgressDialogUtils.showProgressDialog(WelcomeActivity.this, "查询中...");
                                        }

                                        @Override
                                        public void onAfterCheck() {
                                            super.onAfterCheck();
                                            CProgressDialogUtils.cancelProgressDialog(WelcomeActivity.this);
                                        }
                                    })

                                    .updateParser(new CustomUpdateParser())
//                .updatePrompter(new CustomUpdatePrompter(SpsbChangeActivity.this))
                                    .update();
                        } else {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                }
                            }, 2000);
                        }
                    }
                },false);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public class CustomUpdateParser implements IUpdateParser {
        @Override
        public UpdateEntity parseJson(String json) throws Exception {
            UpdateBean result = new Gson().fromJson(json, UpdateBean.class);
            if (result != null) {
                return new UpdateEntity()
                        .setHasUpdate(true)
                        .setIsIgnorable(false)
                        .setForce(true)

                        .setVersionCode(result.getObject().getVersionCode())
                        .setVersionName(result.getObject().getVersionName())
                        .setUpdateContent(result.getObject().getModifyContent())
                        .setDownloadUrl(result.getObject().getDownloadUrl())
                        .setSize(result.getObject().getApkSize());
            }
            return null;
        }
    }

    public class CustomUpdatePrompter implements IUpdatePrompter {

        private Context mContext;

        public CustomUpdatePrompter(Context context) {
            mContext = context;
        }

        @Override
        public void showPrompt(@NonNull UpdateEntity updateEntity, @NonNull IUpdateProxy updateProxy) {
            showUpdatePrompt(updateEntity, updateProxy);
        }

        /**
         * 显示自定义提示
         *
         * @param updateEntity
         * @param updateProxy
         */
        private void showUpdatePrompt(final @NonNull UpdateEntity updateEntity, final @NonNull IUpdateProxy updateProxy) {
            String updateInfo = UpdateUtils.getDisplayUpdateInfo(updateEntity);

            new AlertDialog.Builder(mContext)
                    .setTitle(String.format("是否升级到%s版本？", updateEntity.getVersionName()))
                    .setMessage(updateInfo)
                    .setPositiveButton("升级", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            updateProxy.startDownload(updateEntity, new OnFileDownloadListener() {
                                @Override
                                public void onStart() {
                                    HProgressDialogUtils.showHorizontalProgressDialog(mContext, "下载进度", false);
                                }

                                @Override
                                public void onProgress(float progress, long total) {
                                    HProgressDialogUtils.setProgress(Math.round(progress * 100));
                                }

                                @Override
                                public boolean onCompleted(File file) {
                                    HProgressDialogUtils.cancel();
                                    return true;
                                }

                                @Override
                                public void onError(Throwable throwable) {
                                    HProgressDialogUtils.cancel();
                                }
                            });
                        }
                    })
                    .setNegativeButton("暂不升级", null)
                    .setCancelable(false)
                    .create()
                    .show();
        }
    }
}
