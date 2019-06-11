package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.MessageBean;
import com.mingmen.mayi.mayibanjia.bean.ShangPinGuanLiBean;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.SouSuoDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.shangpinguanli.ShangPinAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.view.PagerSlidingTabStrip;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.custom.CustomViewPager;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/25.
 */

public class ShangPinGuanLiActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title_tj)
    TextView tvTitleTj;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_sousuo)
    ImageView ivSousuo;
    @BindView(R.id.tabs_dingdan)
    PagerSlidingTabStrip tabsDingdan;
    @BindView(R.id.vp_dingdan)
    CustomViewPager vpDingdan;

    private Context mContext;
    private String chaxunzi = "";
    private ArrayList<ShangPinGuanLiBean.GoodsListBean> mlist = new ArrayList<>();
    private String type = "0";
    private boolean isShow;

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    private String goods = "0";
    private boolean isClick = true;
    private String token = "";
    private ShangPinAdapter adapter;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public interface CallBack {
        void setMsg(String msg);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shangpinguanli;
    }

    @Override
    protected void initData() {
        mContext = ShangPinGuanLiActivity.this;
        adapter = new ShangPinAdapter(getSupportFragmentManager(), mContext);
        goods = getIntent().getStringExtra("goods");
        setToken(getIntent().getStringExtra("token"));

        if (StringUtil.isValid(token)) {
            isClick = false;
        } else {
            token = PreferenceUtils.getString(MyApplication.mContext, "token", "");
        }
        tvTitleTj.setTextColor(mContext.getResources().getColor(R.color.white_no_70));
        tvTitleTj.setText("添加商品");
        tvTitle.setTextColor(mContext.getResources().getColor(R.color.white));
        if(goods.equals("0")){
            tvTitle.setText("普通商品");
        } else {
            tvTitle.setText("特价商品");
//            tvTitle.setTextColor(mContext.getResources().getColor(R.color.white_no_70));
//            tvTitle.setVisibility(View.GONE);
//            tvTitleTj.setTextColor(mContext.getResources().getColor(R.color.white));
        }
        vpDingdan.setAdapter(adapter);
        vpDingdan.setScanScroll(false);
        tabsDingdan.setViewPager(vpDingdan);
        setToken(getIntent().getStringExtra("token"));
        vpDingdan.setOffscreenPageLimit(0);
        /**
         * 跳转传过来的页面，到哪个
         */
        int tosome = getIntent().getIntExtra("to_gl", 0);
        vpDingdan.setCurrentItem(tosome);
    }


    @OnClick({R.id.tv_title, R.id.iv_back, R.id.iv_sousuo,R.id.tv_title_tj})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_title:
//                if(goods.equals("1")){
//                    tvTitle.setTextColor(mContext.getResources().getColor(R.color.white));
//                    tvTitleTj.setTextColor(mContext.getResources().getColor(R.color.gray_e7e7e7));
//                    ToastUtil.showToastLong("切换为普通");
//                    isShow =false;
//                    goods = "0";
//                    adapter.notifyDataSetChanged();
//                    EventBus.getDefault().post(new MessageBean(goods));
//                }
                break;
            case R.id.tv_title_tj:
//                if(goods.equals("0")){
//                    tvTitle.setTextColor(mContext.getResources().getColor(R.color.gray_e7e7e7));
//                    tvTitleTj.setTextColor(mContext.getResources().getColor(R.color.white));
//                    ToastUtil.showToastLong("切换为特价");
//                    isShow = true;
//                    goods = "1";
//                    adapter.notifyDataSetChanged();
//                    EventBus.getDefault().post(new MessageBean(goods));
//                }
                //添加商品
                if(isClick){
                    Intent it = new Intent();
                    it.setClass(mContext, XJSPFeiLeiXuanZeActivity.class);
                    it.putExtra("goods",goods);
                    it.putExtra("state", "0");
//                    Intent intent = new Intent(mContext, FaBuShangPinActivity.class);
//                    intent.putExtra("state", "0");
//                    intent.putExtra("goods", goods);
                    startActivity(it);

                } else {
                    ToastUtil.showToastLong("请注意，您只有阅览权限");
                }
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_sousuo:
                if (isClick) {
                    //搜索
                    new SouSuoDialog()
                            .setData(chaxunzi)
                            .setCallBack(new SouSuoDialog.CallBack() {
                                @Override
                                public void sousuozi(String msg) {
                                    Log.e("msg", msg + "==");
                                    chaxunzi = msg;
                                    EventBus.getDefault().post(msg);
//                                    if(isShow){
//                                        vpTj.setCurrentItem(0);
//                                    } else {
                                        vpDingdan.setCurrentItem(0);
//                                    }

                                }
                            }).show(getSupportFragmentManager());
                } else {
                    ToastUtil.showToastLong("请注意，您只有阅览权限");
                }
                break;
        }
    }
}
