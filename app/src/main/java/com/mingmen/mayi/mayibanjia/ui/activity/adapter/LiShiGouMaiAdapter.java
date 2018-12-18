package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.XiTongTuiJianBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * List<XiTongTuiJianBean.OrederBuyBean>
 * Created by Administrator on 2018/7/13/013.
 */

public class LiShiGouMaiAdapter extends RecyclerView.Adapter<LiShiGouMaiAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<XiTongTuiJianBean.CcListBean> mList;

    public LiShiGouMaiAdapter(Context mContext, List<XiTongTuiJianBean.CcListBean> list) {
        this.mContext = mContext;
        this.mList = list;
    }

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lishigoumai, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        XiTongTuiJianBean.CcListBean data = mList.get(position);
        holder.tvSpming.setText(data.getClassify_name());
        holder.tvGuige.setText(data.getPack_standard());
//        holder.tvRenminbi.setText(data.getPrice());
        holder.tvJiage.setText(data.getPrice());
        holder.tvDianming.setText(data.getCompany_name());
        holder.tvSpxiaoliang.setText("已售:" + data.getCommodity_sales());
        holder.tvShijian.setText(data.getEnd_time());

        if (mOnItemClickListener != null) {
            holder.llKuang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("sss", "ss");
                    mOnItemClickListener.onClick(v, position);
                }
            });
        }
//
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.tv_spming)
        TextView tvSpming;
        @BindView(R.id.tv_guige)
        TextView tvGuige;
        @BindView(R.id.tv_renminbi)
        TextView tvRenminbi;
        @BindView(R.id.tv_jiage)
        TextView tvJiage;
        @BindView(R.id.tv_danjia)
        TextView tvDanjia;
        @BindView(R.id.tv_spxiaoliang)
        TextView tvSpxiaoliang;
        @BindView(R.id.tv_dian)
        TextView tvDian;
        @BindView(R.id.tv_dianming)
        TextView tvDianming;
        @BindView(R.id.tv_shijian)
        TextView tvShijian;
        @BindView(R.id.ll_kuang)
        LinearLayout llKuang;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
