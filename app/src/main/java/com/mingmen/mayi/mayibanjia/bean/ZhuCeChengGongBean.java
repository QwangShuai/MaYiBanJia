package com.mingmen.mayi.mayibanjia.bean;

/**
 * Created by Administrator on 2018/7/16/016.
 */

public class ZhuCeChengGongBean {
    /**
     * 账户表
     */

    private String account_id;//   账户id
    private String company_id;//   企业id
    private String principal;//   负责人名称
    private String telephone;//   手机号
    private String password;//   密码
    private String host_account_type;//   账户类型(主账户)
    private String role;//   1餐厅端,2供应端,3物流,4业务员
    private String salesman_code;//   业务员邀请码
    private String salesman_id;//   业务员id
    private String register_state;//   登录状态
    private String account_state;//   账户状态
    private String parent_id;//   父id
    private String token;//   token
    private String changer;//   修改人
    private String creater;//   创建人
    private String change_time;//   更改时间
    private String create_time;//   创建时间
    private Integer random_id;//   生成token和邀请码需要
    private String head_shot;//   头像
    private String company_name;//   店铺名称
    private String business_license;//   营业执照
    private String circulation_permit; //食品流通许可证
    private String booth_number;//   摊位号
    private String son_number; // 市场
    private String login_type;//登陆方式1账号密码登陆2手机号登陆
    private String type;//密码操作1修改2手机号找回
    private String new_password;//新密码
    private String device_name;//1 安卓2苹果
    private String province;//   省（餐厅所在地）
    private String city;//   市
    private String region;//   区
    private String name;//   姓名

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead_shot() {
        return head_shot;
    }


    public void setHead_shot(String head_shot) {
        this.head_shot = head_shot;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return region;
    }

    public void setDistrict(String district) {
        this.region = district;
    }

    public String getBooth_number() {
        return booth_number;
    }

    public void setBooth_number(String booth_number) {
        this.booth_number = booth_number;
    }

    public String getSon_number() {
        return son_number;
    }

    public void setSon_number(String son_number) {
        this.son_number = son_number;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLogin_type() {
        return login_type;
    }

    public void setLogin_type(String login_type) {
        this.login_type = login_type;
    }

    public String getCirculation_permit() {
        return circulation_permit;
    }

    public void setCirculation_permit(String circulation_permit) {
        this.circulation_permit = circulation_permit;
    }

    public String getBusiness_license() {
        return business_license;
    }

    public void setBusiness_license(String business_license) {
        this.business_license = business_license;
    }


    public String getAccount_id() {
        return this.account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getCompany_id() {
        return this.company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getPrincipal() {
        return this.principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost_account_type() {
        return this.host_account_type;
    }

    public void setHost_account_type(String host_account_type) {
        this.host_account_type = host_account_type;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSalesman_code() {
        return this.salesman_code;
    }

    public void setSalesman_code(String salesman_code) {
        this.salesman_code = salesman_code;
    }

    public String getSalesman_id() {
        return this.salesman_id;
    }

    public void setSalesman_id(String salesman_id) {
        this.salesman_id = salesman_id;
    }

    public String getRegister_state() {
        return this.register_state;
    }

    public void setRegister_state(String register_state) {
        this.register_state = register_state;
    }

    public String getAccount_state() {
        return this.account_state;
    }

    public void setAccount_state(String account_state) {
        this.account_state = account_state;
    }

    public String getParent_id() {
        return this.parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getChanger() {
        return this.changer;
    }

    public void setChanger(String changer) {
        this.changer = changer;
    }

    public String getCreater() {
        return this.creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getChange_time() {
        return this.change_time;
    }

    public void setChange_time(String change_time) {
        this.change_time = change_time;
    }

    public String getCreate_time() {
        return this.create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public Integer getRandom_id() {
        return this.random_id;
    }

    public void setRandom_id(Integer random_id) {
        this.random_id = random_id;
    }


}
