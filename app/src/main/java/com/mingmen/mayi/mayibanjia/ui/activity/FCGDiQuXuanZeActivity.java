package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.ProvinceBean;
import com.mingmen.mayi.mayibanjia.bean.ShiChangBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.FCGShiChangAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.view.AutoLineFeedLayoutManager;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/20/020.
 */

public class FCGDiQuXuanZeActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_caigouming)
    EditText et_caigouming;
    @BindView(R.id.bt_queding)
    Button btQueding;
    @BindView(R.id.rv_yijishichang)
    RecyclerView rvShichang1;
    @BindView(R.id.rv_erjishichang)
    RecyclerView rvShichang2;
    @BindView(R.id.rv_sanjishichang)
    RecyclerView rvShichang3;
    @BindView(R.id.iv_yiji)
    ImageView ivYiji;
    @BindView(R.id.iv_erji)
    ImageView ivErji;
    @BindView(R.id.iv_sanji)
    ImageView ivSanji;
    private Context mContext;
    private String shengid;
    private String shiid;
    private String quid;
    private String shengming;
    private String shiming;
    private String quming;
    private ArrayList<ProvinceBean> zonglist;
    private String shichangid;
    private String shichangming;
    private ArrayList<ProvinceBean> shenglist;
    private ArrayList shilist;
    private ArrayList qulist;
    private boolean yiji = false;
    private boolean erji = false;
    private boolean sanji = false;
    AutoLineFeedLayoutManager layout;
    public static FCGDiQuXuanZeActivity instance = null;
    private FCGShiChangAdapter shiChangAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_facaigou_diquxuanze;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
    }

    @Override
    protected void initData() {
        mContext=FCGDiQuXuanZeActivity.this;
        tvTitle.setText("采购需求");
        initList();
    }


    //获取此账户填写的省市区  并显示出来
    private void getmoren(final String level) {
    HttpManager.getInstance()
            .with(mContext)
            .setObservable(
                    RetrofitManager
                            .getService()
                            .getmorendiqu(PreferenceUtils.getString(MyApplication.mContext, "token",""),level))
            .setDataListener(new HttpDataListener<List<ShiChangBean>>() {
                @Override
                public void onNext(List<ShiChangBean> data) {
                    setShowView(level);
                    shiChangAdapter.setNewData(data);
                    shiChangAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            ShiChangBean item = (ShiChangBean) adapter.getItem(position);
                            shichangid = item.getMark_id();
                            shichangming = item.getMarket_name();
                            shiChangAdapter.setXuanzhong(shichangid);
                        }
                    });
                    shiChangAdapter.notifyDataSetChanged();
                }
            });
}




    @OnClick({R.id.iv_back,   R.id.bt_queding,R.id.ll_yijishichang,R.id.ll_erjishichang,R.id.ll_sanjishichang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.bt_queding:
                if (shichangid==null||"".equals(shichangid)){
                    ToastUtil.showToast("请选择市场后进入下一步");
                }else{
                    if (et_caigouming.getText().toString().trim().length()==0){
                        ToastUtil.showToast("请填写采购单名称");
                        return;
                    }
                    Intent intent=new Intent(mContext,FCGCaiGouXuQiuActivity.class);
                    bundle.putString("shichang",shichangid);
                    bundle.putString("caigouming",et_caigouming.getText().toString().trim());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
            case R.id.ll_yijishichang:
                if(yiji){
                    yiji = false;
                    rvShichang1.setVisibility(View.GONE);
                    ivYiji.setImageResource(R.mipmap.jinru);
                } else {
                    getmoren("1");
                }
                break;
            case R.id.ll_erjishichang:
                if(erji){
                    erji = false;
                    rvShichang2.setVisibility(View.GONE);
                    ivErji.setImageResource(R.mipmap.jinru);
                } else {
                    getmoren("2");
                }
                break;
            case R.id.ll_sanjishichang:
                if(sanji){
                    sanji = false;
                    rvShichang3.setVisibility(View.GONE);
                    ivSanji.setImageResource(R.mipmap.jinru);
                } else {
                    getmoren("3");
                }
                break;
        }
    }


    public void guan(){
        finish();
    }

    public void initList(){
        shiChangAdapter = new FCGShiChangAdapter();
        getmoren("1");
    }

    public void setShowView(String type){
        rvShichang1.setVisibility(View.GONE);
        rvShichang2.setVisibility(View.GONE);
        rvShichang3.setVisibility(View.GONE);
        yiji = false;
        erji = false;
        sanji = false;
        ivYiji.setImageResource(R.mipmap.jinru);
        ivErji.setImageResource(R.mipmap.jinru);
        ivSanji.setImageResource(R.mipmap.jinru);
        switch (type){
            case "1":
                layout = new AutoLineFeedLayoutManager();
                layout.setAutoMeasureEnabled(true);
                rvShichang1.setLayoutManager(layout);
                rvShichang1.setAdapter(shiChangAdapter);
                rvShichang1.setVisibility(View.VISIBLE);
                yiji = true;
                ivYiji.setImageResource(R.mipmap.xia_kongxin_hui);
                break;
            case "2":
                layout = new AutoLineFeedLayoutManager();
                layout.setAutoMeasureEnabled(true);
                rvShichang2.setLayoutManager(layout);
                rvShichang2.setAdapter(shiChangAdapter);
                rvShichang2.setVisibility(View.VISIBLE);
                erji = true;
                ivErji.setImageResource(R.mipmap.xia_kongxin_hui);
                break;
            case "3":
                layout = new AutoLineFeedLayoutManager();
                layout.setAutoMeasureEnabled(true);
                rvShichang3.setLayoutManager(layout);
                rvShichang3.setAdapter(shiChangAdapter);
                rvShichang3.setVisibility(View.VISIBLE);
                sanji = true;
                ivSanji.setImageResource(R.mipmap.xia_kongxin_hui);
                break;
        }
    }
}
