package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean;
import com.mingmen.mayi.mayibanjia.bean.CaiGouMingChengBean;
import com.mingmen.mayi.mayibanjia.bean.FCGGuige;
import com.mingmen.mayi.mayibanjia.bean.FCGName;
import com.mingmen.mayi.mayibanjia.bean.ShangPinBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.CaiGouMingChengAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.FaCaiGouGuiGeAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.FaCaiGouMohuAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.XuanZeZhuBiaoAdapter;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/8/15.
 */

public class XuanZeZhuBiaoDailog extends BaseFragmentDialog {
    @BindView(R.id.rv_qiangdandialog)
    RecyclerView rvQiangdandialog;
    @BindView(R.id.bt_quxiao)
    Button btQuxiao;
    @BindView(R.id.bt_queren)
    Button btQueren;
    Unbinder unbinder;
    private CallBack mCallBack;
    private XuanZeZhuBiaoAdapter adapter;
    private String id = "";
    private String p_id = "";
    private String p_name = "";
    private RecyclerView rv_cgmc;
    private PopupWindow cgmcPop;
    private List<CaiGouMingChengBean> list = new ArrayList<>();
    private CaiGouMingChengBean xuanzhongitem;

    public XuanZeZhuBiaoDailog() {
    }

    public XuanZeZhuBiaoDailog setInitStr(String id) {
        this.id = id;
        return this;
    }

    public XuanZeZhuBiaoDailog setCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
        return this;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_xuanzezhubiao;
    }

    @Override
    protected void init() {
        adapter = new XuanZeZhuBiaoAdapter();
        adapter.setNewData(list);
        rvQiangdandialog.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvQiangdandialog.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter1, View view, int position) {
                switch (view.getId()){
                    case R.id.ll_kuang:
                        CaiGouMingChengBean item =(CaiGouMingChengBean) adapter.getItem(position);
                        adapter.setXuanzhong(item.getPurchase_id());
                        p_id = list.get(position).getPurchase_id();
                        p_name = list.get(position).getPurchase_name();
                        xuanzhongitem=item;
                        break;
                }

            }
        });
        getCgmc();
    }

    @OnClick({R.id.bt_queren, R.id.bt_quxiao})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.bt_queren:
                dismiss();
                mCallBack.setPurchase(p_id,p_name);
                break;
            case R.id.bt_quxiao:
                dismiss();
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface CallBack {
        void setPurchase(String id, String name);
    }

    private void getCgmc() {
        HttpManager.getInstance()
                .with(getActivity())
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getCgmc(PreferenceUtils.getString(MyApplication.mContext, "token", ""),id))
                .setDataListener(new HttpDataListener<List<CaiGouMingChengBean>>() {
                    @Override
                    public void onNext(List<CaiGouMingChengBean> data) {
                        list.clear();
                        list.addAll(data);
                        adapter.setNewData(list);
                        adapter.notifyDataSetChanged();
                    }
                }, false);
    }
}
