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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
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

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CreateGuigeBean;
import com.mingmen.mayi.mayibanjia.bean.EditorShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.FCGName;
import com.mingmen.mayi.mayibanjia.bean.FbspCanShuBean;
import com.mingmen.mayi.mayibanjia.bean.FbspGuiGeBean;
import com.mingmen.mayi.mayibanjia.bean.ShangPinSousuoMohuBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.XJSPFeiLeiGuigeAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.XinJianSpMohuAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.GuigeDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.LianggeXuanXiangDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.PhotoDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ZXGuigeDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.GlideUtils;
import com.mingmen.mayi.mayibanjia.utils.MyMath;
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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    @BindView(R.id.ll_fl)
    LinearLayout llFl;
    @BindView(R.id.ll_sp)
    LinearLayout llSp;
    @BindView(R.id.tv_spname)
    TextView tvSpname;
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
    @BindView(R.id.iv_teshu)
    ImageView ivTeshu;
    @BindView(R.id.iv_sanjiguige)
    ImageView ivSanjiguige;
    @BindView(R.id.ll_shangpin)
    LinearLayout llShangpin;
    @BindView(R.id.tv_sanji)
    TextView tvSanji;
    @BindView(R.id.ll_sanjiguige)
    LinearLayout llSanjiguige;
    @BindView(R.id.tv_yxgg)
    TextView tvYxgg;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.tv_zxgg)
    TextView tvZxgg;
    @BindView(R.id.ll_zxgg)
    LinearLayout llZxgg;
    @BindView(R.id.ll_dw)
    LinearLayout llDw;
    @BindView(R.id.ll_tejia)
    LinearLayout llTejia;
    @BindView(R.id.tv_tejia)
    TextView tvTejia;
    @BindView(R.id.ll_showtejia)
    LinearLayout llShowTejia;
    @BindView(R.id.et_tejia)
    EditText etTejia;
    @BindView(R.id.iv_qingkong)
    ImageView ivQingkong;
    @BindView(R.id.tv_zhiman)
    TextView tvZhiman;
    @BindView(R.id.tv_danwei)
    TextView tvDanwei;
    @BindView(R.id.tv_qdl_gg)
    TextView tvQdlGg;
    @BindView(R.id.tv_spxz)
    TextView tvSpxz;
    @BindView(R.id.et_qidingliangdanjia1)
    EditText etQidingliangdanjia1;
    @BindView(R.id.et_qidingliang1)
    EditText etQidingliang1;
    @BindView(R.id.et_kucun)
    EditText etKucun;
    @BindView(R.id.et_ppming)
    EditText etPpming;
    @BindView(R.id.tv_shangpinmingcheng)
    TextView tvShangpinmingcheng;
    @BindView(R.id.ll_shangpinmingcheng)
    LinearLayout llShangpinmingcheng;
    @BindView(R.id.ll_teshu)
    LinearLayout llTeshu;
    @BindView(R.id.tv_xzgg)
    TextView tvXzgg;
    @BindView(R.id.ll_szgg)
    LinearLayout llSzgg;
    @BindView(R.id.rv_guige)
    RecyclerView rvGuige;
    @BindView(R.id.tv_ppname)
    TextView tvPpname;
    @BindView(R.id.tv_ggms)
    TextView tvGgms;
    @BindView(R.id.ll_pinpai)
    LinearLayout llPinpai;
    @BindView(R.id.ll_tj)
    LinearLayout llTj;
    @BindView(R.id.ll_guige)
    LinearLayout llGuige;
    @BindView(R.id.ll_xjguige)
    LinearLayout llXjguige;
    @BindView(R.id.ll_xzguige)
    LinearLayout llXzguige;
