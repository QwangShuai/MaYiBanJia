package com.mingmen.mayi.mayibanjia.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2018/8/22.
 */

public class QueRenDingDanShangPinBean {

    /**
     * state : null
     * region : null
     * sumZL : 0
     * gonglishu : null
     * dplist : [{"state":null,"type":null,"region":"230103","principal":"李岩","pice_one":null,"street":"23010301","end_hour":null,"user_token":null,"account_id":null,"company_id":"234557c23edc46c0a454d4dfea17131b","telephone":"13339309994","city":"230100","province":"230000","son_number":"1","scale":null,"pageNumber":null,"file_path":"6ba7a072374243259e0f2276fa02cba98.jpg","photo":null,"creater":null,"picture_id":null,"hostphoto":null,"zz":null,"fr":null,"id_number":"230605192311272615","order_id":null,"price":null,"ration_one":null,"list":[{"type":null,"number":8,"count":8,"pice_one":10,"user_token":null,"account_id":null,"company_id":"234557c23edc46c0a454d4dfea17131b","url":"http://pbn4jedp4.bkt.clouddn.com/d61a464bd1824592bca1b056261c2dcd?e=1542352618&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:SJ2clmgkmRqkPWY_qd0QcKxvTkE=","inventory":"5000","ration_one":"8","change_time":"2018-11-08 09:34:53","pack_standard":"","company_name":"测试","commodity_id":"0b4d8e5c7edc49f69d90aff2353dafd4","purchase_id":null,"append_money":null,"shopping_id":null,"special_commodity":null,"commodity_name":"肉cs","create_time":null,"pice":null,"ration_two":null,"pice_two":null,"pack_standard_tree":"93a5ebaca0534f1b9aa4ad612f7dcce9","region":null,"level":null,"origin":null,"city":null,"province":null,"son_number":null,"pageNumber":null,"file_path":null,"acount":null,"goods":"0","creater":"1","picture_id":null,"hostphoto":null,"order_id":null,"price":"10","collect_id":null,"evaluate":null,"spec_name":null,"market_id":null,"market_name":null,"classify_name":null,"commodity_state":0,"specThreeNum":null,"companyName":null,"delivery_time":null,"packOneName":null,"pack_standard_one":null,"spec_describe":"","choose_specifications":null,"sumGoodsSales":null,"recommended_categories":"202,201","pack_standard_tree_name":null,"star_evaluation":null,"packFourName":null,"type_two_id":"ca769ca97a3545b8803b8525d722c4d6","whether_ration":1,"specFourNum":null,"pack_standard_four":null,"pack_standard_one_name":null,"deputyphoto":null,"specific_address":null,"approval_state":"2","picture_url":null,"commodity_sales":248,"packStandard":null,"son_order_id":null,"quote_price_id":null,"dpicture":null,"hpicture":null,"avgNum":null,"split_type":null,"specOneNum":null,"specTwoNum":null,"creat_time":"2018-11-07 14:42:33","hostPath":null,"changer":"1","proportion":null,"apply":null,"ftPicture":null,"sortOrder":null,"pice_three":null,"provName":null,"listsmall":null,"citName":null,"listjg":null,"shuliang":null,"big":null,"small":null,"countName":null,"pack_id":null,"avgPrice":null,"deputyPicture":null,"number_views":0,"type_one_id":"343ad67bee889f873b45df78989cc870","type_tree_id":"f39ee1526d194adabb800c77c400028b","companyAddress":null,"comend_address":"601","companyPhoto":null,"packTwoName":null,"ration_three":null,"packThreeName":null,"picture_address":null,"bigCommodity_id":null,"smallCommodity_id":null,"pack_standard_two":null,"hostPicture":null,"total_weight":null}],"market_name":null,"commodity_state":null,"change_time":null,"attention_number":"1","audit_state":"2","company_number":null,"dianpudianhua":null,"companyList":null,"salesman_time":null,"id_positive":null,"company_short":"测试","specific_address":"测试地址","salesman_duty":"黑龙江省哈尔滨市南岗区哈西大街219号靠近哈西大街(地铁站)","parent_number":"201","company_name":"测试","business_state":"0","commodity_id":null,"duty_paragraph":"123112","legal_person":"测试","approval_state":null,"commodity_sales":null,"gy_company_id":null,"comment_text":null,"gy_order_id":null,"deliver_address":null,"commodity_name":null,"attention_id":null,"create_time":null,"business_license":"504ce82a0a754f4d98a1f1083924a8cd16pic_2175213_b.jpg","booth_number":null,"circulation_permit":"","changer":null,"scanState":null,"evaluation":0,"pay_type":null,"yewuyuan":null,"kefuphone":null,"listjgg":null,"shu":null,"dpprice":null,"remark":null,"kefu":null,"quYMC":null,"son_namea":null,"quYMCc":null,"isBuy":null,"begin_hour":null,"quYMCb":null,"id_reverse":null,"quYMCa":null,"listxl":null,"listsp":null,"ywyphone":null,"son_name":null,"phone":null,"sortOrder":null,"scanCount":null,"packCount":null,"listjg":null,"order_number":null,"type_tree_id":null}]
     * mark_id : 1
     * user_token : null
     * city : null
     * province : null
     * order_id : null
     * price : null
     * all_price : null
     * list : null
     * levelState : null
     * market_id : null
     * market_name : 哈达市场
     * classify_id : null
     * driver_phone : null
     * wl_order_state : null
     * plate_number : null
     * driver_name : null
     * update_time : null
     * specific_address : null
     * parent_number : null
     * freight_fee : null
     * create_time : null
     * update_by : null
     * money : 0
     * create_by : null
     * orderState : null
     */


