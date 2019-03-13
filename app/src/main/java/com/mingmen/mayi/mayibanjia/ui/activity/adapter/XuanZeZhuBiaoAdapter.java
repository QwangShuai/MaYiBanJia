package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.CaiGouMingChengBean;
import com.mingmen.mayi.mayibanjia.bean.ShangPinBean;

/**
 * Created by Administrator on 2018/8/30.
 */

public class XuanZeZhuBiaoAdapter extends BaseQuickAdapter<CaiGouMingChengBean,BaseViewHolder> {
    private String xuanzhong;
    public XuanZeZhuBiaoAdapter() {
        super(R.layout.item_mohuchaxun);
    }

    public void setXuanzhong(String xuanzhong){
        this.xuanzhong=xuanzhong;
        notifyDataSetChanged();
    }
    @Override
    protected void convert(BaseViewHolder helper, CaiGouMingChengBean item) {

        if (xuanzhong==item.getPurchase_id()){
            helper.setBackgroundColor(R.id.ll_kuang,mContext.getResources().getColor(R.color.hei20));
        }else{
            helper.setBackgroundColor(R.id.ll_kuang,mContext.getResources().getColor(R.color.white));
        }
        helper.setText(R.id.tv_ming,item.getPurchase_name());
        helper.addOnClickListener(R.id.ll_kuang);
    }
}
