package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.BanbenUpdateBean;
import com.mingmen.mayi.mayibanjia.http.URL;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.LianggeXuanXiangDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.update.CProgressDialogUtils;
import com.xuexiang.xupdate.XUpdate;
import com.xuexiang.xupdate.proxy.impl.DefaultUpdateChecker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YunfeiShezhiActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_zjcd)
    ImageView ivZjcd;
    @BindView(R.id.ll_zjcd)
    LinearLayout llZjcd;
    @BindView(R.id.iv_trcd)
    ImageView ivTrcd;
    @BindView(R.id.ll_trcd)
    LinearLayout llTrcd;
    @BindView(R.id.tv_chengdan)
    TextView tvChengdan;
    @BindView(R.id.ll_chengdan)
    LinearLayout llChengdan;
    @BindView(R.id.ll_cdxe)
    LinearLayout llCdxe;
    @BindView(R.id.et_edu)
    EditText etEdu;
    @BindView(R.id.ll_ssed)
    LinearLayout llSsed;
    @BindView(R.id.tv_tishi)
    TextView tvTishi;
    @BindView(R.id.bt_save)
    Button btSave;

    private Context mContext;
    private LianggeXuanXiangDialog dialog;
    private String zfje = "";
    private boolean xuanzhong = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_yunfei_shezhi;
    }

    @Override
    protected void initData() {
        mContext = YunfeiShezhiActivity.this;
        tvTitle.setText("运费设置");

        etEdu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().equals("0")){
                    ToastUtil.showToastLong("最小为1元");
                    etEdu.setText("1");
                } else {
                    zfje = s.toString().trim();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.ll_zjcd, R.id.ll_trcd, R.id.ll_chengdan, R.id.ll_cdxe, R.id.bt_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_zjcd:
                if(llCdxe.getVisibility()==View.GONE?true:false){
                    xuanzhong = true;
                    llCdxe.setVisibility(View.VISIBLE);
                    llSsed.setVisibility(View.VISIBLE);
                    tvTishi.setVisibility(View.VISIBLE);
                    ivZjcd.setImageDrawable(mContext.getDrawable(R.mipmap.yxz_yfsz));
                    ivTrcd.setImageDrawable(mContext.getDrawable(R.mipmap.wxz_yfsz));
                }
                break;
            case R.id.ll_trcd:
                if(llCdxe.getVisibility()==View.VISIBLE?true:false){
                    xuanzhong = false;
                    zfje = "";
                    llCdxe.setVisibility(View.GONE);
                    llSsed.setVisibility(View.GONE);
                    tvTishi.setVisibility(View.GONE);
                    ivZjcd.setImageDrawable(mContext.getDrawable(R.mipmap.wxz_yfsz));
                    ivTrcd.setImageDrawable(mContext.getDrawable(R.mipmap.yxz_yfsz));
                }
                break;
            case R.id.ll_chengdan:
                dialog = new LianggeXuanXiangDialog(mContext,
                        mContext.getResources().getIdentifier("BottomDialog", "style", mContext.getPackageName()));
                dialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.LEFT | Gravity.RIGHT);
                dialog.showDialog("限额承担", "全额承担");
                dialog.getTvXuanxiang1().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        tvChengdan.setText("限额承担");
                        llSsed.setVisibility(View.VISIBLE);
                        tvTishi.setVisibility(View.VISIBLE);
                    }
                });
                dialog.getTvXuanxiang2().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        tvChengdan.setText("全额承担");
                        llSsed.setVisibility(View.GONE);
                        tvTishi.setVisibility(View.GONE);
                        zfje = "0";
                    }
                });
                break;
            case R.id.ll_cdxe:
                break;
            case R.id.bt_save:
                if(xuanzhong){
                    if(tvChengdan.getText().toString().equals("全额承担")){
                        save();
                    } else {
                        if(StringUtil.isValid(zfje)&&!zfje.equals("0")){
                            save();
                        } else {
                            ToastUtil.showToastLong("请填写限额承担的商品金额");
                        }
                    }
                } else {
                    save();
                }

                break;
        }
    }

    private void save() {
//        GlideUtils.cachePhoto(WelcomeActivity.this,ivWelcome,"http://ceshi.canchengxiang.com/images/welcome.png");
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .setYunfei(PreferenceUtils.getString(MyApplication.mContext,"token",""),zfje))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        ToastUtil.showToastLong("设置成功");
                        finish();
                    }
                });


    }
    private void show() {
//        GlideUtils.cachePhoto(WelcomeActivity.this,ivWelcome,"http://ceshi.canchengxiang.com/images/welcome.png");
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getYunfei(PreferenceUtils.getString(MyApplication.mContext,"token","")))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        if(StringUtil.isEmpty(data)){
                            xuanzhong = false;
                            zfje = "";
                            llCdxe.setVisibility(View.GONE);
                            llSsed.setVisibility(View.GONE);
                            tvTishi.setVisibility(View.GONE);
                            ivZjcd.setImageDrawable(mContext.getDrawable(R.mipmap.wxz_yfsz));
                            ivTrcd.setImageDrawable(mContext.getDrawable(R.mipmap.yxz_yfsz));
                        } else if(Double.valueOf(data)==0){
                            xuanzhong = true;
                            tvChengdan.setText("全额承担");
                            llCdxe.setVisibility(View.VISIBLE);
                            llSsed.setVisibility(View.GONE);
                            tvTishi.setVisibility(View.GONE);
                            zfje = "0";
                            ivZjcd.setImageDrawable(mContext.getDrawable(R.mipmap.yxz_yfsz));
                            ivTrcd.setImageDrawable(mContext.getDrawable(R.mipmap.wxz_yfsz));
                        } else {
                            xuanzhong = true;
                            tvChengdan.setText("限额承担");
                            llCdxe.setVisibility(View.VISIBLE);
                            llSsed.setVisibility(View.VISIBLE);
                            tvTishi.setVisibility(View.VISIBLE);
                            etEdu.setText(data);
                            ivZjcd.setImageDrawable(mContext.getDrawable(R.mipmap.yxz_yfsz));
                            ivTrcd.setImageDrawable(mContext.getDrawable(R.mipmap.wxz_yfsz));
                        }

                    }
                });


    }
}
