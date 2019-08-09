package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean;
import com.mingmen.mayi.mayibanjia.bean.GetAllMarketBean;
import com.mingmen.mayi.mayibanjia.bean.ShangpinidAndDianpuidBean;
import com.mingmen.mayi.mayibanjia.bean.ShenPiQuanXuanBean;
import com.mingmen.mayi.mayibanjia.bean.ZiZhangHuDetailsBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShenPiLevelZeroAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ShenPiShiBaiDailog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.XuanZeZhuBiaoDailog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppManager;
import com.mingmen.mayi.mayibanjia.utils.DateUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/30/030.
 */
/*
* ━━━━━━神兽出没━━━━━━
*      ┏┓　 ┏┓
* 　　┏┛┻━━━┛┻┓
* 　  ┃　　　     ┃
* 　　┃　　　━    ┃
* 　　┃　┳┛　┗┳   ┃
* 　　┃　　　     ┃
* 　　┃　　　┻    ┃
* 　　┃　　　　   ┃
* 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
* 　　  ┃　　　┃    神兽保佑,代码无bug
*       ┃　　　┃
*       ┃　　　┗━━━┓
*       ┃　　　　　 ┣┓
*       ┃　　　　　┏┛
*       ┗┓┓┏━┳┓┏┛
*        ┃┫┫　┃┫┫
*        ┗┻┛　┗┻┛
*
*/

