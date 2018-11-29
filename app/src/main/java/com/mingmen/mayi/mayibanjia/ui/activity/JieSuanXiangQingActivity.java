package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.YunFeiJieSuanBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.JieSuanXiangQingAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JieSuanXiangQingActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rv_xiangqing)
    RecyclerView rvXiangqing;

    private List<YunFeiJieSuanBean.DdListBean> list = new ArrayList<>();
    private JieSuanXiangQingAdapter adapter;
    private Context mContext;
    private String wl_cars_order_id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_jie_suan_xiang_qing;
    }

    @Override
    protected void initData() {
        tvTitle.setText("结算详情");
        mContext = JieSuanXiangQingActivity.this;
        wl_cars_order_id = getIntent().getStringExtra("wl_cars_order_id");
        adapter = new JieSuanXiangQingAdapter(mContext, list, JieSuanXiangQingActivity.this);
        rvXiangqing.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvXiangqing.setAdapter(adapter);
        getList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }

    public void getList(){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getYunfeiList(PreferenceUtils.getString(MyApplication.mContext, "token", ""),"", wl_cars_order_id))
                .setDataListener(new HttpDataListener<YunFeiJieSuanBean>() {
                    @Override
                    public void onNext(YunFeiJieSuanBean bean) {
                        list.addAll(bean.getDdList());
//                        adapter.setSelect(false);
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
