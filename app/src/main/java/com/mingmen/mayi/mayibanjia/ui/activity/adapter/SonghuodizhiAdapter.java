package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.WuliuDingdanBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/26.
 */

public class SonghuodizhiAdapter extends RecyclerView.Adapter<SonghuodizhiAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<WuliuDingdanBean.Dizhi> mList;

    public SonghuodizhiAdapter(Context context, List<WuliuDingdanBean.Dizhi> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_songhuodizhi, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        holder.setIsRecyclable(false);
        final WuliuDingdanBean.Dizhi bean = mList.get(position);
        holder.tvSonghuodizhi.setText(bean.getProvince()+bean.getCity()+bean.getRegion()+
                bean.getStreet()+bean.getSpecific_address());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_songhuodizhi)
        TextView tvSonghuodizhi;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
