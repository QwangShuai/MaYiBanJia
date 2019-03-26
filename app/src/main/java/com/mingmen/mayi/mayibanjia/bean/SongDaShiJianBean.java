package com.mingmen.mayi.mayibanjia.bean;

/**
 * Created by Administrator on 2018/8/9.
 */

public class SongDaShiJianBean {

        /**
         * son_number : 1201
         * son_name : 早7点-9点
         */

        private String son_number;
        private String son_name = "";


        public String getSon_number() {
            return son_number;
        }

        public void setSon_number(String son_number) {
            this.son_number = son_number;
        }

        public String getSon_name() {
            return son_name;
        }

        public void setSon_name(String son_name) {
            this.son_name = son_name;
        }

    @Override
    public String toString() {
        //重写该方法，作为选择器显示的名称
        return son_name;
    }
}
