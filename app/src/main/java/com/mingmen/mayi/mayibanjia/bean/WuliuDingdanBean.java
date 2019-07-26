package com.mingmen.mayi.mayibanjia.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/7/22.
 */

public class WuliuDingdanBean {
    private String driverName;
    private String driverPhone;
    private String plateNumber;
    private String wlddState;
    private String marketCity;
    private String specific_address;
    private String wl_order_state;
    private String wl_cars_order_number;
    private String wl_cars_order_id;
    private String wl_cars_id;
    private String new_wl_cars_id;
    private String marketProvince;
    private String arrival_time;
    private String create_time;
    private String wl_cars_state;
    private String cars_type;
    private String carTypeName;
    private String marketName;
    private String remarke;
    private String wl_cars_type_name;
    private String logistics_draw_money;
    private List<Dizhi> dizhilist;

    public String getLogistics_draw_money() {
        return logistics_draw_money;
    }

    public void setLogistics_draw_money(String logistics_draw_money) {
        this.logistics_draw_money = logistics_draw_money;
    }

    public String getNew_wl_cars_id() {
        return new_wl_cars_id;
    }

    public void setNew_wl_cars_id(String new_wl_cars_id) {
        this.new_wl_cars_id = new_wl_cars_id;
    }

    public String getWl_cars_id() {
        return wl_cars_id;
    }

    public void setWl_cars_id(String wl_cars_id) {
        this.wl_cars_id = wl_cars_id;
    }

    public String getWl_cars_type_name() {
        return wl_cars_type_name;
    }

    public void setWl_cars_type_name(String wl_cars_type_name) {
        this.wl_cars_type_name = wl_cars_type_name;
    }

    public String getRemarke() {
        return remarke;
    }

    public void setRemarke(String remarke) {
        this.remarke = remarke;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getWlddState() {
        return wlddState;
    }

    public void setWlddState(String wlddState) {
        this.wlddState = wlddState;
    }

    public String getMarketCity() {
        return marketCity;
    }

    public void setMarketCity(String marketCity) {
        this.marketCity = marketCity;
    }

    public String getSpecific_address() {
        return specific_address;
    }

    public void setSpecific_address(String specific_address) {
        this.specific_address = specific_address;
    }

    public String getWl_order_state() {
        return wl_order_state;
    }

    public void setWl_order_state(String wl_order_state) {
        this.wl_order_state = wl_order_state;
    }

    public String getWl_cars_order_number() {
        return wl_cars_order_number;
    }

    public void setWl_cars_order_number(String wl_cars_order_number) {
        this.wl_cars_order_number = wl_cars_order_number;
    }

    public String getWl_cars_order_id() {
        return wl_cars_order_id;
    }

    public void setWl_cars_order_id(String wl_cars_order_id) {
        this.wl_cars_order_id = wl_cars_order_id;
    }

    public String getMarketProvince() {
        return marketProvince;
    }

    public void setMarketProvince(String marketProvince) {
        this.marketProvince = marketProvince;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getWl_cars_state() {
        return wl_cars_state;
    }

    public void setWl_cars_state(String wl_cars_state) {
        this.wl_cars_state = wl_cars_state;
    }

    public String getCars_type() {
        return cars_type;
    }

    public void setCars_type(String cars_type) {
        this.cars_type = cars_type;
    }

    public String getCarTypeName() {
        return carTypeName;
    }

    public void setCarTypeName(String carTypeName) {
        this.carTypeName = carTypeName;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public List<Dizhi> getDizhilist() {
        return dizhilist;
    }

    public void setDizhilist(List<Dizhi> dizhilist) {
        this.dizhilist = dizhilist;
    }

    public static class Dizhi{
        private String province;
        private String city;
        private String region;
        private String street;
        private String specific_address;

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getSpecific_address() {
            return specific_address;
        }

        public void setSpecific_address(String specific_address) {
            this.specific_address = specific_address;
        }
    }
}
