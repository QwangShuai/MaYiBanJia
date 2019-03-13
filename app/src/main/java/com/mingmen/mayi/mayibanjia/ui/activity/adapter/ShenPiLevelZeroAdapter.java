package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean;
import com.mingmen.mayi.mayibanjia.bean.LiShiJiLuBean;
import com.mingmen.mayi.mayibanjia.bean.ShangpinidAndDianpuidBean;
import com.mingmen.mayi.mayibanjia.bean.XiTongTuiJianBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.ShenPiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.CaiGouDanXiuGaiDailog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.LiShiJiLuDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ShiChangDialog;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean.TYPE_FIVE;
import static com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean.TYPE_FOUR;
import static com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean.TYPE_ONE;
import static com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean.TYPE_THREE;
import static com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean.TYPE_TWO;

/**
 * Created by Administrator on 2018/11/12.
 */

public class ShenPiLevelZeroAdapter extends RecyclerView.Adapter<ShenPiLevelZeroAdapter.ViewHolder> {

    private ShenPiActivity activity;
    private ViewHolder viewHolder;
    private List<CaiGouDanBean.FllistBean> mList;
    private ShenPiLevelOneAdapter adapter;
    private ConfirmDialog confirmDialog;
    public CallBack callBack;
    private Context mContext;
    private boolean isClick = true;
    private boolean itemIsClick[];
    List listBeanLevel = new ArrayList();
    private String market_id = "";

    public ShenPiLevelZeroAdapter(ShenPiActivity activity, List<CaiGouDanBean.FllistBean> mList, Context mContext) {
        this.activity = activity;
        this.mList = mList;
        this.mContext = mContext;
        itemIsClick = new boolean[mList.size()];
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shenpi_zhengchang_zero, null, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final CaiGouDanBean.FllistBean bean = mList.get(position);
        holder.tvPinlei.setText(bean.getClassify_name());
        if (StringUtil.isValid(bean.getMarket_id())) {
            holder.tvShichang.setText(bean.getMarket_name());
            for (int i = 0; i < mList.get(position).getSonorderlist().size(); i++) {
                mList.get(position).getSonorderlist().get(i).setMarket_id(bean.getMarket_id());
            }
        }
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        adapter = new ShenPiLevelOneAdapter(activity, mList.get(position).getSonorderlist(), ShenPiLevelZeroAdapter.this, position);
        adapter.setClick(isClick);
        holder.rvList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        holder.rvList.setAdapter(adapter);
        holder.rvList.setFocusable(false);
//        holder.rvList.setNestedScrollingEnabled(false);
        if (isClick) {
            holder.rlShichang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!itemIsClick[position]) {
                        ShiChangDialog dialog = new ShiChangDialog(mContext, new ShiChangDialog.CallBack() {
                            @Override
                            public void confirm(String id, String name) {
                                holder.tvShichang.setText(name);
                                market_id = id;
                                mList.get(position).setMarket_id(id);
                                activity.setItemMarket_id(position, id, name);
                                for (int i = 0; i < mList.get(position).getSonorderlist().size(); i++) {
                                    mList.get(position).getSonorderlist().get(i).setMarket_id(id);
                                    mList.get(position).getSonorderlist().get(i).setNeedLoad(true);
                                    mList.get(position).getSonorderlist().get(i).setSelect(false);
                                    ShangpinidAndDianpuidBean bean = new ShangpinidAndDianpuidBean();
                                    bean.setCommodity_id("");
                                    bean.setCompany_id("");
                                    bean.setDanjia("");
                                    activity.setAdapterXuanzhong(mList.get(position).getSonorderlist().get(i).getSon_order_id(), bean);
//                            adapter.setZongjia("");
//                                    adapter.setClick();
                                    notifyDataSetChanged();
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        });
                        dialog.show();
                    } else {
                        ToastUtil.showToast("请耐心等待特殊商品匹配完成");
                    }
                }
            });
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.isShow()) {
                    bean.setShow(false);
                    holder.rvList.setVisibility(View.GONE);
                    holder.ivJinru.setImageResource(R.mipmap.jinru);
                } else {
                    bean.setShow(true);
                    holder.rvList.setVisibility(View.VISIBLE);
                    holder.ivJinru.setImageResource(R.mipmap.xia_kongxin_hui);
                }
            }
        });

