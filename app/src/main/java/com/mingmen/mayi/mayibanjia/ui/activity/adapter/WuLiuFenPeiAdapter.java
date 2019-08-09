package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.WuliuDingdanBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.ShangPinGuanLiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ChangeWuLiuDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.FenPeiWuLiuCheDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.MessageDailog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ShuruDailog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.WuliuFenpeiDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.wuliujingli.BaseJingliFragment;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/26.
 */

public class WuLiuFenPeiAdapter extends RecyclerView.Adapter<WuLiuFenPeiAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<WuliuDingdanBean> mList;
    private CallBack callBack;
    public interface CallBack{
        void onClick(int position,View v,WuliuDingdanBean bean);
    };
    public WuLiuFenPeiAdapter(Context context, List<WuliuDingdanBean> list,CallBack callBack) {
        this.mContext = context;
        this.mList = list;
        this.callBack = callBack;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wuliu, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final WuliuDingdanBean data = mList.get(position);
        holder.tvFenpeiwuliuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//分配物流车
                if (data.getWl_cars_state().equals("0")) {
                    callBack.onClick(position,v,data);
                } else {
                    ToastUtil.showToast("此单已分配物流车");
                }

            }
        });
        if (data.getWl_cars_state().equals("0")) {
            holder.tvOrfenche.setText("未分车");
            holder.tvOrfenche.setTextColor(R.color.zicolor);
        } else if (data.getWl_cars_state().equals("1")) {
            holder.tvOrfenche.setText("已分车");
            holder.tvOrfenche.setTextColor(R.color.zicolor);
        } else if(data.getWl_cars_state().equals("2")) {
            holder.tvOrfenche.setText("已变更");
            holder.tvOrfenche.setTextColor(R.color.red_ff3300);
        } else {
            holder.tvOrfenche.setText("异常");
            holder.tvOrfenche.setTextColor(R.color.red_ff3300);
            holder.tvFenpeiwuliuche.setText(mContext.getString(R.string.wl_jiejueyichang));
            holder.tvFenpeiwuliuche.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//分配物流车
                    ShuruDailog dialog = new ShuruDailog(mContext,"解决原因","请输入解决原因", new ShuruDailog.CallBack() {
                        @Override
                        public void confirm(String msg) {
                            jjycWl(data.getWl_cars_order_number(),data.getWl_cars_id(),msg);
                        }
                    });
                    dialog.show();
                }
            });
        }

        holder.tvBiangeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//变更
                if (data.getWl_cars_state().equals("0")) {
                    ToastUtil.showToast("此状态不可变更");
                } else {
                    callBack.onClick(position,v,data);
                }

            }
        });
        if(data.getWlddState().equals("1401")){
            holder.tvState.setText(data.getWl_cars_type_name());
            holder.tvState.setVisibility(View.VISIBLE);
            holder.rlState.setVisibility(View.VISIBLE);
        } else if(data.getWlddState().equals("1403")) {
            holder.rlState.setVisibility(View.GONE);
            holder.tvState.setText(data.getWl_order_state());
            holder.tvState.setVisibility(View.VISIBLE);
        } else {
            holder.tvState.setText(data.getWl_order_state());
            holder.tvState.setVisibility(View.VISIBLE);
            holder.rlState.setVisibility(View.VISIBLE);
        }

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
        @BindView(R.id.tv_orfenche)
        TextView tvOrfenche;
        @BindView(R.id.tv_fenpeiwuliuche)
        TextView tvFenpeiwuliuche;
        @BindView(R.id.tv_biangeng)
        TextView tvBiangeng;
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
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.ll_shouqi)
        LinearLayout llShouqi;
        @BindView(R.id.ll_show)
        LinearLayout llShow;
        @BindView(R.id.rl_state)
        RelativeLayout rlState;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    private void jjycWl(String number, String id, String remarke){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .jjycWl(PreferenceUtils.getString(mContext,"token",""),number,id,remarke))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        ToastUtil.showToastLong("成功");
                        EventBus.getDefault().post("成功");
                    }
                });
    }
}
