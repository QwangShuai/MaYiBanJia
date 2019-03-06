package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.FCGName;
import com.mingmen.mayi.mayibanjia.ui.activity.AddShangPinActivity;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/8/30.
 */

public class AddSpFourAdapter extends RecyclerView.Adapter<AddSpFourAdapter.ViewHolder> {
    private ViewHolder viewHolder;
    private CallBack mCallBack;
    private String xuanzhongid = "";
    private String type;
    private AddShangPinActivity activity;
    private Context mContext;
    private List<FCGName> list;

    public AddSpFourAdapter(Context mContext, List<FCGName> list) {
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
        final FCGName bean = list.get(position);
        if (StringUtil.isValid(type)) {
            if (bean.isSelect()) {
                holder.tv_ming.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_solid_zangqing_3));
                holder.tv_ming.setTextColor(mContext.getResources().getColor(R.color.white));
            } else {
                holder.tv_ming.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_hollow_999999_3));
                holder.tv_ming.setTextColor(mContext.getResources().getColor(R.color.zicolor));
            }
//            activity.onChangeMap(bean, bean.isSelect());
        } else {
            if (StringUtil.isValid(xuanzhongid)) {
                if (xuanzhongid.equals(bean.getClassify_id())) {
                    holder.tv_ming.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_solid_zangqing_3));
                    holder.tv_ming.setTextColor(mContext.getResources().getColor(R.color.white));
                } else {
                    holder.tv_ming.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_hollow_999999_3));
                    holder.tv_ming.setTextColor(mContext.getResources().getColor(R.color.zicolor));
                }
            }
        }

        holder.tv_ming.setText(bean.getClassify_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallBack != null) {
                    mCallBack.xuanzhong(bean);
                }
//                if (StringUtil.isValid(type)) {
//                    boolean b = !bean.isSelect();
//                    list.get(position).setSelect(b);
//                    activity.onChangeMap(bean, b);
//                } else {
                    xuanzhongid = bean.getClassify_id();
//                }
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
    }

    public AddSpFourAdapter setCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
        return this;
    }

    ;

    public interface CallBack {
        void xuanzhong(FCGName msg);
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

//    public void setType(String type) {
//        this.type = type;
//    }


    public void setActivity(AddShangPinActivity activity) {
        this.activity = activity;
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
