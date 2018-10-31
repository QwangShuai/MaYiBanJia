package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

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
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/14.
 */

public class QuanBuPingjiaActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_sangedian)
    ImageView ivSangedian;
    @BindView(R.id.rv_pingjia)
    SwipeMenuRecyclerView rvPingjia;
    private Context mContext=QuanBuPingjiaActivity.this;
    private PingJiaAdapter adapter;
    private int ye=1;
    private List<XQPingJiaBean> mlist;
    private String company_id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_quanbupingjia;
    }

    @Override
    protected void initData() {
        company_id = getIntent().getStringExtra("company_id");
        getpingjia(1);
        adapter = new PingJiaAdapter();
        SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                ye++;
                Log.e("ye++++", ye + "--");
                getpingjia(ye);
            }
        };
        rvPingjia.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL, false));
        rvPingjia.useDefaultLoadMore(); // 使用默认的加载更多的View。
        rvPingjia.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        rvPingjia.loadMoreFinish(false, true);
        rvPingjia.setAdapter(adapter);
    }
//
    private void getpingjia(final int ye) {
            HttpManager.getInstance()
             .with(mContext)
                    .setObservable(
                RetrofitManager
                        .getService()
                        .getpingjia(PreferenceUtils.getString(MyApplication.mContext, "token",""),company_id,ye+""))
                .setDataListener(new HttpDataListener<List<XQPingJiaBean>>() {
            @Override
            public void onNext(List<XQPingJiaBean> data) {
                Log.e("data", data + "---");
                if (ye == 1) {
                    mlist = new ArrayList<>();
                    rvPingjia.loadMoreFinish(false, true);
                }else{
                    if (data.size()>0&&data.size()<5){
                        rvPingjia.loadMoreFinish(false, false);
                    }else{
                        if (data.size()==0){
                            rvPingjia.loadMoreFinish(true, false);
                        }else{
                            rvPingjia.loadMoreFinish(false, true);
                        }
                    }
                }

                mlist.addAll(data);
                adapter.setNewData(mlist);
            }});


    }


    @OnClick({R.id.iv_back, R.id.iv_sangedian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_sangedian:

                break;
        }
    }
}
