package com.mingmen.mayi.mayibanjia.bean;

/**
 * Created by Administrator on 2019/1/23.
 */

public class AddQrCodeBean {
    private String commodity_id;
    private String classify_name;
    private String is_true;
    private String acount;
    private String url;
    private String number;
    private String all_price;

    public String getAll_price() {
        return all_price;
    }

    public void setAll_price(String all_price) {
        this.all_price = all_price;
    }

    public String getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(String commodity_id) {
        this.commodity_id = commodity_id;
    }

    public String getClassify_name() {
        return classify_name;
    }

    public void setClassify_name(String classify_name) {
        this.classify_name = classify_name;
    }

    public String getIs_true() {
        return is_true;
    }

    public void setIs_true(String is_true) {
        this.is_true = is_true;
    }

    public String getAcount() {
        return acount;
    }

    public void setAcount(String acount) {
        this.acount = acount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
