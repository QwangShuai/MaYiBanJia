package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.WuliuDingdanBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.JujuelishiAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JuJueLiShiActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rv_list)
    SwipeMenuRecyclerView rvList;
    @BindView(R.id.srl_shuaxin)
    SwipeRefreshLayout srlShuaxin;

    private Context mContext;
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;
    private List<WuliuDingdanBean>  mlist = new ArrayList<>();
    private JujuelishiAdapter adapter;
    private int ye = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ju_jue_li_shi;
    }

    @Override
    protected void initData() {
        mContext = JuJueLiShiActivity.this;
        tvTitle.setText("拒绝历史");
        mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                getList();
            }
        };
        srlShuaxin.setColorSchemeResources(R.color.zangqing, R.color.zangqing,
                R.color.zangqing, R.color.zangqing);
        srlShuaxin.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ye = 1;
                mlist.clear();
                adapter.notifyDataSetChanged();
                getList();
                srlShuaxin.setRefreshing(false);
            }
        });
        rvList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvList.useDefaultLoadMore(); // 使用默认的加载更多的View。
        rvList.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        rvList.loadMoreFinish(false, true);
        adapter = new JujuelishiAdapter(mContext,mlist,JuJueLiShiActivity.this);
        rvList.setAdapter(adapter);
        getList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                break;
        }
    }
    //数据
    public void getList() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getJujueList(PreferenceUtils.getString(MyApplication.mContext, "token", ""),
                                getIntent().getStringExtra("id"), ye))
                .setDataListener(new HttpDataListener<List<WuliuDingdanBean>>() {
                    @Override
                    public void onNext(List<WuliuDingdanBean> list) {
                        if (list.size() == 5) {
                            rvList.loadMoreFinish(false, true);
                        } else if (list.size() > 0) {
                            rvList.loadMoreFinish(false, false);
                        } else {
                            rvList.loadMoreFinish(true, false);
                        }
                        mlist.addAll(list);
                        adapter.notifyDataSetChanged();
                        ye++;
                    }
                });
    }
}
