package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CarsTypeBean;
import com.mingmen.mayi.mayibanjia.bean.CheliangBean;
import com.mingmen.mayi.mayibanjia.bean.WuliuDingdanBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.XuanZeCheLiangActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.SonghuodizhiAdapter;
import com.mingmen.mayi.mayibanjia.utils.JumpUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import cn.qqtheme.framework.picker.SinglePicker;

/**
 * Created by Administrator on 2018/9/27.
 */

public class WuliuFenpeiDialog extends Dialog {

    @BindView(R.id.tv_lianxifangshi)
    TextView tvLianxifangshi;


    public void setText(CheliangBean bean) {
        this.tvXuanzecheliang.setText(bean.getNew_driver_name());
        if(mytype.equals("0")){
            this.bean.setWl_cars_id(bean.getWl_cars_id());
        } else {
            this.bean.setNew_wl_cars_id(bean.getWl_cars_id());
        }

    }

    @BindView(R.id.tv_xuanzecheliang)
    TextView tvXuanzecheliang;
    @BindView(R.id.tv_lianxiren)
    TextView tvLianxiren;
    @BindView(R.id.tv_lianxidianhua)
    TextView tvLianxidianhua;
    @BindView(R.id.tv_cheliangleixing)
    TextView tvCheliangleixing;
    @BindView(R.id.tv_chepai)
    TextView tvChepai;
    @BindView(R.id.ll_xinxi)
    LinearLayout llXinxi;
    @BindView(R.id.tv_chepaihao)
    TextView tvChepaihao;
    @BindView(R.id.tv_shichang)
    TextView tvShichang;
    @BindView(R.id.rv_quhuodizhi)
    RecyclerView rvQuhuodizhi;
    @BindView(R.id.tv_luruyunfei)
    TextView tvLuruyunfei;
    @BindView(R.id.et_luruyunfei)
    EditText etLuruyunfei;
    @BindView(R.id.tv_beizhu)
    TextView tvBeizhu;
    @BindView(R.id.et_teshu)
    EditText etTeshu;
    @BindView(R.id.tv_hint)
    TextView tvHint;
    @BindView(R.id.tv_biangengliyou)
    TextView tvBiangengliyou;
    @BindView(R.id.et_biangengliyou)
    EditText etBiangengliyou;
    @BindView(R.id.tv_hint_biangengliyou)
    TextView tvHintBiangengliyou;
    @BindView(R.id.rl_biangengliyou)
    RelativeLayout rlBiangengliyou;
    @BindView(R.id.bt_cancle)
    Button btCancle;
    @BindView(R.id.bt_sure)
    Button btSure;
    private Context context;
    private WuliuDingdanBean bean;
    private SonghuodizhiAdapter adapter;
    private String mytype;

    public WuliuFenpeiDialog(@NonNull Context context, WuliuDingdanBean bean,String mytype) {
        super(context);
        this.context = context;
        this.bean = bean;
        this.mytype = mytype;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_wuliufenpei, null);
        setContentView(v);
        rvQuhuodizhi = (RecyclerView) v.findViewById(R.id.rv_quhuodizhi);
        rlBiangengliyou = (RelativeLayout) v.findViewById(R.id.rl_biangengliyou);

        llXinxi = (LinearLayout) v.findViewById(R.id.ll_xinxi);
        tvChepai = (TextView) v.findViewById(R.id.tv_chepai);
        tvLianxiren = (TextView) v.findViewById(R.id.tv_lianxiren);
        tvLianxidianhua = (TextView) v.findViewById(R.id.tv_lianxidianhua);
        tvChepaihao = (TextView) v.findViewById(R.id.tv_chepaihao);
        tvXuanzecheliang = (TextView) v.findViewById(R.id.tv_xuanzecheliang);
        tvCheliangleixing = (TextView) v.findViewById(R.id.tv_cheliangleixing);
        tvShichang = (TextView) v.findViewById(R.id.tv_shichang);
        etBiangengliyou = (EditText) v.findViewById(R.id.et_biangengliyou);
        etLuruyunfei = (EditText) v.findViewById(R.id.et_luruyunfei);
        etTeshu = (EditText) v.findViewById(R.id.et_teshu);
        btSure = (Button) v.findViewById(R.id.bt_sure);
        btCancle = (Button) v.findViewById(R.id.bt_cancle);
        adapter = new SonghuodizhiAdapter(context,bean.getDizhilist());
        rvQuhuodizhi.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        rvQuhuodizhi.setAdapter(adapter);
//        et_chepaihao.setTransformationMethod(new StringUtil.A2bigA());

