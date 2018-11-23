package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.ShangpinidAndDianpuidBean;
import com.mingmen.mayi.mayibanjia.bean.YunFeiBean;
import com.mingmen.mayi.mayibanjia.bean.YunFeiJieSuanBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.YunFeiAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.YunFeiDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.YunFeiTypeDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YunFeiJieSuanActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.iv_quanxuan)
    ImageView ivQuanxuan;
    @BindView(R.id.ll_quanxuan)
    LinearLayout llQuanxuan;
    @BindView(R.id.bt_jiesuan)
    Button btJiesuan;
    @BindView(R.id.ll_jiesuan)
    LinearLayout llJiesuan;
    @BindView(R.id.tv_zongjia)
    TextView tvZongjia;
    private List<YunFeiJieSuanBean.DdListBean> list = new ArrayList<>();
    private YunFeiAdapter adapter;
    private YunFeiTypeDialog dialog;
    private Context mContext;
    private int ye = 1;
    private boolean b = false;
    private String type = "0";
    private String wl_cars_order_id = "";
    private double zj = 0;
    private HashMap<String, YunFeiJieSuanBean.DdListBean> xuanzhong = new HashMap<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_yun_fei_jie_suan;
    }

    @Override
    protected void initData() {
        mContext = YunFeiJieSuanActivity.this;
        tvTitle.setText("未结算");
        dialog = new YunFeiTypeDialog()
                .setActivity(YunFeiJieSuanActivity.this)
                .init(0, 0, 0)
                .setTop(AppUtil.dip2px(44));
        adapter = new YunFeiAdapter(mContext, list, YunFeiJieSuanActivity.this);
        rvList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvList.setAdapter(adapter);
        getList("0");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.ll_quanxuan, R.id.bt_jiesuan, R.id.ll_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_quanxuan:
                b = !b;
                adapter.setSelect(b);
                ivQuanxuan.setSelected(b);
                adapter.notifyDataSetChanged();
                break;
            case R.id.bt_jiesuan:
                if(zj>0){
                    YunFeiDialog dialog = new YunFeiDialog();
                    dialog.setdata(mContext,zj, new YunFeiDialog.CallBack() {
                        @Override
                        public void succeed(String jine) {
                            jiesuan(wl_cars_order_id,zj+"",jine);
                        }
                    }).show(getSupportFragmentManager());
                } else {
                    ToastUtil.showToast("请选择物流订单后执行结算");
                }
                break;
            case R.id.ll_title:
                dialog.show(getSupportFragmentManager());
                break;
        }
    }

//    public void getList(){
//        list.clear();
//        HttpManager.getInstance()
//                .with(mContext)
//                .setObservable(RetrofitManager.getService()
//                        .getYunfeiList(PreferenceUtils.getString(MyApplication.mContext, "token", ""),ye+"","0"))
//                .setDataListener(new HttpDataListener<YunFeiJieSuanBean>() {
//                    @Override
//                    public void onNext(YunFeiJieSuanBean bean) {
//                        list.addAll(bean.getDdList());
//                        dialog.init(bean.getCount(),bean.getCount1(),bean.getCount2());
//                        adapter.notifyDataSetChanged();
//                        ye++;
//                    }
//                });
//    }

    public void getList(String type) {
        this.type = type;
        list.clear();
        ye = 1;
        if (type.equals("0")) {
            tvTitle.setText("未结算");
            llJiesuan.setVisibility(View.VISIBLE);
        } else if (type.equals("1")) {
            tvTitle.setText("结算中");
            llJiesuan.setVisibility(View.GONE);
        } else if (type.equals("2")) {
            tvTitle.setText("已结算");
            llJiesuan.setVisibility(View.GONE);
        }
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getYunfeiList(PreferenceUtils.getString(MyApplication.mContext, "token", ""), ye + "", type))
                .setDataListener(new HttpDataListener<YunFeiJieSuanBean>() {
                    @Override
                    public void onNext(YunFeiJieSuanBean bean) {
                        list.addAll(bean.getDdList());
                        dialog.init(bean.getCount(), bean.getCount1(), bean.getCount2());
                        adapter.notifyDataSetChanged();
                        ye++;
                    }
                });
    }

    public void jiesuan(String id, String yf, String sjyf) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .yunfeijiesuan(PreferenceUtils.getString(MyApplication.mContext, "token", ""), id, yf, sjyf))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String bean) {
                        adapter.setSelect(!b);
                        getList(type);
                    }
                });
    }

    public void addItem(YunFeiJieSuanBean.DdListBean bean) {
        xuanzhong.put(bean.getWl_cars_order_number(), bean);
        shuaxin();
    }

    public void delItem(YunFeiJieSuanBean.DdListBean bean) {
        xuanzhong.remove(bean.getWl_cars_order_number());
        shuaxin();
    }

    public void shuaxin() {
        wl_cars_order_id = "";
        zj = 0;
        Set<String> mapkey = xuanzhong.keySet();
        for (String key : mapkey) {
            YunFeiJieSuanBean.DdListBean value = xuanzhong.get(key);
            if (value.getWl_cars_order_number().isEmpty()) {
            } else {
                wl_cars_order_id += value.getWl_cars_order_number() + ",";
                zj += value.getFreight_fee();
            }
        }
        tvZongjia.setText(zj+"");
    }
}
