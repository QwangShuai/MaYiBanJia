package com.mingmen.mayi.mayibanjia.ui.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.DianMingChaXunBean;
import com.mingmen.mayi.mayibanjia.bean.JsonBean;
import com.mingmen.mayi.mayibanjia.bean.ProvinceBean;
import com.mingmen.mayi.mayibanjia.bean.ShiChangBean;
import com.mingmen.mayi.mayibanjia.bean.ZhuCeChengGongBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.DianPuMingAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.PhotoDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.cityPicker.JsonFileReader;
import com.mingmen.mayi.mayibanjia.utils.cityPicker.ProvinceBean0;
import com.mingmen.mayi.mayibanjia.utils.photo.FileStorage;
import com.mingmen.mayi.mayibanjia.utils.photo.QiNiuPhoto;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.SinglePicker;


/**
 * Created by Administrator on 2018/7/10/010.
 */

public class GHDWanShanXinXiActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_dianpuming)
    EditText etDianpuming;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.iv_yingyezhizhao)
    ImageView ivYingyezhizhao;
    @BindView(R.id.iv_xukezheng)
    ImageView ivXukezheng;
    @BindView(R.id.et_fuzeren)
    EditText etFuzeren;
    @BindView(R.id.et_yaoqingma)
    EditText etYaoqingma;
    @BindView(R.id.bt_tijiao)
    Button btTijiao;
    @BindView(R.id.tv_xieyi)
    TextView tvXieyi;
    @BindView(R.id.tv_dianhua)
    TextView tvDianhua;
    @BindView(R.id.ll_xia)
    LinearLayout llXia;
    @BindView(R.id.ll_xukezheng)
    LinearLayout llXukezheng;
    @BindView(R.id.rv_dianpu)
    RecyclerView rvDianpu;
    @BindView(R.id.tv_quyuxuanze)
    TextView tvQuyuxuanze;

    private Context mContext;
    private Uri imageUri;//原图保存地址
    private Uri outputUri;//裁剪保存地址
    private String imagePath;
    private static final int REQUEST_PICK_IMAGE = 1; //相册选取
    private static final int REQUEST_CAPTURE = 2;  //拍照
    private static final int REQUEST_PICTURE_CUT = 3;  //剪裁图片
    private Bitmap bitmap = null;
    private PhotoDialog photoDialog;
    private boolean isClickCamera;
    private QiNiuPhoto qiNiuPhoto;
    private boolean isYingYe;
    private String yingyezhizhao="";
    private String xukezheng="";
    private String fuzeren;
    private String yaoqingma;
    private String dianpuming;
    private String dianpuid;
    private String phone;
    private String pass;
    private String yanzhengma;
    private String role = "1";
    private DianPuMingAdapter adapter;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();//省
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();//市
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();//区
    private int shengid;
    private int shiid;
    private int quid;
    private String type="1";
    int city=0;
    int[] pos= new int[3];

    @Override
    public int getLayoutId() {
        return R.layout.activity_wsxx_gonghuoduan;
    }

    @Override
    protected void initData() {
        mContext=GHDWanShanXinXiActivity.this;
        tvTitle.setText("完善信息");
        qiNiuPhoto=new QiNiuPhoto(this);
        photoDialog = new PhotoDialog(mContext,
                mContext.getResources().getIdentifier("BottomDialog", "style", mContext.getPackageName()));
        photoDialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.LEFT | Gravity.RIGHT);
        bundle=getIntent().getExtras();
        phone = bundle.getString("phone");
        pass = bundle.getString("pass1");
        yanzhengma = bundle.getString("yanzhengma");
        if(getIntent().getStringExtra("jueseid").equals("2")){
            type = "1";
        } else {
            type = "2";
        }
        etDianpuming.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                String chaxun = s.toString().trim();
                if (chaxun.length()>0){
                    if (chaxun.equals(dianpuming)){
                        rvDianpu.setVisibility(View.GONE);
                        llXia.setVisibility(View.VISIBLE);
                    }else{
                        getdianpuming(chaxun);
                    }
                }else{
                    llXukezheng.setVisibility(View.GONE);
                    llXia.setVisibility(View.VISIBLE);
                    rvDianpu.setVisibility(View.GONE);
                }
            }
        });
        etDianpuming.setEnabled(false);
        initJsonData();
    }

    private void qiniushangchuan() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .qiniushangchuan(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String list) {
                        Log.e("fenleifenlei",list+"---");
                        Log.e("imageUri",imageUri+"---");

//                        String qiniudata = getRealPathFromUri(GHDWanShanXinXiActivity.this, imageUri);
                        String qiniudata = getRealFilePath(GHDWanShanXinXiActivity.this, outputUri);
//
//                        File file=new File(imageUri.getPath());
                        String key = null;
                        String token =list ;
                        Log.e("qiniudata",qiniudata+"--");
                        MyApplication.uploadManager.put(qiniudata, key, token,
                                new UpCompletionHandler() {
                                    @Override
                                    public void complete(String key, ResponseInfo info, JSONObject res) {
                                        //res包含hash、key等信息，具体字段取决于上传策略的设置
                                        if(info.isOK()) {
//                                            getImageAbsolutePath(CTDWanShanXinXiActivity.this,outputUri)
                                            Log.e("qiniu", "Upload Success");

                                            try {
                                                if (isYingYe){
                                                    yingyezhizhao = res.getString("key");
                                                    Log.e("keykey", yingyezhizhao+"yingyezhizhao");
                                                }else{
                                                    xukezheng = res.getString("key");
                                                    Log.e("keykey", xukezheng+"xukezheng");
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        } else {
                                            Log.e("qiniu", "Upload Fail");
                                            //如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因
                                        }
                                        Log.e("qiniu", key + ",\r\n " + info + ",\r\n " + res);
                                    }
                                }, null);
                        if (isYingYe){
                            ivYingyezhizhao.setImageBitmap(bitmap);
                            Log.e("setImageBitmap", "ivYingyezhizhao");
                        }else{
                            ivXukezheng.setImageBitmap(bitmap);
                            Log.e("setImageBitmap", "ivXukezheng");
                        }

                       
                    }
                });
    }

    @OnClick({R.id.iv_back, R.id.iv_yingyezhizhao, R.id.iv_xukezheng, R.id.bt_tijiao,
            R.id.tv_xieyi, R.id.tv_dianhua,R.id.tv_quyuxuanze})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_yingyezhizhao:
                isYingYe=true;
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
            case R.id.iv_xukezheng:
                isYingYe=false;
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
//                        openCamera();
                        Matisse.from(GHDWanShanXinXiActivity.this)
                                .choose(MimeType.allOf()) // 选择 mime 的类型
                                .countable(true)
                                .capture(true)
                                .captureStrategy(new CaptureStrategy(true,"com.mingmen.mayi.mayibanjia.fileProvider"))
                                .maxSelectable(1) // 图片选择的最多数量