        StringUtil.setInputNoEmoj(etBiangengliyou, 50);
        StringUtil.setInputNoEmoj(etTeshu, 50);
        StringUtil.setPricePoint(etLuruyunfei);

        tvXuanzecheliang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.Jump_intent(context, XuanZeCheLiangActivity.class,new Bundle());
            }
        });
        btSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//确认
                xuanze();

            }
        });
        btCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//取消
                dismiss();
            }
        });
        tvShichang.setText(bean.getMarketName());
        if(mytype.equals("1")){
            llXinxi.setVisibility(View.VISIBLE);
            tvLianxiren.setText(bean.getDriverName());
            tvLianxidianhua.setText(bean.getDriverPhone());
            tvXuanzecheliang.setText(bean.getCarTypeName());
            tvCheliangleixing.setText(bean.getCarTypeName());
            etLuruyunfei.setText(bean.getLogistics_draw_money());
            tvChepai.setText(bean.getPlateNumber());
            etTeshu.setText(bean.getRemarke());
            rlBiangengliyou.setVisibility(View.VISIBLE);
        }
    }

    private void xuanze(){
        if(TextUtils.isEmpty(bean.getWl_cars_id())){
            ToastUtil.showToastLong("请选择车辆");
        } else if(TextUtils.isEmpty(etLuruyunfei.getText().toString())
                ||Double.valueOf(etLuruyunfei.getText().toString().trim())==0){
            ToastUtil.showToastLong("请输入运费");
        } else {
            if(mytype.equals("0")){
                HttpManager.getInstance()
                        .with(context)
                        .setObservable(RetrofitManager.getService()
                                .fenpeiwuliu(PreferenceUtils.getString(MyApplication.mContext, "token", ""),
                                        etTeshu.getText().toString(),
                                        etLuruyunfei.getText().toString().trim(),bean.getWl_cars_order_number(),
                                        bean.getWl_cars_id()))
                        .setDataListener(new HttpDataListener<String>() {
                            @Override
                            public void onNext(String data) {
                                ToastUtil.showToastLong("分配物流车成功");
                                dismiss();
                            }
                        });
            } else {
                if(TextUtils.isEmpty(etBiangengliyou.getText().toString().trim())){
                    ToastUtil.showToastLong("请填写变更理由");
                } else if(TextUtils.isEmpty(bean.getNew_wl_cars_id())){
                    ToastUtil.showToastLong("请选择新的配送车辆");
                } else if(bean.getNew_wl_cars_id().equals(bean.getWl_cars_id())){
                    ToastUtil.showToastLong("请选择新的配送车辆");
                }else{
                    HttpManager.getInstance()
                            .with(context)
                            .setObservable(RetrofitManager.getService()
                                    .biangengwuliu(PreferenceUtils.getString(MyApplication.mContext, "token", ""),
                                            etTeshu.getText().toString(),
                                            etLuruyunfei.getText().toString().trim(),bean.getWl_cars_order_number(),
                                            bean.getWl_cars_id(),bean.getNew_wl_cars_id(),etBiangengliyou.getText().toString().trim()))
                            .setDataListener(new HttpDataListener<String>() {
                                @Override
                                public void onNext(String data) {
                                    ToastUtil.showToastLong("变更物流车成功");
                                    dismiss();
                                }
                            });
                }
            }
        }

    }
}
