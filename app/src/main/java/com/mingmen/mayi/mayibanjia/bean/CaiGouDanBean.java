package com.mingmen.mayi.mayibanjia.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


/**
 * Created by Administrator on 2018/8/1/001.
 */

public class CaiGouDanBean {
    public static final int TYPE_ONE = 0x1;//价格
    public static final int TYPE_TWO = 0x2;//销量
    public static final int TYPE_THREE = 0x3;//评分
    public static final int TYPE_FOUR = 0x4;//历史购买
    public static final int TYPE_FIVE = 0x5;//历史收藏


    /**
     * type : null
     * delivery_address_id : null
     * order_release_sate : null
     * order_describe : null
     * buyer_id : null
     * user_token : null
     * company_id : null
     * order_id : null
     * purchase_id : 66227fbc6b93419697e918add7a88a2a
     * pay_money : null
     * picture_url : 491ceae30ad743cd93baff358a507113index.jpg
     * create_time : 2018-11-29 14:23:42
     * levelState : 1
     * market_id : 1
     * sort_id : null
     * son_order_id : null
     * audit_ps : null
     * purchase_name : 测试测试
     * fllist : [{"paramete_name_id":null,"spec_idTwo":null,"picture":null,"spec_idThree":null,"picture_url":null,"parent_id":null,"classify_name":"鲜肉","create_time":null,"classify_id":"343ad67bee889f873b45df78989cc870","classify_num":null,"spec_idFour":null,"spec_idOne":null,"sonorderlist":[{"type":null,"count":10,"if_special_need":null,"pack_standard_name":"斤","user_token":null,"company_id":null,"commodity_id":null,"purchase_id":"66227fbc6b93419697e918add7a88a2a","shopping_id":null,"picture_url":"http://qiniu.canchengxiang.com/541486b6bc68432ab8730028a52b4ff313.jpg?e=1543479699&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:a6njK-sL97sE5ZRGpImY5DPZE2w=","classify_name":"牛肉","append_money":null,"create_time":"2018-11-29 14:23:42","classify_id":"343ad67bee889f873b45df78989cc870","market_id":"1","sort_id":"f39ee1526d194adabb800c77c400028b","son_order_id":"1d720ac2bba846ee9a2bbb2a64522697","state_type":null,"purchase_name":null,"changer":null,"change_time":null,"movement_type":null,"pack_standard_id":"93a5ebaca0534f1b9aa4ad612f7dcce9","movement_number":null,"order_audit_state":null,"special_commodity":"","quote_price_id":null}],"approval_state":null,"classify_grade":null,"order":null,"page":1,"pager":{"length":6,"endIndex":0,"orderCondition":"","mysqlQueryCondition":" limit 0,10","indexs":[],"rowCount":0,"pageCount":0,"pageTail":0,"startIndex":1,"orderField":"","pageOffset":0,"pageId":1,"pageSize":10,"orderDirection":true},"rows":10,"sort":null},{"paramete_name_id":null,"spec_idTwo":null,"picture":null,"spec_idThree":null,"picture_url":null,"parent_id":null,"classify_name":"蔬菜","create_time":null,"classify_id":"cae0e9688b36471a8efffd9bd544ff9f","classify_num":null,"spec_idFour":null,"spec_idOne":null,"sonorderlist":[{"type":null,"count":30,"if_special_need":null,"pack_standard_name":"斤","user_token":null,"company_id":null,"commodity_id":null,"purchase_id":"66227fbc6b93419697e918add7a88a2a","shopping_id":null,"picture_url":"http://qiniu.canchengxiang.com/491ceae30ad743cd93baff358a507113index.jpg?e=1543479699&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:9q49MRKbS0z6QN2FT7nmLyO_qJ8=","classify_name":"小柿子","append_money":null,"create_time":"2018-11-29 14:23:27","classify_id":"cae0e9688b36471a8efffd9bd544ff9f","market_id":"1","sort_id":"12345678912345634234242546aaabbb","son_order_id":"d5fee85af97844e9ab0a57f6a48076dc","state_type":null,"purchase_name":null,"changer":null,"change_time":null,"movement_type":null,"pack_standard_id":"93a5ebaca0534f1b9aa4ad612f7dcce9","movement_number":null,"order_audit_state":null,"special_commodity":"","quote_price_id":null},{"type":null,"count":10,"if_special_need":null,"pack_standard_name":"斤","user_token":null,"company_id":null,"commodity_id":null,"purchase_id":"66227fbc6b93419697e918add7a88a2a","shopping_id":null,"picture_url":"http://qiniu.canchengxiang.com/491ceae30ad743cd93baff358a507113index.jpg?e=1543479699&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:9q49MRKbS0z6QN2FT7nmLyO_qJ8=","classify_name":"小柿子","append_money":null,"create_time":"2018-11-29 14:23:18","classify_id":"cae0e9688b36471a8efffd9bd544ff9f","market_id":"1","sort_id":"12345678912345634234242546aaabbb","son_order_id":"807f5adc0e8d46cf874a752b9fde0e2b","state_type":null,"purchase_name":null,"changer":null,"change_time":null,"movement_type":null,"pack_standard_id":"93a5ebaca0534f1b9aa4ad612f7dcce9","movement_number":null,"order_audit_state":null,"special_commodity":"666","quote_price_id":null},{"type":null,"count":10,"if_special_need":null,"pack_standard_name":"斤","user_token":null,"company_id":null,"commodity_id":null,"purchase_id":"66227fbc6b93419697e918add7a88a2a","shopping_id":null,"picture_url":"http://qiniu.canchengxiang.com/491ceae30ad743cd93baff358a507113index.jpg?e=1543479699&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:9q49MRKbS0z6QN2FT7nmLyO_qJ8=","classify_name":"小柿子","append_money":null,"create_time":"2018-11-29 14:23:06","classify_id":"cae0e9688b36471a8efffd9bd544ff9f","market_id":"1","sort_id":"12345678912345634234242546aaabbb","son_order_id":"a6e99fdb333e4eab9b710466147a94e5","state_type":null,"purchase_name":null,"changer":null,"change_time":null,"movement_type":null,"pack_standard_id":"93a5ebaca0534f1b9aa4ad612f7dcce9","movement_number":null,"order_audit_state":null,"special_commodity":"","quote_price_id":null}],"approval_state":null,"classify_grade":null,"order":null,"page":1,"pager":{"length":6,"endIndex":0,"orderCondition":"","mysqlQueryCondition":" limit 0,10","indexs":[],"rowCount":0,"pageCount":0,"pageTail":0,"startIndex":1,"orderField":"","pageOffset":0,"pageId":1,"pageSize":10,"orderDirection":true},"rows":10,"sort":null}]
     * qdTime :
     * delivery_type : null
     * audit_time : null
     * assesser_id : null
     * pay_id : null
     * pack_standard_id : null
     * order_audit_state : 902
     */

