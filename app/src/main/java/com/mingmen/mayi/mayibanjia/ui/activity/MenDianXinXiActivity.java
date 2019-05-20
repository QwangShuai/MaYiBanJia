package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.GetMenDianBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.view.CircleImageView;
import com.mingmen.mayi.mayibanjia.utils.GlideUtils;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenDianXinXiActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_touxiang)
    CircleImageView ivTouxiang;
    @BindView(R.id.tv_zizhi_state)
    TextView tvZizhiState;
    @BindView(R.id.ll_zizhi)
    LinearLayout llZizhi;
    @BindView(R.id.tv_yinhangka_state)
    TextView tvYinhangkaState;
    @BindView(R.id.ll_yinhangka)
    LinearLayout llYinhangka;
    @BindView(R.id.tv_dianpu)
    TextView tvDianpu;
    @BindView(R.id.tv_dizhi)
    TextView tvDizhi;
    @BindView(R.id.tv_shichang)
    TextView tvShichang;

    private Context mContext;
    private String sh_state="待审核";

    @Override
    public int getLayoutId() {
        return R.layout.activity_men_dian_xin_xi;
    }

    @Override
    protected void initData() {
        tvTitle.setText("门店信息");
        mContext = MenDianXinXiActivity.this;
        getMenDianShow();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.ll_zizhi, R.id.ll_yinhangka})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                break;
            case R.id.ll_zizhi:
                Intent it = new Intent(mContext, ZiZhiRenZhengActivity.class);
                it.putExtra("id", "");
                it.putExtra("state",sh_state);
                it.putExtra("yemian","1");
                startActivity(it);
                break;
            case R.id.ll_yinhangka:
                break;
        }
    }

    private void getMenDianShow() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .mendianShow(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<GetMenDianBean>() {
                    @Override
                    public void onNext(GetMenDianBean bean) {
                        GlideUtils.cachePhoto(mContext,ivTouxiang,bean.getPhoto());
                        tvDianpu.setText(bean.getCompany_name());
                        tvShichang.setText(bean.getMarket_name());
                        tvDizhi.setText(bean.getDeliver_address());
                        tvZizhiState.setText(bean.getApproval_state());
                        sh_state = bean.getApproval_state();
                    }
                });
    }
}
