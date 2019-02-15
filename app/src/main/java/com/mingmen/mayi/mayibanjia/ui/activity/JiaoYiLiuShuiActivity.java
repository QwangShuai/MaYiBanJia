package com.mingmen.mayi.mayibanjia.ui.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.JiaoYiMingXiBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.JiaoYiLiuShuiAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.jiaoyiliushui.JiaoYiMingXiAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DateTimePicker;

public class JiaoYiLiuShuiActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_riqi)
    TextView tvRiqi;
    @BindView(R.id.tv_zhichu)
    TextView tvZhichu;
    @BindView(R.id.tv_shouru)
    TextView tvShouru;
    @BindView(R.id.rv_dingdan)
    SwipeMenuRecyclerView rvDingdan;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;
    private int ye = 1;
    private Context mContext;
    private List<JiaoYiMingXiBean.LieBiaoBean> mlist = new ArrayList<>();
    private JiaoYiLiuShuiAdapter adapter;
    private DatePickerDialog date;
    private int mYear;
    private int mMonth;
    private int mDay;
    private Calendar ca;
    private String mMonths;

    @Override
    public int getLayoutId() {
        return R.layout.activity_jiao_yi_liu_shui;
    }

    @Override
    protected void initData() {
        tvTitle.setText("交易明细");
        mContext = JiaoYiLiuShuiActivity.this;
        ca = Calendar.getInstance();
        ca.setTime(Calendar.getInstance().getTime());
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mMonths = String.valueOf(mMonth+1);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        initview();
        getData(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.tv_riqi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                break;
            case R.id.tv_riqi:

                date = new DatePickerDialog(mContext, DatePickerDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mYear = year;
                        mMonth = month;
                        mMonths = String.valueOf(month+1);
                        mDay = dayOfMonth;
                        tvRiqi.setText(mYear + "-" + mMonths);
                        ye = 1;
                        getData(true);
                    }
                }, mYear, mMonth, mDay);
                date.show();
                //隐藏天数
                if (date != null) {
                    int SDKVersion = getSDKVersionNumber();
                    if (SDKVersion < 11) {
                        ((ViewGroup) date.getDatePicker().getChildAt(0)).getChildAt(2).setVisibility(View.GONE);
                    } else if (SDKVersion > 14) {
                        ((ViewGroup) ((ViewGroup) date.getDatePicker().getChildAt(0)).getChildAt(0)).getChildAt(2).setVisibility(View.GONE);
                    }
                }

                break;
        }
    }

    private void initview() {
        mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                // 该加载更多啦。
                getData(false);
            }
        };
        rvDingdan.setLayoutManager(new LinearLayoutManager(rvDingdan.getContext(), LinearLayoutManager.VERTICAL, false));
        rvDingdan.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        refreshLayout.setColorSchemeResources(R.color.zangqing, R.color.zangqing,
                R.color.zangqing, R.color.zangqing);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ye = 1;
                getData(true);
                refreshLayout.setRefreshing(false);
            }
        });
        adapter = new JiaoYiLiuShuiAdapter(mContext, mlist);
        rvDingdan.setAdapter(adapter);
    }

    //数据
    private void getData(final boolean b) {

        Log.e("getData: ",mYear+"------"+mMonth );
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getJYMX(PreferenceUtils.getString(MyApplication.mContext, "token", ""), mYear + "", mMonths + "", ye))
                .setDataListener(new HttpDataListener<JiaoYiMingXiBean>() {
                    @Override
                    public void onNext(JiaoYiMingXiBean data) {
                        tvShouru.setText("收入 ￥ " + data.getShouru());
                        tvZhichu.setText("支出 ￥ " + data.getZhichu());
                        if (!"null".equals(String.valueOf(data.getLiebiao()))) {
                            if (b) {
                                mlist.clear();
                            }
                            mlist.addAll(data.getLiebiao());
                            if (data.getLiebiao().size() == 10) {
                                rvDingdan.loadMoreFinish(false, true);
                            } else if (data.getLiebiao().size() > 0) {
                                rvDingdan.loadMoreFinish(false, false);
                            } else {
                                rvDingdan.loadMoreFinish(true, false);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
        ye++;

    }

    /**
     * 获取系统SDK版本
     *
     * @return
     */
    public static int getSDKVersionNumber() {
        int sdkVersion;
        try {
            sdkVersion = Integer.valueOf(android.os.Build.VERSION.SDK_INT);
        } catch (NumberFormatException e) {
            sdkVersion = 0;
        }
        return sdkVersion;
    }
}
