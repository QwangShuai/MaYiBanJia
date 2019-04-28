package com.mingmen.mayi.mayibanjia.ui.fragment.quanbucaipin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.FCGName;
import com.mingmen.mayi.mayibanjia.bean.ShouYeLeiBean;
import com.mingmen.mayi.mayibanjia.ui.activity.QuanBuCaiPinActivity;
import com.mingmen.mayi.mayibanjia.ui.fragment.quanbucaipin.QuanBuCaiPinFragment;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/5/005.
 */

public class QuanBuCaiPinLeiAdapter extends RecyclerView.Adapter<QuanBuCaiPinLeiAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<List<FCGName>> mList;
    private QuanBuCaiPinFragment fragment;
    private QuanBuCaiPinActivity activity;
    private String xuanzhongId="";
//    private boolean isteshu;
    public String getXuanzhongId() {
        return xuanzhongId;
    }

    public void setXuanzhongId(String xuanzhongId) {
        this.xuanzhongId = xuanzhongId;
        notifyDataSetChanged();
    }

    public QuanBuCaiPinLeiAdapter(Context mContext, List<List<FCGName>> list, QuanBuCaiPinFragment fragment) {
        this.mContext = mContext;
        this.mList = list;
        this.fragment = fragment;
    }

    public QuanBuCaiPinLeiAdapter(Context mContext, List<List<FCGName>> list, QuanBuCaiPinActivity activity) {
        this.mContext = mContext;
        this.mList = list;
        this.activity = activity;
//        isteshu = true;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qbcp_biaoqian, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final List<FCGName> data = mList.get(position);
        if (data.size() > 0 && data.get(0) != null) {
            holder.tv1.setText(data.get(0).getClassify_name());
            setTextViewColor(holder.tv1,data.get(0).getClassify_id());
            holder.rl1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (isteshu){
//                        activity.setXuanzhongId(data.get(0).getClassify_id());
//                    } else {
                        fragment.setXuanzhongId(data.get(0).getClassify_id());
//                    }

                }
            });
        } else {
            holder.tv1.setVisibility(View.GONE);
        }
        if (data.size() > 1 && data.get(1) != null) {
            holder.tv2.setText(data.get(1).getClassify_name());
            setTextViewColor(holder.tv2,data.get(1).getClassify_id());
            holder.rl2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (isteshu){
//                        activity.setXuanzhongId(data.get(1).getClassify_id());
//                    } else {
                        fragment.setXuanzhongId(data.get(1).getClassify_id());
//                    }
                }
            });
        } else {
            holder.tv2.setVisibility(View.GONE);
        }
        if (data.size() > 2 && data.get(2) != null) {
            holder.tv3.setText(data.get(2).getClassify_name());
            setTextViewColor(holder.tv3,data.get(2).getClassify_id());
            holder.rl3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (isteshu){
//                        activity.setXuanzhongId(data.get(2).getClassify_id());
//                    } else {
                        fragment.setXuanzhongId(data.get(2).getClassify_id());
//                    }
                }
            });
        } else {
            holder.tv3.setVisibility(View.GONE);
        }
        if (data.size() > 3 && data.get(3) != null) {
            holder.tv4.setText(data.get(3).getClassify_name());
            setTextViewColor(holder.tv4,data.get(3).getClassify_id());
            holder.rl4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (isteshu){
//                        activity.setXuanzhongId(data.get(3).getClassify_id());
//                    } else {
                        fragment.setXuanzhongId(data.get(3).getClassify_id());
//                    }
                }
            });
        } else {
            holder.tv4.setVisibility(View.GONE);
        }
        if (data.size() > 4 && data.get(4) != null) {
            holder.tv5.setText(data.get(4).getClassify_name());
            setTextViewColor(holder.tv5,data.get(4).getClassify_id());
            holder.rl5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (isteshu){
//                        activity.setXuanzhongId(data.get(4).getClassify_id());
//                    } else {
                        fragment.setXuanzhongId(data.get(4).getClassify_id());
//                    }
                }
            });
        } else {
            holder.tv5.setVisibility(View.GONE);
        }
        if (data.size() > 5 && data.get(5) != null) {
            holder.tv6.setText(data.get(5).getClassify_name());
            setTextViewColor(holder.tv6,data.get(5).getClassify_id());
            holder.rl6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (isteshu){
//                        activity.setXuanzhongId(data.get(5).getClassify_id());
//                    } else {
                        fragment.setXuanzhongId(data.get(5).getClassify_id());
//                    }
                }
            });
        } else {
            holder.tv6.setVisibility(View.GONE);
        }
        if (data.size() > 6 && data.get(6) != null) {
            holder.tv7.setText(data.get(6).getClassify_name());
            setTextViewColor(holder.tv7,data.get(6).getClassify_id());
            holder.rl7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (isteshu){
//                        activity.setXuanzhongId(data.get(6).getClassify_id());
//                    } else {
                        fragment.setXuanzhongId(data.get(6).getClassify_id());
//                    }
                }
            });
        } else {
            holder.tv7.setVisibility(View.GONE);
        }
        if (data.size() > 7 && data.get(7) != null) {
            holder.tv8.setText(data.get(7).getClassify_name());
            setTextViewColor(holder.tv8,data.get(7).getClassify_id());
            holder.rl8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (isteshu){
//                        activity.setXuanzhongId(data.get(7).getClassify_id());
//                    } else {
                        fragment.setXuanzhongId(data.get(7).getClassify_id());
//                    }
                }
            });
        } else {
            holder.tv8.setVisibility(View.GONE);
        }
        if (data.size() > 8 && data.get(8) != null) {
            holder.tv9.setText(data.get(8).getClassify_name());
            setTextViewColor(holder.tv9,data.get(8).getClassify_id());
            holder.rl9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (isteshu){
//                        activity.setXuanzhongId(data.get(8).getClassify_id());
//                    } else {
                        fragment.setXuanzhongId(data.get(8).getClassify_id());
//                    }
                }
            });
        } else {
            holder.tv9.setVisibility(View.GONE);
        }
        if (data.size() > 9 && data.get(9) != null) {
            holder.tv10.setText(data.get(9).getClassify_name());
            setTextViewColor(holder.tv10,data.get(9).getClassify_id());
            holder.rl10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (isteshu){
//                        activity.setXuanzhongId(data.get(9).getClassify_id());
//                    } else {
                        fragment.setXuanzhongId(data.get(9).getClassify_id());
//                    }
                }
            });
        } else {
            holder.tv10.setVisibility(View.GONE);
        }
