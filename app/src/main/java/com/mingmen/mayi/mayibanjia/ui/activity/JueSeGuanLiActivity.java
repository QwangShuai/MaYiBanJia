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
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.JueSeGuanLiAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JueSeGuanLiActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rv_list)
    SwipeMenuRecyclerView rvList;

    private Context mContext;
    private List<JueSeBean> mList = new ArrayList<>();
    private JueSeGuanLiAdapter adapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_jue_se_guan_li;
    }

    @Override
    protected void initData() {
        tvTitle.setText("角色管理");
        mContext = JueSeGuanLiActivity.this;
        getmoren();
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

    private void getmoren() {
//        HttpManager.getInstance()
//                .with(mContext)
//                .setObservable(
//                        RetrofitManager
//                                .getService()
//                                .getJueseList(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
//                .setDataListener(new HttpDataListener<List<JueSeBean>>() {
//                    @Override
//                    public void onNext(List<JueSeBean> data) {
//                        int mysize = data==null?0:data.size();
//                        if(mysize!=0){
//                            mList = data;
//                            adapter = new JueSeGuanLiAdapter(mContext,mList);
//                            rvList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
//                            rvList.setAdapter(adapter);
//                        }
//                    }
//                },true);
    }
}
