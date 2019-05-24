package com.mingmen.mayi.mayibanjia.ui.activity.ghdingdan;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Administrator on 2018/9/29.
 */

public class GHAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private String[] Titles = {"全部","待打包","待发货","已发货","待确认","已完成","已违规"};
    private final String[] fragments = {

            GHQuanBuFragment.class.getName(),
            GHDaiDaBaoFragment.class.getName(),
            GHDaiFaHuoFragment.class.getName(),
            GHYiFaHuoFragment.class.getName(),
            GHDaiQueRenFragment.class.getName(),
            GHYiShouHuoFragment.class.getName(),
            GHWeiGuiFragment.class.getName()
    };

    public GHAdapter(FragmentManager fm,Context mContext) {
        super(fm);
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {
        return Fragment.instantiate(mContext,fragments[position]);
    }

    @Override
    public int getCount() {
        return Titles.length;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }
}
