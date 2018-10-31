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

public class ConfirmDialog extends Dialog {
    public View view;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;

    private Context c;

    /**
     * 构造确认信息弹窗
     *
     * @param context 上下文
     */
    public ConfirmDialog(Context context, int theme) {
        super(context, theme);
        view = View.inflate(context, R.layout.dialog_confirm, null);
        this.setContentView(view);
        this.c = context;
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        tvSubmit = (TextView) findViewById(R.id.tv_submit);
    }

    /**
     * 显示确认信息弹窗
     */
    public void showDialog(String title) {
        tvTitle.setText(title);
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

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }

    public TextView getTvCancel() {
        return tvCancel;
    }

    public void setTvCancel(TextView tvCancel) {
        this.tvCancel = tvCancel;
    }

    public TextView getTvSubmit() {
        return tvSubmit;
    }

    public void setTvSubmit(TextView tvSubmit) {
        this.tvSubmit = tvSubmit;
    }
}
