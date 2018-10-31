package com.mingmen.mayi.mayibanjia.bean;

/**
 * Created by Administrator on 2018/8/30.
 */

public class QiangDanBean {

    private String user_token;
    private String quote_price_id;//   id
    private String company_id;//   接单企业id
    private String son_order_id;//   采购单子表
    private String commodity_id;//   商品id
    private String market_id;//   市场id
    private String pack_standard_id;//   规格id
    private String create_time;//   创建时间
    private String sort_id;//  三级分类id
    private String state_type;//   0抢单成功  // 1未成功
    private String special_commodity;//   特殊要求
    private String count;//   数量
    private String pack_standard_name;//   规格id
    private String sort_name;//  三级分类id
    private  String movement_type;//是否已经推送


    public void setPack_standard_name(String pack_standard_name) {
        this.pack_standard_name = pack_standard_name;
    }
    public void setMovement_type(String movement_type) {
        this.movement_type = movement_type;
    }
    public String getSort_name() {
        return sort_name;
    }
    public void setSort_name(String sort_name) {
        this.sort_name = sort_name;
    }
    public String getUser_token() {
        return user_token;
    }
    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }
    public String getQuote_price_id() {
        return quote_price_id;
    }
    public void setQuote_price_id(String quote_price_id) {
        this.quote_price_id = quote_price_id;
    }
    public String getCompany_id() {
        return company_id;
    }
    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }
    public String getSon_order_id() {
        return son_order_id;
    }
    public void setSon_order_id(String son_order_id) {
        this.son_order_id = son_order_id;
    }
    public String getCommodity_id() {
        return commodity_id;
    }
    public void setCommodity_id(String commodity_id) {
        this.commodity_id = commodity_id;
    }
    public String getMarket_id() {
        return market_id;
    }
    public void setMarket_id(String market_id) {
        this.market_id = market_id;
    }
    public String getPack_standard_id() {
        return pack_standard_id;
    }
    public void setPack_standard_id(String pack_standard_id) {
        this.pack_standard_id = pack_standard_id;
    }
    public String getCreate_time() {
        return create_time;
    }
    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
    public String getSort_id() {
        return sort_id;
    }
    public void setSort_id(String sort_id) {
        this.sort_id = sort_id;
    }
    public String getState_type() {
        return state_type;
    }
    public void setState_type(String state_type) {
        this.state_type = state_type;
    }
    public String getSpecial_commodity() {
        return special_commodity;
    }
    public void setSpecial_commodity(String special_commodity) {
        this.special_commodity = special_commodity;
    }
    public String getCount() {
        return count;
    }
    public void setCount(String count) {
        this.count = count;
    }
    public String getMovement_type() {
        return movement_type;
    }

    public String getPack_standard_name() {
        return pack_standard_name;
    }

}
