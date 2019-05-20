package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.PingJiaLableBean;
import com.mingmen.mayi.mayibanjia.bean.XQPingJiaBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.DingDanXiangQingActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.GHDXiangQingActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.YongHuPingJiaActivity;
import com.mingmen.mayi.mayibanjia.ui.view.CircleImageView;
import com.mingmen.mayi.mayibanjia.ui.view.XCFlowLayout;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.GlideUtils;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/15.
 */

public class PingJiaGydAdapter extends RecyclerView.Adapter<PingJiaGydAdapter.ViewHolder> {


    private ViewHolder viewHolder;
    private ArrayList<TextView> tvs;
    private List<PingJiaLableBean> list = new ArrayList<>();
    private List<XQPingJiaBean> mlist = new ArrayList<>();
    private YongHuPingJiaActivity activity;
    private Context mContext;

    public  PingJiaGydAdapter(Context mContext,List<XQPingJiaBean> mlist){
        this.mContext = mContext;
        this.mlist = mlist;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pingjia_gyd, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final XQPingJiaBean item = mlist.get(position);
        if(item.getHfStata().equals("0")){
            getPingjiaLable();
        }
        holder.llShangjiahuifu.setVisibility(View.VISIBLE);
        holder.tvMaijianame.setText(item.getPjCompanyName());
        holder.tvPinglunshijian.setText(item.getCreate_time());
        holder.rlLable.setVisibility(View.GONE);
        if (StringUtil.isValid(item.getGy_order_id())) {
            holder.tvDingdan.setVisibility(View.VISIBLE);
            holder.tvDingdan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(mContext, GHDXiangQingActivity.class);
                    it.putExtra("orderID", item.getGy_order_id());
                    mContext.startActivity(it);
                }
            });
        }
        if (item.getHfStata().equals("1")) {
            holder.btShow.setVisibility(View.GONE);
            holder.btGone.setVisibility(View.VISIBLE);
            holder.btGone.setText("已回复");
        } else {
            holder.btShow.setVisibility(View.VISIBLE);
            holder.btGone.setVisibility(View.GONE);
            holder.btShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (item.isShow()) {
                        item.setShow(false);
                        holder.rlLable.setVisibility(View.GONE);
                    } else {
                        holder.btGone.setText("收起回复");
                        item.setShow(true);
                        holder.rlLable.setVisibility(View.VISIBLE);
                        initShangpinChildViews(holder.xcfHuifuLable);
                    }
                }
            });
        }
        holder.btTijiao.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 0;
                String id = "";
                for (PingJiaLableBean bean : list) {
                    if (bean.isSelect()) {
                        id += bean.getSon_number() + ",";
                        count++;
                    }
                }
                if (count == 0) {
                    ToastUtil.showToastLong("请选择回复标签");
                    return;
                } else {
                    gydHuifu(id, item.getRowgu_id());
                }
            }
        });
        if (item.getReplyList() != null && item.getReplyList().size() != 0) {//商家回复

            holder.tvHuifushijian.setText(item.getReplyList().get(0).getCreate_time());
            String[] huifuarray = new String[item.getReplyList().get(0).getPjList() == null ? 0 : item.getReplyList().get(0).getPjList().size()];
            for (int i = 0; i < item.getReplyList().get(0).getPjList().size(); i++) {
                huifuarray[i] = item.getReplyList().get(0).getPjList().get(i).getSon_name();
            }

            if (huifuarray.length > 0) {
                initShangpinChildViews(holder.xcfHuifu,huifuarray, "0");
            }
        } else {
            holder.llShangjiahuifu.setVisibility(View.GONE);
        }

        GlideUtils.cachePhoto(mContext,holder.ivTouxiang,item.getHeadPhoto());
        holder.rbPingfen.setRating(item.getStar_evaluation());//评分
        String pingjianeirong = item.getComment_text();
        if (item.getPjList() != null && item.getPjList().size() != 0) {
            Log.e("onBindViewHolder: ", "评分走了");
            String[] pingjiaarray = new String[item.getPjList() == null ? 0 : item.getPjList().size()];
            for (int i = 0; i < item.getPjList().size(); i++) {
                pingjiaarray[i] = item.getPjList().get(i).getSon_name();
            }

            if (pingjiaarray.length > 0) {
                initShangpinChildViews(holder.xcfPingjia, pingjiaarray, "1");
            }

        }
    }

    @Override
    public int getItemCount() {
        return mlist == null ? 0 : mlist.size();
    }


    public void setActivity(YongHuPingJiaActivity activity) {
        this.activity = activity;
    }


    private void initShangpinChildViews(XCFlowLayout xcfShangpinlishisousuo, String[] shangpinNamelist, String type) {
        xcfShangpinlishisousuo.removeAllViews();
        ArrayList tvs = new ArrayList();
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = AppUtil.dip2px(12);
        lp.rightMargin = AppUtil.dip2px(0);
        lp.topMargin = AppUtil.dip2px(12);
        lp.bottomMargin = 0;
        for (int i = 0; i < shangpinNamelist.length; i++) {
            TextView view = new TextView(mContext);
            view.setText(shangpinNamelist[i]);
            view.setTextColor(mContext.getResources().getColor(R.color.lishisousuo));
            view.setTextSize(12);
            view.setPadding(AppUtil.dip2px(12), AppUtil.dip2px(8), AppUtil.dip2px(12), AppUtil.dip2px(8));
            if (type.equals("0")) {
                view.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_hollow_fafafa_14));
            } else {
                view.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_solid_fafafa_100));
            }

            tvs.add(view);
            xcfShangpinlishisousuo.addView(view, lp);
        }
    }

    private void initShangpinChildViews(XCFlowLayout xcfShangpinlishisousuo) {
        xcfShangpinlishisousuo.removeAllViews();
        tvs = new ArrayList();
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = AppUtil.dip2px(12);
        lp.rightMargin = AppUtil.dip2px(0);
        lp.topMargin = AppUtil.dip2px(12);
        lp.bottomMargin = 0;
        for (int i = 0; i < list.size(); i++) {
            TextView view = new TextView(mContext);
            view.setText(list.get(i).getSon_name());
            view.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
            view.setTextSize(12);
            view.setPadding(AppUtil.dip2px(12), AppUtil.dip2px(8), AppUtil.dip2px(12), AppUtil.dip2px(8));
            view.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_solid_999999_14));
