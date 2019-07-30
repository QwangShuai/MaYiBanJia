package com.mingmen.mayi.mayibanjia.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/11/20.
 */

public class YunFeiJieSuanBean {

    /**
     * ddList : [{"driverName":null,"market_id":null,"create_time":null,"account":null,"identifying":null,"surplus_weight":null,"street":null,"cars_type":null,"scanCount":null,"packCount":null,"settle_accounts_state":"0","wl_cars_state":null,"gy_order_id":null,"arrival_time":null,"user_token":null,"company_id":null,"city":null,"specific_address":"黑龙江省哈尔滨市南岗区南岗区哈南205号","wl_cars_order_number":"111910223608180","gyMsgList":null,"isTrue":null,"ctAddress":"黑龙江省哈尔滨市南岗区林兴路林兴路1号","ctPhone":null,"ctName":null,"ct_address_id":null,"qy_company_id":null,"driverPhone":null,"plateNumber":null,"carTypeName":null,"marketName":null,"totalWeight":null,"ct_order_id":null,"wl_order_state":null,"wl_cars_order_id":null,"change_time":"","marketCity":null,"marketProvince":null,"wl_cars_id":null,"freight_fee":37}]
     * count : 2
     * count2 : 0
     * count1 : 0
     * count3 : 2
     */

    private int count;
    private int count2;
    private int count1;
    private int count3;
    private List<DdListBean> ddList;

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

    public int getCount3() {
        return count3;
    }

    public void setCount3(int count3) {
        this.count3 = count3;
    }

    public List<DdListBean> getDdList() {
        return ddList;
    }

    public void setDdList(List<DdListBean> ddList) {
        this.ddList = ddList;
    }

    public static class DdListBean {
        /**
         * driverName : null
         * market_id : null
         * create_time : null
         * account : null
         * identifying : null
         * surplus_weight : null
         * street : null
         * cars_type : null
         * scanCount : null
         * packCount : null
         * settle_accounts_state : 0
         * wl_cars_state : null
         * gy_order_id : null
         * arrival_time : null
         * user_token : null
         * company_id : null
         * city : null
         * specific_address : 黑龙江省哈尔滨市南岗区南岗区哈南205号
         * wl_cars_order_number : 111910223608180
         * gyMsgList : null
         * isTrue : null
         * ctAddress : 黑龙江省哈尔滨市南岗区林兴路林兴路1号
         * ctPhone : null
         * ctName : null
         * ct_address_id : null
         * qy_company_id : null
         * driverPhone : null
         * plateNumber : null
         * carTypeName : null
         * marketName : null
         * totalWeight : null
         * ct_order_id : null
         * wl_order_state : null
         * wl_cars_order_id : null
         * change_time :
         * marketCity : null
         * marketProvince : null
         * wl_cars_id : null
         * freight_fee : 37
         */

        private String driverName;
        private Object market_id;
        private Object create_time;
        private Object account;
        private Object identifying;
        private Object surplus_weight;
        private Object street;
        private Object cars_type;
        private Object scanCount;
        private Object packCount;
        private String settle_accounts_state;
        private Object wl_cars_state;
        private Object gy_order_id;
        private Object arrival_time;
        private Object user_token;
        private Object company_id;
        private Object city;
        private String specific_address;
        private String wl_cars_order_number;
        private Object gyMsgList;
        private Object isTrue;
        private String ctAddress;
        private Object ctPhone;
        private Object ctName;
        private Object ct_address_id;
        private Object qy_company_id;
        private String driverPhone;
        private Object plateNumber;
        private Object carTypeName;
        private Object marketName;
        private Object totalWeight;
        private Object ct_order_id;
        private Object wl_order_state;
        private Object wl_cars_order_id;
        private String change_time;
        private Object marketCity;
        private Object marketProvince;
        private Object wl_cars_id;
        private double freight_fee;
        private boolean isXuanzhong;

        public boolean isXuanzhong() {
            return isXuanzhong;
        }

        public void setXuanzhong(boolean xuanzhong) {
            isXuanzhong = xuanzhong;
        }

        public String getDriverName() {
            return driverName;
        }

        public void setDriverName(String driverName) {
            this.driverName = driverName;
        }

        public Object getMarket_id() {
            return market_id;
        }

        public void setMarket_id(Object market_id) {
            this.market_id = market_id;
        }

        public Object getCreate_time() {
            return create_time;
        }

        public void setCreate_time(Object create_time) {
            this.create_time = create_time;
        }

        public Object getAccount() {
            return account;
        }

        public void setAccount(Object account) {
            this.account = account;
        }

        public Object getIdentifying() {
            return identifying;
        }

        public void setIdentifying(Object identifying) {
            this.identifying = identifying;
        }

        public Object getSurplus_weight() {
            return surplus_weight;
        }

        public void setSurplus_weight(Object surplus_weight) {
            this.surplus_weight = surplus_weight;
        }

        public Object getStreet() {
            return street;
        }

        public void setStreet(Object street) {
            this.street = street;
        }

        public Object getCars_type() {
            return cars_type;
        }

