package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.FeiLeiLableSubmitBean;
import com.mingmen.mayi.mayibanjia.bean.SPXiangQingBean;
import com.mingmen.mayi.mayibanjia.ui.activity.FaBuShangPinXiangQingTuActivity;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/8/30.
 */

public class SpFenLeiLableAdapter extends RecyclerView.Adapter<SpFenLeiLableAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<SPXiangQingBean.ParameteListBean> mList;

    public SpFenLeiLableAdapter(Context mContext, List<SPXiangQingBean.ParameteListBean> list) {
        this.mContext = mContext;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fenleicanshu, null));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        SPXiangQingBean.ParameteListBean bean = mList.get(position);
//        if(position==mList.size()-1){
            holder.viewCut.setVisibility(View.GONE);
//        }
        holder.tvName.setText(bean.getParamete_name());
        if(StringUtil.isValid(bean.getParamete_content())){
            holder.etCanshu.setText(bean.getParamete_content());
            holder.etCanshu.setEnabled(false);
        } else {
//            holder.llRq.setVisibility(View.GONE);
//            holder.tvName.setVisibility(View.GONE);
//            holder.etCanshu.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.et_canshu)
        EditText etCanshu;
        @BindView(R.id.view_cut)
        View viewCut;
        @BindView(R.id.ll_rq)
        LinearLayout llRq;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
