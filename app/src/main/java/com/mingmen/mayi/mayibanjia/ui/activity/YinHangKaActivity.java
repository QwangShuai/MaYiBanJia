package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.YinHangKaBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.YinHangKaAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.JumpUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YinHangKaActivity extends BaseActivity {

    @BindView(R.id.rv_yin_hang_ka)
    RecyclerView rvYinHangKa;
    @BindView(R.id.btn_add_card)
    Button btnAddCard;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    private List<YinHangKaBean> mList = new ArrayList<>();
    private YinHangKaAdapter adapter;
    private Context mContext;
    private int tixian = 0;
    private ConfirmDialog confirmDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_yin_hang_ka;
    }

    @Override
    protected void initData() {
        mContext = YinHangKaActivity.this;
        tvRight.setText("解除绑定");
        tvRight.setVisibility(View.GONE);
        tvTitle.setText("我的银行卡");
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        tixian = getIntent().getIntExtra("tixian", 0);
        adapter = new YinHangKaAdapter(mContext, mList, new YinHangKaAdapter.CallBack() {
            @Override
            public void xuanzhong(YinHangKaBean bean) {
                if (tixian != 0) {
                    Intent it = new Intent();
                    it.putExtra("bean", bean);
                    setResult(1, it);
                    finish();
                }
            }
        });
        rvYinHangKa.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvYinHangKa.setAdapter(adapter);
        getBankCardList();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.btn_add_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
//                finish();
                myBack();
                break;

            case R.id.btn_add_card:
                getZiZhi();
                break;
            case R.id.tv_right:
                confirmDialog.showDialog("确认解除当前银行卡绑定");
                confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HttpManager.getInstance().with(mContext)
                                .setObservable(RetrofitManager.getService()
                                        .delBankCard(PreferenceUtils.getString(MyApplication.mContext, "token", ""), mList.get(0).getBank_id()))
                                .setDataListener(new HttpDataListener<String>() {
                                    @Override
                                    public void onNext(String data) {
                                        ToastUtil.showToast("解除绑定成功");
                                        getBankCardList();
                                        confirmDialog.dismiss();
                                    }
                                });
                    }
                });
                confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmDialog.dismiss();
                    }
                });
                break;
        }
    }

    public void getBankCardList() {//获取我的银行卡
        HttpManager.getInstance().with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getMyBankCardList(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<List<YinHangKaBean>>() {
                    @Override
                    public void onNext(List<YinHangKaBean> data) {
                        mList.clear();
                        adapter.notifyDataSetChanged();
                        int mysize = data == null ? 0 : data.size();
                        if (mysize != 0) {
                            tvRight.setVisibility(View.VISIBLE);
                            mList.addAll(data);
                            btnAddCard.setVisibility(View.GONE);
                        } else {
                            tvRight.setVisibility(View.GONE);
                            btnAddCard.setVisibility(View.VISIBLE);
                        }

                        adapter.notifyDataSetChanged();
                    }
                });
    }

    public void getZiZhi() {//资质认证
        HttpManager.getInstance().with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getZiZhi(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(final String data) {
                        if(data.equals("审核中")||data.equals("待审核")||data.equals("审核未通过")){
                            confirmDialog.showDialog("您的资质认证状态暂未通过，是否前去查看");
                            confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    confirmDialog.dismiss();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("state", data);
                                    if(PreferenceUtils.getString(MyApplication.mContext,"juese","").equals("1")){
                                        JumpUtil.Jump_intent(mContext, ZzrzCtdActivity.class, bundle);
                                    } else {
                                        JumpUtil.Jump_intent(mContext, ZzrzGydActivity.class, bundle);
                                    }

                                }
                            });
                            confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    confirmDialog.dismiss();
                                }
                            });
                        } else {
                            Bundle bundle = new Bundle();
                            bundle.putString("principal", data);
                            JumpUtil.Jump_intent(mContext, YinHangKaTianJiaActivity.class, bundle);
                        }
                    }
                });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getBankCardList();
    }

    private void myBack() {
        Intent it = new Intent();
        it.putExtra("bean", "");
        setResult(1, it);
        finish();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        myBack();
    }
}
