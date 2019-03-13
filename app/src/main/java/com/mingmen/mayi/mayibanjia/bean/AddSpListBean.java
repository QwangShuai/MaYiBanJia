package com.mingmen.mayi.mayibanjia.bean;

/**
 * Created by Administrator on 2019/1/28.
 */

public class AddSpListBean {
    private String classify_id;
    private String pack_standard_id;
    private String sort_id;
    private String special_commodity;
    private String count;

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

    public String getClassify_id() {
        return classify_id;
    }

    public void setClassify_id(String classify_id) {
        this.classify_id = classify_id;
    }

    public String getPack_standard_id() {
        return pack_standard_id;
    }

    public void setPack_standard_id(String pack_standard_id) {
        this.pack_standard_id = pack_standard_id;
    }

    public String getSort_id() {
        return sort_id;
    }

    public void setSort_id(String sort_id) {
        this.sort_id = sort_id;
    }
}
