package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.GWCDianPuShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.GWCShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.QueRenDingDanShangPinBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/5/005.
 */

public class TiJiaoDingDanDianPuAdapter extends RecyclerView.Adapter<TiJiaoDingDanDianPuAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<QueRenDingDanShangPinBean> dianpulist;
    private OnItemClickListener mOnItemClickListener1;
    Gson gson = new Gson();
    private boolean isSelected;
    private List<QueRenDingDanShangPinBean.ListBean> shangpinlist;


    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public TiJiaoDingDanDianPuAdapter(Context mContext, List<QueRenDingDanShangPinBean> list) {
        this.mContext = mContext;
        this.dianpulist = list;
    }

    public interface OnItemClickListener {
        void onClick(View view, int position, List<QueRenDingDanShangPinBean> dianpulist);
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener1 = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_querendingdan_dp, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final QueRenDingDanShangPinBean datas = dianpulist.get(position);
//        holder.tv_name.setText(string.getTitle());
//        holder.tv_laizi.setText(string.getCat_name());
//        Glide.with(mContext).load("http://www.zhenlvw.com/"+string.getFile_url())
//                .into(holder.iv_danxuan);
        shangpinlist = datas.getList();
        holder.tvDianming.setText(datas.getCompany_name());
        final TiJiaoDingDanShangPinAdapter shangpinadapter = new TiJiaoDingDanShangPinAdapter(mContext, shangpinlist);
        holder.rvQuerendingdanShangpin.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        holder.rvQuerendingdanShangpin.setAdapter(shangpinadapter);
        holder.etLiuyan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String liuyan = s.toString().trim();
                dianpulist.get(position).setLiuyan(liuyan);
            }
        });
    }

    public List<QueRenDingDanShangPinBean> getDianpulist(){
        return dianpulist;
    }
    @Override
    public int getItemCount() {
        return dianpulist.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_dianming)
        TextView tvDianming;
        @BindView(R.id.rv_querendingdan_shangpin)
        RecyclerView rvQuerendingdanShangpin;
        @BindView(R.id.et_liuyan)
        TextView etLiuyan;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
