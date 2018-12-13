package com.mingmen.mayi.mayibanjia.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/12/11.
 */

public class JueSeBean {
    private String son_role_id;

    private String part;
    private String isSelected;

    public String getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(String isSelected) {
        this.isSelected = isSelected;
    }

    private List<QxList> qxList;

    public List<QxList> getQxList() {
        return qxList;
    }

    public void setQxList(List<QxList> qxList) {
        this.qxList = qxList;
    }

    public String getSon_role_id() {
        return son_role_id;
    }

    public void setSon_role_id(String son_role_id) {
        this.son_role_id = son_role_id;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public static class QxList{
        private String role_id;

        private String roleName;

        public String getRole_id() {
            return role_id;
        }

        public void setRole_id(String role_id) {
            this.role_id = role_id;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }
    }
}
