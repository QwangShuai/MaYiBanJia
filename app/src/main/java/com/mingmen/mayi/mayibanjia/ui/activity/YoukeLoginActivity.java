package com.mingmen.mayi.mayibanjia.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.DiquXuanzeBean;
import com.mingmen.mayi.mayibanjia.bean.JsonBean;
import com.mingmen.mayi.mayibanjia.bean.ZhuCeChengGongBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.DiQuDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.LianggeXuanXiangDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppManager;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.AddressPicker;
import cn.qqtheme.framework.picker.SinglePicker;

public class YoukeLoginActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.rl_phone_clear)
    RelativeLayout rlPhoneClear;
    @BindView(R.id.et_juese)
    TextView etJuese;
    @BindView(R.id.rl_juese)
    RelativeLayout rlJuese;
    @BindView(R.id.tv_diqu)
    TextView tvDiqu;
    @BindView(R.id.rl_diqu)
    RelativeLayout rlDiqu;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.bt_zhuce)
    Button btZhuce;

    private Context mContext;

    //省市区
    private ArrayList<JsonBean> options1Items = new ArrayList<>();//省
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();//市
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();//区
    private int sheng = 0;
    private int city = 0;
    private int qu = 0;
    private int[] pos = new int[3];
    private String province_name;
    private String city_name;
    private String region_name;
    private LianggeXuanXiangDialog dialog;

    //角色
    private String name;
    private List<String> list = new ArrayList<>();

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private String xingQuDian;

    @Override
    public int getLayoutId() {
        return R.layout.activity_youke_login;
    }

    @Override
    protected void initData() {
        tvTitle.setText("游客登录");
        mContext = YoukeLoginActivity.this;
        StringUtil.setInputNoEmoj(etName,4);
        EventBus.getDefault().register(this);
        initLocation();
        //开始定位
        startLocation();
//        initJsonData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.rl_juese, R.id.rl_diqu, R.id.bt_login, R.id.bt_zhuce})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                break;
            case R.id.rl_juese:
//                list.add("买家");
//                list.add("卖家");
////                list.add("社区市场");
//                final SinglePicker<String> picker = new SinglePicker<String>(YoukeLoginActivity.this, list);
//                picker.setCanceledOnTouchOutside(false);
//                picker.setSelectedIndex(1);
//                picker.setCycleDisable(false);
//                picker.setOnItemPickListener(new SinglePicker.OnItemPickListener<String>() {
//                    @Override
//                    public void onItemPicked(int index, String item) {
//                        name = item;
//                        etJuese.setText(item);
//                        picker.dismiss();
//                    }
//                });
//                picker.show();

                dialog = new LianggeXuanXiangDialog(mContext,
                        mContext.getResources().getIdentifier("BottomDialog", "style", mContext.getPackageName()));
                dialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.LEFT | Gravity.RIGHT);
                dialog.showDialog("买家", "卖家");
                dialog.getTvXuanxiang1().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        name = "买家";
                        etJuese.setText("买家");
                    }
                });
                dialog.getTvXuanxiang2().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        name = "卖家";
                        etJuese.setText("卖家");
//                        llShowTejia.setVisibility(View.GONE);
                    }
                });
                break;
            case R.id.rl_diqu:
//                showCityPicker();
                DiQuDialog diQuDialog = new DiQuDialog(mContext,
                        mContext.getResources().getIdentifier("BottomDialog", "style", mContext.getPackageName()));
                diQuDialog.getWindow().setGravity(Gravity.BOTTOM);
                diQuDialog.showDialog(sheng,city,qu,province_name,city_name,region_name);
                break;
            case R.id.bt_login:
                if(!StringUtil.exist(etName.getText().toString().trim())){
                    ToastUtil.showToastLong("请输入正确的姓名");
                } else if(TextUtils.isEmpty(etPhone.getText().toString().trim())){
                    ToastUtil.showToastLong("请输入手机号");
                } else if(TextUtils.isEmpty(name)){
                    ToastUtil.showToastLong("请选择身份");
                } else if(TextUtils.isEmpty(province_name)){
                    ToastUtil.showToastLong("请选择省市区");
                } else {
                    youke();
                }
                break;
            case R.id.bt_zhuce:
                Intent zhuce = new Intent(mContext, ZhuCeActivity.class);
                zhuce.putExtra("yemian", "1");
                startActivity(zhuce);
                break;
        }
    }

    private void youke(){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .youkeLogin(province_name,city_name,region_name,name,
                                        etPhone.getText().toString(),etName.getText().toString()))
                .setDataListener(new HttpDataListener<ZhuCeChengGongBean>() {
                    @Override
                    public void onNext(ZhuCeChengGongBean bean) {
                        Log.e("token", bean.getToken() + "===");
                        PreferenceUtils.putString(MyApplication.mContext, "phone", bean.getTelephone());
                        PreferenceUtils.putString(MyApplication.mContext, "host_account_type", bean.getHost_account_type());
                        PreferenceUtils.putBoolean(MyApplication.mContext, "isLogin", true);
                        PreferenceUtils.putString(MyApplication.mContext, "token", bean.getToken());
                        PreferenceUtils.putString(MyApplication.mContext, "juese", bean.getRole());
                        if (StringUtil.isValid(bean.getName())) {
                            PreferenceUtils.putString(MyApplication.mContext, "name", bean.getName());
                        }
                        PreferenceUtils.putInt(MyApplication.mContext, "random_id", bean.getRandom_id());
                        PreferenceUtils.putBoolean(MyApplication.mContext, "youke", true);
                        Intent intent = new Intent(mContext, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        AppManager.getAppManager().finishAllActivity();
                    }
                    @Override
                    protected Object clone() throws CloneNotSupportedException {
                        return super.clone();
                    }
                });
    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = StringUtil.getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = StringUtil.parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCitylist().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCitylist().get(c).getQuymc();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCitylist().get(c).getQulist() == null
                        || jsonBean.get(i).getCitylist().get(c).getQulist().size() == 0) {
                    City_AreaList.add("");
                } else {
                    for (int d = 0; d < jsonBean.get(i).getCitylist().get(c).getQulist().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCitylist().get(c).getQulist().get(d).getQuymc();
                        if (!AreaName.equals("市辖区")) {
                            City_AreaList.add(AreaName);
                        }
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
    }
    private void showCityPicker() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() + "-" +
                        options2Items.get(options1).get(options2) + "-" +
                        options3Items.get(options1).get(options2).get(options3);
                tvDiqu.setText(tx);
                province_name = options1Items.get(options1).getQuymc();
                city_name = options1Items.get(options1).getCitylist().get(options2).getQuymc();
                city = options1Items.get(options1).getCitylist().get(options2).getQulist().get(options3).getQuybm();
                region_name = options1Items.get(options1).getCitylist().get(options2).getQulist().get(options3).getQuymc();

                pos[0] = options1;
                pos[1] = options2;
                pos[2] = options3;

                Log.e("我的区域编号", city + "");
            }
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.setSelectOptions(pos[0], pos[1], pos[2]);
        pvOptions.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyLocation();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateTimer(DiquXuanzeBean bean){
        sheng = bean.getSheng_bm();
        city = bean.getCity_bm();
        qu = bean.getQu_bm();

        province_name = bean.getSheng_name();
        city_name = bean.getCity_name();
        region_name = bean.getQu_name();

        tvDiqu.setText(bean.getSheng_name()+"-"+bean.getCity_name()+"-"+bean.getQu_name());
    }

    /**
     * 初始化定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(mContext.getApplicationContext());
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
                    city_name = location.getCity();
                    province_name = location.getProvince();
                    region_name = location.getDistrict();
                    xiangxi = location.getAddress();
                    cityCode = location.getCityCode();
                    xingQuDian = location.getPoiName();
//                    ToastUtil.showToast(city+"---"+cityCode);
                }
//                else {
//                    ToastUtil.showToast("定位失败,请检查是否开启定位功能");
//                }
                stopLocation();
                if(StringUtil.isValid(province_name)&&StringUtil.isValid(city_name)&&StringUtil.isValid(region_name)){
                    tvDiqu.setText(province_name + "-" + city_name + "-" + region_name);
                }
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
}
