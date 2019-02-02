package com.mingmen.mayi.mayibanjia.ui.fragment.quanbucaipin;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.app.UMConfig;
import com.mingmen.mayi.mayibanjia.bean.AllShiChangBean;
import com.mingmen.mayi.mayibanjia.bean.FCGName;
import com.mingmen.mayi.mayibanjia.bean.FenLeiBean;
import com.mingmen.mayi.mayibanjia.bean.ShangPinSouSuoBean;
import com.mingmen.mayi.mayibanjia.bean.ShiChangSouSuoShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.ShouYeLeiBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.ShiChangSouSuoShangPinActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.SouSuoActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ErJiFenLeiAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.SanJiFenLeiAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShangPinListAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShangPinMohuAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShiChangAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShiChangSouSuoShangPinListAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.YiJiFenLeiAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.JiaRuGouWuCheDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseFragment;
import com.mingmen.mayi.mayibanjia.ui.fragment.quanbucaipin.adapter.QuanBuCaiPinLeiAdapter;
import com.mingmen.mayi.mayibanjia.ui.view.AutoLineFeedLayoutManager;
import com.mingmen.mayi.mayibanjia.ui.view.PageIndicatorView;
import com.mingmen.mayi.mayibanjia.ui.view.ZiXunPagingScrollHelper;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.ToolLocation;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/16/016.
 */

public class QuanBuCaiPinFragment extends BaseFragment {


    @BindView(R.id.et_sousuozi)
    TextView tvSousuozi;
    @BindView(R.id.ll_sousuo)
    LinearLayout llSousuo;
    @BindView(R.id.tv_diqu)
    TextView tvDiqu;
    //    @BindView(R.id.ll_diqu)
//    LinearLayout llDiqu;
    @BindView(R.id.tv_pinlei)
    TextView tvPinlei;
    //    @BindView(R.id.ll_pinlei)
//    LinearLayout llPinlei;
    @BindView(R.id.tv_shichang)
    TextView tvShichang;
    @BindView(R.id.tv_pinzhong)
    TextView tvPinzhong;
    //    @BindView(R.id.ll_shaixuan)
//    LinearLayout llShaixuan;
//    @BindView(R.id.ll_shaixuankuang)
//    LinearLayout llShaixuankuang;
    @BindView(R.id.tv_xiaoliang)
    TextView tvXiaoliang;
    @BindView(R.id.tv_jiage)
    TextView tvJiage;
    @BindView(R.id.ll_jiage)
    LinearLayout llJiage;
    @BindView(R.id.tv_pingfenzuigao)
    TextView tvPingfenzuigao;
    @BindView(R.id.rv_shangpin)
    SwipeMenuRecyclerView rvShangpin;
    @BindView(R.id.ll_shangpin)
    LinearLayout llShangpin;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.indicator)
    PageIndicatorView indicator;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.rv_shichangjia)
    RecyclerView rvShichangjia;
    @BindView(R.id.ll_shichang)
    LinearLayout llShichang;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.ll_shichangjia)
    LinearLayout llShichangjia;
    @BindView(R.id.view_zhanwei)
    View viewZhanwei;
    @BindView(R.id.rl_lei)
    RelativeLayout rlLei;

    private ShiChangSouSuoShangPinListAdapter shichangadapter;
    private ArrayList<ShiChangSouSuoShangPinBean> shichanglist = new ArrayList<>();
    private List<FCGName> leiBean = new ArrayList<>();
    private QuanBuCaiPinLeiAdapter leiAdapter;
    private View viewSPYXFragment;
    private Context mContext;
    private ShangPinListAdapter shangpinadapter;
    private JiaRuGouWuCheDialog jiarugouwuchedialog;
    private ToolLocation location;
    private ArrayList<ShangPinSouSuoBean.ZhengchangBean> shangpinlist = new ArrayList<ShangPinSouSuoBean.ZhengchangBean>();
    private PopupWindow shaixuanpop;
    String zuidijia = "";
    String zuigaojia = "";
    private AllShiChangBean shiChangBean;
    private ArrayList<FenLeiBean> allFenLei = new ArrayList<>();
    private ArrayList<FCGName> yijiFenLei = new ArrayList<>();
    private ArrayList<FCGName> erjiFenLei = new ArrayList<>();
    private ArrayList<FenLeiBean> sanjiFenLei;
    private boolean isLodeFenLei = false;
    private YiJiFenLeiAdapter adapter;
    private YiJiFenLeiAdapter erjiadapter;
    private ShangPinMohuAdapter sousuoadapter;
    private String yijipinleiid = "";
    private String erjipinleiid = "";
    private String sanjipinleiid = "";
    private String sanjipinleiname = "";
    private String erjipinleiname = "";
    private String shichangid = "";
    private boolean isdi = true;
    private String sousuo = "";
    private int ye = 1;
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;
    private String shichangname;
    private MainActivity activity;
    private String TAG = "QuanBuCaiPinFragment";
    public String type = "0";
    private PopupWindow yijipop;
    private ArrayList<FCGName> datas = new ArrayList<>();
    private String yclId = "346926195929448587b078e7fe613530 ";
    private String xzId = "";
    private String xzId_2 = "";
    private GridLayoutManager manager;
    private int REQUEST_CODE = 1;
    List<List<FCGName>> lei_datas = new ArrayList<>();

   //popupwindow控件绑定
    private View view;
    private ImageView ivBackPop;
    private TextView tvLablePop;
    private TextView tvPinleiPop;
    private TextView tvPinzhongLablePop;
    private TextView tvPinzhongPop;
    private TextView tvShichangLablePop;
    private TextView tvShichangPop;
    private TextView tvYiji;
    private TextView tvErji;
    private TextView tvSanji;
    private LinearLayout llPinleiPop;
    private LinearLayout llPinzhongPop;
    private LinearLayout llShichangPop;
    private LinearLayout llShichangjiaPop;
    private LinearLayout llSousuoPop;
    private ScrollView svShichang;
    private RecyclerView rvYiji;
    private RecyclerView rvErji;
    private RecyclerView rvSanji;
    private RecyclerView rv_yijifenlei;

    private ShiChangAdapter yijiAdapter = new ShiChangAdapter();
    private ShiChangAdapter erjiAdapter = new ShiChangAdapter();
    private ShiChangAdapter sanjiAdapter = new ShiChangAdapter();

    private int viewHeight;
    private int mystate = 0;
    private boolean isResult;

    @Override
    protected View getSuccessView() {
        viewSPYXFragment = View.inflate(MyApplication.mContext, R.layout.fragment_quanbucaipin, null);
        ButterKnife.bind(this, viewSPYXFragment);
        return viewSPYXFragment;
    }

    @Override
    protected void loadData() {
        mContext = getActivity();
        activity = (MainActivity) getActivity();
        if (AppUtil.isConnNet()) {
            stateLayout.showSuccessView();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getActivity().getWindow();
                //设置状态栏颜
                window.setStatusBarColor(getResources().getColor(R.color.beijing));
            }
        } else {
            stateLayout.showErrorView();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getActivity().getWindow();
                //设置状态栏颜
                window.setStatusBarColor(getResources().getColor(R.color.white));
            }
        }

        bindPop();
        getShouyeFenLei(yclId, "2");
        leiAdapter = new QuanBuCaiPinLeiAdapter(mContext, lei_datas, this);
        mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                // 该加载更多啦。

