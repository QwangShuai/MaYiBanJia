package com.mingmen.mayi.mayibanjia.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * Created by Administrator on 2018/11/30.
 */

public class JsonBean implements IPickerViewData {

    /**
     * create_time : null
     * quyfjbm : 86
     * quybm : 110000
     * dengj : 1
     * citylist : [{"create_time":null,"quyfjbm":110000,"quybm":110100,"dengj":2,"citylist":null,"qulist":[{"create_time":null,"quyfjbm":110100,"quybm":110101,"dengj":3,"citylist":null,"qulist":null,"quymc":"东城区"},{"create_time":null,"quyfjbm":110100,"quybm":110102,"dengj":3,"citylist":null,"qulist":null,"quymc":"西城区"},{"create_time":null,"quyfjbm":110100,"quybm":110105,"dengj":3,"citylist":null,"qulist":null,"quymc":"朝阳区"},{"create_time":null,"quyfjbm":110100,"quybm":110106,"dengj":3,"citylist":null,"qulist":null,"quymc":"丰台区"},{"create_time":null,"quyfjbm":110100,"quybm":110107,"dengj":3,"citylist":null,"qulist":null,"quymc":"石景山区"},{"create_time":null,"quyfjbm":110100,"quybm":110108,"dengj":3,"citylist":null,"qulist":null,"quymc":"海淀区"},{"create_time":null,"quyfjbm":110100,"quybm":110109,"dengj":3,"citylist":null,"qulist":null,"quymc":"门头沟区"},{"create_time":null,"quyfjbm":110100,"quybm":110111,"dengj":3,"citylist":null,"qulist":null,"quymc":"房山区"},{"create_time":null,"quyfjbm":110100,"quybm":110112,"dengj":3,"citylist":null,"qulist":null,"quymc":"通州区"},{"create_time":null,"quyfjbm":110100,"quybm":110113,"dengj":3,"citylist":null,"qulist":null,"quymc":"顺义区"},{"create_time":null,"quyfjbm":110100,"quybm":110114,"dengj":3,"citylist":null,"qulist":null,"quymc":"昌平区"},{"create_time":null,"quyfjbm":110100,"quybm":110115,"dengj":3,"citylist":null,"qulist":null,"quymc":"大兴区"},{"create_time":null,"quyfjbm":110100,"quybm":110116,"dengj":3,"citylist":null,"qulist":null,"quymc":"怀柔区"},{"create_time":null,"quyfjbm":110100,"quybm":110117,"dengj":3,"citylist":null,"qulist":null,"quymc":"平谷区"}],"quymc":"北京市辖区"},{"create_time":null,"quyfjbm":110000,"quybm":110200,"dengj":2,"citylist":null,"qulist":[{"create_time":null,"quyfjbm":110200,"quybm":110228,"dengj":3,"citylist":null,"qulist":null,"quymc":"密云县"},{"create_time":null,"quyfjbm":110200,"quybm":110229,"dengj":3,"citylist":null,"qulist":null,"quymc":"延庆县"}],"quymc":"县(北京)"}]
     * qulist : null
     * quymc : 北京市
     */

    private int quyfjbm;
    private int quybm;
    private int dengj;
    private String quymc;
    private List<CitylistBean> citylist;

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

    public int getDengj() {
        return dengj;
    }

    public void setDengj(int dengj) {
        this.dengj = dengj;
    }

    public String getQuymc() {
        return quymc;
    }

    public void setQuymc(String quymc) {
        this.quymc = quymc;
    }

    public List<CitylistBean> getCitylist() {
        return citylist;
    }

    public void setCitylist(List<CitylistBean> citylist) {
        this.citylist = citylist;
    }

    @Override
    public String getPickerViewText() {
        return quymc;
    }

    public static class CitylistBean implements IPickerViewData {
        /**
         * create_time : null
         * quyfjbm : 110000
         * quybm : 110100
         * dengj : 2
         * citylist : null
         * qulist : [{"create_time":null,"quyfjbm":110100,"quybm":110101,"dengj":3,"citylist":null,"qulist":null,"quymc":"东城区"},{"create_time":null,"quyfjbm":110100,"quybm":110102,"dengj":3,"citylist":null,"qulist":null,"quymc":"西城区"},{"create_time":null,"quyfjbm":110100,"quybm":110105,"dengj":3,"citylist":null,"qulist":null,"quymc":"朝阳区"},{"create_time":null,"quyfjbm":110100,"quybm":110106,"dengj":3,"citylist":null,"qulist":null,"quymc":"丰台区"},{"create_time":null,"quyfjbm":110100,"quybm":110107,"dengj":3,"citylist":null,"qulist":null,"quymc":"石景山区"},{"create_time":null,"quyfjbm":110100,"quybm":110108,"dengj":3,"citylist":null,"qulist":null,"quymc":"海淀区"},{"create_time":null,"quyfjbm":110100,"quybm":110109,"dengj":3,"citylist":null,"qulist":null,"quymc":"门头沟区"},{"create_time":null,"quyfjbm":110100,"quybm":110111,"dengj":3,"citylist":null,"qulist":null,"quymc":"房山区"},{"create_time":null,"quyfjbm":110100,"quybm":110112,"dengj":3,"citylist":null,"qulist":null,"quymc":"通州区"},{"create_time":null,"quyfjbm":110100,"quybm":110113,"dengj":3,"citylist":null,"qulist":null,"quymc":"顺义区"},{"create_time":null,"quyfjbm":110100,"quybm":110114,"dengj":3,"citylist":null,"qulist":null,"quymc":"昌平区"},{"create_time":null,"quyfjbm":110100,"quybm":110115,"dengj":3,"citylist":null,"qulist":null,"quymc":"大兴区"},{"create_time":null,"quyfjbm":110100,"quybm":110116,"dengj":3,"citylist":null,"qulist":null,"quymc":"怀柔区"},{"create_time":null,"quyfjbm":110100,"quybm":110117,"dengj":3,"citylist":null,"qulist":null,"quymc":"平谷区"}]
         * quymc : 北京市辖区
         */

        private int quyfjbm;
        private int quybm;
        private int dengj;
        private String quymc;
        private List<QulistBean> qulist;

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

        public int getDengj() {
            return dengj;
        }

        public void setDengj(int dengj) {
            this.dengj = dengj;
        }

        public String getQuymc() {
            return quymc;
        }

        public void setQuymc(String quymc) {
            this.quymc = quymc;
        }

        public List<QulistBean> getQulist() {
            return qulist;
        }

        public void setQulist(List<QulistBean> qulist) {
            this.qulist = qulist;
        }

        @Override
        public String getPickerViewText() {
            return quymc;
        }

        public static class QulistBean  implements IPickerViewData  {
            /**
             * create_time : null
             * quyfjbm : 110100
             * quybm : 110101
             * dengj : 3
             * citylist : null
             * qulist : null
             * quymc : 东城区
             */

            private int quyfjbm;
            private int quybm;
            private int dengj;
            private String quymc;

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

            public int getDengj() {
                return dengj;
            }

            public void setDengj(int dengj) {
                this.dengj = dengj;
            }

            public String getQuymc() {
                return quymc;
            }

            public void setQuymc(String quymc) {
                this.quymc = quymc;
            }

            @Override
            public String getPickerViewText() {
                return quymc;
            }
        }
    }
}
