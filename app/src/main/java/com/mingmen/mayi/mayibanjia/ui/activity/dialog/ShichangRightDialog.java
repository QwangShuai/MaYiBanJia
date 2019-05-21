package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.FbspGuiGeBean;
import com.mingmen.mayi.mayibanjia.bean.ShiChangBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.XinXiLuRuGHDActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.GuigeAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShichangRightAdapter;
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
import cn.qqtheme.framework.picker.SinglePicker;

/**
 * Created by Administrator on 2018/10/19.
 */

public class ShichangRightDialog extends BaseFragmentDialog implements View.OnClickListener {


    Unbinder unbinder;
    @BindView(R.id.rv_guige)
    RecyclerView rvGuige;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    @BindView(R.id.ll)
    LinearLayout ll;
    private ShichangRightAdapter adapter;
    private String dqId;
    private String quid;
    private Context mContext;
    private List<ShiChangBean> songdashijianlist = new ArrayList<ShiChangBean>();
    private ShiChangBean bean = new ShiChangBean();

    public ShichangRightDialog setData(Context mContext) {
        this.mContext = mContext;
        return this;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_guige;
    }

    @Override
    protected void init() {
        getData();
//        getshichang();
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

    public void setDqId(String dqId){
        this.dqId = dqId;
    }
    public void setQuid(String quid){
        this.quid = quid;
        getshichang();
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
                if (StringUtil.isValid(dqId)) {
                    this.dismiss();
//                    bean.se(dqId + " / " + bean.getSon_name());
                    EventBus.getDefault().post(bean);
                } else {
                    ToastUtil.showToastLong("请选择一个市场");
                }
                break;
        }
    }

    public void getData() {
        adapter = new ShichangRightAdapter(mContext, songdashijianlist);
        adapter.setXuanzhongid(dqId);
        adapter.setCallBack(new ShichangRightAdapter.CallBack() {
            @Override
            public void xuanzhong(ShiChangBean msg) {
                adapter.setXuanzhongid(msg.getMark_id());
                dqId = msg.getMark_id();
                bean = msg;
            }
        });
        rvGuige.setLayoutManager(new GridLayoutManager(mContext, 2));
        rvGuige.setAdapter(adapter);

    }
    private void getshichang() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getshichang(PreferenceUtils.getString(MyApplication.mContext, "token", ""), quid + ""))
                .setDataListener(new HttpDataListener<List<ShiChangBean>>() {
                    @Override
                    public void onNext(List<ShiChangBean> list) {
                        songdashijianlist.addAll(list);
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
