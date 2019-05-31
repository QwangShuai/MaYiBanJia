package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/7/10/010.
 */


public class CaipinSousuoDialog extends Dialog {

    public View view;
    @BindView(R.id.et_qiyemingcheng)
    EditText etQiyemingcheng;
    @BindView(R.id.bt_queding)
    Button btQueding;
    @BindView(R.id.tv_quxiao)
    TextView tvQuxiao;
    private Context c;
    private String leibieid;

    /**
     * 构造确认信息弹窗
     *
     * @param context 上下文
     */
    public CaipinSousuoDialog(Context context, int theme) {
        super(context, theme);
        view = View.inflate(context, R.layout.dialog_sousuo, null);
        this.setContentView(view);
        this.c = context;
        etQiyemingcheng = (EditText) findViewById(R.id.et_qiyemingcheng);
        btQueding =(Button) findViewById(R.id.bt_queding);
        tvQuxiao = (TextView)findViewById(R.id.tv_quxiao);
        StringUtil.setInputNoEmoj(etQiyemingcheng,20);
    }

    /**
     * 显示确认信息弹窗
     */
    public void showDialog() {
        this.show();
    }

    public void cancel() {
        if (!isShowing()) {
            return;
        }
        super.cancel();
    }

    public void show() {
        if (isShowing()) {
            return;
        }
        super.show();
//        /**
////         * 设置宽度全屏，要设置在show的后面
////         //         */
//        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
//        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
//        getWindow().setAttributes(layoutParams);
    }

    public void setView(View view) {
        this.view = view;
        this.setContentView(view);
    }

    public EditText getEtQiyemingcheng() {
        return etQiyemingcheng;
    }

    public void setEtQiyemingcheng(EditText etQiyemingcheng) {
        this.etQiyemingcheng = etQiyemingcheng;
    }

    public Button getBtQueding() {
        return btQueding;
    }

    public void setBtQueding(Button btQueding) {
        this.btQueding = btQueding;
    }

    public TextView getTvQuxiao() {
        return tvQuxiao;
    }

    public void setTvQuxiao(TextView tvQuxiao) {
        this.tvQuxiao = tvQuxiao;
    }

}
