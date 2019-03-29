package com.mingmen.mayi.mayibanjia.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/18/018.
 */

public class GWCDianPuShangPinBean {

    private String type;
    private int number;
    private String company_id;
    private String account_id;
    private String pice;
    private String user_token;
    private String shopping_id;
    private String commodity_id;
    private String company_name;
    private String pack_standard;
    private String commodity_name;
    private String pack_standard_tree;
    private boolean isSelected=false;
    private String liuyan="";
    private List<GWCShangPinBean.ShoppingBean> shangPinBeen;
    private String market_name;
    private String market_id;
    private String son_number;
    private String type_tree_id;
    private String realtime;

    public String getRealtime() {
        return realtime;
    }

    public void setRealtime(String realtime) {
        this.realtime = realtime;
    }

    public String getSon_number() {
        return son_number;
    }

    public void setSon_number(String son_number) {
        this.son_number = son_number;
    }

    public String getType_tree_id() {
        return type_tree_id;
    }

    public void setType_tree_id(String type_tree_id) {
        this.type_tree_id = type_tree_id;
    }

    public String getMarket_name() {
        return market_name;
    }

    public void setMarket_name(String market_name) {
        this.market_name = market_name;
    }

    public String getMarket_id() {
        return market_id;
    }

    public void setMarket_id(String market_id) {
        this.market_id = market_id;
    }

    public String getLiuyan() {
        return liuyan;
    }

    public void setLiuyan(String liuyan) {
        this.liuyan = liuyan;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getPice() {
        return pice;
    }

    public void setPice(String pice) {
        this.pice = pice;
    }

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public String getShopping_id() {
        return shopping_id;
    }

    public void setShopping_id(String shopping_id) {
        this.shopping_id = shopping_id;
    }

    public String getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(String commodity_id) {
        this.commodity_id = commodity_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getPack_standard() {
        return pack_standard;
    }

    public void setPack_standard(String pack_standard) {
        this.pack_standard = pack_standard;
    }

    public String getCommodity_name() {
        return commodity_name;
    }

    public void setCommodity_name(String commodity_name) {
        this.commodity_name = commodity_name;
    }

    public String getPack_standard_tree() {
        return pack_standard_tree;
    }

    public void setPack_standard_tree(String pack_standard_tree) {
        this.pack_standard_tree = pack_standard_tree;
    }

    public List<GWCShangPinBean.ShoppingBean> getShangPinBeen() {
        return shangPinBeen;
    }

    public void setShangPinBeen(List<GWCShangPinBean.ShoppingBean> shangPinBeen) {
        this.shangPinBeen = shangPinBeen;
    }


}
