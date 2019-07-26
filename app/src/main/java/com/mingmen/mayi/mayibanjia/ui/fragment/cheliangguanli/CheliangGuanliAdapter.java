package com.mingmen.mayi.mayibanjia.ui.fragment.cheliangguanli;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CheliangBean;
import com.mingmen.mayi.mayibanjia.bean.GouwucheDianpuBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.JuJueLiShiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.AddWuLiuCheDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ShaiXuanWuLiuCheDialog;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/5/005.
 */

public class CheliangGuanliAdapter extends RecyclerView.Adapter<CheliangGuanliAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<CheliangBean> mlist;
    private OnItemClickListener mOnItemClickListener;
    private ConfirmDialog confirmDialog;
    private Activity activity;

    public CheliangGuanliAdapter(Context mContext, List<CheliangBean> list, Activity activity) {
        this.mContext = mContext;
        this.mlist = list;
        this.activity = activity;
    }


    public interface OnItemClickListener {
        void onClick(View view, int position, List<GouwucheDianpuBean> dianpulist);
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cheliangguanli, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        final CheliangBean datas = mlist.get(position);
        holder.tvLianxiren.setText(datas.getNew_driver_name());
        holder.tvLianxifangshi.setText(datas.getNew_driver_phone());
        holder.tvCheliangleixing.setText(datas.getNew_wl_cars_type_name());
        holder.tvChepaihao.setText(datas.getNew_plate_number());
        holder.tvYijujue.setText("已拒绝:  " + datas.getAccount());
        holder.tvYijujue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(mContext, JuJueLiShiActivity.class);
                it.putExtra("id",datas.getWl_cars_id());
                mContext.startActivity(it);
            }
        });
        holder.llBianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddWuLiuCheDialog add_dialog = new AddWuLiuCheDialog(mContext,activity,datas);
                add_dialog.show();
            }
        });
        holder.llShanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog.showDialog("是否确认删除车辆");
                confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmDialog.cancel();
                        HttpManager.getInstance()
                                .with(mContext)
                                .setObservable(
                                        RetrofitManager
                                                .getService()
                                                .delCheliang(PreferenceUtils.getString(MyApplication.mContext, "token", ""),
                                                        datas.getWl_cars_id()))
                                .setDataListener(new HttpDataListener<String>() {
                                    @Override
                                    public void onNext(String data) {
                                        mlist.remove(position);
                                        notifyDataSetChanged();
                                        ToastUtil.showToastLong("删除成功");
                                    }
                                });
                    }
                });
                confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmDialog.cancel();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist == null ? 0 : mlist.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_lianxiren)
        TextView tvLianxiren;
        @BindView(R.id.tv_lianxifangshi)
        TextView tvLianxifangshi;
        @BindView(R.id.tv_cheliangleixing)
        TextView tvCheliangleixing;
        @BindView(R.id.tv_chepaihao)
        TextView tvChepaihao;
        @BindView(R.id.tv_yijujue)
        TextView tvYijujue;
        @BindView(R.id.ll_shanchu)
        LinearLayout llShanchu;
        @BindView(R.id.ll_bianji)
        LinearLayout llBianji;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
