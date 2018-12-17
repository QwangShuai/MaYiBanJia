package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
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

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean;
import com.mingmen.mayi.mayibanjia.bean.FCGGuige;
import com.mingmen.mayi.mayibanjia.bean.FCGName;
import com.mingmen.mayi.mayibanjia.bean.FCGSaveFanHuiBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.FaCaiGouGuiGeAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.FaCaiGouMohuAdapter;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
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

public class CaiGouDanTianJiaDailog extends BaseFragmentDialog{
    @BindView(R.id.et_shangpin_ming)
    EditText etShangpinMing;
    @BindView(R.id.et_caigouliang)
    EditText etCaigouliang;
    @BindView(R.id.tv_guige)
    TextView tvGuige;
    @BindView(R.id.tv_xiangxia)
    ImageView tvXiangxia;
    @BindView(R.id.rl_guige)
    RelativeLayout rlGuige;
    @BindView(R.id.iv_teshu)
    ImageView ivTeshu;
    @BindView(R.id.ll_teshu)
    LinearLayout llTeshu;
    @BindView(R.id.et_teshu)
    EditText etTeshu;
    @BindView(R.id.iv_shanchuwenzi)
    ImageView ivShanchuwenzi;
    @BindView(R.id.tishi)
    TextView tishi;
    @BindView(R.id.rl_teshu)
    RelativeLayout rlTeshu;
    @BindView(R.id.bt_quxiao)
    Button btQuxiao;
    @BindView(R.id.bt_queren)
    Button btQueren;
    Unbinder unbinder;
    private String initStr;
    private String market_id;
    private CallBack mCallBack;
    private FaCaiGouMohuAdapter mohuAdapter;
    private RecyclerView rv_mohu;
    private PopupWindow mPopWindow;
    private ArrayList<FCGName> datas=new ArrayList<>();
    private String leibieid;
    private String sanjifenleiId="";
    private ArrayList<FCGGuige> guigedatas;
    private FaCaiGouGuiGeAdapter guigeadapter;
    private String pack_standard_id="";
    private String sanjifenleiName;
    private String teshuyaoqiu="";
    private String caigouliang="";
    private String guige;
    private PopupWindow guigePop;
    private RecyclerView rvguige;
    public CaiGouDanTianJiaDailog() {
    }

    public CaiGouDanTianJiaDailog setInitStr(String initStr,String market_id) {
        this.initStr = initStr;
        this.market_id = market_id;
        return this;
    }

