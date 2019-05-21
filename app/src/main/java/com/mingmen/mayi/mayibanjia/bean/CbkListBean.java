package com.mingmen.mayi.mayibanjia.bean;

/**
 * Created by Administrator on 2019/5/15.
 */

public class CbkListBean {

    /**
     * state : 0
     * food_formula_id : 1
     * classify_id : null
     * type_four_id : null
     * create_time : 2019-4-1 13:27:36
     * classify_name : null
     * ingredients_remarke : 没有辅料
     * gross_profit : 15
     * flavour_remarke : 没有配料
     * host_remarke : 没有主料
     * cllist : null
     * price : null
     * food_photo : http://qiniu.canchengxiang.com/FsMbeTEiaC7z5XQU-_hMLd-Qin9a?e=1554185885&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:K9GQ4izxTrogyoF57aQGEEu8A30=
     * company_id : 1e794e44445943e3a8aafd4a65576b9f
     * user_token : null
     * food_name : 尖椒木耳
     * costing : 50
     * sale_price : 65
     * fulist : null
     * zhulist : null
     * peilist : null
     */

    private String state;
    private String food_formula_id;
    private String create_time;
    private String ingredients_remarke;
    private String gross_profit;
    private String flavour_remarke;
    private String host_remarke;
    private String food_photo;
    private String company_id;
    private String food_name;
    private String costing;
    private String sale_price;
    private String recommend_price;

    public String getRecommend_price() {
        return recommend_price;
    }

    public void setRecommend_price(String recommend_price) {
        this.recommend_price = recommend_price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFood_formula_id() {
        return food_formula_id;
    }

    public void setFood_formula_id(String food_formula_id) {
        this.food_formula_id = food_formula_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getIngredients_remarke() {
        return ingredients_remarke;
    }

    public void setIngredients_remarke(String ingredients_remarke) {
        this.ingredients_remarke = ingredients_remarke;
    }

    public String getGross_profit() {
        return gross_profit;
    }

    public void setGross_profit(String gross_profit) {
        this.gross_profit = gross_profit;
    }

    public String getFlavour_remarke() {
        return flavour_remarke;
    }

    public void setFlavour_remarke(String flavour_remarke) {
        this.flavour_remarke = flavour_remarke;
    }

    public String getHost_remarke() {
        return host_remarke;
    }

    public void setHost_remarke(String host_remarke) {
        this.host_remarke = host_remarke;
    }

    public String getFood_photo() {
        return food_photo;
    }

    public void setFood_photo(String food_photo) {
        this.food_photo = food_photo;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getCosting() {
        return costing;
    }

    public void setCosting(String costing) {
        this.costing = costing;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }
}
