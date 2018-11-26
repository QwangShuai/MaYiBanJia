package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.AllShiChangBean;
import com.mingmen.mayi.mayibanjia.bean.DianPuBean;
import com.mingmen.mayi.mayibanjia.bean.FCGName;
import com.mingmen.mayi.mayibanjia.bean.FenLeiBean;
import com.mingmen.mayi.mayibanjia.bean.ShangPinSouSuoBean;
import com.mingmen.mayi.mayibanjia.bean.ShiChangSouSuoShangPinBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.DianPuListAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ErJiFenLeiAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.FaCaiGouMohuAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.SanJiFenLeiAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShangPinListAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShangPinMohuAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShiChangAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShiChangSouSuoShangPinListAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.YiJiFenLeiAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.JiaRuGouWuCheDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.view.AutoLineFeedLayoutManager;
import com.mingmen.mayi.mayibanjia.ui.view.XCFlowLayout;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.ToolLocation;
import com.mingmen.mayi.mayibanjia.utils.sQLite.RecordSQLiteOpenHelper;
import com.mingmen.mayi.mayibanjia.utils.sQLite.RecordsDao;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/12/012.
 * 搜索页面
 */

public class SouSuoActivity extends BaseActivity {

    @BindView(R.id.tv_pipei)
    TextView tvPipei;
    @BindView(R.id.iv_pipei)
    ImageView ivPipei;
    @BindView(R.id.ll_pipei)
    LinearLayout llPipei;
    @BindView(R.id.et_sousuo)
    EditText etSousuo;
    @BindView(R.id.tv_quxiao)
    TextView tvQuxiao;
    @BindView(R.id.tv_shichang)
    TextView tvShichang;
    @BindView(R.id.view_shichang)
    View viewShichang;
    @BindView(R.id.rl_shichang)
    RelativeLayout rlShichang;
    @BindView(R.id.tv_dianpu)
    TextView tvDianpu;
    @BindView(R.id.view_dianpu)
    View viewDianpu;
    @BindView(R.id.rl_dianpu)
    RelativeLayout rlDianpu;
    @BindView(R.id.tv_shangpin)
    TextView tvShangpin;
    @BindView(R.id.view_shangpin)
    View viewShangpin;
    @BindView(R.id.rl_shangpin)
    RelativeLayout rlShangpin;
    @BindView(R.id.iv_lishishanchu)
    ImageView ivLishishanchu;
    @BindView(R.id.rl_lishisousuo)
    RelativeLayout rlLishisousuo;
    @BindView(R.id.xcf_lishisousuo)
    XCFlowLayout xcfLishisousuo;
    @BindView(R.id.xcf_remensousuo)
    XCFlowLayout xcfRemensousuo;
    @BindView(R.id.ll_shangpin)
    LinearLayout llShangpin;
    @BindView(R.id.ll_wuzi)
    LinearLayout llWuzi;
    @BindView(R.id.rv_youzi)
    SwipeMenuRecyclerView rvYouzi;
    @BindView(R.id.ll_shichang)
    LinearLayout llShichang;
    @BindView(R.id.iv_tu)
    ImageView ivTu;
    @BindView(R.id.ll_lishi)
    LinearLayout llLishi;
    @BindView(R.id.tv_xiaoliang)
    TextView tvXiaoliang;
    @BindView(R.id.tv_jiage)
    TextView tvJiage;
    @BindView(R.id.ll_jiage)
    LinearLayout llJiage;
    @BindView(R.id.tv_pingfenzuigao)
    TextView tvPingfenzuigao;
    @BindView(R.id.tv_diqu)
    TextView tvDiqu;
    @BindView(R.id.tv_pinlei)
    TextView tvPinlei;
    @BindView(R.id.tv_shaixuan)
    TextView tvShaixuan;
    @BindView(R.id.ll_diqu)
    LinearLayout llDiqu;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;



    private DianPuListAdapter dianpuadapter;
    private ShiChangSouSuoShangPinListAdapter shichangadapter;
    private ShangPinListAdapter shangpinadapter;
    private Context mContext;
    private RecordSQLiteOpenHelper helper;
    private RecordsDao recordsDao;
    private String lastSearch;
    private List<String> shangpinNamelist;
    private List<String> dianpuNamelist;
    private ArrayList<TextView> tvs;
    private ConfirmDialog confirmDialog;
    private String sousuofangxiang;
    private FaCaiGouMohuAdapter mohuAdapter;
    private String fenleiid="";
    private String fenleiname="";
    private String TAG = "SouSuoActivity";
    private int sousuo_state = 0;

    private JiaRuGouWuCheDialog jiarugouwuchedialog;
    private String spguige;
    private ArrayList<ShangPinSouSuoBean.ZhengchangBean> shangpinlist = new ArrayList<>();
    private ArrayList<FenLeiBean> allFenLei = new ArrayList<>();
    private ArrayList<FenLeiBean> yijiFenLei;
    private ArrayList<FenLeiBean> erjiFenLei;
    private ArrayList<FenLeiBean> sanjiFenLei;
    private boolean isLodeFenLei = false;
    private YiJiFenLeiAdapter yijiadapter;
    private String yijipinleiid = "";
    private String erjipinleiid = "";
    private String sanjipinleiid = "";
    private String erjipinleiname = "";
    private String shichangid="";
    private AllShiChangBean shiChangBean;
    private ToolLocation location;
    String zuigaojia="";
    String zuidijia="";
    private PopupWindow shaixuanpop;
    private boolean isdi;
    private String shichangname = "";
    private ArrayList<ShiChangSouSuoShangPinBean> shichanglist = new ArrayList<>();
    private ArrayList<DianPuBean> dianpulist = new ArrayList<>();
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;
    private String sousuozi="";
    private ArrayList<FenLeiBean> dangqianerji;
    private int ye=1;
    private String type="0";
    @Override
    public int getLayoutId() {
        return R.layout.activity_sousuo;
    }