//                                .gridExpectedSize(getResources().getDimensionPixelSize(25))
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                                .thumbnailScale(0.85f) // 缩略图的比例
                                .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                                .forResult(REQUEST_CAPTURE); // 设置作为标记的请求码
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
            case R.id.bt_tijiao:
                fuzeren =etFuzeren.getText().toString().trim();
                yaoqingma =etYaoqingma.getText().toString().trim();
//                dianpuming=etDianpuming.getText().toString().trim();
                if(!StringUtil.isValid(etDianpuming.getText().toString().trim())){
                    ToastUtil.showToastLong("店铺名不可以为空");
                } else if(!StringUtil.isValid(fuzeren)){
                    ToastUtil.showToastLong("负责人不可以为空");
                } else if(!StringUtil.isValid(yaoqingma)){
                    ToastUtil.showToastLong("邀请码不可以为空");
                } else if(!StringUtil.isValid(yingyezhizhao)){
                    ToastUtil.showToastLong("营业执照不可以为空");
                } else{
                    if(StringUtil.isValid(dianpuid)&&etDianpuming.getText().toString().trim().equals(dianpuming)){
                        if(role.equals("2")){
                            if(StringUtil.isValid(xukezheng)){
                                zhuce();
                            } else {
                                ToastUtil.showToastLong("食品流通许可证不可以为空");
                            }
                        }else {
                            zhuce();
                        }
                    } else {
                        ToastUtil.showToastLong("匹配不到该商家，无法注册");
                    }
                }
                break;
            case R.id.tv_xieyi:

                break;
            case R.id.tv_dianhua:

                break;
            case R.id.tv_quyuxuanze:
                showCityPicker();
                break;
        }
    }

    private void zhuce() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .zhuce(fuzeren,phone,pass,yingyezhizhao,xukezheng,dianpuid, yaoqingma,yanzhengma,"1", StringUtil.getMyUUID(mContext)))
                .setDataListener(new HttpDataListener<ZhuCeChengGongBean>() {
                    @Override
                    public void onNext(ZhuCeChengGongBean list) {
                        Log.e("token",list.getToken()+"===");
                        PreferenceUtils.putString(MyApplication.mContext,"token",list.getToken());
                        PreferenceUtils.putString(MyApplication.mContext,"juese",list.getRole());
                        PreferenceUtils.putBoolean(MyApplication.mContext,"isLogin",true);
                        //注册成功后  跳转
//                        Intent intent = new Intent(mContext, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        Intent intent = new Intent(mContext, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                       
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
//                    xianshi();
                }
                break;
            case REQUEST_PICTURE_CUT://裁剪完成
//
                if (data!=null) {
                    Log.e("裁剪完成","裁剪完成");
                    try {
                        if (isClickCamera) {
                            Log.e("裁剪完成","裁剪完成111");
                            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(outputUri));
                        } else {
                            Log.e("裁剪完成","裁剪完成222");
                            bitmap = BitmapFactory.decodeFile(imagePath);
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
    /**
     * 从相册选择
     */
    public void selectFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, REQUEST_PICK_IMAGE);
    }

    /**
     * 根据Uri获取图片的绝对路径
     *
     * @param context 上下文对象
     * @param uri     图片的Uri
     * @return 如果Uri对应的图片存在, 那么返回该图片的绝对路径, 否则返回null
     */
    public static String getRealPathFromUri(Context context, Uri uri) {
        int sdkVersion = Build.VERSION.SDK_INT;
        if (sdkVersion >= 19) { // api >= 19

            return getRealPathFromUriAboveApi19(context, uri);
        } else { // api < 19
            return getRealPathFromUriBelowAPI19(context, uri);
        }
    }

    /**
     * 适配api19以下(不包括api19),根据uri获取图片的绝对路径
     *
     * @param context 上下文对象
     * @param uri     图片的Uri
     * @return 如果Uri对应的图片存在, 那么返回该图片的绝对路径, 否则返回null
     */
    private static String getRealPathFromUriBelowAPI19(Context context, Uri uri) {
        return getDataColumn(context, uri, null, null);
    }

    /**
     * 适配api19及以上,根据uri获取图片的绝对路径
     *
     * @param context 上下文对象
     * @param uri     图片的Uri
     * @return 如果Uri对应的图片存在, 那么返回该图片的绝对路径, 否则返回null
     */
    @SuppressLint("NewApi")
    private static String getRealPathFromUriAboveApi19(Context context, Uri uri) {
        String filePath = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // 如果是document类型的 uri, 则通过document id来进行处理
            String documentId = DocumentsContract.getDocumentId(uri);
            if (isMediaDocument(uri)) { // MediaProvider
                // 使用':'分割
                String id = documentId.split(":")[1];

                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = {id};
                filePath = getDataColumn(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection, selectionArgs);
            } else if (isDownloadsDocument(uri)) { // DownloadsProvider
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
                filePath = getDataColumn(context, contentUri, null, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())){
            // 如果是 content 类型的 Uri
            filePath = getDataColumn(context, uri, null, null);
        } else if ("file".equals(uri.getScheme())) {
            // 如果是 file 类型的 Uri,直接获取图片对应的路径
            filePath = uri.getPath();
        }
        Log.e("filePath",filePath+"-");
        return filePath;
    }

    /**
     * 获取数据库表中的 _data 列，即返回Uri对应的文件路径
     * @return
     */
    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        String path = null;

        String[] projection = new String[]{MediaStore.Images.Media.DATA};
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(projection[0]);
                path = cursor.getString(columnIndex);
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
        }
        return path;
    }

    /**
     * @param uri the Uri to check
     * @return Whether the Uri authority is MediaProvider
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri the Uri to check
     * @return Whether the Uri authority is DownloadsProvider
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
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
        Log.e("我的图片地址",outputUri+"");
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

    private void xianshi(){
        bitmap = null;
        try {
            if (isClickCamera) {
                bitmap = BitmapFactory.decodeFile(imagePath);
                Log.e("裁剪完成","裁剪完成111");
            } else {
                Log.e("裁剪完成","裁剪完成222");
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
            }
            qiniushangchuan();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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
//        xianshi();
        cropPhoto();
        return imagePath;
    }

    public String handleImageBeforeKitKat(Intent intent) {
        imageUri = intent.getData();
        imagePath = qiNiuPhoto.getImagePath(imageUri, null);
        cropPhoto();
//        xianshi();
        return imagePath;
    }



    //https://blog.csdn.net/qq_34476727/article/details/55506508 Android 读取拍照或相册uri 转换成图片的绝对路径

    /**
     * Try to return the absolute file path from the given Uri
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePath( final Context context, final Uri uri ) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if ( scheme == null )
            data = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
    private void getdianpuming(final String chaxun) {
        Log.e("chaxun",chaxun);
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .dianpuchaxun(PreferenceUtils.getString(MyApplication.mContext, "token", ""),shengid+"",shiid+"",quid+"","2",type,chaxun))
                .setDataListener(new HttpDataListener<List<DianMingChaXunBean>>() {
                    @Override
                    public void onNext(final List<DianMingChaXunBean> data) {
                        if (chaxun.equals(dianpuming)){
                            rvDianpu.setVisibility(View.GONE);
                            llXia.setVisibility(View.VISIBLE);
                        }else{
                            rvDianpu.setVisibility(View.VISIBLE);
                            llXia.setVisibility(View.GONE);
                            dianpuming="";
                            dianpuid="";
                        }
                        adapter = new DianPuMingAdapter(mContext, data,chaxun);
                        rvDianpu.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                        rvDianpu.setAdapter(adapter);
                        adapter.setOnItemClickListener(new DianPuMingAdapter.OnItemClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                role = data.get(position).getRole()+"";
                                if(role.equals("1")){
                                    llXukezheng.setVisibility(View.GONE);
                                } else {
                                    llXukezheng.setVisibility(View.VISIBLE);
                                }
                                etDianpuming.setText(data.get(position).getCompany_name());
                                dianpuming=data.get(position).getCompany_name();
                                dianpuid=data.get(position).getCompany_id();
                                rvDianpu.setVisibility(View.GONE);
                                llXia.setVisibility(View.VISIBLE);
                            }
                        });


                    }
                });

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

                etDianpuming.setEnabled(true);

                pos[0] = options1;
                pos[1] = options2;
                pos[2] = options3;

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
