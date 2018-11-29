package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.XQPingJiaBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.PingJiaAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WoDePingJiaActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rv_pj)
    SwipeMenuRecyclerView rvPj;
    private Context mContext = WoDePingJiaActivity.this;
    private PingJiaAdapter adapter;
    private int ye = 1;
    private List<XQPingJiaBean> mlist;

    @Override
    public int getLayoutId() {
        return R.layout.activity_wo_de_ping_jia;
    }

    @Override
    protected void initData() {
        tvTitle.setText("我的评价");
        adapter = new PingJiaAdapter();
        getpingjia(1);
        SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                ye++;
                getpingjia(ye);
            }
        };
        rvPj.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvPj.useDefaultLoadMore(); // 使用默认的加载更多的View。
        rvPj.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        rvPj.loadMoreFinish(false, true);
        rvPj.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void getpingjia(final int ye) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getWodepingjia(PreferenceUtils.getString(MyApplication.mContext, "token", ""), ye + ""))
                .setDataListener(new HttpDataListener<List<XQPingJiaBean>>() {
                    @Override
                    public void onNext(List<XQPingJiaBean> data) {
                        Log.e("data", data + "---");
                        if (ye == 1) {
                            mlist = new ArrayList<>();
                            rvPj.loadMoreFinish(false, true);
                        } else {
                            if (data.size() > 0 && data.size() < 5) {
                                rvPj.loadMoreFinish(false, false);
                            } else {
                                if (data.size() == 0) {
                                    rvPj.loadMoreFinish(true, false);
                                } else {
                                    rvPj.loadMoreFinish(false, true);
                                }
                            }
                        }

                        mlist.addAll(data);
                        adapter.setNewData(mlist);
                    }
                });


    }

    @OnClick(R.id.iv_back)
    public void onViewClicked(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
