package com.mingmen.mayi.mayibanjia.ui.activity;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.EditorShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.FCGName;
import com.mingmen.mayi.mayibanjia.bean.FbspCanShuBean;
import com.mingmen.mayi.mayibanjia.bean.FbspGuiGeBean;
import com.mingmen.mayi.mayibanjia.bean.FenLeiMingChengBean;
import com.mingmen.mayi.mayibanjia.bean.ShangPinGuanLiBean;
import com.mingmen.mayi.mayibanjia.bean.ShangPinSousuoMohuBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.XinJianSpMohuAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.PhotoDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.SinglePicker;

/**
 * Created by Administrator on 2018/8/6/006.
 */

public class FaBuShangPinActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_sptu)
    ImageView ivSptu;
    @BindView(R.id.et_spming)
    EditText etSpming;
    @BindView(R.id.tv_fenleimingcheng)
    TextView tvFenleimingcheng;
    @BindView(R.id.ll_fenleimingcheng)
    LinearLayout llFenleimingcheng;
    @BindView(R.id.et_miaoshu)
    EditText etMiaoshu;
    @BindView(R.id.tishi)
    TextView tvTishi;
    //    @BindView(R.id.iv_yiji)
//    ImageView ivYiji;
//    @BindView(R.id.et_yiji)
//    EditText etYiji;
//    @BindView(R.id.tv_yijiguige)
//    TextView tvYijiguige;
//    @BindView(R.id.ll_yijiguige)
//    LinearLayout llYijiguige;
//    @BindView(R.id.iv_erji)
//    ImageView ivErji;
//    @BindView(R.id.et_erji)
//    EditText etErji;
//    @BindView(R.id.tv_erjiguige)
//    TextView tvErjiguige;
//    @BindView(R.id.ll_erjiguige)
//    LinearLayout llErjiguige;
//    @BindView(R.id.iv_sanji)
//    ImageView ivSanji;
    @BindView(R.id.bt_xiayibu)
    Button btXiayibu;
    @BindView(R.id.iv_teshu)
    ImageView ivTeshu;
    @BindView(R.id.ll_shangpin)
    LinearLayout llShangpin;
    @BindView(R.id.tv_sanji)
    TextView tvSanji;
    @BindView(R.id.ll_sanjiguige)
    LinearLayout llSanjiguige;
    @BindView(R.id.rl_teshu)
    RelativeLayout rlTeshu;

    private Context mContext;
    private ArrayList<FCGName> zonglist = new ArrayList<>();
    private String yijiming = "";
    private String lingjiming = "";
    private String yijiid = "";
    private String lingjiid = "";
    private ArrayList<FCGName> erjilist = new ArrayList<>();
    private String erjiname = "";
    private String erjiid = "";
    private ArrayList<FCGName> sanjilist = new ArrayList<>();
    private String sanjiname = "";
    private String sanjiid = "";
    private ArrayList<FCGName> sijilist = new ArrayList<>();
    private String sijiname = "";
    private String sijiid = "";
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
    private ArrayList<FCGName> yijiFenLei = new ArrayList<>();
    //    private ArrayList<FbspGuiGeBean> yijiguige;
//    private ArrayList<FbspGuiGeBean> erjiguige;
    private ArrayList<FbspGuiGeBean> sanjiguige;
    //    private String yijiguigename = "";
//    private String yijiguigeid = "";
    private String sanjiguigename = "";
    private String sanjiguigeid = "";
    //    private String erjiguigename = "";
