package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.icu.math.BigDecimal;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.WuLiuBean;
import com.mingmen.mayi.mayibanjia.bean.WuLiuObjBean;
import com.mingmen.mayi.mayibanjia.bean.YinHangKaBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.WuLiuFenPeiAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.view.CircleImageView;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TiXianActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_xuanze)
    TextView tvXuanze;
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.iv_tubiao)
    CircleImageView ivTubiao;
    @BindView(R.id.tv_bank_card_name)
    TextView tvBankCardName;
    @BindView(R.id.tv_bank_card_type)
    TextView tvBankCardType;
    @BindView(R.id.tv_bank_card_number)
    TextView tvBankCardNumber;
    @BindView(R.id.et_jine)
    EditText etJine;
    @BindView(R.id.tv_jine)
    TextView tvJine;
    @BindView(R.id.tv_tixian)
    TextView tvTixian;
    @BindView(R.id.btn_tixian)
    Button btnTixian;
    @BindView(R.id.rl_yinhangka)
    RelativeLayout rlYinhangka;

    private Context mContext;
    private String yue = "0.00";
    private final static int REQUESTCODE = 1; // 返回的结果码
    private boolean isClick = false;
    private String cardID = "";
    @Override
    public int getLayoutId() {
        return R.layout.activity_ti_xian;
    }

    @Override
    protected void initData() {
        mContext = TiXianActivity.this;
        tvTitle.setText("提现");
        yue = getIntent().getStringExtra("yue");
        tvJine.setText("可提取金额："+yue+"元，");
        etJine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().equals("")){
                    isClick = false;
                    btnTixian.setBackground(getResources().getDrawable(R.drawable.bg_click_false));
                } else {
                    isClick = true;
                    btnTixian.setBackground(getResources().getDrawable(R.drawable.fillet_solid_zangqing_3));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_xuanze, R.id.tv_tixian, R.id.btn_tixian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_xuanze:
                Intent it = new Intent(mContext,YinHangKaActivity.class);
                it.putExtra("tixian",1);
                startActivityForResult(it,REQUESTCODE);
                break;
            case R.id.tv_tixian:
                etJine.setText(yue);
                break;
            case R.id.btn_tixian:
                if(TextUtils.isEmpty(cardID)){
                    ToastUtil.showToast("请选择银行卡");
                } else if(TextUtils.isEmpty(etJine.getText().toString())){
                    ToastUtil.showToast("请输入正确的提现金额");
                } else if(Double.valueOf(yue)< Double.valueOf(etJine.getText().toString())){
                    ToastUtil.showToast("最大金额不可以超过余额");
                } else {
                    tixian();
                }
                break;
        }
    }

    private void initView(){

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUESTCODE){
            YinHangKaBean bean = (YinHangKaBean) data.getSerializableExtra("bean");
            Glide.with(mContext).load(bean.getLog_url()).into(ivTubiao);
            Glide.with(mContext).load(bean.getReverse_url()).into(ivBg);
            tvBankCardName.setText(bean.getBank_name());
            tvBankCardNumber.setText(bean.getBank_account());
            tvBankCardType.setText(bean.getBank_branch());
            cardID = bean.getBank_id();
            rlYinhangka.setVisibility(View.VISIBLE);
        }
    }

    private void tixian(){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .tixian(PreferenceUtils.getString(MyApplication.mContext, "token", ""),etJine.getText().toString(),
                                cardID))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        ToastUtil.showToast("申请提现成功,请等待耐心等待3-7个工作日");
                        finish();
                    }
                },true);
    }
}
