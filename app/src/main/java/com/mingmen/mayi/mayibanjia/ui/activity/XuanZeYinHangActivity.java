package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.XuanZeYinHangKaBean;
import com.mingmen.mayi.mayibanjia.bean.YinHangKaBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.XuanZeYinHangKaAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XuanZeYinHangActivity extends BaseActivity {

    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.rv_yin_hang_ka)
    RecyclerView rvYinHangKa;

    private List<XuanZeYinHangKaBean> mList = new ArrayList<>();
    private XuanZeYinHangKaAdapter adapter ;
    private Context mContext;
    @Override
    public int getLayoutId() {
        return R.layout.activity_xuan_ze_yin_hang;
    }

    @Override
    protected void initData() {
        mContext = XuanZeYinHangActivity.this;
        adapter = new XuanZeYinHangKaAdapter(mContext, mList, new XuanZeYinHangKaAdapter.CallBack() {
            @Override
            public void xuanzhong(String msg,String son_name) {
                Intent it = new Intent(mContext,YinHangKaTianJiaActivity.class);
                it.putExtra("son_number",msg);
                it.putExtra("son_name",son_name);
                setResult(1,it);
                finish();
            }
        });
        rvYinHangKa.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvYinHangKa.setAdapter(adapter);
        getBankCardList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_fanhui)
    public void onViewClicked() {
        finish();
    }
    public void getBankCardList(){
        HttpManager.getInstance().with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getBankCardList())
                .setDataListener(new HttpDataListener<List<XuanZeYinHangKaBean>>() {
                    @Override
                    public void onNext(List<XuanZeYinHangKaBean> data) {
                        mList.addAll(data);
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
