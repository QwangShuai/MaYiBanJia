package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.QrCodeBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.DaYinQrCodeActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.ghdingdan.GHOrderAdapter;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/10/8.
 */

public class QrCodeAdapter extends RecyclerView.Adapter<QrCodeAdapter.ViewHolder>  {
    private ViewHolder viewHolder;
    private Context mContext;
    private List<QrCodeBean> mList;
    private DaYinQrCodeActivity activity;
    public QrCodeAdapter(Context mContext, List<QrCodeBean> list,DaYinQrCodeActivity activity) {
        this.mContext = mContext;
        this.mList = list;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new QrCodeAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qr_code, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final QrCodeBean bean = mList.get(position);
        holder.tv_suoyin.setText(bean.getSerial());
        if(!TextUtils.isEmpty(bean.getTwocode()))
            Glide.with(mContext).load(bean.getTwocode()).into(holder.iv_qr_code);
        holder.tv_biaoshi.setText(bean.getIdentifying());
        holder.tv_dianpu.setText(bean.getCompany_name());
        holder.tv_dizhi.setText(bean.getCompanyAddress());
        holder.tv_phone.setText(bean.getTelephone());
        holder.tv_zuofei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpManager.getInstance()
                        .with(mContext)
                        .setObservable(
                                RetrofitManager
                                        .getService()
                                        .delQrCode(PreferenceUtils.getString(MyApplication.mContext, "token",""),bean.getTwocode_id()))
                        .setDataListener(new HttpDataListener<String>() {
                            @Override
                            public void onNext(String data) {
                                mList.remove(position);
                                notifyDataSetChanged();
                            }
                        });
            }
        });
        holder.tv_dayin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.dayinQrCode(holder.rl_dayin);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_suoyin)
        TextView tv_suoyin;
        @BindView(R.id.iv_qr_code)
        ImageView iv_qr_code;
        @BindView(R.id.tv_biaoshi)
                TextView tv_biaoshi;
        @BindView(R.id.tv_dianpu)
                TextView tv_dianpu;
        @BindView(R.id.tv_dizhi)
                TextView tv_dizhi;
        @BindView(R.id.tv_phone)
                TextView tv_phone;
        @BindView(R.id.tv_zuofei)
                TextView tv_zuofei;
        @BindView(R.id.tv_dayin)
                TextView tv_dayin;
        @BindView(R.id.rl_dayin)
        RelativeLayout rl_dayin;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
