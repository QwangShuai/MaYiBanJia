package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.EditorShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.FbspCanShuBean;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.LianggeXuanXiangDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.PhotoDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/7/007.
 */

public class FaBuShangPinQiDingLiangActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_qingkong)
    ImageView ivQingkong;
    @BindView(R.id.tv_zhiman)
    TextView tvZhiman;
    @BindView(R.id.et_qidingliangdanjia1)
    EditText etQidingliangdanjia1;
    @BindView(R.id.et_qidingliang1)
    EditText etQidingliang1;
//    @BindView(R.id.et_qidingliangdanjia2)
//    EditText etQidingliangdanjia2;
//    @BindView(R.id.et_qidingliang2)
//    EditText etQidingliang2;
//    @BindView(R.id.et_qidingliangdanjia3)
//    EditText etQidingliangdanjia3;
//    @BindView(R.id.et_qidingliang3)
//    EditText etQidingliang3;
    @BindView(R.id.et_kucun)
    EditText etKucun;
    @BindView(R.id.bt_xiayibu)
    Button btXiayibu;
    @BindView(R.id.ll_tejia)
    LinearLayout llTejia;
    @BindView(R.id.tv_tejia)
    TextView tvTejia;
    @BindView(R.id.ll_showtejia)
    LinearLayout llShowTejia;
    @BindView(R.id.et_tejia)
    EditText etTejia;
    private Context mContext;
    private FbspCanShuBean canshu;
    private LianggeXuanXiangDialog tejiadialog;
    private boolean istejia;
    private String qidingliang1;
    private String qidingliang2;
    private String qidingliang3;
    private String qidingliangdanjia1;
    private String qidingliangdanjia2;
    private String qidingliangdanjia3;
    public static FaBuShangPinQiDingLiangActivity instance = null;
    private String yemian = "0";
    private String pipei = "0";

    @Override
    public int getLayoutId() {
        return R.layout.activity_fabushangpinqidingliang;
    }

    @Override
    protected void initData() {
        mContext = FaBuShangPinQiDingLiangActivity.this;
        instance = FaBuShangPinQiDingLiangActivity.this;
        String canshujson = getIntent().getStringExtra("canshu");
        canshu = gson.fromJson(canshujson, FbspCanShuBean.class);
//        etQidingliang2.setEnabled(false);
//        etQidingliangdanjia2.setEnabled(false);
//        etQidingliang3.setEnabled(false);
//        etQidingliangdanjia3.setEnabled(false);
        ivQingkong.setVisibility(View.GONE);
        yemian = getIntent().getStringExtra("yemian");
        if(canshu.getGoods().equals("1")){
            llShowTejia.setVisibility(View.VISIBLE);
        }
        if (yemian.equals("0")) {
            tvTitle.setText("新建商品");
            pipei = getIntent().getStringExtra("pipei");
            if(!pipei.equals("0")){
                setDataView();
            }
        } else {
            tvTitle.setText("编辑商品");
            setDataView();
        }
        etKucun.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() > 0) {
                    ivQingkong.setVisibility(View.VISIBLE);
                } else {
                    ivQingkong.setVisibility(View.GONE);
                }
            }
        });
//        etQidingliang1.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (!"".equals(s.toString().trim()) & !"".equals(etQidingliangdanjia1.getText().toString())) {
//                    etQidingliang2.setEnabled(true);
//                    etQidingliangdanjia2.setEnabled(true);
//                } else {
//                    etQidingliang2.setEnabled(false);
//                    etQidingliangdanjia2.setEnabled(false);
//                }
//            }
//        });
//        etQidingliangdanjia1.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (!"".equals(s.toString().trim()) & !"".equals(etQidingliang1.getText().toString())) {
//                    etQidingliang2.setEnabled(true);
//                    etQidingliangdanjia2.setEnabled(true);
//                } else {
//                    etQidingliang2.setEnabled(false);
//                    etQidingliangdanjia2.setEnabled(false);
//                }
//            }
//        });
//
//
//        etQidingliang2.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (!"".equals(s.toString().trim()) & !"".equals(etQidingliangdanjia2.getText().toString())) {
//                    etQidingliang3.setEnabled(true);
//                    etQidingliangdanjia3.setEnabled(true);
//                    Log.e("isEnabledliang", etQidingliang3.isEnabled() + "-");
//                    Log.e("isFocusable", etQidingliang3.isFocusable() + "-");
//
//
//                } else {
//                    etQidingliang3.setEnabled(false);
//                    etQidingliangdanjia3.setEnabled(false);
//                    Log.e("isEnabledbuliang", etQidingliang3.isEnabled() + "-");
//                    Log.e("isFocusable", etQidingliang3.isFocusable() + "-");
//                }
//            }
//        });
//        etQidingliangdanjia2.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (!"".equals(s.toString().trim()) & !"".equals(etQidingliang2.getText().toString())) {
//                    etQidingliang3.setEnabled(true);
//                    etQidingliangdanjia3.setEnabled(true);
//                    Log.e("liang1", "liang1");
//                } else {
//                    etQidingliang3.setEnabled(false);
//                    etQidingliangdanjia3.setEnabled(false);
//                    Log.e("buliang2", "buliang2");
//                }
//            }
//        });
    }

    @OnClick({R.id.iv_back, R.id.iv_qingkong, R.id.tv_zhiman, R.id.ll_tejia, R.id.bt_xiayibu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_qingkong:
                etKucun.setText("");
                break;
            case R.id.ll_tejia:
//                istejia = true;
                tejiadialog = new LianggeXuanXiangDialog(mContext,
                        mContext.getResources().getIdentifier("BottomDialog", "style", mContext.getPackageName()));
                tejiadialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.LEFT | Gravity.RIGHT);
                tejiadialog.showDialog("是", "否");
                tejiadialog.getTvXuanxiang1().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tejiadialog.cancel();
//                        istejia = true;
                        tvTejia.setText("是");
                    }
                });
                tejiadialog.getTvXuanxiang2().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tejiadialog.cancel();
