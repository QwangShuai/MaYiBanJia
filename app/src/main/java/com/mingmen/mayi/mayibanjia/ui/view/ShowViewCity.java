package com.mingmen.mayi.mayibanjia.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.configure.PickerOptions;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.bean.JsonBean;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/11/30.
 * 省市区三级联动
 */

public class ShowViewCity {
    private Context context;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();//省
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();//市
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();//区

    public String getTx() {
        return tx;
    }

    public void setTx(String tx) {
        this.tx = tx;
    }

    public int[] getCity() {
        return city;
    }

    public void setCity(int[] city) {
        this.city = city;
    }

    private String tx;
    int[] city = new int[3];

    public ShowViewCity(Context context) {
        this.context = context;
    }

    public void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = StringUtil.getJson(context,"province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = StringUtil.parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i=0;i<jsonBean.size();i++){//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c=0; c<jsonBean.get(i).getCitylist().size(); c++){//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCitylist().get(c).getQuymc();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCitylist().get(c).getQulist() == null
                        ||jsonBean.get(i).getCitylist().get(c).getQulist().size()==0) {
                    City_AreaList.add("");
                }else {

                    for (int d=0; d < jsonBean.get(i).getCitylist().get(c).getQulist().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCitylist().get(c).getQulist().get(d).getQuymc();

                        if(!AreaName.equals("市辖区")){                                 City_AreaList.add(AreaName);                             }
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
            OptionsPickerView pvOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    tx = options1Items.get(options1).getPickerViewText()+"-"+
                            options2Items.get(options1).get(options2)+"-"+
                            options3Items.get(options1).get(options2).get(options3);

                    city[0] = options1Items.get(options1).getQuybm();
                    city[1] = options1Items.get(options1).getCitylist().get(options2).getQuybm();
                    city[2] = options1Items.get(options1).getCitylist().get(options2).getQulist().get(options3).getQuybm();
                }
            })
                    .setTitleText("城市选择")
                    .setDividerColor(Color.BLACK)
                    .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                    .setContentTextSize(20)
                    .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
            pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器
            pvOptions.show();
        }
    }
//    public void showCityPicker(){
//
//    }
}
