package com.mingmen.mayi.mayibanjia.ui.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.GetZiZhiBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.PhotoDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.GlideUtils;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.custom.CustomDialog;
import com.mingmen.mayi.mayibanjia.utils.photo.FileStorage;
import com.mingmen.mayi.mayibanjia.utils.photo.QiNiuPhoto;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZzrzGydActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_shenhe_state)
    TextView tvShenheState;
    @BindView(R.id.tv_qymc)
    TextView tvQymc;
    @BindView(R.id.et_lxdh)
    EditText etLxdh;
    @BindView(R.id.et_lxr)
    EditText etLxr;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_shenfenzheng)
    EditText etShenfenzheng;
    @BindView(R.id.et_xinyongma)
    EditText etXinyongma;
    @BindView(R.id.tv_yyzz)
    TextView tvYyzz;
    @BindView(R.id.iv_yingyezhizhao)
    ImageView ivYingyezhizhao;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.iv_xukezheng)
    ImageView ivXukezheng;
    @BindView(R.id.tv_sdzp)
    TextView tvSdzp;
    @BindView(R.id.iv_shidizhaopian)
    ImageView ivShidizhaopian;
    @BindView(R.id.tv_sysc)
    TextView tvSysc;
    @BindView(R.id.tv_tvh)
    TextView tvTvh;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.tv_yingyezhizhao)
    TextView tvYingyezhizhao;
    @BindView(R.id.tv_xukezheng)
    TextView tvXukezheng;
    @BindView(R.id.tv_shidizhaopian)
    TextView tvShidizhaopian;

    private Context mContext;
    private String state = "";
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
    private int isXuanzhong = 0;
    private String yingyezhizhao = "", xukezheng = "", shidizhaopian = "";
    private String xinyongma = "";
    private String shenfenzheng = "";
    private String name = "";
    private String fuzeren = "";
    private String phone = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_zzrz_gyd;
    }

    @Override
    protected void initData() {
        mContext = ZzrzGydActivity.this;
        tvTitle.setText("完善信息");
        tvRight.setText("帮助");

        photoDialog = new PhotoDialog(mContext,
                mContext.getResources().getIdentifier("BottomDialog", "style", mContext.getPackageName()));
        photoDialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.LEFT | Gravity.RIGHT);
        qiNiuPhoto = new QiNiuPhoto(ZzrzGydActivity.this);

        StringUtil.setInputNoEmoj(etLxr, 6);
        StringUtil.setInputNoEmoj(etName, 6);
        state = getIntent().getStringExtra("state");
        Log.e("state111", state);
        tvShenheState.setText("审核状态:" + state);
        if (state.equals("审核中")) {
            tvShenheState.setTextColor(mContext.getResources().getColor(R.color.zicolor));
            etName.setEnabled(false);
            etXinyongma.setEnabled(false);
            etShenfenzheng.setEnabled(false);
            ivShidizhaopian.setEnabled(false);
            ivXukezheng.setEnabled(false);
            ivYingyezhizhao.setEnabled(false);
            etLxr.setEnabled(false);
            etLxdh.setEnabled(false);
            btnSubmit.setVisibility(View.GONE);
        } else if (state.equals("审核通过")) {
            tvShenheState.setTextColor(mContext.getResources().getColor(R.color.green_6dd46d));
        } else {
            tvShenheState.setTextColor(mContext.getResources().getColor(R.color.zangqing));
        }

        getZizhiShow();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.iv_yingyezhizhao, R.id.iv_xukezheng,
            R.id.iv_shidizhaopian, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                ToastUtil.showToastLong("请关注稍后更新");
                break;
            case R.id.iv_yingyezhizhao:
                isXuanzhong = 0;
                showDialog();
                break;
            case R.id.iv_xukezheng:
                isXuanzhong = 1;
                showDialog();
                break;
            case R.id.iv_shidizhaopian:
                isXuanzhong = 2;
                showDialog();
                break;
            case R.id.btn_submit:
                if (isClick()) {
                    saveZizhi();
                }
                break;
        }
    }

    private void showDialog(){
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
    }

    private boolean isClick() {
        xinyongma = etXinyongma.getText().toString().trim();
        shenfenzheng = etShenfenzheng.getText().toString().trim();
        name = etName.getText().toString().trim();
        fuzeren = etLxr.getText().toString().trim();
        phone = etLxdh.getText().toString().trim();
        if (TextUtils.isEmpty(xinyongma)) {
            ToastUtil.showToast("社会信用码不能为空");
            return false;
        } else if (!StringUtil.isLegalId(shenfenzheng)) {
            ToastUtil.showToast("身份证号格式不正确");
            return false;
        } else if (TextUtils.isEmpty(name)) {
            ToastUtil.showToast("法人姓名不能为空");
            return false;
        } else if (TextUtils.isEmpty(fuzeren)) {
            ToastUtil.showToast("负责人姓名不能为空");
            return false;
        } else if (TextUtils.isEmpty(phone)) {
            ToastUtil.showToast("电话号不能为空");
            return false;
        } else {
            return true;
        }
    }

    private void saveZizhi() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .saveZizhi(PreferenceUtils.getString(MyApplication.mContext, "token", ""),
                                shenfenzheng, name, xinyongma, fuzeren, yingyezhizhao, xukezheng,
                                shidizhaopian, "", phone))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String bean) {
                        ToastUtil.showToast("保存成功");
                        finish();
                    }
                });
    }

    private void getZizhiShow() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getZizhiShow(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<GetZiZhiBean>() {
                    @Override
                    public void onNext(GetZiZhiBean bean) {
                        if (StringUtil.isEmpty(bean.getCirculation_permit())) {
                            tvXukezheng.setText("未上传");
                            tvXukezheng.setTextColor(mContext.getResources().getColor(R.color.zicolor));
                        }
                        if (StringUtil.isEmpty(bean.getBusiness_license())) {
                            tvYingyezhizhao.setText("未上传");
                            tvYingyezhizhao.setTextColor(mContext.getResources().getColor(R.color.zicolor));
                        }
                        if (StringUtil.isEmpty(bean.getPhoto())) {
                            tvShidizhaopian.setText("未上传");
                            tvShidizhaopian.setTextColor(mContext.getResources().getColor(R.color.zicolor));
                        }
                        GlideUtils.cachePhoto(mContext, ivXukezheng, bean.getCirculation_permit());
                        GlideUtils.cachePhoto(mContext, ivYingyezhizhao, bean.getBusiness_license());
                        GlideUtils.cachePhoto(mContext, ivShidizhaopian, bean.getPhoto());
                        etLxr.setText(bean.getPrincipal());
                        etName.setText(bean.getLegal_person());
                        Log.e("审核", state);
                        etXinyongma.setText(bean.getDuty_paragraph());
                        etShenfenzheng.setText(bean.getId_number());
                        etLxdh.setText(bean.getTelephone());
                        tvQymc.setText(bean.getCompany_name());
                        tvSysc.setText(bean.getMarket_name());
                        tvTvh.setText(bean.getBooth_number());
                    }
                });
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
                        Log.e("imageUri", imageUri + "---");

