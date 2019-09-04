package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.AddQrCodeBean;
import com.mingmen.mayi.mayibanjia.bean.GHOrderBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.AddQrCodeActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.TuikuanDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.ghdingdan.BaseGHOrderFragment;
import com.mingmen.mayi.mayibanjia.ui.activity.ghdingdan.GHShangPinAdapter;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.custom.MarqueeTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/28.
 */

public class GHOrderDdbAdapter extends RecyclerView.Adapter<GHOrderDdbAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<GHOrderBean> mList;




    public GHOrderDdbAdapter(Context mContext, List<GHOrderBean> list) {
        this.mContext = mContext;
        this.mList = list;
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gh_order_ddbsj, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final GHOrderBean bean = mList.get(position);
        holder.tvTime.setText("要求送达时间:" + bean.getArrival_time());
        final LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        holder.rvShangpin.setLayoutManager(manager);
        if (StringUtil.isValid(bean.getRefund()) && !bean.getRefund().equals("0")) {
            holder.rlTuikuan.setVisibility(View.VISIBLE);
            holder.tvTuikuanjine.setText(bean.getRefund());
        } else {
            holder.rlTuikuan.setVisibility(View.GONE);
        }
        if (StringUtil.isValid(bean.getAppend_money()) && !bean.getAppend_money().equals("0")) {
            holder.rlFuJiaMoney.setVisibility(View.VISIBLE);
            holder.tvFuJiaFeiMoney.setText(bean.getAppend_money());
        } else {
            holder.rlFuJiaMoney.setVisibility(View.GONE);
        }

        holder.tvDianpu.setText(bean.getCompany_name() + ":");
        holder.tvDianpuPhone.setText(bean.getTelephone());
        holder.tvShangpin.setMarqueeEnable(true);
        holder.tvShangpin.setText(bean.getCountname());
        holder.tvZongjia.setText("￥:" + bean.getTotal_price());
        holder.tvOrderNumber.setText("订单编号:" + bean.getGy_order_number());
        holder.tvOrderTime.setText("下单时间:" + bean.getCreate_time());
        GHShangPinAdapter adapter = new GHShangPinAdapter(mContext, bean.getZilist());
        holder.rvShangpin.setAdapter(adapter);
        holder.rvShangpin.setFocusable(false);
        holder.rvShangpin.setNestedScrollingEnabled(false);
        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<GHOrderBean.ZilistBean> list = new ArrayList<GHOrderBean.ZilistBean>();
                if (holder.btnMore.getText().equals("展开")) {
                    holder.btnMore.setText("收起");
                    holder.btnMore.setTextColor(mContext.getResources().getColor(R.color.zangqing));
                    holder.btnMore.setBackground(mContext.getResources().getDrawable(R.drawable.bg_bt_f2f2f2));
                    holder.llRv.setVisibility(View.VISIBLE);
                } else {
                    holder.btnMore.setTextColor(mContext.getResources().getColor(R.color.white));
                    holder.btnMore.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_solid_zangqing_14));
                    holder.btnMore.setText("展开");
                    holder.llRv.setVisibility(View.GONE);
                }

            }
        });
            holder.ivDianpuDianhua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CallPhone(bean.getTelephone());
                }
            });
            holder.tvTuikuan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//通知商家
                    CallPhone(bean.getTelephone());
                }
            });



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.tv_dianpu)
        TextView tvDianpu;
        @BindView(R.id.tv_dianpu_phone)
        TextView tvDianpuPhone;
        @BindView(R.id.iv_dianpu_dianhua)
        ImageView ivDianpuDianhua;
        @BindView(R.id.ll_dianpu_rongqi)
        LinearLayout llDianpuRongqi;
        @BindView(R.id.tv_shangpin)
        MarqueeTextView tvShangpin;
        @BindView(R.id.btn_more)
        Button btnMore;
        @BindView(R.id.rv_shangpin)
        RecyclerView rvShangpin;
        @BindView(R.id.ll_rv)
        LinearLayout llRv;
        @BindView(R.id.tuikuan)
        TextView tuikuan;
        @BindView(R.id.tv_tuikuanjine)
        TextView tvTuikuanjine;
        @BindView(R.id.rl_tuikuan)
        RelativeLayout rlTuikuan;
        @BindView(R.id.tv_fuJiaFei)
        TextView tvFuJiaFei;
        @BindView(R.id.tv_fuJiaFeiMoney)
        TextView tvFuJiaFeiMoney;
        @BindView(R.id.rl_fuJiaMoney)
        RelativeLayout rlFuJiaMoney;
        @BindView(R.id.heji)
        TextView heji;
        @BindView(R.id.tv_zongjia)
        TextView tvZongjia;
        @BindView(R.id.tv_order_number)
        TextView tvOrderNumber;
        @BindView(R.id.tv_order_time)
        TextView tvOrderTime;
        @BindView(R.id.tv_end_time)
        TextView tvEndTime;
        @BindView(R.id.tv_tuikuan)
        TextView tvTuikuan;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void CallPhone(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        mContext.startActivity(intent);
    }
}
