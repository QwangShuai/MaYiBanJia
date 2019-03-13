package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean;
import com.mingmen.mayi.mayibanjia.bean.FbspGuiGeBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.XuanZeGuiGeAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XuanZeGuiGeActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    private Context mContext;
    private List<FbspGuiGeBean> mlist = new ArrayList<>();
    private XuanZeGuiGeAdapter adapter;
    private String name = "";
    private String id = "";
    private FbspGuiGeBean bean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_xuan_ze_gui_ge;
    }

    @Override
    protected void initData() {
        mContext =  XuanZeGuiGeActivity.this;
        tvTitle.setText("选择规格");
//        name = getIntent().getStringExtra("name");
        id = getIntent().getStringExtra("id");
        bean = (FbspGuiGeBean) getIntent().getSerializableExtra("bean");
        adapter =  new XuanZeGuiGeAdapter(mContext,mlist);
        adapter.setCallBack(new XuanZeGuiGeAdapter.CallBack() {
            @Override
            public void isClick(View v, int pos) {
                setResultIntent(mlist.get(pos));
            }
        });
        adapter.setXzBean(bean);
        rvList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvList.setAdapter(adapter);
        getlist();
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
                break;
        }
    }

    public  void getlist() {
        Log.e("getlist: ",name+"---"+id );
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getXuanZeGuige(PreferenceUtils.getString(MyApplication.mContext, "token",""),name,id))
                .setDataListener(new HttpDataListener<List<FbspGuiGeBean>>() {
                    @Override
                    public void onNext(List<FbspGuiGeBean> list) {
                        mlist.clear();
                        mlist.addAll(list);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    public void setResultIntent(FbspGuiGeBean bean){
        Intent it = new Intent();
        it.putExtra("bean",bean);
        setResult(6,it);
        finish();
    }
}
