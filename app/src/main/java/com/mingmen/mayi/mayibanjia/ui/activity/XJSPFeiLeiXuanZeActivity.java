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

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.AddSpListBean;
import com.mingmen.mayi.mayibanjia.bean.FCGName;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.XJSPFeiLeiXuanZeAdapter;
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

public class XJSPFeiLeiXuanZeActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_sousuo)
    EditText etSousuo;
    @BindView(R.id.ll_sousuo)
    LinearLayout llSousuo;
    @BindView(R.id.tv_quxiao)
    TextView tvQuxiao;
    @BindView(R.id.tv_feilei_lable)
    TextView tvFeileiLable;
    @BindView(R.id.tv_fenlei)
    TextView tvFenlei;
    @BindView(R.id.ll_fenlei)
    LinearLayout llFenlei;
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
    @BindView(R.id.tv_mingcheng_lable)
    TextView tvMingchengLable;
    @BindView(R.id.tv_mingcheng)
    TextView tvMingcheng;
    @BindView(R.id.ll_mingcheng)
    LinearLayout llMingcheng;

    private ArrayList<FCGName> yijiFenLei = new ArrayList<>();
    private XJSPFeiLeiXuanZeAdapter adapter;
    private Context mContext;
    private GridLayoutManager manager;
    private String yclId = "346926195929448587b078e7fe613530 ";
    private String oneid;
    private String twoid;
    private String threeid;
    private String oneName;
    private String twoName;
    private String threeName;
    private String fourName;
    private Map<String, FCGName> map = new HashMap<>();
    private List<AddSpListBean> list = new ArrayList<>();
    private String id = "";
    private int count = 0;
    private String mytype = "1";

    @Override
    public int getLayoutId() {
        return R.layout.activity_xjspfei_lei_xuan_ze;
    }

    @Override
    protected void initData() {
        mContext = XJSPFeiLeiXuanZeActivity.this;
        adapter = new XJSPFeiLeiXuanZeAdapter(mContext, yijiFenLei);
        adapter.setActivity(XJSPFeiLeiXuanZeActivity.this);
        setMyManager();
        bindAdapter();
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
        getShouyeFenLei("-1", "1");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_quxiao, R.id.ll_fenlei, R.id.ll_pinlei, R.id.ll_pinzhong, R.id.btn_queren,R.id.ll_mingcheng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_quxiao:
                setViewShowClear();
                getfcgname(etSousuo.getText().toString());
                break;
            case R.id.ll_fenlei:
                getOne();
                break;
            case R.id.ll_pinlei:
                getTwo();
                break;
            case R.id.ll_pinzhong:
                getThree();
                break;
            case R.id.ll_mingcheng:

                break;
            case R.id.btn_queren:
                if (StringUtil.isValid(threeid)) {
                    resultData();
                } else {
                    ToastUtil.showToastLong("您还没选择商品");
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
                            adapter.setActivity(XJSPFeiLeiXuanZeActivity.this);
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
        adapter.setCallBack(new XJSPFeiLeiXuanZeAdapter.CallBack() {
            @Override
            public void xuanzhong(FCGName msg) {
                if (mytype.equals("1")) {
                    yclId = msg.getClassify_id();
                    oneName = msg.getClassify_name();
                    adapter.setXuanzhongid(yclId);
                    tvFenlei.setText(msg.getClassify_name());
                    getTwo();
                } else if (mytype.equals("2")) {
                    oneid = msg.getClassify_id();
                    twoName = msg.getClassify_name();
                    adapter.setXuanzhongid(oneid);
                    tvPinlei.setText(msg.getClassify_name());
                    getThree();
                } else if (mytype.equals("3")) {
                    twoid = msg.getClassify_id();
                    threeName = msg.getClassify_name();
                    adapter.setXuanzhongid(twoid);
                    tvPinzhong.setText(msg.getClassify_name());
                    if (StringUtil.isValid(msg.getOne_classify_id())) {
                        yclId = msg.getOne_classify_id();
                        oneid = msg.getTwo_classify_id();
                        oneName = msg.getOne_classify_name();
                        twoName = msg.getTwo_classify_name();
                        tvFenlei.setText(msg.getOne_classify_name());
                        tvPinlei.setText(msg.getTwo_classify_name());
                    }
                    getFour();
                } else if(mytype.equals("4")) {
                    threeid = msg.getClassify_id();
                    Log.e("xuanzhong: ", threeid+"+++");
                    fourName = msg.getClassify_name();
                    adapter.setXuanzhongid(threeid);
                    tvMingcheng.setText(msg.getClassify_name());
                }

                adapter.notifyDataSetChanged();

            }
        });
    }

    private void getOne() {
//        yclId = "";
//        adapter.setXuanzhongid("");
        map.clear();
//        yijiFenLei.clear();
//        adapter.notifyDataSetChanged();
        tvFeileiLable.setTextColor(mContext.getResources().getColor(R.color.zangqing));
        tvLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        tvPinzhongLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        tvMingcheng.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        mytype = "1";
        adapter.setXuanzhongid(yclId);
        getShouyeFenLei("-1", "1");
    }

    private void getTwo() {
//        twoid = "";
//        tvFeileiLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
//        adapter.setXuanzhongid("");
        map.clear();
//        yijiFenLei.clear();
//        adapter.notifyDataSetChanged();
        tvFeileiLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        tvLable.setTextColor(mContext.getResources().getColor(R.color.zangqing));
        tvPinzhongLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        tvMingcheng.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        mytype = "2";
        adapter.setXuanzhongid(oneid);
        getShouyeFenLei(yclId, "2");
    }

    private void getThree() {
//        adapter.setXuanzhongid("");
        map.clear();
//        yijiFenLei.clear();
//        adapter.notifyDataSetChanged();
        tvFeileiLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        tvPinzhongLable.setTextColor(mContext.getResources().getColor(R.color.zangqing));
        tvLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        tvMingcheng.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        mytype = "3";
        adapter.setXuanzhongid(twoid);
        getShouyeFenLei(oneid, "3");
    }
    private void getFour() {
//        adapter.setXuanzhongid("");
        map.clear();
//        yijiFenLei.clear();
//        adapter.notifyDataSetChanged();
        tvFeileiLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        tvPinzhongLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        tvLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        tvMingcheng.setTextColor(mContext.getResources().getColor(R.color.zangqing));
        mytype = "4";
        adapter.setXuanzhongid(threeid);
        getShouyeFenLei(twoid, "4");
    }
    private void setViewShowClear() {
        yclId = "";
        oneid = "";
        twoid = "";
        threeid = "";
        tvLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        tvPinzhongLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        adapter.setXuanzhongid("");
        map.clear();
        tvFenlei.setText("全部");
        tvPinlei.setText("全部");
        tvPinzhong.setText("全部");
        tvMingcheng.setText("全部");
        yijiFenLei.clear();
        mytype = "4";
        adapter.notifyDataSetChanged();
    }

    private void setMyManager() {
        manager = new GridLayoutManager(mContext, 3);
    }

    private void getfcgname(final String name) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getFourSp(PreferenceUtils.getString(MyApplication.mContext, "token", ""), name))
                .setDataListener(new HttpDataListener<List<FCGName>>() {
                    @Override
                    public void onNext(List<FCGName> list) {
                        int mysize = list == null ? 0 : list.size();
                        if (mysize != 0) {
                            yijiFenLei.clear();
                            yijiFenLei.addAll(list);
                            adapter.notifyDataSetChanged();
                        } else {
                            ToastUtil.showToastLong("当前类别暂无商品");
                        }

                    }
                }, false);
    }

    private void resultData() {
        Intent it = new Intent();
        it.putExtra("one_id", yclId);
        it.putExtra("two_id", oneid);
        it.putExtra("three_id", twoid);
        it.putExtra("four_id", threeid);
        it.putExtra("name", oneName + "-" + twoName + "-" + threeName);
        it.putExtra("spname",fourName);
        setResult(4, it);
        finish();
    }

}
