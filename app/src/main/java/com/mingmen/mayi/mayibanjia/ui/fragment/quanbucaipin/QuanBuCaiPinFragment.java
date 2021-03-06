package com.mingmen.mayi.mayibanjia.ui.fragment.quanbucaipin;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
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
import com.mingmen.mayi.mayibanjia.bean.PostShichangBean;
import com.mingmen.mayi.mayibanjia.bean.ShangPinSouSuoBean;
import com.mingmen.mayi.mayibanjia.bean.ShangPinSousuoMohuBean;
import com.mingmen.mayi.mayibanjia.bean.ShiChangSouSuoShangPinBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.ShiChangSouSuoShangPinActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.ShichangJiageActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.ShichangXuanzeActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.SouSuoActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShangPinListAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShangPinMohuAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShiChangAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShiChangSouSuoShangPinListAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.XJSPFeiLeiGuigeAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.YiJiFenLeiAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.JiaRuGouWuCheDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseFragment;
import com.mingmen.mayi.mayibanjia.ui.fragment.quanbucaipin.adapter.QuanBuCaiPinLeiOneAdapter;
import com.mingmen.mayi.mayibanjia.ui.view.PageIndicatorView;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.ToolLocation;
import com.mingmen.mayi.mayibanjia.utils.custom.lable.MyGridViewAdpter;
import com.mingmen.mayi.mayibanjia.utils.custom.lable.MyViewPagerAdapter;
import com.mingmen.mayi.mayibanjia.utils.custom.zixun.HorizontalPageLayoutManager;
import com.mingmen.mayi.mayibanjia.utils.custom.zixun.PagingScrollHelper;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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
    @BindView(R.id.ll_pop_zhanwei)
    LinearLayout llPopZhanwei;
    @BindView(R.id.tv_pingfenzuigao)
    TextView tvPingfenzuigao;
    @BindView(R.id.rv_shangpin)
    SwipeMenuRecyclerView rvShangpin;
    @BindView(R.id.ll_shangpin)
    LinearLayout llShangpin;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    //    @BindView(R.id.indicator)
//    PageIndicatorView indicator;
//    @BindView(R.id.recycler_view)
//    RecyclerView recyclerView;
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
    @BindView(R.id.ll_dingwei)
    LinearLayout llDingwei;
    @BindView(R.id.tv_shichangjia)
    TextView tvShichangjia;
    //    @BindView(R.id.ll_rv)
