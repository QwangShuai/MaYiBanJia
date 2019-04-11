package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.WuLiuBean;
import com.mingmen.mayi.mayibanjia.bean.WuLiuObjBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.SiJiPeiSongAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.custom.SwipeRecyclerView;
import com.mingmen.mayi.mayibanjia.utils.qrCode.CaptureActivity;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShichangWuliuActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rv_dingdan)
    SwipeRecyclerView rvDingdan;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    private ArrayList<WuLiuBean> mlist = new ArrayList<>();
    private SiJiPeiSongAdapter adapter;
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;
    private int ye = 1;
    private Context mContext;
    private String type="";
    private String isShichang="";
    private final static int SCANNIN_GREQUEST_CODE = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shichang_wuliu;
    }

    @Override
    protected void initData() {
        mContext = this;
        type = getIntent().getStringExtra("type");
        isShichang = getIntent().getStringExtra("isShichang");
        switch (type){
            case "1401":
                if(isShichang.equals("2")){
                    tvTitle.setText("待处理");
                }else if(isShichang.equals("1")){
                    tvTitle.setText("待确认");
                }else {
                    tvTitle.setText("待取货");
                }
                break;
            case "1402":
                tvTitle.setText("待送货");
                break;
            case "1406":
                tvTitle.setText("预警订单");
                break;
            case "1403":
                tvTitle.setText("已完成");
                break;
            case "1407":
                tvTitle.setText("自处理订单");
                break;
        }

        initview();
        getPeiSong();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_title, R.id.iv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_title:
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                break;
        }
    }
    //数据
    public void getPeiSong() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getWuliu(PreferenceUtils.getString(MyApplication.mContext, "token", ""), "", ye + "", type,isShichang))
                .setDataListener(new HttpDataListener<WuLiuObjBean<WuLiuBean>>() {
                    @Override
                    public void onNext(WuLiuObjBean<WuLiuBean> bean) {
                        mlist.clear();
                        adapter.notifyDataSetChanged();
                        if (bean.getDdList().size() == 5) {
                            rvDingdan.loadMoreFinish(false, true);
                        } else if (bean.getDdList().size() > 0) {
                            rvDingdan.loadMoreFinish(false, false);
                        } else {
                            rvDingdan.loadMoreFinish(true, false);
                        }
                        mlist.addAll(bean.getDdList());
                        adapter.notifyDataSetChanged();
                        ye++;
                    }
                });
    }

    private void initview() {
        mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                getPeiSong();
            }
        };
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
        rvDingdan.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvDingdan.useDefaultLoadMore(); // 使用默认的加载更多的View。
        rvDingdan.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        rvDingdan.loadMoreFinish(false, true);
        adapter = new SiJiPeiSongAdapter(mContext,mlist,type,this);
        rvDingdan.setAdapter(adapter);
        getPeiSong();

    }

    public void updateQrCode(String id) {
        HttpManager.getInstance()
                .with(mContext)
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
        Intent intent = new Intent();
        intent.setClass(mContext, CaptureActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
    }
    public void onResume() {
        super.onResume();
        if (ye != 1) {
            ye = 1;
            mlist.clear();
            adapter.notifyDataSetChanged();
            getPeiSong();

        }
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
}
