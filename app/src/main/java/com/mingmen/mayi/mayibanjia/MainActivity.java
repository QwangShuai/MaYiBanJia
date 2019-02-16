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

import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.ZiZhangHuDetailsBean;
import com.mingmen.mayi.mayibanjia.ui.activity.CaiGouXuQiuActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.FCGDiQuXuanZeActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.LoginActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.myinterface.MainCallBack;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.fragment.gouwuche.GouWuCheFragment;
import com.mingmen.mayi.mayibanjia.ui.fragment.quanbucaipin.QuanBuCaiPinFragment;
import com.mingmen.mayi.mayibanjia.ui.fragment.shouye.ShouYeFragment;
import com.mingmen.mayi.mayibanjia.ui.fragment.wode.WoDeFragment;
import com.mingmen.mayi.mayibanjia.ui.view.noscrollviewpager.NoScrollViewPager;
import com.mingmen.mayi.mayibanjia.utils.AppManager;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.QuanXian;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;

public class MainActivity extends BaseActivity {

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
    private Context context = MainActivity.this;

    public static MainActivity instance = null;
    private String sp_type = "";
    private String sp_id = "";
    private String zzh = "";
    //    private String role ="";
    private List<ZiZhangHuDetailsBean.RoleListBean> role = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        StatusBarCompat.translucentStatusBar(this);

        ButterKnife.bind(this);
        //检测版本
        updataVersion();
        //权限
        QuanXian.huoqu(MainActivity.this);
        //布局
        initview();
        instance = this;
    }


    private void initview() {
        zzh = PreferenceUtils.getString(MyApplication.mContext, "host_account_type", "");
        if (zzh.equals("0")) {

        } else {
            role = PreferenceUtils.getQuanxianList(MyApplication.mContext, "quanxian");
        }
        ShouYeFragment shouYeFragment = new ShouYeFragment();
        QuanBuCaiPinFragment quanBuCaiPinFragment = new QuanBuCaiPinFragment();
        GouWuCheFragment gouWuCheFragment = new GouWuCheFragment();
        WoDeFragment woDeFragment = new WoDeFragment();
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
        int tosome = getIntent().getIntExtra("tosome", 0);
        Log.e("tosome", tosome + "---");
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
    public void onBackPressed() {
//        super.onBackPressed();
        exit();
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            exit();
//            return false;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            AppManager.getAppManager().AppExit(context);
//            if(LoginActivity.instance!=null){
//                LoginActivity.instance.finish();
//            }
//            System.exit(0);
        }
    }

    @OnClick({R.id.iv_shouye, R.id.iv_quanbucaipin, R.id.iv_gouwuche, R.id.iv_wode, R.id.iv_facaigou})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_shouye:
//                ivFacaigou.setVisibility(View.VISIBLE);
                if (zzh.equals("0")) {
                    gaibianye(0);
                } else {
                    ToastUtil.showToastLong("子账户无权查看 ");
                }

                break;
            case R.id.iv_quanbucaipin:
//                ivFacaigou.setVisibility(View.GONE);
                if (zzh.equals("0")) {
                    gaibianye(1);
                } else {
                    ToastUtil.showToastLong("子账户无权查看 ");
                }

                break;
            case R.id.iv_gouwuche:
//                ivFacaigou.setVisibility(View.GONE);
                if (zzh.equals("0")) {
                    gaibianye(2);
                } else {
                    ToastUtil.showToastLong("子账户无权查看 ");
                }
                break;
            case R.id.iv_wode:
//                ivFacaigou.setVisibility(View.GONE);
                gaibianye(3);
                break;
            case R.id.iv_facaigou:
                if (zzh.equals("0")) {
                    Intent caigouintent = new Intent(MainActivity.this, FCGDiQuXuanZeActivity.class);
                    startActivity(caigouintent);
                } else {
                    int mysize = role == null ? 0 : role.size();
                    if (mysize != 0) {
                        if (isClick()) {
                            Intent caigouintent = new Intent(MainActivity.this, FCGDiQuXuanZeActivity.class);
                            startActivity(caigouintent);
                        }
                    } else {
                        ToastUtil.showToastLong("子账户无权查看 ");
                    }
                }

                break;
        }
    }

    public void gaibianye(int ye) {
        switch (ye) {
            case 0://首页
                ivShouye.setImageResource(R.mipmap.shouye_pre);
                ivQuanbucaipin.setImageResource(R.mipmap.quanbucaipin);
                ivGouwuche.setImageResource(R.mipmap.gouwuche);
                ivWode.setImageResource(R.mipmap.wode);
                viewPager.setCurrentItem(0, false);
                Log.e("sss", "首页");
                break;
            case 1://全部菜品
                changeView("", "");
                break;
            case 2://购物车
                ivShouye.setImageResource(R.mipmap.shouye);
                ivQuanbucaipin.setImageResource(R.mipmap.quanbucaipin);
                ivGouwuche.setImageResource(R.mipmap.gouwuche_pre);
                ivWode.setImageResource(R.mipmap.wode);
                viewPager.setCurrentItem(2, false);
                Log.e("sss", "购物车");
                break;
            case 3://我的
                ivShouye.setImageResource(R.mipmap.shouye);
                ivQuanbucaipin.setImageResource(R.mipmap.quanbucaipin);
                ivGouwuche.setImageResource(R.mipmap.gouwuche);
                ivWode.setImageResource(R.mipmap.wode_pre);
                viewPager.setCurrentItem(3, false);
                Log.e("sss", "我的");
                break;
        }
    }

    public void setType(String type) {
        this.sp_type = type;
    }

    public String getType() {
        return sp_type;
    }

    public String getSp_id() {
        return sp_id;
    }

    public void changeView(String sp_type, String sp_id) {
        this.sp_type = sp_type;
        this.sp_id = sp_id;
        ivShouye.setImageResource(R.mipmap.shouye);
        ivQuanbucaipin.setImageResource(R.mipmap.quanbucaipin_pre);
        ivGouwuche.setImageResource(R.mipmap.gouwuche);
        ivWode.setImageResource(R.mipmap.wode);
        viewPager.setCurrentItem(1, false);
    }

    private boolean isClick() {
        boolean b = false;
        for (int i = 0; i < role.size(); i++) {
            if (role.get(i).getRole_id().equals("5")) {
                PreferenceUtils.putString(MyApplication.mContext, "isShenPi", "5");
                b = true;
            } else if (role.get(i).getRole_id().equals("2")) {
                b = true;
            }
        }

        return b;
    }
}
