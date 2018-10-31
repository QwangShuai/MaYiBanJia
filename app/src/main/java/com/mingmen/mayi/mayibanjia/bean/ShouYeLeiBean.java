package com.mingmen.mayi.mayibanjia.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/5/005.
 */

public class ShouYeLeiBean {

    /**
     * classify_id : 15f71aee85c74f2fb209de2d3f77317c
     * classify_name : 水果
     * classify_num : null
     * classify_grade : 1
     * parent_id : -1
     * picture : null
     * create_time : 2018-07-04 14:56:14
     * page : 1
     * order : null
     * sort : null
     * pager : {"length":6,"endIndex":0,"orderDirection":true,"mysqlQueryCondition":" limit 0,10","orderCondition":"","pageSize":10,"rowCount":0,"pageOffset":0,"pageTail":0,"pageId":1,"orderField":"","pageCount":0,"startIndex":1,"indexs":[]}
     * rows : 10
     */

    private String classify_id;
    private String classify_name;
    private String classify_num;
    private String classify_grade;
    private String parent_id;
    private String picture;
    private String create_time;
    private int page;
    private String order;
    private String sort;
    private PagerBean pager;
    private int rows;
    private String picture_url;

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public String getClassify_id() {
        return classify_id;
    }

    public void setClassify_id(String classify_id) {
        this.classify_id = classify_id;
    }

    public String getClassify_name() {
        return classify_name;
    }

    public void setClassify_name(String classify_name) {
        this.classify_name = classify_name;
    }

    public String getClassify_num() {
        return classify_num;
    }

    public void setClassify_num(String classify_num) {
        this.classify_num = classify_num;
    }

    public String getClassify_grade() {
        return classify_grade;
    }

    public void setClassify_grade(String classify_grade) {
        this.classify_grade = classify_grade;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public PagerBean getPager() {
        return pager;
    }

    public void setPager(PagerBean pager) {
        this.pager = pager;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public static class PagerBean {
        /**
         * length : 6
         * endIndex : 0
         * orderDirection : true
         * mysqlQueryCondition :  limit 0,10
         * orderCondition :
         * pageSize : 10
         * rowCount : 0
         * pageOffset : 0
         * pageTail : 0
         * pageId : 1
         * orderField :
         * pageCount : 0
         * startIndex : 1
         * indexs : []
         */

        private int length;
        private int endIndex;
        private boolean orderDirection;
        private String mysqlQueryCondition;
        private String orderCondition;
        private int pageSize;
        private int rowCount;
        private int pageOffset;
        private int pageTail;
        private int pageId;
        private String orderField;
        private int pageCount;
        private int startIndex;

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

        public boolean isOrderDirection() {
            return orderDirection;
        }

        public void setOrderDirection(boolean orderDirection) {
            this.orderDirection = orderDirection;
        }

        public String getMysqlQueryCondition() {
            return mysqlQueryCondition;
        }

        public void setMysqlQueryCondition(String mysqlQueryCondition) {
            this.mysqlQueryCondition = mysqlQueryCondition;
        }

        public String getOrderCondition() {
            return orderCondition;
        }

        public void setOrderCondition(String orderCondition) {
            this.orderCondition = orderCondition;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getRowCount() {
            return rowCount;
        }

        public void setRowCount(int rowCount) {
            this.rowCount = rowCount;
        }

        public int getPageOffset() {
            return pageOffset;
        }

        public void setPageOffset(int pageOffset) {
            this.pageOffset = pageOffset;
        }

        public int getPageTail() {
            return pageTail;
        }

        public void setPageTail(int pageTail) {
            this.pageTail = pageTail;
        }

        public int getPageId() {
            return pageId;
        }

        public void setPageId(int pageId) {
            this.pageId = pageId;
        }

        public String getOrderField() {
            return orderField;
        }

        public void setOrderField(String orderField) {
            this.orderField = orderField;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getStartIndex() {
            return startIndex;
        }

        public void setStartIndex(int startIndex) {
            this.startIndex = startIndex;
        }

    }
}