//                        istejia = false;
                        tvTejia.setText("否");
                    }
                });
                break;
            case R.id.tv_zhiman:
                etKucun.setText("9999");
                break;
            case R.id.bt_xiayibu:
                qidingliang1 = etQidingliang1.getText().toString().trim();
//                qidingliang2 = etQidingliang2.getText().toString().trim();
//                qidingliang3 = etQidingliang3.getText().toString().trim();
                qidingliangdanjia1 = etQidingliangdanjia1.getText().toString().trim();
//                qidingliangdanjia2 = etQidingliangdanjia2.getText().toString().trim();
//                qidingliangdanjia3 = etQidingliangdanjia3.getText().toString().trim();
                if ("".equals(etKucun.getText().toString().trim())) {
                    ToastUtil.showToast("请填写库存数量");
                    return;
                }
                if (("".equals(qidingliang1) || "".equals(qidingliangdanjia1))) {
                    ToastUtil.showToast("请填写起订量");
                    return;
                }
                if(Double.valueOf(etQidingliangdanjia1.getText().toString().trim())<=Double.valueOf(etTejia.getText().toString().trim())){
                    ToastUtil.showToast("特价必须小于原价");
                    return;
                }
                tiaoye();

//                if ("".equals(qidingliang2) & "".equals(qidingliangdanjia2)) {
//                    tiaoye();
//                    return;
//                }
//                if (!"".equals(qidingliang2) & !"".equals(qidingliangdanjia2)) {
//                    if (Integer.parseInt(qidingliang2) > Integer.parseInt(qidingliang1)) {
//                        if ("".equals(qidingliang3) & "".equals(qidingliangdanjia3)) {
//                            tiaoye();
//                            return;
//                        }
//                        if (!"".equals(qidingliang3) & !"".equals(qidingliangdanjia3)) {
//                            if (Integer.parseInt(qidingliang3) > Integer.parseInt(qidingliang2)) {
//                                tiaoye();
//                            } else {
//                                ToastUtil.showToast("起定量3需要大于起订量2");
//                                return;
//                            }
//                        } else {
//                            ToastUtil.showToast("请确认起订量与价格同时填写");
//                            return;
//                        }
//                    } else {
//                        ToastUtil.showToast("起定量2需要大于起订量1");
//                        return;
//                    }
//                } else {
//                    ToastUtil.showToast("请确认起订量与价格同时填写");
//                    return;
//                }


                break;
        }
    }

    private void tiaoye() {
        canshu.setInventory(etKucun.getText().toString().trim());
        canshu.setRation_one(qidingliang1);
//        canshu.setRation_two(qidingliang2);
//        canshu.setRation_three(qidingliang3);
        canshu.setPice_one(qidingliangdanjia1);
//        canshu.setPice_two(qidingliangdanjia2);
//        canshu.setPice_three(qidingliangdanjia3);

//        if (tvTejia.getText().toString().equals("是")) {
//            canshu.setGoods("1");
//        } else {
//            canshu.setGoods("0");
//        }
        if(canshu.getGoods().equals("1")){
            canshu.setPrice(etTejia.getText().toString().trim());
        }
        Log.e("传的啥玩意啊",canshu.getGoods());
        Intent intent = new Intent(mContext, FaBuShangPinXiangQingTuActivity.class);
        intent.putExtra("yemian",yemian);
        intent.putExtra("canshu", gson.toJson(canshu));
        intent.putExtra("pipei",pipei);
        startActivity(intent);
    }

    public void setDataView() {
        EditorShangPinBean.XqBean bean = PreferenceUtils.getEditorShangPinBean(MyApplication.mContext, "").getXq();
        etKucun.setText(bean.getInventory());
        ivQingkong.setVisibility(View.VISIBLE);
        if (bean.getPice_one() != 0){
            etQidingliangdanjia1.setText(bean.getPice_one() + "");
//            etQidingliang2.setEnabled(true);
//            etQidingliangdanjia2.setEnabled(true);
        }

//        if (bean.getPice_two() != 0){
//            etQidingliangdanjia2.setText(bean.getPice_two() + "");
//            etQidingliang3.setEnabled(true);
//            etQidingliangdanjia3.setEnabled(true);
//        }
//
//        if (bean.getPice_three() != 0){
//            etQidingliangdanjia3.setText(bean.getPice_three() + "");
//        }
        etQidingliang1.setText(bean.getRation_one());
//        etQidingliang2.setText(bean.getRation_two());
//        etQidingliang3.setText(bean.getRation_three());
        tvTejia.setText(bean.getGoods().equals("1") ? "是" : "否");
    }
}
