package com.mingmen.mayi.mayibanjia.bean;

/**
 * Created by Administrator on 2018/10/30.
 */

public class ShangPinSousuoMohuBean {
    private String commodity_name;
    private String classify_name;
    private String commodity_id;
    private String classify_id;
    private String spec_name;
    private String spec_idFour;
    private String spec_describe;
    private String spec_description;
    private boolean isSelect;

    public String getSpec_description() {
        return spec_description;
    }

    public void setSpec_description(String spec_description) {
        this.spec_description = spec_description;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getSpec_describe() {
        return spec_describe;
    }

    public void setSpec_describe(String spec_describe) {
        this.spec_describe = spec_describe;
    }

    public String getSpec_idFour() {
        return spec_idFour;
    }

    public void setSpec_idFour(String spec_idFour) {
        this.spec_idFour = spec_idFour;
    }

    private String affiliated_number;
    private String affiliated_spec_name;
    private String affiliated_spec;

    public String getAffiliated_spec() {
        return affiliated_spec;
    }

    public void setAffiliated_spec(String affiliated_spec) {
        this.affiliated_spec = affiliated_spec;
    }

    public String getSpec_name() {
        return spec_name;
    }

    public void setSpec_name(String spec_name) {
        this.spec_name = spec_name;
    }

    public String getAffiliated_number() {
        return affiliated_number;
    }

    public void setAffiliated_number(String affiliated_number) {
        this.affiliated_number = affiliated_number;
    }

    public String getAffiliated_spec_name() {
        return affiliated_spec_name;
    }

    public void setAffiliated_spec_name(String affiliated_spec_name) {
        this.affiliated_spec_name = affiliated_spec_name;
    }

    public String getClassify_name() {
        return classify_name;
    }

    public void setClassify_name(String classify_name) {
        this.classify_name = classify_name;
    }

    public String getClassify_id() {
        return classify_id;
    }

    public void setClassify_id(String classify_id) {
        this.classify_id = classify_id;
    }

    public String getCommodity_name() {
        return commodity_name;
    }

    public void setCommodity_name(String commodity_name) {
        this.commodity_name = commodity_name;
    }

    public String getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(String commodity_id) {
        this.commodity_id = commodity_id;
    }
}