public class ShenPiActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rv_shenpi)
    RecyclerView rvShenpi;
    @BindView(R.id.rv_shenpi_ts)
    RecyclerView rvShenpiTs;
    @BindView(R.id.tv_zongjia)
    TextView tvZongjia;
    @BindView(R.id.tv_tijiao)
    TextView tvTijiao;
    @BindView(R.id.tv_biaoqian)
    TextView tvBiaoqian;
    @BindView(R.id.tv_pingfenzuigao)
    TextView tvPingfenzuigao;
    @BindView(R.id.tv_jiagezuidi)
    TextView tvJiagezuidi;
    @BindView(R.id.tv_shibai)
    TextView tvShibai;
    @BindView(R.id.tv_hdmc)
    TextView tvHdmc;
    @BindView(R.id.ll_quanxuan)
    LinearLayout llQuanxuan;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.tv_ptsp)
    TextView tvPtsp;
    @BindView(R.id.tv_tjsp)
    TextView tvTjsp;
    @BindView(R.id.view_ptsp)
    View viewPtsp;
    @BindView(R.id.view_tjsp)
    View viewTjsp;
    @BindView(R.id.ll_select)
    LinearLayout llSelect;
    @BindView(R.id.bt_qiangdan)
    Button btQiangdan;

    private Context mContext;
    private List<CaiGouDanBean.FllistBean> caigoudan = new ArrayList<>();
    private List<CaiGouDanBean.FllistBean> ts_caigoudan = new ArrayList<>();
    private ConfirmDialog confirmDialog;
    private ShenPiLevelZeroAdapter adapter;
    private ShenPiLevelZeroAdapter ts_adapter;
    private HashMap<String, ShangpinidAndDianpuidBean> xuanzhong = new HashMap<>();
    private String purchase_id;
    private String purchase_name = "";
    private String ct_buy_final_id = "";

    public String getPurchase_id() {
        return purchase_id;
    }

    public void setPurchase_id(String purchase_id) {
        this.purchase_id = purchase_id;
    }

    public String getCt_buy_final_id() {
        return ct_buy_final_id;
    }

    public void setCt_buy_final_id(String ct_buy_final_id) {
        this.ct_buy_final_id = ct_buy_final_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private LinearLayoutManager manager;
    private CaiGouDanBean myBean = new CaiGouDanBean();
    List<GetAllMarketBean> market_id = new ArrayList<>();
    private boolean isClick = true;
    private String message = "暂无此权限";
    private int REQUEST_CODE = 1;
    private String state = "0";
    private XuanZeZhuBiaoDailog dialog;
    private CountDownTimer timer;
    private long time = 300 * 1000;

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click, String message) {
        this.isClick = click;
        this.message = message;
    }

    public static ShenPiActivity instance = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shenpi;
    }

    @Override
    protected void initData() {
        mContext = ShenPiActivity.this;
        tvTitle.setText("审批");
        tvRight.setText("添加商品");
        tvRight.setVisibility(View.GONE);
        instance = this;
        if (StringUtil.isValid(getIntent().getStringExtra("ct_buy_final_id"))) {
            ct_buy_final_id = getIntent().getStringExtra("ct_buy_final_id");
        } else {
            purchase_id = getIntent().getStringExtra("purchase_id");
            purchase_name = getIntent().getStringExtra("caigouming");
        }

        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        getShenpi("0");
        getShenpi("1");
        if (StringUtil.isValid(PreferenceUtils.getString(MyApplication.mContext, "isShenPi", ""))) {
            if (PreferenceUtils.getString(MyApplication.mContext, "isShenPi", "").equals("5")) {
                ll.setVisibility(View.GONE);
                tvRight.setVisibility(View.GONE);
                for (ZiZhangHuDetailsBean.RoleListBean bean : PreferenceUtils.getQuanxianList(MyApplication.mContext, "quanxian")) {
                    if (bean.getRole_id().equals("2")) {
                        ll.setVisibility(View.VISIBLE);
                        return;
                    }

                }
                isClick = false;
            }
        }

    }

    private void initView(String mystate) {
//        caigoudan = myBean.getFllist();//采购单一级数据
//        if (myBean.getOrder_audit_state().equals("903") || myBean.getOrder_audit_state().equals("901")) {
//            isClick = false;
//            adapter.setClick(isClick);
//        }
        manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        if(mystate.equals("0")){
            for (int i = 0; i < caigoudan.size(); i++) {
                for (int j = 0; j < caigoudan.get(i).getSonorderlist().size(); j++) {
                    ShangpinidAndDianpuidBean bean = new ShangpinidAndDianpuidBean();
                    bean.setCommodity_id("");
                    bean.setCompany_id("");
                    bean.setDanjia("");
                    bean.setCount("0");
                    xuanzhong.put(caigoudan.get(i).getSonorderlist().get(j).getSon_order_id(), bean);
                }
            }
            adapter = new ShenPiLevelZeroAdapter(ShenPiActivity.this, caigoudan, mContext);
            rvShenpi.setLayoutManager(manager);
            rvShenpi.setFocusable(true);
            rvShenpi.setFocusableInTouchMode(true);
            rvShenpi.setAdapter(adapter);
            adapter.setClick(isClick);
            adapter.notifyDataSetChanged();
            adapter.setCallBack(new ShenPiLevelZeroAdapter.CallBack() {
                @Override
                public void isClick(View v, int pos) {

                }

                @Override
                public void setMarket(int pos, String id) {
                    caigoudan.get(pos).setMarket_id(id);
                }
            });
        } else {
            if(ts_caigoudan==null||ts_caigoudan.size()==0){

            } else {

                for (int i = 0; i < ts_caigoudan.size(); i++) {
                    for (int j = 0; j < ts_caigoudan.get(i).getSonorderlist().size(); j++) {
                        ShangpinidAndDianpuidBean bean = new ShangpinidAndDianpuidBean();
                        bean.setCommodity_id("");
                        bean.setCompany_id("");
                        bean.setDanjia("");
                        bean.setCount("0");
                        xuanzhong.put(ts_caigoudan.get(i).getSonorderlist().get(j).getSon_order_id(), bean);
                    }
                }
            }

            ts_adapter = new ShenPiLevelZeroAdapter(ShenPiActivity.this, ts_caigoudan, mContext);
            rvShenpiTs.setLayoutManager(manager);
            rvShenpiTs.setFocusable(true);
            rvShenpiTs.setFocusableInTouchMode(true);
            rvShenpiTs.setAdapter(ts_adapter);
            ts_adapter.setClick(isClick);
            ts_adapter.notifyDataSetChanged();
            ts_adapter.setCallBack(new ShenPiLevelZeroAdapter.CallBack() {
                @Override
                public void isClick(View v, int pos) {

                }

                @Override
                public void setMarket(int pos, String id) {
                    ts_caigoudan.get(pos).setMarket_id(id);
                }
            });
        }
    }

    //获取总价
    private void zongjia(String son_order_id, String commodity_id, String count, final TextView tv) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getcaigoudanjiage(PreferenceUtils.getString(MyApplication.mContext, "token", ""), son_order_id, commodity_id, count))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        tv.setText(data);
                    }
                });
    }

    //全选接口   返回的是全选后选择商品list
    private void setquanxuan(String purchase_id, final String type) {
        market_id.clear();
        for (int i = 0; i < caigoudan.size(); i++) {
            for (int j = 0; j < caigoudan.get(i).getSonorderlist().size(); j++) {
                GetAllMarketBean bean = new GetAllMarketBean();
                bean.setMarket_id(caigoudan.get(i).getMarket_id());
                bean.setSon_order_id(caigoudan.get(i).getSonorderlist().get(j).getSon_order_id());
                market_id.add(bean);
            }
        }
        for (int i = 0; i < ts_caigoudan.size(); i++) {
            for (int j = 0; j < ts_caigoudan.get(i).getSonorderlist().size(); j++) {
                GetAllMarketBean bean = new GetAllMarketBean();
                bean.setMarket_id(ts_caigoudan.get(i).getMarket_id());
                bean.setSon_order_id(ts_caigoudan.get(i).getSonorderlist().get(j).getSon_order_id());
                market_id.add(bean);
            }
        }
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .shenpitongguo(PreferenceUtils.getString(MyApplication.mContext, "token", ""), new Gson().toJson(market_id), purchase_id, ct_buy_final_id,state, type))
                .setDataListener(new HttpDataListener<ShenPiQuanXuanBean>() {
                    @Override
                    public void onNext(ShenPiQuanXuanBean data) {
                        //通过选中的商品list中的getSon_order_id和采购单的getSon_order_id 对比  把选中的商品存到map中
                        List<ShenPiQuanXuanBean.ListBean> datalist = data.getList();
                        for (int i = 0; i < caigoudan.size(); i++) {
                            for (int j = 0; j < caigoudan.get(i).getSonorderlist().size(); j++) {
                                for (int k = 0; k < datalist.size(); k++) {
                                    if (caigoudan.get(i).getSonorderlist().get(j).getSon_order_id().equals(datalist.get(k).getSon_order_id())) {
                                        ShangpinidAndDianpuidBean bean = new ShangpinidAndDianpuidBean();
                                        bean.setCommodity_id(datalist.get(k).getCommodity_id());
                                        bean.setCompany_id(datalist.get(k).getCompany_id());
                                        bean.setDanjia(datalist.get(k).getPrice());
                                        bean.setCount(datalist.get(k).getCount() + "");
                                        xuanzhong.put(caigoudan.get(i).getSonorderlist().get(j).getSon_order_id(), bean);
                                        break;
                                    }
                                }
                            }
                        }
                        for (int i = 0; i < ts_caigoudan.size(); i++) {
                            for (int j = 0; j < ts_caigoudan.get(i).getSonorderlist().size(); j++) {
                                for (int k = 0; k < datalist.size(); k++) {
                                    if (ts_caigoudan.get(i).getSonorderlist().get(j).getSon_order_id().equals(datalist.get(k).getSon_order_id())) {
                                        ShangpinidAndDianpuidBean bean = new ShangpinidAndDianpuidBean();
                                        bean.setCommodity_id(datalist.get(k).getCommodity_id());
                                        bean.setCompany_id(datalist.get(k).getCompany_id());
                                        bean.setDanjia(datalist.get(k).getPrice());
                                        bean.setCount(datalist.get(k).getCount() + "");
                                        xuanzhong.put(ts_caigoudan.get(i).getSonorderlist().get(j).getSon_order_id(), bean);
                                        break;
                                    }
                                }
                            }
                        }
                        //拼接商品id获取总价
                        Set<String> mapkey = xuanzhong.keySet();
                        String son_order_id = "";
                        String commodity_id = "";
                        String count = "";
                        for (String key : mapkey) {
                            ShangpinidAndDianpuidBean value = xuanzhong.get(key);
                            if (value.getCommodity_id().isEmpty()) {
                            } else {
                                son_order_id += key + ",";
                                commodity_id += value.getCommodity_id() + ",";
                                count += value.getCount() + ",";
                            }
                        }
                        son_order_id = son_order_id.substring(0, son_order_id.length() - 1);
                        commodity_id = commodity_id.substring(0, commodity_id.length() - 1);
                        count = count.substring(0, count.length() - 1);
                        zongjia(son_order_id, commodity_id, count, tvZongjia);
                        adapter.notifyDataSetChanged();
                        ts_adapter.notifyDataSetChanged();
                    }
                });
    }

    //判断是否全选了商品
    private HashMap<String, String> isQuanXuan() {
        Set<String> keys = xuanzhong.keySet();
        HashMap<String, String> map = new HashMap();
        for (String key : keys) {
            ShangpinidAndDianpuidBean value = xuanzhong.get(key);
            if (value.getCommodity_id().isEmpty()) {
                return null;
            } else {
                map.put(key, value.getCommodity_id());
            }
        }
        return map;
    }


    @OnClick({R.id.iv_back, R.id.tv_tijiao, R.id.tv_right, R.id.ll_select, R.id.ll_quanxuan, R.id.tv_biaoqian,
            R.id.tv_pingfenzuigao, R.id.tv_jiagezuidi, R.id.tv_shibai,R.id.tv_ptsp, R.id.tv_tjsp, R.id.bt_qiangdan})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.iv_back) {
            myBack();
        } else if(view.getId() == R.id.tv_ptsp){//普通商品
            tvPtsp.setTextColor(mContext.getResources().getColor(R.color.zangqing));
            tvTjsp.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
            viewPtsp.setBackgroundColor(mContext.getResources().getColor(R.color.zangqing));
            viewTjsp.setBackgroundColor(mContext.getResources().getColor(R.color.hintcolor));
            state = "0";
            btQiangdan.setVisibility(View.GONE);
            rvShenpi.setVisibility(View.VISIBLE);
            rvShenpiTs.setVisibility(View.INVISIBLE);
        }  else if(view.getId() == R.id.tv_tjsp){//特价商品
            tvPtsp.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
            tvTjsp.setTextColor(mContext.getResources().getColor(R.color.zangqing));
            viewPtsp.setBackgroundColor(mContext.getResources().getColor(R.color.hintcolor));
            viewTjsp.setBackgroundColor(mContext.getResources().getColor(R.color.zangqing));
            state = "1";
            btQiangdan.setVisibility(View.VISIBLE);
            rvShenpi.setVisibility(View.INVISIBLE);
            rvShenpiTs.setVisibility(View.VISIBLE);
        } else if (isClick) {
            switch (view.getId()) {
                case R.id.tv_right:
                    //添加商品
                    if (StringUtil.isValid(ct_buy_final_id)) {
                        dialog = new XuanZeZhuBiaoDailog();
                        dialog.setInitStr(ct_buy_final_id)
                                .setCallBack(new XuanZeZhuBiaoDailog.CallBack() {
                                    @Override
                                    public void setPurchase(String id, String name) {
                                        Intent it = new Intent();
                                        it.setClass(mContext, AddShangPinActivity.class);
                                        it.putExtra("name", name);
                                        it.putExtra("id", id);
                                        startActivityForResult(it, REQUEST_CODE);
                                    }
                                }).show(getSupportFragmentManager());
                    } else {
                        Intent it = new Intent();
                        it.setClass(mContext, AddShangPinActivity.class);
                        it.putExtra("name", purchase_name);
                        it.putExtra("id", purchase_id);
                        startActivityForResult(it, REQUEST_CODE);
                    }

//                    dialog = new CaiGouDanTianJiaDailog();
//                    dialog.setInitStr(ct_buy_final_id, purchase_id, myBean.getMarket_id())
//                            .setCallBack(new CaiGouDanTianJiaDailog.CallBack() {
//                                @Override
//                                public void confirm(CaiGouDanBean.FllistBean.SonorderlistBean msg) {
//                                    ToastUtil.showToast("添加成功");
//                                    tvZongjia.setText("0");
//                                    selectPinlei(msg);
//                                    adapter.notifyDataSetChanged();
//                                }
//
//                                @Override
//                                public void setDialogResult() {
//                                    Intent it = new Intent();
//                                    it.setClass(mContext, AddShangPinActivity.class);
//                                    it.putExtra("name",purchase_name);
//                                    it.putExtra("id",purchase_id);
//                                    startActivityForResult(it,REQUEST_CODE);
//                                }
//
//                                @Override
//                                public void setPurchase(String id, String name) {
//                                    purchase_id = id;
//                                    purchase_name = name;
//                                }
//                            }).show(getSupportFragmentManager());

                    break;
                case R.id.tv_tijiao:
                    //提交按钮
                    if (isQuanXuan() != null) {//全选了   拼接   跳转 确认订单
                        confirmDialog.showDialog("确认审批通过即发布商品成功");
                        confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mContext, QueRenDingDanActivity.class);
                                Set<String> mapkey = xuanzhong.keySet();
                                String son_order_id = "";
                                String commodity_id = "";
                                String company_id = "";
                                String shuliang = "";
//                                String special_son_order_id = "";
//                                String special_commodity_id = "";
                                for (String key : mapkey) {
                                    ShangpinidAndDianpuidBean value = xuanzhong.get(key);
                                    son_order_id += key + ",";

                                    commodity_id += value.getCommodity_id() + ",";
                                    company_id += value.getCompany_id() + ",";
                                    shuliang += value.getCount() + ",";
//
//                                    for (int i = 0; i < caigoudan.size(); i++) {
//                                        for (int j = 0; j < caigoudan.get(i).getSonorderlist().size(); j++) {
//                                            if (caigoudan.get(i).getSonorderlist().get(j).getSon_order_id().equals(key)) {
//                                                if (StringUtil.isValid(caigoudan.get(i).getSonorderlist().get(j).getSpecial_commodity())) {
//                                                    special_son_order_id += key + ",";
//                                                    special_commodity_id += value.getCommodity_id() + ",";
//                                                    shuliang += value.getCount() + ",";
//                                                }
//                                            }
//                                        }
//
//                                    }
                                }
//                                if (special_son_order_id.length() != 0) {
//                                    special_son_order_id = special_son_order_id.substring(0, special_son_order_id.length() - 1);
//                                    special_commodity_id = special_commodity_id.substring(0, special_commodity_id.length() - 1);
//                                    //如果存在特殊商品  并且已选择  更新抢单信息
//                                    gengxinqiangdan(special_son_order_id, special_commodity_id);
//                                }
                                intent.putExtra("son_order_id", son_order_id);
                                intent.putExtra("commodity_id", commodity_id);
                                Log.e("onClick: ", shuliang);
                                intent.putExtra("count", shuliang);
                                intent.putExtra("lujingtype", "2");
                                intent.putExtra("ct_buy_final_id", ct_buy_final_id);
                                intent.putExtra("company_id", company_id);
                                intent.putExtra("zongjia", tvZongjia.getText().toString());
                                startActivity(intent);
                                if (StringUtil.isValid(PreferenceUtils.getString(MyApplication.mContext, "host_account_type", ""))) {
                                    AppManager.getAppManager().finishAllActivity();
                                }
                                confirmDialog.dismiss();
                            }
                        });
                        confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                confirmDialog.cancel();
                            }
                        });
                    } else {
                        ToastUtil.showToast("商品还未全部选择无法提交订单");
                    }
                    break;
                case R.id.ll_select:
