package com.mingmen.mayi.mayibanjia.ui.activity.jiaoyiliushui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mingmen.mayi.mayibanjia.ui.activity.ghdingdan.GHDaiFaHuoFragment;
import com.mingmen.mayi.mayibanjia.ui.activity.ghdingdan.GHQuanBuFragment;
import com.mingmen.mayi.mayibanjia.ui.activity.ghdingdan.GHYiFaHuoFragment;
import com.mingmen.mayi.mayibanjia.ui.activity.ghdingdan.GHYiShouHuoFragment;

/**
 * Created by Administrator on 2018/9/29.
 */

public class JYMXAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private String[] Titles = {"全部","收入","支出"};
    private final String[] fragments = {

            JYMXQuanBuFragment.class.getName(),
            JYMXShouRuFragment.class.getName(),
            JYMXZhiChuFragment.class.getName()

    };

    public JYMXAdapter(FragmentManager fm, Context mContext) {
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
