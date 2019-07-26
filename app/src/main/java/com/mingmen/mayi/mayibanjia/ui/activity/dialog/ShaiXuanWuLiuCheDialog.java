package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
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
import com.mingmen.mayi.mayibanjia.bean.CheliangBean;
import com.mingmen.mayi.mayibanjia.bean.WuliuDingdanBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.WuLiuActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ChePaiMohuAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.wuliujingli.BaseJingliFragment;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import cn.qqtheme.framework.picker.SinglePicker;

/**
 * Created by Administrator on 2018/9/27.
 */

public class ShaiXuanWuLiuCheDialog extends Dialog {

    private Context context;
    private Activity activity;
    private RelativeLayout rl,rl_cheliangzhuangtai;
    private TextView tv_cheliangleixing,tv_cheliangzhuangtai;
    private EditText et_chepaihao,et_lianxifangshi;
    private Button bt_sure,bt_cancle;
    private CheliangBean cheliangBean;
    private LianggeXuanXiangDialog dialog;
    public ShaiXuanWuLiuCheDialog(@NonNull Context context,Activity activity) {
        super(context);
        this.context = context;
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void init(){
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_wuliuche_shaixuan,null);
        setContentView(v);
        cheliangBean = new CheliangBean();
        rl = (RelativeLayout) v.findViewById(R.id.rl_cheliangleixing);
        rl_cheliangzhuangtai = (RelativeLayout) v.findViewById(R.id.rl_cheliangzhuangtai);

        tv_cheliangleixing = (TextView) v.findViewById(R.id.tv_cheliangleixing);
        tv_cheliangzhuangtai = (TextView) v.findViewById(R.id.tv_cheliangzhuangtai);
        et_chepaihao = (EditText) v.findViewById(R.id.et_chepaihao);
        et_lianxifangshi = (EditText) v.findViewById(R.id.et_lianxifangshi);
        bt_sure = (Button) v.findViewById(R.id.bt_sure);
        bt_cancle = (Button) v.findViewById(R.id.bt_cancle);
        et_chepaihao.setTransformationMethod(new StringUtil.A2bigA());

        StringUtil.setInputNoEmoj(et_chepaihao,24);
        rl_cheliangzhuangtai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//弹出选择车辆状态
                                dialog = new LianggeXuanXiangDialog(context,
                                        context.getResources().getIdentifier("BottomDialog", "style", context.getPackageName()));
                                dialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.LEFT | Gravity.RIGHT);
                                dialog.showDialog("空闲", "已分车");
                                dialog.getTvXuanxiang1().setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.cancel();
                                        cheliangBean.setCars_type("0");
                                        tv_cheliangzhuangtai.setText("空闲");
                                    }
                                });
                                dialog.getTvXuanxiang2().setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.cancel();
                                        cheliangBean.setCars_type("1");
                                        tv_cheliangzhuangtai.setText("已分车");
//                        llShowTejia.setVisibility(View.GONE);
                                    }
                                });
            }
        });
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
                cheliangBean.setNew_driver_phone(et_lianxifangshi.getText().toString().trim());
                EventBus.getDefault().post(cheliangBean);
                dismiss();
            }
        });
        bt_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//取消
                dismiss();
            }
        });
    }
    //验证车牌号
    public static boolean isCarnumberNO(String carnumber) {
        /*
         车牌号格式：汉字 + A-Z + 5位A-Z或0-9
        （只包括了普通车牌号，教练车和部分部队车等车牌号不包括在内）
         */
        String carnumRegex = "[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}";
        if (TextUtils.isEmpty(carnumber)) return false;
        else return carnumber.matches(carnumRegex);
    }
}
