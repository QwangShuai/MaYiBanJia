package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.PhoneBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/10/19.
 */

public class PhoneDialog extends BaseFragmentDialog implements View.OnClickListener{
    @BindView(R.id.tv_dialog_phone_yewujingli)
    TextView tvYWJL;
    @BindView(R.id.tv_dialog_phone_yewuyuan)
    TextView tvYWY;
    private String phone_ywjl;
    private String phone_ywy;
    private String name_ywjl;
    private String name_ywy;
    public PhoneDialog(){

    }
    public PhoneDialog setData(String name_ywjl,String name_ywy,String phone_ywjl,String phone_ywy) {
        this.phone_ywjl = phone_ywjl;
        this.phone_ywy = phone_ywy;
        this.name_ywjl = name_ywjl;
        this.name_ywy = name_ywy;
        return this;
    }
    @OnClick({R.id.tv_dialog_phone_yewujingli,R.id.tv_dialog_phone_yewuyuan,R.id.tv_dialog_phone_cancle})
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data;
        switch (v.getId()){
            case R.id.tv_dialog_phone_yewujingli:
                intent = new Intent(Intent.ACTION_DIAL);
                data = Uri.parse("tel:" + phone_ywjl);
                intent.setData(data);
                startActivity(intent);
                break;
            case R.id.tv_dialog_phone_yewuyuan:
                data = Uri.parse("tel:" + phone_ywy);
                intent.setData(data);
                startActivity(intent);
                break;
            case R.id.tv_dialog_phone_cancle:
                dismiss();
                break;
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_phone;
    }

    @Override
    protected void init() {
        tvYWY.setText(name_ywy+":"+phone_ywy);
        tvYWJL.setText(name_ywjl+":"+phone_ywjl);
    }

    @Override
    protected void initConfiguration(Configuration configuration){
        configuration.fullWidth()
                .setGravity(Gravity.BOTTOM);
    }
}
