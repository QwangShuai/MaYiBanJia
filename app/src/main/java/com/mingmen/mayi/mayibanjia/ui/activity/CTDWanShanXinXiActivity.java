package com.mingmen.mayi.mayibanjia.ui.activity;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.DianMingChaXunBean;
import com.mingmen.mayi.mayibanjia.bean.ZhuCeChengGongBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.DianPuMingAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.PhotoDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.photo.FileStorage;
import com.mingmen.mayi.mayibanjia.utils.photo.QiNiuPhoto;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/10/010.
 */

public class CTDWanShanXinXiActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_dianpuming)
    EditText etDianpuming;
    @BindView(R.id.rv_dianpu)
    RecyclerView rvDianpu;
    @BindView(R.id.iv_yingyezhizhao)
    ImageView ivYingyezhizhao;
    @BindView(R.id.et_fuzeren)
    EditText etFuzeren;
    @BindView(R.id.et_yaoqingma)
    EditText etYaoqingma;
    @BindView(R.id.bt_submit)
    Button btSubmit;
    @BindView(R.id.tv_xieyi)
    TextView tvXieyi;
    @BindView(R.id.tv_dianhua)
    TextView tvDianhua;
    @BindView(R.id.ll_xia)
    LinearLayout llXia;
    private Context mContext;
    private Uri imageUri;//原图保存地址
    private Uri outputUri;
    private String imagePath;
    private static final int REQUEST_PICK_IMAGE = 1; //相册选取
    private static final int REQUEST_CAPTURE = 2;  //拍照
    private static final int REQUEST_PICTURE_CUT = 3;  //剪裁图片
    private Bitmap bitmap = null;
    private PhotoDialog photoDialog;
    private boolean isClickCamera;
    private String dianpuming;
    private String yingyezhizhao;
    private String fuzeren;
    private String yaoqingma;
    private String phone;
    private String pass;
    private DianPuMingAdapter adapter;
    private QiNiuPhoto qiNiuPhoto;
    private String dianpuid;
    private String yanzhengma;


    @Override
    public int getLayoutId() {
        return R.layout.activity_wsxx_cantingduan;
    }

    @Override
    protected void initData() {
        mContext=CTDWanShanXinXiActivity.this;
        tvTitle.setText("完善信息");
        tvRight.setText("帮助");
           photoDialog = new PhotoDialog(mContext,
                mContext.getResources().getIdentifier("BottomDialog", "style", mContext.getPackageName()));
        photoDialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.LEFT | Gravity.RIGHT);
        bundle=getIntent().getExtras();
        phone = bundle.getString("phone");
        pass = bundle.getString("pass1");
        yanzhengma = bundle.getString("yanzhengma");
        qiNiuPhoto=new QiNiuPhoto(CTDWanShanXinXiActivity.this);
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
                    llXia.setVisibility(View.VISIBLE);
                    rvDianpu.setVisibility(View.GONE);
                }
            }
        });
    }

    private void getdianpuming(final String chaxun) {
        Log.e("chaxun",chaxun);
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .dianpuchaxun(chaxun))
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

    private void zhuce() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .zhuce(fuzeren,phone,pass,yingyezhizhao,"",dianpuid,yaoqingma,yanzhengma))
                .setDataListener(new HttpDataListener<ZhuCeChengGongBean>() {
                    @Override
                    public void onNext(ZhuCeChengGongBean list) {
                        ToastUtil.showToastLong("注册成功");
                        PreferenceUtils.putString(MyApplication.mContext,"token",list.getToken());
                        PreferenceUtils.putString(MyApplication.mContext,"juese",list.getRole());
                        PreferenceUtils.putBoolean(MyApplication.mContext,"isLogin",true);
                        //注册成功后  跳转
                        Intent intent = new Intent(mContext, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  ;
                        startActivity(intent);

                    }
                });
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
                        String qiniudata = qiNiuPhoto.getImageAbsolutePath(CTDWanShanXinXiActivity.this, outputUri);
                        String key = null;
                        String token =list ;
                        MyApplication.uploadManager.put(qiniudata, key, token,
                                new UpCompletionHandler() {
                                    @Override
                                    public void complete(String key, ResponseInfo info, JSONObject res) {
                                        //res包含hash、key等信息，具体字段取决于上传策略的设置
                                        if(info.isOK()) {
//                                            getImageAbsolutePath(CTDWanShanXinXiActivity.this,outputUri)
                                            try {
                                                yingyezhizhao = res.getString("key");
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        } else {
                                            //如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因
                                        }
                                    }
                                }, null);
                        ivYingyezhizhao.setImageBitmap(bitmap);

                    }
                });
    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.iv_yingyezhizhao, R.id.bt_submit, R.id.tv_xieyi, R.id.tv_dianhua})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                //帮助

                break;
            case R.id.iv_yingyezhizhao:
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
            case R.id.bt_submit:
                //提交
                fuzeren =etFuzeren.getText().toString().trim();
                yaoqingma =etYaoqingma.getText().toString().trim();
                if (dianpuming!=null&&yingyezhizhao!=null&& fuzeren !=null&& yaoqingma !=null){
                    zhuce();
                }else{
                    ToastUtil.showToast("请确认信息填写无误后，再提交");
                }

                break;
            case R.id.tv_xieyi:
                //协议

                break;
            case R.id.tv_dianhua:
                //点击拨打电话

                break;
        }
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
//                    xianshi();
                    cropPhoto();
                }
                break;
            case REQUEST_PICTURE_CUT://裁剪完成
                if (data!=null) {
                    try {
                        if (isClickCamera) {
                            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(outputUri));
                        } else {
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


    private void xianshi(){
        try {
        if (isClickCamera) {
            Log.e("裁剪完成","裁剪完成111");
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
        } else {
            Log.e("裁剪完成","裁剪完成222");

            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
            Log.e("outputUri", String.valueOf(imageUri));
        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        qiniushangchuan();
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
//        xianshi();
        return imagePath;
    }

    public String handleImageBeforeKitKat(Intent intent) {
        imageUri = intent.getData();
        imagePath = qiNiuPhoto.getImagePath(imageUri, null);
//        cropPhoto();
        xianshi();
        return imagePath;
    }
}
