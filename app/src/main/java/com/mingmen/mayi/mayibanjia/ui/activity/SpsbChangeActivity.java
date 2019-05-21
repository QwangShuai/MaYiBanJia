package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.FCGName;
import com.mingmen.mayi.mayibanjia.bean.UpdateBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.fragment.quanbucaipin.adapter.QuanBuCaiPinLeiOneAdapter;
import com.mingmen.mayi.mayibanjia.ui.view.PageIndicatorView;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.custom.zixun.HorizontalPageLayoutManager;
import com.mingmen.mayi.mayibanjia.utils.custom.zixun.PagingItemDecoration;
import com.mingmen.mayi.mayibanjia.utils.custom.zixun.PagingScrollHelper;
import com.mingmen.mayi.mayibanjia.utils.update.HProgressDialogUtils;
import com.xuexiang.xupdate.entity.UpdateEntity;
import com.xuexiang.xupdate.proxy.IUpdateParser;
import com.xuexiang.xupdate.proxy.IUpdatePrompter;
import com.xuexiang.xupdate.proxy.IUpdateProxy;
import com.xuexiang.xupdate.service.OnFileDownloadListener;
import com.xuexiang.xupdate.utils.UpdateUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/6/006.
 */

public class SpsbChangeActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.ll_rv)
    LinearLayout llRv;
    @BindView(R.id.bt_xiayibu)
    Button btXiayibu;
    @BindView(R.id.indicator)
    PageIndicatorView indicator;

    private Context mContext;

    //测试全部菜品分类标签
    PagingScrollHelper scrollHelper = new PagingScrollHelper();
    private QuanBuCaiPinLeiOneAdapter adapter;
    private List<FCGName> mList = new ArrayList<>();
    private HorizontalPageLayoutManager hLinearLayoutManager = null;
    private PagingItemDecoration pagingItemDecoration  = null;
    private String yclId = "346926195929448587b078e7fe613530";

    @Override
    public int getLayoutId() {
        return R.layout.activity_spsb_change;
    }

    @Override
    protected void initData() {
        mContext = SpsbChangeActivity.this;
        getShouyeFenLei(yclId, "2");

//        XUpdate.newBuild(SpsbChangeActivity.this)
//                .updateUrl("http://192.168.0.21:8080/ant/version/UpdateVersion.do")
//                .updateChecker(new DefaultUpdateChecker() {
//                    @Override
//                    public void onBeforeCheck() {
//                        super.onBeforeCheck();
//                        CProgressDialogUtils.showProgressDialog(SpsbChangeActivity.this, "查询中...");
//                    }
//                    @Override
//                    public void onAfterCheck() {
//                        super.onAfterCheck();
//                        CProgressDialogUtils.cancelProgressDialog(SpsbChangeActivity.this);
//                    }
//                })
//                .updateParser(new CustomUpdateParser())
////                .updatePrompter(new CustomUpdatePrompter(SpsbChangeActivity.this))
//                .update();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_title, R.id.iv_back, R.id.bt_xiayibu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_title:
                break;
            case R.id.iv_back:
                break;
            case R.id.bt_xiayibu:
                break;
        }
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

    private void initViewLable(){//测试显示标签
        adapter = new QuanBuCaiPinLeiOneAdapter(mContext,mList);
        recyclerView.setAdapter(adapter);
        scrollHelper.setUpRecycleView(recyclerView);
        scrollHelper.setIndicator(indicator);
        hLinearLayoutManager = new HorizontalPageLayoutManager(2, 5);
        pagingItemDecoration = new PagingItemDecoration(mContext, hLinearLayoutManager);
        recyclerView.setLayoutManager(hLinearLayoutManager);
        scrollHelper.setAdapter(adapter,2);
//        recyclerView.addItemDecoration(pagingItemDecoration);
        scrollHelper.updateLayoutManger();
//        recyclerView.post(new Runnable() {
//            @Override
//            public void run() {
//                indicator.initIndicator(scrollHelper.getPageCount());
//            }
//        });
        scrollHelper.scrollToPosition(0);
        scrollHelper.setOnPageChangeListener(new PagingScrollHelper.onPageChangeListener() {
            @Override
            public void onPageChange(int index) {
                indicator.setSelectedPage(index);
            }
        });
        recyclerView.setHorizontalScrollBarEnabled(true);
    }

    private void getShouyeFenLei(String id, final String type) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getFeiLei(PreferenceUtils.getString(MyApplication.mContext, "token", ""), id, type))
                .setDataListener(new HttpDataListener<List<FCGName>>() {

                    @Override
                    public void onNext(List<FCGName> list) {
                        int mysize = list == null ? 0 : list.size();
                        if (mysize != 0) {
                            mList.addAll(list);
                            initViewLable();
//                            adapter.notifyDataSetChanged();
                        } else {
                            ToastUtil.showToastLong("当前类别暂无品类");
                        }
                    }
                }, false);
    }
}
