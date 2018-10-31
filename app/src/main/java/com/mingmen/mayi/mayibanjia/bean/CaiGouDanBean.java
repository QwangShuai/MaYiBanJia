package com.mingmen.mayi.mayibanjia.bean;

import android.text.TextUtils;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2018/8/1/001.
 */

public class CaiGouDanBean {
        public static final int TYPE_ONE=0x1;//价格
        public static final int TYPE_TWO=0x2;//销量
        public static final int TYPE_THREE=0x3;//评分
        public static final int TYPE_FOUR=0x4;//历史购买
        public static final int TYPE_FIVE=0x5;//历史收藏


        /**
         * market_id : 2
         * buyer_id : 360a206ff73540d6ac747ded1c6423a7
         * company_id : 21
         * user_token : null
         * purchase_id : 3bb2a4abb4d54163a649bf168cbff9bb
         * create_time : 2018-08-01 16:35:30
         * audit_time : null
         * list : [{"count":55,"market_id":null,"sort_id":"930a7f13c33749c18cc708a31c98234d","user_token":null,"purchase_id":"3bb2a4abb4d54163a649bf168cbff9bb","son_order_id":"521c601b1df943ff9e7172a087225e0c","if_special_need":"0","create_time":null,"change_time":"2018-07-26 09:32:16","special_commodity":"白色","classify_name":"葡萄","changer":null},{"count":10,"market_id":null,"sort_id":"930a7f13c33749c18cc708a31c98234d","user_token":null,"purchase_id":"3bb2a4abb4d54163a649bf168cbff9bb","son_order_id":"90f60bf40af04f34951fd07d1c270cb5","if_special_need":"1","create_time":null,"change_time":"2018-07-26 09:32:16","special_commodity":"就要绿色的","classify_name":"葡萄","changer":null},{"count":55,"market_id":null,"sort_id":"930a7f13c33749c18cc708a31c98234d","user_token":null,"purchase_id":"3bb2a4abb4d54163a649bf168cbff9bb","son_order_id":"c8d812bedb034b33bcf9e5fa38997e09","if_special_need":"1","create_time":null,"change_time":"2018-07-26 09:32:13","special_commodity":"黑色","classify_name":"葡萄","changer":null}]
         * pay_id : null
         * audit_ps : null
         * order_audit_state : 1
         * delivery_address_id : null
         * delivery_type : null
         * order_release_sate : 1
         * assesser_id : null
         * order_describe : null
         * pay_money : null
         */

        private String market_id;
        private String buyer_id;
        private String company_id;
        private String user_token;
        private String purchase_id;
        private String create_time;
        private String audit_time;
        private String pay_id;
        private String audit_ps;
        private String order_audit_state;
        private String delivery_address_id;
        private String delivery_type;
        private String order_release_sate;
        private String assesser_id;
        private String order_describe;
        private String pay_money;
        private String qdTime;
        private List<ListBean> list;

    public String getQdTime() {
        return qdTime;
    }

    public void setQdTime(String qdTime) {
        this.qdTime = qdTime;
    }

    private String zongjia;
    private String son_order_id;
    private String commodity_id;

    public String getSon_order_id() {
        return son_order_id;
    }

    public void setSon_order_id(String son_order_id) {
        this.son_order_id = son_order_id;
    }

    public String getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(String commodity_id) {
        this.commodity_id = commodity_id;
    }

    public String getZongjia() {
        return zongjia;
    }

    public void setZongjia(String zongjia) {
        this.zongjia = zongjia;
    }

    private int type;
    private String purchase_name;

    public String getPurchase_name() {
        return purchase_name;
    }

    public void setPurchase_name(String purchase_name) {
        this.purchase_name = purchase_name;
    }

    public String getMarket_id() {
            return market_id;
        }

        public void setMarket_id(String market_id) {
            this.market_id = market_id;
        }

        public String getBuyer_id() {
            return buyer_id;
        }

        public void setBuyer_id(String buyer_id) {
            this.buyer_id = buyer_id;
        }

        public String getCompany_id() {
            return company_id;
        }

        public void setCompany_id(String company_id) {
            this.company_id = company_id;
        }

        public String getUser_token() {
            return user_token;
        }

        public void setUser_token(String user_token) {
            this.user_token = user_token;
        }

        public String getPurchase_id() {
            return purchase_id;
        }

