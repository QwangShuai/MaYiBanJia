package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.graphics.drawable.BitmapDrawable;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean;
import com.mingmen.mayi.mayibanjia.bean.ChongFaQiangDanBean;
import com.mingmen.mayi.mayibanjia.bean.ShangpinidAndDianpuidBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.ShenPiActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

/**
 * Created by Administrator on 2018/8/16.
 */

public class ShenPiShangPinAdapter1 extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    private ShenPiActivity activity;
    private PopupWindow mPopWindow;
    CaiGouDanBean.CcListBeanLevel ccListBeanLevel;
    ShangpinidAndDianpuidBean xuanzhongid;
    private int po = 0;
    HashMap<String, ShangpinidAndDianpuidBean> xuanzhong = new HashMap<>();

    public ShenPiShangPinAdapter1(ShenPiActivity activity) {
        super(null);
        addItemType(CaiGouDanBean.ListBean.Level_0, R.layout.item_shenpi_zhengchang);//一级
        addItemType(CaiGouDanBean.ListBean.Level_1, R.layout.item_shenpi_zhengchang_child);//二级
        this.activity = activity;
    }

    public int getLevel0size() {

        return 0;
    }

    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
        switch (holder.getItemViewType()) {
            case CaiGouDanBean.ListBean.Level_0:
                final CaiGouDanBean.ListBean listBean = (CaiGouDanBean.ListBean) item;
                Log.e("listBean-adapter", new Gson().toJson(listBean));
                holder.setText(R.id.tv_shuliang, listBean.getCount() + "");
                boolean youtuijian = false;
                final boolean[] runningThree = {true};
                final TextView tv_xitongtuijian = holder.getView(R.id.tv_xitongtuijian);
                final CountDownTimer downTimer = new CountDownTimer(300 * 1000, 1000) {
                    @Override
                    public void onTick(long l) {
                        tv_xitongtuijian.setText((l / 1000) + "秒后抢单结束");
                        tv_xitongtuijian.setTextColor(mContext.getResources().getColor(R.color.mayihong));
                        tv_xitongtuijian.setEnabled(false);
                    }

                    @Override
                    public void onFinish() {
                        runningThree[0] = true;
                        tv_xitongtuijian.setText("重发抢单");
                        activity.getshenpi(listBean, getParentPosition(listBean), false);
                        tv_xitongtuijian.setEnabled(true);
                        tv_xitongtuijian.setTextColor(mContext.getResources().getColor(R.color.lishisousuo));

                    }
                };
                HashMap<String, ShangpinidAndDianpuidBean> map = activity.getXuanzhong();


                ShangpinidAndDianpuidBean bean = map.get(listBean.getSon_order_id());

                ImageView iv_select = holder.getView(R.id.iv_select);
                if (!TextUtils.isEmpty(bean.getCommodity_id()) || bean.getCommodity_id() == null) {
                    iv_select.setSelected(!bean.getCommodity_id().isEmpty());//设置选中状态
                }
                holder.setText(R.id.tv_shangpinming, listBean.getClassify_name());//商品名

                ImageView sptu = holder.getView(R.id.iv_sptu);
                Glide.with(mContext).load(listBean.getPicture_url()).into(sptu);//商品图

                holder.setText(R.id.tv_guige, listBean.getPack_standard_name());//规格
//                holder.setText(R.id.tv_qidingliang,listBean.getLevels().get(getLoadMoreViewPosition()).getCcListBean().getRation_one());
                TextView tv_zongjia = holder.getView(R.id.tv_zongjia);
                LinearLayout ll_zongjia = holder.getView(R.id.ll_zongjia);

                if (bean.getCommodity_id() == null || bean.getCommodity_id().isEmpty()) {//是否选中
                    ll_zongjia.setVisibility(View.GONE);
                } else {
                    ll_zongjia.setVisibility(View.VISIBLE);//选中的就获取当前总价
                    getcaigoudanjiage(listBean.getSon_order_id(), bean.getCommodity_id(), tv_zongjia);
                }

                holder.setVisible(R.id.tv_teshu, listBean.isSpecial());//特殊商品显示标签
                holder.setVisible(R.id.ll_lishi, !listBean.isSpecial());//特殊商品  隐藏历史记录

                holder.setText(R.id.tv_xitongtuijian, listBean.isNeedLoad() == true ? "重发抢单" : "系统推荐");
                if (listBean.isSpecial()) {
                    if (listBean.getLevels() == null) {
                        Log.e("meiyou系统推荐", "meiyou系统推荐");
                        holder.setText(R.id.tv_xitongtuijian, "重新抢单");//特殊商品没有系统推荐  重新抢单
                        youtuijian = false;
                    } else {
                        Log.e("有系统推荐", "有系统推荐");
                        holder.setText(R.id.tv_xitongtuijian, "系统推荐");//特殊商品  有推荐  点系统推荐
                        youtuijian = true;
                    }
                }
                final boolean finalYoutuijian = youtuijian;
                holder.setOnClickListener(R.id.tv_xitongtuijian, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (finalYoutuijian) {//有推荐
                            if (listBean.isExpanded()) {//判断展开还是关闭
                                collapse(getParentPosition(listBean));
                            } else {
                                expand(getParentPosition(listBean));
                            }
                        } else {//没有推荐
                            if (!((CaiGouDanBean.ListBean) item).isSpecial()) {//不是特殊商品  不调用接口
                                return;
                            }
                            Log.e("listBean.getMarket_id()", listBean.getMarket_id() + "==");
                            if (runningThree[0] == true) {//是否在进行倒计时    不在倒计时就调取接口
                                HttpManager.getInstance()
                                        .with(mContext)
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

                final TextView tv_teshu = holder.getView(R.id.tv_teshu);
                //特殊商品的pop
                holder.setOnClickListener(R.id.tv_teshu, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showPopupWindow(listBean.getSpecial_commodity(), tv_teshu);
                    }
                });
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("这是我的点击位置", getParentPosition(listBean) + "---");
                        int position = getParentPosition(listBean);
                        if (listBean.isNeedLoad()) {//是否需要加载数据
                            if (!listBean.isSpecial()) {//如果不是特殊商品
                                activity.getshenpi(listBean, getParentPosition(listBean), false);
                            } else {
//                                ToastUtil.showToast("");
                            }
                        } else {
                            //不需要加载数据时
                            List<CaiGouDanBean.CcListBeanLevel> levels = listBean.getSubItems();
//                            展开二级列表
                            if (levels.get(0).getCcListBean().getCommodity_id() != null) {//如果第一个是空的  说明全是空的
                                if (listBean.isExpanded()) {//判断展开还是关闭
//                                    collapse(getParentPosition(listBean));
                                    collapse(position);

                                } else {
                                    expand(position);
//                                    expand(getParentPosition(listBean));
                                }
                            } else {
                                ToastUtil.showToast("该市场下没有此分类商品");//
                            }

                        }
                    }
                });
                holder.addOnClickListener(R.id.ll_lishi);
                holder.addOnClickListener(R.id.iv_shanchu);
                holder.addOnClickListener(R.id.iv_xiugai);
                break;
            case CaiGouDanBean.ListBean.Level_1:
                ccListBeanLevel = (CaiGouDanBean.CcListBeanLevel) item;
                final RelativeLayout rl_kuang = holder.getView(R.id.rl_kuang);
                if (ccListBeanLevel.getCcListBean().getCommodity_id() != null) {
                    rl_kuang.setVisibility(View.VISIBLE);
                } else {
                    rl_kuang.setVisibility(View.GONE);
                }