//    private String erjiguigeid = "";
    private FbspCanShuBean canShuBean;
    //    private int xuanzeguige = 0;
    private boolean sanjikexuan;
    private ShangPinGuanLiBean.GoodsListBean bean;
    public static FaBuShangPinActivity instance = null;
    public String yemian = "0";
    public String pipei = "0";
    public String goods = "0";
    private PopupWindow mPopWindow;
    private ArrayList<ShangPinSousuoMohuBean> datas = new ArrayList<>();
    private XinJianSpMohuAdapter mohuAdapter;
    private RecyclerView rv_mohu;
    private String ming = "";

    //    private boolean isSelect = false;
    @Override
    public int getLayoutId() {
        return R.layout.activity_fabushangpin;
    }

    @Override
    protected void initData() {
        mContext = this;
        instance = this;
        goods = getIntent().getStringExtra("goods");
        if ("0".equals(getIntent().getStringExtra("state"))) {
            tvTitle.setText("新建商品");
            etSpming.setEnabled(false);
            etSpming.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (!ming.equals(s.toString().trim())) {
                        Log.e("我的输入", s.toString().trim() + "这是原来的名字" + ming);
                        canShuBean.setType_tree_id("");
                        sanjiid = "";
                        getfcgname(s.toString().trim());
                    }

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        } else {
            tvTitle.setText("编辑商品");
            yemian = "1";
            String id = getIntent().getStringExtra("bean");
            setDataView(id);
        }

        canShuBean = new FbspCanShuBean();
        qiNiuPhoto = new QiNiuPhoto(FaBuShangPinActivity.this);
        photoDialog = new PhotoDialog(mContext,
                mContext.getResources().getIdentifier("BottomDialog", "style", mContext.getPackageName()));
        photoDialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.LEFT | Gravity.RIGHT);
        etMiaoshu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvTishi.setText(s.toString().trim().length() + "/50");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    //    @OnClick({R.id.iv_back, R.id.iv_sptu, R.id.iv_yiji, R.id.iv_erji, R.id.iv_sanji, R.id.ll_fenleimingcheng, R.id.ll_yijiguige, R.id.ll_erjiguige, R.id.ll_sanjiguige, R.id.bt_xiayibu})
    @OnClick({R.id.iv_back, R.id.iv_sptu, R.id.ll_fenleimingcheng, R.id.ll_sanjiguige, R.id.bt_xiayibu, R.id.iv_teshu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_sptu:
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
            case R.id.ll_fenleimingcheng:
                getFeilei("-1","1");
                break;
            case R.id.ll_sanjiguige:
//                if ("".equals(sanjiid)) {
//                    ToastUtil.showToast("请先选择分类名称");
//                } else {
                int sanjisize = sanjiguige == null ? 0 : sanjiguige.size();
                if (sanjisize > 0) {
                    final SinglePicker<FbspGuiGeBean> picker = new SinglePicker<FbspGuiGeBean>(FaBuShangPinActivity.this, sanjiguige);
                    picker.setCanceledOnTouchOutside(false);
                    picker.setSelectedIndex(1);
                    picker.setCycleDisable(false);
                    picker.setOnItemPickListener(new SinglePicker.OnItemPickListener<FbspGuiGeBean>() {
                        @Override
                        public void onItemPicked(int index, FbspGuiGeBean item) {
                            sanjiguigename = item.getSpec_name();
                            sanjiguigeid = item.getSpec_id() + "";
                            tvSanji.setText(sanjiguigename);
                            Log.e("sanjiguigenamesanjiguigeid", sanjiguigename + "===" + sanjiguigeid);
                            picker.dismiss();
                        }
                    });
                    picker.show();
                } else {
                    ToastUtil.showToast("该商品没有三级规格");
                }

//                }
                break;
            case R.id.bt_xiayibu:
                if ("".equals(etSpming.getText().toString().trim())) {
                    ToastUtil.showToast("请填写商品名");
                } else if ("".equals(shangpintu)) {
                    ToastUtil.showToast("请选择商品图");
                }
//                    else if (xuanzeguige == 0) {
//                        ToastUtil.showToast("请选择售卖规格");
//                    }
                else if (sanjikexuan == true) {
                    if ("".equals(sanjiguigeid) | "".equals(tvSanji.getText().toString().trim())) {
                        ToastUtil.showToast("请选择三级规格并填写数量");
                    } else {
                        tiaozhuan();
                    }
                } else {
                    tiaozhuan();
                }
                break;
//            case R.id.iv_teshu:
//                isSelect = !isSelect;
//                ivTeshu.setSelected(isSelect);
//                llShangpin.setVisibility(isSelect?View.VISIBLE:View.GONE);
//                break;
        }
    }

    private void tiaozhuan() {//传递参数界面
        canShuBean.setCommodity_name(etSpming.getText().toString().trim());
//        canShuBean.setChoose_specifications(xuanzeguige + "");
//        canShuBean.setPack_standard_one(yijiguigeid);
//        canShuBean.setPack_standard_two(erjiguigeid);
        canShuBean.setType_one_id(lingjiid);
        canShuBean.setType_two_id(yijiid);
        canShuBean.setType_tree_id(erjiid);
        canShuBean.setHostPicture(shangpintu);
        canShuBean.setCommodity_state("0");
        canShuBean.setPack_standard_tree(sanjiguigeid);
        canShuBean.setGoods(goods);
        canShuBean.setSpec_describe(etMiaoshu.getText().toString().trim());
        if (!ming.equals(etSpming.getText().toString().trim())) {
            canShuBean.setType_four_id("");
        } else {
            canShuBean.setType_four_id(sanjiid);
        }
        Intent intent = new Intent(mContext, FaBuShangPinQiDingLiangActivity.class);
        intent.putExtra("canshu", gson.toJson(canShuBean));
        intent.putExtra("yemian", yemian);
        intent.putExtra("pipei", pipei);
        startActivity(intent);
    }





    private void getguige() {
        Log.e("sanjiid", sanjiid);
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getguige(sanjiid))
                .setDataListener(new HttpDataListener<List<FbspGuiGeBean>>() {

                    @Override
                    public void onNext(List<FbspGuiGeBean> data) {
                        Log.e("data", gson.toJson(data) + "---");
                        sanjiguige = new ArrayList<FbspGuiGeBean>();


                        for (int i = 0; i < data.size(); i++) {
                            sanjiguige.add(data.get(i));
                        }
                        if (sanjiguige.size() == 0) {
                            sanjikexuan = false;
                        } else {
                            sanjikexuan = true;
                            tvSanji.setText(data.get(0).getSpec_name());
                            sanjiguigeid = data.get(0).getSpec_id();
                        }

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
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .qiniushangchuan())
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String list) {
                        Log.e("fenleifenlei", list + "---");
                        String qiniudata = qiNiuPhoto.getImageAbsolutePath(FaBuShangPinActivity.this, outputUri);
                        String key = null;
                        String token = list;
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
                        ivSptu.setImageBitmap(bitmap);

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


    public void setDataView(String id) {//编辑页面展示
        pipei = "1";
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .editorShangPin(id))
                .setDataListener(new HttpDataListener<EditorShangPinBean>() {
                    @Override
                    public void onNext(EditorShangPinBean data) {
                        EditorShangPinBean.XqBean bean = data.getXq();
                        PreferenceUtils.setEditorShangPinBean(MyApplication.mContext, data);
                        if (StringUtil.isValid(bean.getHostPicture())) {
                            Glide.with(FaBuShangPinActivity.this).load(bean.getHostPicture()).into(ivSptu);
                            shangpintu = bean.getHostPicture();
                        }
                        etMiaoshu.setText(bean.getSpec_describe());
                        tvTishi.setText(bean.getSpec_describe().length() + "/50");
                        ming = bean.getClassify_name() + "";
                        etSpming.setText(bean.getClassify_name() + "");
                        tvFenleimingcheng.setText(bean.getClassify_name());
                        sanjikexuan = true;
                        sanjiguigeid = bean.getPack_standard_tree();
                        yijiid = bean.getType_one_id();
                        erjiid = bean.getType_two_id();
                        sanjiid = bean.getType_tree_id();
                        etSpming.setEnabled(true);
                        getguige();
                        tvSanji.setText(bean.getPackThreeName());
                        tvTishi.setText(bean.getSpec_describe().length() + "/50");
                    }
                });
    }

    private void getfcgname(final String name) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .searchSpname(PreferenceUtils.getString(MyApplication.mContext, "token", ""), erjiid, name))
                .setDataListener(new HttpDataListener<List<ShangPinSousuoMohuBean>>() {
                    @Override
                    public void onNext(List<ShangPinSousuoMohuBean> data) {
                        datas.clear();
                        datas.addAll(data);
                        if (mPopWindow != null) {
                            Log.e("data", data + "111111111");
                            mPopWindow.showAsDropDown(etSpming);
                            mohuAdapter.setData(datas);
                        } else {
                            showPopupWindow();
                        }

                    }
                }, false);
    }

    //PopupWindow
    private void showPopupWindow() {
        View view = View.inflate(mContext, R.layout.pp_textview_recycleview, null);
        mPopWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int width = getWindowManager().getDefaultDisplay().getWidth();
        int height = getWindowManager().getDefaultDisplay().getHeight();
        mPopWindow.setWidth(width * 2 / 6);
        mPopWindow.setHeight(height * 2 / 9);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
//        mPopWindow.setFocusable(true);
//        mPopWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//        mPopWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        mPopWindow.showAsDropDown(etSpming);
        rv_mohu = (RecyclerView) view.findViewById(R.id.rv_list);
        mohuAdapter = new XinJianSpMohuAdapter(mContext, datas);
        rv_mohu.setAdapter(mohuAdapter);
        rv_mohu.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mohuAdapter.setOnItemClickListener(new XinJianSpMohuAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                ming = "" + datas.get(position).getClassify_name();
                etSpming.setText("" + datas.get(position).getClassify_name());
                sanjiid = datas.get(position).getClassify_id();
                canShuBean.setType_tree_id(datas.get(position).getClassify_id());
                mPopWindow.dismiss();
                getguige();
//                setDataView(datas.get(position).getCommodity_id()+"");
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void getFeilei(String id, final String level) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getFeiLei(id, level))
                .setDataListener(new HttpDataListener<List<FCGName>>() {

                    @Override
                    public void onNext(List<FCGName> list) {
                        final int mysize = list == null ? 0 : list.size();
                        final SinglePicker<FCGName> picker = new SinglePicker<FCGName>(FaBuShangPinActivity.this, list);
                        picker.setCanceledOnTouchOutside(false);
                        picker.setSelectedIndex(2);
                        picker.setCycleDisable(false);
                        picker.setOnItemPickListener(new SinglePicker.OnItemPickListener<FCGName>() {
                            @Override
                            public void onItemPicked(int index, FCGName item) {
                                if(level.equals("1")&&mysize!=0){
                                    lingjiming = item.getClassify_name();
                                    lingjiid = item.getClassify_id() + "";
                                    tvFenleimingcheng.setText(lingjiming);
                                    picker.dismiss();
                                    getFeilei(lingjiid,"2");
                                } else  if(level.equals("2")&&mysize!=0){
                                    yijiming = item.getClassify_name();
                                    yijiid = item.getClassify_id();
                                    tvFenleimingcheng.setText(lingjiming+"-"+yijiming);
                                    picker.dismiss();
                                    getFeilei(yijiid,"3");
                                } else if(level.equals("3")&&mysize!=0){
                                    erjiname = item.getClassify_name();
                                    erjiid = item.getClassify_id();
                                    tvFenleimingcheng.setText(lingjiming+"-"+yijiming+"-"+erjiname);
                                    picker.dismiss();
//                                    getFeilei(erjiid,"4");
                                    etSpming.setEnabled(true);
                                    getguige();
                                } else if (level.equals("4")&&mysize!=0){
                                    sanjiname = item.getClassify_name();
                                    sanjiid = item.getClassify_id();
                                    tvFenleimingcheng.setText(lingjiming+"-"+yijiming+"-"+erjiname+"-"+sanjiname);
                                    picker.dismiss();
//                                    getFeilei(sanjiid,"4");
                                }

                            }
                        });
                        picker.show();
                    }
                }, false);
    }
}
