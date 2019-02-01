package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.SiJiWLXQBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.SJPSXiangQingAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.SiJiPeiSongAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.qrCode.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PeiSongXiangQingActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.rv_peisong)
    RecyclerView rv_peisong;
//    private String id = "";
    private String wlID = "";
    private Context mContext;
    private SJPSXiangQingAdapter adapter;
    private List<SiJiWLXQBean> mList = new ArrayList<SiJiWLXQBean>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_pei_song_xiang_qing;
    }

    @Override
    protected void initData() {
        mContext = PeiSongXiangQingActivity.this;
        tv_title.setText("配送详情");
        wlID = getIntent().getStringExtra("xqID");
        getWLXQ(wlID);
    }

    public void getWLXQ(String id) {
        mList.clear();
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getSJWuLiuXQ(PreferenceUtils.getString(MyApplication.mContext, "token", ""),id, "1"))
                .setDataListener(new HttpDataListener<List<SiJiWLXQBean>>() {
                    @Override
                    public void onNext(List<SiJiWLXQBean> data) {
                        mList = data;
                        adapter = new SJPSXiangQingAdapter(mContext, mList, PeiSongXiangQingActivity.this);
                        rv_peisong.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                        rv_peisong.setAdapter(adapter);
                    }
                });
    }

    @OnClick(R.id.iv_back)
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