//                rl_kuang.setSelected(true);
                rl_kuang.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("我的原始老Gson", new Gson().toJson(item));
                        Log.e("我的新的老Gson", new Gson().toJson(ccListBeanLevel));
                        activity.getIvQuanxuan().setSelected(false);
                        activity.getTvBiaoqian().setText("");
                        activity.getTvBiaoqian().setHint("请选择");
                        rl_kuang.setSelected(true);
                        rl_kuang.setBackgroundColor(mContext.getResources().getColor(R.color.hei20));
                        activity.setViewShow(item);
                    }
                });
//               xuanzhong = activity.getXuanzhong();
//                xuanzhongid = new ShangpinidAndDianpuidBean();
//               xuanzhongid = xuanzhong.get(ccListBeanLevel.getCcListBean().getSon_order_id());
//               LinearLayout ll_qidingliang = holder.getView(R.id.ll_qidingliang);
//               boolean b = false;
//               Log.e("aaaa",activity.getItem_position()+"----");
//                        if(xuanzhongid!=null){
//                            if (ccListBeanLevel.getCcListBean().getCommodity_id().equals(xuanzhongid.getCommodity_id())) {
//                                rl_kuang.setBackgroundColor(mContext.getResources().getColor(R.color.hei20));
//                            }else{
//                                rl_kuang.setBackgroundColor(mContext.getResources().getColor(R.color.white));
//                            }
//                        } else {
//                            rl_kuang.setBackgroundColor(mContext.getResources().getColor(R.color.white));
//                        }
//               if(activity.getItem_position()==1&&ccListBeanLevel.getType()==3){
//                    b = true;
//               } else if(activity.getItem_position()==2&&ccListBeanLevel.getType()==1){
//                   b = true;
//               } else if(activity.getItem_position()==3&&ccListBeanLevel.getType()==2){
//                   b = true;
//               } else if(activity.getItem_position()==4&&ccListBeanLevel.getType()==4) {
//                   b = true;
//               }else  if(activity.getItem_position()==5&&ccListBeanLevel.getType()==5) {
//                   b = true;
//               } else {
//                   b=false;
//               }
//               if (xuanzhongid!=null&&b){//审批二级页面加载
//               if (xuanzhongid!=null) {//审批二级页面加载
//                   if (ccListBeanLevel.getCcListBean().getCommodity_id().equals(xuanzhongid.getCommodity_id())) {
//                       rl_kuang.setBackgroundColor(mContext.getResources().getColor(R.color.hei20));

