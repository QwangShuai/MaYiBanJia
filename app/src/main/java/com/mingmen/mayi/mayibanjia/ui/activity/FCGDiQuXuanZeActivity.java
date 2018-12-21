package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.FCGName;
import com.mingmen.mayi.mayibanjia.bean.FCGSaveFanHuiBean;
import com.mingmen.mayi.mayibanjia.bean.ProvinceBean;
import com.mingmen.mayi.mayibanjia.bean.ShiChangBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.FCGShiChangAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.FaCaiGouLishiAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.FaCaiGouMohuAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.view.AutoLineFeedLayoutManager;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.text.SimpleDateFormat;
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
    @BindView(R.id.et_caigouming)
    EditText et_caigouming;
    @BindView(R.id.bt_queding)
    Button btQueding;
    @BindView(R.id.iv_bg)
    ImageView ivBg;

    private List<FCGSaveFanHuiBean> datas = new ArrayList<>();
    private Context mContext;
    public static FCGDiQuXuanZeActivity instance = null;
    private FCGShiChangAdapter shiChangAdapter;
    private FaCaiGouLishiAdapter adapter;
    private PopupWindow mPopWindow;
    private RecyclerView rv_mohu;
    private String shichang="";
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

    @OnClick({R.id.iv_back,   R.id.bt_queding})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.bt_queding:
                    if (et_caigouming.getText().toString().trim().length()==0){
                        ToastUtil.showToast("请填写采购单名称");
                        return;
                    }
                    yanzheng();

                break;
        }
    }


    public void guan(){
        finish();
    }

    public void initList(){
        shiChangAdapter = new FCGShiChangAdapter();
        et_caigouming.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length()>0){
                    if(!s.toString().trim().equals(shichang)){
                        getfcgname(s.toString().trim());
                    }

                }

            }
        });

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String date=sdf.format(new java.util.Date());
        et_caigouming.setText(date);

        getBg();
    }

    //PopupWindow
    private void showPopupWindow() {
        View view = View.inflate(mContext, R.layout.pp_textview_recycleview, null);
        mPopWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int width = getWindowManager().getDefaultDisplay().getWidth();
        int height = getWindowManager().getDefaultDisplay().getHeight();
        mPopWindow.setWidth(width * 2 / 6);
        mPopWindow.setHeight(height * 2 / 9);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.showAsDropDown(et_caigouming);
        rv_mohu = (RecyclerView) view.findViewById(R.id.rv_list);
        adapter = new FaCaiGouLishiAdapter(mContext, datas);
        rv_mohu.setAdapter(adapter);
        rv_mohu.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        adapter.setOnItemClickListener(new FaCaiGouLishiAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                shichang = ""+datas.get(position).getPurchase_name();
                et_caigouming.setText(""+datas.get(position).getPurchase_name());
                mPopWindow.dismiss();
            }
        });
    }
    private void getfcgname(final String name) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getCaigouming(PreferenceUtils.getString(MyApplication.mContext, "token",""),name))
                .setDataListener(new HttpDataListener<List<FCGSaveFanHuiBean>>() {
                    @Override
                    public void onNext(List<FCGSaveFanHuiBean> data) {
                        if(data!=null||data.size()!=0){
                            datas.clear();
                            datas.addAll(data);
                            if (mPopWindow!=null){
                                mPopWindow.showAsDropDown(et_caigouming);
                                adapter.setData(datas);
                            }else{
                                showPopupWindow();
                            }
                        } else {
                            ToastUtil.showToast("没查到，空的");
                        }
                    }
                },false);
    }
    private void getBg() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getBg())
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        Glide.with(mContext).load(data).into(ivBg);
                    }
                },false);
    }

    private void yanzheng(){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .yanzhengCgd(PreferenceUtils.getString(MyApplication.mContext, "token", ""),et_caigouming.getText().toString().trim()))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        Intent intent=new Intent(mContext,FCGCaiGouXuQiuActivity.class);
                        bundle.putString("caigouming",et_caigouming.getText().toString().trim());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                },true);
    }
}
