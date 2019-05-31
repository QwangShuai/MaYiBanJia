package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/8/15.
 */

public class ShenPiShiBaiDailog extends BaseFragmentDialog implements View.OnClickListener{
    @BindView(R.id.et_shibaiyuanyin)
    EditText etShibaiyuanyin;
    private String initStr;
    private CallBack mCallBack;

    public ShenPiShiBaiDailog() {
    }

    public ShenPiShiBaiDailog setInitStr(String initStr) {
        this.initStr = initStr;
        return this;
    }

    public ShenPiShiBaiDailog setCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
        return this;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_shenpishibai;
    }

    @Override
    protected void init() {
        etShibaiyuanyin.setText(initStr);
        StringUtil.setInputNoEmoj(etShibaiyuanyin,50);
        setOnClickListener(this,R.id.bt_queding);
    }


    @Override
    public void onClick(View v) {
        String str=etShibaiyuanyin.getText().toString().trim();
        if(TextUtils.isEmpty(str)){
            ToastUtil.showToast("输入不能为空");
            return ;
        }
        if(mCallBack!=null)
            mCallBack.confirm(str);
        dismiss();
    }

    public interface CallBack{
        void confirm(String msg);
    }
}
