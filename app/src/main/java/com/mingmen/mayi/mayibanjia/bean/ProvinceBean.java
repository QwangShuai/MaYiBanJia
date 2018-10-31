package com.mingmen.mayi.mayibanjia.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2018/7/13/013.
 */

public class ProvinceBean {

        /**
         * quymc : 北京市
         * quyfjbm : null
         * quybm : 110000
         * dengj : null
         * create_time : null
         */

        private String quymc;//名
        private int quyfjbm;
        private int quybm;//编码
        private String dengj;
        private String create_time;



    public String getQuymc() {
            return quymc;
        }

        public void setQuymc(String quymc) {
            this.quymc = quymc;
        }

        public int getQuyfjbm() {
            return quyfjbm;
        }

        public void setQuyfjbm(int quyfjbm) {
            this.quyfjbm = quyfjbm;
        }

        public int getQuybm() {
            return quybm;
        }

        public void setQuybm(int quybm) {
            this.quybm = quybm;
        }

        public String getDengj() {
            return dengj;
        }

        public void setDengj(String dengj) {
            this.dengj = dengj;
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
        return quymc;
    }

}
