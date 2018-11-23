package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.WuLiuBean;
import com.mingmen.mayi.mayibanjia.bean.WuLiuObjBean;
import com.mingmen.mayi.mayibanjia.bean.YunFeiJieSuanBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.WuLiuActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.YunFeiJieSuanActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.YunFeiDialog;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/26.
 */

public class YunFeiAdapter extends RecyclerView.Adapter<YunFeiAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<YunFeiJieSuanBean.DdListBean> mList;
    private YunFeiJieSuanActivity activity;
    private boolean[] isSelect;

    public YunFeiAdapter(Context context, List<YunFeiJieSuanBean.DdListBean> list, YunFeiJieSuanActivity activity) {
        this.mContext = context;
        this.mList = list;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_yunfei, parent, false));
        isSelect = new boolean[mList==null?0:mList.size()];
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final YunFeiJieSuanBean.DdListBean bean = mList.get(position);
        if(bean.getSettle_accounts_state().equals("0")){
            holder.btJiesuan.setText("结算");
            holder.ivDanxuan.setVisibility(View.VISIBLE);
        } else if(bean.getSettle_accounts_state().equals("1")){
            holder.btJiesuan.setText("结算中");
            holder.ivDanxuan.setVisibility(View.GONE);
        } else {
            holder.btJiesuan.setText("已完成");
            holder.ivDanxuan.setVisibility(View.GONE);
        }
        if (isSelect[position]){
            activity.addItem(mList.get(position));
        } else {
            activity.delItem(mList.get(position));
        }
        holder.ivDanxuan.setSelected(isSelect[position]);
        holder.tvOrderNumber.setText(bean.getWl_cars_order_number());
        holder.tvJine.setText(bean.getFreight_fee()+"");
        holder.tvEndTime.setText(bean.getChange_time());
        holder.tvQuhuodizhi.setText(bean.getSpecific_address());
        holder.tvSonghuodizhi.setText(bean.getCtAddress());
        holder.btJiesuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.btJiesuan.getText().toString().equals("结算")){
                    YunFeiDialog dialog = new YunFeiDialog();
                    dialog.setdata(mContext,bean.getFreight_fee(), new YunFeiDialog.CallBack() {
                        @Override
                        public void succeed(String jine) {
                            jiesuan(bean.getWl_cars_order_number(),bean.getFreight_fee()+"",jine);
                        }
                    }).show(activity.getSupportFragmentManager());
                }
            }
        });
        holder.ivDanxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelect[position] = !isSelect[position];
                holder.ivDanxuan.setSelected(isSelect[position]);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_danxuan)
        ImageView ivDanxuan;
        @BindView(R.id.tv_order_number)
        TextView tvOrderNumber;
        @BindView(R.id.tv_end_time)
        TextView tvEndTime;
        @BindView(R.id.tv_quhuodizhi)
        TextView tvQuhuodizhi;
        @BindView(R.id.tv_songhuodizhi)
        TextView tvSonghuodizhi;
        @BindView(R.id.tv_jine)
        TextView tvJine;
        @BindView(R.id.bt_jiesuan)
        Button btJiesuan;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void setSelect(boolean b){
        for(int i=0;i<isSelect.length;i++){
            isSelect[i] = b;
            notifyDataSetChanged();
        }
    }

    public void jiesuan(String id,String yf,String sjyf){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .yunfeijiesuan(PreferenceUtils.getString(MyApplication.mContext, "token", ""),id,yf,sjyf))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String bean) {
                        activity.getList("0");
                    }
                });
    }
}
