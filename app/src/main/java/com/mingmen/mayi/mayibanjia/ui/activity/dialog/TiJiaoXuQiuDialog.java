package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/7/12/012.
 */

public class TiJiaoXuQiuDialog extends Dialog {
    public View view;
    @BindView(R.id.tv_caigoudan)
    TextView tvCaigoudan;
    @BindView(R.id.tv_shouye)
    TextView tvShouye;


    private Context c;

    /**
     * 构造确认信息弹窗
     *
     * @param context 上下文
     */
    public TiJiaoXuQiuDialog(Context context, int theme) {
        super(context, theme);
        view = View.inflate(context, R.layout.dialog_tijiaoxuqiu, null);
        this.setContentView(view);
        this.c = context;
        tvCaigoudan=(TextView)findViewById(R.id.tv_caigoudan);
        tvShouye=(TextView)findViewById(R.id.tv_shouye);

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
//         * 设置宽度全屏，要设置在show的后面
//         //         */
//        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
//        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
//        getWindow().setAttributes(layoutParams);
    }

    public void setView(View view) {
        this.view = view;
        this.setContentView(view);
    }

    public TextView getTvCaigoudan() {
        return tvCaigoudan;
    }

    public void setTvCaigoudan(TextView tvCaigoudan) {
        this.tvCaigoudan = tvCaigoudan;
    }

    public TextView getTvShouye() {
        return tvShouye;
    }

    public void setTvShouye(TextView tvShouye) {
        this.tvShouye = tvShouye;
    }
}
