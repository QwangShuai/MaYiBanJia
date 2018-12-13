package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.ShiChangBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShenPiLevelOneAdapter;
import com.mingmen.mayi.mayibanjia.ui.view.XCFlowLayout;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/27.
 */

public class ShiChangDialog extends Dialog {
    @BindView(R.id.iv_yiji)
    ImageView ivYiji;
    @BindView(R.id.ll_yijishichang)
    LinearLayout llYijishichang;
    @BindView(R.id.xcf_shichang1)
    XCFlowLayout xcfShichang1;
    @BindView(R.id.iv_erji)
    ImageView ivErji;
    @BindView(R.id.ll_erjishichang)
    LinearLayout llErjishichang;
    @BindView(R.id.xcf_shichang2)
    XCFlowLayout xcfShichang2;
    @BindView(R.id.iv_sanji)
    ImageView ivSanji;
    @BindView(R.id.ll_sanjishichang)
    LinearLayout llSanjishichang;
    @BindView(R.id.xcf_shichang3)
    XCFlowLayout xcfShichang3;
    private Context context;
    private List<ShiChangBean> list1 = new ArrayList<>();
    private List<ShiChangBean> list2 = new ArrayList<>();
    private List<ShiChangBean> list3 = new ArrayList<>();
    boolean yiji, erji, sanji;
    private CallBack callBack;

    public ShiChangDialog(@NonNull Context context, CallBack callBack) {
        super(context);
        this.context = context;
        this.callBack = callBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @OnClick({R.id.ll_yijishichang, R.id.ll_erjishichang, R.id.ll_sanjishichang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_yijishichang:
                getmoren("1");
                break;
            case R.id.ll_erjishichang:
                getmoren("2");
                break;
            case R.id.ll_sanjishichang:
                getmoren("3");
                break;
        }
    }

    public interface CallBack {
        void confirm(String id, String name);
    }

    public void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_shichang, null);
        setContentView(view);
        ButterKnife.bind(this);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
        getmoren("1");
        getmoren("2");
        getmoren("3");
    }

    private void initShangpinChildViews(XCFlowLayout xcfShangpinlishisousuo, final List<ShiChangBean> mList) {
        xcfShangpinlishisousuo.removeAllViews();
        ArrayList<TextView> tvs = new ArrayList();
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = AppUtil.dip2px(12);
        lp.rightMargin = AppUtil.dip2px(0);
        lp.topMargin = AppUtil.dip2px(12);
        lp.bottomMargin = 0;
        for (int i = 0; i < mList.size(); i++) {
            TextView view = new TextView(context);
            view.setText(mList.get(i).getMarket_name());
            view.setTextColor(context.getResources().getColor(R.color.lishisousuo));
            view.setTextSize(12);
            view.setPadding(AppUtil.dip2px(12), AppUtil.dip2px(8), AppUtil.dip2px(12), AppUtil.dip2px(8));
            view.setBackground(context.getResources().getDrawable(R.drawable.fillet_hollow_zangqing_3));
            tvs.add(view);
            xcfShangpinlishisousuo.addView(view, lp);
        }
        for (int i = 0;i<tvs.size();i++){
            final int finalI = i;
            tvs.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    callBack.confirm(mList.get(finalI).getMark_id(), mList.get(finalI).getMarket_name());
                }
            });
        }
    }

    //获取此账户填写的省市区  并显示出来
    private void getmoren(final String level) {
        HttpManager.getInstance()
                .with(context)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getmorendiqu(PreferenceUtils.getString(MyApplication.mContext, "token", ""), level))
                .setDataListener(new HttpDataListener<List<ShiChangBean>>() {
                    @Override
                    public void onNext(List<ShiChangBean> data) {
                        int mysize = data==null?0:data.size();
                        if(mysize!=0){
                            switch (level){
                                case "1":
                                    list1 = data;
                                    initShangpinChildViews(xcfShichang1,list1);
                                    break;
                                case "2":
                                    list2 = data;
                                    initShangpinChildViews(xcfShichang2,list2);
                                    break;
                                case "3":
                                    list3 = data;
                                    initShangpinChildViews(xcfShichang3,list3);
                                    break;
                            }
                        } else {
                            switch (level){
                                case "1":
                                    llYijishichang.setVisibility(View.GONE);
                                    break;
                                case "2":
                                    llErjishichang.setVisibility(View.GONE);
                                    break;
                                case "3":
                                    llSanjishichang.setVisibility(View.GONE);
                                    break;
                            }
                        }

//                        setShowView(level);
                    }
                },false);
    }

    public void setShowView(String type) {
        xcfShichang1.setVisibility(View.GONE);
        xcfShichang2.setVisibility(View.GONE);
        xcfShichang3.setVisibility(View.GONE);
        yiji = false;
        erji = false;
        sanji = false;
        ivYiji.setImageResource(R.mipmap.jinru);
        ivErji.setImageResource(R.mipmap.jinru);
        ivSanji.setImageResource(R.mipmap.jinru);
        switch (type) {
            case "1":
                xcfShichang1.setVisibility(View.VISIBLE);
                yiji = true;
                ivYiji.setImageResource(R.mipmap.xia_kongxin_hui);
                initShangpinChildViews(xcfShichang1,list1);
                break;
            case "2":
                xcfShichang2.setVisibility(View.VISIBLE);
                erji = true;
                ivErji.setImageResource(R.mipmap.xia_kongxin_hui);
                initShangpinChildViews(xcfShichang2,list2);
                break;
            case "3":
                xcfShichang3.setVisibility(View.VISIBLE);
                sanji = true;
                ivSanji.setImageResource(R.mipmap.xia_kongxin_hui);
                initShangpinChildViews(xcfShichang3,list3);
                break;
        }
    }
}
