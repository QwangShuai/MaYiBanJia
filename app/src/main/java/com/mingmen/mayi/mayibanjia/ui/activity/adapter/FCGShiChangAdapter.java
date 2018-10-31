package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.ShiChangBean;

import java.util.List;

/**
 * Created by Administrator on 2018/9/6.
 */

public class FCGShiChangAdapter extends BaseQuickAdapter<ShiChangBean,BaseViewHolder> {
    private String xuanzhongid="";
    public FCGShiChangAdapter() {
        super(R.layout.item_shichang);
    }

    public void setXuanzhong(String xuanzhongid){
        this.xuanzhongid=xuanzhongid;
        notifyDataSetChanged();
    }
    @Override
    protected void convert(BaseViewHolder helper, ShiChangBean item) {
        TextView shichangming=helper.getView(R.id.tv_shichangming);
        if(xuanzhongid.equals(item.getMark_id())){
            shichangming.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_solid_zangqing_3));
            shichangming.setTextColor(mContext.getResources().getColor(R.color.white));

        }else{
            shichangming.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_hollow_999999_3));
            shichangming.setTextColor(mContext.getResources().getColor(R.color.zicolor));
        }
        helper.setText(R.id.tv_shichangming,item.getMarket_name());
    }
}
