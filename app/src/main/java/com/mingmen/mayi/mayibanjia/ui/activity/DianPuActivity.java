package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.DianPuZhanShiBean;
import com.mingmen.mayi.mayibanjia.bean.ShangPinSouSuoBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.DianPuZhanShiAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.JiaRuGouWuCheDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.GlideUtils;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.custom.MarqueeTextView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/27/027.
 */

public class DianPuActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_sousuo)
    EditText etSousuo;
    @BindView(R.id.iv_sangedian)
    ImageView ivSangedian;
    @BindView(R.id.iv_focus)
    ImageView ivFocus;
    @BindView(R.id.iv_diantu)
    ImageView ivDiantu;
    @BindView(R.id.tv_dianming)
    TextView tvDianming;
    @BindView(R.id.tv_focus)
    TextView tvFocus;
    @BindView(R.id.rb_pingfen)
    RatingBar rbPingfen;
    @BindView(R.id.tv_pingfen)
    TextView tvPingfen;
    @BindView(R.id.tv_jiage)
    TextView tvJiage;
    @BindView(R.id.tv_guanzhu)
    TextView tvGuanzhu;
    @BindView(R.id.ll_guanzhu)
    LinearLayout llGuanzhu;
    @BindView(R.id.tv_xiaoliang)
    TextView tvXiaoliang;
    @BindView(R.id.ll_jiage)
    LinearLayout llJiage;
    @BindView(R.id.rv_splist)
    SwipeMenuRecyclerView rvSplist;
    @BindView(R.id.ll_dianhua)
    LinearLayout llDianhua;
    @BindView(R.id.ll_gouwuche)
    LinearLayout llGouwuche;
    @BindView(R.id.iv_fanhuidingbu)
    ImageView ivFanhuidingbu;
    @BindView(R.id.tv_weizhi)
    TextView tvWeizhi;
    @BindView(R.id.tv_zongshu)
    TextView tvZongshu;
    @BindView(R.id.ll_weizhi)
    LinearLayout llWeizhi;
    @BindView(R.id.iv_bg)
    ImageView iv_bg;
    @BindView(R.id.iv_jishida)
    ImageView ivJishida;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;
    private Context mContext;
    private PopupWindow mPopWindow;
    private LinearLayout pp_shouye;
    private LinearLayout pp_fenxiang;
    private LinearLayout pp_shoucang;
    private String dianpuid="";
    private DianPuZhanShiAdapter adapter;
    private DianPuZhanShiBean dianpuxinxi;
    private String dianhua;
    private String guanzhuid="";
    private boolean isFocus;//是否关注
    private int ye = 1;
    private String type = "";
    private boolean jiage;
    private String telephone;
    private List<DianPuZhanShiBean.CompanyListBean> mlist = new ArrayList<>();
    private JiaRuGouWuCheDialog jiarugouwuchedialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_dianpu;
    }

    @Override
    protected void initData() {
        mContext=DianPuActivity.this;
        dianpuid = getIntent().getExtras().getString("dianpuid");

        jiarugouwuchedialog = new JiaRuGouWuCheDialog(mContext,
                mContext.getResources().getIdentifier("BottomDialog", "style", mContext.getPackageName()));
        jiarugouwuchedialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.LEFT | Gravity.RIGHT);
        Glide.with(this).load(R.mipmap.timg).into(iv_bg);
        mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                // 该加载更多啦。
                getSpList();
            }
        };
        rvSplist.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        adapter = new DianPuZhanShiAdapter(mContext, mlist);
        refreshLayout.setColorSchemeResources(R.color.zangqing, R.color.zangqing,
                R.color.zangqing, R.color.zangqing);
        rvSplist.setLoadMoreListener(mLoadMoreListener);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                etSousuo.clearFocus();
                ye = 1;
                mlist.clear();
                getSpList();
                refreshLayout.setRefreshing(false);
            }
        });
        adapter.setOnItemClickListener(new DianPuZhanShiAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                switch (view.getId()){
                    case R.id.iv_addcar:
                        final DianPuZhanShiBean.CompanyListBean data = mlist.get(position);
                        String spguige = "";
                        jiarugouwuchedialog.showDialog(data.getInventory(),data.getClassify_name(), spguige, data.getRation_one() + "", data.getPrice() + ""
                                , data.getHostphoto());
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
                                Log.e("jiarugouwuche", jiarugouwuchedialog.getEtShuliang().getText().toString().trim());
                                jiarugouwuchedialog.getEtShuliang().setText("0");
                                jiarugouwuchedialog.cancel();
                            }
                        });
                        break;
                    case R.id.iv_zoushitu:
                        Intent zoushi=new Intent(mContext,TubiaoActivity.class);
                        zoushi.putExtra("mark_id",mlist.get(position).getSon_number());//市场id
                        zoushi.putExtra("market_name",mlist.get(position).getMarket_name());//市场名
                        zoushi.putExtra("classify_id",mlist.get(position).getType_tree_id());//三级分类名称
                        zoushi.putExtra("classify_name",mlist.get(position).getClassify_name());//三级分类名称
                        startActivity(zoushi);
                        break;
                }
            }
        });
        rvSplist.setAdapter(adapter);
        rvSplist.setOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView,int dx,int dy){
                super.onScrolled(recyclerView,dx,dy);
                LinearLayoutManager l = (LinearLayoutManager)recyclerView.getLayoutManager();
                int adapterNowPos = l.findFirstVisibleItemPosition()+1;
                tvWeizhi.setText(adapterNowPos+"");
            }
        });
        rvSplist.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if(firstVisibleItemPosition>10){
                        if(ivFanhuidingbu.getVisibility()==View.GONE){
                            ivFanhuidingbu.setVisibility(View.VISIBLE);
                            llWeizhi.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if(ivFanhuidingbu.getVisibility()==View.VISIBLE){
                            ivFanhuidingbu.setVisibility(View.GONE);
                            llWeizhi.setVisibility(View.GONE);
                        }
                    }

                } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {//拖动中
                    if(ivFanhuidingbu.getVisibility()==View.GONE){
                        ivFanhuidingbu.setVisibility(View.VISIBLE);
                        llWeizhi.setVisibility(View.VISIBLE);
                    }
                } else if(newState ==RecyclerView.SCROLL_STATE_SETTLING){
                    if(ivFanhuidingbu.getVisibility()==View.GONE){
                        ivFanhuidingbu.setVisibility(View.VISIBLE);
                        llWeizhi.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        etSousuo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    getSpSearchList();
                    return true;
                }
                return false;

            }
        });

        getdianpuzhanshi();
    }


     private void getdianpuzhanshi(){
         Log.e("当前页码",ye+"");
         HttpManager.getInstance()
                 .with(mContext)
                 .setObservable(
                         RetrofitManager
                                 .getService()
                                 .getdianpuzhanshi(PreferenceUtils.getString(MyApplication.mContext, "token",""),dianpuid,ye+"",type))
                 .setDataListener(new HttpDataListener<DianPuZhanShiBean>() {
                     @Override
                     public void onNext(DianPuZhanShiBean data) {
                         dianpuxinxi = data;
                         initView();
                     }
                 });
     }

    private void getSpList(){
        Log.e("当前页码",ye+"");
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getDpspList(PreferenceUtils.getString(MyApplication.mContext, "token",""),dianpuid,ye+"",type))
                .setDataListener(new HttpDataListener<List<DianPuZhanShiBean.CompanyListBean>>() {
                    @Override
                    public void onNext(List<DianPuZhanShiBean.CompanyListBean> list) {
                        if (list!=null){
                            if (list.size() == 10) {
                                rvSplist.loadMoreFinish(false, true);
                            } else if (list.size() > 0) {
                                rvSplist.loadMoreFinish(false, false);
                            } else {
                                rvSplist.loadMoreFinish(true, false);
                            }
                            mlist.addAll(list);
                            adapter.notifyDataSetChanged();
                            ye++;
                        }
                    }
                },false);
    }

