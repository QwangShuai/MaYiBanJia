package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
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

    private Map<String, ChangYongBean.ListBean> map = new HashMap<>();
    private ChangGouListLevelOneAdapter adapter;
    private List<ChangYongBean> mlist = new ArrayList<>();
    private Context mContext;
    private GridLayoutManager manager;
    private List<AddSpListBean> list = new ArrayList<>();
    private String id = "";
    private int count = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_chang_gou;
    }

    @Override
    protected void initData() {
        tvTitle.setText("常购商品");
        tvRight.setText("完成");
        mContext = ChangGouActivity.this;
        manager = new GridLayoutManager(mContext, 3);
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
                finish();
                break;
            case R.id.tv_right:
                addSpList();
                break;
        }
    }

    public void onChangeMap(ChangYongBean.ListBean bean, boolean b) {
        count = 0;
        list.clear();
        if (b) {
            map.put(bean.getSort_id(), bean);
        } else {
            map.remove(bean.getSort_id());
        }
        for (ChangYongBean.ListBean mybean : map.values()) {
            AddSpListBean addSpListBean = new AddSpListBean();
            addSpListBean.setClassify_id(mybean.getClassify_id());
            addSpListBean.setPack_standard_id(mybean.getPack_standard_id());
            addSpListBean.setSort_id(mybean.getSort_id());
            list.add(addSpListBean);
            count++;
        }
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
                        if (mysize != 0) {
                            mlist.clear();
                            mlist.addAll(list);
                        } else {
                            ToastUtil.showToastLong("请先去添加常用商品");
                        }

                    }
                });
    }

    public void addSpList() {
        if (count != 0) {
            String sort_id = new Gson().toJson(list);
            HttpManager.getInstance()
                    .with(mContext)
                    .setObservable(RetrofitManager.getService()
                            .addSpList(PreferenceUtils.getString(MyApplication.mContext, "token", ""), sort_id,getIntent().getStringExtra("name")))
                    .setDataListener(new HttpDataListener<String>() {
                        @Override
                        public void onNext(String data) {
                            id = data;
                            ToastUtil.showToastLong("添加成功");
                        }
                    });
        } else {
            ToastUtil.showToastLong("请至少选择一项商品");
        }

    }
}
