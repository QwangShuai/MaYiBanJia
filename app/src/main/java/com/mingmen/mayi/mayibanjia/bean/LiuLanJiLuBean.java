package com.mingmen.mayi.mayibanjia.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/10/23.
 * 浏览记录
 */

public class LiuLanJiLuBean {

    /**
     * state : 0
     * goodsPicture : http://pbn4jedp4.bkt.clouddn.com/Fq1t9xI6LLaMpH9EdltykZ9ml1E4?e=1539943493&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:RuromuzzqgtYfILmt58izxBwKW8=
     * browse_id : 96b8e0ee8b3b4084a58416180840f4ec
     * commodity_id : 19ead74f2be54c438b6acaf09eca8f39
     * son_number : 1
     * price : 1元/L
     * goodsName : 球球
     * classify_name : 牛肉
     * account_id : 33e1e92ed949440ab85c2c4ef6c71462
     * packStandard :
     * type_tree_id : f39ee1526d194adabb800c77c400028b
     * market_name : 哈达市场
     * shoppingId :
     * user_token : null
     * shoppingCount :
     * browse_time : 2018-10-18 17:11:29
     * collectCount : 0
     * shopName : 互传
     * page : 1
     * sort : null
     * pager : {"length":6,"endIndex":0,"mysqlQueryCondition":" limit 0,10","rowCount":0,"indexs":[],"startIndex":1,"pageCount":0,"orderCondition":"","orderDirection":true,"orderField":"","pageId":1,"pageOffset":0,"pageSize":10,"pageTail":0}
     * order : null
     * rows : 10
     */

    private String goodsPicture;
    private String browse_id;
    private String commodity_id;
    private String son_number;
    private String price;
    private String goodsName;
    private String classify_name;
    private String account_id;
    private String packStandard;
    private String type_tree_id;
    private String market_name;
    private String shoppingId;
    private Object user_token;
    private String shoppingCount;
    private String browse_time;
    private String collectCount;
    private String shopName;
    private String goods;
    private String real_time_state;
    private int page;
    private Object sort;
    private PagerBean pager;
    private Object order;
    private int rows;
    private String inventory;//库存
    private String ration_one;//起订量一
    private String ration_two;//起订量二
    private String ration_three;//起订量三
    private String pice_one;//价格一
    private String pice_two;//价格二
    private String pice_three;//价格三
    private String type_four_id;//四级分类id

    public String getReal_time_state() {
        return real_time_state;
    }

