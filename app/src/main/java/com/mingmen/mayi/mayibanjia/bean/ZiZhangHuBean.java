package com.mingmen.mayi.mayibanjia.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/12/11.
 */

public class ZiZhangHuBean {
    private String principal;
    private String account_id;
    private List<AppRoleList> list;

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public List<AppRoleList> getList() {
        return list;
    }

    public void setList(List<AppRoleList> list) {
        this.list = list;
    }

    public static class AppRoleList {
        private String part;
        private String son_role_id;

        public String getPart() {
            return part;
        }

        public void setPart(String part) {
            this.part = part;
        }

        public String getSon_role_id() {
            return son_role_id;
        }

        public void setSon_role_id(String son_role_id) {
            this.son_role_id = son_role_id;
        }
    }

}
