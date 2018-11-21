package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.ghdingdan.BaseGHOrderFragment;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/10/19.
 */

public class TuikuanDialog extends Dialog{
    @BindView(R.id.et_tuikuan)
    EditText etTuikuan;
    @BindView(R.id.et_yuanyin)
    EditText etYuanyin;
    @BindView(R.id.tishi)
    TextView tishi;
    @BindView(R.id.tv_order_number)
    TextView tvOrderNumber;
    @BindView(R.id.btn_cancle)
    Button btnCancle;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    Unbinder unbinder;
    private String order_id;
    private String order_number;
    private Context context;
    private BaseGHOrderFragment fragment;

    public TuikuanDialog(@NonNull Context context,String order_id,String order_number,BaseGHOrderFragment fragment) {
        super(context);
        this.context = context;
        this.order_id = order_id;
        this.order_number = order_number;
        this.fragment = fragment;
    }

    @OnClick({R.id.btn_cancle,R.id.btn_confirm})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancle:
                dismiss();
                break;
            case R.id.btn_confirm:
                if(TextUtils.isEmpty(etTuikuan.getText().toString())){
                    ToastUtil.showToast("退款金额不可以为空");
                } else {
                    orderTuikuan();
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    protected void init() {
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_tuikuan,null);
        unbinder = ButterKnife.bind(this, v);
        setContentView(v);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
        tvOrderNumber.setText("订单编号:"+order_number);
        etYuanyin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tishi.setText(s.toString().trim().length()+"/200");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbinder.unbind();
    }
    public void orderTuikuan(){
        HttpManager.getInstance()
                .with(context)
                .setObservable(RetrofitManager.getService()
                        .orderTuikuan(PreferenceUtils.getString(MyApplication.mContext, "token", ""),
                                order_id,etTuikuan.getText().toString(),etYuanyin.getText().toString().trim()))
                .setDataListener(new HttpDataListener<String>() {

                    @Override
                    public void onNext(String bean) {
                        ToastUtil.showToast("退款成功");
                        fragment.shuaxinData();
                        dismiss();
                    }
                },true);

    }
}
