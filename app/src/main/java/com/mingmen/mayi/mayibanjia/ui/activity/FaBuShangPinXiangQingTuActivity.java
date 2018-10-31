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
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.EditorShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.FbspCanShuBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.PhotoDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppManager;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.photo.FileStorage;
import com.mingmen.mayi.mayibanjia.utils.photo.QiNiuPhoto;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/7/007.
 */

public class FaBuShangPinXiangQingTuActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_xq1)
    ImageView ivXq1;
    @BindView(R.id.iv_xq2)
    ImageView ivXq2;
    @BindView(R.id.iv_xq3)
    ImageView ivXq3;
    @BindView(R.id.iv_xq4)
    ImageView ivXq4;
    @BindView(R.id.et_chandi)
    EditText etChandi;
    @BindView(R.id.et_dengji)
    EditText etDengji;
    @BindView(R.id.et_shiyong)
    EditText etShiyong;
    @BindView(R.id.et_bili)
    EditText etBili;
    @BindView(R.id.bt_baocun)
    Button btBaocun;
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
    private int tuji;
    private String tu1="",tu2="",tu3="",tu4="";
    private FbspCanShuBean canshu;
    private String futu = "";
    private String yemian = "0";
    private String pipei = "0";
    private String spID;
    private List<String> picList = Arrays.asList("","","","");
    @Override
    public int getLayoutId() {
        return R.layout.activity_fabushangpin_xiangqingtu;
    }

    @Override
    protected void initData() {
        mContext=FaBuShangPinXiangQingTuActivity.this;
        yemian = getIntent().getStringExtra("yemian");
        if(yemian.equals("0")){
            tvTitle.setText("新建商品");
            pipei = getIntent().getStringExtra("pipei");
            if(!pipei.equals("0")){
                setDataView();
            }
        } else {
            tvTitle.setText("编辑商品");
            setDataView();
        }

        qiNiuPhoto=new QiNiuPhoto(FaBuShangPinXiangQingTuActivity.this);
        photoDialog = new PhotoDialog(mContext,
                mContext.getResources().getIdentifier("BottomDialog", "style", mContext.getPackageName()));
        photoDialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.LEFT | Gravity.RIGHT);
        String canshujson = getIntent().getStringExtra("canshu");
        canshu = gson.fromJson(canshujson, FbspCanShuBean.class);
    }


    @OnClick({R.id.iv_back, R.id.iv_xq1, R.id.iv_xq2, R.id.iv_xq3, R.id.iv_xq4,R.id.bt_baocun})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_xq1:
                tuji=1;
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
            case R.id.iv_xq2:
                tuji=2;
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
            case R.id.iv_xq3:
                tuji=3;
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
            case R.id.iv_xq4:
                tuji=4;
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
            case R.id.bt_baocun:
                canshu.setOrigin(etChandi.getText().toString().trim());
                canshu.setLevel(etDengji.getText().toString().trim());
                canshu.setApply(etShiyong.getText().toString().trim());
                canshu.setProportion(etBili.getText().toString().trim());
                if(yemian.equals("0")){
                    if ("".equals(tu1)&"".equals(tu2)&"".equals(tu3)&"".equals(tu4)){
                        canshu.setDeputyPicture(canshu.getHostPicture());
                    }else {
                        if (!"".equals(tu1)){
                            futu +=tu1+",";
                        }
                        if (!"".equals(tu2)){
                            futu +=tu2+",";
                        }
                        if (!"".equals(tu3)){
                            futu +=tu3+",";
                        }
                        if (!"".equals(tu4)){
                            futu +=tu4+",";
                        }
                        canshu.setDeputyPicture(futu);
                    }
                    tianjiashangpin();
                } else {
                    updateshangpin();
                }

                break;
        }
    }

    private void tianjiashangpin() {
        Log.e("canshu",gson.toJson(canshu));
     HttpManager.getInstance()
             .with(mContext)
                    .setObservable(
                RetrofitManager
                        .getService()
                        .fabushangpin(PreferenceUtils.getString(MyApplication.mContext, "token",""),"",canshu.getDeputyPicture(),canshu.getChoose_specifications(),canshu.getPack_standard_one(),
                                canshu.getSpecOneNum(),canshu.getPack_standard_two(),canshu.getSpecTwoNum(),canshu.getPack_standard_tree(),
                                canshu.getSpecThreeNum(),canshu.getRation_one(),canshu.getPice_one(),canshu.getRation_two(),canshu.getPice_two(),
                                canshu.getRation_three(),canshu.getPice_three(),canshu.getInventory(),canshu.getOrigin(),canshu.getLevel(),
                                canshu.getApply(),canshu.getProportion(),canshu.getType_one_id(),canshu.getGoods(),canshu.getCommodity_state(),canshu.getCommodity_name(),
                                canshu.getType_two_id(),canshu.getType_tree_id(),canshu.getHostPicture(),canshu.getSpec_describe()))
                .setDataListener(new HttpDataListener<String>() {
            @Override
            public void onNext(String data) {
                Log.e("data",data+"---");
                ToastUtil.showToast("添加成功");
                finish();
                FaBuShangPinQiDingLiangActivity.instance.finish();
                FaBuShangPinActivity.instance.finish();
            }
        });
    }
    private void updateshangpin() {
        Log.e("canshu",gson.toJson(canshu));
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .updateshangpin(PreferenceUtils.getString(MyApplication.mContext, "token",""),spID,"",picList,canshu.getChoose_specifications(),canshu.getPack_standard_one(),
                                        canshu.getSpecOneNum(),canshu.getPack_standard_two(),canshu.getSpecTwoNum(),canshu.getPack_standard_tree(),
                                        canshu.getSpecThreeNum(),canshu.getRation_one(),canshu.getPice_one(),canshu.getRation_two(),canshu.getPice_two(),
                                        canshu.getRation_three(),canshu.getPice_three(),canshu.getInventory(),canshu.getOrigin(),canshu.getLevel(),
                                        canshu.getApply(),canshu.getProportion(),canshu.getType_one_id(),canshu.getGoods(),canshu.getCommodity_state(),canshu.getCommodity_name(),
                                        canshu.getType_two_id(),canshu.getType_tree_id(),canshu.getHostPicture()))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        Log.e("data",data+"---");
                        ToastUtil.showToast("编辑成功");
                        finish();
                        FaBuShangPinQiDingLiangActivity.instance.finish();
                        FaBuShangPinActivity.instance.finish();
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
                    public void onNext(final String list) {
                        Log.e("fenleifenlei",list+"---");
                        String qiniudata = qiNiuPhoto.getImageAbsolutePath(FaBuShangPinXiangQingTuActivity.this, outputUri);
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
                                                switch(tuji){
                                                    case 1:
                                                        tu1 = res.getString("key");
                                                        ivXq1.setImageBitmap(bitmap);
                                                        picList.set(0,tu1);
                                                        break;
                                                    case 2:
                                                        tu2 = res.getString("key");
                                                        ivXq2.setImageBitmap(bitmap);
                                                        picList.set(1,tu2);
                                                        break;
                                                    case 3:
                                                        tu3 = res.getString("key");
                                                        ivXq3.setImageBitmap(bitmap);
                                                        picList.set(2,tu3);
                                                        break;
                                                    case 4:
                                                        tu4 = res.getString("key");
                                                        ivXq4.setImageBitmap(bitmap);
                                                        picList.set(3,tu4);
                                                        break;
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
            imageUri = FileProvider.getUriForFile(this, "com.mingmen.mayi.mayibanjia", file);//通过FileProvider创建一个content类型的Uri
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

    public void setDataView(){//编辑展示
        EditorShangPinBean bean = PreferenceUtils.getEditorShangPinBean(MyApplication.mContext, "");
        spID = bean.getXq().getCommodity_id();
//        picList.addAll(bean.getXq().getFtPicture());
        if(!"null".equals(String.valueOf(bean.getXq().getFtPicture()))){
            for (int i=0;i<bean.getXq().getFtPicture().size();i++){
                picList.set(i,bean.getXq().getFtPicture().get(i));
            }
            for(int i = 0;i<bean.getXq().getDpicture().size();i++){
                if(i==0){
                    Glide.with(FaBuShangPinXiangQingTuActivity.this).load(bean.getXq().getDpicture().get(i)).into(ivXq1);
                } else if(i==1){
                    Glide.with(FaBuShangPinXiangQingTuActivity.this).load(bean.getXq().getDpicture().get(i)).into(ivXq2);
                } else if(i==2){
                    Glide.with(FaBuShangPinXiangQingTuActivity.this).load(bean.getXq().getDpicture().get(i)).into(ivXq3);
                } else {
                    Glide.with(FaBuShangPinXiangQingTuActivity.this).load(bean.getXq().getDpicture().get(i)).into(ivXq4);
                }
            }
        }

        for(int i = 0 ; i<bean.getParameteList().size();i++){
            switch (bean.getParameteList().get(i).getParamete_name_id()){
                case "产地":
                    etChandi.setText(bean.getParameteList().get(i).getParamete_content());
                    break;
                case "适用":
                    etShiyong.setText(bean.getParameteList().get(i).getParamete_content());
                    break;
                case "比例":
                    etBili.setText(bean.getParameteList().get(i).getParamete_content());
                    break;
                case "等级":
                    etDengji.setText(bean.getParameteList().get(i).getParamete_content());
                    break;
            }
        }
    }
}