//                        String qiniudata = getRealPathFromUri(GHDWanShanXinXiActivity.this, imageUri);
                        String qiniudata = getRealFilePath(ZzrzGydActivity.this, outputUri);
//
//                        File file=new File(imageUri.getPath());
                        String key = null;
                        String token = list;
                        File file = StringUtil.luban(mContext, qiniudata);
                        if (StringUtil.isValid(file.getPath())) {
                            bitmap = BitmapFactory.decodeFile(file.getPath());
                            Log.e("qiniudata", qiniudata + "--");
                            MyApplication.uploadManager.put(qiniudata, key, token,
                                    new UpCompletionHandler() {
                                        @Override
                                        public void complete(String key, ResponseInfo info, JSONObject res) {
                                            //res包含hash、key等信息，具体字段取决于上传策略的设置
                                            if (info.isOK()) {
//                                            getImageAbsolutePath(CTDWanShanXinXiActivity.this,outputUri)
                                                Log.e("qiniu", "Upload Success");

                                                try {
                                                    if (isXuanzhong == 0) {
                                                        yingyezhizhao = res.getString("key");
                                                        customDialog.dismiss();
                                                    } else if (isXuanzhong == 1) {
                                                        xukezheng = res.getString("key");
                                                        customDialog.dismiss();
                                                    } else {
                                                        shidizhaopian = res.getString("key");
                                                        customDialog.dismiss();
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
                            if (isXuanzhong == 0) {
                                GlideUtils.cachePhoto(mContext, ivYingyezhizhao, file.getPath());
                                tvYingyezhizhao.setText("已上传");
                                tvYingyezhizhao.setTextColor(mContext.getResources().getColor(R.color.green_6dd46d));
                            } else if (isXuanzhong == 1) {
                                GlideUtils.cachePhoto(mContext, ivXukezheng, file.getPath());
                                tvXukezheng.setText("已上传");
                                tvXukezheng.setTextColor(mContext.getResources().getColor(R.color.green_6dd46d));
                            } else {
                                GlideUtils.cachePhoto(mContext, ivShidizhaopian, file.getPath());
                                tvShidizhaopian.setText("已上传");
                                tvShidizhaopian.setTextColor(mContext.getResources().getColor(R.color.green_6dd46d));
                            }

                        }
                    }
                }, true);

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
//                    xianshi();
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
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是 content 类型的 Uri
            filePath = getDataColumn(context, uri, null, null);
        } else if ("file".equals(uri.getScheme())) {
            // 如果是 file 类型的 Uri,直接获取图片对应的路径
            filePath = uri.getPath();
        }
        Log.e("filePath", filePath + "-");
        return filePath;
    }

    /**
     * 获取数据库表中的 _data 列，即返回Uri对应的文件路径
     *
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
            imageUri = FileProvider.getUriForFile(this, mContext.getApplicationContext().getPackageName() + ".fileProvider", file);//通过FileProvider创建一个content类型的Uri
        } else {
            imageUri = Uri.fromFile(file);
        }
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
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
        Log.e("我的图片地址", outputUri + "");
        Intent intent = new Intent("com.android.camera.action.CROP");
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//        }
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
    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}
