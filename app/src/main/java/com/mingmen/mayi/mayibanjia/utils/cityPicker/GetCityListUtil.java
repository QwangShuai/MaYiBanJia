package com.mingmen.mayi.mayibanjia.utils.cityPicker;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.bean.JsonBean;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/3/29.
 */

public class GetCityListUtil {

    public static List<JsonBean> getProvinceList(Context mContext){
        List<JsonBean> getList = new ArrayList<>();
        String JsonData = StringUtil.getJson(mContext,"province.json");//获取assets目录下的json文件数据
        ArrayList<JsonBean> jsonBean = StringUtil.parseData(JsonData);//用Gson 转成实体

        Log.e("getProvinceList: ", new Gson().toJson(jsonBean));
        return jsonBean;
    }

    public static List<JsonBean.CitylistBean> getCityList(Context mContext,String province_id){
        List<JsonBean.CitylistBean> cityList = new ArrayList<>();
        String JsonData = StringUtil.getJson(mContext,"province.json");//获取assets目录下的json文件数据
        ArrayList<JsonBean> jsonBean = StringUtil.parseData(JsonData);//用Gson 转成实体
                for (JsonBean bean:jsonBean) {
                    if(province_id.equals(bean.getQuybm()+"")){
                        cityList.addAll(bean.getCitylist());
                    }
                }
        Log.e("getCityList: ", new Gson().toJson(cityList));
        return cityList;
    }

    public static List<JsonBean.CitylistBean.QulistBean> getQuList(Context mContext,String province_id,String city_id){
        List<JsonBean.CitylistBean.QulistBean> quList = new ArrayList<>();
        String JsonData = StringUtil.getJson(mContext,"province.json");//获取assets目录下的json文件数据
        ArrayList<JsonBean> jsonBean = StringUtil.parseData(JsonData);//用Gson 转成实体
        for (JsonBean bean:jsonBean) {
            if(province_id.equals(bean.getQuybm()+"")){
                for (JsonBean.CitylistBean citybean:bean.getCitylist()) {
                    if(city_id.equals(""+citybean.getQuybm())){
                        quList.addAll(citybean.getQulist());
                    }
                }
            }
        }
        Log.e("getQuList: ", new Gson().toJson(quList));
        return quList;
    }
}
