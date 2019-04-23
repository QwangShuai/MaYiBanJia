package com.mingmen.mayi.mayibanjia.ui.activity;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.EditorShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.FCGName;
import com.mingmen.mayi.mayibanjia.bean.FbspCanShuBean;
import com.mingmen.mayi.mayibanjia.bean.FbspGuiGeBean;
import com.mingmen.mayi.mayibanjia.bean.ShangPinSousuoMohuBean;
import com.mingmen.mayi.mayibanjia.bean.UpdateBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.XJSPFeiLeiGuigeAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.XinJianSpMohuAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.LianggeXuanXiangDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.PhotoDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.photo.FileStorage;
import com.mingmen.mayi.mayibanjia.utils.photo.QiNiuPhoto;
import com.mingmen.mayi.mayibanjia.utils.update.CProgressDialogUtils;
import com.mingmen.mayi.mayibanjia.utils.update.HProgressDialogUtils;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.xuexiang.xupdate.XUpdate;
import com.xuexiang.xupdate.entity.UpdateEntity;
import com.xuexiang.xupdate.proxy.IUpdateParser;
import com.xuexiang.xupdate.proxy.IUpdatePrompter;
import com.xuexiang.xupdate.proxy.IUpdateProxy;
import com.xuexiang.xupdate.proxy.impl.DefaultUpdateChecker;
import com.xuexiang.xupdate.service.OnFileDownloadListener;
import com.xuexiang.xupdate.utils.UpdateUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.SinglePicker;

/**
 * Created by Administrator on 2018/8/6/006.
 */

public class SpsbChangeActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_spsb_change;
    }

    @Override
    protected void initData() {
        XUpdate.newBuild(SpsbChangeActivity.this)
                .updateUrl("http://192.168.0.21:8080/ant/version/UpdateVersion.do")
                .updateChecker(new DefaultUpdateChecker() {
                    @Override
                    public void onBeforeCheck() {
                        super.onBeforeCheck();
                        CProgressDialogUtils.showProgressDialog(SpsbChangeActivity.this, "查询中...");
                    }
                    @Override
                    public void onAfterCheck() {
                        super.onAfterCheck();
                        CProgressDialogUtils.cancelProgressDialog(SpsbChangeActivity.this);
                    }
                })
                .updateParser(new CustomUpdateParser())
//                .updatePrompter(new CustomUpdatePrompter(SpsbChangeActivity.this))
                .update();
    }


    public class CustomUpdateParser implements IUpdateParser {
        @Override
        public UpdateEntity parseJson(String json) throws Exception {
            UpdateBean result = new Gson().fromJson(json, UpdateBean.class);
            if (result != null) {
                return new UpdateEntity()
                        .setHasUpdate(true)
                        .setIsIgnorable(false)
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
