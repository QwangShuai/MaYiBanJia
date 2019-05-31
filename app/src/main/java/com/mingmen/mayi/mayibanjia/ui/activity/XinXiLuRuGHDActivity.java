package com.mingmen.mayi.mayibanjia.ui.activity;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.FCGName;
import com.mingmen.mayi.mayibanjia.bean.JsonBean;
import com.mingmen.mayi.mayibanjia.bean.ProvinceBean;
import com.mingmen.mayi.mayibanjia.bean.QiYeLeiBieBean;
import com.mingmen.mayi.mayibanjia.bean.QiYeLieBiaoBean;
import com.mingmen.mayi.mayibanjia.bean.ShiChangBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.FenLeiMohuAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.GuigeDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.PhotoDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ShichangRightDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.GlideUtils;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.custom.CustomDialog;
import com.mingmen.mayi.mayibanjia.utils.photo.FileStorage;
import com.mingmen.mayi.mayibanjia.utils.photo.QiNiuPhoto;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.SinglePicker;

public class XinXiLuRuGHDActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.et_qiyemingcheng)
    EditText etQiyemingcheng;
    @BindView(R.id.tv_xuanzeshichang)
    TextView tvXuanzeshichang;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.et_tanweihao)
    EditText etTanweihao;
    @BindView(R.id.iv_tu)
    ImageView ivTu;
    @BindView(R.id.tv_qiehuan)
    TextView tvQiehuan;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_quyuxuanze)
    TextView tvQuyuxuanze;
    @BindView(R.id.et_xiangxidizhi)
    EditText etXiangxidizhi;
    @BindView(R.id.sl)
    ScrollView sl;
    @BindView(R.id.bt_queding)
    Button btQueding;
    @BindView(R.id.tv_fenleixuanze)
    TextView tvFenleixuanze;

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
    private String shengming;
    private String shiming;
    private int shiid;
    private String quming;
    private int quid;
    private String leibiename;
    private String leibieid;
    private SinglePicker<QiYeLeiBieBean> leibiepicker;
    private int shengid;

    private String yewuyuanweizhi;
    private String qiyemingcheng;
    private String xiangxidizhi;
    private String rukou;
    private QiYeLieBiaoBean qiyexinxi;
    private String qiyeid;

    private ArrayList<JsonBean> options1Items = new ArrayList<>();//省
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();//市
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();//区
    private int city = 0;
    private int[] pos = new int[3];
    private String random_id = "1";
    private String shichang_id;
    private String shichangming;
    private String tanweihao;
    private String phone;

    private PopupWindow mPopWindow;
    private RecyclerView rv_mohu;
    private FenLeiMohuAdapter mohuAdapter;
    private ArrayList<FCGName> datas = new ArrayList<>();
    private String oneId = "";


    @Override
    public int getLayoutId() {
        return R.layout.activity_xin_xi_lu_ru_ghd;
    }

    @Override
    protected void initData() {
        mContext = XinXiLuRuGHDActivity.this;
        qiNiuPhoto = new QiNiuPhoto(XinXiLuRuGHDActivity.this);
        bundle = getIntent().getExtras();
        rukou = bundle.getString("rukou");
        EventBus.getDefault().register(this);
        StringUtil.setInputNoEmoj(etQiyemingcheng,30);
        StringUtil.setInputNoEmoj(etTanweihao,50);
        StringUtil.setInputNoEmoj(etXiangxidizhi,50);
        if ("add".equals(rukou)) {
//            tvQiehuan.setText("否");
            random_id = getIntent().getStringExtra("random_id");
        } else {
            String xinxi = bundle.getString("xinxi");
            Log.e("xinxi", xinxi);
            qiyexinxi = gson.fromJson(xinxi, QiYeLieBiaoBean.class);
            qiyeid = qiyexinxi.getCompany_id();
            etQiyemingcheng.setText(qiyexinxi.getCompany_name());
            leibieid = qiyexinxi.getLeiBieId();
            leibiename = qiyexinxi.getLeiBieName();
            shengid = Integer.parseInt(qiyexinxi.getProvince());
            shiid = Integer.parseInt(qiyexinxi.getCity());
            quid = Integer.parseInt(qiyexinxi.getRegion());
            city = Integer.parseInt(qiyexinxi.getRegion());
            shengming = qiyexinxi.getQuYMC();
            shiming = qiyexinxi.getQuYMCa();
            quming = qiyexinxi.getQuYMCb();
            tanweihao = qiyexinxi.getBooth_number();
            phone = qiyexinxi.getTelephone();
            shichangming = qiyexinxi.getSon_number();
            shidizhaopian = qiyexinxi.getPhoto();
            xiangxidizhi = qiyexinxi.getSpecific_address();
            shichang_id = qiyexinxi.getMarket_id();
            Log.e("shichang_id--", qiyexinxi.getMarket_id());
            tvQuyuxuanze.setText(qiyexinxi.getQuYMC() + "-" + qiyexinxi.getQuYMCa() + "-" + qiyexinxi.getQuYMCb());
            etXiangxidizhi.setText(qiyexinxi.getSpecific_address());
            GlideUtils.cachePhoto(mContext,ivTu,shidizhaopian);
            etPhone.setText(phone);
            tvXuanzeshichang.setText(shichangming);
            etTanweihao.setText(tanweihao);
            oneId = qiyexinxi.getOne_classify_id();
            tvFenleixuanze.setText(qiyexinxi.getOne_classify_name());
        }
        photoDialog = new PhotoDialog(mContext,
                mContext.getResources().getIdentifier("BottomDialog", "style", mContext.getPackageName()));
        photoDialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.LEFT | Gravity.RIGHT);
        showPopupWindow();
        initJsonData();
        getMorenFenLei();
        getShouyeFenLei("-1", "1");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_right, R.id.tv_xuanzeshichang, R.id.iv_tu, R.id.tv_qiehuan,
            R.id.tv_quyuxuanze, R.id.bt_queding, R.id.tv_fenleixuanze})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_right:
                finish();
                break;
            case R.id.tv_xuanzeshichang:

                ShichangRightDialog dialog = new ShichangRightDialog().setData(mContext).show(getSupportFragmentManager());
                dialog.setQuid(quid+"");
                dialog.setDqId(shichang_id);
