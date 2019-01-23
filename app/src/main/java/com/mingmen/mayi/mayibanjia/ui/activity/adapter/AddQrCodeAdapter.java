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
import com.mingmen.mayi.mayibanjia.bean.AddQrCodeBean;
import com.mingmen.mayi.mayibanjia.ui.activity.AddQrCodeActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.DaYinQrCodeActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/26.
 */

public class AddQrCodeAdapter extends RecyclerView.Adapter<AddQrCodeAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<AddQrCodeBean> mList;
//    private AddQrCodeActivity activity;
    private String id;

//    public AddQrCodeAdapter(Context context, List<AddQrCodeBean> list, AddQrCodeActivity activity) {
    public AddQrCodeAdapter(Context context, List<AddQrCodeBean> list,String id) {
        this.mContext = context;
        this.mList = list;
//        this.activity = activity;
        this.id = id;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_qrcode, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        holder.setIsRecyclable(false);
        final AddQrCodeBean bean = mList.get(position);
        if(bean.getIs_true().equals("0")){
            holder.btnState.setText("已打包");
            holder.btnState.setVisibility(View.GONE);
            holder.tvSpbaozhuang.setVisibility(View.VISIBLE);
            holder.llYdb.setVisibility(View.VISIBLE);
            holder.llYdb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tiaozhuan(bean.getCommodity_id(),"0");
                }
            });

        } else if(bean.getIs_true().equals("1")){
            holder.btnState.setText("待确认");
            holder.btnState.setVisibility(View.VISIBLE);
            holder.tvSpbaozhuang.setVisibility(View.VISIBLE);
            holder.llYdb.setVisibility(View.GONE);
            holder.btnState.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tiaozhuan(bean.getCommodity_id(),"1");
                }
            });
        } else {
            holder.btnState.setText("未打包");
            holder.btnState.setVisibility(View.VISIBLE);
            holder.tvSpbaozhuang.setVisibility(View.GONE);
            holder.llYdb.setVisibility(View.GONE);
            holder.btnState.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tiaozhuan(bean.getCommodity_id(),"2");
                }
            });
        }
        Glide.with(mContext).load(bean.getUrl()).into(holder.ivTouxiang);
        holder.tvSpming.setText(bean.getClassify_name());
        holder.tvSpnumber.setText("数量："+bean.getAcount());
        holder.tvSpbaozhuang.setText("包装个数："+bean.getNumber());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_touxiang)
        ImageView ivTouxiang;
        @BindView(R.id.tv_spming)
        TextView tvSpming;
        @BindView(R.id.tv_spnumber)
        TextView tvSpnumber;
        @BindView(R.id.tv_spbaozhuang)
        TextView tvSpbaozhuang;
        @BindView(R.id.btn_state)
        Button btnState;
        @BindView(R.id.ll_ydb)
        LinearLayout llYdb;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private void tiaozhuan(String sp_id,String type){
        Intent it = new Intent(mContext, DaYinQrCodeActivity.class);
        it.putExtra("id",id );
        it.putExtra("sp_id",sp_id);
        it.putExtra("type",type);
        mContext.startActivity(it);
    }
}