//                       holder.setBackgroundColor(R.id.rl_kuang,mContext.getResources().getColor(R.color.hei20));
//                           ll_qidingliang.setVisibility(View.VISIBLE);
//                           holder.setText(R.id.tv_qidingliang,ccListBeanLevel.getCcListBean().getPack_standard_one_name());
//                           holder.setText(R.id.tv_qidingliangdanjia1,ccListBeanLevel.getCcListBean().getPrice_one());
//                           if(!TextUtils.isEmpty(String.valueOf(ccListBeanLevel.getCcListBean().getPice_two()))){
//                               holder.setText(R.id.tv_qidingliang2,ccListBeanLevel.getCcListBean().getPack_standard_two_name());
//                               holder.setText(R.id.tv_qidingliangdanjia2,ccListBeanLevel.getCcListBean().getPrice_two());
//                               holder.setVisible(R.id.tv_qidingliang2,true);
//                               holder.setVisible(R.id.tv_qidingliangdanjia2,true);
//                           }
//                           if(!TextUtils.isEmpty(String.valueOf(ccListBeanLevel.getCcListBean().getPice_three()))){
//                               holder.setText(R.id.tv_qidingliang3,ccListBeanLevel.getCcListBean().getPack_standard_tree_name());
//                               holder.setText(R.id.tv_qidingliangdanjia3,ccListBeanLevel.getCcListBean().getPrice_three());
//                               holder.setVisible(R.id.tv_qidingliang3,true);
//                               holder.setVisible(R.id.tv_qidingliangdanjia3,true);
//                           }
//                       }else{
//                           ll_qidingliang.setVisibility(View.GONE);
////                       holder.setBackgroundColor(R.id.rl_kuang,mContext.getResources().getColor(R.color.white));
//                           rl_kuang.setBackgroundColor(mContext.getResources().getColor(R.color.white));
//                   }
////
//                   } else {
//                   holder.setBackgroundColor(R.id.rl_kuang,mContext.getResources().getColor(R.color.white));
//                   ll_qidingliang.setVisibility(View.GONE);
//                       rl_kuang.setBackgroundColor(mContext.getResources().getColor(R.color.white));
//                   }
                Log.e("这是我的附加费",""+ccListBeanLevel.getCcListBean().getAppend_money());
                if(ccListBeanLevel.getCcListBean().getAppend_money()!=null&&!TextUtils.isEmpty(ccListBeanLevel.getCcListBean().getAppend_money()+""))
                    holder.setText(R.id.tv_fujiafei, "附加费：￥" + ccListBeanLevel.getCcListBean().getAppend_money());
                else
                    holder.getView(R.id.tv_fujiafei).setVisibility(View.GONE);
                holder.setText(R.id.tv_spming, ccListBeanLevel.getCcListBean().getCommodity_name() + "");
                holder.setText(R.id.tv_dianming, ccListBeanLevel.getCcListBean().getCompany_name() + "");
                holder.setText(R.id.tv_guige, ccListBeanLevel.getCcListBean().getPack_standard() + "");
                holder.setText(R.id.tv_danjia, ccListBeanLevel.getCcListBean().getPrice() + "");
                holder.setText(R.id.tv_spxiaoliang, "已售" + ccListBeanLevel.getCcListBean().getCommodity_sales());
                holder.setVisible(R.id.biaoqian, ccListBeanLevel.getCcListBean().isxianshi());
                holder.setText(R.id.biaoqian, ccListBeanLevel.getCcListBean().getBiaoqian() + "");
                holder.addOnClickListener(R.id.bt_shangjia);
                holder.addOnClickListener(R.id.bt_xiangqing);
                break;
        }
    }

    //采购单价格
    private void getcaigoudanjiage(String son_order_id, String commodity_id, final TextView tv_zongjia) {
        HttpManager.getInstance()
                .with(mContext)
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
        View view = View.inflate(mContext, R.layout.pop_teshuyaoqiu, null);
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

    public void setPosition(int po) {
        this.po = po;
    }
}

