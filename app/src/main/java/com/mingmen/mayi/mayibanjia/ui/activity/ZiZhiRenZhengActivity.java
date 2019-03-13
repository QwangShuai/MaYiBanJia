package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.GetZiZhiBean;
import com.mingmen.mayi.mayibanjia.bean.WuLiuBean;
import com.mingmen.mayi.mayibanjia.bean.WuLiuObjBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZiZhiRenZhengActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_xinyongma)
    EditText etXinyongma;
    @BindView(R.id.iv_yingyezhizhao)
    ImageView ivYingyezhizhao;
    @BindView(R.id.iv_xukezheng)
    ImageView ivXukezheng;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_shenfenzheng)
    EditText etShenfenzheng;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.tv_fuzeren)
    TextView tvFuzeren;
    @BindView(R.id.rl_xukezheng)
    RelativeLayout rlXukezheng;
    private Context mContext;
    private String yemian = "";
    private String xinyongma = "";
    private String shenfenzheng = "";
    private String name = "";
    private String state;
    @Override
    public int getLayoutId() {
        return R.layout.activity_zi_zhi_ren_zheng;
    }

    @Override
    protected void initData() {
        tvTitle.setText("资质认证");
        mContext = ZiZhiRenZhengActivity.this;
        yemian = getIntent().getStringExtra("yemian");
        state = getIntent().getStringExtra("state");
        getZizhiShow();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back,R.id.btn_submit})
    public void onViewClicked(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_submit:
                if(isClick()){
                    saveZizhi();
                }
                break;
        }
    }

    private void getZizhiShow(){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getZizhiShow(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<GetZiZhiBean>() {
                    @Override
                    public void onNext(GetZiZhiBean bean) {
                        if(StringUtil.isValid(yemian)){
                            Glide.with(mContext).load(bean.getCirculation_permit()).into(ivXukezheng);
                        } else {
                            rlXukezheng.setVisibility(View.GONE);
                        }
                        Glide.with(mContext).load(bean.getBusiness_license()).into(ivYingyezhizhao);
                        tvFuzeren.setText(bean.getPrincipal());
                        etName.setText(bean.getLegal_person());
                        if(state.equals("审核中")||state.equals("审核通过")){
                            etName.setEnabled(false);
                            etXinyongma.setEnabled(false);
                            etShenfenzheng.setEnabled(false);
                            btnSubmit.setVisibility(View.GONE);
                        }
                        etXinyongma.setText(bean.getDuty_paragraph());
                        etShenfenzheng.setText(bean.getId_number());
                    }
                });
    }

    private void saveZizhi(){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .saveZizhi(PreferenceUtils.getString(MyApplication.mContext, "token", ""),shenfenzheng,name,xinyongma))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String bean) {
                        ToastUtil.showToast("申请认证成功,等待审核");
                        finish();
                    }
                });
    }

    private boolean isClick(){
        xinyongma = etXinyongma.getText().toString().trim();
        shenfenzheng = etShenfenzheng.getText().toString().trim();
        name = etName.getText().toString().trim();
        if(TextUtils.isEmpty(xinyongma)){
            ToastUtil.showToast("社会信用码不能为空");
            return false;
        } else if(!StringUtil.isLegalId(shenfenzheng)){
            ToastUtil.showToast("身份证格式不正确");
            return false;
        } else if(TextUtils.isEmpty(name)){
            ToastUtil.showToast("姓名不能为空");
            return false;
        } else {
            return true;
        }
    }
}
