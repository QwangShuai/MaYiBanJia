package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.DianPuBean;
import com.mingmen.mayi.mayibanjia.bean.ZxxzQiyeBean;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/13/013.
 */
//店铺名称模糊查询
public class ZxxzDianPuMohuAdapter extends  RecyclerView.Adapter<ZxxzDianPuMohuAdapter.ViewHolder>{
    private ViewHolder viewHolder;
    private Context mContext;
    private List<ZxxzQiyeBean> mList;
    private OnItemClickListener mOnItemClickListener;
    public ZxxzDianPuMohuAdapter(Context mContext, List<ZxxzQiyeBean> list) {
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
    public ZxxzDianPuMohuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mohuchaxun, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ZxxzQiyeBean dianPuBean = mList.get(position);
        holder.tv_dianming.setText(dianPuBean.getCompany_name());
        if (mOnItemClickListener!=null){
            holder.tv_dianming.setOnClickListener(new View.OnClickListener() {
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

        TextView tv_dianming;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            tv_dianming=(TextView)view.findViewById(R.id.tv_ming);

        }
    }

}
