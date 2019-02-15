package com.mingmen.mayi.mayibanjia.ui.activity.dingdan;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;

/**
 * Created by Administrator on 2018/7/28/028.
 */

public class OrderAdapter  extends FragmentPagerAdapter {


    private Context mContext;
    private String[] Titles = {"全部","待付款","待发货","待收货","已收货","已完成"};
    private String[] fragments = {

            QuanBuFragment.class.getName(),
            DaiFuKuanFragment.class.getName(),
            DaiFaHuoFragment.class.getName(),
            DaiShouHuoFragment.class.getName(),
            YiShouHuoFragment.class.getName(),
            YiWanChengFragment.class.getName(),

    };

    private String type = "";
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

    public void setFragments(String type){
        if(type.equals("1")){
            fragments = new String[]{DaiFuKuanFragment.class.getName(),YiWanChengFragment.class.getName(),};
            Titles = new String[]{"待付款","已完成"};
        } else if(type.equals("4")){
            fragments = new String[]{DaiShouHuoFragment.class.getName(),YiShouHuoFragment.class.getName(),};
            Titles = new String[]{"待收货","已收货"};
        } else {
            fragments = new String[]{DaiFuKuanFragment.class.getName(),DaiShouHuoFragment.class.getName(),
                    YiShouHuoFragment.class.getName(),YiWanChengFragment.class.getName()};
            Titles = new String[]{"待付款","待收货","已收货","已完成"};
        }
        notifyDataSetChanged();
    }
}
