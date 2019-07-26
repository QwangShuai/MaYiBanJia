package com.mingmen.mayi.mayibanjia.ui.activity.wuliujingli;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mingmen.mayi.mayibanjia.ui.activity.wuliusiji.SijiDaiquhuoFragment;
import com.mingmen.mayi.mayibanjia.ui.activity.wuliusiji.SijiDaisongdaFragment;
import com.mingmen.mayi.mayibanjia.ui.activity.wuliusiji.SijiQuanbuFragment;
import com.mingmen.mayi.mayibanjia.ui.activity.wuliusiji.SijiYiwanchengFragment;
import com.mingmen.mayi.mayibanjia.ui.fragment.cheliangguanli.CheLiangGuanLiFragment;

/**
 * Created by Administrator on 2018/7/28/028.
 */

public class JingliAdapter extends FragmentPagerAdapter {


    private Context mContext;
    private String[] Titles = {"全部","未分车","已分车","配送中","已完成","未到达"};
    private String[] fragments = {
            JingliQuanbuFragment.class.getName(),
            JingliWeifencheFragment.class.getName(),
            JingliYifencheFragment.class.getName(),
            JingliPeisongzhongFragment.class.getName(),
            JingliYibiangengFragment.class.getName(),
            JingliWeifencheFragment.class.getName(),
    };

    public JingliAdapter(FragmentManager fm, Context context) {
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
