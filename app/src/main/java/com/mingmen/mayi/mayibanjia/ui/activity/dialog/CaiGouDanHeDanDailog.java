package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mingmen.mayi.mayibanjia.R;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/8/15.
 */

public class CaiGouDanHeDanDailog extends Dialog {

    private EditText etTeshu;
    private Button btQuxiao;
    private Button btQueren;

    Unbinder unbinder;
    private CallBack mCallBack;
    private String teshuyaoqiu = "";
    private Context context;

    public CaiGouDanHeDanDailog(@NonNull Context context, CallBack mCallBack) {
        super(context);
        this.context = context;
        this.mCallBack = mCallBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    protected void init() {
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_caigoudanhedan, null);
        setContentView(v);
        etTeshu = (EditText) v.findViewById(R.id.et_teshu);
        btQueren = (Button) v.findViewById(R.id.bt_queren);
        btQuxiao = (Button) v.findViewById(R.id.bt_quxiao);

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
