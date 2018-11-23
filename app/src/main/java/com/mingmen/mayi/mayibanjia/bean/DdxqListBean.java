package com.mingmen.mayi.mayibanjia.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2018/11/15.
 */

public class DdxqListBean {

    /**
     * total : 206
     * order_number : 103015145535073
     * market : [{"user_token":null,"mark_id":null,"specific_address":null,"market_id":"1","province":null,"city":null,"parent_number":null,"create_time":null,"list":null,"levelState":null,"sumZL":0,"gonglishu":null,"driver_name":"椅子","classify_id":null,"market_name":"哈达市场","packCount":"3","scanCount":"3","orderState":"405","wl_order_state":"1403","driver_phone":"15545117969","plate_number":"黑A454545","dplist":[{"commodity_name":null,"user_token":null,"price":null,"pice_one":null,"account_id":null,"company_id":"22","ration_one":null,"commodity_id":null,"pageNumber":null,"street":null,"specific_address":null,"province":null,"city":null,"company_name":"哈市","zz":null,"telephone":"","hostphoto":null,"attention_id":null,"id_number":null,"phone":null,"picture_id":null,"business_state":null,"approval_state":null,"photo":null,"gy_order_id":"acc158d7837b4b9b8c54b2698a8c41db","comment_text":null,"scale":null,"salesman_duty":null,"parent_number":null,"file_path":null,"fr":null,"creater":null,"business_license":null,"circulation_permit":null,"legal_person":null,"order_id":"cc3a1e68889b46748a492433abd23f69","gy_company_id":null,"type_tree_id":null,"create_time":"2018-10-30 15:14:55","list":null,"end_hour":null,"change_time":null,"commodity_sales":null,"son_name":null,"order_number":"103015145535073","deliver_address":"b7ae3cebf3ba4b39a737aebcbd44dae1","son_number":null,"booth_number":null,"listjg":null,"commodity_state":null,"changer":null,"evaluation":0,"attention_number":null,"market_name":"哈达市场","pay_type":null,"scanState":"0","yewuyuan":null,"dianpudianhua":null,"kefu":null,"kefuphone":null,"listjgg":null,"company_short":null,"company_number":null,"duty_paragraph":null,"audit_state":null,"remark":null,"salesman_time":null,"quYMC":null,"son_namea":null,"quYMCa":null,"quYMCb":null,"quYMCc":null,"ywyphone":null,"companyList":null,"isBuy":null,"id_reverse":null,"id_positive":null,"begin_hour":null,"listxl":null,"listsp":[{"commodity_name":"4","picture_address":null,"user_token":null,"packStandard":"1斤/(2g*3L)","packOneName":null,"packTwoName":null,"packThreeName":null,"packFourName":null,"specOneNum":null,"specTwoNum":null,"specThreeNum":null,"price":"12元/斤","choose_specifications":null,"pice_one":null,"company_id":null,"ration_one":null,"ration_two":null,"pice_two":null,"ration_three":null,"pice_three":null,"number_views":null,"commodity_id":"54565595db574c6e80c8525cef90af59","comend_address":null,"sortOrder":null,"pageNumber":null,"evaluate":null,"son_order_id":null,"shopping_id":null,"specific_address":null,"market_id":null,"province":null,"city":null,"company_name":null,"picture_url":null,"bigCommodity_id":null,"smallCommodity_id":null,"hostphoto":null,"collect_id":null,"hostPicture":"http://pbn4jedp4.bkt.clouddn.com/eb7b6a3e3437470291d399a7f48c1b3d?e=1540888097&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:m_5nF1pBf8G01mqFQHLV6x6x3fE=","pack_standard_tree":null,"goods":null,"inventory":null,"type_one_id":null,"apply":null,"proportion":null,"deputyPicture":null,"picture_id":null,"ftPicture":null,"approval_state":null,"companyPhoto":null,"companyAddress":null,"file_path":null,"creater":null,"order_id":null,"type_tree_id":null,"quote_price_id":null,"create_time":null,"append_money":null,"change_time":null,"acount":12,"commodity_sales":null,"spec_name":null,"pack_standard_one":null,"pack_standard_two":null,"son_number":null,"total_weight":null,"listsmall":null,"listjg":null,"star_evaluation":null,"shuliang":null,"pack_standard_one_name":null,"big":null,"small":null,"avgPrice":null,"sumGoodsSales":null,"deputyphoto":null,"hostPath":null,"pice":null,"avgNum":null,"hpicture":null,"dpicture":null,"companyName":null,"commodity_state":null,"whether_ration":null,"split_type":null,"creat_time":null,"changer":null,"delivery_time":null,"recommended_categories":null,"type_two_id":null,"specFourNum":null,"pack_standard_four":null,"market_name":null,"pack_standard_tree_name":null,"spec_describe":null,"pack_id":null,"classify_name":null,"provName":null,"citName":null,"countName":null,"origin":null,"level":null,"count":null,"number":null,"region":null}],"principal":null,"type":null,"region":null}],"create_by":null,"update_by":null,"update_time":"2018-10-30 15:17:00","state":null,"region":null}]
     * sp : 共1件商品
     * freight_fee : 31
     * dizhi : 黑龙江省哈尔滨市南岗区汉水路15号
     * son_name : 午12点-13点
     * linman : 比森
     * create_time : 2018-10-30 15:14:55
     * state : 405
     * total_price : 175
     * dianhua : 136655346
     */

    private int total;
    private String order_number;
    private String sp;
    private int freight_fee;
    private String dizhi;
    private String son_name;
    private String linman;
    private String create_time;
    private String state;
    private int total_price;
    private String dianhua;
    private String append_money;

    public String getAppend_money() {
        return append_money;
    }

    public void setAppend_money(String append_money) {
        this.append_money = append_money;
    }

    private List<MarketBean> market;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

    public int getFreight_fee() {
        return freight_fee;
    }

    public void setFreight_fee(int freight_fee) {
        this.freight_fee = freight_fee;
    }

    public String getDizhi() {
        return dizhi;
    }

    public void setDizhi(String dizhi) {
        this.dizhi = dizhi;
    }

    public String getSon_name() {
        return son_name;
    }

    public void setSon_name(String son_name) {
        this.son_name = son_name;
    }

    public String getLinman() {
        return linman;
    }

