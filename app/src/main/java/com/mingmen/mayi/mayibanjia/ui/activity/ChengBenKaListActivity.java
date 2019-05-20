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
import com.mingmen.mayi.mayibanjia.bean.CbkListBean;
import com.mingmen.mayi.mayibanjia.bean.DingDanBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ChengBenKaAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.CaipinSousuoDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dingdan.DingDanActivity;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.JumpUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChengBenKaListActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_sousuo)
    ImageView ivSousuo;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.ll_add)
    LinearLayout llAdd;
    @BindView(R.id.rv_list)
    SwipeMenuRecyclerView rvList;
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

    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;
    private int ye = 1;
    private Context mContext;
    private List<CbkListBean> mlist = new ArrayList<>();
    private ChengBenKaAdapter adapter;
    private CaipinSousuoDialog sousuoDialog;
    private String name = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_cheng_ben_ka_list;
    }

    @Override
    protected void initData() {
        mContext = ChengBenKaListActivity.this;
        adapter = new ChengBenKaAdapter(mContext,mlist);
        tvTishiLeft.setText("哇~列表里是空的，快去");
        tvTishiCenter.setText("新建菜品");
        tvTishiRight.setText("~吧");
        mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                // 该加载更多啦。
                getData("");
            }
        };
        rvList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
//        rvDingdan.useDefaultLoadMore(); // 使用默认的加载更多的View。
        rvList.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
//        rvDingdan.loadMoreFinish(false, true);

        refreshLayout.setColorSchemeResources(R.color.zangqing, R.color.zangqing,
                R.color.zangqing, R.color.zangqing);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ye = 1;
                mlist.clear();
                adapter.notifyDataSetChanged();
                getData("");
                refreshLayout.setRefreshing(false);
            }
        });
        rvList.setAdapter(adapter);
        getData("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.iv_sousuo, R.id.iv_add,R.id.ll_list_null})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_sousuo:
                sousuoDialog = new CaipinSousuoDialog(mContext,
                        mContext.getResources().getIdentifier("BottomDialog", "style", mContext.getPackageName()));
                sousuoDialog.showDialog();
                sousuoDialog.getBtQueding().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sousuoDialog.cancel();
                        ye = 1;
                        mlist.clear();
                        adapter.notifyDataSetChanged();
                        getData(sousuoDialog.getEtQiyemingcheng().toString().trim());
                    }
                });
                sousuoDialog.getTvQuxiao().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sousuoDialog.cancel();
                    }
                });
                break;
            case R.id.iv_add:
                Jump_intent(AddCbkActivity.class,new Bundle());
                break;
            case R.id.ll_list_null:
                Jump_intent(AddCbkActivity.class,new Bundle());
                break;
        }
    }

    private void getData(String name) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getCbkList(PreferenceUtils.getString(MyApplication.mContext, "token", ""),name, ye))
                .setDataListener(new HttpDataListener<List<CbkListBean>>() {
                    @Override
                    public void onNext(List<CbkListBean> list) {
                        int mysize =list==null?0:list.size();
                        if(ye==1&&mysize==0){
                            llListNull.setVisibility(View.VISIBLE);
                            rvList.setVisibility(View.GONE);
                        } else {
                            llListNull.setVisibility(View.GONE);
                            rvList.setVisibility(View.VISIBLE);
                        }
                        if (!"null".equals(String.valueOf(list))) {
                            mlist.clear();
                            mlist.addAll(list);
                            if (list.size() == 10) {
                                rvList.loadMoreFinish(false, true);
                            } else if (list.size() > 0) {
                                rvList.loadMoreFinish(false, false);
                            } else {
                                rvList.loadMoreFinish(true, false);
                            }
                            adapter.notifyDataSetChanged();
                            ye++;
                        }

                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(ye!=1){
            ye = 1;
            mlist.clear();
            adapter.notifyDataSetChanged();
            getData("");
        }
    }
}
