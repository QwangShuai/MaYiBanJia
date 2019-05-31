package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean;
import com.mingmen.mayi.mayibanjia.bean.CaiGouMingChengBean;
import com.mingmen.mayi.mayibanjia.bean.FCGGuige;
import com.mingmen.mayi.mayibanjia.bean.FCGName;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.CaiGouMingChengAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.FaCaiGouGuiGeAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.FaCaiGouMohuAdapter;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/8/15.
 */

public class CaiGouDanTianJiaDailog extends BaseFragmentDialog {
    @BindView(R.id.tv_shangpin_ming)
    TextView tvShangpinMing;
    @BindView(R.id.et_caigouliang)
    EditText etCaigouliang;
    @BindView(R.id.tv_guige)
    TextView tvGuige;
    @BindView(R.id.tv_xiangxia)
    ImageView tvXiangxia;
    @BindView(R.id.rl_guige)
    RelativeLayout rlGuige;
    @BindView(R.id.iv_teshu)
    ImageView ivTeshu;
    @BindView(R.id.ll_teshu)
    LinearLayout llTeshu;
    @BindView(R.id.et_teshu)
    EditText etTeshu;
    @BindView(R.id.iv_shanchuwenzi)
    ImageView ivShanchuwenzi;
    @BindView(R.id.tishi)
    TextView tishi;
    @BindView(R.id.rl_teshu)
    RelativeLayout rlTeshu;
    @BindView(R.id.bt_quxiao)
    Button btQuxiao;
    @BindView(R.id.bt_queren)
    Button btQueren;
    Unbinder unbinder;
    @BindView(R.id.tv_cgmc)
    TextView tvCgmc;
    @BindView(R.id.rl_cgmc)
    RelativeLayout rlCgmc;
    @BindView(R.id.ll_cgmc)
    LinearLayout llCgmc;
    Unbinder unbinder1;
    private CallBack mCallBack;
    private String teshuyaoqiu = "";
    private String caigouliang = "";
    private String name = "";
    private String guigeName = "";

    public CaiGouDanTianJiaDailog() {

    }
    public void setDate(String name,String guigeName){
        this.name = name;
        this.guigeName = guigeName;
    }
    public CaiGouDanTianJiaDailog setCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
        return this;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_caigoudantianjia;
    }

    @Override
    protected void init() {
        tvShangpinMing.setText(name);
        tvGuige.setText(guigeName);
        StringUtil.setInputNoEmoj(etTeshu,50);
        etTeshu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tishi.setText(s.toString().trim().length() + "/50");
                if (s.toString().trim().length() > 0) {
                    ivTeshu.setSelected(true);
                } else {
                    ivTeshu.setSelected(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @OnClick({R.id.bt_queren, R.id.bt_quxiao, R.id.iv_shanchuwenzi, R.id.rl_guige,
            R.id.rl_cgmc,R.id.tv_shangpin_ming})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.bt_queren:
                huoqushuju();
                if(StringUtil.isValid(caigouliang)){
                    if(Double.valueOf(caigouliang)>=1){
                        dismiss();
                        mCallBack.confirm(caigouliang,teshuyaoqiu);
                    } else {
                        ToastUtil.showToastLong("采购数量不能小于1");
                    }
                } else {
                    ToastUtil.showToastLong("采购数量不能为空");
                }

                break;
            case R.id.bt_quxiao:
                dismiss();
                break;
            case R.id.iv_shanchuwenzi:
                etTeshu.setText("");
                break;
            case R.id.rl_guige:
                break;
            case R.id.rl_cgmc:
                break;
            case R.id.tv_shangpin_ming:
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }

    public interface CallBack {
        void confirm(String count,String tsyq);
    }

    //获取用户填写的数据
    private void huoqushuju() {
        caigouliang = etCaigouliang.getText().toString().trim();
        teshuyaoqiu = etTeshu.getText().toString().trim();
    }

}
