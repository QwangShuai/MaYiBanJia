package com.mingmen.mayi.mayibanjia.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.SPXiangQingBean;
import com.mingmen.mayi.mayibanjia.bean.XQPingJiaBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.SpFenLeiLableAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.WeiNiTuiJianAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.XiangQTuAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.JiaRuGouWuCheDialog;
import com.mingmen.mayi.mayibanjia.ui.view.CircleImageView;
import com.mingmen.mayi.mayibanjia.ui.view.GlideImageLoader;
import com.mingmen.mayi.mayibanjia.ui.view.XCFlowLayout;
import com.mingmen.mayi.mayibanjia.ui.view.xiangqing.IdeaScrollView;
import com.mingmen.mayi.mayibanjia.ui.view.xiangqing.IdeaViewPager;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.JumpUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.custom.MarqueeTextView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.sephiroth.android.library.easing.Linear;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by Administrator on 2018/7/27/027.
 */

public class SPXiangQingActivity extends Activity implements View.OnClickListener {

    @BindView(R.id.ll_one)
    LinearLayout llOne;
    @BindView(R.id.rv_xiangqingtu)
    RecyclerView rvXiangqingtu;
    @BindView(R.id.ll_two)
    LinearLayout llTwo;
    @BindView(R.id.ll_three)
    LinearLayout llThree;
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.headerParent)
    LinearLayout headerParent;
    @BindView(R.id.layer)
    View layer;
    @BindView(R.id.ideaScrollView)
    IdeaScrollView ideaScrollView;
    @BindView(R.id.viewPager)
    IdeaViewPager viewPager;
    @BindView(R.id.civ_touxiang)
    CircleImageView civTouxiang;
    @BindView(R.id.tv_shangpinming)
    MarqueeTextView tvShangpinming;
    @BindView(R.id.iv_zoushi)
    ImageView ivZoushi;
    @BindView(R.id.tv_guige)
    TextView tvGuige;
    @BindView(R.id.tv_danjia)
    TextView tvDanjia;
    @BindView(R.id.iv_diantu)
    ImageView ivDiantu;
    @BindView(R.id.tv_dianming)
    TextView tvDianming;
    @BindView(R.id.tv_dizhi)
    TextView tvDizhi;
    @BindView(R.id.ll_jindian)
    LinearLayout llJindian;
    @BindView(R.id.tv_fuwufen)
    TextView tvFuwufen;
    @BindView(R.id.tv_xiaoliang)
    TextView tvXiaoliang;
    @BindView(R.id.tv_xiangqingzi)
    TextView tvXiangqingzi;
    @BindView(R.id.ll_chakanquanbupingjia)
    LinearLayout llChakanquanbupingjia;
    @BindView(R.id.tv_pingjiaming)
    TextView tvPingjiaming;
    @BindView(R.id.tv_wupingjia)
    TextView tvWupingjia;
    @BindView(R.id.tv_riqi)
    TextView tvRiqi;
    @BindView(R.id.tv_kucun)
    TextView tvKucun;
    //    @BindView(R.id.tv_pingjiashangpin)
//    TextView tvPingjiashangpin;
    @BindView(R.id.rv_tuijian)
    RecyclerView rvTuijian;
    @BindView(R.id.ll_dianhua)
    LinearLayout llDianhua;
    @BindView(R.id.ll_shoucang)
    LinearLayout llShoucang;
    @BindView(R.id.ll_gouwuche)
    LinearLayout llGouwuche;
    @BindView(R.id.bt_addcar)
    Button btAddcar;
    @BindView(R.id.bt_lijigoumai)
    Button btLijigoumai;
    @BindView(R.id.iv_sangedian)
    ImageView ivSangedian;
    @BindView(R.id.iv_jishida)
    ImageView ivJishida;
    @BindView(R.id.iv_shoucang)
    ImageView iv_shoucang;
    @BindView(R.id.xiangqing_banner)
    Banner xiangqingBanner;
    @BindView(R.id.tv_qidingliang1)
    TextView tvQidingliang1;
/*    @BindView(R.id.tv_qidingliangjiage1)
    TextView tvQidingliangjiage1;*/
