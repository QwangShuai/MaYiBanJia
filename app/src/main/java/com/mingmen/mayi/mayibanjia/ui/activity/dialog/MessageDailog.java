package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/8/15.
 */

public class MessageDailog extends Dialog {

    private EditText etTeshu;
    private Button btQuxiao;
    private Button btQueren;
    private TextView tvName;
    private TextView tvHint;

    Unbinder unbinder;
    private CallBack mCallBack;
    private String teshuyaoqiu = "";
    private Context context;
    private String name;
    private String tsyq;

    public MessageDailog(@NonNull Context context,String name,String tsyq, CallBack mCallBack) {
        super(context);
        this.context = context;
        this.mCallBack = mCallBack;
        this.name = name;
        this.tsyq = tsyq;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    protected void init() {
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_message, null);
        setContentView(v);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
        tvName = (TextView) v.findViewById(R.id.tv_name);
        tvHint = (TextView) v.findViewById(R.id.tv_hint);
        etTeshu = (EditText) v.findViewById(R.id.et_teshu);
        btQueren = (Button) v.findViewById(R.id.bt_queren);
        btQuxiao = (Button) v.findViewById(R.id.bt_quxiao);
        StringUtil.setInputNoEmoj(etTeshu,50);
        tvName.setText(name);
        etTeshu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvHint.setText(s.toString().trim().length()+"/50");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etTeshu.setText(tsyq);
        btQueren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addName();
            }
        });
        btQuxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }


    public interface CallBack {
        void confirm(String msg);
    }


    //获取用户填写的数据
    private void huoqushuju() {
        teshuyaoqiu = etTeshu.getText().toString().trim();
    }

    private void addName() {
        huoqushuju();
        dismiss();
        mCallBack.confirm(teshuyaoqiu);
    }
}
