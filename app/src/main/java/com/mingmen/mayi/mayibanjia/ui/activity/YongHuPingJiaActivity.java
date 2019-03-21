package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.PingJiaLableBean;
import com.mingmen.mayi.mayibanjia.bean.XQPingJiaBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.PingJiaAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.PingJiaGydAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YongHuPingJiaActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.bt_quanbu)
    Button btQuanbu;
    @BindView(R.id.bt_weihuifu)
    Button btWeihuifu;
    @BindView(R.id.bt_yihuifu)
    Button btYihuifu;
    @BindView(R.id.rv_pj)
    SwipeMenuRecyclerView rvPj;

    private Context mContext;
    private PingJiaGydAdapter adapter;
    private int ye = 1;
    private List<XQPingJiaBean> mlist = new ArrayList<>();
    private String type = "";
    @Override
    public int getLayoutId() {
        return R.layout.activity_yong_hu_ping_jia;
    }

    @Override
    protected void initData() {
        tvTitle.setText("用户评价");
        mContext = this;
        adapter = new PingJiaGydAdapter(mContext,mlist);
        adapter.setActivity(YongHuPingJiaActivity.this);
        SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                getpingjia();
            }
        };
        rvPj.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvPj.useDefaultLoadMore(); // 使用默认的加载更多的View。
        rvPj.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        rvPj.loadMoreFinish(false, true);
        rvPj.setAdapter(adapter);
        getpingjia();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_title, R.id.iv_back, R.id.tv_right, R.id.bt_quanbu, R.id.bt_weihuifu, R.id.bt_yihuifu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_title:
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                break;
            case R.id.bt_quanbu:
                type = "";
                setViewColor();
                break;
            case R.id.bt_weihuifu:
                type = "0";
                setViewColor();
                break;
            case R.id.bt_yihuifu:
                type = "1";
                setViewColor();
                break;
        }
    }
    private void getpingjia() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getGydPingjia(PreferenceUtils.getString(MyApplication.mContext, "token", ""), type, ye + ""))
                .setDataListener(new HttpDataListener<List<XQPingJiaBean>>() {
                    @Override
                    public void onNext(List<XQPingJiaBean> data) {
                        Log.e("data", data + "---");
                        if (data.size() > 0 && data.size() < 5) {
                            rvPj.loadMoreFinish(false, false);
                        } else {
                            if (data.size() == 0) {
                                rvPj.loadMoreFinish(true, false);
                            } else {
                                rvPj.loadMoreFinish(false, true);
                            }
                        }
                        mlist.addAll(data);
                        adapter.notifyDataSetChanged();
                        ye++;
                    }
                });


    }

    public void setViewColor() {
        btQuanbu.setTextColor(mContext.getResources().getColor(R.color.lishisousuo));
        btWeihuifu.setTextColor(mContext.getResources().getColor(R.color.lishisousuo));
        btYihuifu.setTextColor(mContext.getResources().getColor(R.color.lishisousuo));
        switch (type){
            case "":
                btQuanbu.setTextColor(mContext.getResources().getColor(R.color.zangqing));
                break;
            case "0":
                btWeihuifu.setTextColor(mContext.getResources().getColor(R.color.zangqing));
                break;
            case "1":
                btYihuifu.setTextColor(mContext.getResources().getColor(R.color.zangqing));
                break;
        }
        mlist.clear();
        adapter.notifyDataSetChanged();
        ye = 1;
        getpingjia();
    }
}
