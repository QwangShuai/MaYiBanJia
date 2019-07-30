package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.ReplacementTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CarsTypeBean;
import com.mingmen.mayi.mayibanjia.bean.ChePaiBean;
import com.mingmen.mayi.mayibanjia.bean.FCGName;
import com.mingmen.mayi.mayibanjia.bean.WuLiuBean;
import com.mingmen.mayi.mayibanjia.bean.WuliuDingdanBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.WuLiuActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ChePaiMohuAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.FaCaiGouMohuAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.wuliujingli.BaseJingliFragment;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.SinglePicker;

/**
 * Created by Administrator on 2018/9/27.
 */

public class FenPeiWuLiuCheDialog extends Dialog {

    private Context context;
    private Activity activity;
    private RelativeLayout rl;
    private TextView tv_cheliangleixing;
    private EditText et_chepaihao,et_xingming,et_lianxifangshi;
    private Button bt_sure,bt_cancle;
    private WuliuDingdanBean bean;
    private RecyclerView rv_mohu;
    private PopupWindow mPopWindow;
    private ChePaiMohuAdapter mohuAdapter;
    private ArrayList<ChePaiBean> datas = new ArrayList<>();
    String car_type_name = "",car_type_id="";
    private String chepai="";
    private BaseJingliFragment fragment;
    public FenPeiWuLiuCheDialog(@NonNull Context context, WuliuDingdanBean bean, Activity activity, BaseJingliFragment fragment) {
        super(context);
        this.context = context;
        this.bean = bean;
        this.activity = activity;
        this.fragment = fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    public void init(){
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_fenpeiwuliuche,null);
        setContentView(v);
        rl = (RelativeLayout) v.findViewById(R.id.rl_cheliangleixing);

        tv_cheliangleixing = (TextView) v.findViewById(R.id.tv_cheliangleixing);
        et_chepaihao = (EditText) v.findViewById(R.id.et_chepaihao);
        et_xingming = (EditText) v.findViewById(R.id.et_xingming);
        et_lianxifangshi = (EditText) v.findViewById(R.id.et_lianxifangshi);
        StringUtil.setInputNoEmoj(et_xingming,10);
        et_chepaihao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() > 0) {
                    if(StringUtil.isValid(car_type_id)){
                        if(!s.toString().trim().equals(chepai)){
                            getChepaihao(s.toString().trim());
                        }
                    }
                }

            }
        });
        bt_sure = (Button) v.findViewById(R.id.bt_sure);
        bt_cancle = (Button) v.findViewById(R.id.bt_cancle);
        et_chepaihao.setTransformationMethod(new StringUtil.A2bigA());
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//弹出选择车辆类型
                HttpManager.getInstance()
                        .with(context)
                        .setObservable(RetrofitManager.getService()
                                .getCarsType(PreferenceUtils.getString(MyApplication.mContext, "token", ""),bean.getCars_type()))
                        .setDataListener(new HttpDataListener<List<CarsTypeBean>>() {
                            @Override
                            public void onNext(List<CarsTypeBean> data) {
                                final SinglePicker<CarsTypeBean> picker =new SinglePicker<>(activity,data);
                                Log.e("2222",data.toString());
                                picker.setCanceledOnTouchOutside(false);
                                picker.setSelectedIndex(1);
                                picker.setCycleDisable(true);
                                picker.setOnItemPickListener(new SinglePicker.OnItemPickListener<CarsTypeBean>() {
                                    @Override
                                    public void onItemPicked(int index, CarsTypeBean item) {
                                        car_type_name = item.getCar_type_name();
                                        car_type_id = item.getCar_type_id();
                                        tv_cheliangleixing.setText(item.getCar_type_name());
                                        picker.dismiss();
                                    }
                                });
                                picker.show();
                            }
                        });
            }
        });
        bt_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//确认

                if(TextUtils.isEmpty(et_chepaihao.getText().toString())){
                    ToastUtil.showToast("车牌号不能为空!");
                    return;
                }
//                boolean   yz =isCarnumberNO(et_chepaihao.getText().toString());
//                if(yz==false){
//                    ToastUtil.showToast("车牌号格式错误");
//
//                } else
                    if(!StringUtil.exist(et_xingming.getText().toString())){
                    ToastUtil.showToast("联系人姓名不正确!");
                } else if(!AppUtil.isMobile(et_lianxifangshi.getText().toString())){
                    ToastUtil.showToast("手机号格式不正确!");
                } else if(TextUtils.isEmpty(car_type_id)){
                    ToastUtil.showToast("请选择车辆类型!");
                } else {

                    HttpManager.getInstance()
                            .with(context)
                            .setObservable(RetrofitManager.getService()
                                    .fenpeiWuLiu(PreferenceUtils.getString(MyApplication.mContext, "token", ""),
                                            et_xingming.getText().toString(),
                                            et_lianxifangshi.getText().toString(),
                                            et_chepaihao.getText().toString(),
                                            bean.getMarketProvince().toString(),
                                            bean.getMarketCity(),
                                            car_type_id,bean.getWl_cars_order_number()))
                            .setDataListener(new HttpDataListener<String>() {
                                @Override
                                public void onNext(String s) {
                                    dismiss();
                                    fragment.onResume();
                                }
                            });
                }
            }
        });
        bt_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//取消
                dismiss();
            }
        });
    }
    //PopupWindow
    private void showPopupWindow() {
        View view = View.inflate(context, R.layout.pp_textview_recycleview, null);
        mPopWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = activity.getWindowManager().getDefaultDisplay().getHeight();
        mPopWindow.setWidth(width * 2 / 6);
        mPopWindow.setHeight(height * 2 / 9);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.showAsDropDown(et_chepaihao);
        rv_mohu = (RecyclerView) view.findViewById(R.id.rv_list);
        mohuAdapter = new ChePaiMohuAdapter(context, datas);
        rv_mohu.setAdapter(mohuAdapter);
        rv_mohu.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mohuAdapter.setOnItemClickListener(new ChePaiMohuAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                chepai = datas.get(position).getNew_plate_number()+"";
                et_chepaihao.setText(datas.get(position).getNew_plate_number()+"");
                et_lianxifangshi.setText(datas.get(position).getDriver_phone()+"");
                et_xingming.setText(datas.get(position).getDriver_name()+"");
                mPopWindow.dismiss();
            }
        });
    }
    private void getChepaihao(final String name) {
        HttpManager.getInstance()
                .with(context)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getChepai(PreferenceUtils.getString(MyApplication.mContext, "token", ""),name, car_type_id))
                .setDataListener(new HttpDataListener<List<ChePaiBean>>() {
                    @Override
                    public void onNext(List<ChePaiBean> data) {
                        datas = new ArrayList<ChePaiBean>();
                        datas.addAll(data);
                        Log.e("data", data + "---");
                        if (mPopWindow != null) {
                            mPopWindow.showAsDropDown(et_chepaihao);
                            mohuAdapter.setData(datas);
                        } else {
                            showPopupWindow();
                        }

                    }
                }, false);
    }
}
