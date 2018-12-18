package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.QiYeLieBiaoBean;
import com.mingmen.mayi.mayibanjia.ui.activity.ghdingdan.GHDOrderActivity;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/13/013.
 */

public class QiYeLieBiaoAdapter extends RecyclerView.Adapter<QiYeLieBiaoAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<QiYeLieBiaoBean> mList;
    private OnItemClickListener mOnItemClickListener;

    public QiYeLieBiaoAdapter(Context mContext, List<QiYeLieBiaoBean> list) {
        this.mContext = mContext;
        this.mList = list;
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qiyeliebiao, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final QiYeLieBiaoBean data = mList.get(position);
//        String zi = string.getCompany_name().toString().trim();
//        holder.tv_ming.setText(zi);
//        holder.tvName.setText(data.getLinkman());
//        holder.tvPhone.setText(data.getContact_type());
//        holder.tvDizhi.setText(data.getProvince_name()+data.getCity_name()+data.getRegion_name()+data.getSpecific_address());
//        if (Integer.parseInt(data.getDefault_address())!=0){
//            holder.tvMoren.setVisibility(View.GONE);
//        }else{
//            holder.tvMoren.setVisibility(View.VISIBLE);
//        }
        holder.tvMingzi.setText(data.getCompany_name());
        holder.tvLeibie.setText(data.getLeiBieName());
        holder.tvGuimo.setText(data.getGuiMoId());
        Glide.with(mContext).load(data.getPhoto()).into(holder.ivTouxiang);
        Log.e("data.getsss", data.getCompany_id() + "-");
        if (data.getSpecific_address() != null) {
            holder.tvDizhi.setText(data.getQuYMC() + data.getQuYMCa() + data.getQuYMCb() + data.getQuYMCc() + data.getSpecific_address());
        } else {
            holder.tvDizhi.setText(data.getQuYMC() + data.getQuYMCa() + data.getQuYMCb() + data.getQuYMCc());
        }

        if(!StringUtil.isValid(data.getRole())){
            holder.tvDingdanCtd.setVisibility(View.GONE);
            holder.tvDingdanGyd.setVisibility(View.GONE);
            holder.tvShangpinPt.setVisibility(View.GONE);
            holder.tvShangpinTj.setVisibility(View.GONE);
        } else if(data.getRole().equals("1")){
            holder.tvDingdanGyd.setVisibility(View.GONE);
            holder.tvShangpinPt.setVisibility(View.GONE);
            holder.tvShangpinTj.setVisibility(View.GONE);
        }

        if (mOnItemClickListener != null) {
            holder.llBianji.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(v, position);
                }
            });
            holder.tvDingdanGyd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(mContext, GHDOrderActivity.class);
                    it.putExtra("token",data.getUser_token());
                    mContext.startActivity(it);
                }
            });
            holder.tvShangpinPt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showToast("普通");
                }
            });
            holder.tvDingdanCtd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showToast("餐厅端");
                }
            });
            holder.tvShangpinTj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showToast("特价");
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_mingzi)
        TextView tvMingzi;
        @BindView(R.id.tv_leibie)
        TextView tvLeibie;
        @BindView(R.id.tv_guimo)
        TextView tvGuimo;
        @BindView(R.id.tv_dizhi)
        TextView tvDizhi;
        @BindView(R.id.iv_bianji)
        ImageView ivBianji;
        @BindView(R.id.ll_bianji)
        LinearLayout llBianji;
        @BindView(R.id.iv_touxiang)
        ImageView ivTouxiang;
        @BindView(R.id.tv_dingdan_gyd)
        TextView tvDingdanGyd;
        @BindView(R.id.tv_dingdan_ctd)
        TextView tvDingdanCtd;
        @BindView(R.id.tv_shangpin_pt)
        TextView tvShangpinPt;
        @BindView(R.id.tv_shangpin_tj)
        TextView tvShangpinTj;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
