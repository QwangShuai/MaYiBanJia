package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.FenLeiBean;

/**
 * Created by Administrator on 2018/8/30.
 */

public class SanJiFenLeiAdapter extends BaseQuickAdapter<FenLeiBean,BaseViewHolder> {

    public SanJiFenLeiAdapter() {
        super(R.layout.item_sanjifenlei);
    }
    private CallBack mCallBack;
    private String xuanzhongid="";
    public SanJiFenLeiAdapter setCallBack(CallBack mCallBack){
        this.mCallBack=mCallBack;
        return  this;
    };
    public interface CallBack{
        void xuanzhong(FenLeiBean msg);
    }
    public interface OnItemClickListener {
        void onClick(View view, int position);
    }
    @Override
    protected void convert(final BaseViewHolder helper,final FenLeiBean item) {
        TextView tv_sanjifenlei = helper.getView(R.id.tv_sanjifenlei);
        if (xuanzhongid.equals(item.getClassify_id())){
            tv_sanjifenlei.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_hollow_zangqing_3));
        }else{
            tv_sanjifenlei.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_hollow_e7e7e7_3));
        }
        helper.setText(R.id.tv_sanjifenlei,item.getClassify_name());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCallBack!=null)
                    mCallBack.xuanzhong(item);
                xuanzhongid=item.getClassify_id();
            }
        });
    }
}
