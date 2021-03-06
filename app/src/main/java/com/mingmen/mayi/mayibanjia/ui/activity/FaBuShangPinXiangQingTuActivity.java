package com.mingmen.mayi.mayibanjia.ui.activity;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
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
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.EditorShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.FbspCanShuBean;
import com.mingmen.mayi.mayibanjia.bean.FeiLeiLableSubmitBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.FenLeiLableAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.PhotoDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.ClickUtil;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    //    @BindView(R.id.et_chandi)
//    EditText etChandi;
//    @BindView(R.id.et_dengji)
//    EditText etDengji;
//    @BindView(R.id.et_shiyong)
//    EditText etShiyong;
//    @BindView(R.id.et_bili)
//    EditText etBili;
    @BindView(R.id.bt_baocun)
    Button btBaocun;
    @BindView(R.id.rv_flcs)
    RecyclerView rvFlcs;
    @BindView(R.id.et_miaoshu)
    EditText etMiaoshu;
    @BindView(R.id.tishi)
    TextView tishi;


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
    private String tu1 = "", tu2 = "", tu3 = "", tu4 = "";
    private FbspCanShuBean canshu;
    private String futu = "";
    private String yemian = "0";
    private String pipei = "0";
    private String spID;
    private List<String> picList = Arrays.asList("", "", "", "");
    private FenLeiLableAdapter adapter;
    private List<FeiLeiLableSubmitBean> mlist = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.activity_fabushangpin_xiangqingtu;
    }

    @Override
    protected void initData() {
        mContext = FaBuShangPinXiangQingTuActivity.this;
        yemian = getIntent().getStringExtra("yemian");
        setRvAdapter();
        StringUtil.setInputNoEmoj(etMiaoshu, 50);
        etMiaoshu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tishi.setText(s.toString().trim().length()+"/50");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        if (yemian.equals("0")) {
            if (StringUtil.isValid(getIntent().getStringExtra("guige"))) {
                tvTitle.setText("新增规格");
                setDataView();
            } else {
                tvTitle.setText("添加商品");
                getFenLeiLable();
                pipei = getIntent().getStringExtra("pipei");
                if (!pipei.equals("0")) {
                    setDataView();
                }
            }

        } else {
            tvTitle.setText("编辑商品");
            setDataView();
        }

        qiNiuPhoto = new QiNiuPhoto(FaBuShangPinXiangQingTuActivity.this);
        photoDialog = new PhotoDialog(mContext,
                mContext.getResources().getIdentifier("BottomDialog", "style", mContext.getPackageName()));
        photoDialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.LEFT | Gravity.RIGHT);
        String canshujson = getIntent().getStringExtra("canshu");
        canshu = gson.fromJson(canshujson, FbspCanShuBean.class);
    }


    @OnClick({R.id.iv_back, R.id.iv_xq1, R.id.iv_xq2, R.id.iv_xq3, R.id.iv_xq4, R.id.bt_baocun})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_xq1:
                tuji = 1;
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
                tuji = 2;
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
                tuji = 3;
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
                tuji = 4;
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
//                canshu.setOrigin(etChandi.getText().toString().trim());
//                canshu.setLevel(etDengji.getText().toString().trim());
//                canshu.setApply(etShiyong.getText().toString().trim());
                canshu.setSpms(etMiaoshu.getText().toString());
                if (!ClickUtil.isFastDoubleClick()) {
                    if (yemian.equals("0")) {
                        if ("".equals(tu1) & "".equals(tu2) & "".equals(tu3) & "".equals(tu4)) {
                            canshu.setDeputyPicture(canshu.getHostPicture());
                        } else {
                            if (!"".equals(tu1)) {
                                futu += tu1 + ",";
                            }
                            if (!"".equals(tu2)) {
                                futu += tu2 + ",";
                            }
                            if (!"".equals(tu3)) {
                                futu += tu3 + ",";
                            }
                            if (!"".equals(tu4)) {
                                futu += tu4 + ",";
                            }
                            canshu.setDeputyPicture(futu);
                        }
                        tianjiashangpin();
                    } else {
//                    if(StringUtil.isValid(canshu.getPrice())&&Double.valueOf(canshu.getPrice())>0){
//                        tejia();
//                    } else {
                        updateshangpin();
//                    }

                    }
                }
                break;
        }
    }

    private void tianjiashangpin() {
        Log.e("tianjiashangpin: ",canshu.getSpec_count()+"" );
        Log.e("canshu", gson.toJson(canshu));
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .fabushangpin(PreferenceUtils.getString(MyApplication.mContext, "token", ""),canshu.getClassify_name(), spID, "", canshu.getDeputyPicture(),
                                        canshu.getPack_standard_two(), canshu.getPack_standard_tree(),
                                        canshu.getRation_one(), canshu.getPice_one(), canshu.getInventory(), new Gson().toJson(mlist), canshu.getType_one_id(), canshu.getGoods(), canshu.getCommodity_state(), canshu.getCommodity_name(),
                                        canshu.getType_two_id(), canshu.getType_tree_id(), canshu.getType_four_id(), canshu.getHostPicture(),
                                        canshu.getSpec_describe(), canshu.getPrice(), canshu.getSpec_count(), canshu.getSpec_detal_id(), canshu.getPack_standard_tree_name(), canshu.getSpec_detal_name(), canshu.getBrand(), canshu.getSpms(),1))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        Log.e("data", data + "---");
                        ToastUtil.showToast("添加成功");
                        finish();
                        if (XJSPFeiLeiXuanZeActivity.instance != null) {
                            XJSPFeiLeiXuanZeActivity.instance.finish();
                        }
                        FaBuShangPinActivity.instance.finish();
                    }
                });
    }

    private void updateshangpin() {
        Log.e("canshu", gson.toJson(canshu));
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .updateshangpin(PreferenceUtils.getString(MyApplication.mContext, "token", ""),canshu.getClassify_name(), spID, "", picList,
                                        canshu.getPack_standard_two(), canshu.getPack_standard_tree(),
                                        canshu.getRation_one(), canshu.getPice_one(), canshu.getInventory(), new Gson().toJson(mlist), canshu.getType_one_id(), canshu.getGoods(), canshu.getCommodity_state(), canshu.getCommodity_name(),
                                        canshu.getType_two_id(), canshu.getType_tree_id(), canshu.getType_four_id(), canshu.getHostPicture(), canshu.getSpec_describe(),
                                        canshu.getPrice(), canshu.getSpec_count(), canshu.getSpec_detal_id(), canshu.getPack_standard_tree_name(), canshu.getSpec_detal_name(), canshu.getBrand(),canshu.getSpms(),1))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        Log.e("data", data + "---");
                        ToastUtil.showToast("编辑成功");
