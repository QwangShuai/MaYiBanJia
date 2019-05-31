package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.FeiLeiLableSubmitBean;
import com.mingmen.mayi.mayibanjia.bean.PingJiaLableBean;
import com.mingmen.mayi.mayibanjia.ui.activity.FaBuShangPinXiangQingTuActivity;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/8/30.
 */

public class FenLeiLableAdapter extends RecyclerView.Adapter<FenLeiLableAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<FeiLeiLableSubmitBean> mList;
    private FaBuShangPinXiangQingTuActivity activity;
    private boolean isShow;
    public FenLeiLableAdapter(Context mContext, List<FeiLeiLableSubmitBean> list, FaBuShangPinXiangQingTuActivity activity) {
        this.mContext = mContext;
        this.mList = list;
        this.activity = activity;
    }
    public FenLeiLableAdapter(Context mContext, List<FeiLeiLableSubmitBean> list) {
        this.mContext = mContext;
        this.mList = list;
        this.isShow = true;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fenleicanshu, null));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        FeiLeiLableSubmitBean bean = mList.get(position);
        StringUtil.setInputNoEmoj(holder.etCanshu,20);
        if (isShow){
            holder.etCanshu.setEnabled(false);
        }
        if(position==mList.size()-1){
            holder.viewCut.setVisibility(View.INVISIBLE);
        }
        holder.tvName.setText(bean.getParamete_name());
        if(StringUtil.isValid(bean.getParamete_content())){
            holder.etCanshu.setText(bean.getParamete_content());
        }

        holder.etCanshu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               activity.changeList(position,s.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