//    LinearLayout llRv;
    @BindView(R.id.ll_pinzhong)
    LinearLayout llPinzhong;
    @BindView(R.id.tv_guige)
    TextView tvGuige;
    @BindView(R.id.tv_tejia)
    TextView tvTejia;
    @BindView(R.id.tv_jishida)
    TextView tvJishida;
    @BindView(R.id.ll_guige)
    LinearLayout llGuige;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.points)
    LinearLayout points;

    Unbinder unbinder;

    private ShiChangSouSuoShangPinListAdapter shichangadapter;
    private ArrayList<ShiChangSouSuoShangPinBean> shichanglist = new ArrayList<>();
    private List<FCGName> leiBean = new ArrayList<>();
    //    private QuanBuCaiPinLeiAdapter leiAdapter;
    private View viewSPYXFragment;
    private Context mContext;
    private ShangPinListAdapter shangpinadapter;
    private JiaRuGouWuCheDialog jiarugouwuchedialog;
    private ArrayList<ShangPinSouSuoBean.ZhengchangBean> shangpinlist = new ArrayList<ShangPinSouSuoBean.ZhengchangBean>();
    String zuidijia = "";
    String zuigaojia = "";
    private AllShiChangBean shiChangBean;
    private ArrayList<FenLeiBean> allFenLei = new ArrayList<>();
    private ArrayList<FCGName> yijiFenLei = new ArrayList<>();
    private ArrayList<FCGName> erjiFenLei = new ArrayList<>();
    private boolean isLodeFenLei = false;
    private YiJiFenLeiAdapter adapter;
    private YiJiFenLeiAdapter erjiadapter;
    private String yijipinleiid = "";
    private String erjipinleiid = "";
    private String sanjipinleiid = "";
    private String sanjipinleiname = "";
    private String erjipinleiname = "";
    private String shichangid = "";
    private boolean isdi = true;
    private String sousuo = "";
    private String istejia = "";
    private String iszhunshida = "";
    private boolean isTejia;
    private boolean isZhunshida;
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
    private String xzId_3 = "";
    private String guigeId = "";
    private String guigeName = "";
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
    private TextView tvGuigeLablePop;
    private TextView tvGuigePop;
    private TextView tvYiji;
    private TextView tvErji;
    private TextView tvSanji;
    private LinearLayout llPinleiPop;
    private LinearLayout llPinzhongPop;
    private LinearLayout llSousuoPop;
    private LinearLayout llGuigePop;
    private RecyclerView rv_yijifenlei;
    private RecyclerView rvGuige;

    //销量筛选pop
    private PopupWindow xlPop;
    private String xlPopXuanze = "";
    private View xlPopView;
    private LinearLayout llXiaoliang;
    private LinearLayout llJgjx;
    private LinearLayout llJgsx;
    private LinearLayout llPfzg;

    private ImageView ivXiaoliang;
    private ImageView ivJgjx;
    private ImageView ivJgsx;
    private ImageView ivPfzg;

    private TextView tvXl;
    private TextView tvJgjx;
    private TextView tvJgsx;
    private TextView tvPfzg;

    private int viewHeight;
    private int mystate = 0;
    private boolean isResult;
    private XJSPFeiLeiGuigeAdapter guigeadapter;
    private List<ShangPinSousuoMohuBean> mlist = new ArrayList<>();


    private View[] ivPoints;//小圆点图片的集合
    private int totalPage; //总的页数
    private int mPageSize = 10; //每页显示的最大的数量
    private List<View> viewPagerList;//GridView作为一个View对象添加到ViewPager集合中
    private MyViewPagerAdapter pageadapter;
    private List<MyGridViewAdpter> adpters = new ArrayList<>();
    private int dotSize = 10;
    private int margins = 10;
    LinearLayout.LayoutParams params;
    private boolean isShichang;

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
        EventBus.getDefault().register(this);
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
        bindXlPop();
        dotSize = AppUtil.Dp2px(mContext, dotSize);
        margins = AppUtil.Dp2px(mContext, margins);
        params = new LinearLayout.LayoutParams(dotSize, dotSize);
        params.setMargins(margins, margins, margins, margins);
        getShouyeFenLei(yclId, "2");
        mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                sousuoshangpin(sousuo, type);
            }
        };
        viewHeight = rlLei.getHeight();
        final LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rvShangpin.setLayoutManager(manager);
        rvShangpin.useDefaultLoadMore(); // 使用默认的加载更多的View。
        rvShangpin.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        rvShangpin.loadMoreFinish(false, true);
        rvShangpin.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (mystate == 1) {
                        if (manager.findFirstCompletelyVisibleItemPosition() == 0) {
                            if (rlLei.getVisibility() == View.VISIBLE ? false : true) {
                                rlLei.setVisibility(View.VISIBLE);
                            }
                            mystate = 0;
                        }
                    } else if (mystate == 2) {
                        if (manager.findFirstCompletelyVisibleItemPosition() > 0) {
                            if (rlLei.getVisibility() == View.VISIBLE ? true : false) {
                                rlLei.setVisibility(View.GONE);
                            }
                            mystate = 0;
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView r, int dx, int dy) {
                super.onScrolled(r, dx, dy);

                if (dy + viewHeight < 0) {
                    mystate = 1;
                } else if (dy - viewHeight > 0) {
                    mystate = 2;
                }
            }
        });
        refreshLayout.setColorSchemeResources(R.color.zangqing, R.color.zangqing,
                R.color.zangqing, R.color.zangqing);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mystate = 0;
                updateAdapter();
                refreshLayout.setRefreshing(false);
                if (rlLei.getVisibility() == View.VISIBLE ? false : true) {
                    rlLei.setVisibility(View.VISIBLE);
                }
            }
        });


        jiarugouwuchedialog = new JiaRuGouWuCheDialog(mContext,
                mContext.getResources().getIdentifier("BottomDialog", "style", mContext.getPackageName()));
        jiarugouwuchedialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.LEFT | Gravity.RIGHT);
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
                                , data.getPicture_url());
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
        adapter = new YiJiFenLeiAdapter();
        erjiadapter = new YiJiFenLeiAdapter();
        guigeadapter = new XJSPFeiLeiGuigeAdapter(mContext, mlist);
        isZhunshida = activity.isZhunshida();
        isTejia = activity.isTejia();
        if (isTejia) {
            istejia = "1";
            tvTejia.setTextColor(getResources().getColor(R.color.zangqing));
        } else {
            istejia = "";
            tvTejia.setTextColor(getResources().getColor(R.color.lishisousuo));
        }
        if (isZhunshida) {
            iszhunshida = "0";
            tvJishida.setTextColor(getResources().getColor(R.color.zangqing));
        } else {
            iszhunshida = "";
            tvJishida.setTextColor(getResources().getColor(R.color.lishisousuo));
        }
        if (!TextUtils.isEmpty(activity.getType())) {//是否是首页传参过来的
            Log.e(TAG, "loadData: " + activity.getType() + "---" + activity.getSp_id());
            tvPinlei.setText(activity.getType());
//            tvPinlei.setTextColor(getResources().getColor(R.color.zangqing));
            yijipinleiid = activity.getSp_id();
            xzId = activity.getSp_id();
//            leiAdapter.setXuanzhongId(activity.getSp_id());
            updateAdapter();
//            activity.setType("");
//            setAdapterXuanzhong();
            for (MyGridViewAdpter myadapter : adpters) {
                myadapter.setXzid(xzId);
                setXuanzhongId(xzId);
                myadapter.notifyDataSetChanged();
            }
        } else {
            Log.e(TAG, "loadData: " + "啊呀呦");
            sousuoshangpin("", "0");
        }
    }


    //商品搜索
    private void sousuoshangpin(final String sousuo, final String type) {
        Log.e(TAG, "sousuoshangpin: 我是特价" + istejia + "---我是食时达" + iszhunshida);
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .sousuoshangpin(PreferenceUtils.getString(MyApplication.mContext, "token", ""), sousuo, "", "", UMConfig.YCL_ID, yijipinleiid, erjipinleiid, sanjipinleiname, shichangid, zuigaojia, zuidijia, type, ye, guigeId, iszhunshida, istejia))
                .setDataListener(new HttpDataListener<ShangPinSouSuoBean>() {
                    @Override
                    public void onNext(final ShangPinSouSuoBean shangpin) {
                        if ("3".equals(type)) {
                            isdi = true;
                        } else if ("4".equals(type)) {
                            isdi = false;
                        }
                        if (shangpin.getZhengchang() != null) {
                            if (QuanBuCaiPinFragment.this.isVisible()) {
                                if (shangpin.getZhengchang().size() == 10) {
                                    rvShangpin.loadMoreFinish(false, true);
                                } else if (shangpin.getZhengchang().size() > 0) {
                                    rvShangpin.loadMoreFinish(false, false);
                                } else {
                                    rvShangpin.loadMoreFinish(true, false);
                                }
                            }

                            shangpinlist.addAll(shangpin.getZhengchang());
                        } else {
                            ToastUtil.showToast("没有商品");
                        }
                        shangpinadapter.notifyDataSetChanged();
                        ye++;
                    }
                }, true);
    }

    private void showYiJiPop(int state) {
        if (state == 1) {
            showYiji();
        } else if (state == 2) {
            showErji();
        } else if (state == 4) {
            showThreePop();
        }
        rlLei.setVisibility(View.GONE);
        yijipop.showAsDropDown(viewZhanwei);
    }

    private void setMyManager() {
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
                        ToastUtil.showToast("添加购物车成功");
                        updateGwc();
                    }
                });
    }


    //    @OnClick({R.id.ll_diqu, R.id.ll_pinlei, R.id.ll_shaixuan,R.id.iv_shanchuzi, R.id.tv_xiaoliang, R.id.ll_jiage, R.id.tv_pingfenzuigao})
    @OnClick({R.id.ll_shichangjia, R.id.ll_pinzhong, R.id.ll_pinlei, R.id.ll_shichang,
            R.id.ll_sousuo, R.id.tv_xiaoliang, R.id.ll_jiage, R.id.tv_pingfenzuigao,
            R.id.ll_guige, R.id.tv_tejia, R.id.tv_jishida})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            case R.id.ll_diqu:
//                //地区
//                break;
            case R.id.tv_tejia://特价
                if (isTejia) {
                    isTejia = false;
                    istejia = "";
                    tvTejia.setTextColor(getResources().getColor(R.color.lishisousuo));
                } else {
                    isTejia = true;
                    istejia = "1";
                    tvTejia.setTextColor(getResources().getColor(R.color.zangqing));

                }
                updateAdapter();
                break;
            case R.id.tv_jishida://食时达
                if (isZhunshida) {
                    isZhunshida = false;
                    iszhunshida = "";
                    tvJishida.setTextColor(getResources().getColor(R.color.lishisousuo));
                } else {
                    isZhunshida = true;
                    iszhunshida = "0";
                    tvJishida.setTextColor(getResources().getColor(R.color.zangqing));
                }
                updateAdapter();
                break;
            case R.id.ll_pinlei:
                showPopOne();
                break;
            case R.id.tv_xiaoliang:
                xlPop.setFocusable(true);
                if (xlPop.isShowing()) {
                    xlPop.dismiss();
                } else {
                    xlPop.showAsDropDown(llPopZhanwei);
                }

//                ye = 1;
//                //按销量排序
//                if ("xiaoliang".equals(sousuo)) {
//                    return;
//                }
//                sousuo = "";
//                type = "1";
//
//                sousuoshangpin(sousuo, type);
//                setXuanXiangColor(tvXiaoliang);
                break;
            case R.id.ll_jiage:
                ye = 1;
                mlist.clear();
                shangpinadapter.notifyDataSetChanged();
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
                mlist.clear();
                shangpinadapter.notifyDataSetChanged();
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
                if (PreferenceUtils.getBoolean(MyApplication.mContext, "youke", false)) {
                    showDialog(mContext, UMConfig.ZHUCE_MESSAGE);
                } else {
                    rvShichangjia.setVisibility(View.GONE);
                    Intent it = new Intent(mContext, SouSuoActivity.class);
                    it.putExtra("sousuo", sousuo);
                    startActivityForResult(it, REQUEST_CODE);
                }

                break;
            case R.id.ll_shichang:
//                showYiJiPop(3);

                isShichang = true;
                Intent it_shichang = new Intent(mContext, ShichangXuanzeActivity.class);
                if (StringUtil.isValid(shichangid)) {
                    it_shichang.putExtra("id", shichangid);
                    it_shichang.putExtra("name", shichangname);
                } else {
                    it_shichang.putExtra("id", "");
                    it_shichang.putExtra("name", "全部");
                }
                startActivity(it_shichang);
