package com.mingmen.mayi.mayibanjia.ui.activity.wuliusiji;

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

/**
 * Created by Administrator on 2018/7/28/028.
 */

public class SijiAdapter extends FragmentPagerAdapter {


    private Context mContext;
    private String[] Titles = {"全部","待确认","待取货","待送货","已完成"};
    private String[] fragments = {

            SijiQuanbuFragment.class.getName(),
            SijiDaijiedanFragment.class.getName(),
            SijiDaiquhuoFragment.class.getName(),
            SijiDaisongdaFragment.class.getName(),
            SijiYiwanchengFragment.class.getName(),
    };

    public SijiAdapter(FragmentManager fm, Context context) {
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
