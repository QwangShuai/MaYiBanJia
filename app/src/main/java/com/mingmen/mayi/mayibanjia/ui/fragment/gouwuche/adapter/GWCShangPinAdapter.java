package com.mingmen.mayi.mayibanjia.ui.fragment.gouwuche.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.GWCShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.GouwucheDianpuBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.GouWuCheActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.LoginActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.SPXiangQingActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.TubiaoActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.GWCXiuGaiShuLiangDialog;
import com.mingmen.mayi.mayibanjia.ui.fragment.gouwuche.GouWuCheFragment;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.GlideUtils;
import com.mingmen.mayi.mayibanjia.utils.JumpUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.custom.MarqueeTextView;
import com.qiniu.android.utils.Json;
import com.qiniu.android.utils.StringUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.sephiroth.android.library.easing.Linear;

import static com.mingmen.mayi.mayibanjia.ui.activity.dialog.GWCXiuGaiShuLiangDialog.JIA;
import static com.mingmen.mayi.mayibanjia.ui.activity.dialog.GWCXiuGaiShuLiangDialog.JIAN;

/**
 * Created by Administrator on 2018/7/5/005.
 */

public class GWCShangPinAdapter extends RecyclerView.Adapter<GWCShangPinAdapter.ViewHolder> {
    private ViewHolder viewHolder;
    private Context mContext;
    private List<GouwucheDianpuBean.SplistBean> mList;
    GouWuCheFragment gouWuCheFragment;
    private OnItemClickListener mOnItemClickListener;
    private GouWuCheActivity activity;
    private boolean isTeshu;

