package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShenPiShangPinAdapter1;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.CaiGouDanTianJiaDailog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.CaiGouDanXiuGaiDailog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.GengDuoShangJiaDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.LiShiJiLuDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.JumpUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

import static android.widget.ListPopupWindow.MATCH_PARENT;
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
    SwipeMenuRecyclerView rvShenpi;
    @BindView(R.id.tv_zongjia)
    TextView tvZongjia;
    @BindView(R.id.tv_tijiao)
    TextView tvTijiao;
    @BindView(R.id.tv_biaoqian)
    TextView tvBiaoqian;
    @BindView(R.id.view_showpop)
    View viewShowpop;
    @BindView(R.id.ll_select)
    LinearLayout llSelect;
    @BindView(R.id.iv_quanxuan)
    ImageView ivQuanxuan;
    private Context mContext;
    private List<CaiGouDanBean.ListBean> caigoudan = new ArrayList<>();
    private ConfirmDialog confirmDialog;
    private ShenPiShangPinAdapter1 shangpinadapter;
    private PopupWindow mPopWindow;
    private HashMap<String,ShangpinidAndDianpuidBean> xuanzhong = new HashMap<>();
    private int item_position;
    private String purchase_id;
    List listBeanLevel = new ArrayList();
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
//        xuanzhong=new HashMap();
        //存储选中商品的商品id 店铺id  单价
//        for (int i = 0; i < caigoudan.size(); i++) {
//            ShangpinidAndDianpuidBean bean = new ShangpinidAndDianpuidBean();
//            bean.setCommodity_id("");
//            bean.setCompany_id("");
//            bean.setDanjia("");
//            xuanzhong.put(caigoudan.get(i).getSon_order_id(),bean);
//            Log.e("caigoudan"+i,caigoudan.get(i).getSon_order_id()+"--");
//            getshenpi(caigoudan.get(i),i,true);
//        }
        List<MultiItemEntity> data = new ArrayList<>();
        for (int i = 0; i < caigoudan.size(); i++) {
            CaiGouDanBean.ListBean item0 = caigoudan.get(i);
            ShangpinidAndDianpuidBean bean = new ShangpinidAndDianpuidBean();
            bean.setCommodity_id("");
            bean.setCompany_id("");
            bean.setDanjia("");
            xuanzhong.put(caigoudan.get(i).getSon_order_id(),bean);
            if(item0.getLevels() != null && item0.getLevels().size()>0) {
                for (int j = 0; j < item0.getLevels().size(); j++) {
                    CaiGouDanBean.CcListBeanLevel item1 = item0.getLevels().get(j);;
                    item0.addSubItem(item1);
                }
            }
            data.add(item0);
        }
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        shangpinadapter = new ShenPiShangPinAdapter1(this);