//            xuanzhong.put(mList.get(i).getRole_id(),mList.get(i));
            tvs.add(view);
            xcfShangpinlishisousuo.addView(view, lp);
        }
        for (int i = 0; i < tvs.size(); i++) {
            final int finalI = i;
            tvs.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.get(finalI).isSelect()) {
                        list.get(finalI).setSelect(false);
                        tvs.get(finalI).setTextColor(mContext.getResources().getColor(R.color.hintcolor));
                        tvs.get(finalI).setBackground(mContext.getResources().getDrawable(R.drawable.fillet_solid_999999_14));
                    } else {
                        list.get(finalI).setSelect(true);
                        tvs.get(finalI).setTextColor(mContext.getResources().getColor(R.color.lishisousuo));
                        tvs.get(finalI).setBackground(mContext.getResources().getDrawable(R.drawable.fillet_hollow_white_14));
                    }

                }
            });
        }
    }

    public void getPingjiaLable() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getGydPingjiaLable(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<List<PingJiaLableBean>>() {
                    @Override
                    public void onNext(List<PingJiaLableBean> data) {
                        list.clear();
                        list.addAll(data);
                    }
                },false);
    }

    public void gydHuifu(String comment_text, String rowgu_id) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .gydHuifu(PreferenceUtils.getString(MyApplication.mContext, "token", ""), comment_text, rowgu_id))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        ToastUtil.showToastLong("回复成功");
                        activity.setViewColor();
                    }
                });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_touxiang)
        CircleImageView ivTouxiang;
        @BindView(R.id.tv_maijianame)
        TextView tvMaijianame;
        @BindView(R.id.tv_pinglunshijian)
        TextView tvPinglunshijian;
        @BindView(R.id.rb_pingfen)
        RatingBar rbPingfen;
        @BindView(R.id.tv_dingdan)
        TextView tvDingdan;
        @BindView(R.id.xcf_pingjia)
        XCFlowLayout xcfPingjia;
        @BindView(R.id.bt_gone)
        TextView btGone;
        @BindView(R.id.bt_show)
        TextView btShow;
        @BindView(R.id.xcf_huifu_lable)
        XCFlowLayout xcfHuifuLable;
        @BindView(R.id.ll)
        LinearLayout ll;
        @BindView(R.id.bt_tijiao)
        TextView btTijiao;
        @BindView(R.id.rl_lable)
        RelativeLayout rlLable;
        @BindView(R.id.textView5)
        TextView textView5;
        @BindView(R.id.tv_huifushijian)
        TextView tvHuifushijian;
        @BindView(R.id.xcf_huifu)
        XCFlowLayout xcfHuifu;
        @BindView(R.id.ll_shangjiahuifu)
        LinearLayout llShangjiahuifu;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
