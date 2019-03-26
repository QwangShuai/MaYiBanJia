package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.GHOrderBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.ghdingdan.GHDOrderActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.ghdingdan.GHShangPinAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppManager;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GHDXiangQingActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_dianpu)
    TextView tvDianpu;
    @BindView(R.id.tv_dianpu_phone)
    TextView tvDianpuPhone;
    @BindView(R.id.iv_dianpu_dianhua)
    ImageView ivDianpuDianhua;
    @BindView(R.id.ll_dianpu_rongqi)
    LinearLayout llDianpuRongqi;
    @BindView(R.id.tv_peisongyuan)
    TextView tvPeisongyuan;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.iv_dianhua)
    ImageView ivDianhua;
    @BindView(R.id.ll_rongqi)
    LinearLayout llRongqi;
    @BindView(R.id.tv_chepaihao)
    TextView tvChepaihao;
    @BindView(R.id.tv_fahuoshijian)
    TextView tvFahuoshijian;
    @BindView(R.id.rl_rongqi)
    RelativeLayout rlRongqi;
    @BindView(R.id.tv_shangpin)
    TextView tvShangpin;
    @BindView(R.id.btn_more)
    Button btnMore;
    @BindView(R.id.rv_shangpin)
    RecyclerView rvShangpin;
    @BindView(R.id.tuikuan)
    TextView tuikuan;
    @BindView(R.id.tv_tuikuanjine)
    TextView tvTuikuanjine;
    @BindView(R.id.rl_tuikuan)
    RelativeLayout rlTuikuan;
    @BindView(R.id.tv_fuJiaFei)
    TextView tvFuJiaFei;
    @BindView(R.id.tv_fuJiaFeiMoney)
    TextView tvFuJiaFeiMoney;
    @BindView(R.id.rl_fuJiaMoney)
    RelativeLayout rlFuJiaMoney;
    @BindView(R.id.heji)
    TextView heji;
    @BindView(R.id.tv_zongjia)
    TextView tvZongjia;
    @BindView(R.id.tv_order_number)
    TextView tvOrderNumber;
    @BindView(R.id.tv_order_time)
    TextView tvOrderTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.fengexian)
    View fengexian;

    private Context mContext;
    private List<GHOrderBean.ZilistBean> list = new ArrayList<GHOrderBean.ZilistBean>();
    private GHOrderBean bean;
    private GHShangPinAdapter adapter;
    private String orderid;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ghdxiang_qing;
    }

    @Override
    protected void initData() {
        mContext = this;
        tvTitle.setText("订单详情");
        orderid = getIntent().getStringExtra("orderID");
        bean = new GHOrderBean();
        adapter = new GHShangPinAdapter(mContext, list);
        final LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvShangpin.setLayoutManager(manager);
        rvShangpin.setAdapter(adapter);
        getData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.iv_dianpu_dianhua, R.id.iv_dianhua,R.id.btn_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                AppManager.getAppManager().finishActivity();
                break;
            case R.id.tv_right:
                break;
            case R.id.iv_dianpu_dianhua:
                if(StringUtil.isValid(bean.getTelephone())){
                    CallPhone(bean.getTelephone());
                }
                break;
            case R.id.iv_dianhua:
                if(StringUtil.isValid(bean.getDriver_phone())){
                    CallPhone(bean.getDriver_phone());
                }
                break;
            case R.id.btn_more:
                if (btnMore.getText().equals("展开")) {
                    list.addAll(bean.getZilist());
                    adapter.notifyDataSetChanged();
                    btnMore.setText("收起");
                } else {
                    list.clear();
                    adapter.notifyDataSetChanged();
                    btnMore.setText("展开");
                }
                break;
        }
    }

    private void initView(){
        if(StringUtil.isValid(bean.getRefund())&&!bean.getRefund().equals("0")){
            rlTuikuan.setVisibility(View.VISIBLE);
            tvTuikuanjine.setText(bean.getRefund());
        } else {
            rlTuikuan.setVisibility(View.GONE);
        }
        if(StringUtil.isValid(bean.getAppend_money())&&!bean.getAppend_money().equals("0")){
            rlFuJiaMoney.setVisibility(View.VISIBLE);
            tvFuJiaFeiMoney.setText(bean.getAppend_money());
        } else {
            rlFuJiaMoney.setVisibility(View.GONE);
        }

            tvPeisongyuan.setText("配送员:" + bean.getDriver_name());
            tvPhone.setText(String.valueOf(bean.getDriver_phone()));
            tvChepaihao.setText(String.valueOf(bean.getPlate_number()));
            tvFahuoshijian.setText("发货时间:" + String.valueOf(bean.getWl_sweep_time()));
            tvEndTime.setText(bean.getMj_sweep_time());
        tvDianpu.setText(bean.getCompany_name() + ":");
        tvDianpuPhone.setText(bean.getTelephone());
        tvShangpin.setText(bean.getCountname());
        tvZongjia.setText("￥:" + bean.getTotal_price());
        tvOrderNumber.setText("订单编号:" + bean.getGy_order_number());
        tvOrderTime.setText("下单时间:" + bean.getCreate_time());
    }

    //数据
    private void getData() {

        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getGHOrderList(PreferenceUtils.getString(MyApplication.mContext, "token", ""),orderid, "", 1))
                .setDataListener(new HttpDataListener<List<GHOrderBean>>() {
                    @Override
                    public void onNext(List<GHOrderBean> data) {
                        bean =  data.get(0);
                        initView();
                    }
                });
    }

    public void CallPhone(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        startActivity(intent);
    }
}
