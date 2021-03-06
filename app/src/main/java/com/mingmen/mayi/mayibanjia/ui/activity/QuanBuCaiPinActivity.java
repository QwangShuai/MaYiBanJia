package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.app.UMConfig;
import com.mingmen.mayi.mayibanjia.bean.AllShiChangBean;
import com.mingmen.mayi.mayibanjia.bean.FCGName;
import com.mingmen.mayi.mayibanjia.bean.FenLeiBean;
import com.mingmen.mayi.mayibanjia.bean.ShangPinSouSuoBean;
import com.mingmen.mayi.mayibanjia.bean.ShangPinSousuoMohuBean;
import com.mingmen.mayi.mayibanjia.bean.ShiChangSouSuoShangPinBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShangPinListAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShiChangAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShiChangSouSuoShangPinListAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.XJSPFeiLeiGuigeAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.YiJiFenLeiAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.JiaRuGouWuCheDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.fragment.quanbucaipin.adapter.QuanBuCaiPinLeiAdapter;
import com.mingmen.mayi.mayibanjia.ui.view.PageIndicatorView;
import com.mingmen.mayi.mayibanjia.ui.view.ZiXunPagingScrollHelper;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.custom.ChatView;
import com.mingmen.mayi.mayibanjia.utils.custom.FixedHeadScrollView;
import com.mingmen.mayi.mayibanjia.utils.custom.lable.MyGridViewAdpter;
import com.mingmen.mayi.mayibanjia.utils.custom.lable.MyViewPagerAdapter;
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

public class QuanBuCaiPinActivity extends BaseActivity{

    @BindView(R.id.et_sousuozi)
    TextView tvSousuozi;
    @BindView(R.id.ll_sousuo)
    LinearLayout llSousuo;
    @BindView(R.id.tv_diqu)
    TextView tvDiqu;
    @BindView(R.id.tv_pinlei)
    TextView tvPinlei;
    @BindView(R.id.tv_shichang)
    TextView tvShichang;
    @BindView(R.id.tv_pinzhong)
    TextView tvPinzhong;
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

    private ShiChangSouSuoShangPinListAdapter shichangadapter;
    private ArrayList<ShiChangSouSuoShangPinBean> shichanglist = new ArrayList<>();
    private List<FCGName> leiBean = new ArrayList<>();
    private QuanBuCaiPinLeiAdapter leiAdapter;
    private ShangPinListAdapter shangpinadapter;
    private ArrayList<ShangPinSouSuoBean.ZhengchangBean> shangpinlist = new ArrayList<ShangPinSouSuoBean.ZhengchangBean>();
    String zuidijia = "";
    String zuigaojia = "";
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
    private String sousuo = "";
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;
    private String shichangname;
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

    private Context mContext;
    private boolean isTejia;//是否特价
    private boolean isZhunshida;//是否食时达
    private boolean isdi = true;//价格排序

    private int ye = 1;

    private String istejia;
    private String iszhunshida;

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
    private LinearLayout llShichangjiaPop;
    private LinearLayout llSousuoPop;
    private LinearLayout llGuigePop;
    private RecyclerView rvYiji;
    private RecyclerView rvErji;
    private RecyclerView rvSanji;
    private RecyclerView rv_yijifenlei;
    private RecyclerView rvGuige;

    private int viewHeight;
    private int mystate = 0;
    private XJSPFeiLeiGuigeAdapter guigeadapter;
    private List<ShangPinSousuoMohuBean> mlist = new ArrayList<>();
    private int topDistance;

    private View[] ivPoints;//小圆点图片的集合
    private int totalPage; //总的页数
    private int mPageSize = 10; //每页显示的最大的数量
    private List<View> viewPagerList;//GridView作为一个View对象添加到ViewPager集合中
    private MyViewPagerAdapter pageadapter;
    private List<MyGridViewAdpter> adpters = new ArrayList<>();
    private int dotSize = 10;
    private int margins = 10;
    LinearLayout.LayoutParams params;

    @Override
    public int getLayoutId() {
        return R.layout.activity_quan_bu_cai_pin;
    }

