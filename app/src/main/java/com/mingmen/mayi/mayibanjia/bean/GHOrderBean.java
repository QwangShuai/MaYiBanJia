package com.mingmen.mayi.mayibanjia.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/9/28.
 */

public class GHOrderBean {

    /**
     * state : 待发货
     * remarke : null
     * user_token : null
     * order_id : null
     * total_price : 5.00
     * arrival_time : 午12点-13点
     * order_number : null
     * deliver_address : null
     * commodity_name : null
     * gy_order_number : cfe691d99dfe402581d9734c54350f0d
     * status_type : 1
     * gy_order_id : 9d429d6319964da8947a4b5dbb6e1891
     * create_time : 2018-09-28 09:56:56
     * gy_company_id : null
     * driver_name : null
     * driver_phone : null
     * wl_order_state : null
     * plate_number : null
     * ct_company_id : null
     * change_time : null
     * wl_sweep_time : null
     * changer : null
     * zilist : [{"remarke":"11","all_price":6,"spec_name":"L","user_token":null,"acount":1,"order_id":null,"price":5,"commodity_id":"98fabd9c277445ed917f5b8e0122108b","commodity_name":"皮球柿子","gy_order_id":"9d429d6319964da8947a4b5dbb6e1891","create_time":null,"gy_order_detal_id":"4807bc14e18c4fc2aa9a89876ceb0791","acount_spec":"1/L","order_details_id":null}]
     */

    private String state;
    private Object remarke;
    private Object user_token;
    private Object order_id;
    private String total_price;
    private String arrival_time;
    private Object order_number;
    private Object deliver_address;
    private Object commodity_name;
    private String gy_order_number;
    private String status_type;
    private String gy_order_id;
    private String create_time;
    private Object gy_company_id;
    private Object driver_name;
    private Object driver_phone;
    private Object wl_order_state;
    private Object plate_number;
    private Object ct_company_id;
    private Object change_time;
    private Object wl_sweep_time;
    private Object changer;
    private String state_name;
    private String countname;
    private String mj_sweep_time;
    private String company_name;
    private String telephone;
    private String refund;
    private String append_money;

    public String getAppend_money() {
        return append_money;
    }

    public void setAppend_money(String append_money) {
        this.append_money = append_money;
    }

    public String getRefund() {
        return refund;
    }