//        Glide.with(mContext).load(""+string.getFile_url())
//                .into(holder.iv1);
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv1)
        TextView tv1;
        @BindView(R.id.rl_1)
        RelativeLayout rl1;
        @BindView(R.id.tv2)
        TextView tv2;
        @BindView(R.id.rl_2)
        RelativeLayout rl2;
        @BindView(R.id.tv3)
        TextView tv3;
        @BindView(R.id.rl_3)
        RelativeLayout rl3;
        @BindView(R.id.tv4)
        TextView tv4;
        @BindView(R.id.rl_4)
        RelativeLayout rl4;
        @BindView(R.id.tv5)
        TextView tv5;
        @BindView(R.id.rl_5)
        RelativeLayout rl5;
        @BindView(R.id.tv6)
        TextView tv6;
        @BindView(R.id.rl_6)
        RelativeLayout rl6;
        @BindView(R.id.tv7)
        TextView tv7;
        @BindView(R.id.rl_7)
        RelativeLayout rl7;
        @BindView(R.id.tv8)
        TextView tv8;
        @BindView(R.id.rl_8)
        RelativeLayout rl8;
        @BindView(R.id.tv9)
        TextView tv9;
        @BindView(R.id.rl_9)
        RelativeLayout rl9;
        @BindView(R.id.tv10)
        TextView tv10;
        @BindView(R.id.rl_10)
        RelativeLayout rl10;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private void setTextViewColor(TextView tv,String id){
        if(id.equals(xuanzhongId)){
            tv.setTextColor(mContext.getResources().getColor(R.color.white));
            tv.setBackground(mContext.getResources().getDrawable(R.drawable.zangqing_yuan));
        } else {
            tv.setTextColor(mContext.getResources().getColor(R.color.zicolor));
            tv.setBackground(mContext.getResources().getDrawable(R.drawable.white_yuan));
        }
    }
}