//                if(quid!=0){
//                getshichang();
//                } else {
//                    ToastUtil.showToastLong("请先选择区域");
//                }

                break;
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
//            case R.id.tv_qiehuan:
//                showQiehuan();
//                break;
            case R.id.tv_quyuxuanze:
                showCityPicker();
                break;
            case R.id.bt_queding:
                qiyemingcheng = etQiyemingcheng.getText().toString().trim();
                tanweihao = etTanweihao.getText().toString().trim();
                xiangxidizhi = etXiangxidizhi.getText().toString().trim();
                if (TextUtils.isEmpty(qiyemingcheng)) {
                    ToastUtil.showToast("企业名称不可以为空");
                } else if (TextUtils.isEmpty(tanweihao)) {
                    ToastUtil.showToast("摊位号不可以为空");
                }
//                else if(TextUtils.isEmpty(xiangxidizhi)){
//                    ToastUtil.showToast("详细地址不可以为空");
//                }
                else if (TextUtils.isEmpty(shichang_id)) {
                    ToastUtil.showToast("市场不可以为空");
                } else if (!AppUtil.isMobile(etPhone.getText().toString().trim()) && !AppUtil.isPhone(etPhone.getText().toString().trim())) {
                    ToastUtil.showToast("请输入正确的联系方式");
                } else {
                    if ("add".equals(rukou)) {
                        qiyeluru();
                    } else {
                        xiugai();
                    }
                }
                break;
            case R.id.tv_fenleixuanze:
                if (mPopWindow != null) {
                    mPopWindow.showAsDropDown(tvFenleixuanze);
                }
                break;
        }
    }

    //修改
    private void xiugai() {
        Log.e("xiugai: ", shidizhaopian + "---");
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
//                                .qiyexiugai(PreferenceUtils.getString(MyApplication.mContext, "token",""),qiyemingcheng,shengid+"",shiid+"",quid+"","", xiangxidizhi,yewuyuanweizhi,shidizhaopian,leibieid,"",random_id,"2",tanweihao,shichang_id,etPhone.getText().toString().trim(),qiyeid))
                                .qiyexiugai(PreferenceUtils.getString(MyApplication.mContext, "token", ""), qiyemingcheng, "", "", "", "", "", yewuyuanweizhi, shidizhaopian, leibieid, "", random_id, "2", tanweihao, shichang_id, etPhone.getText().toString().trim(), qiyeid, oneId))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        Log.e("data", data + "---");
                        ToastUtil.showToast("修改成功");
                        finish();
                    }
                });
    }

    //企业录入
    private void qiyeluru() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .qiyeluru(PreferenceUtils.getString(MyApplication.mContext, "token", ""), qiyemingcheng, shengid + "", shiid + "", quid + "", "", xiangxidizhi, yewuyuanweizhi, shidizhaopian, leibieid, "", random_id, "2", tanweihao, shichang_id, etPhone.getText().toString().trim(), oneId))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        Log.e("data", data + "---");
                        ToastUtil.showToast("添加成功");
                        finish();

                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_PICK_IMAGE://从相册选择
                Log.e("xiangce", "xiangce");
                if (data != null) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        imagePath = handleImageOnKitKat(data);
                    } else {
                        imagePath = handleImageBeforeKitKat(data);
                    }
                }
                break;
            case REQUEST_CAPTURE://拍照
                Log.e("拍照", "拍照");
                if (resultCode == RESULT_OK) {
                    cropPhoto();
                }
                break;
            case REQUEST_PICTURE_CUT://裁剪完成

                if (data != null) {
                    Log.e("裁剪完成", "裁剪完成");
                    try {
                        if (isClickCamera) {
                            Log.e("裁剪完成", "裁剪完成111");
                            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(outputUri));
                        } else {
                            Log.e("裁剪完成", "裁剪完成222");
//                            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(outputUri));
                            bitmap = BitmapFactory.decodeFile(imagePath);
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
        final CustomDialog customDialog = new CustomDialog(this, "正在加载...");
        customDialog.show();//显示,显示时页面不可点击,只能点击返回
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .qiniushangchuan(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String list) {
                        Log.e("fenleifenlei", list + "---");
                        String qiniudata = qiNiuPhoto.getImageAbsolutePath(XinXiLuRuGHDActivity.this, outputUri);
                        String key = null;
                        String token = list;
                        File file = StringUtil.luban(mContext, qiniudata);
                        if (StringUtil.isValid(file.getPath())) {
                            bitmap = BitmapFactory.decodeFile(file.getPath());
                            MyApplication.uploadManager.put(qiniudata, key, token,
                                    new UpCompletionHandler() {
                                        @Override
                                        public void complete(String key, ResponseInfo info, JSONObject res) {
                                            //res包含hash、key等信息，具体字段取决于上传策略的设置
                                            if (info.isOK()) {
//                                            getImageAbsolutePath(CTDWanShanXinXiActivity.this,outputUri)
                                                Log.e("qiniu", "Upload Success");
                                                try {
                                                    customDialog.dismiss();
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
                            GlideUtils.cachePhoto(mContext,ivTu,file.getPath());
//                            ivTu.setImageBitmap(bitmap);
                        }
//                        else {
//                            ToastUtil.showToastLong("您选择的图片低于50像素，不够清晰");
//                        }
                    }
                },true);
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
            imageUri = FileProvider.getUriForFile(this, mContext.getApplicationContext().getPackageName() + ".fileProvider", file);//通过FileProvider创建一个content类型的Uri
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
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
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
                tvQuyuxuanze.setText(tx);
                shengid = options1Items.get(options1).getQuybm();
                shiid = options1Items.get(options1).getCitylist().get(options2).getQuybm();
                city = options1Items.get(options1).getCitylist().get(options2).getQulist().get(options3).getQuybm();
                quid = options1Items.get(options1).getCitylist().get(options2).getQulist().get(options3).getQuybm();

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

    private void getshichang() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getshichang(PreferenceUtils.getString(MyApplication.mContext, "token", ""), quid + ""))
                .setDataListener(new HttpDataListener<List<ShiChangBean>>() {
                    @Override
                    public void onNext(List<ShiChangBean> list) {
                        final SinglePicker<ShiChangBean> picker = new SinglePicker<ShiChangBean>(XinXiLuRuGHDActivity.this, list);
                        picker.setCanceledOnTouchOutside(false);
                        picker.setSelectedIndex(1);
                        picker.setCycleDisable(false);
                        picker.setOnItemPickListener(new SinglePicker.OnItemPickListener<ShiChangBean>() {
                            @Override
                            public void onItemPicked(int index, ShiChangBean item) {
                                tvXuanzeshichang.setText(item.getMarket_name());
                                shichang_id = item.getMark_id();
                                shichangming = item.getMarket_name();
                                picker.dismiss();
                            }
                        });

                        picker.show();

                    }
                });
    }

    //    private void showQiehuan(){
//        final String[] items = {"是", "否"};
//
//        final SinglePicker<String> picker =new SinglePicker<String>(XinXiLuRuGHDActivity.this,items);
//        picker.setCanceledOnTouchOutside(false);
//        picker.setSelectedIndex(1);
//        picker.setCycleDisable(false);
//        picker.setOnItemPickListener(new SinglePicker.OnItemPickListener<String>() {
//            @Override
//            public void onItemPicked(int index, String item) {
//                tvQiehuan.setText(item);
//                random_id = item.equals("是")?"1":"2";
//                picker.dismiss();
//            }
//        });
//
//        picker.show();
//    }
    private void showPopupWindow() {
        View view = View.inflate(mContext, R.layout.pp_textview_recycleview, null);
        mPopWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int width = getWindowManager().getDefaultDisplay().getWidth();
        int height = getWindowManager().getDefaultDisplay().getHeight();
        mPopWindow.setWidth(width * 2 / 6);
        mPopWindow.setHeight(height * 2 / 9);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
//    mPopWindow.showAsDropDown(tvFenleixuanze);
        rv_mohu = (RecyclerView) view.findViewById(R.id.rv_list);
        mohuAdapter = new FenLeiMohuAdapter(mContext, datas);
        rv_mohu.setAdapter(mohuAdapter);
        rv_mohu.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mohuAdapter.setOnItemClickListener(new FenLeiMohuAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                tvFenleixuanze.setText(datas.get(position).getClassify_name());
                oneId = datas.get(position).getClassify_id();
                mPopWindow.dismiss();
            }
        });
    }

    private void getShouyeFenLei(String id, final String type) {
        datas.clear();
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getFeiLei(PreferenceUtils.getString(MyApplication.mContext, "token", ""), id, type))
                .setDataListener(new HttpDataListener<List<FCGName>>() {

                    @Override
                    public void onNext(List<FCGName> list) {
                        int mysize = list == null ? 0 : list.size();
                        if (mysize != 0) {
                            datas.addAll(list);
                        } else {
                            ToastUtil.showToastLong("当前类别暂无品类");
                        }
                    }
                }, false);
    }

    private void getMorenFenLei() {
        datas.clear();
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getMorenFenlei(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<FCGName>() {

                    @Override
                    public void onNext(FCGName bean) {
                        tvFenleixuanze.setText(bean.getOne_classify_name());
                        oneId = bean.getOne_classify_id();
                    }
                }, false);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMarket(ShiChangBean item) {
        shichang_id =item.getMark_id();
        shichangming = item.getMarket_name();
        tvXuanzeshichang.setText(item.getMarket_name());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
