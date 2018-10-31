package com.mingmen.mayi.mayibanjia.bean;

/**
 * Created by Administrator on 2018/7/25/025.
 */

public class QiYeLeiBieBean {

        /**
         * state : null
         * parent_number : null
         * create_time : null
         * son_number : 201
         * dictionary_id : null
         * parent_name : null
         * change_time : null
         * remarks : null
         * son_name : 火锅店
         */

        private Object state;
        private Object parent_number;
        private Object create_time;
        private String son_number;
        private Object dictionary_id;
        private Object parent_name;
        private Object change_time;
        private Object remarks;
        private String son_name;

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }

        public Object getParent_number() {
            return parent_number;
        }

        public void setParent_number(Object parent_number) {
            this.parent_number = parent_number;
        }

        public Object getCreate_time() {
            return create_time;
        }

        public void setCreate_time(Object create_time) {
            this.create_time = create_time;
        }

        public String getSon_number() {
            return son_number;
        }

        public void setSon_number(String son_number) {
            this.son_number = son_number;
        }

        public Object getDictionary_id() {
            return dictionary_id;
        }

        public void setDictionary_id(Object dictionary_id) {
            this.dictionary_id = dictionary_id;
        }

        public Object getParent_name() {
            return parent_name;
        }

        public void setParent_name(Object parent_name) {
            this.parent_name = parent_name;
        }

        public Object getChange_time() {
            return change_time;
        }

        public void setChange_time(Object change_time) {
            this.change_time = change_time;
        }

        public Object getRemarks() {
            return remarks;
        }

        public void setRemarks(Object remarks) {
            this.remarks = remarks;
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
