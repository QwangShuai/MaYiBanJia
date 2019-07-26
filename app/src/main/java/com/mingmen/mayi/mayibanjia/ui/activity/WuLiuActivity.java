package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.fragment.cheliangguanli.CheLiangGuanLiFragment;
import com.mingmen.mayi.mayibanjia.ui.fragment.gerenzhongxin.GeRenZhongXinFragment;
import com.mingmen.mayi.mayibanjia.ui.fragment.wuliujingli.WuLiuJingLiFragment;
import com.mingmen.mayi.mayibanjia.ui.view.noscrollviewpager.NoPreloadViewPager;
import com.mingmen.mayi.mayibanjia.ui.view.noscrollviewpager.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WuLiuActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    NoScrollViewPager viewpager;
    @BindView(R.id.ll_wlgl)
    LinearLayout llWlgl;
    @BindView(R.id.ll_clgl)
    LinearLayout llClgl;
    @BindView(R.id.ll_grzx)
    LinearLayout llGrzx;
    @BindView(R.id.iv_wlgl)
    ImageView ivWlgl;
    @BindView(R.id.tv_wlgl)
    TextView tvWlgl;
    @BindView(R.id.iv_clgl)
    ImageView ivClgl;
    @BindView(R.id.tv_clgl)
    TextView tvClgl;
    @BindView(R.id.iv_grzx)
    ImageView ivGrzx;
    @BindView(R.id.tv_grzx)
    TextView tvGrzx;
    private Context mContext;
    private List<Fragment> fragments = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.activity_wu_liu;
    }

    @Override
    protected void initData() {
        mContext = WuLiuActivity.this;
        WuLiuJingLiFragment wuLiuJingLiFragment = new WuLiuJingLiFragment();
        CheLiangGuanLiFragment cheLiangGuanLiFragment = new CheLiangGuanLiFragment();
        GeRenZhongXinFragment geRenZhongXinFragment = new GeRenZhongXinFragment();
        fragments.add(wuLiuJingLiFragment);
        fragments.add(cheLiangGuanLiFragment);
        fragments.add(geRenZhongXinFragment);
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        viewpager.setAdapter(adapter);

        viewpager.setOnPageChangeListener(new NoScrollViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewpager.setNoScroll(true);
        viewpager.setOffscreenPageLimit(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_wlgl, R.id.ll_clgl, R.id.ll_grzx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_wlgl:
                gaibianye(0);
                break;
            case R.id.ll_clgl:
                gaibianye(1);
                break;
            case R.id.ll_grzx:
                gaibianye(2);
                break;
        }
    }

    public void gaibianye(int ye) {
        switch (ye) {
            case 0://物流管理
                ivWlgl.setImageResource(R.mipmap.wlgl_true_wljl);
                ivClgl.setImageResource(R.mipmap.clgl_false_wljl);
                ivGrzx.setImageResource(R.mipmap.grzx_false_wljl);
                viewpager.setCurrentItem(0, false);
                tvWlgl.setTextColor(mContext.getResources().getColor(R.color.zangqing));
                tvClgl.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
                tvGrzx.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
                break;
            case 1://全部菜品
                ivWlgl.setImageResource(R.mipmap.wlgl_false_wljl);
                ivClgl.setImageResource(R.mipmap.clgl_true_wljl);
                ivGrzx.setImageResource(R.mipmap.grzx_false_wljl);
                viewpager.setCurrentItem(1, false);
                tvWlgl.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
                tvClgl.setTextColor(mContext.getResources().getColor(R.color.zangqing));
                tvGrzx.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
                break;
            case 2://购物车
                ivWlgl.setImageResource(R.mipmap.wlgl_false_wljl);
                ivClgl.setImageResource(R.mipmap.clgl_false_wljl);
                ivGrzx.setImageResource(R.mipmap.grzx_true_wljl);
                viewpager.setCurrentItem(2, false);
                tvWlgl.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
                tvClgl.setTextColor(mContext.getResources().getColor(R.color.hintcolor));
                tvGrzx.setTextColor(mContext.getResources().getColor(R.color.zangqing));
                break;
        }
    }
}
