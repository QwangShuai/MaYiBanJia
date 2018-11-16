package com.mingmen.mayi.mayibanjia.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/28/028.
 */

public class DingDanBean {

    /**
     * state : 402
     * type : null
     * account_id : 360a206ff73540d6ac747ded1c6423a7
     * user_token : null
     * url : null
     * order_id : 1
     * creater : null
     * company_id : 21
     * company_name : null
     * commodity_id : 21
     * deliver_address : 75bca7ceb05e4b668411f36d711cc451
     * create_time : null
     * commodity_name : null
     * end_time : 1
     * list : [{"count":null,"url":"FvHTbYSBTEA4B7J-3xVnY6CgpqZm","order_id":null,"company_name":"古色古香","commodity_id":"31","create_time":null,"commodity_name":"肉类","spec_name":"斤","price":3,"acount":10,"all_price":null,"total_weight":null,"order_details_id":null},{"count":null,"url":null,"order_id":null,"company_name":"古色古香","commodity_id":"22","create_time":null,"commodity_name":"肉类","spec_name":"斤","price":11,"acount":20,"all_price":null,"total_weight":null,"order_details_id":null},{"count":null,"url":"FjYKT87t3tHUIjXIjAMubhAfo-Lg","order_id":null,"company_name":"古色古香","commodity_id":null,"create_time":null,"commodity_name":null,"spec_name":"斤","price":3,"acount":10,"all_price":null,"total_weight":null,"order_details_id":null}]
     * spec_name : null
     * pirce : null
     * price : null
     * acount : null
     * pay_type : null
     * revise : null
     * remarke : null
     * change_time : null
     * status_type : 0
     * order_number : 1231
     * total_price : 532
     * balance_pice : 0
     * freight_fee : 50
     */

    private String state;
    private Object type;
    private String account_id;
    private Object user_token;
    private Object url;
    private String order_id;
    private Object creater;
    private String company_id;
    private Object company_name;
    private String commodity_id;
    private String deliver_address;
    private Object create_time;
    private Object commodity_name;
    private String end_time;
    private Object spec_name;
    private Object pirce;
    private Object price;
    private Object acount;
    private Object pay_type;
    private Object revise;
    private Object remarke;
    private Object change_time;
    private String status_type;
    private String order_number;
    private String total_price;
    private int balance_pice;
    private int freight_fee;
    private String gy_order_id;
    private String scanState;//扫码状态 0:已扫完1:未扫完
    private String packCount;//包装个数
    private String scanCount;//扫码个数
    private String shichang;//市场商品数量

    public String getShichang() {
        return shichang;
    }

    public void setShichang(String shichang) {
        this.shichang = shichang;
    }

    private List<DdShichangBean> marklist;

    public String getGy_order_id() {
        return gy_order_id;
    }

    public void setGy_order_id(String gy_order_id) {
        this.gy_order_id = gy_order_id;
    }

    public String getScanState() {
        return scanState;
    }

    public void setScanState(String scanState) {
        this.scanState = scanState;
    }

    public String getPackCount() {
        return packCount;
    }

    public void setPackCount(String packCount) {
        this.packCount = packCount;
    }

    public String getScanCount() {
        return scanCount;
    }

