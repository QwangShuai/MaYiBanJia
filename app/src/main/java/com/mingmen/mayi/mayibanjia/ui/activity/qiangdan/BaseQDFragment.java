package com.mingmen.mayi.mayibanjia.ui.activity.qiangdan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.JYMXBean;
import com.mingmen.mayi.mayibanjia.bean.QiangDanBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.QiangDanShangPinDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.jiaoyiliushui.JiaoYiMingXiAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseFragment;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/28.
 */

public abstract class BaseQDFragment extends BaseFragment {
    @BindView(R.id.rv_dingdan)
    SwipeMenuRecyclerView rvDingdan;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    private ArrayList<QiangDanBean> mlist = new ArrayList<QiangDanBean>();
    private QiangDanAdapter adapter;
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;
    private int ye =1;
    private boolean b = false;
    protected boolean isCreate = false;
    @Override
    protected View getSuccessView() {
        View view = View.inflate(MyApplication.mContext, R.layout.fragment_dingdan, null);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreate=true;
    }
    @Override
    protected void loadData() {
        stateLayout.showSuccessView();
    }
    @Override
    public void onStart() {
        super.onStart();
        initview();
        getData(true);
    }
    //数据
    private void getData(final boolean b) {
        HttpManager.getInstance()
                .with(getActivity())
                .setObservable(
                        RetrofitManager
                                .getService()
                                .qiangdanlist(PreferenceUtils.getString(MyApplication.mContext,"token",""),getZhuangTai()))
                .setDataListener(new HttpDataListener<List<QiangDanBean>>() {
                    @Override
                    public void onNext(List<QiangDanBean> data) {
                        Log.e("data1",data+"---");
                        if(!"null".equals(String.valueOf(data))){
                            if(b){
                                mlist.clear();
                            }

                            mlist.addAll(data);
                            adapter.setNewData(mlist);
                            if(data.size()==5){
                                rvDingdan.loadMoreFinish(false, true);
                            }else if(data.size()>0){
                                rvDingdan.loadMoreFinish(false, false);
                            } else {
                                rvDingdan.loadMoreFinish(true, false);
                            }
                            adapter.notifyDataSetChanged();
                        }

                        ye++;
                    }
                });
    }
    private void initview() {
        mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                // 该加载更多啦。
                getData(false);
            }
        };
        rvDingdan.setLayoutManager(new LinearLayoutManager(rvDingdan.getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new QiangDanAdapter((GongYingDuanQiangDanActivity) getActivity());
        refreshLayout.setColorSchemeResources(R.color.zangqing, R.color.zangqing,
                R.color.zangqing, R.color.zangqing);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ye = 1;
                getData(true);
                refreshLayout.setRefreshing(false);
            }
        });
        rvDingdan.setAdapter(adapter);
    }
    public abstract String getZhuangTai();


    @Override
    public void onResume() {
        super.onResume();
        ye = 1;
        getData(true);
    }
}
