package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.FCGName;
import com.mingmen.mayi.mayibanjia.bean.RoleBean;
import com.mingmen.mayi.mayibanjia.bean.SearchCbkBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.FaCaiGouMohuAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.view.XCFlowLayout;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchCaipinActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_sousuo)
    EditText etSousuo;
    @BindView(R.id.tv_sousuo)
    TextView tvSousuo;
    @BindView(R.id.ll_sousuo)
    LinearLayout llSousuo;
    @BindView(R.id.xcf_caipin)
    XCFlowLayout xcfCaipin;

    private Context mContext;

    private ArrayList<TextView> tvs;
    private String name = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_search_caipin;
    }

    @Override
    protected void initData() {
        mContext = SearchCaipinActivity.this;
        tvTitle.setText("添加");
        StringUtil.setInputNoEmoj(etSousuo);
        etSousuo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().equals(" ")){
                    name = s.toString().trim();
                } else {
                    etSousuo.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etSousuo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    getSpList();
                    return true;
                }
                return false;
            }
        });
        getSpList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.ll_sousuo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                break;
            case R.id.ll_sousuo:
                getSpList();
                break;
        }
    }

    private void initShangpinChildViews(final List<SearchCbkBean> mList) {
        xcfCaipin.removeAllViews();
        tvs = new ArrayList();
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = AppUtil.dip2px(12);
        lp.rightMargin = AppUtil.dip2px(0);
        lp.topMargin = AppUtil.dip2px(12);
        lp.bottomMargin = 0;
        for (int i = 0; i < mList.size(); i++) {
            TextView view = new TextView(mContext);
            view.setTextColor(mContext.getResources().getColor(R.color.zangqing));
            view.setTextSize(12);
            view.setPadding(AppUtil.dip2px(12), AppUtil.dip2px(8), AppUtil.dip2px(12), AppUtil.dip2px(8));
            view.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_hollow_zangqing_3));
            view.setText(mList.get(i).getCommodity_name());
            tvs.add(view);
            xcfCaipin.addView(view, lp);
        }
        for (int i = 0; i < tvs.size(); i++) {
            final int finalI = i;
            tvs.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(mList.get(finalI));
                    finish();
                }
            });
        }
    }

    private void getSpList() {//搜索市场
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getCbkLishiList(PreferenceUtils.getString(MyApplication.mContext, "token", ""), name))
                .setDataListener(new HttpDataListener<List<SearchCbkBean>>() {
                    @Override
                    public void onNext(List<SearchCbkBean> list) {
                        initShangpinChildViews(list);
                    }
                }, false);
    }
}
