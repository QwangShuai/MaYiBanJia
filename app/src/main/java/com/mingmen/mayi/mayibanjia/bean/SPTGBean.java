package com.mingmen.mayi.mayibanjia.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/12/19.
 */

public class SPTGBean {

    /**
     * fllist : [{"freight_fee":32,"classify_name":"蔬菜","picture_url":"4aa022f63da444518e8984988c649e9asc.jpg","market_id":"1","classify_id":"cae0e9688b36471a8efffd9bd544ff9f","market_name":"哈达市场","sonorderlist":[{"count":20,"pack_standard_id":"93a5ebaca0534f1b9aa4ad612f7dcce9","special_commodity":"","pack_standard_name":"斤","price":"3.00斤","commodity_id":"218b6dc299924cc1a624e9d43e807e50","purchase_id":"9c17c865fd434d21908e49c44f5ab5d7","classify_name":"小柿子","picture_url":"http://qiniu.canchengxiang.com/c0a6819c7fa94e51943ed6321142f05d?e=1545128162&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:P91jopUmEZh1g8jETqhHcN6_eHo=","all_price":60,"create_time":"2018-12-18 15:01:33","market_id":"1","son_order_id":"8f0a7c29046e45678ddfa6e750a189df","classify_id":"cae0e9688b36471a8efffd9bd544ff9f","sort_id":"12345678912345634234242546aaabbb","market_name":"哈达市场"},{"count":10,"pack_standard_id":"93a5ebaca0534f1b9aa4ad612f7dcce9","special_commodity":"得劲","pack_standard_name":"斤","price":"5.00斤","commodity_id":"ca6b273a8b7a4a29ab0297813eaae8eb","purchase_id":"9c17c865fd434d21908e49c44f5ab5d7","classify_name":"小柿子","picture_url":"http://qiniu.canchengxiang.com/e1971db31edf4e58b2eab3d9fd08c455?e=1545128162&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:nHJsaQdgxneNL87NainkJukK4UA=","all_price":50,"create_time":"2018-12-18 15:01:26","market_id":"1","son_order_id":"d0334ae1a7ee48a4a25d67781035d522","classify_id":"cae0e9688b36471a8efffd9bd544ff9f","sort_id":"12345678912345634234242546aaabbb","market_name":"哈达市场"}]}]
     * zongjia : 192.0
     */

    private double zongjia;
    private List<FllistBean> fllist;

    public double getZongjia() {
        return zongjia;
    }

    public void setZongjia(double zongjia) {
        this.zongjia = zongjia;
    }

    public List<FllistBean> getFllist() {
        return fllist;
    }

    public void setFllist(List<FllistBean> fllist) {
        this.fllist = fllist;
    }

    public static class FllistBean {
        /**
         * freight_fee : 32.0
         * classify_name : 蔬菜
         * picture_url : 4aa022f63da444518e8984988c649e9asc.jpg
         * market_id : 1
         * classify_id : cae0e9688b36471a8efffd9bd544ff9f
         * market_name : 哈达市场
         * sonorderlist : [{"count":20,"pack_standard_id":"93a5ebaca0534f1b9aa4ad612f7dcce9","special_commodity":"","pack_standard_name":"斤","price":"3.00斤","commodity_id":"218b6dc299924cc1a624e9d43e807e50","purchase_id":"9c17c865fd434d21908e49c44f5ab5d7","classify_name":"小柿子","picture_url":"http://qiniu.canchengxiang.com/c0a6819c7fa94e51943ed6321142f05d?e=1545128162&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:P91jopUmEZh1g8jETqhHcN6_eHo=","all_price":60,"create_time":"2018-12-18 15:01:33","market_id":"1","son_order_id":"8f0a7c29046e45678ddfa6e750a189df","classify_id":"cae0e9688b36471a8efffd9bd544ff9f","sort_id":"12345678912345634234242546aaabbb","market_name":"哈达市场"},{"count":10,"pack_standard_id":"93a5ebaca0534f1b9aa4ad612f7dcce9","special_commodity":"得劲","pack_standard_name":"斤","price":"5.00斤","commodity_id":"ca6b273a8b7a4a29ab0297813eaae8eb","purchase_id":"9c17c865fd434d21908e49c44f5ab5d7","classify_name":"小柿子","picture_url":"http://qiniu.canchengxiang.com/e1971db31edf4e58b2eab3d9fd08c455?e=1545128162&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:nHJsaQdgxneNL87NainkJukK4UA=","all_price":50,"create_time":"2018-12-18 15:01:26","market_id":"1","son_order_id":"d0334ae1a7ee48a4a25d67781035d522","classify_id":"cae0e9688b36471a8efffd9bd544ff9f","sort_id":"12345678912345634234242546aaabbb","market_name":"哈达市场"}]
         */

