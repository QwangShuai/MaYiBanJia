package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.AddSpListBean;
import com.mingmen.mayi.mayibanjia.bean.ChangYongBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ChangGouListLevelOneAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.CaiGouDanTianJiaDailog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppManager;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangGouActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    private ChangGouListLevelOneAdapter adapter;
    private List<ChangYongBean> mlist = new ArrayList<>();
    private Context mContext;
    private LinearLayoutManager manager;
    private List<AddSpListBean> list = new ArrayList<>();
    private String id = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_chang_gou;
    }

    @Override
    protected void initData() {
        tvTitle.setText("常购商品");
        tvRight.setText("完成");
        tvRight.setVisibility(View.GONE);
        mContext = ChangGouActivity.this;
        id = getIntent().getStringExtra("id");
        manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        adapter = new ChangGouListLevelOneAdapter(mContext, mlist, ChangGouActivity.this);
        rvList.setLayoutManager(manager);
        rvList.setAdapter(adapter);
        getList();
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
//                addSpList();
                break;
        }
    }

    public void onChangeMap(final ChangYongBean.ListBean bean) {
        Log.e("onChangeMap: ", new Gson().toJson(bean));
            final AddSpListBean addSpListBean = new AddSpListBean();
            addSpListBean.setClassify_id(bean.getClassify_id());
            addSpListBean.setPack_standard_id(bean.getPack_standard_id());
            addSpListBean.setSort_id(bean.getSort_id());
        CaiGouDanTianJiaDailog dailog = new CaiGouDanTianJiaDailog();
        dailog.setDate(bean.getClassify_name(),bean.getSpec_name());
        dailog.setCallBack(new CaiGouDanTianJiaDailog.CallBack() {
            @Override
            public void confirm(String count, String tsyq) {
                addSpListBean.setClassify_id(bean.getClassify_id());
                addSpListBean.setCount(count);
                addSpListBean.setSpecial_commodity(tsyq);
                addSpListBean.setPack_standard_id(bean.getPack_standard_id());
                addSpListBean.setSort_id(bean.getSort_id());
                list.clear();
                list.add(addSpListBean);
                addSpList();
            }
        });
        dailog.show(getSupportFragmentManager());

    }

    public void getList() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getChanggouList(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<List<ChangYongBean>>() {
                    @Override
                    public void onNext(List<ChangYongBean> list) {
                        int mysize = list == null ? 0 : list.size();
                        mlist.clear();
                        if (mysize != 0) {
                            mlist.addAll(list);
                        } else {
                            ToastUtil.showToastLong("请先去添加常用商品");
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    public void addSpList() {
            String sort_id = new Gson().toJson(list);
            HttpManager.getInstance()
                    .with(mContext)
                    .setObservable(RetrofitManager.getService()
                            .addSpList(PreferenceUtils.getString(MyApplication.mContext, "token", ""), sort_id,getIntent().getStringExtra("name"),id))
                    .setDataListener(new HttpDataListener<String>() {
                        @Override
                        public void onNext(String data) {
                            id = data;
                            ToastUtil.showToastLong("添加成功");
                            myBack();
                        }
                    });
    }

    private void myBack(){
        Intent it = new Intent();
        it.putExtra("id",id);
        setResult(2,it);
        finish();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        myBack();
    }
}
