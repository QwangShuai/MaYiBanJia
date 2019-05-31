package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.JueSeBean;
import com.mingmen.mayi.mayibanjia.bean.RoleBean;
import com.mingmen.mayi.mayibanjia.bean.WuLiuBean;
import com.mingmen.mayi.mayibanjia.bean.WuLiuObjBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.WuLiuFenPeiAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.JueSeDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.XuanZeDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.view.XCFlowLayout;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddZiZhuangHuActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_dianpu)
    TextView tvDianpu;
    @BindView(R.id.tv_miaoshu)
    TextView tvMiaoshu;
    @BindView(R.id.et_juese)
    EditText etJuese;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pass)
    EditText etPass;
    @BindView(R.id.tv_juese)
    TextView tvJuese;
    @BindView(R.id.tv_quanxian)
    TextView tvQuanxian;
    @BindView(R.id.btn_quanxian)
    Button btnQuanxian;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.xcf_juese)
    XCFlowLayout xcfJuese;
    @BindView(R.id.xcf_quanxian)
    XCFlowLayout xcfQuanxian;



    private Context mContext;
    private String company_id="";
    private String company_name="";
    private String principal="";
    private String telephone="";
    private String son_role_id="";
    private String password="";
    @Override
    public int getLayoutId() {
        return R.layout.activity_add_zi_zhuang_hu;
    }

    @Override
    protected void initData() {
        tvTitle.setText("添加子账户");
        StringUtil.setInputNoEmoj(etJuese,8);
        mContext = AddZiZhuangHuActivity.this;
        company_name = getIntent().getStringExtra("name");
        company_id =  getIntent().getStringExtra("id");
        tvDianpu.setText(company_name);
        getmoren();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.btn_quanxian, R.id.btn_submit,R.id.ll_quanxian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                break;
            case R.id.btn_quanxian:

                break;
            case R.id.btn_submit:
                submitQuanxian();
                break;
            case R.id.ll_quanxian:
                JueSeDialog dialog = new JueSeDialog(mContext,"",son_role_id, new JueSeDialog.CallBack() {
                    @Override
                    public void confirm(String id, String name) {
                        son_role_id = id;
                        tvJuese.setText(name);
                        initShangpinChildViews(mContext,xcfJuese,name.split(","));
                        getQuanxian();
                        getmoren();
                    }
                });
                dialog.show();
                break;
        }
    }
    public void submitQuanxian(){
        principal = etJuese.getText().toString().trim();
        telephone = etPhone.getText().toString().trim();
        password = etPass.getText().toString().trim();
        if(!StringUtil.isValid(principal)){
            ToastUtil.showToast("角色名不能为空");
        } else if(!AppUtil.isMobile(telephone)){
            ToastUtil.showToast("手机号不正确");
        }
//        else if(!StringUtil.isValid(principal)){
//            ToastUtil.showToast("角色名不能为空");
//        }
        else {
            HttpManager.getInstance()
                    .with(mContext)
                    .setObservable(RetrofitManager.getService()
                            .addZizhanghu(PreferenceUtils.getString(MyApplication.mContext, "token", ""),company_id
                                    ,principal,telephone,son_role_id,password))
                    .setDataListener(new HttpDataListener<String>() {
                        @Override
                        public void onNext(String data) {
                            ToastUtil.showToast("添加成功");
                            finish();
                        }
                    });
        }
    }



    public void getQuanxian(){

        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getRoleTwoList(PreferenceUtils.getString(MyApplication.mContext, "token", ""),son_role_id))
                .setDataListener(new HttpDataListener<List<RoleBean>>() {
                    @Override
                    public void onNext(List<RoleBean> data) {
                        int mysize = data==null?0:data.size();
                        String[] quanxianList = new String[mysize];
                        if(mysize!=0){
                            for (int i=0;i<mysize;i++){
                                quanxianList[i]=data.get(i).getRoleName();
                            }
                            initShangpinChildViews(mContext,xcfQuanxian,quanxianList);
                        }
                    }
                },false);
    }

    private void getmoren() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getJuese(PreferenceUtils.getString(MyApplication.mContext, "token", ""),son_role_id))
                .setDataListener(new HttpDataListener<List<JueSeBean>>() {
                    @Override
                    public void onNext(List<JueSeBean> data) {
                        int mysize = data==null?0:data.size();
                        String miaoshu = "";
                        for (JueSeBean bean:data) {
                            miaoshu+=bean.getPart()+":"+bean.getSynopsis()+"\n";
                        }
                        tvMiaoshu.setText(miaoshu);
                    }
                },false);
    }
}
