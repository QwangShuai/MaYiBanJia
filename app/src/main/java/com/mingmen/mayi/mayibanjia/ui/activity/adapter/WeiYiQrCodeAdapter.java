package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.SiJiWLXQBean;
import com.mingmen.mayi.mayibanjia.bean.WeiYiQrCodeBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.PeiSongXiangQingActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.WeiYiQrCodeActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/26.
 */

public class WeiYiQrCodeAdapter extends RecyclerView.Adapter<WeiYiQrCodeAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<WeiYiQrCodeBean> mList;
    private ConfirmDialog confirmDialog;
    private WeiYiQrCodeBean data;
//    private int pos;
    public WeiYiQrCodeAdapter(Context context, List<WeiYiQrCodeBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quhuoma, parent, false));
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
//        pos = position;
        data = mList.get(position);
        holder.tvSuoyin.setText(data.getSerial());
        holder.tvQuhuoma.setText(data.getOnlyCode());
        holder.btSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_quhuoma)
        TextView tvQuhuoma;
        @BindView(R.id.tv_suoyin)
        TextView tvSuoyin;
        @BindView(R.id.bt_sure)
        Button btSure;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
    public void showDialog(final int pos){
        confirmDialog.showDialog("是否确认收货");
        confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmGoods(pos);
            }
        });
        confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog.dismiss();
            }
        });
    }

    public void confirmGoods(final int pos){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .updateQrCode(PreferenceUtils.getString(MyApplication.mContext, "token",""),"",data.getGy_order_id(),"2",data.getOnlyCode()))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        ToastUtil.showToast("物流单确认成功");
                        mList.remove(pos);
                        Log.e("position",pos+"......");
                        confirmDialog.dismiss();
                        notifyDataSetChanged();
                    }
                });
    }
}
