package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.CaiGouXuQiuActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/11/12.
 */

public class CaiGouListXuQiuLevelTwoAdapter extends RecyclerView.Adapter<CaiGouListXuQiuLevelTwoAdapter.ViewHolder> {

    private Context mContext;
    private ViewHolder viewHolder;
    private List<CaiGouDanBean.FllistBean.SonorderlistBean> mList;
    public CallBack callBack;
    private int pos;
    private CaiGouXuQiuActivity activity;
    public CaiGouListXuQiuLevelTwoAdapter(Context mContext, List<CaiGouDanBean.FllistBean.SonorderlistBean> mList,int pos,CaiGouXuQiuActivity activity) {
        this.mContext = mContext;
        this.mList = mList;
        this.pos = pos;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_caigou_shangpin_xuqiu, null, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final CaiGouDanBean.FllistBean.SonorderlistBean listBean = mList.get(position);
        holder.tvShangpin.setText(listBean.getClassify_name());
        holder.tvGuige.setText(listBean.getSpec_description());
        holder.tvMiaoshu.setText("规格详情:"+listBean.getPack_standard_name());
        if(StringUtil.isValid(listBean.getOften_name_id())){
            holder.tvTjcg.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_solid_zangqing_3));
            holder.tvTjcg.setTextColor(mContext.getResources().getColor(R.color.white));
        } else {
            holder.tvTjcg.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_hollow_999999_3));
            holder.tvTjcg.setTextColor(mContext.getResources().getColor(R.color.zicolor));
        }
        holder.tvTjcg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.isClick(v, position);
            }
        });
        if(listBean.getCount()!=0){
            holder.etCaigouliang.setText(listBean.getCount()+"");
        }
        if(StringUtil.isValid(listBean.getSpecial_commodity())){
            holder.tvTsyq.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_solid_zangqing_3));
            holder.tvTsyq.setTextColor(mContext.getResources().getColor(R.color.white));
        } else {
            holder.tvTsyq.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_hollow_999999_3));
            holder.tvTsyq.setTextColor(mContext.getResources().getColor(R.color.zicolor));
        }

        holder.ivShanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.isClick(v, position);
            }
        });
        holder.etCaigouliang.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(StringUtil.isValid(s.toString().trim())) {
                    if (StringUtil.isValid(s.toString().trim())) {
                        int num = Integer.valueOf(s.toString().trim());
                        activity.setListData(pos, position, num);
                        HttpManager.getInstance()
                                .with(mContext)
                                .setObservable(
                                        RetrofitManager
                                                .getService()
                                                //user_token  是否是特殊商品不是0 是1    如果是特殊商品 填写要求   市场id  类别id  产品数量
                                                .editorXuqiudan(num + "",
                                                        PreferenceUtils.getString(MyApplication.mContext, "token", ""),
                                                        mList.get(position).getSon_order_id(),
                                                        listBean.getSpecial_commodity(), ""))
                                .setDataListener(new HttpDataListener<CaiGouDanBean.FllistBean.SonorderlistBean>() {
                                    @Override
                                    public void onNext(CaiGouDanBean.FllistBean.SonorderlistBean data) {
//                                        activity.getlist();
                                    }
                                }, false);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
//        holder.etCaigouliang.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(hasFocus){
//
//                } else {
//                    if(StringUtil.isValid(holder.etCaigouliang.getText().toString().trim())){
//                        int num = Integer.valueOf(holder.etCaigouliang.getText().toString().trim());
//                        activity.setListData(pos,position,num);
//                        HttpManager.getInstance()
//                                .with(mContext)
//                                .setObservable(
//                                        RetrofitManager
//                                                .getService()
//                                                //user_token  是否是特殊商品不是0 是1    如果是特殊商品 填写要求   市场id  类别id  产品数量
//                                                .editorXuqiudan(num+"",
//                                                        PreferenceUtils.getString(MyApplication.mContext, "token",""),
//                                                        mList.get(position).getSon_order_id(),
//                                                        listBean.getSpecial_commodity(),""))
//                                .setDataListener(new HttpDataListener<CaiGouDanBean.FllistBean.SonorderlistBean>() {
//                                    @Override
//                                    public void onNext(CaiGouDanBean.FllistBean.SonorderlistBean data) {
////                                        activity.getlist();
//                                    }
//                                },false);
//                    }
//                }
//            }
//        });
        holder.tvTsyq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.isClick(v, position);
            }
        });
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_shangpin)
        TextView tvShangpin;
        @BindView(R.id.et_caigouliang)
        EditText etCaigouliang;
        @BindView(R.id.tv_guige)
        TextView tvGuige;
        @BindView(R.id.tv_miaoshu)
        TextView tvMiaoshu;
//        @BindView(R.id.iv_xiangxia)
//        ImageView ivXiangxia;
        @BindView(R.id.rl_guige)
        RelativeLayout rlGuige;
        @BindView(R.id.tv_tsyq)
        TextView tvTsyq;
        @BindView(R.id.tv_tjcg)
        TextView tvTjcg;
        @BindView(R.id.iv_shanchu)
        ImageView ivShanchu;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface CallBack {
        void isClick(View v, int pos);
    }
}
