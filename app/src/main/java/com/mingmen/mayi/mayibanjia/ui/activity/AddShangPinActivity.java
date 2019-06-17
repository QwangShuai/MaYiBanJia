package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.app.UMConfig;
import com.mingmen.mayi.mayibanjia.bean.AddSpListBean;
import com.mingmen.mayi.mayibanjia.bean.FCGName;
import com.mingmen.mayi.mayibanjia.bean.ShangPinSousuoMohuBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.AddSpFourAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.XJSPFeiLeiGuigeAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.CaiGouDanTianJiaDailog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddShangPinActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_sousuo)
    EditText etSousuo;
    @BindView(R.id.ll_sousuo)
    LinearLayout llSousuo;
    @BindView(R.id.tv_quxiao)
    TextView tvQuxiao;
    @BindView(R.id.tv_lable)
    TextView tvLable;
    @BindView(R.id.tv_pinlei)
    TextView tvPinlei;
    @BindView(R.id.ll_pinlei)
    LinearLayout llPinlei;
    @BindView(R.id.tv_pinzhong_lable)
    TextView tvPinzhongLable;
    @BindView(R.id.tv_pinzhong)
    TextView tvPinzhong;
    @BindView(R.id.ll_pinzhong)
    LinearLayout llPinzhong;
    @BindView(R.id.btn_queren)
    Button btnQueren;
    @BindView(R.id.rv_yijifenlei)
    RecyclerView rvYijifenlei;
    @BindView(R.id.tv_shangpin_lable)
    TextView tvShangpinLable;
    @BindView(R.id.tv_shangpin)
    TextView tvShangpin;
    @BindView(R.id.ll_shangpin)
    LinearLayout llShangpin;
    @BindView(R.id.tv_guige_lable)
    TextView tvGuigeLable;
    @BindView(R.id.tv_guige)
    TextView tvGuige;
    @BindView(R.id.ll_guige)
    LinearLayout llGuige;
    @BindView(R.id.rv_guige)
    RecyclerView rvGuige;

    private ArrayList<FCGName> yijiFenLei = new ArrayList<>();
    private AddSpFourAdapter adapter;
    private Context mContext;
    private GridLayoutManager manager;
    private String yclId = "346926195929448587b078e7fe613530 ";
    private String oneid;
    private String twoid;
