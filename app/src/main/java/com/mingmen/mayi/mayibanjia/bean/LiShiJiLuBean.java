package com.mingmen.mayi.mayibanjia.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/16.
 */

public class LiShiJiLuBean {


        private List<XiTongTuiJianBean.CcListBean> csList;
        private List<XiTongTuiJianBean.CcListBean> oreder_buy;

        public List<XiTongTuiJianBean.CcListBean> getCsList() {
            return csList;
        }

        public void setCsList(List<XiTongTuiJianBean.CcListBean> csList) {
            this.csList = csList;
        }

        public List<XiTongTuiJianBean.CcListBean> getOreder_buy() {
            return oreder_buy;
        }

        public void setOreder_buy(List<XiTongTuiJianBean.CcListBean> oreder_buy) {
            this.oreder_buy = oreder_buy;
        }



}
