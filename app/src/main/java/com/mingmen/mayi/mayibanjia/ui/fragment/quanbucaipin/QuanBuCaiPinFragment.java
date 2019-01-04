package com.mingmen.mayi.mayibanjia.ui.fragment.quanbucaipin;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.AllShiChangBean;
import com.mingmen.mayi.mayibanjia.bean.FenLeiBean;
import com.mingmen.mayi.mayibanjia.bean.ShangPinSouSuoBean;
import com.mingmen.mayi.mayibanjia.bean.SouSuoJieGuoShangPinBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ErJiFenLeiAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.SanJiFenLeiAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShangPinListAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShangPinMohuAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShiChangAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.YiJiFenLeiAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.JiaRuGouWuCheDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseFragment;
import com.mingmen.mayi.mayibanjia.ui.view.AutoLineFeedLayoutManager;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.ToolLocation;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/7/16/016.
 */

public class QuanBuCaiPinFragment extends BaseFragment {


    @BindView(R.id.et_sousuozi)
    EditText etSousuozi;
    @BindView(R.id.ll_sousuo)
    LinearLayout llSousuo;
    @BindView(R.id.tv_diqu)
    TextView tvDiqu;
    @BindView(R.id.iv_shanchuzi)
    ImageView ivShanchuzi;
    @BindView(R.id.ll_diqu)
    LinearLayout llDiqu;
    @BindView(R.id.tv_pinlei)
    TextView tvPinlei;
    @BindView(R.id.ll_pinlei)
    LinearLayout llPinlei;
    @BindView(R.id.tv_shaixuan)
    TextView tvShaixuan;
    @BindView(R.id.ll_shaixuan)
    LinearLayout llShaixuan;
    @BindView(R.id.ll_shaixuankuang)
    LinearLayout llShaixuankuang;
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
    @BindView(R.id.rv_sousuo)
    RecyclerView rvSousuo;
    @BindView(R.id.ll_shangpin)
    LinearLayout llShangpin;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    private View viewSPYXFragment;
    private Context mContext;
    private ShangPinListAdapter shangpinadapter;
    private JiaRuGouWuCheDialog jiarugouwuchedialog;
    private ToolLocation location;
    private ArrayList<ShangPinSouSuoBean.ZhengchangBean> shangpinlist = new ArrayList<ShangPinSouSuoBean.ZhengchangBean>();
    private PopupWindow shaixuanpop;
    String zuidijia="";
    String zuigaojia="";
    private AllShiChangBean shiChangBean;
    private ArrayList<FenLeiBean> allFenLei = new ArrayList<>();
    private ArrayList<FenLeiBean> yijiFenLei;
    private ArrayList<FenLeiBean> erjiFenLei;
    private ArrayList<FenLeiBean> sanjiFenLei;
    private boolean isLodeFenLei = false;
    private YiJiFenLeiAdapter adapter;
    private ShangPinMohuAdapter sousuoadapter;
    private String yijipinleiid="";
    private String erjipinleiid="";
    private String sanjipinleiid="";
    private String erjipinleiname="";
    private String shichangid="";
    private boolean isdi=true;
    private String sousuo="";
    private int ye=1;
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;
    private String shichangname;
    private ArrayList<FenLeiBean> dangqianerji;
    private MainActivity activity;
    private String TAG = "QuanBuCaiPinFragment";
    public String type="0";
    private PopupWindow yijipop;
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
        xianshi("shangpin");
        etSousuozi.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    sousuo = etSousuozi.getText().toString().trim();
//                    sousuomoren(sousuo);
                    sousuoshangpin( sousuo,"0");
                    xianshi("shangpin");
                    return true;
                }
                return false;
            }
        });
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
                                        .sousuoshangpin(PreferenceUtils.getString(MyApplication.mContext, "token", ""), sousuo,"","",yijipinleiid,erjipinleiid,sanjipinleiid,shichangid,zuigaojia,zuidijia,type,ye))
                        .setDataListener(new HttpDataListener<ShangPinSouSuoBean>() {
                            @Override
                            public void onNext(final ShangPinSouSuoBean shangpin) {
                                if (shangpin.getZhengchang()!=null){
                                    shangpinlist.addAll(shangpin.getZhengchang());
                                    if (shangpin.getZhengchang().size()==5){
                                        rvShangpin.loadMoreFinish(false, true);
                                        Log.e("本次加载有数据,数据条数为","5条，后面有没有数据待定");
                                    }else if (shangpin.getZhengchang().size()>0){
                                        rvShangpin.loadMoreFinish(false, false);
                                        Log.e("本次加载有数据,数据条数为",shangpin.getZhengchang().size()+"条，后面没有数据");
                                    }else{
                                        rvShangpin.loadMoreFinish(true, false);
                                        Log.e("本次加载有数据,数据条数为","0条，后面没有数据");
                                    }
                                }
                                Log.e("当前位置",ye+"---");
                                shangpinadapter.notifyDataSetChanged();
                                ye++;
                            }
                        },false);
            }
        };

        rvShangpin.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL, false));
        rvShangpin.useDefaultLoadMore(); // 使用默认的加载更多的View。
        rvShangpin.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        rvShangpin.loadMoreFinish(false, true);

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
        if(!TextUtils.isEmpty(activity.getType())){//是否是首页传参过来的
            tvPinlei.setText(activity.getType());
//            tvPinlei.setTextColor(getResources().getColor(R.color.zangqing));
            yijipinleiid = activity.getSp_id();
            getOneList();
        } else {
            sousuoshangpin("","0");
            yijipinleiid = "";
        }
        etSousuozi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                if (s.length()>0){
//                    sousuo=s.toString().trim();
//                    sousuomoren(sousuo);
//                    xianshi("sousuo");
//                }else{
//                    xianshi("shangpin");
//                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0){
                    sousuo=s.toString().trim();
                    sousuomoren(sousuo);
                    xianshi("sousuo");
                }else{
                    xianshi("shangpin");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
//                if (s.length()>0){
//                    sousuo=s.toString().trim();
//                    sousuomoren(sousuo);
//                    xianshi("sousuo");
//                }else{
//                    xianshi("shangpin");
//                }
            }
        });
        //
    }

    private void xianshi(String sousuo){
        if ("sousuo".equals(sousuo)){
            rvSousuo.setVisibility(View.VISIBLE);
            rvShangpin.setVisibility(View.GONE);
        }else{
            rvSousuo.setVisibility(View.GONE);
            rvShangpin.setVisibility(View.VISIBLE);
        }

    }


    //商品搜索
    private void sousuoshangpin(final String sousuo, final String type) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .sousuoshangpin(PreferenceUtils.getString(MyApplication.mContext, "token", ""), sousuo,"","",yijipinleiid,erjipinleiid,sanjipinleiid,shichangid,zuigaojia,zuidijia,type,1))
                .setDataListener(new HttpDataListener<ShangPinSouSuoBean>() {
                    @Override
                    public void onNext(final ShangPinSouSuoBean shangpin) {
//                        rvShangpin.removeAllViews();
                        ye=1;
                        rvShangpin.loadMoreFinish(false, true);
                        if ("3".equals(type)){
                            isdi=true;
                        }else if ("4".equals(type)){
                            isdi=false;
                        }
                        shangpinlist.clear();
                        if (shangpin.getZhengchang()!=null){
                            shangpinlist.addAll(shangpin.getZhengchang());
                        }else{
                            ToastUtil.showToast("没有商品");
                        }

                        shangpinadapter = new ShangPinListAdapter(mContext, shangpinlist);

                        rvShangpin.setAdapter(shangpinadapter);

                        shangpinadapter.notifyDataSetChanged();
                        shangpinadapter.setOnItemClickListener(new ShangPinListAdapter.OnItemClickListener() {
                            @Override
                            public void onClick(View view,int position) {
                                switch (view.getId()) {
                                    case R.id.iv_addcar:
                                        //添加购物车
                                        final ShangPinSouSuoBean.ZhengchangBean data = shangpinlist.get(position);
                                        String spguige = "";
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

                                        jiarugouwuchedialog.showDialog(data.getInventory(),data.getClassify_name(), data.getPackStandard(), data.getRation_one() + "", data.getPrice() + ""
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
                },rvShangpin.getVisibility()==View.VISIBLE?true:false);
    }
    //商品搜索
    private void sousuomoren(String sousuozi) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .sousuomoren(PreferenceUtils.getString(MyApplication.mContext, "token", ""), sousuozi,"0"))
                .setDataListener(new HttpDataListener<ShangPinSouSuoBean>() {
                    @Override
                    public void onNext(final ShangPinSouSuoBean data) {
                        Log.e("sousuoshangpin", "sousuoshangpin");
                        shangpinlist.clear();
                        shangpinadapter.notifyDataSetChanged();
                        shangpinlist.addAll(data.getZhengchang());
                        sousuoadapter = new ShangPinMohuAdapter(mContext, data.getZhengchang());
                        rvSousuo.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                        rvSousuo.setAdapter(sousuoadapter);
                        sousuoadapter.notifyDataSetChanged();
                        sousuoadapter.setOnItemClickListener(new ShangPinMohuAdapter.OnItemClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                etSousuozi.setText(data.getZhengchang().get(position).getClassify_name());
                                sousuo = etSousuozi.getText().toString();
                                sousuoshangpin(sousuo,"0");
                                xianshi("shangpin");

                            }
                        });
                    }
                },rvShangpin.getVisibility()==View.VISIBLE?true:false);
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
        WindowManager wm1 = getActivity().getWindowManager();
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
        shaixuanpop.setHeight(height- AppUtil.dip2px(165));

        et_jiagedi.setText(zuidijia);
        et_jiagegao.setText(zuigaojia);

        tv_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zuidijia=et_jiagedi.getText().toString().trim();
                zuigaojia=et_jiagegao.getText().toString().trim();
                tvShaixuan.setText(StringUtil.isEmpty(shichangname)?"不限":shichangname);
//                tvShaixuan.setTextColor(StringUtil.isEmpty(shichangname)?getResources().getColor(R.color.zicolor):getResources().getColor(R.color.zangqing));
                sousuoshangpin(sousuo,"0");
                shaixuanpop.dismiss();
            }
        });
        tv_chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_jiagedi.setText("");
                et_jiagegao.setText("");
                shichangid="";
                shichangname="";
                yijiadapter.setXuanZhongId(shichangid);
                erjiadapter.setXuanZhongId(shichangid);
                sanjiadapter.setXuanZhongId(shichangid);
                yijiadapter.notifyDataSetChanged();
                erjiadapter.notifyDataSetChanged();
                sanjiadapter.notifyDataSetChanged();
                tvShaixuan.setText("不限");
