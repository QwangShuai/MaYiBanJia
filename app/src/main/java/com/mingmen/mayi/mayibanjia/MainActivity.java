package com.mingmen.mayi.mayibanjia;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.mingmen.mayi.mayibanjia.ui.activity.CaiGouXuQiuActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.FCGDiQuXuanZeActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.myinterface.MainCallBack;
import com.mingmen.mayi.mayibanjia.ui.fragment.gouwuche.GouWuCheFragment;
import com.mingmen.mayi.mayibanjia.ui.fragment.quanbucaipin.QuanBuCaiPinFragment;
import com.mingmen.mayi.mayibanjia.ui.fragment.shouye.ShouYeFragment;
import com.mingmen.mayi.mayibanjia.ui.fragment.wode.WoDeFragment;
import com.mingmen.mayi.mayibanjia.ui.view.noscrollviewpager.NoScrollViewPager;
import com.mingmen.mayi.mayibanjia.utils.QuanXian;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;

public class MainActivity extends AppCompatActivity{

    @BindView(R.id.view_fenge)
    View viewFenge;
    @BindView(R.id.iv_shouye)
    ImageView ivShouye;
    @BindView(R.id.iv_quanbucaipin)
    ImageView ivQuanbucaipin;
    @BindView(R.id.iv_gouwuche)
    ImageView ivGouwuche;
    @BindView(R.id.iv_wode)
    ImageView ivWode;
    @BindView(R.id.iv_facaigou)
    ImageView ivFacaigou;
    @BindView(R.id.viewpager)
    NoScrollViewPager viewPager;
    private long exitTime = 0;
    private List<Fragment> fragments = new ArrayList<>();
    private Context context=MainActivity.this;

    public static MainActivity instance = null;
    private String sp_type ="";
    private String sp_id ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            //设置状态栏颜
//            window.setStatusBarColor(getResources().getColor(R.color.zangqing));
//        }
        setContentView(R.layout.activity_main);
        StatusBarCompat.translucentStatusBar(this);

        ButterKnife.bind(this);
        //检测版本
        updataVersion();
        //权限
        QuanXian.huoqu(MainActivity.this);
        //布局
        initview();
        instance=this;
    }


    private void initview() {

        ShouYeFragment shouYeFragment=new ShouYeFragment();
        QuanBuCaiPinFragment quanBuCaiPinFragment=new QuanBuCaiPinFragment();
        GouWuCheFragment gouWuCheFragment=new GouWuCheFragment();
        WoDeFragment woDeFragment=new WoDeFragment();
        fragments.add(shouYeFragment);
        fragments.add(quanBuCaiPinFragment);
        fragments.add(gouWuCheFragment);
        fragments.add(woDeFragment);
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
        viewPager.setAdapter(adapter);

        viewPager.setOnPageChangeListener(new NoScrollViewPager.OnPageChangeListener() {
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
        viewPager.setNoScroll(true);
        viewPager.setOffscreenPageLimit(0); //设置向左和向右都缓存limit个页面
        int tosome =  getIntent().getIntExtra("tosome",0);
        Log.e("tosome",tosome+"---");
        gaibianye(tosome);
    }

    /**
     * 检测版本
     */
    private void updataVersion() {
//        VersionParams.Builder builder = new VersionParams.Builder()
//                .setRequestUrl("http://www.baidu.com")
//                .setService(UpdataSerive.class);
//        builder.setShowNotification(true);
//        CustomVersionDialogActivity.customVersionDialogIndex = 2;
//        builder.setCustomDownloadActivityClass(CustomVersionDialogActivity.class);
//        CustomVersionDialogActivity.isCustomDownloading = true;
//        builder.setCustomDownloadActivityClass(CustomVersionDialogActivity.class);
//        AllenChecker.startVersionCheck((Application) MyApplication.mContext, builder.build());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
//            System.exit(0);
        }
    }
    @OnClick({R.id.iv_shouye, R.id.iv_quanbucaipin,R.id.iv_gouwuche, R.id.iv_wode,R.id.iv_facaigou})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_shouye:
//                ivFacaigou.setVisibility(View.VISIBLE);
                gaibianye(0);
                break;
            case R.id.iv_quanbucaipin:
//                ivFacaigou.setVisibility(View.GONE);
                gaibianye(1);
                break;
            case R.id.iv_gouwuche:
//                ivFacaigou.setVisibility(View.GONE);
                gaibianye(2);
                break;
            case R.id.iv_wode:
//                ivFacaigou.setVisibility(View.GONE);
                gaibianye(3);
                break;
            case R.id.iv_facaigou:
                Intent caigouintent=new Intent(MainActivity.this,FCGDiQuXuanZeActivity.class);
                startActivity(caigouintent);
                break;
        }
    }

    public void gaibianye(int ye){
        switch(ye){
            case 0://首页
                ivShouye.setImageResource(R.mipmap.shouye_pre);
                ivQuanbucaipin.setImageResource(R.mipmap.quanbucaipin);
                ivGouwuche.setImageResource(R.mipmap.gouwuche);
                ivWode.setImageResource(R.mipmap.wode);
                viewPager.setCurrentItem(0, false);
                Log.e("sss","首页");
                break;
            case 1://全部菜品
                changeView("","");
                break;
            case 2://购物车
                ivShouye.setImageResource(R.mipmap.shouye);
                ivQuanbucaipin.setImageResource(R.mipmap.quanbucaipin);
                ivGouwuche.setImageResource(R.mipmap.gouwuche_pre);
                ivWode.setImageResource(R.mipmap.wode);
                viewPager.setCurrentItem(2, false);
                Log.e("sss","购物车");
                break;
            case 3://我的
                ivShouye.setImageResource(R.mipmap.shouye);
                ivQuanbucaipin.setImageResource(R.mipmap.quanbucaipin);
                ivGouwuche.setImageResource(R.mipmap.gouwuche);
                ivWode.setImageResource(R.mipmap.wode_pre);
                viewPager.setCurrentItem(3, false);
                Log.e("sss","我的");
                break;
        }
    }

    public void setType(String type){
        this.sp_type = type;
    }

    public String getType(){
        return sp_type;
    }
    public String getSp_id(){
        return sp_id;
    }
    public void changeView(String sp_type,String sp_id) {
        this.sp_type = sp_type;
        this.sp_id = sp_id;
        ivShouye.setImageResource(R.mipmap.shouye);
        ivQuanbucaipin.setImageResource(R.mipmap.quanbucaipin_pre);
        ivGouwuche.setImageResource(R.mipmap.gouwuche);
        ivWode.setImageResource(R.mipmap.wode);
        viewPager.setCurrentItem(1, false);
    }
}