//        ... // 请求数据，并更新数据源操作。
//                sousuoshangpin(sousuozi,type);
                HttpManager.getInstance()
                        .with(mContext)
                        .setObservable(
                                RetrofitManager
                                        .getService()
                                        .sousuoshangpin(PreferenceUtils.getString(MyApplication.mContext, "token", ""), sousuo, "", "", UMConfig.YCL_ID, yijipinleiid, erjipinleiid, sanjipinleiid, shichangid, zuigaojia, zuidijia, type, ye))
                        .setDataListener(new HttpDataListener<ShangPinSouSuoBean>() {
                            @Override
                            public void onNext(final ShangPinSouSuoBean shangpin) {
                                if (shangpin.getZhengchang() != null) {
                                    shangpinlist.addAll(shangpin.getZhengchang());
                                    if (shangpin.getZhengchang().size() == 5) {
                                        rvShangpin.loadMoreFinish(false, true);
                                        Log.e("本次加载有数据,数据条数为", "5条，后面有没有数据待定");
                                    } else if (shangpin.getZhengchang().size() > 0) {
                                        rvShangpin.loadMoreFinish(false, false);
                                        Log.e("本次加载有数据,数据条数为", shangpin.getZhengchang().size() + "条，后面没有数据");
                                    } else {
                                        rvShangpin.loadMoreFinish(true, false);
                                        Log.e("本次加载有数据,数据条数为", "0条，后面没有数据");
                                    }
                                }
                                Log.e("当前位置", ye + "---");
                                shangpinadapter.notifyDataSetChanged();
                                ye++;
                            }
                        }, false);
            }
        };
        viewHeight = rlLei.getHeight();
        rvShangpin.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvShangpin.useDefaultLoadMore(); // 使用默认的加载更多的View。
        rvShangpin.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        rvShangpin.loadMoreFinish(false, true);
        rvShangpin.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if(newState==RecyclerView.SCROLL_STATE_IDLE){
                    if(mystate==1){
                        if(rlLei.getVisibility()==View.VISIBLE?false:true){
                            rlLei.setVisibility(View.VISIBLE);
                        }
                        mystate = 0;
                    } else if(mystate==2){
                        if(rlLei.getVisibility()==View.VISIBLE?true:false){
                            rlLei.setVisibility(View.GONE);
                        }
                        mystate = 0;
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView r, int dx, int dy) {
                super.onScrolled(r, dx, dy);

                if (dy+viewHeight < 0) {
                    mystate =1;
                } else if (dy-viewHeight > 0) {
                    mystate = 2;
                }
            }
        });
        refreshLayout.setColorSchemeResources(R.color.zangqing, R.color.zangqing,
                R.color.zangqing, R.color.zangqing);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ye = 1;
                getOneList();
                refreshLayout.setRefreshing(false);
            }
        });


        jiarugouwuchedialog = new JiaRuGouWuCheDialog(mContext,
                mContext.getResources().getIdentifier("BottomDialog", "style", mContext.getPackageName()));
        jiarugouwuchedialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.LEFT | Gravity.RIGHT);

        adapter = new YiJiFenLeiAdapter();
        erjiadapter = new YiJiFenLeiAdapter();
        if (!TextUtils.isEmpty(activity.getType())) {//是否是首页传参过来的
            tvPinlei.setText(activity.getType());
//            tvPinlei.setTextColor(getResources().getColor(R.color.zangqing));
            yijipinleiid = activity.getSp_id();
            getOneList();
            activity.setType("");
        } else {
            sousuoshangpin("", "0");
        }
    }


    //商品搜索
    private void sousuoshangpin(final String sousuo, final String type) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .sousuoshangpin(PreferenceUtils.getString(MyApplication.mContext, "token", ""), sousuo, "", "",UMConfig.YCL_ID, yijipinleiid, erjipinleiid, sanjipinleiid, shichangid, zuigaojia, zuidijia, type, 1))
                .setDataListener(new HttpDataListener<ShangPinSouSuoBean>() {
                    @Override
                    public void onNext(final ShangPinSouSuoBean shangpin) {
//                        rvShangpin.removeAllViews();
                        Log.e("我的数据",new Gson().toJson(shangpin));
                        rvShangpin.loadMoreFinish(false, true);
                        if ("3".equals(type)) {
                            isdi = true;
                        } else if ("4".equals(type)) {
                            isdi = false;
                        }
                        shangpinlist.clear();
                        if (shangpin.getZhengchang() != null) {
                            shangpinlist.addAll(shangpin.getZhengchang());
                        } else {
                            ToastUtil.showToast("没有商品");
                        }

                        shangpinadapter = new ShangPinListAdapter(mContext, shangpinlist);

                        rvShangpin.setAdapter(shangpinadapter);

                        shangpinadapter.notifyDataSetChanged();
                        shangpinadapter.setOnItemClickListener(new ShangPinListAdapter.OnItemClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                switch (view.getId()) {
                                    case R.id.iv_addcar:
                                        //添加购物车
                                        final ShangPinSouSuoBean.ZhengchangBean data = shangpinlist.get(position);
                                        String spguige = "";

                                        jiarugouwuchedialog.showDialog(data.getInventory(), data.getClassify_name(), data.getPackStandard(), data.getRation_one() + "", data.getPrice() + ""
                                                , data.getHostPath());
                                        final String finalSpguige = spguige;
                                        jiarugouwuchedialog.getBtQueding().setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                String shuliang = jiarugouwuchedialog.getEtShuliang().getText().toString().trim();
                                                Log.e("data.getShopping_id()", data.getShopping_id() + "---");
                                                if (Integer.parseInt(shuliang) >= Integer.parseInt(data.getRation_one())) {
                                                    addcar(data.getCommodity_id(), shuliang, data.getCompany_id(), "", finalSpguige);
                                                } else {
                                                    ToastUtil.showToast("不够起订量");
                                                }
                                                jiarugouwuchedialog.getEtShuliang().setText("0");
                                                jiarugouwuchedialog.cancel();
                                            }
                                        });
                                        break;
                                }
                            }
                        });
                    }
                }, rvShangpin.getVisibility() == View.VISIBLE ? true : false);
        ye++;
    }

    //商品搜索
    private void sousuomoren(String sousuozi) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getfcgname(PreferenceUtils.getString(MyApplication.mContext, "token", ""), sousuozi))
                .setDataListener(new HttpDataListener<List<FCGName>>() {
                    @Override
                    public void onNext(final List<FCGName> data) {
                        datas.clear();
                        datas.addAll(data);
//                        adapter.notifyDataSetChanged();
                    }
                });
    }



    private void showYiJiPop(int state) {
        if(state==1){
            showYiji();
        } else if(state==2){
            showErji();
        } else if(state==3){
            showShichang();
        }
//        recyclerView.setVisibility(View.GONE);
        yijipop.showAsDropDown(viewZhanwei);
    }

    private void setMyManager(){
        manager = new GridLayoutManager(mContext, 3);

        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 3;
                } else {
                    return 1;
                }
            }
        });
    }

    //添加购物车
    private void addcar(final String spid, String shuliang, String dianpuid, String gouwucheid, String guigeid) {
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


    private void getshichang() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getallshichang(PreferenceUtils.getString(MyApplication.mContext, "token", ""),"230000", "230100"))
                .setDataListener(new HttpDataListener<AllShiChangBean>() {
                    @Override
                    public void onNext(AllShiChangBean data) {
                        shiChangBean = data;
                        if (shiChangBean.getOneList() != null) {
                            yijiAdapter.setNewData(shiChangBean.getOneList());
                        } else {
                            tvYiji.setVisibility(View.GONE);
                        }
                        yijiAdapter.setXuanZhongId(shichangid)
                                .setCallBack(new ShiChangAdapter.CallBack() {
                                    @Override
                                    public void xuanzhong(AllShiChangBean.Bean msg) {
                                        shuaxinAdapter(msg);
                                        sousuoshangpin(sousuo, "0");
                                    }
                                });
                        rvYiji.setLayoutManager(new GridLayoutManager(mContext, 2));
                        rvYiji.setAdapter(yijiAdapter);

                        if (shiChangBean.getTwoList() != null) {
                            erjiAdapter.setNewData(shiChangBean.getTwoList());
                            Log.e("erji", "erji");
                        } else {
                            tvErji.setVisibility(View.GONE);
                        }
                        erjiAdapter
                                .setXuanZhongId(shichangid)
                                .setCallBack(new ShiChangAdapter.CallBack() {
                                    @Override
                                    public void xuanzhong(AllShiChangBean.Bean msg) {
                                        shuaxinAdapter(msg);
                                        sousuoshangpin(sousuo, "0");
                                    }
                                });
                        rvErji.setLayoutManager(new GridLayoutManager(mContext, 2));
                        rvErji.setAdapter(erjiAdapter);


                        if (shiChangBean.getThreeList() != null) {
                            Log.e("sanji", "sanji");
                            sanjiAdapter.setNewData(shiChangBean.getThreeList());
                        } else {
                            tvSanji.setVisibility(View.GONE);
                        }
                        sanjiAdapter
                                .setXuanZhongId(shichangid)
                                .setCallBack(new ShiChangAdapter.CallBack() {
                                    @Override
                                    public void xuanzhong(AllShiChangBean.Bean msg) {
                                        shuaxinAdapter(msg);
                                        sousuoshangpin(sousuo, "0");
                                    }
                                });
                        rvSanji.setLayoutManager(new GridLayoutManager(mContext, 2));
                        rvSanji.setAdapter(sanjiAdapter);
                    }
                }, false);
    }

    //    @OnClick({R.id.ll_diqu, R.id.ll_pinlei, R.id.ll_shaixuan,R.id.iv_shanchuzi, R.id.tv_xiaoliang, R.id.ll_jiage, R.id.tv_pingfenzuigao})
    @OnClick({R.id.ll_shichangjia,R.id.ll_pinzhong, R.id.ll_pinlei, R.id.ll_shichang, R.id.ll_sousuo, R.id.tv_xiaoliang, R.id.ll_jiage, R.id.tv_pingfenzuigao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            case R.id.ll_diqu:
//                //地区
//                break;
            case R.id.ll_pinlei:
                showPopOne();
                break;
            case R.id.tv_xiaoliang:
                ye = 1;
                //按销量排序
                if ("xiaoliang".equals(sousuo)) {
                    return;
                }
                sousuo = "";
                type = "1";

                sousuoshangpin(sousuo, type);
                setXuanXiangColor(tvXiaoliang);
                break;
            case R.id.ll_jiage:
                ye = 1;
                //按价格升序或者降序
                sousuo = "";
                if (isdi) {
                    type = "4";
                    sousuoshangpin(sousuo, type);
                } else {
                    type = "3";
                    sousuoshangpin(sousuo, type);
                }
                setXuanXiangColor(tvJiage);
                break;
            case R.id.tv_pingfenzuigao:
                ye = 1;
                //按评分最高排序
                if ("pingfen".equals(sousuo)) {
                    return;
                }
                sousuo = "";
                type = "2";
                sousuoshangpin(sousuo, type);
                setXuanXiangColor(tvPingfenzuigao);
                break;
            case R.id.ll_sousuo:
                rvShichangjia.setVisibility(View.GONE);
                Intent it = new Intent(mContext, SouSuoActivity.class);
                it.putExtra("sousuo",sousuo);
                startActivityForResult(it,REQUEST_CODE);
                break;
            case R.id.ll_shichang:
                showYiJiPop(3);
                break;
            case R.id.ll_pinzhong:
                showPopTwo();
                break;
            case R.id.ll_shichangjia:
                if(StringUtil.isValid(sanjipinleiid)){
                    if(rvShichangjia.getVisibility()==View.VISIBLE?true:false){
                        shichanglist.clear();
                        rvShichangjia.setVisibility(View.GONE);
                    } else {
                        sousuoshichang();
                    }
                } else {
                    ToastUtil.showToastLong("您还没有选择商品的品种");
                }


                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ye = 1;
        if (!TextUtils.isEmpty(activity.getType())) {//是否是首页传参过来的
//            tvPinlei.setText(activity.getType());
            setState();
            shichangname = "";
            sousuo = "";
            tvSousuozi.setText("");
            yijipinleiid = activity.getSp_id();
            leiAdapter.setXuanzhongId(yijipinleiid);
            xzId = yijipinleiid;
            leiAdapter.notifyDataSetChanged();
            getOneList();
            activity.setType("");
        }
        else {
            if(!isResult){
                xzId = "";
                yijipinleiid = "";
                leiAdapter.setXuanzhongId("");
                leiAdapter.notifyDataSetChanged();
                setState();
                clearPopXuanzhong();
                sousuoshangpin("","0");
            }
        }
    }

    private void setXuanXiangColor(TextView bianseview) {
        tvXiaoliang.setTextColor(getResources().getColor(R.color.lishisousuo));
        tvJiage.setTextColor(getResources().getColor(R.color.lishisousuo));
        tvPingfenzuigao.setTextColor(getResources().getColor(R.color.lishisousuo));
        bianseview.setTextColor(getResources().getColor(R.color.zangqing));
    }

    //商品搜索
    private void getOneList() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .sousuoshangpin(PreferenceUtils.getString(MyApplication.mContext, "token", ""), sousuo, "", "",UMConfig.YCL_ID, yijipinleiid, erjipinleiid, sanjipinleiid, shichangid, zuigaojia, zuidijia, type, 1))
                .setDataListener(new HttpDataListener<ShangPinSouSuoBean>() {
                    @Override
                    public void onNext(final ShangPinSouSuoBean shangpin) {
//                        rvShangpin.removeAllViews();
//                        ye=1;
                        rvShangpin.loadMoreFinish(false, true);
                        if ("3".equals(type)) {
                            isdi = true;
                        } else if ("4".equals(type)) {
                            isdi = false;
                        }
                        Log.e("sousuoshangpin", "sousuoshangpin");
                        shangpinlist.clear();
                        if (shangpin.getZhengchang() != null) {
                            shangpinlist.addAll(shangpin.getZhengchang());
                        } else {
                            ToastUtil.showToast("没有商品");
                        }

                        shangpinadapter = new ShangPinListAdapter(mContext, shangpinlist);

                        rvShangpin.setAdapter(shangpinadapter);

                        shangpinadapter.notifyDataSetChanged();
                        shangpinadapter.setOnItemClickListener(new ShangPinListAdapter.OnItemClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                switch (view.getId()) {
                                    case R.id.iv_addcar:
                                        //添加购物车
                                        final ShangPinSouSuoBean.ZhengchangBean data = shangpinlist.get(position);
                                        String spguige = "";
                                        jiarugouwuchedialog.showDialog(data.getInventory(), data.getClassify_name(), data.getSpec_describe(), data.getRation_one() + "", data.getPrice() + ""
                                                , data.getPicture_url());
                                        final String finalSpguige = spguige;
                                        jiarugouwuchedialog.getBtQueding().setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                String shuliang = jiarugouwuchedialog.getEtShuliang().getText().toString().trim();
                                                if (Integer.parseInt(shuliang) >= Integer.parseInt(data.getRation_one())) {
                                                    addcar(data.getCommodity_id(), shuliang, data.getCompany_id(), "", finalSpguige);
                                                } else {
                                                    ToastUtil.showToast("不够起订量");
                                                }
                                                jiarugouwuchedialog.getEtShuliang().setText("0");
                                                jiarugouwuchedialog.cancel();
                                            }
                                        });
                                        break;
                                }
                            }
                        });
                    }
                }, rvShangpin.getVisibility() == View.VISIBLE ? true : false);
    }

    private void getShouyeFenLei(String id, final String type) {
        HttpManager.getInstance()
                .with(getActivity())
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getFeiLei(PreferenceUtils.getString(MyApplication.mContext, "token", ""),id, type))
                .setDataListener(new HttpDataListener<List<FCGName>>() {

                    @Override
                    public void onNext(List<FCGName> list) {
                        int mysize = list == null ? 0 : list.size();
                        if (mysize != 0) {
                            FCGName quanbu = new FCGName();
                            quanbu.setClassify_name("全部");
                            quanbu.setClassify_grade("0");
                            quanbu.setClassify_id("");
                            if (type.equals("2")) {
                                leiBean.addAll(list);
                                bindZiXun();
                            } else if (type.equals("3")) {
                                yijiFenLei.clear();
                                yijiFenLei.add(quanbu);
                                yijiFenLei.addAll(list);
                                showYiJiPop(1);
                            } else if(type.equals("4")){
                                erjiFenLei.clear();
                                erjiFenLei.add(quanbu);
                                erjiFenLei.addAll(list);
                                showYiJiPop(2);
                            }
                        } else {
                            ToastUtil.showToastLong("当前类别暂无品类");
                        }
                    }
                }, false);
    }

    private void bindZiXun() {
        //滑动卡片
        int count = 0;
        int mysize = 0;
        int minsize = 0;
        mysize = leiBean.size() / 10;
        minsize = leiBean.size() % 10;
        if (minsize != 0) {
            mysize++;
        }
        for (int i = 0; i < mysize; i++) {
            List<FCGName> list = new ArrayList<>();
            if (i < mysize - 1) {
                for (int j = 0; j < 10; j++) {
                    list.add(leiBean.get(count));
                    count++;
                }
            } else {
                for (int j = 0; j < minsize; j++) {
                    list.add(leiBean.get(count));
                    count++;
                }
            }

            lei_datas.add(list);
        }

        final int horizontal = LinearLayoutManager.HORIZONTAL;
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, horizontal, false));
        leiAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(leiAdapter);
        recyclerView.setHasFixedSize(true);
        ZiXunPagingScrollHelper rp = new ZiXunPagingScrollHelper();
        rp.setIndicator(indicator);
        rp.setUpRecycleView(recyclerView);
        rp.setAdapter(leiAdapter, 1);
        rp.setOnPageChangeListener(new ZiXunPagingScrollHelper.onPageChangeListener() {
            @Override
            public void onPageChange(int index) {
                indicator.setSelectedPage(index);
            }
        });
        leiAdapter.notifyDataSetChanged();
    }

    public void setXuanzhongId(String id) {
        xzId = id;
        yijipinleiid = id;
        leiAdapter.setXuanzhongId(id);
        leiAdapter.notifyDataSetChanged();
        setState();
        sousuoshangpin("",type);
    }

    private void setState(){
//        yijipinleiid = "";
        erjipinleiid = "";
        sanjipinleiid = "";
        tvPinlei.setText("全部");
        tvPinzhong.setTextColor(getResources().getColor(R.color.zicolor));
        tvPinlei.setTextColor(getResources().getColor(R.color.zicolor));
        tvPinzhong.setText("全部");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==2){
            isResult = true;
            if(requestCode==REQUEST_CODE){
                yijipinleiid = "";
                leiAdapter.setXuanzhongId(yijipinleiid);
                leiAdapter.notifyDataSetChanged();
                setState();
                clearPopXuanzhong();
                erjipinleiid =data.getStringExtra("three_id");
                if(StringUtil.isValid(data.getStringExtra("four_id"))){
                    sanjipinleiid = data.getStringExtra("four_id");
                    sousuo = data.getStringExtra("four_name");
                    tvSousuozi.setText(sousuo);
                    sousuoshangpin("",type);
                } else {
                    erjipinleiname = data.getStringExtra("three_name");
                    sousuo = data.getStringExtra("three_name");
                    sanjipinleiid = "";
                    tvSousuozi.setText(sousuo);
                    sousuoshangpin("",type);
                }

            }
        }
        if(resultCode==3) {
            isResult = true;
            if (requestCode == REQUEST_CODE) {
                boolean b = data.getBooleanExtra("clearType",false);
                sousuo = b?"":sousuo;
                if(b){
                    clearPopXuanzhong();
                    setState();
                    yijipinleiid = "";
                    leiAdapter.setXuanzhongId(yijipinleiid);
                    leiAdapter.notifyDataSetChanged();
                    tvSousuozi.setText(sousuo);
                    sousuoshangpin(sousuo,type);
                }
            }
        }
    }
    //市场搜索
    private void sousuoshichang() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .shichangsousuoshangpin(PreferenceUtils.getString(MyApplication.mContext, "token", ""),sanjipinleiid))
                .setDataListener(new HttpDataListener<List<ShiChangSouSuoShangPinBean>>() {
                    @Override
                    public void onNext(List<ShiChangSouSuoShangPinBean> list) {
                        int mysize = list == null ? 0:list.size();
                        if(mysize!=0){
                            rvShichangjia.setVisibility(View.VISIBLE);
                        } else {
                            ToastUtil.showToastLong("该商品暂无市场报价");
                        }
                        shichanglist.clear();
                        shichanglist.addAll(list);
                        shichangadapter = new ShiChangSouSuoShangPinListAdapter(mContext, shichanglist);
                        rvShichangjia.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                        rvShichangjia.setAdapter(shichangadapter);
                        shichangadapter.setOnItemClickListener(new ShiChangSouSuoShangPinListAdapter.OnItemClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                switch (view.getId()){
                                    case R.id.tv_chakan://点击查看
                                        Intent intent=new Intent(mContext,ShiChangSouSuoShangPinActivity.class);
                                        intent.putExtra("type_tree_id",sanjipinleiid);
                                        intent.putExtra("son_number",shichanglist.get(position).getSon_number());
                                        intent.putExtras(intent);
                                        startActivity(intent);
                                        break;
                                }

                            }
                        });
                    }
                });
    }

    private void bindPop() {
        view = View.inflate(mContext, R.layout.pop_pinlei, null);
        yijipop = new PopupWindow(view);
        ivBackPop = (ImageView) view.findViewById(R.id.iv_back_pop);
        tvLablePop = (TextView) view.findViewById(R.id.tv_lable_pop);
        tvPinleiPop = (TextView) view.findViewById(R.id.tv_pinlei_pop);
        llPinleiPop = (LinearLayout) view.findViewById(R.id.ll_pinlei_pop);
        llPinzhongPop = (LinearLayout) view.findViewById(R.id.ll_pinzhong_pop);
        llShichangPop = (LinearLayout) view.findViewById(R.id.ll_shichang_pop);
        llShichangjiaPop = (LinearLayout) view.findViewById(R.id.ll_shichangjia_pop);
        llSousuoPop = (LinearLayout) view.findViewById(R.id.ll_sousuo_pop);
        tvPinzhongLablePop = (TextView) view.findViewById(R.id.tv_pinzhong_lable_pop);
        tvPinzhongPop = (TextView) view.findViewById(R.id.tv_pinzhong_pop);
        tvShichangLablePop = (TextView) view.findViewById(R.id.tv_shichang_lable_pop);
        tvShichangPop = (TextView) view.findViewById(R.id.tv_shichang_pop);
        svShichang = (ScrollView) view.findViewById(R.id.sv_shichang);
        rvYiji = (RecyclerView) view.findViewById(R.id.rv_yijishichang);
        rvErji = (RecyclerView) view.findViewById(R.id.rv_erjishichang);
        rvSanji = (RecyclerView) view.findViewById(R.id.rv_sanjishichang);
        tvYiji = (TextView) view.findViewById(R.id.tv_yiji);
        tvErji = (TextView) view.findViewById(R.id.tv_yiji);
        tvErji = (TextView) view.findViewById(R.id.tv_yiji);
        rv_yijifenlei = view.findViewById(R.id.rv_yijifenlei);
        getshichang();

        llPinleiPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showPopOne();
            }
        });
        llPinzhongPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopTwo();
            }
        });
        llShichangPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShichang();
            }
        });
        ivBackPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yijipop.dismiss();
            }
        });
        llSousuoPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yijipop.dismiss();
                startActivityForResult(new Intent(mContext, SouSuoActivity.class),REQUEST_CODE);
            }
        });
        llShichangjiaPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yijipop.dismiss();
                if(StringUtil.isValid(sanjipinleiid)){
                    if(rvShichangjia.getVisibility()==View.VISIBLE?true:false){
                        shichanglist.clear();
                        rvShichangjia.setVisibility(View.GONE);
                    } else {
                        sousuoshichang();
                    }
                } else {
                    ToastUtil.showToastLong("您还没有选择商品的品种");
                }
            }
        });

        WindowManager wm1 = getActivity().getWindowManager();
        int width = wm1.getDefaultDisplay().getWidth();
        int height = wm1.getDefaultDisplay().getHeight();
        yijipop.setWidth(width);
        yijipop.setHeight(height);
        yijipop.setClippingEnabled(false);

        yijipop.setOutsideTouchable(true);
        yijipop.setBackgroundDrawable(new BitmapDrawable());
    }

    private void showYiji(){
        setPopColor();
        tvLablePop.setTextColor(mContext.getResources().getColor(R.color.zangqing));
        rv_yijifenlei.setVisibility(View.VISIBLE);
        svShichang.setVisibility(View.GONE);
        if(StringUtil.isValid(erjipinleiname)){
            tvPinleiPop.setText(erjipinleiname);
            tvPinleiPop.setTextColor(mContext.getResources().getColor(R.color.zangqing));
        } else {
            tvPinleiPop.setText("全部");
            tvPinleiPop.setTextColor(mContext.getResources().getColor(R.color.zicolor));
        }
        rv_yijifenlei.setLayoutManager(manager);
        rv_yijifenlei.setAdapter(adapter);
        adapter.setNewData(yijiFenLei);
        adapter.setCallBack(new YiJiFenLeiAdapter.CallBack() {
            @Override
            public void xuanzhong(FCGName msg) {
                setState();
                yijipop.dismiss();
                tvPinlei.setText(msg.getClassify_name());
                erjipinleiid = msg.getClassify_id();
                sanjipinleiid = "";
                if(msg.getClassify_name().equals("全部")){
                    tvPinlei.setTextColor(getResources().getColor(R.color.zicolor));
                } else {
                    tvPinlei.setTextColor(getResources().getColor(R.color.zangqing));
                }
                xzId_2 = msg.getClassify_id();
                sousuo = "";
                recyclerView.setVisibility(View.VISIBLE);
                sousuoshangpin(sousuo, "0");
            }
        });
        setMyManager();
    }

    private void showErji(){
        setPopColor();
        rv_yijifenlei.setVisibility(View.VISIBLE);
        svShichang.setVisibility(View.GONE);
        tvPinzhongLablePop.setTextColor(mContext.getResources().getColor(R.color.zangqing));
        if(StringUtil.isValid(sanjipinleiname)){
            tvPinzhongPop.setText(sanjipinleiname);
            tvPinzhongPop.setTextColor(mContext.getResources().getColor(R.color.zangqing));
        } else {
            tvPinzhongPop.setText("全部");
            tvPinzhongPop.setTextColor(mContext.getResources().getColor(R.color.zicolor));
        }
        rv_yijifenlei.setLayoutManager(manager);
        rv_yijifenlei.setAdapter(erjiadapter);
        erjiadapter.setNewData(erjiFenLei);
            erjiadapter.notifyDataSetChanged();
        erjiadapter.setCallBack(new YiJiFenLeiAdapter.CallBack() {
            @Override
            public void xuanzhong(FCGName msg) {
                yijipop.dismiss();
                tvPinzhong.setText(msg.getClassify_name());
                sanjipinleiid = msg.getClassify_id();
                sanjipinleiname = msg.getClassify_name();
//                    erjiadapter.notifyDataSetChanged();
                if(msg.getClassify_name().equals("全部")){
                    tvPinzhong.setTextColor(getResources().getColor(R.color.zicolor));
                } else {
                    tvPinzhong.setTextColor(getResources().getColor(R.color.zangqing));
                }
                recyclerView.setVisibility(View.VISIBLE);
                sousuoshangpin(sousuo, "0");

            }
        });
        setMyManager();
    }
    private void showShichang(){
        setPopColor();
        rv_yijifenlei.setVisibility(View.GONE);
        svShichang.setVisibility(View.VISIBLE);
        tvShichangLablePop.setTextColor(mContext.getResources().getColor(R.color.zangqing));
        if(StringUtil.isValid(shichangname)){
            tvShichangPop.setText(shichangname);
            tvShichangPop.setTextColor(mContext.getResources().getColor(R.color.zangqing));
        } else {
            tvShichangPop.setText("全部");
            tvShichangPop.setTextColor(mContext.getResources().getColor(R.color.zicolor));
        }

    }
    private void shuaxinAdapter(AllShiChangBean.Bean msg){
        yijipop.dismiss();
        Log.e("item", new Gson().toJson(msg));
        shichangname = msg.getMarket_name();
        shichangid = msg.getMark_id();
        tvShichang.setText(shichangname);
        tvShichang.setTextColor(getResources().getColor(R.color.zangqing));
        yijiAdapter.setXuanZhongId(shichangid);
        erjiAdapter.setXuanZhongId(shichangid);
        sanjiAdapter.setXuanZhongId(shichangid);
        yijiAdapter.notifyDataSetChanged();
        erjiAdapter.notifyDataSetChanged();
        sanjiAdapter.notifyDataSetChanged();
    }
    private void setPopColor(){
        shichangid = "";
        shichangname = "全部";
        tvShichangPop.setText(shichangname);
        tvLablePop.setTextColor(mContext.getResources().getColor(R.color.zicolor));
        tvPinzhongLablePop.setTextColor(mContext.getResources().getColor(R.color.zicolor));
        tvShichangLablePop.setTextColor(mContext.getResources().getColor(R.color.zicolor));
    }

    private void showPopOne(){
        if(StringUtil.isValid(yijipinleiid)){
            //品类
            if (isLodeFenLei) {
                Log.e("是否展示了", yijipop.isShowing() + "");
                yijipop.setFocusable(true);
                if (yijipop.isShowing()) {
                    yijipop.dismiss();
                } else {
//                        yijipop.showAsDropDown(llShichang);
                    showYiJiPop(1);
                }
            } else {
                getShouyeFenLei(xzId, "3");
            }
        } else {
            ToastUtil.showToastLong("请先选择商品的一级分类");
        }
    }

    private void showPopTwo(){
        //品类
        if(StringUtil.isValid(erjipinleiid)){
            if (isLodeFenLei) {
                yijipop.setFocusable(true);
                if (yijipop.isShowing()) {
                    yijipop.dismiss();
                } else {
//                        yijipop.showAsDropDown(llShichang);
                    showYiJiPop(2);
                }
            } else {
                getShouyeFenLei(xzId_2, "4");
            }
        } else {
            ToastUtil.showToastLong("请先选择商品的二级分类");
        }

    }
    private void clearPopXuanzhong(){
        erjipinleiname = "全部";
        tvPinleiPop.setText("全部");
        tvPinleiPop.setTextColor(mContext.getResources().getColor(R.color.zicolor));
        adapter.setXuanzhongid("");
        adapter.notifyDataSetChanged();
        tvPinzhongPop.setText("全部");
        tvPinzhongPop.setTextColor(mContext.getResources().getColor(R.color.zicolor));
        erjiadapter.setXuanzhongid("");
        erjiadapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        isResult = false;
        rvShichangjia.setVisibility(View.GONE);
    }
}
