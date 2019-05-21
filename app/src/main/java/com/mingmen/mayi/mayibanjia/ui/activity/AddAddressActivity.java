package com.mingmen.mayi.mayibanjia.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.AddressListBean;
import com.mingmen.mayi.mayibanjia.bean.JsonBean;
import com.mingmen.mayi.mayibanjia.bean.ProvinceBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.SinglePicker;

/**
 * Created by Administrator on 2018/7/18/018.
 */
//添加收货地址--
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
    @BindView(R.id.tv_suozaijiedao)
    TextView tvSuozaijiedao;
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
    private int moren = 1;
    private String phoneName = "";
    private String phonenumber = "";
    private String xiangxidizhi = "";
    private String quming;
    private ArrayList<ProvinceBean> jielist;
    private String jieming;
    private String jieid;
    private String addressid;
//    private ArrayList<JsonBean> options1Items = new ArrayList<>();//省
//    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();//市
//    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();//区
//    int city = 0;
//    int[] pos = new int[3];

    @Override
    public int getLayoutId() {
        return R.layout.activity_addaddress;
    }

    @Override
    protected void initData() {
        tvTitle.setText("添加收货地址");
        tvRight.setText("保存");
        rukou = getIntent().getStringExtra("rukou");
        mContext = AddAddressActivity.this;
        StringUtil.setInputNoEmoj(etXiangxidizhi);
        StringUtil.setInputNoEmoj(etShouhuoren);
        //判断入口
        if ("add".equals(rukou)) {

        } else {
            //入口为修改
            String json = getIntent().getStringExtra("json");
            AddressListBean editdata = gson.fromJson(json, AddressListBean.class);
//            回显
            addressid = editdata.getAddress_id();
            phoneName = editdata.getLinkman();
            phonenumber = editdata.getContact_type();
            shengid = editdata.getProvince();
            shiid = editdata.getCity();
            quid = editdata.getRegion();
            jieid = editdata.getStreet();
            shengming = editdata.getProvince_name();
            shiming = editdata.getCity_name();
            quming = editdata.getRegion_name();
            jieming = editdata.getStreet_name();

            xiangxidizhi = editdata.getSpecific_address();
            etShouhuoren.setText(phoneName);
            etDianhua.setText(phonenumber);
            etXiangxidizhi.setText(xiangxidizhi);
            //tvSuozaidiqu.setText(shengming + shiming + quming + jieming);
            tvSuozaidiqu.setText(shengming + shiming + quming);
            tvSuozaijiedao.setText(jieming);
            //etShouhuoren.setText(editdata.getLinkman());
            //是否默认
            if (moren == 0) {
                switchMorendizhi.setChecked(true);
            } else {
                switchMorendizhi.setChecked(false);
            }
        }

        switchMorendizhi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    moren = 0;
                } else {
                    moren = 1;
                }

            }
        });
