package com.mingmen.mayi.mayibanjia.ui.activity.qiangdan;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mingmen.mayi.mayibanjia.ui.activity.jiaoyiliushui.JYMXQuanBuFragment;
import com.mingmen.mayi.mayibanjia.ui.activity.jiaoyiliushui.JYMXShouRuFragment;
import com.mingmen.mayi.mayibanjia.ui.activity.jiaoyiliushui.JYMXZhiChuFragment;

/**
 * Created by Administrator on 2018/9/29.
 */

public class QDFragmentAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private String[] Titles = {"全部","待抢单","抢单中","抢单失败","抢单成功"};
    private final String[] fragments = {

            QDQuanbuFragment.class.getName(),
            QDDaiqiangdanFragment.class.getName(),
            QDZhongFragment.class.getName(),
            QDShibaiFragment.class.getName(),
            QDChenggongFragment.class.getName()

    };

    public QDFragmentAdapter(FragmentManager fm, Context mContext) {
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