//                        if(XJSPFeiLeiXuanZeActivity.instance!=null){
//                            XJSPFeiLeiXuanZeActivity.instance.finish();
//                        }
                        finish();
                        FaBuShangPinActivity.instance.finish();
                    }
                });
    }

    private void tejia() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .shangpinZhuanhuan(PreferenceUtils.getString(MyApplication.mContext, "token", ""), spID, "", "", ""))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        Log.e("data", data + "---");
                        ToastUtil.showToast("编辑成功");
//                        if(XJSPFeiLeiXuanZeActivity.instance!=null){
//                            XJSPFeiLeiXuanZeActivity.instance.finish();
//                        }
                        finish();
                        FaBuShangPinActivity.instance.finish();
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
                    public void onNext(final String list) {
                        Log.e("fenleifenlei", list + "---");
                        String qiniudata = qiNiuPhoto.getImageAbsolutePath(FaBuShangPinXiangQingTuActivity.this, outputUri);
                        String key = null;
                        String token = list;
                        final File file = StringUtil.luban(mContext, qiniudata);
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
                                                    switch (tuji) {
                                                        case 1:
                                                            tu1 = res.getString("key");
                                                            GlideUtils.cachePhoto(mContext, ivXq1, file.getPath());
//                                                            ivXq1.setImageBitmap(bitmap);
                                                            picList.set(0, tu1);
                                                            customDialog.dismiss();
                                                            break;
                                                        case 2:
                                                            tu2 = res.getString("key");
//                                                            ivXq2.setImageBitmap(bitmap);
                                                            GlideUtils.cachePhoto(mContext, ivXq2, file.getPath());
                                                            picList.set(1, tu2);
                                                            customDialog.dismiss();
                                                            break;
                                                        case 3:
                                                            tu3 = res.getString("key");
//                                                            ivXq3.setImageBitmap(bitmap);
                                                            GlideUtils.cachePhoto(mContext, ivXq3, file.getPath());
                                                            picList.set(2, tu3);
                                                            customDialog.dismiss();
                                                            break;
                                                        case 4:
                                                            tu4 = res.getString("key");
//                                                            ivXq4.setImageBitmap(bitmap);
                                                            GlideUtils.cachePhoto(mContext, ivXq4, file.getPath());
                                                            picList.set(3, tu4);
                                                            customDialog.dismiss();
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


                    }
                }, true);
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

    public void setDataView() {//编辑展示
        EditorShangPinBean bean = PreferenceUtils.getEditorShangPinBean(MyApplication.mContext, "");
        etMiaoshu.setText(bean.getXq().getSpms());
        spID = bean.getXq().getCommodity_id();
//        picList.addAll(bean.getXq().getFtPicture());
        if (!"null".equals(String.valueOf(bean.getXq().getFtPicture()))) {
            for (int i = 0; i < bean.getXq().getFtPicture().size(); i++) {
                picList.set(i, bean.getXq().getFtPicture().get(i));
            }
            for (int i = 0; i < bean.getXq().getDpicture().size(); i++) {
                if (i == 0) {
                    GlideUtils.cachePhoto(FaBuShangPinXiangQingTuActivity.this, ivXq1, bean.getXq().getDpicture().get(i));
                    tu1 = bean.getXq().getDpicture().get(i);
                } else if (i == 1) {
                    GlideUtils.cachePhoto(FaBuShangPinXiangQingTuActivity.this, ivXq2, bean.getXq().getDpicture().get(i));
                    tu2 = bean.getXq().getDpicture().get(i);
                } else if (i == 2) {
                    GlideUtils.cachePhoto(FaBuShangPinXiangQingTuActivity.this, ivXq3, bean.getXq().getDpicture().get(i));
                    tu3 = bean.getXq().getDpicture().get(i);
                } else {
                    GlideUtils.cachePhoto(FaBuShangPinXiangQingTuActivity.this, ivXq4, bean.getXq().getDpicture().get(i));
                    tu4 = bean.getXq().getDpicture().get(i);
                }
            }
        }
        int mysize = bean.getParameteList() == null ? 0 : bean.getParameteList().size();
        if (mysize != 0) {
            for (int i = 0; i < mysize; i++) {
                FeiLeiLableSubmitBean mybean = new FeiLeiLableSubmitBean();
                mybean.setParamete_name(bean.getParameteList().get(i).getParamete_name());
                mybean.setParamete_name_id(bean.getParameteList().get(i).getParamete_name_id());
                mybean.setParamete_content(bean.getParameteList().get(i).getParamete_content());
                mlist.add(mybean);
                adapter.notifyDataSetChanged();
            }
        }


//        for(int i = 0 ; i<bean.getParameteList().size();i++){
//            switch (bean.getParameteList().get(i).getParamete_name_id()){
//                case "产地":
//                    etChandi.setText(bean.getParameteList().get(i).getParamete_content());
//                    break;
//                case "适用":
//                    etShiyong.setText(bean.getParameteList().get(i).getParamete_content());
//                    break;
//                case "比例":
//                    etBili.setText(bean.getParameteList().get(i).getParamete_content());
//                    break;
//                case "等级":
//                    etDengji.setText(bean.getParameteList().get(i).getParamete_content());
//                    break;
//            }
//        }
    }

    public void changeList(int pos, String text) {
        if (!text.equals(mlist.get(pos).getParamete_content())) {
            mlist.get(pos).setParamete_content(text);
        }
    }

    private void getFenLeiLable() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getFenLeiCanShu(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<List<FeiLeiLableSubmitBean>>() {
                    @Override
                    public void onNext(List<FeiLeiLableSubmitBean> data) {
                        int mysize = data == null ? 0 : data.size();
                        if (mysize != 0) {
                            mlist.addAll(data);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void setRvAdapter() {
        rvFlcs.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        adapter = new FenLeiLableAdapter(mContext, mlist, FaBuShangPinXiangQingTuActivity.this);
        rvFlcs.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
