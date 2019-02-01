package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.PingJiaTypeListBean;
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
    @BindView(R.id.btn_all)
    Button btnAll;
    @BindView(R.id.btn_good)
    Button btnGood;
    @BindView(R.id.btn_bad)
    Button btnBad;

    private Context mContext=QuanBuPingjiaActivity.this;
    private PingJiaAdapter adapter;
    private int ye=1;
    private List<XQPingJiaBean> mlist = new ArrayList<>();
    private String company_id;
    private String type = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_quanbupingjia;
    }

    @Override
    protected void initData() {
        company_id = getIntent().getStringExtra("company_id");
        getpingjiaCount();
        getpingjia();
        adapter = new PingJiaAdapter();
        SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                getpingjia();
            }
        };
        rvPingjia.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL, false));
        rvPingjia.useDefaultLoadMore(); // 使用默认的加载更多的View。
        rvPingjia.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        rvPingjia.loadMoreFinish(false, true);
        rvPingjia.setAdapter(adapter);
    }
//
    private void getpingjia() {
            HttpManager.getInstance()
             .with(mContext)
                    .setObservable(
                RetrofitManager
                        .getService()
                        .getpingjia(PreferenceUtils.getString(MyApplication.mContext, "token",""),company_id,ye+"",type))
                .setDataListener(new HttpDataListener<List<XQPingJiaBean>>() {
            @Override
            public void onNext(List<XQPingJiaBean> data) {
                Log.e("data", data + "---");
                if (ye == 1) {
                    mlist.clear();
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
                ye++;
            }});


    }


    @OnClick({R.id.iv_back, R.id.iv_sangedian,R.id.btn_all,R.id.btn_good,R.id.btn_bad})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_sangedian:

                break;
            case R.id.btn_all:
                type = "";
                setViewShow();
                break;
            case R.id.btn_good:
                type = "1";
                setViewShow();
                break;
            case R.id.btn_bad:
                type = "2";
                setViewShow();
                break;
        }
    }

    private void setViewShow(){
        btnAll.setBackgroundResource(R.drawable.fillet_solid_f5f5f5_20);
        btnGood.setBackgroundResource(R.drawable.fillet_solid_f5f5f5_20);
        btnBad.setBackgroundResource(R.drawable.fillet_solid_f5f5f5_20);
        btnAll.setTextColor(mContext.getResources().getColor(R.color.lishisousuo));
        btnGood.setTextColor(mContext.getResources().getColor(R.color.lishisousuo));
        btnBad.setTextColor(mContext.getResources().getColor(R.color.lishisousuo));
        switch (type){
            case "":
                btnAll.setBackgroundResource(R.drawable.fillet_solid_zangqing_40);
                btnAll.setTextColor(mContext.getResources().getColor(R.color.white));
                break;
            case "1":
                btnGood.setBackgroundResource(R.drawable.fillet_solid_zangqing_40);
                btnGood.setTextColor(mContext.getResources().getColor(R.color.white));
                break;
            case "2":
                btnBad.setBackgroundResource(R.drawable.fillet_solid_zangqing_40);
                btnBad.setTextColor(mContext.getResources().getColor(R.color.white));
                break;
        }
        ye = 1;
        getpingjia();
    }
    private void getpingjiaCount() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getPingjiaTypeNumber(PreferenceUtils.getString(MyApplication.mContext, "token", ""),company_id))
                .setDataListener(new HttpDataListener<PingJiaTypeListBean>() {
                    @Override
                    public void onNext(PingJiaTypeListBean data) {
                        btnAll.setText("全部  "+ data.getAllP());
                        btnGood.setText("好评  "+ data.getGoodP());
                        btnBad.setText("差评  "+ data.getBadP());
                    }});


    }
}
