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
import com.mingmen.mayi.mayibanjia.bean.WuliuShaixuanBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import cn.qqtheme.framework.picker.SinglePicker;

/**
 * Created by Administrator on 2018/9/27.
 */

public class ShaiXuanWuLiuDingdanDialog extends Dialog {

    @BindView(R.id.dingdanhao)
    TextView dingdanhao;
    @BindView(R.id.et_dingdanhao)
    EditText etDingdanhao;
    @BindView(R.id.quhuodizhi)
    TextView quhuodizhi;
    @BindView(R.id.et_quhuodizhi)
    EditText etQuhuodizhi;
    @BindView(R.id.tv_chepaihao)
    TextView tvChepaihao;
    @BindView(R.id.et_lianxiren)
    EditText etLianxiren;
    @BindView(R.id.tv_lianxifangshi)
    TextView tvLianxifangshi;
    @BindView(R.id.et_lianxidianhua)
    EditText etLianxidianhua;
    @BindView(R.id.rl_lianxiren)
    RelativeLayout rlLianxiren;
    @BindView(R.id.rl_lianxidianhua)
    RelativeLayout rlLianxidianhua;
    @BindView(R.id.bt_cancle)
    Button btCancle;
    @BindView(R.id.bt_sure)
    Button btSure;
    private Context context;
    private CallBack callBack;
    private String type;
    public interface CallBack{
        void success(WuliuShaixuanBean bean);
    }


    public ShaiXuanWuLiuDingdanDialog(@NonNull Context context,String type,CallBack callBack) {
        super(context);
        this.context = context;
        this.type = type;
        this.callBack = callBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_wuliudingdan_shaixuan, null);
        setContentView(v);
        etLianxidianhua = (EditText) v.findViewById(R.id.et_lianxidianhua);
        etLianxiren = (EditText) v.findViewById(R.id.et_lianxiren);
        etDingdanhao = (EditText) v.findViewById(R.id.et_dingdanhao);
        etQuhuodizhi = (EditText) v.findViewById(R.id.et_quhuodizhi);
        rlLianxidianhua = (RelativeLayout) v.findViewById(R.id.rl_lianxidianhua);
        rlLianxiren = (RelativeLayout) v.findViewById(R.id.rl_lianxiren);

        btSure = (Button) v.findViewById(R.id.bt_sure);
        btCancle = (Button) v.findViewById(R.id.bt_cancle);

        StringUtil.setInputNoEmoj(etLianxiren, 6);
        StringUtil.setInputNoEmoj(etQuhuodizhi,30);
        if(type.equals("1")){
            rlLianxidianhua.setVisibility(View.GONE);
            rlLianxiren.setVisibility(View.GONE);
        }
        btSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//确认
                WuliuShaixuanBean bean = new WuliuShaixuanBean();
                if(type.equals("0")){
                    bean.setDriverName(etLianxiren.getText().toString().trim());
                    bean.setDriverPhone(etLianxidianhua.getText().toString().trim());
                    bean.setWl_cars_order_number(etDingdanhao.getText().toString().trim());
                }
                bean.setMarketName(etQuhuodizhi.getText().toString().trim());
//                EventBus.getDefault().post(bean);
                callBack.success(bean);
                dismiss();
            }
        });
        btCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//取消
                dismiss();
            }
        });
    }
}