        private double freight_fee;
        private String classify_name;
        private String picture_url;
        private String market_id;
        private String classify_id;
        private String market_name;
        private List<SonorderlistBean> sonorderlist;

        public double getFreight_fee() {
            return freight_fee;
        }

        public void setFreight_fee(double freight_fee) {
            this.freight_fee = freight_fee;
        }

        public String getClassify_name() {
            return classify_name;
        }

        public void setClassify_name(String classify_name) {
            this.classify_name = classify_name;
        }

        public String getPicture_url() {
            return picture_url;
        }

        public void setPicture_url(String picture_url) {
            this.picture_url = picture_url;
        }

        public String getMarket_id() {
            return market_id;
        }

        public void setMarket_id(String market_id) {
            this.market_id = market_id;
        }

        public String getClassify_id() {
            return classify_id;
        }

        public void setClassify_id(String classify_id) {
            this.classify_id = classify_id;
        }

        public String getMarket_name() {
            return market_name;
        }

        public void setMarket_name(String market_name) {
            this.market_name = market_name;
        }

        public List<SonorderlistBean> getSonorderlist() {
            return sonorderlist;
        }

        public void setSonorderlist(List<SonorderlistBean> sonorderlist) {
            this.sonorderlist = sonorderlist;
        }

        public static class SonorderlistBean {
            /**
             * count : 20
             * pack_standard_id : 93a5ebaca0534f1b9aa4ad612f7dcce9
             * special_commodity :
             * pack_standard_name : 斤
             * price : 3.00斤
             * commodity_id : 218b6dc299924cc1a624e9d43e807e50
             * purchase_id : 9c17c865fd434d21908e49c44f5ab5d7
             * classify_name : 小柿子
             * picture_url : http://qiniu.canchengxiang.com/c0a6819c7fa94e51943ed6321142f05d?e=1545128162&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:P91jopUmEZh1g8jETqhHcN6_eHo=
             * all_price : 60.0
             * create_time : 2018-12-18 15:01:33
             * market_id : 1
             * son_order_id : 8f0a7c29046e45678ddfa6e750a189df
             * classify_id : cae0e9688b36471a8efffd9bd544ff9f
             * sort_id : 12345678912345634234242546aaabbb
             * market_name : 哈达市场
             */

            private int count;
            private String pack_standard_id;
            private String special_commodity;
            private String pack_standard_name;
            private String price;
            private String commodity_id;
            private String purchase_id;
            private String classify_name;
            private String picture_url;
            private double all_price;
            private String create_time;
            private String market_id;
            private String son_order_id;
            private String classify_id;
            private String sort_id;
            private String market_name;
            private String company_name;

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getPack_standard_id() {
                return pack_standard_id;
            }

            public void setPack_standard_id(String pack_standard_id) {
                this.pack_standard_id = pack_standard_id;
            }

            public String getSpecial_commodity() {
                return special_commodity;
            }

            public void setSpecial_commodity(String special_commodity) {
                this.special_commodity = special_commodity;
            }

            public String getPack_standard_name() {
                return pack_standard_name;
            }

            public void setPack_standard_name(String pack_standard_name) {
                this.pack_standard_name = pack_standard_name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getCommodity_id() {
                return commodity_id;
            }

            public void setCommodity_id(String commodity_id) {
                this.commodity_id = commodity_id;
            }

            public String getPurchase_id() {
                return purchase_id;
            }

            public void setPurchase_id(String purchase_id) {
                this.purchase_id = purchase_id;
            }

            public String getClassify_name() {
                return classify_name;
            }

            public void setClassify_name(String classify_name) {
                this.classify_name = classify_name;
            }

            public String getPicture_url() {
                return picture_url;
            }

            public void setPicture_url(String picture_url) {
                this.picture_url = picture_url;
            }

            public double getAll_price() {
                return all_price;
            }

            public void setAll_price(double all_price) {
                this.all_price = all_price;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getMarket_id() {
                return market_id;
            }

            public void setMarket_id(String market_id) {
                this.market_id = market_id;
            }

            public String getSon_order_id() {
                return son_order_id;
            }

            public void setSon_order_id(String son_order_id) {
                this.son_order_id = son_order_id;
            }

            public String getClassify_id() {
                return classify_id;
            }

            public void setClassify_id(String classify_id) {
                this.classify_id = classify_id;
            }

            public String getSort_id() {
                return sort_id;
            }

            public void setSort_id(String sort_id) {
                this.sort_id = sort_id;
            }

            public String getMarket_name() {
                return market_name;
            }

            public void setMarket_name(String market_name) {
                this.market_name = market_name;
            }
        }
    }
}
