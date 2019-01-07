package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.QiYeLieBiaoBean;
import com.mingmen.mayi.mayibanjia.bean.QuanBuShiChangBean;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/13/013.
 */

public class QuanbuShichangAdapter extends RecyclerView.Adapter<QuanbuShichangAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<QuanBuShiChangBean> mList;
    private OnItemClickListener mOnItemClickListener;
    private Intent it;

    public QuanbuShichangAdapter(Context mContext, List<QuanBuShiChangBean> list) {
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
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quanbushichang, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final QuanBuShiChangBean data = mList.get(position);
        holder.tvName.setText(data.getMarket_name());
        holder.tvNum.setText("商户数量: "+ data.getNumber());
        holder.tvLoc.setText(data.getSpecific_address());
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_loc)
        TextView tvLoc;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

//    public void CallPhone(String phone) {
//        Intent intent = new Intent(Intent.ACTION_DIAL);
//        Uri data = Uri.parse("tel:" + phone);
//        intent.setData(data);
//        mContext.startActivity(intent);
//    }
}
