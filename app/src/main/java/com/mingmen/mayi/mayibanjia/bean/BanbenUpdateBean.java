package com.mingmen.mayi.mayibanjia.bean;

/**
 * Created by Administrator on 2019/5/27.
 */

public class BanbenUpdateBean {

    /**
     * updateStatus : 0
     * versionCode : 2
     * versionName : 1.0.1
     * modifyContent : 1测试 2测试
     * downloadUrl : https://raw.githubusercontent.com/xuexiangjys/XUpdate/master/apk/xupdate_demo_1.0.2.apk
     * apkSize : 35
     * apkMd5 : 0781d15d4f64bdf8b49834120ea6cf05
     * code : 0
     * msg : 31231
     */

    private int UpdateStatus;
    private int VersionCode;
    private String VersionName;
    private String ModifyContent;
    private String DownloadUrl;
    private long ApkSize;
    private String ApkMd5;
    private int Code;
    private String Msg;

    public int getUpdateStatus() {
        return UpdateStatus;
    }

    public void setUpdateStatus(int updateStatus) {
        UpdateStatus = updateStatus;
    }

    public int getVersionCode() {
        return VersionCode;
    }

    public void setVersionCode(int versionCode) {
        VersionCode = versionCode;
    }

    public String getVersionName() {
        return VersionName;
    }

    public void setVersionName(String versionName) {
        VersionName = versionName;
    }

    public String getModifyContent() {
        return ModifyContent;
    }

    public void setModifyContent(String modifyContent) {
        ModifyContent = modifyContent;
    }

    public String getDownloadUrl() {
        return DownloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        DownloadUrl = downloadUrl;
    }

    public long getApkSize() {
        return ApkSize;
    }

    public void setApkSize(long apkSize) {
        ApkSize = apkSize;
    }

    public String getApkMd5() {
        return ApkMd5;
    }

    public void setApkMd5(String apkMd5) {
        ApkMd5 = apkMd5;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }
}
