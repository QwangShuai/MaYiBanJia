package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.YinHangKaBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.XuanZeYinHangKaAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.YinHangKaAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.JumpUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YinHangKaActivity extends BaseActivity {

    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.rv_yin_hang_ka)
    RecyclerView rvYinHangKa;
    @BindView(R.id.btn_add_card)
    Button btnAddCard;
    private List<YinHangKaBean> mList = new ArrayList<>();
    private YinHangKaAdapter adapter ;
    private Context mContext;
    private int tixian = 0;
    @Override
    public int getLayoutId() {
        return R.layout.activity_yin_hang_ka;
    }

    @Override
    protected void initData() {
        mContext = YinHangKaActivity.this;
        tixian = getIntent().getIntExtra("tixian",0);
        adapter = new YinHangKaAdapter(mContext, mList, new YinHangKaAdapter.CallBack() {
            @Override
            public void xuanzhong(YinHangKaBean bean) {
                if(tixian!=0){
                    Intent it = new Intent();
                    it.putExtra("bean",bean);
                    setResult(1,it);
                    finish();
                }
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

    @OnClick({R.id.iv_fanhui, R.id.btn_add_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
//                finish();
                myBack();
                break;

            case R.id.btn_add_card:
                getZiZhi();
                break;
        }
    }
    public void getBankCardList(){//获取我的银行卡
        HttpManager.getInstance().with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getMyBankCardList(PreferenceUtils.getString(MyApplication.mContext, "token","")))
                .setDataListener(new HttpDataListener<List<YinHangKaBean>>() {
                    @Override
                    public void onNext(List<YinHangKaBean> data) {
                        mList.clear();
                        mList.addAll(data);
                        adapter.notifyDataSetChanged();
                    }
                });
    }
    public void getZiZhi(){//资质认证
        HttpManager.getInstance().with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getZiZhi(PreferenceUtils.getString(MyApplication.mContext, "token","")))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        if(StringUtil.isValid(data)){
                            Bundle bundle = new Bundle();
                            bundle.putString("principal",data);
                            JumpUtil.Jump_intent(mContext,YinHangKaTianJiaActivity.class,bundle);
                        } else {
                            Bundle bundle = new Bundle();
                            bundle.putString("state","待审核");
                            JumpUtil.Jump_intent(mContext,ZiZhiRenZhengActivity.class,bundle);
                        }

                    }
                });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getBankCardList();
    }

    private void myBack(){
        Intent it = new Intent();
        it.putExtra("bean","");
        setResult(1,it);
        finish();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        myBack();
    }
}
