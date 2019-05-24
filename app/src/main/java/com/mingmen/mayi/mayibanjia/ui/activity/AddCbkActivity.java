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
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CbkXiangqingBean;
import com.mingmen.mayi.mayibanjia.bean.FbspGuiGeBean;
import com.mingmen.mayi.mayibanjia.bean.SearchCbkBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.CbkTouliaobiaozhunAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.PhotoDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.view.XCFlowLayout;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.GlideUtils;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddCbkActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_ppname)
    TextView tvPpname;
    @BindView(R.id.et_caipinmingcheng)
    EditText etCaipinmingcheng;
    @BindView(R.id.ll_mingcheng)
    LinearLayout llMingcheng;
    @BindView(R.id.iv_cptu)
    ImageView ivCptu;
    @BindView(R.id.tv_caipinzhuliao)
    TextView tvCaipinzhuliao;
    @BindView(R.id.xcf_zhuliao)
    XCFlowLayout xcfZhuliao;
    @BindView(R.id.rv_zhuliao_touliaobiaozhun)
    RecyclerView rvZhuliaoTouliaobiaozhun;
    @BindView(R.id.tv_add_zhuliao)
    TextView tvAddZhuliao;
    @BindView(R.id.tv_zhuliaobeizhu)
    TextView tvZhuliaobeizhu;
    @BindView(R.id.et_zhuliaobeizhu)
    EditText etZhuliaobeizhu;
    @BindView(R.id.tv_caipinfuliao)
    TextView tvCaipinfuliao;
    @BindView(R.id.xcf_fuliao)
    XCFlowLayout xcfFuliao;
    @BindView(R.id.rv_fuliao_touliaobiaozhun)
    RecyclerView rvFuliaoTouliaobiaozhun;
    @BindView(R.id.tv_add_fuliao)
    TextView tvAddFuliao;
    @BindView(R.id.tv_fuliaobeizhu)
    TextView tvFuliaobeizhu;
    @BindView(R.id.et_fuliaobeizhu)
    EditText etFuliaobeizhu;
    @BindView(R.id.et_jiage)
    EditText etJiage;
    @BindView(R.id.tv_caipintiaoliao)
    TextView tvCaipintiaoliao;
    @BindView(R.id.xcf_tiaoliao)
    XCFlowLayout xcfTiaoliao;
    @BindView(R.id.rv_tiaoliao_touliaobiaozhun)
    RecyclerView rvTiaoliaoTouliaobiaozhun;
    @BindView(R.id.tv_add_tiaoliao)
    TextView tvAddTiaoliao;
    @BindView(R.id.tv_tiaoliaobeizhu)
    TextView tvTiaoliaobeizhu;
    @BindView(R.id.et_tiaoliaobeizhu)
    EditText etTiaoliaobeizhu;
    @BindView(R.id.tv_maoli)
    TextView tvMaoli;

    private Context mContext;
    private int level = 1;
    private List<SearchCbkBean> zhulist = new ArrayList<>();
    private List<SearchCbkBean> fulist = new ArrayList<>();
    private List<SearchCbkBean> tiaolist = new ArrayList<>();