//                startActivityForResult(new Intent(mContext, ShichangXuanzeActivity.class),REQUEST_CODE);

                break;
            case R.id.ll_pinzhong:
                showPopTwo();
                break;
            case R.id.ll_guige:
                showPopThree();
                break;
            case R.id.ll_shichangjia:
                if (PreferenceUtils.getBoolean(MyApplication.mContext, "youke", false)) {
                    showDialog(mContext, UMConfig.ZHUCE_MESSAGE);
                } else {
                    if (StringUtil.isValid(sanjipinleiid)) {
//                    if (rvShichangjia.getVisibility() == View.VISIBLE ? true : false) {
//                        shichanglist.clear();
//                        rvShichangjia.setVisibility(View.GONE);
//                    } else {
//                        sousuoshichang();
//                    }
                        Intent it_jiage = new Intent(mContext, ShichangJiageActivity.class);
                        it_jiage.putExtra("id", sanjipinleiid);
                        it_jiage.putExtra("name", sanjipinleiname);
                        mContext.startActivity(it_jiage);
                    } else {
                        ToastUtil.showToastLong("您还没有选择商品的品种");
                    }

                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        isZhunshida = activity.isZhunshida();
        isTejia = activity.isTejia();
        Log.e(TAG, "onResume: " + activity.getType());
        if (isTejia) {
            istejia = "1";
            tvTejia.setTextColor(getResources().getColor(R.color.zangqing));
        } else {
            istejia = "";
            tvTejia.setTextColor(getResources().getColor(R.color.lishisousuo));
        }
        if (isZhunshida) {
            iszhunshida = "0";
            tvJishida.setTextColor(getResources().getColor(R.color.zangqing));
        } else {
            iszhunshida = "";
            tvJishida.setTextColor(getResources().getColor(R.color.lishisousuo));
        }
        if (!TextUtils.isEmpty(activity.getType())) {//是否是首页传参过来的
//            tvPinlei.setText(activity.getType());
//            if(ye!=1){
            setState();
            shichangid = "";
            shichangname = "全部";
            tvShichang.setText("全部");
            tvShichang.setTextColor(getResources().getColor(R.color.zicolor));
            sousuo = "";
            tvSousuozi.setText("");
            yijipinleiid = activity.getSp_id();
//            leiAdapter.setXuanzhongId(yijipinleiid);
            xzId = yijipinleiid;
//            setAdapterXuanzhong();
            for (MyGridViewAdpter myadapter : adpters) {
                myadapter.setXzid(xzId);
                setXuanzhongId(xzId);
                myadapter.notifyDataSetChanged();
            }
            activity.setType("");
            updateAdapter();
//            }
        } else {
            if (!isShichang) {
                if (!isResult && ye != 1) {
                    tvSousuozi.setText("");
                    sousuo = "";
                    xzId = "";
                    yijipinleiid = "";
//                    setAdapterXuanzhong();
                    for (MyGridViewAdpter myadapter : adpters) {
                        myadapter.setXzid(xzId);
                        setXuanzhongId(xzId);
                        myadapter.notifyDataSetChanged();
                    }
                    shichangid = "";
                    shichangname = "全部";
                    tvShichang.setText("全部");
                    tvShichang.setTextColor(getResources().getColor(R.color.zicolor));
                    setState();
                    clearPopXuanzhong();
                    updateAdapter();
                }
            } else {
                isShichang = false;
            }
//            if(!isTejia&&!isZhunshida){

//            }
        }
//        bindZiXun();
    }

    private void setXuanXiangColor(TextView bianseview) {
        tvXiaoliang.setTextColor(getResources().getColor(R.color.lishisousuo));
        tvJiage.setTextColor(getResources().getColor(R.color.lishisousuo));
        tvPingfenzuigao.setTextColor(getResources().getColor(R.color.lishisousuo));
        bianseview.setTextColor(getResources().getColor(R.color.zangqing));
    }


    private void getShouyeFenLei(String id, final String type) {
        HttpManager.getInstance()
                .with(getActivity())
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getFeiLei(PreferenceUtils.getString(MyApplication.mContext, "token", ""), id, type))
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
                                Log.e(TAG, "onNext: " + "执行了啊");
                                leiBean.addAll(list);
                                bindZiXun();
                            } else if (type.equals("3")) {
                                Log.e(TAG, "onNext: " + "为什么没展示");
                                yijiFenLei.clear();
                                yijiFenLei.add(quanbu);
                                yijiFenLei.addAll(list);
                                showYiJiPop(1);
                            } else if (type.equals("4")) {
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
        Log.e("bindZiXun: ", "faxie");
        totalPage = (int) Math.ceil(leiBean.size() * 1.0 / mPageSize);
        viewPagerList = new ArrayList<View>();
        for (int i = 0; i < totalPage; i++) {
            //每个页面都是inflate出一个新实例
            MyGridViewAdpter myGridViewAdpter = new MyGridViewAdpter(getActivity(), leiBean, i, mPageSize);
            adpters.add(myGridViewAdpter);
            final GridView gridView = (GridView) View.inflate(getActivity(), R.layout.item_grid, null);
            gridView.setAdapter(adpters.get(i));
            //添加item点击监听
            gridView.setOnItemClickListener(new GridView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {
                    // TODO Auto-generated method stub
                    Object obj = gridView.getItemAtPosition(position);
                    if (obj != null && obj instanceof FCGName) {
                        for (MyGridViewAdpter myadapter : adpters) {
                            myadapter.setXzid(((FCGName) obj).getClassify_id());
                            xzId = ((FCGName) obj).getClassify_id();
                            setXuanzhongId(xzId);
                            myadapter.notifyDataSetChanged();
                        }
                        sousuoshangpin("", type);
                    }
                }
            });
            //每一个GridView作为一个View对象添加到ViewPager集合中
            viewPagerList.add(gridView);
        }
        pageadapter = new MyViewPagerAdapter(viewPagerList);
        //设置ViewPager适配器
        viewpager.setAdapter(pageadapter);

        //添加小圆点
        ivPoints = new View[totalPage];
        for (int i = 0; i < totalPage; i++) {
            //循坏加入点点图片组
            ivPoints[i] = new View(mContext);
            if (i == 0) {
                ivPoints[i].setBackground(getResources().getDrawable(R.drawable.fillet_solid_zangqing_20));
            } else {
                ivPoints[i].setBackground(getResources().getDrawable(R.drawable.fillet_solid_cutoff_20));
            }

//            ivPoints[i].setPadding(8, 8, 8, 8);

            points.addView(ivPoints[i], params);
        }
        //设置ViewPager的滑动监听，主要是设置点点的背景颜色的改变
        viewpager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                //currentPage = position;
                for (int i = 0; i < totalPage; i++) {
                    if (i == position) {
                        ivPoints[i].setBackground(getResources().getDrawable(R.drawable.fillet_solid_zangqing_20));
                    } else {
                        ivPoints[i].setBackground(getResources().getDrawable(R.drawable.fillet_solid_cutoff_20));
                    }
                }
            }
        });

        if (ivPoints.length > 1) {
            points.setVisibility(View.VISIBLE);
        } else {
            points.setVisibility(View.GONE);
        }
    }

    public void setXuanzhongId(String id) {
        xzId = id;
        yijipinleiid = id;
//        leiAdapter.setXuanzhongId(id);
        setState();
    }

    private void setState() {
//        yijipinleiid = "";
        ye = 1;
        shangpinlist.clear();
        shangpinadapter.notifyDataSetChanged();
        clearPopXuanzhong();
        erjipinleiid = "";
        sanjipinleiid = "";
        sanjipinleiname = "";
        tvPinlei.setText("全部");
        tvPinzhong.setTextColor(getResources().getColor(R.color.zicolor));
        tvPinlei.setTextColor(getResources().getColor(R.color.zicolor));
        tvPinzhong.setText("全部");
        tvGuige.setText("全部");
        tvPinleiPop.setText("全部");
        tvPinzhongPop.setTextColor(getResources().getColor(R.color.zicolor));
        tvPinleiPop.setTextColor(getResources().getColor(R.color.zicolor));
        tvPinzhongPop.setText("全部");
        tvGuigePop.setText("全部");
        shichangid = "";
        shichangname = "全部";
        guigeId = "";
        guigeName = "";
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "onActivityResult: " + "妖魔鬼怪");
        if (resultCode == 2) {
            isResult = true;
            if (requestCode == REQUEST_CODE) {
                yijipinleiid = "";
                xzId = "";
//                leiAdapter.setXuanzhongId(yijipinleiid);
//                setAdapterXuanzhong();
                for (MyGridViewAdpter myadapter : adpters) {
                    myadapter.setXzid(xzId);
                    setXuanzhongId(xzId);
                    myadapter.notifyDataSetChanged();
                }
                setState();
                clearPopXuanzhong();
                erjipinleiid = data.getStringExtra("three_id");

                if (StringUtil.isValid(data.getStringExtra("four_name"))) {
                    sanjipinleiid = data.getStringExtra("four_id");
                    sousuo = data.getStringExtra("four_name");
                    tvSousuozi.setText(sousuo);
//                    if (StringUtil.isValid(sanjipinleiid)) {
//                        sousuoshangpin(sousuo, type);
//                    } else {
                    sousuoshangpin(sousuo, type);
//                    }

                } else {
                    erjipinleiname = data.getStringExtra("three_name");
                    sousuo = data.getStringExtra("three_name");
                    sanjipinleiid = "";
                    tvSousuozi.setText(sousuo);
                    sousuoshangpin("", type);
                }

            }
        }
        if (resultCode == 3) {
            isResult = true;
            if (requestCode == REQUEST_CODE) {
                boolean b = data.getBooleanExtra("clearType", false);
                sousuo = b ? "" : sousuo;
                if (b) {
                    clearPopXuanzhong();
                    setState();
                    yijipinleiid = "";
                    xzId = "";
//                    leiAdapter.setXuanzhongId(yijipinleiid);
//                    setAdapterXuanzhong();
                    for (MyGridViewAdpter myadapter : adpters) {
                        myadapter.setXzid(xzId);
                        setXuanzhongId(xzId);
                        myadapter.notifyDataSetChanged();
                    }
                    tvSousuozi.setText(sousuo);
                    sousuoshangpin(sousuo, type);
                }
            }
        }
    }


    private void bindPop() {
        view = View.inflate(mContext, R.layout.pop_pinlei, null);
        yijipop = new PopupWindow(view);
        ivBackPop = (ImageView) view.findViewById(R.id.iv_back_pop);
        tvLablePop = (TextView) view.findViewById(R.id.tv_lable_pop);
        tvPinleiPop = (TextView) view.findViewById(R.id.tv_pinlei_pop);
        tvGuigePop = (TextView) view.findViewById(R.id.tv_guige_pop);
        tvGuigeLablePop = (TextView) view.findViewById(R.id.tv_guige_lable_pop);
        llPinleiPop = (LinearLayout) view.findViewById(R.id.ll_pinlei_pop);
        llPinzhongPop = (LinearLayout) view.findViewById(R.id.ll_pinzhong_pop);
        llSousuoPop = (LinearLayout) view.findViewById(R.id.ll_sousuo_pop);
        llGuigePop = (LinearLayout) view.findViewById(R.id.ll_guige_pop);
        tvPinzhongLablePop = (TextView) view.findViewById(R.id.tv_pinzhong_lable_pop);
        tvPinzhongPop = (TextView) view.findViewById(R.id.tv_pinzhong_pop);
        tvYiji = (TextView) view.findViewById(R.id.tv_yiji);
        tvErji = (TextView) view.findViewById(R.id.tv_yiji);
        rv_yijifenlei = view.findViewById(R.id.rv_yijifenlei);
        rvGuige = view.findViewById(R.id.rv_guige);


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
        llGuigePop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopThree();
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
                startActivityForResult(new Intent(mContext, SouSuoActivity.class), REQUEST_CODE);
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

    private void showYiji() {
        setPopColor();
        tvLablePop.setTextColor(mContext.getResources().getColor(R.color.zangqing));
        rv_yijifenlei.setVisibility(View.VISIBLE);
        refreshView();
        setMyManager();
        rv_yijifenlei.setLayoutManager(manager);
        rv_yijifenlei.setAdapter(adapter);
        adapter.setNewData(yijiFenLei);
        adapter.notifyDataSetChanged();
        adapter.setCallBack(new YiJiFenLeiAdapter.CallBack() {
            @Override
            public void xuanzhong(FCGName msg) {
                yijipop.dismiss();
                shangpinlist.clear();
                shangpinadapter.notifyDataSetChanged();
                tvPinlei.setText(msg.getClassify_name());
                erjipinleiid = msg.getClassify_id();
                clearPinzhong();
                clearGuige();
                erjipinleiname = msg.getClassify_name();

                if (msg.getClassify_name().equals("全部")) {
                    tvPinlei.setTextColor(getResources().getColor(R.color.zicolor));
                } else {
                    tvPinlei.setTextColor(getResources().getColor(R.color.zangqing));
                }
                xzId_2 = msg.getClassify_id();
                sousuo = "";
                rlLei.setVisibility(View.VISIBLE);
                ye = 1;
                shangpinlist.clear();
                shangpinadapter.notifyDataSetChanged();
                sousuoshangpin(sousuo, "0");
            }
        });

    }

    private void showErji() {
        setPopColor();
        rv_yijifenlei.setVisibility(View.VISIBLE);
        tvPinzhongLablePop.setTextColor(mContext.getResources().getColor(R.color.zangqing));
        refreshView();
        rv_yijifenlei.setLayoutManager(manager);
        rv_yijifenlei.setAdapter(erjiadapter);
        erjiadapter.setNewData(erjiFenLei);
        erjiadapter.notifyDataSetChanged();
        erjiadapter.setCallBack(new YiJiFenLeiAdapter.CallBack() {
            @Override
            public void xuanzhong(FCGName msg) {
                yijipop.dismiss();
                shangpinlist.clear();
                shangpinadapter.notifyDataSetChanged();
                tvPinzhong.setText(msg.getClassify_name());
                if (msg.getClassify_name().equals("全部")) {
                    sanjipinleiname = "";
                } else {
                    sanjipinleiname = msg.getClassify_name();
                }

                clearGuige();
                Log.e(TAG, "xuanzhong: " + new Gson().toJson(msg));
                if (msg.getClassify_name().equals("全部")) {
                    tvPinzhong.setTextColor(getResources().getColor(R.color.zicolor));
                } else {
                    tvPinzhong.setTextColor(getResources().getColor(R.color.zangqing));
                }
                sanjipinleiid = msg.getClassify_id();
                rlLei.setVisibility(View.VISIBLE);
                ye = 1;
                shangpinlist.clear();
                shangpinadapter.notifyDataSetChanged();
                sousuoshangpin(sousuo, "0");

            }
        });
        setMyManager();
    }


    private void setPopColor() {
        if (rvGuige.getVisibility() == View.VISIBLE) {
            rv_yijifenlei.setVisibility(View.VISIBLE);
            rvGuige.setVisibility(View.GONE);
        }
        ye = 1;
        tvLablePop.setTextColor(mContext.getResources().getColor(R.color.zicolor));
        tvPinzhongLablePop.setTextColor(mContext.getResources().getColor(R.color.zicolor));
        tvGuigeLablePop.setTextColor(mContext.getResources().getColor(R.color.zicolor));
    }

    private void showPopOne() {
        if (StringUtil.isValid(yijipinleiid)) {
            //品类
            if (isLodeFenLei) {
                Log.e("是否展示了", yijipop.isShowing() + "");
                yijipop.setFocusable(true);
                if (yijipop.isShowing()) {
                    yijipop.dismiss();
                } else {
                    Log.e(TAG, "showPopOne: " + "开始展示 但是没刷新吗");
                    showYiJiPop(1);
                }
            } else {
                getShouyeFenLei(xzId, "3");
            }
        } else {
            ToastUtil.showToastLong("请先选择商品的一级分类");
        }
    }

    private void showPopTwo() {
        //品类
        if (StringUtil.isValid(erjipinleiid)) {
            if (isLodeFenLei) {
                yijipop.setFocusable(true);
                if (yijipop.isShowing()) {
                    yijipop.dismiss();
                } else {
                    showYiJiPop(2);
                }
            } else {
                getShouyeFenLei(xzId_2, "4");
            }
        } else {
            ToastUtil.showToastLong("请先选择商品的二级分类");
        }

    }

    private void showPopThree() {
        //规格
        if (StringUtil.isValid(sanjipinleiname)) {
            if (isLodeFenLei) {
                yijipop.setFocusable(true);
                if (yijipop.isShowing()) {
                    yijipop.dismiss();
                } else {
                    showYiJiPop(4);
                }
            } else {
                getGuige();
            }
        } else {
            ToastUtil.showToastLong("请先选择商品的品种");
        }

    }

    private void clearPopXuanzhong() {
        erjipinleiname = "";
        shichangname = "全部";
        tvPinleiPop.setText("全部");
        tvPinleiPop.setTextColor(mContext.getResources().getColor(R.color.zicolor));
        adapter.setXuanzhongid("");
        adapter.notifyDataSetChanged();
        tvPinzhongPop.setText("全部");
        tvPinzhongPop.setTextColor(mContext.getResources().getColor(R.color.zicolor));
        erjiadapter.setXuanzhongid("");
        erjiadapter.notifyDataSetChanged();
        tvPinzhongPop.setTextColor(mContext.getResources().getColor(R.color.zicolor));
    }

    @Override
    public void onPause() {
        super.onPause();
        activity.setTejia(false);
        activity.setZhunshida(false);
        rvShichangjia.setVisibility(View.GONE);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void getGuige() {
        mlist.clear();
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .searchSpname(PreferenceUtils.getString(MyApplication.mContext, "token", ""), xzId_2, sanjipinleiname))
                .setDataListener(new HttpDataListener<List<ShangPinSousuoMohuBean>>() {
                    @Override
                    public void onNext(List<ShangPinSousuoMohuBean> data) {
                        int mysize = data == null ? 0 : data.size();
                        ShangPinSousuoMohuBean bean = new ShangPinSousuoMohuBean();
                        bean.setClassify_id("");
                        bean.setClassify_name("全部");
                        mlist.add(bean);
                        if (mysize != 0) {
                            mlist.addAll(data);
                        }
                        guigeadapter.notifyDataSetChanged();
                        showYiJiPop(4);
                    }
                }, false);
    }

    private void showThreePop() {
        setPopColor();
        refreshView();
        rvGuige.setLayoutManager(new GridLayoutManager(mContext, 3));
        rvGuige.setAdapter(guigeadapter);
        guigeadapter.setCallBack(new XJSPFeiLeiGuigeAdapter.CallBack() {
            @Override
            public void xuanzhong(ShangPinSousuoMohuBean msg) {
                ye = 1;
                mlist.clear();
                shangpinadapter.notifyDataSetChanged();
                rlLei.setVisibility(View.VISIBLE);
                yijipop.dismiss();
                if (msg.getClassify_name().equals("全部")) {
                    tvGuige.setTextColor(getResources().getColor(R.color.zicolor));
                    guigeId = "";
                } else {
                    tvGuige.setTextColor(getResources().getColor(R.color.zangqing));
                    guigeId = msg.getSpec_idFour();
                }
                guigeadapter.setXuanzhongid(msg.getClassify_id());
                tvGuige.setText(msg.getClassify_name());
                guigeName = msg.getClassify_name();
                xzId_3 = msg.getClassify_id();

                Log.e("xuanzhong: guigeId=", guigeId);
                guigeadapter.notifyDataSetChanged();
                ye = 1;
                shangpinlist.clear();
                shangpinadapter.notifyDataSetChanged();
                sousuoshangpin(sousuo, "0");
            }
        });
        if (rvGuige.getVisibility() == View.GONE) {
            rv_yijifenlei.setVisibility(View.GONE);
            rvGuige.setVisibility(View.VISIBLE);
        }
        tvGuigeLablePop.setTextColor(mContext.getResources().getColor(R.color.zangqing));
        setMyManager();
    }

    private void clearPinzhong() {
        tvPinzhongPop.setText("全部");
        tvPinzhongPop.setTextColor(mContext.getResources().getColor(R.color.zicolor));
        tvPinzhong.setText("全部");
        tvPinzhong.setTextColor(mContext.getResources().getColor(R.color.zicolor));
        erjiadapter.setXuanzhongid("");
        sanjipinleiid = "";
        sanjipinleiname = "";
    }

    private void clearGuige() {
        tvGuigePop.setText("全部");
        tvGuigePop.setTextColor(mContext.getResources().getColor(R.color.zicolor));
        tvGuige.setText("全部");
        tvGuige.setTextColor(mContext.getResources().getColor(R.color.zicolor));
        guigeadapter.setXuanzhongid("");
        xzId_3 = "";
        guigeId = "";
        guigeName = "";
    }

    private void refreshView() {
        if (StringUtil.isValid(erjipinleiname)) {
            tvPinleiPop.setText(erjipinleiname);
            tvPinleiPop.setTextColor(mContext.getResources().getColor(R.color.zangqing));
        } else {
            tvPinleiPop.setText("全部");
            tvPinleiPop.setTextColor(mContext.getResources().getColor(R.color.zicolor));
        }

        if (StringUtil.isValid(sanjipinleiname)) {
            tvPinzhongPop.setText(sanjipinleiname);
            tvPinzhongPop.setTextColor(mContext.getResources().getColor(R.color.zangqing));
        } else {
            tvPinzhongPop.setText("全部");
            tvPinzhongPop.setTextColor(mContext.getResources().getColor(R.color.zicolor));
        }

        if (StringUtil.isValid(guigeName)) {
            tvGuigePop.setText(guigeName);
            tvGuigePop.setTextColor(mContext.getResources().getColor(R.color.zangqing));
        } else {
            tvGuigePop.setText("全部");
            tvGuigePop.setTextColor(mContext.getResources().getColor(R.color.zicolor));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getShouye(Map<String, Boolean> map) {
        Log.e(TAG, "getShouye: " + "好嗨鼬");
        for (String s : map.keySet()) {
            if (s.equals("isTejia")) {
                isTejia = true;
                istejia = "1";
                sousuoshangpin(sousuo, type);
            }
            if (s.equals("isZhunshida")) {
                isZhunshida = true;
                iszhunshida = "0";
                sousuoshangpin(sousuo, type);
            }
        }
    }

    private void updateAdapter() {
        ye = 1;
        shangpinlist.clear();
        shangpinadapter.notifyDataSetChanged();
        sousuoshangpin(sousuo, type);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMarketId(AllShiChangBean.Bean bean) {
        shichangid = bean.getMark_id();
        shichangname = bean.getMarket_name();
        tvShichang.setText(bean.getMarket_name());
        Log.e(TAG, "getMarketId: " + bean.getMark_id());
        if (bean.getMarket_name().equals("全部")) {
            tvShichang.setTextColor(getResources().getColor(R.color.zicolor));
        } else {
            tvShichang.setTextColor(getResources().getColor(R.color.zangqing));
        }
        updateAdapter();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (!isShichang) {
            Log.e(TAG, "onStop: " + "走了啊兄弟");
            isResult = false;
            viewpager.setCurrentItem(0);
            xzId = "";
            setAdapterXuanzhong();
        }
    }

    private void bindXlPop() {
        xlPopView = View.inflate(mContext, R.layout.item_shaixuan_qbcp, null);
        xlPop = new PopupWindow(xlPopView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llXiaoliang = (LinearLayout) xlPopView.findViewById(R.id.ll_xiaoliang);
        llJgjx = (LinearLayout) xlPopView.findViewById(R.id.ll_jgjx);
        llJgsx = (LinearLayout) xlPopView.findViewById(R.id.ll_jgsx);
        llPfzg = (LinearLayout) xlPopView.findViewById(R.id.ll_pfzg);

        ivXiaoliang = (ImageView) xlPopView.findViewById(R.id.iv_xiaoliang);
        ivJgjx = (ImageView) xlPopView.findViewById(R.id.iv_jgjx);
        ivJgsx = (ImageView) xlPopView.findViewById(R.id.iv_jgsx);
        ivPfzg = (ImageView) xlPopView.findViewById(R.id.iv_pfzg);

        tvXl = (TextView) xlPopView.findViewById(R.id.tv_xiaoliang);
        tvJgjx = (TextView) xlPopView.findViewById(R.id.tv_jgjx);
        tvJgsx = (TextView) xlPopView.findViewById(R.id.tv_jgsx);
        tvPfzg = (TextView) xlPopView.findViewById(R.id.tv_pfzg);

        llXiaoliang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearXlPopShow();
                ivXiaoliang.setVisibility(View.VISIBLE);
                tvXl.setTextColor(mContext.getResources().getColor(R.color.zangqing));
                tvXiaoliang.setText("销量最高");
                tvXiaoliang.setTextColor(mContext.getResources().getColor(R.color.zangqing));
                ye = 1;
                shangpinlist.clear();
                shangpinadapter.notifyDataSetChanged();
                //按销量排序
                if ("xiaoliang".equals(sousuo)) {
                    return;
                }
                sousuo = "";
                type = "1";

                sousuoshangpin(sousuo, type);
//                setXuanXiangColor(tvXiaoliang);
            }
        });

        llJgsx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearXlPopShow();
                ivJgsx.setVisibility(View.VISIBLE);
                tvJgsx.setTextColor(mContext.getResources().getColor(R.color.zangqing));
                tvXiaoliang.setText("价格升序");
                tvXiaoliang.setTextColor(mContext.getResources().getColor(R.color.zangqing));
                ye = 1;
                shangpinlist.clear();
                shangpinadapter.notifyDataSetChanged();
                //按价格升序或者降序
                sousuo = "";
                type = "4";
                sousuoshangpin(sousuo, type);
//                setXuanXiangColor(tvJiage);
            }
        });

        llJgjx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearXlPopShow();
                ivJgjx.setVisibility(View.VISIBLE);
                tvJgjx.setTextColor(mContext.getResources().getColor(R.color.zangqing));
                tvXiaoliang.setText("价格降序");
                tvXiaoliang.setTextColor(mContext.getResources().getColor(R.color.zangqing));
                ye = 1;
                shangpinlist.clear();
                shangpinadapter.notifyDataSetChanged();
                //按价格升序或者降序
                sousuo = "";
                type = "3";
                sousuoshangpin(sousuo, type);
