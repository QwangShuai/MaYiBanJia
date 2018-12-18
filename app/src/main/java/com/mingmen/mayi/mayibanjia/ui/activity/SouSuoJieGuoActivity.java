package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.AllShiChangBean;
import com.mingmen.mayi.mayibanjia.bean.DianPuBean;
import com.mingmen.mayi.mayibanjia.bean.FenLeiBean;
import com.mingmen.mayi.mayibanjia.bean.ShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.ShangPinSouSuoBean;
import com.mingmen.mayi.mayibanjia.bean.ShiChangBean;
import com.mingmen.mayi.mayibanjia.bean.ShiChangSouSuoShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.SouSuoJieGuoShangPinBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.DianPuListAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ErJiFenLeiAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.SanJiFenLeiAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShangPinListAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShiChangAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShiChangSouSuoShangPinListAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.YiJiFenLeiAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.JiaRuGouWuCheDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.view.AutoLineFeedLayoutManager;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.ToolLocation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.sephiroth.android.library.easing.Linear;

/**
 * Created by Administrator on 2018/7/16/016.
 */

public class SouSuoJieGuoActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_sousuozi)
    TextView tvSousuozi;
    @BindView(R.id.ll_sousuo)
    LinearLayout llSousuo;
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
    @BindView(R.id.iv_tu)
    ImageView ivTu;
    @BindView(R.id.rv_shichang)
    RecyclerView rvShichang;
    @BindView(R.id.ll_shichang)
    LinearLayout llShichang;
    @BindView(R.id.rv_dianpulist)
    RecyclerView rvDianpulist;
    @BindView(R.id.ll_dianpu)
    LinearLayout llDianpu;
    @BindView(R.id.ll_diqu)
    LinearLayout llDiqu;
    @BindView(R.id.ll_shaixuankuang)
    LinearLayout llShaixuankuang;
    @BindView(R.id.ll_pinlei)
    LinearLayout llPinlei;
    @BindView(R.id.ll_shaixuan)
    LinearLayout llShaixuan;
    @BindView(R.id.tv_xiaoliang)
    TextView tvXiaoliang;
    @BindView(R.id.tv_jiage)
    TextView tvJiage;
    @BindView(R.id.ll_jiage)
    LinearLayout llJiage;
    @BindView(R.id.tv_pingfenzuigao)
    TextView tvPingfenzuigao;
    @BindView(R.id.rv_shangpin)
    RecyclerView rvShangpin;
    @BindView(R.id.ll_shangpin)
    LinearLayout llShangpin;
    @BindView(R.id.tv_diqu)
    TextView tvDiqu;
    @BindView(R.id.tv_pinlei)
    TextView tvPinlei;
    @BindView(R.id.tv_shaixuan)
    TextView tvShaixuan;
    private String sousuo="";
    private String sousuofangxiang;
    private Context mContext;
    private DianPuListAdapter dianpuadapter;
    private ShiChangSouSuoShangPinListAdapter shichangadapter;
    private ShangPinListAdapter shangpinadapter;
    private JiaRuGouWuCheDialog jiarugouwuchedialog;
    private String spguige;
    private ArrayList<ShiChangSouSuoShangPinBean> shichanglist;
    private ArrayList<DianPuBean> dianpulist = new ArrayList<>();
    private ArrayList<ShangPinSouSuoBean.ZhengchangBean> shangpinlist = new ArrayList<>();
    private ArrayList<FenLeiBean> allFenLei = new ArrayList<>();
    private ArrayList<FenLeiBean> yijiFenLei;
    private ArrayList<FenLeiBean> erjiFenLei;
    private ArrayList<FenLeiBean> sanjiFenLei;
    private boolean isLodeFenLei = false;
    private YiJiFenLeiAdapter yijiadapter;
    private String yijipinleiid;
    private String erjipinleiid;
    private String sanjipinleiid;
    private String erjipinleiname;
    private String shichangid="";
    private AllShiChangBean shiChangBean;
    private ToolLocation location;
    String zuigaojia="";
    String zuidijia="";
    private PopupWindow shaixuanpop;
    private boolean isdi;
    private String type_tree_id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_sousuojieguo;
    }

    @Override
    protected void initData() {

        mContext = SouSuoJieGuoActivity.this;
        tvSousuozi.setText(sousuo);
        sousuofangxiang = getIntent().getStringExtra("sousuofangxiang");
        if ("shichang".equals(sousuofangxiang)) {
            type_tree_id = getIntent().getStringExtra("type_tree_id");
            shichangxianshi();
            dianpuyincang();
            shangpinyincang();
            sousuoshichang();
        } else if ("dianpu".equals(sousuofangxiang)) {
            sousuo = getIntent().getStringExtra("sousuo");
            dianpuxianshi();
            shichangyincang();
            shangpinyincang();
            sousuodianpu();
        } else if ("shangpin".equals(sousuofangxiang)) {
            sousuo = getIntent().getStringExtra("sousuo");
            shangpinxianshi();
            dianpuyincang();
            shichangyincang();
            sousuoshangpin("0");
        }

        jiarugouwuchedialog = new JiaRuGouWuCheDialog(mContext,
                mContext.getResources().getIdentifier("BottomDialog", "style", mContext.getPackageName()));
        jiarugouwuchedialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.LEFT | Gravity.RIGHT);
        yijiadapter = new YiJiFenLeiAdapter();

