package com.mingmen.mayi.mayibanjia.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CbkListBean;
import com.mingmen.mayi.mayibanjia.bean.CbkXiangqingBean;
import com.mingmen.mayi.mayibanjia.bean.SearchCbkBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.AddCbkActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.CbkTouliaobiaozhunAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.CbkXiangqingListAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.GlideUtils;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CbkXiangqingActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.iv_photo)
    ImageView ivPhoto;
    @BindView(R.id.ll_add)
    LinearLayout llAdd;
    @BindView(R.id.tv_caipin)
    TextView tvCaipin;
    @BindView(R.id.iv_bianji)
    ImageView ivBianji;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rv_zhuliao)
    RecyclerView rvZhuliao;
    @BindView(R.id.rv_fuliao)
    RecyclerView rvFuliao;
    @BindView(R.id.rv_tiaoliao)
    RecyclerView rvTiaoliao;
    @BindView(R.id.tv_beizhu)
    TextView tvBeizhu;
    @BindView(R.id.tv_chengbenjia)
    TextView tvChengbenjia;
    @BindView(R.id.tv_shoumaijia)
    TextView tvShoumaijia;
    @BindView(R.id.tv_maoli)
    TextView tvMaoli;

    private Context mContext;
    private String id;

    private CbkXiangqingListAdapter zhuadapter;
    private CbkXiangqingListAdapter fuadapter;
    private CbkXiangqingListAdapter tiaoadapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_cbk_xiangqing;
    }

    @Override
    protected void initData() {
        mContext = CbkXiangqingActivity.this;
        id = getIntent().getStringExtra("id");
        getData();
    }

    private void initView(CbkXiangqingBean bean){
        tvName.setText(bean.getFood_name());
        tvBeizhu.setText(bean.getHost_remarke());
        tvChengbenjia.setText(bean.getCosting());
        tvShoumaijia.setText(bean.getSale_price());
        tvCaipin.setText(bean.getFood_name());
        tvMaoli.setText(bean.getGross_profit());
        GlideUtils.cachePhotoRound(mContext,ivPhoto,bean.getFood_photo(),5);

        zhuadapter = new CbkXiangqingListAdapter(mContext,bean.getZhulist());
        rvZhuliao.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvZhuliao.setAdapter(zhuadapter);
        fuadapter = new CbkXiangqingListAdapter(mContext,bean.getFulist());
        rvFuliao.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvFuliao.setAdapter(fuadapter);
        tiaoadapter = new CbkXiangqingListAdapter(mContext,bean.getPeilist());
        rvTiaoliao.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvTiaoliao.setAdapter(tiaoadapter);
    }

    private void getData() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getCbkXiangqing(PreferenceUtils.getString(MyApplication.mContext, "token", ""), id))
                .setDataListener(new HttpDataListener<CbkXiangqingBean>() {
                    @Override
                    public void onNext(CbkXiangqingBean data) {
                        initView(data);
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.ll_add, R.id.iv_bianji})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_add:
                break;
            case R.id.iv_bianji:
                Intent it = new Intent(mContext, AddCbkActivity.class);
                it.putExtra("id",id);
                startActivity(it);
                break;
        }
    }

    @Override
    protected void onResume() {
        getData();
        super.onResume();
    }
}
