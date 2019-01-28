package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean;
import com.mingmen.mayi.mayibanjia.bean.ChangYongBean;
import com.mingmen.mayi.mayibanjia.ui.activity.ChangGouActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/8/30.
 */

public class ChangGouListLevelTwoAdapter extends RecyclerView.Adapter<ChangGouListLevelTwoAdapter.ViewHolder> {

    private CallBack mCallBack;
    private String xuanzhongid = "";
    private Context mContext;
    private ViewHolder viewHolder;
    private List<ChangYongBean.ListBean> mList;
    private ChangGouActivity activity;

    public ChangGouListLevelTwoAdapter(Context mContext, List<ChangYongBean.ListBean> mList, ChangGouActivity activity) {
        this.mContext = mContext;
        this.mList = mList;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bianji_x, null, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ChangYongBean.ListBean bean= mList.get(position);
        if(bean.isSelect()){
            holder.tvYijifenlei.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_solid_zangqing_3));
            holder.tvYijifenlei.setTextColor(mContext.getResources().getColor(R.color.white));
        } else {
            holder.tvYijifenlei.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_hollow_999999_3));
            holder.tvYijifenlei.setTextColor(mContext.getResources().getColor(R.color.zicolor));
        }
        activity.onChangeMap(bean, bean.isSelect());
        holder.tvYijifenlei.setText(bean.getClassify_name());
        holder.ivShanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }



    public String getXuanzhongid() {
        return xuanzhongid;
    }

    public void setXuanzhongid(String xuanzhongid) {
        this.xuanzhongid = xuanzhongid;
    }

    public ChangGouListLevelTwoAdapter setCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
        return this;
    }

    ;

    public interface CallBack {
        void xuanzhong(ChangYongBean.ListBean msg);
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public void setShow(boolean b){
        viewHolder.ivShanchu.setVisibility(b?View.VISIBLE:View.GONE);

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_yijifenlei)
        TextView tvYijifenlei;
        @BindView(R.id.iv_shanchu)
        ImageView ivShanchu;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
