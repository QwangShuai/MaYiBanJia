package com.mingmen.mayi.mayibanjia.ui.activity;

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
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
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
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.tv_dizhi)
    TextView tvDizhi;
    @BindView(R.id.ll_dizhi)
    LinearLayout llDizhi;
    @BindView(R.id.ll_pwd)
    LinearLayout llPwd;
    @BindView(R.id.btn_exit)
    Button btnExit;

    private boolean isShow;
    private boolean isSelect;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ye_wu_yuan;
    }

    @Override
    protected void initData() {
        ivBack.setVisibility(View.GONE);
        tvTitle.setText("业务员");
        showPieChat();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void showPieChat() {
        final List<PieEntry> strings = new ArrayList<>();
        strings.add(new PieEntry(30f, "社区市场"));
        strings.add(new PieEntry(50f, "供货端"));
        strings.add(new PieEntry(20f, "餐厅端"));
        PieDataSet dataSet = new PieDataSet(strings, "");
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(getResources().getColor(R.color.zangqing));
        colors.add(getResources().getColor(R.color.yellow_fbb03b));
        colors.add(getResources().getColor(R.color.blue_3794e5));
        pcBing.setUsePercentValues(true);
        pcBing.setDrawEntryLabels(false);
        pcBing.getDescription().setEnabled(false);
        dataSet.setColors(colors);
        pcBing.setDrawCenterText(true);
        pcBing.setCenterText("3000\n已录入企业");
        pcBing.setRotationEnabled(false);
        PieData pieData = new PieData(dataSet);
        pieData.setDrawValues(true);
        pieData.setValueTextColor(getResources().getColor(R.color.white));
        pcBing.setHoleRadius(60f);
        pcBing.setData(pieData);
        pcBing.invalidate();
        pcBing.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if (e == null)
                    return;
                for (PieEntry value : strings) {
                    if (value.getValue() == e.getY()) {
                        ToastUtil.showToastLong("当前点击了" + value.getLabel());
                    }
                }
            }

            @Override
            public void onNothingSelected() {
                ToastUtil.showToastLong("点击了非标签区域");
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.btn_quanbu, R.id.btn_wode, R.id.ll_qygl,
            R.id.ll_dcldd, R.id.ll_ywy, R.id.ll_phone, R.id.ll_dizhi,
            R.id.ll_pwd, R.id.btn_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.tv_right:
                break;
            case R.id.btn_quanbu:
                if (isSelect) {
                    isSelect = false;
                    btnQuanbu.setTextColor(getResources().getColor(R.color.zangqing));
                    btnWode.setTextColor(getResources().getColor(R.color.hintcolor));
                    btnQuanbu.setBackground(getResources().getDrawable(R.drawable.fillet_hollow_zangqing_3));
                    btnWode.setBackground(getResources().getDrawable(R.drawable.fillet_hollow_999999_3));
                }
                break;
            case R.id.btn_wode:
                if (!isSelect) {
                    isSelect = true;
                    btnQuanbu.setTextColor(getResources().getColor(R.color.hintcolor));
                    btnWode.setTextColor(getResources().getColor(R.color.zangqing));
                    btnQuanbu.setBackground(getResources().getDrawable(R.drawable.fillet_hollow_999999_3));
                    btnWode.setBackground(getResources().getDrawable(R.drawable.fillet_hollow_zangqing_3));
                }
                break;
            case R.id.ll_qygl:
                break;
            case R.id.ll_dcldd:
                break;
            case R.id.ll_ywy:
                if (isShow) {
                    isShow = false;
                    llPhone.setVisibility(View.VISIBLE);
                    llDizhi.setVisibility(View.VISIBLE);
                } else {
                    isShow = true;
                    llPhone.setVisibility(View.GONE);
                    llDizhi.setVisibility(View.GONE);
                }
                break;
            case R.id.ll_phone:
                break;
            case R.id.ll_dizhi:
                break;
            case R.id.ll_pwd:
                break;
            case R.id.btn_exit:
                break;
        }
    }
}