    public void setLinman(String linman) {
        this.linman = linman;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public String getDianhua() {
        return dianhua;
    }

    public void setDianhua(String dianhua) {
        this.dianhua = dianhua;
    }

    public List<MarketBean> getMarket() {
        return market;
    }

    public void setMarket(List<MarketBean> market) {
        this.market = market;
    }

    public static class MarketBean {
        /**
         * user_token : null
         * mark_id : null
         * specific_address : null
         * market_id : 1
         * province : null
         * city : null
         * parent_number : null
         * create_time : null
         * list : null
         * levelState : null
         * sumZL : 0
         * gonglishu : null
         * driver_name : 椅子
         * classify_id : null
         * market_name : 哈达市场
         * packCount : 3
         * scanCount : 3
         * orderState : 405
         * wl_order_state : 1403
         * driver_phone : 15545117969
         * plate_number : 黑A454545
         * dplist : [{"commodity_name":null,"user_token":null,"price":null,"pice_one":null,"account_id":null,"company_id":"22","ration_one":null,"commodity_id":null,"pageNumber":null,"street":null,"specific_address":null,"province":null,"city":null,"company_name":"哈市","zz":null,"telephone":"","hostphoto":null,"attention_id":null,"id_number":null,"phone":null,"picture_id":null,"business_state":null,"approval_state":null,"photo":null,"gy_order_id":"acc158d7837b4b9b8c54b2698a8c41db","comment_text":null,"scale":null,"salesman_duty":null,"parent_number":null,"file_path":null,"fr":null,"creater":null,"business_license":null,"circulation_permit":null,"legal_person":null,"order_id":"cc3a1e68889b46748a492433abd23f69","gy_company_id":null,"type_tree_id":null,"create_time":"2018-10-30 15:14:55","list":null,"end_hour":null,"change_time":null,"commodity_sales":null,"son_name":null,"order_number":"103015145535073","deliver_address":"b7ae3cebf3ba4b39a737aebcbd44dae1","son_number":null,"booth_number":null,"listjg":null,"commodity_state":null,"changer":null,"evaluation":0,"attention_number":null,"market_name":"哈达市场","pay_type":null,"scanState":"0","yewuyuan":null,"dianpudianhua":null,"kefu":null,"kefuphone":null,"listjgg":null,"company_short":null,"company_number":null,"duty_paragraph":null,"audit_state":null,"remark":null,"salesman_time":null,"quYMC":null,"son_namea":null,"quYMCa":null,"quYMCb":null,"quYMCc":null,"ywyphone":null,"companyList":null,"isBuy":null,"id_reverse":null,"id_positive":null,"begin_hour":null,"listxl":null,"listsp":[{"commodity_name":"4","picture_address":null,"user_token":null,"packStandard":"1斤/(2g*3L)","packOneName":null,"packTwoName":null,"packThreeName":null,"packFourName":null,"specOneNum":null,"specTwoNum":null,"specThreeNum":null,"price":"12元/斤","choose_specifications":null,"pice_one":null,"company_id":null,"ration_one":null,"ration_two":null,"pice_two":null,"ration_three":null,"pice_three":null,"number_views":null,"commodity_id":"54565595db574c6e80c8525cef90af59","comend_address":null,"sortOrder":null,"pageNumber":null,"evaluate":null,"son_order_id":null,"shopping_id":null,"specific_address":null,"market_id":null,"province":null,"city":null,"company_name":null,"picture_url":null,"bigCommodity_id":null,"smallCommodity_id":null,"hostphoto":null,"collect_id":null,"hostPicture":"http://pbn4jedp4.bkt.clouddn.com/eb7b6a3e3437470291d399a7f48c1b3d?e=1540888097&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:m_5nF1pBf8G01mqFQHLV6x6x3fE=","pack_standard_tree":null,"goods":null,"inventory":null,"type_one_id":null,"apply":null,"proportion":null,"deputyPicture":null,"picture_id":null,"ftPicture":null,"approval_state":null,"companyPhoto":null,"companyAddress":null,"file_path":null,"creater":null,"order_id":null,"type_tree_id":null,"quote_price_id":null,"create_time":null,"append_money":null,"change_time":null,"acount":12,"commodity_sales":null,"spec_name":null,"pack_standard_one":null,"pack_standard_two":null,"son_number":null,"total_weight":null,"listsmall":null,"listjg":null,"star_evaluation":null,"shuliang":null,"pack_standard_one_name":null,"big":null,"small":null,"avgPrice":null,"sumGoodsSales":null,"deputyphoto":null,"hostPath":null,"pice":null,"avgNum":null,"hpicture":null,"dpicture":null,"companyName":null,"commodity_state":null,"whether_ration":null,"split_type":null,"creat_time":null,"changer":null,"delivery_time":null,"recommended_categories":null,"type_two_id":null,"specFourNum":null,"pack_standard_four":null,"market_name":null,"pack_standard_tree_name":null,"spec_describe":null,"pack_id":null,"classify_name":null,"provName":null,"citName":null,"countName":null,"origin":null,"level":null,"count":null,"number":null,"region":null}],"principal":null,"type":null,"region":null}]
         * create_by : null
         * update_by : null
         * update_time : 2018-10-30 15:17:00
         * state : null
         * region : null
         */

        private Object user_token;
        private Object mark_id;
        private Object specific_address;
        private String market_id;
        private Object province;
        private Object city;
        private Object parent_number;
        private Object create_time;
        private Object list;
        private Object levelState;
        private int sumZL;
        private Object gonglishu;
        private String driver_name;
        private Object classify_id;
        private String market_name;
        private String packCount;
        private String scanCount;
        private String orderState;
        private String wl_order_state;
        private String driver_phone;
        private String plate_number;
        private Object create_by;
        private Object update_by;
        private String update_time;
        private Object state;
        private Object region;
        private String price;
        private String freight_fee;
        private String refund;//退款金额

        public String getRefund() {
            return refund;
        }

        public void setRefund(String refund) {
            this.refund = refund;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getFreight_fee() {
            return freight_fee;
        }

        public void setFreight_fee(String freight_fee) {
            this.freight_fee = freight_fee;
        }

        private List<DplistBean> dplist;

        public Object getUser_token() {
            return user_token;
        }

        public void setUser_token(Object user_token) {
            this.user_token = user_token;
        }

        public Object getMark_id() {
            return mark_id;
        }

        public void setMark_id(Object mark_id) {
            this.mark_id = mark_id;
        }

        public Object getSpecific_address() {
            return specific_address;
        }

        public void setSpecific_address(Object specific_address) {
            this.specific_address = specific_address;
        }

        public String getMarket_id() {
            return market_id;
        }

        public void setMarket_id(String market_id) {
            this.market_id = market_id;
        }

        public Object getProvince() {
            return province;
        }

        public void setProvince(Object province) {
            this.province = province;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public Object getParent_number() {
            return parent_number;
        }

        public void setParent_number(Object parent_number) {
            this.parent_number = parent_number;
        }

        public Object getCreate_time() {
            return create_time;
        }

        public void setCreate_time(Object create_time) {
            this.create_time = create_time;
        }

        public Object getList() {
            return list;
        }

        public void setList(Object list) {
            this.list = list;
        }

        public Object getLevelState() {
            return levelState;
        }

        public void setLevelState(Object levelState) {
            this.levelState = levelState;
        }

        public int getSumZL() {
            return sumZL;
        }

        public void setSumZL(int sumZL) {
            this.sumZL = sumZL;
        }

        public Object getGonglishu() {
            return gonglishu;
        }

        public void setGonglishu(Object gonglishu) {
            this.gonglishu = gonglishu;
        }

        public String getDriver_name() {
            return driver_name;
        }

        public void setDriver_name(String driver_name) {
            this.driver_name = driver_name;
        }

        public Object getClassify_id() {
            return classify_id;
        }

        public void setClassify_id(Object classify_id) {
            this.classify_id = classify_id;
        }

        public String getMarket_name() {
            return market_name;
        }

        public void setMarket_name(String market_name) {
            this.market_name = market_name;
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

        public String getOrderState() {
            return orderState;
        }

        public void setOrderState(String orderState) {
            this.orderState = orderState;
        }

        public String getWl_order_state() {
            return wl_order_state;
        }

        public void setWl_order_state(String wl_order_state) {
            this.wl_order_state = wl_order_state;
        }

        public String getDriver_phone() {
            return driver_phone;
        }

        public void setDriver_phone(String driver_phone) {
            this.driver_phone = driver_phone;
        }

        public String getPlate_number() {
            return plate_number;
        }

        public void setPlate_number(String plate_number) {
            this.plate_number = plate_number;
        }

        public Object getCreate_by() {
            return create_by;
        }

        public void setCreate_by(Object create_by) {
            this.create_by = create_by;
        }

        public Object getUpdate_by() {
            return update_by;
        }

        public void setUpdate_by(Object update_by) {
            this.update_by = update_by;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }

        public Object getRegion() {
            return region;
        }

        public void setRegion(Object region) {
            this.region = region;
        }

        public List<DplistBean> getDplist() {
            return dplist;
        }

        public void setDplist(List<DplistBean> dplist) {
            this.dplist = dplist;
        }

        public static class DplistBean {
            /**
             * commodity_name : null
             * user_token : null
             * price : null
             * pice_one : null
             * account_id : null
             * company_id : 22
             * ration_one : null
             * commodity_id : null
             * pageNumber : null
             * street : null
             * specific_address : null
             * province : null
             * city : null
             * company_name : 哈市
             * zz : null
             * telephone :
             * hostphoto : null
             * attention_id : null
             * id_number : null
             * phone : null
             * picture_id : null
             * business_state : null
             * approval_state : null
             * photo : null
             * gy_order_id : acc158d7837b4b9b8c54b2698a8c41db
             * comment_text : null
             * scale : null
             * salesman_duty : null
             * parent_number : null
             * file_path : null
             * fr : null
             * creater : null
             * business_license : null
             * circulation_permit : null
             * legal_person : null
             * order_id : cc3a1e68889b46748a492433abd23f69
             * gy_company_id : null
             * type_tree_id : null
             * create_time : 2018-10-30 15:14:55
             * list : null
             * end_hour : null
             * change_time : null
             * commodity_sales : null
             * son_name : null
             * order_number : 103015145535073
             * deliver_address : b7ae3cebf3ba4b39a737aebcbd44dae1
             * son_number : null
             * booth_number : null
             * listjg : null
             * commodity_state : null
             * changer : null
             * evaluation : 0
             * attention_number : null
             * market_name : 哈达市场
             * pay_type : null
             * scanState : 0
             * yewuyuan : null
             * dianpudianhua : null
             * kefu : null
             * kefuphone : null
             * listjgg : null
             * company_short : null
             * company_number : null
             * duty_paragraph : null
             * audit_state : null
             * remark : null
             * salesman_time : null
             * quYMC : null
             * son_namea : null
             * quYMCa : null
             * quYMCb : null
             * quYMCc : null
             * ywyphone : null
             * companyList : null
             * isBuy : null
             * id_reverse : null
             * id_positive : null
             * begin_hour : null
             * listxl : null
             * listsp : [{"commodity_name":"4","picture_address":null,"user_token":null,"packStandard":"1斤/(2g*3L)","packOneName":null,"packTwoName":null,"packThreeName":null,"packFourName":null,"specOneNum":null,"specTwoNum":null,"specThreeNum":null,"price":"12元/斤","choose_specifications":null,"pice_one":null,"company_id":null,"ration_one":null,"ration_two":null,"pice_two":null,"ration_three":null,"pice_three":null,"number_views":null,"commodity_id":"54565595db574c6e80c8525cef90af59","comend_address":null,"sortOrder":null,"pageNumber":null,"evaluate":null,"son_order_id":null,"shopping_id":null,"specific_address":null,"market_id":null,"province":null,"city":null,"company_name":null,"picture_url":null,"bigCommodity_id":null,"smallCommodity_id":null,"hostphoto":null,"collect_id":null,"hostPicture":"http://pbn4jedp4.bkt.clouddn.com/eb7b6a3e3437470291d399a7f48c1b3d?e=1540888097&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:m_5nF1pBf8G01mqFQHLV6x6x3fE=","pack_standard_tree":null,"goods":null,"inventory":null,"type_one_id":null,"apply":null,"proportion":null,"deputyPicture":null,"picture_id":null,"ftPicture":null,"approval_state":null,"companyPhoto":null,"companyAddress":null,"file_path":null,"creater":null,"order_id":null,"type_tree_id":null,"quote_price_id":null,"create_time":null,"append_money":null,"change_time":null,"acount":12,"commodity_sales":null,"spec_name":null,"pack_standard_one":null,"pack_standard_two":null,"son_number":null,"total_weight":null,"listsmall":null,"listjg":null,"star_evaluation":null,"shuliang":null,"pack_standard_one_name":null,"big":null,"small":null,"avgPrice":null,"sumGoodsSales":null,"deputyphoto":null,"hostPath":null,"pice":null,"avgNum":null,"hpicture":null,"dpicture":null,"companyName":null,"commodity_state":null,"whether_ration":null,"split_type":null,"creat_time":null,"changer":null,"delivery_time":null,"recommended_categories":null,"type_two_id":null,"specFourNum":null,"pack_standard_four":null,"market_name":null,"pack_standard_tree_name":null,"spec_describe":null,"pack_id":null,"classify_name":null,"provName":null,"citName":null,"countName":null,"origin":null,"level":null,"count":null,"number":null,"region":null}]
             * principal : null
             * type : null
             * region : null
             */

            private Object commodity_name;
            private Object user_token;
            private Object price;
            private Object pice_one;
            private Object account_id;
            private String company_id;
            private Object ration_one;
            private Object commodity_id;
            private Object pageNumber;
            private Object street;
            private Object specific_address;
            private Object province;
            private Object city;
            private String company_name;
            private Object zz;
            private String telephone;
            private Object hostphoto;
            private Object attention_id;
            private Object id_number;
            private Object phone;
            private Object picture_id;
            private Object business_state;
            private Object approval_state;
            private Object photo;
            private String gy_order_id;
            private Object comment_text;
            private Object scale;
            private Object salesman_duty;
            private Object parent_number;
            private Object file_path;
            private Object fr;
            private Object creater;
            private Object business_license;
            private Object circulation_permit;
            private Object legal_person;
            private String order_id;
            private Object gy_company_id;
            private Object type_tree_id;
            private String create_time;
            private Object list;
            private Object end_hour;
            private Object change_time;
            private Object commodity_sales;
            private Object son_name;
            private String order_number;
            private String deliver_address;
            private Object son_number;
            private Object booth_number;
            private Object listjg;
            private Object commodity_state;
            private Object changer;
            private int evaluation;
            private Object attention_number;
            private String market_name;
            private Object pay_type;
            private String scanState;
            private Object yewuyuan;
            private Object dianpudianhua;
            private Object kefu;
            private Object kefuphone;
            private Object listjgg;
            private Object company_short;
            private Object company_number;
            private Object duty_paragraph;
            private Object audit_state;
            private Object remark;
            private Object salesman_time;
            private Object quYMC;
            private Object son_namea;
            private Object quYMCa;
            private Object quYMCb;
            private Object quYMCc;
            private Object ywyphone;
            private Object companyList;
            private Object isBuy;
            private Object id_reverse;
            private Object id_positive;
            private Object begin_hour;
            private Object listxl;
            private Object principal;
            private Object type;
            private Object region;
            private String state;
            private String shu;
            private String total;
            private String ct_state;

            public String getCt_state() {
                return ct_state;
            }

            public void setCt_state(String ct_state) {
                this.ct_state = ct_state;
            }

            private List<ListspBean> listsp;

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getShu() {
                return shu;
            }

            public void setShu(String shu) {
                this.shu = shu;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public Object getCommodity_name() {
                return commodity_name;
            }

            public void setCommodity_name(Object commodity_name) {
                this.commodity_name = commodity_name;
            }

            public Object getUser_token() {
                return user_token;
            }

            public void setUser_token(Object user_token) {
                this.user_token = user_token;
            }

            public Object getPrice() {
                return price;
            }

            public void setPrice(Object price) {
                this.price = price;
            }

            public Object getPice_one() {
                return pice_one;
            }

            public void setPice_one(Object pice_one) {
                this.pice_one = pice_one;
            }

            public Object getAccount_id() {
                return account_id;
            }

            public void setAccount_id(Object account_id) {
                this.account_id = account_id;
            }

            public String getCompany_id() {
                return company_id;
            }

            public void setCompany_id(String company_id) {
                this.company_id = company_id;
            }

            public Object getRation_one() {
                return ration_one;
            }

            public void setRation_one(Object ration_one) {
                this.ration_one = ration_one;
            }

            public Object getCommodity_id() {
                return commodity_id;
            }

            public void setCommodity_id(Object commodity_id) {
                this.commodity_id = commodity_id;
            }

            public Object getPageNumber() {
                return pageNumber;
            }

            public void setPageNumber(Object pageNumber) {
                this.pageNumber = pageNumber;
            }

            public Object getStreet() {
                return street;
            }

            public void setStreet(Object street) {
                this.street = street;
            }

            public Object getSpecific_address() {
                return specific_address;
            }

            public void setSpecific_address(Object specific_address) {
                this.specific_address = specific_address;
            }

            public Object getProvince() {
                return province;
            }

            public void setProvince(Object province) {
                this.province = province;
            }

            public Object getCity() {
                return city;
            }

            public void setCity(Object city) {
                this.city = city;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public Object getZz() {
                return zz;
            }

            public void setZz(Object zz) {
                this.zz = zz;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public Object getHostphoto() {
                return hostphoto;
            }

            public void setHostphoto(Object hostphoto) {
                this.hostphoto = hostphoto;
            }

            public Object getAttention_id() {
                return attention_id;
            }

            public void setAttention_id(Object attention_id) {
                this.attention_id = attention_id;
            }

            public Object getId_number() {
                return id_number;
            }

            public void setId_number(Object id_number) {
                this.id_number = id_number;
            }

            public Object getPhone() {
                return phone;
            }

            public void setPhone(Object phone) {
                this.phone = phone;
            }

            public Object getPicture_id() {
                return picture_id;
            }

            public void setPicture_id(Object picture_id) {
                this.picture_id = picture_id;
            }

            public Object getBusiness_state() {
                return business_state;
            }

            public void setBusiness_state(Object business_state) {
                this.business_state = business_state;
            }

            public Object getApproval_state() {
                return approval_state;
            }

            public void setApproval_state(Object approval_state) {
                this.approval_state = approval_state;
            }

            public Object getPhoto() {
                return photo;
            }

            public void setPhoto(Object photo) {
                this.photo = photo;
            }

            public String getGy_order_id() {
                return gy_order_id;
            }

            public void setGy_order_id(String gy_order_id) {
                this.gy_order_id = gy_order_id;
            }

            public Object getComment_text() {
                return comment_text;
            }

            public void setComment_text(Object comment_text) {
                this.comment_text = comment_text;
            }

            public Object getScale() {
                return scale;
            }

            public void setScale(Object scale) {
                this.scale = scale;
            }

            public Object getSalesman_duty() {
                return salesman_duty;
            }

            public void setSalesman_duty(Object salesman_duty) {
                this.salesman_duty = salesman_duty;
            }

            public Object getParent_number() {
                return parent_number;
            }

            public void setParent_number(Object parent_number) {
                this.parent_number = parent_number;
            }

            public Object getFile_path() {
                return file_path;
            }

            public void setFile_path(Object file_path) {
                this.file_path = file_path;
            }

            public Object getFr() {
                return fr;
            }

            public void setFr(Object fr) {
                this.fr = fr;
            }

            public Object getCreater() {
                return creater;
            }

            public void setCreater(Object creater) {
                this.creater = creater;
            }

            public Object getBusiness_license() {
                return business_license;
            }

            public void setBusiness_license(Object business_license) {
                this.business_license = business_license;
            }

            public Object getCirculation_permit() {
                return circulation_permit;
            }

            public void setCirculation_permit(Object circulation_permit) {
                this.circulation_permit = circulation_permit;
            }

            public Object getLegal_person() {
                return legal_person;
            }

            public void setLegal_person(Object legal_person) {
                this.legal_person = legal_person;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public Object getGy_company_id() {
                return gy_company_id;
            }

            public void setGy_company_id(Object gy_company_id) {
                this.gy_company_id = gy_company_id;
            }

            public Object getType_tree_id() {
                return type_tree_id;
            }

            public void setType_tree_id(Object type_tree_id) {
                this.type_tree_id = type_tree_id;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public Object getList() {
                return list;
            }

            public void setList(Object list) {
                this.list = list;
            }

            public Object getEnd_hour() {
                return end_hour;
            }

            public void setEnd_hour(Object end_hour) {
                this.end_hour = end_hour;
            }

            public Object getChange_time() {
                return change_time;
            }

            public void setChange_time(Object change_time) {
                this.change_time = change_time;
            }

            public Object getCommodity_sales() {
                return commodity_sales;
            }

            public void setCommodity_sales(Object commodity_sales) {
                this.commodity_sales = commodity_sales;
            }

            public Object getSon_name() {
                return son_name;
            }

            public void setSon_name(Object son_name) {
                this.son_name = son_name;
            }

            public String getOrder_number() {
                return order_number;
            }

            public void setOrder_number(String order_number) {
                this.order_number = order_number;
            }

            public String getDeliver_address() {
                return deliver_address;
            }

            public void setDeliver_address(String deliver_address) {
                this.deliver_address = deliver_address;
            }

            public Object getSon_number() {
                return son_number;
            }

            public void setSon_number(Object son_number) {
                this.son_number = son_number;
            }

            public Object getBooth_number() {
                return booth_number;
            }

            public void setBooth_number(Object booth_number) {
                this.booth_number = booth_number;
            }

            public Object getListjg() {
                return listjg;
            }

            public void setListjg(Object listjg) {
                this.listjg = listjg;
            }

            public Object getCommodity_state() {
                return commodity_state;
            }

            public void setCommodity_state(Object commodity_state) {
                this.commodity_state = commodity_state;
            }

            public Object getChanger() {
                return changer;
            }

            public void setChanger(Object changer) {
                this.changer = changer;
            }

            public int getEvaluation() {
                return evaluation;
            }

            public void setEvaluation(int evaluation) {
                this.evaluation = evaluation;
            }

            public Object getAttention_number() {
                return attention_number;
            }

            public void setAttention_number(Object attention_number) {
                this.attention_number = attention_number;
            }

            public String getMarket_name() {
                return market_name;
            }

            public void setMarket_name(String market_name) {
                this.market_name = market_name;
            }

            public Object getPay_type() {
                return pay_type;
            }

            public void setPay_type(Object pay_type) {
                this.pay_type = pay_type;
            }

            public String getScanState() {
                return scanState;
            }

            public void setScanState(String scanState) {
                this.scanState = scanState;
            }

            public Object getYewuyuan() {
                return yewuyuan;
            }

            public void setYewuyuan(Object yewuyuan) {
                this.yewuyuan = yewuyuan;
            }

            public Object getDianpudianhua() {
                return dianpudianhua;
            }

            public void setDianpudianhua(Object dianpudianhua) {
                this.dianpudianhua = dianpudianhua;
            }

            public Object getKefu() {
                return kefu;
            }

            public void setKefu(Object kefu) {
                this.kefu = kefu;
            }

            public Object getKefuphone() {
                return kefuphone;
            }

            public void setKefuphone(Object kefuphone) {
                this.kefuphone = kefuphone;
            }

            public Object getListjgg() {
                return listjgg;
            }

            public void setListjgg(Object listjgg) {
                this.listjgg = listjgg;
            }

            public Object getCompany_short() {
                return company_short;
            }

            public void setCompany_short(Object company_short) {
                this.company_short = company_short;
            }

            public Object getCompany_number() {
                return company_number;
            }

            public void setCompany_number(Object company_number) {
                this.company_number = company_number;
            }

            public Object getDuty_paragraph() {
                return duty_paragraph;
            }

            public void setDuty_paragraph(Object duty_paragraph) {
                this.duty_paragraph = duty_paragraph;
            }

            public Object getAudit_state() {
                return audit_state;
            }

            public void setAudit_state(Object audit_state) {
                this.audit_state = audit_state;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public Object getSalesman_time() {
                return salesman_time;
            }

            public void setSalesman_time(Object salesman_time) {
                this.salesman_time = salesman_time;
            }

            public Object getQuYMC() {
                return quYMC;
            }

            public void setQuYMC(Object quYMC) {
                this.quYMC = quYMC;
            }

            public Object getSon_namea() {
                return son_namea;
            }

            public void setSon_namea(Object son_namea) {
                this.son_namea = son_namea;
            }

            public Object getQuYMCa() {
                return quYMCa;
            }

            public void setQuYMCa(Object quYMCa) {
                this.quYMCa = quYMCa;
            }

            public Object getQuYMCb() {
                return quYMCb;
            }

            public void setQuYMCb(Object quYMCb) {
                this.quYMCb = quYMCb;
            }

            public Object getQuYMCc() {
                return quYMCc;
            }

            public void setQuYMCc(Object quYMCc) {
                this.quYMCc = quYMCc;
            }

            public Object getYwyphone() {
                return ywyphone;
            }

            public void setYwyphone(Object ywyphone) {
                this.ywyphone = ywyphone;
            }

            public Object getCompanyList() {
                return companyList;
            }

            public void setCompanyList(Object companyList) {
                this.companyList = companyList;
            }

            public Object getIsBuy() {
                return isBuy;
            }

            public void setIsBuy(Object isBuy) {
                this.isBuy = isBuy;
            }

            public Object getId_reverse() {
                return id_reverse;
            }

            public void setId_reverse(Object id_reverse) {
                this.id_reverse = id_reverse;
            }

            public Object getId_positive() {
                return id_positive;
            }

            public void setId_positive(Object id_positive) {
                this.id_positive = id_positive;
            }

            public Object getBegin_hour() {
                return begin_hour;
            }

            public void setBegin_hour(Object begin_hour) {
                this.begin_hour = begin_hour;
            }

            public Object getListxl() {
                return listxl;
            }

            public void setListxl(Object listxl) {
                this.listxl = listxl;
            }

            public Object getPrincipal() {
                return principal;
            }

            public void setPrincipal(Object principal) {
                this.principal = principal;
            }

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
            }

            public Object getRegion() {
                return region;
            }

            public void setRegion(Object region) {
                this.region = region;
            }

            public List<ListspBean> getListsp() {
                return listsp;
            }

            public void setListsp(List<ListspBean> listsp) {
                this.listsp = listsp;
            }

            public static class ListspBean {
                /**
                 * commodity_name : 4
                 * picture_address : null
                 * user_token : null
                 * packStandard : 1斤/(2g*3L)
                 * packOneName : null
                 * packTwoName : null
                 * packThreeName : null
                 * packFourName : null
                 * specOneNum : null
                 * specTwoNum : null
                 * specThreeNum : null
                 * price : 12元/斤
                 * choose_specifications : null
                 * pice_one : null
                 * company_id : null
                 * ration_one : null
                 * ration_two : null
                 * pice_two : null
                 * ration_three : null
                 * pice_three : null
                 * number_views : null
                 * commodity_id : 54565595db574c6e80c8525cef90af59
                 * comend_address : null
                 * sortOrder : null
                 * pageNumber : null
                 * evaluate : null
                 * son_order_id : null
                 * shopping_id : null
                 * specific_address : null
                 * market_id : null
                 * province : null
                 * city : null
                 * company_name : null
                 * picture_url : null
                 * bigCommodity_id : null
                 * smallCommodity_id : null
                 * hostphoto : null
                 * collect_id : null
                 * hostPicture : http://pbn4jedp4.bkt.clouddn.com/eb7b6a3e3437470291d399a7f48c1b3d?e=1540888097&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:m_5nF1pBf8G01mqFQHLV6x6x3fE=
                 * pack_standard_tree : null
                 * goods : null
                 * inventory : null
                 * type_one_id : null
                 * apply : null
                 * proportion : null
                 * deputyPicture : null
                 * picture_id : null
                 * ftPicture : null
                 * approval_state : null
                 * companyPhoto : null
                 * companyAddress : null
                 * file_path : null
                 * creater : null
                 * order_id : null
                 * type_tree_id : null
                 * quote_price_id : null
                 * create_time : null
                 * append_money : null
                 * change_time : null
                 * acount : 12
                 * commodity_sales : null
                 * spec_name : null
                 * pack_standard_one : null
                 * pack_standard_two : null
                 * son_number : null
                 * total_weight : null
                 * listsmall : null
                 * listjg : null
                 * star_evaluation : null
                 * shuliang : null
                 * pack_standard_one_name : null
                 * big : null
                 * small : null
                 * avgPrice : null
                 * sumGoodsSales : null
                 * deputyphoto : null
                 * hostPath : null
                 * pice : null
                 * avgNum : null
                 * hpicture : null
                 * dpicture : null
                 * companyName : null
                 * commodity_state : null
                 * whether_ration : null
                 * split_type : null
                 * creat_time : null
                 * changer : null
                 * delivery_time : null
                 * recommended_categories : null
                 * type_two_id : null
                 * specFourNum : null
                 * pack_standard_four : null
                 * market_name : null
                 * pack_standard_tree_name : null
                 * spec_describe : null
                 * pack_id : null
                 * classify_name : null
                 * provName : null
                 * citName : null
                 * countName : null
                 * origin : null
                 * level : null
                 * count : null
                 * number : null
                 * region : null
                 */

                private String commodity_name;
                private Object picture_address;
                private Object user_token;
                private String packStandard;
                private Object packOneName;
                private Object packTwoName;
                private Object packThreeName;
                private Object packFourName;
                private Object specOneNum;
                private Object specTwoNum;
                private Object specThreeNum;
                private String price;
                private Object choose_specifications;
                private Object pice_one;
                private Object company_id;
                private Object ration_one;
                private Object ration_two;
                private Object pice_two;
                private Object ration_three;
                private Object pice_three;
                private Object number_views;
                private String commodity_id;
                private Object comend_address;
                private Object sortOrder;
                private Object pageNumber;
                private Object evaluate;
                private Object son_order_id;
                private Object shopping_id;
                private Object specific_address;
                private Object market_id;
                private Object province;
                private Object city;
                private Object company_name;
                private Object picture_url;
                private Object bigCommodity_id;
                private Object smallCommodity_id;
                private Object hostphoto;
                private Object collect_id;
                private String hostPicture;
                private Object pack_standard_tree;
                private Object goods;
                private Object inventory;
                private Object type_one_id;
                private Object apply;
                private Object proportion;
                private Object deputyPicture;
                private Object picture_id;
                private Object ftPicture;
                private Object approval_state;
                private Object companyPhoto;
                private Object companyAddress;
                private Object file_path;
                private Object creater;
                private Object order_id;
                private Object type_tree_id;
                private Object quote_price_id;
                private Object create_time;
                private BigDecimal append_money;
                private Object change_time;
                private int acount;
                private Object commodity_sales;
                private Object spec_name;
                private Object pack_standard_one;
                private Object pack_standard_two;
                private Object son_number;
                private Object total_weight;
                private Object listsmall;
                private Object listjg;
                private Object star_evaluation;
                private Object shuliang;
                private Object pack_standard_one_name;
                private Object big;
                private Object small;
                private Object avgPrice;
                private Object sumGoodsSales;
                private Object deputyphoto;
                private Object hostPath;
                private Object pice;
                private Object avgNum;
                private Object hpicture;
                private Object dpicture;
                private Object companyName;
                private Object commodity_state;
                private Object whether_ration;
                private Object split_type;
                private Object creat_time;
                private Object changer;
                private Object delivery_time;
                private Object recommended_categories;
                private Object type_two_id;
                private Object specFourNum;
                private Object pack_standard_four;
                private Object market_name;
                private Object pack_standard_tree_name;
                private Object spec_describe;
                private Object pack_id;
                private Object classify_name;
                private Object provName;
                private Object citName;
                private Object countName;
                private Object origin;
                private Object level;
                private Object count;
                private Object number;
                private Object region;

                public String getCommodity_name() {
                    return commodity_name;
                }

                public void setCommodity_name(String commodity_name) {
                    this.commodity_name = commodity_name;
                }

                public Object getPicture_address() {
                    return picture_address;
                }

                public void setPicture_address(Object picture_address) {
                    this.picture_address = picture_address;
                }

                public Object getUser_token() {
                    return user_token;
                }

                public void setUser_token(Object user_token) {
                    this.user_token = user_token;
                }

                public String getPackStandard() {
                    return packStandard;
                }

                public void setPackStandard(String packStandard) {
                    this.packStandard = packStandard;
                }

                public Object getPackOneName() {
                    return packOneName;
                }

                public void setPackOneName(Object packOneName) {
                    this.packOneName = packOneName;
                }

                public Object getPackTwoName() {
                    return packTwoName;
                }

                public void setPackTwoName(Object packTwoName) {
                    this.packTwoName = packTwoName;
                }

                public Object getPackThreeName() {
                    return packThreeName;
                }

                public void setPackThreeName(Object packThreeName) {
                    this.packThreeName = packThreeName;
                }

                public Object getPackFourName() {
                    return packFourName;
                }

                public void setPackFourName(Object packFourName) {
                    this.packFourName = packFourName;
                }

                public Object getSpecOneNum() {
                    return specOneNum;
                }

                public void setSpecOneNum(Object specOneNum) {
                    this.specOneNum = specOneNum;
                }

                public Object getSpecTwoNum() {
                    return specTwoNum;
                }

                public void setSpecTwoNum(Object specTwoNum) {
                    this.specTwoNum = specTwoNum;
                }

                public Object getSpecThreeNum() {
                    return specThreeNum;
                }

                public void setSpecThreeNum(Object specThreeNum) {
                    this.specThreeNum = specThreeNum;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public Object getChoose_specifications() {
                    return choose_specifications;
                }

                public void setChoose_specifications(Object choose_specifications) {
                    this.choose_specifications = choose_specifications;
                }

                public Object getPice_one() {
                    return pice_one;
                }

                public void setPice_one(Object pice_one) {
                    this.pice_one = pice_one;
                }

                public Object getCompany_id() {
                    return company_id;
                }

                public void setCompany_id(Object company_id) {
                    this.company_id = company_id;
                }

                public Object getRation_one() {
                    return ration_one;
                }

                public void setRation_one(Object ration_one) {
                    this.ration_one = ration_one;
                }

                public Object getRation_two() {
                    return ration_two;
                }

                public void setRation_two(Object ration_two) {
                    this.ration_two = ration_two;
                }

                public Object getPice_two() {
                    return pice_two;
                }

                public void setPice_two(Object pice_two) {
                    this.pice_two = pice_two;
                }

                public Object getRation_three() {
                    return ration_three;
                }

                public void setRation_three(Object ration_three) {
                    this.ration_three = ration_three;
                }

                public Object getPice_three() {
                    return pice_three;
                }

                public void setPice_three(Object pice_three) {
                    this.pice_three = pice_three;
                }

                public Object getNumber_views() {
                    return number_views;
                }

                public void setNumber_views(Object number_views) {
                    this.number_views = number_views;
                }

                public String getCommodity_id() {
                    return commodity_id;
                }

                public void setCommodity_id(String commodity_id) {
                    this.commodity_id = commodity_id;
                }

                public Object getComend_address() {
                    return comend_address;
                }

                public void setComend_address(Object comend_address) {
                    this.comend_address = comend_address;
                }

                public Object getSortOrder() {
                    return sortOrder;
                }

                public void setSortOrder(Object sortOrder) {
                    this.sortOrder = sortOrder;
                }

                public Object getPageNumber() {
                    return pageNumber;
                }

                public void setPageNumber(Object pageNumber) {
                    this.pageNumber = pageNumber;
                }

                public Object getEvaluate() {
                    return evaluate;
                }

                public void setEvaluate(Object evaluate) {
                    this.evaluate = evaluate;
                }

                public Object getSon_order_id() {
                    return son_order_id;
                }

                public void setSon_order_id(Object son_order_id) {
                    this.son_order_id = son_order_id;
                }

                public Object getShopping_id() {
                    return shopping_id;
                }

                public void setShopping_id(Object shopping_id) {
                    this.shopping_id = shopping_id;
                }

                public Object getSpecific_address() {
                    return specific_address;
                }

                public void setSpecific_address(Object specific_address) {
                    this.specific_address = specific_address;
                }

                public Object getMarket_id() {
                    return market_id;
                }

                public void setMarket_id(Object market_id) {
                    this.market_id = market_id;
                }

                public Object getProvince() {
                    return province;
                }

                public void setProvince(Object province) {
                    this.province = province;
                }

                public Object getCity() {
                    return city;
                }

                public void setCity(Object city) {
                    this.city = city;
                }

                public Object getCompany_name() {
                    return company_name;
                }

                public void setCompany_name(Object company_name) {
                    this.company_name = company_name;
                }

                public Object getPicture_url() {
                    return picture_url;
                }

                public void setPicture_url(Object picture_url) {
                    this.picture_url = picture_url;
                }

                public Object getBigCommodity_id() {
                    return bigCommodity_id;
                }

                public void setBigCommodity_id(Object bigCommodity_id) {
                    this.bigCommodity_id = bigCommodity_id;
                }

                public Object getSmallCommodity_id() {
                    return smallCommodity_id;
                }

                public void setSmallCommodity_id(Object smallCommodity_id) {
                    this.smallCommodity_id = smallCommodity_id;
                }

                public Object getHostphoto() {
                    return hostphoto;
                }

                public void setHostphoto(Object hostphoto) {
                    this.hostphoto = hostphoto;
                }

                public Object getCollect_id() {
                    return collect_id;
                }

                public void setCollect_id(Object collect_id) {
                    this.collect_id = collect_id;
                }

                public String getHostPicture() {
                    return hostPicture;
                }

                public void setHostPicture(String hostPicture) {
                    this.hostPicture = hostPicture;
                }

                public Object getPack_standard_tree() {
                    return pack_standard_tree;
                }

                public void setPack_standard_tree(Object pack_standard_tree) {
                    this.pack_standard_tree = pack_standard_tree;
                }

                public Object getGoods() {
                    return goods;
                }

                public void setGoods(Object goods) {
                    this.goods = goods;
                }

                public Object getInventory() {
                    return inventory;
                }

                public void setInventory(Object inventory) {
                    this.inventory = inventory;
                }

                public Object getType_one_id() {
                    return type_one_id;
                }

                public void setType_one_id(Object type_one_id) {
                    this.type_one_id = type_one_id;
                }

                public Object getApply() {
                    return apply;
                }

                public void setApply(Object apply) {
                    this.apply = apply;
                }

                public Object getProportion() {
                    return proportion;
                }

                public void setProportion(Object proportion) {
                    this.proportion = proportion;
                }

                public Object getDeputyPicture() {
                    return deputyPicture;
                }

                public void setDeputyPicture(Object deputyPicture) {
                    this.deputyPicture = deputyPicture;
                }

                public Object getPicture_id() {
                    return picture_id;
                }

                public void setPicture_id(Object picture_id) {
                    this.picture_id = picture_id;
                }

                public Object getFtPicture() {
                    return ftPicture;
                }

                public void setFtPicture(Object ftPicture) {
                    this.ftPicture = ftPicture;
                }

                public Object getApproval_state() {
                    return approval_state;
                }

                public void setApproval_state(Object approval_state) {
                    this.approval_state = approval_state;
                }

                public Object getCompanyPhoto() {
                    return companyPhoto;
                }

                public void setCompanyPhoto(Object companyPhoto) {
                    this.companyPhoto = companyPhoto;
                }

                public Object getCompanyAddress() {
                    return companyAddress;
                }

                public void setCompanyAddress(Object companyAddress) {
                    this.companyAddress = companyAddress;
                }

                public Object getFile_path() {
                    return file_path;
                }

                public void setFile_path(Object file_path) {
                    this.file_path = file_path;
                }

                public Object getCreater() {
                    return creater;
                }

                public void setCreater(Object creater) {
                    this.creater = creater;
                }

                public Object getOrder_id() {
                    return order_id;
                }

                public void setOrder_id(Object order_id) {
                    this.order_id = order_id;
                }

                public Object getType_tree_id() {
                    return type_tree_id;
                }

                public void setType_tree_id(Object type_tree_id) {
                    this.type_tree_id = type_tree_id;
                }

                public Object getQuote_price_id() {
                    return quote_price_id;
                }

                public void setQuote_price_id(Object quote_price_id) {
                    this.quote_price_id = quote_price_id;
                }

                public Object getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(Object create_time) {
                    this.create_time = create_time;
                }

                public BigDecimal getAppend_money() {
                    return append_money;
                }

                public void setAppend_money(BigDecimal append_money) {
                    this.append_money = append_money;
                }

                public Object getChange_time() {
                    return change_time;
                }

                public void setChange_time(Object change_time) {
                    this.change_time = change_time;
                }

                public int getAcount() {
                    return acount;
                }

                public void setAcount(int acount) {
                    this.acount = acount;
                }

                public Object getCommodity_sales() {
                    return commodity_sales;
                }

                public void setCommodity_sales(Object commodity_sales) {
                    this.commodity_sales = commodity_sales;
                }

                public Object getSpec_name() {
                    return spec_name;
                }

                public void setSpec_name(Object spec_name) {
                    this.spec_name = spec_name;
                }

                public Object getPack_standard_one() {
                    return pack_standard_one;
                }

                public void setPack_standard_one(Object pack_standard_one) {
                    this.pack_standard_one = pack_standard_one;
                }

                public Object getPack_standard_two() {
                    return pack_standard_two;
                }

                public void setPack_standard_two(Object pack_standard_two) {
                    this.pack_standard_two = pack_standard_two;
                }

                public Object getSon_number() {
                    return son_number;
                }

                public void setSon_number(Object son_number) {
                    this.son_number = son_number;
                }

                public Object getTotal_weight() {
                    return total_weight;
                }

                public void setTotal_weight(Object total_weight) {
                    this.total_weight = total_weight;
                }

                public Object getListsmall() {
                    return listsmall;
                }

                public void setListsmall(Object listsmall) {
                    this.listsmall = listsmall;
                }

                public Object getListjg() {
                    return listjg;
                }

                public void setListjg(Object listjg) {
                    this.listjg = listjg;
                }

                public Object getStar_evaluation() {
                    return star_evaluation;
                }

                public void setStar_evaluation(Object star_evaluation) {
                    this.star_evaluation = star_evaluation;
                }

                public Object getShuliang() {
                    return shuliang;
                }

                public void setShuliang(Object shuliang) {
                    this.shuliang = shuliang;
                }

                public Object getPack_standard_one_name() {
                    return pack_standard_one_name;
                }

                public void setPack_standard_one_name(Object pack_standard_one_name) {
                    this.pack_standard_one_name = pack_standard_one_name;
                }

                public Object getBig() {
                    return big;
                }

                public void setBig(Object big) {
                    this.big = big;
                }

                public Object getSmall() {
                    return small;
                }

                public void setSmall(Object small) {
                    this.small = small;
                }

                public Object getAvgPrice() {
                    return avgPrice;
                }

                public void setAvgPrice(Object avgPrice) {
                    this.avgPrice = avgPrice;
                }

                public Object getSumGoodsSales() {
                    return sumGoodsSales;
                }

                public void setSumGoodsSales(Object sumGoodsSales) {
                    this.sumGoodsSales = sumGoodsSales;
                }

                public Object getDeputyphoto() {
                    return deputyphoto;
                }

                public void setDeputyphoto(Object deputyphoto) {
                    this.deputyphoto = deputyphoto;
                }

                public Object getHostPath() {
                    return hostPath;
                }

                public void setHostPath(Object hostPath) {
                    this.hostPath = hostPath;
                }

                public Object getPice() {
                    return pice;
                }

                public void setPice(Object pice) {
                    this.pice = pice;
                }

                public Object getAvgNum() {
                    return avgNum;
                }

                public void setAvgNum(Object avgNum) {
                    this.avgNum = avgNum;
                }

                public Object getHpicture() {
                    return hpicture;
                }

                public void setHpicture(Object hpicture) {
                    this.hpicture = hpicture;
                }

                public Object getDpicture() {
                    return dpicture;
                }

                public void setDpicture(Object dpicture) {
                    this.dpicture = dpicture;
                }

                public Object getCompanyName() {
                    return companyName;
                }

                public void setCompanyName(Object companyName) {
                    this.companyName = companyName;
                }

                public Object getCommodity_state() {
                    return commodity_state;
                }

                public void setCommodity_state(Object commodity_state) {
                    this.commodity_state = commodity_state;
                }

                public Object getWhether_ration() {
                    return whether_ration;
                }

                public void setWhether_ration(Object whether_ration) {
                    this.whether_ration = whether_ration;
                }

                public Object getSplit_type() {
                    return split_type;
                }

                public void setSplit_type(Object split_type) {
                    this.split_type = split_type;
                }

                public Object getCreat_time() {
                    return creat_time;
                }

                public void setCreat_time(Object creat_time) {
                    this.creat_time = creat_time;
                }

                public Object getChanger() {
                    return changer;
                }

                public void setChanger(Object changer) {
                    this.changer = changer;
                }

                public Object getDelivery_time() {
                    return delivery_time;
                }

                public void setDelivery_time(Object delivery_time) {
                    this.delivery_time = delivery_time;
                }

                public Object getRecommended_categories() {
                    return recommended_categories;
                }

                public void setRecommended_categories(Object recommended_categories) {
                    this.recommended_categories = recommended_categories;
                }

                public Object getType_two_id() {
                    return type_two_id;
                }

                public void setType_two_id(Object type_two_id) {
                    this.type_two_id = type_two_id;
                }

                public Object getSpecFourNum() {
                    return specFourNum;
                }

                public void setSpecFourNum(Object specFourNum) {
                    this.specFourNum = specFourNum;
                }

                public Object getPack_standard_four() {
                    return pack_standard_four;
                }

                public void setPack_standard_four(Object pack_standard_four) {
                    this.pack_standard_four = pack_standard_four;
                }

                public Object getMarket_name() {
                    return market_name;
                }

                public void setMarket_name(Object market_name) {
                    this.market_name = market_name;
                }

                public Object getPack_standard_tree_name() {
                    return pack_standard_tree_name;
                }

                public void setPack_standard_tree_name(Object pack_standard_tree_name) {
                    this.pack_standard_tree_name = pack_standard_tree_name;
                }

                public Object getSpec_describe() {
                    return spec_describe;
                }

                public void setSpec_describe(Object spec_describe) {
                    this.spec_describe = spec_describe;
                }

                public Object getPack_id() {
                    return pack_id;
                }

                public void setPack_id(Object pack_id) {
                    this.pack_id = pack_id;
                }

                public Object getClassify_name() {
                    return classify_name;
                }

                public void setClassify_name(Object classify_name) {
                    this.classify_name = classify_name;
                }

                public Object getProvName() {
                    return provName;
                }

                public void setProvName(Object provName) {
                    this.provName = provName;
                }

                public Object getCitName() {
                    return citName;
                }

                public void setCitName(Object citName) {
                    this.citName = citName;
                }

                public Object getCountName() {
                    return countName;
                }

                public void setCountName(Object countName) {
                    this.countName = countName;
                }

                public Object getOrigin() {
                    return origin;
                }

                public void setOrigin(Object origin) {
                    this.origin = origin;
                }

                public Object getLevel() {
                    return level;
                }

                public void setLevel(Object level) {
                    this.level = level;
                }

                public Object getCount() {
                    return count;
                }

                public void setCount(Object count) {
                    this.count = count;
                }

                public Object getNumber() {
                    return number;
                }

                public void setNumber(Object number) {
                    this.number = number;
                }

                public Object getRegion() {
                    return region;
                }

                public void setRegion(Object region) {
                    this.region = region;
                }
            }
        }
    }
}
