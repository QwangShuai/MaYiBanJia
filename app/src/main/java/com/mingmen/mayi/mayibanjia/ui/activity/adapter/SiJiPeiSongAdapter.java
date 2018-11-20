package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.WuLiuBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.PeiSongXiangQingActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.SiJiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.WeiYiQrCodeActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.DiTuDialog;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/26.
 */

public class SiJiPeiSongAdapter extends RecyclerView.Adapter<SiJiPeiSongAdapter.ViewHolder> {
    private ViewHolder viewHolder;
    private Context mContext;
    private List<WuLiuBean> mList;
    private SiJiActivity activity;
    private String type = "0";

    public SiJiPeiSongAdapter(Context context, List<WuLiuBean> list, SiJiActivity activity, String type) {
        this.mContext = context;
        this.mList = list;
        this.activity = activity;
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pei_song_xin_xi, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final WuLiuBean data = mList.get(position);
        if (data.getWl_order_state().equals("待取货")) {

        } else {
            holder.tv_state.setTextColor(R.color.zicolor);
        }
        holder.tv_state.setText(String.valueOf(data.getWl_order_state()));
        holder.tv_order_number.setText("订单编号：" + data.getWl_cars_order_number());
        holder.tv_songdashijian.setText(data.getArrival_time());
        holder.tv_shichang.setText(data.getMarketName());
        holder.tv_dizhi.setText(data.getSpecific_address());
        holder.tvBaozhuanggeshu.setText(data.getPackCount());
        holder.tvSaomageshu.setText(data.getScanCount());
        if(data.getPackCount().equals("0")||data.getIsTrue().equals("1")){//判断是否显示扫码以及展示数量
            holder.llSaoma.setVisibility(View.GONE);
            holder.llBaozhuang.setVisibility(View.GONE);
            holder.llSaomageshu.setVisibility(View.GONE);
            holder.tvQuhuoma.setVisibility(View.GONE);
        }
        holder.ll_rongqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(new Intent(mContext, PeiSongXiangQingActivity.class));
                it.putExtra("xqID", data.getWl_cars_order_number());
                mContext.startActivity(it);
            }
        });
        holder.tv_ditu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpManager.getInstance()
                        .with(mContext)
                        .setObservable(RetrofitManager.getService()
                                .getJingweidu(data.getSpecific_address() + ""))
                        .setDataListener(new HttpDataListener<String>() {
                            @Override
                            public void onNext(String weizhi) {
                                DiTuDialog dialog = new DiTuDialog();
                                dialog.setData(mContext, weizhi.split(",")[1], weizhi.split(",")[0], data.getSpecific_address());
                                dialog.show(activity.getSupportFragmentManager());
                            }
                        });
            }
        });
        holder.llSaoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data.getScanCount().equals(data.getPackCount())){
                    ToastUtil.showToast("装车完成");
                } else {
                    activity.saomiaoQrCode();
                }
            }
        });

        holder.tvQuhuoma.setOnClickListener(new View.OnClickListener() {
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

    @OnClick(R.id.ll_saoma)
    public void onViewClicked() {
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_state)
        TextView tv_state;
        @BindView(R.id.tv_order_number)
        TextView tv_order_number;
        @BindView(R.id.tv_songdashijian0)
        TextView tv_songdashijian0;
        @BindView(R.id.tv_songdashijian)
        TextView tv_songdashijian;
        @BindView(R.id.tv_shichang0)
        TextView tv_shichang0;
        @BindView(R.id.tv_shichang)
        TextView tv_shichang;
        @BindView(R.id.tv_dizhi0)
        TextView tv_dizhi0;
        @BindView(R.id.tv_dizhi)
        TextView tv_dizhi;
        @BindView(R.id.ll_rongqi)
        LinearLayout ll_rongqi;
        @BindView(R.id.tv_ditu)
        TextView tv_ditu;
        @BindView(R.id.ll_saoma)
        LinearLayout llSaoma;
        @BindView(R.id.tv_baozhuanggeshu)
        TextView tvBaozhuanggeshu;
        @BindView(R.id.ll_baozhuang)
        LinearLayout llBaozhuang;
        @BindView(R.id.tv_quhuoma)
        TextView tvQuhuoma;
        @BindView(R.id.tv_saomageshu)
        TextView tvSaomageshu;
        @BindView(R.id.ll_saomageshu)
        LinearLayout llSaomageshu;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }


}
