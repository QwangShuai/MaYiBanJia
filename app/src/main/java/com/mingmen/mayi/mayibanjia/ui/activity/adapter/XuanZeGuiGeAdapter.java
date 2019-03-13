package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.FbspGuiGeBean;
import com.mingmen.mayi.mayibanjia.ui.activity.DaYinQrCodeActivity;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/26.
 */

public class XuanZeGuiGeAdapter extends RecyclerView.Adapter<XuanZeGuiGeAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<FbspGuiGeBean> mList;
    public CallBack callBack;
    private FbspGuiGeBean xzBean;

    public void setXzBean(FbspGuiGeBean xzBean) {
        this.xzBean = xzBean;
    }
    public interface CallBack {
        void isClick(View v, int pos);
    }
    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public XuanZeGuiGeAdapter(Context context, List<FbspGuiGeBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_xuanzeguige, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final FbspGuiGeBean bean = mList.get(position);
        if(xzBean.getAffiliated_number().equals(bean.getAffiliated_number())
                &&xzBean.getSpec_id().equals(bean.getSpec_id())
                &&xzBean.getAffiliated_spec().equals(bean.getAffiliated_spec())){
            holder.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.black_f2000000));
        } else {
            holder.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
        if(!StringUtil.isValid(bean.getAffiliated_spec())){
            holder.llDw.setVisibility(View.GONE);
        }
        holder.tvSanji.setText(bean.getSpec_name());
        holder.tvNumber.setText(bean.getAffiliated_number());
        holder.tvZxgg.setText(bean.getAffiliated_spec_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.isClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_sanji)
        TextView tvSanji;
        @BindView(R.id.tv_number)
        TextView tvNumber;
        @BindView(R.id.tv_zxgg)
        TextView tvZxgg;
        @BindView(R.id.ll_dw)
        LinearLayout llDw;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
