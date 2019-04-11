package com.mingmen.mayi.mayibanjia.bean;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/12/7.
 */

public class AllMarket {
    private String market_id;
    private Double freight_fee;
    private Double total_weight;
    private Double gonglishu;

    public Double getGonglishu() {
        return gonglishu;
    }

    public void setGonglishu(Double gonglishu) {
        this.gonglishu = gonglishu;
    }

    public String getMarket_id() {
        return market_id;
    }

    public void setMarket_id(String market_id) {
        this.market_id = market_id;
    }

    public Double getFreight_fee() {
        return freight_fee;
    }

    public void setFreight_fee(Double freight_fee) {
        this.freight_fee = freight_fee;
    }

    public Double getTotal_weight() {
        return total_weight;
    }

    public void setTotal_weight(Double total_weight) {
        this.total_weight = total_weight;
    }
}
