package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/7/10/010.
 */


public class PhotoDialog extends Dialog {
    public View view;
    @BindView(R.id.tv_paishe)
    TextView ivZhaoxiang;
    @BindView(R.id.tv_xiangce)
    TextView ivXiangce;
    @BindView(R.id.tv_quxiao)
    TextView ivGuanbi;
    private Context c;

    /**
     * 构造确认信息弹窗
     *
     * @param context 上下文
     */
    public PhotoDialog(Context context, int theme) {
        super(context, theme);
        view = View.inflate(context, R.layout.dialog_photo, null);
        this.setContentView(view);
        this.c = context;
        ivZhaoxiang = (TextView) findViewById(R.id.tv_paishe);
        ivXiangce = (TextView) findViewById(R.id.tv_xiangce);
        ivGuanbi = (TextView) findViewById(R.id.tv_quxiao);
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


    public TextView getIvZhaoxiang() {
        return ivZhaoxiang;
    }

    public void setIvZhaoxiang(TextView ivZhaoxiang) {
        this.ivZhaoxiang = ivZhaoxiang;
    }

    public TextView getIvXiangce() {
        return ivXiangce;
    }

    public void setIvXiangce(TextView ivXiangce) {
        this.ivXiangce = ivXiangce;
    }

    public TextView getIvGuanbi() {
        return ivGuanbi;
    }

    public void setIvGuanbi(TextView ivGuanbi) {
        this.ivGuanbi = ivGuanbi;
    }
}
