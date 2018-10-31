package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.XuanZeYinHangKaBean;
import com.mingmen.mayi.mayibanjia.ui.view.CircleImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/10/22.
 */

public class XuanZeYinHangKaAdapter extends RecyclerView.Adapter<XuanZeYinHangKaAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<XuanZeYinHangKaBean> mList;
    private CallBack callBack;
    public XuanZeYinHangKaAdapter(Context mContext, List<XuanZeYinHangKaBean> list,CallBack callBack) {
        this.mContext = mContext;
        this.mList = list;
        this.callBack = callBack;
    }
    public interface CallBack{
        void xuanzhong(String msg,String son_name);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_xuanzeyinhangka, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final XuanZeYinHangKaBean bean = mList.get(position);
        Glide.with(mContext).load(bean.getLog_url()).into(holder.ivTubiao);
        holder.tvYinhang.setText(bean.getSon_name());

        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.xuanzhong(bean.getSon_number(),bean.getSon_name());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_tubiao)
        CircleImageView ivTubiao;
        @BindView(R.id.tv_yinhang)
        TextView tvYinhang;
        @BindView(R.id.ll_item)
        LinearLayout llItem;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
