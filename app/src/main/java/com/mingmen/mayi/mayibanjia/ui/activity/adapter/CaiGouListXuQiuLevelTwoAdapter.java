package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/11/12.
 */

public class CaiGouListXuQiuLevelTwoAdapter extends RecyclerView.Adapter<CaiGouListXuQiuLevelTwoAdapter.ViewHolder> {

    private Context mContext;
    private ViewHolder viewHolder;
    private List<CaiGouDanBean.FllistBean.SonorderlistBean> mList;
    public CallBack callBack;


    public CaiGouListXuQiuLevelTwoAdapter(Context mContext, List<CaiGouDanBean.FllistBean.SonorderlistBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_caigou_shangpin_xuqiu, null, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final CaiGouDanBean.FllistBean.SonorderlistBean listBean = mList.get(position);
        holder.tvShangpin.setText(listBean.getClassify_name());

        holder.ivShanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.isClick(v, position);
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
        @BindView(R.id.et_caigouliang)
        EditText etCaigouliang;
        @BindView(R.id.tv_guige)
        TextView tvGuige;
        @BindView(R.id.iv_xiangxia)
        ImageView ivXiangxia;
        @BindView(R.id.rl_guige)
        RelativeLayout rlGuige;
        @BindView(R.id.tv_tsyq)
        TextView tvTsyq;
        @BindView(R.id.tv_tjcg)
        TextView tvTjcg;
        @BindView(R.id.iv_shanchu)
        ImageView ivShanchu;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface CallBack {
        void isClick(View v, int pos);
    }
}
