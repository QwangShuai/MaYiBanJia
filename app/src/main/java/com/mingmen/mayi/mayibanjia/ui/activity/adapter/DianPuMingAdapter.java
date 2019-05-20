package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.DianMingChaXunBean;
import com.mingmen.mayi.mayibanjia.utils.GlideUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/13/013.
 */
//搜索店铺模糊查询列表
public class DianPuMingAdapter extends  RecyclerView.Adapter<DianPuMingAdapter.ViewHolder>{
    private ViewHolder viewHolder;
    private Context mContext;
    private List<DianMingChaXunBean> mList;
    private OnItemClickListener mOnItemClickListener;
    public DianPuMingAdapter(Context mContext, List<DianMingChaXunBean> list, String chaxun) {
        this.mContext = mContext;
        this.mList = list;
    }
    public interface OnItemClickListener{
        void onClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this. mOnItemClickListener=onItemClickListener;
    }
    @Override
    public DianPuMingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sousuodianpu, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        DianMingChaXunBean string = mList.get(position);
        String zi = string.getCompany_name().toString().trim();
        holder.tv_ming.setText(zi);
        GlideUtils.cachePhoto(mContext,holder.iv_touxiang,string.getPhoto());
        if (mOnItemClickListener!=null){
            holder.tv_ming.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(v,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_ming;
        ImageView iv_touxiang;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            tv_ming=(TextView)view.findViewById(R.id.tv_ming);
            iv_touxiang=(ImageView) view.findViewById(R.id.iv_touxiang);
        }
    }

}
