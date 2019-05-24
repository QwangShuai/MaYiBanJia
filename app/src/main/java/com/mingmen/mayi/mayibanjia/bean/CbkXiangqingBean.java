package com.mingmen.mayi.mayibanjia.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/5/21.
 */

public class CbkXiangqingBean {

    private String host_remarke;
    private String costing;
    private String food_photo;
    private String gross_profit;
    private String food_name;
    private String sale_price;

    private List<CbkBean> zhulist;
    private List<CbkBean> fulist;
    private List<CbkBean> peilist;

    public String getCosting() {
        return costing;
    }

    public void setCosting(String costing) {
        this.costing = costing;
    }

    public String getFood_photo() {
        return food_photo;
    }

    public void setFood_photo(String food_photo) {
        this.food_photo = food_photo;
    }

    public String getGross_profit() {
        return gross_profit;
    }

    public void setGross_profit(String gross_profit) {
        this.gross_profit = gross_profit;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public String getHost_remarke() {
        return host_remarke;
    }

    public void setHost_remarke(String host_remarke) {
        this.host_remarke = host_remarke;
    }
    public List<CbkBean> getZhulist() {
        return zhulist;
    }

    public void setZhulist(List<CbkBean> zhulist) {
        this.zhulist = zhulist;
    }

    public List<CbkBean> getFulist() {
        return fulist;
    }

    public void setFulist(List<CbkBean> fulist) {
        this.fulist = fulist;
    }

    public List<CbkBean> getPeilist() {
        return peilist;
    }

    public void setPeilist(List<CbkBean> peilist) {
        this.peilist = peilist;
    }

    public static class CbkBean{
        private String count;
        private String price;
        private String commodity_id;
        private String commodity_name;
        private String classify_name;
        private String type_four_id;
        private String spec_name;

        public String getType_four_id() {
            return type_four_id;
        }

        public void setType_four_id(String type_four_id) {
            this.type_four_id = type_four_id;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
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

        public String getCommodity_name() {
            return commodity_name;
        }

        public void setCommodity_name(String commodity_name) {
            this.commodity_name = commodity_name;
        }

        public String getClassify_name() {
            return classify_name;
        }

        public void setClassify_name(String classify_name) {
            this.classify_name = classify_name;
        }

        public String getSpec_name() {
            return spec_name;
        }

        public void setSpec_name(String spec_name) {
            this.spec_name = spec_name;
        }
    }
}