//        shangpinadapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                item_position = position;
//                //判断点击的是一级还是二级
//                if (adapter.getItemViewType(position) == CaiGouDanBean.ListBean.Level_0) {
//                    final CaiGouDanBean.ListBean listBean = (CaiGouDanBean.ListBean) adapter.getItem(position);
//
//                }
//            }
//        });

        shangpinadapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                switch (view.getId()) {
                    case R.id.bt_xiangqing://查看商品详情
                        CaiGouDanBean.CcListBeanLevel item = (CaiGouDanBean.CcListBeanLevel) adapter.getItem(position);
                        XiTongTuiJianBean.CcListBean ccListBean = item.getCcListBean();
                        Bundle bundle = new Bundle();
                        bundle.putString("spid", ccListBean.getCommodity_id());
                        Log.e("spid", ccListBean.getCommodity_id());
                        JumpUtil.Jump_intent(mContext, SPXiangQingActivity.class, bundle);
                        break;
                    case R.id.bt_shangjia://点击更多商家   把选中的商品换到当前位置
                        final CaiGouDanBean.CcListBeanLevel shangjia = (CaiGouDanBean.CcListBeanLevel) adapter.getItem(position);
                        XiTongTuiJianBean.CcListBean shangjiabean = shangjia.getCcListBean();
                        new GengDuoShangJiaDialog()
                                .setId(shangjiabean.getSon_order_id(),shangjiabean.getMarket_id())
                                .setCallBack(new GengDuoShangJiaDialog.CallBack() {
                                    @Override
                                    public void xuanzhong(XiTongTuiJianBean.CcListBean msg) {
                                        msg.setIsxianshi(false);
                                        shangjia.setCcListBean(msg);
                                        adapter.setData(position, shangjia);
                                        Log.e("dada",new Gson().toJson(shangjia));
                                    }
                                })
                                .show(getSupportFragmentManager());
                        break;
                    case R.id.iv_shanchu://删除当前采购单
                        final CaiGouDanBean.ListBean delcaigoudan = (CaiGouDanBean.ListBean) adapter.getItem(position);
                        //不能全删
                        if (caigoudan.size()==1){
                            ToastUtil.showToast("不能全部删除");
                            return ;
                        }
                        confirmDialog.showDialog("是否确定删除此采购单");
                        confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.e("listBean.getSon_order_id()",delcaigoudan.getSon_order_id()+"===");
                                HttpManager.getInstance()
                                        .with(mContext)
                                        .setObservable(
                                                RetrofitManager
                                                        .getService()
                                                        .delsonorderid(PreferenceUtils.getString(MyApplication.mContext, "token",""),delcaigoudan.getSon_order_id()))
                                        .setDataListener(new HttpDataListener<String>() {
                                            @Override
                                            public void onNext(String data) {
                                                Log.e("data",data+"(＾－＾)V");
                                                ToastUtil.showToast("删除成功");
                                                confirmDialog.dismiss();
                                                shangpinadapter.remove(position);
                                                //在存储商品id 的map中删掉当前商品
                                                for (int i = 0; i < caigoudan.size(); i++) {
                                                    if (delcaigoudan.getSon_order_id().equals(caigoudan.get(i).getSon_order_id())){
                                                        xuanzhong.remove(delcaigoudan.getSon_order_id());
                                                        caigoudan.remove(i);
                                                        break;
                                                    }
                                                }
                                                shangpinadapter.notifyDataSetChanged();
                                            }
                                        },false);
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
                        CaiGouDanBean.ListBean bean = (CaiGouDanBean.ListBean) shangpinadapter.getData().get(position);
                        danXiuGaiDailog.setInitStr(bean.getClassify_name(),bean.getPack_standard_name(),bean.getPack_standard_id(),
                                bean.getSpecial_commodity(),String.valueOf(bean.getCount()),bean.getSon_order_id(),bean.getSort_id())
                        .setCallBack(new CaiGouDanXiuGaiDailog.CallBack() {
                            @Override
                            public void confirm(CaiGouDanBean.ListBean msg) {
                                ToastUtil.showToast("修改成功");
                                for (int i = 0;i<shangpinadapter.getLevel0size();i++){
                                    shangpinadapter.collapse(i);
                                }
                                caigoudan.set(position,msg);
                                ShangpinidAndDianpuidBean bean = new ShangpinidAndDianpuidBean();
                                bean.setCommodity_id("");
                                bean.setCompany_id("");
                                bean.setDanjia("");
                                xuanzhong.put(caigoudan.get(position).getSon_order_id(),bean);
                                shangpinadapter.setData(position,msg);
                                getshenpi(caigoudan.get(position),position,true);
                                shangpinadapter.notifyDataSetChanged();
//                                getList();
                            }
                        }).show(getSupportFragmentManager());
                        break;
                    case R.id.ll_lishi://打开历史记录   从历史记录中选择商品换到当前位置
                        final CaiGouDanBean.ListBean listBean = (CaiGouDanBean.ListBean) adapter.getItem(position);
                        final List<CaiGouDanBean.CcListBeanLevel> level=new ArrayList<CaiGouDanBean.CcListBeanLevel>();
                        if (listBean.isNeedLoad()) {
                            getshenpi(listBean, position,false);
//                            ToastUtil.showToast("请先打开系统推荐");
                            return;
                        } else {
//                            if (listBean.isExpanded()) {
//                                adapter.collapse(position);
//                            } else {
//                                adapter.expand(position);
//                            }
                        }
                        for (int i = 0; i < 5; i++) {
                            CaiGouDanBean.CcListBeanLevel subitem = listBean.getSubItem(i);
                            Log.e("subitem",gson.toJson(subitem)+"=");
                            level.add(subitem);
                        }
                        HttpManager.getInstance()
                                .with(mContext)
                                .setObservable(
                                        RetrofitManager
                                                .getService()
                                                .getlishi(PreferenceUtils.getString(MyApplication.mContext, "token",""), "1", listBean.getSon_order_id()))
                                .setDataListener(new HttpDataListener<LiShiJiLuBean>() {
                                    @Override
                                    public void onNext(LiShiJiLuBean data) {
                                        new LiShiJiLuDialog()
                                                .setSon_order_id(data.getOreder_buy(),data.getCsList())
                                                .setCallBack(new LiShiJiLuDialog.CallBack() {
                                                    @Override
                                                    public void xuanzhong(String lujing,XiTongTuiJianBean.CcListBean msg){
                                                        msg.setIsxianshi(true);
                                                        msg.setSon_order_id(listBean.getSon_order_id());
                                                        //通过选择记录的类型  放入固定位置
                                                        if ("goumai".equals(lujing)) {
                                                            msg.setBiaoqian("购买记录");
                                                            level.get(3).setCcListBean(msg);
                                                        } else if ("shoucang".equals(lujing)) {
                                                            msg.setBiaoqian("收藏记录");
                                                            Log.e("msgmsgmsg",gson.toJson(msg)+"=");
                                                            level.get(4).setCcListBean(msg);
                                                        }
                                                        listBean.setSubItems(level);
                                                        if (listBean.hasSubItem()) {
                                                            adapter.expand(position);
                                                        } else {
                                                        }
                                                        adapter.notifyDataSetChanged();
                                                    }
                                                }).show(getSupportFragmentManager());
                                    }
                                });
                        break;
                }
            }
        });
        rvShenpi.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvShenpi.setNestedScrollingEnabled(false);
        rvShenpi.setAdapter(shangpinadapter);
        shangpinadapter.addData(caigoudan);
