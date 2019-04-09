package com.mingmen.mayi.mayibanjia.bean;

/**
 * Created by Administrator on 2019/4/9.
 */

public class SqscFuzeren {

    private int yjcount;//预警订单数量
    private int wait_count;//待确认订单数量
    private int waitQh_count;//待取货订单数量
    private int waitSH_count;//待送货订单数量
    private int waitWC_count;//已完成订单数量
    private int zcl_count;//自处理订单数量

    private String name;//名字
    private String telephone;//电话
    private String specific_address;//地址

    public int getZcl_count() {
        return zcl_count;
    }

    public void setZcl_count(int zcl_count) {
        this.zcl_count = zcl_count;
    }

    public int getYjcount() {
        return yjcount;
    }

    public void setYjcount(int yjcount) {
        this.yjcount = yjcount;
    }

    public int getWait_count() {
        return wait_count;
    }

    public void setWait_count(int wait_count) {
        this.wait_count = wait_count;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