//    private Map<String,SearchCbkBean> zhuMap = new HashMap<>();
//    private Map<String,SearchCbkBean> fuMap = new HashMap<>();
//    private Map<String,SearchCbkBean> tiaoMap = new HashMap<>();

    private CbkTouliaobiaozhunAdapter zhuadapter;
    private CbkTouliaobiaozhunAdapter fuadapter;
    private CbkTouliaobiaozhunAdapter tiaoadapter;

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
    private String shangpintu = "";
    private ConfirmDialog confirmDialog;
    private String id = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_cbk;
    }

    @Override
    protected void initData() {
        mContext = AddCbkActivity.this;
        tvTitle.setText("添加成本");
        tvRight.setText("完成");
        id = getIntent().getStringExtra("id");
        tvRight.setTextColor(mContext.getResources().getColor(R.color.zangqing));
        EventBus.getDefault().register(this);
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));

        photoDialog = new PhotoDialog(mContext,
                mContext.getResources().getIdentifier("BottomDialog", "style", mContext.getPackageName()));
        qiNiuPhoto = new QiNiuPhoto(AddCbkActivity.this);
        photoDialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.LEFT | Gravity.RIGHT);
        zhuadapter = new CbkTouliaobiaozhunAdapter(mContext, zhulist);
        rvZhuliaoTouliaobiaozhun.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvZhuliaoTouliaobiaozhun.setAdapter(zhuadapter);
        zhuadapter.setCallBack(new CbkTouliaobiaozhunAdapter.CallBack() {
            @Override
            public void isClick(int pos, String num) {
                zhulist.get(pos).setCount(num);
            }
        });
        fuadapter = new CbkTouliaobiaozhunAdapter(mContext, fulist);
        rvFuliaoTouliaobiaozhun.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvFuliaoTouliaobiaozhun.setAdapter(fuadapter);
        fuadapter.setCallBack(new CbkTouliaobiaozhunAdapter.CallBack() {
            @Override
            public void isClick(int pos, String num) {
                fulist.get(pos).setCount(num);
            }
        });
        tiaoadapter = new CbkTouliaobiaozhunAdapter(mContext, tiaolist);
        rvTiaoliaoTouliaobiaozhun.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvTiaoliaoTouliaobiaozhun.setAdapter(tiaoadapter);
        tiaoadapter.setCallBack(new CbkTouliaobiaozhunAdapter.CallBack() {
            @Override
            public void isClick(int pos, String num) {
                tiaolist.get(pos).setCount(num);
            }
        });
        StringUtil.setInputNoEmoj(etCaipinmingcheng);
        StringUtil.setInputNoEmoj(etFuliaobeizhu);
        StringUtil.setInputNoEmoj(etZhuliaobeizhu);
        StringUtil.setInputNoEmoj(etTiaoliaobeizhu);

        if (StringUtil.isValid(id)) {
            getData();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.tv_add_zhuliao, R.id.tv_add_fuliao,
            R.id.tv_add_tiaoliao, R.id.iv_cptu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                if (!StringUtil.isValid(etCaipinmingcheng.getText().toString().trim())) {
                    ToastUtil.showToast("请填写菜品名");
                    return;
                } else if (!StringUtil.isValid(shangpintu)) {
                    ToastUtil.showToast("请选择商品图");
                    return;
                } else if (zhulist.size() == 0) {
                    ToastUtil.showToast("请添加主料");
                    return;
                } else if (fulist.size() == 0) {
                    ToastUtil.showToast("请添加辅料");
                    return;
                } else if (tiaolist.size() == 0) {
                    ToastUtil.showToast("请添加调料");
                    return;
                } else if (TextUtils.isEmpty(etJiage.getText().toString()) || Integer.valueOf(etJiage.getText().toString()) == 0) {
                    ToastUtil.showToast("请输入正确的菜品售卖价格");
                } else if (isNum(zhulist)) {
                    ToastUtil.showToastLong("请确认主料用料标准是否正确");
                } else if (isNum(fulist)) {
                    ToastUtil.showToastLong("请确认辅料用料标准是否正确");
                } else if (isNum(tiaolist)) {
                    ToastUtil.showToastLong("请确认调料用料标准是否正确");
                } else {
                    if(StringUtil.isValid(id)){
                        updateCaipin();
                    } else {
                        addCaipin();
                    }

                }
                break;
            case R.id.tv_add_zhuliao:
                level = 1;
                Jump_intent(SearchCaipinActivity.class, new Bundle());
                break;
            case R.id.tv_add_fuliao:
                level = 2;
                Jump_intent(SearchCaipinActivity.class, new Bundle());
                break;
            case R.id.tv_add_tiaoliao:
                level = 3;
                Jump_intent(SearchCaipinActivity.class, new Bundle());
                break;
            case R.id.iv_cptu:
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

    public void openCamera() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            //创建一个路径保存图片
            File imageFile = null;
            String storagePath;
            File storageDir;
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            try { //文件路径是公共的DCIM目录下的/camerademo目录
                storagePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + File.separator + "camerademo";
                storageDir = new File(storagePath);
                storageDir.mkdirs();
                imageFile = File.createTempFile(timeStamp, ".jpg", storageDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (imageFile != null) {
                imageUri = FileProvider.getUriForFile(this, mContext.getApplicationContext().getPackageName() + ".fileProvider", new File(imageFile.getAbsolutePath()));
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(takePictureIntent, REQUEST_CAPTURE);
            }
        }

    }


    /**
     * 裁剪
     */
    public void cropPhoto() {
        File file = new FileStorage().createCropFile();
        //缩略图保存地址
        outputUri = Uri.fromFile(file);
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
//        intent.putExtra("outputFormat", "PNG");
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void update(SearchCbkBean bean) {
        if (bean != null) {
            if (level == 1) {
                if (zhulist.size() != 0) {
                    for (int i = 0; i < zhulist.size(); i++) {
                        if (zhulist.get(i).getCommodity_id().equals(bean.getCommodity_id())) {
                            ToastUtil.showToastLong("该商品已存在");
                            return;
                        }
                    }
                }
                zhulist.add(bean);
//                zhulist.clear();
//                zhuMap.put(bean.getCommodity_id(),bean);
//                for (SearchCbkBean cbkbean: zhuMap.values()) {
//                    zhulist.add(cbkbean);
//                }
                initShangpinChildViews(xcfZhuliao, zhulist, 1);
                zhuadapter.notifyDataSetChanged();
            } else if (level == 2) {
                if (fulist.size() != 0) {
                    for (int i = 0; i < fulist.size(); i++) {
                        if (fulist.get(i).getCommodity_id().equals(bean.getCommodity_id())) {
                            ToastUtil.showToastLong("该商品已存在");
                            return;
                        }
                    }
                }
                fulist.add(bean);
//                fulist.clear();
//                fuMap.put(bean.getCommodity_id(),bean);
//                for (SearchCbkBean cbkbean: fuMap.values()) {
//                    fulist.add(cbkbean);
//                }
                initShangpinChildViews(xcfFuliao, fulist, 2);
                fuadapter.notifyDataSetChanged();
            } else {
                if (tiaolist.size() != 0) {
                    for (int i = 0; i < tiaolist.size(); i++) {
                        if (tiaolist.get(i).getCommodity_id().equals(bean.getCommodity_id())) {
                            ToastUtil.showToastLong("该商品已存在");
                            return;
                        }
                    }
                }
                tiaolist.add(bean);
//                tiaolist.clear();
//                tiaoMap.put(bean.getCommodity_id(),bean);
//                for (SearchCbkBean cbkbean: tiaoMap.values()) {
//                    tiaolist.add(cbkbean);
//                }
                initShangpinChildViews(xcfTiaoliao, tiaolist, 3);
                tiaoadapter.notifyDataSetChanged();
            }
        }

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        if (confirmDialog != null) {
            confirmDialog.dismiss();
        }
        super.onDestroy();
    }

    private void initShangpinChildViews(XCFlowLayout xcf, final List<SearchCbkBean> mList, final int xcflevel) {
        xcf.removeAllViews();
        List<LinearLayout> rls = new ArrayList();
        List<ImageView> ivs = new ArrayList<>();

        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = AppUtil.dip2px(12);
        lp.rightMargin = AppUtil.dip2px(0);
        lp.topMargin = AppUtil.dip2px(12);
        lp.bottomMargin = 0;
        for (int i = 0; i < mList.size(); i++) {
            LinearLayout rl = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.item_wenzi_x, null);
            TextView view = rl.findViewById(R.id.tv_wenzi);
            ImageView ivx = rl.findViewById(R.id.iv_shanchu);
//            view.setTextColor(mContext.getResources().getColor(R.color.zangqing));
//            view.setTextSize(12);
//            view.setPadding(AppUtil.dip2px(12), AppUtil.dip2px(8), AppUtil.dip2px(12), AppUtil.dip2px(8));
//            view.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_hollow_zangqing_3));
            view.setText(mList.get(i).getClassify_name());
            rls.add(rl);
            ivs.add(ivx);
            xcf.addView(rl, lp);
        }
        for (int i = 0; i < rls.size(); i++) {
            final int finalI = i;
            ivs.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (xcflevel == 1) {
                        deleteItem(finalI, 1);
                    } else if (xcflevel == 2) {
                        deleteItem(finalI, 2);
                    } else {
                        deleteItem(finalI, 3);
                    }
                }
            });
        }
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
                        final String qiniudata = qiNiuPhoto.getImageAbsolutePath(AddCbkActivity.this, outputUri);
                        final String key = null;
                        final String token = list;
                        File file = StringUtil.luban(mContext, qiniudata);
                        if (StringUtil.isValid(file.getPath())) {
                            bitmap = BitmapFactory.decodeFile(file.getPath());
                            String mydata = qiNiuPhoto.getImageAbsolutePath(AddCbkActivity.this, Uri.parse(file.getPath()));
                            MyApplication.uploadManager.put(qiniudata, key, token,
                                    new UpCompletionHandler() {
                                        @Override
                                        public void complete(String key, ResponseInfo info, JSONObject res) {
                                            //res包含hash、key等信息，具体字段取决于上传策略的设置
                                            if (info.isOK()) {
//                                            getImageAbsolutePath(CTDWanShanXinXiActivity.this,outputUri)
                                                try {
                                                    shangpintu = res.getString("key");
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            } else {
                                                //如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因
                                            }
                                        }
                                    }, null);

                            ivCptu.setImageBitmap(bitmap);
                        }

                    }
