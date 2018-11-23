package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean;
import com.mingmen.mayi.mayibanjia.bean.LiShiJiLuBean;
import com.mingmen.mayi.mayibanjia.bean.ShangpinidAndDianpuidBean;
import com.mingmen.mayi.mayibanjia.bean.ShenPiQuanXuanBean;
import com.mingmen.mayi.mayibanjia.bean.XiTongTuiJianBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShenPiLevelOneAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.CaiGouDanTianJiaDailog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.CaiGouDanXiuGaiDailog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.LiShiJiLuDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.JumpUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.custom.TvRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

import static com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean.TYPE_FIVE;
import static com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean.TYPE_FOUR;
import static com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean.TYPE_ONE;
import static com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean.TYPE_THREE;
import static com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean.TYPE_TWO;

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
    //    @BindView(R.id.view_showpop)
//    View viewShowpop;
//    @BindView(R.id.ll_select)
//    LinearLayout llSelect;
//    @BindView(R.id.iv_quanxuan)
//    ImageView ivQuanxuan;
    private Context mContext;
    private List<CaiGouDanBean.ListBean> caigoudan = new ArrayList<>();
    private ConfirmDialog confirmDialog;
    //    private ShenPiShangPinAdapter1 shangpinadapter;
    private ShenPiLevelOneAdapter adapter;
    private PopupWindow mPopWindow;
    private HashMap<String, ShangpinidAndDianpuidBean> xuanzhong = new HashMap<>();
    private int item_position;
    private String purchase_id;
    private LinearLayoutManager manager;
    private boolean isClick = true;
    List listBeanLevel = new ArrayList();

    //    List<MultiItemEntity> data;
    @Override
    public int getLayoutId() {
        return R.layout.activity_shenpi;
    }

    @Override
    protected void initData() {
        mContext = ShenPiActivity.this;
        tvTitle.setText("审批");
        tvRight.setText("添加商品");
        purchase_id = getIntent().getStringExtra("data");
        caigoudan = gson.fromJson(purchase_id, CaiGouDanBean.class).getList();//采购单一级数据
        adapter = new ShenPiLevelOneAdapter(ShenPiActivity.this, caigoudan);
        if(gson.fromJson(purchase_id,CaiGouDanBean.class).getOrder_audit_state().equals("903")){
            isClick = false;
            adapter.setClick(isClick);
        }
        xuanzhong = new HashMap();
        for (int i = 0; i < caigoudan.size(); i++) {
            ShangpinidAndDianpuidBean bean = new ShangpinidAndDianpuidBean();
            bean.setCommodity_id("");
            bean.setCompany_id("");
            bean.setDanjia("");
            xuanzhong.put(caigoudan.get(i).getSon_order_id(), bean);
        }
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        adapter.setCallBack(new ShenPiLevelOneAdapter.CallBack() {
            @Override
            public void isClick(View view, final int position) {
                if(isClick){
                    switch (view.getId()) {
                        case R.id.iv_shanchu://删除当前采购单
                            //不能全删
                            if (caigoudan.size() == 1) {
                                ToastUtil.showToast("不能全部删除");
                                return;
                            }
                            confirmDialog.showDialog("是否确定删除此采购单");
                            confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Log.e("listBean.getSon_order_id()", caigoudan.get(position).getSon_order_id() + "===");
                                    HttpManager.getInstance()
                                            .with(mContext)
                                            .setObservable(
                                                    RetrofitManager
                                                            .getService()
                                                            .delsonorderid(PreferenceUtils.getString(MyApplication.mContext, "token", ""), caigoudan.get(position).getSon_order_id()))
                                            .setDataListener(new HttpDataListener<String>() {
                                                @Override
                                                public void onNext(String data) {
                                                    Log.e("data", data + "(＾－＾)V");
                                                    ToastUtil.showToast("删除成功");
                                                    confirmDialog.dismiss();
                                                    //在存储商品id 的map中删掉当前商品
                                                    for (int i = 0; i < caigoudan.size(); i++) {
                                                        if (caigoudan.get(position).getSon_order_id().equals(caigoudan.get(i).getSon_order_id())) {
                                                            xuanzhong.remove(caigoudan.get(position).getSon_order_id());
                                                            caigoudan.remove(i);
                                                            adapter.notifyDataSetChanged();
                                                            delViewShow();
                                                            break;

                                                        }
                                                    }
                                                }
                                            }, false);
                                }
                            });
                            confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    confirmDialog.dismiss();
                                }
                            });
                            break;
                        case R.id.iv_xiugai:
                            //修改
                            CaiGouDanXiuGaiDailog danXiuGaiDailog = new CaiGouDanXiuGaiDailog();
                            danXiuGaiDailog.setInitStr(caigoudan.get(position).getClassify_name(), caigoudan.get(position).getPack_standard_name(), caigoudan.get(position).getPack_standard_id(),
                                    caigoudan.get(position).getSpecial_commodity(), String.valueOf(caigoudan.get(position).getCount()), caigoudan.get(position).getSon_order_id(), caigoudan.get(position).getSort_id())
                                    .setCallBack(new CaiGouDanXiuGaiDailog.CallBack() {
                                        @Override
                                        public void confirm(CaiGouDanBean.ListBean msg) {
                                            ToastUtil.showToast("修改成功");
//                                        for (int i = 0;i<shangpinadapter.getLevel0size();i++){
//                                            shangpinadapter.collapse(i);
//                                        }
                                            caigoudan.set(position, msg);
                                            ShangpinidAndDianpuidBean bean = new ShangpinidAndDianpuidBean();
                                            bean.setCommodity_id("");
                                            bean.setCompany_id("");
                                            bean.setDanjia("");
                                            xuanzhong.put(caigoudan.get(position).getSon_order_id(), bean);
                                            adapter.notifyDataSetChanged();
                                        }
                                    }).show(getSupportFragmentManager());
                            break;
                        case R.id.ll_lishi://打开历史记录   从历史记录中选择商品换到当前位置
                            final List<CaiGouDanBean.ListBean.CcListBeanLevel> level = new ArrayList<CaiGouDanBean.ListBean.CcListBeanLevel>();
                            if (caigoudan.get(position).isNeedLoad()) {
                                ToastUtil.showToast("正在匹配商家...");
                                getshenpi(caigoudan.get(position), position, false);
                                return;
                            }
                            for (int i = 0; i < 5; i++) {
                                CaiGouDanBean.ListBean.CcListBeanLevel subitem = caigoudan.get(position).getLevels().get(i);
                                Log.e("subitem", gson.toJson(subitem) + "=");
                                level.add(subitem);
                            }
                            HttpManager.getInstance()
                                    .with(mContext)
                                    .setObservable(
                                            RetrofitManager
                                                    .getService()
                                                    .getlishi(PreferenceUtils.getString(MyApplication.mContext, "token", ""), "1", caigoudan.get(position).getSon_order_id()))
                                    .setDataListener(new HttpDataListener<LiShiJiLuBean>() {
                                        @Override
                                        public void onNext(LiShiJiLuBean data) {
                                            new LiShiJiLuDialog()
                                                    .setSon_order_id(data.getOreder_buy(), data.getCsList())
                                                    .setCallBack(new LiShiJiLuDialog.CallBack() {
                                                        @Override
                                                        public void xuanzhong(String lujing, XiTongTuiJianBean.CcListBean msg) {
                                                            msg.setIsxianshi(true);
                                                            msg.setSon_order_id(caigoudan.get(position).getSon_order_id());
                                                            //通过选择记录的类型  放入固定位置
                                                            if ("goumai".equals(lujing)) {
                                                                msg.setBiaoqian("已购店铺");
                                                                level.get(3).setCcListBean(msg);
                                                            } else if ("shoucang".equals(lujing)) {
                                                                msg.setBiaoqian("收藏店铺");
                                                                Log.e("msgmsgmsg", gson.toJson(msg) + "=");
                                                                level.get(4).setCcListBean(msg);
                                                            }
                                                            caigoudan.get(position).setLevels(level);
                                                            if (caigoudan.get(position).getLevels().size() != 0) {
                                                                adapter.setShow(position);
                                                            } else {
                                                            }
                                                            adapter.notifyDataSetChanged();
                                                        }
                                                    }).show(getSupportFragmentManager());
                                        }
                                    });
                            break;
                    }
                } else {
                    ToastUtil.showToast("暂无此权限");
                }

            }
        });
        manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rvShenpi.setLayoutManager(manager);
        rvShenpi.setFocusable(true);
        rvShenpi.setFocusableInTouchMode(true);
        rvShenpi.setNestedScrollingEnabled(false);
        rvShenpi.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    //获取总价
    private void zongjia(String son_order_id, String commodity_id, final TextView tv) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getcaigoudanjiage(PreferenceUtils.getString(MyApplication.mContext, "token", ""), son_order_id, commodity_id))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        Log.e("data", data + "---");
                        tv.setText(data);
                    }
                });
    }

    //全选接口   返回的是全选后选择商品list
    private void setquanxuan(String market_id, String purchase_id, final String type) {
        Log.e("canshu", PreferenceUtils.getString(MyApplication.mContext, "token", "") + "-" + market_id + "-" + purchase_id + "-" + type);
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .shenpitongguo(PreferenceUtils.getString(MyApplication.mContext, "token", ""), "1", purchase_id, type))
                .setDataListener(new HttpDataListener<ShenPiQuanXuanBean>() {
                    @Override
                    public void onNext(ShenPiQuanXuanBean data) {
                        //通过选中的商品list中的getSon_order_id和采购单的getSon_order_id 对比  把选中的商品存到map中
                        List<ShenPiQuanXuanBean.ListBean> datalist = data.getList();
                        for (int i = 0; i < caigoudan.size(); i++) {
                            for (int j = 0; j < datalist.size(); j++) {
                                Log.e("caigoudan.get(i).getSon_order_id()", caigoudan.get(i).getSon_order_id() + "==");
                                Log.e("datalist.get(j).getSon_order_id()", datalist.get(j).getSon_order_id() + "==");
                                if (caigoudan.get(i).getSon_order_id().equals(datalist.get(j).getSon_order_id())) {
                                    Log.e("getCommodity_id", datalist.get(j).getCommodity_id() + "---");
                                    ShangpinidAndDianpuidBean bean = new ShangpinidAndDianpuidBean();
                                    bean.setCommodity_id(datalist.get(j).getCommodity_id());
                                    bean.setCompany_id(datalist.get(j).getCompany_id());
                                    bean.setDanjia(datalist.get(j).getPrice());
                                    xuanzhong.put(caigoudan.get(i).getSon_order_id(), bean);
                                    break;
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
                        Log.e("commodity_id", commodity_id + "---");
                        Log.e("son_order_id", son_order_id + "---");
                        zongjia(son_order_id, commodity_id, tvZongjia);
                        //通过传入的type确定选中的选项  显示在页面上
//                        switch (Integer.parseInt(type)){
//                            case CaiGouDanBean.TYPE_ONE:
//                                if (mPopWindow.isShowing()){
//                                    tvBiaoqian.setText("价格最低");
//                                    ivQuanxuan.setSelected(true);
//                                    mPopWindow.dismiss();
//                                }
//                                break;
//                            case CaiGouDanBean.TYPE_TWO:
//                                if (mPopWindow.isShowing()){
//                                    tvBiaoqian.setText("销量最高");
//                                    ivQuanxuan.setSelected(true);
//                                    mPopWindow.dismiss();
//                                }
//                                break;
//                            case CaiGouDanBean.TYPE_THREE:
//                                if (mPopWindow.isShowing()){
//                                    tvBiaoqian.setText("评分最高");
//                                    ivQuanxuan.setSelected(true);
//                                    mPopWindow.dismiss();
//                                }
//                                break;
//                        }
                        adapter.notifyDataSetChanged();
                        Log.e("xuanzhong-activity", gson.toJson(xuanzhong));
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

    //获取子采购单的系统推荐数据   2级---
    public void getshenpi(final CaiGouDanBean.ListBean listBean, final int pos, final boolean isgangkaishi) {
//        final int index = data.indexOf(listBean);
        Log.e("getshenpi()getSon_order_id", listBean.getMarket_id() + "---" + listBean.getSon_order_id());
        Log.e("这是我的当前位置", pos + "---");
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getshenpi(PreferenceUtils.getString(MyApplication.mContext, "token", ""), listBean.getMarket_id(), listBean.getSon_order_id()))
                .setDataListener(new HttpDataListener<XiTongTuiJianBean>() {
                    @Override
                    public void onNext(XiTongTuiJianBean list) {
                        listBeanLevel.clear();
                        List<XiTongTuiJianBean.CcListBean> pflist = list.getPflist();   //评分
                        //避免空选项和收藏记录购买记录没有位置放  以及找不到位置放  提前new出来  add进去
                        if (pflist != null && pflist.size() > 0) {
                            XiTongTuiJianBean.CcListBean pingfen = list.getPflist().get(0);
                            pingfen.setBiaoqian("评分最高");
                            listBeanLevel.add(new CaiGouDanBean.ListBean.CcListBeanLevel(TYPE_THREE, pingfen));
                        } else {
                            listBeanLevel.add(new CaiGouDanBean.ListBean.CcListBeanLevel(TYPE_THREE, new XiTongTuiJianBean.CcListBean()));
                        }
                        List<XiTongTuiJianBean.CcListBean> commodity_sales = list.getCommodity_sales(); //销量
                        if (commodity_sales != null && commodity_sales.size() > 0) {
                            XiTongTuiJianBean.CcListBean xiaoliang = list.getCommodity_sales().get(0);
                            Log.e("xiaoliang", xiaoliang.getSon_order_id());
                            xiaoliang.setBiaoqian("销量最高");
                            listBeanLevel.add(new CaiGouDanBean.ListBean.CcListBeanLevel(TYPE_ONE, xiaoliang));
                        } else {
                            listBeanLevel.add(new CaiGouDanBean.ListBean.CcListBeanLevel(TYPE_ONE, new XiTongTuiJianBean.CcListBean()));
                        }
                        List<XiTongTuiJianBean.CcListBean> pice = list.getPice();//价格
                        if (pice != null && pice.size() > 0) {
                            XiTongTuiJianBean.CcListBean jiage = list.getPice().get(0);
                            jiage.setBiaoqian("价格最低");
                            listBeanLevel.add(new CaiGouDanBean.ListBean.CcListBeanLevel(TYPE_TWO, jiage));
                        } else {
                            listBeanLevel.add(new CaiGouDanBean.ListBean.CcListBeanLevel(TYPE_TWO, new XiTongTuiJianBean.CcListBean()));
                        }
                        listBeanLevel.add(new CaiGouDanBean.ListBean.CcListBeanLevel(TYPE_FOUR, new XiTongTuiJianBean.CcListBean()));
                        listBeanLevel.add(new CaiGouDanBean.ListBean.CcListBeanLevel(TYPE_FIVE, new XiTongTuiJianBean.CcListBean()));

                        listBean.setLevels(listBeanLevel);

                        //获取之后  改成不需要加载状态
                        listBean.setNeedLoad(false);
                        if (listBean.getLevels().size() != 0) {
//                            shangpinadapter.expand(pos);
//                            shangpinadapter.notifyDataSetChanged();
                        }
                        if (listBean.isSpecial()) {
                            if (pflist != null && pflist.size() > 0) {
                            } else {
                                //没有系统推荐   继续加载
                                listBean.setNeedLoad(true);
                            }
                        }
//                        shangpinadapter.replaceData(data);
//                        shangpinadapter.notifyDataSetChanged();
//                        adapter.notifyDataSetChanged();
//                        if (isgangkaishi){
//                            return;
//                        }
                        if (listBean.getLevels().size() != 0) {
                            adapter.setShow(pos);
                            adapter.notifyDataSetChanged();
                        } else {
                            if (listBean.isSpecial()) {//特殊商品 特殊处理
                                ToastUtil.showToast("没有商家推送商品");
                            } else {
                                ToastUtil.showToast("该市场目前没有此商品");
                            }
                        }

                    }
                }, "正在获取数据...");

    }

    @OnClick({R.id.iv_back, R.id.tv_tijiao, R.id.tv_right, R.id.ll_select, R.id.ll_quanxuan, R.id.tv_biaoqian,
            R.id.tv_pingfenzuigao, R.id.tv_jiagezuidi})
    public void onViewClicked(View view) {
        CaiGouDanBean.ListBean listBean = caigoudan.get(0);
        if (view.getId()==R.id.iv_back){
                myBack();
        } else if(isClick){
            switch (view.getId()) {
                case R.id.tv_right:
                    //添加商品
                    CaiGouDanTianJiaDailog dailog = new CaiGouDanTianJiaDailog();
                    dailog.setInitStr(caigoudan.get(0).getPurchase_id(), caigoudan.get(0).getMarket_id())
                            .setCallBack(new CaiGouDanTianJiaDailog.CallBack() {
                                @Override
                                public void confirm(CaiGouDanBean.ListBean msg) {
                                    ToastUtil.showToast("添加成功");
//                                for (int i = 0;i<caigoudan.size();i++) {
//                                    shangpinadapter.collapse(i);
//                                }
                                    caigoudan.add(msg);
                                    adapter.addShow();
                                    ShangpinidAndDianpuidBean bean = new ShangpinidAndDianpuidBean();
                                    bean.setCommodity_id("");
                                    bean.setCompany_id("");
                                    bean.setDanjia("");
                                    tvZongjia.setText("0");
//                                ivQuanxuan.setSelected(false);
                                    xuanzhong.put(caigoudan.get(caigoudan.size() - 1).getSon_order_id(), bean);
                                    adapter.notifyDataSetChanged();
//                                shangpinadapter.addData(msg);
//                                getshenpi(caigoudan.get(caigoudan.size()-1),caigoudan.size()-1,false);
//                                shangpinadapter.notifyDataSetChanged();
//                                getList();
                                }
                            }).show(getSupportFragmentManager());

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
                                        if (caigoudan.get(i).getSon_order_id().equals(key)) {
                                            if (caigoudan.get(i).isSpecial()) {
                                                special_son_order_id += key + ",";
                                                special_commodity_id += value.getCommodity_id() + ",";
                                            }
                                        }
                                    }
                                }
                                if (special_son_order_id.length() != 0) {
                                    special_son_order_id = special_son_order_id.substring(0, special_son_order_id.length() - 1);
                                    special_commodity_id = special_commodity_id.substring(0, special_commodity_id.length() - 1);
                                    //如果存在特殊商品  并且已选择  更新抢单信息
                                    gengxinqiangdan(special_son_order_id, special_commodity_id);
                                }
//                                son_order_id=son_order_id.substring(0,son_order_id.length()-1);
//                                commodity_id=commodity_id.substring(0,commodity_id.length()-1);

                                intent.putExtra("son_order_id", son_order_id);
                                intent.putExtra("commodity_id", commodity_id);
                                intent.putExtra("lujingtype", "2");
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
//                if(isQuanXuan()!=null){
//                    xuanzhong.clear();
//                    shangpinadapter.notifyDataSetChanged();
//                    ivQuanxuan.setSelected(false);
//                    tvZongjia.setText("0.00");
//                } else {
//                    ToastUtil.showToast("请选择右边的标签");
//                }
                    break;
                case R.id.tv_biaoqian:
                    setShowColor();
                    setquanxuan(listBean.getMarket_id(), listBean.getPurchase_id(), CaiGouDanBean.TYPE_TWO + "");
                    tvBiaoqian.setTextColor(getResources().getColor(R.color.zangqing));
                    break;
                case R.id.tv_pingfenzuigao:
                    setShowColor();
                    setquanxuan(listBean.getMarket_id(), listBean.getPurchase_id(), CaiGouDanBean.TYPE_THREE + "");
                    tvPingfenzuigao.setTextColor(getResources().getColor(R.color.zangqing));
                    break;
                case R.id.tv_jiagezuidi:
                    setShowColor();
                    setquanxuan(listBean.getMarket_id(), listBean.getPurchase_id(), CaiGouDanBean.TYPE_ONE + "");
                    tvJiagezuidi.setTextColor(getResources().getColor(R.color.zangqing));
                    break;
            }
        } else {
            ToastUtil.showToast("暂无此权限");
        }

    }

    //更新抢单信息接口
    private void gengxinqiangdan(String special_son_order_id, String special_commodity_id) {
        Log.e("special_son_order_id", special_son_order_id + "==");
        Log.e("special_commodity_id", special_commodity_id + "==");
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .gengxinqiangdan(PreferenceUtils.getString(MyApplication.mContext, "token", ""), special_son_order_id, special_commodity_id))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        Log.e("data", data + "---");
                    }
                }, false);
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
        Intent intent = new Intent(ShenPiActivity.this, CaiGouDanActivity.class);
        startActivity(intent);
        finish();
    }

    public int getItem_position() {
        return item_position;
    }

    public void setViewShow(CaiGouDanBean.ListBean.CcListBeanLevel item) {//存储点击item,计算总价
//        CaiGouDanBean.CcListBeanLevel level = (CaiGouDanBean.CcListBeanLevel) item;
//        Log.e("点击",new Gson().toJson(level));
        int count = 0;
        String son_order_id = "";
        String commodity_id = "";
        Set<String> keys = xuanzhong.keySet();
        //存储选中的商品信息  更新adapter
        for (String key : keys) {
            Log.e("我的key", key);
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
            Log.e("commodity_id", commodity_id + "---");
            Log.e("son_order_id", son_order_id + "---");
            //调用接口获取总价
            zongjia(son_order_id, commodity_id, tvZongjia);
        } else {
            tvZongjia.setText("0");
        }

    }

    public void delViewShow() {//存储点击item,计算总价
//        CaiGouDanBean.CcListBeanLevel level = (CaiGouDanBean.CcListBeanLevel) item;
//        Log.e("点击",new Gson().toJson(level));
        int count = 0;
        String son_order_id = "";
        String commodity_id = "";
        Set<String> keys = xuanzhong.keySet();
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
            Log.e("commodity_id", commodity_id + "---");
            Log.e("son_order_id", son_order_id + "---");
            //调用接口获取总价
            zongjia(son_order_id, commodity_id, tvZongjia);
        } else {
            tvZongjia.setText("0");
        }

    }

    //    public void setMoreShangjia(XiTongTuiJianBean.CcListBean bean){
    public void setMoreShangjia() {
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

    public void setShowColor() {
        tvBiaoqian.setTextColor(getResources().getColor(R.color.zicolor));
        tvPingfenzuigao.setTextColor(getResources().getColor(R.color.zicolor));
        tvJiagezuidi.setTextColor(getResources().getColor(R.color.zicolor));
    }
}
