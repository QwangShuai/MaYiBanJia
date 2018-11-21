package com.mingmen.mayi.mayibanjia.ui.activity.ghdingdan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.GHOrderBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
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

public abstract class BaseGHOrderFragment extends BaseFragment {
    @BindView(R.id.rv_dingdan)
    SwipeMenuRecyclerView rvDingdan;
    private ArrayList<GHOrderBean> mlist = new ArrayList<GHOrderBean>();
    private GHOrderAdapter adapter;
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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isCreate) {
            //相当于Fragment的onResume
            //在这里处理加载数据等操作
            ye = 1;
            getData();
            adapter.notifyDataSetChanged();
            Log.e("onResume",getZhuangTai());
        } else {
            //相当于Fragment的onPause
        }
    }
    @Override
    protected void loadData() {
        stateLayout.showSuccessView();
    }
    @Override
    public void onStart() {
        super.onStart();
        getData();
    }
    //数据
    private void getData() {
        Log.e("110",getZhuangTai());
        HttpManager.getInstance()
                .with(getActivity())
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getGHOrderList(PreferenceUtils.getString(MyApplication.mContext, "token",""),getZhuangTai(),ye))
                .setDataListener(new HttpDataListener<List<GHOrderBean>>() {
                    @Override
                    public void onNext(List<GHOrderBean> data) {
                        Log.e("data1",data+"---");
                        mlist = new ArrayList<GHOrderBean>();
                        mlist.addAll(data);
                        initview();
                        ye++;
                    }
                });
    }
    private void initview() {
        rvDingdan.setLayoutManager(new LinearLayoutManager(rvDingdan.getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new GHOrderAdapter(getActivity(), mlist,getActivity(),this);
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
    }
    //数据
    public void shuaxinData() {
        ye = 1;
        Log.e("110",getZhuangTai());
        HttpManager.getInstance()
                .with(getActivity())
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getGHOrderList(PreferenceUtils.getString(MyApplication.mContext, "token",""),getZhuangTai(),ye))
                .setDataListener(new HttpDataListener<List<GHOrderBean>>() {
                    @Override
                    public void onNext(List<GHOrderBean> data) {
                        Log.e("data1",data+"---");
                        mlist.clear();
                        mlist.addAll(data);
                        adapter.notifyDataSetChanged();
                        ye++;
                    }
                });
    }
}
