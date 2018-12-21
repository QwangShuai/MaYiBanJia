package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
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

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean;
import com.mingmen.mayi.mayibanjia.bean.XiTongTuiJianBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.CaiGouListActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.ShenPiShiBaiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.CaiGouDanXiuGaiDailog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import org.greenrobot.eventbus.util.ErrorDialogManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean.TYPE_FIVE;
import static com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean.TYPE_FOUR;
import static com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean.TYPE_ONE;
import static com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean.TYPE_THREE;
import static com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean.TYPE_TWO;

/**
 * Created by Administrator on 2018/11/12.
 */

public class CaiGouListLevelOneAdapter extends RecyclerView.Adapter<CaiGouListLevelOneAdapter.ViewHolder> {


    private ViewHolder viewHolder;
    private List<CaiGouDanBean.FllistBean> mList;
    public CallBack callBack;
    private Context mContext;
    private boolean itemIsClick[];
    private CaiGouListLevelTwoAdapter adapter;
    private ConfirmDialog confirmDialog;
    private CaiGouListActivity activity;
    private ShenPiShiBaiActivity shibai_activity;
    private String type="0";

    public CaiGouListLevelOneAdapter(CaiGouListActivity activity,List<CaiGouDanBean.FllistBean> mList, Context mContext) {
        this.activity = activity;
        this.mList = mList;
        this.mContext = mContext;
        itemIsClick = new boolean[mList.size()];
    }
    public CaiGouListLevelOneAdapter(ShenPiShiBaiActivity shibai_activity,List<CaiGouDanBean.FllistBean> mList, Context mContext) {
        this.shibai_activity = shibai_activity;
        this.mList = mList;
        this.mContext = mContext;
        this.type = "1";
        itemIsClick = new boolean[mList.size()];
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_caigou_pinlei, null, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        final CaiGouDanBean.FllistBean bean = mList.get(position);
        holder.tvPinlei.setText(bean.getClassify_name());
        adapter = new CaiGouListLevelTwoAdapter(mContext,mList.get(position).getSonorderlist());
        holder.rvListTwo.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        holder.rvListTwo.setAdapter(adapter);
        holder.rvListTwo.setFocusable(false);
        holder.rvListTwo.setNestedScrollingEnabled(false);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.isShow()) {
                    bean.setShow(false);
//                    holder.rvListTwo.setVisibility(View.GONE);
                    holder.llRongqi.setVisibility(View.GONE);
                    holder.ivJinru.setImageResource(R.mipmap.jinru);
                } else {
                    bean.setShow(true);
//                    holder.rvListTwo.setVisibility(View.VISIBLE);
                    holder.llRongqi.setVisibility(View.VISIBLE);
                    holder.ivJinru.setImageResource(R.mipmap.xia_kongxin_hui);
                }
            }
        });
        adapter.setCallBack(new CaiGouListLevelTwoAdapter.CallBack() {
            @Override
            public void isClick(View view, final int pos) {
                        switch (view.getId()) {
                            case R.id.iv_shanchu://删除当前采购单
                                //不能全删
                                if (mList.get(position).getSonorderlist().size() == 1&&mList.size()==1) {
                                    ToastUtil.showToast("不能全部删除");
                                    return;
                                }
                                confirmDialog.showDialog("是否确定删除此采购单");
                                confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        HttpManager.getInstance()
                                                .with(mContext)
                                                .setObservable(
                                                        RetrofitManager
                                                                .getService()
                                                                .delsonorderid(PreferenceUtils.getString(MyApplication.mContext, "token", ""), mList.get(position).getSonorderlist().get(pos).getSon_order_id()))
                                                .setDataListener(new HttpDataListener<String>() {
                                                    @Override
                                                    public void onNext(String data) {
                                                        Log.e("data", data + "(＾－＾)V");
                                                        ToastUtil.showToast("删除成功");
                                                        confirmDialog.dismiss();
                                                        //在存储商品id 的map中删掉当前商品
                                                        for (int i = 0; i < mList.get(position).getSonorderlist().size(); i++) {
                                                            if (mList.get(position).getSonorderlist().get(pos).getSon_order_id().equals(mList.get(position).getSonorderlist().get(i).getSon_order_id())) {
                                                                mList.get(position).getSonorderlist().remove(i);
                                                                if (mList.get(position).getSonorderlist().size()==0){
                                                                    mList.remove(position);
//                                                                    delLength();
                                                                }
                                                                if(type.equals("0")){
                                                                    activity.getlist();
                                                                } else {
                                                                    shibai_activity.getlist();
                                                                }

                                                                notifyDataSetChanged();
                                                                adapter.notifyDataSetChanged();
                                                                break;
                                                            }
                                                        }
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
                                break;
                            case R.id.iv_xiugai:
                                //修改
                                FragmentManager mFragmentManager ;
                                if(type.equals("0")){
                                    mFragmentManager = activity.getSupportFragmentManager();
                                } else {
                                    mFragmentManager = shibai_activity.getSupportFragmentManager();
                                }
                                CaiGouDanXiuGaiDailog danXiuGaiDailog = new CaiGouDanXiuGaiDailog();
                                danXiuGaiDailog.setInitStr(mList.get(position).getSonorderlist().get(pos).getClassify_name(), mList.get(position).getSonorderlist().get(pos).getPack_standard_name(), mList.get(position).getSonorderlist().get(pos).getPack_standard_id(),
                                        mList.get(position).getSonorderlist().get(pos).getSpecial_commodity(), String.valueOf(mList.get(position).getSonorderlist().get(pos).getCount()), mList.get(position).getSonorderlist().get(pos).getSon_order_id(), mList.get(position).getSonorderlist().get(pos).getSort_id())
                                        .setCallBack(new CaiGouDanXiuGaiDailog.CallBack() {
                                            @Override
                                            public void confirm(CaiGouDanBean.FllistBean.SonorderlistBean msg) {
                                                ToastUtil.showToast("修改成功");
                                                if(type.equals("0")){
                                                    activity.getlist();
                                                } else {
                                                    shibai_activity.getlist();
                                                }
                                                adapter.notifyDataSetChanged();
                                            }
                                        }).show(mFragmentManager);
                                break;
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
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
