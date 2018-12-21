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

import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean;
import com.mingmen.mayi.mayibanjia.bean.ShangpinidAndDianpuidBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.CaiGouListLevelOneAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.CaiGouDanTianJiaDailog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.TiJiaoXuQiuDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShenPiShiBaiActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.bt_tijiao)
    Button btTijiao;
    @BindView(R.id.tv_shibai)
    TextView tvShibai;

    private Context mContext;
    private String purchase_id;
    private CaiGouListLevelOneAdapter adapter;
    private List<CaiGouDanBean.FllistBean> mList = new ArrayList<>();
    private TiJiaoXuQiuDialog tijiaoxuqiuDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shen_pi_shi_bai;
    }

    @Override
    protected void initData() {
        tvTitle.setText("审批失败");
        tvRight.setText("添加商品");
        mContext = ShenPiShiBaiActivity.this;
        tijiaoxuqiuDialog = new TiJiaoXuQiuDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        mContext = ShenPiShiBaiActivity.this;
        purchase_id = getIntent().getStringExtra("id");
        adapter = new CaiGouListLevelOneAdapter(ShenPiShiBaiActivity.this,mList,mContext);
        rvList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvList.setAdapter(adapter);
        rvList.setFocusable(false);
        rvList.setNestedScrollingEnabled(false);
        adapter.notifyDataSetChanged();
        getlist();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.bt_tijiao,R.id.tv_shibai})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                myBack();
                break;
            case R.id.tv_right:
                CaiGouDanTianJiaDailog dailog = new CaiGouDanTianJiaDailog();
                dailog.setInitStr(purchase_id, "")
                        .setCallBack(new CaiGouDanTianJiaDailog.CallBack() {
                            @Override
                            public void confirm(CaiGouDanBean.FllistBean.SonorderlistBean msg) {
                                ToastUtil.showToast("添加成功");
                                selectPinlei(msg);
                                adapter.notifyDataSetChanged();
                            }
                        }).show(getSupportFragmentManager());

                break;
            case R.id.bt_tijiao:
                HttpManager.getInstance()
                        .with(mContext)
                        .setObservable(
                                RetrofitManager
                                        .getService()
                                        //user_token  是否是特殊商品不是0 是1    如果是特殊商品 填写要求   市场id  类别id  产品数量
                                        .postCaigoudan(purchase_id))
                        .setDataListener(new HttpDataListener<String>() {
                            @Override
                            public void onNext(String data) {
                                tijiaoxuqiuDialog.showDialog();
                                tijiaoxuqiuDialog.getTvCaigoudan().setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(mContext, CaiGouDanActivity.class);
                                        startActivity(intent);
                                        FCGDiQuXuanZeActivity.instance.finish();
                                        tijiaoxuqiuDialog.dismiss();
                                        finish();
                                    }
                                });
                                tijiaoxuqiuDialog.getTvShouye().setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(mContext, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        tijiaoxuqiuDialog.dismiss();
                                        finish();
                                    }
                                });
                            }

                        }, false);
                break;
        }
    }
    //采购单列表
    public  void getlist() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getcaigoudanlist(PreferenceUtils.getString(MyApplication.mContext, "token",""),"903",purchase_id))
                .setDataListener(new HttpDataListener<List<CaiGouDanBean>>() {
                    @Override
                    public void onNext(List<CaiGouDanBean> list) {
                        mList.clear();
                        mList.addAll(list.get(0).getFllist());
                        tvShibai.setText(list.get(0).getAudit_ps()+"");
                        adapter.notifyDataSetChanged();
                    }
                });
    }
    public void selectPinlei(CaiGouDanBean.FllistBean.SonorderlistBean bean){//选择品类
        for (int i=0;i<mList.size();i++){
            if(bean.getOne_classify_name().equals(mList.get(i).getClassify_name())){
                bean.setMarket_id(mList.get(i).getSonorderlist().get(mList.get(i).getSonorderlist().size()-1).getMarket_id());
                mList.get(i).getSonorderlist().add(bean);
                adapter.notifyDataSetChanged();
                break;
            } else if(i==mList.size()-1) {
                CaiGouDanBean.FllistBean myBean = new CaiGouDanBean.FllistBean();
                List<CaiGouDanBean.FllistBean.SonorderlistBean> myList = new ArrayList<>();
                myBean.setClassify_id(bean.getClassify_id());
                myBean.setClassify_name(bean.getOne_classify_name());
                myList.add(bean);
                myBean.setSonorderlist(myList);
                mList.add(myBean);
//                adapter.addLength();
                adapter.notifyDataSetChanged();
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        myBack();
    }

    public void myBack() {
        Intent intent = new Intent(ShenPiShiBaiActivity.this, CaiGouDanActivity.class);
        startActivity(intent);
        finish();
    }
}
