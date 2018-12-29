package com.mingmen.mayi.mayibanjia.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2018/12/28.
 */

public class WXPayBean {


    /**
     * package : Sign=WXPay
     * appid : wx82e8c18d4d1082b4
     * sign : 1740EA375287ED78A51E9A8EE3A13EBA
     * partnerid : 1521664591
     * prepayid : wx281430050326151cde427b542448911315
     * noncestr : eb1e3eede9814d72a81f107e593a16d0
     * timestamp : 1545978604
     */

    @SerializedName("package")
    private String packageX;
    private String appid;
    private String sign;
    private String partnerid;
    private String prepayid;
    private String noncestr;
    private String timestamp;

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
