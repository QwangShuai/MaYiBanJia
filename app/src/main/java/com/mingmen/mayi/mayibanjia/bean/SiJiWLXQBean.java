package com.mingmen.mayi.mayibanjia.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/10/10.
 * 司机物流详情
 */

public class SiJiWLXQBean {

    /**
     * company_id : null
     * user_token : null
     * city : null
     * street : null
     * specific_address : null
     * market_id : null
     * identifying : a
     * gy_order_id : 9ddf308e620e49d7ab9bac3c5de9361c
     * wl_cars_id : null
     * wl_cars_order_number : null
     * wl_cars_order_id : null
     * freight_fee : null
     * create_time : 2018-11-19 10:22:36
     * scanCount : null
     * packCount : null
     * wl_cars_state : null
     * marketProvince : null
     * marketCity : null
     * cars_type : null
     * arrival_time : null
     * surplus_weight : null
     * account : null
     * change_time : null
     * wl_order_state : 待取货
     * gyMsgList : [{"company_id":null,"order_id":null,"market_id":null,"identifying":null,"gy_order_id":null,"twocode_id":null,"wl_order_number":null,"is_loading":null,"wl_order_company_id":null,"create_time":null,"scanCount":"0","packCount":"1","gyAddress":"黑龙江省哈尔滨市南岗区南岗区哈南205号","gyPhone":"15774668719","standthree":null}]
     * isTrue : null
     * ctAddress : 黑龙江省哈尔滨市南岗区林兴路林兴路1号
     * ctPhone : 15555455711
     * ctName : 光明
     * settle_accounts_state : null
     * ct_address_id : null
     * qy_company_id : null
     * ct_order_id : c86668d8280d4bdbb151503b5987ceef
     * driverPhone : null
     * plateNumber : null
     * carTypeName : null
     * marketName : null
     * totalWeight : null
     * driverName : null
     */

    private String specific_address;
    private String identifying;
    private String gy_order_id;
    private String create_time;
    private String wl_order_state;
    private String ctAddress;
    private String ctPhone;
    private String ctName;
    private String ct_order_id;
    private String gonglishu;
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private List<GyMsgListBean> gyMsgList;

    public String getGonglishu() {
        return gonglishu;
    }

    public void setGonglishu(String gonglishu) {
        this.gonglishu = gonglishu;
    }


    public String getSpecific_address() {
        return specific_address;
    }

    public void setSpecific_address(String specific_address) {
        this.specific_address = specific_address;
    }


    public String getIdentifying() {
        return identifying;
    }

    public void setIdentifying(String identifying) {
        this.identifying = identifying;
    }

    public String getGy_order_id() {
        return gy_order_id;
    }

    public void setGy_order_id(String gy_order_id) {
        this.gy_order_id = gy_order_id;
    }


    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }


    public String getWl_order_state() {
        return wl_order_state;
    }

    public void setWl_order_state(String wl_order_state) {
        this.wl_order_state = wl_order_state;
    }

    public String getCtAddress() {
        return ctAddress;
    }

    public void setCtAddress(String ctAddress) {
        this.ctAddress = ctAddress;
    }

    public String getCtPhone() {
        return ctPhone;
    }

    public void setCtPhone(String ctPhone) {
        this.ctPhone = ctPhone;
    }

    public String getCtName() {
        return ctName;
    }

    public void setCtName(String ctName) {
        this.ctName = ctName;
    }




    public String getCt_order_id() {
        return ct_order_id;
    }

    public void setCt_order_id(String ct_order_id) {
        this.ct_order_id = ct_order_id;
    }


    public List<GyMsgListBean> getGyMsgList() {
        return gyMsgList;
    }

    public void setGyMsgList(List<GyMsgListBean> gyMsgList) {
        this.gyMsgList = gyMsgList;
    }

    public static class GyMsgListBean {
        /**
         * company_id : null
         * order_id : null
         * market_id : null
         * identifying : null
         * gy_order_id : null
         * twocode_id : null
         * wl_order_number : null
         * is_loading : null
         * wl_order_company_id : null
         * create_time : null
         * scanCount : 0
         * packCount : 1
         * gyAddress : 黑龙江省哈尔滨市南岗区南岗区哈南205号
         * gyPhone : 15774668719
         * standthree : null
         */

        private Object company_id;
        private Object order_id;
        private Object market_id;
        private Object identifying;
        private String gy_order_id;
        private Object twocode_id;
        private Object wl_order_number;
        private Object is_loading;
        private Object wl_order_company_id;
        private Object create_time;
        private String scanCount;
        private String packCount;
        private String gyAddress;
        private String gyPhone;
        private String business_state;
        private Object standthree;

        public String getBusiness_state() {
            return business_state;
        }

        public void setBusiness_state(String business_state) {
            this.business_state = business_state;
        }

        public Object getCompany_id() {
            return company_id;
        }

        public void setCompany_id(Object company_id) {
            this.company_id = company_id;
        }

        public Object getOrder_id() {
            return order_id;
        }

        public void setOrder_id(Object order_id) {
            this.order_id = order_id;
        }

        public Object getMarket_id() {
            return market_id;
        }

        public void setMarket_id(Object market_id) {
            this.market_id = market_id;
        }

        public Object getIdentifying() {
            return identifying;
        }

        public void setIdentifying(Object identifying) {
            this.identifying = identifying;
        }

        public String getGy_order_id() {
            return gy_order_id;
        }

        public void setGy_order_id(String gy_order_id) {
            this.gy_order_id = gy_order_id;
        }

        public Object getTwocode_id() {
            return twocode_id;
        }

        public void setTwocode_id(Object twocode_id) {
            this.twocode_id = twocode_id;
        }

        public Object getWl_order_number() {
            return wl_order_number;
        }

        public void setWl_order_number(Object wl_order_number) {
            this.wl_order_number = wl_order_number;
        }

        public Object getIs_loading() {
            return is_loading;
        }

        public void setIs_loading(Object is_loading) {
            this.is_loading = is_loading;
        }

        public Object getWl_order_company_id() {
            return wl_order_company_id;
        }

        public void setWl_order_company_id(Object wl_order_company_id) {
            this.wl_order_company_id = wl_order_company_id;
        }

        public Object getCreate_time() {
            return create_time;
        }

        public void setCreate_time(Object create_time) {
            this.create_time = create_time;
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

        public String getGyAddress() {
            return gyAddress;
        }

        public void setGyAddress(String gyAddress) {
            this.gyAddress = gyAddress;
        }

        public String getGyPhone() {
            return gyPhone;
        }

        public void setGyPhone(String gyPhone) {
            this.gyPhone = gyPhone;
        }

        public Object getStandthree() {
            return standthree;
        }

        public void setStandthree(Object standthree) {
            this.standthree = standthree;
        }
    }
}

