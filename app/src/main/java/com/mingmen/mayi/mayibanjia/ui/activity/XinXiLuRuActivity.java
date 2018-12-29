package com.mingmen.mayi.mayibanjia.ui.activity;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.JsonBean;
import com.mingmen.mayi.mayibanjia.bean.ProvinceBean;
import com.mingmen.mayi.mayibanjia.bean.QiYeGuiMoBean;
import com.mingmen.mayi.mayibanjia.bean.QiYeLeiBieBean;
import com.mingmen.mayi.mayibanjia.bean.QiYeLieBiaoBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.PhotoDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.view.ShowViewCity;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.photo.FileStorage;
import com.mingmen.mayi.mayibanjia.utils.photo.QiNiuPhoto;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import cn.qqtheme.framework.picker.AddressPicker;
import cn.qqtheme.framework.picker.SinglePicker;

/**
 * Created by Administrator on 2018/7/24/024.
 */

public class XinXiLuRuActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_qiyemingcheng)
    EditText etQiyemingcheng;
    @BindView(R.id.tv_qiyeleibie)
    TextView tvQiyeleibie;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.et_qiyeguimo)
    TextView etQiyeguimo;
    @BindView(R.id.iv_tu)
    ImageView ivTu;
    @BindView(R.id.tv_quyuxuanze)
    TextView tvQuyuxuanze;
    @BindView(R.id.tv_jiedaoxuanze)
    TextView tvJiedaoxuanze;
    @BindView(R.id.et_xiangxidizhi)
    EditText etXiangxidizhi;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.bt_queding)
    TextView btQueding;
    private Uri imageUri;//原图保存地址
    private Uri outputUri;
    private String imagePath;
    private static final int REQUEST_PICK_IMAGE = 1; //相册选取
    private static final int REQUEST_CAPTURE = 2;  //拍照
    private static final int REQUEST_PICTURE_CUT = 3;  //剪裁图片
    private Bitmap bitmap = null;
    private PhotoDialog photoDialog;
    private boolean isClickCamera;
    private QiNiuPhoto qiNiuPhoto;
    private Context mContext;
    private String shidizhaopian;
    private ArrayList<ProvinceBean> zonglist;
    private ArrayList<ProvinceBean> shenglist;
    private String shengming;
    private ArrayList shilist;
    private String shiming;
    private ArrayList qulist;
    private int shiid;
    private String quming;
    private int quid;
    private String guimoname;
    private String guimoid;
    private String leibiename;
    private String leibieid;
    private SinglePicker<QiYeLeiBieBean> leibiepicker;
    private int shengid;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private String yewuyuanweizhi;
    private String qiyemingcheng;
    private String xiangxidizhi;
    private ArrayList jielist;
    private String jieid;
    private String jieming;
    private String rukou;
    private QiYeLieBiaoBean qiyexinxi;
    private String qiyeid;

    private ArrayList<JsonBean> options1Items = new ArrayList<>();//省
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();//市
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();//区
    int city=0;
    int[] pos= new int[3];
    @Override
    public int getLayoutId() {
        return R.layout.activity_xinxiluru;
    }

    @Override
    protected void initData() {
        mContext=XinXiLuRuActivity.this;
        qiNiuPhoto=new QiNiuPhoto(XinXiLuRuActivity.this);
        bundle=getIntent().getExtras();
        rukou = bundle.getString("rukou");
        if ("add".equals(rukou)){

        }else{
            String xinxi = bundle.getString("xinxi");
            Log.e("xinxi",xinxi);
            qiyexinxi = gson.fromJson(xinxi, QiYeLieBiaoBean.class);
            qiyeid = qiyexinxi.getCompany_id();
            etQiyemingcheng.setText(qiyexinxi.getCompany_name());
            tvQiyeleibie.setText(qiyexinxi.getLeiBieName());
            etQiyeguimo.setText(qiyexinxi.getGuiMoName());

            leibieid=qiyexinxi.getLeiBieId();
            leibiename=qiyexinxi.getLeiBieName();
//            guimoid=qiyexinxi.getGuiMoId();
            guimoname=qiyexinxi.getGuiMoName();
            Log.e("guimo",guimoname+"---"+guimoid);
            shengid= Integer.parseInt(qiyexinxi.getProvince());
            shiid= Integer.parseInt(qiyexinxi.getCity());
            quid= Integer.parseInt(qiyexinxi.getRegion());
            jieid=qiyexinxi.getStreet();
            shengming=qiyexinxi.getQuYMC();
            shiming=qiyexinxi.getQuYMCa();
            quming=qiyexinxi.getQuYMCb();
            jieming=qiyexinxi.getQuYMCc();
            shidizhaopian=qiyexinxi.getPhoto();
            xiangxidizhi=qiyexinxi.getSpecific_address();
            Log.e("xiangxidizhi",xiangxidizhi+"===");
            tvQuyuxuanze.setText(qiyexinxi.getQuYMC()+"-"+qiyexinxi.getQuYMCa()+"-"+qiyexinxi.getQuYMCb());
            tvJiedaoxuanze.setText(qiyexinxi.getQuYMCc());
            etQiyeguimo.setText(qiyexinxi.getGuiMoId());
            etXiangxidizhi.setText(qiyexinxi.getSpecific_address());
        }
        photoDialog = new PhotoDialog(mContext,
                mContext.getResources().getIdentifier("BottomDialog", "style", mContext.getPackageName()));
        photoDialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.LEFT | Gravity.RIGHT);

        //初始化定位
        initLocation();
        //开始定位
        startLocation();
        initJsonData();
    }

    @OnClick({R.id.tv_right, R.id.tv_qiyeleibie, R.id.iv_tu, R.id.tv_quyuxuanze,R.id.tv_jiedaoxuanze,R.id.bt_queding})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_right:
                finish();
                break;
            case R.id.tv_qiyeleibie:
                //企业类别
                getleibie();
                break;
