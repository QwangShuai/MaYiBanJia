package com.mingmen.mayi.mayibanjia.bean;

/**
 * Created by Administrator on 2018/10/8.
 */

public class QrCodeBean {

    private String twocode_id;//   uuid
    private String twocode;//   二维码
    private String twocode_valid;//   二维码是否有效
    private String wl_sweep;//   物流方是否扫过
    private String mj_sweep;//   买方是否扫过
    private String creat_time;//   创建时间
    private String serial;//序号
    private String user_token;
    private String gy_order_id;//订单号（供应端）
    private String wl_sweep_time;//物流扫码时间
    private String mj_sweep_time;//买方扫码时间
    private String type;//1.物流方 2.买方
    private String wl_cars_order_id;
    private String wl_cars_id;
    private String is_true;//是否打包完成
    private String identifying;//标识
    private String company_name;//企业名称
    private String telephone;//电话
    private String companyAddress;//详细地址
    public String getIs_true() {
        return is_true;
    }

    public void setIs_true(String is_true) {
        this.is_true = is_true;
    }

    public String getWl_cars_id() {
        return wl_cars_id;
    }

    public void setWl_cars_id(String wl_cars_id) {
        this.wl_cars_id = wl_cars_id;
    }

    public String getWl_cars_order_id() {
        return wl_cars_order_id;
    }

    public void setWl_cars_order_id(String wl_cars_order_id) {
        this.wl_cars_order_id = wl_cars_order_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWl_sweep_time() {
        return wl_sweep_time;
    }

    public void setWl_sweep_time(String wl_sweep_time) {
        this.wl_sweep_time = wl_sweep_time;
    }

    public String getMj_sweep_time() {
        return mj_sweep_time;
    }

    public void setMj_sweep_time(String mj_sweep_time) {
        this.mj_sweep_time = mj_sweep_time;
    }

    public String getGy_order_id() {
        return gy_order_id;
    }

    public void setGy_order_id(String gy_order_id) {
        this.gy_order_id = gy_order_id;
    }

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getTwocode_id() {
        return this.twocode_id;
    }

    public void setTwocode_id(String twocode_id) {
        this.twocode_id = twocode_id;
    }

    public String getTwocode() {
        return this.twocode;
    }

    public void setTwocode(String twocode) {
        this.twocode = twocode;
    }

    public String getTwocode_valid() {
        return this.twocode_valid;
    }

    public void setTwocode_valid(String twocode_valid) {
        this.twocode_valid = twocode_valid;
    }

    public String getWl_sweep() {
        return this.wl_sweep;
    }

    public void setWl_sweep(String wl_sweep) {
        this.wl_sweep = wl_sweep;
    }

    public String getMj_sweep() {
        return this.mj_sweep;
    }

    public void setMj_sweep(String mj_sweep) {
        this.mj_sweep = mj_sweep;
    }

    public String getCreat_time() {
        return this.creat_time;
    }

    public void setCreat_time(String creat_time) {
        this.creat_time = creat_time;
    }

    public String getIdentifying() {
        return identifying;
    }

    public void setIdentifying(String identifying) {
        this.identifying = identifying;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }
}
