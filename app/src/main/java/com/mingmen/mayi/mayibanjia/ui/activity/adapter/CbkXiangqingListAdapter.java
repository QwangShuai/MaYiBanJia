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
import com.mingmen.mayi.mayibanjia.bean.CbkXiangqingBean;
import com.mingmen.mayi.mayibanjia.bean.SearchCbkBean;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/8/30.
 */

public class CbkXiangqingListAdapter extends RecyclerView.Adapter<CbkXiangqingListAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<CbkXiangqingBean.CbkBean> list;
    private CallBack callBack;
    public CbkXiangqingListAdapter(Context mContext, List<CbkXiangqingBean.CbkBean> list) {
        this.mContext = mContext;
        this.list = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cbk_touliao, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final CbkXiangqingBean.CbkBean bean = list.get(position);
        holder.tvName.setText(bean.getClassify_name());
        holder.etNum.setText(bean.getCount());
        if(StringUtil.isValid(bean.getCount())&&Double.valueOf(bean.getCount())!=0){
            holder.etNum.setText(bean.getCount()+"");
        }
        StringUtil.setPricePoint(holder.etNum);
        holder.tvGuige.setText(bean.getSpec_name()+";");
        holder.etNum.setEnabled(false);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public interface CallBack {
        void isClick(int pos, String num);
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }
    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.et_num)
        EditText etNum;
        @BindView(R.id.tv_guige)
        TextView tvGuige;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