//        getList();
    }
//获取总价
    private void zongjia(String son_order_id,String commodity_id) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getcaigoudanjiage(PreferenceUtils.getString(MyApplication.mContext, "token",""),son_order_id,commodity_id))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        Log.e("data",data+"---");
                        tvZongjia.setText(data);
                    }
                });
    }


    //PopupWindow  三个选项的pop   价格最低  评分最高  销量最高
    private void showPopupWindow() {
        MultiItemEntity mu = shangpinadapter.getItem(0);
        CaiGouDanBean.ListBean listBean = null;
           
        if (mu.getItemType()==CaiGouDanBean.ListBean.Level_0){
             listBean = (CaiGouDanBean.ListBean)mu;
        }
        
        View view = View.inflate(mContext, R.layout.pop_xuanzebiaoqian, null);
        mPopWindow = new PopupWindow(view);
        int width = getWindowManager().getDefaultDisplay().getWidth();
        mPopWindow.setWidth(width * 2 / 6);
        mPopWindow.setHeight(AppUtil.dip2px(160));

        TextView tv_xiaoliangzuigao =  (TextView)view.findViewById(R.id.tv_xiaoliangzuigao);
        TextView tv_pingfenzuigao = (TextView) view.findViewById(R.id.tv_pingfenzuigao);
        TextView tv_jiagezuidi = (TextView) view.findViewById(R.id.tv_jiagezuidi);
        final Set<String> keys = xuanzhong.keySet();
        final CaiGouDanBean.ListBean finalListBean = listBean;
        tv_xiaoliangzuigao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //全选销量最高
                Log.e("woyaode id",finalListBean.getPurchase_id());
                setquanxuan(finalListBean.getMarket_id(), finalListBean.getPurchase_id(),CaiGouDanBean.TYPE_TWO+"");
            }
        });
        tv_pingfenzuigao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //全选评分最高
                setquanxuan(finalListBean.getMarket_id(), finalListBean.getPurchase_id(),CaiGouDanBean.TYPE_THREE+"");
            }
        });
        tv_jiagezuidi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //全选评分最高
                setquanxuan(finalListBean.getMarket_id(), finalListBean.getPurchase_id(),CaiGouDanBean.TYPE_ONE+"");
            }
        });
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.showAsDropDown(viewShowpop);

    }
    //全选接口   返回的是全选后选择商品list
    private void setquanxuan(String market_id, String purchase_id, final String type) {
        Log.e("canshu",PreferenceUtils.getString(MyApplication.mContext, "token","")+"-"+market_id+"-"+purchase_id+"-"+type);
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .shenpitongguo(PreferenceUtils.getString(MyApplication.mContext, "token",""),"1",purchase_id,type))
                .setDataListener(new HttpDataListener<ShenPiQuanXuanBean>() {
                    @Override
                    public void onNext(ShenPiQuanXuanBean data) {
                        //通过选中的商品list中的getSon_order_id和采购单的getSon_order_id 对比  把选中的商品存到map中
                        List<ShenPiQuanXuanBean.ListBean> datalist = data.getList();
                        for (int i = 0; i <caigoudan.size() ; i++) {
                            for (int j = 0; j < datalist.size(); j++) {
                                Log.e("caigoudan.get(i).getSon_order_id()",caigoudan.get(i).getSon_order_id()+"==");
                                Log.e("datalist.get(j).getSon_order_id()",datalist.get(j).getSon_order_id()+"==");
                                if (caigoudan.get(i).getSon_order_id().equals(datalist.get(j).getSon_order_id())){
                                    Log.e("getCommodity_id",datalist.get(j).getCommodity_id()+"---");
                                    ShangpinidAndDianpuidBean bean=new ShangpinidAndDianpuidBean();
                                    bean.setCommodity_id(datalist.get(j).getCommodity_id());
                                    bean.setCompany_id(datalist.get(j).getCompany_id());
                                    bean.setDanjia(datalist.get(j).getPrice());
                                    xuanzhong.put(caigoudan.get(i).getSon_order_id(),bean);
                                    break;
                                }
                            }
                        }
                        //拼接商品id获取总价
                        Set<String> mapkey = xuanzhong.keySet();
                        String son_order_id="";
                        String commodity_id="";
                        for (String key:mapkey) {
                            ShangpinidAndDianpuidBean value = xuanzhong.get(key);
                            if (value.getCommodity_id().isEmpty()){
                            }else{
                                son_order_id+=key+",";
                                commodity_id+=value.getCommodity_id()+",";
                            }
                        }
                        son_order_id=son_order_id.substring(0,son_order_id.length()-1);
                        commodity_id=commodity_id.substring(0,commodity_id.length()-1);
                        Log.e("commodity_id",commodity_id+"---");
                        Log.e("son_order_id",son_order_id+"---");
                        zongjia(son_order_id,commodity_id);
                        shangpinadapter.notifyDataSetChanged();
                        //通过传入的type确定选中的选项  显示在页面上
                        switch (Integer.parseInt(type)){
                            case CaiGouDanBean.TYPE_ONE:
                                if (mPopWindow.isShowing()){
                                    tvBiaoqian.setText("价格最低");
                                    ivQuanxuan.setSelected(true);
                                    mPopWindow.dismiss();
                                }
                                break;
                            case CaiGouDanBean.TYPE_TWO:
                                if (mPopWindow.isShowing()){
                                    tvBiaoqian.setText("销量最高");
                                    ivQuanxuan.setSelected(true);
                                    mPopWindow.dismiss();
                                }
                                break;
                            case CaiGouDanBean.TYPE_THREE:
                                if (mPopWindow.isShowing()){
                                    tvBiaoqian.setText("评分最高");
                                    ivQuanxuan.setSelected(true);
                                    mPopWindow.dismiss();
                                }
                                break;
                        }
                        Log.e("xuanzhong-activity",gson.toJson(xuanzhong));
                    }
                });
    }

    //判断是否全选了商品
    private HashMap<String,String> isQuanXuan(){
        Set<String> keys = xuanzhong.keySet();
        HashMap<String,String> map=new HashMap();
        for (String key:keys) {
            ShangpinidAndDianpuidBean value = xuanzhong.get(key);
            if (value.getCommodity_id().isEmpty()){
                return null;
            }else{
                map.put(key,value.getCommodity_id());
            }
        }
        return map;
    }
    //获取子采购单的系统推荐数据
    public void getshenpi(final CaiGouDanBean.ListBean listBean, final int pos, final boolean isgangkaishi) {
        Log.e("getshenpi()getSon_order_id", listBean.getMarket_id()+"---"+listBean.getSon_order_id());
        Log.e("这是我的当前位置",pos+"---");
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getshenpi(PreferenceUtils.getString(MyApplication.mContext, "token",""), listBean.getMarket_id(), listBean.getSon_order_id()))
                .setDataListener(new HttpDataListener<XiTongTuiJianBean>() {
                    @Override
                    public void onNext(XiTongTuiJianBean list) {
                        listBeanLevel.clear();
                        List<XiTongTuiJianBean.CcListBean> pflist = list.getPflist();   //评分
                        //避免空选项和收藏记录购买记录没有位置放  以及找不到位置放  提前new出来  add进去
                        if (pflist != null && pflist.size() > 0) {
                            XiTongTuiJianBean.CcListBean pingfen = list.getPflist().get(0);
                            pingfen.setBiaoqian("评分最高");
                            listBeanLevel.add( new CaiGouDanBean.CcListBeanLevel(TYPE_THREE, pingfen));
                        }else{
                            listBeanLevel.add(new CaiGouDanBean.CcListBeanLevel(TYPE_THREE, new XiTongTuiJianBean.CcListBean()));
                        }
                        List<XiTongTuiJianBean.CcListBean> commodity_sales = list.getCommodity_sales(); //销量
                        if (commodity_sales != null && commodity_sales.size() > 0) {
                            XiTongTuiJianBean.CcListBean xiaoliang = list.getCommodity_sales().get(0);
                            Log.e("xiaoliang",xiaoliang.getSon_order_id());
                            xiaoliang.setBiaoqian("销量最高");
                            listBeanLevel.add(new CaiGouDanBean.CcListBeanLevel(TYPE_ONE,xiaoliang));
                        }else{
                            listBeanLevel.add(new CaiGouDanBean.CcListBeanLevel(TYPE_ONE, new XiTongTuiJianBean.CcListBean()));
                        }
                        List<XiTongTuiJianBean.CcListBean> pice = list.getPice();//价格
                        if (pice != null && pice.size() > 0) {
                            XiTongTuiJianBean.CcListBean jiage = list.getPice().get(0);
                            jiage.setBiaoqian("价格最低");
                            listBeanLevel.add(new CaiGouDanBean.CcListBeanLevel(TYPE_TWO, jiage));
                        }else{
                            listBeanLevel.add(new CaiGouDanBean.CcListBeanLevel(TYPE_TWO, new XiTongTuiJianBean.CcListBean()));
                        }
                        listBeanLevel.add(new CaiGouDanBean.CcListBeanLevel(TYPE_FOUR, new XiTongTuiJianBean.CcListBean()));
                        listBeanLevel.add(new CaiGouDanBean.CcListBeanLevel(TYPE_FIVE, new XiTongTuiJianBean.CcListBean()));

                        listBean.setSubItems(listBeanLevel);

                        //获取之后  改成不需要加载状态
                        listBean.setNeedLoad(false);
                        if (listBean.isSpecial()){
                            if (pflist != null && pflist.size() > 0){
                            }else{
                                //没有系统推荐   继续加载
                                listBean.setNeedLoad(true);
                            }
                        }

                        shangpinadapter.notifyDataSetChanged();
                        if (isgangkaishi){
                            return;
                        }
                        if (listBean.hasSubItem()) {
                            shangpinadapter.expand(pos);
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

    @OnClick({R.id.iv_back, R.id.tv_tijiao, R.id.tv_right, R.id.ll_select,R.id.ll_quanxuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                myBack();
                break;
            case R.id.tv_right:
                //添加商品
                CaiGouDanTianJiaDailog dailog = new CaiGouDanTianJiaDailog();
                dailog.setInitStr(caigoudan.get(0).getPurchase_id(),caigoudan.get(0).getMarket_id())
                        .setCallBack(new CaiGouDanTianJiaDailog.CallBack() {
                            @Override
                            public void confirm(CaiGouDanBean.ListBean msg) {
                                ToastUtil.showToast("添加成功");
                                for (int i = 0;i<caigoudan.size();i++) {
                                    shangpinadapter.collapse(i);
                                }
                                caigoudan.add(msg);
                                ShangpinidAndDianpuidBean bean = new ShangpinidAndDianpuidBean();
                                bean.setCommodity_id("");
                                bean.setCompany_id("");
                                bean.setDanjia("");
                                xuanzhong.put(caigoudan.get(caigoudan.size()-1).getSon_order_id(),bean);
                                shangpinadapter.addData(msg);
                                getshenpi(caigoudan.get(caigoudan.size()-1),caigoudan.size()-1,true);
                                shangpinadapter.notifyDataSetChanged();
//                                getList();
                            }
                        }).show(getSupportFragmentManager());

                break;
            case R.id.tv_tijiao:
                //提交按钮
                    if (isQuanXuan()!=null){//全选了   拼接   跳转 确认订单
                        confirmDialog.showDialog("确认审批通过即发布商品成功");
                        confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent(mContext,QueRenDingDanActivity.class);
                                Set<String> mapkey = xuanzhong.keySet();
                                String son_order_id = "";
                                String commodity_id="";
                                String company_id="";
                                String special_son_order_id="";
                                String special_commodity_id="";
                                for (String key:mapkey) {
                                    ShangpinidAndDianpuidBean value = xuanzhong.get(key);
                                    son_order_id+=key+",";

                                    commodity_id+=value.getCommodity_id()+",";
                                    company_id += value.getCompany_id() + ",";
//
                                    for (int i = 0; i <caigoudan.size() ; i++) {
                                        if (caigoudan.get(i).getSon_order_id().equals(key)){
                                            if (caigoudan.get(i).isSpecial()){
                                                special_son_order_id+=key+",";
                                                special_commodity_id+=value.getCommodity_id()+",";
                                            }
                                        }
                                    }
                                }
                                if (special_son_order_id.length()!=0){
                                    special_son_order_id=special_son_order_id.substring(0,special_son_order_id.length()-1);
                                    special_commodity_id=special_commodity_id.substring(0,special_commodity_id.length()-1);
                                    //如果存在特殊商品  并且已选择  更新抢单信息
                                    gengxinqiangdan(special_son_order_id,special_commodity_id);
                                }
//                                son_order_id=son_order_id.substring(0,son_order_id.length()-1);
//                                commodity_id=commodity_id.substring(0,commodity_id.length()-1);

                                    intent.putExtra("son_order_id",son_order_id);
                                    intent.putExtra("commodity_id",commodity_id);
                                    intent.putExtra("lujingtype","2");
                                    intent.putExtra("company_id",company_id);
                                    intent.putExtra("zongjia",tvZongjia.getText().toString());
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
                    }else{
                        ToastUtil.showToast("商品还未全部选择无法提交订单");
                    }
                break;
            case R.id.ll_select:
                showPopupWindow();
                break;
            case R.id.ll_quanxuan://点击全选按钮
                if(isQuanXuan()!=null){
                    xuanzhong.clear();
//                    shangpinadapter.notifyDataSetChanged();
                    ivQuanxuan.setSelected(false);
                    tvZongjia.setText("0.00");
                } else {
                    ToastUtil.showToast("请选择右边的标签");
                }
                break;
        }
    }
//更新抢单信息接口
    private void gengxinqiangdan(String special_son_order_id,String special_commodity_id){
        Log.e("special_son_order_id",special_son_order_id+"==");
        Log.e("special_commodity_id",special_commodity_id+"==");
         HttpManager.getInstance()
         .with(mContext)
                .setObservable(
            RetrofitManager
                    .getService()
                    .gengxinqiangdan(PreferenceUtils.getString(MyApplication.mContext, "token",""),special_son_order_id,special_commodity_id))
            .setDataListener(new HttpDataListener<String>() {
        @Override
        public void onNext(String data) {
            Log.e("data",data+"---");
        }
        },false);
    }
    //获取单条数据
    private void getList(){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getShenpiItem(purchase_id))
                .setDataListener(new HttpDataListener<List<CaiGouDanBean.ListBean>>() {
                    @Override
                    public void onNext(List<CaiGouDanBean.ListBean> data) {
                        caigoudan.clear();
                        caigoudan.addAll(data);
                        //存储选中商品的商品id 店铺id  单价
                        for (int i = caigoudan.size(); i < data.size(); i++) {
                            ShangpinidAndDianpuidBean bean = new ShangpinidAndDianpuidBean();
                            bean.setCommodity_id("");
                            bean.setCompany_id("");
                            bean.setDanjia("");
                            xuanzhong.put(data.get(i).getSon_order_id(),bean);
                            Log.e("caigoudan"+i,data.get(i).getSon_order_id()+"--");
                            getshenpi(data.get(i),i,true);
                        } 
//                        shangpinadapter.setNewData(caigoudan.get(0));
                        shangpinadapter.addData(caigoudan);
                        shangpinadapter.notifyDataSetChanged();
                        for (MultiItemEntity bean : shangpinadapter.getData()){
                            CaiGouDanBean.ListBean mu = (CaiGouDanBean.ListBean) bean;
                            Log.e("位置--",mu.getClassify_name());
                        }
                    }
                },true);
    }
    public TextView getTvBiaoqian() {
        return tvBiaoqian;
    }

    public void setTvBiaoqian(TextView tvBiaoqian) {
        this.tvBiaoqian = tvBiaoqian;
    }

    public ImageView getIvQuanxuan() {
        return ivQuanxuan;
    }

    public void setIvQuanxuan(ImageView ivQuanxuan) {
        this.ivQuanxuan = ivQuanxuan;
    }

    public HashMap<String,ShangpinidAndDianpuidBean> getXuanzhong() {
        return xuanzhong;
    }

    public void setXuanzhong(HashMap<String,ShangpinidAndDianpuidBean> xuanzhong) {
        this.xuanzhong = xuanzhong;
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        myBack();
    }

    public void myBack(){
        Intent intent = new Intent(ShenPiActivity.this,CaiGouDanActivity.class);
        startActivity(intent);
        finish();
    }
    public int getItem_position(){
        return item_position;
    }

    public void setViewShow(MultiItemEntity item){//存储点击item,计算总价
        CaiGouDanBean.CcListBeanLevel level = (CaiGouDanBean.CcListBeanLevel) item;
        Log.e("点击",new Gson().toJson(level));
        String son_order_id = "";
        String commodity_id = "";
        Set<String> keys = xuanzhong.keySet();
        //存储选中的商品信息  更新adapter
        for (String key: keys){
                Log.e("我的key",key);
            if (key.equals(level.getCcListBean().getSon_order_id())){
                ShangpinidAndDianpuidBean bean=new ShangpinidAndDianpuidBean();
                bean.setCommodity_id(level.getCcListBean().getCommodity_id());
                bean.setCompany_id(level.getCcListBean().getCompany_id());
                bean.setDanjia(level.getCcListBean().getPrice());
                xuanzhong.put(level.getCcListBean().getSon_order_id(),bean);
            }
        }
        //把所有选中的商品拼接起来
        Set<String> mapkey = xuanzhong.keySet();
        for (String key:mapkey) {
            ShangpinidAndDianpuidBean value = xuanzhong.get(key);
            if (value.getCommodity_id().isEmpty()){//没选中的不拼   避免有多余的,
            }else{
                son_order_id+=key+",";
                commodity_id+=value.getCommodity_id()+",";
            }
        }
        son_order_id=son_order_id.substring(0,son_order_id.length()-1);
        commodity_id=commodity_id.substring(0,commodity_id.length()-1);
        Log.e("commodity_id",commodity_id+"---");
        Log.e("son_order_id",son_order_id+"---");
        //调用接口获取总价
        zongjia(son_order_id,commodity_id);
    }
}
