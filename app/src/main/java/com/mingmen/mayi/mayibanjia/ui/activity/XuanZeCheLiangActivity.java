package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CheliangBean;
import com.mingmen.mayi.mayibanjia.bean.WuliuDingdanBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.XuanzechexingAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XuanZeCheLiangActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_sousuo)
    EditText etSousuo;
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    private Context mContext;
    private XuanzechexingAdapter adapter;
    private List<CheliangBean> mlist = new ArrayList<>();
    private String type = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_xuan_ze_che_liang;
    }

    @Override
    protected void initData() {
        mContext = XuanZeCheLiangActivity.this;
        tvTitle.setText("车辆选择");
        type = getIntent().getStringExtra("type");
        adapter = new XuanzechexingAdapter(mContext, mlist, new XuanzechexingAdapter.CallBack() {
            @Override
            public void succeed(CheliangBean bean) {
                EventBus.getDefault().post(bean);
                finish();
            }
        });
        rvList.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        rvList.setAdapter(adapter);
        StringUtil.setInputNoEmoj(etSousuo,24);
        getCheliang("");
        etSousuo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    getCheliang(etSousuo.getText().toString().trim());
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

    @OnClick({R.id.iv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                break;
        }
    }
    //数据
    public void getCheliang(String name) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getCheliangList(PreferenceUtils.getString(MyApplication.mContext, "token", ""),type, name))
                .setDataListener(new HttpDataListener<List<CheliangBean>>() {
                    @Override
                    public void onNext(List<CheliangBean> list) {
                        mlist.clear();
                        adapter.notifyDataSetChanged();
                        mlist.addAll(list);
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
