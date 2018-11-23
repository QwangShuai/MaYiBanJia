package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.YunFeiJieSuanBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.YunFeiJieSuanActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.YunFeiDialog;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/26.
 */

public class jieSuanJieGuoAdapter extends RecyclerView.Adapter<jieSuanJieGuoAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<YunFeiJieSuanBean.DdListBean> mList;
    private YunFeiJieSuanActivity activity;
    private boolean[] isSelect;

    public jieSuanJieGuoAdapter(Context context, List<YunFeiJieSuanBean.DdListBean> list, YunFeiJieSuanActivity activity) {
        this.mContext = context;
        this.mList = list;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_yunfeijiesuan, parent, false));
        isSelect = new boolean[mList == null ? 0 : mList.size()];
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final YunFeiJieSuanBean.DdListBean bean = mList.get(position);
//        if (bean.getSettle_accounts_state().equals("0")) {
//            holder.btJiesuan.setText("结算");
//            holder.ivDanxuan.setVisibility(View.VISIBLE);
//        } else if (bean.getSettle_accounts_state().equals("1")) {
//            holder.btJiesuan.setText("结算中");
//            holder.ivDanxuan.setVisibility(View.GONE);
//        } else {
//            holder.btJiesuan.setText("已完成");
//            holder.ivDanxuan.setVisibility(View.GONE);
//        }

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_quanbu_yunfei)
        TextView tvQuanbuYunfei;
        @BindView(R.id.tv_shiji_yunfei)
        TextView tvShijiYunfei;
        @BindView(R.id.tv_submit_time)
        TextView tvSubmitTime;
        @BindView(R.id.tv_end_time)
        TextView tvEndTime;
        @BindView(R.id.bt_chakan)
        Button btChakan;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
