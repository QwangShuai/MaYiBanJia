package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.FenLeiBean;
import com.mingmen.mayi.mayibanjia.bean.QiangDanBean;
import com.mingmen.mayi.mayibanjia.bean.ShangPinBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.QiangDanActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.GengDuoShangJiaDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.QiangDanShangPinDialog;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/8/30.
 */

public class YiJiFenLeiAdapter extends BaseQuickAdapter<FenLeiBean,BaseViewHolder> {

    public YiJiFenLeiAdapter() {
        super(R.layout.item_yijipinlei);
    }
    private CallBack mCallBack;
    private String xuanzhongid="";
    public YiJiFenLeiAdapter setCallBack(CallBack mCallBack){
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
        TextView yijifenlei = helper.getView(R.id.tv_yijifenlei);
        if (xuanzhongid.equals(item.getClassify_id())){
            yijifenlei.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_hollow_zangqing_3));
        }else{
            yijifenlei.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_hollow_999999_3));
        }
        helper.setText(R.id.tv_yijifenlei,item.getClassify_name());
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
