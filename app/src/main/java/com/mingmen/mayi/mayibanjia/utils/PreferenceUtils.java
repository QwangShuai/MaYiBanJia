package com.mingmen.mayi.mayibanjia.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.bean.EditorShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.ZiZhangHuDetailsBean;
import com.mingmen.mayi.mayibanjia.bean.ZzhQuanXianBean;

import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2018/7/4/004.
 */

public class PreferenceUtils {

    private static SharedPreferences sp;

    public static void getSharedPreference(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        }
    }

    public static void putString(Context context, String key, String value) {
        getSharedPreference(context);
        sp.edit().putString(key, value).commit();
    }

    public static String getString(Context context, String key, String defValue) {
        getSharedPreference(context);
        return sp.getString(key, defValue);
    }

    public static void putInt(Context context, String key, int value) {
        getSharedPreference(context);
        sp.edit().putInt(key, value).commit();
    }

    public static int getInt(Context context, String key, int defValue) {
        getSharedPreference(context);
        return sp.getInt(key, defValue);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        getSharedPreference(context);
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context, String key,
                                     boolean defValue) {
        getSharedPreference(context);
        return sp.getBoolean(key, defValue);
    }

    public static void putLong(Context context, String key, long value) {
        getSharedPreference(context);
        sp.edit().putLong(key, value).commit();
    }

    public static long getLong(Context context, String key, long defValue) {
        getSharedPreference(context);
        return sp.getLong(key, defValue);
    }

    public static void setEditorShangPinBean(Context context, EditorShangPinBean bean){//存储编辑数据
        getSharedPreference(context);
        Gson gson = new Gson();
        String data = gson.toJson(bean);
        sp.edit().putString("bean",data).commit();
    }
    public static EditorShangPinBean getEditorShangPinBean(Context context, String defValue) {//获取编辑数据
        getSharedPreference(context);
        EditorShangPinBean bean = new Gson().fromJson(sp.getString("bean",""),EditorShangPinBean.class);
        return bean;
    }

    public static void setQuanxianList(Context context, ZiZhangHuDetailsBean bean){//存储编辑数据
        getSharedPreference(context);
        Gson gson = new Gson();
        String data = gson.toJson(bean);
        sp.edit().putString("quanxian",data).commit();
    }
    public static List<ZiZhangHuDetailsBean.RoleListBean> getQuanxianList(Context context, String defValue) {//获取编辑数据
        getSharedPreference(context);
        ZiZhangHuDetailsBean bean =  new Gson().fromJson(sp.getString("quanxian",""),ZiZhangHuDetailsBean.class);
        return bean.getRoleList();
    }
    /**
     * 移除
     * @param
     */
    public static void remove(Context context, String key) {
        getSharedPreference(context);
        sp.edit().remove(key).commit();
    }

    public static void clear(Context context) {
        getSharedPreference(context);
        sp.edit().clear().commit();
    }

}
