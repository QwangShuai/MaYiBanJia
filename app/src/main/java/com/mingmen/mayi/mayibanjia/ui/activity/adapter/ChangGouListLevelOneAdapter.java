package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean;
import com.mingmen.mayi.mayibanjia.bean.ChangYongBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.CaiGouListActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.ChangGouActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.ShenPiShiBaiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.CaiGouDanXiuGaiDailog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/11/12.
 */

public class ChangGouListLevelOneAdapter extends RecyclerView.Adapter<ChangGouListLevelOneAdapter.ViewHolder> {



    private ViewHolder viewHolder;
    private List<ChangYongBean> mList;
    public CallBack callBack;
    private Context mContext;
    private boolean itemIsClick[];
    private ChangGouListLevelTwoAdapter adapter;
    private ConfirmDialog confirmDialog;
    private String type = "0";
    private ChangGouActivity activity;

    public ChangGouListLevelOneAdapter(Context mContext,List<ChangYongBean> mList, ChangGouActivity activity) {
        this.mList = mList;
        this.mContext = mContext;
        this.activity = activity;
        itemIsClick = new boolean[mList.size()];
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bianji_one, null, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        final ChangYongBean bean = mList.get(position);
        holder.tvPinlei.setText(bean.getClassify_name());
        adapter = new ChangGouListLevelTwoAdapter(mContext, mList.get(position).getList(),activity);
        holder.rvListTwo.setLayoutManager(new GridLayoutManager(mContext, 3));
        holder.rvListTwo.setAdapter(adapter);
        holder.rvListTwo.setFocusable(false);
        holder.rvListTwo.setNestedScrollingEnabled(false);
        holder.rlBianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.tvBianji.getText().toString().equals("编辑")){
                    setShow(position,true);
                    holder.tvBianji.setText("取消");
                } else {
                    setShow(position,false);
                    holder.tvBianji.setText("编辑");

                }
                adapter.notifyDataSetChanged();

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.isShow()) {
                    bean.setShow(false);
                    holder.rlBianji.setVisibility(View.GONE);
                    holder.llRongqi.setVisibility(View.GONE);
                    holder.ivJinru.setImageResource(R.mipmap.jinru);
                } else {
                    bean.setShow(true);
                    holder.rlBianji.setVisibility(View.VISIBLE);
                    holder.llRongqi.setVisibility(View.VISIBLE);
                    holder.ivJinru.setImageResource(R.mipmap.xia_kongxin_hui);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface CallBack {
        void isClick(View v, int pos);
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_pinlei)
        TextView tvPinlei;
        @BindView(R.id.iv_jinru)
        ImageView ivJinru;
        @BindView(R.id.rv_list_two)
        RecyclerView rvListTwo;
        @BindView(R.id.ll_rongqi)
        LinearLayout llRongqi;
        @BindView(R.id.tv_bianji)
        TextView tvBianji;
        @BindView(R.id.rl_bianji)
        RelativeLayout rlBianji;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    public void setShow(int pos,boolean b){
        for(int i=0;i<mList.get(pos).getList().size();i++){
            mList.get(pos).getList().get(i).setShow(b);
            mList.get(pos).getList().get(i).setSelect(false);
        }
        Log.e("setShow: ",new Gson().toJson(mList) );
        notifyDataSetChanged();
    }
}
