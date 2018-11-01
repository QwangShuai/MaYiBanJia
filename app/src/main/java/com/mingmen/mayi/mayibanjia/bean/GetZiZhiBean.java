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
}
