package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShenPiLevelOneAdapter;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/7/12/012.
 */

public class YunFeiDialog extends BaseFragmentDialog {

    @BindView(R.id.tv_yunfei)
    TextView tvYunfei;
    @BindView(R.id.et_yunfei)
    EditText etYunfei;
    @BindView(R.id.bt_cancle)
    Button btCancle;
    @BindView(R.id.bt_submit)
    Button btSubmit;
    private double yunfei;
    Unbinder unbinder;
    private CallBack callBack;
    private Context context;
    public YunFeiDialog() {
    }

    public YunFeiDialog setdata(Context context,double yunfei, CallBack callBack){
        this.yunfei = yunfei;
        this.callBack = callBack;
        this.context = context;
        return this;
    }
    public interface CallBack{
        void succeed(String jine);
    };
    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_yunfei;
    }

    public void init() {
        Window dialogWindow = getDialog().getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
        tvYunfei.setText(yunfei+"");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bt_cancle, R.id.bt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_cancle:
                dismiss();
                break;
            case R.id.bt_submit:
                if(Double.valueOf(etYunfei.getText().toString())<=yunfei){
                    dismiss();
                    callBack.succeed(etYunfei.getText().toString());
                } else {
                    ToastUtil.showToast("请输入正确的运费，并且不能超过实际运费");
                }
                break;
        }
    }
}