//                                });
//                    }
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

    //添加菜品
    private void addCaipin() {
        Log.e("addCaipin: ", new Gson().toJson(zhulist));
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .addCaipin(PreferenceUtils.getString(MyApplication.mContext, "token", ""),
                                        etCaipinmingcheng.getText().toString(), shangpintu, etJiage.getText().toString(),
                                        etZhuliaobeizhu.getText().toString(), etFuliaobeizhu.getText().toString(),
                                        etTiaoliaobeizhu.getText().toString(), new Gson().toJson(zhulist),
                                        new Gson().toJson(fulist), new Gson().toJson(tiaolist)))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        Log.e("data", new Gson().toJson(data) + "---");
                        finish();
                    }
                });

    }
    //更新菜品
    private void updateCaipin() {
        Log.e("updateCaipin: ", new Gson().toJson(zhulist));
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .updateCaipin(PreferenceUtils.getString(MyApplication.mContext, "token", ""),id,
                                        etCaipinmingcheng.getText().toString(), shangpintu, etJiage.getText().toString(),
                                        etZhuliaobeizhu.getText().toString(), etFuliaobeizhu.getText().toString(),
                                        etTiaoliaobeizhu.getText().toString(), new Gson().toJson(zhulist),
                                        new Gson().toJson(fulist), new Gson().toJson(tiaolist)))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        Log.e("data", new Gson().toJson(data) + "---");
                        finish();
                    }
                });

    }
    private void deleteItem(final int pos, final int level) {
        confirmDialog.showDialog("是否确认删除");
        confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog.dismiss();
                if (level == 1) {
                    zhulist.remove(pos);
                    initShangpinChildViews(xcfZhuliao, zhulist, 1);
                    zhuadapter.notifyDataSetChanged();
                } else if (level == 2) {
                    fulist.remove(pos);
                    initShangpinChildViews(xcfFuliao, fulist, 2);
                    fuadapter.notifyDataSetChanged();
                } else if (level == 3) {
                    tiaolist.remove(pos);
                    initShangpinChildViews(xcfTiaoliao, tiaolist, 3);
                    tiaoadapter.notifyDataSetChanged();
                }
            }
        });
        confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog.dismiss();
            }
        });

    }

    private boolean isNum(List<SearchCbkBean> list) {
        for (SearchCbkBean bean : list) {
            if (TextUtils.isEmpty(bean.getCount()) || Double.valueOf(bean.getCount()) == 0) {
                return true;
            }
        }
        return false;
    }

    //    private boolean isNum(List<SearchCbkBean> list,int level){
