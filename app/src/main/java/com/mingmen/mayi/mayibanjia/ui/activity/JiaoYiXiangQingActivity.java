package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.JYMXItemBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JiaoYiXiangQingActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_jine)
    TextView tvJine;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_dianpu)
    TextView tvDianpu;
    @BindView(R.id.tv_fukuandianpu)
    TextView tvFukuandianpu;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_order_number)
    TextView tvOrderNumber;
    @BindView(R.id.tv_reasons)
    TextView tvSeasons;
    @BindView(R.id.tv_fangshi)
    TextView tvFangshi;
    @BindView(R.id.ll_fkdp)
    LinearLayout llFkdp;
    @BindView(R.id.ll_skdp)
    LinearLayout llSkdp;
    private Context mContext;
    private String id;
    @Override
    public int getLayoutId() {
        return R.layout.activity_jiao_yi_xiang_qing;
    }

    @Override
    protected void initData() {
        mContext = JiaoYiXiangQingActivity.this;
        tvTitle.setText("交易详情");
        id = getIntent().getStringExtra("id");
        getShowView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
    private void getShowView(){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getJYMXItem(PreferenceUtils.getString(MyApplication.mContext, "token", ""),id))
                .setDataListener(new HttpDataListener<JYMXItemBean>() {
                    @Override
                    public void onNext(JYMXItemBean bean) {
                        tvSeasons.setText(bean.getReturn_remarke());
                        switch (bean.getState()){
                            case "0":
                                tvState.setText("收款");
                                tvJine.setTextColor(mContext.getResources().getColor(R.color.zicolor));
                                tvJine.setText("+"+bean.getCollect_money());
                                break;
                            case "1":
                                tvState.setText("付款");
                                tvJine.setTextColor(mContext.getResources().getColor(R.color.red_ff3300));
                                tvJine.setText("-"+bean.getPay_money());
                                break;
                            case "2":
                                tvState.setText("提现");
                                tvJine.setTextColor(mContext.getResources().getColor(R.color.red_ff3300));
                                tvJine.setText("-"+bean.getPay_money());
                                llFkdp.setVisibility(View.GONE);
                                llSkdp.setVisibility(View.GONE);
                                break;
                            case "3":
                                tvState.setText("退款");
                                tvJine.setTextColor(mContext.getResources().getColor(R.color.zicolor));
                                tvJine.setText("+"+bean.getCollect_money());
                                break;
                            case "4":
                                tvState.setText("提现成功");
                                tvJine.setTextColor(mContext.getResources().getColor(R.color.zicolor));
                                tvJine.setText("+"+bean.getPay_money());
                                llFkdp.setVisibility(View.GONE);
                                llSkdp.setVisibility(View.GONE);
                                break;
                            case "5":
                                tvState.setText("提现失败");
                                tvJine.setTextColor(mContext.getResources().getColor(R.color.red_ff3300));
                                tvJine.setText(bean.getPay_money());
                                llFkdp.setVisibility(View.GONE);
                                llSkdp.setVisibility(View.GONE);
                                tvSeasons.setText(bean.getReasons());
                                break;
                            case "6":
                                tvState.setText("系统扣款");
                                tvJine.setTextColor(mContext.getResources().getColor(R.color.red_ff3300));
                                tvJine.setText("-"+bean.getPay_money());
                                llFkdp.setVisibility(View.GONE);
                                llSkdp.setVisibility(View.GONE);
                                break;
                        }
                        tvFukuandianpu.setText(bean.getCt_company_name());
                        tvDianpu.setText(bean.getGy_company_name());
                        tvTime.setText(bean.getCreate_time());
                        tvNumber.setText(bean.getPay_number());
                        tvOrderNumber.setText(bean.getOrder_id());
                        tvFangshi.setText(bean.getThird_party_payment());
                    }
                });
    }
}