//    @BindView(R.id.tv_qidingliang2)
//    TextView tvQidingliang2;
//    @BindView(R.id.tv_qidingliangjiage2)
//    TextView tvQidingliangjiage2;
//    @BindView(R.id.tv_qidingliang3)
//    TextView tvQidingliang3;
//    @BindView(R.id.tv_qidingliangjiage3)
//    TextView tvQidingliangjiage3;
//    @BindView(R.id.ll_qidingliang2)
//    LinearLayout llQidingliang2;
    @BindView(R.id.xcf_pingjia)
    XCFlowLayout xcfPingjia;
    @BindView(R.id.tv_tejia)
    TextView tvTejia;
    @BindView(R.id.tv_miaoshu)
    TextView tvMiaoshu;
    @BindView(R.id.tv_spgg)
    TextView tvSpgg;
    @BindView(R.id.ll_tejia)
    LinearLayout llTejia;
    @BindView(R.id.rv_xiangqing)
    RecyclerView rvXiangqing;
//    @BindView(R.id.ll_qidingliang3)
//    LinearLayout llQidingliang3;
    private boolean isNeedScrollTo = true;
    private float currentPercentage = 0;
    private Context mContext;
    private XiangQTuAdapter tuadapter;
    private WeiNiTuiJianAdapter tuijianadapter;
    private Bundle bundle;
    private String spid;
    private SPXiangQingBean spxinxi;
    private List<String> imgs = new ArrayList<>();
    private List<String> tulist = new ArrayList<>();
    private String dianpuid;
    private String dianhua;
    //    private boolean isShoucang=true;
    private boolean isShoucang = false;
    //    private String shoucangid="8f708f289af74c36bd4e270e438ec45b";
    private String shoucangid;
    private PopupWindow mPopWindow;
    private SPXiangQingBean.XqBean xq;
    private XQPingJiaBean pingjia;
    private List<SPXiangQingBean.GoodsRecommendBean> tuijianlist;
    private String guigeid = "";
    private JiaRuGouWuCheDialog jiarugouwuchedialog;
    private String guigename;
    private SpFenLeiLableAdapter spfllAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangpinxiangqing);
        StatusBarCompat.translucentStatusBar(this);
        ButterKnife.bind(this);
        mContext = SPXiangQingActivity.this;
        ivSangedian.setVisibility(View.GONE);
        initData();
        jiarugouwuchedialog = new JiaRuGouWuCheDialog(mContext,
                mContext.getResources().getIdentifier("BottomDialog", "style", mContext.getPackageName()));
        jiarugouwuchedialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.LEFT | Gravity.RIGHT);
    }

    private RadioGroup.OnCheckedChangeListener radioGroupListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                radioButton.setTextColor(radioButton.isChecked() ? getRadioCheckedAlphaColor(currentPercentage) : getRadioAlphaColor(currentPercentage));
                if (radioButton.isChecked() && isNeedScrollTo) {
                    ideaScrollView.setPosition(i);
                }
            }
        }
    };

    private void initData() {
        Rect rectangle = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rectangle);
        ideaScrollView.setViewPager(viewPager, getMeasureHeight(headerParent) - rectangle.top);
        radioGroup.setAlpha(0);
        radioGroup.check(radioGroup.getChildAt(0).getId());

        ArrayList<Integer> araryDistance = new ArrayList<>();

        araryDistance.add(0);
        araryDistance.add(getMeasureHeight(llOne) - getMeasureHeight(headerParent));
        araryDistance.add(getMeasureHeight(llOne) + getMeasureHeight(llTwo) - getMeasureHeight(headerParent));
