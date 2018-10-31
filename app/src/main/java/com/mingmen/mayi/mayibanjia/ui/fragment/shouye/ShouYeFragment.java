package com.mingmen.mayi.mayibanjia.ui.fragment.shouye;

import android.content.Intent;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.ShouYeBannerBean;
import com.mingmen.mayi.mayibanjia.bean.ShouYeLeiBean;
import com.mingmen.mayi.mayibanjia.bean.ShouYeTeJiaBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.FCGDiQuXuanZeActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.SouSuoActivity;
import com.mingmen.mayi.mayibanjia.ui.base.BaseFragment;
import com.mingmen.mayi.mayibanjia.ui.fragment.shouye.adapter.ShouYeAdapter;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/4/004.
 */

public class ShouYeFragment extends BaseFragment {

    @BindView(R.id.rv_shouye)
    RecyclerView rvShouye;
    @BindView(R.id.iv_facaigou)
    ImageView ivFacaigou;
    @BindView(R.id.tv_dingwei)
    TextView tvDingwei;
    @BindView(R.id.ll_dingwei)
    LinearLayout llDingwei;
    @BindView(R.id.ll_sousuo)
    LinearLayout llSousuo;
    @BindView(R.id.srl_shuaxin)
    SwipeRefreshLayout srlShuaxin;
    private View viewSPYXFragment;
    private ShouYeAdapter adapter;
    private List<ShouYeBannerBean> bannerBean = new ArrayList<>();
    private List<ShouYeLeiBean> leiBean = new ArrayList<>();
    private List<ShouYeTeJiaBean> teJiaBean = new ArrayList<>();
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private String xingQuDian;

    @Override
    protected View getSuccessView() {
        viewSPYXFragment = View.inflate(MyApplication.mContext, R.layout.fragment_shouye, null);
        ButterKnife.bind(this, viewSPYXFragment);
        return viewSPYXFragment;
    }

    @Override
    protected void loadData() {

        if (AppUtil.isConnNet()) {
            stateLayout.showSuccessView();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getActivity().getWindow();
                //设置状态栏颜
                window.setStatusBarColor(getResources().getColor(R.color.zangqing));
            }
        } else {
            stateLayout.showErrorView();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getActivity().getWindow();
                //设置状态栏颜
                window.setStatusBarColor(getResources().getColor(R.color.white));
            }
        }
        srlShuaxin.setColorSchemeResources(R.color.zangqing, R.color.zangqing,
                R.color.zangqing, R.color.zangqing);
        srlShuaxin.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 设置可见
                srlShuaxin.setRefreshing(true);
                // 重置adapter的数据源为空
                // 获取第第0条到第PAGE_COUNT（值为10）条的数据
                getShouyeBanner();


                srlShuaxin.setRefreshing(false);
            }
        });

        //初始化定位
        initLocation();
        //开始定位
        startLocation();


        getShouyeBanner();

    }

    private void getShouyeTeJia() {
        HttpManager.getInstance()
                .with(getActivity())
                .setObservable(
                        RetrofitManager
                                .getService()
                                . getShouYeTeJia(PreferenceUtils.getString(MyApplication.mContext, "token","")))
                .setDataListener(new HttpDataListener<List<ShouYeTeJiaBean>>() {

                    @Override
                    public void onNext(List<ShouYeTeJiaBean> list) {
                        teJiaBean=new ArrayList<ShouYeTeJiaBean>();
                        teJiaBean.addAll(list);
                        Log.e("tejiatejia",teJiaBean.size()+"---");
                        adapter = new ShouYeAdapter(getActivity(), bannerBean, leiBean, teJiaBean,(MainActivity) getActivity());
                        rvShouye.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        rvShouye.setAdapter(adapter);

                    }
                });
    }
//

    /**
     * 初始化定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(getActivity().getApplicationContext());
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }

    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(true);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }

    private String city = "哈尔滨市";
    private String sheng;
    private String qu;
    private String xiangxi;
    private String cityCode = "230100";
    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {


                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if (location.getErrorCode() == 0) {
                    city = location.getCity();
                    sheng = location.getProvince();
                    qu = location.getDistrict();
                    xiangxi = location.getAddress();
                    cityCode = location.getCityCode();
                    xingQuDian = location.getPoiName();
//                    ToastUtil.showToast(city+"---"+cityCode);
                } else {
                    ToastUtil.showToast("定位失败,请检查是否开启定位功能");
                    Log.e("错误描述", "错误描述:" + location.getLocationDetail());
                    Log.e("错误码", "错误码是:" + location.getErrorCode());

                    Log.e("错误信息", "错误信息:" + location.getErrorInfo() + "\n");
                }
                stopLocation();
                Log.e("xingQuDian", xingQuDian);
                Log.e("xiangxi", sheng + "-" + city + "-" + qu + "-" + xiangxi);
                tvDingwei.setText(city);
//                tvCitySelected.setText(city);
//                tvCurrentCity.setText(city);
//                //附近位置
//                jianSuoFujin();
            } else {
//                tvLocationAddress.setText("定位失败");
            }
        }
    };

    /**
     * 开始定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void startLocation() {
        //根据控件的选择，重新设置定位参数
//        resetOption();
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 停止定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void stopLocation() {
        // 停止定位
        locationClient.stopLocation();
    }

    /**
     * 销毁定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    private void getShouyeFenLei() {
        HttpManager.getInstance()
                .with(getActivity())
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getShouYeFenLei(PreferenceUtils.getString(MyApplication.mContext, "token","")))
                .setDataListener(new HttpDataListener<List<ShouYeLeiBean>>() {

                    @Override
                    public void onNext(List<ShouYeLeiBean> list) {
                        leiBean=new ArrayList<ShouYeLeiBean>();
                        leiBean.addAll(list);
                        Log.e("fenleifenlei", leiBean.size() + "---");
                        getShouyeTeJia();
                    }
                },false);
    }

    private void getShouyeBanner() {
        HttpManager.getInstance()
                .with(getActivity())
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getbanner())
                .setDataListener(new HttpDataListener<List<ShouYeBannerBean>>() {

                    @Override
                    public void onNext(List<ShouYeBannerBean> list) {
                        bannerBean=new ArrayList<ShouYeBannerBean>();
                        bannerBean.addAll(list);
                        Log.e("fenleifenlei", bannerBean.size() + "---");
                        getShouyeFenLei();
                    }
                },false);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        destroyLocation();
    }

    @OnClick({R.id.ll_sousuo, R.id.iv_facaigou})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_sousuo:
                Intent intent=new Intent(getActivity(),SouSuoActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_facaigou:
                Intent caigouintent=new Intent(getActivity(),FCGDiQuXuanZeActivity.class);
                startActivity(caigouintent);
                break;
        }
    }
}