    private String ct_buy_final_name;
    private List<MarketlistBean> marketlist;

    public List<MarketlistBean> getMarketlist() {
        return marketlist;
    }

    public void setMarketlist(List<MarketlistBean> marketlist) {
        this.marketlist = marketlist;
    }

    public String getCt_buy_final_name() {
        return ct_buy_final_name;
    }

    public void setCt_buy_final_name(String ct_buy_final_name) {
        this.ct_buy_final_name = ct_buy_final_name;
    }



    public static class MarketlistBean {
        private Double sumZL;
        private String mark_id;
        private String market_id;
        private String market_name;
        private BigDecimal freight_fee;
        private double money;
        private BigDecimal yunfei;
        private List<DplistBean> dplist;

        private List<CgzhulistBean> cgzhulist;

        public String getMarket_name() {
            return market_name;
        }

        public void setMarket_name(String market_name) {
            this.market_name = market_name;
        }

        public List<CgzhulistBean> getCgzhulist() {
            return cgzhulist;
        }

        public void setCgzhulist(List<CgzhulistBean> cgzhulist) {
            this.cgzhulist = cgzhulist;
        }

        public static class CgzhulistBean{
            private String purchase_name;
            private String zongjia;
            private List<CgzilistBean> cgzilist;

            public List<CgzilistBean> getCgzilist() {
                return cgzilist;
            }

            public void setCgzilist(List<CgzilistBean> cgzilist) {
                this.cgzilist = cgzilist;
            }

            public String getPurchase_name() {
                return purchase_name;
            }

            public void setPurchase_name(String purchase_name) {
                this.purchase_name = purchase_name;
            }

            public String getZongjia() {
                return zongjia;
            }

            public void setZongjia(String zongjia) {
                this.zongjia = zongjia;
            }

            public static class CgzilistBean{
                private String commodity_id;
                private int count;

                public String getCommodity_id() {
                    return commodity_id;
                }

                public void setCommodity_id(String commodity_id) {
                    this.commodity_id = commodity_id;
                }

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }
            }
        }
        public BigDecimal getYunfei() {
            return yunfei;
        }

        public void setYunfei(BigDecimal yunfei) {
            this.yunfei = yunfei;
        }


        public Double getSumZL() {
            return sumZL;
        }

        public void setSumZL(Double sumZL) {
            this.sumZL = sumZL;
        }


        public String getMark_id() {
            return mark_id;
        }

        public void setMark_id(String mark_id) {
            this.mark_id = mark_id;
        }

        public String getMarket_id() {
            return market_id;
        }

        public void setMarket_id(String market_id) {
            this.market_id = market_id;
        }


        public BigDecimal getFreight_fee() {
            return freight_fee;
        }

