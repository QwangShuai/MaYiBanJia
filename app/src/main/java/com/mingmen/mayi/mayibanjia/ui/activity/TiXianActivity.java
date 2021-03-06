package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.icu.math.BigDecimal;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzj.pass.dialog.PayPassDialog;
import com.lzj.pass.dialog.PayPassView;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.WuLiuBean;
import com.mingmen.mayi.mayibanjia.bean.WuLiuObjBean;
import com.mingmen.mayi.mayibanjia.bean.YinHangKaBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.WuLiuFenPeiAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.view.CircleImageView;
import com.mingmen.mayi.mayibanjia.utils.GlideUtils;
import com.mingmen.mayi.mayibanjia.utils.MyMath;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.List;

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
    @BindView(R.id.rl_add_yinhangka)
    RelativeLayout rlAddYinhangka;

    private Context mContext;
    private String yue = "0.00";
    private final static int REQUESTCODE = 1; // 返回的结果码
    private boolean isClick = false;
    private String cardID = "";
    private ConfirmDialog confirmDialog;
    private PayPassDialog payDialog;
    @Override
    public int getLayoutId() {
        return R.layout.activity_ti_xian;
    }

    @Override
    protected void initData() {
        mContext = TiXianActivity.this;
        tvTitle.setText("提现");
        yue = (int)Math.floor(MyMath.getDouble(Double.valueOf(getIntent().getStringExtra("yue"))))+"";
        tvJine.setText("可提取金额："+yue+"元，");
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
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
                    if(Integer.valueOf(s.toString().trim())>Integer.valueOf(yue)){
                        etJine.setText(yue);
                    }
                    isClick = true;
                    btnTixian.setBackground(getResources().getDrawable(R.drawable.fillet_solid_zangqing_3));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        getBankCardList();
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
                if(isClick){
                    if(TextUtils.isEmpty(cardID)){
                        ToastUtil.showToast("请选择银行卡");
                    } else if(TextUtils.isEmpty(etJine.getText().toString())){
                        ToastUtil.showToast("请输入正确的提现金额");
                    } else if(Double.valueOf(yue)< Double.valueOf(etJine.getText().toString())){
                        ToastUtil.showToast("最大金额不可以超过余额");
                    } else {
                        double  yueDou=Double.parseDouble(etJine.getText().toString());
                        double  fuWuFei=0.006;
                        double mony=MyMath.mul(yueDou,fuWuFei);
                        confirmDialog.showDialog("确认提现将收取"+mony+"元的手续费，是否提现");
                        confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                confirmDialog.dismiss();
                                selectPayPwd();
                            }
                        });
                        confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                confirmDialog.dismiss();
                            }
                        });

                    }
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
            if(StringUtil.isValid(String.valueOf(data.getSerializableExtra("bean")))){
                YinHangKaBean bean = (YinHangKaBean) data.getSerializableExtra("bean");
                GlideUtils.cachePhoto(mContext,ivTubiao,bean.getLog_url());
                GlideUtils.cachePhoto(mContext,ivBg,bean.getReverse_url());
                tvBankCardName.setText(bean.getBank_name());
                tvBankCardNumber.setText(bean.getBank_account());
                tvBankCardType.setText(bean.getBank_branch());
                cardID = bean.getBank_id();
                rlYinhangka.setVisibility(View.VISIBLE);
                rlAddYinhangka.setVisibility(View.GONE);
            } else {
                rlYinhangka.setVisibility(View.GONE);
                rlAddYinhangka.setVisibility(View.VISIBLE);
            }
        }
    }

    private void tixian(String s){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .tixian(PreferenceUtils.getString(MyApplication.mContext, "token", ""),etJine.getText().toString(),
                                cardID,s))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        payDialog.dismiss();
                        ToastUtil.showToast("申请提现成功,请等待耐心等待3-7个工作日");
                        finish();
                    }
                },true);
    }

    public void getBankCardList(){//获取我的银行卡
        HttpManager.getInstance().with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getMyBankCardList(PreferenceUtils.getString(MyApplication.mContext, "token","")))
                .setDataListener(new HttpDataListener<List<YinHangKaBean>>() {
                    @Override
                    public void onNext(List<YinHangKaBean> data) {
                        int mysize = data==null?0:data.size();
                        if(mysize!=0){
                            YinHangKaBean bean = data.get(0);
                            GlideUtils.cachePhoto(mContext,ivTubiao,bean.getLog_url());
                            GlideUtils.cachePhoto(mContext,ivBg,bean.getReverse_url());
                            tvBankCardName.setText(bean.getBank_name());
                            tvBankCardNumber.setText(bean.getBank_account());
                            tvBankCardType.setText(bean.getBank_branch());
                            cardID = bean.getBank_id();
                            rlYinhangka.setVisibility(View.VISIBLE);
                            rlAddYinhangka.setVisibility(View.GONE);
                        } else {
                            rlYinhangka.setVisibility(View.GONE);
                            rlAddYinhangka.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    private void selectPayPwd() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .selectPayPwd(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<String>() {

                    @Override
                    public void onNext(String data) {
                        if (data.equals("0")) {
                            payDialog = new PayPassDialog(mContext);
                            payDialog.getPayViewPass()
                                    .setPayClickListener(new PayPassView.OnPayClickListener() {
                                        @Override
                                        public void onPassFinish(String s) {
                                            tixian(s);
                                        }

                                        @Override
                                        public void onPayClose() {
                                            payDialog.dismiss();
                                        }

                                        @Override
                                        public void onPayForget() {
                                            startActivity(new Intent(mContext,ChangePayPwdActivity.class));
                                        }
                                    });
                        } else {
                            confirmDialog.showDialog("您还没有设置支付密码，是否前去设置？");
                            confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    confirmDialog.dismiss();
                                    startActivity(new Intent(mContext,SetPayPwdActivity.class));
                                }
                            });
                            confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    confirmDialog.dismiss();
                                }
                            });
                        }
                    }
                });
    }
}