//        location = new ToolLocation();
//        location.startLocation(this);

    }

    @OnClick({R.id.iv_back, R.id.ll_sousuo, R.id.rl_shichang, R.id.rl_dianpu, R.id.rl_shangpin, R.id.ll_diqu, R.id.ll_pinlei, R.id.ll_shaixuan, R.id.tv_xiaoliang, R.id.ll_jiage, R.id.tv_pingfenzuigao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_sousuo:
                Intent intent = new Intent(this, SouSuoActivity.class);
                bundle.putString("sousuofangxiang", sousuofangxiang);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
                break;
            case R.id.rl_shichang:
                shichangxianshi();
                dianpuyincang();
                shangpinyincang();
                sousuofangxiang = "shichang";
//                sousuoshichang();
                break;
            case R.id.rl_dianpu:
                dianpuxianshi();
                shichangyincang();
                shangpinyincang();
                sousuofangxiang = "dianpu";
                sousuodianpu();
                break;
            case R.id.rl_shangpin:
                shangpinxianshi();
                dianpuyincang();
                shichangyincang();
                sousuofangxiang = "shangpin";
                sousuoshangpin("0");
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
                //按销量排序
                sousuoshangpin("1");
                setXuanXiangColor(tvXiaoliang);

                break;
            case R.id.ll_jiage:
                //按价格升序或者降序
                if (isdi){
                    sousuoshangpin("4");
                }else{
                    sousuoshangpin("3");
                }
                setXuanXiangColor(tvJiage);
                break;
            case R.id.tv_pingfenzuigao:
                //按评分最高排序
                sousuoshangpin("2");
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
                shaixuanpop.dismiss();
            }
        });
        tv_chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_jiagedi.setText("");
                et_jiagegao.setText("");
                shichangid="";
                yijiadapter.setXuanZhongId(shichangid);
                erjiadapter.setXuanZhongId(shichangid);
                sanjiadapter.setXuanZhongId(shichangid);
                yijiadapter.notifyDataSetChanged();
                erjiadapter.notifyDataSetChanged();
                sanjiadapter.notifyDataSetChanged();
                tvShaixuan.setText("不限");

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
                        quanbu.setClassify_id("0");
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

        rv_yijifenlei.setLayoutManager(new GridLayoutManager(mContext, 3));
        rv_yijifenlei.setAdapter(yijiadapter);
        yijiadapter.setNewData(yijiFenLei);
        yijiadapter.setCallBack(new YiJiFenLeiAdapter.CallBack() {
            @Override
            public void xuanzhong(FenLeiBean msg) {
                Log.e("item",gson.toJson(msg));
                tvPinlei.setText(msg.getClassify_name());
                yijipinleiid = msg.getClassify_id();
                if (yijipinleiid.equals("0")){
                    tvPinlei.setTextColor(getResources().getColor(R.color.zicolor));
                }else{
                    tvPinlei.setTextColor(getResources().getColor(R.color.zangqing));
                    showErJiFenLei();
                }

                yijipop.dismiss();
            }
        });

        yijipop.setOutsideTouchable(true);
        yijipop.setBackgroundDrawable(new BitmapDrawable());
        yijipop.showAsDropDown(llDiqu);
    }

    private void showErJiFenLei() {
        View view = View.inflate(mContext, R.layout.pop_ersanjifenlei, null);
        final PopupWindow yijipop = new PopupWindow(view);
        WindowManager wm1 = this.getWindowManager();
        int width = wm1.getDefaultDisplay().getWidth();
        int height = wm1.getDefaultDisplay().getHeight();

        RecyclerView rv_erjifenlei = view.findViewById(R.id.rv_erjifenlei);
        RecyclerView rv_sanjifenlei = view.findViewById(R.id.rv_sanjifenlei);

        yijipop.setWidth(width);
        yijipop.setHeight(height-AppUtil.dip2px(165));
        ErJiFenLeiAdapter erjiadapter = new ErJiFenLeiAdapter();
        rv_erjifenlei.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        rv_erjifenlei.setAdapter(erjiadapter);

        SanJiFenLeiAdapter sanJiFenLeiAdapter = new SanJiFenLeiAdapter();
        rv_sanjifenlei.setLayoutManager(new AutoLineFeedLayoutManager());
        rv_sanjifenlei.setAdapter(sanJiFenLeiAdapter);
        ArrayList<FenLeiBean> dangqianerji=new ArrayList<>();
        for (int i = 0; i < erjiFenLei.size(); i++) {
            if (yijipinleiid.equals(erjiFenLei.get(i).getParent_id())){
                dangqianerji.add(erjiFenLei.get(i));
            }
        }
        erjiadapter.setNewData(dangqianerji);
        if (dangqianerji.size()>0){
                erjipinleiid=dangqianerji.get(0).getClassify_id();
                erjipinleiname=dangqianerji.get(0).getClassify_name();

                erjiadapter.setCallBack(new ErJiFenLeiAdapter.CallBack() {
                    @Override
                    public void xuanzhong(FenLeiBean msg) {
                        Log.e("item",gson.toJson(msg));
                        tvPinlei.setText(msg.getClassify_name());
                        erjipinleiid = msg.getClassify_id();
                        erjipinleiname = msg.getClassify_name();
                        tvPinlei.setTextColor(getResources().getColor(R.color.zangqing));
                        yijipop.dismiss();
                    }

                });
            ArrayList<FenLeiBean> dangqiansanji=new ArrayList<>();
            for (int i = 0; i < sanjiFenLei.size(); i++) {
                if (erjipinleiid.equals(sanjiFenLei.get(i).getParent_id())){
                    dangqiansanji.add(sanjiFenLei.get(i));
                }
            }
            sanJiFenLeiAdapter.setNewData(sanjiFenLei);
            sanJiFenLeiAdapter.setCallBack(new SanJiFenLeiAdapter.CallBack() {
                @Override
                public void xuanzhong(FenLeiBean msg) {
                    Log.e("item",gson.toJson(msg));
                    sanjipinleiid = msg.getClassify_id();
                    tvPinlei.setTextColor(getResources().getColor(R.color.zangqing));
                    if (sanjipinleiid.equals("0")){
                        tvPinlei.setText(erjipinleiname);

                    }else{
                        tvPinlei.setText(msg.getClassify_name());

                    }
                    yijipop.dismiss();
                }

            });
        }else{

        }


        yijipop.setOutsideTouchable(true);
        yijipop.setBackgroundDrawable(new BitmapDrawable());
        yijipop.showAsDropDown(llDiqu);
    }


    //市场搜索
    private void sousuoshichang() {
        Log.e(" type_tree_id",type_tree_id+"====");
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .shichangsousuoshangpin(type_tree_id))
                .setDataListener(new HttpDataListener<List<ShiChangSouSuoShangPinBean>>() {
                    @Override
                    public void onNext(List<ShiChangSouSuoShangPinBean> list) {
                        Log.e("shichangsousuo",gson.toJson(list));
                        shichanglist = new ArrayList<>();
                        shichanglist.addAll(list);
                        shichangadapter = new ShiChangSouSuoShangPinListAdapter(mContext, shichanglist);
                        rvShichang.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                        rvShichang.setAdapter(shichangadapter);
                        shichangadapter.setOnItemClickListener(new ShiChangSouSuoShangPinListAdapter.OnItemClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                switch (view.getId()){
                                    case R.id.tv_chakan:
                                        Intent intent=new Intent(mContext,ShiChangSouSuoShangPinActivity.class);
                                        bundle.putString("type_tree_id",type_tree_id);
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
    private void sousuodianpu() {
        if ("".equals(sousuo)){
            ToastUtil.showToast("请输入要搜索的内容");
            return;
        }
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .sousuodianpu(PreferenceUtils.getString(MyApplication.mContext, "token", ""), sousuo))
                .setDataListener(new HttpDataListener<List<DianPuBean>>() {
                    @Override
                    public void onNext(List<DianPuBean> list) {
                        Log.e("sousuodianpu", "sousuodianpu");
                        dianpulist=new ArrayList<DianPuBean>();
                        dianpulist.addAll(list);
                        dianpuadapter = new DianPuListAdapter(mContext, dianpulist);
                        rvDianpulist.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                        rvDianpulist.setAdapter(dianpuadapter);
                    }
                });
    }

    //商品搜索
    private void sousuoshangpin(final String type) {
        Log.e("shangpinsousuo","yijipinleiid,erjipinleiid,sanjipinleiid,shichangid,zuigaojia,zuidijia");
        Log.e("shangpinsousuo",yijipinleiid+"-"+erjipinleiid+"-"+sanjipinleiid+"-"+shichangid+"-"+zuigaojia+"-"+zuidijia);
        if ("".equals(sousuo)){
            ToastUtil.showToast("请输入要搜索的内容");
            return;
        }
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .sousuoshangpin(PreferenceUtils.getString(MyApplication.mContext, "token", ""), sousuo,"","",yijipinleiid,erjipinleiid,sanjipinleiid,shichangid,zuigaojia,zuidijia,"0",1))
                .setDataListener(new HttpDataListener<ShangPinSouSuoBean>() {
                    @Override
                    public void onNext(final ShangPinSouSuoBean shangpin) {
                        Log.e("sousuoshangpin", "sousuoshangpin");
                        if ("3".equals(type)){
                            isdi =true;
                        }else if ("4".equals(type)){
                            isdi =false;
                        }
                        shangpinlist = new ArrayList<>();
                        shangpinlist.addAll(shangpin.getZhengchang());
                        shangpinadapter = new ShangPinListAdapter(mContext, shangpinlist);
                        rvShangpin.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                        rvShangpin.setAdapter(shangpinadapter);
                        shangpinadapter.setOnItemClickListener(new ShangPinListAdapter.OnItemClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                switch (view.getId()) {
                                    case R.id.iv_addcar:
                                        //添加购物车
                                        final ShangPinSouSuoBean.ZhengchangBean data = shangpinlist.get(position);
                                        spguige = "";
                                        switch (Integer.parseInt(data.getChoose_specifications())) {
                                            case 1:
                                                spguige = data.getPack_standard_one();
                                                break;
                                            case 2:
                                                spguige = data.getPack_standard_two();
                                                break;
                                            case 3:
                                                spguige = data.getPack_standard_tree();
                                                break;
                                        }
                                        jiarugouwuchedialog.showDialog(data.getInventory(),data.getClassify_name(), spguige, data.getRation_one() + "", data.getPrice() + ""
                                                , "");
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


    private void shichangxianshi() {
        llShichang.setVisibility(View.VISIBLE);
        tvShichang.setSelected(true);
        viewShichang.setVisibility(View.VISIBLE);
    }

    private void shichangyincang() {
        llShichang.setVisibility(View.GONE);
        tvShichang.setSelected(false);
        viewShichang.setVisibility(View.GONE);
    }

    private void dianpuxianshi() {
        llDianpu.setVisibility(View.VISIBLE);
        tvDianpu.setSelected(true);
        viewDianpu.setVisibility(View.VISIBLE);
    }

    private void dianpuyincang() {
        llDianpu.setVisibility(View.GONE);
        tvDianpu.setSelected(false);
        viewDianpu.setVisibility(View.GONE);
    }

    private void shangpinxianshi() {
        llShangpin.setVisibility(View.VISIBLE);
        tvShangpin.setSelected(true);
        viewShangpin.setVisibility(View.VISIBLE);
    }

    private void shangpinyincang() {
        llShangpin.setVisibility(View.GONE);
        tvShangpin.setSelected(false);
        viewShangpin.setVisibility(View.GONE);
    }

}
