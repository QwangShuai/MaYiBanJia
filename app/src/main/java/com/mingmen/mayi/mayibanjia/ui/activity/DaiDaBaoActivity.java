package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.GHOrderBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.GHOrderDdbAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DaiDaBaoActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rv_dingdan)
    SwipeMenuRecyclerView rvDingdan;
    @BindView(R.id.tv_tishi_left)
    TextView tvTishiLeft;
    @BindView(R.id.tv_tishi_center)
    TextView tvTishiCenter;
    @BindView(R.id.tv_tishi_right)
    TextView tvTishiRight;
    @BindView(R.id.ll_list_null)
    LinearLayout llListNull;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    private ArrayList<GHOrderBean> mlist = new ArrayList<GHOrderBean>();
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;
    private int ye = 1;
    private Context mContext;
    private GHOrderDdbAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_dai_da_bao;
    }

    @Override
    protected void initData() {
        mContext = DaiDaBaoActivity.this;
        tvTitle.setText("待打包商家");
        rvDingdan.setLayoutManager(new LinearLayoutManager(rvDingdan.getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new GHOrderDdbAdapter(mContext, mlist);
        mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                // 该加载更多啦。
                getData();
            }
        };

        rvDingdan.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        refreshLayout.setColorSchemeResources(R.color.zangqing, R.color.zangqing,
                R.color.zangqing, R.color.zangqing);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ye = 1;
                mlist.clear();
                adapter.notifyDataSetChanged();
                getData();
                refreshLayout.setRefreshing(false);
            }
        });
        rvDingdan.setAdapter(adapter);
        getData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.ll_list_null})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                break;
            case R.id.ll_list_null:
                break;
        }
    }

    //数据
    private void getData() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .ywyDdbList(PreferenceUtils.getString(MyApplication.mContext, "token", ""), ye+""))
                .setDataListener(new HttpDataListener<List<GHOrderBean>>() {
                    @Override
                    public void onNext(List<GHOrderBean> data) {
//                        if (!"null".equals(String.valueOf(data))) {
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
//                        }
                    }
                });
    }
}
