package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CreateGuigeBean;
import com.mingmen.mayi.mayibanjia.bean.FbspGuiGeBean;
import com.mingmen.mayi.mayibanjia.bean.QiYeLeiBieBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.GuigeAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.QiyeleibieAdapter;
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

public class QiyeleibieDialog extends BaseFragmentDialog implements View.OnClickListener {


    Unbinder unbinder;
    @BindView(R.id.rv_guige)
    RecyclerView rvGuige;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    @BindView(R.id.ll)
    LinearLayout ll;
    private QiyeleibieAdapter adapter;
    private String dqId;
    private Context mContext;
    private List<QiYeLeiBieBean> songdashijianlist = new ArrayList<QiYeLeiBieBean>();
    private QiYeLeiBieBean bean = new QiYeLeiBieBean();
    private int type = 0;

    public QiyeleibieDialog setData(Context mContext) {
        this.mContext = mContext;
        return this;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_guige;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL,R.style.dialog_soft_input);
    }

    @Override
    protected void init() {
        getData();
        if(type==0){
            getGuige();
        } else {
            getQylbGy();
        }

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
                    if (StringUtil.isValid(dqId)&&StringUtil.isValid(bean.getSon_number())) {
                        Log.e( "onClick: ",new Gson().toJson(bean) );
                        this.dismiss();
                        EventBus.getDefault().post(bean);
                    }else {
                        ToastUtil.showToastLong("请选择一个规格");
                    }

                break;
        }
    }

    public void getData() {
        adapter = new QiyeleibieAdapter(mContext, songdashijianlist);
        adapter.setXuanzhongid(dqId);
        adapter.setCallBack(new QiyeleibieAdapter.CallBack() {
            @Override
            public void xuanzhong(QiYeLeiBieBean msg) {
                adapter.setXuanzhongid(msg.getSon_number());
                dqId = msg.getSon_number();
                bean = msg;
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
                                .getqylb(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<List<QiYeLeiBieBean>>() {
                    @Override
                    public void onNext(List<QiYeLeiBieBean> data) {
                        Log.e("data", new Gson().toJson(data) + "---");
                        for (QiYeLeiBieBean bean:data) {
                            songdashijianlist.add(bean);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });

    }
    //获取规格
    private void getQylbGy() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getqylbGhd(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<List<QiYeLeiBieBean>>() {
                    @Override
                    public void onNext(List<QiYeLeiBieBean> data) {
                        Log.e("data", new Gson().toJson(data) + "---");
                        for (QiYeLeiBieBean bean:data) {
                            songdashijianlist.add(bean);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });

    }
}
