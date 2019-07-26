package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.YinHangKaBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.LoginActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.view.CircleImageView;
import com.mingmen.mayi.mayibanjia.utils.GlideUtils;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/10/22.
 */

public class YinHangKaAdapter extends RecyclerView.Adapter<YinHangKaAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<YinHangKaBean> mList;
    private ConfirmDialog confirmDialog;
    private CallBack callBack;

    public YinHangKaAdapter(Context mContext, List<YinHangKaBean> list, CallBack callBack) {
        this.mContext = mContext;
        this.mList = list;
        this.callBack = callBack;
    }

    public interface CallBack {
        void xuanzhong(YinHangKaBean bean);
        void jiechu(String id);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_yinhangka, parent, false));
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final YinHangKaBean bean = mList.get(position);
        GlideUtils.cachePhoto(mContext,holder.ivTubiao,bean.getLog_url());
        GlideUtils.cachePhoto(mContext,holder.ivBg,bean.getReverse_url());
        holder.tvBankCardName.setText(bean.getBank_name());
        holder.tvBankCardNumber.setText(bean.getBank_account());
        holder.tvBankCardType.setText(bean.getBank_branch());
        holder.rlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.xuanzhong(mList.get(position));
            }
        });
        holder.rlItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                confirmDialog.showDialog("确认解除当前银行卡绑定");
                confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callBack.jiechu(bean.getBank_id());
                        confirmDialog.dismiss();
                    }
                });
                confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmDialog.dismiss();
                    }
                });
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_bg)
        ImageView ivBg;
        @BindView(R.id.iv_tubiao)
        CircleImageView ivTubiao;
        @BindView(R.id.tv_bank_card_name)
        TextView tvBankCardName;
        @BindView(R.id.tv_bank_card_type)
        TextView tvBankCardType;
        @BindView(R.id.tv_bank_card_number)
        TextView tvBankCardNumber;
        @BindView(R.id.rl_item)
        RelativeLayout rlItem;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void addBankCard(String bank_id) {//添加银行卡

    }
}
