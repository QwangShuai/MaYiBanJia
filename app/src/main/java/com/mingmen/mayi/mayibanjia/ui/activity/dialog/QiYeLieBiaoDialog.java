package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/7/10/010.
 */


public class QiYeLieBiaoDialog extends Dialog {
    public View view;
    @BindView(R.id.ll_shanchu)
    LinearLayout llShanchu;
    @BindView(R.id.ll_bianji)
    LinearLayout llBianji;
    @BindView(R.id.iv_guanbi)
    ImageView ivGuanbi;
    private Context c;


    /**
     * 构造确认信息弹窗
     *
     * @param context 上下文
     */
    public QiYeLieBiaoDialog(Context context, int theme) {
        super(context, theme);
        view = View.inflate(context, R.layout.dialog_qiyeliebiao, null);
        this.setContentView(view);
        this.c = context;
        llShanchu = (LinearLayout) findViewById(R.id.ll_shanchu);
        llBianji = (LinearLayout) findViewById(R.id.ll_bianji);
        ivGuanbi = (ImageView) findViewById(R.id.iv_guanbi);
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
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(layoutParams);
    }

    public void setView(View view) {
        this.view = view;
        this.setContentView(view);
    }

    public LinearLayout getLlShanchu() {
        return llShanchu;
    }

    public void setLlShanchu(LinearLayout llShanchu) {
        this.llShanchu = llShanchu;
    }

    public LinearLayout getLlBianji() {
        return llBianji;
    }

    public void setLlBianji(LinearLayout llBianji) {
        this.llBianji = llBianji;
    }

    public ImageView getIvGuanbi() {
        return ivGuanbi;
    }

    public void setIvGuanbi(ImageView ivGuanbi) {
        this.ivGuanbi = ivGuanbi;
    }
}