//关注
     private void guanzhudianpu(){
         Log.e("dianpuid",dianpuid+"-");
         HttpManager.getInstance()
                 .with(mContext)
                 .setObservable(
                         RetrofitManager
                                 .getService()
                                 .guanzhudianpu(PreferenceUtils.getString(MyApplication.mContext, "token",""),dianpuid))
                 .setDataListener(new HttpDataListener<String>() {
                     @Override
                     public void onNext(String data) {
                         ToastUtil.showToast("关注成功");
                         isFocus = true;
                         tvFocus.setText("已关注");
                         ivFocus.setVisibility(View.GONE);
                         mlist.clear();
                         ye = 1;
                         getdianpuzhanshi();
                     }
                 });
     }
//取消关注
     private void quxiaoguanzhu(){
         HttpManager.getInstance()
                 .with(mContext)
                 .setObservable(
                         RetrofitManager
                                 .getService()
                                 .quxiaoguanzhudianpu(PreferenceUtils.getString(MyApplication.mContext, "token",""),dianpuid))
                 .setDataListener(new HttpDataListener<String>() {
                     @Override
                     public void onNext(String data) {
                         ToastUtil.showToast("取消关注成功");
                         isFocus = false;
                         tvFocus.setText("关注");
                         ivFocus.setVisibility(View.VISIBLE);
                         mlist.clear();
                         ye = 1;
                         getdianpuzhanshi();
                     }
                 });
     }

    private void initView() {
        GlideUtils.cachePhoto(mContext,ivDiantu,dianpuxinxi.getFile_path());
//        tvDianming.setMarqueeEnable(true);
        tvDianming.setText(dianpuxinxi.getCompany_name()+"("+dianpuxinxi.getMarket_name()+")");
        tvPingfen.setText(dianpuxinxi.getEvaluation()+"");
        rbPingfen.setRating((float) dianpuxinxi.getEvaluation());
        ivJishida.setVisibility(dianpuxinxi.getRealtime().equals("1")?View.GONE:View.VISIBLE);
        guanzhuid=dianpuxinxi.getAttention_id()!=null?dianpuxinxi.getAttention_id():"";
        tvGuanzhu.setText(dianpuxinxi.getAttention_number()==null?"0人关注":dianpuxinxi.getAttention_number()+"人关注");
        dianhua = dianpuxinxi.getPhone();
        isFocus = dianpuxinxi.getAttention_id().equals("")?false:true;
        tvFocus.setText(isFocus?"已关注":"关注");
        ivFocus.setVisibility(isFocus?View.GONE:View.VISIBLE);
        tvZongshu.setText( dianpuxinxi.getGoodsCount()+"");
        telephone = dianpuxinxi.getTelephone();
        getSpList();
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

        pp_shouye = (LinearLayout) view.findViewById(R.id.pp_shouye);
        pp_fenxiang = (LinearLayout) view.findViewById(R.id.pp_fenxiang);
        pp_shoucang = (LinearLayout) view.findViewById(R.id.pp_shoucang);

        pp_shouye.setOnClickListener(this);
        pp_fenxiang.setOnClickListener(this);
        pp_shoucang.setOnClickListener(this);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.showAsDropDown(ivSangedian);
    }
    @OnClick({R.id.iv_back, R.id.iv_sangedian, R.id.ll_guanzhu, R.id.tv_xiaoliang, R.id.ll_jiage, R.id.ll_dianhua, R.id.ll_gouwuche, R.id.iv_fanhuidingbu, R.id.ll_weizhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_sangedian:
                showPopupWindow();
                break;
            case R.id.ll_guanzhu:
                etSousuo.clearFocus();
                if (isFocus) {
                    quxiaoguanzhu();
                }else{
                    guanzhudianpu();
                }
                break;
            case R.id.tv_xiaoliang:
                //按销量排序
                type = "1";
                setShow();
                break;
            case R.id.ll_jiage:
                //按价格升序降序
                if (jiage){
                    jiage = false;
                    type = "2";
                } else {
                    jiage = true;
                    type = "3";
                }
                setShow();
                break;
            case R.id.ll_dianhua:
                //电话
                Intent dianhua = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + telephone );
                dianhua.setData(data);
                startActivity(dianhua);
                break;
            case R.id.ll_gouwuche:
                //购物车
                Intent intent=new Intent(mContext, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("tosome", 2);
                startActivity(intent);
                break;
            case R.id.iv_fanhuidingbu:
                rvSplist.scrollToPosition(0);
                break;
            case R.id.ll_weizhi:
                break;

        }
    }

    @Override
    public void onClick(View v) {
        mPopWindow.dismiss();
        switch (v.getId()){
            case R.id.pp_fenxiang:
                //分享
                break;
            case R.id.pp_shoucang:
                //跳转收藏列表
                break;
            case R.id.pp_shouye:
                //首页
                Intent intent=new Intent(mContext, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("tosome", 0);
                startActivity(intent);
                break;
        }
    }

    private void setShow(){
        ye = 1 ;
        mlist.clear();
        tvXiaoliang.setTextColor(mContext.getResources().getColor(R.color.lishisousuo));
        tvJiage.setTextColor(mContext.getResources().getColor(R.color.lishisousuo));
        switch (type){
            case "1"://销量最高
                tvXiaoliang.setTextColor(mContext.getResources().getColor(R.color.zangqing));
                break;
            case "2"://价格最高
                tvJiage.setTextColor(mContext.getResources().getColor(R.color.zangqing));
                break;
            case "3"://价格最低
                tvJiage.setTextColor(mContext.getResources().getColor(R.color.zangqing));
                break;
        }
        getSpList();
    }

    private void getSpSearchList(){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getDpspListSearch(PreferenceUtils.getString(MyApplication.mContext, "token", ""),etSousuo.getText().toString().trim(),dianpuid))
                .setDataListener(new HttpDataListener<List<DianPuZhanShiBean.CompanyListBean>>() {
                    @Override
                    public void onNext(List<DianPuZhanShiBean.CompanyListBean> list) {
                        mlist.clear();
                        adapter.notifyDataSetChanged();
                        if (list!=null){
//                            if (list.size() == 10) {
//                                rvSplist.loadMoreFinish(false, true);
//                            } else if (list.size() > 0) {
//                                rvSplist.loadMoreFinish(false, false);
//                            } else {
                                rvSplist.loadMoreFinish(true, false);
//                            }
                            mlist.addAll(list);
                            adapter.notifyDataSetChanged();
//                            ye++;
                        }
                    }
                },false);
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
                        for (int i = 0; i < mlist.size(); i++) {
                            if (spid.equals(mlist.get(i).getCommodity_id())) {
                                mlist.get(i).setShopping_id(data);
                            }
                        }
                        adapter.notifyDataSetChanged();
                        ToastUtil.showToast("添加购物车成功");
                        updateGwc();

                    }
                });
    }
}
