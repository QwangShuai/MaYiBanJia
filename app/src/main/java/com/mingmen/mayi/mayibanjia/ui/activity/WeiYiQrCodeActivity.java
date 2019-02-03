package com.mingmen.mayi.mayibanjia.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.WeiYiQrCodeBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.WeiNiTuiJianAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.WeiYiQrCodeAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeiYiQrCodeActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rv_qr_list)
    RecyclerView rvQrList;

    private List<WeiYiQrCodeBean> list = new ArrayList<WeiYiQrCodeBean>();
    private WeiYiQrCodeAdapter adapter;
    private String gyID;
    private String ddID;
    private String type;
    @Override
    public int getLayoutId() {
        return R.layout.activity_wei_yi_qr_code;
    }

    @Override
    protected void initData() {
        tvTitle.setText("唯一码");
        type = getIntent().getStringExtra("type");
        if(type.equals("gyID")){
            gyID = getIntent().getStringExtra("gyID");
            ddID = "";
        } else {
            ddID = getIntent().getStringExtra("ddID");
            gyID = "";
        }



        getQrList();
    }
    @OnClick({R.id.iv_back})
    protected void OnClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void getQrList(){
        HttpManager.getInstance()
                .with(WeiYiQrCodeActivity.this)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getWeiYiQrCodeList(PreferenceUtils.getString(MyApplication.mContext, "token",""),gyID,ddID))
                .setDataListener(new HttpDataListener<List<WeiYiQrCodeBean>>() {
                    @Override
                    public void onNext(List<WeiYiQrCodeBean> data) {
//                        list = data;
                        adapter = new WeiYiQrCodeAdapter(WeiYiQrCodeActivity.this,data);
                        rvQrList.setLayoutManager(new LinearLayoutManager(WeiYiQrCodeActivity.this, LinearLayoutManager.VERTICAL, false));
                        rvQrList.setAdapter(adapter);
                    }
                });
    }
}
