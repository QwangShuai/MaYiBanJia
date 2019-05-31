package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.ShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.ShangPinSouSuoBean;
import com.mingmen.mayi.mayibanjia.bean.SouSuoJieGuoShangPinBean;
import com.mingmen.mayi.mayibanjia.ui.activity.SPXiangQingActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.TubiaoActivity;
import com.mingmen.mayi.mayibanjia.utils.GlideUtils;
import com.mingmen.mayi.mayibanjia.utils.JumpUtil;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.custom.MarqueeTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/13/013.
 */

public class ShangPinListAdapter extends RecyclerView.Adapter<ShangPinListAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<ShangPinSouSuoBean.ZhengchangBean> mList;
    private OnItemClickListener mOnItemClickListener;

    public boolean isTeshu() {
        return isTeshu;
    }

    public void setTeshu(boolean teshu) {
        isTeshu = teshu;
    }

    private boolean isTeshu;

    public ShangPinListAdapter(Context mContext, List<ShangPinSouSuoBean.ZhengchangBean> list) {
        this.mContext = mContext;
        this.mList = list;
    }
    public void setmList(List<ShangPinSouSuoBean.ZhengchangBean> list){
        this.mList = list;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shouye_tejia, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ShangPinSouSuoBean.ZhengchangBean data = mList.get(position);
        holder.ivJishida.setVisibility(View.GONE);
        //holder.tvSpming.setText(data.getClassify_name());
//        holder.tvSpming.setMarqueeEnable(true);
        if(data.getGoods().equals("1")){
            holder.tvYuanjiage.setVisibility(View.VISIBLE);
            holder.tvYuanjiage.setText(data.getPice_one());
            holder.tvYuanjiage.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.tvYuanjiage.setVisibility(View.GONE);
        }
        holder.tvGuigeMiaoshu.setVisibility(StringUtil.isValid(data.getPackStandard())?View.VISIBLE:View.GONE);
        if(StringUtil.isValid(data.getPackStandard())){
            holder.tvGuigeMiaoshu.setText(data.getPackStandard());
        }
        holder.tvSpming.setText(data.getClassify_name());
        holder.tvDianming.setText(data.getCompany_name()+"("+data.getMarket_name()+")");
        holder.tvJiage.setText(data.getPrice());
        holder.tvSpxiaoliang.setText("已售"+data.getCommodity_sales());
        Log.e("onBindViewHolder: ",data.getReal_time_state()+"---");
        if(StringUtil.isValid(data.getReal_time_state())&&data.getReal_time_state().equals("0")){
            holder.ivJishida.setVisibility(View.VISIBLE);
        } else {
            holder.ivJishida.setVisibility(View.GONE);
        }
        GlideUtils.cachePhoto(mContext,holder.ivSptu,data.getPicture_url());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putBoolean("teshu",isTeshu);
                bundle.putString("spid",data.getCommodity_id());
                JumpUtil.Jump_intent(mContext, SPXiangQingActivity.class,bundle);
            }
        });
        holder.ivZoushitu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent zoushi=new Intent(mContext,TubiaoActivity.class);
                zoushi.putExtra("mark_id",data.getSon_number());//市场id
                zoushi.putExtra("market_name",data.getMarket_name());//市场名
                zoushi.putExtra("classify_id",data.getType_four_id());//三级分类名称
                zoushi.putExtra("classify_name",data.getClassify_name());//三级分类名称
                //Log.e("classify_name",data.getSon_number()+"="+data.getClassify_name()+"="+data.getMarket_name()+"="+data.getType_tree_id());
                mContext.startActivity(zoushi);
            }
        });
        if (mOnItemClickListener != null) {
            holder.ivAddcar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(v, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_sptu)
        ImageView ivSptu;
//        @BindView(R.id.tv_spming)
//        MarqueeTextView tvSpming;
        @BindView(R.id.tv_spming)
        TextView tvSpming;
        @BindView(R.id.tv_dianming)
        TextView tvDianming;
        @BindView(R.id.tv_guige_miaoshu)
        TextView tvGuigeMiaoshu;
/*        @BindView(R.id.tv_guige)
        TextView tvGuige;*/
        @BindView(R.id.tv_renminbi)
        TextView tvRenminbi;
        @BindView(R.id.tv_jiage)
        TextView tvJiage;
        @BindView(R.id.tv_yuanjiage)
        TextView tvYuanjiage;
        @BindView(R.id.tv_danjia)
        TextView tvDanjia;
        @BindView(R.id.tv_spxiaoliang)
        TextView tvSpxiaoliang;
        @BindView(R.id.tv_dian)
        TextView tvDian;
        @BindView(R.id.iv_addcar)
        ImageView ivAddcar;
        @BindView(R.id.iv_jishida)
        ImageView ivJishida;
        @BindView(R.id.cl_kuang)
        RelativeLayout clKuang;
        @BindView(R.id.iv_zoushitu)
        ImageView ivZoushitu;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
