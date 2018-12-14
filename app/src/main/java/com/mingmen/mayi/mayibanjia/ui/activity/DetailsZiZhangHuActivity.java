package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.RoleBean;
import com.mingmen.mayi.mayibanjia.bean.ZiZhangHuDetailsBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.JueSeDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.view.XCFlowLayout;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.List;

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
    LinearLayout ll;
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

    private String id = "";
    private Context mContext;
    private String son_role_id="";
    private String son_role_id_old="";
    private ConfirmDialog confirmDialog;
    @Override
    public int getLayoutId() {
        return R.layout.activity_details_zi_zhang_hu;
    }

    @Override
    protected void initData() {
        tvTitle.setText("子账户详情");
        mContext = DetailsZiZhangHuActivity.this;
        id = getIntent().getStringExtra("id");
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        getShow();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.ll_quanxian, R.id.btn_quanxian, R.id.btn_submit,R.id.ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBack();
                break;
            case R.id.ll_quanxian:
                break;
            case R.id.btn_quanxian:
                break;
            case R.id.btn_submit:
                if(son_role_id_old.equals(son_role_id)){
                    ToastUtil.showToast("没改点什么确定");
                } else {
                    changeJuese();
                }
                break;
            case R.id.ll:
                JueSeDialog dialog = new JueSeDialog(mContext,id,son_role_id, new JueSeDialog.CallBack() {
                    @Override
                    public void confirm(String id, String name) {
                        son_role_id = id;
//                        tvJuese.setText(name);
                        initShangpinChildViews(mContext,xcfJuese,name.split(","));
                        getQuanxian();
                    }
                });
                dialog.show();
                break;
        }
    }

    public void getShow(){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getJueseDetails(PreferenceUtils.getString(MyApplication.mContext, "token", ""),id))
                .setDataListener(new HttpDataListener<ZiZhangHuDetailsBean>() {
                    @Override
                    public void onNext(ZiZhangHuDetailsBean data) {
                        String[] quanxianList = new String[data.getRoleList()==null?0:data.getRoleList().size()];
                            for (int i=0;i<quanxianList.length;i++){
                                quanxianList[i]=data.getRoleList().get(i).getRoleName();
                            }
                            initShangpinChildViews(mContext,xcfQuanxian,quanxianList);

                        String[] jueseList = new String[data.getAppRoleList()==null?0:data.getAppRoleList().size()];
                        for (int i=0;i<jueseList.length;i++){
                            jueseList[i]=data.getAppRoleList().get(i).getPart();
                            son_role_id += data.getAppRoleList().get(i).getSon_role_id()+",";
                        }
                        son_role_id_old = son_role_id;
                        initShangpinChildViews(mContext,xcfJuese,jueseList);
                        tvDianpu.setText(data.getCompany_name()+"");
                        tvJuese.setText(data.getPrincipal()+"");
                        tvPhone.setText(data.getTelephone()+"");
                    }
                },false);
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

    public void changeJuese(){

        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .changeJuese(PreferenceUtils.getString(MyApplication.mContext, "token", ""),id,son_role_id))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        ToastUtil.showToast("修改成功");
                        finish();
                    }
                },true);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        onBack();
    }

    private void onBack(){
        if(son_role_id_old.equals(son_role_id)){
            finish();
        } else {
            confirmDialog.showDialog("角色已更改，是否确认退出");
            confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
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
}
