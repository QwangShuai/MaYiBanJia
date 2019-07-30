package com.mingmen.mayi.mayibanjia.ui.activity.wuliujingli;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CheliangBean;
import com.mingmen.mayi.mayibanjia.bean.WuliuDingdanBean;
import com.mingmen.mayi.mayibanjia.bean.WuliuShaixuanBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.WuLiuActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.WuLiuFenPeiAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.WuliuFenpeiDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseFragment;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.custom.SwipeRecyclerView;
import com.mingmen.mayi.mayibanjia.utils.qrCode.CaptureActivity;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/7/28/028.
 */

public abstract class BaseJingliFragment extends BaseFragment {

    @BindView(R.id.rv_dingdan)
    SwipeRecyclerView rvShangpinguanli;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.ll_yifenche)
    LinearLayout llYifenche;
    View view;
    @BindView(R.id.tv_daiqueding)
    TextView tvDaiqueding;
    @BindView(R.id.tv_yijieshou)
    TextView tvYijieshou;
    @BindView(R.id.tv_tishi_left)
    TextView tvTishiLeft;
    @BindView(R.id.tv_tishi_center)
    TextView tvTishiCenter;
    @BindView(R.id.tv_tishi_right)
    TextView tvTishiRight;
    @BindView(R.id.ll_list_null)
    LinearLayout llListNull;
    Unbinder unbinder;

    private List<WuliuDingdanBean> mlist = new ArrayList<>();
    private WuLiuFenPeiAdapter adapter;
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;
    private int ye = 1;
    private final static int SCANNIN_GREQUEST_CODE = 1;
    private String wuliu_type = "";
    WuliuFenpeiDialog dialog;
    private String wl_cars_order_number = "";
    private String marketName = "";
    private String driverName = "";
    private String driverPhone = "";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected View getSuccessView() {
        view = View.inflate(MyApplication.mContext, R.layout.fragment_shangpinguanli, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void loadData() {
        stateLayout.showSuccessView();
        wuliu_type = getZhuangTai();
        initview();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    //数据
    public void getPeiSong() {
        HttpManager.getInstance()
                .with(getContext())
                .setObservable(RetrofitManager.getService()
                        .getWuliu(PreferenceUtils.getString(MyApplication.mContext, "token", ""), ye + "", wuliu_type, marketName, driverPhone, driverName, wl_cars_order_number))
                .setDataListener(new HttpDataListener<List<WuliuDingdanBean>>() {
                    @Override
                    public void onNext(List<WuliuDingdanBean> list) {
                        int mysize = list==null?0:list.size();
                        if (mysize == 5) {
                            rvShangpinguanli.loadMoreFinish(false, true);
                        } else if (mysize > 0) {
                            rvShangpinguanli.loadMoreFinish(false, false);
                        } else {
                            rvShangpinguanli.loadMoreFinish(true, false);
                        }
//                        if(ye==1){
//                            mlist.clear();
//                            adapter.notifyDataSetChanged();
//                        }
                        mlist.addAll(list);
                        adapter.notifyDataSetChanged();
                        ye++;
                    }
                });
    }


    private void initview() {
        if (getZhuangTai().equals("7")) {
            llYifenche.setVisibility(View.VISIBLE);
        } else {
            llYifenche.setVisibility(View.GONE);
        }
        WuLiuActivity activity = (WuLiuActivity) getActivity();
        EventBus.getDefault().register(this);
        mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                getPeiSong();
            }
        };
        refreshLayout.setColorSchemeResources(R.color.zangqing, R.color.zangqing,
                R.color.zangqing, R.color.zangqing);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ye = 1;
                mlist.clear();
                adapter.notifyDataSetChanged();
                getPeiSong();
                refreshLayout.setRefreshing(false);
            }
        });
        rvShangpinguanli.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
//        rvShangpinguanli.useDefaultLoadMore(); // 使用默认的加载更多的View。
        rvShangpinguanli.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        adapter = new WuLiuFenPeiAdapter(getContext(), mlist, new WuLiuFenPeiAdapter.CallBack() {
            @Override
            public void onClick(int position, View v, WuliuDingdanBean bean) {
                switch (v.getId()){
                    case R.id.tv_fenpeiwuliuche:
                        dialog = new WuliuFenpeiDialog(getContext(),bean.getCars_type(),bean,"0");
                        dialog.show();
                        break;
                    case R.id.tv_biangeng:
                        dialog = new WuliuFenpeiDialog(getContext(),bean.getCars_type(),bean,"1");
                        dialog.show();
                        break;
                }

            }
        });
        rvShangpinguanli.setAdapter(adapter);
        rvShangpinguanli.loadMoreFinish(false, true);
        getPeiSong();
    }

    public abstract String getZhuangTai();

    public void onResume() {
        super.onResume();
        Log.e("onResume: ", getZhuangTai());
        if (ye != 1) {
            ye = 1;
            mlist.clear();
            adapter.notifyDataSetChanged();
            getPeiSong();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void updateQrCode(String id) {
        HttpManager.getInstance()
                .with(getContext())
                .setObservable(
                        RetrofitManager
                                .getService()
                                .updateQrCode(PreferenceUtils.getString(MyApplication.mContext, "token", ""), id, "", "1", ""))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        ye = 1;
                        mlist.clear();
                        adapter.notifyDataSetChanged();
                        getPeiSong();
                    }
                });
    }

    public void saomiaoQrCode() {
//        this.id = id;
        Intent intent = new Intent();
        intent.setClass(getContext(), CaptureActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateTimer(String message) {
        if(!message.equals("0000")){
            ye = 1;
            mlist.clear();
            adapter.notifyDataSetChanged();
            getPeiSong();
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void update(WuliuShaixuanBean bean) {
        driverName = bean.getDriverName();
        driverPhone = bean.getDriverPhone();
        marketName = bean.getMarketName();
        wl_cars_order_number = bean.getWl_cars_order_number();
        ye = 1;
        mlist.clear();
        adapter.notifyDataSetChanged();
        getPeiSong();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateText(CheliangBean bean) {
        dialog.setText(bean);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        if (resultCode == CaptureActivity.RESULT_CODE_QR_SCAN) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString(CaptureActivity.INTENT_EXTRA_KEY_QR_SCAN);
            Log.e("678678", scanResult);
            updateQrCode(scanResult);
        }
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

    @OnClick({R.id.tv_daiqueding, R.id.tv_yijieshou})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_daiqueding:
                ye = 1;
                mlist.clear();
                adapter.notifyDataSetChanged();
                tvDaiqueding.setTextColor(getContext().getResources().getColor(R.color.zangqing));
                tvYijieshou.setTextColor(getContext().getResources().getColor(R.color.lishisousuo));
                getPeiSong();
                break;
            case R.id.tv_yijieshou:
                ye = 1;
                mlist.clear();
                adapter.notifyDataSetChanged();
                tvDaiqueding.setTextColor(getContext().getResources().getColor(R.color.lishisousuo));
                tvYijieshou.setTextColor(getContext().getResources().getColor(R.color.zangqing));
                wuliu_type = "7";
                wuliu_type = "8";
                getPeiSong();
                break;
        }
    }
}
