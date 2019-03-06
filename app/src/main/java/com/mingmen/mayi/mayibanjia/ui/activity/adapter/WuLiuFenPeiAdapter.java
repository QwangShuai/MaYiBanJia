package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.WuLiuBean;
import com.mingmen.mayi.mayibanjia.ui.activity.WuLiuActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ChangeWuLiuDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.FenPeiWuLiuCheDialog;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/26.
 */

public class WuLiuFenPeiAdapter extends  RecyclerView.Adapter<WuLiuFenPeiAdapter.ViewHolder> {
    private ViewHolder viewHolder;
    private Context mContext;
    private List<WuLiuBean> mList;
    private WuLiuActivity activity;
    private String type="0";
    public WuLiuFenPeiAdapter(Context context,List<WuLiuBean> list,WuLiuActivity activity,String type){
        this.mContext = context;
        this.mList = list;
        this.activity = activity;
        this.type = type;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new WuLiuFenPeiAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wuliu, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final WuLiuBean data = mList.get(position);
        if (data.getWl_cars_state().equals("0")){
            holder.tv_orfenche.setText("未分车");
        } else if(data.getWl_cars_state().equals("1")){
            holder.tv_orfenche.setText("已分车");
        } else {
            holder.tv_orfenche.setText("已变更");
            holder.tv_orfenche.setTextColor(R.color.red_ff3300);
        }
        holder.tv_fenpeiwuliuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//分配物流车
                if(data.getWl_cars_state().equals("0")){
                    FenPeiWuLiuCheDialog dialog = new FenPeiWuLiuCheDialog(mContext,mList.get(position),activity,type);
                    dialog.show();
                } else {
                    ToastUtil.showToast("此单已分配物流车");
                }

            }
        });
        holder.tv_biangeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//变更
                if(data.getWl_cars_state().equals("1")){
                    ChangeWuLiuDialog   dialog = new ChangeWuLiuDialog(mContext,mList.get(position),activity,type);
                    dialog.show();
                } else {
                    ToastUtil.showToast("此状态不可变更");
                }

            }
        });
        holder.tv_dingdanbianhao.setText(data.getWl_cars_order_number().toString());
        if(!TextUtils.isEmpty(String.valueOf(data.getArrival_time()))){
            holder.tv_songhuoshijian.setText(String.valueOf(data.getArrival_time()));
        }
        if(!TextUtils.isEmpty(String.valueOf(data.getTotalWeight().toString()))){
            holder.tv_zongzhongliang.setText(data.getTotalWeight().toString());
        }
        holder.tv_yunfei.setText(String.valueOf(data.getFreight_fee()));
        if(!TextUtils.isEmpty(String.valueOf(data.getCarTypeName()))){
            holder.tv_cheliangleixing.setText(String.valueOf(data.getCarTypeName()));
        }
        if(data.getPlateNumber()!=null){
            holder.tv_chepaihao.setText(data.getPlateNumber().toString());
        }
        if (data.getDriverName()!=null){
            holder.tv_lianxiren.setText(data.getDriverName().toString());
        }
        if (data.getDriverPhone()!=null){
            holder.tv_lianxifangshi.setText(data.getDriverPhone().toString());
        }
        holder.tv_shichang.setText(data.getMarketName().toString());
        holder.tv_dizhi.setText(data.getSpecific_address().toString());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_orfenche,tv_biangeng,tv_fenpeiwuliuche,tv_dingdanbianhao,tv_songhuoshijian,
                tv_zongzhongliang,tv_yunfei,tv_cheliangleixing,tv_chepaihao,tv_lianxiren,
                tv_lianxifangshi,tv_shichang,tv_dizhi;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            tv_orfenche=(TextView)view.findViewById(R.id.tv_orfenche);
            tv_biangeng=(TextView)view.findViewById(R.id.tv_biangeng);
            tv_fenpeiwuliuche=(TextView)view.findViewById(R.id.tv_fenpeiwuliuche);
            tv_dingdanbianhao=(TextView)view.findViewById(R.id.tv_dingdanbianhao);
            tv_songhuoshijian=(TextView)view.findViewById(R.id.tv_songhuoshijian);
            tv_zongzhongliang=(TextView)view.findViewById(R.id.tv_zongzhongliang);
            tv_yunfei=(TextView)view.findViewById(R.id.tv_yunfei);
            tv_cheliangleixing=(TextView)view.findViewById(R.id.tv_cheliangleixing);
            tv_chepaihao=(TextView)view.findViewById(R.id.tv_chepaihao);
            tv_lianxiren=(TextView)view.findViewById(R.id.tv_lianxiren);
            tv_lianxifangshi=(TextView)view.findViewById(R.id.tv_lianxifangshi);
            tv_shichang=(TextView)view.findViewById(R.id.tv_shichang);
            tv_dizhi=(TextView)view.findViewById(R.id.tv_dizhi);
        }
    }
}
