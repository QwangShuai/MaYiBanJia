package com.mingmen.mayi.mayibanjia.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.view.XCFlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsZiZhangHuActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_dianpu)
    TextView tvDianpu;
    @BindView(R.id.tv_juese)
    TextView tvJuese;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_genggai_juese)
    TextView tvGenggaiJuese;
    @BindView(R.id.ll)
    RelativeLayout ll;
    @BindView(R.id.xcf_juese)
    XCFlowLayout xcfJuese;
    @BindView(R.id.tv_quanxian)
    TextView tvQuanxian;
    @BindView(R.id.xcf_quanxian)
    XCFlowLayout xcfQuanxian;
    @BindView(R.id.btn_quanxian)
    Button btnQuanxian;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @Override
    public int getLayoutId() {
        return R.layout.activity_details_zi_zhang_hu;
    }

    @Override
    protected void initData() {
        tvTitle.setText("子账户详情");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.ll_quanxian, R.id.btn_quanxian, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.ll_quanxian:
                break;
            case R.id.btn_quanxian:
                break;
            case R.id.btn_submit:
                break;
        }
    }
}
