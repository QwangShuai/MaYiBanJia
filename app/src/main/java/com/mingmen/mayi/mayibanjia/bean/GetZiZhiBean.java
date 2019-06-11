package com.mingmen.mayi.mayibanjia.bean;

/**
 * Created by Administrator on 2018/11/1.
 */

public class GetZiZhiBean {
    private String business_license;//营业执照
    private String circulation_permit;//食品流通许可证（供货端）
    private String id_number;//身份证号
    private String legal_person;//法人姓名
    private String principal;//负责人姓名
    private String duty_paragraph;//税号
    private String specific_address;//详细地址（餐）
    private String province;//省市区拼好的（餐）
    private String street;//商圈（餐）
    private String telephone;//电话
    private String photo;//实地图片
    private String market_name;//市场名称（供）
    private String parent_number;//企业类别（餐）
    private String scale;//规模（餐）
    private String approval_state;//审核资质状态(0:成功1:失败2待审核3审核中)
    private String company_name;//企业名称
    private String booth_number;//摊位号

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

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getLegal_person() {
        return legal_person;
    }

    public void setLegal_person(String legal_person) {
        this.legal_person = legal_person;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getDuty_paragraph() {
        return duty_paragraph;
    }

    public void setDuty_paragraph(String duty_paragraph) {
        this.duty_paragraph = duty_paragraph;
    }

    public String getSpecific_address() {
        return specific_address;
    }

    public void setSpecific_address(String specific_address) {
        this.specific_address = specific_address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMarket_name() {
        return market_name;
    }

    public void setMarket_name(String market_name) {
        this.market_name = market_name;
    }

    public String getParent_number() {
        return parent_number;
    }

    public void setParent_number(String parent_number) {
        this.parent_number = parent_number;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getApproval_state() {
        return approval_state;
    }

    public void setApproval_state(String approval_state) {
        this.approval_state = approval_state;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getBooth_number() {
        return booth_number;
    }

    public void setBooth_number(String booth_number) {
        this.booth_number = booth_number;
    }
}