//            case R.id.tv_qiyeguimo:
//                //企业规模
//                getguimo();
//                break;
            case R.id.iv_tu:
                //上传图片
                photoDialog.showDialog();
                photoDialog.getIvXiangce().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectFromAlbum();
                        isClickCamera = false;
                        photoDialog.cancel();
                    }
                });
                photoDialog.getIvZhaoxiang().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openCamera();
                        isClickCamera = true;
                        photoDialog.cancel();
                    }
                });
                photoDialog.getIvGuanbi().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        photoDialog.cancel();
                    }
                });
                break;
            case R.id.tv_quyuxuanze:
                showCityPicker();
                break;
            case R.id.bt_queding:
                qiyemingcheng = etQiyemingcheng.getText().toString().trim();
                guimoname = etQiyeguimo.getText().toString().trim();
                xiangxidizhi =etXiangxidizhi.getText().toString().trim();
                if(TextUtils.isEmpty(qiyemingcheng)){
                    ToastUtil.showToast("企业名称不可以为空");
                } else if(TextUtils.isEmpty(guimoname)){
                    ToastUtil.showToast("企业规模不可以为空");
                } else if(TextUtils.isEmpty(xiangxidizhi)){
                    ToastUtil.showToast("详细地址不可以为空");
                } else if(TextUtils.isEmpty(jieid)){
                    ToastUtil.showToast("街道地址不可以为空");
                } else if(!AppUtil.isMobile(etPhone.getText().toString().trim())&&!AppUtil.isPhone(etPhone.getText().toString().trim())){
                    ToastUtil.showToast("请输入正确的联系方式");
                }else {
                    if ("add".equals(rukou)){
                        qiyeluru();
                    }else{
                        xiugai();
                    }
                }

                break;
            case R.id.tv_jiedaoxuanze:
                //区域选择
                if(city==0){
                    ToastUtil.showToast("请先选择区域");
                } else {
//                    if (zonglist!=null){
//                        jiedialog();
//                    }else{
                        getsheng();
//                    }
                }

                break;
        }
    }