    private String purchase_id;
    private String picture_url;
    private String create_time;
    private String levelState;
    private String market_id;
    private String purchase_name;
    private String qdTime;
    private BigDecimal zongjia;
    private String order_audit_state;
    private String audit_ps;
    private boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getAudit_ps() {
        return audit_ps;
    }

    public void setAudit_ps(String audit_ps) {
        this.audit_ps = audit_ps;
    }

    public String getOrder_audit_state() {
        return order_audit_state;
    }

    public void setOrder_audit_state(String order_audit_state) {
        this.order_audit_state = order_audit_state;
    }

    public BigDecimal getZongjia() {
        return zongjia;
    }

    public void setZongjia(BigDecimal zongjia) {
        this.zongjia = zongjia;
    }

    private List<FllistBean> fllist;

    public String getPurchase_id() {
        return purchase_id;
    }

    public void setPurchase_id(String purchase_id) {
        this.purchase_id = purchase_id;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getLevelState() {
        return levelState;
    }

    public void setLevelState(String levelState) {
        this.levelState = levelState;
    }

    public String getMarket_id() {
        return market_id;
    }

    public void setMarket_id(String market_id) {
        this.market_id = market_id;
    }

    public String getPurchase_name() {
        return purchase_name;
    }

    public void setPurchase_name(String purchase_name) {
        this.purchase_name = purchase_name;
    }

    public String getQdTime() {
        return qdTime;
    }

    public void setQdTime(String qdTime) {
        this.qdTime = qdTime;
    }

    public List<FllistBean> getFllist() {
        return fllist;
    }

    public void setFllist(List<FllistBean> fllist) {
        this.fllist = fllist;
    }

    public static class FllistBean {
        /**
         * paramete_name_id : null
         * spec_idTwo : null
         * picture : null
         * spec_idThree : null
         * picture_url : null
         * parent_id : null
         * classify_name : 鲜肉
         * create_time : null
         * classify_id : 343ad67bee889f873b45df78989cc870
         * classify_num : null
         * spec_idFour : null
         * spec_idOne : null
         * sonorderlist : [{"type":null,"count":10,"if_special_need":null,"pack_standard_name":"斤","user_token":null,"company_id":null,"commodity_id":null,"purchase_id":"66227fbc6b93419697e918add7a88a2a","shopping_id":null,"picture_url":"http://qiniu.canchengxiang.com/541486b6bc68432ab8730028a52b4ff313.jpg?e=1543479699&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:a6njK-sL97sE5ZRGpImY5DPZE2w=","classify_name":"牛肉","append_money":null,"create_time":"2018-11-29 14:23:42","classify_id":"343ad67bee889f873b45df78989cc870","market_id":"1","sort_id":"f39ee1526d194adabb800c77c400028b","son_order_id":"1d720ac2bba846ee9a2bbb2a64522697","state_type":null,"purchase_name":null,"changer":null,"change_time":null,"movement_type":null,"pack_standard_id":"93a5ebaca0534f1b9aa4ad612f7dcce9","movement_number":null,"order_audit_state":null,"special_commodity":"","quote_price_id":null}]
         * approval_state : null
         * classify_grade : null
         * order : null
         * page : 1
         * pager : {"length":6,"endIndex":0,"orderCondition":"","mysqlQueryCondition":" limit 0,10","indexs":[],"rowCount":0,"pageCount":0,"pageTail":0,"startIndex":1,"orderField":"","pageOffset":0,"pageId":1,"pageSize":10,"orderDirection":true}
         * rows : 10
         * sort : null
         */

        private String classify_name;
        private String classify_id;
        private String picture_url;
        private String order_audit_state;
        private String market_id;
        private String market_name;
        private boolean isShow;
        private List<SonorderlistBean> sonorderlist;

        public String getMarket_name() {
            return market_name;
        }

        public void setMarket_name(String market_name) {
            this.market_name = market_name;
        }

        public boolean isShow() {
            return isShow;
        }

        public void setShow(boolean show) {
            isShow = show;
        }

        public String getMarket_id() {
            return market_id;
        }

        public void setMarket_id(String market_id) {
            this.market_id = market_id;
        }

        public String getOrder_audit_state() {
            return order_audit_state;
        }

        public void setOrder_audit_state(String order_audit_state) {
            this.order_audit_state = order_audit_state;
        }
        public String getPicture_url() {
            return picture_url;
        }

        public void setPicture_url(String picture_url) {
            this.picture_url = picture_url;
        }

        public String getClassify_name() {
            return classify_name;
        }

        public void setClassify_name(String classify_name) {
            this.classify_name = classify_name;
        }

        public String getClassify_id() {
            return classify_id;
        }

        public void setClassify_id(String classify_id) {
            this.classify_id = classify_id;
        }

        public List<SonorderlistBean> getSonorderlist() {
            return sonorderlist;
        }

        public void setSonorderlist(List<SonorderlistBean> sonorderlist) {
            this.sonorderlist = sonorderlist;
        }


        public static class SonorderlistBean {
            /**
             * type : null
             * count : 10
             * if_special_need : null
             * pack_standard_name : 斤
             * user_token : null
             * company_id : null
             * commodity_id : null
             * purchase_id : 66227fbc6b93419697e918add7a88a2a
             * shopping_id : null
             * picture_url : http://qiniu.canchengxiang.com/541486b6bc68432ab8730028a52b4ff313.jpg?e=1543479699&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:a6njK-sL97sE5ZRGpImY5DPZE2w=
             * classify_name : 牛肉
             * append_money : null
             * create_time : 2018-11-29 14:23:42
             * classify_id : 343ad67bee889f873b45df78989cc870
             * market_id : 1
             * sort_id : f39ee1526d194adabb800c77c400028b
             * son_order_id : 1d720ac2bba846ee9a2bbb2a64522697
             * state_type : null
             * purchase_name : null
             * changer : null
             * change_time : null
             * movement_type : null
             * pack_standard_id : 93a5ebaca0534f1b9aa4ad612f7dcce9
             * movement_number : null
             * order_audit_state : null
             * special_commodity :
             * quote_price_id : null
             */

            private int count;
            private String pack_standard_name;
            private String purchase_id;
            private String picture_url;
            private String classify_name;
            private String create_time;
            private String classify_id;
            private String market_id;
            private String sort_id;
            private String son_order_id;
            private String pack_standard_id;
            private String special_commodity;
            private String one_classify_name;
            private List<CcListBeanLevel> levels;
            private boolean needLoad = true;
            private boolean isSelect;

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            public String getOne_classify_name() {
                return one_classify_name;
            }

            public void setOne_classify_name(String one_classify_name) {
                this.one_classify_name = one_classify_name;
            }

            public List<CcListBeanLevel> getLevels() {
                return levels;
            }

            public void setLevels(List<CcListBeanLevel> levels) {
                this.levels = levels;
            }

            public boolean isNeedLoad() {
                return needLoad;
            }

            public void setNeedLoad(boolean needLoad) {
                this.needLoad = needLoad;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getPack_standard_name() {
                return pack_standard_name;
            }

            public void setPack_standard_name(String pack_standard_name) {
                this.pack_standard_name = pack_standard_name;
            }

            public String getPurchase_id() {
                return purchase_id;
            }

            public void setPurchase_id(String purchase_id) {
                this.purchase_id = purchase_id;
            }

            public String getPicture_url() {
                return picture_url;
            }

            public void setPicture_url(String picture_url) {
                this.picture_url = picture_url;
            }

            public String getClassify_name() {
                return classify_name;
            }

            public void setClassify_name(String classify_name) {
                this.classify_name = classify_name;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getClassify_id() {
                return classify_id;
            }

            public void setClassify_id(String classify_id) {
                this.classify_id = classify_id;
            }

            public String getMarket_id() {
                return market_id;
            }

            public void setMarket_id(String market_id) {
                this.market_id = market_id;
            }

            public String getSort_id() {
                return sort_id;
            }

            public void setSort_id(String sort_id) {
                this.sort_id = sort_id;
            }

            public String getSon_order_id() {
                return son_order_id;
            }

            public void setSon_order_id(String son_order_id) {
                this.son_order_id = son_order_id;
            }

            public String getPack_standard_id() {
                return pack_standard_id;
            }

            public void setPack_standard_id(String pack_standard_id) {
                this.pack_standard_id = pack_standard_id;
            }

            public String getSpecial_commodity() {
                return special_commodity;
            }

            public void setSpecial_commodity(String special_commodity) {
                this.special_commodity = special_commodity;
            }
            public static class CcListBeanLevel implements Serializable {
                private int type;
                private boolean isXuanzhong;

                public boolean isXuanzhong() {
                    return isXuanzhong;
                }

                public void setXuanzhong(boolean xuanzhong) {
                    isXuanzhong = xuanzhong;
                }
                private XiTongTuiJianBean.CcListBean ccListBean;

                public CcListBeanLevel(int type, XiTongTuiJianBean.CcListBean ccListBean) {
                    this.type = type;
                    this.ccListBean = ccListBean;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public void setCcListBean(XiTongTuiJianBean.CcListBean ccListBean) {
                    this.ccListBean = ccListBean;
                }

                public int getType() {
                    return type;
                }

                public XiTongTuiJianBean.CcListBean getCcListBean() {
                    return ccListBean;
                }

            }
        }
    }
}