    public interface OnItemClickListener {
        void onClick(View view, int position, List<GouwucheDianpuBean.SplistBean> mList);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    public GWCShangPinAdapter(Context mContext, List<GouwucheDianpuBean.SplistBean> list, GouWuCheFragment gouWuCheFragment) {
        this.mContext = mContext;
        this.mList = list;
        this.gouWuCheFragment = gouWuCheFragment;
    }
    public void setAllCheck(boolean b){
        for(int i=0;i<mList.size();i++){
            mList.get(i).setSelect(b);
            notifyDataSetChanged();
        }
    }
    public GWCShangPinAdapter(Context mContext, List<GouwucheDianpuBean.SplistBean> list, GouWuCheActivity activity) {
        this.mContext = mContext;
        this.mList = list;
        this.activity = activity;
        this.isTeshu = true;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gouwuche_shangpin, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {//购物车页面初始数据加载
        final GouwucheDianpuBean.SplistBean shoppingBean = mList.get(position);
        //holder.tvName.setText(shoppingBean.getClassify_name());
        //holder.tvGuige.setText(shoppingBean.getPack_standard());
        holder.tvPrice.setText(shoppingBean.getPrice() + "");
        holder.tvNumber.setText(shoppingBean.getNumber() + "");
        holder.tvName.setMarqueeEnable(true);
        if (shoppingBean.getCommodity_state().equals("1")) {
            holder.tvName.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
            holder.tvName.setText(shoppingBean.getClassify_name() + "(已下架)");
            holder.tvXiajia.setVisibility(View.VISIBLE);
        } else {
            holder.tvName.setText(shoppingBean.getClassify_name());//商品名称改为三级分类名称
            holder.tvXiajia.setVisibility(View.GONE);
        }
        GlideUtils.cachePhoto(mContext,holder.ivTu,shoppingBean.getUrl());
        holder.ivDanxuan.setSelected(shoppingBean.isSelect());

        holder.tvQidingliang.setText(shoppingBean.getRation_one() + shoppingBean.getSpecNameThree());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shoppingBean.getCommodity_state().equals("1")) {
                    ToastUtil.showToastLong("已过期商品，请及时删除");
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("spid", shoppingBean.getCommodity_id());
                    JumpUtil.Jump_intent(mContext, SPXiangQingActivity.class, bundle);
                }

            }
        });

        holder.ivZoushi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //走势图
                if (shoppingBean.getCommodity_state().equals("1")) {
                    ToastUtil.showToastLong("已过期商品，请及时删除");
                } else {
                    Intent zoushi = new Intent(mContext, TubiaoActivity.class);
                    zoushi.putExtra("mark_id", shoppingBean.getSon_number());//市场id
                    zoushi.putExtra("market_name", shoppingBean.getMarket_name());//市场名
                    zoushi.putExtra("classify_id", shoppingBean.getType_four_id());//三级分类id
                    zoushi.putExtra("classify_name", shoppingBean.getClassify_name());//三级分类名称
                    Log.e("ceshi----------", shoppingBean.getSon_number() + shoppingBean.getMarket_name() + shoppingBean.getType_four_id() + shoppingBean.getClassify_name() + "================================");
                    mContext.startActivity(zoushi);
                }

            }
        });
        if (mOnItemClickListener != null) {
            holder.ivDanxuan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    gouWuCheFragment.getalllist();
                    if(isTeshu){
                        if(activity.isGuanli()){
                            shoppingBean.setSelect(!shoppingBean.isSelect());
                            holder.ivDanxuan.setSelected(shoppingBean.isSelect());
                            mList.get(position).setSelect(shoppingBean.isSelect());
                            mOnItemClickListener.onClick(v, position, mList);
                        } else {
                            if (shoppingBean.getCommodity_state().equals("1")) {
                                ToastUtil.showToastLong("已过期商品，请及时删除");
                            } else {
                                shoppingBean.setSelect(!shoppingBean.isSelect());
                                holder.ivDanxuan.setSelected(shoppingBean.isSelect());
                                mList.get(position).setSelect(shoppingBean.isSelect());
                                mOnItemClickListener.onClick(v, position, mList);
                            }
                        }
                    } else {
                        if(gouWuCheFragment.isGuanli()){
                            shoppingBean.setSelect(!shoppingBean.isSelect());
                            holder.ivDanxuan.setSelected(shoppingBean.isSelect());
                            mList.get(position).setSelect(shoppingBean.isSelect());
                            mOnItemClickListener.onClick(v, position, mList);
                        } else {
                            if (shoppingBean.getCommodity_state().equals("1")) {
                                ToastUtil.showToastLong("已过期商品，请及时删除");
                            } else {
                                shoppingBean.setSelect(!shoppingBean.isSelect());
                                holder.ivDanxuan.setSelected(shoppingBean.isSelect());
                                mList.get(position).setSelect(shoppingBean.isSelect());
                                mOnItemClickListener.onClick(v, position, mList);
                            }
                        }
                    }

                }
            });
            final String qidingliang1 = shoppingBean.getRation_one();
            holder.tvNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (shoppingBean.getCommodity_state().equals("1")) {
                        ToastUtil.showToastLong("已过期商品，请及时删除");
                    } else {
                        new GWCXiuGaiShuLiangDialog()
                                .setKuCun(shoppingBean.getInventory(), qidingliang1, Integer.parseInt(holder.tvNumber.getText().toString().trim()))
                                .setCallBack(new GWCXiuGaiShuLiangDialog.CallBack() {
                                    @Override
                                    public void shuliang(final String msg) {
                                        HttpManager.getInstance()
                                                .with(mContext)
                                                .setObservable(
                                                        RetrofitManager
                                                                .getService()
                                                                .editcar(PreferenceUtils.getString(MyApplication.mContext, "token", ""), shoppingBean.getShopping_id(), 3 + "", msg))
                                                .setDataListener(new HttpDataListener<String>() {
                                                    @Override
                                                    public void onNext(String data) {
                                                        holder.tvNumber.setText(msg);
                                                        mList.get(position).setNumber(Integer.parseInt(msg));
                                                        notifyDataSetChanged();
                                                        if(isTeshu){

                                                        } else {
                                                            gouWuCheFragment.updatejiage();
                                                        }
                                                    }
                                                }, false);

                                    }
                                }).show(isTeshu?activity.getSupportFragmentManager():gouWuCheFragment.getActivity().getSupportFragmentManager());
                    }
                }
            });
            final int[] shuliang = {Integer.parseInt(holder.tvNumber.getText().toString().trim())};
            holder.tvJiahao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (shoppingBean.getCommodity_state().equals("1")) {
                        ToastUtil.showToastLong("已过期商品，请及时删除");
                    } else {
                        if (shuliang[0] == Integer.parseInt(shoppingBean.getInventory())) {
                            ToastUtil.showToast("不能再加啦");
                            return;
                        } else {
                            HttpManager.getInstance()
                                    .with(mContext)
                                    .setObservable(
                                            RetrofitManager
                                                    .getService()
                                                    .editcar(PreferenceUtils.getString(MyApplication.mContext, "token", ""), shoppingBean.getShopping_id(), 1 + "", shuliang[0] + ""))
                                    .setDataListener(new HttpDataListener<String>() {
                                        @Override
                                        public void onNext(String data) {
                                            shuliang[0]++;
                                            holder.tvNumber.setText(shuliang[0] + "");
                                            mList.get(position).setNumber(shuliang[0]);
                                            notifyDataSetChanged();
                                            if(isTeshu){

                                            } else {
                                                gouWuCheFragment.updatejiage();
                                            }
//                        ToastUtil.showToast("修改成功");
                                        }
                                    }, false);

                        }
                    }
                }
            });
            holder.tvJianhao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (shoppingBean.getCommodity_state().equals("1")) {
                        ToastUtil.showToastLong("已过期商品，请及时删除");
                    } else {
                        if (shuliang[0] <= Integer.parseInt(qidingliang1)) {
                            ToastUtil.showToast("不能再减啦");
                            return;
                        } else {
                            if (shuliang[0] == Integer.parseInt(qidingliang1)) {
                                ToastUtil.showToast("不能再减啦");
                                return;
                            }
                            HttpManager.getInstance()
                                    .with(mContext)
                                    .setObservable(
                                            RetrofitManager
                                                    .getService()
                                                    .editcar(PreferenceUtils.getString(MyApplication.mContext, "token", ""), shoppingBean.getShopping_id(), 2 + "", shuliang[0] + ""))
                                    .setDataListener(new HttpDataListener<String>() {
                                        @Override
                                        public void onNext(String data) {
                                            shuliang[0]--;
                                            holder.tvNumber.setText(shuliang[0] + "");
                                            mList.get(position).setNumber(shuliang[0]);
                                            if(isTeshu){

                                            } else {
                                                gouWuCheFragment.updatejiage();
                                            }
//                        ToastUtil.showToast("修改成功");
                                        }
                                    }, false);
                        }
                    }

                }


            });
        }
    }

    //修改购物车数量
    private void editcar(String shoppingid, String shuliang) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .editcar(PreferenceUtils.getString(MyApplication.mContext, "token", ""), shoppingid, 3 + "", shuliang))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
//                        ToastUtil.showToast("修改成功");
                    }
                }, false);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_danxuan)
        ImageView ivDanxuan;
        @BindView(R.id.iv_tu)
        ImageView ivTu;
        @BindView(R.id.tv_name)
        MarqueeTextView tvName;
        /*        @BindView(R.id.tv_guige)
                TextView tvGuige;*/
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_jianhao)
        TextView tvJianhao;
        @BindView(R.id.tv_number)
        TextView tvNumber;
        @BindView(R.id.tv_xiajia)
        TextView tvXiajia;
        @BindView(R.id.tv_jiahao)
        TextView tvJiahao;
        /*        @BindView(R.id.ll_qidingliang)
                LinearLayout llQidingliang;*/
        @BindView(R.id.iv_zoushi)
        ImageView ivZoushi;
        @BindView(R.id.tv_qidingliang)
        TextView tvQidingliang;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}

