package com.mingmen.mayi.mayibanjia.ui.activity.dingdan;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Administrator on 2018/7/28/028.
 */

public class OrderAdapter  extends FragmentPagerAdapter {


    private Context mContext;
    private String[] Titles = {"全部","待付款","待发货","待收货","已完成"};
    private final String[] fragments = {

            QuanBuFragment.class.getName(),
            DaiFuKuanFragment.class.getName(),
            DaiFaHuoFragment.class.getName(),
            DaiShouHuoFragment.class.getName(),
            YiWanChengFragment.class.getName(),

    };
    public OrderAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext=context;
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
