package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/11/12.
 */

public class CaiGouListLevelTwoAdapter extends RecyclerView.Adapter<CaiGouListLevelTwoAdapter.ViewHolder> {

    private Context mContext;
    private ViewHolder viewHolder;
    private List<CaiGouDanBean.FllistBean.SonorderlistBean> mList;
    public CallBack callBack;


    public CaiGouListLevelTwoAdapter(Context mContext, List<CaiGouDanBean.FllistBean.SonorderlistBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_caigou_shangpin, null, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final CaiGouDanBean.FllistBean.SonorderlistBean listBean = mList.get(position);
        holder.tvShangpin.setText(listBean.getClassify_name());
        if(StringUtil.isValid(listBean.getSpecial_commodity())){
            holder.tvTeshu.setVisibility(View.VISIBLE);
        }
        holder.ivXiugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.isClick(v,position);
            }
        });

        holder.ivShanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.isClick(v,position);
            }
        });
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_shangpin)
        TextView tvShangpin;
        @BindView(R.id.tv_teshu)
        TextView tvTeshu;
        @BindView(R.id.iv_shanchu)
        ImageView ivShanchu;
        @BindView(R.id.iv_xiugai)
        ImageView ivXiugai;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface CallBack {
        void isClick(View v, int pos);
    }
}