//        araryDistance.add(getMeasureHeight(llOne)+getMeasureHeight(llTwo)+getMeasureHeight(llThree)-getMeasureHeight(headerParent));

        ideaScrollView.setArrayDistance(araryDistance);

        ideaScrollView.setOnScrollChangedColorListener(new IdeaScrollView.OnScrollChangedColorListener() {
            @Override
            public void onChanged(float percentage) {

                int color = getAlphaColor(percentage > 0.9f ? 1.0f : percentage);
                headerParent.setBackgroundDrawable(new ColorDrawable(color));
                radioGroup.setAlpha((percentage > 0.9f ? 1.0f : percentage) * 255);
                setRadioButtonTextColor(percentage);

            }

            @Override
            public void onChangedFirstColor(float percentage) {

            }

            @Override
            public void onChangedSecondColor(float percentage) {

            }
        });

        ideaScrollView.setOnSelectedIndicateChangedListener(new IdeaScrollView.OnSelectedIndicateChangedListener() {
            @Override
            public void onSelectedChanged(int position) {
                isNeedScrollTo = false;
                radioGroup.check(radioGroup.getChildAt(position).getId());
                isNeedScrollTo = true;
            }
        });

        radioGroup.setOnCheckedChangeListener(radioGroupListener);
        bundle = new Bundle();
        bundle = getIntent().getExtras();

        spid = bundle.getString("spid");
        getspxiangqing();


    }


    private void initview() {
        xq = spxinxi.getXq();
        if (spxinxi.getEvaluate()!=null) {
            pingjia = spxinxi.getEvaluate();
            if (pingjia.getPjList() != null&& pingjia.getPjList().size()!=0) {
                tvPingjiaming.setText(pingjia.getPjCompanyName());
//            tvPingjianeirong.setText(pingjia.getComment_text());
                tvRiqi.setText(pingjia.getCreate_time() + "");
                if (pingjia.getHeadPhoto() != null) {
                    Glide.with(mContext).load(pingjia.getHeadPhoto()).into(civTouxiang);
                }
                String[] pingjiaarray = new String[pingjia.getPjList()==null?0:pingjia.getPjList().size()];
                for (int i=0;i<pingjia.getPjList().size();i++){
                    pingjiaarray[i] = pingjia.getPjList().get(i).getSon_name();
                }

                if (pingjiaarray.length>0){
                    initShangpinChildViews(xcfPingjia,pingjiaarray);
                }

            }else{
                tvWupingjia.setText("该商品暂无评价");
            }
        }else{
            tvWupingjia.setText("该商品暂无评价");
        }
        tuijianlist = spxinxi.getGoodsRecommend();
        initBanner();
        dianhua = spxinxi.getTelePhone();
        tvKucun.setText("库存："+xq.getInventory());
        //tvShangpinming.setText(xq.getClassify_name());
        tvShangpinming.setMarqueeEnable(true);
        tvShangpinming.setText(xq.getClassify_name());
        ivJishida.setVisibility(xq.getReal_time_state().equals("0")?View.VISIBLE:View.GONE);
        tvDianming.setText(xq.getCompanyName());
        Glide.with(mContext).load(xq.getCompanyPhoto()).into(ivDiantu);
        tvDizhi.setText(xq.getCompanyAddress());
        if(xq.getGoods().equals("1")){
            llTejia.setVisibility(View.VISIBLE);
            tvTejia.setText("￥ " + xq.getTjprice());
            tvTejia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
        tvDanjia.setText(xq.getPrice());

        if(TextUtils.isEmpty(xq.getSpec_describe())){
            tvGuige.setVisibility(View.GONE);
        }else{
            tvGuige.setText(xq.getSpec_describe());
        }

        if(StringUtil.isValid(xq.getSpms())){
            tvMiaoshu.setText("备注:" + xq.getSpms());
        } else {
            tvMiaoshu.setVisibility(View.GONE);
        }

        tvXiaoliang.setText(xq.getCommodity_sales());
        tvFuwufen.setText(spxinxi.getAvgNum() + "");
        dianpuid = xq.getCompany_id();

/*        if (xq.getChoose_specifications() != null) {
            switch (Integer.parseInt(xq.getChoose_specifications())) {
                case 1:
                    guigeid = xq.getPack_standard_one();
                    guigename = xq.getPackOneName();
                    break;
                case 2:
                    guigeid = xq.getPack_standard_two();
                    guigename = xq.getPackTwoName();
                    break;
                case 3:
                    guigeid = xq.getPack_standard_tree();
                    guigename = xq.getPackThreeName();
                    break;
            }
        }*/
        if(!StringUtil.isEmpty(xq.getPack_standard_two())){
            guigename=xq.getPackTwoName();
        }else{
            guigename=xq.getPackThreeName();
        }
       if (!StringUtil.isEmpty(xq.getRation_one())){
            tvQidingliang1.setText(xq.getRation_one()+guigename+"起批");
/*            if (!StringUtil.isEmpty(xq.getPice_one())){
                tvQidingliangjiage1.setText(xq.getPice_one());
            }*/
        }

        int mysize = spxinxi.getParameteList()==null?0:spxinxi.getParameteList().size();
        if(mysize!=0){
            rvXiangqing.setVisibility(View.VISIBLE);
            rvXiangqing.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
            spfllAdapter = new SpFenLeiLableAdapter(mContext,spxinxi.getParameteList());
            rvXiangqing.setAdapter(spfllAdapter);
        } else {
            rvXiangqing.setVisibility(View.GONE);
            tvSpgg.setVisibility(View.GONE);
        }

//        if (!StringUtil.isEmpty(xq.getRation_two())){
//            tvQidingliang2.setText(!StringUtil.isEmpty(xq.getRation_three())?xq.getRation_two()+"-"+xq.getRation_three()+guigename:xq.getRation_two()+guigename+"以上");
//            if (!StringUtil.isEmpty(xq.getPice_two())){
//                tvQidingliangjiage2.setText(xq.getPice_two());
//            }
//        }else{
//            llQidingliang2.setVisibility(View.INVISIBLE);
//        }
//        if (!StringUtil.isEmpty(xq.getRation_three())){
//            tvQidingliang3.setText(xq.getRation_three()+guigename+"以上");
//            if (!StringUtil.isEmpty(xq.getPice_three())){
//                tvQidingliangjiage3.setText(xq.getPice_three());
//            }
//        }else{
//            llQidingliang3.setVisibility(View.INVISIBLE);
//        }




        Log.e("dianpuid", xq.getCompany_id() + "----");
        //评价

        if (spxinxi.getCollectId() == null || "".equals(spxinxi.getCollectId())) {
            Log.e("spxinxi.", "isShoucangfalse");
            isShoucang = false;
            shoucangid = "";
            iv_shoucang.setSelected(false);
        } else {
            Log.e("spxinxi.", "shoucangid");
            isShoucang = true;
            shoucangid = spxinxi.getCollectId();
            iv_shoucang.setSelected(true);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                // 直接禁止垂直滑动
                return false;
            }
        };
        Log.e("xiangqingkaishi11111111", xq.getDpicture() + "-+++++++++++++++++++++==-");
        int tusize = xq.getDpicture()==null?0:xq.getDpicture().size();
        if (tusize != 0) {
            tulist.addAll(xq.getDpicture());
        } else {
            rvXiangqingtu.setVisibility(View.GONE);
        }
        tuadapter = new XiangQTuAdapter(mContext, tulist);
        rvXiangqingtu.setLayoutManager(layoutManager);
        rvXiangqingtu.setAdapter(tuadapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2) {
            @Override
            public boolean canScrollVertically() {
                // 直接禁止垂直滑动
                return false;
            }
        };
        tuijianadapter = new WeiNiTuiJianAdapter(mContext, tuijianlist);
        rvTuijian.setLayoutManager(gridLayoutManager);
        rvTuijian.setAdapter(tuijianadapter);
    }

    /**
     * 绑定轮播图数据
     */
    private void initBanner() {
        if (xq.getHpicture() != null) {
            for (int i = 0; i < xq.getHpicture().size(); i++) {
                imgs.add(xq.getHpicture().get(i));
            }
        }

        initDataTitle();
    }

    private void initDataTitle() {
        //设置banner样式
        xiangqingBanner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        //设置图片集合
        xiangqingBanner.setImages(imgs);
        xiangqingBanner.setImageLoader(new GlideImageLoader());
        //设置banner动画效果
        xiangqingBanner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        xiangqingBanner.isAutoPlay(true);
        //设置轮播时间
        xiangqingBanner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        xiangqingBanner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        xiangqingBanner.start();
    }

    //PopupWindow
    private void showPopupWindow() {
        View view = View.inflate(mContext, R.layout.layout_pupup, null);
        mPopWindow = new PopupWindow(view);
//        mPopWindow.setWidth(130);
//        mPopWindow.setHeight(148);
//        mPopWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        int width = getWindowManager().getDefaultDisplay().getWidth();
        int height = getWindowManager().getDefaultDisplay().getHeight();
        mPopWindow.setWidth(width * 2 / 6);
        mPopWindow.setHeight(height * 2 / 9);

        LinearLayout pp_shouye = (LinearLayout) view.findViewById(R.id.pp_shouye);
        LinearLayout pp_fenxiang = (LinearLayout) view.findViewById(R.id.pp_fenxiang);
        LinearLayout pp_shoucang = (LinearLayout) view.findViewById(R.id.pp_shoucang);

        pp_shouye.setOnClickListener(this);
        pp_fenxiang.setOnClickListener(this);
        pp_shoucang.setOnClickListener(this);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.showAsDropDown(ivSangedian);
    }

    private void initShangpinChildViews(XCFlowLayout xcfShangpinlishisousuo,String[] shangpinNamelist) {
        xcfShangpinlishisousuo.removeAllViews();
        ArrayList tvs = new ArrayList();
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = AppUtil.dip2px(12);
        lp.rightMargin = AppUtil.dip2px(0);
        lp.topMargin = AppUtil.dip2px(12);
        lp.bottomMargin = 0;
        for (int i = 0; i < shangpinNamelist.length; i++) {
            TextView view = new TextView(mContext);
            view.setText(shangpinNamelist[i]);
            view.setTextColor(mContext.getResources().getColor(R.color.lishisousuo));
            view.setTextSize(12);
            view.setPadding( AppUtil.dip2px(8), AppUtil.dip2px(12), AppUtil.dip2px(8), AppUtil.dip2px(12));
            view.setBackground(getResources().getDrawable(R.drawable.fillet_solid_fafafa_100));
            tvs.add(view);
            xcfShangpinlishisousuo.addView(view, lp);
        }
    }

    //查询商品详情
    private void getspxiangqing() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getspxiangqing(PreferenceUtils.getString(MyApplication.mContext, "token", ""), spid))
                .setDataListener(new HttpDataListener<SPXiangQingBean>() {
                    @Override
                    public void onNext(SPXiangQingBean data) {
                        Log.e("data", data + "---");
                        spxinxi = data;
                        if (spxinxi != null) {
                            initview();
                        }

                    }
                });
    }

    //取消收藏此商品
    private void quxiaoshoucang() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .quxiaoshoucang(PreferenceUtils.getString(MyApplication.mContext, "token", ""), shoucangid))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        Log.e("data", data + "---");
                        iv_shoucang.setSelected(false);
                        isShoucang = false;

                    }
                });
    }

    //收藏此商品
    private void shoucang() {
        Log.e("spidshoucang", spid + "---");
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .shoucang(PreferenceUtils.getString(MyApplication.mContext, "token", ""), spid,""))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        Log.e("data", data + "---");
                        shoucangid = data;
                        iv_shoucang.setSelected(true);
                        isShoucang = true;
                        ToastUtil.showToast("收藏成功");

                    }
                });
    }

    public void setRadioButtonTextColor(float percentage) {
        if (Math.abs(percentage - currentPercentage) >= 0.1f) {
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                radioButton.setTextColor(radioButton.isChecked() ? getRadioCheckedAlphaColor(percentage) : getRadioAlphaColor(percentage));
            }
            this.currentPercentage = percentage;
        }
    }

    public int getMeasureHeight(View view) {
        int width = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
        return view.getMeasuredHeight();
    }

    public int getAlphaColor(float f) {
        return Color.argb((int) (f * 255), 242, 242, 242);
    }

    public int getLayerAlphaColor(float f) {
        return Color.argb((int) (f * 255), 0x09, 0xc1, 0xf4);
    }

    public int getRadioCheckedAlphaColor(float f) {
        return Color.argb((int) (f * 255), 60, 146, 163);
    }

    //    102102102
    public int getRadioAlphaColor(float f) {
        return Color.argb((int) (f * 255), 102, 102, 102);
    }

    @OnClick({R.id.iv_zoushi, R.id.ll_jindian, R.id.ll_chakanquanbupingjia, R.id.ll_dianhua, R.id.ll_shoucang, R.id.ll_gouwuche, R.id.bt_addcar, R.id.bt_lijigoumai, R.id.iv_fanhui, R.id.iv_sangedian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_zoushi:
                //走势图
                Intent zoushi=new Intent(mContext,TubiaoActivity.class);
                zoushi.putExtra("mark_id",xq.getSon_number());//市场id
                zoushi.putExtra("market_name",xq.getMarket_name());//市场名
                zoushi.putExtra("classify_id",xq.getType_four_id());//四级分类id
                zoushi.putExtra("classify_name",xq.getClassify_name());//4级分类名称
                startActivity(zoushi);
                break;
            case R.id.ll_jindian:
                //进店
                Intent jindian = new Intent(mContext, DianPuActivity.class);
                bundle.putString("dianpuid", dianpuid);
                Log.e("dianpuding", dianpuid + "-111");
                jindian.putExtras(bundle);
                startActivity(jindian);
                break;
            case R.id.ll_chakanquanbupingjia:
                //查看全部评价
                Intent pingjiaintent=new Intent(mContext,QuanBuPingjiaActivity.class);
                pingjiaintent.putExtra("company_id",xq.getCompany_id());
                startActivity(pingjiaintent);
                break;
            case R.id.ll_dianhua:
                //电话
                Intent dianhua = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + this.dianhua);
                dianhua.setData(data);
                startActivity(dianhua);
                break;
            case R.id.ll_shoucang:
                //收藏
                Log.e("shoucangidshoucangid", shoucangid + "=");
                if (isShoucang) {
                    quxiaoshoucang();
                } else {
                    shoucang();
                }
                break;
            case R.id.ll_gouwuche:
                //购物车
                if(getIntent().getBooleanExtra("teshu",false)){
                    JumpUtil.Jump_intent(mContext,GouWuCheActivity.class,new Bundle());
                } else {
                    Intent intent = new Intent(mContext, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("tosome", 2);
                    startActivity(intent);
                }

                break;
            case R.id.bt_addcar:
                //添加购物车

                    jiarugouwuchedialog.showDialog(xq.getInventory(), xq.getClassify_name(), xq.getPackStandard(), xq.getRation_one() + "", xq.getPrice() + ""
                            ,  xq.getHostPicture());

                jiarugouwuchedialog.getBtQueding().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String shuliang = jiarugouwuchedialog.getEtShuliang().getText().toString().trim();
                        Log.e("data.getShopping_id()", xq.getShoppingId() + "---");

                            if (Integer.parseInt(shuliang) >= Integer.parseInt(xq.getRation_one())) {
                                addcar(xq.getCommodity_id(), shuliang, xq.getCompany_id(), "", guigeid);
                            } else {
                                ToastUtil.showToast("不够起订量");
                            }

                        Log.e("jiarugouwuche", jiarugouwuchedialog.getEtShuliang().getText().toString().trim());
                        jiarugouwuchedialog.getEtShuliang().setText("0");
                        jiarugouwuchedialog.cancel();
                    }
                });

                break;
            case R.id.bt_lijigoumai:
                //立即购买
                ToastUtil.showToast("该功能还未开发，敬请期待");
                break;
            case R.id.iv_fanhui:
                //返回
                finish();
                break;
            case R.id.iv_sangedian:
                //三个点
                showPopupWindow();
                break;
        }
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
                        Log.e("2222",data);
                        ToastUtil.showToast("添加购物车成功");
                        if(MainActivity.instance!=null){
                            MainActivity.instance.getGwcNo();
                        }
                    }

                });
    }


    @Override
    public void onClick(View v) {
        mPopWindow.dismiss();
        switch (v.getId()) {
            case R.id.pp_fenxiang:
                //分享
                break;
            case R.id.pp_shoucang:
                //跳转收藏列表
                break;
            case R.id.pp_shouye:
                //首页
                Intent intent = new Intent(mContext, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("tosome", 0);
                startActivity(intent);
                break;
        }
    }
}
