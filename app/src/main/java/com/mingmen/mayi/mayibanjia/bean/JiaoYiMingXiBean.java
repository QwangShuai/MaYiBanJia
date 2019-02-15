package com.mingmen.mayi.mayibanjia.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/2/14.
 */

public class JiaoYiMingXiBean {

    private List<LieBiaoBean> liebiao;
    private String shouru;
    private String zhichu;

    public List<LieBiaoBean> getLiebiao() {
        return liebiao;
    }

    public void setLiebiao(List<LieBiaoBean> liebiao) {
        this.liebiao = liebiao;
    }

    public String getShouru() {
        return shouru;
    }

    public void setShouru(String shouru) {
        this.shouru = shouru;
    }

    public String getZhichu() {
        return zhichu;
    }

    public void setZhichu(String zhichu) {
        this.zhichu = zhichu;
    }

    public static class LieBiaoBean {
        private String state;
        private String balance;
        private String pay_money;
        private String collect_money;
        private String create_time;
        private String history_id;

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getPay_money() {
            return pay_money;
        }

        public void setPay_money(String pay_money) {
            this.pay_money = pay_money;
        }

        public String getCollect_money() {
            return collect_money;
        }

        public void setCollect_money(String collect_money) {
            this.collect_money = collect_money;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getHistory_id() {
            return history_id;
        }

        public void setHistory_id(String history_id) {
            this.history_id = history_id;
        }
    }
}
