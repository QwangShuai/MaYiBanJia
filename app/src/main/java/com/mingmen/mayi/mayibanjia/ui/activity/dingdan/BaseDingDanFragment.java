package com.mingmen.mayi.mayibanjia.ui.activity.dingdan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.DingDanBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.DingDanXiangQingActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.FaBiaoPingJiaActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.WeiYiQrCodeActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.DingDanXiangQingAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.ghdingdan.GHDOrderActivity;
import com.mingmen.mayi.mayibanjia.ui.base.BaseFragment;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.qrCode.CaptureActivity;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/28/028.
 */

public abstract class BaseDingDanFragment extends BaseFragment {

    @BindView(R.id.rv_dingdan)
    SwipeMenuRecyclerView rvDingdan;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    View view;
    private ArrayList<DingDanBean> mlist = new ArrayList<DingDanBean>();
    ;
    private DingDanXiangQingAdapter adapter;
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;
    private int ye = 1;
    private boolean b = false;
    protected boolean isCreate = false;
    private String token = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreate = true;
    }
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser && isCreate) {
//            //相当于Fragment的onResume
//            //在这里处理加载数据等操作
//            ye = 1;
//            getData();
//            adapter.notifyDataSetChanged();
//            Log.e("onResume",getZhuangTai());
//        } else {
//            //相当于Fragment的onPause
//        }
//    }

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
        getData();
    }

    //数据
    private void getData() {
        DingDanActivity activity = (DingDanActivity) getActivity();
        token = activity.getToken();
        if (StringUtil.isValid(token)) {
            adapter.setClick(false);
        } else {
            token = PreferenceUtils.getString(MyApplication.mContext, "token", "");
        }
        HttpManager.getInstance()
                .with(getActivity())
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getdingdan(token, getZhuangTai(), ye))
                .setDataListener(new HttpDataListener<List<DingDanBean>>() {
                    @Override
                    public void onNext(List<DingDanBean> list) {
                        if (!"null".equals(String.valueOf(list))) {
                            mlist.clear();
                            mlist.addAll(list);
                            if (list.size() == 5) {
                                rvDingdan.loadMoreFinish(false, true);
                            } else if (list.size() > 0) {
                                rvDingdan.loadMoreFinish(false, false);
                            } else {
                                rvDingdan.loadMoreFinish(true, false);
                            }
                            adapter.notifyDataSetChanged();
                        }

                    }
                });
        ye++;
    }

    private void initview() {
        adapter = new DingDanXiangQingAdapter(getActivity(), mlist);
        mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                // 该加载更多啦。
                updateList(false);
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
                updateList(true);
                refreshLayout.setRefreshing(false);
            }
        });
        adapter.setOnItemClickListener(new DingDanXiangQingAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent it;
                switch (view.getId()) {
                    case R.id.ll_saoma:
                        saomiaoQrCode();
                        break;
                    case R.id.ll_rongqi:
                        it = new Intent(getActivity(), DingDanXiangQingActivity.class);
                        it.putExtra("orderID", mlist.get(position).getOrder_id());
                        startActivity(it);
                        break;
                    case R.id.tv_quhuoma:
                        it = new Intent(getActivity(), WeiYiQrCodeActivity.class);
                        it.putExtra("type","ddID");
                        it.putExtra("ddID", mlist.get(position).getOrder_id());
                        getActivity().startActivity(it);
                        break;
                    case R.id.bt_pingjia://评价
                        it = new Intent(getActivity(), FaBiaoPingJiaActivity.class);
                        it.putExtra("orderID", mlist.get(position).getOrder_id());
                        startActivity(it);
                        break;
//                    case R.id.btn_more://展开收起
//
//                        break;
                }
            }
        });
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

    public void saomiaoQrCode() {
//        this.id = id;
        Intent intent = new Intent();
        intent.setClass(getActivity(), CaptureActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(intent, 1);
    }

    public void updateQrCode(String id) {
        HttpManager.getInstance()
                .with(getActivity())
                .setObservable(
                        RetrofitManager
                                .getService()
                                .updateQrCode(token, id, "", "1", ""))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        ye = 1;
                        updateList(true);
                    }
                });
    }

    public void updateList(final boolean b_clear) {
        HttpManager.getInstance()
                .with(getActivity())
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getdingdan(token, getZhuangTai(), ye))
                .setDataListener(new HttpDataListener<List<DingDanBean>>() {
                    @Override
                    public void onNext(final List<DingDanBean> list) {
                        if (!"null".equals(String.valueOf(list))) {
                            if (list.size() == 5) {
                                rvDingdan.loadMoreFinish(false, true);
                            } else if (list.size() > 0) {
                                rvDingdan.loadMoreFinish(false, false);
                            } else {
                                rvDingdan.loadMoreFinish(true, false);
                            }
                            if (b_clear) {//刷新清空当前页面数据
                                mlist.clear();
                            }
                            mlist.addAll(list);
                            adapter.notifyDataSetChanged();
                        }

                    }
                }, false);
        ye++;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        if (resultCode == CaptureActivity.RESULT_CODE_QR_SCAN) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString(CaptureActivity.INTENT_EXTRA_KEY_QR_SCAN);
            updateQrCode(scanResult);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("onResume: ", getZhuangTai()+"----");
        ye = 1;
//        updateList(true);
        getData();
        adapter.notifyDataSetChanged();
    }
}
