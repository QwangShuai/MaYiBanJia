package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
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
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.PeiSongXiangQingActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.SiJiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.WeiYiQrCodeActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.DiTuDialog;
import com.mingmen.mayi.mayibanjia.utils.JumpUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.custom.RecycleViewDivider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/26.
 */

public class SJPSXiangQingAdapter extends RecyclerView.Adapter<SJPSXiangQingAdapter.ViewHolder> {
    private ViewHolder viewHolder;
    private Context mContext;
    private List<SiJiWLXQBean> mList;
    private PeiSongXiangQingActivity activity;
    private SJPSXiangQingTwoAdapter adapter;
    private boolean[] b;
    public SJPSXiangQingAdapter(Context context, List<SiJiWLXQBean> list, PeiSongXiangQingActivity activity) {
        this.mContext = context;
        this.mList = list;
        this.activity = activity;
        b = new boolean[list==null?0:list.size()];
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new SJPSXiangQingAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ps_xiang_qing, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final SiJiWLXQBean data = mList.get(position);
        if (data.getWl_order_state().equals("待取货")) {

        } else {
            holder.tv_state.setTextColor(R.color.zicolor);
        }
        if(TextUtils.isEmpty(String.valueOf(data.getWl_order_state()))){
            holder.tv_state.setText("");
        }else{
            holder.tv_state.setText(String.valueOf(data.getWl_order_state()));
        }
        adapter = new SJPSXiangQingTwoAdapter(mContext,data.getGyMsgList());
        holder.rvList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        holder.rvList.setAdapter(adapter);
        holder.rvList.setFocusable(false);
//        holder.rvList.addItemDecoration(new RecycleViewDivider(
//                mContext, LinearLayoutManager.VERTICAL));
        holder.tv_peisongcanting.setText(String.valueOf(data.getCtName()));
        holder.tv_peisongdizhi.setText(data.getCtAddress());
        holder.tv_cantingdianhua.setText(data.getCtPhone());
        holder.tvLable.setText(data.getIdentifying());
        holder.tv_ditu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpManager.getInstance()
                        .with(mContext)
                        .setObservable(RetrofitManager.getService()
                                .getJingweidu(data.getCtAddress() + ""))
                        .setDataListener(new HttpDataListener<String>() {
                            @Override
                            public void onNext(String weizhi) {
                                DiTuDialog dialog = new DiTuDialog();
                                dialog.setData(mContext, weizhi.split(",")[1], weizhi.split(",")[0], data.getCtAddress());
                                dialog.show(activity.getSupportFragmentManager());
                            }
                        });
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b[position]){
                    holder.rvList.setVisibility(View.GONE);
                    b[position] = false;
                } else {
                    holder.rvList.setVisibility(View.VISIBLE);
                    b[position] = true;
                }
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
        // @BindView(R.id.tv_quhuotanwei)
//         TextView tv_quhuotanwei;
//        @BindView(R.id.tv_lianxidianhua)
//        TextView tv_lianxidianhua;
//        @BindView(R.id.tv_baozhuanggeshu)
//        TextView tv_baozhuanggeshu;
//        @BindView(R.id.tv_saomageshu)
//        TextView tv_saomageshu;
        @BindView(R.id.tv_peisongcanting)
        TextView tv_peisongcanting;
        @BindView(R.id.tv_peisongdizhi)
        TextView tv_peisongdizhi;
        @BindView(R.id.tv_cantingdianhua)
        TextView tv_cantingdianhua;
        @BindView(R.id.tv_lable)
        TextView tvLable;
//        @BindView(R.id.ll_saoma)
//        LinearLayout ll_saoma;
//        @BindView(R.id.ll_baozhuang)
//        LinearLayout ll_baozhuang;
//        @BindView(R.id.ll_saomageshu)
//        LinearLayout ll_saomageshu;
//        @BindView(R.id.tv_quhuoma)
//        TextView tv_quhuoma;
        @BindView(R.id.rv_list)
        RecyclerView rvList;
        @BindView(R.id.tv_ditu)
        TextView tv_ditu;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
}