    public void setRefund(String refund) {
        this.refund = refund;
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

    public String getMj_sweep_time() {
        return mj_sweep_time;
    }

    public void setMj_sweep_time(String mj_sweep_time) {
        this.mj_sweep_time = mj_sweep_time;
    }

    private List<QrCodeBean> list;

    public List<QrCodeBean> getList() {
        return list;
    }

    public void setList(List<QrCodeBean> list) {
        this.list = list;
    }

    public String getCountname() {
        return countname;
    }

    public void setCountname(String countname) {
        this.countname = countname;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    private List<ZilistBean> zilist;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Object getRemarke() {
        return remarke;
    }

    public void setRemarke(Object remarke) {
        this.remarke = remarke;
    }

    public Object getUser_token() {
        return user_token;
    }

    public void setUser_token(Object user_token) {
        this.user_token = user_token;
    }

    public Object getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Object order_id) {
        this.order_id = order_id;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public Object getOrder_number() {
        return order_number;
    }

    public void setOrder_number(Object order_number) {
        this.order_number = order_number;
    }

    public Object getDeliver_address() {
        return deliver_address;
    }

    public void setDeliver_address(Object deliver_address) {
        this.deliver_address = deliver_address;
    }

    public Object getCommodity_name() {
        return commodity_name;
    }

    public void setCommodity_name(Object commodity_name) {
        this.commodity_name = commodity_name;
    }

    public String getGy_order_number() {
        return gy_order_number;
    }

    public void setGy_order_number(String gy_order_number) {
        this.gy_order_number = gy_order_number;
    }

    public String getStatus_type() {
        return status_type;
    }

    public void setStatus_type(String status_type) {
        this.status_type = status_type;
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

    public Object getGy_company_id() {
        return gy_company_id;
    }

    public void setGy_company_id(Object gy_company_id) {
        this.gy_company_id = gy_company_id;
    }

    public Object getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(Object driver_name) {
        this.driver_name = driver_name;
    }

    public Object getDriver_phone() {
        return driver_phone;
    }

    public void setDriver_phone(Object driver_phone) {
        this.driver_phone = driver_phone;
    }

    public Object getWl_order_state() {
        return wl_order_state;
    }

    public void setWl_order_state(Object wl_order_state) {
        this.wl_order_state = wl_order_state;
    }

    public Object getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(Object plate_number) {
        this.plate_number = plate_number;
    }

    public Object getCt_company_id() {
        return ct_company_id;
    }

    public void setCt_company_id(Object ct_company_id) {
        this.ct_company_id = ct_company_id;
    }

    public Object getChange_time() {
        return change_time;
    }

    public void setChange_time(Object change_time) {
        this.change_time = change_time;
    }

    public Object getWl_sweep_time() {
        return wl_sweep_time;
    }

    public void setWl_sweep_time(Object wl_sweep_time) {
        this.wl_sweep_time = wl_sweep_time;
    }

    public Object getChanger() {
        return changer;
    }

    public void setChanger(Object changer) {
        this.changer = changer;
    }

    public List<ZilistBean> getZilist() {
        return zilist;
    }

    public void setZilist(List<ZilistBean> zilist) {
        this.zilist = zilist;
    }

    public static class ZilistBean {
        /**
         * remarke : 11
         * all_price : 6.0
         * spec_name : L
         * user_token : null
         * acount : 1
         * order_id : null
         * price : 5.0
         * commodity_id : 98fabd9c277445ed917f5b8e0122108b
         * commodity_name : 皮球柿子
         * gy_order_id : 9d429d6319964da8947a4b5dbb6e1891
         * create_time : null
         * gy_order_detal_id : 4807bc14e18c4fc2aa9a89876ceb0791
         * acount_spec : 1/L
         * order_details_id : null
         */

        private String remarke;
        private double all_price;
        private String spec_name;
        private Object user_token;
        private int acount;
        private Object order_id;
        private double price;
        private String commodity_id;
        private String commodity_name;
        private String gy_order_id;
        private Object create_time;
        private String gy_order_detal_id;
        private String acount_spec;
        private Object order_details_id;
        private String classify_name;

        public String getClassify_name() {
            return classify_name;
        }

        public void setClassify_name(String classify_name) {
            this.classify_name = classify_name;
        }

        public String getRemarke() {
            return remarke;
        }

        public void setRemarke(String remarke) {
            this.remarke = remarke;
        }

        public double getAll_price() {
            return all_price;
        }

        public void setAll_price(double all_price) {
            this.all_price = all_price;
        }

        public String getSpec_name() {
            return spec_name;
        }

        public void setSpec_name(String spec_name) {
            this.spec_name = spec_name;
        }

        public Object getUser_token() {
            return user_token;
        }

        public void setUser_token(Object user_token) {
            this.user_token = user_token;
        }

        public int getAcount() {
            return acount;
        }

        public void setAcount(int acount) {
            this.acount = acount;
        }

        public Object getOrder_id() {
            return order_id;
        }

        public void setOrder_id(Object order_id) {
            this.order_id = order_id;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getCommodity_id() {
            return commodity_id;
        }

        public void setCommodity_id(String commodity_id) {
            this.commodity_id = commodity_id;
        }

        public String getCommodity_name() {
            return commodity_name;
        }

        public void setCommodity_name(String commodity_name) {
            this.commodity_name = commodity_name;
        }

        public String getGy_order_id() {
            return gy_order_id;
        }

        public void setGy_order_id(String gy_order_id) {
            this.gy_order_id = gy_order_id;
        }

        public Object getCreate_time() {
            return create_time;
        }

        public void setCreate_time(Object create_time) {
            this.create_time = create_time;
        }

        public String getGy_order_detal_id() {
            return gy_order_detal_id;
        }

        public void setGy_order_detal_id(String gy_order_detal_id) {
            this.gy_order_detal_id = gy_order_detal_id;
        }

        public String getAcount_spec() {
            return acount_spec;
        }

        public void setAcount_spec(String acount_spec) {
            this.acount_spec = acount_spec;
        }

        public Object getOrder_details_id() {
            return order_details_id;
        }

        public void setOrder_details_id(Object order_details_id) {
            this.order_details_id = order_details_id;
        }
    }
}
