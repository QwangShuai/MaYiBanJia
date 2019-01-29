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
import com.mingmen.mayi.mayibanjia.bean.AddSpListBean;
import com.mingmen.mayi.mayibanjia.bean.FCGName;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.AddSpFourAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.YiJiFenLeiAdapter;
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
    @BindView(R.id.ll_shichang)
    LinearLayout llShichang;
    @BindView(R.id.rv_yijifenlei)
    RecyclerView rvYijifenlei;

    private ArrayList<FCGName> yijiFenLei = new ArrayList<>();
    private AddSpFourAdapter adapter;
    private Context mContext;
    private GridLayoutManager manager;
    private String yclId = "92bd917674ee41f392a25674445e76f9";
    private String oneid;
    private String twoid;
    private Map<String,FCGName> map = new HashMap<>();
    private String mytype = "2";
    private List<AddSpListBean> list =new ArrayList<>();
    private String id = "";
    private int count = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_shang_pin;
    }

    @Override
    protected void initData() {
        mContext = AddShangPinActivity.this;
        id = getIntent().getStringExtra("id");
        adapter = new AddSpFourAdapter(mContext,yijiFenLei);
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

    @OnClick({R.id.iv_back, R.id.tv_quxiao, R.id.ll_pinlei, R.id.ll_pinzhong, R.id.btn_queren})
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
                getTwo();
                break;
            case R.id.ll_pinzhong:
                getThree();
                break;
            case R.id.btn_queren:
                addSpList();
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
                                .getFeiLei(id, type))
                .setDataListener(new HttpDataListener<List<FCGName>>() {

                    @Override
                    public void onNext(List<FCGName> list) {
                        int mysize = list == null ? 0 : list.size();
                        if (mysize != 0) {
                            if(mytype.equals("4")){
                                adapter.setType(mytype);
                                adapter.setActivity(AddShangPinActivity.this);
                            }
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
                } else if(mytype.equals("3")) {
                    twoid = msg.getClassify_id();
                    adapter.setXuanzhongid(oneid);
                    tvPinzhong.setText(msg.getClassify_name());
                    getFour();
                }else if(mytype.equals("4")){
                    onChangeMap(msg,msg.isSelect());
                }

                adapter.notifyDataSetChanged();

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
        if(StringUtil.isValid(oneid)){
            tvPinzhongLable.setTextColor(mContext.getResources().getColor(R.color.zangqing));
            tvLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
            mytype = "3";
            adapter.setType("");
            adapter.setXuanzhongid(twoid);
            getShouyeFenLei(oneid, "3");
        }
    }

    private void getTwo() {
        twoid = "";
        tvLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        tvPinzhongLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        adapter.setXuanzhongid("");
        map.clear();
        yijiFenLei.clear();
        adapter.notifyDataSetChanged();
        tvLable.setTextColor(mContext.getResources().getColor(R.color.zangqing));
        tvPinzhongLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        mytype = "2";
        adapter.setType("");
        adapter.setXuanzhongid(oneid);
        getShouyeFenLei(yclId, "2");
    }
    private void getFour() {
        tvLable.setTextColor(mContext.getResources().getColor(R.color.zangqing));
        tvPinzhongLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        mytype = "4";
//        adapter.setXuanzhongid(oneid);
        getShouyeFenLei(twoid, "4");
    }

    public void onChangeMap(FCGName mybean,boolean b){
        count = 0;
        list.clear();
        if(b){
            map.put(mybean.getClassify_id(),mybean);
        } else {
            map.remove(mybean.getClassify_id());
        }
        for (FCGName fcgName : map.values()) {
            AddSpListBean addSpListBean = new AddSpListBean();
            if(StringUtil.isValid(fcgName.getTwo_classify_id())){
                addSpListBean.setClassify_id(fcgName.getTwo_classify_id());
            } else {
                addSpListBean.setClassify_id(oneid);
            }

            addSpListBean.setPack_standard_id(fcgName.getSpec_idThree());
            addSpListBean.setSort_id(fcgName.getClassify_id());
            list.add(addSpListBean);
            count++;
            Log.e("zzzzz",count+"------");
        }
    }
    private void getfcgname(final String name) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getfcgname(PreferenceUtils.getString(MyApplication.mContext, "token", ""), name))
                .setDataListener(new HttpDataListener<List<FCGName>>() {
                    @Override
                    public void onNext(List<FCGName> list) {
                        int mysize = list == null ? 0 : list.size();
                        if (mysize != 0) {
                            if(mytype.equals("4")){
                                adapter.setType(mytype);
                            }
                            yijiFenLei.clear();
                            yijiFenLei.addAll(list);
                            adapter.notifyDataSetChanged();
                        } else {
                            ToastUtil.showToastLong("当前类别暂无品类");
                        }

                    }
                }, false);
    }

    private void setViewShowClear(){
        oneid = "";
        twoid = "";
        tvLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        tvPinzhongLable.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
        adapter.setXuanzhongid("");
        map.clear();
        tvPinlei.setText("全部");
        tvPinzhong.setText("全部");
        yijiFenLei.clear();
        adapter.setType("4");
        adapter.notifyDataSetChanged();
    }
    public void addSpList() {
        if (count != 0) {
            String sort_id = new Gson().toJson(list);
            HttpManager.getInstance()
                    .with(mContext)
                    .setObservable(RetrofitManager.getService()
                            .addSpList(PreferenceUtils.getString(MyApplication.mContext, "token", ""), sort_id, getIntent().getStringExtra("name"),id))
                    .setDataListener(new HttpDataListener<String>() {
                        @Override
                        public void onNext(String data) {
                            id = data;
                            Log.e("myId",id);
                            ToastUtil.showToastLong("添加成功");
                        }
                    });
        } else {
            ToastUtil.showToastLong("请至少选择一项商品");
        }
    }

    private void myBack(){
        Intent it = new Intent();
        it.putExtra("id",id);
        setResult(2,it);
        finish();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        myBack();
    }
}
