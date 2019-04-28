package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/7/12/012.
 */

public class PeiSongLableDialog extends Dialog {
    public View view;
    @BindView(R.id.tv_lable)
    TextView tvLable;

    public TextView getTvLable() {
        return tvLable;
    }

    public void setTvLable(TextView tvLable) {
        this.tvLable = tvLable;
    }

    public RecyclerView getRvXiangqing() {
        return rvXiangqing;
    }

    public void setRvXiangqing(RecyclerView rvXiangqing) {
        this.rvXiangqing = rvXiangqing;
    }

    @BindView(R.id.rv_xiangqing)
    RecyclerView rvXiangqing;


    private Context c;

    /**
     * 构造确认信息弹窗
     *
     * @param context 上下文
     */
    public PeiSongLableDialog(Context context, int theme) {
        super(context, theme);
        view = View.inflate(context, R.layout.dialog_peisong, null);
        this.setContentView(view);
        this.c = context;
        tvLable = (TextView) findViewById(R.id.tv_lable);
        rvXiangqing = (RecyclerView) findViewById(R.id.rv_xiangqing);
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
        /**
         * 设置宽度全屏，要设置在show的后面
         //         */
//        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
//        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
//        getWindow().setAttributes(layoutParams);
    }

    public void setView(View view) {
        this.view = view;
        this.setContentView(view);
    }
}
