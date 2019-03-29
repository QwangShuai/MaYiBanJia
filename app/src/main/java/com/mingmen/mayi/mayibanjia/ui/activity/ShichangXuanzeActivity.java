package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.AllShiChangBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShiChangAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShichangXuanzeActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_shangquan)
    TextView tvShangquan;
    @BindView(R.id.ll_shangquan)
    LinearLayout llShangquan;
    @BindView(R.id.tv_all_shichang)
    TextView tvAllShichang;
    @BindView(R.id.tv_yiji)
    TextView tvYiji;
    @BindView(R.id.rv_yijishichang)
    RecyclerView rvYijishichang;
    @BindView(R.id.tv_erji)
    TextView tvErji;
    @BindView(R.id.rv_erjishichang)
    RecyclerView rvErjishichang;
    @BindView(R.id.tv_sanji)
    TextView tvSanji;
    @BindView(R.id.rv_sanjishichang)
    RecyclerView rvSanjishichang;
    @BindView(R.id.ll_shichang_view)
    LinearLayout llShichangView;
    @BindView(R.id.sv_shichang)
    ScrollView svShichang;

    private ShiChangAdapter yijiAdapter = new ShiChangAdapter();
    private ShiChangAdapter erjiAdapter = new ShiChangAdapter();
    private ShiChangAdapter sanjiAdapter = new ShiChangAdapter();
    private AllShiChangBean shiChangBean = new AllShiChangBean();
    private Context mContext;
    private String shichangid = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_shichang_xuanze;
    }

    @Override
    protected void initData() {
        mContext = this;
        tvTitle.setText("市场选择");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_title, R.id.iv_back, R.id.tv_right, R.id.tv_city, R.id.ll_shangquan, R.id.tv_all_shichang, R.id.tv_yiji, R.id.tv_erji, R.id.tv_sanji})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_title:
                break;
            case R.id.iv_back:
                break;
            case R.id.tv_right:
                break;
            case R.id.tv_city:
                break;
            case R.id.ll_shangquan:
                break;
            case R.id.tv_all_shichang:
                break;
            case R.id.tv_yiji:
                break;
            case R.id.tv_erji:
                break;
            case R.id.tv_sanji:
                break;
        }
    }

    private void getshichang() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getallshichang(PreferenceUtils.getString(MyApplication.mContext, "token", ""), "230000", "230100", "", ""))
                .setDataListener(new HttpDataListener<AllShiChangBean>() {
                    @Override
                    public void onNext(AllShiChangBean data) {
                        shiChangBean = data;
                        if (shiChangBean.getOneList() != null) {
                            yijiAdapter.setNewData(shiChangBean.getOneList());
                        } else {
                            tvYiji.setVisibility(View.GONE);
                        }
                        yijiAdapter.setXuanZhongId(shichangid)
                                .setCallBack(new ShiChangAdapter.CallBack() {
                                    @Override
                                    public void xuanzhong(AllShiChangBean.Bean msg) {
//                                        shuaxinAdapter(msg);
//                                        ye = 1;
//                                        sousuoshangpin(sousuo, "0");
                                    }
                                });
                        rvYijishichang.setLayoutManager(new GridLayoutManager(mContext, 2));
                        rvYijishichang.setAdapter(yijiAdapter);

                        if (shiChangBean.getTwoList() != null) {
                            erjiAdapter.setNewData(shiChangBean.getTwoList());
                            Log.e("erji", "erji");
                        } else {
                            tvErji.setVisibility(View.GONE);
                        }
                        erjiAdapter
                                .setXuanZhongId(shichangid)
                                .setCallBack(new ShiChangAdapter.CallBack() {
                                    @Override
                                    public void xuanzhong(AllShiChangBean.Bean msg) {
//                                        ye = 1;
//                                        shuaxinAdapter(msg);
//                                        sousuoshangpin(sousuo, "0");
                                    }
                                });
                        rvErjishichang.setLayoutManager(new GridLayoutManager(mContext, 2));
                        rvErjishichang.setAdapter(erjiAdapter);


                        if (shiChangBean.getThreeList() != null) {
                            Log.e("sanji", "sanji");
                            sanjiAdapter.setNewData(shiChangBean.getThreeList());
                        } else {
                            tvSanji.setVisibility(View.GONE);
                        }
                        sanjiAdapter
                                .setXuanZhongId(shichangid)
                                .setCallBack(new ShiChangAdapter.CallBack() {
                                    @Override
                                    public void xuanzhong(AllShiChangBean.Bean msg) {
//                                        ye = 1;
//                                        shuaxinAdapter(msg);
//                                        sousuoshangpin(sousuo, "0");
                                    }
                                });
                        rvSanjishichang.setLayoutManager(new GridLayoutManager(mContext, 2));
                        rvSanjishichang.setAdapter(sanjiAdapter);
                    }
                }, false);
    }
}
