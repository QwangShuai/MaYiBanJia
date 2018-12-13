package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.JueSeBean;
import com.mingmen.mayi.mayibanjia.bean.ZiZhangHuBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.JueSeGuanLiAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ZiZhangHuAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZiZhangHuActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rv_list)
    SwipeMenuRecyclerView rvList;

    private Context mContext;
    private String company_id="";
    private String company_name="";
    private List<ZiZhangHuBean> mList = new ArrayList<>();
    private ZiZhangHuAdapter adapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_zi_zhang_hu;
    }

    @Override
    protected void initData() {
        tvTitle.setText("子账户");
        tvRight.setText("添加");
        mContext = ZiZhangHuActivity.this;
        company_id = getIntent().getStringExtra("id");
        company_name = getIntent().getStringExtra("name");

        adapter = new ZiZhangHuAdapter(mContext,mList);
        rvList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvList.setAdapter(adapter);

        getmoren();
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
                finish();
                break;
            case R.id.tv_right:
                Bundle zzh  = new Bundle();
                zzh.putString("name",company_name);
                zzh.putString("id",company_id);
                Jump_intent(AddZiZhuangHuActivity.class,zzh);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getmoren();
    }

    private void getmoren() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getZizhanghuList(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<List<ZiZhangHuBean>>() {
                    @Override
                    public void onNext(List<ZiZhangHuBean> data) {
                        int mysize = data==null?0:data.size();
                        if(mysize!=0){
                            mList.clear();
                            mList.addAll(data);
                            adapter.notifyDataSetChanged();
                        }
                    }
                },true);
    }
}