        public void setCars_type(Object cars_type) {
            this.cars_type = cars_type;
        }

        public Object getScanCount() {
            return scanCount;
        }

        public void setScanCount(Object scanCount) {
            this.scanCount = scanCount;
        }

        public Object getPackCount() {
            return packCount;
        }

        public void setPackCount(Object packCount) {
            this.packCount = packCount;
        }

        public String getSettle_accounts_state() {
            return settle_accounts_state;
        }

        public void setSettle_accounts_state(String settle_accounts_state) {
            this.settle_accounts_state = settle_accounts_state;
        }

        public Object getWl_cars_state() {
            return wl_cars_state;
        }

        public void setWl_cars_state(Object wl_cars_state) {
            this.wl_cars_state = wl_cars_state;
        }

        public Object getGy_order_id() {
            return gy_order_id;
        }

        public void setGy_order_id(Object gy_order_id) {
            this.gy_order_id = gy_order_id;
        }

        public Object getArrival_time() {
            return arrival_time;
        }

        public void setArrival_time(Object arrival_time) {
            this.arrival_time = arrival_time;
        }

        public Object getUser_token() {
            return user_token;
        }

        public void setUser_token(Object user_token) {
            this.user_token = user_token;
        }

        public Object getCompany_id() {
            return company_id;
        }

        public void setCompany_id(Object company_id) {
            this.company_id = company_id;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public String getSpecific_address() {
            return specific_address;
        }

        public void setSpecific_address(String specific_address) {
            this.specific_address = specific_address;
        }

        public String getWl_cars_order_number() {
            return wl_cars_order_number;
        }

        public void setWl_cars_order_number(String wl_cars_order_number) {
            this.wl_cars_order_number = wl_cars_order_number;
        }

        public Object getGyMsgList() {
            return gyMsgList;
        }

        public void setGyMsgList(Object gyMsgList) {
            this.gyMsgList = gyMsgList;
        }

        public Object getIsTrue() {
            return isTrue;
        }

        public void setIsTrue(Object isTrue) {
            this.isTrue = isTrue;
        }

        public String getCtAddress() {
            return ctAddress;
        }

        public void setCtAddress(String ctAddress) {
            this.ctAddress = ctAddress;
        }

        public Object getCtPhone() {
            return ctPhone;
        }

        public void setCtPhone(Object ctPhone) {
            this.ctPhone = ctPhone;
        }

        public Object getCtName() {
            return ctName;
        }

        public void setCtName(Object ctName) {
            this.ctName = ctName;
        }

        public Object getCt_address_id() {
            return ct_address_id;
        }

        public void setCt_address_id(Object ct_address_id) {
            this.ct_address_id = ct_address_id;
        }

        public Object getQy_company_id() {
            return qy_company_id;
        }

        public void setQy_company_id(Object qy_company_id) {
            this.qy_company_id = qy_company_id;
        }

        public String getDriverPhone() {
            return driverPhone;
        }

        public void setDriverPhone(String driverPhone) {
            this.driverPhone = driverPhone;
        }

        public Object getPlateNumber() {
            return plateNumber;
        }

        public void setPlateNumber(Object plateNumber) {
            this.plateNumber = plateNumber;
        }

        public Object getCarTypeName() {
            return carTypeName;
        }

        public void setCarTypeName(Object carTypeName) {
            this.carTypeName = carTypeName;
        }

        public Object getMarketName() {
            return marketName;
        }

        public void setMarketName(Object marketName) {
            this.marketName = marketName;
        }

        public Object getTotalWeight() {
            return totalWeight;
        }

        public void setTotalWeight(Object totalWeight) {
            this.totalWeight = totalWeight;
        }

        public Object getCt_order_id() {
            return ct_order_id;
        }

        public void setCt_order_id(Object ct_order_id) {
            this.ct_order_id = ct_order_id;
        }

        public Object getWl_order_state() {
            return wl_order_state;
        }

        public void setWl_order_state(Object wl_order_state) {
            this.wl_order_state = wl_order_state;
        }

        public Object getWl_cars_order_id() {
            return wl_cars_order_id;
        }

        public void setWl_cars_order_id(Object wl_cars_order_id) {
            this.wl_cars_order_id = wl_cars_order_id;
        }

        public String getChange_time() {
            return change_time;
        }

        public void setChange_time(String change_time) {
            this.change_time = change_time;
        }

        public Object getMarketCity() {
            return marketCity;
        }

        public void setMarketCity(Object marketCity) {
            this.marketCity = marketCity;
        }

        public Object getMarketProvince() {
            return marketProvince;
        }

        public void setMarketProvince(Object marketProvince) {
            this.marketProvince = marketProvince;
        }

        public Object getWl_cars_id() {
            return wl_cars_id;
        }

        public void setWl_cars_id(Object wl_cars_id) {
            this.wl_cars_id = wl_cars_id;
        }

        public Double getFreight_fee() {
            return freight_fee;
        }

        public void setFreight_fee(double freight_fee) {
            this.freight_fee = freight_fee;
        }
    }
}
