package com.mingmen.mayi.mayibanjia.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.AddressListBean;
import com.mingmen.mayi.mayibanjia.bean.ProvinceBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.SinglePicker;

/**
 * Created by Administrator on 2018/7/18/018.
 */
//添加收货地址
public class AddAddressActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_shouhuoren)
    EditText etShouhuoren;
    @BindView(R.id.et_dianhua)
    EditText etDianhua;
    @BindView(R.id.ll_dianhuabu)
    LinearLayout llDianhuabu;
    @BindView(R.id.tv_suozaidiqu)
    TextView tvSuozaidiqu;
    @BindView(R.id.et_xiangxidizhi)
    EditText etXiangxidizhi;
    @BindView(R.id.switch_morendizhi)
    Switch switchMorendizhi;
    @BindView(R.id.tv_moren)
    TextView tvMoren;
    private String rukou;
    private Context mContext;
    private ArrayList<ProvinceBean> zonglist;
    private ArrayList<ProvinceBean> shenglist;
    private String shengming;
    private String shengid;
    private ArrayList<ProvinceBean> shilist;
    private String shiming;
    private String shiid;
    private String quid;
    private ArrayList<ProvinceBean> qulist;
    private int moren=1;
    private String phoneName="";
    private String phonenumber="";
    private String xiangxidizhi="";
    private String quming;
    private ArrayList<ProvinceBean> jielist;
    private String jieming;
    private String jieid;
    private String addressid;

    @Override
    public int getLayoutId() {
        return R.layout.activity_addaddress;
    }

    @Override
    protected void initData() {
        tvTitle.setText("添加收货地址");
        tvRight.setText("保存");
        rukou = getIntent().getStringExtra("rukou");
        mContext=AddAddressActivity.this;
        //判断入口
        if ("add".equals(rukou)){

        }else{
            //入口为修改
            String json = getIntent().getStringExtra("json");
            AddressListBean editdata = gson.fromJson(json, AddressListBean.class);
            Log.e("json",json);
//            回显
            addressid=editdata.getAddress_id();
            phoneName=editdata.getLinkman();
            phonenumber=editdata.getContact_type();
            shengid=editdata.getProvince();
            shiid=editdata.getCity();
            quid=editdata.getRegion();
            jieid=editdata.getStreet();
            shengming=editdata.getProvince_name();
            shiming=editdata.getCity_name();
            quming=editdata.getRegion_name();
            jieming=editdata.getStreet_name();
            Log.e("jieid",jieid);

            xiangxidizhi=editdata.getSpecific_address();
            moren= Integer.parseInt(editdata.getDefault_address());
            etShouhuoren.setText(phoneName);
            etDianhua.setText(phonenumber);
            etXiangxidizhi.setText(xiangxidizhi);
            tvSuozaidiqu.setText(shengming+shiming+quming+jieming);
            //是否默认
            if (moren==0){
                switchMorendizhi.setChecked(true);
            }else{
                switchMorendizhi.setChecked(false);
            }
        }

        switchMorendizhi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    moren=0;
                }else{
                    moren=1;
                }

            }
        });
    }


    @OnClick({R.id.iv_back, R.id.tv_right, R.id.ll_dianhuabu, R.id.tv_suozaidiqu,R.id.switch_morendizhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                //                ,,shengid,shiid,quid,etXiangxidizhi.getText().toString().trim(),isMoRen
//                联系人，电话号，省id，市id，区id，详细地址，是否默认
                xiangxidizhi=etXiangxidizhi.getText().toString().trim();
                phoneName=etShouhuoren.getText().toString().trim();
                phonenumber=etDianhua.getText().toString().trim();
                //验证信息是否有空
                if (!"".equals(phoneName)&!"".equals(phonenumber)&!"".equals(shengming)&!"".equals(shiming)&!"".equals(quming)&!"".equals(xiangxidizhi)){
                    if ("add".equals(rukou)){
                        addAddress();
                    }else{
                        editAddress();
                    }
                }else{
                    ToastUtil.showToast("请确认信息填写完整后再提交");
                    Log.e("phonename",phoneName);
                    Log.e("phonenumber",phonenumber);
                    Log.e("shengming",shengming);
                    Log.e("shiming",shiming);
                    Log.e("quming",quming);
                    Log.e("jieming",jieming);
                    Log.e("xiangxidizhi",xiangxidizhi);
                    Log.e("moren",moren+"--");
                    Log.e("shengid",shengid+"==");
                    Log.e("shiid",shiid+"==");
                    Log.e("quid",quid+"==");
                    Log.e("jieid",jieid+"==");
                }
                break;
            case R.id.tv_suozaidiqu:
                if (zonglist!=null){
                    shengdialog();
                }else{
                    getsheng();
                }
                break;
            case R.id.ll_dianhuabu:
                //通过电话簿获取姓名电话
                if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    Log.e("quanxian"," 没有权限，申请权限。");
                    // 没有权限，申请权限。
                    ActivityCompat.requestPermissions(AddAddressActivity.this , new String[]{Manifest.permission.READ_CONTACTS} , 1);
                }else{
                    // 有权限了，去放肆吧。
                    Log.e("quanxian"," 有权限了，去放肆吧。");
                    try {
                        Intent intentPhone=new Intent(Intent.ACTION_PICK);
                        intentPhone.setData(ContactsContract.Contacts.CONTENT_URI);
                        startActivityForResult(intentPhone,1);
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    }
                }
                break;

        }
    }
    private void getsheng() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getsheng())
                .setDataListener(new HttpDataListener<List<ProvinceBean>>() {
                    @Override
                    public void onNext(final List<ProvinceBean> list) {
                        zonglist = new ArrayList<ProvinceBean>();
                        zonglist.addAll(list);
                        shenglist = new ArrayList<ProvinceBean>();
                        for (int i = 0; i <list.size() ; i++) {
                            if (list.get(i).getQuyfjbm()==86) {
                                shenglist.add(list.get(i));
                            }
                        }
                        shengdialog();

                    }
                });
    }

    private void shengdialog(){
        final SinglePicker<ProvinceBean> picker =new SinglePicker<ProvinceBean>(AddAddressActivity.this, shenglist);
        picker.setCanceledOnTouchOutside(false);
        picker.setSelectedIndex(1);
        picker.setCycleDisable(false);
        picker.setOnItemPickListener(new SinglePicker.OnItemPickListener<ProvinceBean>() {
            @Override
            public void onItemPicked(int index, ProvinceBean item) {
                shengming = item.getQuymc();
                shengid = item.getQuybm()+"";
                Log.e("shengsheng",shengid+"==="+shengming);
                shilist = new ArrayList();
                for (int i = 0; i < zonglist.size(); i++) {
                    if (zonglist.get(i).getQuyfjbm()==Integer.parseInt(shengid)) {
                        shilist.add(zonglist.get(i));
                    }
                }
                shidialog();
                picker.dismiss();
            }
        });
        picker.show();
    }
    private void shidialog() {
        final SinglePicker<ProvinceBean> picker =new SinglePicker<ProvinceBean>(AddAddressActivity.this,shilist);
        picker.setCanceledOnTouchOutside(false);
        picker.setSelectedIndex(1);
        picker.setCycleDisable(false);
        picker.setOnItemPickListener(new SinglePicker.OnItemPickListener<ProvinceBean>() {
            @Override
            public void onItemPicked(int index, ProvinceBean item) {
                shiming = item.getQuymc();
                shiid = item.getQuybm()+"";
                Log.e("shishishsi",shiid+"===");
                qulist = new ArrayList();
                for (int i = 0; i < zonglist.size(); i++) {
                    if (zonglist.get(i).getQuyfjbm()==Integer.parseInt(shiid)) {
                        qulist.add(zonglist.get(i));
                    }
                }
                qudialog();
                picker.dismiss();
            }
        });
        picker.show();
    }

    private void qudialog() {
            final SinglePicker<ProvinceBean> picker =new SinglePicker<ProvinceBean>(AddAddressActivity.this,qulist);
            picker.setCanceledOnTouchOutside(false);
            picker.setSelectedIndex(1);
            picker.setCycleDisable(false);
            picker.setOnItemPickListener(new SinglePicker.OnItemPickListener<ProvinceBean>() {
                @Override
                public void onItemPicked(int index, ProvinceBean item) {
                    quming = item.getQuymc();
                    quid = item.getQuybm()+"";
                    Log.e("hishishsi",quid+"===");

                    jielist = new ArrayList();
                    for (int i = 0; i < zonglist.size(); i++) {
                        if (zonglist.get(i).getQuyfjbm()==Integer.parseInt(quid)) {
                            jielist.add(zonglist.get(i));
                        }
                    }
                    if (jielist.size()==0){
                        jieming = "";
                        jieid = "";
                        tvSuozaidiqu.setText(shengming+shiming+quming+jieming);
                    }else{
                        jiedialog();
                    }

                    picker.dismiss();
//                    picker.dismiss();
//                    tvSuozaidiqu.setText(shengming+shiming+quming);
                }
            });
            picker.show();
    }

    private void jiedialog() {
        final SinglePicker<ProvinceBean> picker =new SinglePicker<ProvinceBean>(AddAddressActivity.this,jielist);
        picker.setCanceledOnTouchOutside(false);
        picker.setSelectedIndex(1);
        picker.setCycleDisable(false);
        picker.show();
        picker.setOnItemPickListener(new SinglePicker.OnItemPickListener<ProvinceBean>() {
            @Override
            public void onItemPicked(int index, ProvinceBean item) {
                jieming = item.getQuymc();
                jieid = item.getQuybm()+"";
                Log.e("jieidjieid",jieid+"===");
                picker.dismiss();
                tvSuozaidiqu.setText(shengming+shiming+quming+jieming);
            }
        });

    }

    //获得返回的结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode== Activity.RESULT_OK){
