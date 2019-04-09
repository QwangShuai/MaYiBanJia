package com.mingmen.mayi.mayibanjia.ui.activity.wuliujingli;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.WuLiuBean;
import com.mingmen.mayi.mayibanjia.bean.WuLiuObjBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.SiJiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.WuLiuActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.SiJiPeiSongAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.WuLiuFenPeiAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseFragment;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.custom.SwipeRecyclerView;
import com.mingmen.mayi.mayibanjia.utils.qrCode.CaptureActivity;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/28/028.
 */

public abstract class BaseJingliFragment extends BaseFragment {

    @BindView(R.id.rv_dingdan)
    SwipeRecyclerView rvShangpinguanli;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    View view;

    private ArrayList<WuLiuBean> mlist = new ArrayList<>();
    private WuLiuFenPeiAdapter adapter;
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;
    private int ye = 1;
    private boolean b = false;
    protected boolean isCreate = false;
    private final static int SCANNIN_GREQUEST_CODE = 1;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreate = true;
    }

    @Override
    protected View getSuccessView() {
        view = View.inflate(MyApplication.mContext, R.layout.fragment_shangpinguanli, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void loadData() {
        stateLayout.showSuccessView();
        initview();
        getPeiSong();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    //数据
    public void getPeiSong() {
        HttpManager.getInstance()
                .with(getContext())
                .setObservable(RetrofitManager.getService()
                        .getWuliu(PreferenceUtils.getString(MyApplication.mContext, "token", ""), getZhuangTai(), ye + "", ""))
                .setDataListener(new HttpDataListener<WuLiuObjBean<WuLiuBean>>() {
                    @Override
                    public void onNext(WuLiuObjBean<WuLiuBean> bean) {
                        if (bean.getDdList().size() == 5) {
                            rvShangpinguanli.loadMoreFinish(false, true);
                        } else if (bean.getDdList().size() > 0) {
                            rvShangpinguanli.loadMoreFinish(false, false);
                        } else {
                            rvShangpinguanli.loadMoreFinish(true, false);
                        }
                        mlist.addAll(bean.getDdList());
                        adapter.notifyDataSetChanged();
                        ye++;
                    }
                });
    }


    private void initview() {
        WuLiuActivity activity = (WuLiuActivity) getActivity();
        mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                getPeiSong();
            }
        };
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ye = 1;
                mlist.clear();
                adapter.notifyDataSetChanged();
                getPeiSong();
                refreshLayout.setRefreshing(false);
            }
        });
        rvShangpinguanli.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvShangpinguanli.useDefaultLoadMore(); // 使用默认的加载更多的View。
        rvShangpinguanli.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        rvShangpinguanli.loadMoreFinish(false, true);
        adapter = new WuLiuFenPeiAdapter(getContext(),mlist,activity,this);
        rvShangpinguanli.setAdapter(adapter);
        getPeiSong();

    }

    public abstract String getZhuangTai();

    public void onResume() {
        super.onResume();
        Log.e("onResume: ",getZhuangTai() );
        if (ye != 1) {
            ye = 1;
            mlist.clear();
            adapter.notifyDataSetChanged();
            getPeiSong();

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void updateQrCode(String id) {
        HttpManager.getInstance()
                .with(getContext())
                .setObservable(
                        RetrofitManager
                                .getService()
                                .updateQrCode(PreferenceUtils.getString(MyApplication.mContext, "token", ""), id, "", "1", ""))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        ye = 1;
                        mlist.clear();
                        adapter.notifyDataSetChanged();
                        getPeiSong();
                    }
                });
    }

    public void saomiaoQrCode() {
//        this.id = id;
        Intent intent = new Intent();
        intent.setClass(getContext(), CaptureActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        if (resultCode == CaptureActivity.RESULT_CODE_QR_SCAN) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString(CaptureActivity.INTENT_EXTRA_KEY_QR_SCAN);
            Log.e("678678", scanResult);
            updateQrCode(scanResult);
        }
    }
}