package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.EditorShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.FeiLeiLableSubmitBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.FenLeiLableAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SpXinxiActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_fenleimingcheng)
    TextView tvFenleimingcheng;
    @BindView(R.id.ll_fenleimingcheng)
    LinearLayout llFenleimingcheng;
    @BindView(R.id.ll_fl)
    LinearLayout llFl;
    @BindView(R.id.tv_shangpinmingcheng)
    TextView tvShangpinmingcheng;
    @BindView(R.id.ll_shangpinmingcheng)
    LinearLayout llShangpinmingcheng;
    @BindView(R.id.ll_sp)
    LinearLayout llSp;
    @BindView(R.id.tv_spname)
    TextView tvSpname;
    @BindView(R.id.tv_spming)
    TextView tvSpming;
    @BindView(R.id.ll_shangpin)
    LinearLayout llShangpin;
    @BindView(R.id.tv_ppname)
    TextView tvPpname;
    @BindView(R.id.tv_ppming)
    TextView tvPpming;
    @BindView(R.id.ll_pinpai)
    LinearLayout llPinpai;
    @BindView(R.id.iv_sptu)
    ImageView ivSptu;
    @BindView(R.id.tv_xzgg)
    TextView tvXzgg;
    @BindView(R.id.rv_guige)
    RecyclerView rvGuige;
    @BindView(R.id.ll_szgg)
    LinearLayout llSzgg;
    @BindView(R.id.tv_sanji)
    TextView tvSanji;
    @BindView(R.id.ll_sanjiguige)
    LinearLayout llSanjiguige;
    @BindView(R.id.tv_yxgg)
    TextView tvYxgg;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_zxgg)
    TextView tvZxgg;
    @BindView(R.id.ll_zxgg)
    LinearLayout llZxgg;
    @BindView(R.id.ll_tu)
    LinearLayout llTu;
    @BindView(R.id.ll_ggcs)
    LinearLayout llGgcs;
    @BindView(R.id.tv_ggms)
    TextView tvGgms;
    @BindView(R.id.ll_dw)
    LinearLayout llDw;
    @BindView(R.id.tv_kucun)
    TextView tvKucun;
    @BindView(R.id.tv_qidingliangdanjia)
    TextView tvQidingliangdanjia;
    @BindView(R.id.tv_danwei)
    TextView tvDanwei;
    @BindView(R.id.tv_qidingliang)
    TextView tvQidingliang;
    @BindView(R.id.tv_qdl_gg)
    TextView tvQdlGg;
    @BindView(R.id.tv_tejia)
    TextView tvTejia;
    @BindView(R.id.tv_tj_danwei)
    TextView tvTjDanwei;
    @BindView(R.id.ll_showtejia)
    LinearLayout llShowtejia;
    @BindView(R.id.iv_xq1)
    ImageView ivXq1;
    @BindView(R.id.iv_xq2)
    ImageView ivXq2;
    @BindView(R.id.iv_xq3)
    ImageView ivXq3;
    @BindView(R.id.iv_xq4)
    ImageView ivXq4;
    @BindView(R.id.rv_flcs)
    RecyclerView rvFlcs;
    @BindView(R.id.tv_miaoshu)
    TextView tvMiaoshu;
    @BindView(R.id.rl_teshu)
    RelativeLayout rlTeshu;
    private Context mContext;
    private FenLeiLableAdapter adapter;
    private List<FeiLeiLableSubmitBean> mlist = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_sp_xinxi;
    }

    @Override
    protected void initData() {
        mContext = SpXinxiActivity.this;
        tvTitle.setText("商品信息");
        setDataView(getIntent().getStringExtra("id"));
        setRvAdapter();
        getFenLeiLable(getIntent().getStringExtra("id"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                break;
        }
    }

    public void setDataView(String id) {//编辑页面展示
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .editorShangPin(PreferenceUtils.getString(MyApplication.mContext, "token", ""), id))
                .setDataListener(new HttpDataListener<EditorShangPinBean>() {
                    @Override
                    public void onNext(EditorShangPinBean data) {
                        final EditorShangPinBean.XqBean bean = data.getXq();
                        if (StringUtil.isValid(bean.getHostPicture())) {
                            Glide.with(mContext).load(bean.getHostPicture()).into(ivSptu);
                        }
                        if (StringUtil.isValid(bean.getType_tree_id())) {
                            tvFenleimingcheng.setText(bean.getType_one_name());
                            llShangpin.setVisibility(View.GONE);
                            llFl.setVisibility(View.VISIBLE);
                            llSp.setVisibility(View.VISIBLE);
                            tvSpming.setText(bean.getClassify_name());
                            tvShangpinmingcheng.setText(bean.getClassify_name() + "");
                        } else {
                            tvSpming.setText(bean.getCommodity_name());
                            llFl.setVisibility(View.GONE);
                            llSp.setVisibility(View.GONE);
                            llShangpin.setVisibility(View.VISIBLE);
                        }
                        tvDanwei.setText("元/" + bean.getPackThreeName());
                        tvQdlGg.setText(bean.getPackThreeName());
                        if (StringUtil.isValid(bean.getBrand())) {
                            tvPpming.setText(bean.getBrand());
                        } else {
                            llPinpai.setVisibility(View.GONE);
                        }
                        if (!StringUtil.isValid(getIntent().getStringExtra("guige"))) {
                            tvKucun.setText(bean.getInventory());
                            tvQidingliang.setText(bean.getRation_one());
                            if (bean.getPice_one() != 0) {
                                tvQidingliangdanjia.setText(bean.getPice_one() + "");
                            }
                        }


                        if (StringUtil.isValid(bean.getAffiliated_number()) && Double.valueOf(bean.getAffiliated_number()) != 0) {
                            llDw.setVisibility(View.VISIBLE);
                            tvNumber.setText(bean.getAffiliated_number());
                            tvZxgg.setText(bean.getPackFourName());
                            tvYxgg.setText("每" + bean.getPackThreeName() + "换算单位为");
                            tvGgms.setText(bean.getPackThreeName());
                        } else {
                            llDw.setVisibility(View.GONE);
                        }
                        tvSanji.setText(bean.getPackThreeName());
                        tvGgms.setText(bean.getPackThreeName());
                        tvQdlGg.setText(bean.getPackThreeName());
                        tvMiaoshu.setText(bean.getSpec_describe());
                        int tusize = bean.getFtPicture()==null?0:bean.getFtPicture().size();
                        if (tusize!=0) {
                            for (int i = 0; i < bean.getDpicture().size(); i++) {
                                if (i == 0) {
                                    Glide.with(mContext).load(bean.getDpicture().get(i)).into(ivXq1);
                                    ivXq1.setVisibility(View.VISIBLE);
                                } else if (i == 1) {
                                    Glide.with(mContext).load(bean.getDpicture().get(i)).into(ivXq2);
                                    ivXq2.setVisibility(View.VISIBLE);
                                } else if (i == 2) {
                                    Glide.with(mContext).load(bean.getDpicture().get(i)).into(ivXq3);
                                    ivXq3.setVisibility(View.VISIBLE);
                                } else {
                                    Glide.with(mContext).load(bean.getDpicture().get(i)).into(ivXq4);
                                    ivXq4.setVisibility(View.VISIBLE);
                                }
                            }
                        } else {
                            llTu.setVisibility(View.GONE);
                        }
                    }
                });
    }

    private void setRvAdapter() {
        rvFlcs.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        adapter = new FenLeiLableAdapter(mContext, mlist);
        rvFlcs.setAdapter(adapter);
    }

    private void getFenLeiLable(String id) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getSpGuige(PreferenceUtils.getString(MyApplication.mContext, "token", ""),id))
                .setDataListener(new HttpDataListener<List<FeiLeiLableSubmitBean>>() {
                    @Override
                    public void onNext(List<FeiLeiLableSubmitBean> data) {
                        int mysize = data == null ? 0 : data.size();
                        if (mysize != 0) {
                            mlist.addAll(data);
                            adapter.notifyDataSetChanged();
                        } else {
                            llGgcs.setVisibility(View.GONE);
                        }
                    }
                });
    }
}
