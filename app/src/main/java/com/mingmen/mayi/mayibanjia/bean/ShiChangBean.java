package com.mingmen.mayi.mayibanjia.bean;

/**
 * Created by Administrator on 2018/7/16/016.
 */

public class ShiChangBean {

        /**
         * state : null
         * mark_id : 1
         * provinces : null
         * cities : null
         * counties : null
         * create_by : null
         * update_by : null
         * parent_number : null
         * detailed_address : null
         * classify_id : null
         * market_name : 哈达市场
         * update_time : null
         * create_time : null
         */

        private String state;
        private String mark_id;
        private String provinces;
        private String cities;
        private String counties;
        private String create_by;
        private String update_by;
        private String parent_number;
        private String detailed_address;
        private String classify_id;
        private String market_name;
        private String update_time;
        private String create_time;

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getMark_id() {
            return mark_id;
        }

        public void setMark_id(String mark_id) {
            this.mark_id = mark_id;
        }

        public String getProvinces() {
            return provinces;
        }

        public void setProvinces(String provinces) {
            this.provinces = provinces;
        }

        public String getCities() {
            return cities;
        }

        public void setCities(String cities) {
            this.cities = cities;
        }

        public String getCounties() {
            return counties;
        }

        public void setCounties(String counties) {
            this.counties = counties;
        }

        public String getCreate_by() {
            return create_by;
        }

        public void setCreate_by(String create_by) {
            this.create_by = create_by;
        }

        public String getUpdate_by() {
            return update_by;
        }

        public void setUpdate_by(String update_by) {
            this.update_by = update_by;
        }

        public String getParent_number() {
            return parent_number;
        }

        public void setParent_number(String parent_number) {
            this.parent_number = parent_number;
        }

        public String getDetailed_address() {
            return detailed_address;
        }

        public void setDetailed_address(String detailed_address) {
            this.detailed_address = detailed_address;
        }

        public String getClassify_id() {
            return classify_id;
        }

        public void setClassify_id(String classify_id) {
            this.classify_id = classify_id;
        }

        public String getMarket_name() {
            return market_name;
        }

        public void setMarket_name(String market_name) {
            this.market_name = market_name;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    @Override
    public String toString() {
        //重写该方法，作为选择器显示的名称
        return market_name;
    }
}