//    private Map<String, ShangPinSousuoMohuBean> map = new HashMap<>();
    private String mytype = "2";
    private List<AddSpListBean> list = new ArrayList<>();
    private String id = "";
    private XJSPFeiLeiGuigeAdapter guigeadapter;
    private List<ShangPinSousuoMohuBean> datas = new ArrayList<>();
    private String twoName;
    private String threeName;
    private String fourid;
    private String fourName;
    private String fiveName;
    private String threeid;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_shang_pin;
    }

    @Override
    protected void initData() {
        mContext = AddShangPinActivity.this;
        id = getIntent().getStringExtra("id");
        btnQueren.setVisibility(View.GONE);
        adapter = new AddSpFourAdapter(mContext, yijiFenLei);
        adapter.setActivity(AddShangPinActivity.this);
        setMyManager();
        bindAdapter();
        getShouyeFenLei(yclId, "2");
        etSousuo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (etSousuo.getText().toString().trim().length() == 0) {
                        ToastUtil.showToastLong("请输入要搜索的商品");
                    } else {
                        setViewShowClear();
                        getfcgname(etSousuo.getText().toString().trim());
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_quxiao, R.id.ll_pinlei, R.id.ll_pinzhong, R.id.btn_queren,
            R.id.ll_shangpin, R.id.ll_guige})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                myBack();
                break;
            case R.id.tv_quxiao:
                setViewShowClear();
                getfcgname(etSousuo.getText().toString());
                break;
            case R.id.ll_pinlei:
                if(StringUtil.isValid(yclId)){
                    getTwo();
                } else {
                    ToastUtil.showToastLong("请选择商品分类");
                }
                break;
            case R.id.ll_pinzhong:
                if(StringUtil.isValid(oneid)){
                    getThree();
                } else {
                    ToastUtil.showToastLong("请选择商品品类");
                }
                break;
            case R.id.btn_queren:
                addSpList();
                break;
            case R.id.ll_shangpin:
                if (StringUtil.isValid(twoid)) {
                    getFour();
                } else {
                    ToastUtil.showToastLong("请选择商品品类");
                }
                break;
            case R.id.ll_guige:
                if (StringUtil.isValid(fourName)) {
                    getGuige();
                } else {
                    ToastUtil.showToastLong("请先选择商品名称");
                }
                break;
        }
    }

    private void getShouyeFenLei(String id, final String type) {
        yijiFenLei.clear();
        adapter.notifyDataSetChanged();
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getFeiLei(PreferenceUtils.getString(MyApplication.mContext, "token", ""), id, type))
                .setDataListener(new HttpDataListener<List<FCGName>>() {

                    @Override
                    public void onNext(List<FCGName> list) {
                        int mysize = list == null ? 0 : list.size();
                        if (mysize != 0) {
//                            if (mytype.equals("4")) {
//                                adapter.setType(mytype);
//                                adapter.setActivity(AddShangPinActivity.this);
//                            }
                            yijiFenLei.clear();
                            yijiFenLei.addAll(list);
                            adapter.notifyDataSetChanged();
                        } else {
                            ToastUtil.showToastLong("当前类别暂无品类");
                        }
                    }
                }, false);
    }

    private void bindAdapter() {
        rvYijifenlei.setLayoutManager(manager);
        rvYijifenlei.setAdapter(adapter);
        adapter.setCallBack(new AddSpFourAdapter.CallBack() {
            @Override
            public void xuanzhong(FCGName msg) {
                if (mytype.equals("2")) {
                    oneid = msg.getClassify_id();
                    adapter.setXuanzhongid(oneid);
                    tvPinlei.setText(msg.getClassify_name());
                    getThree();
                } else if (mytype.equals("3")) {
                    twoid = msg.getClassify_id();
                    adapter.setXuanzhongid(oneid);
                    tvPinzhong.setText(msg.getClassify_name());
                    getFour();
                } else if (mytype.equals("4")) {
                    if (StringUtil.isValid(msg.getOne_classify_id())) {
                        oneid = msg.getTwo_classify_id();
                        twoid = msg.getThree_classify_id();
                        twoName = msg.getTwo_classify_name();
                        threeName = msg.getThree_classify_name();
                        tvPinlei.setText(msg.getTwo_classify_name());
                        tvPinzhong.setText(msg.getThree_classify_name());
                    }
                    threeid = msg.getClassify_id();
                    Log.e("xuanzhong: ", threeid + "+++");
                    fourName = msg.getClassify_name();
                    adapter.setXuanzhongid(threeid);
                    tvShangpin.setText(msg.getClassify_name());
                    getGuige();
                }

                adapter.notifyDataSetChanged();

            }
        });
        guigeadapter = new XJSPFeiLeiGuigeAdapter(mContext, datas);
        rvGuige.setLayoutManager(new GridLayoutManager(mContext, 3));
        rvGuige.setAdapter(guigeadapter);
        guigeadapter.setCallBack(new XJSPFeiLeiGuigeAdapter.CallBack() {
            @Override
            public void xuanzhong(final ShangPinSousuoMohuBean msg) {
                list.clear();
                Log.e( "xuanzhong: ",fourName );
                CaiGouDanTianJiaDailog dailog = new CaiGouDanTianJiaDailog();
                dailog.setDate(fourName,msg.getSpec_name());
                dailog.setCallBack(new CaiGouDanTianJiaDailog.CallBack() {
                    @Override
                    public void confirm(String count, String tsyq) {
                        AddSpListBean addSpListBean = new AddSpListBean();
                        addSpListBean.setClassify_id(oneid);
                        addSpListBean.setCount(count);
                        addSpListBean.setSpecial_commodity(tsyq);
                        addSpListBean.setPack_standard_id(msg.getSpec_idFour());
                        fourid = msg.getClassify_id();
                        fiveName = msg.getClassify_name();
                        addSpListBean.setSort_id(msg.getClassify_id());
                        list.add(addSpListBean);
                        guigeadapter.setXuanzhongid(msg.getClassify_id());
                        tvGuige.setText(msg.getClassify_name());
                        guigeadapter.notifyDataSetChanged();
                        addSpList();
                    }
                });
                dailog.show(getSupportFragmentManager());

            }
        });
    }

    private void setMyManager() {
        manager = new GridLayoutManager(mContext, 3);

//        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                if (position == 0) {
//                    return 3;
//                } else {
//                    return 1;
//                }
//            }
//        });
    }

    private void getThree() {
        if (rvGuige.getVisibility() == View.VISIBLE) {
            rvGuige.setVisibility(View.GONE);
            rvYijifenlei.setVisibility(View.VISIBLE);
        }
//        tvPinzhongLable.setTextColor(mContext.getResources().getColor(R.color.zangqing));
//        tvLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
//        tvShangpinLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
//        tvGuigeLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
//        mytype = "3";
//        adapter.setXuanzhongid(twoid);
//        getShouyeFenLei(oneid, "3");

        threeid = "";
        fourid = "";
        tvShangpin.setText("全部");
        tvGuige.setText("全部");

        tvShangpinLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        tvPinzhongLable.setTextColor(mContext.getResources().getColor(R.color.zangqing));
        tvLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        tvGuigeLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        mytype = "3";
        adapter.setXuanzhongid(twoid);
        getShouyeFenLei(oneid, "3");
    }

    private void getTwo() {
        if (rvGuige.getVisibility() == View.VISIBLE) {
            rvGuige.setVisibility(View.GONE);
            rvYijifenlei.setVisibility(View.VISIBLE);
        }
        twoid = "";
        threeid = "";
        fourid = "";
        tvPinzhong.setText("全部");
        tvShangpin.setText("全部");
        tvGuige.setText("全部");
        tvLable.setTextColor(mContext.getResources().getColor(R.color.zangqing));
        tvPinzhongLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        tvShangpinLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        tvGuigeLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        mytype = "2";
        adapter.setXuanzhongid(oneid);
        getShouyeFenLei(yclId, "2");
//        tvLable.setTextColor(mContext.getResources().getColor(R.color.zangqing));
//        tvPinzhongLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
//        tvShangpinLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
//        tvGuigeLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
//        mytype = "2";
//        adapter.setXuanzhongid(oneid);
//        getShouyeFenLei(yclId, "2");
    }

    private void getFour() {
        if (rvGuige.getVisibility() == View.VISIBLE) {
            rvGuige.setVisibility(View.GONE);
            rvYijifenlei.setVisibility(View.VISIBLE);
        }

//        tvPinzhongLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
//        tvLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
//        tvShangpinLable.setTextColor(mContext.getResources().getColor(R.color.zangqing));
//        tvGuigeLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
//        mytype = "4";
//        adapter.setXuanzhongid(threeid);
//        getShouyeFenLei(twoid, "4");

        fourid = "";
        tvGuige.setText("全部");
        tvPinzhongLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        tvLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        tvShangpinLable.setTextColor(mContext.getResources().getColor(R.color.zangqing));
        tvGuigeLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        mytype = "4";
        adapter.setXuanzhongid(threeid);
        getShouyeFenLei(twoid, "4");
    }

