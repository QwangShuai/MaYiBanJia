package com.mingmen.mayi.mayibanjia.ui.activity.ghdingdan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.GHOrderBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.base.BaseFragment;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/9/28.
 */

public abstract class BaseGHOrderFragment extends BaseFragment {
    @BindView(R.id.rv_dingdan)
    SwipeMenuRecyclerView rvDingdan;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    Unbinder unbinder;
    private ArrayList<GHOrderBean> mlist = new ArrayList<GHOrderBean>();
    private GHOrderAdapter adapter;
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;
    private int ye = 1;
    protected boolean isCreate = false;
    private String token = "";

    @Override
    protected View getSuccessView() {
        View view = View.inflate(MyApplication.mContext, R.layout.fragment_dingdan, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreate = true;
    }

    //    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser && isCreate) {
//            //相当于Fragment的onResume
//            //在这里处理加载数据等操作
//            ye = 1;
//            getData();
//            adapter.notifyDataSetChanged();
//            Log.e("onResume",getZhuangTai());
//        } else {
//            //相当于Fragment的onPause
//        }
//    }
    @Override
    protected void loadData() {
        stateLayout.showSuccessView();
    }

    @Override
    public void onStart() {
        super.onStart();
        initview();
        getData();
    }

    //数据
    private void getData() {
        GHDOrderActivity activity = (GHDOrderActivity) getActivity();
        token = activity.getToken();
        if (StringUtil.isValid(token)) {
            adapter.setClick(false);
        } else {
            token = PreferenceUtils.getString(MyApplication.mContext, "token", "");
        }

        HttpManager.getInstance()
                .with(getActivity())
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getGHOrderList(token, getZhuangTai(), ye))
                .setDataListener(new HttpDataListener<List<GHOrderBean>>() {
                    @Override
                    public void onNext(List<GHOrderBean> data) {
                        if (!"null".equals(String.valueOf(data))) {
                            mlist.clear();
                            mlist.addAll(data);
                            if (data.size() == 5) {
                                rvDingdan.loadMoreFinish(false, true);
                            } else if (data.size() > 0) {
                                rvDingdan.loadMoreFinish(false, false);
                            } else {
                                rvDingdan.loadMoreFinish(true, false);
                            }
                            adapter.notifyDataSetChanged();
                            ye++;
                        }
                    }
                });
    }
    //数据
    private void updateList(final boolean b_clear) {

        HttpManager.getInstance()
                .with(getActivity())
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getGHOrderList(token, getZhuangTai(), ye))
                .setDataListener(new HttpDataListener<List<GHOrderBean>>() {
                    @Override
                    public void onNext(List<GHOrderBean> data) {
                        if (!"null".equals(String.valueOf(data))) {
                            if (data.size() == 5) {
                                rvDingdan.loadMoreFinish(false, true);
                            } else if (data.size() > 0) {
                                rvDingdan.loadMoreFinish(false, false);
                            } else {
                                rvDingdan.loadMoreFinish(true, false);
                            }
                            if (b_clear) {//刷新清空当前页面数据
                                mlist.clear();
                            }
                            mlist.addAll(data);
                            adapter.notifyDataSetChanged();

                        }
                    }
                });
        ye++;
    }
    private void initview() {
        rvDingdan.setLayoutManager(new LinearLayoutManager(rvDingdan.getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new GHOrderAdapter(getActivity(), mlist, getActivity(), this);
        mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                // 该加载更多啦。
                updateList(false);
            }
        };

        rvDingdan.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        refreshLayout.setColorSchemeResources(R.color.zangqing, R.color.zangqing,
                R.color.zangqing, R.color.zangqing);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ye = 1 ;
                updateList(true);
                refreshLayout.setRefreshing(false);
            }
        });
        rvDingdan.setAdapter(adapter);
    }

    public abstract String getZhuangTai();

    public void Jump_intent(Class<?> cla, Bundle bundle) {
        Intent intent = new Intent(getContext(), cla);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        ye = 1;
        getData();
        adapter.notifyDataSetChanged();
    }

    //数据
    public void shuaxinData() {
        ye = 1;
        HttpManager.getInstance()
                .with(getActivity())
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getGHOrderList(token, getZhuangTai(), ye))
                .setDataListener(new HttpDataListener<List<GHOrderBean>>() {
                    @Override
                    public void onNext(List<GHOrderBean> data) {
                        mlist.clear();
                        mlist.addAll(data);
                        if (data.size() == 5) {
                            rvDingdan.loadMoreFinish(false, true);
                        } else if (data.size() > 0) {
                            rvDingdan.loadMoreFinish(false, false);
                        } else {
                            rvDingdan.loadMoreFinish(true, false);
                        }
                        adapter.notifyDataSetChanged();
                        ye++;
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