    public void setScanCount(String scanCount) {
        this.scanCount = scanCount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public Object getUser_token() {
        return user_token;
    }

    public void setUser_token(Object user_token) {
        this.user_token = user_token;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Object getCreater() {
        return creater;
    }

    public void setCreater(Object creater) {
        this.creater = creater;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public Object getCompany_name() {
        return company_name;
    }

    public void setCompany_name(Object company_name) {
        this.company_name = company_name;
    }

    public String getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(String commodity_id) {
        this.commodity_id = commodity_id;
    }

    public String getDeliver_address() {
        return deliver_address;
    }

    public void setDeliver_address(String deliver_address) {
        this.deliver_address = deliver_address;
    }

    public Object getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Object create_time) {
        this.create_time = create_time;
    }

    public Object getCommodity_name() {
        return commodity_name;
    }

    public void setCommodity_name(Object commodity_name) {
        this.commodity_name = commodity_name;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public Object getSpec_name() {
        return spec_name;
    }

    public void setSpec_name(Object spec_name) {
        this.spec_name = spec_name;
    }

    public Object getPirce() {
        return pirce;
    }

    public void setPirce(Object pirce) {
        this.pirce = pirce;
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public Object getAcount() {
        return acount;
    }

    public void setAcount(Object acount) {
        this.acount = acount;
    }

    public Object getPay_type() {
        return pay_type;
    }

    public void setPay_type(Object pay_type) {
        this.pay_type = pay_type;
    }

    public Object getRevise() {
        return revise;
    }

    public void setRevise(Object revise) {
        this.revise = revise;
    }

    public Object getRemarke() {
        return remarke;
    }

    public void setRemarke(Object remarke) {
        this.remarke = remarke;
    }

    public Object getChange_time() {
        return change_time;
    }

    public void setChange_time(Object change_time) {
        this.change_time = change_time;
    }

    public String getStatus_type() {
        return status_type;
    }

    public void setStatus_type(String status_type) {
        this.status_type = status_type;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public int getBalance_pice() {
        return balance_pice;
    }

    public void setBalance_pice(int balance_pice) {
        this.balance_pice = balance_pice;
    }

    public int getFreight_fee() {
        return freight_fee;
    }

    public void setFreight_fee(int freight_fee) {
        this.freight_fee = freight_fee;
    }

    public List<DdShichangBean> getList() {
        return marklist;
    }

    public void setList(List<DdShichangBean> marklist) {
        this.marklist = marklist;
    }

//    public static class ListBean {
//        /**
//         * count : null
//         * url : FvHTbYSBTEA4B7J-3xVnY6CgpqZm
//         * order_id : null
//         * company_name : 古色古香
//         * commodity_id : 31
//         * create_time : null
//         * commodity_name : 肉类
//         * spec_name : 斤
//         * price : 3
//         * acount : 10
//         * all_price : null
//         * total_weight : null
//         * order_details_id : null
//         */
//
//        private Object count;
//        private String url;
//        private Object order_id;
//        private String company_name;
//        private String commodity_id;
//        private Object create_time;
//        private String commodity_name;
//        private String spec_name;
//        private int price;
//        private int acount;
//        private Object all_price;
//        private Object total_weight;
//        private Object order_details_id;
//
//        public Object getCount() {
//            return count;
//        }
//
//        public void setCount(Object count) {
//            this.count = count;
//        }
//
//        public String getUrl() {
//            return url;
//        }
//
//        public void setUrl(String url) {
//            this.url = url;
//        }
//
//        public Object getOrder_id() {
//            return order_id;
//        }
//
//        public void setOrder_id(Object order_id) {
//            this.order_id = order_id;
//        }
//
//        public String getCompany_name() {
//            return company_name;
//        }
//
//        public void setCompany_name(String company_name) {
//            this.company_name = company_name;
//        }
//
//        public String getCommodity_id() {
//            return commodity_id;
//        }
//
//        public void setCommodity_id(String commodity_id) {
//            this.commodity_id = commodity_id;
//        }
//
//        public Object getCreate_time() {
//            return create_time;
//        }
//
//        public void setCreate_time(Object create_time) {
//            this.create_time = create_time;
//        }
//
//        public String getCommodity_name() {
//            return commodity_name;
//        }
//
//        public void setCommodity_name(String commodity_name) {
//            this.commodity_name = commodity_name;
//        }
//
//        public String getSpec_name() {
//            return spec_name;
//        }
//
//        public void setSpec_name(String spec_name) {
//            this.spec_name = spec_name;
//        }
//
//        public int getPrice() {
//            return price;
//        }
//
//        public void setPrice(int price) {
//            this.price = price;
//        }
//
//        public int getAcount() {
//            return acount;
//        }
//
//        public void setAcount(int acount) {
//            this.acount = acount;
//        }
//
//        public Object getAll_price() {
//            return all_price;
//        }
//
//        public void setAll_price(Object all_price) {
//            this.all_price = all_price;
//        }
//
//        public Object getTotal_weight() {
//            return total_weight;
//        }
//
//        public void setTotal_weight(Object total_weight) {
//            this.total_weight = total_weight;
//        }
//
//        public Object getOrder_details_id() {
//            return order_details_id;
//        }
//
//        public void setOrder_details_id(Object order_details_id) {
//            this.order_details_id = order_details_id;
//        }
//    }
}