//    public void onChangeMap(ShangPinSousuoMohuBean mybean, boolean b) {
//        count = 0;
//        list.clear();
//        if (b) {
//            map.put(mybean.getClassify_id(), mybean);
//        } else {
//            map.remove(mybean.getClassify_id());
//        }
//        for (ShangPinSousuoMohuBean bean : map.values()) {
//            AddSpListBean addSpListBean = new AddSpListBean();
//            if (StringUtil.isValid(bean.getClassify_id())) {
//                addSpListBean.setClassify_id(bean.getClassify_id());
//            } else {
//                addSpListBean.setClassify_id(oneid);
//            }
//
//            addSpListBean.setPack_standard_id(bean.getSpec_idFour());
//            addSpListBean.setSort_id(threeid);
//            list.add(addSpListBean);
//            count++;
//            Log.e("zzzzz", count + "------");
//        }
//    }

    private void getfcgname(final String name) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getFourSp(PreferenceUtils.getString(MyApplication.mContext, "token", ""), name, UMConfig.YCL_ID))
                .setDataListener(new HttpDataListener<List<FCGName>>() {
                    @Override
                    public void onNext(List<FCGName> list) {
                        int mysize = list == null ? 0 : list.size();
                        if (mysize != 0) {
//                            if (mytype.equals("4")) {
//                                adapter.setType(mytype);
//                            }
                            yijiFenLei.clear();
                            yijiFenLei.addAll(list);
                            adapter.notifyDataSetChanged();
                        } else {
                            ToastUtil.showToastLong("当前类别暂无品类");
                        }

                    }
                }, false);
    }

    private void setViewShowClear() {
        if (rvGuige.getVisibility() == View.VISIBLE) {
            rvGuige.setVisibility(View.GONE);
            rvYijifenlei.setVisibility(View.VISIBLE);
        }
        oneid = "";
        twoid = "";
        threeid = "";
        fourid = "";
        tvLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        tvPinzhongLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        tvShangpinLable.setTextColor(mContext.getResources().getColor(R.color.zangqing));
        tvGuigeLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        adapter.setXuanzhongid("");
        adapter.notifyDataSetChanged();
        guigeadapter.setXuanzhongid("");
        guigeadapter.notifyDataSetChanged();
//        map.clear();
        tvPinlei.setText("全部");
        tvPinzhong.setText("全部");
        tvShangpin.setText("全部");
        tvGuige.setText("全部");
        yijiFenLei.clear();
        mytype = "4";

    }

    public void addSpList() {
        if (StringUtil.isValid(fourid)) {
            String sort_id = new Gson().toJson(list);
            HttpManager.getInstance()
                    .with(mContext)
                    .setObservable(RetrofitManager.getService()
                            .addSpList(PreferenceUtils.getString(MyApplication.mContext, "token", ""), sort_id, getIntent().getStringExtra("name"), id))
                    .setDataListener(new HttpDataListener<String>() {
                        @Override
                        public void onNext(String data) {
                            id = data;
                            Log.e("myId", id);
                            ToastUtil.showToastLong("添加成功");
                            myBack();
                        }
                    });
        } else {
            ToastUtil.showToastLong("请至少选择一项商品");
        }
    }

    private void myBack() {
        Intent it = new Intent();
        it.putExtra("id", id);
        setResult(2, it);
        finish();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        myBack();
    }

    private void getGuige() {
        if (rvGuige.getVisibility() == View.GONE) {
            rvYijifenlei.setVisibility(View.GONE);
            rvGuige.setVisibility(View.VISIBLE);
        }
        tvPinzhongLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        tvLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        tvShangpinLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        tvGuigeLable.setTextColor(mContext.getResources().getColor(R.color.zangqing));
        datas.clear();
        Log.e("getfcgname: ", PreferenceUtils.getString(MyApplication.mContext, "token", "") + "---" + twoid + "---" + fourName);
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .searchSpname(PreferenceUtils.getString(MyApplication.mContext, "token", ""), twoid, fourName))
                .setDataListener(new HttpDataListener<List<ShangPinSousuoMohuBean>>() {
                    @Override
                    public void onNext(List<ShangPinSousuoMohuBean> data) {
                        int mysize = data == null ? 0 : data.size();
                        if (mysize != 0) {
                            datas.addAll(data);
                        }
                        guigeadapter.notifyDataSetChanged();
                    }
                }, false);
    }
}
