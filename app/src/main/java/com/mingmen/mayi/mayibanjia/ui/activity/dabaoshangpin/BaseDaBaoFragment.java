package com.mingmen.mayi.mayibanjia.ui.activity.dabaoshangpin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.AddQrCodeBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.AddQrCodeActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.AddQrCodeAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseFragment;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/7/28/028.
 */

public abstract class BaseDaBaoFragment extends BaseFragment {

    @BindView(R.id.rv_dingdan)
    SwipeMenuRecyclerView rvDingdan;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    View view;
    @BindView(R.id.tv_tishi_left)
    TextView tvTishiLeft;
    @BindView(R.id.tv_tishi_center)
    TextView tvTishiCenter;
    @BindView(R.id.tv_tishi_right)
    TextView tvTishiRight;
    @BindView(R.id.ll_list_null)
    LinearLayout llListNull;
    Unbinder unbinder;
    private ArrayList<AddQrCodeBean> mlist = new ArrayList<AddQrCodeBean>();
    ;
    private AddQrCodeAdapter adapter;
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;
    private int ye = 1;
    private boolean b = false;
    protected boolean isCreate = false;
    private String gy_order_id = "";
    private String son_order_id = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreate = true;
    }

    @Override
    protected View getSuccessView() {
        view = View.inflate(MyApplication.mContext, R.layout.fragment_dingdan, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void loadData() {
        stateLayout.showSuccessView();
    }

    @Override
    public void onStart() {
        super.onStart();
        initview();
        getData(true);
    }

    //数据
    private void getData(final boolean b) {
        HttpManager.getInstance()
                .with(getContext())
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getQrCodeSp(PreferenceUtils.getString(MyApplication.mContext, "token", ""), gy_order_id, getZhuangTai(),son_order_id, ye + ""))
                .setDataListener(new HttpDataListener<List<AddQrCodeBean>>() {
                    @Override
                    public void onNext(final List<AddQrCodeBean> data) {
                        int size = data == null ? 0 : data.size();
                        if (b) {
                            mlist.clear();
                            adapter.notifyDataSetChanged();
                        }
                        if (size != 0) {
                            mlist.addAll(data);
                            if (data.size() == 10) {
                                rvDingdan.loadMoreFinish(false, true);
                            } else if (data.size() > 0) {
                                rvDingdan.loadMoreFinish(false, false);
                            } else {
                                rvDingdan.loadMoreFinish(true, false);
                            }
                            adapter.notifyDataSetChanged();
                        }
                        ye++;
                    }
                });
    }

    private void initview() {
        AddQrCodeActivity activity = (AddQrCodeActivity) getActivity();
        gy_order_id = activity.getGy_order_id();
        son_order_id = activity.getSon_order_id();
        adapter = new AddQrCodeAdapter(getActivity(), mlist, gy_order_id);
        mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                // 该加载更多啦。
                updateList();
            }
        };

        rvDingdan.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//        rvDingdan.useDefaultLoadMore(); // 使用默认的加载更多的View。
        rvDingdan.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
//        rvDingdan.loadMoreFinish(false, true);

        refreshLayout.setColorSchemeResources(R.color.zangqing, R.color.zangqing,
                R.color.zangqing, R.color.zangqing);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ye = 1;
                mlist.clear();
                adapter.notifyDataSetChanged();
                updateList();
                refreshLayout.setRefreshing(false);
            }
        });
        rvDingdan.setAdapter(adapter);
//        tvTishiLeft.setText("点击右上角");
//        tvTishiCenter.setText("新增");
//        tvTishiRight.setText("生成二维码用于贴在商品外包装");
    }

    public abstract String getZhuangTai();

    public void Jump_intent(Class<?> cla, Bundle bundle) {
        Intent intent = new Intent(getContext(), cla);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void updateList() {
        HttpManager.getInstance()
                .with(getContext())
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getQrCodeSp(PreferenceUtils.getString(MyApplication.mContext, "token", ""), gy_order_id, getZhuangTai(),son_order_id, ye + ""))
                .setDataListener(new HttpDataListener<List<AddQrCodeBean>>() {
                    @Override
                    public void onNext(final List<AddQrCodeBean> data) {
                        int size = data == null ? 0 : data.size();
//                        if(ye==1){
//                            if(size==0){
//                                rvDingdan.setVisibility(View.GONE);
//                                llListNull.setVisibility(View.VISIBLE);
//                            } else {
//                                rvDingdan.setVisibility(View.VISIBLE);
//                                llListNull.setVisibility(View.GONE);
//                            }
//                        }
                        mlist.addAll(data);
                        if (size != 0) {
                            if (data.size() == 10) {
                                rvDingdan.loadMoreFinish(false, true);
                            } else if (data.size() > 0) {
                                rvDingdan.loadMoreFinish(false, false);
                            } else {
                                rvDingdan.loadMoreFinish(true, false);
                            }
                        }
                        adapter.notifyDataSetChanged();
                        ye++;
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onResume() {
        super.onResume();
        ye = 1;
//        mlist.clear();
//        updateList(true);
        getData(true);
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
