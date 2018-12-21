package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.SPTGBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.SPTGOneAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.SiJiPeiSongAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShenPiChengGongActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.tv_heji)
    TextView tvHeji;

    private Context mContext;
    private SPTGOneAdapter adapter;
    private List<SPTGBean.FllistBean> mlist = new ArrayList<>();
    private String purchase_id = "";
    @Override
    public int getLayoutId() {
        return R.layout.activity_shen_pi_cheng_gong;
    }

    @Override
    protected void initData() {
        tvTitle.setText("审批通过");
        mContext = ShenPiChengGongActivity.this;
        purchase_id = getIntent().getStringExtra("id");
        adapter = new SPTGOneAdapter(mlist,mContext,ShenPiChengGongActivity.this);
        rvList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvList.setAdapter(adapter);
        rvList.setFocusable(false);
        rvList.setNestedScrollingEnabled(false);
        getShow();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                myBack();
                break;
            case R.id.tv_right:
                break;
        }
    }
    public void getShow() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getShenhechenggong(PreferenceUtils.getString(MyApplication.mContext, "token", ""), purchase_id))
                .setDataListener(new HttpDataListener<SPTGBean>() {
                    @Override
                    public void onNext(SPTGBean bean) {
                        mlist.clear();
//                        mlist = bean.getFllist();
                        tvHeji.setText(bean.getZongjia()+"");
                        mlist.addAll(bean.getFllist());
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    public void myBack(){
        Intent intent = new Intent(ShenPiChengGongActivity.this, CaiGouDanActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        myBack();
    }
}
