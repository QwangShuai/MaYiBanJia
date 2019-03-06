package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean;
import com.mingmen.mayi.mayibanjia.bean.ChangYongBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.ChangGouActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/8/30.
 */

public class ChangGouListLevelTwoAdapter extends RecyclerView.Adapter<ChangGouListLevelTwoAdapter.ViewHolder> {

    private CallBack mCallBack;
    private String xuanzhongid = "";
    private Context mContext;
    private ViewHolder viewHolder;
    private List<ChangYongBean.ListBean> mList;
    private ChangGouActivity activity;
    private ConfirmDialog confirmDialog;
    private ChangGouListLevelOneAdapter adapter;
    private OnItemClickListener listener;
    private int pos;

    public ChangGouListLevelTwoAdapter(Context mContext, List<ChangYongBean.ListBean> mList, ChangGouActivity activity) {
        this.mContext = mContext;
        this.mList = mList;
        this.activity = activity;
        this.adapter = adapter;
        this.pos = pos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bianji_x, null, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        final ChangYongBean.ListBean bean= mList.get(position);
        activity.onChangeMap(bean,bean.isSelect());
        holder.ivShanchu.setVisibility(bean.isShow()?View.VISIBLE:View.GONE);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(bean.isShow()){
//                    confirmDialog.showDialog("是否确定删除此采购单");
//                    confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            HttpManager.getInstance()
//                                    .with(mContext)
//                                    .setObservable(
//                                            RetrofitManager
//                                                    .getService()
//                                                    .delChanggouSp(PreferenceUtils.getString(MyApplication.mContext, "token", ""), bean.getOften_name_id()))
//                                    .setDataListener(new HttpDataListener<String>() {
//                                        @Override
//                                        public void onNext(String data) {
//                                            ToastUtil.showToastLong("删除常用商品成功");
//                                            mList.remove(position);
//                                            int mysize = mList==null?0:mList.size();
//                                            Log.e("onNext:mysize",mysize+"---" );
//                                            if(mysize!=0){
//                                                notifyDataSetChanged();
//                                            } else {
//                                                adapter.delPos(pos);
//                                            }
//                                            activity.getList();
//                                            confirmDialog.dismiss();
//                                        }
//                                    }, false);
//                        }
//                    });
//                    confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            confirmDialog.dismiss();
//                        }
//                    });
//                } else {
//                    boolean b = !bean.isSelect();
//                    mList.get(position).setSelect(b);
//                    activity.onChangeMap(bean, b);
//                    notifyDataSetChanged();
//                }
//            }
//        });

        if(bean.isSelect()){
            holder.tvYijifenlei.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_solid_zangqing_3));
            holder.tvYijifenlei.setTextColor(mContext.getResources().getColor(R.color.white));
        } else {
            holder.tvYijifenlei.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_hollow_999999_3));
            holder.tvYijifenlei.setTextColor(mContext.getResources().getColor(R.color.zicolor));
        }
        activity.onChangeMap(bean, bean.isSelect());
        holder.tvYijifenlei.setText(bean.getClassify_name());
        holder.ivShanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                listener.onClick(v,position);
                confirmDialog.showDialog("是否确定删除此采购单");
                confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        HttpManager.getInstance()
                                .with(mContext)
                                .setObservable(
                                        RetrofitManager
                                                .getService()
                                                .delChanggouSp(PreferenceUtils.getString(MyApplication.mContext, "token", ""), bean.getOften_name_id()))
                                .setDataListener(new HttpDataListener<String>() {
                                    @Override
                                    public void onNext(String data) {
                                        ToastUtil.showToastLong("删除常用商品成功");
//                                        mList.remove(position);
//                                        notifyDataSetChanged();
                                        activity.getList();
                                        confirmDialog.dismiss();
                                    }
                                }, false);
                    }
                });
                confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmDialog.dismiss();
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }



    public String getXuanzhongid() {
        return xuanzhongid;
    }

    public void setXuanzhongid(String xuanzhongid) {
        this.xuanzhongid = xuanzhongid;
    }

    public ChangGouListLevelTwoAdapter setCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
        return this;
    }

    public ChangGouListLevelTwoAdapter setCallBack(OnItemClickListener listener) {
        this.listener = listener;
        return this;
    }

    public interface CallBack {
        void xuanzhong(ChangYongBean.ListBean msg);
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

//    public void setShow(boolean b){
//        for(int i=0;i<mList.size();i++){
//            mList.get(i).setShow(b);
//        }
//        notifyDataSetChanged();
//    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_yijifenlei)
        TextView tvYijifenlei;
        @BindView(R.id.iv_shanchu)
        ImageView ivShanchu;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