//        initJsonData();
        getDizhi();
    }


    @OnClick({R.id.iv_back, R.id.tv_right, R.id.ll_dianhuabu, R.id.tv_suozaidiqu, R.id.switch_morendizhi, R.id.tv_suozaijiedao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                //                ,,shengid,shiid,quid,etXiangxidizhi.getText().toString().trim(),isMoRen
//                联系人，电话号，省id，市id，区id，详细地址，是否默认
                xiangxidizhi = etXiangxidizhi.getText().toString().trim();
                phoneName = etShouhuoren.getText().toString().trim();
                phonenumber = etDianhua.getText().toString().trim();
                if (AppUtil.isMobile(phonenumber)) {
                    etDianhua.setText(phonenumber);
                } else {
                    ToastUtil.showToast("请正确填写手机号");
                    return;
                }

                //验证信息是否有空
//                if (!"".equals(phoneName) & !"".equals(phonenumber) & !"".equals(shengming) & !"".equals(shiming) & !"".equals(quming) & !"".equals(xiangxidizhi)) {
                if (!"".equals(phoneName) & !"".equals(phonenumber) & !"".equals(xiangxidizhi)) {
                    if ("add".equals(rukou)) {
                        addAddress();
                    } else {
                        editAddress();
                    }
                } else {
                    ToastUtil.showToast("请确认信息填写完整后再提交");
                }
                break;
//            case R.id.tv_suozaidiqu:
//                showCityPicker();
//                break;
//            case R.id.tv_suozaijiedao:
//                if (city == 0) {
//                    ToastUtil.showToast("请先选择区域");
//                } else {
//                    getsheng();
//                }
//                break;
            case R.id.ll_dianhuabu:
                //通过电话簿获取姓名电话
                if ("魅族".equals(AppUtil.getDeviceBrand())) {
                } else {
                    if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                        // 没有权限，申请权限。
                        ActivityCompat.requestPermissions(AddAddressActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
                    } else {
                        // 有权限了，去放肆吧。
                        try {
                            Intent intentPhone = new Intent(Intent.ACTION_PICK);
                            intentPhone.setData(ContactsContract.Contacts.CONTENT_URI);
                            startActivityForResult(intentPhone, 1);
                        } catch (SecurityException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;

        }
    }

//    private void getsheng() {
//        HttpManager.getInstance()
//                .with(mContext)
//                .setObservable(
//                        RetrofitManager
//                                .getService()
//                                .getsheng(PreferenceUtils.getString(MyApplication.mContext, "token", ""),city+""))
//                .setDataListener(new HttpDataListener<List<ProvinceBean>>() {
//                    @Override
//                    public void onNext(final List<ProvinceBean> list) {
//                        zonglist = new ArrayList<ProvinceBean>();
//                        zonglist.addAll(list);
//                        jiedialog();
//
//                    }
//                });
//    }
//
//    private void shengdialog() {
//        final SinglePicker<ProvinceBean> picker = new SinglePicker<ProvinceBean>(AddAddressActivity.this, shenglist);
//        picker.setCanceledOnTouchOutside(false);
//        picker.setSelectedIndex(1);
//        picker.setCycleDisable(false);
//        picker.setOnItemPickListener(new SinglePicker.OnItemPickListener<ProvinceBean>() {
//            @Override
//            public void onItemPicked(int index, ProvinceBean item) {
//                shengming = item.getQuymc();
//                shengid = item.getQuybm() + "";
//                shilist = new ArrayList();
//                for (int i = 0; i < zonglist.size(); i++) {
//                    if (zonglist.get(i).getQuyfjbm() == Integer.parseInt(shengid)) {
//                        shilist.add(zonglist.get(i));
//                    }
//                }
//                shidialog();
//                picker.dismiss();
//            }
//        });
//        picker.show();
//    }
//
//    private void shidialog() {
//        final SinglePicker<ProvinceBean> picker = new SinglePicker<ProvinceBean>(AddAddressActivity.this, shilist);
//        picker.setCanceledOnTouchOutside(false);
//        picker.setSelectedIndex(1);
//        picker.setCycleDisable(false);
//        picker.setOnItemPickListener(new SinglePicker.OnItemPickListener<ProvinceBean>() {
//            @Override
//            public void onItemPicked(int index, ProvinceBean item) {
//                shiming = item.getQuymc();
//                shiid = item.getQuybm() + "";
//                qulist = new ArrayList();
//                for (int i = 0; i < zonglist.size(); i++) {
//                    if (zonglist.get(i).getQuyfjbm() == Integer.parseInt(shiid)) {
//                        qulist.add(zonglist.get(i));
//                    }
//                }
//                qudialog();
//                picker.dismiss();
//            }
//        });
//        picker.show();
//    }
//
//    private void qudialog() {
//        final SinglePicker<ProvinceBean> picker = new SinglePicker<ProvinceBean>(AddAddressActivity.this, qulist);
//        picker.setCanceledOnTouchOutside(false);
//        picker.setSelectedIndex(1);
//        picker.setCycleDisable(false);
//        picker.setOnItemPickListener(new SinglePicker.OnItemPickListener<ProvinceBean>() {
//            @Override
//            public void onItemPicked(int index, ProvinceBean item) {
//                quming = item.getQuymc();
//                quid = item.getQuybm() + "";
//                jielist = new ArrayList();
//                for (int i = 0; i < zonglist.size(); i++) {
//                    if (zonglist.get(i).getQuyfjbm() == Integer.parseInt(quid)) {
//                        jielist.add(zonglist.get(i));
//                    }
//                }
//                if (jielist.size() == 0) {
//                    jieming = "";
//                    jieid = "";
//                    tvSuozaidiqu.setText(shengming + shiming + quming + jieming);
//                } else {
//                    jiedialog();
//                }
//
//                picker.dismiss();
////                    picker.dismiss();
////                    tvSuozaidiqu.setText(shengming+shiming+quming);
//            }
//        });
//        picker.show();
//    }
//
//    private void jiedialog() {
//        if (zonglist.size() != 0) {
//            final SinglePicker<ProvinceBean> picker = new SinglePicker<ProvinceBean>(AddAddressActivity.this, zonglist);
//            picker.setCanceledOnTouchOutside(false);
//            picker.setSelectedIndex(1);
//            picker.setCycleDisable(false);
//            picker.show();
//            picker.setOnItemPickListener(new SinglePicker.OnItemPickListener<ProvinceBean>() {
//                @Override
//                public void onItemPicked(int index, ProvinceBean item) {
//                    jieming = item.getQuymc();
//                    jieid = item.getQuybm() + "";
//                    picker.dismiss();
//                    tvSuozaijiedao.setText(jieming);
//                }
//            });
//        } else {
//            ToastUtil.showToast("暂无街道信息,信息录入失败");
//        }
//    }

    //获得返回的结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == Activity.RESULT_OK) {
//                    &&requestCode==1
                    Uri uri = data.getData();
                    Cursor cursor = managedQuery(uri, null, null, null, null);
                    cursor.moveToFirst();
                    Cursor phone = null;
                    try {
                        String contactid = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                        //得到ContentResolver
                        ContentResolver contentResolver = getContentResolver();
                        phone = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactid, null, null);
                    } catch (Exception e) {
                        ToastUtil.showToast("请检查是否开启读取联系人权限");
//                        e.printStackTrace();
                        return;
                    }
                    while (phone.moveToNext()) {
                        //联系人
                        phoneName = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        //手机号码
                        phonenumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        if(phonenumber.contains("+")){
                            phonenumber = phonenumber.substring(3,phonenumber.length());
                        }
                        phonenumber = phonenumber.replaceAll(" ","");
                        phonenumber = phonenumber.replaceAll("-","");
                        //格式化手机号
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
                                .addAddress(PreferenceUtils.getString(MyApplication.mContext, "token", ""),  etXiangxidizhi.getText().toString().trim(), phoneName, phonenumber, moren + ""))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
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
                                .editAddress(PreferenceUtils.getString(MyApplication.mContext, "token", ""), etXiangxidizhi.getText().toString().trim(), phoneName, phonenumber, moren + "", addressid))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        finish();
                    }
                });
    }