//    @BindView(R.id.et_xjguige)
//    EditText etXjguige;
    @BindView(R.id.iv_guige)
    ImageView ivGuige;
    @BindView(R.id.tv_tj_danwei)
    TextView tvTjDanwei;
    @BindView(R.id.et_miaoshu)
    TextView etMiaoshu;
    @BindView(R.id.tishi)
    TextView tvTishi;
    @BindView(R.id.et_number_cj1)
    EditText etNumberCj1;
    @BindView(R.id.tv_gg_cj1)
    TextView tvGgCj1;
    @BindView(R.id.ll_gg_cj1)
    LinearLayout llGgCj1;
    @BindView(R.id.tv_ggms_cj1)
    TextView tvGgmsCj1;
    @BindView(R.id.ll_cj1)
    LinearLayout llCj1;
    @BindView(R.id.et_number_cj2)
    EditText etNumberCj2;
    @BindView(R.id.tv_gg_cj2)
    TextView tvGgCj2;
    @BindView(R.id.ll_gg_cj2)
    LinearLayout llGgCj2;
    @BindView(R.id.tv_ggms_cj2)
    TextView tvGgmsCj2;
    @BindView(R.id.tv_cj1)
    TextView tvCj1;
    @BindView(R.id.tv_cj2)
    TextView tvCj2;
    @BindView(R.id.ll_cj2)
    LinearLayout llCj2;
    @BindView(R.id.rl_teshu)
    RelativeLayout rlTeshu;
    @BindView(R.id.bt_xiayibu)
    Button btXiayibu;

    private Context mContext;
    private String yijiming = "";
    private String lingjiming = "";
    private String yijiid = "";
    private String lingjiid = "";
    private String erjiname = "";
    private String erjiid = "";
    private String sanjiname = "";
    private String sanjiid = "";
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
    private ArrayList<FbspGuiGeBean> sanjiguige;
    private ArrayList<FbspGuiGeBean> zuixiaoguige = new ArrayList<>();
    private ArrayList<FbspGuiGeBean> zuixiaoguigelist = new ArrayList<>();
    private String sanjiguigename = "";
    private String sanjiguigeid = "";
    private FbspCanShuBean canShuBean;
    private boolean sanjikexuan;
    public static FaBuShangPinActivity instance = null;
    public String yemian = "0";
    public String pipei = "0";
    public String goods = "0";
    private PopupWindow mPopWindow;
    private ArrayList<ShangPinSousuoMohuBean> datas = new ArrayList<>();
    private XinJianSpMohuAdapter mohuAdapter;
    private RecyclerView rv_mohu;
    private String ming = "";
    private boolean isGuige;
    private String zxid = "";
    private String zxname = "";
    private String spname = "";
    private String yclId = "346926195929448587b078e7fe613530 ";
    private boolean isSelect;
    private boolean isGgSelect;
    private int REQUEST_GUIGE = 6;
    private XJSPFeiLeiGuigeAdapter guigeadapter;
    private List<ShangPinSousuoMohuBean> guigedatas = new ArrayList<>();
    private Map<String, EditText> etMap;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fabushangpin;
    }

    private int REQUEST_CODE = 5;

    private LianggeXuanXiangDialog tejiadialog;
    private boolean istejia;
    private String qidingliang1;
    private String qidingliangdanjia1;
    private boolean getGuige;
    private String cj1 = "",cj2 = "";

    @Override
    protected void initData() {
        mContext = this;
        instance = this;
        canShuBean = new FbspCanShuBean();
        goods = getIntent().getStringExtra("goods");
        EventBus.getDefault().register(this);
        StringUtil.setInputNoEmoj(etSpming, 20);
        StringUtil.setInputNoEmoj(etPpming, 20);
        etSpming.setEnabled(false);
        etMap = new LinkedHashMap<>();
        etNumberCj1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (StringUtil.isValid(s.toString().trim())) {
                    if(!s.toString().trim().equals("0")||Double.valueOf(s.toString().trim())!=0){
                        setEtMiaoshuText();
                    } else {
                        ToastUtil.showToastLong("数量最小为1");
                        etNumberCj1.setText("1");
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etNumberCj2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (StringUtil.isValid(s.toString().trim())) {
                    if(!s.toString().trim().equals("0")||Double.valueOf(s.toString().trim())!=0){
                        setEtMiaoshuText();
                    } else {
                        ToastUtil.showToastLong("数量最小为1");
                        etNumberCj2.setText("1");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (StringUtil.isValid(s.toString().trim())) {
                    if(!s.toString().trim().equals("0")||Double.valueOf(s.toString().trim())!=0){
                        setEtMiaoshuText();
                    } else {
                        ToastUtil.showToastLong("数量最小为1");
                        etNumber.setText("1");
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
//        etSpming.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (!ming.equals(s.toString().trim())) {
//                    if (!isSelect) {
//                        Log.e("我的输入", s.toString().trim() + "这是原来的名字" + ming);
//                        canShuBean.setType_tree_id("");
//                        sanjiid = "";
//                        llSanjiguige.setEnabled(true);
//                        etMiaoshu.setEnabled(true);
//                        llZxgg.setEnabled(true);
//                        etNumber.setEnabled(true);
//                        getfcgname(s.toString().trim());
//                        getguige();
//                    }
//                }
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });
        if ("0".equals(getIntent().getStringExtra("state"))) {
//            llTj.setVisibility(View.GONE);
            llShowTejia.setVisibility(View.GONE);
            if (StringUtil.isValid(getIntent().getStringExtra("guige"))) {
                tvTitle.setText("新增规格");
                getGuige = true;
                llSzgg.setVisibility(View.VISIBLE);
                llTeshu.setVisibility(View.GONE);
                String id = getIntent().getStringExtra("bean");
                setDataView(id);
                llFenleimingcheng.setEnabled(false);
                etMiaoshu.setEnabled(true);
//                etPpming.setEnabled(false);
//                getGuigeName();
            } else {
                tvTitle.setText("添加商品");
                getShow();
            }
        } else {
            tvTitle.setText("编辑商品");
            yemian = "1";
            llFenleimingcheng.setEnabled(false);
            etMiaoshu.setEnabled(true);
//            etPpming.setEnabled(false);
            llGuige.setVisibility(View.GONE);
            String id = getIntent().getStringExtra("bean");
            if(StringUtil.isValid(getIntent().getStringExtra("shsb"))){
                llTeshu.setVisibility(View.VISIBLE);
            } else {
                llTeshu.setVisibility(View.GONE);
            }
            setDataView(id);
        }
        if (goods.equals("1")) {
            istejia = true;
            llShowTejia.setVisibility(View.VISIBLE);
        }
        getZuixiaoGuigeYanzheng();
        ivQingkong.setVisibility(View.GONE);
        etQidingliang1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (StringUtil.isValid(s.toString().trim())) {
                    if (StringUtil.isValid(etKucun.getText().toString().trim())) {
                        if (Double.valueOf(s.toString().trim()) <= Double.valueOf(etKucun.getText().toString().trim())) {
                        } else {
                            ToastUtil.showToastLong("起订量不能超出库存");
                            int num = Integer.valueOf(etKucun.getText().toString().trim()) - 1;
                            etQidingliang1.setText(num + "");
                        }
                    } else {
                        ToastUtil.showToastLong("请先输入库存");
                        etQidingliang1.setText("");
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() > 0) {
                    ivQingkong.setVisibility(View.VISIBLE);
                } else {
                    ivQingkong.setVisibility(View.GONE);
                }
            }
        });
        etKucun.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() > 0) {
                    ivQingkong.setVisibility(View.VISIBLE);
                } else {
                    ivQingkong.setVisibility(View.GONE);
                }
            }
        });

//        etXjguige.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (StringUtil.isValid(s.toString().trim())) {
//                    sanjiguigename = s.toString().trim();
//                    tvGgms.setText(s.toString().trim());
//                    tvYxgg.setText("每" + sanjiguigename + "规格");
//                    tvGgms.setText(sanjiguigename);
//                    tvQdlGg.setText(sanjiguigename);
//                    tvDanwei.setText("元/" + sanjiguigename);
//                    for (FbspGuiGeBean bean : zuixiaoguigelist) {
//                        if (s.toString().trim().equals(bean.getSpec_name())) {
//                            zxid = bean.getSpec_id();
//                            zxname = bean.getSpec_name();
//                            llDw.setVisibility(View.GONE);
//                            isGuige = false;
//                            return;
//                        }
//                    }
//                    isGuige = true;
//                    llDw.setVisibility(View.VISIBLE);
//
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });


//        if(getIntent().getStringExtra(""))
        StringUtil.setPricePoint(etQidingliangdanjia1);
        StringUtil.setPricePoint(etTejia);
        StringUtil.setPricePoint(etNumber);
//        StringUtil.setInputNoEmoj(etXjguige, 10);
//        StringUtil.setInputNoEmoj(etMiaoshu, 20);

        qiNiuPhoto = new QiNiuPhoto(FaBuShangPinActivity.this);
        photoDialog = new PhotoDialog(mContext,
                mContext.getResources().getIdentifier("BottomDialog", "style", mContext.getPackageName()));
        photoDialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.LEFT | Gravity.RIGHT);
        showPopupWindow();
//        if(getGuige){
//            getguigeXzge();
//        } else {
//        }
        if (StringUtil.isValid(getIntent().getStringExtra("shsb"))) {
            llSanjiguige.setEnabled(true);
            etMiaoshu.setEnabled(true);
            llZxgg.setEnabled(true);
            llFenleimingcheng.setEnabled(true);
        }
        getguige();
//        etNumber.setEnabled(true);