    @Override
    protected void initData() {
        mContext = SouSuoActivity.this;
        //dialog
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        etSousuo.setText("");
        sousuofangxiang = "shichang";
        setViewMoren();
        if (getIntent().getStringExtra("sousuofangxiang") != null) {
            sousuofangxiang = getIntent().getStringExtra("sousuofangxiang");
            Log.e("sousuofangxiang", sousuofangxiang + "=1=1=");
            if ("shichang".equals(sousuofangxiang)) {
                shichangxianshi();
            } else if ("dianpu".equals(sousuofangxiang)) {
                dianpuxianshi();
            } else if ("shangpin".equals(sousuofangxiang)) {
                shangpinxianshi();

            }
        }
        mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                // 该加载更多啦。

//        ... // 请求数据，并更新数据源操作。
                ye++;
                Log.e("ye++++",ye+"--");
                Log.e(TAG,yijipinleiid+"-"+erjipinleiid+"-"+sanjipinleiid+"-"+shichangid+"-"+zuigaojia+"-"+zuidijia+"-"+type+"-"+ye);
//                sousuoshangpin(sousuozi,type);
                HttpManager.getInstance()
                        .with(mContext)
                        .setObservable(
                                RetrofitManager
                                        .getService()
                                        .sousuoshangpin(PreferenceUtils.getString(MyApplication.mContext, "token", ""), sousuozi,"","",yijipinleiid,erjipinleiid,sanjipinleiid,shichangid,zuigaojia,zuidijia,type,ye))
                        .setDataListener(new HttpDataListener<ShangPinSouSuoBean>() {
                            @Override
                            public void onNext(final ShangPinSouSuoBean shangpin) {
                                if (shangpin.getZhengchang()!=null){
                                    shangpinlist.addAll(shangpin.getZhengchang());
                                    if (shangpin.getZhengchang().size()==5){
                                        rvYouzi.loadMoreFinish(false, true);
                                        Log.e("本次加载有数据,数据条数为","5条，后面有没有数据待定");
                                    }else if (shangpin.getZhengchang().size()>0){
                                        rvYouzi.loadMoreFinish(false, false);
                                        Log.e("本次加载有数据,数据条数为",shangpin.getZhengchang().size()+"条，后面没有数据");
                                    }else{
                                        rvYouzi.loadMoreFinish(true, false);
                                        Log.e("本次加载有数据,数据条数为","0条，后面没有数据");
                                    }
                                }
                                shangpinadapter.notifyDataSetChanged();
                            }
                        },false);
            }
        };

        rvYouzi.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL, false));
        rvYouzi.useDefaultLoadMore(); // 使用默认的加载更多的View。
        rvYouzi.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        rvYouzi.loadMoreFinish(false, true);

        refreshLayout.setColorSchemeResources(R.color.zangqing, R.color.zangqing,
                R.color.zangqing, R.color.zangqing);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                sousuoshangpin(type,sousuozi);
                refreshLayout.setRefreshing(false);
            }
        });
        rvYouzi.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        // 2. 实例化数据库SQLiteOpenHelper子类对象
        helper = new RecordSQLiteOpenHelper(mContext);
        recordsDao = new RecordsDao(mContext);
        initChildViews();
        etSousuo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if ("".equals(etSousuo.getText().toString().trim()) || etSousuo.getText().toString().trim() == null) {
                    llWuzi.setVisibility(View.VISIBLE);
                    rvYouzi.setVisibility(View.GONE);
                }
                if(s.length()>0){
                    sousuozi=s.toString().trim();
                }
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etSousuo.getText().toString().trim().length() == 0) {
                    llWuzi.setVisibility(View.VISIBLE);
                    rvYouzi.setVisibility(View.GONE);
                } else {
                    if(s.length()>0){
                        sousuozi=s.toString().trim();
                    }
                    llWuzi.setVisibility(View.GONE);
                    rvYouzi.setVisibility(View.VISIBLE);
                    if(sousuo_state==0){
                        String sousuo = etSousuo.getText().toString().trim();
                        //查询
                        getmohuchaxun(sousuo);
                    } else if(sousuo_state==1){
                        sousuodianpu(etSousuo.getText().toString().trim());
                    } else {
                        llWuzi.setVisibility(View.VISIBLE);
                        sousuoshangpin("3",sousuozi);
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>0){
                    sousuozi=s.toString().trim();
                }
                if(sousuo_state==2){
                    sousuo(sousuozi,true);
                    llShangpin.setVisibility(View.VISIBLE);
                }

            }
        });
        etSousuo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (etSousuo.getText().toString().trim().length() == 0) {
                        lastSearch = etSousuo.getHint().toString().trim();
                    } else {
                        lastSearch = etSousuo.getText().toString().trim();
                    }

                    sousuo(lastSearch,false);


                    if (recordsDao.dianpuIsHas(lastSearch)) {
                        recordsDao.deleteOneDianpu(lastSearch);
                    }
                    PreferenceUtils.putString(MyApplication.mContext, "keyword", lastSearch);
                    recordsDao.addDianpu(lastSearch);
                    initChildViews();
                    return true;
                }
                return false;
            }
        });
        jiarugouwuchedialog = new JiaRuGouWuCheDialog(mContext,
                mContext.getResources().getIdentifier("BottomDialog", "style", mContext.getPackageName()));
        jiarugouwuchedialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.LEFT | Gravity.RIGHT);
        yijiadapter = new YiJiFenLeiAdapter();
    }

    private void getmohuchaxun(String sousuo) {//模糊查询
        if ("shichang".equals(sousuofangxiang)) {
            getfcgname(sousuo);
        } else if ("dianpu".equals(sousuofangxiang)) {
            sousuodianpu(sousuo);
        } else if (("shangpin".equals(sousuofangxiang))) {
            sousuomoren(sousuo);
        }
    }

    private void getfcgname(final String name) {//搜索市场
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getfcgname(PreferenceUtils.getString(MyApplication.mContext, "token", ""), name))
                .setDataListener(new HttpDataListener<List<FCGName>>() {
                    @Override
                    public void onNext(List<FCGName> data) {
                        final ArrayList<FCGName> datas = new ArrayList<FCGName>();
                        datas.addAll(data);
                        Log.e("data", data + "---");
                        mohuAdapter = new FaCaiGouMohuAdapter(mContext, datas);
                        mohuAdapter.setData(datas);
                        rvYouzi.setAdapter(mohuAdapter);
                        mohuAdapter.setOnItemClickListener(new FaCaiGouMohuAdapter.OnItemClickListener() {//下拉弹出点击事件
                            @Override
                            public void onClick(View view, int position) {
                                fenleiid = datas.get(position).getClassify_id();
                                fenleiname = datas.get(position).getClassify_name();
                                tvPipei.setText(fenleiname);
                                llPipei.setVisibility(View.VISIBLE);
                                etSousuo.setText("");
                                sousuo("",false);
                            }
                        });
                    }
                }, false);
    }

    //商品搜索
    private void sousuomoren(String sousuo) {
        Log.e("sousuoshangpin", sousuo + "-");
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .sousuomoren(PreferenceUtils.getString(MyApplication.mContext, "token", ""), sousuo, "0"))
                .setDataListener(new HttpDataListener<ShangPinSouSuoBean>() {
                    @Override
                    public void onNext(final ShangPinSouSuoBean shangPinBean) {
                        ShangPinMohuAdapter shangpinadapter = new ShangPinMohuAdapter(mContext, shangPinBean.getZhengchang());

                        rvYouzi.setAdapter(shangpinadapter);
                        shangpinadapter.setOnItemClickListener(new ShangPinMohuAdapter.OnItemClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                etSousuo.setText(shangPinBean.getZhengchang().get(position).getCommodity_name());
                                sousuo(shangPinBean.getZhengchang().get(position).getCommodity_name(),false);
                                if (recordsDao.shangpinIsHas(shangPinBean.getZhengchang().get(position).getCommodity_name())) {
                                    recordsDao.deleteOneShangpin(shangPinBean.getZhengchang().get(position).getCommodity_name());
                                }
                                PreferenceUtils.putString(MyApplication.mContext, "keyword", shangPinBean.getZhengchang().get(position).getCommodity_name());
                                recordsDao.addShangpin(shangPinBean.getZhengchang().get(position).getCommodity_name());
                            }
                        });

                    }
                }, false);
    }

    private void initChildViews() {
        xcfLishisousuo.removeAllViews();
        if (dianpuNamelist != null) {
            dianpuNamelist.clear();
        }
        dianpuNamelist = recordsDao.getdianpu();
        String[] historyNames = new String[dianpuNamelist.size()];
        for (int i = 0; i < dianpuNamelist.size(); i++) {
            historyNames[i] = dianpuNamelist.get(i);
        }
        tvs = new ArrayList();
        if (historyNames.length == 0) {
            rlLishisousuo.setVisibility(View.GONE);
        } else {
            rlLishisousuo.setVisibility(View.VISIBLE);
            ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.leftMargin = AppUtil.dip2px(0);
            lp.rightMargin = AppUtil.dip2px(12);
            lp.topMargin = AppUtil.dip2px(12);
            lp.bottomMargin = 0;
            for (int i = 0; i < historyNames.length; i++) {
                TextView view = new TextView(this);
//                Log.e("ssss--------->",historyNames[i]+"");
                view.setText(historyNames[i]);
                view.setTextColor(getResources().getColor(R.color.lishisousuo));
                view.setTextSize(12);
                view.setBackground(getResources().getDrawable(R.drawable.lishisousuotextstyle));
                tvs.add(view);
                xcfLishisousuo.addView(view, lp);
            }
        }
        for (int i = 0; i < tvs.size(); i++) {
            final int finalI = i;
            tvs.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String wenzi = tvs.get(finalI).getText().toString().trim();
                    sousuo(wenzi,false);
                    if (recordsDao.dianpuIsHas(wenzi)) {
                        recordsDao.deleteOneDianpu(wenzi);
                    }
                    PreferenceUtils.putString(MyApplication.mContext, "keyword", wenzi);
                    recordsDao.addDianpu(wenzi);
                    initChildViews();
                }
            });

        }
    }

    @OnClick({R.id.iv_back,R.id.tv_quxiao, R.id.rl_shichang, R.id.rl_dianpu, R.id.rl_shangpin, R.id.iv_pipei, R.id.iv_lishishanchu
            , R.id.ll_diqu, R.id.ll_pinlei, R.id.ll_shaixuan, R.id.tv_xiaoliang, R.id.ll_jiage, R.id.tv_pingfenzuigao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_quxiao:
                finish();
                break;
            case R.id.rl_shichang:
                shichangxianshi();
                sousuofangxiang = "shichang";
                break;
            case R.id.rl_dianpu:
                dianpuxianshi();
                sousuofangxiang = "dianpu";
                break;
            case R.id.rl_shangpin:
                shangpinxianshi();
                sousuofangxiang = "shangpin";
                break;
            case R.id.iv_pipei:
                tvPipei.setText("");
                llPipei.setVisibility(View.GONE);
                fenleiid="";
                fenleiname="";
                break;
            case R.id.iv_lishishanchu:
                confirmDialog.showDialog("是否删除全部历史记录");
                confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recordsDao.deleteAllDianpu();
                        xcfLishisousuo.removeAllViews();
                        xcfLishisousuo.setVisibility(View.GONE);
//                        rlLishisousuo.setVisibility(View.GONE);
                        confirmDialog.cancel();
                    }
                });
                confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmDialog.cancel();
                    }
                });
                break;
            case R.id.ll_diqu:
                //地区

                break;
            case R.id.ll_pinlei:
                //品类
                if (isLodeFenLei) {
                    showYiJiPop();
                } else {
                    getFenLei();
                }
                break;
            case R.id.ll_shaixuan:
                //筛选
                if (shiChangBean ==null) {
                    getshichang();
                } else {
                    showShaiXuanPop();
                }

                break;
            case R.id.tv_xiaoliang:
                ye = 1;
                type="1";
                //按销量排序
                sousuoshangpin(type,sousuozi);
                setXuanXiangColor(tvXiaoliang);
                break;
            case R.id.ll_jiage:
                ye = 1;
                //按价格升序或者降序
                if (isdi){
                    sousuoshangpin("4",sousuozi);
                }else{
                    sousuoshangpin("3",sousuozi);
                }
                setXuanXiangColor(tvJiage);
                break;
            case R.id.tv_pingfenzuigao:
                ye = 1;
                type = "2";
                //按评分最高排序
                sousuoshangpin(type,sousuozi);
                setXuanXiangColor(tvPingfenzuigao);
                break;
        }
    }
    private void setXuanXiangColor(TextView bianseview){
        tvXiaoliang.setTextColor(getResources().getColor(R.color.lishisousuo));
        tvJiage.setTextColor(getResources().getColor(R.color.lishisousuo));
        tvPingfenzuigao.setTextColor(getResources().getColor(R.color.lishisousuo));
        bianseview.setTextColor(getResources().getColor(R.color.zangqing));
    }
    private void shangpinxianshi() {
        if(sousuo_state!=2){
//            if(TextUtils.isEmpty(etSousuo.getText().toString())){
                setViewMoren();
//            } else {
//                llLishi.setVisibility(View.GONE);
//                llShangpin.setVisibility(View.VISIBLE);
//            }
            sousuo_state = 2;
            tvShichang.setSelected(false);
            tvDianpu.setSelected(false);
            tvShangpin.setSelected(true);
            viewShichang.setVisibility(View.GONE);
            viewDianpu.setVisibility(View.GONE);
            viewShangpin.setVisibility(View.VISIBLE);
            llShichang.setVisibility(View.GONE);
            llPipei.setVisibility(View.GONE);
            fenleiid = "";
            fenleiname = "";
//            shichanglist.clear();
//            dianpulist.clear();
//            shangpinlist.clear();
            rvYouzi.setVisibility(View.GONE);
        }
    }

    private void dianpuxianshi() {
        if(sousuo_state!=1){
//            if(TextUtils.isEmpty(etSousuo.getText().toString())){
                setViewMoren();
//            } else {
//                llLishi.setVisibility(View.GONE);
//            }
            sousuo_state = 1;
            tvShichang.setSelected(false);
            tvDianpu.setSelected(true);
            tvShangpin.setSelected(false);
            viewShichang.setVisibility(View.GONE);
            viewDianpu.setVisibility(View.VISIBLE);
            viewShangpin.setVisibility(View.GONE);
            llShichang.setVisibility(View.GONE);
            llShangpin.setVisibility(View.GONE);
            llPipei.setVisibility(View.GONE);
            fenleiid="";
            fenleiname="";
//            shichanglist.clear();
//            dianpulist.clear();
//            shangpinlist.clear();
            rvYouzi.setVisibility(View.GONE);
        }
    }

    private void shichangxianshi() {
        if(sousuo_state!=0){
//            if(TextUtils.isEmpty(etSousuo.getText().toString())){
                setViewMoren();
//            } else {
//                llLishi.setVisibility(View.GONE);
//                llShichang.setVisibility(View.VISIBLE);
//            }
            sousuo_state = 0;
            llShangpin.setVisibility(View.GONE);
            tvShichang.setSelected(true);
            tvDianpu.setSelected(false);
            tvShangpin.setSelected(false);
            viewShichang.setVisibility(View.VISIBLE);
            viewDianpu.setVisibility(View.GONE);
            viewShangpin.setVisibility(View.GONE);
//            shichanglist.clear();
//            dianpulist.clear();
//            shangpinlist.clear();
            rvYouzi.setVisibility(View.GONE);
        }

    }

    private void sousuo(String sousuo,boolean end) {//搜索框触发
        if ("shichang".equals(sousuofangxiang)){
            if (fenleiid.length()==0){
//                if(end){
                    ToastUtil.showToast("没有匹配项，无法搜索");
//                }
                return;
            } else {
                sousuoshichang();
            }
        } else if("dianpu".equals(sousuofangxiang)){
            sousuodianpu(sousuo);
        } else {
            if("".equals(sousuo)){
                ToastUtil.showToast("请输入要查询的商品名");
            } else {
                sousuoshangpin("0",sousuo);
            }
        }
    }
    private void setViewMoren(){//设置默认显示
        llLishi.setVisibility(View.VISIBLE);
        llShichang.setVisibility(View.GONE);
        llShangpin.setVisibility(View.GONE);
        rvYouzi.setVisibility(View.GONE);
    }
    private void setViewList(int type){//设置列表显示
        if(type==0){
            llShichang.setVisibility(View.VISIBLE);
        } else if(type==1){
            llShangpin.setVisibility(View.VISIBLE);
        } else {
            llShichang.setVisibility(View.GONE);
            llShangpin.setVisibility(View.GONE);
        }
        rvYouzi.setVisibility(View.VISIBLE);
        llLishi.setVisibility(View.GONE);
    }

    //市场搜索
    private void sousuoshichang() {
        Log.e(" type_tree_id",fenleiid+"====");
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .shichangsousuoshangpin(fenleiid))
                .setDataListener(new HttpDataListener<List<ShiChangSouSuoShangPinBean>>() {
                    @Override
                    public void onNext(List<ShiChangSouSuoShangPinBean> list) {
                        Log.e("shichangsousuo",gson.toJson(list));
                        shichanglist.clear();
                        shichanglist.addAll(list);
                        shichangadapter = new ShiChangSouSuoShangPinListAdapter(mContext, shichanglist);
                        rvYouzi.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                        rvYouzi.setAdapter(shichangadapter);
                        setViewList(0);
                        shichangadapter.setOnItemClickListener(new ShiChangSouSuoShangPinListAdapter.OnItemClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                switch (view.getId()){
                                    case R.id.tv_chakan://点击查看
                                        Intent intent=new Intent(mContext,ShiChangSouSuoShangPinActivity.class);
                                        bundle.putString("type_tree_id",fenleiid);
                                        bundle.putString("son_number",shichanglist.get(position).getSon_number());
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                        break;
                                }

                            }
                        });
                    }
                });
    }
    //店铺搜索
    private void sousuodianpu(String dianpuming) {
        if ("".equals(dianpuming)){
            ToastUtil.showToast("请输入要搜索的内容");
            return;
        }
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .sousuodianpu(PreferenceUtils.getString(MyApplication.mContext, "token", ""), dianpuming))
                .setDataListener(new HttpDataListener<List<DianPuBean>>() {
                    @Override
                    public void onNext(List<DianPuBean> list) {
                        Log.e("sousuodianpu", "sousuodianpu");
                        shichanglist.clear();
                        dianpulist.clear();
                        dianpulist.addAll(list);
                        if (dianpulist.size()==0){
                            ToastUtil.showToast("暂无匹配项!");
                            llLishi.setVisibility(View.VISIBLE);
                        } else {
                            setViewList(2);
                        }
                        dianpuadapter = new DianPuListAdapter(mContext, dianpulist);
                        rvYouzi.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                        rvYouzi.setAdapter(dianpuadapter);
                    }
                });
    }
    //商品搜索
    private void sousuoshangpin(final String type,String sousuo) {
        Log.e("shangpinsousuo",yijipinleiid+"-"+erjipinleiid+"-"+sanjipinleiid+"-"+shichangid+"-"+zuigaojia+"-"+zuidijia+"-"+type);
        llShangpin.setVisibility(View.VISIBLE);
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .sousuoshangpin(PreferenceUtils.getString(MyApplication.mContext, "token", ""), sousuo,"","",yijipinleiid,erjipinleiid,sanjipinleiid,shichangid,zuigaojia,zuidijia,type,1))
                .setDataListener(new HttpDataListener<ShangPinSouSuoBean>() {
                    @Override
                    public void onNext(final ShangPinSouSuoBean shangpin) {
                        ye = 1;
                        rvYouzi.loadMoreFinish(false, true);
                        if ("3".equals(type)){
                            isdi =true;
                        }else if ("4".equals(type)){
                            isdi =false;
                        }
                        shangpinlist.clear();
                        rvYouzi.removeAllViews();
//                        shangpinadapter.notifyDataSetChanged();
                        shangpinlist.addAll(shangpin.getZhengchang());
                        shangpinadapter = new ShangPinListAdapter(mContext, shangpinlist);
                        rvYouzi.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                        rvYouzi.setAdapter(shangpinadapter);
                        shangpinadapter.notifyDataSetChanged();
                        setViewList(1);
                        shangpinadapter.setOnItemClickListener(new ShangPinListAdapter.OnItemClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                switch (view.getId()) {
                                    case R.id.iv_addcar:
                                        //添加购物车
                                        final ShangPinSouSuoBean.ZhengchangBean data = shangpinlist.get(position);
                                        spguige = "";
//                                        switch (Integer.parseInt(data.getChoose_specifications())) {
//                                            case 1:
//                                                spguige = data.getPack_standard_one();
//                                                break;
//                                            case 2:
//                                                spguige = data.getPack_standard_two();
//                                                break;
//                                            case 3:
//                                                spguige = data.getPack_standard_tree();
//                                                break;
//                                        }
                                        jiarugouwuchedialog.showDialog(data.getInventory(),data.getCommodity_name(), spguige, data.getRation_one() + "", data.getPice_one() + ""
                                                , data.getRation_two() + "", data.getPice_two() + "", data.getRation_three() + "", data.getPice_three() + "", "");
                                        jiarugouwuchedialog.getBtQueding().setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                String shuliang = jiarugouwuchedialog.getEtShuliang().getText().toString().trim();
                                                Log.e("data.getShopping_id()", data.getShopping_id() + "---");
                                                if (Integer.parseInt(shuliang) >= Integer.parseInt(data.getRation_one())) {
                                                    addcar(data.getCommodity_id(), shuliang, data.getCompany_id(), "", spguige);
                                                } else {
                                                    ToastUtil.showToast("不够起订量");
                                                }
                                                Log.e("jiarugouwuche", jiarugouwuchedialog.getEtShuliang().getText().toString().trim());
                                                jiarugouwuchedialog.getEtShuliang().setText("0");
                                                jiarugouwuchedialog.cancel();
                                            }
                                        });
                                        break;
                                }
                            }
                        });


                    }
                });
    }
    //添加购物车
    private void addcar(final String spid, String shuliang, String dianpuid, String gouwucheid, String guigeid) {
        Log.e("canshu", spid + "---" + shuliang + "---" + dianpuid + "---" + gouwucheid + "---" + guigeid);
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .addcar(PreferenceUtils.getString(MyApplication.mContext, "token", ""), spid, shuliang, dianpuid, gouwucheid, guigeid))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        Log.e("data", data + "---");
                        for (int i = 0; i < shangpinlist.size(); i++) {
                            if (spid.equals(shangpinlist.get(i).getCommodity_id())) {
                                shangpinlist.get(i).setShopping_id(data);
                            }
                        }
                        shangpinadapter.notifyDataSetChanged();
                        ToastUtil.showToast("添加成功");

                    }
                });
    }
    private void showShaiXuanPop() {
        View view = View.inflate(mContext, R.layout.pop_shaixuan, null);
        shaixuanpop = new PopupWindow(view);
        view.setFocusable(false);
        shaixuanpop.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        shaixuanpop.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        //设置可以获取焦点，否则弹出菜单中的EditText是无法获取输入的
        shaixuanpop.setFocusable(true);
        //这句是为了防止弹出菜单获取焦点之后，点击activity的其他组件没有响应
        shaixuanpop.setBackgroundDrawable(new BitmapDrawable());
        //防止虚拟软键盘被弹出菜单遮住
        shaixuanpop.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        //在底部显示
//        shaixuanpop.showAtLocation(this,Gravity.BOTTOM, 0, 0);
        WindowManager wm1 = this.getWindowManager();
        int width = wm1.getDefaultDisplay().getWidth();
        int height = wm1.getDefaultDisplay().getHeight();

        final RecyclerView rv_yijishichang = view.findViewById(R.id.rv_yijishichang);
        final RecyclerView rv_erjishichang = view.findViewById(R.id.rv_erjishichang);
        final RecyclerView rv_sanjishichang = view.findViewById(R.id.rv_sanjishichang);

        LinearLayout ll_yijishichang = view.findViewById(R.id.ll_yijishichang);
        LinearLayout ll_erjishichang = view.findViewById(R.id.ll_erjishichang);
        LinearLayout ll_sanjishichang = view.findViewById(R.id.ll_sanjishichang);
        TextView tv_queding = view.findViewById(R.id.tv_queding);
        TextView tv_chongzhi = view.findViewById(R.id.tv_chongzhi);
        final EditText et_jiagedi = view.findViewById(R.id.et_jiagedi);
        final EditText et_jiagegao = view.findViewById(R.id.et_jiagegao);

//        et_jiagedi.setFocusable(true);
//        et_jiagegao.setFocusable(true);
        final ImageView iv_yiji = view.findViewById(R.id.iv_yiji);
        final ImageView iv_erji = view.findViewById(R.id.iv_erji);
        final ImageView iv_sanji = view.findViewById(R.id.iv_sanji);

        final ShiChangAdapter yijiadapter=new ShiChangAdapter();
        final ShiChangAdapter erjiadapter=new ShiChangAdapter();
        final ShiChangAdapter sanjiadapter=new ShiChangAdapter();

        shaixuanpop.setWidth(width);
        shaixuanpop.setHeight(height-AppUtil.dip2px(165));

        et_jiagedi.setText(zuidijia);
        et_jiagegao.setText(zuigaojia);
        tv_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zuidijia=et_jiagedi.getText().toString().trim();
                zuigaojia=et_jiagegao.getText().toString().trim();
                tvShaixuan.setText(StringUtil.isEmpty(shichangname)?"不限":shichangname);
                tvShaixuan.setTextColor(StringUtil.isEmpty(shichangname)?getResources().getColor(R.color.zicolor):getResources().getColor(R.color.zangqing));
                sousuoshangpin("0",sousuozi);
                shaixuanpop.dismiss();
            }
        });
        tv_chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_jiagedi.setText("");
                et_jiagegao.setText("");
                shichangid="";
                shichangname = "";
                yijiadapter.setXuanZhongId(shichangid);
                erjiadapter.setXuanZhongId(shichangid);
                sanjiadapter.setXuanZhongId(shichangid);
                yijiadapter.notifyDataSetChanged();
                erjiadapter.notifyDataSetChanged();
                sanjiadapter.notifyDataSetChanged();
                tvShaixuan.setText("不限");
                tvShaixuan.setTextColor(getResources().getColor(R.color.zicolor));
            }
        });


        if (shiChangBean.getOneList()!=null){
            yijiadapter.setNewData(shiChangBean.getOneList());
        }
        yijiadapter
                .setXuanZhongId(shichangid)
                .setCallBack(new ShiChangAdapter.CallBack() {
                    @Override
                    public void xuanzhong(AllShiChangBean.Bean msg) {
                        Log.e("item",gson.toJson(msg));
                        tvShaixuan.setText(msg.getMarket_name());
                        shichangid = msg.getMark_id();
                        shichangname = msg.getMarket_name();
                        tvShichang.setTextColor(getResources().getColor(R.color.zangqing));
                        yijiadapter.setXuanZhongId(shichangid);
                        erjiadapter.setXuanZhongId(shichangid);
                        sanjiadapter.setXuanZhongId(shichangid);
                        yijiadapter.notifyDataSetChanged();
                        erjiadapter.notifyDataSetChanged();
                        sanjiadapter.notifyDataSetChanged();
                    }
                });
        rv_yijishichang.setLayoutManager(new GridLayoutManager(mContext,2));
        rv_yijishichang.setAdapter(yijiadapter);

        if (shiChangBean.getTwoList()!=null){
            erjiadapter.setNewData(shiChangBean.getTwoList());
            Log.e("erji","erji");
        }
        erjiadapter
                .setXuanZhongId(shichangid)
                .setCallBack(new ShiChangAdapter.CallBack() {
                    @Override
                    public void xuanzhong(AllShiChangBean.Bean msg) {
                        Log.e("item",gson.toJson(msg));
                        tvShaixuan.setText(msg.getMarket_name());
                        shichangid = msg.getMark_id();
                        tvShichang.setTextColor(getResources().getColor(R.color.zangqing));
                        yijiadapter.setXuanZhongId(shichangid);
                        erjiadapter.setXuanZhongId(shichangid);
                        sanjiadapter.setXuanZhongId(shichangid);
                        yijiadapter.notifyDataSetChanged();
                        erjiadapter.notifyDataSetChanged();
                        sanjiadapter.notifyDataSetChanged();
                    }
                });
        rv_erjishichang.setLayoutManager(new GridLayoutManager(mContext,2));
        rv_erjishichang.setAdapter(erjiadapter);

        if (shiChangBean.getThreeList()!=null){
            Log.e("sanji","sanji");
            sanjiadapter.setNewData(shiChangBean.getThreeList());
        }
        sanjiadapter
                .setXuanZhongId(shichangid)
                .setCallBack(new ShiChangAdapter.CallBack() {
                    @Override
                    public void xuanzhong(AllShiChangBean.Bean msg) {
                        Log.e("item",gson.toJson(msg));
                        tvShaixuan.setText(msg.getMarket_name());
                        shichangid = msg.getMark_id();
                        tvShichang.setTextColor(getResources().getColor(R.color.zangqing));
                        yijiadapter.setXuanZhongId(shichangid);
                        erjiadapter.setXuanZhongId(shichangid);
                        sanjiadapter.setXuanZhongId(shichangid);
                        yijiadapter.notifyDataSetChanged();
                        erjiadapter.notifyDataSetChanged();
                        sanjiadapter.notifyDataSetChanged();
                    }
                });
        rv_sanjishichang.setLayoutManager(new  GridLayoutManager(mContext,2));
        rv_sanjishichang.setAdapter(sanjiadapter);

        ll_yijishichang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rv_yijishichang.getVisibility()==0){
                    rv_yijishichang.setVisibility(View.GONE);
                    Log.e("rv_yijishichang","一级关");
                    iv_yiji.setImageResource(R.mipmap.jinru);
                }else{
                    rv_yijishichang.setVisibility(View.VISIBLE);
                    Log.e("rv_yijishichang","一级kai ");
                    iv_yiji.setImageResource(R.mipmap.xia_kongxin_hui);

                }

            }
        });
        ll_erjishichang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rv_erjishichang.getVisibility()==0){
                    rv_erjishichang.setVisibility(View.GONE);
                    Log.e("rv_erjishichang","er级关");
                    Log.e("erjiadapter.getData().size()",erjiadapter.getData().size()+"--");
                    iv_erji.setImageResource(R.mipmap.jinru);
                }else{
                    rv_erjishichang.setVisibility(View.VISIBLE);
                    Log.e("rv_erjishichang","er级kai");
                    iv_erji.setImageResource(R.mipmap.xia_kongxin_hui);
                }
            }
        });
        ll_sanjishichang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rv_sanjishichang.getVisibility()==0){
                    rv_sanjishichang.setVisibility(View.GONE);
                    iv_sanji.setImageResource(R.mipmap.jinru);
                }else{
                    rv_sanjishichang.setVisibility(View.VISIBLE);
                    iv_sanji.setImageResource(R.mipmap.xia_kongxin_hui);
                }
            }
        });
        shaixuanpop.setOutsideTouchable(true);
        shaixuanpop.setBackgroundDrawable(new BitmapDrawable());
        shaixuanpop.showAsDropDown(llDiqu);
    }

    private void getFenLei() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getfenlei())
                .setDataListener(new HttpDataListener<List<FenLeiBean>>() {
                    @Override
                    public void onNext(List<FenLeiBean> data) {
                        isLodeFenLei = true;
                        allFenLei = new ArrayList<FenLeiBean>();
                        yijiFenLei = new ArrayList<FenLeiBean>();
                        erjiFenLei = new ArrayList<FenLeiBean>();
                        sanjiFenLei = new ArrayList<FenLeiBean>();
                        allFenLei.addAll(data);
                        FenLeiBean quanbu = new FenLeiBean();
                        quanbu.setClassify_name("全部");
                        quanbu.setClassify_grade("0");
                        quanbu.setClassify_id("");
                        yijiFenLei.add(quanbu);
                        sanjiFenLei.add(quanbu);
                        for (int i = 0; i < allFenLei.size(); i++) {
                            if ("1".equals(allFenLei.get(i).getClassify_grade())) {
                                yijiFenLei.add(allFenLei.get(i));
                            } else if ("2".equals(allFenLei.get(i).getClassify_grade())) {
                                erjiFenLei.add(allFenLei.get(i));
                            } else if ("3".equals(allFenLei.get(i).getClassify_grade())) {
                                sanjiFenLei.add(allFenLei.get(i));
                            }
                        }
                        showYiJiPop();
                    }
                }, false);
    }

    private void showYiJiPop() {
        View view = View.inflate(mContext, R.layout.pop_pinlei, null);
        final PopupWindow yijipop = new PopupWindow(view);

        WindowManager wm1 = this.getWindowManager();
        int width = wm1.getDefaultDisplay().getWidth();
        int height = wm1.getDefaultDisplay().getHeight();
        RecyclerView rv_yijifenlei = view.findViewById(R.id.rv_yijifenlei);
        yijipop.setWidth(width);
        int zhengchu = yijiFenLei.size() / 3;
        int yushu = yijiFenLei.size() % 3;
        if (yushu > 0) {
            height = (zhengchu + 1) * 55 + 20;
        } else {
            height = zhengchu * 55 + 20;
        }

        yijipop.setHeight(AppUtil.dip2px(height));
        GridLayoutManager manager = new GridLayoutManager(mContext, 3);
        rv_yijifenlei.setLayoutManager(manager);
        rv_yijifenlei.setAdapter(yijiadapter);
        yijiadapter.setNewData(yijiFenLei);
//        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                if(position==0){
//                    return 1;
//                } else {
//                    return 3;
//                }
//
//            }
//        });
        yijiadapter.setCallBack(new YiJiFenLeiAdapter.CallBack() {
            @Override
            public void xuanzhong(FenLeiBean msg) {
                Log.e("item",gson.toJson(msg));
                yijipop.dismiss();
                tvPinlei.setText(msg.getClassify_name());
                yijipinleiid = msg.getClassify_id();
                erjipinleiid="";
                erjipinleiname="";
                sanjipinleiid="";
                if (yijipinleiid.equals("0")){
                    tvPinlei.setTextColor(getResources().getColor(R.color.zicolor));
                }else{
                    tvPinlei.setTextColor(getResources().getColor(R.color.zangqing));
                    dangqianerji = new ArrayList<>();
                    for (int i = 0; i < erjiFenLei.size(); i++) {
                        if (yijipinleiid.equals(erjiFenLei.get(i).getParent_id())){
                            dangqianerji.add(erjiFenLei.get(i));
                        }
                    }
                    if (dangqianerji.size()!=0){
                        showErJiFenLei();
                    }
                }
                sousuozi = "";
                erjipinleiid = "";
                sousuoshangpin("0",sousuozi);
            }
        });

        yijipop.setOutsideTouchable(true);
        yijipop.setBackgroundDrawable(new BitmapDrawable());
        yijipop.showAsDropDown(llDiqu);
    }

    private void showErJiFenLei() {
        View view = View.inflate(mContext, R.layout.pop_ersanjifenlei, null);
        final PopupWindow yijipop = new PopupWindow(view);
        WindowManager wm1 = getWindowManager();
        int width = wm1.getDefaultDisplay().getWidth();
        int height = wm1.getDefaultDisplay().getHeight();

        RecyclerView rv_erjifenlei = view.findViewById(R.id.rv_erjifenlei);
        RecyclerView rv_sanjifenlei = view.findViewById(R.id.rv_sanjifenlei);

        yijipop.setWidth(width);
        yijipop.setHeight(height-AppUtil.dip2px(165));
        ErJiFenLeiAdapter erjiadapter = new ErJiFenLeiAdapter();
        rv_erjifenlei.setLayoutManager(new AutoLineFeedLayoutManager());
        rv_erjifenlei.setAdapter(erjiadapter);

        SanJiFenLeiAdapter sanJiFenLeiAdapter = new SanJiFenLeiAdapter();
        rv_sanjifenlei.setLayoutManager(new AutoLineFeedLayoutManager());
        rv_sanjifenlei.setAdapter(sanJiFenLeiAdapter);
        erjiadapter.setNewData(dangqianerji);
        if (dangqianerji.size()>0){
            erjipinleiid= dangqianerji.get(0).getClassify_id();
            erjipinleiname= dangqianerji.get(0).getClassify_name();

            erjiadapter.setCallBack(new ErJiFenLeiAdapter.CallBack() {
                @Override
                public void xuanzhong(FenLeiBean msg) {
                    Log.e("item",new Gson().toJson(msg));
                    tvPinlei.setText(msg.getClassify_name());
                    erjipinleiid = msg.getClassify_id();
                    erjipinleiname = msg.getClassify_name();

                    tvPinlei.setTextColor(getResources().getColor(R.color.zangqing));
                    sousuoshangpin("0",sousuozi);
                    yijipop.dismiss();
                }

            });
            ArrayList<FenLeiBean> dangqiansanji=new ArrayList<>();
            for (int i = 0; i < sanjiFenLei.size(); i++) {
                if (erjipinleiid.equals(sanjiFenLei.get(i).getParent_id())){
                    dangqiansanji.add(sanjiFenLei.get(i));
                }
            }
            sanJiFenLeiAdapter.setNewData(dangqiansanji);
            sanJiFenLeiAdapter.setCallBack(new SanJiFenLeiAdapter.CallBack() {
                @Override
                public void xuanzhong(FenLeiBean msg) {
                    Log.e("item",new Gson().toJson(msg));
                    sanjipinleiid = msg.getClassify_id();
                    tvPinlei.setTextColor(getResources().getColor(R.color.zangqing));
                    if (sanjipinleiid.equals("0")){
                        tvPinlei.setText(erjipinleiname);

                    }else{
                        tvPinlei.setText(msg.getClassify_name());

                    }
                    sousuozi = "";
                    sanjipinleiid = "";
                    sousuoshangpin("0",sousuozi);
                    yijipop.dismiss();
                }

            });
        }else{

        }


        yijipop.setOutsideTouchable(true);
        yijipop.setBackgroundDrawable(new BitmapDrawable());
        yijipop.showAsDropDown(llDiqu);
    }
    private void getshichang() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getallshichang("230000","230100"))
                .setDataListener(new HttpDataListener<AllShiChangBean>() {
                    @Override
                    public void onNext(AllShiChangBean data) {
                        shiChangBean = data;
                        showShaiXuanPop();
                    }
                }, false);
    }
}
