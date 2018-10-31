package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/9/19.
 */

public class XuanZeRiQiDialog extends Dialog {
    @BindView(R.id.datePicker)
    DatePicker datePicker;
    @BindView(R.id.bt_quxiao)
    Button btQuxiao;
    @BindView(R.id.bt_queding)
    Button btQueding;

    private CallBack mCallBack;
    private int nian;
    private int yue;
    private int ri;
    private String yueString;
    private String riString;

    /**
     * 构造确认信息弹窗
     *
     * @param context 上下文
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public XuanZeRiQiDialog(Context context, int theme) {
        super(context, theme);
        View view = View.inflate(context, R.layout.dialog_xuanzeriqi, null);
        this.setContentView(view);
        datePicker = findViewById(R.id.datePicker);
        btQuxiao = findViewById(R.id.bt_quxiao);
        btQueding = findViewById(R.id.bt_queding);
        datePicker.setMaxDate(Calendar.getInstance().getTimeInMillis());
        datePicker.setDescendantFocusability(DatePicker.FOCUS_BLOCK_DESCENDANTS);

        btQueding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nian = datePicker.getYear();
                yue = datePicker.getMonth() + 1;
                ri = datePicker.getDayOfMonth();

                if (yue<10){
                    yueString = "0" + yue;
                }else{
                    yueString=yue+"";
                }
                if (ri<10){
                    riString = "0" + ri;
                }else{
                    riString=ri+"";
                }

                if (mCallBack!=null)
                    mCallBack.getDate(nian +"-"+ yueString +"-"+ riString);
                dismiss();
            }
        });
        btQuxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public XuanZeRiQiDialog setmCallBack(CallBack mCallBack){
        this.mCallBack=mCallBack;
        return this;
    }
   
    public interface CallBack{
        void getDate(String date);
    }

}
