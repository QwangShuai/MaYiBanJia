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
import com.mingmen.mayi.mayibanjia.bean.JsonBean;
import com.mingmen.mayi.mayibanjia.bean.QiYeLieBiaoBean;
import com.mingmen.mayi.mayibanjia.bean.QuanBuShiChangBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.QuanbuShichangAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ZhuceShangjiaAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuanBuShiChangActivity extends BaseActivity {
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
    @BindView(R.id.rv_list)
    SwipeMenuRecyclerView rvList;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    private Context mContext;
    private String provinceNo = "";
    private String province = "";
    private String city = "";
    private String cityNo = "";
    private String region = "";
    private String street = "";
    private String parent_number = "";
    private String name = "";
    private int ye = 1;
    private QuanbuShichangAdapter adapter;
    private ArrayList<QuanBuShiChangBean> mlist = new ArrayList<>();
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();//省
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();//市
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();//区

    @Override
    public int getLayoutId() {
        return R.layout.activity_quan_bu_shi_chang;
    }

    @Override
    protected void initData() {
        mContext = this;
        tvDizhi.setText(getIntent().getStringExtra("city"));
        getNumber();
        etSousuo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    mlist.clear();
                    ye = 1;
                    getList();
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
                getList();
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
                getList();
                refreshLayout.setRefreshing(false);
            }
        });
        adapter = new QuanbuShichangAdapter(mContext, mlist);
        rvList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvList.setAdapter(adapter);
        getList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.ll_pipei, R.id.tv_all, R.id.tv_dizhi, R.id.tv_paixu, R.id.ll_shaixuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_pipei:
                break;
            case R.id.tv_all:
                break;
            case R.id.tv_dizhi:
                break;
            case R.id.tv_paixu:
                break;
            case R.id.ll_shaixuan:
                break;
        }
    }

    //查询企业列表
    public void getList() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getShichangList(PreferenceUtils.getString(MyApplication.mContext, "token", ""),provinceNo,cityNo,region,name,ye+""))
                .setDataListener(new HttpDataListener<List<QuanBuShiChangBean>>() {
                    @Override
                    public void onNext(final List<QuanBuShiChangBean> data) {
                        if (!"null".equals(String.valueOf(data))) {
                            if (data.size() == 5) {
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

    private void getNumber(){
        for(int i=0;i<options1Items.size();i++){
            if(options1Items.get(i).getQuymc().equals(province)){
                provinceNo = options1Items.get(i).getQuybm()+"";
                for(int j=0;j<options1Items.get(i).getCitylist().size();j++){
                    if(options1Items.get(i).getCitylist().get(j).getQuymc().equals(city)){
                        cityNo = options1Items.get(i).getCitylist().get(j).getQuybm()+"";
                        return;
                    }
                }
            }
        }
    }
    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = StringUtil.getJson(this,"province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = StringUtil.parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i=0;i<jsonBean.size();i++){//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c=0; c<jsonBean.get(i).getCitylist().size(); c++){//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCitylist().get(c).getQuymc();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCitylist().get(c).getQulist() == null
                        ||jsonBean.get(i).getCitylist().get(c).getQulist().size()==0) {
                    City_AreaList.add("");
                }else {

                    for (int d=0; d < jsonBean.get(i).getCitylist().get(c).getQulist().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCitylist().get(c).getQulist().get(d).getQuymc();

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
    }
}
