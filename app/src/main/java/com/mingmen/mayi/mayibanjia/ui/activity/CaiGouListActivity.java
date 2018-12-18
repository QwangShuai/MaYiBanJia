package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.CaiGouListLevelOneAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.CaiGouListLevelTwoAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.TiJiaoXuQiuDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CaiGouListActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.bt_tianjia)
    Button btTianjia;
    @BindView(R.id.bt_tijiao)
    Button btTijiao;

    private Context mContext;
    private String purchase_id;
    private CaiGouListLevelOneAdapter adapter;
    private List<CaiGouDanBean.FllistBean> mList = new ArrayList<>();
    private TiJiaoXuQiuDialog tijiaoxuqiuDialog;


    @Override
    public int getLayoutId() {
        return R.layout.activity_cai_gou_list;
    }

    @Override
    protected void initData() {
        tvTitle.setText("采购列表");
        mContext = CaiGouListActivity.this;
        purchase_id = getIntent().getStringExtra("id");
        tijiaoxuqiuDialog = new TiJiaoXuQiuDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        adapter = new CaiGouListLevelOneAdapter(CaiGouListActivity.this,mList,mContext);
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

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.bt_tianjia, R.id.bt_tijiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                break;
            case R.id.bt_tianjia:
                finish();
                break;
            case R.id.bt_tijiao:
//                if (mList.size() == 0) {
//                    ToastUtil.showToast("请先添加至少一个商品再提交");
//                } else {
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
                                    ToastUtil.showToast("data");
                                }

                            }, false);
                    //dialog
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
//                }
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
                                .getcaigoudanlist(PreferenceUtils.getString(MyApplication.mContext, "token",""),"904",purchase_id))
                .setDataListener(new HttpDataListener<List<CaiGouDanBean>>() {
                    @Override
                    public void onNext(List<CaiGouDanBean> list) {
                        mList.clear();
                        mList.addAll(list.get(0).getFllist());
                        adapter.notifyDataSetChanged();
                    }
                });
    }

}
