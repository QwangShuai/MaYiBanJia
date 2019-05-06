package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.JsonBean;
import com.mingmen.mayi.mayibanjia.bean.ShiChangBean;
import com.mingmen.mayi.mayibanjia.bean.ZhuCeChengGongBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppManager;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.SinglePicker;

public class YoukeLoginActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.rl_phone_clear)
    RelativeLayout rlPhoneClear;
    @BindView(R.id.et_juese)
    TextView etJuese;
    @BindView(R.id.rl_juese)
    RelativeLayout rlJuese;
    @BindView(R.id.tv_diqu)
    TextView tvDiqu;
    @BindView(R.id.rl_diqu)
    RelativeLayout rlDiqu;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.bt_zhuce)
    Button btZhuce;

    private Context mContext;

    //省市区
    private ArrayList<JsonBean> options1Items = new ArrayList<>();//省
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();//市
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();//区
    private int city = 0;
    private int[] pos = new int[3];
    private String province_name;
    private String city_name;
    private String region_name;

    //角色
    private String name;
    private List<String> list = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_youke_login;
    }

    @Override
    protected void initData() {
        tvTitle.setText("游客登录");
        mContext = YoukeLoginActivity.this;
        initJsonData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.rl_juese, R.id.rl_diqu, R.id.bt_login, R.id.bt_zhuce})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                break;
            case R.id.rl_juese:
                list.add("餐厅端");
                list.add("供货端");
                list.add("社区市场");
                final SinglePicker<String> picker = new SinglePicker<String>(YoukeLoginActivity.this, list);
                picker.setCanceledOnTouchOutside(false);
                picker.setSelectedIndex(1);
                picker.setCycleDisable(false);
                picker.setOnItemPickListener(new SinglePicker.OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        name = item;
                        etJuese.setText(item);
                        picker.dismiss();
                    }
                });

                picker.show();
                break;
            case R.id.rl_diqu:
                showCityPicker();
                break;
            case R.id.bt_login:
                if(!StringUtil.exist(etName.getText().toString().trim())){
                    ToastUtil.showToastLong("请输入正确的姓名");
                } else if(TextUtils.isEmpty(etPhone.getText().toString().trim())){
                    ToastUtil.showToastLong("请输入手机号");
                } else if(TextUtils.isEmpty(name)){
                    ToastUtil.showToastLong("请选择身份");
                } else if(TextUtils.isEmpty(province_name)){
                    ToastUtil.showToastLong("请选择省市区");
                } else {
                    youke();
                }
                break;
            case R.id.bt_zhuce:
                Intent zhuce = new Intent(mContext, ZhuCeActivity.class);
                zhuce.putExtra("yemian", "1");
                startActivity(zhuce);
                break;
        }
    }

    private void youke(){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .youkeLogin(province_name,city_name,region_name,name,
                                        etPhone.getText().toString(),etName.getText().toString()))
                .setDataListener(new HttpDataListener<ZhuCeChengGongBean>() {
                    @Override
                    public void onNext(ZhuCeChengGongBean bean) {
                        Log.e("token", bean.getToken() + "===");
                        PreferenceUtils.putString(MyApplication.mContext, "phone", bean.getTelephone());
                        PreferenceUtils.putString(MyApplication.mContext, "host_account_type", bean.getHost_account_type());
                        PreferenceUtils.putBoolean(MyApplication.mContext, "isLogin", true);
                        PreferenceUtils.putString(MyApplication.mContext, "token", bean.getToken());
                        PreferenceUtils.putString(MyApplication.mContext, "juese", bean.getRole());
                        if (StringUtil.isValid(bean.getName())) {
                            PreferenceUtils.putString(MyApplication.mContext, "name", bean.getName());
                        }
                        PreferenceUtils.putInt(MyApplication.mContext, "random_id", bean.getRandom_id());
                        PreferenceUtils.putBoolean(MyApplication.mContext, "youke", true);
                        Intent intent = new Intent(mContext, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        AppManager.getAppManager().finishAllActivity();
                    }
                    @Override
                    protected Object clone() throws CloneNotSupportedException {
                        return super.clone();
                    }
                });
    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = StringUtil.getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = StringUtil.parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCitylist().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCitylist().get(c).getQuymc();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCitylist().get(c).getQulist() == null
                        || jsonBean.get(i).getCitylist().get(c).getQulist().size() == 0) {
                    City_AreaList.add("");
                } else {
                    for (int d = 0; d < jsonBean.get(i).getCitylist().get(c).getQulist().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCitylist().get(c).getQulist().get(d).getQuymc();
                        if (!AreaName.equals("市辖区")) {
                            City_AreaList.add(AreaName);
                        }
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
    private void showCityPicker() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() + "-" +
                        options2Items.get(options1).get(options2) + "-" +
                        options3Items.get(options1).get(options2).get(options3);
                tvDiqu.setText(tx);
                province_name = options1Items.get(options1).getQuymc();
                city_name = options1Items.get(options1).getCitylist().get(options2).getQuymc();
                city = options1Items.get(options1).getCitylist().get(options2).getQulist().get(options3).getQuybm();
                region_name = options1Items.get(options1).getCitylist().get(options2).getQulist().get(options3).getQuymc();

                pos[0] = options1;
                pos[1] = options2;
                pos[2] = options3;

                Log.e("我的区域编号", city + "");
            }
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.setSelectOptions(pos[0], pos[1], pos[2]);
        pvOptions.show();
    }
}