    public CaiGouDanTianJiaDailog setCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
        return this;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_caigoudantianjia;
    }

    @Override
    protected void init() {
        etShangpinMing.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                getfcgname(s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getfcgname(s.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {
//                getfcgname(s.toString());
            }
        });

        etTeshu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                tishi.setText(s.length()+"/50");
//                if(s.length()>0){
//                    ivTeshu.setSelected(true);
//                } else {
//                    ivTeshu.setSelected(false);
//                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tishi.setText(s.toString().trim().length()+"/50");
                if(s.toString().trim().length()>0){
                    ivTeshu.setSelected(true);
                } else {
                    ivTeshu.setSelected(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
//                tishi.setText(s.length()+"/50");
//                if(s.length()>0){
//                    ivTeshu.setSelected(true);
//                } else {
//                    ivTeshu.setSelected(false);
//                }
            }
        });
    }

    @OnClick({R.id.bt_queren,R.id.bt_quxiao,R.id.iv_shanchuwenzi,R.id.rl_guige})
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.bt_queren:
                addXuQiu();
                break;
            case R.id.bt_quxiao:
                dismiss();
                break;
            case R.id.iv_shanchuwenzi:
                etTeshu.setText("");
                break;
            case R.id.rl_guige:
                if (guigedatas!=null&&guigedatas.size()>0){
                    showGuigePopupWindow();
                }else{
                    ToastUtil.showToast("请输入商品名，并选择相应的分类");
                }
                break;
        }
    }

    public interface CallBack {
        void confirm( CaiGouDanBean.FllistBean.SonorderlistBean msg);
    }

    //PopupWindow
    private void showPopupWindow() {
        View view = View.inflate(getActivity(), R.layout.pp_textview_recycleview, null);
        mPopWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        int height = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        mPopWindow.setWidth(width * 2 / 6);
        mPopWindow.setHeight(height * 2 / 9);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.showAsDropDown(etShangpinMing);
        rv_mohu = (RecyclerView) view.findViewById(R.id.rv_list);
        mohuAdapter = new FaCaiGouMohuAdapter(getActivity(), datas);
        rv_mohu.setAdapter(mohuAdapter);
        rv_mohu.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mohuAdapter.setOnItemClickListener(new FaCaiGouMohuAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                leibieid = datas.get(position).getClassify_id();
                Log.e("leibieid",leibieid+"--");
                etShangpinMing.setText(""+datas.get(position).getClassify_name());
                sanjifenleiName=datas.get(position).getClassify_name();
                sanjifenleiId=datas.get(position).getClassify_id();
                getfcgguige(sanjifenleiId);
                mPopWindow.dismiss();
            }
        });
    }
    private void getfcgguige(String sanjifenleiId) {
        huoqushuju();
        HttpManager.getInstance()
                .with(getActivity())
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getfcgguige(sanjifenleiId))
                .setDataListener(new HttpDataListener<List<FCGGuige>>() {
                    @Override
                    public void onNext(List<FCGGuige> data) {
                        guigedatas = new ArrayList<>();
                        guigedatas.addAll(data);
                        tvGuige.setText("");
                        pack_standard_id="";
                        if (guigeadapter!=null){
                            guigeadapter.setData(guigedatas);
                        }
                        if(guigedatas!=null&&guigedatas.size()!=0){
                            tvGuige.setText(guigedatas.get(0).getSpec_name());
                            pack_standard_id = guigedatas.get(0).getSpec_id();
                        }

                    }
                },false);
    }
    private void getfcgname(final String name) {
        if (name.equals(sanjifenleiName)){
            return;
        }
        Log.e("name",name+"---");
        HttpManager.getInstance()
                .with(getActivity())
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getfcgname(PreferenceUtils.getString(MyApplication.mContext, "token",""),name))
                .setDataListener(new HttpDataListener<List<FCGName>>() {
                    @Override
                    public void onNext(List<FCGName> data) {
                        datas=new ArrayList<FCGName>();
                        datas.addAll(data);
                        Log.e("data",data+"---");
                        if (mPopWindow!=null){
                            Log.e("data",data+"111111111");
                            if (name.equals(sanjifenleiName)){
                                return;
                            }
                            mPopWindow.showAsDropDown(etShangpinMing);
                            mohuAdapter.setData(datas);
                        }else{
                            showPopupWindow();
                        }

                    }
                },false);
    }
    //获取用户填写的数据
    private void huoqushuju() {
        sanjifenleiName=etShangpinMing.getText().toString().trim();
        caigouliang = etCaigouliang.getText().toString().trim();
        teshuyaoqiu = etTeshu.getText().toString().trim();
        guige = tvGuige.getText().toString().trim();
    }
    private void addXuQiu(){
        huoqushuju();
        HttpManager.getInstance()
                .with(getActivity())
                .setObservable(
                        RetrofitManager
                                .getService()
                                //user_token  是否是特殊商品不是0 是1    如果是特殊商品 填写要求   市场id  类别id  产品数量
                                .addfcg(PreferenceUtils.getString(MyApplication.mContext, "token",""),"".equals(teshuyaoqiu)?teshuyaoqiu:teshuyaoqiu,
                                        market_id,sanjifenleiId,initStr,"",pack_standard_id,"",caigouliang+""))
                .setDataListener(new HttpDataListener<CaiGouDanBean.FllistBean.SonorderlistBean>() {
                    @Override
                    public void onNext(CaiGouDanBean.FllistBean.SonorderlistBean data) {
                        dismiss();
                        mCallBack.confirm(data);
                    }
                });
    }
    private void showGuigePopupWindow() {
        View view = View.inflate(getActivity(), R.layout.pp_textview_recycleview, null);
        guigePop = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        int width = getWindowManager().getDefaultDisplay().getWidth();
        int height = getActivity().getWindowManager().getDefaultDisplay().getHeight();

        guigePop.setWidth(rlGuige.getWidth());
        guigePop.setHeight(height * 2 / 9);
        guigePop.setOutsideTouchable(true);
        guigePop.setBackgroundDrawable(new BitmapDrawable());
        guigePop.showAsDropDown(rlGuige);
        rvguige = (RecyclerView) view.findViewById(R.id.rv_list);

        guigeadapter = new FaCaiGouGuiGeAdapter(getActivity(), guigedatas);
        rvguige.setAdapter(guigeadapter);
        rvguige.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        guigeadapter.setOnItemClickListener(new FaCaiGouGuiGeAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                tvGuige.setText(guigedatas.get(position).getSpec_name());
                pack_standard_id = guigedatas.get(position).getSpec_id();
//                leibieid = datas.get(position).getClassify_id();
//                Log.e("leibieid",leibieid+"--");
//                etShangpinMing.setText(""+datas.get(position).getClassify_name());
//                sanjifenleiName=datas.get(position).getClassify_name();
//                sanjifenleiId=datas.get(position).getClassify_id();
//                getfcgguige(sanjifenleiId);
                guigePop.dismiss();
            }
        });
    }
}
