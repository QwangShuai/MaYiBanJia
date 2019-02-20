package com.mingmen.mayi.mayibanjia.bean;

/**
 * Created by Administrator on 2018/8/8.
 */

public class FbspGuiGeBean {

        /**
         * change_time : null
         * parent_id : null
         * create_time : 2018-07-19 09:23:56
         * spec_id : 7f6e635f04014b0ebf16759eb5b38318
         * spec_name : d
         * spec_grade : 801
         */

        private String change_time;
        private String parent_id;
        private String create_time;
        private String spec_id;
        private String spec_name;
        private String spec_grade;
        private String affiliated_spec;
        private String affiliated_number;

    public String getAffiliated_number() {
        return affiliated_number;
    }

    public void setAffiliated_number(String affiliated_number) {
        this.affiliated_number = affiliated_number;
    }

    public String getAffiliated_spec() {
        return affiliated_spec;
    }

    public void setAffiliated_spec(String affiliated_spec) {
        this.affiliated_spec = affiliated_spec;
    }

    public String getChange_time() {
            return change_time;
        }

        public void setChange_time(String change_time) {
            this.change_time = change_time;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getSpec_id() {
            return spec_id;
        }

        public void setSpec_id(String spec_id) {
            this.spec_id = spec_id;
        }

        public String getSpec_name() {
            return spec_name;
        }

        public void setSpec_name(String spec_name) {
            this.spec_name = spec_name;
        }

        public String getSpec_grade() {
            return spec_grade;
        }

        public void setSpec_grade(String spec_grade) {
            this.spec_grade = spec_grade;
        }
    @Override
    public String toString() {
        //重写该方法，作为选择器显示的名称
        return spec_name;
    }
}
