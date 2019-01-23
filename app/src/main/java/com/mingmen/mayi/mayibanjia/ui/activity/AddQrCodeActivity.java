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
import com.mingmen.mayi.mayibanjia.bean.AddQrCodeBean;
import com.mingmen.mayi.mayibanjia.bean.QiYeLieBiaoBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.AddQrCodeAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ThreeDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddQrCodeActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.iv_sangedian)
    ImageView ivSangedian;
    @BindView(R.id.rv_qrcode)
    SwipeMenuRecyclerView rvQrcode;
    @BindView(R.id.srl_shuaxin)
    SwipeRefreshLayout srlShuaxin;

    private Context mContext;
    private AddQrCodeAdapter adapter;
    private List<AddQrCodeBean> mlist = new ArrayList<>();
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;
    private int ye = 1;
    private String gy_order_id = "";
    private String type = "2";
    private ThreeDialog dialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_qr_code;
    }

    @Override
    protected void initData() {
        tvTitle.setText("待打包商品");
        mContext = AddQrCodeActivity.this;
        gy_order_id = getIntent().getStringExtra("id");
        dialog = new ThreeDialog();
        dialog.setAddQrCodeActivity(this);
        dialog.setTop(AppUtil.dip2px(44));
        mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                // 该加载更多啦。
                getSpList(type);
            }
        };
        srlShuaxin.setColorSchemeResources(R.color.zangqing, R.color.zangqing,
                R.color.zangqing, R.color.zangqing);
        srlShuaxin.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 设置可见
                srlShuaxin.setRefreshing(true);
                // 重置adapter的数据源为空
                updateSpList(type);
                srlShuaxin.setRefreshing(false);
            }
        });
        adapter = new AddQrCodeAdapter(mContext,mlist,gy_order_id);
        rvQrcode.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvQrcode.setLoadMoreListener(mLoadMoreListener);
        rvQrcode.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.ll_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_title:
                dialog.show(getSupportFragmentManager());
                break;
        }
    }

    //查询商品列表
    public void getSpList(String type) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getQrCodeSp(gy_order_id,type,ye+""))
                .setDataListener(new HttpDataListener<List<AddQrCodeBean>>() {
                    @Override
                    public void onNext(final List<AddQrCodeBean> data) {
                        ye++;
                        int size = data==null?0:data.size();
                        if(size!=0){
                            mlist.addAll(data);
                            if(data.size()==10){
                                rvQrcode.loadMoreFinish(false, true);
                            }else if(data.size()>0){
                                rvQrcode.loadMoreFinish(false, false);
                            } else {
                                rvQrcode.loadMoreFinish(true, false);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

    }
    //查询商品列表
    public void updateSpList(String type) {
        ye = 1;
        mlist.clear();
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getQrCodeSp(gy_order_id,type,ye+""))
                .setDataListener(new HttpDataListener<List<AddQrCodeBean>>() {
                    @Override
                    public void onNext(final List<AddQrCodeBean> data) {
                        ye++;
                        int size = data==null?0:data.size();
                        mlist.addAll(data);
                        if(size!=0){
                            if(data.size()==10){
                                rvQrcode.loadMoreFinish(false, true);
                            }else if(data.size()>0){
                                rvQrcode.loadMoreFinish(false, false);
                            } else {
                                rvQrcode.loadMoreFinish(true, false);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                });

    }
    @Override
    protected void onResume() {
        super.onResume();
        ye = 1;
        mlist.clear();
        getSpList(type);
    }
}
