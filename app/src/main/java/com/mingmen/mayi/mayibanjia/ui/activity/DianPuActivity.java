package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.DianPuZhanShiAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

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
    @BindView(R.id.iv_diantu)
    ImageView ivDiantu;
    @BindView(R.id.tv_dianming)
    TextView tvDianming;
    @BindView(R.id.rb_pingfen)
    RatingBar rbPingfen;
    @BindView(R.id.tv_pingfen)
    TextView tvPingfen;
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

    @Override
    public int getLayoutId() {
        return R.layout.activity_dianpu;
    }

    @Override
    protected void initData() {
        mContext=this;
        dianpuid = getIntent().getExtras().getString("dianpuid");
        Log.e("dianpuid",dianpuid+"-");
        Glide.with(this).load(R.mipmap.timg).into(iv_bg);
        getdianpuzhanshi();
    }


     private void getdianpuzhanshi(){
         HttpManager.getInstance()
                 .with(mContext)
                 .setObservable(
                         RetrofitManager
                                 .getService()
                                 .getdianpuzhanshi(PreferenceUtils.getString(MyApplication.mContext, "token",""),dianpuid))
                 .setDataListener(new HttpDataListener<DianPuZhanShiBean>() {
                     @Override
                     public void onNext(DianPuZhanShiBean data) {
                         Log.e("data",gson.toJson(data)+"---");
                         dianpuxinxi = data;
                         initView();

                     }
                 });
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
                         isFocus = false;
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
                         isFocus = true;
                     }
                 });
     }

    private void initView() {
        Glide.with(mContext).load(dianpuxinxi.getFile_path()).into(ivDiantu);
        tvDianming.setText(dianpuxinxi.getCompany_name());
        tvPingfen.setText(dianpuxinxi.getEvaluation()+"");
        rbPingfen.setRating((float) dianpuxinxi.getEvaluation());

        guanzhuid=dianpuxinxi.getAttention_id()!=null?dianpuxinxi.getAttention_id():"";

        Log.e("guanzhuid",guanzhuid+"==");
        tvGuanzhu.setText(dianpuxinxi.getAttention_number()==null?"0人关注":dianpuxinxi.getAttention_number()+"人关注");
        dianhua = dianpuxinxi.getPhone();
        rvSplist.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        adapter = new DianPuZhanShiAdapter(mContext, dianpuxinxi.getCompanyList());
        isFocus = dianpuxinxi.getAttention_id().equals("")?true:false;
        rvSplist.setAdapter(adapter);
        tvZongshu.setText( dianpuxinxi.getCompanyList().size()+"");
        rvSplist.setOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView,int dx,int dy){
                super.onScrolled(recyclerView,dx,dy);
                LinearLayoutManager l = (LinearLayoutManager)recyclerView.getLayoutManager();
                int adapterNowPos = l.findFirstVisibleItemPosition();
                tvWeizhi.setText(adapterNowPos+"");
            }
        });
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
                if (isFocus) {
                    guanzhudianpu();
                }else{
                    quxiaoguanzhu();
                }
                break;
            case R.id.tv_xiaoliang:
                //按销量排序
                break;
            case R.id.ll_jiage:
                //按价格升序降序
                break;
            case R.id.ll_dianhua:
                //电话
                Intent dianhua = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + "");
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
}