//        for (SearchCbkBean bean:list) {
//            if(TextUtils.isEmpty(bean.getCount())||Double.valueOf(bean.getCount())==0){
//                if(level==1){
//                    ToastUtil.showToastLong("请确认主料用料标准是否正确");
//                } else if(level==2){
//                    ToastUtil.showToastLong("请确认辅料用料标准是否正确");
//                } else {
//                    ToastUtil.showToastLong("请确认调料用料标准是否正确");
//                }
//
//                return true;
//            }
//        }
//        return false;
//    }
    private void getData() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getCbkXiangqing(PreferenceUtils.getString(MyApplication.mContext, "token", ""), id))
                .setDataListener(new HttpDataListener<CbkXiangqingBean>() {
                    @Override
                    public void onNext(CbkXiangqingBean data) {
                        etCaipinmingcheng.setText(data.getFood_name());
                        etJiage.setText(data.getSale_price());
                        shangpintu = data.getFood_photo();
                        GlideUtils.cachePhoto(mContext,ivCptu,data.getFood_photo());
                        zhulist.addAll(getZhuanhuan(data.getZhulist()));
                        fulist.addAll(getZhuanhuan(data.getFulist()));
                        tiaolist.addAll(getZhuanhuan(data.getPeilist()));
                        etZhuliaobeizhu.setText(data.getHost_remarke());
                        initShangpinChildViews(xcfZhuliao,zhulist,1);
                        initShangpinChildViews(xcfFuliao,fulist,2);
                        initShangpinChildViews(xcfTiaoliao,tiaolist,3);
                        zhuadapter.notifyDataSetChanged();
                        fuadapter.notifyDataSetChanged();
                        tiaoadapter.notifyDataSetChanged();
                    }
                });
    }

    private List<SearchCbkBean> getZhuanhuan(List<CbkXiangqingBean.CbkBean> list){
        List<SearchCbkBean> newlist = new ArrayList<>();
        for (CbkXiangqingBean.CbkBean cbkbean:list) {
            SearchCbkBean bean = new SearchCbkBean();
            bean.setAffiliated_spec_name(cbkbean.getSpec_name());
            bean.setClassify_name(cbkbean.getClassify_name());
            bean.setCommodity_id(cbkbean.getCommodity_id());
            bean.setCommodity_name(cbkbean.getCommodity_name());
            bean.setCount(cbkbean.getCount());
            bean.setPrice(cbkbean.getPrice());
            bean.setType_four_id(cbkbean.getType_four_id());
            newlist.add(bean);
        }
        return newlist;
    }
}