//                setXuanXiangColor(tvJiage);
            }
        });

        llPfzg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearXlPopShow();
                ivPfzg.setVisibility(View.VISIBLE);
                tvPfzg.setTextColor(mContext.getResources().getColor(R.color.zangqing));
                tvXiaoliang.setText("评分最高");
                tvXiaoliang.setTextColor(mContext.getResources().getColor(R.color.zangqing));
                ye = 1;
                shangpinlist.clear();
                shangpinadapter.notifyDataSetChanged();
                //按评分最高排序
                if ("pingfen".equals(sousuo)) {
                    return;
                }
                sousuo = "";
                type = "2";
                sousuoshangpin(sousuo, type);
//                setXuanXiangColor(tvPingfenzuigao);
            }
        });

//        WindowManager wm1 = getActivity().getWindowManager();
//        int width = wm1.getDefaultDisplay().getWidth();
//        int height = wm1.getDefaultDisplay().getHeight();
//        xlPop.setWidth(width);
//        xlPop.setHeight(xlPopView.getHeight());
        xlPop.setClippingEnabled(false);

        xlPop.setOutsideTouchable(true);
        xlPop.setBackgroundDrawable(new BitmapDrawable());
    }

    private void clearXlPopShow() {

        xlPop.dismiss();

        ivXiaoliang.setVisibility(View.GONE);
        ivPfzg.setVisibility(View.GONE);
        ivJgsx.setVisibility(View.GONE);
        ivJgjx.setVisibility(View.GONE);

        tvXl.setTextColor(mContext.getResources().getColor(R.color.lishisousuo));
        tvPfzg.setTextColor(mContext.getResources().getColor(R.color.lishisousuo));
        tvJgsx.setTextColor(mContext.getResources().getColor(R.color.lishisousuo));
        tvJgjx.setTextColor(mContext.getResources().getColor(R.color.lishisousuo));
    }

    private void setAdapterXuanzhong() {
        Log.e(TAG, "setAdapterXuanzhong: " + "难受啊兄弟");
        for (MyGridViewAdpter myadapter : adpters) {
            myadapter.setXzid(xzId);
            setXuanzhongId(xzId);
            myadapter.notifyDataSetChanged();
        }
        sousuoshangpin("", type);
    }
}
