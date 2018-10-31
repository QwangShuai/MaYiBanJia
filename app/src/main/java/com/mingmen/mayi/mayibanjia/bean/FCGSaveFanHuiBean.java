package com.mingmen.mayi.mayibanjia.bean;

/**
 * Created by Administrator on 2018/7/23/023.
 */

public class FCGSaveFanHuiBean {

        /**
         * CtBuySonorder : {"count":22,"classify_name":null,"sort_id":"930a7f13c33749c18cc708a31c98234d","changer":null,"create_time":"2018-07-23 16:23:25","son_order_id":"6a93c2adc4aa4c1497cbf6e8b3d35f0b","pruchase_id":"82ea540d2b304697a93b61f31f7dbbd5","change_time":null}
         * CtBuyHostorder : {"audit_time":null,"pay_id":null,"market_id":"2","audit_ps":null,"user_token":"111","special_commodity":"0","delivery_address_id":null,"delivery_type":null,"order_audit_state":"1","order_release_sate":"1","assesser_id":null,"order_describe":null,"buyer_id":"360a206ff73540d6ac747ded1c6423a7","company_id":null,"pay_money":null,"if_special_need":"0","create_time":"2018-07-23 16:23:25","purchase_id":"82ea540d2b304697a93b61f31f7dbbd5"}
         */

        private CtBuySonorderBean CtBuySonorder;
        private CtBuyHostorderBean CtBuyHostorder;

        public CtBuySonorderBean getCtBuySonorder() {
            return CtBuySonorder;
        }

        public void setCtBuySonorder(CtBuySonorderBean CtBuySonorder) {
            this.CtBuySonorder = CtBuySonorder;
        }

        public CtBuyHostorderBean getCtBuyHostorder() {
            return CtBuyHostorder;
        }

        public void setCtBuyHostorder(CtBuyHostorderBean CtBuyHostorder) {
            this.CtBuyHostorder = CtBuyHostorder;
        }

        public static class CtBuySonorderBean {
            /**
             * count : 22
             * classify_name : null
             * sort_id : 930a7f13c33749c18cc708a31c98234d
             * changer : null
             * create_time : 2018-07-23 16:23:25
             * son_order_id : 6a93c2adc4aa4c1497cbf6e8b3d35f0b
             * pruchase_id : 82ea540d2b304697a93b61f31f7dbbd5
             * change_time : null
             */

            private int count;
            private Object classify_name;
            private String sort_id;
            private Object changer;
            private String create_time;
            private String son_order_id;
            private String purchase_id;
            private Object change_time;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public Object getClassify_name() {
                return classify_name;
            }

            public void setClassify_name(Object classify_name) {
                this.classify_name = classify_name;
            }

            public String getSort_id() {
                return sort_id;
            }

            public void setSort_id(String sort_id) {
                this.sort_id = sort_id;
            }

            public Object getChanger() {
                return changer;
            }

            public void setChanger(Object changer) {
                this.changer = changer;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getSon_order_id() {
                return son_order_id;
            }

            public void setSon_order_id(String son_order_id) {
                this.son_order_id = son_order_id;
            }

            public String getPurchase_id() {
                return purchase_id;
            }

            public void setPurchase_id(String pruchase_id) {
                this.purchase_id = pruchase_id;
            }

            public Object getChange_time() {
                return change_time;
            }

            public void setChange_time(Object change_time) {
                this.change_time = change_time;
            }
        }

        public static class CtBuyHostorderBean {
            /**
             * audit_time : null
             * pay_id : null
             * market_id : 2
             * audit_ps : null
             * user_token : 111
             * special_commodity : 0
             * delivery_address_id : null
             * delivery_type : null
             * order_audit_state : 1
             * order_release_sate : 1
             * assesser_id : null
             * order_describe : null
             * buyer_id : 360a206ff73540d6ac747ded1c6423a7
             * company_id : null
             * pay_money : null
             * if_special_need : 0
             * create_time : 2018-07-23 16:23:25
             * purchase_id : 82ea540d2b304697a93b61f31f7dbbd5
             */

            private Object audit_time;
            private Object pay_id;
            private String market_id;
            private Object audit_ps;
            private String user_token;
            private String special_commodity;
            private Object delivery_address_id;
            private Object delivery_type;
            private String order_audit_state;
            private String order_release_sate;
            private Object assesser_id;
            private Object order_describe;
            private String buyer_id;
            private Object company_id;
            private Object pay_money;
            private String if_special_need;
            private String create_time;
            private String purchase_id;

            public Object getAudit_time() {
                return audit_time;
            }

            public void setAudit_time(Object audit_time) {
                this.audit_time = audit_time;
            }

            public Object getPay_id() {
                return pay_id;
            }

            public void setPay_id(Object pay_id) {
                this.pay_id = pay_id;
            }

            public String getMarket_id() {
                return market_id;
            }

            public void setMarket_id(String market_id) {
                this.market_id = market_id;
            }

            public Object getAudit_ps() {
                return audit_ps;
            }

            public void setAudit_ps(Object audit_ps) {
                this.audit_ps = audit_ps;
            }

            public String getUser_token() {
                return user_token;
            }

            public void setUser_token(String user_token) {
                this.user_token = user_token;
            }

            public String getSpecial_commodity() {
                return special_commodity;
            }

            public void setSpecial_commodity(String special_commodity) {
                this.special_commodity = special_commodity;
            }

            public Object getDelivery_address_id() {
                return delivery_address_id;
            }

            public void setDelivery_address_id(Object delivery_address_id) {
                this.delivery_address_id = delivery_address_id;
            }

            public Object getDelivery_type() {
                return delivery_type;
            }

            public void setDelivery_type(Object delivery_type) {
                this.delivery_type = delivery_type;
            }

            public String getOrder_audit_state() {
                return order_audit_state;
            }

            public void setOrder_audit_state(String order_audit_state) {
                this.order_audit_state = order_audit_state;
            }

            public String getOrder_release_sate() {
                return order_release_sate;
            }

            public void setOrder_release_sate(String order_release_sate) {
                this.order_release_sate = order_release_sate;
            }

            public Object getAssesser_id() {
                return assesser_id;
            }

            public void setAssesser_id(Object assesser_id) {
                this.assesser_id = assesser_id;
            }

            public Object getOrder_describe() {
                return order_describe;
            }

            public void setOrder_describe(Object order_describe) {
                this.order_describe = order_describe;
            }

            public String getBuyer_id() {
                return buyer_id;
            }

            public void setBuyer_id(String buyer_id) {
                this.buyer_id = buyer_id;
            }

            public Object getCompany_id() {
                return company_id;
            }

            public void setCompany_id(Object company_id) {
                this.company_id = company_id;
            }

            public Object getPay_money() {
                return pay_money;
            }

            public void setPay_money(Object pay_money) {
                this.pay_money = pay_money;
            }

            public String getIf_special_need() {
                return if_special_need;
            }

            public void setIf_special_need(String if_special_need) {
                this.if_special_need = if_special_need;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getPurchase_id() {
                return purchase_id;
            }

            public void setPurchase_id(String purchase_id) {
                this.purchase_id = purchase_id;
            }
        }
}
