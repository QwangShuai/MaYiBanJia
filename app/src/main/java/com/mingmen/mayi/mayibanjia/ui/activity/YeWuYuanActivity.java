package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.YwyBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YeWuYuanActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.btn_quanbu)
    Button btnQuanbu;
    @BindView(R.id.btn_wode)
    Button btnWode;
    @BindView(R.id.pc_bing)
    PieChart pcBing;
    @BindView(R.id.tv_yizhuce)
    TextView tvYizhuce;
    @BindView(R.id.tv_weizhuce)
    TextView tvWeizhuce;
    @BindView(R.id.ll_qygl)
    LinearLayout llQygl;
    @BindView(R.id.ll_dcldd)
    LinearLayout llDcldd;
    @BindView(R.id.tv_ywy)
    TextView tvYwy;
    @BindView(R.id.ll_ywy)
    LinearLayout llYwy;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_order_number)
    TextView tvOrderNumber;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.tv_dizhi)
    TextView tvDizhi;
    @BindView(R.id.tv_qiye)
    TextView tvQiye;
    @BindView(R.id.ll_dizhi)
    LinearLayout llDizhi;
    @BindView(R.id.ll_pwd)
    LinearLayout llPwd;
    @BindView(R.id.ll_show)
    LinearLayout llShow;
    @BindView(R.id.btn_exit)
    Button btnExit;
    @BindView(R.id.tv_yjdd_no)
    TextView tvYjddNo;
    @BindView(R.id.tv_dqh_no)
    TextView tvDqhNo;
    @BindView(R.id.tv_dsh_no)
    TextView tvDshNo;
    @BindView(R.id.tv_zcldd_no)
    TextView tvZclddNo;
    @BindView(R.id.tv_ywcsh_no)
    TextView tvYwcshNo;

    private boolean isShow;
    private String isSelect = "1";
    private Context mContext;
    private ConfirmDialog confirmDialog;
    private String type = "全部企业";

    @Override
    public int getLayoutId() {
        return R.layout.activity_ye_wu_yuan;
    }

    @Override
    protected void initData() {
        ivBack.setVisibility(View.GONE);
        mContext = this;
        tvTitle.setText("业务员");
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        showYwy();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void showPieChat(final YwyBean bean) {
        final List<PieEntry> strings = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<Integer>();


        PieDataSet dataSet = new PieDataSet(strings, "");
        if (bean.getAll_she() != 0) {
            strings.add(new PieEntry(Float.valueOf(bean.getAll_she()), "社区市场"));
            colors.add(getResources().getColor(R.color.zangqing));
        }
        if (bean.getAll_gy() != 0) {
            strings.add(new PieEntry(Float.valueOf(bean.getAll_gy()), "供货端"));
            colors.add(getResources().getColor(R.color.yellow_fbb03b));
        }
        if (bean.getAll_ct() != 0) {
            strings.add(new PieEntry(Float.valueOf(bean.getAll_ct()), "餐厅端"));
            colors.add(getResources().getColor(R.color.blue_3794e5));
        }

        pcBing.setUsePercentValues(false);
        pcBing.setDrawEntryLabels(false);
        pcBing.getDescription().setEnabled(false);
        dataSet.setColors(colors);
        pcBing.setDrawCenterText(true);
        pcBing.setHighlightPerTapEnabled(true);
        pcBing.setCenterText(bean.getAll_count() + "\n已录入企业");
        pcBing.setRotationEnabled(false);
        PieData pieData = new PieData(dataSet);
        pieData.setDrawValues(true);
        pieData.setValueTextColor(getResources().getColor(R.color.white));
        pieData.setValueTextSize(15f);//外围数据字体大小
        pcBing.setHoleRadius(60f);
        pcBing.setData(pieData);
        pcBing.invalidate();
        //pcBing.setEntryLabelTextSize(120f);

        pcBing.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if (e == null)
                    return;
                for (PieEntry value : strings) {
                    if (value.getValue() == e.getY()) {
                        switch (value.getLabel()) {
                            case "社区市场":
                                tvYizhuce.setText("已注册:" + bean.getRegistered_she() + "");
                                tvWeizhuce.setText("未注册:" + bean.getNo_she() + "");
                                type = "社区市场";
                                break;
                            case "供货端":
                                tvYizhuce.setText("已注册:" + bean.getRegistered_gy() + "");
                                tvWeizhuce.setText("未注册:" + bean.getNo_gy() + "");
                                type = "供货端";
                                break;
                            case "餐厅端":
                                tvYizhuce.setText("已注册:" + bean.getRegistered_ct() + "");
                                tvWeizhuce.setText("未注册:" + bean.getNo_ct() + "");
                                type = "餐厅端";
                                break;
                        }
                        ToastUtil.showToastLong("当前点击了" + value.getLabel());
                        tvQiye.setText(type);
                    }
                }
            }

            @Override
            public void onNothingSelected() {
                ToastUtil.showToastLong("点击了非标签区域");
                tvYizhuce.setText("已注册:" + bean.getRegistered_all() + "");
                tvWeizhuce.setText("未注册:" + bean.getNo_all() + "");
                type = "全部企业";
                tvQiye.setText(type);
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.btn_quanbu, R.id.btn_wode, R.id.ll_qygl,
            R.id.ll_dcldd, R.id.ll_ywy, R.id.ll_phone, R.id.ll_dizhi,
            R.id.ll_pwd, R.id.btn_exit, R.id.ll_zcldd, R.id.ll_yjdd, R.id.ll_dqh,
            R.id.ll_dsh, R.id.ll_ywcsh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.tv_right:
                break;
            case R.id.btn_quanbu:
                if (isSelect.equals("2")) {
                    isSelect = "1";
                    btnQuanbu.setTextColor(getResources().getColor(R.color.zangqing));
                    btnWode.setTextColor(getResources().getColor(R.color.hintcolor));
                    btnQuanbu.setBackground(getResources().getDrawable(R.drawable.fillet_hollow_zangqing_3));
                    btnWode.setBackground(getResources().getDrawable(R.drawable.fillet_hollow_999999_3));
                    showYwy();
                }
                break;
            case R.id.btn_wode:
                if (isSelect.equals("1")) {
                    isSelect = "2";
                    btnQuanbu.setTextColor(getResources().getColor(R.color.hintcolor));
                    btnWode.setTextColor(getResources().getColor(R.color.zangqing));
                    btnQuanbu.setBackground(getResources().getDrawable(R.drawable.fillet_hollow_999999_3));
                    btnWode.setBackground(getResources().getDrawable(R.drawable.fillet_hollow_zangqing_3));
                    showYwy();
                }
                break;
            case R.id.ll_qygl:
                Intent intent = new Intent(mContext, YeWuYuanMainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.ll_dcldd:
                JumpScwl("1401","2");
                break;
            case R.id.ll_ywy:
                if (isShow) {
                    isShow = false;
                    llShow.setVisibility(View.GONE);
                } else {
                    isShow = true;
                    llShow.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.ll_phone:
                break;
            case R.id.ll_dizhi:
                break;
            case R.id.ll_pwd:
                Intent it = new Intent(mContext, WoDeZhangHuActivity.class);
                startActivity(it);
                break;
            case R.id.btn_exit:
                confirmDialog.showDialog("是否确定退出当前账号");
                confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitLogin();
                    }
                });
                confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmDialog.dismiss();
                    }
                });
                break;
            case R.id.ll_zcldd:
                JumpScwl("1407","0");
                break;
            case R.id.ll_yjdd:
                JumpScwl("1406","");
                break;
            case R.id.ll_dqh:
                JumpScwl("1401","");
                break;
            case R.id.ll_dsh:
                JumpScwl("1402","");
                break;
            case R.id.ll_ywcsh:
                JumpScwl("1403","");
                break;
        }
    }

    private void showYwy() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getYwyCount(PreferenceUtils.getString(MyApplication.mContext, "token", ""), isSelect))
                .setDataListener(new HttpDataListener<YwyBean>() {
                    @Override
                    public void onNext(YwyBean bean) {
                        pcBing.clear();
                        pcBing.notifyDataSetChanged();
                        pcBing.invalidate();
                        tvYwy.setText(bean.getName());
                        tvPhone.setText(bean.getTelephone());
                        tvDizhi.setText(bean.getSpecific_address());
                        tvOrderNumber.setVisibility(bean.getOrder_count() == 0 ? View.GONE : View.VISIBLE);
                        tvOrderNumber.setText(bean.getOrder_count() + "");
                        type = "全部企业";
                        tvYizhuce.setText("已注册:" + bean.getRegistered_all());
                        tvWeizhuce.setText("未注册:" + bean.getNo_all());
                        tvQiye.setText(type);

                        tvYjddNo.setVisibility(bean.getYjcount() == 0 ? View.GONE : View.VISIBLE);
                        tvYjddNo.setText(bean.getYjcount() + "");
                        tvDqhNo.setVisibility(bean.getWaitQh_count() == 0 ? View.GONE : View.VISIBLE);
                        tvDqhNo.setText(bean.getWaitQh_count() + "");
                        tvDshNo.setVisibility(bean.getWaitSH_count() == 0 ? View.GONE : View.VISIBLE);
                        tvDshNo.setText(bean.getWaitSH_count() + "");
                        tvYwcshNo.setVisibility(bean.getWaitWC_count() == 0 ? View.GONE : View.VISIBLE);
                        tvYwcshNo.setText(bean.getWaitWC_count() + "");
                        tvZclddNo.setVisibility(bean.getZcl_count() == 0 ? View.GONE : View.VISIBLE);
                        tvZclddNo.setText(bean.getZcl_count() + "");

                        showPieChat(bean);
                    }
                }, false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showYwy();
    }

    private void exitLogin() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .exitLogin(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        confirmDialog.dismiss();
                        goLogin(mContext, "login");
                    }
                });
    }
    private void JumpScwl(String state,String teshu){
        Intent it = new Intent(mContext,ShichangWuliuActivity.class);
        it.putExtra("type",state);
        it.putExtra("isShichang",teshu);
        startActivity(it);
    }
}