//                    &&requestCode==1
                    Uri uri=data.getData();
                    Cursor cursor=managedQuery(uri,null,null,null,null);
                    cursor.moveToFirst();
                    Cursor phone = null;
                    try {
                        String contactid= cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                        //得到ContentResolver
                        ContentResolver contentResolver=getContentResolver();
                        phone=contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"="+contactid,null,null);
                    } catch (Exception e) {
                        ToastUtil.showToast("请检查是否开启读取联系人权限");
//                        e.printStackTrace();
                        return ;
                    }
                    while (phone.moveToNext()){
                        //联系人
                        phoneName = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        //手机号码
                        phonenumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        //格式化手机号
                        Log.e("phoneName", phoneName);
                        Log.e("phonenumber", phonenumber);
                        etShouhuoren.setText(phoneName);
                        etDianhua.setText(phonenumber);
                    }
                }
                break;

        }
    }
    //添加地址
    private void addAddress() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .addAddress(PreferenceUtils.getString(MyApplication.mContext, "token",""),shengid+"",shiid+"",quid+"",jieid+"",etXiangxidizhi.getText().toString().trim(),phoneName,phonenumber,moren+""))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        Log.e("datatianjia",data+"==");
                        finish();

                    }
                });
    }
    //修改
    private void editAddress() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .editAddress(PreferenceUtils.getString(MyApplication.mContext, "token",""),shengid+"",shiid+"",quid+"",jieid+"",etXiangxidizhi.getText().toString().trim(),phoneName,phonenumber,moren+"",addressid))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        Log.e("dataxiugai",data+"==");
                        finish();
                    }
                });
    }
}