    public void setReal_time_state(String real_time_state) {
        this.real_time_state = real_time_state;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getType_four_id() {
        return type_four_id;
    }

    public void setType_four_id(String type_four_id) {
        this.type_four_id = type_four_id;
    }

    public String getGoodsPicture() {
        return goodsPicture;
    }

    public void setGoodsPicture(String goodsPicture) {
        this.goodsPicture = goodsPicture;
    }

    public String getBrowse_id() {
        return browse_id;
    }

    public void setBrowse_id(String browse_id) {
        this.browse_id = browse_id;
    }

    public String getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(String commodity_id) {
        this.commodity_id = commodity_id;
    }

    public String getSon_number() {
        return son_number;
    }

    public void setSon_number(String son_number) {
        this.son_number = son_number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getClassify_name() {
        return classify_name;
    }

    public void setClassify_name(String classify_name) {
        this.classify_name = classify_name;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getPackStandard() {
        return packStandard;
    }

    public void setPackStandard(String packStandard) {
        this.packStandard = packStandard;
    }

    public String getType_tree_id() {
        return type_tree_id;
    }

    public void setType_tree_id(String type_tree_id) {
        this.type_tree_id = type_tree_id;
    }

    public String getMarket_name() {
        return market_name;
    }

    public void setMarket_name(String market_name) {
        this.market_name = market_name;
    }

    public String getShoppingId() {
        return shoppingId;
    }

    public void setShoppingId(String shoppingId) {
        this.shoppingId = shoppingId;
    }

    public Object getUser_token() {
        return user_token;
    }

    public void setUser_token(Object user_token) {
        this.user_token = user_token;
    }

    public String getShoppingCount() {
        return shoppingCount;
    }

    public void setShoppingCount(String shoppingCount) {
        this.shoppingCount = shoppingCount;
    }

    public String getBrowse_time() {
        return browse_time;
    }

    public void setBrowse_time(String browse_time) {
        this.browse_time = browse_time;
    }

    public String getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(String collectCount) {
        this.collectCount = collectCount;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Object getSort() {
        return sort;
    }

    public void setSort(Object sort) {
        this.sort = sort;
    }

    public PagerBean getPager() {
        return pager;
    }

    public void setPager(PagerBean pager) {
        this.pager = pager;
    }

    public Object getOrder() {
        return order;
    }

    public void setOrder(Object order) {
        this.order = order;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public String getRation_one() {
        return ration_one;
    }

    public void setRation_one(String ration_one) {
        this.ration_one = ration_one;
    }

    public String getRation_two() {
        return ration_two;
    }

    public void setRation_two(String ration_two) {
        this.ration_two = ration_two;
    }

    public String getRation_three() {
        return ration_three;
    }

    public void setRation_three(String ration_three) {
        this.ration_three = ration_three;
    }

    public String getPice_one() {
        return pice_one;
    }

    public void setPice_one(String pice_one) {
        this.pice_one = pice_one;
    }

    public String getPice_two() {
        return pice_two;
    }

    public void setPice_two(String pice_two) {
        this.pice_two = pice_two;
    }

    public String getPice_three() {
        return pice_three;
    }

    public void setPice_three(String pice_three) {
        this.pice_three = pice_three;
    }

    public static class PagerBean {
        /**
         * length : 6
         * endIndex : 0
         * mysqlQueryCondition :  limit 0,10
         * rowCount : 0
         * indexs : []
         * startIndex : 1
         * pageCount : 0
         * orderCondition :
         * orderDirection : true
         * orderField :
         * pageId : 1
         * pageOffset : 0
         * pageSize : 10
         * pageTail : 0
         */

        private int length;
        private int endIndex;
        private String mysqlQueryCondition;
        private int rowCount;
        private int startIndex;
        private int pageCount;
        private String orderCondition;
        private boolean orderDirection;
        private String orderField;
        private int pageId;
        private int pageOffset;
        private int pageSize;
        private int pageTail;
        private List<?> indexs;

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int getEndIndex() {
            return endIndex;
        }

        public void setEndIndex(int endIndex) {
            this.endIndex = endIndex;
        }

        public String getMysqlQueryCondition() {
            return mysqlQueryCondition;
        }

        public void setMysqlQueryCondition(String mysqlQueryCondition) {
            this.mysqlQueryCondition = mysqlQueryCondition;
        }

        public int getRowCount() {
            return rowCount;
        }

        public void setRowCount(int rowCount) {
            this.rowCount = rowCount;
        }

        public int getStartIndex() {
            return startIndex;
        }

        public void setStartIndex(int startIndex) {
            this.startIndex = startIndex;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public String getOrderCondition() {
            return orderCondition;
        }

        public void setOrderCondition(String orderCondition) {
            this.orderCondition = orderCondition;
        }

        public boolean isOrderDirection() {
            return orderDirection;
        }

        public void setOrderDirection(boolean orderDirection) {
            this.orderDirection = orderDirection;
        }

        public String getOrderField() {
            return orderField;
        }

        public void setOrderField(String orderField) {
            this.orderField = orderField;
        }

        public int getPageId() {
            return pageId;
        }

        public void setPageId(int pageId) {
            this.pageId = pageId;
        }

        public int getPageOffset() {
            return pageOffset;
        }

        public void setPageOffset(int pageOffset) {
            this.pageOffset = pageOffset;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageTail() {
            return pageTail;
        }

        public void setPageTail(int pageTail) {
            this.pageTail = pageTail;
        }

        public List<?> getIndexs() {
            return indexs;
        }

        public void setIndexs(List<?> indexs) {
            this.indexs = indexs;
        }
    }
}
