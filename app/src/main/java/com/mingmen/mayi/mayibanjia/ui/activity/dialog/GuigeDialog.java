package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CreateGuigeBean;
import com.mingmen.mayi.mayibanjia.bean.FCGGuige;
import com.mingmen.mayi.mayibanjia.bean.FbspGuiGeBean;
import com.mingmen.mayi.mayibanjia.bean.ShangPinSousuoMohuBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.GuigeAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.XJSPFeiLeiGuigeAdapter;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/10/19.
 */

public class GuigeDialog extends BaseFragmentDialog implements View.OnClickListener {


    Unbinder unbinder;
    @BindView(R.id.rv_guige)
    RecyclerView rvGuige;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    @BindView(R.id.et_create_guige)
    EditText etCreateGuige;
    @BindView(R.id.ll)
    LinearLayout ll;
    private GuigeAdapter adapter;
    private String dqId;
    private Context mContext;
    private List<FbspGuiGeBean> songdashijianlist = new ArrayList<FbspGuiGeBean>();
    private FbspGuiGeBean bean = new FbspGuiGeBean();
    private int type = 0;
    private boolean xztype;

    public GuigeDialog setData(Context mContext) {
        this.mContext = mContext;
        return this;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_guige;
    }

    @Override
    protected void init() {
        StringUtil.setInputNoEmoj(etCreateGuige,4);
        Window window = getDialog().getWindow();
        if (window != null) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
        etCreateGuige.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(StringUtil.isValid(s.toString().trim())){
                    xztype = true;
                } else {
                    xztype = false;
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        getData();
        getGuige();
    }

    @Override
    protected void initConfiguration(Configuration configuration) {
        configuration.fullHight()
                .setGravity(Gravity.RIGHT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void setDqId(String dqId,int type){
        this.type = type;
        this.dqId = dqId;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_cancel, R.id.tv_sure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                this.dismiss();
                break;
            case R.id.tv_sure:
                if(xztype){
                    if(StringUtil.isValid(etCreateGuige.getText().toString())){
                        CreateGuigeBean bean = new CreateGuigeBean();
                        bean.setName(etCreateGuige.getText().toString());
                        bean.setLevel(type);
                        EventBus.getDefault().post(bean);
                        this.dismiss();
                    }else if (StringUtil.isValid(dqId)&&StringUtil.isValid(bean.getSpec_id())) {
                        Log.e( "onClick: ",new Gson().toJson(bean) );
                        this.dismiss();
                        EventBus.getDefault().post(bean);
                    } else if(StringUtil.isValid(dqId)){
                        for (FbspGuiGeBean ggbean:songdashijianlist) {
                            if(dqId.equals(ggbean.getSpec_id())){
                                ggbean.setLevel(type);
                                this.dismiss();
                                EventBus.getDefault().post(ggbean);
                                return;
                            }
                        }
                            ToastUtil.showToastLong("请选择一个规格");

                    } else {
                        ToastUtil.showToastLong("请选择一个规格");
                    }
                } else {
                    if (StringUtil.isValid(dqId)&&StringUtil.isValid(bean.getSpec_id())) {
                        Log.e( "onClick: ",new Gson().toJson(bean) );
                        this.dismiss();
                        EventBus.getDefault().post(bean);
                    } else if(StringUtil.isValid(dqId)){
                        for (FbspGuiGeBean ggbean:songdashijianlist) {
                            if(dqId.equals(ggbean.getSpec_id())){
                                ggbean.setLevel(type);
                                this.dismiss();
                                EventBus.getDefault().post(ggbean);
                                return;
                            }
                        }
                        if(StringUtil.isValid(etCreateGuige.getText().toString())){
                            CreateGuigeBean bean = new CreateGuigeBean();
                            bean.setName(etCreateGuige.getText().toString());
                            bean.setLevel(type);
                            EventBus.getDefault().post(bean);
                            this.dismiss();
                        } else {
                            ToastUtil.showToastLong("请选择一个规格");
                        }

                    }else if(StringUtil.isValid(etCreateGuige.getText().toString())){
                        CreateGuigeBean bean = new CreateGuigeBean();
                        bean.setName(etCreateGuige.getText().toString());
                        bean.setLevel(type);
                        EventBus.getDefault().post(bean);
                        this.dismiss();
                    } else {
                        ToastUtil.showToastLong("请选择一个规格");
                    }
                }

                break;
        }
    }

    public void getData() {
        adapter = new GuigeAdapter(mContext, songdashijianlist);
        adapter.setXuanzhongid(dqId);
        adapter.setCallBack(new GuigeAdapter.CallBack() {
            @Override
            public void xuanzhong(FbspGuiGeBean msg) {
                adapter.setXuanzhongid(msg.getSpec_id());
                dqId = msg.getSpec_id();
                bean = msg;
                xztype = false;
            }
        });
        rvGuige.setLayoutManager(new GridLayoutManager(mContext, 2));
        rvGuige.setAdapter(adapter);

    }

    //获取规格
    private void getGuige() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getguige(PreferenceUtils.getString(MyApplication.mContext, "token", ""), ""))
                .setDataListener(new HttpDataListener<List<FbspGuiGeBean>>() {
                    @Override
                    public void onNext(List<FbspGuiGeBean> data) {
                        Log.e("data", new Gson().toJson(data) + "---");
                        for (FbspGuiGeBean bean:data) {
                            bean.setLevel(type);
                            songdashijianlist.add(bean);
                        }
//                        songdashijianlist.addAll(data);
                        adapter.notifyDataSetChanged();
                    }
                });

    }
}