//                tvShaixuan.setTextColor(getResources().getColor(R.color.white_80));
            }
        });


        if (shiChangBean.getOneList()!=null){
            yijiadapter.setNewData(shiChangBean.getOneList());
        }
        yijiadapter.setXuanZhongId(shichangid)
                .setCallBack(new ShiChangAdapter.CallBack() {
                    @Override
                    public void xuanzhong(AllShiChangBean.Bean msg) {
                        Log.e("item",new Gson().toJson(msg));
                        shichangname = msg.getMarket_name();
                        shichangid = msg.getMark_id();
                        tvShaixuan.setText(shichangname);
//                        tvShaixuan.setTextColor(getResources().getColor(R.color.zangqing));
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
                        Log.e("item",new Gson().toJson(msg));
                        shichangname = msg.getMarket_name();
                        shichangid = msg.getMark_id();
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
                        Log.e("item",new Gson().toJson(msg));
                        shichangname = msg.getMarket_name();
                        shichangid = msg.getMark_id();
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
        yijipop = new PopupWindow(view);

        WindowManager wm1 = getActivity().getWindowManager();
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
        GridLayoutManager manager = new GridLayoutManager(mContext,3);
        rv_yijifenlei.setLayoutManager(manager);
        rv_yijifenlei.setAdapter(adapter);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize( int position) {
                if(position==0){
                    Log.e("看到了全部",""+position);
                    return 3;
                } else{
                    return 1;
                }
            }
        });
        adapter.setNewData(yijiFenLei);
        adapter.setCallBack(new YiJiFenLeiAdapter.CallBack() {
            @Override
            public void xuanzhong(FenLeiBean msg) {
                Log.e("item",new Gson().toJson(msg));
                yijipop.dismiss();
                tvPinlei.setText(msg.getClassify_name());
                yijipinleiid = msg.getClassify_id();
                erjipinleiid="";
                erjipinleiname="";
                sanjipinleiid="";

                if (yijipinleiid.equals("")){
//                    tvPinlei.setTextColor(getResources().getColor(R.color.white_80));
                }else{
//                    tvPinlei.setTextColor(getResources().getColor(R.color.zangqing));
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
                sousuo = "";
                erjipinleiid = "";
                sousuoshangpin(sousuo,"0");

            }
        });
        yijipop.setOutsideTouchable(true);
        yijipop.setBackgroundDrawable(new BitmapDrawable());
        yijipop.showAsDropDown(llDiqu);
    }
    private void showErJiFenLei() {
        View view = View.inflate(mContext, R.layout.pop_ersanjifenlei, null);
        final PopupWindow yijipop = new PopupWindow(view);
        WindowManager wm1 = getActivity().getWindowManager();
        int width = wm1.getDefaultDisplay().getWidth();
        int height = wm1.getDefaultDisplay().getHeight();

        RecyclerView rv_erjifenlei = view.findViewById(R.id.rv_erjifenlei);
        RecyclerView rv_sanjifenlei = view.findViewById(R.id.rv_sanjifenlei);

        yijipop.setWidth(width);
        yijipop.setHeight(height-AppUtil.dip2px(165));
        final ErJiFenLeiAdapter erjiadapter = new ErJiFenLeiAdapter();
        rv_erjifenlei.setLayoutManager(new AutoLineFeedLayoutManager());
        rv_erjifenlei.setAdapter(erjiadapter);

        final SanJiFenLeiAdapter sanJiFenLeiAdapter = new SanJiFenLeiAdapter();
        rv_sanjifenlei.setLayoutManager(new AutoLineFeedLayoutManager());
        rv_sanjifenlei.setAdapter(sanJiFenLeiAdapter);
        erjiadapter.setNewData(dangqianerji);
        final ArrayList<FenLeiBean> dangqiansanji=new ArrayList<>();
        if (dangqianerji.size()>0){
            erjipinleiid= dangqianerji.get(0).getClassify_id();
            erjipinleiname= dangqianerji.get(0).getClassify_name();
            erjiadapter.setXuanzhongid(dangqianerji.get(0).getClassify_id());
            erjiadapter.notifyDataSetChanged();
            erjiadapter.setCallBack(new ErJiFenLeiAdapter.CallBack() {
                @Override
                public void xuanzhong(FenLeiBean msg) {
                    Log.e("item",new Gson().toJson(msg));
                    tvPinlei.setText(msg.getClassify_name());
                    erjipinleiid = msg.getClassify_id();
                    erjipinleiname = msg.getClassify_name();
                    erjiadapter.notifyDataSetChanged();
//                    tvPinlei.setTextColor(getResources().getColor(R.color.zangqing));
                    sousuoshangpin(sousuo,"0");
                    dangqiansanji.clear();
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
//                            tvPinlei.setTextColor(getResources().getColor(R.color.zangqing));
                            if (sanjipinleiid.equals("0")){
                                tvPinlei.setText(erjipinleiname);

                            }else{
                                tvPinlei.setText(msg.getClassify_name());
                            }
                            sanjipinleiid = "";
                            sousuoshangpin(sousuo,"0");
                            yijipop.dismiss();
                        }

                    });
//                    yijipop.dismiss();
                }

            });
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
//                    tvPinlei.setTextColor(getResources().getColor(R.color.zangqing));
                    if (sanjipinleiid.equals("0")){
                        tvPinlei.setText(erjipinleiname);

                    }else{
                        tvPinlei.setText(msg.getClassify_name());
                    }
                    sousuoshangpin(sousuo,"0");
                    yijipop.dismiss();
                }

            });
        }else{

        }


        yijipop.setOutsideTouchable(true);
        yijipop.setBackgroundDrawable(new BitmapDrawable());
        yijipop.showAsDropDown(llDiqu);
    }
    private ArrayList<FenLeiBean> shauxinSanji(){
        ArrayList<FenLeiBean> dangqiansanji=new ArrayList<>();
        for (int i = 0; i < sanjiFenLei.size(); i++) {
            if (erjipinleiid.equals(sanjiFenLei.get(i).getParent_id())){
                dangqiansanji.add(sanjiFenLei.get(i));
            }
        }
        return dangqiansanji;
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
    @OnClick({R.id.ll_diqu, R.id.ll_pinlei, R.id.ll_shaixuan,R.id.iv_shanchuzi, R.id.tv_xiaoliang, R.id.ll_jiage, R.id.tv_pingfenzuigao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_diqu:
                //地区
                break;
            case R.id.ll_pinlei:
                //品类
                if (isLodeFenLei) {
                    Log.e("是否展示了",yijipop.isShowing()+"");
                    yijipop.setFocusable(true);
                    if(yijipop.isShowing()){
                        yijipop.dismiss();
                    } else {
                        yijipop.showAsDropDown(llDiqu);
//                        showYiJiPop();
                    }
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
            case R.id.iv_shanchuzi:
                //取消
                etSousuozi.setText("");
                break;
            case R.id.tv_xiaoliang:
                ye = 1;
                //按销量排序
                if ("xiaoliang".equals(sousuo)){
                    return;
                }
                sousuo="";
                type="1";

                sousuoshangpin(sousuo,type);
                setXuanXiangColor(tvXiaoliang);
                break;
            case R.id.ll_jiage:
                ye = 1;
                //按价格升序或者降序
                sousuo="";
                if (isdi){
                    type="4";
                    sousuoshangpin(sousuo,type);
                }else{
                    type="3";
                    sousuoshangpin(sousuo,type);
                }
                setXuanXiangColor(tvJiage);
                break;
            case R.id.tv_pingfenzuigao:
                ye = 1;
                //按评分最高排序
                if ("pingfen".equals(sousuo)){
                    return;
                }
                sousuo="";
                type="2";
                sousuoshangpin(sousuo,type);
                setXuanXiangColor(tvPingfenzuigao);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ye = 1;
        etSousuozi.clearFocus();
        if(!TextUtils.isEmpty(activity.getType())){//是否是首页传参过来的
            tvPinlei.setText(activity.getType());
//            tvPinlei.setTextColor(getResources().getColor(R.color.zangqing));
            yijipinleiid = activity.getSp_id();
        } else {
            type = "0";
            tvPinlei.setText("全部");
//            tvPinlei.setTextColor(getResources().getColor(R.color.white_80));
            yijipinleiid = "";
            erjipinleiid = "";
            sanjipinleiid = "";
            tvXiaoliang.setTextColor(getResources().getColor(R.color.zicolor));
            tvJiage.setTextColor(getResources().getColor(R.color.zicolor));
            tvPingfenzuigao.setTextColor(getResources().getColor(R.color.zicolor));

        }
        shichangname = "";
        tvShaixuan.setText("不限");
//        tvShaixuan.setTextColor(getResources().getColor(R.color.white_80));
        sousuo = "";
        getOneList();
    }

    private void setXuanXiangColor(TextView bianseview){
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
                        .sousuoshangpin(PreferenceUtils.getString(MyApplication.mContext, "token", ""), sousuo,"","",yijipinleiid,erjipinleiid,sanjipinleiid,shichangid,zuigaojia,zuidijia,type,1))
                .setDataListener(new HttpDataListener<ShangPinSouSuoBean>() {
                    @Override
                    public void onNext(final ShangPinSouSuoBean shangpin) {
//                        rvShangpin.removeAllViews();
//                        ye=1;
                        rvShangpin.loadMoreFinish(false, true);
                        if ("3".equals(type)){
                            isdi=true;
                        }else if ("4".equals(type)){
                            isdi=false;
                        }
                        Log.e("sousuoshangpin", "sousuoshangpin");
                        shangpinlist.clear();
//                        shangpinadapter.notifyDataSetChanged();
                        if (shangpin.getZhengchang()!=null){
                            shangpinlist.addAll(shangpin.getZhengchang());
                        }else{
                            ToastUtil.showToast("没有商品");
                        }

                        shangpinadapter = new ShangPinListAdapter(mContext, shangpinlist);

                        rvShangpin.setAdapter(shangpinadapter);

                        shangpinadapter.notifyDataSetChanged();
                        shangpinadapter.setOnItemClickListener(new ShangPinListAdapter.OnItemClickListener() {
                            @Override
                            public void onClick(View view,int position) {
                                switch (view.getId()) {
                                    case R.id.iv_addcar:
                                        //添加购物车
                                        final ShangPinSouSuoBean.ZhengchangBean data = shangpinlist.get(position);
                                        String spguige = "";
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
                                        jiarugouwuchedialog.showDialog(data.getInventory(),data.getClassify_name(), data.getSpec_describe(), data.getRation_one() + "", data.getPrice() + ""
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
                },rvShangpin.getVisibility()==View.VISIBLE?true:false);
    }
}