    @Override
    protected void initData() {
        mContext = this;
        EventBus.getDefault().register(this);
        dotSize = AppUtil.Dp2px(mContext, dotSize);
        margins = AppUtil.Dp2px(mContext, margins);
        params = new LinearLayout.LayoutParams(dotSize, dotSize);
        params.setMargins(margins, margins, margins, margins);
        bindPop();
        leiAdapter = new QuanBuCaiPinLeiAdapter(mContext, lei_datas, this);
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
                        if(manager.findFirstCompletelyVisibleItemPosition()==0){
                            if (rlLei.getVisibility() == View.VISIBLE ? false : true) {
                                rlLei.setVisibility(View.VISIBLE);
                            }
                            mystate = 0;
                        }
                    } else if (mystate == 2) {
                        if(manager.findFirstCompletelyVisibleItemPosition()>0) {
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
                Log.e(TAG, "onScrolled: "+manager.findFirstCompletelyVisibleItemPosition());
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


        shangpinadapter = new ShangPinListAdapter(mContext, shangpinlist);
        shangpinadapter.setTeshu(true);
        rvShangpin.setAdapter(shangpinadapter);

        shangpinadapter.notifyDataSetChanged();
        shangpinadapter.setOnItemClickListener(new ShangPinListAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
            }
        });
        adapter = new YiJiFenLeiAdapter();
        erjiadapter = new YiJiFenLeiAdapter();
        guigeadapter = new XJSPFeiLeiGuigeAdapter(mContext, mlist);
        sousuoshangpin("", "0");
    }


    @OnClick({R.id.ll_shichangjia, R.id.ll_pinzhong, R.id.ll_pinlei, R.id.ll_shichang,
            R.id.ll_sousuo, R.id.tv_xiaoliang, R.id.ll_jiage, R.id.tv_pingfenzuigao,
            R.id.ll_guige, R.id.tv_tejia, R.id.tv_jishida, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
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
                it.putExtra("sousuo", sousuo);
                startActivityForResult(it, REQUEST_CODE);
                break;
            case R.id.ll_shichang:
//                showYiJiPop(3);
                startActivityForResult(new Intent(mContext, ShichangXuanzeActivity.class), REQUEST_CODE);
                break;
            case R.id.ll_pinzhong:
                showPopTwo();
                break;
            case R.id.ll_guige:
                showPopThree();
                break;
            case R.id.ll_shichangjia:
                if (StringUtil.isValid(sanjipinleiid)) {
                    Intent it_jiage = new Intent(mContext,ShichangJiageActivity.class);
                    it_jiage.putExtra("id",sanjipinleiid);
                    it_jiage.putExtra("name",sanjipinleiname);
                    mContext.startActivity(it_jiage);
                } else {
                    ToastUtil.showToastLong("您还没有选择商品的品种");
                }


                break;
        }
    }

    private void updateAdapter() {
        ye = 1;
        shangpinlist.clear();
        shangpinadapter.notifyDataSetChanged();
        sousuoshangpin(sousuo, type);
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
        llShichangjiaPop = (LinearLayout) view.findViewById(R.id.ll_shichangjia_pop);
        llSousuoPop = (LinearLayout) view.findViewById(R.id.ll_sousuo_pop);
        llGuigePop = (LinearLayout) view.findViewById(R.id.ll_guige_pop);
        tvPinzhongLablePop = (TextView) view.findViewById(R.id.tv_pinzhong_lable_pop);
        tvPinzhongPop = (TextView) view.findViewById(R.id.tv_pinzhong_pop);
        rvYiji = (RecyclerView) view.findViewById(R.id.rv_yijishichang);
        rvErji = (RecyclerView) view.findViewById(R.id.rv_erjishichang);
        rvSanji = (RecyclerView) view.findViewById(R.id.rv_sanjishichang);
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
        WindowManager wm1 = getWindowManager();
        int width = wm1.getDefaultDisplay().getWidth();
        int height = wm1.getDefaultDisplay().getHeight();
        yijipop.setWidth(width);
        yijipop.setHeight(height);
        yijipop.setClippingEnabled(false);

        yijipop.setOutsideTouchable(true);
        yijipop.setBackgroundDrawable(new BitmapDrawable());
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

    private void clearPopXuanzhong() {
        erjipinleiname = "";
        shichangname = "";
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
//                recyclerView.setVisibility(View.VISIBLE);
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
                tvPinzhong.setText(msg.getClassify_name());
                sanjipinleiname = msg.getClassify_name();
                clearGuige();
                Log.e(TAG, "xuanzhong: " + new Gson().toJson(msg));
                if (msg.getClassify_name().equals("全部")) {
                    tvPinzhong.setTextColor(getResources().getColor(R.color.zicolor));
                } else {
                    tvPinzhong.setTextColor(getResources().getColor(R.color.zangqing));
                }
                sanjipinleiid = msg.getClassify_id();
                ye = 1;
//                recyclerView.setVisibility(View.VISIBLE);
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

    private void getShouyeFenLei(String id, final String type) {
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
                            FCGName quanbu = new FCGName();
                            quanbu.setClassify_name("全部");
                            quanbu.setClassify_grade("0");
                            quanbu.setClassify_id("");
                            if (type.equals("2")) {
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
            MyGridViewAdpter myGridViewAdpter = new MyGridViewAdpter(mContext, leiBean, i, mPageSize);
            adpters.add(myGridViewAdpter);
            final GridView gridView = (GridView) View.inflate(mContext, R.layout.item_grid, null);
            gridView.setAdapter(adpters.get(i));
            //添加item点击监听
            gridView.setOnItemClickListener(new GridView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {
                    // TODO Auto-generated method stub
                    Object obj = gridView.getItemAtPosition(position);
                    if (obj != null && obj instanceof FCGName) {
                        for (MyGridViewAdpter myadapter: adpters) {
                            myadapter.setXzid(((FCGName)obj).getClassify_id());
                            xzId = ((FCGName)obj).getClassify_id();
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

            points.addView(ivPoints[i],params);
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

        if(ivPoints.length>1){
            points.setVisibility(View.VISIBLE);
        } else {
            points.setVisibility(View.GONE);
        }
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
                        if (ye == 1) {
                            shangpinlist.clear();
                            shangpinadapter.notifyDataSetChanged();
                        }
                        if ("3".equals(type)) {
                            isdi = true;
                        } else if ("4".equals(type)) {
                            isdi = false;
                        }
                        if (shangpin.getZhengchang() != null) {
                            if (shangpin.getZhengchang().size() == 5) {
                                rvShangpin.loadMoreFinish(false, true);
                            } else if (shangpin.getZhengchang().size() > 0) {
                                rvShangpin.loadMoreFinish(false, false);
                            } else {
                                rvShangpin.loadMoreFinish(true, false);
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

    private void setXuanXiangColor(TextView bianseview) {
        tvXiaoliang.setTextColor(getResources().getColor(R.color.lishisousuo));
        tvJiage.setTextColor(getResources().getColor(R.color.lishisousuo));
        tvPingfenzuigao.setTextColor(getResources().getColor(R.color.lishisousuo));
        bianseview.setTextColor(getResources().getColor(R.color.zangqing));
    }

    //市场搜索
    private void sousuoshichang() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .shichangsousuoshangpin(PreferenceUtils.getString(MyApplication.mContext, "token", ""), sanjipinleiname))
                .setDataListener(new HttpDataListener<List<ShiChangSouSuoShangPinBean>>() {
                    @Override
                    public void onNext(List<ShiChangSouSuoShangPinBean> list) {
                        int mysize = list == null ? 0 : list.size();
                        if (mysize != 0) {
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
                                switch (view.getId()) {
                                    case R.id.tv_chakan://点击查看
                                        Intent intent = new Intent(mContext, ShiChangSouSuoShangPinActivity.class);
                                        intent.putExtra("type_tree_id", sanjipinleiid);
                                        intent.putExtra("type_tree_name", sanjipinleiname);
                                        intent.putExtra("son_number", shichanglist.get(position).getSon_number());
                                        intent.putExtras(intent);
                                        startActivity(intent);
                                        break;
                                }

                            }
                        });
                    }
                });
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

    private void showThreePop() {
        setPopColor();
        refreshView();
        rvGuige.setLayoutManager(new GridLayoutManager(mContext, 3));
        rvGuige.setAdapter(guigeadapter);
        guigeadapter.setCallBack(new XJSPFeiLeiGuigeAdapter.CallBack() {
            @Override
            public void xuanzhong(ShangPinSousuoMohuBean msg) {
                ye = 1;
//                recyclerView.setVisibility(View.VISIBLE);
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

    public void setXuanzhongId(String id) {
        xzId = id;
        yijipinleiid = id;
        leiAdapter.setXuanzhongId(id);
        leiAdapter.notifyDataSetChanged();
        setState();
        sousuoshangpin("", type);
    }

    private void setState() {
//        yijipinleiid = "";
        ye = 1;
        shangpinlist.clear();
        shangpinadapter.notifyDataSetChanged();
        erjipinleiid = "";
        sanjipinleiid = "";
        sanjipinleiname = "";
        tvPinlei.setText("全部");
        tvPinzhong.setTextColor(getResources().getColor(R.color.zicolor));
        tvPinlei.setTextColor(getResources().getColor(R.color.zicolor));
        tvPinzhong.setText("全部");
        tvShichang.setText("全部");
        tvGuige.setText("全部");
        tvShichang.setTextColor(getResources().getColor(R.color.zicolor));
        shichangid = "";
        shichangname = "";
        guigeId = "";
        guigeName = "";
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMarketId(AllShiChangBean.Bean bean) {
        shichangid = bean.getMark_id();
        shichangname = bean.getMarket_name();
        tvShichang.setText(bean.getMarket_name());
        if (bean.getMarket_name().equals("全部")) {
            tvShichang.setTextColor(getResources().getColor(R.color.zicolor));
        } else {
            tvShichang.setTextColor(getResources().getColor(R.color.zangqing));
        }
        updateAdapter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
