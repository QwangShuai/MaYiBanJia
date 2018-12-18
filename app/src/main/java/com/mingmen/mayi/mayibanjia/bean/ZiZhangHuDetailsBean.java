package com.mingmen.mayi.mayibanjia.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/12/14.
 */

public class ZiZhangHuDetailsBean {

    /**
     * account_state : 0
     * account_id : 9be41260b1bb4ca4878790f546f3f18f
     * company_id : 6b1057c23edc46c0a454d4dfea38131d
     * user_token : null
     * company_name : null
     * province : null
     * city : null
     * host_account_type : 1
     * telephone : 13000000000
     * random_id : 1
     * parent_id : 33e1e92ed949440ab85c2c4ef6c71462
     * creater : 9be41260b1bb4ca4878790f546f3f18f
     * business_license : null
     * circulation_permit : null
     * create_time : 2018-12-14 10:16:41
     * son_number : null
     * son_role_id : null
     * booth_number : null
     * head_shot : null
     * login_type : null
     * register_state : 0
     * new_password : null
     * yzm : null
     * new_telephone : null
     * salesman_code : null
     * change_time : null
     * changer : null
     * device_name : null
     * roleList : [{"account_id":null,"company_id":null,"user_token":null,"role_id":"2","creater":null,"create_time":null,"son_role_id":null,"changer":null,"isSelected":null,"rel_id":null,"changer_time":null,"roleName":"采购","part":null,"state":null}]
     * appRoleList : [{"account_id":null,"company_id":null,"user_token":null,"role_id":null,"creater":null,"create_time":null,"son_role_id":"1","changer":null,"isSelected":"0","rel_id":null,"changer_time":null,"roleName":null,"part":"厨师长","state":null},{"account_id":null,"company_id":null,"user_token":null,"role_id":null,"creater":null,"create_time":null,"son_role_id":"2","changer":null,"isSelected":"1","rel_id":null,"changer_time":null,"roleName":null,"part":"会计","state":null},{"account_id":null,"company_id":null,"user_token":null,"role_id":null,"creater":null,"create_time":null,"son_role_id":"3","changer":null,"isSelected":"1","rel_id":null,"changer_time":null,"roleName":null,"part":"餐厅经理","state":null},{"account_id":null,"company_id":null,"user_token":null,"role_id":null,"creater":null,"create_time":null,"son_role_id":"4","changer":null,"isSelected":"1","rel_id":null,"changer_time":null,"roleName":null,"part":"采购员","state":null}]
     * salesman_id : null
     * token : 1db083a8edfffa0a84f6c833db6222b9
     * role : null
     * principal : 张三
     * password : 25f9e794323b453885f5181f1b624d0b
     * type : null
     * region : null
     */

    private String account_id;
    private String company_id;
    private String company_name;
    private String telephone;
    private String principal;
    private String password;
    private List<RoleListBean> roleList;
    private List<AppRoleListBean> appRoleList;

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }


    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleListBean> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleListBean> roleList) {
        this.roleList = roleList;
    }

    public List<AppRoleListBean> getAppRoleList() {
        return appRoleList;
    }

    public void setAppRoleList(List<AppRoleListBean> appRoleList) {
        this.appRoleList = appRoleList;
    }

    public static class RoleListBean {
        /**
         * account_id : null
         * company_id : null
         * user_token : null
         * role_id : 2
         * creater : null
         * create_time : null
         * son_role_id : null
         * changer : null
         * isSelected : null
         * rel_id : null
         * changer_time : null
         * roleName : 采购
         * part : null
         * state : null
         */

        private String role_id;
        private Object changer_time;
        private String roleName;
        private Object state;

        public String getRole_id() {
            return role_id;
        }

        public void setRole_id(String role_id) {
            this.role_id = role_id;
        }

        public Object getChanger_time() {
            return changer_time;
        }

        public void setChanger_time(Object changer_time) {
            this.changer_time = changer_time;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }
    }

    public static class AppRoleListBean {
        /**
         * account_id : null
         * company_id : null
         * user_token : null
         * role_id : null
         * creater : null
         * create_time : null
         * son_role_id : 1
         * changer : null
         * isSelected : 0
         * rel_id : null
         * changer_time : null
         * roleName : null
         * part : 厨师长
         * state : null
         */

        private Object account_id;
        private String son_role_id;
        private String isSelected;
        private String part;

        public Object getAccount_id() {
            return account_id;
        }

        public void setAccount_id(Object account_id) {
            this.account_id = account_id;
        }

        public String getSon_role_id() {
            return son_role_id;
        }

        public void setSon_role_id(String son_role_id) {
            this.son_role_id = son_role_id;
        }

        public String getIsSelected() {
            return isSelected;
        }

        public void setIsSelected(String isSelected) {
            this.isSelected = isSelected;
        }

        public String getPart() {
            return part;
        }

        public void setPart(String part) {
            this.part = part;
        }
    }
}