        public void setFreight_fee(BigDecimal freight_fee) {
            this.freight_fee = freight_fee;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public List<DplistBean> getDplist() {
            return dplist;
        }

        public void setDplist(List<DplistBean> dplist) {
            this.dplist = dplist;
        }
        public static class DplistBean {
            /**
             * state : null
             * type : null
             * region : 230103
             * principal : 李岩
             * pice_one : null
             * street : 23010301
             * end_hour : null
             * user_token : null
             * account_id : null
             * company_id : 234557c23edc46c0a454d4dfea17131b
             * telephone : 13339309994
             * city : 230100
             * province : 230000
             * son_number : 1
             * scale : null
             * pageNumber : null
             * file_path : 6ba7a072374243259e0f2276fa02cba98.jpg
             * photo : null
             * creater : null
             * picture_id : null
             * hostphoto : null
             * zz : null
             * fr : null
             * id_number : 230605192311272615
             * order_id : null
             * price : null
             * ration_one : null
             * list : [{"type":null,"number":8,"count":8,"pice_one":10,"user_token":null,"account_id":null,"company_id":"234557c23edc46c0a454d4dfea17131b","url":"http://pbn4jedp4.bkt.clouddn.com/d61a464bd1824592bca1b056261c2dcd?e=1542352618&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:SJ2clmgkmRqkPWY_qd0QcKxvTkE=","inventory":"5000","ration_one":"8","change_time":"2018-11-08 09:34:53","pack_standard":"","company_name":"测试","commodity_id":"0b4d8e5c7edc49f69d90aff2353dafd4","purchase_id":null,"append_money":null,"shopping_id":null,"special_commodity":null,"commodity_name":"肉cs","create_time":null,"pice":null,"ration_two":null,"pice_two":null,"pack_standard_tree":"93a5ebaca0534f1b9aa4ad612f7dcce9","region":null,"level":null,"origin":null,"city":null,"province":null,"son_number":null,"pageNumber":null,"file_path":null,"acount":null,"goods":"0","creater":"1","picture_id":null,"hostphoto":null,"order_id":null,"price":"10","collect_id":null,"evaluate":null,"spec_name":null,"market_id":null,"market_name":null,"classify_name":null,"commodity_state":0,"specThreeNum":null,"companyName":null,"delivery_time":null,"packOneName":null,"pack_standard_one":null,"spec_describe":"","choose_specifications":null,"sumGoodsSales":null,"recommended_categories":"202,201","pack_standard_tree_name":null,"star_evaluation":null,"packFourName":null,"type_two_id":"ca769ca97a3545b8803b8525d722c4d6","whether_ration":1,"specFourNum":null,"pack_standard_four":null,"pack_standard_one_name":null,"deputyphoto":null,"specific_address":null,"approval_state":"2","picture_url":null,"commodity_sales":248,"packStandard":null,"son_order_id":null,"quote_price_id":null,"dpicture":null,"hpicture":null,"avgNum":null,"split_type":null,"specOneNum":null,"specTwoNum":null,"creat_time":"2018-11-07 14:42:33","hostPath":null,"changer":"1","proportion":null,"apply":null,"ftPicture":null,"sortOrder":null,"pice_three":null,"provName":null,"listsmall":null,"citName":null,"listjg":null,"shuliang":null,"big":null,"small":null,"countName":null,"pack_id":null,"avgPrice":null,"deputyPicture":null,"number_views":0,"type_one_id":"343ad67bee889f873b45df78989cc870","type_tree_id":"f39ee1526d194adabb800c77c400028b","companyAddress":null,"comend_address":"601","companyPhoto":null,"packTwoName":null,"ration_three":null,"packThreeName":null,"picture_address":null,"bigCommodity_id":null,"smallCommodity_id":null,"pack_standard_two":null,"hostPicture":null,"total_weight":null}]
             * market_name : null
             * commodity_state : null
             * change_time : null
             * attention_number : 1
             * audit_state : 2
             * company_number : null
             * dianpudianhua : null
             * companyList : null
             * salesman_time : null
             * id_positive : null
             * company_short : 测试
             * specific_address : 测试地址
             * salesman_duty : 黑龙江省哈尔滨市南岗区哈西大街219号靠近哈西大街(地铁站)
             * parent_number : 201
             * company_name : 测试
             * business_state : 0
             * commodity_id : null
             * duty_paragraph : 123112
             * legal_person : 测试
             * approval_state : null
             * commodity_sales : null
             * gy_company_id : null
             * comment_text : null
             * gy_order_id : null
             * deliver_address : null
             * commodity_name : null
             * attention_id : null
             * create_time : null
             * business_license : 504ce82a0a754f4d98a1f1083924a8cd16pic_2175213_b.jpg
             * booth_number : null
             * circulation_permit :
             * changer : null
             * scanState : null
             * evaluation : 0.0
             * pay_type : null
             * yewuyuan : null
             * kefuphone : null
             * listjgg : null
             * shu : null
             * dpprice : null
             * remark : null
             * kefu : null
             * quYMC : null
             * son_namea : null
             * quYMCc : null
             * isBuy : null
             * begin_hour : null
             * quYMCb : null
             * id_reverse : null
             * quYMCa : null
             * listxl : null
             * listsp : null
             * ywyphone : null
             * son_name : null
             * phone : null
             * sortOrder : null
             * scanCount : null
             * packCount : null
             * listjg : null
             * order_number : null
             * type_tree_id : null
             */

            private String region;
            private String principal;
            private String street;
            private String company_id;
            private String telephone;
            private String city;
            private String province;
            private String son_number;
            private String file_path;
            private String id_number;
            private String attention_number;
            private String audit_state;
            private String company_short;
            private String specific_address;
            private String salesman_duty;
            private String parent_number;
            private String company_name;
            private String business_state;
            private String duty_paragraph;
            private String legal_person;
            private String business_license;
            private String circulation_permit;
            private String remark;
            private double evaluation;
            private List<ListBean> list;

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public String getPrincipal() {
                return principal;
            }

            public void setPrincipal(String principal) {
                this.principal = principal;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }

            public String getCompany_id() {
                return company_id;
            }

            public void setCompany_id(String company_id) {
                this.company_id = company_id;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getSon_number() {
                return son_number;
            }

            public void setSon_number(String son_number) {
                this.son_number = son_number;
            }


            public String getId_number() {
                return id_number;
            }

            public void setId_number(String id_number) {
                this.id_number = id_number;
            }

            public String getAttention_number() {
                return attention_number;
            }

            public void setAttention_number(String attention_number) {
                this.attention_number = attention_number;
            }

            public String getAudit_state() {
                return audit_state;
            }

            public void setAudit_state(String audit_state) {
                this.audit_state = audit_state;
            }

            public String getCompany_short() {
                return company_short;
            }

            public void setCompany_short(String company_short) {
                this.company_short = company_short;
            }

            public String getSpecific_address() {
                return specific_address;
            }

            public void setSpecific_address(String specific_address) {
                this.specific_address = specific_address;
            }

            public String getSalesman_duty() {
                return salesman_duty;
            }

            public void setSalesman_duty(String salesman_duty) {
                this.salesman_duty = salesman_duty;
            }

            public String getParent_number() {
                return parent_number;
            }

            public void setParent_number(String parent_number) {
                this.parent_number = parent_number;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public String getBusiness_state() {
                return business_state;
            }

            public void setBusiness_state(String business_state) {
                this.business_state = business_state;
            }

            public String getDuty_paragraph() {
                return duty_paragraph;
            }

            public void setDuty_paragraph(String duty_paragraph) {
                this.duty_paragraph = duty_paragraph;
            }

            public String getLegal_person() {
                return legal_person;
            }

            public void setLegal_person(String legal_person) {
                this.legal_person = legal_person;
            }

            public String getBusiness_license() {
                return business_license;
            }

            public void setBusiness_license(String business_license) {
                this.business_license = business_license;
            }
            public String getCirculation_permit() {
                return circulation_permit;
            }

            public void setCirculation_permit(String circulation_permit) {
                this.circulation_permit = circulation_permit;
            }
            public double getEvaluation() {
                return evaluation;
            }

            public void setEvaluation(double evaluation) {
                this.evaluation = evaluation;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * type : null
                 * number : 8
                 * count : 8
                 * pice_one : 10
                 * user_token : null
                 * account_id : null
                 * company_id : 234557c23edc46c0a454d4dfea17131b
                 * url : http://pbn4jedp4.bkt.clouddn.com/d61a464bd1824592bca1b056261c2dcd?e=1542352618&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:SJ2clmgkmRqkPWY_qd0QcKxvTkE=
                 * inventory : 5000
                 * ration_one : 8
                 * change_time : 2018-11-08 09:34:53
                 * pack_standard :
                 * company_name : 测试
                 * commodity_id : 0b4d8e5c7edc49f69d90aff2353dafd4
                 * purchase_id : null
                 * append_money : null
                 * shopping_id : null
                 * special_commodity : null
                 * commodity_name : 肉cs
                 * create_time : null
                 * pice : null
                 * ration_two : null
                 * pice_two : null
                 * pack_standard_tree : 93a5ebaca0534f1b9aa4ad612f7dcce9
                 * region : null
                 * level : null
                 * origin : null
                 * city : null
                 * province : null
                 * son_number : null
                 * pageNumber : null
                 * file_path : null
                 * acount : null
                 * goods : 0
                 * creater : 1
                 * picture_id : null
                 * hostphoto : null
                 * order_id : null
                 * price : 10
                 * collect_id : null
                 * evaluate : null
                 * spec_name : null
                 * market_id : null
                 * market_name : null
                 * classify_name : null
                 * commodity_state : 0
                 * specThreeNum : null
                 * companyName : null
                 * delivery_time : null
                 * packOneName : null
                 * pack_standard_one : null
                 * spec_describe :
                 * choose_specifications : null
                 * sumGoodsSales : null
                 * recommended_categories : 202,201
                 * pack_standard_tree_name : null
                 * star_evaluation : null
                 * packFourName : null
                 * type_two_id : ca769ca97a3545b8803b8525d722c4d6
                 * whether_ration : 1
                 * specFourNum : null
                 * pack_standard_four : null
                 * pack_standard_one_name : null
                 * deputyphoto : null
                 * specific_address : null
                 * approval_state : 2
                 * picture_url : null
                 * commodity_sales : 248
                 * packStandard : null
                 * son_order_id : null
                 * quote_price_id : null
                 * dpicture : null
                 * hpicture : null
                 * avgNum : null
                 * split_type : null
                 * specOneNum : null
                 * specTwoNum : null
                 * creat_time : 2018-11-07 14:42:33
                 * hostPath : null
                 * changer : 1
                 * proportion : null
                 * apply : null
                 * ftPicture : null
                 * sortOrder : null
                 * pice_three : null
                 * provName : null
                 * listsmall : null
                 * citName : null
                 * listjg : null
                 * shuliang : null
                 * big : null
                 * small : null
                 * countName : null
                 * pack_id : null
                 * avgPrice : null
                 * deputyPicture : null
                 * number_views : 0
                 * type_one_id : 343ad67bee889f873b45df78989cc870
                 * type_tree_id : f39ee1526d194adabb800c77c400028b
                 * companyAddress : null
                 * comend_address : 601
                 * companyPhoto : null
                 * packTwoName : null
                 * ration_three : null
                 * packThreeName : null
                 * picture_address : null
                 * bigCommodity_id : null
                 * smallCommodity_id : null
                 * pack_standard_two : null
                 * hostPicture : null
                 * total_weight : null
                 */

                private Object type;
                private int number;
                private int count;
                private BigDecimal pice_one;
                private Object user_token;
                private Object account_id;
                private String company_id;
                private String url;
                private String inventory;
                private String ration_one;
                private String change_time;
                private String pack_standard;
                private String company_name;
                private String commodity_id;
                private Object purchase_id;
                private Object append_money;
                private Object shopping_id;
                private String special_commodity;
                private String commodity_name;
                private Object create_time;
                private Object pice;
                private Object ration_two;
                private Object pice_two;
                private String pack_standard_tree;
                private Object region;
                private Object level;
                private Object origin;
                private Object city;
                private Object province;
                private Object son_number;
                private Object pageNumber;
                private Object file_path;
                private Object acount;
                private String goods;
                private String creater;
                private Object picture_id;
                private Object hostphoto;
                private Object order_id;
                private String price;
                private Object collect_id;
                private Object evaluate;
                private Object spec_name;
                private Object market_id;
                private Object market_name;
                private int commodity_state;
                private Object specThreeNum;
                private Object companyName;
                private Object delivery_time;
                private Object packOneName;
                private Object pack_standard_one;
                private String spec_describe;
                private Object choose_specifications;
                private Object sumGoodsSales;
                private String recommended_categories;
                private Object pack_standard_tree_name;
                private Object star_evaluation;
                private Object packFourName;
                private String type_two_id;
                private int whether_ration;
                private Object specFourNum;
                private Object pack_standard_four;
                private Object pack_standard_one_name;
                private Object deputyphoto;
                private Object specific_address;
                private String approval_state;
                private Object picture_url;
                private int commodity_sales;
                private Object packStandard;
                private Object son_order_id;
                private Object quote_price_id;
                private Object dpicture;
                private Object hpicture;
                private Object avgNum;
                private Object split_type;
                private Object specOneNum;
                private Object specTwoNum;
                private String creat_time;
                private Object hostPath;
                private String changer;
                private Object proportion;
                private Object apply;
                private Object ftPicture;
                private Object sortOrder;
                private Object pice_three;
                private Object provName;
                private Object listsmall;
                private Object citName;
                private Object listjg;
                private Object shuliang;
                private Object big;
                private Object small;
                private Object countName;
                private Object pack_id;
                private Object avgPrice;
                private Object deputyPicture;
                private int number_views;
                private String type_one_id;
                private String type_tree_id;
                private Object companyAddress;
                private String comend_address;
                private Object companyPhoto;
                private Object packTwoName;
                private Object ration_three;
                private Object packThreeName;
                private Object picture_address;
                private Object bigCommodity_id;
                private Object smallCommodity_id;
                private Object pack_standard_two;
                private Object hostPicture;
                private Object total_weight;
                private String classify_name;

                public Object getType() {
                    return type;
                }

                public void setType(Object type) {
                    this.type = type;
                }

                public int getNumber() {
                    return number;
                }

                public void setNumber(int number) {
                    this.number = number;
                }

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }

                public BigDecimal getPice_one() {
                    return pice_one;
                }

                public void setPice_one(BigDecimal pice_one) {
                    this.pice_one = pice_one;
                }

                public Object getUser_token() {
                    return user_token;
                }

                public void setUser_token(Object user_token) {
                    this.user_token = user_token;
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

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getInventory() {
                    return inventory;
                }

                public void setInventory(String inventory) {
                    this.inventory = inventory;
                }

                public String getRation_one() {
                    return ration_one;
                }

                public void setRation_one(String ration_one) {
                    this.ration_one = ration_one;
                }

                public String getChange_time() {
                    return change_time;
                }

                public void setChange_time(String change_time) {
                    this.change_time = change_time;
                }

                public String getPack_standard() {
                    return pack_standard;
                }

                public void setPack_standard(String pack_standard) {
                    this.pack_standard = pack_standard;
                }

                public String getCompany_name() {
                    return company_name;
                }

                public void setCompany_name(String company_name) {
                    this.company_name = company_name;
                }

                public String getCommodity_id() {
                    return commodity_id;
                }

                public void setCommodity_id(String commodity_id) {
                    this.commodity_id = commodity_id;
                }

                public Object getPurchase_id() {
                    return purchase_id;
                }

                public void setPurchase_id(Object purchase_id) {
                    this.purchase_id = purchase_id;
                }

                public Object getAppend_money() {
                    return append_money;
                }

                public void setAppend_money(Object append_money) {
                    this.append_money = append_money;
                }

                public Object getShopping_id() {
                    return shopping_id;
                }

                public void setShopping_id(Object shopping_id) {
                    this.shopping_id = shopping_id;
                }

                public String getSpecial_commodity() {
                    return special_commodity;
                }

                public void setSpecial_commodity(String special_commodity) {
                    this.special_commodity = special_commodity;
                }

                public String getCommodity_name() {
                    return commodity_name;
                }

                public void setCommodity_name(String commodity_name) {
                    this.commodity_name = commodity_name;
                }

                public Object getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(Object create_time) {
                    this.create_time = create_time;
                }

                public Object getPice() {
                    return pice;
                }

                public void setPice(Object pice) {
                    this.pice = pice;
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

                public String getPack_standard_tree() {
                    return pack_standard_tree;
                }

                public void setPack_standard_tree(String pack_standard_tree) {
                    this.pack_standard_tree = pack_standard_tree;
                }

                public Object getRegion() {
                    return region;
                }

                public void setRegion(Object region) {
                    this.region = region;
                }

                public Object getLevel() {
                    return level;
                }

                public void setLevel(Object level) {
                    this.level = level;
                }

                public Object getOrigin() {
                    return origin;
                }

                public void setOrigin(Object origin) {
                    this.origin = origin;
                }

                public Object getCity() {
                    return city;
                }

                public void setCity(Object city) {
                    this.city = city;
                }

                public Object getProvince() {
                    return province;
                }

                public void setProvince(Object province) {
                    this.province = province;
                }

                public Object getSon_number() {
                    return son_number;
                }

                public void setSon_number(Object son_number) {
                    this.son_number = son_number;
                }

                public Object getPageNumber() {
                    return pageNumber;
                }

                public void setPageNumber(Object pageNumber) {
                    this.pageNumber = pageNumber;
                }

                public Object getFile_path() {
                    return file_path;
                }

                public void setFile_path(Object file_path) {
                    this.file_path = file_path;
                }

                public Object getAcount() {
                    return acount;
                }

                public void setAcount(Object acount) {
                    this.acount = acount;
                }

                public String getGoods() {
                    return goods;
                }

                public void setGoods(String goods) {
                    this.goods = goods;
                }

                public String getCreater() {
                    return creater;
                }

                public void setCreater(String creater) {
                    this.creater = creater;
                }

                public Object getPicture_id() {
                    return picture_id;
                }

                public void setPicture_id(Object picture_id) {
                    this.picture_id = picture_id;
                }

                public Object getHostphoto() {
                    return hostphoto;
                }

                public void setHostphoto(Object hostphoto) {
                    this.hostphoto = hostphoto;
                }

                public Object getOrder_id() {
                    return order_id;
                }

                public void setOrder_id(Object order_id) {
                    this.order_id = order_id;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public Object getCollect_id() {
                    return collect_id;
                }

                public void setCollect_id(Object collect_id) {
                    this.collect_id = collect_id;
                }

                public Object getEvaluate() {
                    return evaluate;
                }

                public void setEvaluate(Object evaluate) {
                    this.evaluate = evaluate;
                }

                public Object getSpec_name() {
                    return spec_name;
                }

                public void setSpec_name(Object spec_name) {
                    this.spec_name = spec_name;
                }

                public Object getMarket_id() {
                    return market_id;
                }

                public void setMarket_id(Object market_id) {
                    this.market_id = market_id;
                }

                public Object getMarket_name() {
                    return market_name;
                }

                public void setMarket_name(Object market_name) {
                    this.market_name = market_name;
                }

                public String getClassify_name() {
                    return classify_name;
                }

                public void setClassify_name(String classify_name) {
                    this.classify_name = classify_name;
                }

                public int getCommodity_state() {
                    return commodity_state;
                }

                public void setCommodity_state(int commodity_state) {
                    this.commodity_state = commodity_state;
                }

                public Object getSpecThreeNum() {
                    return specThreeNum;
                }

                public void setSpecThreeNum(Object specThreeNum) {
                    this.specThreeNum = specThreeNum;
                }

                public Object getCompanyName() {
                    return companyName;
                }

                public void setCompanyName(Object companyName) {
                    this.companyName = companyName;
                }

                public Object getDelivery_time() {
                    return delivery_time;
                }

                public void setDelivery_time(Object delivery_time) {
                    this.delivery_time = delivery_time;
                }

                public Object getPackOneName() {
                    return packOneName;
                }

                public void setPackOneName(Object packOneName) {
                    this.packOneName = packOneName;
                }

                public Object getPack_standard_one() {
                    return pack_standard_one;
                }

                public void setPack_standard_one(Object pack_standard_one) {
                    this.pack_standard_one = pack_standard_one;
                }

                public String getSpec_describe() {
                    return spec_describe;
                }

                public void setSpec_describe(String spec_describe) {
                    this.spec_describe = spec_describe;
                }

                public Object getChoose_specifications() {
                    return choose_specifications;
                }

                public void setChoose_specifications(Object choose_specifications) {
                    this.choose_specifications = choose_specifications;
                }

                public Object getSumGoodsSales() {
                    return sumGoodsSales;
                }

                public void setSumGoodsSales(Object sumGoodsSales) {
                    this.sumGoodsSales = sumGoodsSales;
                }

                public String getRecommended_categories() {
                    return recommended_categories;
                }

                public void setRecommended_categories(String recommended_categories) {
                    this.recommended_categories = recommended_categories;
                }

                public Object getPack_standard_tree_name() {
                    return pack_standard_tree_name;
                }

                public void setPack_standard_tree_name(Object pack_standard_tree_name) {
                    this.pack_standard_tree_name = pack_standard_tree_name;
                }

                public Object getStar_evaluation() {
                    return star_evaluation;
                }

                public void setStar_evaluation(Object star_evaluation) {
                    this.star_evaluation = star_evaluation;
                }

                public Object getPackFourName() {
                    return packFourName;
                }

                public void setPackFourName(Object packFourName) {
                    this.packFourName = packFourName;
                }

                public String getType_two_id() {
                    return type_two_id;
                }

                public void setType_two_id(String type_two_id) {
                    this.type_two_id = type_two_id;
                }

                public int getWhether_ration() {
                    return whether_ration;
                }

                public void setWhether_ration(int whether_ration) {
                    this.whether_ration = whether_ration;
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

                public Object getPack_standard_one_name() {
                    return pack_standard_one_name;
                }

                public void setPack_standard_one_name(Object pack_standard_one_name) {
                    this.pack_standard_one_name = pack_standard_one_name;
                }

                public Object getDeputyphoto() {
                    return deputyphoto;
                }

                public void setDeputyphoto(Object deputyphoto) {
                    this.deputyphoto = deputyphoto;
                }

                public Object getSpecific_address() {
                    return specific_address;
                }

                public void setSpecific_address(Object specific_address) {
                    this.specific_address = specific_address;
                }

                public String getApproval_state() {
                    return approval_state;
                }

                public void setApproval_state(String approval_state) {
                    this.approval_state = approval_state;
                }

                public Object getPicture_url() {
                    return picture_url;
                }

                public void setPicture_url(Object picture_url) {
                    this.picture_url = picture_url;
                }

                public int getCommodity_sales() {
                    return commodity_sales;
                }

                public void setCommodity_sales(int commodity_sales) {
                    this.commodity_sales = commodity_sales;
                }

                public Object getPackStandard() {
                    return packStandard;
                }

                public void setPackStandard(Object packStandard) {
                    this.packStandard = packStandard;
                }

                public Object getSon_order_id() {
                    return son_order_id;
                }

                public void setSon_order_id(Object son_order_id) {
                    this.son_order_id = son_order_id;
                }

                public Object getQuote_price_id() {
                    return quote_price_id;
                }

                public void setQuote_price_id(Object quote_price_id) {
                    this.quote_price_id = quote_price_id;
                }

                public Object getDpicture() {
                    return dpicture;
                }

                public void setDpicture(Object dpicture) {
                    this.dpicture = dpicture;
                }

                public Object getHpicture() {
                    return hpicture;
                }

                public void setHpicture(Object hpicture) {
                    this.hpicture = hpicture;
                }

                public Object getAvgNum() {
                    return avgNum;
                }

                public void setAvgNum(Object avgNum) {
                    this.avgNum = avgNum;
                }

                public Object getSplit_type() {
                    return split_type;
                }

                public void setSplit_type(Object split_type) {
                    this.split_type = split_type;
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

                public String getCreat_time() {
                    return creat_time;
                }

                public void setCreat_time(String creat_time) {
                    this.creat_time = creat_time;
                }

                public Object getHostPath() {
                    return hostPath;
                }

                public void setHostPath(Object hostPath) {
                    this.hostPath = hostPath;
                }

                public String getChanger() {
                    return changer;
                }

                public void setChanger(String changer) {
                    this.changer = changer;
                }

                public Object getProportion() {
                    return proportion;
                }

                public void setProportion(Object proportion) {
                    this.proportion = proportion;
                }

                public Object getApply() {
                    return apply;
                }

                public void setApply(Object apply) {
                    this.apply = apply;
                }

                public Object getFtPicture() {
                    return ftPicture;
                }

                public void setFtPicture(Object ftPicture) {
                    this.ftPicture = ftPicture;
                }

                public Object getSortOrder() {
                    return sortOrder;
                }

                public void setSortOrder(Object sortOrder) {
                    this.sortOrder = sortOrder;
                }

                public Object getPice_three() {
                    return pice_three;
                }

                public void setPice_three(Object pice_three) {
                    this.pice_three = pice_three;
                }

                public Object getProvName() {
                    return provName;
                }

                public void setProvName(Object provName) {
                    this.provName = provName;
                }

                public Object getListsmall() {
                    return listsmall;
                }

                public void setListsmall(Object listsmall) {
                    this.listsmall = listsmall;
                }

                public Object getCitName() {
                    return citName;
                }

                public void setCitName(Object citName) {
                    this.citName = citName;
                }

                public Object getListjg() {
                    return listjg;
                }

                public void setListjg(Object listjg) {
                    this.listjg = listjg;
                }

                public Object getShuliang() {
                    return shuliang;
                }

                public void setShuliang(Object shuliang) {
                    this.shuliang = shuliang;
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

                public Object getCountName() {
                    return countName;
                }

                public void setCountName(Object countName) {
                    this.countName = countName;
                }

                public Object getPack_id() {
                    return pack_id;
                }

                public void setPack_id(Object pack_id) {
                    this.pack_id = pack_id;
                }

                public Object getAvgPrice() {
                    return avgPrice;
                }

                public void setAvgPrice(Object avgPrice) {
                    this.avgPrice = avgPrice;
                }

                public Object getDeputyPicture() {
                    return deputyPicture;
                }

                public void setDeputyPicture(Object deputyPicture) {
                    this.deputyPicture = deputyPicture;
                }

                public int getNumber_views() {
                    return number_views;
                }

                public void setNumber_views(int number_views) {
                    this.number_views = number_views;
                }

                public String getType_one_id() {
                    return type_one_id;
                }

                public void setType_one_id(String type_one_id) {
                    this.type_one_id = type_one_id;
                }

                public String getType_tree_id() {
                    return type_tree_id;
                }

                public void setType_tree_id(String type_tree_id) {
                    this.type_tree_id = type_tree_id;
                }

                public Object getCompanyAddress() {
                    return companyAddress;
                }

                public void setCompanyAddress(Object companyAddress) {
                    this.companyAddress = companyAddress;
                }

                public String getComend_address() {
                    return comend_address;
                }

                public void setComend_address(String comend_address) {
                    this.comend_address = comend_address;
                }

                public Object getCompanyPhoto() {
                    return companyPhoto;
                }

                public void setCompanyPhoto(Object companyPhoto) {
                    this.companyPhoto = companyPhoto;
                }

                public Object getPackTwoName() {
                    return packTwoName;
                }

                public void setPackTwoName(Object packTwoName) {
                    this.packTwoName = packTwoName;
                }

                public Object getRation_three() {
                    return ration_three;
                }

                public void setRation_three(Object ration_three) {
                    this.ration_three = ration_three;
                }

                public Object getPackThreeName() {
                    return packThreeName;
                }

                public void setPackThreeName(Object packThreeName) {
                    this.packThreeName = packThreeName;
                }

                public Object getPicture_address() {
                    return picture_address;
                }

                public void setPicture_address(Object picture_address) {
                    this.picture_address = picture_address;
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

                public Object getPack_standard_two() {
                    return pack_standard_two;
                }

                public void setPack_standard_two(Object pack_standard_two) {
                    this.pack_standard_two = pack_standard_two;
                }

                public Object getHostPicture() {
                    return hostPicture;
                }

                public void setHostPicture(Object hostPicture) {
                    this.hostPicture = hostPicture;
                }

                public Object getTotal_weight() {
                    return total_weight;
                }

                public void setTotal_weight(Object total_weight) {
                    this.total_weight = total_weight;
                }
            }
        }
    }

}
