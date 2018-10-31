package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.os.CountDownTimer;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.QiangDanBean;
import com.mingmen.mayi.mayibanjia.bean.ShangPinBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.QiangDanActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.QiangDanShangPinDialog;
import com.mingmen.mayi.mayibanjia.utils.DateUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/8/30.
 */

public class QiangDanAdapter extends BaseQuickAdapter<QiangDanBean,BaseViewHolder> {
    private QiangDanActivity activity;
    public QiangDanAdapter(QiangDanActivity activity) {
        super(R.layout.item_qiangdan);
        this.activity=activity;

    }
    public interface OnItemClickListener {
        void onClick(View view, int position);
    }


    @Override
    protected void convert(final BaseViewHolder helper, final QiangDanBean item) {
        helper.setText(R.id.tv_fabushijian,item.getCreate_time());
        helper.setText(R.id.tv_spming,item.getSort_name());
        helper.setText(R.id.tv_guige,item.getCount()+item.getPack_standard_name());
        helper.setText(R.id.tv_teshuyaoqiu,item.getSpecial_commodity());
        final boolean[] runningThree = new boolean[1];
        final TextView tv_daojishi = helper.getView(R.id.tv_daojishi);

//        111
        Date createTime = DateUtil.StrToDate(item.getCreate_time(), "yyyy-MM-dd HH:mm:ss");
        Date dangqianTime = new Date();
        long diff = DateUtil.dqsj(createTime, dangqianTime, "4");
        long fen = DateUtil.dqsj(createTime, dangqianTime, "3");

        if (fen<5){
            CountDownTimer downTimer = new CountDownTimer(5*60*1000-diff, 1000) {
                @Override
                public void onTick(long l) {
                    tv_daojishi.setText((l / 1000) + "秒");
                }
                @Override
                public void onFinish() {
                    runningThree[0] = true;
                    tv_daojishi.setVisibility(View.GONE);
                }
            };
            downTimer.start();
        }

        final Button bt_qiangdan = helper.getView(R.id.bt_qiangdan);
        final TextView tv_dengdaiqueren = helper.getView(R.id.tv_dengdaiqueren);
//        Log.e("item.getCreate_time()",item.getCreate_time()+"-----");
//        Log.e("item.getSon_order_id()",item.getSon_order_id()+"-----");
        Log.e("item.getState_type()",item.getState_type()+"-----");
        if ("0".equals(item.getState_type())){
            bt_qiangdan.setVisibility(View.GONE);
            tv_dengdaiqueren.setVisibility(View.VISIBLE);
            tv_dengdaiqueren.setText("抢单成功");
        }else if("1".equals(item.getState_type())){
            bt_qiangdan.setVisibility(View.GONE);
            tv_dengdaiqueren.setVisibility(View.VISIBLE);
            tv_dengdaiqueren.setText("抢单失败");
        }else if ("2".equals(item.getState_type())){
            bt_qiangdan.setVisibility(View.VISIBLE);
            tv_dengdaiqueren.setVisibility(View.GONE);
        }else if ("4".equals(item.getState_type())){
            bt_qiangdan.setVisibility(View.GONE);
            tv_dengdaiqueren.setVisibility(View.VISIBLE);
            tv_dengdaiqueren.setText("等待买家确认");
        }
        helper.setOnClickListener(R.id.bt_qiangdan, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qiangdanshangpin(item,bt_qiangdan,tv_dengdaiqueren,tv_daojishi,helper.getPosition());
            }
        });

    }

    private void qiangdanshangpin(final QiangDanBean item, final Button bt_qiangdan, final TextView tv_dengdaiqueren, final TextView tv_daojishi, final int position){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .qiangdanshangpin(item.getCompany_id(),item.getSort_id(),item.getSon_order_id()))
                .setDataListener(new HttpDataListener<List<ShangPinBean>>() {
                    @Override
                    public void onNext(List<ShangPinBean> data) {
                        new QiangDanShangPinDialog()
                                .setData(data)
                                .setCallBack(new QiangDanShangPinDialog.CallBack() {
                                    @Override
                                    public void xuanzhongitem(ShangPinBean msg,String fujiafei) {
                                        if (msg==null){
                                        }else{
                                            Log.e("canshu","user_token="+PreferenceUtils.getString(MyApplication.mContext,"token","")+"&commodity_id="+msg.getCommodity_id()+"&market_id="+item.getMarket_id()+"&son_order_id"+item.getSon_order_id());
                                            HttpManager.getInstance()
                                                    .with(mContext)
                                                    .setObservable(
                                                            RetrofitManager
                                                                    .getService()
                                                                    .qiangdan(PreferenceUtils.getString(MyApplication.mContext,"token",""),
                                                                            msg.getCommodity_id(),item.getQuote_price_id(),"".equals(fujiafei)?"0":fujiafei))
                                                    .setDataListener(new HttpDataListener<String>() {
                                                        @Override
                                                        public void onNext(String data) {
                                                            bt_qiangdan.setVisibility(View.GONE);
                                                            tv_dengdaiqueren.setVisibility(View.VISIBLE);
                                                            tv_dengdaiqueren.setText("抢单中");
                                                            tv_daojishi.setVisibility(View.GONE);
                                                        }
                                                    },false);
                                        }

                                    }
                                }) .show(activity.getSupportFragmentManager());
                    }
                },false);
    }
}
