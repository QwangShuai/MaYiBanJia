package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.ProvinceBean;
import com.mingmen.mayi.mayibanjia.bean.ShiChangBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShangQuanRightAdapter;
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

/**
 * Created by Administrator on 2018/10/19.
 */

public class ShangquanRightDialog extends BaseFragmentDialog implements View.OnClickListener {


    Unbinder unbinder;
    @BindView(R.id.rv_guige)
    RecyclerView rvGuige;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    @BindView(R.id.ll)
    LinearLayout ll;
    private ShangQuanRightAdapter adapter;
    private int dqId;
    private String quid;
    private Context mContext;
    private List<ProvinceBean> songdashijianlist = new ArrayList<ProvinceBean>();
    private ProvinceBean bean = new ProvinceBean();

    public ShangquanRightDialog setData(Context mContext) {
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

    public void setDqId(int dqId){
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
                if (dqId!=0) {
                    this.dismiss();
//                    bean.se(dqId + " / " + bean.getSon_name());
                    EventBus.getDefault().post(bean);
                } else {
                    ToastUtil.showToastLong("请选择一个商圈");
                }
                break;
        }
    }

    public void getData() {
        adapter = new ShangQuanRightAdapter(mContext, songdashijianlist);
        adapter.setXuanzhongid(dqId);
        adapter.setCallBack(new ShangQuanRightAdapter.CallBack() {
            @Override
            public void xuanzhong(ProvinceBean msg) {
                adapter.setXuanzhongid(msg.getQuybm());
                dqId = msg.getQuybm();
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
                                .getsheng(PreferenceUtils.getString(MyApplication.mContext, "token", ""), quid + ""))
                .setDataListener(new HttpDataListener<List<ProvinceBean>>() {
                    @Override
                    public void onNext(final List<ProvinceBean> list) {
                        songdashijianlist.addAll(list);
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
