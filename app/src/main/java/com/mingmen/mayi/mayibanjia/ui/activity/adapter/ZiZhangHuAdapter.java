package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.JueSeBean;
import com.mingmen.mayi.mayibanjia.bean.ZiZhangHuBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.DetailsZiZhangHuActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.JueSeDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.XuanZeDialog;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/13/013.
 */

public class  ZiZhangHuAdapter extends RecyclerView.Adapter<ZiZhangHuAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<ZiZhangHuBean> mList;
    private OnItemClickListener mOnItemClickListener;

    public ZiZhangHuAdapter(Context mContext, List<ZiZhangHuBean> list) {
        this.mContext = mContext;
        this.mList = list;
    }

    public void setmList(List<ZiZhangHuBean> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zizhanghu, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ZiZhangHuBean data = mList.get(position);
        holder.tvJuese.setText(data.getPrincipal()+"");
        holder.tvQuanxian.setText(data.getTelephone()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(mContext, DetailsZiZhangHuActivity.class);
                it.putExtra("id",data.getAccount_id());
                mContext.startActivity(it);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_juese)
        TextView tvJuese;
        @BindView(R.id.tv_quanxian)
        TextView tvQuanxian;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    public void addJuese(String id,String role_id){
            HttpManager.getInstance()
                    .with(mContext)
                    .setObservable(RetrofitManager.getService()
                            .addJueseQuanxian(PreferenceUtils.getString(MyApplication.mContext, "token", ""),id,role_id))
                    .setDataListener(new HttpDataListener<String>() {
                        @Override
                        public void onNext(String data) {
                            ToastUtil.showToast("添加成功");
                        }
                    });
    }
}
