package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/7/10/010.
 */


public class LianggeXuanXiangDialog extends Dialog {
    public View view;
    @BindView(R.id.tv_xuanxiang1)
    TextView tvXuanxiang1;
    @BindView(R.id.tv_xuanxiang2)
    TextView tvXuanxiang2;
    private Context c;

    /**
     * 构造确认信息弹窗
     *
     * @param context 上下文
     */
    public LianggeXuanXiangDialog(Context context, int theme) {
        super(context, theme);
        view = View.inflate(context, R.layout.dialog_lianggexuanxiang, null);
        this.setContentView(view);
        this.c = context;
        tvXuanxiang1=(TextView)view.findViewById(R.id.tv_xuanxiang1);
        tvXuanxiang2=(TextView)view.findViewById(R.id.tv_xuanxiang2);
    }

    /**
     * 显示确认信息弹窗
     */
    public void showDialog(String xuanxiang1,String xuanxiang2) {
        Log.e("xuanxiang1",xuanxiang1);
        Log.e("xuanxiang2",xuanxiang2);
        if (!"".equals(xuanxiang1)){
            tvXuanxiang1.setText(xuanxiang1);
        }
        if (!"".equals(xuanxiang2)) {
            tvXuanxiang2.setText(xuanxiang2);
        }
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
////         //         */
//        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
//        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
//        getWindow().setAttributes(layoutParams);
    }

    public void setView(View view) {
        this.view = view;
        this.setContentView(view);
    }

    public TextView getTvXuanxiang1() {
        return tvXuanxiang1;
    }

    public void setTvXuanxiang1(TextView tvXuanxiang1) {
        this.tvXuanxiang1 = tvXuanxiang1;
    }

    public TextView getTvXuanxiang2() {
        return tvXuanxiang2;
    }

    public void setTvXuanxiang2(TextView tvXuanxiang2) {
        this.tvXuanxiang2 = tvXuanxiang2;
    }
}
