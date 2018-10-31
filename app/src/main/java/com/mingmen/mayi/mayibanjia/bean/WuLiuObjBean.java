package com.mingmen.mayi.mayibanjia.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/10/8.
 */

public class WuLiuObjBean<T> {

    /**
     * ddList : [{"driverName":"","wl_order_state":null,"wl_cars_order_id":"14c403efc03e4c6abaff03e38aa0a42b","new_wl_cars_id":null,"ct_address_id":null,"qy_company_id":null,"ct_order_id":"6578772ec2874365a2f6c6b266115168","driverPhone":"","plateNumber":"","carTypeName":"小型面包","totalWeight":"804.00","wl_cars_id":null,"marketCity":"230100","cars_type":"1","specific_address":"黑龙江省哈尔滨市南岗区南岗区哈南205号","freight_fee":0.23,"arrival_time":"午12点-13点","gy_order_id":null,"create_time":null,"surplus_weight":null,"wl_cars_order_number":"180929175033939","wl_cars_state":"0","marketProvince":"230000","user_token":null,"city":null,"marketName":"哈达市场","market_id":null,"street":null}]
     * count : 1
     * count2 : 0
     * count1 : 0
     * count0 : 1
     */

    private int count;
    private int count2;
    private int count1;
    private int count0;
    private int state1;
    private int state2;
    private int state3;
    private int state4;

    public int getState1() {
        return state1;
    }

    public void setState1(int state1) {
        this.state1 = state1;
    }

    public int getState2() {
        return state2;
    }

    public void setState2(int state2) {
        this.state2 = state2;
    }

    public int getState3() {
        return state3;
    }

    public void setState3(int state3) {
        this.state3 = state3;
    }

    public int getState4() {
        return state4;
    }

    public void setState4(int state4) {
        this.state4 = state4;
    }

    private List<T> ddList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount2() {
        return count2;
    }

    public void setCount2(int count2) {
        this.count2 = count2;
    }

    public int getCount1() {
        return count1;
    }

    public void setCount1(int count1) {
        this.count1 = count1;
    }

    public int getCount0() {
        return count0;
    }

    public void setCount0(int count0) {
        this.count0 = count0;
    }

    public List<T> getDdList() {
        return ddList;
    }

    public void setDdList(List<T> ddList) {
        this.ddList = ddList;
    }

    public static class DdListBean {
    }
}
