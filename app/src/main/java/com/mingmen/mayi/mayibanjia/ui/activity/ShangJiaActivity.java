package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.QiYeLieBiaoBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.QiYeLieBiaoAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ZhuceShangjiaAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShangJiaActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_pipei)
    TextView tvPipei;
    @BindView(R.id.iv_pipei)
    ImageView ivPipei;
    @BindView(R.id.ll_pipei)
    LinearLayout llPipei;
    @BindView(R.id.et_sousuo)
    EditText etSousuo;
    @BindView(R.id.rv_list)
    SwipeMenuRecyclerView rvList;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.tv_dizhi)
    TextView tvDizhi;
    @BindView(R.id.tv_paixu)
    TextView tvPaixu;
    @BindView(R.id.tv_shaixuan)
    TextView tvShaixuan;
    @BindView(R.id.ll_shaixuan)
    LinearLayout llShaixuan;

    private Context mContext;
    private String province = "";
    private String city = "";
    private String region = "";
    private String street = "";
    private String parent_number = "";
    private String name = "";
    private int ye = 1;
    private ZhuceShangjiaAdapter adapter;
    private ArrayList<QiYeLieBiaoBean> mlist = new ArrayList<>();
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shang_jia;
    }

    @Override
    protected void initData() {
        mContext = this;
        getQiyeLiebiao();
        tvDizhi.setText(getIntent().getStringExtra("city"));
        etSousuo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    mlist.clear();
                    ye = 1;
                    getQiyeLiebiao();
                    return true;
                }
                return false;

            }
        });
        etSousuo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(!s.toString().trim().equals(" ")){
                    name = s.toString().trim();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                // 该加载更多啦。
                getQiyeLiebiao();
            }
        };
        rvList.setLoadMoreListener(mLoadMoreListener);
        refreshLayout.setColorSchemeResources(R.color.zangqing, R.color.zangqing,
                R.color.zangqing, R.color.zangqing);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ye = 1;
                mlist.clear();
                adapter.notifyDataSetChanged();
                getQiyeLiebiao();
                refreshLayout.setRefreshing(false);
            }
        });
        adapter = new ZhuceShangjiaAdapter(mContext, mlist);
        rvList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvList.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_all, R.id.tv_dizhi, R.id.tv_paixu, R.id.ll_shaixuan,R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_all:
                break;
            case R.id.tv_dizhi:
                break;
            case R.id.tv_paixu:
                break;
            case R.id.ll_shaixuan:
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    //查询企业列表
    public void getQiyeLiebiao() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getShangjiaList(PreferenceUtils.getString(MyApplication.mContext, "token",""),"7",province,city,region,street,"2",parent_number,name,ye+""))
                .setDataListener(new HttpDataListener<List<QiYeLieBiaoBean>>() {
                    @Override
                    public void onNext(final List<QiYeLieBiaoBean> data) {
                        if (!"null".equals(String.valueOf(data))) {
                            if (data.size() == 10) {
                                rvList.loadMoreFinish(false, true);
                            } else if (data.size() > 0) {
                                rvList.loadMoreFinish(false, false);
                            } else {
                                rvList.loadMoreFinish(true, false);
                            }
                            mlist.addAll(data);
                            adapter.notifyDataSetChanged();
                            ye++;
                        }


                    }
                });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(ye!=1){
            ye = 1;
            mlist.clear();
            adapter.notifyDataSetChanged();
            getQiyeLiebiao();
        }
    }
}
