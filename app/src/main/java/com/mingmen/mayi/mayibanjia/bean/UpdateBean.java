package com.mingmen.mayi.mayibanjia.bean;

/**
 * Created by Administrator on 2019/4/22.
 */

public class UpdateBean {

    /**
     * object : {"versionName":"1.0.1","downloadUrl":"213123123","modifyContent":"1测试 2测试","apkSize":null,"versionCode":"2","apkMd5":"1e6c8112864a941584b9671532ca6e4c","updateStatus":"0","code":"0","msg":"31231"}
     * status : 0000
     * msg : 成功
     */

    private BanbenUpdateBean object;
    private String status;
    private String msg;

    public BanbenUpdateBean getObject() {
        return object;
    }

    public void setObject(BanbenUpdateBean object) {
        this.object = object;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
