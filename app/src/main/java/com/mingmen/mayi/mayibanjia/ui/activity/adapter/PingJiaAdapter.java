package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
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
import com.mingmen.mayi.mayibanjia.ui.activity.DingDanXiangQingActivity;
import com.mingmen.mayi.mayibanjia.ui.view.AutoLineFeedLayoutManager;
import com.mingmen.mayi.mayibanjia.ui.view.XCFlowLayout;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

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
    protected void convert(BaseViewHolder helper, final XQPingJiaBean item) {
        helper.setText(R.id.tv_maijianame,item.getPjCompanyName());
        if(StringUtil.isValid(item.getBpCompanyName())){
            helper.setText(R.id.tv_dianming,item.getBpCompanyName());
        } else {
            helper.setGone(R.id.ll_dianpu,false);
        }

        helper.setText(R.id.tv_pinglunshijian,item.getCreate_time());
        if (item.getOrder_id()!=null&&!TextUtils.isEmpty(item.getOrder_id())){
            helper.getView(R.id.tv_dingdan).setVisibility(View.VISIBLE);
            helper.getView(R.id.tv_dingdan).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(mContext, DingDanXiangQingActivity.class);
                    it.putExtra("orderID", item.getOrder_id());
                    mContext.startActivity(it);
                }
            });
        }
        if (item.getReplyList()!=null&&item.getReplyList().size()!=0){
            helper.setText(R.id.tv_huifushijian,item.getReplyList().get(0).getCreate_time());
            String[] huifuarray = new String[item.getReplyList().get(0).getPjList()==null?0:item.getReplyList().get(0).getPjList().size()];
            for (int i=0;i<item.getReplyList().get(0).getPjList().size();i++){
                huifuarray[i] = item.getReplyList().get(0).getPjList().get(i).getSon_name();
            }

            if (huifuarray.length>0){
                XCFlowLayout xcf_huifu = helper.getView(R.id.xcf_huifu);
//                PingJiaNeiRongAdapter huifuadapter=new PingJiaNeiRongAdapter();
//                RecyclerView rv_huifuneirong = helper.getView(R.id.rv_huifuneirong);
//                rv_huifuneirong.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
//                rv_huifuneirong.setAdapter(huifuadapter);
//                huifuadapter.setNewData(huifulist);
//                new AutoLineFeedLayoutManager()
                initShangpinChildViews(xcf_huifu,huifuarray,"0");
            }
        }else{
            helper.getView(R.id.ll_shangjiahuifu).setVisibility(View.GONE);
        }

        Glide.with(mContext).load(item.getHeadPhoto()).into((ImageView) helper.getView(R.id.iv_touxiang));
        helper.setRating(R.id.rb_pingfen, item.getStar_evaluation());//评分
        String pingjianeirong = item.getComment_text();
        if (item.getPjList() != null&& item.getPjList().size()!=0) {
            XCFlowLayout xcf_pingjia = helper.getView(R.id.xcf_pingjia);
            String[] pingjiaarray = new String[item.getPjList()==null?0:item.getPjList().size()];
            for (int i=0;i<item.getPjList().size();i++){
                pingjiaarray[i] = item.getPjList().get(i).getSon_name();
            }

            if (pingjiaarray.length>0){
                initShangpinChildViews(xcf_pingjia,pingjiaarray,"1");
            }

        }
    }
    private void initShangpinChildViews(XCFlowLayout xcfShangpinlishisousuo,String[] shangpinNamelist,String type) {
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
            view.setPadding( AppUtil.dip2px(12), AppUtil.dip2px(8), AppUtil.dip2px(12), AppUtil.dip2px(8));
            if(type.equals("0")){
                view.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_hollow_fafafa_14));
            } else {
                view.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_solid_fafafa_100));
            }

            tvs.add(view);
            xcfShangpinlishisousuo.addView(view, lp);
        }
    }
}
