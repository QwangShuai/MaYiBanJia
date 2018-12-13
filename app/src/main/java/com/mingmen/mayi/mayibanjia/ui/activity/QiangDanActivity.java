package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.QiangDanBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.qiangdan.QiangDanAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/30.
 */

public class QiangDanActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rv_qiangdan)
    RecyclerView rvQiangdan;
    private Context mContext;
    private QiangDanAdapter adapter;
    private List<QiangDanBean> qiangdanData;
    @Override
    public int getLayoutId() {
        return R.layout.activity_qiangdan;
    }

    @Override
    protected void initData() {
        mContext=QiangDanActivity.this;
        tvTitle.setText("抢单");

//        adapter=new QiangDanAdapter(this);
//        rvQiangdan.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
//        rvQiangdan.setAdapter(adapter);
//        getQiangDanList();
    }
    private void getQiangDanList(){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .qiangdanlist(PreferenceUtils.getString(MyApplication.mContext,"token",""),""))
                .setDataListener(new HttpDataListener<List<QiangDanBean>>() {
                    @Override
                    public void onNext(List<QiangDanBean> data) {
//                        Log.e("data",data+"---");
//                        qiangdanData=new ArrayList<>();
//                        qiangdanData.addAll(data);
//                        adapter.setNewData(data);
                    }
                },false);
    }
    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
