package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.ZhangHuRenZhengBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.view.CircleImageView;
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
//    @BindView(R.id.ll_farenrenzheng)
//    LinearLayout llFarenrenzheng;
    @BindView(R.id.iv_touxiang)
    CircleImageView ivTouxiang;
    @BindView(R.id.tv_zz_shenhe)
    TextView tvZzShenhe;
//    @BindView(R.id.tv_fr_shenhe)
//    TextView tvFrShenhe;
    @BindView(R.id.tv_dianming)
    TextView tvDianming;
    private Context mContext;
    private ConfirmDialog confirmDialog;
    private String sh_state="待审核";

    @Override
    public int getLayoutId() {
        return R.layout.activity_zhanghuxinxi;
    }

    @Override
    protected void initData() {
        tvTitle.setText("账户信息");
        mContext = ZhangHuXinXiActivity.this;
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        getRenzheng();
    }


    @OnClick({R.id.iv_back, R.id.tv_tuichu, R.id.ll_touxiang, R.id.iv_anquan_jinru, R.id.ll_zizhirenzheng})
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
                it = new Intent(mContext, ZiZhiRenZhengActivity.class);
                it.putExtra("id", "");
                it.putExtra("state",sh_state);
                it.putExtra("yemian","0");
                startActivity(it);
                break;
//            case R.id.ll_farenrenzheng:
//                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    private void getRenzheng() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getGerenrenzheng(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<ZhangHuRenZhengBean>() {
                    @Override
                    public void onNext(ZhangHuRenZhengBean bean) {
                        Glide.with(mContext).load(bean.getFile_path()).into(ivTouxiang);
                        tvDianming.setText(bean.getCompany_name());
                        tvZzShenhe.setText(bean.getZz().toString());
                        sh_state = bean.getZz().toString();
//                        tvFrShenhe.setText(bean.getFr().toString());
                    }
                });
    }
}