//        if(mList.get(position).getOrder_audit_state().equals("903")){
//            isClick = false;
//            adapter.setClick(isClick);
//        }
        adapter.setCallBack(new ShenPiLevelOneAdapter.CallBack() {
            @Override
            public void isClick(View view, final int pos) {
                if (isClick) {
                    if (!itemIsClick[position]) {
                        switch (view.getId()) {
                            case R.id.iv_shanchu://删除当前采购单
                                //不能全删
                                if (mList.get(position).getSonorderlist().size() == 1 && mList.size() == 1) {
                                    ToastUtil.showToast("不能全部删除");
                                    return;
                                }
                                confirmDialog.showDialog("是否确定删除此采购单");
                                confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        HttpManager.getInstance()
                                                .with(mContext)
                                                .setObservable(
                                                        RetrofitManager
                                                                .getService()
                                                                .delsonorderid(PreferenceUtils.getString(MyApplication.mContext, "token", ""), mList.get(position).getSonorderlist().get(pos).getSon_order_id()))
                                                .setDataListener(new HttpDataListener<String>() {
                                                    @Override
                                                    public void onNext(String data) {
                                                        Log.e("data", position + "(＾－＾)V" + pos);
                                                        ToastUtil.showToast("删除成功");
                                                        confirmDialog.dismiss();
                                                        activity.setAdapterDelXuanzhong(mList.get(position).getSonorderlist().get(pos).getSon_order_id());
                                                        mList.get(position).getSonorderlist().remove(pos);
                                                        if (mList.get(position).getSonorderlist().size() == 0) {
                                                            mList.remove(position);
                                                            delLength();
                                                        }
                                                        notifyDataSetChanged();
                                                        adapter.notifyDataSetChanged();
                                                        activity.delViewShow();
                                                        //在存储商品id 的map中删掉当前商品
//                                                        for (int i = 0; i < mList.get(position).getSonorderlist().size(); i++) {
//                                                            if (mList.get(position).getSonorderlist().get(pos).getSon_order_id().equals(mList.get(position).getSonorderlist().get(i).getSon_order_id())) {
//                                                                activity.setAdapterDelXuanzhong(mList.get(position).getSonorderlist().get(pos).getSon_order_id());
//                                                                mList.get(position).getSonorderlist().remove(i);
//                                                                if (mList.get(position).getSonorderlist().size()==0){
//                                                                    mList.remove(position);
//                                                                    delLength();
//                                                                }
//                                                                notifyDataSetChanged();
//                                                                adapter.notifyDataSetChanged();
//                                                                activity.delViewShow();
//                                                            }
//                                                        }
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
                                danXiuGaiDailog.setInitStr(mList.get(position).getSonorderlist().get(pos).getClassify_name(), mList.get(position).getSonorderlist().get(pos).getPack_standard_name(), mList.get(position).getSonorderlist().get(pos).getPack_standard_id(),
                                        mList.get(position).getSonorderlist().get(pos).getSpecial_commodity(), String.valueOf(mList.get(position).getSonorderlist().get(pos).getCount()), mList.get(position).getSonorderlist().get(pos).getSon_order_id(), mList.get(position).getSonorderlist().get(pos).getSort_id())
                                        .setCallBack(new CaiGouDanXiuGaiDailog.CallBack() {
                                            @Override
                                            public void confirm(CaiGouDanBean.FllistBean.SonorderlistBean msg) {
                                                ToastUtil.showToast("修改成功");
//                                        for (int i = 0;i<shangpinadapter.getLevel0size();i++){
//                                            shangpinadapter.collapse(i);
//                                        }
                                                msg.setMarket_id(mList.get(position).getSonorderlist().get(pos).getMarket_id());
                                                mList.get(position).getSonorderlist().set(pos, msg);
                                                ShangpinidAndDianpuidBean bean = new ShangpinidAndDianpuidBean();
                                                bean.setCommodity_id("");
                                                bean.setCompany_id("");
                                                bean.setDanjia("");
//                                            adapter.setZongjia("");
                                                activity.setAdapterXuanzhong(mList.get(position).getSonorderlist().get(pos).getSon_order_id(), bean);
                                                notifyDataSetChanged();
                                                adapter.notifyDataSetChanged();
                                            }
                                        }).show(activity.getSupportFragmentManager());
                                break;
                            case R.id.ll_lishi://打开历史记录   从历史记录中选择商品换到当前位置
                                if (StringUtil.isValid(mList.get(position).getSonorderlist().get(pos).getMarket_id())) {
                                    market_id = mList.get(position).getSonorderlist().get(pos).getMarket_id();
                                }
                                if (StringUtil.isValid(market_id)) {
                                    final List<CaiGouDanBean.FllistBean.SonorderlistBean.CcListBeanLevel> level = new ArrayList<>();
                                    if (mList.get(position).getSonorderlist().get(pos).isNeedLoad()) {
                                        ToastUtil.showToast("正在匹配商家...");
                                        getshenpi(mList.get(position).getSonorderlist().get(pos), position, false);
                                        return;
                                    }
                                    for (int i = 0; i < 5; i++) {
                                        CaiGouDanBean.FllistBean.SonorderlistBean.CcListBeanLevel subitem = mList.get(position).getSonorderlist().get(pos).getLevels().get(i);
                                        level.add(subitem);
                                    }
                                    HttpManager.getInstance()
                                            .with(mContext)
                                            .setObservable(
                                                    RetrofitManager
                                                            .getService()
                                                            .getlishi(PreferenceUtils.getString(MyApplication.mContext, "token", ""), mList.get(position).getMarket_id(), mList.get(position).getSonorderlist().get(pos).getSon_order_id()))
                                            .setDataListener(new HttpDataListener<LiShiJiLuBean>() {
                                                @Override
                                                public void onNext(LiShiJiLuBean data) {
                                                    new LiShiJiLuDialog()
                                                            .setSon_order_id(data.getOreder_buy(), data.getCsList())
                                                            .setCallBack(new LiShiJiLuDialog.CallBack() {
                                                                @Override
                                                                public void xuanzhong(String lujing, XiTongTuiJianBean.CcListBean msg) {
                                                                    msg.setIsxianshi(true);
                                                                    msg.setSon_order_id(mList.get(position).getSonorderlist().get(pos).getSon_order_id());
                                                                    //通过选择记录的类型  放入固定位置
                                                                    if ("goumai".equals(lujing)) {
                                                                        msg.setBiaoqian("已购店铺");
                                                                        level.get(3).setCcListBean(msg);
                                                                    } else if ("shoucang".equals(lujing)) {
                                                                        msg.setBiaoqian("收藏店铺");
                                                                        level.get(4).setCcListBean(msg);
                                                                    }
                                                                    mList.get(position).getSonorderlist().get(pos).setLevels(level);
                                                                    if (mList.get(position).getSonorderlist().get(pos).getLevels().size() != 0) {
                                                                        Log.e("dd", pos + "---");
                                                                        mList.get(position).getSonorderlist().get(pos).setSelect(false);
                                                                        adapter.notifyDataSetChanged();
//                                                                        adapter.setShow(pos);
                                                                    } else {
                                                                    }
//                                                                adapter.notifyDataSetChanged();
                                                                    notifyDataSetChanged();
                                                                }
                                                            }).show(activity.getSupportFragmentManager());
                                                }
                                            });
                                } else {
                                    ToastUtil.showToastLong("请先选择市场");
                                }

                                break;
                        }
                    } else {
                        ToastUtil.showToast("等待特殊商品抢单完成");
                    }

                } else {
                    ToastUtil.showToast("暂无此权限");
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface CallBack {
        void isClick(View v, int pos);
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_pinlei)
        TextView tvPinlei;
        @BindView(R.id.iv_jinru)
        ImageView ivJinru;
        @BindView(R.id.tv_shichang)
        TextView tvShichang;
        @BindView(R.id.rl_shichang)
        RelativeLayout rlShichang;
        @BindView(R.id.rv_list)
        RecyclerView rvList;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void setClick(boolean b) {
        isClick = b;
    }

    //获取子采购单的系统推荐数据   2级---
    public void getshenpi(final CaiGouDanBean.FllistBean.SonorderlistBean listBean, final int pos, final boolean isgangkaishi) {
        Log.e("获取的市场ID", market_id);
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getshenpi(PreferenceUtils.getString(MyApplication.mContext, "token", ""), market_id, listBean.getSon_order_id()))
                .setDataListener(new HttpDataListener<XiTongTuiJianBean>() {
                    @Override
                    public void onNext(XiTongTuiJianBean list) {
                        listBeanLevel.clear();
                        List<XiTongTuiJianBean.CcListBean> pflist = list.getPflist();   //评分
                        //避免空选项和收藏记录购买记录没有位置放  以及找不到位置放  提前new出来  add进去
                        if (pflist != null && pflist.size() > 0) {
                            XiTongTuiJianBean.CcListBean pingfen = list.getPflist().get(0);
                            pingfen.setBiaoqian("评分最高");
                            listBeanLevel.add(new CaiGouDanBean.FllistBean.SonorderlistBean.CcListBeanLevel(TYPE_THREE, pingfen));
                        } else {
                            listBeanLevel.add(new CaiGouDanBean.FllistBean.SonorderlistBean.CcListBeanLevel(TYPE_THREE, new XiTongTuiJianBean.CcListBean()));
                        }
                        List<XiTongTuiJianBean.CcListBean> commodity_sales = list.getCommodity_sales(); //销量
                        if (commodity_sales != null && commodity_sales.size() > 0) {
                            XiTongTuiJianBean.CcListBean xiaoliang = list.getCommodity_sales().get(0);
                            Log.e("xiaoliang", xiaoliang.getSon_order_id());
                            xiaoliang.setBiaoqian("销量最高");
                            listBeanLevel.add(new CaiGouDanBean.FllistBean.SonorderlistBean.CcListBeanLevel(TYPE_ONE, xiaoliang));
                        } else {
                            listBeanLevel.add(new CaiGouDanBean.FllistBean.SonorderlistBean.CcListBeanLevel(TYPE_ONE, new XiTongTuiJianBean.CcListBean()));
                        }
                        List<XiTongTuiJianBean.CcListBean> pice = list.getPice();//价格
                        if (pice != null && pice.size() > 0) {
                            XiTongTuiJianBean.CcListBean jiage = list.getPice().get(0);
                            jiage.setBiaoqian("价格最低");
                            listBeanLevel.add(new CaiGouDanBean.FllistBean.SonorderlistBean.CcListBeanLevel(TYPE_TWO, jiage));
                        } else {
                            listBeanLevel.add(new CaiGouDanBean.FllistBean.SonorderlistBean.CcListBeanLevel(TYPE_TWO, new XiTongTuiJianBean.CcListBean()));
                        }
                        listBeanLevel.add(new CaiGouDanBean.FllistBean.SonorderlistBean.CcListBeanLevel(TYPE_FOUR, new XiTongTuiJianBean.CcListBean()));
                        listBeanLevel.add(new CaiGouDanBean.FllistBean.SonorderlistBean.CcListBeanLevel(TYPE_FIVE, new XiTongTuiJianBean.CcListBean()));

                        listBean.setLevels(listBeanLevel);

                        //获取之后  改成不需要加载状态
                        listBean.setNeedLoad(false);
                        if (listBean.getLevels().size() != 0) {
                            viewHolder.rvList.setVisibility(View.VISIBLE);
//                            shangpinadapter.expand(pos);
//                            shangpinadapter.notifyDataSetChanged();
                        }
                        if (StringUtil.isValid(listBean.getSpecial_commodity())) {
                            if (pflist != null && pflist.size() > 0) {
                            } else {
                                //没有系统推荐   继续加载
                                listBean.setNeedLoad(true);
                            }
                        }
                        if (listBean.getLevels().size() != 0) {
                            adapter.setShow(pos);
                            adapter.notifyDataSetChanged();
                        } else {
                            if (StringUtil.isValid(listBean.getSpecial_commodity())) {//特殊商品 特殊处理
                                ToastUtil.showToast("没有商家推送商品");
                            } else {
                                ToastUtil.showToast("该市场目前没有此商品");
                            }
                        }

                    }
                }, "正在获取数据...");

    }

    public void setPosClick(int mypos, boolean b) {
        itemIsClick[mypos] = b;
    }

    public void addLength() {
        itemIsClick = new boolean[itemIsClick.length + 1];
    }

    public void delLength() {
        itemIsClick = new boolean[itemIsClick.length - 1];
    }

    public void stopTime() {
        adapter.timeCancel();
    }
}