        public void setPurchase_id(String purchase_id) {
            this.purchase_id = purchase_id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getAudit_time() {
            return audit_time;
        }

        public void setAudit_time(String audit_time) {
            this.audit_time = audit_time;
        }

        public String getPay_id() {
            return pay_id;
        }

        public void setPay_id(String pay_id) {
            this.pay_id = pay_id;
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

        public String getDelivery_address_id() {
            return delivery_address_id;
        }

        public void setDelivery_address_id(String delivery_address_id) {
            this.delivery_address_id = delivery_address_id;
        }

        public String getDelivery_type() {
            return delivery_type;
        }

        public void setDelivery_type(String delivery_type) {
            this.delivery_type = delivery_type;
        }

        public String getOrder_release_sate() {
            return order_release_sate;
        }

        public void setOrder_release_sate(String order_release_sate) {
            this.order_release_sate = order_release_sate;
        }

        public String getAssesser_id() {
            return assesser_id;
        }

        public void setAssesser_id(String assesser_id) {
            this.assesser_id = assesser_id;
        }

        public String getOrder_describe() {
            return order_describe;
        }

        public void setOrder_describe(String order_describe) {
            this.order_describe = order_describe;
        }

        public String getPay_money() {
            return pay_money;
        }

        public void setPay_money(String pay_money) {
            this.pay_money = pay_money;
        }

        public List<ListBean> getList() {
            return list==null?new ArrayList<ListBean>():list;
        }

        public int listSize(){
            return getList().size();
        }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public String getString(){
            StringBuilder stringBuilder=new StringBuilder();
            List<CaiGouDanBean.ListBean> listBeen=getList();
            int size=listBeen.size();
            for (int i = 0; i < size; i++) {
                CaiGouDanBean.ListBean bean=listBeen.get(i);
                stringBuilder.append(bean==null?"":bean.getClassify_name());
            }
            return stringBuilder.toString();
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }



    public static class ListBean extends AbstractExpandableItem<CcListBeanLevel> implements MultiItemEntity {
            public static final int Level_0=0x10;
            public static final int Level_1=0x20;

            /**
             * count : 55
             * market_id : null
             * sort_id : 930a7f13c33749c18cc708a31c98234d
             * user_token : null
             * purchase_id : 3bb2a4abb4d54163a649bf168cbff9bb
             * son_order_id : 521c601b1df943ff9e7172a087225e0c
             * if_special_need : 0
             * create_time : null
             * change_time : 2018-07-26 09:32:16
             * special_commodity : 白色
             * classify_name : 葡萄
             * changer : null
             */

            private int count;
            private String market_id;
            private String sort_id;
            private String user_token;
            private String purchase_id;
            private String son_order_id;
            private int if_special_need;
            private String create_time;
            private String change_time;
            private String special_commodity;//空就不是特殊商品
            private String classify_name;
            private String changer;
            private boolean yituijian;
            private String picture_url;
        private String pack_standard_name;
        private String pack_standard_id;

        public String getPack_standard_id() {
            return pack_standard_id;
        }

        public void setPack_standard_id(String pack_standard_id) {
            this.pack_standard_id = pack_standard_id;
        }

        private List<CaiGouDanBean.CcListBeanLevel> levels;
            private boolean needLoad=true;
            private String mark_id;

        public String getMark_id() {
            return mark_id;
        }

        public void setMark_id(String mark_id) {
            this.mark_id = mark_id;
        }

        public String getPack_standard_name() {
            return pack_standard_name;
        }

        public void setPack_standard_name(String pack_standard_name) {
            this.pack_standard_name = pack_standard_name;
        }

        public List<CcListBeanLevel> getLevels() {
            return levels;
        }

        public void setLevels(List<CcListBeanLevel> levels) {
            this.levels = levels;
        }

        public String getPicture_url() {
                return picture_url;
            }

            public void setPicture_url(String picture_url) {
                this.picture_url = picture_url;
            }

            public boolean isYituijian() {
                return yituijian;
            }

            public void setYituijian(boolean yituijian) {
                this.yituijian = yituijian;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
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

            public String getUser_token() {
                return user_token;
            }

            public void setUser_token(String user_token) {
                this.user_token = user_token;
            }

            public String getPurchase_id() {
                return purchase_id;
            }

            public void setPurchase_id(String purchase_id) {
                this.purchase_id = purchase_id;
            }

            public String getSon_order_id() {
                return son_order_id;
            }

            public void setSon_order_id(String son_order_id) {
                this.son_order_id = son_order_id;
            }

            public int getIf_special_need() {
                return if_special_need;
            }

            public void setIf_special_need(int if_special_need) {
                this.if_special_need = if_special_need;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getChange_time() {
                return change_time;
            }

            public void setChange_time(String change_time) {
                this.change_time = change_time;
            }

            public String getSpecial_commodity() {
                return special_commodity;
            }

            public void setSpecial_commodity(String special_commodity) {
                this.special_commodity = special_commodity;
            }

            public String getClassify_name() {
                return classify_name;
            }

            public void setClassify_name(String classify_name) {
                this.classify_name = classify_name;
            }

            public String getChanger() {
                return changer;
            }

            public boolean isSpecial(){//判断特殊要求是否为空
                return !TextUtils.isEmpty(special_commodity);
            }

        public boolean isNeedLoad() {
            return needLoad;
        }

        public void setNeedLoad(boolean needLoad) {
            this.needLoad = needLoad;
        }

        public void setChanger(String changer) {
                this.changer = changer;
            }

        @Override
        public int getLevel() {
            return Level_0;
        }

        @Override
        public int getItemType() {
            return Level_0;
        }
    }


    public static class CcListBeanLevel implements MultiItemEntity{
        private int type;

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

        @Override
        public int getItemType() {
            return CaiGouDanBean.ListBean.Level_1;
        }
    }

}
