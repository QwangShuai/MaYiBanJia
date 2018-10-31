package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.ShangPinBean;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.QiangDanShangPinAdapter;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/8/17.
 */

public class SouSuoDialog extends BaseFragmentDialog {

    @BindView(R.id.et_sousuo)
    EditText etSousuo;
    @BindView(R.id.bt_quxiao)
    Button btQuxiao;
    @BindView(R.id.bt_sousuo)
    Button btSousuo;
    Unbinder unbinder;
    private CallBack mCallBack;
    private String sousuo;

    public SouSuoDialog() {

    }

    public SouSuoDialog setData(String sousuo) {
        this.sousuo = sousuo;
        return this;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_sousuokuang;
    }

    @Override
    protected void init() {

        btSousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallBack != null)
                    mCallBack.sousuozi(etSousuo.getText().toString().trim());
                dismiss();
            }
        });
        btQuxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public SouSuoDialog setCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
        return this;
    }

    @Override
    protected void initConfiguration(Configuration configuration) {
        configuration
                .setWidth(AppUtil.dip2px(300))
                .setHeight(AppUtil.dip2px(150))
                .setGravity(Gravity.CENTER);
    }

    public interface CallBack {
        void sousuozi(String msg);
    }
}
