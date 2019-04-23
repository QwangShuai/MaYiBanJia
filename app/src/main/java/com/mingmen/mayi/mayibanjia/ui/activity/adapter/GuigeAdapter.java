package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.FCGGuige;
import com.mingmen.mayi.mayibanjia.bean.FbspGuiGeBean;
import com.mingmen.mayi.mayibanjia.bean.ShangPinSousuoMohuBean;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/8/30.
 */

public class GuigeAdapter extends RecyclerView.Adapter<GuigeAdapter.ViewHolder> {
    private ViewHolder viewHolder;
    private CallBack mCallBack;
    private String xuanzhongid = "";
    private Context mContext;
    private List<FbspGuiGeBean> list;

    public GuigeAdapter(Context mContext, List<FbspGuiGeBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_yijipinlei, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final FbspGuiGeBean bean = list.get(position);
        if (StringUtil.isValid(xuanzhongid)) {
            if (xuanzhongid.equals(bean.getSpec_id())) {
                holder.tv_ming.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_solid_zangqing_3));
                holder.tv_ming.setTextColor(mContext.getResources().getColor(R.color.white));
            } else {
                holder.tv_ming.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_hollow_999999_3));
                holder.tv_ming.setTextColor(mContext.getResources().getColor(R.color.zicolor));
            }
        }
        holder.tv_ming.setText(bean.getSpec_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallBack != null) {
                    mCallBack.xuanzhong(bean);
                    xuanzhongid = bean.getSpec_id();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    public String getXuanzhongid() {
        return xuanzhongid;
    }

    public void setXuanzhongid(String xuanzhongid) {
        this.xuanzhongid = xuanzhongid;
        notifyDataSetChanged();
    }

    public GuigeAdapter setCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
        return this;
    }

    ;

    public interface CallBack {
        void xuanzhong(FbspGuiGeBean msg);
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_ming;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            tv_ming = (TextView) view.findViewById(R.id.tv_yijifenlei);

        }
    }
}
