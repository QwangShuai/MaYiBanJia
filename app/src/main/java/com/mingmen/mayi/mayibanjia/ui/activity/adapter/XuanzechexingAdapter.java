package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.CheliangBean;
import com.mingmen.mayi.mayibanjia.bean.WuliuDingdanBean;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.YunFeiDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/26.
 */

public class XuanzechexingAdapter extends RecyclerView.Adapter<XuanzechexingAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<CheliangBean> mList;

    private CallBack callBack;

    public interface CallBack{
        void succeed(CheliangBean bean);
    };

    public XuanzechexingAdapter(Context context, List<CheliangBean> list,CallBack callBack) {
        this.mContext = context;
        this.mList = list;
        this.callBack = callBack;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_xuanzechexing, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        holder.setIsRecyclable(false);
        final CheliangBean bean = mList.get(position);
        holder.tvChepaihao.setText(bean.getNew_plate_number());
        holder.tvLianxifangshi.setText(bean.getNew_driver_phone());
        holder.tvLianxiren.setText(bean.getNew_driver_name());
        holder.tvXuanzhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.succeed(bean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_lianxiren)
        TextView tvLianxiren;
        @BindView(R.id.tv_lianxifangshi)
        TextView tvLianxifangshi;
        @BindView(R.id.tv_xuanzhong)
        TextView tvXuanzhong;
        @BindView(R.id.tv_chepaihao)
        TextView tvChepaihao;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
