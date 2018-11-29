package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.JieSuanJirGuoBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.JieSuanJieGuoAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JieSuanJieGuoActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rv_jieguo)
    SwipeMenuRecyclerView rvJieguo;

    private List<JieSuanJirGuoBean> list = new ArrayList<>();
    private Context mContext;
    private int ye = 1;
    private JieSuanJieGuoAdapter adapter;
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;
    @Override
    public int getLayoutId() {
        return R.layout.activity_jie_suan_jie_guo;
    }

    @Override
    protected void initData() {
        mContext = JieSuanJieGuoActivity.this;
        tvTitle.setText("结算结果");
        adapter = new JieSuanJieGuoAdapter(mContext, list, JieSuanJieGuoActivity.this);
        rvJieguo.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvJieguo.setAdapter(adapter);
        mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                getList();
            }
        };
        rvJieguo.setLoadMoreListener(mLoadMoreListener);
        rvJieguo.loadMoreFinish(false, true);
        getList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
    public void getList() {

        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getJiesuanjieguoList(PreferenceUtils.getString(MyApplication.mContext, "token", ""), ye + ""))
                .setDataListener(new HttpDataListener<List<JieSuanJirGuoBean>>() {
                    @Override
                    public void onNext(List<JieSuanJirGuoBean> data) {
                        list.addAll(data);
                        adapter.notifyDataSetChanged();
                        ye++;
                    }
                });
    }
}
