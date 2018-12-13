package com.mingmen.mayi.mayibanjia.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/12/11.
 */

public class RoleBean {
    private List<QxList> qxList;
    private List<QxList> mayList;

    public List<QxList> getQxList() {
        return qxList;
    }

    public void setQxList(List<QxList> qxList) {
        this.qxList = qxList;
    }

    public List<QxList> getMayList() {
        return mayList;
    }

    public void setMayList(List<QxList> mayList) {
        this.mayList = mayList;
    }

    public static class QxList{
        private String role_id;

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        private String roleName;

        public String getRole_id() {
            return role_id;
        }

        public void setRole_id(String role_id) {
            this.role_id = role_id;
        }
    }
}
