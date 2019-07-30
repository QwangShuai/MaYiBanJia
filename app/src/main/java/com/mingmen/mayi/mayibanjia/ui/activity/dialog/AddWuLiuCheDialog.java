package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CarsTypeBean;
import com.mingmen.mayi.mayibanjia.bean.CheliangBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import cn.qqtheme.framework.picker.SinglePicker;

/**
 * Created by Administrator on 2018/9/27.
 */

public class AddWuLiuCheDialog extends Dialog {

    private Context context;
    private Activity activity;
    private RelativeLayout rl;
    private TextView tv_cheliangleixing;
    private EditText et_chepaihao,et_lianxifangshi,et_xingming;
    private Button bt_sure,bt_cancle;
    private CheliangBean cheliangBean;
    private LianggeXuanXiangDialog dialog;
    public AddWuLiuCheDialog(@NonNull Context context, Activity activity,CheliangBean mybean) {
        super(context);
        this.context = context;
        this.activity = activity;
        this.cheliangBean = mybean;
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
        et_lianxifangshi = (EditText) v.findViewById(R.id.et_lianxifangshi);
        et_xingming = (EditText) v.findViewById(R.id.et_xingming);
        bt_sure = (Button) v.findViewById(R.id.bt_sure);
        bt_cancle = (Button) v.findViewById(R.id.bt_cancle);
        et_chepaihao.setTransformationMethod(new StringUtil.A2bigA());
        if(StringUtil.isValid(cheliangBean.getWl_cars_id())){
            et_xingming.setText(cheliangBean.getNew_driver_name());
            et_lianxifangshi.setText(cheliangBean.getNew_driver_phone());
            et_chepaihao.setText(cheliangBean.getNew_plate_number());
            tv_cheliangleixing.setText(cheliangBean.getNew_wl_cars_type_name());
        }
        StringUtil.setInputNoEmoj(et_xingming,10);
        StringUtil.setInputNoEmoj(et_chepaihao,24);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//弹出选择车辆类型
                HttpManager.getInstance()
                        .with(context)
                        .setObservable(RetrofitManager.getService()
                                .getAllCarsType(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
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
                                        cheliangBean.setNew_wl_cars_type(item.getCar_type_id());
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
                cheliangBean.setNew_plate_number(et_chepaihao.getText().toString().trim());
//                cheliangBean.setNew_driver_phone(et_lianxifangshi.getText().toString().trim());
                cheliangBean.setNew_driver_name(et_xingming.getText().toString().trim());

//                boolean   yz =isCarnumberNO(et_chepaihao.getText().toString());
//                if(yz==false){
//                    ToastUtil.showToast("车牌号格式错误");
//
//                } else
//                if(!StringUtil.exist(et_xingming.getText().toString())){
//                    ToastUtil.showToast("联系人姓名不正确!");
//                    return;
//                } else
                    if(!AppUtil.isMobile(et_lianxifangshi.getText().toString())){
                    ToastUtil.showToast("手机号格式不正确!");
                    return;
                } else if(TextUtils.isEmpty(cheliangBean.getNew_wl_cars_type())){
                    ToastUtil.showToast("请选择车辆类型!");
                    return;
                }
//                else if(!StringUtil.isCarnumberNO(et_chepaihao.getText().toString().trim())){
//                    ToastUtil.showToast("车牌号不正确!");
//                    return;
//                }
                else if(TextUtils.isEmpty(et_chepaihao.getText().toString().trim())){
                    ToastUtil.showToast("车牌号不能为空!");
                    return;
                }

                if(et_lianxifangshi.getText().toString().trim().equals(cheliangBean.getNew_driver_phone())){
                    post();
                } else {
                    HttpManager.getInstance()
                            .with(context)
                            .setObservable(RetrofitManager.getService()
                                    .yanzhengPhone(PreferenceUtils.getString(MyApplication.mContext, "token", ""),
                                            et_lianxifangshi.getText().toString().trim()))
                            .setDataListener(new HttpDataListener<String>() {
                                @Override
                                public void onNext(String data) {
                                    post();
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

    private void post(){
        if(StringUtil.isValid(cheliangBean.getWl_cars_id())){
            HttpManager.getInstance()
                    .with(context)
                    .setObservable(RetrofitManager.getService()
                            .updateCheliang(PreferenceUtils.getString(MyApplication.mContext, "token", ""), cheliangBean.getWl_cars_id(),
                                    cheliangBean.getNew_driver_name(),et_lianxifangshi.getText().toString().trim(),
                                    cheliangBean.getNew_plate_number(),cheliangBean.getNew_wl_cars_type()))
                    .setDataListener(new HttpDataListener<String>() {
                        @Override
                        public void onNext(String data) {
                            EventBus.getDefault().post("0000");
                            dismiss();
                        }
                    });
        } else {
            HttpManager.getInstance()
                    .with(context)
                    .setObservable(RetrofitManager.getService()
                            .addCheliang(PreferenceUtils.getString(MyApplication.mContext, "token", ""),
                                    cheliangBean.getNew_driver_name(),et_lianxifangshi.getText().toString().trim(),
                                    cheliangBean.getNew_plate_number(),cheliangBean.getNew_wl_cars_type()))
                    .setDataListener(new HttpDataListener<String>() {
                        @Override
                        public void onNext(String data) {
                            EventBus.getDefault().post("0000");
                            dismiss();
                        }
                    });
        }
    }
}