//                showPopupWindow();
                    break;
                case R.id.bt_qiangdan:
                    yijianqiangdan();
                    break;
                case R.id.ll_quanxuan://点击全选按钮
                    break;
                case R.id.tv_biaoqian:
                    if (isQuanxuanClick()) {
                        setShowColor();
                        setquanxuan(purchase_id, CaiGouDanBean.TYPE_TWO + "");
                        tvBiaoqian.setTextColor(getResources().getColor(R.color.zangqing));
                    } else {
                        ToastUtil.showToast("请确认市场是否全部选择");
                    }

                    break;
                case R.id.tv_pingfenzuigao:
                    if (isQuanxuanClick()) {
                        setShowColor();
                        setquanxuan(purchase_id, CaiGouDanBean.TYPE_THREE + "");
                        tvPingfenzuigao.setTextColor(getResources().getColor(R.color.zangqing));
                    } else {
                        ToastUtil.showToast("请确认市场是否全部选择");
                    }

                    break;
                case R.id.tv_jiagezuidi:
                    if (isQuanxuanClick()) {
                        setShowColor();
                        setquanxuan(purchase_id, CaiGouDanBean.TYPE_ONE + "");
                        tvJiagezuidi.setTextColor(getResources().getColor(R.color.zangqing));

                    } else {
                        ToastUtil.showToast("请确认市场是否全部选择");
                    }
                    break;
                case R.id.tv_shibai:
                    new ShenPiShiBaiDailog()
                            .setCallBack(new ShenPiShiBaiDailog.CallBack() {
                                @Override
                                public void confirm(String msg) {
                                    setShenPiShiBai(msg, purchase_id);
                                }
                            }).show(getSupportFragmentManager());
                    break;
            }
        } else {
            ToastUtil.showToast(message);
        }

    }


    public TextView getTvBiaoqian() {
        return tvBiaoqian;
    }

    public void setTvBiaoqian(TextView tvBiaoqian) {
        this.tvBiaoqian = tvBiaoqian;
    }

    public HashMap<String, ShangpinidAndDianpuidBean> getXuanzhong() {
        return xuanzhong;
    }

    public void setXuanzhong(HashMap<String, ShangpinidAndDianpuidBean> xuanzhong) {
        this.xuanzhong = xuanzhong;
    }

    @Override
    public void onBackPressed() {
        myBack();
    }

    public void myBack() {
        adapter.stopTime();
        Intent intent = new Intent(ShenPiActivity.this, CaiGouDanActivity.class);
        startActivity(intent);
        finish();
    }

    public void setViewShow(CaiGouDanBean.FllistBean.SonorderlistBean.CcListBeanLevel item) {//存储点击item,计算总价
        int count = 0;
        String son_order_id = "";
        String commodity_id = "";
        String mycount = "";
        Set<String> keys = xuanzhong.keySet();
        //存储选中的商品信息  更新adapter
        for (String key : keys) {
            if (key.equals(item.getCcListBean().getSon_order_id())) {
                ShangpinidAndDianpuidBean bean = new ShangpinidAndDianpuidBean();
                bean.setCommodity_id(item.getCcListBean().getCommodity_id());
                bean.setCompany_id(item.getCcListBean().getCompany_id());
                bean.setDanjia(item.getCcListBean().getPrice());
                bean.setCount(item.getCcListBean().getCount() + "");
                xuanzhong.put(item.getCcListBean().getSon_order_id(), bean);
            }
        }

        //把所有选中的商品拼接起来
        Set<String> mapkey = xuanzhong.keySet();
        for (String key : mapkey) {
            ShangpinidAndDianpuidBean value = xuanzhong.get(key);
            if (value.getCommodity_id().isEmpty()) {//没选中的不拼   避免有多余的,
            } else {
                son_order_id += key + ",";
                commodity_id += value.getCommodity_id() + ",";
                mycount += value.getCount() + ",";
                count++;
            }
        }
        if (count != 0) {
            son_order_id = son_order_id.substring(0, son_order_id.length() - 1);
            commodity_id = commodity_id.substring(0, commodity_id.length() - 1);
            mycount = mycount.substring(0, mycount.length() - 1);
            //调用接口获取总价
            zongjia(son_order_id, commodity_id, mycount, tvZongjia);
        } else {
            tvZongjia.setText("0");
        }

    }

    public void delViewShow() {//存储点击item,计算总价
        int count = 0;
        String son_order_id = "";
        String commodity_id = "";
        String mycount = "";
        Set<String> mapkey = xuanzhong.keySet();
        for (String key : mapkey) {
            ShangpinidAndDianpuidBean value = xuanzhong.get(key);
            if (value.getCommodity_id().isEmpty()) {//没选中的不拼   避免有多余的,
            } else {
                son_order_id += key + ",";
                commodity_id += value.getCommodity_id() + ",";
                mycount += value.getCount() + ",";
                count++;
            }
        }
        if (count != 0) {
            son_order_id = son_order_id.substring(0, son_order_id.length() - 1);
            commodity_id = commodity_id.substring(0, commodity_id.length() - 1);
            mycount = mycount.substring(0, mycount.length() - 1);
            //调用接口获取总价
            zongjia(son_order_id, commodity_id, mycount, tvZongjia);
        } else {
            tvZongjia.setText("0");
        }

    }

    //    public void setMoreShangjia(XiTongTuiJianBean.CcListBean bean){
    public void setMoreShangjia() {//刷新适配器
        if(state.equals("0")){
            adapter.notifyDataSetChanged();
        } else {
            ts_adapter.notifyDataSetChanged();
        }
    }

    public void setShowColor() {//设置显示颜色
        tvBiaoqian.setTextColor(getResources().getColor(R.color.zicolor));
        tvPingfenzuigao.setTextColor(getResources().getColor(R.color.zicolor));
        tvJiagezuidi.setTextColor(getResources().getColor(R.color.zicolor));
    }

    public void setAdapterXuanzhong(String id, ShangpinidAndDianpuidBean bean) {//设置选中
        xuanzhong.put(id, bean);
    }

    public void setAdapterDelXuanzhong(String key) {//取消选中
        xuanzhong.remove(key);
    }

    public void setItemMarket_id(int pos, String id, String name) {
        Log.e("setItemMarket_id: ", state+"------"+id);
        if(state.equals("0")){
            caigoudan.get(pos).setMarket_id(id);
            caigoudan.get(pos).setMarket_name(name);
        } else {
            ts_caigoudan.get(pos).setMarket_id(id);
            ts_caigoudan.get(pos).setMarket_name(name);
        }
    }

    private void setShenPiShiBai(String yuanyin, String purchase_id) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .shenpishibai(PreferenceUtils.getString(MyApplication.mContext, "token", ""), yuanyin, purchase_id, ct_buy_final_id))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        ToastUtil.showToast("审批失败");
                        myBack();
                    }
                });
    }

    public boolean isQuanxuanClick() {
        boolean myClick = true;
        for (int i = 0; i < caigoudan.size(); i++) {
            if (!StringUtil.isValid(caigoudan.get(i).getMarket_id())) {
                myClick = false;
            }
        }
        for (int i = 0; i < ts_caigoudan.size(); i++) {
            if (!StringUtil.isValid(ts_caigoudan.get(i).getMarket_id())) {
                myClick = false;
            }
        }
        return myClick;
    }
    public boolean tsQuanxuan() {
        boolean myClick = true;
        for (int i = 0; i < ts_caigoudan.size(); i++) {
            if (!StringUtil.isValid(ts_caigoudan.get(i).getMarket_id())) {
                myClick = false;
            }
        }
        return myClick;
    }



    private void getShenpi(final String mystate) {
        if (StringUtil.isValid(ct_buy_final_id)) {
            HttpManager.getInstance()
                    .with(mContext)
                    .setObservable(
                            RetrofitManager
                                    .getService()
                                    .getShenpiFour(PreferenceUtils.getString(MyApplication.mContext, "token", ""), ct_buy_final_id,mystate))
                    .setDataListener(new HttpDataListener<CaiGouDanBean>() {
                        @Override
                        public void onNext(CaiGouDanBean data) {
                            myBean.setMarket_id(data.getMarket_id());
                            myBean.setPurchase_id(data.getPurchase_id());
//                            myBean = data;
                            if(mystate.equals("0")){
                                caigoudan.addAll(data.getFllist());
                                initView(mystate);
                            } else {
                                ts_caigoudan.addAll(data.getFllist());
                                initView(mystate);
                                if(StringUtil.isValid(data.getQdTime())){
                                    try {
                                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        Date date = new Date(System.currentTimeMillis());
                                        Date date2 = null;
                                        date2 = format.parse(data.getQdTime());
                                        if(DateUtil.dqsj(date2,date,"4")/1000<=300){
                                            time = 300*1000 - DateUtil.dqsj(date2,date,"4");
                                            Log.e("onNext: time",DateUtil.dqsj(date2,date,"4")/1000+"" );
                                            startTime();
                                        }
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }

//                                    btQiangdan.setText(data.getQdTime());
                                }
                            }
//                            adapter.notifyDataSetChanged();
                            tvHdmc.setVisibility(View.VISIBLE);
                            tvHdmc.setText(data.getCt_buy_final_name());


                        }
                    });
        } else {
            HttpManager.getInstance()
                    .with(mContext)
                    .setObservable(
                            RetrofitManager
                                    .getService()
                                    .getShenpiThree(PreferenceUtils.getString(MyApplication.mContext, "token", ""), purchase_id,mystate))
                    .setDataListener(new HttpDataListener<CaiGouDanBean>() {
                        @Override
                        public void onNext(CaiGouDanBean data){
                            myBean.setMarket_id(data.getMarket_id());
                            myBean.setPurchase_id(data.getPurchase_id());
//                            myBean = data;
                            if(mystate.equals("0")){
                                caigoudan.addAll(data.getFllist());
                                initView(mystate);
                            } else {
                                ts_caigoudan.addAll(data.getFllist());
                                initView(mystate);
                                if(StringUtil.isValid(data.getQdTime())){
                                    try {
                                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        Date date = new Date(System.currentTimeMillis());
                                        Date date2 = null;
                                        date2 = format.parse(data.getQdTime());
                                        if(DateUtil.dqsj(date2,date,"4")/1000<=300){
                                            time = 300*1000 - DateUtil.dqsj(date2,date,"4");
                                            Log.e("onNext: time",DateUtil.dqsj(date2,date,"4")/1000+"" );
                                            startTime();
                                        }
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
//                                    btQiangdan.setText(data.getQdTime());
                                }
                            }
//                            adapter.notifyDataSetChanged();
                        }
                    });
        }
    }

    private void updateList(final String mystate) {
        if (StringUtil.isValid(ct_buy_final_id)) {
            HttpManager.getInstance()
                    .with(mContext)
                    .setObservable(
                            RetrofitManager
                                    .getService()
                                    .getShenpiFour(PreferenceUtils.getString(MyApplication.mContext, "token", ""), ct_buy_final_id,mystate))
                    .setDataListener(new HttpDataListener<CaiGouDanBean>() {
                        @Override
                        public void onNext(CaiGouDanBean data) {
                            myBean.setMarket_id(data.getMarket_id());
                            myBean.setPurchase_id(data.getPurchase_id());
//                            myBean = data;
                            for (int i = 0; i < data.getFllist().size(); i++) {
                                for (int j = 0; j < data.getFllist().get(i).getSonorderlist().size(); j++) {
                                    ShangpinidAndDianpuidBean bean = new ShangpinidAndDianpuidBean();
                                    bean.setCommodity_id("");
                                    bean.setCompany_id("");
                                    bean.setDanjia("");
                                    bean.setCount("0");
                                    xuanzhong.put(data.getFllist().get(i).getSonorderlist().get(j).getSon_order_id(), bean);
                                }
                            }
                            if(mystate.equals("0")){
                                caigoudan.clear();
                                adapter.notifyDataSetChanged();
                                caigoudan.addAll(data.getFllist());
                                adapter.notifyDataSetChanged();
                            } else {
                                ts_caigoudan.clear();
                                ts_adapter.notifyDataSetChanged();
                                ts_caigoudan.addAll(data.getFllist());
                                ts_adapter.notifyDataSetChanged();
                                if(StringUtil.isValid(data.getQdTime())){
                                    try {
                                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        Date date = new Date(System.currentTimeMillis());
                                        Date date2 = null;
                                        date2 = format.parse(data.getQdTime());
                                        if(DateUtil.dqsj(date2,date,"4")/1000<=300){
                                            time = 300*1000 - DateUtil.dqsj(date2,date,"4");
                                            Log.e("onNext: time",DateUtil.dqsj(date2,date,"4")/1000+"" );
                                            startTime();
                                        }
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
//                                    btQiangdan.setText(data.getQdTime());
                                }


                            }
//                            adapter.notifyDataSetChanged();
                            tvHdmc.setVisibility(View.VISIBLE);
                            tvHdmc.setText(data.getCt_buy_final_name());
                        }
                    });
        } else {
            HttpManager.getInstance()
                    .with(mContext)
                    .setObservable(
                            RetrofitManager
                                    .getService()
                                    .getShenpiThree(PreferenceUtils.getString(MyApplication.mContext, "token", ""), purchase_id,mystate))
                    .setDataListener(new HttpDataListener<CaiGouDanBean>() {
                        @Override
                        public void onNext(CaiGouDanBean data) {
                            myBean.setMarket_id(data.getMarket_id());
                            myBean.setPurchase_id(data.getPurchase_id());

                            for (int i = 0; i < data.getFllist().size(); i++) {
                                for (int j = 0; j < data.getFllist().get(i).getSonorderlist().size(); j++) {
                                    ShangpinidAndDianpuidBean bean = new ShangpinidAndDianpuidBean();
                                    bean.setCommodity_id("");
                                    bean.setCompany_id("");
                                    bean.setDanjia("");
                                    bean.setCount("0");
                                    xuanzhong.put(data.getFllist().get(i).getSonorderlist().get(j).getSon_order_id(), bean);
                                }
                            }
//                            myBean = data;
                            if(mystate.equals("0")){
                                caigoudan.clear();
                                adapter.notifyDataSetChanged();
                                caigoudan.addAll(data.getFllist());
                                adapter.notifyDataSetChanged();
                            } else {
                                ts_caigoudan.clear();
                                ts_adapter.notifyDataSetChanged();
                                ts_caigoudan.addAll(data.getFllist());
                                ts_adapter.notifyDataSetChanged();
                                if(StringUtil.isValid(data.getQdTime())){
//                                    btQiangdan.setText(data.getQdTime());
                                    try {
                                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        Date date = new Date(System.currentTimeMillis());
                                        Date date2 = null;
                                        date2 = format.parse(data.getQdTime());
                                        if(DateUtil.dqsj(date2,date,"4")/1000<=300){
                                            time = 300*1000 - DateUtil.dqsj(date2,date,"4");
                                            Log.e("onNext: time",DateUtil.dqsj(date2,date,"4")/1000+"" );
                                            startTime();
                                        }
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void yijianqiangdan(){
        market_id.clear();
        if(tsQuanxuan()){
            for (int i = 0; i < ts_caigoudan.size(); i++) {
                for (int j = 0; j < ts_caigoudan.get(i).getSonorderlist().size(); j++) {
//                    if(ts_caigoudan.get(i).getSonorderlist().get(j).getIstrue_type().equals("1")){
                        GetAllMarketBean bean = new GetAllMarketBean();
                        bean.setMarket_id(ts_caigoudan.get(i).getMarket_id());
                        bean.setSon_order_id(ts_caigoudan.get(i).getSonorderlist().get(j).getSon_order_id());
                        market_id.add(bean);
//                    }
                }
            }
            Log.e("yijianqiangdan: ",new Gson().toJson(market_id) );
            HttpManager.getInstance()
                    .with(mContext)
                    .setObservable(
                            RetrofitManager
                                    .getService()
                                    .yijianqiangdan(PreferenceUtils.getString(MyApplication.mContext, "token", ""),new Gson().toJson(market_id)))
                    .setDataListener(new HttpDataListener<String>() {
                        @Override
                        public void onNext(String data) {
                            time = 300 * 1000;
                            startTime();
                        }
                    });
        } else {
            ToastUtil.showToastLong("请确认市场全部选择");
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2) {
            if (requestCode == REQUEST_CODE) {
                if (StringUtil.isValid(data.getStringExtra("id"))) {
                    updateList("0");
                    updateList("1");
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer!=null){
            timer.cancel();
            timer = null;
        }
    }

    private void startTime(){
        ts_adapter.setClick(false);
        btQiangdan.setEnabled(false);
        ts_adapter.notifyDataSetChanged();
        if (timer != null) {
            timer = null;
        }
        timer = new CountDownTimer(time,1000) {
            @Override
            public void onTick(long l) {
                btQiangdan.setText((l/1000)+"s");
            }

            @Override
            public void onFinish() {
                updateList("1");
                ts_adapter.setClick(true);
                btQiangdan.setText("重新抢单");
                ts_adapter.notifyDataSetChanged();
                btQiangdan.setEnabled(true);
            }
        }.start();
        Log.e("startTime: ", "time:"+time);
    }
}
