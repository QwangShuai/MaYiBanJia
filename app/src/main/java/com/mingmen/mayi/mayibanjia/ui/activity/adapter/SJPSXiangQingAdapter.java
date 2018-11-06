package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.SiJiWLXQBean;
import com.mingmen.mayi.mayibanjia.bean.WuLiuBean;
import com.mingmen.mayi.mayibanjia.ui.activity.PeiSongXiangQingActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.SiJiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.WeiYiQrCodeActivity;
import com.mingmen.mayi.mayibanjia.utils.JumpUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/26.
 */

public class SJPSXiangQingAdapter extends  RecyclerView.Adapter<SJPSXiangQingAdapter.ViewHolder> {
    private ViewHolder viewHolder;
    private Context mContext;
    private List<SiJiWLXQBean> mList;
    private PeiSongXiangQingActivity activity;
    public SJPSXiangQingAdapter(Context context, List<SiJiWLXQBean> list, PeiSongXiangQingActivity activity){
        this.mContext = context;
        this.mList = list;
        this.activity = activity;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new SJPSXiangQingAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ps_xiang_qing, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final SiJiWLXQBean data = mList.get(position);
        if (data.getWl_order_state().equals("待取货")){

        } else{
            holder.tv_state.setTextColor(R.color.zicolor);
        }
        holder.tv_state.setText(String.valueOf(data.getWl_order_state()));
        holder.tv_quhuotanwei.setText(data.getGyAddress());
        holder.tv_lianxidianhua.setText(data.getGyPhone());
        holder.tv_baozhuanggeshu.setText(data.getPackCount());
        holder.tv_saomageshu.setText(data.getScanCount());
        holder.tv_peisongcanting.setText(String.valueOf(data.getCtName()));
        holder.tv_peisongdizhi.setText(data.getCtAddress());
        holder.tv_cantingdianhua.setText(data.getCtPhone());
        if(data.getPackCount().equals("0")||data.getIsTrue().equals("1")){//判断是否显示扫码以及展示数量
            holder.ll_saoma.setVisibility(View.GONE);
            holder.ll_baozhuang.setVisibility(View.GONE);
            holder.ll_saomageshu.setVisibility(View.GONE);
            holder.tv_quhuoma.setVisibility(View.GONE);
        }
        Log.e("212121",data.getPackCount());
        holder.ll_saoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data.getScanCount().equals(data.getPackCount())){
                    ToastUtil.showToast("装车完成");
                } else {
                    activity.saomiaoQrCode();
                }
            }
        });

        holder.tv_quhuoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(mContext,WeiYiQrCodeActivity.class);
                it.putExtra("type","gyID");
                it.putExtra("gyID",String.valueOf(mList.get(position).getGy_order_id()));
                mContext.startActivity(it);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_state)
                TextView tv_state;
        @BindView(R.id.tv_quhuotanwei)
                TextView tv_quhuotanwei;
        @BindView(R.id.tv_lianxidianhua)
                TextView tv_lianxidianhua;
        @BindView(R.id.tv_baozhuanggeshu)
                TextView tv_baozhuanggeshu;
        @BindView(R.id.tv_saomageshu)
                TextView tv_saomageshu;
        @BindView(R.id.tv_peisongcanting)
                TextView tv_peisongcanting;
        @BindView(R.id.tv_peisongdizhi)
                TextView tv_peisongdizhi;
        @BindView(R.id.tv_cantingdianhua)
                TextView tv_cantingdianhua;
        @BindView(R.id.ll_saoma)
        LinearLayout ll_saoma;
        @BindView(R.id.ll_baozhuang)
                LinearLayout ll_baozhuang;
        @BindView(R.id.ll_saomageshu)
                LinearLayout ll_saomageshu;
        @BindView(R.id.tv_quhuoma)
                TextView tv_quhuoma;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
}
