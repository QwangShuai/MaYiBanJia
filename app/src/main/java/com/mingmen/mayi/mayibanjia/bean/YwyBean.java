package com.mingmen.mayi.mayibanjia.bean;

/**
 * Created by Administrator on 2019/3/26.
 */

public class YwyBean {

    /**
     * name : 测试业务员
     * all_gy : 16
     * no_ct : 6
     * no_gy : 12
     * no_she : 5
     * all_she : 10
     * all_ct : 17
     * all_count : 43
     * telephone : 13399998888
     * specific_address : 黑龙江省哈尔滨市南岗区
     * order_count : 2
     * registered_ct : 11
     * registered_gy : 4
     * registered_she : 5
     */

    private String name;
    private int all_gy;
    private int no_ct;
    private int no_gy;
    private int no_she;
    private int all_she;
    private int all_ct;
    private int all_count;
    private String telephone;
    private String specific_address;
    private String salesman_code;
    private String salesman_level;
    private int order_count;
    private int registered_ct;
    private int registered_gy;
    private int registered_she;
    private int registered_all;
    private int no_all;
    private int yjcount;//预警订单数量
    private int waitQh_count;//待取货订单数量
    private int waitSH_count;//待送货订单数量
    private int waitWC_count;//已完成订单数量
    private int zcl_count;//自处理订单数量

    public String getSalesman_code() {
        return salesman_code;
    }

    public void setSalesman_code(String salesman_code) {
        this.salesman_code = salesman_code;
    }

    public int getYjcount() {
        return yjcount;
    }

    public void setYjcount(int yjcount) {
        this.yjcount = yjcount;
    }

    public int getWaitQh_count() {
        return waitQh_count;
    }

    public void setWaitQh_count(int waitQh_count) {
        this.waitQh_count = waitQh_count;
    }

    public int getWaitSH_count() {
        return waitSH_count;
    }

    public void setWaitSH_count(int waitSH_count) {
        this.waitSH_count = waitSH_count;
    }

    public int getWaitWC_count() {
        return waitWC_count;
    }

    public void setWaitWC_count(int waitWC_count) {
        this.waitWC_count = waitWC_count;
    }

    public int getZcl_count() {
        return zcl_count;
    }

    public void setZcl_count(int zcl_count) {
        this.zcl_count = zcl_count;
    }

    public int getRegistered_all() {
        return registered_all;
    }

    public void setRegistered_all(int registered_all) {
        this.registered_all = registered_all;
    }

    public int getNo_all() {
        return no_all;
    }

    public void setNo_all(int no_all) {
        this.no_all = no_all;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAll_gy() {
        return all_gy;
    }

    public void setAll_gy(int all_gy) {
        this.all_gy = all_gy;
    }

    public int getNo_ct() {
        return no_ct;
    }

    public void setNo_ct(int no_ct) {
        this.no_ct = no_ct;
    }

    public int getNo_gy() {
        return no_gy;
    }

    public void setNo_gy(int no_gy) {
        this.no_gy = no_gy;
    }

    public int getNo_she() {
        return no_she;
    }

    public void setNo_she(int no_she) {
        this.no_she = no_she;
    }

    public int getAll_she() {
        return all_she;
    }

    public void setAll_she(int all_she) {
        this.all_she = all_she;
    }

    public int getAll_ct() {
        return all_ct;
    }

    public void setAll_ct(int all_ct) {
        this.all_ct = all_ct;
    }

    public int getAll_count() {
        return all_count;
    }

    public void setAll_count(int all_count) {
        this.all_count = all_count;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSpecific_address() {
        return specific_address;
    }

    public void setSpecific_address(String specific_address) {
        this.specific_address = specific_address;
    }

    public int getOrder_count() {
        return order_count;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }

    public int getRegistered_ct() {
        return registered_ct;
    }

    public void setRegistered_ct(int registered_ct) {
        this.registered_ct = registered_ct;
    }

    public int getRegistered_gy() {
        return registered_gy;
    }

    public void setRegistered_gy(int registered_gy) {
        this.registered_gy = registered_gy;
    }

    public int getRegistered_she() {
        return registered_she;
    }

    public void setRegistered_she(int registered_she) {
        this.registered_she = registered_she;
    }

    public String getSalesman_level() {
        return salesman_level;
    }

    public void setSalesman_level(String salesman_level) {
        this.salesman_level = salesman_level;
    }
}
