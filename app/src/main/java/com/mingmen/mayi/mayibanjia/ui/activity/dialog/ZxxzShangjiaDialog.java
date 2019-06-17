package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.XiTongTuiJianBean;
import com.mingmen.mayi.mayibanjia.bean.ZxxzQiyeBean;
import com.mingmen.mayi.mayibanjia.bean.ZxxzShangpinBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.ShenPiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.GengDuoShangJiaAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ZxxzDianPuMohuAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ZxxzPinpaiAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ZxxzShangPinListAdapter;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/17.
 */

public class ZxxzShangjiaDialog extends BaseFragmentDialog {

    @BindView(R.id.et_sousuozi)
    EditText etSousuozi;
    @BindView(R.id.ll_sousuo)
    RelativeLayout llSousuo;
    @BindView(R.id.ll_pinpai)
    LinearLayout llPinpai;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.rv_pp_list)
    RecyclerView rvPpList;
    @BindView(R.id.iv_sousuo)
    ImageView ivSousuo;
    @BindView(R.id.tv_pinpai)
    TextView tvPinpai;

    private ShenPiActivity activity;
    private String son_order_id = "";
    private String market_id = "";
    private String company_id = "";
    private String company_name = "";
    private String brand = "";

    private List<ZxxzQiyeBean> qyList = new ArrayList<>();
    private List<ZxxzQiyeBean> ppList = new ArrayList<>();
    private List<ZxxzShangpinBean> spList = new ArrayList<>();

    private RecyclerView rv_mohu;
    private PopupWindow mPopWindow;
    private ZxxzDianPuMohuAdapter mohuAdapter;
    private ZxxzPinpaiAdapter pinpaiAdapter;
    private ZxxzShangPinListAdapter shangpinAdapter;


    private CallBack mCallBack;


    public ZxxzShangjiaDialog() {

    }
    public ZxxzShangjiaDialog setId(ShenPiActivity activity, String son_order_id, String maket_id) {
        this.activity = activity;
        this.son_order_id=son_order_id;
        this.market_id=maket_id;
      return this;
    }
    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_zxxz;
    }

    @Override
    protected void init() {

        shangpinAdapter = new ZxxzShangPinListAdapter(activity, spList);
        rvList.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        rvList.setAdapter(shangpinAdapter);
        pinpaiAdapter = new ZxxzPinpaiAdapter(activity, ppList);
        rvPpList.setLayoutManager(new GridLayoutManager(activity, 3));
        rvPpList.setAdapter(pinpaiAdapter);
        etSousuozi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (StringUtil.isValid(s.toString().trim())) {
                    if(!company_name.equals(s.toString().trim())){
                        ivSousuo.setVisibility(View.GONE);
                        mohudianpu(s.toString().trim());
                    }
                } else {
                    ivSousuo.setVisibility(View.VISIBLE);
                    if (mPopWindow != null && mPopWindow.isShowing()) {
                        mPopWindow.dismiss();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        pinpaiAdapter.setCallBack(new ZxxzPinpaiAdapter.CallBack() {
            @Override
            public void xuanzhong(ZxxzQiyeBean msg) {
                brand = msg.getBrand();
                tvPinpai.setText(msg.getBrand());
                rvList.setVisibility(View.VISIBLE);
                rvPpList.setVisibility(View.GONE);
                getShangpinList();
            }
        });


        shangpinAdapter.setOnItemClickListener(new ZxxzShangPinListAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                XiTongTuiJianBean.CcListBean item =new XiTongTuiJianBean.CcListBean();
                item.setPack_standard(spList.get(position).getPack_standard());
                item.setStar_evaluation(Float.valueOf(spList.get(position).getStar_evaluation()));
                item.setClassify_name(spList.get(position).getClassify_name());
                item.setPrice(spList.get(position).getPrice());
                item.setCommodity_id(spList.get(position).getCommodity_id());
                item.setMarket_id(market_id);
                item.setCompany_name(spList.get(position).getCompany_name());
                item.setSon_order_id(son_order_id);
                item.setCompany_id(spList.get(position).getCompany_id());
                item.setCommodity_sales(spList.get(position).getCommodity_sales());
                item.setPicture_url(spList.get(position).getPicture_url());
                item.setCount(spList.get(position).getCount());
//                EventBus.getDefault().post(spList.get(position));
                mCallBack.xuanzhong(item);
                dismiss();
            }
        });
        mohudianpu("");
        getShangpinList();

    }
    public ZxxzShangjiaDialog setCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
        return this;
    }

    //店铺模糊搜索
    private void mohudianpu(final String name) {
        HttpManager.getInstance()
                .with(activity)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .zxxzQiye(PreferenceUtils.getString(MyApplication.mContext, "token", ""), son_order_id, market_id, name))
                .setDataListener(new HttpDataListener<List<ZxxzQiyeBean>>() {
                    @Override
                    public void onNext(List<ZxxzQiyeBean> list) {
                        if (StringUtil.isValid(name)) {
                            qyList.clear();
                            if (mohuAdapter != null) {
                                mohuAdapter.notifyDataSetChanged();
                            }
                            qyList.addAll(list);
                            Log.e("data", list + "---");
                            if (mPopWindow != null) {
                                mPopWindow.showAsDropDown(etSousuozi);
                                mohuAdapter.notifyDataSetChanged();
                            } else {
                                showPopupWindow();
                            }
                        } else {
                            ppList.clear();
                            pinpaiAdapter.notifyDataSetChanged();
                            ppList.addAll(list);
                            pinpaiAdapter.notifyDataSetChanged();

                        }
                    }
                },false);
    }

    //获取商品列表
    private void getShangpinList() {
        HttpManager.getInstance()
                .with(activity)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .zxxzShangpin(PreferenceUtils.getString(MyApplication.mContext, "token", ""), son_order_id, market_id, company_id, brand))
                .setDataListener(new HttpDataListener<List<ZxxzShangpinBean>>() {
                    @Override
                    public void onNext(List<ZxxzShangpinBean> list) {
                        spList.clear();
                        shangpinAdapter.notifyDataSetChanged();
                        spList.addAll(list);
                        shangpinAdapter.notifyDataSetChanged();
                    }
                });
    }

    //PopupWindow
    private void showPopupWindow() {
        View view = View.inflate(activity, R.layout.pp_textview_recycleview, null);
        mPopWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = activity.getWindowManager().getDefaultDisplay().getHeight();
        mPopWindow.setWidth(width * 2 / 6);
        mPopWindow.setHeight(height * 2 / 9);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.showAsDropDown(etSousuozi);
        rv_mohu = (RecyclerView) view.findViewById(R.id.rv_list);
        mohuAdapter = new ZxxzDianPuMohuAdapter(activity, qyList);
        rv_mohu.setAdapter(mohuAdapter);
        rv_mohu.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        mohuAdapter.setOnItemClickListener(new ZxxzDianPuMohuAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                company_id = qyList.get(position).getCompany_id();
                company_name = qyList.get(position).getCompany_name();
                etSousuozi.setText(qyList.get(position).getCompany_name() + "");
                getShangpinList();
                mPopWindow.dismiss();
            }
        });
    }

    @OnClick({R.id.iv_sousuo, R.id.ll_sousuo, R.id.ll_pinpai})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_sousuo:
                break;
            case R.id.ll_sousuo:
                break;
            case R.id.ll_pinpai:
                if(rvPpList.getVisibility()==View.VISIBLE?true:false){
                    rvPpList.setVisibility(View.GONE);
                    rvList.setVisibility(View.VISIBLE);
                } else {
                    rvList.setVisibility(View.GONE);
                    rvPpList.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    protected void initConfiguration(Configuration configuration){
        configuration.fullScreen();
    }

    public interface CallBack{
        void xuanzhong(XiTongTuiJianBean.CcListBean msg);
    }
}