//    private void showCityPicker() {
//        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                //返回的分别是三个级别的选中位置
//                String tx = options1Items.get(options1).getPickerViewText() + "-" +
//                        options2Items.get(options1).get(options2) + "-" +
//                        options3Items.get(options1).get(options2).get(options3);
//                tvSuozaidiqu.setText(tx);
//                shengid = options1Items.get(options1).getQuybm() + "";
//                shiid = options1Items.get(options1).getCitylist().get(options2).getQuybm() + "";
//                city = options1Items.get(options1).getCitylist().get(options2).getQulist().get(options3).getQuybm();
//                quid = options1Items.get(options1).getCitylist().get(options2).getQulist().get(options3).getQuybm() + "";
//
//                pos[0] = options1;
//                pos[1] = options2;
//                pos[2] = options3;
//
//                jieming = "";
//                jieid = "";
//                tvSuozaijiedao.setText("");
//                Log.e("我的区域编号", city + "");
//            }
//        })
//                .setTitleText("城市选择")
//                .setDividerColor(Color.BLACK)
//                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
//                .setContentTextSize(20)
//                .build();
//
//        /*pvOptions.setPicker(options1Items);//一级选择器
//        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
//        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
//        pvOptions.setSelectOptions(pos[0], pos[1], pos[2]);
//        pvOptions.show();
//    }
//
//    private void initJsonData() {//解析数据
//
//        /**
//         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
//         * 关键逻辑在于循环体
//         *
//         * */
//        String JsonData = StringUtil.getJson(this, "province.json");//获取assets目录下的json文件数据
//
//        ArrayList<JsonBean> jsonBean = StringUtil.parseData(JsonData);//用Gson 转成实体
//
//        /**
//         * 添加省份数据
//         *
//         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
//         * PickerView会通过getPickerViewText方法获取字符串显示出来。
//         */
//        options1Items = jsonBean;
//
//        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
//            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
//            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
//
//            for (int c = 0; c < jsonBean.get(i).getCitylist().size(); c++) {//遍历该省份的所有城市
//                String CityName = jsonBean.get(i).getCitylist().get(c).getQuymc();
//                CityList.add(CityName);//添加城市
//
//                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表
//
//                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
//                if (jsonBean.get(i).getCitylist().get(c).getQulist() == null
//                        || jsonBean.get(i).getCitylist().get(c).getQulist().size() == 0) {
//                    City_AreaList.add("");
//                } else {
//
//                    for (int d = 0; d < jsonBean.get(i).getCitylist().get(c).getQulist().size(); d++) {//该城市对应地区所有数据
//                        String AreaName = jsonBean.get(i).getCitylist().get(c).getQulist().get(d).getQuymc();
//
//                        if(!AreaName.equals("市辖区")){                                 City_AreaList.add(AreaName);                             }
//                    }
//                }
//                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
//            }
//
//            /**
//             * 添加城市数据
//             */
//            options2Items.add(CityList);
//
//            /**
//             * 添加地区数据
//             */
//            options3Items.add(Province_AreaList);
//        }
//    }

    private void getDizhi() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getDizhi(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<AddressListBean>() {
                    @Override
                    public void onNext(final AddressListBean bean) {
                        tvSuozaidiqu.setText(bean.getAdressall()+"");
                    }
                });
    }
}
