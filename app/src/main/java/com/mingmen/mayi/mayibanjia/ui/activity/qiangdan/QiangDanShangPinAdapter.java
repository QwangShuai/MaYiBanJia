package com.mingmen.mayi.mayibanjia.ui.activity.qiangdan;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.ShangPinBean;

/**
 * Created by Administrator on 2018/8/30.
 */

public class QiangDanShangPinAdapter extends BaseQuickAdapter<ShangPinBean,BaseViewHolder> {
    private String xuanzhong;
    public QiangDanShangPinAdapter() {
        super(R.layout.item_qiangdanshangpin);
    }

    public void setXuanzhong(String xuanzhong){
        this.xuanzhong=xuanzhong;
        notifyDataSetChanged();
    }
    @Override
    protected void convert(BaseViewHolder helper, ShangPinBean item) {

        if (xuanzhong==item.getCommodity_id()){
            helper.setBackgroundColor(R.id.ll_kuang,mContext.getResources().getColor(R.color.hei20));
        }else{
            helper.setBackgroundColor(R.id.ll_kuang,mContext.getResources().getColor(R.color.white));
        }
        helper.setText(R.id.tv_spming,item.getCommodity_name());
        helper.setText(R.id.tv_danjia,item.getPrice());
        helper.addOnClickListener(R.id.ll_kuang);
    }
}
