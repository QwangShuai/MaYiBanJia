package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.WuliuDingdanBean;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ChangeWuLiuDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.FenPeiWuLiuCheDialog;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/26.
 */

public class JujuelishiAdapter extends RecyclerView.Adapter<JujuelishiAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<WuliuDingdanBean> mList;
    private Activity activity;

    public JujuelishiAdapter(Context context, List<WuliuDingdanBean> list, Activity activity) {
        this.mContext = context;
        this.mList = list;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jujuelishi, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final WuliuDingdanBean data = mList.get(position);
        holder.tvChuangjianshijian.setText(data.getCreate_time());
        holder.tvDingdanbianhao.setText(data.getWl_cars_order_number().toString());
        if (!TextUtils.isEmpty(String.valueOf(data.getArrival_time()))) {
            holder.tvSonghuoshijian.setText(String.valueOf(data.getArrival_time()));
        }
        if (!TextUtils.isEmpty(String.valueOf(data.getCarTypeName()))) {
            holder.tvCheliangleixing.setText(String.valueOf(data.getCarTypeName()));
        }
        if (data.getPlateNumber() != null) {
            holder.tvChepaihao.setText(data.getPlateNumber().toString());
        }
        if (data.getDriverName() != null) {
            holder.tvLianxiren.setText(data.getDriverName().toString());
        }
        if (data.getDriverPhone() != null) {
            holder.tvLianxifangshi.setText(data.getDriverPhone().toString());
        }
        if(StringUtil.isValid(data.getRemarke())){
            holder.llJujueliyou.setVisibility(View.VISIBLE);
            holder.tvJujueliyou.setText("拒绝理由:"+data.getRemarke());
        } else {
            holder.llJujueliyou.setVisibility(View.GONE);
        }
        holder.tvShichang.setText(data.getMarketName().toString());
        holder.tvDizhi.setText(data.getSpecific_address().toString());
        holder.llZhankai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.llShow.setVisibility(View.VISIBLE);
                holder.llZhankai.setVisibility(View.GONE);
            }
        });
        holder.llShouqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.llShow.setVisibility(View.GONE);
                holder.llZhankai.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.dingdangbianhao)
        TextView dingdangbianhao;
        @BindView(R.id.tv_dingdanbianhao)
        TextView tvDingdanbianhao;
        @BindView(R.id.chuangjianshijian)
        TextView chuangjianshijian;
        @BindView(R.id.tv_chuangjianshijian)
        TextView tvChuangjianshijian;
        @BindView(R.id.songhuoshijian)
        TextView songhuoshijian;
        @BindView(R.id.tv_songhuoshijian)
        TextView tvSonghuoshijian;
        @BindView(R.id.yunfei)
        TextView yunfei;
        @BindView(R.id.tv_yunfei)
        TextView tvYunfei;
        @BindView(R.id.ll_zhankai)
        LinearLayout llZhankai;
        @BindView(R.id.cheliangleixing)
        TextView cheliangleixing;
        @BindView(R.id.tv_cheliangleixing)
        TextView tvCheliangleixing;
        @BindView(R.id.chepaihao)
        TextView chepaihao;
        @BindView(R.id.tv_chepaihao)
        TextView tvChepaihao;
        @BindView(R.id.lianxiren)
        TextView lianxiren;
        @BindView(R.id.tv_lianxiren)
        TextView tvLianxiren;
        @BindView(R.id.lianxifangshi)
        TextView lianxifangshi;
        @BindView(R.id.tv_lianxifangshi)
        TextView tvLianxifangshi;
        @BindView(R.id.shichang)
        TextView shichang;
        @BindView(R.id.tv_shichang)
        TextView tvShichang;
        @BindView(R.id.dizhi)
        TextView dizhi;
        @BindView(R.id.tv_dizhi)
        TextView tvDizhi;
        @BindView(R.id.tv_jujueliyou)
        TextView tvJujueliyou;
        @BindView(R.id.ll_show)
        LinearLayout llShow;
        @BindView(R.id.ll_jujueliyou)
        LinearLayout llJujueliyou;
        @BindView(R.id.ll_shouqi)
        LinearLayout llShouqi;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