//        getZuixiaoGuige();
    }


    //    @OnClick({R.id.iv_back, R.id.iv_sptu, R.id.iv_yiji, R.id.iv_erji, R.id.iv_sanji, R.id.ll_fenleimingcheng, R.id.ll_yijiguige, R.id.ll_erjiguige, R.id.ll_sanjiguige, R.id.bt_xiayibu})
    @OnClick({R.id.iv_back, R.id.iv_qingkong, R.id.tv_zhiman, R.id.ll_tejia, R.id.iv_sptu,
            R.id.ll_fenleimingcheng, R.id.ll_sanjiguige, R.id.ll_guige, R.id.tv_xzgg, R.id.tv_spxz,
            R.id.bt_xiayibu, R.id.ll_teshu, R.id.ll_zxgg, R.id.ll_shangpinmingcheng,
            R.id.ll_gg_cj1, R.id.ll_gg_cj2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_gg_cj1:
                GuigeDialog cj1_dialog = new GuigeDialog().setData(mContext).show(getSupportFragmentManager());
                cj1_dialog.setDqId("", 2);
                break;
            case R.id.ll_gg_cj2:
                GuigeDialog cj2_dialog = new GuigeDialog().setData(mContext).show(getSupportFragmentManager());
                cj2_dialog.setDqId("", 3);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_qingkong:
                etKucun.setText("");
                break;
            case R.id.ll_guige://是否创建规格
                isGgSelect = !isGgSelect;
                ivGuige.setSelected(isGgSelect);
                if (isGgSelect) {
                    sanjiguigeid = "";
                    sanjiguigename = "";
                    tvSanji.setText("");
                } else {
//                    etXjguige.setText("");
                    tvSanji.setText("");
                }
                llDw.setVisibility(View.GONE);
                llXzguige.setVisibility(isGgSelect ? View.GONE : View.VISIBLE);
                llXjguige.setVisibility(isGgSelect ? View.VISIBLE : View.GONE);
                break;
            case R.id.ll_tejia:
                istejia = true;
                tejiadialog = new LianggeXuanXiangDialog(mContext,
                        mContext.getResources().getIdentifier("BottomDialog", "style", mContext.getPackageName()));
                tejiadialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.LEFT | Gravity.RIGHT);
                tejiadialog.showDialog("是", "否");
                tejiadialog.getTvXuanxiang1().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tejiadialog.cancel();
                        istejia = true;
                        tvTejia.setText("是");
                        llShowTejia.setVisibility(View.VISIBLE);
                    }
                });
                tejiadialog.getTvXuanxiang2().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tejiadialog.cancel();
                        istejia = false;
                        tvTejia.setText("否");
                        llShowTejia.setVisibility(View.GONE);
                    }
                });
                break;
            case R.id.tv_zhiman:
                etKucun.setText("9999");
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
                setIntentType();
//                getFeilei(yclId, "2");
                break;
            case R.id.tv_spxz:
//                setIntentType();
//                getFeilei(yclId, "2");
                break;
            case R.id.ll_sanjiguige:
                GuigeDialog dialog = new GuigeDialog().setData(mContext).show(getSupportFragmentManager());
                dialog.setDqId(sanjiguigeid, 1);
                break;
            case R.id.bt_xiayibu:
                qidingliang1 = etQidingliang1.getText().toString().trim();
                qidingliangdanjia1 = etQidingliangdanjia1.getText().toString().trim();
                Log.e("onViewClicked: ", qidingliang1 + "---" + qidingliangdanjia1);
                if (!StringUtil.isValid(etSpming.getText().toString().trim()) && !StringUtil.isValid(sanjiid)) {
                    ToastUtil.showToast("请填写商品名或选择一件商品");
                    return;
                } else if (TextUtils.isEmpty(sanjiguigename)) {
                    ToastUtil.showToast("请选择或创建规格");
                    return;
                } else if (!StringUtil.isValid(shangpintu)) {
                    ToastUtil.showToast("请选择商品图");
                    return;
                }else if (!StringUtil.isValid(etKucun.getText().toString().trim())) {
                    ToastUtil.showToast("请填写库存数量");
                    return;
                } else if (!StringUtil.isValid(qidingliangdanjia1)) {
                    ToastUtil.showToast("请填写商品单价");
                    return;
                } else if (Double.valueOf(qidingliangdanjia1) <= 0) {
                    ToastUtil.showToast("商品单价必须大于0");
                    return;
                } else if (!StringUtil.isValid(qidingliang1)) {
                    ToastUtil.showToast("请填写起订量");
                    return;
                } else if (Integer.valueOf(qidingliang1) == 0 || Integer.valueOf(qidingliang1) > Integer.valueOf(etKucun.getText().toString().trim())) {
                    ToastUtil.showToast("起订量不能为0，且必须小于库存");
                    return;
                } else if (istejia) {
                    if (StringUtil.isValid(etTejia.getText().toString().trim())) {
                        if (Double.valueOf(qidingliangdanjia1) <= Double.valueOf(etTejia.getText().toString().trim())) {
                            ToastUtil.showToast("特价必须小于原价");
                            return;
                        } else if (Double.valueOf(etTejia.getText().toString().trim()) <= 0) {
                            ToastUtil.showToast("特价必须大于0");
                        } else {
                            if (StringUtil.isValid(getIntent().getStringExtra("guige"))) {
                                guigeYanzheng();
                            } else {
                                tiaozhuan();
                            }


                            return;
                        }
                    } else {
                        ToastUtil.showToastLong("特价不可以为空");
                        return;
                    }
                } else {
                    if (StringUtil.isValid(getIntent().getStringExtra("guige"))) {
                        guigeYanzheng();
                    } else {
                        tiaozhuan();
                    }
                }

                break;
            case R.id.ll_teshu:
                isSelect = !isSelect;
                ivTeshu.setSelected(isSelect);
                if (isSelect) {
                    tvSpname.setText("商品名称");
                    tvFenleimingcheng.setText("");
                    tvShangpinmingcheng.setText("");
                    yclId = "";
                    lingjiid = "";
                    yijiid = "";
                    erjiid = "";
                    sanjiid = "";
                    llSanjiguige.setEnabled(true);
                    etMiaoshu.setEnabled(true);
                    llZxgg.setEnabled(true);
                    etNumber.setEnabled(true);
                    llFenleimingcheng.setEnabled(false);
                    llShangpinmingcheng.setEnabled(false);
//                    llGuige.setVisibility(View.VISIBLE);
                    llFl.setVisibility(View.GONE);
                    llSp.setVisibility(View.GONE);
//                    tvGgms.setText("");

                    getguige();
                } else {
                    yclId = "";
                    lingjiid = "";
                    yijiid = "";
                    erjiid = "";
                    sanjiid = "";
                    tvSpname.setText("选择商品");
                    llFenleimingcheng.setEnabled(true);
                    llShangpinmingcheng.setEnabled(true);
                    llFl.setVisibility(View.VISIBLE);
                    llSp.setVisibility(View.VISIBLE);
                    etSpming.setText("");
                    etNumber.setEnabled(false);
                }
                etSpming.setEnabled(isSelect);
                llShangpin.setVisibility(isSelect ? View.VISIBLE : View.GONE);
                break;
            case R.id.ll_zxgg:
//                if (getGuige) {
//                    getZuixiaoGuigeXzgg();
//                } else {
                ZXGuigeDialog zxGuigeDialog = new ZXGuigeDialog().setData(mContext).show(getSupportFragmentManager());
                zxGuigeDialog.setDqId(zxid);
//                }
                break;
            case R.id.ll_shangpinmingcheng:
