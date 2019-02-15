package com.mingmen.mayi.mayibanjia.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/2/13.
 */

public class ZzhQuanXianBean implements Serializable{
    private String roleName;
    private String role_id;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }
}
