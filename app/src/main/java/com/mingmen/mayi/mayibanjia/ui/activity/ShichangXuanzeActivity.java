package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
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
import com.mingmen.mayi.mayibanjia.bean.PostShichangBean;
import com.mingmen.mayi.mayibanjia.bean.ProvinceBean;
import com.mingmen.mayi.mayibanjia.bean.SongDaShiJianBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShiChangAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.DateDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ShangQuanDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.cityPicker.GetCityListUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private String shichangname = "";
    private String qu_id = "";
    private String sq_id = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_shichang_xuanze;
    }

    @Override
    protected void initData() {
        mContext = this;
        EventBus.getDefault().register(this);
        tvTitle.setText("市场选择");
        rvYijishichang.setLayoutManager(new GridLayoutManager(mContext,2));
        rvYijishichang.setAdapter(yijiAdapter);
        rvErjishichang.setLayoutManager(new GridLayoutManager(mContext,2));
        rvErjishichang.setAdapter(erjiAdapter);
        rvSanjishichang.setLayoutManager(new GridLayoutManager(mContext,2));
        rvSanjishichang.setAdapter(sanjiAdapter);
        getshichang();
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
                finish();
                break;
            case R.id.tv_right:
                break;
            case R.id.tv_city:
                break;
            case R.id.ll_shangquan:
                new ShangQuanDialog().setData(mContext).show(getSupportFragmentManager());
                break;
            case R.id.tv_all_shichang:
                shichangid = "";
                shichangname = "全部";
                backResult();
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
                                .getallshichang(PreferenceUtils.getString(MyApplication.mContext, "token", ""), "230000", "230100", qu_id, sq_id))
                .setDataListener(new HttpDataListener<AllShiChangBean>() {
                    @Override
                    public void onNext(AllShiChangBean data) {
                        shiChangBean = data;
                        if(StringUtil.isValid(sq_id)){
                            int two = shiChangBean.getTwoList()==null?0:shiChangBean.getTwoList().size();
                            if (two!=0) {
                                erjiAdapter.setNewData(shiChangBean.getTwoList());
                                tvErji.setVisibility(View.VISIBLE);
                                Log.e("erji", "erji");
                            } else {
                                tvErji.setVisibility(View.GONE);
                                erjiAdapter.setNewData(null);
                            }
                            erjiAdapter
                                    .setXuanZhongId(shichangid)
                                    .setCallBack(new ShiChangAdapter.CallBack() {
                                        @Override
                                        public void xuanzhong(AllShiChangBean.Bean msg) {
                                            shichangid = msg.getMark_id();
                                            shichangname = msg.getMarket_name();
                                            backResult();
                                        }
                                    });
                            rvErjishichang.setLayoutManager(new GridLayoutManager(mContext, 2));
                            rvErjishichang.setAdapter(erjiAdapter);

                            int three = shiChangBean.getThreeList()==null?0:shiChangBean.getThreeList().size();
                            if (three != 0) {
                                Log.e("sanji", "sanji");
                                tvSanji.setVisibility(View.VISIBLE);
                                sanjiAdapter.setNewData(shiChangBean.getThreeList());
                            } else {
                                tvSanji.setVisibility(View.GONE);
                                sanjiAdapter.setNewData(null);
                            }
                            sanjiAdapter
                                    .setXuanZhongId(shichangid)
                                    .setCallBack(new ShiChangAdapter.CallBack() {
                                        @Override
                                        public void xuanzhong(AllShiChangBean.Bean msg) {
                                            shichangid = msg.getMark_id();
                                            shichangname = msg.getMarket_name();
                                            backResult();
                                        }
                                    });
                            rvSanjishichang.setLayoutManager(new GridLayoutManager(mContext, 2));
                            rvSanjishichang.setAdapter(sanjiAdapter);
                        } else {
                            int one = shiChangBean.getOneList()==null?0:shiChangBean.getOneList().size();
                            if (one != 0) {
                                yijiAdapter.setNewData(shiChangBean.getOneList());
                                tvYiji.setVisibility(View.VISIBLE);
                            } else {
                                tvYiji.setVisibility(View.GONE);
                                yijiAdapter.setNewData(null);
                            }
                            yijiAdapter.setXuanZhongId(shichangid)
                                    .setCallBack(new ShiChangAdapter.CallBack() {
                                        @Override
                                        public void xuanzhong(AllShiChangBean.Bean msg) {
                                            shichangid = msg.getMark_id();
                                            shichangname = msg.getMarket_name();
                                            backResult();
                                        }
                                    });
                            rvYijishichang.setLayoutManager(new GridLayoutManager(mContext, 2));
                            rvYijishichang.setAdapter(yijiAdapter);
                            tvErji.setVisibility(View.GONE);
                            tvSanji.setVisibility(View.GONE);
                        }
                    }
                }, false);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getShangquan(PostShichangBean bean) {
        tvShangquan.setText(bean.getName());
        qu_id = bean.getQu_bm();
        sq_id = bean.getSq_bm();
        getshichang();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void backResult(){
        AllShiChangBean.Bean bean= new AllShiChangBean.Bean();
        bean.setMark_id(shichangid);
        bean.setMarket_name(shichangname);
        EventBus.getDefault().post(bean);
        finish();
    }
}
