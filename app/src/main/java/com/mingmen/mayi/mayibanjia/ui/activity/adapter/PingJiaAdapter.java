package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.XQPingJiaBean;
import com.mingmen.mayi.mayibanjia.ui.view.AutoLineFeedLayoutManager;
import com.mingmen.mayi.mayibanjia.ui.view.XCFlowLayout;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/9/15.
 */

public class PingJiaAdapter extends BaseQuickAdapter<XQPingJiaBean,BaseViewHolder> {
    public PingJiaAdapter() {
        super(R.layout.item_pingjia);
    }

    @Override
    protected void convert(BaseViewHolder helper, XQPingJiaBean item) {
        helper.setText(R.id.tv_maijianame,item.getPjCompanyName());
        helper.setText(R.id.tv_dianming,item.getBpCompanyName());
        helper.setText(R.id.tv_pinglunshijian,item.getCreate_time());
        if (item.getReplyList()!=null){
            helper.setText(R.id.tv_huifushijian,item.getReplyList().get(0).getCreate_time());

            String huifuneirong = item.getReplyList().get(0).getComment_text();

            String[] huifuarray = huifuneirong.split(",");

            if (huifuarray.length>0){
                XCFlowLayout xcf_huifu = helper.getView(R.id.xcf_huifu);
//                PingJiaNeiRongAdapter huifuadapter=new PingJiaNeiRongAdapter();
//                RecyclerView rv_huifuneirong = helper.getView(R.id.rv_huifuneirong);
//                rv_huifuneirong.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
//                rv_huifuneirong.setAdapter(huifuadapter);
//                huifuadapter.setNewData(huifulist);
//                new AutoLineFeedLayoutManager()
                initShangpinChildViews(xcf_huifu,huifuarray);
            }
        }else{
            helper.getView(R.id.ll_shangjiahuifu).setVisibility(View.GONE);
        }

        Glide.with(mContext).load(item.getHeadPhoto()).into((ImageView) helper.getView(R.id.iv_touxiang));
        helper.setRating(R.id.rb_pingfen, Float.parseFloat(item.getStar_evaluation()));//评分
        String pingjianeirong = item.getComment_text();
        String[] pingjiaarray = pingjianeirong.split(",");
        if (pingjiaarray.length>0){
            XCFlowLayout xcf_pingjia = helper.getView(R.id.xcf_pingjia);
            initShangpinChildViews(xcf_pingjia,pingjiaarray);
//            PingJiaNeiRongAdapter pingjiaadapter=new PingJiaNeiRongAdapter();
//            RecyclerView rv_pinglunneirong = helper.getView(R.id.rv_pinglunneirong);
//            rv_pinglunneirong.setLayoutManager(new AutoLineFeedLayoutManager());
//            rv_pinglunneirong.setAdapter(pingjiaadapter);
//            pingjiaadapter.setNewData(pingjialist);

        }

    }
    private void initShangpinChildViews(XCFlowLayout xcfShangpinlishisousuo,String[] shangpinNamelist) {
        xcfShangpinlishisousuo.removeAllViews();
        ArrayList tvs = new ArrayList();
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = AppUtil.dip2px(12);
        lp.rightMargin = AppUtil.dip2px(0);
        lp.topMargin = AppUtil.dip2px(12);
        lp.bottomMargin = 0;
        for (int i = 0; i < shangpinNamelist.length; i++) {
            TextView view = new TextView(mContext);
            view.setText(shangpinNamelist[i]);
            view.setTextColor(mContext.getResources().getColor(R.color.lishisousuo));
            view.setTextSize(12);
            view.setPadding( AppUtil.dip2px(8), AppUtil.dip2px(12), AppUtil.dip2px(8), AppUtil.dip2px(12));
            view.setBackground(mContext.getDrawable(R.drawable.fillet_solid_e7e7e7_3));
            tvs.add(view);
            xcfShangpinlishisousuo.addView(view, lp);
        }
    }
}