//修改
    private void xiugai() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .qiyexiugai(PreferenceUtils.getString(MyApplication.mContext, "token",""),qiyeid,qiyemingcheng,shengid+"",shiid+"",quid+"",jieid+"", xiangxidizhi,shidizhaopian,leibieid,guimoname,etPhone.getText().toString().trim()))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        Log.e("data",data+"---");
                        ToastUtil.showToast("修改成功");
                        finish();
                    }
                });
    }
    //企业录入
    private void qiyeluru() {
        Log.e("xinxixinxi",PreferenceUtils.getString(MyApplication.mContext, "token","")+"-"+qiyemingcheng+"-"+shengid+"-"+shiid+"-"+quid+"-"+jieid+"-"+ xiangxidizhi+"-"+yewuyuanweizhi+"-"+shidizhaopian+"-"+leibieid+"-"+guimoid);
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .qiyeluru(PreferenceUtils.getString(MyApplication.mContext, "token",""),qiyemingcheng,shengid+"",shiid+"",quid+"",jieid+"", xiangxidizhi,yewuyuanweizhi,shidizhaopian,leibieid,guimoname,etPhone.getText().toString().trim()))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        Log.e("data",data+"---");
                        ToastUtil.showToast("添加成功");
                        finish();
                        
                    }
                });
    }
    /**
     * 初始化定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(getApplicationContext());
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


    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        String sheng;
        String city;
        String qu;
        String xiangxi;
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {


                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明

                if (location.getErrorCode() == 0) {
//                    sb.append("定位成功" + "\n");
//                    sb.append("定位类型: " + location.getLocationType() + "\n");
//                    sb.append("经    度    : " + location.getLongitude() + "\n");
//                    sb.append("纬    度    : " + location.getLatitude() + "\n");
//                    sb.append("精    度    : " + location.getAccuracy() + "米" + "\n");
//                    sb.append("提供者    : " + location.getProvider() + "\n");
//                    jingDu = location.getLongitude();
//                    weiDu = location.getLatitude();
//                    sb.append("速    度    : " + location.getSpeed() + "米/秒" + "\n");
//                    sb.append("角    度    : " + location.getBearing() + "\n");
//                    // 获取当前提供定位服务的卫星个数
//                    sb.append("星    数    : " + location.getSatellites() + "\n");
//                    sb.append("国    家    : " + location.getCountry() + "\n");
//                    sb.append("省            : " + location.getProvince() + "\n");
//                    sb.append("市            : " + location.getCity() + "\n");

//                    province=location.getProvince();
                    sheng = location.getProvince();
                    city = location.getCity();
                    qu = location.getDistrict();
                    xiangxi = location.getAddress();

//                    sb.append("城市编码 : " + location.getCityCode() + "\n");
//                    sb.append("区            : " + location.getDistrict() + "\n");
//                    sb.append("区域 码   : " + location.getAdCode() + "\n");
//                    sb.append("地    址    : " + location.getAddress() + "\n");
//                    sb.append("兴趣点    : " + location.getPoiName() + "\n");
                    //定位完成的时间
//                    sb.append("定位时间: " + Utils.formatUTC(location.getTime(), "yyyy-MM-dd HH:mm:ss") + "\n");
                } else {
                    //定位失败
//                    sb.append("定位失败");
//                    sb.append("错误信息:" + location.getErrorInfo() + "\n");
//                    sb.append("错误描述:" + location.getLocationDetail() + "\n");
                    ToastUtil.showToast("定位失败,请检查是否开启定位功能");
                    Log.e("错误描述", "错误描述:" + location.getLocationDetail());
                    Log.e("错误码", "错误码是:" + location.getErrorCode());

                    Log.e("错误信息", "错误信息:" + location.getErrorInfo() + "\n");
                }
//                sb.append("***定位质量报告***").append("\n");
//                sb.append("* WIFI开关：").append(location.getLocationQualityReport().isWifiAble() ? "开启":"关闭").append("\n");
//                sb.append("* GPS状态：").append(getGPSStatusString(location.getLocationQualityReport().getGPSStatus())).append("\n");
//                sb.append("* GPS星数：").append(location.getLocationQualityReport().getGPSSatellites()).append("\n");
//                sb.append("****************").append("\n");
                //定位之后的回调时间
//                sb.append("回调时间: " + Utils.formatUTC(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + "\n");
                //解析定位结果，
                stopLocation();
                yewuyuanweizhi =xiangxi;
                Log.e("xiangxi", xiangxi);
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
    private void getsheng() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getsheng(city+""))
                .setDataListener(new HttpDataListener<List<ProvinceBean>>() {
                    @Override
                    public void onNext(final List<ProvinceBean> list) {
                        zonglist = new ArrayList<ProvinceBean>();
                        zonglist.addAll(list);
                        jiedialog();

                    }
                });
    }

    private void jiedialog() {
        if(zonglist.size()!=0){
            final SinglePicker<ProvinceBean> picker =new SinglePicker<ProvinceBean>(XinXiLuRuActivity.this,zonglist);
            picker.setCanceledOnTouchOutside(false);
            picker.setSelectedIndex(1);
            picker.setCycleDisable(false);
            picker.setOnItemPickListener(new SinglePicker.OnItemPickListener<ProvinceBean>() {
                @Override
                public void onItemPicked(int index, ProvinceBean item) {
                    jieming = item.getQuymc();
                    jieid = item.getQuybm() + "";
                    picker.dismiss();
                    tvJiedaoxuanze.setText(""+jieming);
                }
            });
            picker.show();
        } else {
            ToastUtil.showToast("暂无街道信息,信息录入失败");
        }
    }
    //企业规模
//    private void getguimo() {
//            HttpManager.getInstance()
//                    .with(mContext)
//                    .setObservable(
//                            RetrofitManager
//                                    .getService()
//                                    .getqygm())
//                    .setDataListener(new HttpDataListener<List<QiYeGuiMoBean>>() {
//                        @Override
//                        public void onNext(List<QiYeGuiMoBean> data) {
//                            final SinglePicker<QiYeGuiMoBean> picker =new SinglePicker<QiYeGuiMoBean>(XinXiLuRuActivity.this,data);
//                            picker.setCanceledOnTouchOutside(false);
//                            picker.setSelectedIndex(1);
//                            picker.setCycleDisable(false);
//                            picker.setOnItemPickListener(new SinglePicker.OnItemPickListener<QiYeGuiMoBean>() {
//                                @Override
//                                public void onItemPicked(int index, QiYeGuiMoBean item) {
//
//                                    guimoname = item.getSon_name();
//                                    guimoid = item.getSon_number();
//                                    etQiyeguimo.setText(guimoname);
//                                    Log.e("guimoname+guimoid",guimoname+"+"+guimoid);
//                                    picker.dismiss();
//                                }
//                            });
//                            picker.show();
//
//                        }
//                    });
//    }

//企业类别
    private void getleibie() {
            HttpManager.getInstance()
                    .with(mContext)
                    .setObservable(
                            RetrofitManager
                                    .getService()
                                    .getqylb())
                    .setDataListener(new HttpDataListener<List<QiYeLeiBieBean>>(){
                        @Override
                        public void onNext(List<QiYeLeiBieBean> data) {
                            leibiepicker = new SinglePicker<>(XinXiLuRuActivity.this,data);
                            leibiepicker.setCanceledOnTouchOutside(false);
                            leibiepicker.setSelectedIndex(1);
                            leibiepicker.setCycleDisable(false);
                            leibiepicker.setOnItemPickListener(new SinglePicker.OnItemPickListener<QiYeLeiBieBean>() {
                                @Override
                                public void onItemPicked(int index, QiYeLeiBieBean item) {
                                    leibiename = item.getSon_name();
                                    leibieid = item.getSon_number();
                                    tvQiyeleibie.setText(leibiename);
                                    Log.e("leibiename+leibieid",leibiename+"+"+leibieid);
                                    leibiepicker.dismiss();
                                }
                            });
                            leibiepicker.show();
                            
                        }
                    });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_PICK_IMAGE://从相册选择
                Log.e("xiangce","xiangce");
                if (data!=null){
                    if (Build.VERSION.SDK_INT >= 19) {
                        imagePath=handleImageOnKitKat(data);
                    } else {
                        imagePath=handleImageBeforeKitKat(data);
                    }
                }
                break;
            case REQUEST_CAPTURE://拍照
                Log.e("拍照","拍照");
                if (resultCode == RESULT_OK) {
                    cropPhoto();
                }
                break;
            case REQUEST_PICTURE_CUT://裁剪完成

                if (data!=null) {
                    Log.e("裁剪完成","裁剪完成");
                    try {
                        if (isClickCamera) {
                            Log.e("裁剪完成","裁剪完成111");
                            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(outputUri));
                        } else {
                            Log.e("裁剪完成","裁剪完成222");
                            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(outputUri));
//                            bitmap = BitmapFactory.decodeFile(imagePath);
                        }
                        qiniushangchuan();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private void qiniushangchuan() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .qiniushangchuan())
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String list) {
                        Log.e("fenleifenlei",list+"---");
                        String qiniudata = qiNiuPhoto.getImageAbsolutePath(XinXiLuRuActivity.this, outputUri);
                        String key = null;
                        String token =list ;
                        MyApplication.uploadManager.put(qiniudata, key, token,
                                new UpCompletionHandler() {
                                    @Override
                                    public void complete(String key, ResponseInfo info, JSONObject res) {
                                        //res包含hash、key等信息，具体字段取决于上传策略的设置
                                        if(info.isOK()) {
//                                            getImageAbsolutePath(CTDWanShanXinXiActivity.this,outputUri)
                                            Log.e("qiniu", "Upload Success");
                                            try {
                                                shidizhaopian = res.getString("key");
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            Log.e("keykey", shidizhaopian);
                                        } else {
                                            Log.e("qiniu", "Upload Fail");
                                            //如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因
                                        }
                                        Log.e("qiniu", key + ",\r\n " + info + ",\r\n " + res);
                                    }
                                }, null);
                        ivTu.setImageBitmap(bitmap);
                        
                    }
                });
    }

    /**
     * 从相册选择
     */
    public void selectFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, REQUEST_PICK_IMAGE);
    }

    /**
     * 打开系统相机
     */
    public void openCamera() {
        File file = new FileStorage().createIconFile();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            imageUri = FileProvider.getUriForFile(this, mContext.getApplicationContext().getPackageName()+".fileProvider", file);//通过FileProvider创建一个content类型的Uri
        } else {
            imageUri = Uri.fromFile(file);
        }
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
        }
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//将拍取的照片保存到指定URI
        startActivityForResult(intent, REQUEST_CAPTURE);
    }
    /**
     * 裁剪
     */
    public void cropPhoto() {
        File file = new FileStorage().createCropFile();
        //缩略图保存地址
        outputUri = Uri.fromFile(file);
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(imageUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, REQUEST_PICTURE_CUT);
    }

    @TargetApi(19)
    public String handleImageOnKitKat(Intent data) {
        imagePath = null;
        imageUri = data.getData();
        if (DocumentsContract.isDocumentUri(this, imageUri)) {
            //如果是document类型的uri,则通过document id处理
            String docId = DocumentsContract.getDocumentId(imageUri);
            if ("com.android.providers.media.documents".equals(imageUri.getAuthority())) {
                String id = docId.split(":")[1];//解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = qiNiuPhoto.getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.downloads.documents".equals(imageUri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = qiNiuPhoto.getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
            //如果是content类型的Uri，则使用普通方式处理
            imagePath = qiNiuPhoto.getImagePath(imageUri, null);
        } else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
            //如果是file类型的Uri,直接获取图片路径即可
            imagePath = imageUri.getPath();
        }
        cropPhoto();
        return imagePath;
    }

    public String handleImageBeforeKitKat(Intent intent) {
        imageUri = intent.getData();
        imagePath = qiNiuPhoto.getImagePath(imageUri, null);
        cropPhoto();
        return imagePath;
    }
    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = StringUtil.getJson(this,"province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = StringUtil.parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i=0;i<jsonBean.size();i++){//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c=0; c<jsonBean.get(i).getCitylist().size(); c++){//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCitylist().get(c).getQuymc();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCitylist().get(c).getQulist() == null
                        ||jsonBean.get(i).getCitylist().get(c).getQulist().size()==0) {
                    City_AreaList.add("");
                }else {

                    for (int d=0; d < jsonBean.get(i).getCitylist().get(c).getQulist().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCitylist().get(c).getQulist().get(d).getQuymc();

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
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
    private void showCityPicker(){
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText()+"-"+
                        options2Items.get(options1).get(options2)+"-"+
                        options3Items.get(options1).get(options2).get(options3);
                tvQuyuxuanze.setText(tx);
                shengid = options1Items.get(options1).getQuybm();
                shiid = options1Items.get(options1).getCitylist().get(options2).getQuybm();
                city = options1Items.get(options1).getCitylist().get(options2).getQulist().get(options3).getQuybm();
                quid = options1Items.get(options1).getCitylist().get(options2).getQulist().get(options3).getQuybm();

                pos[0] = options1;
                pos[1] = options2;
                pos[2] = options3;

                jieming = "";
                jieid = "";
                tvJiedaoxuanze.setText("");
                Log.e("我的区域编号",city+"");
            }
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器
        pvOptions.setSelectOptions(pos[0],pos[1],pos[2]);
        pvOptions.show();
    }
}
