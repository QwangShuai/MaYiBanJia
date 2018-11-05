package com.mingmen.mayi.mayibanjia.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/10/24.
 */

public class YinHangKaBean implements Serializable{
    private String bank_id;
    private String bank_account;
    private String bank_name;
    private String reverse_url;
    private String log_url;
    private String bank_branch;

    public String getBank_id() {
        return bank_id;
    }

    public void setBank_id(String bank_id) {
        this.bank_id = bank_id;
    }

    public String getBank_account() {
        return bank_account;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getReverse_url() {
        return reverse_url;
    }

    public void setReverse_url(String reverse_url) {
        this.reverse_url = reverse_url;
    }

    public String getLog_url() {
        return log_url;
    }

    public void setLog_url(String log_url) {
        this.log_url = log_url;
    }

    public String getBank_branch() {
        return bank_branch;
    }

    public void setBank_branch(String bank_branch) {
        this.bank_branch = bank_branch;
    }
}
