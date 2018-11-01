package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/7.
 */

public class ZhangHuXinXiActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_tuichu)
    TextView tvTuichu;
    @BindView(R.id.ll_touxiang)
    LinearLayout llTouxiang;
    @BindView(R.id.iv_anquan_jinru)
    ImageView ivAnquanJinru;
    @BindView(R.id.ll_zizhirenzheng)
    LinearLayout llZizhirenzheng;
    @BindView(R.id.ll_farenrenzheng)
    LinearLayout llFarenrenzheng;
    private Context mContext;
    private ConfirmDialog confirmDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_zhanghuxinxi;
    }

    @Override
    protected void initData() {
        mContext = ZhangHuXinXiActivity.this;
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
    }


    @OnClick({R.id.iv_back, R.id.tv_tuichu,R.id.ll_touxiang, R.id.iv_anquan_jinru, R.id.ll_zizhirenzheng, R.id.ll_farenrenzheng})
    public void onViewClicked(View view) {
        Intent it;
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_tuichu:
                confirmDialog.showDialog("是否确定退出当前账号");
                confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PreferenceUtils.putBoolean(MyApplication.mContext, "isLogin", false);
                        Intent intent = new Intent(mContext, LoginActivity.class);
                        startActivity(intent);
                        confirmDialog.dismiss();
                        MainActivity.instance.finish();
                        finish();
                    }
                });
                confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmDialog.dismiss();
                    }
                });
                break;
            case R.id.ll_touxiang:
                break;
            case R.id.iv_anquan_jinru:
                break;
            case R.id.ll_zizhirenzheng://资质认证
                it= new Intent(mContext,ZiZhiRenZhengActivity.class);
                it.putExtra("id","");
                startActivity(it);
                break;
            case R.id.ll_farenrenzheng:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
