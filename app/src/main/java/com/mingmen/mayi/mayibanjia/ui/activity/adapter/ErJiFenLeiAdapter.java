package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.FenLeiBean;
import com.mingmen.mayi.mayibanjia.bean.ShouYeLeiBean;

/**
 * Created by Administrator on 2018/8/30.
 */

public class ErJiFenLeiAdapter extends BaseQuickAdapter<ShouYeLeiBean,BaseViewHolder> {

    public ErJiFenLeiAdapter() {
        super(R.layout.item_erjifenlei);
    }
    private CallBack mCallBack;

    public String getXuanzhongid() {
        return xuanzhongid;
    }

    public void setXuanzhongid(String xuanzhongid) {
        this.xuanzhongid = xuanzhongid;
    }

    private String xuanzhongid="";
    public ErJiFenLeiAdapter setCallBack(CallBack mCallBack){
        this.mCallBack=mCallBack;
        return  this;
    };
    public interface CallBack{
        void xuanzhong(ShouYeLeiBean msg);
    }
    public interface OnItemClickListener {
        void onClick(View view, int position);
    }
    @Override
    protected void convert(final BaseViewHolder helper,final ShouYeLeiBean item) {
        TextView tv_erjifenlei = helper.getView(R.id.tv_erjifenlei);
        View view_xuanzhong = helper.getView(R.id.view_xuanzhong);
        if (!xuanzhongid.equals(item.getClassify_id())){
            tv_erjifenlei.setTextColor(mContext.getResources().getColor(R.color.zicolor));
            view_xuanzhong.setVisibility(View.GONE);
        }else{
            tv_erjifenlei.setTextColor(mContext.getResources().getColor(R.color.zangqing));
            view_xuanzhong.setVisibility(View.VISIBLE);
        }

        helper.setText(R.id.tv_erjifenlei,item.getClassify_name());

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
