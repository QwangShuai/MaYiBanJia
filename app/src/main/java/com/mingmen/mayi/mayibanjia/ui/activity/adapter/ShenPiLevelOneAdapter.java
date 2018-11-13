package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.CountDownTimer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean;
import com.mingmen.mayi.mayibanjia.bean.ShangpinidAndDianpuidBean;
import com.mingmen.mayi.mayibanjia.bean.XiTongTuiJianBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.ShenPiActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
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

public class ShenPiLevelOneAdapter extends RecyclerView.Adapter<ShenPiLevelOneAdapter.ViewHolder> {

    private ShenPiActivity activity;
    private PopupWindow mPopWindow;
    private ViewHolder viewHolder;
    private List<CaiGouDanBean.ListBean> mList;
    private List<CaiGouDanBean.ListBean.CcListBeanLevel> listBeanLevel;
    private ShenPiLevelTwoAdapter adapter;
    private boolean[] isShow;



    public void setShow(int pos) {
        isShow[pos] = true;
    }

    public void addShow(){
        isShow = new boolean[isShow.length+1];
    }

    public CallBack callBack;

    public ShenPiLevelOneAdapter(ShenPiActivity activity,List<CaiGouDanBean.ListBean> mList){
        this.activity = activity;
        this.mList = mList;
        isShow = new boolean[mList.size()];
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shenpi_zhengchang, null, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final CaiGouDanBean.ListBean listBean = mList.get(position);
        holder.tvShuliang.setText(listBean.getCount() + "");
        listBeanLevel = new ArrayList<>();
        adapter = new ShenPiLevelTwoAdapter(activity,mList.get(position).getLevels());
        holder.rvDplist.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        holder.rvDplist.setAdapter(adapter);
        holder.rvDplist.setFocusable(false);
        holder.rvDplist.setNestedScrollingEnabled(false);
//        adapter.notifyDataSetChanged();
        if(isShow[position]){
            holder.rvDplist.setVisibility(View.VISIBLE);
        } else {
            holder.rvDplist.setVisibility(View.GONE);
        }

        boolean youtuijian = false;
        final boolean[] runningThree = {true};
        final CountDownTimer downTimer = new CountDownTimer(300 * 1000, 1000) {
            @Override
            public void onTick(long l) {
                holder.tvXitongtuijian.setText((l / 1000) + "秒后抢单结束");
                holder.tvXitongtuijian.setTextColor(activity.getResources().getColor(R.color.mayihong));
                holder.tvXitongtuijian.setEnabled(false);
            }

            @Override
            public void onFinish() {
                runningThree[0] = true;
                holder.tvXitongtuijian.setText("重发抢单");
                getshenpi(listBean, position,activity);
                holder.tvXitongtuijian.setEnabled(true);
                holder.tvXitongtuijian.setTextColor(activity.getResources().getColor(R.color.lishisousuo));

            }
        };
        HashMap<String, ShangpinidAndDianpuidBean> map = activity.getXuanzhong();


        ShangpinidAndDianpuidBean bean = map.get(listBean.getSon_order_id());

        if (!TextUtils.isEmpty(String.valueOf(bean.getCommodity_id())) && bean.getCommodity_id()!=null) {
            holder.ivSelect.setSelected(!bean.getCommodity_id().isEmpty());//设置选中状态
        }
        holder.tvShangpinming.setText(listBean.getClassify_name());//商品名

        Glide.with(activity).load(listBean.getPicture_url()).into(holder.ivSptu);//商品图
        holder.tvGuige.setText(listBean.getPack_standard_name());//规格

        if (bean.getCommodity_id() == null || bean.getCommodity_id().isEmpty()) {//是否选中
            holder.llZongjia.setVisibility(View.GONE);
        } else {
            holder.llZongjia.setVisibility(View.VISIBLE);//选中的就获取当前总价
            getcaigoudanjiage(listBean.getSon_order_id(), bean.getCommodity_id(), viewHolder.tvZongjia);
        }
        if(listBean.isSpecial()){
            holder.tvTeshu.setVisibility(View.VISIBLE);//特殊商品显示标签
        }
        if(listBean.isSpecial()){
            holder.llLishi.setVisibility(View.GONE);//特殊商品  隐藏历史记录
        } else {

        }

        holder.tvXitongtuijian.setText(listBean.isNeedLoad() == true ? "重发抢单" : "系统推荐");
        if (listBean.isSpecial()) {
            if (listBean.getLevels() == null) {
                Log.e("meiyou系统推荐", "meiyou系统推荐");
                holder.tvXitongtuijian.setText("重新抢单");//特殊商品没有系统推荐  重新抢单
                youtuijian = false;
            } else {
                Log.e("有系统推荐", "有系统推荐");
                holder.tvXitongtuijian.setText( "系统推荐");//特殊商品  有推荐  点系统推荐
                youtuijian = true;
            }
        }
        final boolean finalYoutuijian = youtuijian;
        holder.tvXitongtuijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalYoutuijian) {//有推荐
                    if (isShow[position]) {//判断展开还是关闭
//                        collapse(getParentPosition(listBean));
                    } else {
//                        expand(getParentPosition(listBean));
                    }
                } else {//没有推荐
                    if (!listBean.isSpecial()) {//不是特殊商品  不调用接口
                        return;
                    }
                    Log.e("listBean.getMarket_id()", listBean.getMarket_id() + "==");
                    if (runningThree[0] == true) {//是否在进行倒计时    不在倒计时就调取接口
                        HttpManager.getInstance()
                                .with(activity)
                                .setObservable(
                                        RetrofitManager
                                                .getService()
                                                .chongfaqiangdan(listBean.getSon_order_id(),
                                                        PreferenceUtils.getString(MyApplication.mContext, "token", ""), listBean.getMarket_id()))
                                .setDataListener(new HttpDataListener<String>() {
                                    @Override
                                    public void onNext(String data) {
                                        Log.e("data", data + "---");
                                        ToastUtil.showToast("发送抢单信息成功");
                                        downTimer.start();
                                        runningThree[0] = false;
                                    }
                                });
                    }


                }

            }
        });


        //特殊商品的pop
        holder.tvTeshu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(listBean.getSpecial_commodity(), holder.tvTeshu);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                activity.MoveToPosition(position);

                if (listBean.isNeedLoad()) {//是否需要加载数据
                    if (!listBean.isSpecial()) {//如果不是特殊商品
                        getshenpi(listBean, position,activity);
                    } else {
//                                ToastUtil.showToast("");
                    }
                } else {
                    //不需要加载数据时
//                    展开二级列表
                    if(isShow[position]){
                        isShow[position] = false;
                        holder.rvDplist.setVisibility(View.GONE);
//                        adapter.notifyDataSetChanged();
                    } else {
                        isShow[position] = true;
                        holder.rvDplist.setVisibility(View.VISIBLE);
//                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
        holder.llLishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.isClick(holder.llLishi,position);
            }
        });

        holder.ivShanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.isClick(holder.ivShanchu,position);
            }
        });
        holder.ivXiugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.isClick(holder.ivXiugai,position);
            }
        });
    }

    public void setCallBack(CallBack callBack){
        this.callBack = callBack;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_teshu)
        TextView tvTeshu;
        @BindView(R.id.iv_select)
        ImageView ivSelect;
        @BindView(R.id.iv_sptu)
        ImageView ivSptu;
        @BindView(R.id.tv_shangpinming)
        TextView tvShangpinming;
        @BindView(R.id.tv_shuliang)
        TextView tvShuliang;
        @BindView(R.id.tv_guige)
        TextView tvGuige;
        @BindView(R.id.ll_guige)
        LinearLayout llGuige;
        @BindView(R.id.tv_zongjia)
        TextView tvZongjia;
        @BindView(R.id.ll_zongjia)
        LinearLayout llZongjia;
        @BindView(R.id.iv_shanchu)
        ImageView ivShanchu;
        @BindView(R.id.iv_xiugai)
        ImageView ivXiugai;
        @BindView(R.id.tv_xitongtuijian)
        TextView tvXitongtuijian;
        @BindView(R.id.tv_lishijilu)
        TextView tvLishijilu;
        @BindView(R.id.ll_lishi)
        LinearLayout llLishi;
        @BindView(R.id.ll_tuijian)
        LinearLayout llTuijian;
        @BindView(R.id.ll_kuang)
        LinearLayout llKuang;
        @BindView(R.id.rv_dplist)
        RecyclerView rvDplist;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    //采购单价格
    private void getcaigoudanjiage(String son_order_id, String commodity_id, final TextView tv_zongjia) {
        HttpManager.getInstance()
                .with(activity)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getcaigoudanjiage(PreferenceUtils.getString(MyApplication.mContext, "token", ""), son_order_id, commodity_id))
                .setDataListener(
                        new HttpDataListener<String>() {
                            @Override
                            public void onNext(String data) {
                                Log.e("data", data + "---");
                                tv_zongjia.setText(data);
                            }
                        });
    }


    //PopupWindow
    private void showPopupWindow(String yaoqiu, View xianshiView) {
        View view = View.inflate(activity, R.layout.pop_teshuyaoqiu, null);
        mPopWindow = new PopupWindow(view);
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = activity.getWindowManager().getDefaultDisplay().getHeight();
        mPopWindow.setWidth(width * 4 / 6);
        mPopWindow.setHeight(height * 2 / 7);

        TextView tv_yaoqiu = (TextView) view.findViewById(R.id.tv_yaoqiu);
        tv_yaoqiu.setText(yaoqiu);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.showAsDropDown(xianshiView);
    }

    public void getshenpi(final CaiGouDanBean.ListBean listBean, final int pos, final ShenPiActivity myactivity) {
        HttpManager.getInstance()
                .with(activity)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getshenpi(PreferenceUtils.getString(MyApplication.mContext, "token",""), listBean.getMarket_id(), listBean.getSon_order_id()))
                .setDataListener(new HttpDataListener<XiTongTuiJianBean>() {
                    @Override
                    public void onNext(XiTongTuiJianBean list) {
                        Log.e("拿到数据了吗", new Gson().toJson(list));
                        List<XiTongTuiJianBean.CcListBean> pflist = list.getPflist();   //评分
                        //避免空选项和收藏记录购买记录没有位置放  以及找不到位置放  提前new出来  add进去
                        if (pflist != null && pflist.size() > 0) {
                            XiTongTuiJianBean.CcListBean pingfen = list.getPflist().get(0);
                            pingfen.setBiaoqian("评分最高");
                            listBeanLevel.add( new CaiGouDanBean.ListBean.CcListBeanLevel(TYPE_THREE, pingfen));
                        }else{
                            listBeanLevel.add(new CaiGouDanBean.ListBean.CcListBeanLevel(TYPE_THREE, new XiTongTuiJianBean.CcListBean()));
                        }
                        List<XiTongTuiJianBean.CcListBean> commodity_sales = list.getCommodity_sales(); //销量
                        if (commodity_sales != null && commodity_sales.size() > 0) {
                            XiTongTuiJianBean.CcListBean xiaoliang = list.getCommodity_sales().get(0);
                            Log.e("xiaoliang",xiaoliang.getSon_order_id());
                            xiaoliang.setBiaoqian("销量最高");
                            listBeanLevel.add(new CaiGouDanBean.ListBean.CcListBeanLevel(TYPE_ONE,xiaoliang));
                        }else{
                            listBeanLevel.add(new CaiGouDanBean.ListBean.CcListBeanLevel(TYPE_ONE, new XiTongTuiJianBean.CcListBean()));
                        }
                        List<XiTongTuiJianBean.CcListBean> pice = list.getPice();//价格
                        if (pice != null && pice.size() > 0) {
                            XiTongTuiJianBean.CcListBean jiage = list.getPice().get(0);
                            jiage.setBiaoqian("价格最低");
                            listBeanLevel.add(new CaiGouDanBean.ListBean.CcListBeanLevel(TYPE_TWO, jiage));
                        }else{
                            listBeanLevel.add(new CaiGouDanBean.ListBean.CcListBeanLevel(TYPE_TWO, new XiTongTuiJianBean.CcListBean()));
                        }
                        listBeanLevel.add(new CaiGouDanBean.ListBean.CcListBeanLevel(TYPE_FOUR, new XiTongTuiJianBean.CcListBean()));
                        listBeanLevel.add(new CaiGouDanBean.ListBean.CcListBeanLevel(TYPE_FIVE, new XiTongTuiJianBean.CcListBean()));

                        //获取之后  改成不需要加载状态
                        listBean.setNeedLoad(false);
//                        if (listBean.isSpecial()){
//                            if (pflist != null && pflist.size() > 0){
//                            }else{
//                                //没有系统推荐   继续加载
//                                listBean.setNeedLoad(true);
//                            }
//                        }

                        mList.get(pos).setLevels(listBeanLevel);
                        Log.e("瓜娃子",new Gson().toJson(mList.get(pos).getLevels()));
                        notifyDataSetChanged();

                        if(mList.get(pos).getLevels().size()!=0){
                            isShow[pos] = true;
                            notifyDataSetChanged();
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

    public interface CallBack{
        void isClick(View v, int pos);
    }
}
