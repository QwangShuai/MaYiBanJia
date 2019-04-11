package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.SiJiWLXQBean;
import com.mingmen.mayi.mayibanjia.ui.activity.AddQrCodeActivity;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.custom.MarqueeTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/26.
 */

public class SJPSXiangQingTwoAdapter extends RecyclerView.Adapter<SJPSXiangQingTwoAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<SiJiWLXQBean.GyMsgListBean> mList;

    public SJPSXiangQingTwoAdapter(Context context, List<SiJiWLXQBean.GyMsgListBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sjps_two, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final SiJiWLXQBean.GyMsgListBean bean = mList.get(position);
        holder.tvQuhuotanwei.setText(bean.getGyAddress());
        holder.tvQuhuotanwei.setMarqueeEnable(true);
        holder.tvGonghuodianhua.setText(bean.getGyPhone());
        holder.tvBaozhuanggeshu.setText(bean.getPackCount());
        holder.tvSaomageshu.setText(bean.getScanCount());
        if(StringUtil.isValid(bean.getBusiness_state())&&bean.getBusiness_state().equals("3")&&bean.getStandthree().equals("3")){
            holder.tvDabao.setVisibility(View.VISIBLE);
            holder.tvDabao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(mContext, AddQrCodeActivity.class);
                    it.putExtra("id", bean.getGy_order_id());
                    mContext.startActivity(it);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_quhuotanwei)
        MarqueeTextView tvQuhuotanwei;
        @BindView(R.id.tv_gonghuodianhua)
        TextView tvGonghuodianhua;
        @BindView(R.id.tv_baozhuanggeshu)
        TextView tvBaozhuanggeshu;
        @BindView(R.id.tv_saomageshu)
        TextView tvSaomageshu;
        @BindView(R.id.tv_chakan)
        TextView tvDabao;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
}
