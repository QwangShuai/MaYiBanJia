package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.DingDanBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.DingDanXiangQingActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dingdan.DingDanActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * DingDanBean
 * Created by Administrator on 2018/7/13/013.
 */

public class DingDanXiangQingAdapter extends RecyclerView.Adapter<DingDanXiangQingAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<DingDanBean> mList;
    private OnItemClickListener mOnItemClickListener;
    private ConfirmDialog dialog;

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    private boolean isClick = true;

    public DingDanXiangQingAdapter(Context mContext, List<DingDanBean> list) {
        this.mContext = mContext;
        this.mList = list;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dingdan, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final DingDanBean dingdan = mList.get(position);
        holder.tvDingdanbianhao.setText("订单编号：" + dingdan.getOrder_number());
        holder.tvZhuangtai.setText(zhuangtai(holder, Integer.parseInt(dingdan.getState())));
        String zongjia = dingdan.getTotal_price() + "";

        if (zongjia.contains(".")) {
            holder.tvZongjia1.setText(zongjia.split("\\.")[0]);
            holder.tvZongjia2.setText(zongjia.split("\\.")[1]);
        } else {
            holder.tvZongjia1.setText(zongjia);
            holder.tvZongjia2.setText("00");
        }

        if (StringUtil.isValid(dingdan.getApp_money()) && Double.valueOf(dingdan.getApp_money()) != 0) {
            holder.llChaoshifei.setVisibility(View.VISIBLE);
            if (dingdan.getApp_money().contains(".")) {
                holder.tvChaoshifei1.setText(dingdan.getApp_money().split("\\.")[0]);
                holder.tvChaoshifei2.setText(dingdan.getApp_money().split("\\.")[1]);
            } else {
                holder.tvChaoshifei1.setText(dingdan.getApp_money());
                holder.tvChaoshifei2.setText("00");
            }
        } else {
            holder.llChaoshifei.setVisibility(View.GONE);
        }
        if (dingdan.getState().equals("404")) {
            if (dingdan.getScanState().equals("1")) {
                holder.ll_baozhuang.setVisibility(View.VISIBLE);
                holder.tv_baozhuanggeshu.setText(dingdan.getPackCount());
                holder.tv_saomageshu.setText(dingdan.getScanCount());
            } else {
                holder.ll_baozhuang.setVisibility(View.GONE);
            }
        }
//        int zongshu = 0;
//        for (int i = 0; i < dingdan.getList().size(); i++) {
//            zongshu = zongshu + dingdan.getList().get(i).getAcount();
//        }
        holder.tvSpShuliang.setText(dingdan.getShichang());
        DdShichangAdapter shangpinadapter = new DdShichangAdapter(mContext, dingdan.getList());
        holder.rvShangpinliebiao.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        holder.rvShangpinliebiao.setAdapter(shangpinadapter);
        holder.rvShangpinliebiao.setFocusable(false);
        if (StringUtil.isValid(dingdan.getCreate_time())) {
            holder.tvShijian.setText("创建时间：" + dingdan.getCreate_time());
        } else {
            holder.tvShijian.setVisibility(View.GONE);
        }

        if (mOnItemClickListener != null) {
            if (isClick) {
                holder.btFukuan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onClick(v, position);
                    }
                });
                holder.btPingjia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onClick(v, position);
                    }
                });
                holder.btQuxiao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onClick(v, position);
                    }
                });
                holder.btShouhuo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onClick(v, position);
                    }
                });
                holder.btZaimai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onClick(v, position);
                    }
                });
                holder.ll_saoma.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onClick(v, position);
                    }
                });
                holder.ll_rongqi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onClick(v, position);
                    }
                });
                holder.tv_quhuoma.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onClick(v, position);
                    }
                });
                holder.ivShanchu.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            dialog = new ConfirmDialog(mContext,
                                                                    mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
                                                            dialog.showDialog("是否确认删除订单");
                                                            dialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View v) {
                                                                    dialog.dismiss();
                                                                    delOrder(dingdan.getOrder_id(), position);
                                                                }
                                                            });
                                                            dialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View v) {
                                                                    dialog.cancel();
                                                                }
                                                            });
                                                        }
                                                    }
                );
            } else {
                ToastUtil.showToastLong("注意，您只有阅览权限");
            }
            holder.btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    mOnItemClickListener.onClick(v, position);
                    if (holder.btnMore.getText().equals("收起")) {
                        holder.btnMore.setText("展开");
                        holder.rvShangpinliebiao.setVisibility(View.GONE);
                    } else {
                        holder.btnMore.setText("收起");
                        holder.rvShangpinliebiao.setVisibility(View.VISIBLE);
                    }

                }
            });
        }

    }

    private String zhuangtai(ViewHolder holder, int zhuantai) {
        switch (zhuantai) {
            case 401:
                holder.btQuxiao.setVisibility(View.VISIBLE);
                holder.btFukuan.setVisibility(View.GONE);
                holder.tvTishi.setText("您还没有付款哦~");
                return "待买家付款";
//已付款409  已评价 408  未评价  407 已取消  406  已收货 405 已发货404  待发货 402 待付款 401
            case 402:
                holder.btZaimai.setVisibility(View.VISIBLE);
                holder.tvTishi.setText("卖家正在备货，请耐心等待~");
                return "待卖家发货";
            //待发货
            case 404:
                holder.ll_baozhuang.setVisibility(View.VISIBLE);
                holder.tvTishi.setVisibility(View.GONE);
                holder.btShouhuo.setVisibility(View.VISIBLE);
                return "卖家已发货";
            //已发货
            case 405:
                holder.llShanchu.setVisibility(View.VISIBLE);
                holder.tvTishi.setText("本次订单已完成，欢迎您再次购买~");
                holder.btPingjia.setVisibility(View.VISIBLE);
                holder.btZaimai.setVisibility(View.VISIBLE);
                return "已完成";
            case 406:
                holder.tvTishi.setText("您已确认收货，快去付款吧~");
                holder.btPingjia.setVisibility(View.VISIBLE);
                holder.btZaimai.setVisibility(View.VISIBLE);
                return "买家已收货";
            case 407:
                holder.tvTishi.setText("本次订单已完成，欢迎您再次购买~");
                holder.btPingjia.setVisibility(View.VISIBLE);
                holder.btZaimai.setVisibility(View.VISIBLE);
                return "未评价";
            case 409:
                holder.btZaimai.setVisibility(View.VISIBLE);
                return "买家已付款";
//            case 406:
//                holder.btZaimai.setVisibility(View.VISIBLE);
//                return "已取消";
        }
        return "";
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_dingdanbianhao)
        TextView tvDingdanbianhao;
        @BindView(R.id.tv_zhuangtai)
        TextView tvZhuangtai;
        @BindView(R.id.iv_shanchu)
        ImageView ivShanchu;
        @BindView(R.id.rv_shangpinliebiao)
        RecyclerView rvShangpinliebiao;
        @BindView(R.id.tv_sp_shuliang)
        TextView tvSpShuliang;
        @BindView(R.id.tv_zongjia1)
        TextView tvZongjia1;
        @BindView(R.id.tv_zongjia2)
        TextView tvZongjia2;
        @BindView(R.id.bt_quxiao)
        Button btQuxiao;
        @BindView(R.id.bt_fukuan)
        Button btFukuan;
        @BindView(R.id.bt_shouhuo)
        Button btShouhuo;
        @BindView(R.id.bt_zaimai)
        Button btZaimai;
        @BindView(R.id.bt_pingjia)
        Button btPingjia;
        @BindView(R.id.ll_saoma)
        LinearLayout ll_saoma;
        @BindView(R.id.ll_rongqi)
        LinearLayout ll_rongqi;
        @BindView(R.id.tv_quhuoma)
        TextView tv_quhuoma;
        @BindView(R.id.tv_baozhuanggeshu)
        TextView tv_baozhuanggeshu;
        @BindView(R.id.tv_saomageshu)
        TextView tv_saomageshu;
        @BindView(R.id.ll_baozhuang)
        LinearLayout ll_baozhuang;
        @BindView(R.id.tv_tishi)
        TextView tvTishi;
        @BindView(R.id.ll_state)
        LinearLayout llState;
        @BindView(R.id.ll_chaoshifei)
        LinearLayout llChaoshifei;
        @BindView(R.id.ll_shanchu)
        LinearLayout llShanchu;
        @BindView(R.id.btn_more)
        Button btnMore;
        @BindView(R.id.tv_shijian)
        TextView tvShijian;
        @BindView(R.id.tv_chaoshifei1)
        TextView tvChaoshifei1;
        @BindView(R.id.tv_chaoshifei2)
        TextView tvChaoshifei2;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private void delOrder(String order_id, final int pos) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .delOrder(PreferenceUtils.getString(MyApplication.mContext, "token", ""), order_id))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String s) {
                        ToastUtil.showToast("订单删除成功");
                        mList.remove(pos);
                        notifyDataSetChanged();
                    }
                });
    }
}
