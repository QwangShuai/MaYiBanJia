package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CarsTypeBean;
import com.mingmen.mayi.mayibanjia.bean.WuLiuBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.WuLiuActivity;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.List;

import cn.qqtheme.framework.picker.SinglePicker;

/**
 * Created by Administrator on 2018/9/27.
 */

public class ChangeWuLiuDialog extends Dialog {
    private Context context;
    private WuLiuActivity activity;
    private RelativeLayout rl;
    private TextView tv_cheliangleixing,tv_chepaihao,tv_lianxiren,tv_lianxifangshi;
    private EditText et_xinchepaihao,et_xinxingming,et_xinlianxifangshi;
    private Button bt_sure,bt_cancle;
    private WuLiuBean bean;
    private String car_type_name = "",car_type_id="";
    private String type = "";
    public ChangeWuLiuDialog(@NonNull Context context,WuLiuBean bean,WuLiuActivity activity,String type) {
        super(context);
        this.context = context;
        this.bean = bean;
        this.activity = activity;
        this.type =type;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    public void init(){
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_changewuliuche,null);
        setContentView(v);
        rl = (RelativeLayout) v.findViewById(R.id.rl_cheliangleixing);

        tv_cheliangleixing = (TextView) v.findViewById(R.id.et_cheliangleixing);
        tv_chepaihao = (TextView) v.findViewById(R.id.tv_chepaihao);
        tv_lianxiren = (TextView) v.findViewById(R.id.tv_lianxiren);
        tv_lianxifangshi = (TextView) v.findViewById(R.id.tv_lianxifangshi);

        et_xinchepaihao = (EditText) v.findViewById(R.id.et_chepaihao);
        et_xinxingming = (EditText) v.findViewById(R.id.et_xinxingming);
        et_xinlianxifangshi = (EditText) v.findViewById(R.id.et_xinlianxifangshi);

        bt_sure = (Button) v.findViewById(R.id.bt_sure);
        bt_cancle = (Button) v.findViewById(R.id.bt_cancle);

        tv_chepaihao.setText(bean.getPlateNumber());
        tv_lianxiren.setText(bean.getDriverName());
        tv_lianxifangshi.setText(bean.getDriverPhone());
        et_xinchepaihao.setTransformationMethod(new StringUtil.A2bigA());
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);

        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//弹出选择车辆类型
                HttpManager.getInstance()
                        .with(context)
                        .setObservable(RetrofitManager.getService()
                                .getCarsType(bean.getCars_type()))
                        .setDataListener(new HttpDataListener<List<CarsTypeBean>>() {
                            @Override
                            public void onNext(List<CarsTypeBean> data) {
                                final SinglePicker<CarsTypeBean> picker =new SinglePicker<>(activity,data);
                                Log.e("2222",data.toString());
                                picker.setCanceledOnTouchOutside(false);
                                picker.setSelectedIndex(1);
                                picker.setCycleDisable(false);
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
                if(TextUtils.isEmpty(et_xinchepaihao.getText().toString())){
                    ToastUtil.showToast("车牌号不能为空!");
                } else if(TextUtils.isEmpty(et_xinxingming.getText().toString())){
                    ToastUtil.showToast("用户名不能为空!");
                } else if(!AppUtil.isMobile(et_xinlianxifangshi.getText().toString())){
                    ToastUtil.showToast("手机号格式不正确!");
                } else if(TextUtils.isEmpty(car_type_id)){
                    ToastUtil.showToast("请选择车辆类型!");
                } else {
                    HttpManager.getInstance()
                            .with(context)
                            .setObservable(RetrofitManager.getService()
                                    .changeWuLiu(PreferenceUtils.getString(MyApplication.mContext, "token", ""),
                                            String.valueOf(bean.getWl_cars_id()),
                                            et_xinxingming.getText().toString(),
                                            et_xinlianxifangshi.getText().toString(),
                                            et_xinchepaihao.getText().toString(),
                                            bean.getMarketProvince().toString(),
                                            bean.getMarketCity(),
                                            car_type_id,bean.getWl_cars_order_number()))
                            .setDataListener(new HttpDataListener<String>() {
                                @Override
                                public void onNext(String s) {
                                    dismiss();
                                    activity.shuaxinWuLiu(type);
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
}
