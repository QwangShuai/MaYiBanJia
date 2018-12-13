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
import com.mingmen.mayi.mayibanjia.bean.FCGSaveFanHuiBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.FaCaiGouGuiGeAdapter;
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

public class CaiGouDanXiuGaiDailog extends BaseFragmentDialog{

    @BindView(R.id.tv_shangpinming)
    TextView tvShangpinming;
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
    @BindView(R.id.tishi)
    TextView tishi;
    @BindView(R.id.rl_teshu)
    RelativeLayout rlTeshu;
    private String initStr;
    private String guige;
    private String guigeID;
    private String yaoqiu;
    private String son_order_id;
    private String count;
    private String sanjifenleiId;
    private CallBack mCallBack;
    private ArrayList<FCGGuige> guigedatas;
    private FaCaiGouGuiGeAdapter guigeadapter;
    private String pack_standard_id="";
    private PopupWindow guigePop;
    private RecyclerView rvguige;
    public CaiGouDanXiuGaiDailog() {
    }

    public CaiGouDanXiuGaiDailog setInitStr(String initStr,String guige,String guigeID,String yaoqiu,
                                            String count,String son_order_id,String sanjifenleiId) {
        this.initStr = initStr;
        this.guige = guige;
        this.guigeID = guigeID;
        this.yaoqiu = yaoqiu;
        this.son_order_id = son_order_id;
        this.count = count;
        this.sanjifenleiId = sanjifenleiId;
        return this;
    }

    public CaiGouDanXiuGaiDailog setCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
        return this;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_caigoudanxiugai;
    }

    @Override
    protected void init() {
        tvShangpinming.setText(initStr);
        etCaigouliang.setText(count);
        tvGuige.setText(guige);
        if(!TextUtils.isEmpty(yaoqiu)){
            etTeshu.setText(yaoqiu);
            ivTeshu.setSelected(true);
            tishi.setText(yaoqiu.length()+"/50");
        }
        etTeshu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
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
            }
        });
        getfcgguige(sanjifenleiId);
    }

    @OnClick({R.id.bt_queren,R.id.bt_quxiao,R.id.iv_shanchuwenzi,R.id.rl_guige})
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.bt_queren:
                editorXuQiu();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public interface CallBack {
        void confirm(CaiGouDanBean.FllistBean.SonorderlistBean msg);
    }
    //获取用户填写的数据
    private void huoqushuju() {
        count = etCaigouliang.getText().toString().trim();
        yaoqiu = etTeshu.getText().toString().trim();
//        guige = tvGuige.getText().toString().trim();
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
//                        tvGuige.setText("");
//                        pack_standard_id="";
                        if (guigeadapter!=null){
                            guigeadapter.setData(guigedatas);
                        }
                        Log.e("data",data+"---");

                    }
                },false);
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
                guigePop.dismiss();
            }
        });
    }

    public void editorXuQiu(){
        huoqushuju();
        HttpManager.getInstance()
                .with(getActivity())
                .setObservable(
                        RetrofitManager
                                .getService()
                                //user_token  是否是特殊商品不是0 是1    如果是特殊商品 填写要求   市场id  类别id  产品数量
                                .editorXuqiudan(count,PreferenceUtils.getString(MyApplication.mContext, "token",""),son_order_id,
                                        yaoqiu,pack_standard_id))
                .setDataListener(new HttpDataListener<CaiGouDanBean.FllistBean.SonorderlistBean>() {
                    @Override
                    public void onNext(CaiGouDanBean.FllistBean.SonorderlistBean data) {
                        dismiss();
                        mCallBack.confirm(data);
                    }
                });
    }
}
