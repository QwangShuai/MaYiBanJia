package com.mingmen.mayi.mayibanjia.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.ShiChangBean;
import com.mingmen.mayi.mayibanjia.bean.ZouShiTuBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.XuanZeRiQiDialog;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.DateUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.SinglePicker;

/**
 * Created by Administrator on 2018/9/19.
 */

public class TubiaoActivity extends FragmentActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.lineChart)
    LineChart lineChart;
    @BindView(R.id.tv_shichang)
    TextView tvShichang;
    @BindView(R.id.tv_fenleimingcheng)
    TextView tvFenleimingcheng;
    @BindView(R.id.ll_shichang)
    LinearLayout llShichang;
    @BindView(R.id.tv_kaishi)
    TextView tvKaishi;
    @BindView(R.id.ll_kaishi)
    LinearLayout llKaishi;
    @BindView(R.id.tv_jieshu)
    TextView tvJieshu;
    @BindView(R.id.ll_jieshu)
    LinearLayout llJieshu;
    @BindView(R.id.bt_chongzhi)
    Button btChongzhi;
    @BindView(R.id.bt_queding)
    Button btQueding;
    private XAxis xAxis;                //X轴
    private YAxis leftYAxis;            //左侧Y轴
    private YAxis rightYaxis;           //右侧Y轴
    private Legend legend;              //图例
    private Context mContext = TubiaoActivity.this;
    private String classify_id;
    private String mark_id;
    private String startDate;
    private String endDate;
    private String qitianqian;
    private String jintian;
    private List<ZouShiTuBean> list;
    private List<ShiChangBean> shichanglist;
    private String market_name;
    private String old_mark_id;
    private String old_market_name;
    private String old_classify_id;
    private String geshi="yyyy-MM-dd";
    private String old_classify_name;
    private String classify_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tubiao);
        ButterKnife.bind(this);
        initChart(lineChart);
        getShichang();
        initData();
    }

    private void getShichang(){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getShiChangByCity(PreferenceUtils.getString(MyApplication.mContext, "token",""),"230100"))//230100  哈尔滨市编码
                .setDataListener(new HttpDataListener<List<ShiChangBean>>() {
                    @Override
                    public void onNext(List<ShiChangBean> data) {
                        shichanglist = new ArrayList<>();
                        shichanglist.addAll(data);
                    }
                },false);
    }
    //走势图接口
    private void zoushitu() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .zoushitu(PreferenceUtils.getString(MyApplication.mContext, "token", ""), classify_id, mark_id, startDate, endDate))
                .setDataListener(new HttpDataListener<List<ZouShiTuBean>>() {
                    @Override
                    public void onNext(List<ZouShiTuBean> data) {
                        list = new ArrayList<>();
                        list.addAll(data);

                        if (data.size()==0||data==null){
                            ToastUtil.showToast("该商品暂无价格走势");
                            lineChart.setVisibility(View.GONE);
                            return;
                        }else{
                            showLineChart(list);
                            lineChart.setVisibility(View.VISIBLE);
                        }



                    }
                });
    }

    protected void initData() {

        old_mark_id = getIntent().getStringExtra("mark_id");
        old_market_name = getIntent().getStringExtra("market_name");
        old_classify_id = getIntent().getStringExtra("classify_id");
        old_classify_name = getIntent().getStringExtra("classify_name");

        qitianqian = DateUtil.timeDateqt();
        jintian = DateUtil.dateFormat(new Date(), geshi);
        market_name=old_market_name;
        mark_id=old_mark_id;
        classify_id=old_classify_id;
        classify_name = old_classify_name;

        startDate = qitianqian;
        endDate = jintian;

        tvKaishi.setText(startDate);
        tvFenleimingcheng.setText("分类名称："+classify_name);
        tvJieshu.setText(endDate);
        tvShichang.setText(market_name);
        zoushitu();

    }

    /**
     * 展示曲线
     *
     * @param dataList 数据集合
     * @param
     * @param
     */
    public void showLineChart(final List<ZouShiTuBean> dataList ) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            ZouShiTuBean data = dataList.get(i);
            /**
             * 在此可查看 Entry构造方法，可发现 可传入数值 Entry(float x, float y)
             * 也可传入Drawable， Entry(float x, float y, Drawable icon) 可在XY轴交点 设置Drawable图像展示
             */
            Entry entry = new Entry(i, Float.parseFloat(data.getAveragepic()));
            entries.add(entry);
        }
        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(entries, "日期");
        initLineDataSet(lineDataSet, getResources().getColor(R.color.accent), LineDataSet.Mode.LINEAR);
        LineData lineData = new LineData(lineDataSet);
        //设置一页最大显示个数为6，超出部分就滑动
        float ratio = (float) dataList.size()/(float) 7;
        // 显示的时候是按照多大的比率缩放显示,1f表示不放大缩小
        lineChart.zoom(ratio,1f,0,0);
        lineChart.setData(lineData);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String tradeDate = dataList.get((int) value % dataList.size()).getCreate_time();
                return tradeDate;
            }
        });
    }

    /**
     * 曲线初始化设置 一个LineDataSet 代表一条曲线
     *
     * @param lineDataSet 线条
     * @param color       线条颜色
     * @param mode
     */
    private void initLineDataSet(LineDataSet lineDataSet, int color, LineDataSet.Mode mode) {
        lineDataSet.setColor(color);
        lineDataSet.setCircleColor(color);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(3f);
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(10f);
        //设置折线图填充
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFormLineWidth(1f);
        lineDataSet.setFormSize(15.f);
        if (mode == null) {
            //设置曲线展示为圆滑曲线（如果不设置则默认折线）
            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        } else {
            lineDataSet.setMode(mode);
        }
    }

    /**
     * 初始化图表
     */
    private void initChart(LineChart lineChart) {
        /***图表设置***/

        //设置描述文本不显示
        lineChart.getDescription().setEnabled(false);
        //设置是否显示表格背景
        // mLineChart.setDrawGridBackground(true);
        // 设置是否可以触摸
        lineChart.setTouchEnabled(true);
        lineChart.setDragDecelerationFrictionCoef(0.9f);
        //设置是否可以拖拽 mLineChart.setDragEnabled(true);
        // 设置是否可以缩放
        lineChart.setScaleEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setHighlightPerDragEnabled(true);
        lineChart.setPinchZoom(true);
        //设置背景颜色
        // lineChart.setBackgroundColor(ColorAndImgUtils.CHART_BACKGROUND_COLOR);
        // 设置从X轴出来的动画时间 //
        //lineChart.animateX(1500);
        // 设置XY轴动画
        lineChart.animateY(2500);
        lineChart.animateX(1500);

        /***XY轴的设置***/
        xAxis = lineChart.getXAxis();
        leftYAxis = lineChart.getAxisLeft();
        rightYaxis = lineChart.getAxisRight();
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setTextSize(9f);
        xAxis.setAvoidFirstLastClipping(true);
        //保证Y轴从0开始，不然会上移一点
        leftYAxis.setAxisMinimum(0f);
        rightYaxis.setAxisMinimum(0f);

        /***折线图例 标签 设置***/
        legend = lineChart.getLegend();
        //设置显示类型，LINE CIRCLE SQUARE EMPTY 等等 多种方式，查看LegendForm 即可
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(12f);
        //显示位置 左下方
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);
    }
    //
    @OnClick({R.id.iv_back, R.id.ll_shichang, R.id.ll_kaishi, R.id.ll_jieshu,R.id.bt_chongzhi,R.id.bt_queding})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_shichang:
                if (shichanglist!=null) {
                    final SinglePicker<ShiChangBean> picker =new SinglePicker<ShiChangBean>(TubiaoActivity.this, shichanglist);
                    picker.setCanceledOnTouchOutside(false);
                    picker.setSelectedIndex(1);
                    picker.setCycleDisable(false);
                    picker.setOnItemPickListener(new SinglePicker.OnItemPickListener<ShiChangBean>() {
                        @Override
                        public void onItemPicked(int index, ShiChangBean item) {
                            tvShichang.setText(item.getMarket_name());
                            mark_id = item.getMark_id();
                            picker.dismiss();
                        }
                    });
                    picker.show();
                }else{
                    ToastUtil.showToast("请稍等正在获取数据");
                    getShichang();
                }
                break;
            case R.id.ll_kaishi:
                new XuanZeRiQiDialog(mContext, mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()))
                        .setmCallBack(new XuanZeRiQiDialog.CallBack() {
                            @Override
                            public void getDate(String date) {
                                tvKaishi.setText(date);
                                startDate=date;
                            }
                        }).show();
                break;
            case R.id.ll_jieshu:
                new XuanZeRiQiDialog(mContext, mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()))
                        .setmCallBack(new XuanZeRiQiDialog.CallBack() {
                            @Override
                            public void getDate(String date) {
                                tvJieshu.setText(date);
                                endDate=date;
                            }
                        }).show();
                break;
            case R.id.bt_queding:
                int leixing = DateUtil.compare_date(DateUtil.StringToDate(startDate, geshi), DateUtil.StringToDate(endDate, geshi));
                if (leixing==1){
                    ToastUtil.showToast("开始时间不能大于结束时间");
                    return;
                }
                if (leixing==2){
                    ToastUtil.showToast("时间间隔不得超过30天");
                    return;
                }
                zoushitu();

                break;
            case R.id.bt_chongzhi:
                startDate=qitianqian;
                endDate=jintian;
                market_name=old_market_name;
                mark_id=old_mark_id;
                classify_id=old_classify_id;
                classify_name=old_classify_name;
                tvKaishi.setText(startDate);
                tvJieshu.setText(endDate);
                tvShichang.setText(market_name);
                break;
        }
    }

}
