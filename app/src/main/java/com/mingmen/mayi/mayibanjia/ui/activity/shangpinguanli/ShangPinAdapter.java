package com.mingmen.mayi.mayibanjia.ui.activity.shangpinguanli;

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

public class ShangPinAdapter extends FragmentPagerAdapter {


    private Context mContext;
    private String[] Titles = {"全部","上架","下架","待审核","审核失败"};
    private String[] fragments = {
            QuanBuShangPinFragment.class.getName(),
            ShangJiaShangPinFragment.class.getName(),
            XiaJiaShangPinFragment.class.getName(),
            DaiShenHeShangPinFragment.class.getName(),
            ShenHeShiBaiShangPinFragment.class.getName(),
    };

    public ShangPinAdapter(FragmentManager fm, Context context) {
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
