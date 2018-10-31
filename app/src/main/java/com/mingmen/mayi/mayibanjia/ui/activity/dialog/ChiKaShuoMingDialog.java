package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/10/19.
 */

public class ChiKaShuoMingDialog extends BaseFragmentDialog implements View.OnClickListener {

    @BindView(R.id.tv_cksm)
    TextView tvCksm;
    @BindView(R.id.tv_dissmiss)
    TextView tvDissmiss;

    public ChiKaShuoMingDialog() {

    }

    @OnClick({R.id.tv_dissmiss})
    public void onClick(View v) {
        dismiss();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_chikashuoming;
    }

    @Override
    protected void init() {//文字加粗
        tvCksm.getPaint().setFlags(Paint.FAKE_BOLD_TEXT_FLAG);
    }

//    @Override
//    protected void initConfiguration(Configuration configuration) {
//        configuration.fullWidth()
//                .setGravity(Gravity.CENTER);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
