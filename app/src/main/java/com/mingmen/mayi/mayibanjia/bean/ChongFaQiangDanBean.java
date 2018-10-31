package com.mingmen.mayi.mayibanjia.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/29.
 */

public class ChongFaQiangDanBean {

        /**
         * getToken : ["111","1022d48effc691886b4a98ff90e66fd5","4abc7b91710d3009306f7fc6f13c802c","4abc7b91710d3009306f7fc6f13c802c","4abc7b91710d3009306f7fc6f13c802c","4abc7b91710d3009306f7fc6f13c802c","123"]
         * type_tree_id : 930a7f13c33749c18cc708a31c98234d
         * son_order_id : ccbe11cef65741d4a3103b2106690660
         * special_commodity : 我要一个小狮子他的名字叫春桃，春桃的春，春桃的桃。如果有比春桃还好吃的也行，
         */

        private String type_tree_id;
        private String son_order_id;
        private String special_commodity;
        private List<String> getToken;

        public String getType_tree_id() {
            return type_tree_id;
        }

        public void setType_tree_id(String type_tree_id) {
            this.type_tree_id = type_tree_id;
        }

        public String getSon_order_id() {
            return son_order_id;
        }

        public void setSon_order_id(String son_order_id) {
            this.son_order_id = son_order_id;
        }

        public String getSpecial_commodity() {
            return special_commodity;
        }

        public void setSpecial_commodity(String special_commodity) {
            this.special_commodity = special_commodity;
        }

        public List<String> getGetToken() {
            return getToken;
        }

        public void setGetToken(List<String> getToken) {
            this.getToken = getToken;
        }

}
