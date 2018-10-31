package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.AllShiChangBean;
import com.mingmen.mayi.mayibanjia.bean.FenLeiBean;

/**
 * Created by Administrator on 2018/8/30.
 */

public class ShiChangAdapter extends BaseQuickAdapter<AllShiChangBean.Bean,BaseViewHolder> {

    public ShiChangAdapter() {
        super(R.layout.item_shichang);
    }
    private CallBack mCallBack;
    private String xuanzhongid="";
    public ShiChangAdapter setCallBack(CallBack mCallBack){
        this.mCallBack=mCallBack;
        return  this;
    };
    public ShiChangAdapter setXuanZhongId(String xuanzhongid){
        this.xuanzhongid=xuanzhongid;
        return  this;
    };
    public interface CallBack{
        void xuanzhong(AllShiChangBean.Bean msg);
    }
    public interface OnItemClickListener {
        void onClick(View view, int position);
    }
    @Override
    protected void convert(final BaseViewHolder helper,final AllShiChangBean.Bean item) {
        TextView tv_shichangming = helper.getView(R.id.tv_shichangming);
        Log.e("xuanzhongid",xuanzhongid+"---");
        Log.e("item.getMark_id()",item.getMark_id()+"---");
        if (xuanzhongid.equals(item.getMark_id())){

            tv_shichangming.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_hollow_zangqing_3));
        }else{
            tv_shichangming.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_hollow_999999_3));
        }
        helper.setText(R.id.tv_shichangming,item.getMarket_name());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCallBack!=null)
                    mCallBack.xuanzhong(item);

            }
        });
    }
}
