package com.mingmen.mayi.mayibanjia.ui.activity;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.ShangPinGuanLiBean;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.SouSuoDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.shangpinguanli.BaseShangPinFragment;
import com.mingmen.mayi.mayibanjia.ui.activity.shangpinguanli.DaiShenHeShangPinFragment;
import com.mingmen.mayi.mayibanjia.ui.activity.shangpinguanli.ShangJiaShangPinFragment;
import com.mingmen.mayi.mayibanjia.ui.activity.shangpinguanli.ShangPinAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.view.PagerSlidingTabStrip;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.custom.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.widget.ListPopupWindow.MATCH_PARENT;

/**
 * Created by Administrator on 2018/9/25.
 */

public class ShangPinGuanLiActivity extends BaseActivity {
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_sousuo)
    ImageView ivSousuo;
    @BindView(R.id.tabs_dingdan)
    PagerSlidingTabStrip tabsDingdan;
    @BindView(R.id.vp_dingdan)
    CustomViewPager vpDingdan;
    private Context mContext;
    private String chaxunzi="";
    private ArrayList<ShangPinGuanLiBean.GoodsListBean> mlist = new ArrayList<>();
    private String type="0";

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    private String goods= "0";
    private boolean isClick = true;
    private String token = "";
    private ShangPinAdapter adapter;
    private CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
        mContext=ShangPinGuanLiActivity.this;
        adapter = new ShangPinAdapter(getSupportFragmentManager(), mContext);
        goods = getIntent().getStringExtra("goods");
        setToken(getIntent().getStringExtra("token"));

        if(StringUtil.isValid(token)){
            isClick = false;
        } else {
            token =PreferenceUtils.getString(MyApplication.mContext,"token","");
        }
        vpDingdan.setAdapter(adapter);
        vpDingdan.setScanScroll(false);
        tabsDingdan.setViewPager(vpDingdan);
        setToken(getIntent().getStringExtra("token"));
        vpDingdan.setOffscreenPageLimit(0);
//        vpDingdan.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                ShangPinAdapter myadapter = (ShangPinAdapter) vpDingdan.getAdapter();
//                BaseShangPinFragment fragment = (BaseShangPinFragment) myadapter.getItem(position);
//                fragment.onResume();
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
        /**
         * 跳转传过来的页面，到哪个
         */
        int tosome = getIntent().getIntExtra("to_gl",0);
        vpDingdan.setCurrentItem(tosome);
    }




    @OnClick({R.id.ll_title, R.id.iv_back, R.id.iv_sousuo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title:
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_sousuo:
                if(isClick){
                    //搜索
                    new SouSuoDialog()
                            .setData(chaxunzi)
                            .setCallBack(new SouSuoDialog.CallBack() {
                                @Override
                                public void sousuozi(String msg) {
                                        Log.e("msg",msg+"==");
                                    chaxunzi=msg;
                                    callBack.setMsg(msg);
                                    vpDingdan.setCurrentItem(0);
                                }
                            }).show(getSupportFragmentManager());
                } else {
                    ToastUtil.showToastLong("请注意，您只有阅览权限");
                }
                break;
        }
    }
}
