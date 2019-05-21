package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CbkListBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.utils.GlideUtils;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.custom.GlideRoundTransform;
import com.mingmen.mayi.mayibanjia.utils.custom.MarqueeTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/10/8.
 */

public class ChengBenKaAdapter extends RecyclerView.Adapter<ChengBenKaAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<CbkListBean> mList;

    public ChengBenKaAdapter(Context mContext, List<CbkListBean> list) {
        this.mContext = mContext;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cbk, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final CbkListBean bean = mList.get(position);
        GlideUtils.cachePhotoRound(mContext,holder.ivCaipin,bean.getFood_photo(),5);
        holder.tvName.setMarqueeEnable(true);
        holder.tvName.setText(bean.getFood_name());
        holder.tvChengben.setText(bean.getCosting()+"");
        holder.etShoumaijia.setText(bean.getSale_price()+"");
        holder.tvMaoli.setText(bean.getGross_profit()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToastLong("查看详情");
            }
        });
        holder.etShoumaijia.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(StringUtil.isValid(s.toString())){
                    if(Integer.valueOf(s.toString())>0){
                        updatePrice(bean.getFood_formula_id(),s.toString());
                    } else {
                        holder.etShoumaijia.setText("1");
                        ToastUtil.showToastLong("售卖价格最小为1元");
                    }
                } else {
                    holder.etShoumaijia.setText("1");
                    ToastUtil.showToastLong("售卖价格不能为空，且最小为1元");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_zoushitu)
        ImageView ivZoushitu;
        @BindView(R.id.iv_caipin)
        ImageView ivCaipin;
        @BindView(R.id.tv_chengben)
        TextView tvChengben;
        @BindView(R.id.tv_maoli)
        TextView tvMaoli;
        @BindView(R.id.tv_name)
        MarqueeTextView tvName;
        @BindView(R.id.et_shoumaijia)
        EditText etShoumaijia;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    private void updatePrice(String id,String price) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .updatePrice(PreferenceUtils.getString(MyApplication.mContext, "token", ""), id,price))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                    }
                },false);
    }
}
