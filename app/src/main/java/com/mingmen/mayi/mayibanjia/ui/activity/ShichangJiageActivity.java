package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.ShiChangSouSuoShangPinBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShiChangSouSuoShangPinListAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShichangJiageActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rv_shoucang)
    SwipeMenuRecyclerView rvShoucang;
//    @BindView(R.id.srl_shuaxin)
//    SwipeRefreshLayout srlShuaxin;

    private Context mContext;
    private ArrayList<ShiChangSouSuoShangPinBean> shichanglist = new ArrayList<>();
    private ShiChangSouSuoShangPinListAdapter shichangadapter;
    private String sanjipinleiid = "",sanjipinleiname = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_shichang_jiage;
    }

    @Override
    protected void initData() {
        mContext = ShichangJiageActivity.this;
        tvTitle.setText("各市场价");
        sanjipinleiid = getIntent().getStringExtra("id");
        sanjipinleiname = getIntent().getStringExtra("name");
        sousuoshichang();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_title, R.id.iv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_title:
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                break;
        }
    }


    //市场搜索
    private void sousuoshichang() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .shichangsousuoshangpin(PreferenceUtils.getString(MyApplication.mContext, "token", ""), sanjipinleiname))
                .setDataListener(new HttpDataListener<List<ShiChangSouSuoShangPinBean>>() {
                    @Override
                    public void onNext(List<ShiChangSouSuoShangPinBean> list) {
                        int mysize = list == null ? 0 : list.size();
                        if (mysize != 0) {
                            rvShoucang.setVisibility(View.VISIBLE);
                        } else {
                            ToastUtil.showToastLong("该商品暂无市场报价");
                        }
                        shichanglist.clear();
                        shichanglist.addAll(list);
                        shichangadapter = new ShiChangSouSuoShangPinListAdapter(mContext, shichanglist);
                        rvShoucang.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                        rvShoucang.setAdapter(shichangadapter);
                        shichangadapter.setOnItemClickListener(new ShiChangSouSuoShangPinListAdapter.OnItemClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                switch (view.getId()) {
                                    case R.id.tv_chakan://点击查看
                                        Intent intent = new Intent(mContext, ShiChangSouSuoShangPinActivity.class);
                                        intent.putExtra("type_tree_id", sanjipinleiid);
                                        intent.putExtra("type_tree_name", sanjipinleiname);
                                        intent.putExtra("son_number", shichanglist.get(position).getSon_number());
                                        intent.putExtras(intent);
                                        startActivity(intent);
                                        break;
                                }

                            }
                        });
                    }
                });
    }
}
