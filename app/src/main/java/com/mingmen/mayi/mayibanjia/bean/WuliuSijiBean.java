package com.mingmen.mayi.mayibanjia.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/7/26.
 */

public class WuliuSijiBean {

    /**
     * state1 : 0
     * state2 : 0
     * ddList : [{"driverName":"假","wl_cars_order_number":"111309340624369","user_token":null,"specific_address":"黑龙江省哈尔滨市南岗区南岗区哈南205号","city":null,"company_id":null,"freight_fee":43,"wl_cars_state":"1","gy_order_id":"f8973e762f214344983ba9c234c6ae21,ba86686e85d34cd3b90f1b576149f87c,eed8b098e7394dc999fd601417c4e4c1,27fa2171edb944e9ae5e2ad26dd3156e","arrival_time":"午12点-13点","market_id":null,"create_time":null,"scanCount":"0","packCount":"3","change_time":null,"marketCity":"230100","marketProvince":"230000","wl_cars_id":"b793efc70f8547c3af237d042cc86c45","gyMsgList":null,"isTrue":"1","ctAddress":null,"ctPhone":null,"wl_cars_order_id":"155151f58ddb4a3cad04e7a8d9cc1afa","account":null,"identifying":null,"surplus_weight":null,"street":null,"cars_type":"1","totalWeight":"6.00","marketName":"哈达市场","carTypeName":"小型面包","plateNumber":"他","driverPhone":"13836795642","qy_company_id":null,"ct_address_id":null,"ctName":null,"ct_order_id":null,"wl_order_state":"","settle_accounts_state":null}]
     * state3 : 0
     * state4 : 0
     */

    private int state1;
    private int state2;
    private int state3;
    private int state4;
    private List<DdListBean> ddList;

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

    public List<DdListBean> getDdList() {
        return ddList;
    }

    public void setDdList(List<DdListBean> ddList) {
        this.ddList = ddList;
    }

    public static class DdListBean {
        /**
         * driverName : 假
         * wl_cars_order_number : 111309340624369
         * user_token : null
         * specific_address : 黑龙江省哈尔滨市南岗区南岗区哈南205号
         * city : null
         * company_id : null
         * freight_fee : 43
         * wl_cars_state : 1
         * gy_order_id : f8973e762f214344983ba9c234c6ae21,ba86686e85d34cd3b90f1b576149f87c,eed8b098e7394dc999fd601417c4e4c1,27fa2171edb944e9ae5e2ad26dd3156e
         * arrival_time : 午12点-13点
         * market_id : null
         * create_time : null
         * scanCount : 0
         * packCount : 3
         * change_time : null
         * marketCity : 230100
         * marketProvince : 230000
         * wl_cars_id : b793efc70f8547c3af237d042cc86c45
         * gyMsgList : null
         * isTrue : 1
         * ctAddress : null
         * ctPhone : null
         * wl_cars_order_id : 155151f58ddb4a3cad04e7a8d9cc1afa
         * account : null
         * identifying : null
         * surplus_weight : null
         * street : null
         * cars_type : 1
         * totalWeight : 6.00
         * marketName : 哈达市场
         * carTypeName : 小型面包
         * plateNumber : 他
         * driverPhone : 13836795642
         * qy_company_id : null
         * ct_address_id : null
         * ctName : null
         * ct_order_id : null
         * wl_order_state :
         * settle_accounts_state : null
         */

        private String driverName;
        private String wl_cars_order_number;
        private String specific_address;
        private double freight_fee;
        private String wl_cars_state;
        private String gy_order_id;
        private String arrival_time;
        private String scanCount;
        private String packCount;
        private String marketCity;
        private String marketProvince;
        private String wl_cars_id;
        private String isTrue;
        private String wl_cars_order_id;
        private String cars_type;
        private String totalWeight;
        private String marketName;
        private String carTypeName;
        private String plateNumber;
        private String driverPhone;
        private String wl_order_state;
        private String create_time;
        private String wl_cars_type_name;

        public String getWl_cars_type_name() {
            return wl_cars_type_name;
        }

        public void setWl_cars_type_name(String wl_cars_type_name) {
            this.wl_cars_type_name = wl_cars_type_name;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getDriverName() {
            return driverName;
        }

        public void setDriverName(String driverName) {
            this.driverName = driverName;
        }

        public String getWl_cars_order_number() {
            return wl_cars_order_number;
        }

        public void setWl_cars_order_number(String wl_cars_order_number) {
            this.wl_cars_order_number = wl_cars_order_number;
        }

        public String getSpecific_address() {
            return specific_address;
        }

        public void setSpecific_address(String specific_address) {
            this.specific_address = specific_address;
        }

        public double getFreight_fee() {
            return freight_fee;
        }

        public void setFreight_fee(double freight_fee) {
            this.freight_fee = freight_fee;
        }

        public String getWl_cars_state() {
            return wl_cars_state;
        }

        public void setWl_cars_state(String wl_cars_state) {
            this.wl_cars_state = wl_cars_state;
        }

        public String getGy_order_id() {
            return gy_order_id;
        }

        public void setGy_order_id(String gy_order_id) {
            this.gy_order_id = gy_order_id;
        }

        public String getArrival_time() {
            return arrival_time;
        }

        public void setArrival_time(String arrival_time) {
            this.arrival_time = arrival_time;
        }

        public String getScanCount() {
            return scanCount;
        }

        public void setScanCount(String scanCount) {
            this.scanCount = scanCount;
        }

        public String getPackCount() {
            return packCount;
        }

        public void setPackCount(String packCount) {
            this.packCount = packCount;
        }

        public String getMarketCity() {
            return marketCity;
        }

        public void setMarketCity(String marketCity) {
            this.marketCity = marketCity;
        }

        public String getMarketProvince() {
            return marketProvince;
        }

        public void setMarketProvince(String marketProvince) {
            this.marketProvince = marketProvince;
        }

        public String getWl_cars_id() {
            return wl_cars_id;
        }

        public void setWl_cars_id(String wl_cars_id) {
            this.wl_cars_id = wl_cars_id;
        }

        public String getIsTrue() {
            return isTrue;
        }

        public void setIsTrue(String isTrue) {
            this.isTrue = isTrue;
        }

        public String getWl_cars_order_id() {
            return wl_cars_order_id;
        }

        public void setWl_cars_order_id(String wl_cars_order_id) {
            this.wl_cars_order_id = wl_cars_order_id;
        }

        public String getCars_type() {
            return cars_type;
        }

        public void setCars_type(String cars_type) {
            this.cars_type = cars_type;
        }

        public String getTotalWeight() {
            return totalWeight;
        }

        public void setTotalWeight(String totalWeight) {
            this.totalWeight = totalWeight;
        }

        public String getMarketName() {
            return marketName;
        }

        public void setMarketName(String marketName) {
            this.marketName = marketName;
        }

        public String getCarTypeName() {
            return carTypeName;
        }

        public void setCarTypeName(String carTypeName) {
            this.carTypeName = carTypeName;
        }

        public String getPlateNumber() {
            return plateNumber;
        }

        public void setPlateNumber(String plateNumber) {
            this.plateNumber = plateNumber;
        }

        public String getDriverPhone() {
            return driverPhone;
        }

        public void setDriverPhone(String driverPhone) {
            this.driverPhone = driverPhone;
        }

        public String getWl_order_state() {
            return wl_order_state;
        }

        public void setWl_order_state(String wl_order_state) {
            this.wl_order_state = wl_order_state;
        }
    }
}