//                setIntentType();
                break;
            case R.id.tv_xzgg:
                if (rvGuige.getVisibility() == View.VISIBLE) {
                    rvGuige.setVisibility(View.GONE);
                } else {
                    rvGuige.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    private void tiaozhuan() {//传递参数界面
        double shuliang = 1;
        if (llDw.getVisibility()==View.VISIBLE?true:false) {
            if (TextUtils.isEmpty(tvZxgg.getText().toString())) {
                ToastUtil.showToastLong("请选择最小换算单位");
                return;
            } else if (!StringUtil.isValid(etNumber.getText().toString().trim()) || Double.valueOf(etNumber.getText().toString().trim()) == 0) {
                ToastUtil.showToastLong("请先输入最小单位数量并且不能为0");
                return;
            }else {
                shuliang = Double.valueOf(etNumber.getText().toString().trim());
                if(StringUtil.isValid(etNumberCj1.getText().toString().trim())){
                    shuliang = shuliang*Double.valueOf(etNumberCj1.getText().toString().trim());
                }

                if(StringUtil.isValid(etNumberCj2.getText().toString().trim())){
                    shuliang = shuliang*Double.valueOf(etNumberCj2.getText().toString().trim());
                }
                shuliang = MyMath.getDouble(shuliang);
                canShuBean.setSpec_count(shuliang);
                canShuBean.setSpec_detal_id(zxid);
            }
        } else if(llCj2.getVisibility()==View.VISIBLE?true:false){
            if (TextUtils.isEmpty(tvGgCj2.getText().toString())) {
                ToastUtil.showToastLong("请选择最小换算单位");
                return;
            } else if (!StringUtil.isValid(etNumberCj2.getText().toString().trim()) || Double.valueOf(etNumberCj2.getText().toString().trim()) == 0) {
                ToastUtil.showToastLong("请先输入最小单位数量并且不能为0");
                return;
            }else {
                if(StringUtil.isValid(etNumberCj1.getText().toString().trim())){
                    shuliang = shuliang*Double.valueOf(etNumberCj1.getText().toString().trim());
                }

                if(StringUtil.isValid(etNumberCj2.getText().toString().trim())){
                    shuliang = shuliang*Double.valueOf(etNumberCj2.getText().toString().trim());
                }
//                zxid = cj2;
                zxname = tvGgCj2.getText().toString();
                canShuBean.setSpec_count(shuliang);
                canShuBean.setSpec_detal_id(zxid);
            }
        } else if(llCj1.getVisibility()==View.VISIBLE?true:false){
            if (TextUtils.isEmpty(tvGgCj1.getText().toString())) {
                ToastUtil.showToastLong("请选择最小换算单位");
                return;
            } else if (!StringUtil.isValid(etNumberCj1.getText().toString().trim()) || Double.valueOf(etNumberCj1.getText().toString().trim()) == 0) {
                ToastUtil.showToastLong("请先输入最小单位数量并且不能为0");
                return;
            }else {
                if(StringUtil.isValid(etNumberCj1.getText().toString().trim())){
                    shuliang = shuliang*Double.valueOf(etNumberCj1.getText().toString().trim());
                }
//                zxid = cj1;
                zxname = tvGgCj1.getText().toString();
                canShuBean.setSpec_count(shuliang);
                canShuBean.setSpec_detal_id(zxid);
            }
        } else {
            canShuBean.setSpec_count(0.0);
            canShuBean.setSpec_detal_id("");
        }
        canShuBean.setSpec_describe(etMiaoshu.getText().toString().trim());
        canShuBean.setSpec_detal_name(sanjiguigename);
        canShuBean.setCommodity_name(etSpming.getText().toString().trim());
        canShuBean.setType_one_id(lingjiid);
        canShuBean.setType_two_id(yijiid);
        canShuBean.setType_tree_id(erjiid);
        canShuBean.setHostPicture(shangpintu);
        canShuBean.setCommodity_state("0");
        canShuBean.setPack_standard_tree(sanjiguigeid);
        canShuBean.setGoods(goods);
        canShuBean.setBrand(etPpming.getText().toString().trim());
        if (istejia) {
            canShuBean.setGoods("1");
            canShuBean.setPrice(etTejia.getText().toString().trim());
        }
        canShuBean.setType_four_id(sanjiid);
        canShuBean.setPack_standard_tree_name(sanjiguigename);
        canShuBean.setSpec_detal_name(zxname);
        canShuBean.setInventory(etKucun.getText().toString().trim());
        canShuBean.setRation_one(qidingliang1);
        canShuBean.setPice_one(qidingliangdanjia1);
        canShuBean.setClassify_name(spname);
        if (canShuBean.getGoods().equals("1")) {
            canShuBean.setPrice(etTejia.getText().toString().trim());
        }
        Intent intent = new Intent(mContext, FaBuShangPinXiangQingTuActivity.class);
        intent.putExtra("canshu", gson.toJson(canShuBean));
        Log.e("tiaozhuan: ", gson.toJson(canShuBean));
        intent.putExtra("yemian", yemian);
        if (StringUtil.isValid(getIntent().getStringExtra("guige"))) {
            intent.putExtra("guige", "1");
        }
        intent.putExtra("pipei", pipei);
        startActivity(intent);
    }


    private void getguige() {
        Log.e("getguige: ", sanjiid + "---");
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getguige(PreferenceUtils.getString(MyApplication.mContext, "token", ""), sanjiname))
                .setDataListener(new HttpDataListener<List<FbspGuiGeBean>>() {

                    @Override
                    public void onNext(List<FbspGuiGeBean> data) {
                        if (StringUtil.isValid(sanjiid) && yemian.equals("1")) {
                            llZxgg.setEnabled(false);
                            llSanjiguige.setEnabled(false);
                            etMiaoshu.setEnabled(false);
                            if (!isSelect) {
                                etNumber.setEnabled(false);
                            }

                        }
                        if (StringUtil.isValid(getIntent().getStringExtra("shsb"))) {
                            llSanjiguige.setEnabled(true);
                            etMiaoshu.setEnabled(true);
                            llZxgg.setEnabled(true);
                            etNumber.setEnabled(true);
                        }
                        sanjiguige = new ArrayList<FbspGuiGeBean>();

                        for (int i = 0; i < data.size(); i++) {
                            sanjiguige.add(data.get(i));
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
        if (resultCode == 4) {
            if (requestCode == REQUEST_CODE) {
                if (StringUtil.isValid(data.getStringExtra("name"))) {
                    tvFenleimingcheng.setText(data.getStringExtra("name"));
//                    etSpming.setEnabled(true);
//                    etSpming.setText(data.getStringExtra("spname"));
                    tvShangpinmingcheng.setText(data.getStringExtra("spname"));
                    lingjiid = data.getStringExtra("one_id");
                    yclId = data.getStringExtra("one_id");
                    yijiid = data.getStringExtra("two_id");
                    erjiid = data.getStringExtra("three_id");
                    sanjiid = data.getStringExtra("five_id");
                    zxid = data.getStringExtra("zxId");
                    zxname = data.getStringExtra("zxName");
                    llGuige.setVisibility(View.GONE);
                    llXjguige.setVisibility(View.GONE);
                    llXzguige.setVisibility(View.VISIBLE);
                    if (!isSelect) {
                        etNumber.setText(data.getStringExtra("zxNumber"));
                    }
                    if (StringUtil.isValid(getIntent().getStringExtra("guige"))||StringUtil.isValid(data.getStringExtra("guige"))) {
                        tvSanji.setEnabled(true);
                        llZxgg.setEnabled(true);
                        etNumber.setEnabled(true);
                        llSanjiguige.setEnabled(true);
                    } else {
                        tvSanji.setEnabled(false);
                        llZxgg.setEnabled(false);
                        etNumber.setEnabled(false);
                        llSanjiguige.setEnabled(false);
                        etMiaoshu.setEnabled(false);
//                        etQidingliangdanjia1.setEnabled(false);
                        llZxgg.setEnabled(false);
                    }
//                    if (StringUtil.isValid(getIntent().getStringExtra("shsb"))) {
//                        llSanjiguige.setEnabled(true);
//                        etMiaoshu.setEnabled(true);
//                        llZxgg.setEnabled(true);
//                    }
                    sanjiguigename = data.getStringExtra("guigeName");
                    tvSanji.setText(sanjiguigename);
                    etMiaoshu.setText(data.getStringExtra("guigeMiaoshu"));
                    sanjiguigeid = data.getStringExtra("guigeId");

                    tvZxgg.setText(data.getStringExtra("zxName"));
                    tvYxgg.setText("每" + sanjiguigename + "规格");
                    tvGgms.setText(sanjiguigename);
                    tvQdlGg.setText(sanjiguigename);
                    tvDanwei.setText("元/" + sanjiguigename);
                    tvTjDanwei.setText("元/" + sanjiguigename);
                    if (StringUtil.isValid(data.getStringExtra("zxId"))) {
                        llDw.setVisibility(View.VISIBLE);
                        isGuige = true;
                    } else {
                        llDw.setVisibility(View.GONE);
                        isGuige = false;
                    }
                    Log.e("onActivityResult: ", zxid + "---aaa");
//                    getguige();
                } else {
                    if(StringUtil.isValid(getIntent().getStringExtra("shsb"))){
                        isSelect = true;
                        ivTeshu.setSelected(isSelect);
                        tvSpname.setText("商品名称");
                        tvFenleimingcheng.setText("");
                        tvShangpinmingcheng.setText("");
                        yclId = "";
                        lingjiid = "";
                        yijiid = "";
                        erjiid = "";
                        sanjiid = "";
                        llSanjiguige.setEnabled(true);
                        etMiaoshu.setEnabled(true);
                        llZxgg.setEnabled(true);
                        etNumber.setEnabled(true);
                        llFenleimingcheng.setEnabled(false);
                        llShangpinmingcheng.setEnabled(false);
//                    llGuige.setVisibility(View.VISIBLE);
                        llFl.setVisibility(View.GONE);
                        llSp.setVisibility(View.GONE);
                        etSpming.setEnabled(isSelect);
                        llShangpin.setVisibility(isSelect ? View.VISIBLE : View.GONE);
//                    tvGgms.setText("");

                        getguige();
                    }
                }
            }
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
                        final String qiniudata = qiNiuPhoto.getImageAbsolutePath(FaBuShangPinActivity.this, outputUri);
                        final String key = null;
                        final String token = list;
                        File file = StringUtil.luban(mContext, qiniudata);
                        if (StringUtil.isValid(file.getPath())) {
                            bitmap = BitmapFactory.decodeFile(file.getPath());
                            String mydata = qiNiuPhoto.getImageAbsolutePath(FaBuShangPinActivity.this, Uri.parse(file.getPath()));
                            MyApplication.uploadManager.put(qiniudata, key, token,
                                    new UpCompletionHandler() {
                                        @Override
                                        public void complete(String key, ResponseInfo info, JSONObject res) {
                                            //res包含hash、key等信息，具体字段取决于上传策略的设置
                                            if (info.isOK()) {
//                                            getImageAbsolutePath(CTDWanShanXinXiActivity.this,outputUri)
                                                try {
                                                    shangpintu = res.getString("key");
                                                    customDialog.dismiss();
                                                    Log.e("complete: ", shangpintu);
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            } else {
                                                //如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因
                                            }
                                        }
                                    }, null);
//                            Log.e("onNext: ",bitmap+"" );
                            GlideUtils.cachePhoto(mContext, ivSptu, file.getPath());
//                            ivSptu.setImageBitmap(bitmap);
                        }

                    }
//                                });
//                    }
                }, false);
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


    public void setDataView(String id) {//编辑页面展示
        pipei = "1";
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .editorShangPin(PreferenceUtils.getString(MyApplication.mContext, "token", ""), id))
                .setDataListener(new HttpDataListener<EditorShangPinBean>() {
                    @Override
                    public void onNext(EditorShangPinBean data) {
                        final EditorShangPinBean.XqBean bean = data.getXq();
                        PreferenceUtils.setEditorShangPinBean(MyApplication.mContext, data);
                        if (StringUtil.isValid(bean.getHostPicture())) {
                            GlideUtils.cachePhoto(FaBuShangPinActivity.this, ivSptu, bean.getHostPicture());
                            shangpintu = bean.getHostPicture();
                        }
                        ming = bean.getClassify_name() + "";
//                        if (StringUtil.isValid(getIntent().getStringExtra("shsb"))) {//审核失败编辑
                        if (StringUtil.isValid(bean.getType_tree_id())) {
                            tvFenleimingcheng.setText(bean.getType_one_name());
                            llShangpin.setVisibility(View.GONE);
//                            llTeshu.setVisibility(View.GONE);
                            llFl.setVisibility(View.VISIBLE);
                            llSp.setVisibility(View.VISIBLE);
                            isSelect = false;
                            ivTeshu.setSelected(false);
                            etSpming.setText(bean.getClassify_name());
                            spname = bean.getClassify_name() + "";
                            tvShangpinmingcheng.setText(bean.getClassify_name() + "");
                        } else {
                            spname = bean.getCommodity_name() + "";
                            etSpming.setText(bean.getCommodity_name());
                            llFl.setVisibility(View.GONE);
                            llSp.setVisibility(View.GONE);
                            llShangpin.setVisibility(View.VISIBLE);
//                            llTeshu.setVisibility(View.VISIBLE);
                            isSelect = true;
                            ivTeshu.setSelected(true);
                        }
//                        }
                        if (bean.getPice() > 0) {
                            llShowTejia.setVisibility(View.VISIBLE);
                            istejia = true;
                            etTejia.setText(bean.getPice() + "");
                        }

                        sanjikexuan = true;
                        sanjiguigeid = bean.getPack_standard_tree();
                        sanjiguigename = bean.getPackThreeName();
                        tvDanwei.setText("元/" + sanjiguigename);
                        tvTjDanwei.setText("元/" + sanjiguigename);
                        tvQdlGg.setText(sanjiguigename);
                        lingjiid = bean.getType_one_id();
                        yijiid = bean.getType_two_id();
                        erjiid = bean.getType_tree_id();
//                        spname = bean.getClassify_name();
                        sanjiid = bean.getType_four_id();
                        ivQingkong.setVisibility(View.VISIBLE);
                        if (yemian.equals("0")) {
                            getGuigeName();
                        }
                        if (StringUtil.isValid(bean.getBrand())) {
                            etPpming.setText(bean.getBrand());
                        }
                        if (!StringUtil.isValid(getIntent().getStringExtra("guige"))) {
                            etKucun.setText(bean.getInventory());
                            etQidingliang1.setText(bean.getRation_one());
                            if (bean.getPice_one() != 0) {
                                etQidingliangdanjia1.setText(bean.getPice_one() + "");
                            }
                        }

                        etSpming.setEnabled(true);
                        zxid = bean.getAffiliated_spec();
                        zxname = bean.getPackFourName();
                        if (StringUtil.isValid(bean.getAffiliated_number()) && Double.valueOf(bean.getAffiliated_number()) != 0) {
                            llDw.setVisibility(View.VISIBLE);
//                            if(!isSelect){
                            etNumber.setText(bean.getAffiliated_number());
//                            }
                            tvZxgg.setText(bean.getPackFourName());
                            tvYxgg.setText("每" + bean.getPackThreeName() + "规格");
                            tvGgms.setText(bean.getPackThreeName());
                            isGuige = true;
                        } else {
                            llDw.setVisibility(View.GONE);
                            isGuige = false;
                        }
                        etMiaoshu.setText(bean.getSpec_describe());
                        tvSanji.setText(bean.getPackThreeName());
//                        etMiaoshu.setText(bean.getSpec_describe());
                        tvGgms.setText(bean.getPackThreeName());
                        tvQdlGg.setText(bean.getPackThreeName());
                        tvDanwei.setText(bean.getPackThreeName());
                        tvTjDanwei.setText(bean.getPackThreeName());
                        canShuBean.setClassify_name(bean.getClassify_name());
                        etTejia.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if(StringUtil.isValid(s.toString().trim())){
                                    if (Double.valueOf(s.toString().trim()) >= Double.valueOf(etQidingliangdanjia1.getText().toString().trim())) {
                                        ToastUtil.showToastLong("特价金额必须小于原价");
                                        etTejia.setText("0");
                                    }
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });

                    }
                });
    }

    private void getfcgname(final String name) {
        Log.e("getfcgname: ", PreferenceUtils.getString(MyApplication.mContext, "token", "") + "---" + erjiid + "---" + name);
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .searchSpname(PreferenceUtils.getString(MyApplication.mContext, "token", ""), erjiid, name))
                .setDataListener(new HttpDataListener<List<ShangPinSousuoMohuBean>>() {
                    @Override
                    public void onNext(List<ShangPinSousuoMohuBean> data) {
                        int mysize = data == null ? 0 : data.size();
                        if (mPopWindow != null) {
//                                if(mysize!=0){
                            datas.clear();
                            datas.addAll(data);
                            Log.e("data", data + "111111111");
                            mPopWindow.showAsDropDown(etSpming);
                            mohuAdapter.notifyDataSetChanged();
//                                }
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
                                .getFeiLei(PreferenceUtils.getString(MyApplication.mContext, "token", ""), id, level))
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
                                if (level.equals("1") && mysize != 0) {
                                    lingjiming = item.getClassify_name();
                                    lingjiid = item.getClassify_id() + "";
                                    tvFenleimingcheng.setText(lingjiming);
                                    picker.dismiss();
                                    getFeilei(lingjiid, "2");
                                } else if (level.equals("2") && mysize != 0) {
                                    yijiming = item.getClassify_name();
                                    yijiid = item.getClassify_id();
//                                    tvFenleimingcheng.setText(lingjiming + "-" + yijiming);
                                    tvFenleimingcheng.setText(yijiming);
                                    picker.dismiss();
                                    getFeilei(yijiid, "3");
                                } else if (level.equals("3") && mysize != 0) {
                                    erjiname = item.getClassify_name();
                                    erjiid = item.getClassify_id();
//                                    tvFenleimingcheng.setText(lingjiming + "-" + yijiming + "-" + erjiname);
                                    tvFenleimingcheng.setText(yijiming + "-" + erjiname);
                                    picker.dismiss();
//                                    getFeilei(erjiid,"4");
                                    etSpming.setEnabled(true);
                                    getguige();
                                } else if (level.equals("4") && mysize != 0) {
                                    sanjiname = item.getClassify_name();
                                    sanjiid = item.getClassify_id();
//                                    tvFenleimingcheng.setText(lingjiming + "-" + yijiming + "-" + erjiname + "-" + sanjiname);
                                    tvFenleimingcheng.setText(yijiming + "-" + erjiname + "-" + sanjiname);
                                    picker.dismiss();
//                                    getFeilei(sanjiid,"4");
                                }

                            }
                        });
                        picker.show();
                    }
                }, false);
    }


    private void setIntentType() {
        Intent it = new Intent();
        it.setClass(mContext, XJSPFeiLeiXuanZeActivity.class);
        it.putExtra("teshu", "fabu");
        it.putExtra("state", yemian);
        it.putExtra("goods", goods);
        it.putExtra("shsb",getIntent().getStringExtra("shsb"));
        startActivityForResult(it, REQUEST_CODE);
    }

    private void guigeYanzheng() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .guigeYanzheng(PreferenceUtils.getString(MyApplication.mContext, "token", "")
                                        , sanjiid, etQidingliangdanjia1.getText().toString().trim(),
                                        etQidingliang1.getText().toString().trim(),
                                        sanjiguigename, etNumber.getText().toString().trim(),
                                        zxname))
                .setDataListener(new HttpDataListener<String>() {

                    @Override
                    public void onNext(String data) {
                        tiaozhuan();
                    }
                });
    }



    private void getZuixiaoGuigeYanzheng() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getZxgg(PreferenceUtils.getString(MyApplication.mContext, "token", ""), ""))
                .setDataListener(new HttpDataListener<List<FbspGuiGeBean>>() {

                    @Override
                    public void onNext(List<FbspGuiGeBean> data) {
                        int mysize = data == null ? 0 : data.size();
                        zuixiaoguigelist.clear();
                        if (mysize != 0) {
                            zuixiaoguigelist.addAll(data);
                        }
                    }
                });
    }

    private void getGuigeName() {
        Log.e("getGuigeName: ", erjiid + "----" + spname);
        guigedatas.clear();
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .searchSpname(PreferenceUtils.getString(MyApplication.mContext, "token", ""), erjiid, spname))
                .setDataListener(new HttpDataListener<List<ShangPinSousuoMohuBean>>() {
                    @Override
                    public void onNext(List<ShangPinSousuoMohuBean> data) {
                        Log.e("onNext:data= ", new Gson().toJson(data));
                        int mysize = data == null ? 0 : data.size();
                        if (mysize != 0) {
                            guigedatas.addAll(data);
                        }
                        guigeadapter = new XJSPFeiLeiGuigeAdapter(mContext, guigedatas);
                        rvGuige.setLayoutManager(new GridLayoutManager(mContext, 2));
                        rvGuige.setAdapter(guigeadapter);
                        guigeadapter.setCallBack(new XJSPFeiLeiGuigeAdapter.CallBack() {
                            @Override
                            public void xuanzhong(final ShangPinSousuoMohuBean msg) {
                                guigeadapter.setXuanzhongid(msg.getClassify_id());
                                tvZxgg.setText(msg.getAffiliated_spec_name());
                                tvSanji.setText(msg.getSpec_name());
                                Log.e( "xuanzhong: ", msg.getSpec_describe());
                                tvGgms.setText(msg.getSpec_name());
//                                if(!isSelect){
                                etNumber.setText(msg.getAffiliated_number());
//                                }
//                                etNumber.setText(msg.getAffiliated_number());
                                sanjiguigeid = msg.getSpec_idFour();
                                zxid = msg.getAffiliated_spec();
                                zxname = msg.getAffiliated_spec_name();
                                sanjiguigename = msg.getSpec_name();
                                tvDanwei.setText("元/" + sanjiguigename);
                                tvTjDanwei.setText("元/" + sanjiguigename);
                                tvQdlGg.setText(sanjiguigename);
                                if (StringUtil.isValid(msg.getAffiliated_number())) {
                                    zxname = msg.getAffiliated_spec_name();
                                    llDw.setVisibility(View.VISIBLE);
                                    isGuige = true;
                                } else {
                                    llDw.setVisibility(View.GONE);
                                    isGuige = false;
                                }
                                etMiaoshu.setText(msg.getSpec_describe());
                                guigeadapter.notifyDataSetChanged();
                            }
                        });
//                        guigeadapter.notifyDataSetChanged();
                    }
                }, false);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getGuige(FbspGuiGeBean item) {
        if (StringUtil.isValid(item.getType())) {
            if(item.getSpec_id().equals(sanjiguigeid)||item.getSpec_id().equals(cj1)||item.getSpec_id().equals(cj2)){
                ToastUtil.showToastLong("规格重复");
                return;
            }
            zxid = item.getSpec_id() + "";
            zxname = item.getSpec_name() + "";
            tvZxgg.setText(item.getSpec_name() + "");
            setEtMiaoshuText();
        } else {
            if (item.getLevel() == 1) {
                sanjiguigename = item.getSpec_name();
                tvDanwei.setText("元/" + sanjiguigename);
                tvTjDanwei.setText("元/" + sanjiguigename);
                tvQdlGg.setText(sanjiguigename);
                sanjiguigeid = item.getSpec_id() + "";
                tvSanji.setText(sanjiguigename);
                etMiaoshu.setText(item.getSpec_describe());
                tvCj1.setText("每" + item.getSpec_name() + "规格");
                tvGgmsCj1.setText(item.getSpec_name());
                tvGgCj1.setText("");
                etNumberCj1.setText("");
            } else if (item.getLevel() == 2) {
                if(item.getSpec_id().equals(sanjiguigeid)){
                    ToastUtil.showToastLong("规格重复");
                    return;
                }
                tvCj2.setText("每" + item.getSpec_name() + "规格");
                tvGgCj1.setText(item.getSpec_name());
                tvGgmsCj2.setText(item.getSpec_name());
                cj1 = item.getSpec_id();
                tvGgCj2.setText("");
                etNumberCj2.setText("");
            } else {
                if(item.getSpec_id().equals(sanjiguigeid)||item.getSpec_id().equals(cj1)){
                    ToastUtil.showToastLong("规格重复");
                    return;
                }
                etNumber.setEnabled(true);
                tvGgCj2.setText(item.getSpec_name());
                cj2 = item.getSpec_id();
                tvYxgg.setText("每" + item.getSpec_name() + "规格");
                tvGgms.setText(item.getSpec_name());
                tvZxgg.setText("");
                etNumber.setText("");
            }
            setEtMiaoshuText();

            for (FbspGuiGeBean bean : zuixiaoguigelist) {
                if (item.getSpec_name().equals(bean.getSpec_name())) {
                    if (item.getLevel() == 1) {
                        clearShow();
                    } else if (item.getLevel() == 2) {
                        clearShow();
                        llCj1.setVisibility(View.VISIBLE);
                    } else {
                        clearShow();
                        llCj1.setVisibility(View.VISIBLE);
                        llCj2.setVisibility(View.VISIBLE);
                    }
                    Log.e("getGuige:zxid ",bean.getSpec_id() );
                    zxid = bean.getSpec_id();
                    setEtMiaoshuText();
                    return;
                }
            }
            Log.e("getGuige: ", "我的等级啊" + item.getLevel());
            if (item.getLevel() == 1) {
                clearShow();
                llCj1.setVisibility(View.VISIBLE);
            } else if (item.getLevel() == 2) {
                clearShow();
                llCj1.setVisibility(View.VISIBLE);
                llCj2.setVisibility(View.VISIBLE);
            } else {
                clearShow();
                llCj1.setVisibility(View.VISIBLE);
                llCj2.setVisibility(View.VISIBLE);
                llDw.setVisibility(View.VISIBLE);
            }
            setEtMiaoshuText();
//            if (StringUtil.isValid(item.getAffiliated_number())) {
//                llDw.setVisibility(View.VISIBLE);
//                isGuige = true;
//                zxid = item.getAffiliated_spec();
//                if (!isSelect) {
//                    etNumber.setText(item.getAffiliated_number());
//                }
////                etNumber.setText(item.getAffiliated_number());
//                zxname = item.getAffiliated_spec_name();
//                tvZxgg.setText(item.getAffiliated_spec_name());
//            } else {
//                llDw.setVisibility(View.GONE);
//                isGuige = false;
//            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getGuige(CreateGuigeBean bean) {
        if (bean.getLevel() == 1) {
            sanjiguigename = bean.getName();
            sanjiguigeid = "";
            tvDanwei.setText("元/" + sanjiguigename);
            tvTjDanwei.setText("元/" + sanjiguigename);
            tvQdlGg.setText(sanjiguigename);
            tvSanji.setText(sanjiguigename);
            tvCj1.setText("每" + bean.getName() + "规格");
            tvGgmsCj1.setText(bean.getName());
            tvGgCj1.setText("");
            etNumberCj1.setText("");
        } else if (bean.getLevel() == 2) {
            if(bean.getName().equals(sanjiguigename)){
                ToastUtil.showToastLong("规格重复");
                return;
            }
            tvCj2.setText("每" + bean.getName() + "规格");
            tvGgCj1.setText(bean.getName());
            tvGgmsCj2.setText(bean.getName());
            tvGgCj2.setText("");
            etNumberCj2.setText("");
        } else if (bean.getLevel() == 3) {
            if(bean.getName().equals(sanjiguigename)||bean.getName().equals(tvGgCj1.getText().toString())){
                ToastUtil.showToastLong("规格重复");
                return;
            }
            etNumber.setEnabled(true);
            tvGgCj2.setText(bean.getName());
            tvYxgg.setText("每" + bean.getName() + "规格");
            tvGgms.setText(bean.getName());
            tvZxgg.setText("");
            etNumber.setText("");
        }

        setEtMiaoshuText();
        for (FbspGuiGeBean ggBean : zuixiaoguigelist) {
            if (bean.getName().equals(ggBean.getSpec_name())) {
                if (bean.getLevel() == 1) {
                    clearShow();
                } else if (bean.getLevel() == 2) {
                    clearShow();
                    llCj1.setVisibility(View.VISIBLE);
                } else {
                    clearShow();
                    llCj1.setVisibility(View.VISIBLE);
                    llCj2.setVisibility(View.VISIBLE);
                }
                zxid = ggBean.getSpec_id();
                setEtMiaoshuText();
                return;
            }
        }
        Log.e("getGuige: ", "我的等级啊" + bean.getLevel());
        if (bean.getLevel() == 1) {
            clearShow();
            llCj1.setVisibility(View.VISIBLE);
        } else if (bean.getLevel() == 2) {
            clearShow();
            llCj1.setVisibility(View.VISIBLE);
            llCj2.setVisibility(View.VISIBLE);
        } else {
            clearShow();
            llCj1.setVisibility(View.VISIBLE);
            llCj2.setVisibility(View.VISIBLE);
            llDw.setVisibility(View.VISIBLE);
        }
        setEtMiaoshuText();
    }

    private void clearShow() {
        llCj2.setVisibility(View.GONE);
        llDw.setVisibility(View.GONE);
        llCj1.setVisibility(View.GONE);
    }

    //    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void getSelect(boolean xuanze) {
//        isSelect = xuanze;
//        ivTeshu.setSelected(isSelect);
//        if (isSelect) {
//            tvSpname.setText("添加商品");
//            tvFenleimingcheng.setText("");
//            tvShangpinmingcheng.setText("");
//            yclId = "";
//            lingjiid = "";
//            yijiid = "";
//            erjiid = "";
//            sanjiid = "";
//            llSanjiguige.setEnabled(true);
//            etMiaoshu.setEnabled(true);
//            llZxgg.setEnabled(true);
//            etNumber.setEnabled(true);
//            llFenleimingcheng.setEnabled(false);
//            llShangpinmingcheng.setEnabled(false);
//            llGuige.setVisibility(View.VISIBLE);
//            llFl.setVisibility(View.GONE);
//            llSp.setVisibility(View.GONE);
////                    tvGgms.setText("");
//            getguige();
//        } else {
//            yclId = "";
//            lingjiid = "";
//            yijiid = "";
//            erjiid = "";
//            sanjiid = "";
//            tvSpname.setText("选择商品");
//            llFenleimingcheng.setEnabled(true);
//            llShangpinmingcheng.setEnabled(true);
//            llFl.setVisibility(View.VISIBLE);
//            llSp.setVisibility(View.VISIBLE);
//            etSpming.setText("");
//        }
//        etSpming.setEnabled(isSelect);
//        llShangpin.setVisibility(isSelect ? View.VISIBLE : View.GONE);
//    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void getShow() {
        if (StringUtil.isValid(getIntent().getStringExtra("name"))) {
            tvFenleimingcheng.setText(getIntent().getStringExtra("name"));
//                    etSpming.setEnabled(true);
//                    etSpming.setText(data.getStringExtra("spname"));
            tvShangpinmingcheng.setText(getIntent().getStringExtra("spname"));
            lingjiid = getIntent().getStringExtra("one_id");
            yclId = getIntent().getStringExtra("one_id");
            yijiid = getIntent().getStringExtra("two_id");
            erjiid = getIntent().getStringExtra("three_id");
            sanjiid = getIntent().getStringExtra("five_id");
            zxid = getIntent().getStringExtra("zxId");
            zxname = getIntent().getStringExtra("zxName");
            llGuige.setVisibility(View.GONE);
            llXjguige.setVisibility(View.GONE);
            llXzguige.setVisibility(View.VISIBLE);
//            if (!isSelect) {
            Log.e("getShow: ", getIntent().getStringExtra("zxNumber"));
//            }
            if (StringUtil.isValid(getIntent().getStringExtra("guige"))) {
                tvSanji.setEnabled(true);
                llZxgg.setEnabled(true);
                etNumber.setEnabled(true);
                llSanjiguige.setEnabled(true);
            } else {
                tvSanji.setEnabled(false);
                llZxgg.setEnabled(false);
                etNumber.setEnabled(false);
                llSanjiguige.setEnabled(false);
                etMiaoshu.setEnabled(false);
//                        etQidingliangdanjia1.setEnabled(false);
                llZxgg.setEnabled(false);
            }
            if (StringUtil.isValid(getIntent().getStringExtra("shsb"))) {
//                llSanjiguige.setEnabled(true);
//                etMiaoshu.setEnabled(true);
//                llZxgg.setEnabled(true);
                llFenleimingcheng.setEnabled(true);
                llShangpinmingcheng.setEnabled(true);
            }
            sanjiguigename = getIntent().getStringExtra("guigeName");
            tvSanji.setText(sanjiguigename);
            etMiaoshu.setText(getIntent().getStringExtra("guigeMiaoshu"));
            sanjiguigeid = getIntent().getStringExtra("guigeId");

            tvZxgg.setText(getIntent().getStringExtra("zxName"));
            tvYxgg.setText("每" + sanjiguigename + "规格");
            tvGgms.setText(sanjiguigename);
            tvQdlGg.setText(sanjiguigename);
            tvDanwei.setText("元/" + sanjiguigename);
            tvTjDanwei.setText("元/" + sanjiguigename);
            if (StringUtil.isValid(getIntent().getStringExtra("zxId"))) {
                llDw.setVisibility(View.VISIBLE);
                isGuige = true;
            } else {
                llDw.setVisibility(View.GONE);
                isGuige = false;
            }
            etNumber.setText(getIntent().getStringExtra("zxNumber"));
//                    getguige();
        } else {
            if (StringUtil.isValid(getIntent().getStringExtra("sr_name"))) {
                etSpming.setText(getIntent().getStringExtra("sr_name"));
            }
            isSelect = true;
            tvSpname.setText("商品名称");
            tvFenleimingcheng.setText("");
            tvShangpinmingcheng.setText("");
            yclId = "";
            lingjiid = "";
            yijiid = "";
            erjiid = "";
            sanjiid = "";
            llSanjiguige.setEnabled(true);
            etMiaoshu.setEnabled(true);
            llZxgg.setEnabled(true);
            etNumber.setEnabled(true);
            if (StringUtil.isValid(getIntent().getStringExtra("shsb"))) {
                llFenleimingcheng.setEnabled(true);
                llShangpinmingcheng.setEnabled(true);
            } else {
                llFenleimingcheng.setEnabled(false);
                llShangpinmingcheng.setEnabled(false);
            }
//            llGuige.setVisibility(View.VISIBLE);
            llFl.setVisibility(View.GONE);
            llSp.setVisibility(View.GONE);
//          tvGgms.setText("");
//            getguige();
            etSpming.setEnabled(isSelect);
            llShangpin.setVisibility(isSelect ? View.VISIBLE : View.GONE);
        }
    }

    private void setEtMiaoshuText() {
        etMap.clear();
        String ggms = "";
        if (llDw.getVisibility() == View.VISIBLE ? true : false) {
            etMap.put(tvZxgg.getText().toString(), etNumber);
            etMap.put(tvGgCj2.getText().toString(), etNumberCj2);
            etMap.put(tvGgCj1.getText().toString(), etNumberCj1);
        }else if (llCj2.getVisibility() == View.VISIBLE ? true : false) {
            etMap.put(tvGgCj2.getText().toString(), etNumberCj2);
            etMap.put(tvGgCj1.getText().toString(), etNumberCj1);
            etNumber.setText("");
            tvZxgg.setText("");
        } else if (llCj1.getVisibility() == View.VISIBLE ? true : false) {
            etMap.put(tvGgCj1.getText().toString(), etNumberCj1);
            etNumberCj2.setText("");
            tvGgCj2.setText("");
            etNumber.setText("");
            tvZxgg.setText("");
        }  else {
            etNumberCj1.setText("");
            tvGgCj1.setText("");
            etNumberCj2.setText("");
            tvGgCj2.setText("");
            etNumber.setText("");
            tvZxgg.setText("");
        }
        Set<Map.Entry<String, EditText>> entrys = etMap.entrySet();
        for (Map.Entry<String, EditText> et : entrys) {
            if (StringUtil.isValid(et.getKey().toString())&&StringUtil.isValid(et.getValue().getText().toString())) {
                ggms += et.getValue().getText().toString() + et.getKey().toString() + "*";
            }
        }
        if(ggms.length()!=0){
            ggms = ggms.substring(0,ggms.length()-1);
            etMiaoshu.setText(ggms+"/"+sanjiguigename);
        } else {
            etMiaoshu.setText(sanjiguigename);
        }
    }

}
