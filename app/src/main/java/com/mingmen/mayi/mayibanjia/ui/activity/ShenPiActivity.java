package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean;
import com.mingmen.mayi.mayibanjia.bean.GetAllMarketBean;
import com.mingmen.mayi.mayibanjia.bean.ShangpinidAndDianpuidBean;
import com.mingmen.mayi.mayibanjia.bean.ShenPiQuanXuanBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShenPiLevelZeroAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.CaiGouDanTianJiaDailog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ShenPiShiBaiDailog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.XuanZeZhuBiaoDailog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
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

    private Context mContext;
    private List<CaiGouDanBean.FllistBean> caigoudan = new ArrayList<>();
    private ConfirmDialog confirmDialog;
    //    private ShenPiShangPinAdapter1 shangpinadapter;
//    private ShenPiLevelOneAdapter adapter;
    private ShenPiLevelZeroAdapter adapter;
    private PopupWindow mPopWindow;
    private HashMap<String, ShangpinidAndDianpuidBean> xuanzhong = new HashMap<>();
    private int item_position;
    private String purchase_id;
    private String purchase_name = "";
    private String ct_buy_final_id = "";
    private LinearLayoutManager manager;
    private CaiGouDanBean myBean = new CaiGouDanBean();
    List<GetAllMarketBean> market_id = new ArrayList<>();
    private boolean isClick = true;
    private String message = "暂无此权限";
    private int REQUEST_CODE = 1;
    //    CaiGouDanTianJiaDailog dialog;
    XuanZeZhuBiaoDailog dialog;

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
        instance = this;
        if (StringUtil.isValid(getIntent().getStringExtra("ct_buy_final_id"))) {
            ct_buy_final_id = getIntent().getStringExtra("ct_buy_final_id");
        } else {
            purchase_id = getIntent().getStringExtra("purchase_id");
            purchase_name = getIntent().getStringExtra("caigouming");
        }

        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        if (StringUtil.isValid(PreferenceUtils.getString(MyApplication.mContext, "isShenPi", ""))) {
            if (PreferenceUtils.getString(MyApplication.mContext, "isShenPi", "").equals("5")) {
                Log.e("My", PreferenceUtils.getString(MyApplication.mContext, "isShenPi", ""));
                ll.setVisibility(View.GONE);
            }
        }
        getShenpi();
    }

    private void initView() {
//        caigoudan = myBean.getFllist();//采购单一级数据
//        if (myBean.getOrder_audit_state().equals("903") || myBean.getOrder_audit_state().equals("901")) {
//            isClick = false;
//            adapter.setClick(isClick);
//        }
        xuanzhong = new HashMap();
        for (int i = 0; i < caigoudan.size(); i++) {
            for (int j = 0; j < caigoudan.get(i).getSonorderlist().size(); j++) {
                ShangpinidAndDianpuidBean bean = new ShangpinidAndDianpuidBean();
                bean.setCommodity_id("");
                bean.setCompany_id("");
                bean.setDanjia("");
                xuanzhong.put(caigoudan.get(i).getSonorderlist().get(j).getSon_order_id(), bean);
            }
        }
        adapter = new ShenPiLevelZeroAdapter(ShenPiActivity.this, caigoudan, mContext);

        manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rvShenpi.setLayoutManager(manager);
        rvShenpi.setFocusable(true);
        rvShenpi.setFocusableInTouchMode(true);
//        rvShenpi.setNestedScrollingEnabled(false);
        rvShenpi.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    //获取总价
    private void zongjia(String son_order_id, String commodity_id, final TextView tv) {
        Log.e( "zongjia: ",son_order_id+"----"+commodity_id );
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getcaigoudanjiage(PreferenceUtils.getString(MyApplication.mContext, "token", ""), son_order_id, commodity_id))
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
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .shenpitongguo(PreferenceUtils.getString(MyApplication.mContext, "token", ""), new Gson().toJson(market_id), purchase_id, ct_buy_final_id, type))
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
                                        xuanzhong.put(caigoudan.get(i).getSonorderlist().get(j).getSon_order_id(), bean);
                                        break;
                                    }
                                }
                            }
                        }
                        //拼接商品id获取总价
                        Set<String> mapkey = xuanzhong.keySet();
                        String son_order_id = "";
                        String commodity_id = "";
                        for (String key : mapkey) {
                            ShangpinidAndDianpuidBean value = xuanzhong.get(key);
                            if (value.getCommodity_id().isEmpty()) {
                            } else {
                                son_order_id += key + ",";
                                commodity_id += value.getCommodity_id() + ",";
                            }
                        }
                        son_order_id = son_order_id.substring(0, son_order_id.length() - 1);
                        commodity_id = commodity_id.substring(0, commodity_id.length() - 1);
                        zongjia(son_order_id, commodity_id, tvZongjia);
                        adapter.notifyDataSetChanged();
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
            R.id.tv_pingfenzuigao, R.id.tv_jiagezuidi, R.id.tv_shibai})
    public void onViewClicked(View view) {
        CaiGouDanBean.FllistBean listBean = caigoudan.get(0);
        if (view.getId() == R.id.iv_back) {
            myBack();
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
                                String special_son_order_id = "";
                                String special_commodity_id = "";
                                for (String key : mapkey) {
                                    ShangpinidAndDianpuidBean value = xuanzhong.get(key);
                                    son_order_id += key + ",";

                                    commodity_id += value.getCommodity_id() + ",";
                                    company_id += value.getCompany_id() + ",";
//
                                    for (int i = 0; i < caigoudan.size(); i++) {
                                        for (int j = 0; j < caigoudan.get(i).getSonorderlist().size(); j++) {
                                            if (caigoudan.get(i).getSonorderlist().get(j).getSon_order_id().equals(key)) {
                                                if (StringUtil.isValid(caigoudan.get(i).getSonorderlist().get(j).getSpecial_commodity())) {
                                                    special_son_order_id += key + ",";
                                                    special_commodity_id += value.getCommodity_id() + ",";
                                                }
                                            }
                                        }

                                    }
                                }
//                                if (special_son_order_id.length() != 0) {
//                                    special_son_order_id = special_son_order_id.substring(0, special_son_order_id.length() - 1);
//                                    special_commodity_id = special_commodity_id.substring(0, special_commodity_id.length() - 1);
//                                    //如果存在特殊商品  并且已选择  更新抢单信息
//                                    gengxinqiangdan(special_son_order_id, special_commodity_id);
//                                }

                                intent.putExtra("son_order_id", son_order_id);
                                intent.putExtra("commodity_id", commodity_id);
                                intent.putExtra("lujingtype", "2");
                                intent.putExtra("ct_buy_final_id", ct_buy_final_id);
                                intent.putExtra("company_id", company_id);
                                intent.putExtra("zongjia", tvZongjia.getText().toString());
                                startActivity(intent);
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

//    //更新抢单信息接口
//    private void gengxinqiangdan(String special_son_order_id, String special_commodity_id) {
//        HttpManager.getInstance()
//                .with(mContext)
//                .setObservable(
//                        RetrofitManager
//                                .getService()
//                                .gengxinqiangdan(PreferenceUtils.getString(MyApplication.mContext, "token", ""), special_son_order_id, special_commodity_id))
//                .setDataListener(new HttpDataListener<String>() {
//                    @Override
//                    public void onNext(String data) {
//                    }
//                }, false);
//    }

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

    public int getItem_position() {
        return item_position;
    }

    public void setViewShow(CaiGouDanBean.FllistBean.SonorderlistBean.CcListBeanLevel item) {//存储点击item,计算总价
        int count = 0;
        String son_order_id = "";
        String commodity_id = "";
        Set<String> keys = xuanzhong.keySet();
        //存储选中的商品信息  更新adapter
        for (String key : keys) {
            if (key.equals(item.getCcListBean().getSon_order_id())) {
                ShangpinidAndDianpuidBean bean = new ShangpinidAndDianpuidBean();
                bean.setCommodity_id(item.getCcListBean().getCommodity_id());
                bean.setCompany_id(item.getCcListBean().getCompany_id());
                bean.setDanjia(item.getCcListBean().getPrice());
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
                count++;
            }
        }
        if (count != 0) {
            son_order_id = son_order_id.substring(0, son_order_id.length() - 1);
            commodity_id = commodity_id.substring(0, commodity_id.length() - 1);
            //调用接口获取总价
            zongjia(son_order_id, commodity_id, tvZongjia);
        } else {
            tvZongjia.setText("0");
        }

    }

    public void delViewShow() {//存储点击item,计算总价
        int count = 0;
        String son_order_id = "";
        String commodity_id = "";
        Set<String> mapkey = xuanzhong.keySet();
        for (String key : mapkey) {
            ShangpinidAndDianpuidBean value = xuanzhong.get(key);
            if (value.getCommodity_id().isEmpty()) {//没选中的不拼   避免有多余的,
            } else {
                son_order_id += key + ",";
                commodity_id += value.getCommodity_id() + ",";
                count++;
            }
        }
        if (count != 0) {
            son_order_id = son_order_id.substring(0, son_order_id.length() - 1);
            commodity_id = commodity_id.substring(0, commodity_id.length() - 1);
            //调用接口获取总价
            zongjia(son_order_id, commodity_id, tvZongjia);
        } else {
            tvZongjia.setText("0");
        }

    }

    //    public void setMoreShangjia(XiTongTuiJianBean.CcListBean bean){
    public void setMoreShangjia() {//刷新适配器
        adapter.notifyDataSetChanged();
    }

    public void MoveToPosition(int n) {
        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            rvShenpi.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = rvShenpi.getChildAt(n - firstItem).getTop();
            rvShenpi.scrollBy(0, top);
        } else {
            rvShenpi.scrollToPosition(n);
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

    public void selectPinlei(CaiGouDanBean.FllistBean.SonorderlistBean bean) {//选择品类
        for (int i = 0; i < caigoudan.size(); i++) {
            if (bean.getOne_classify_name().equals(caigoudan.get(i).getClassify_name())) {
//                for (int j=0;j<caigoudan.get(i).getSonorderlist().size();j++){
//                    if(bean.getSort_id().equals(caigoudan.get(i).getSonorderlist().get(j).getSort_id())&&
//                            !StringUtil.isValid(bean.getSpecial_commodity())&&
//                            !StringUtil.isValid(caigoudan.get(i).getSonorderlist().get(j).getSpecial_commodity())){
////                        int count = caigoudan.get(i).getSonorderlist().get(j).getCount() + bean.getCount();
////                        bean.setCount(count);
//                        bean.setClassify_name(caigoudan.get(i).getSonorderlist().get(j).getClassify_name());
//                        caigoudan.get(i).getSonorderlist().set(j,bean);
//                        ShangpinidAndDianpuidBean sdbean = new ShangpinidAndDianpuidBean();
//                        sdbean.setCommodity_id("");
//                        sdbean.setCompany_id("");
//                        sdbean.setDanjia("");
//                        xuanzhong.put(caigoudan.get(i).getSonorderlist().get(caigoudan.get(i).getSonorderlist().size() - 1).getSon_order_id(), sdbean);
//                    }else if(j==caigoudan.get(i).getSonorderlist().size()-1){
                bean.setMarket_id(caigoudan.get(i).getSonorderlist().get(caigoudan.get(i).getSonorderlist().size() - 1).getMarket_id());
                caigoudan.get(i).getSonorderlist().add(bean);
                ShangpinidAndDianpuidBean sdbean = new ShangpinidAndDianpuidBean();
                sdbean.setCommodity_id("");
                sdbean.setCompany_id("");
                sdbean.setDanjia("");
                xuanzhong.put(caigoudan.get(i).getSonorderlist().get(caigoudan.get(i).getSonorderlist().size() - 1).getSon_order_id(), sdbean);
//                    }
//                }
                adapter.notifyDataSetChanged();
                break;
            } else if (i == caigoudan.size() - 1) {
                CaiGouDanBean.FllistBean myBean = new CaiGouDanBean.FllistBean();
                List<CaiGouDanBean.FllistBean.SonorderlistBean> myList = new ArrayList<>();
                myBean.setClassify_id(bean.getClassify_id());
                myBean.setClassify_name(bean.getOne_classify_name());
                myList.add(bean);
                myBean.setSonorderlist(myList);
                caigoudan.add(myBean);
                ShangpinidAndDianpuidBean sdbean = new ShangpinidAndDianpuidBean();
                sdbean.setCommodity_id("");
                sdbean.setCompany_id("");
                sdbean.setDanjia("");
                xuanzhong.put(caigoudan.get(i + 1).getSonorderlist().get(caigoudan.get(i + 1).getSonorderlist().size() - 1).getSon_order_id(), sdbean);
                adapter.addLength();
                adapter.notifyDataSetChanged();
                break;
            }
        }

    }

    public void setItemMarket_id(int pos, String id, String name) {
        caigoudan.get(pos).setMarket_id(id);
        caigoudan.get(pos).setMarket_name(name);
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
                        ToastUtil.showToast("成功");
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
        return myClick;
    }

    private void getShenpi() {
        if (StringUtil.isValid(ct_buy_final_id)) {
            HttpManager.getInstance()
                    .with(mContext)
                    .setObservable(
                            RetrofitManager
                                    .getService()
                                    .getShenpiFour(PreferenceUtils.getString(MyApplication.mContext, "token", ""), ct_buy_final_id))
                    .setDataListener(new HttpDataListener<CaiGouDanBean>() {
                        @Override
                        public void onNext(CaiGouDanBean data) {
                            myBean.setMarket_id(data.getMarket_id());
                            myBean.setPurchase_id(data.getPurchase_id());
//                            myBean = data;
                            caigoudan.addAll(data.getFllist());
//                            adapter.notifyDataSetChanged();
                            tvHdmc.setVisibility(View.VISIBLE);
                            tvHdmc.setText(data.getCt_buy_final_name());
                            initView();

                        }
                    });
        } else {
            HttpManager.getInstance()
                    .with(mContext)
                    .setObservable(
                            RetrofitManager
                                    .getService()
                                    .getShenpiThree(PreferenceUtils.getString(MyApplication.mContext, "token", ""), purchase_id))
                    .setDataListener(new HttpDataListener<CaiGouDanBean>() {
                        @Override
                        public void onNext(CaiGouDanBean data) {
                            myBean.setMarket_id(data.getMarket_id());
                            myBean.setPurchase_id(data.getPurchase_id());
//                            myBean = data;
                            caigoudan.addAll(data.getFllist());
//                            adapter.notifyDataSetChanged();
                            initView();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2) {
            if (requestCode == REQUEST_CODE) {
                if (StringUtil.isValid(data.getStringExtra("id"))) {
                    caigoudan.clear();
                    getShenpi();
                }
            }
        }
    }
}
