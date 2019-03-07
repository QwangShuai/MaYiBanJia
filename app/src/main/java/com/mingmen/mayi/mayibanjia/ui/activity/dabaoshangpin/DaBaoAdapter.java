package com.mingmen.mayi.mayibanjia.ui.activity.dabaoshangpin;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mingmen.mayi.mayibanjia.ui.activity.dingdan.DaiFaHuoFragment;
import com.mingmen.mayi.mayibanjia.ui.activity.dingdan.DaiFuKuanFragment;
import com.mingmen.mayi.mayibanjia.ui.activity.dingdan.DaiShouHuoFragment;
import com.mingmen.mayi.mayibanjia.ui.activity.dingdan.QuanBuFragment;
import com.mingmen.mayi.mayibanjia.ui.activity.dingdan.YiShouHuoFragment;
import com.mingmen.mayi.mayibanjia.ui.activity.dingdan.YiWanChengFragment;
import com.mingmen.mayi.mayibanjia.ui.activity.ghdingdan.GHDaiDaBaoFragment;

/**
 * Created by Administrator on 2018/7/28/028.
 */

public class DaBaoAdapter extends FragmentPagerAdapter {


    private Context mContext;
    private String[] Titles = {"待打包","待确认","已打包"};
    private String[] fragments = {

            WeiDaBaoFragment.class.getName(),
            DaiQueRenFragment.class.getName(),
            YiDaBaoFragment.class.getName(),
    };

    public DaBaoAdapter(FragmentManager fm, Context context) {
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
