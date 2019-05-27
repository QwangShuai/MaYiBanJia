package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;

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

    @Override
    public int getLayoutId() {
        return R.layout.activity_yunfei_shezhi;
    }

    @Override
    protected void initData() {
        mContext = YunfeiShezhiActivity.this;
        tvTitle.setText("运费设置");
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
                    llCdxe.setVisibility(View.VISIBLE);
                    llSsed.setVisibility(View.VISIBLE);
                    tvTishi.setVisibility(View.VISIBLE);
                    ivZjcd.setImageDrawable(mContext.getDrawable(R.mipmap.yxz_yfsz));
                    ivTrcd.setImageDrawable(mContext.getDrawable(R.mipmap.wxz_yfsz));
                }
                break;
            case R.id.ll_trcd:
                if(llCdxe.getVisibility()==View.VISIBLE?true:false){
                    llCdxe.setVisibility(View.GONE);
                    llSsed.setVisibility(View.GONE);
                    tvTishi.setVisibility(View.GONE);
                    ivZjcd.setImageDrawable(mContext.getDrawable(R.mipmap.wxz_yfsz));
                    ivTrcd.setImageDrawable(mContext.getDrawable(R.mipmap.yxz_yfsz));
                }
                break;
            case R.id.ll_chengdan:
                break;
            case R.id.ll_cdxe:
                break;
            case R.id.bt_save:
                break;
        }
    }
}
