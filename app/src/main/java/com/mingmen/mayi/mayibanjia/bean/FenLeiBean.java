package com.mingmen.mayi.mayibanjia.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/31.
 */

public class FenLeiBean {
    
        /**
         * parent_id : null
         * spec_idTwo : null
         * spec_idOne : null
         * picture : null
         * create_time : null
         * spec_idFour : null
         * classify_id : 12345678912345634234242546aaabbb
         * paramete_name_id : null
         * classify_num : null
         * classify_name : 小柿子
         * classify_grade : null
         * picture_url : null
         * spec_idThree : null
         * order : null
         * page : 1
         * sort : null
         * rows : 10
         * pager : {"length":6,"endIndex":0,"pageId":1,"pageSize":10,"pageCount":0,"pageOffset":0,"pageTail":0,"startIndex":1,"orderField":"","indexs":[],"rowCount":0,"orderCondition":"","orderDirection":true,"mysqlQueryCondition":" limit 0,10"}
         */

        private String parent_id;
        private String spec_idTwo;
        private String spec_idOne;
        private String picture;
        private String create_time;
        private String spec_idFour;
        private String classify_id;
        private String paramete_name_id;
        private String classify_num;
        private String classify_name;
        private String classify_grade;
        private String picture_url;
        private String spec_idThree;
        private String order;
        private int page;
        private String sort;
        private int rows;
        private PagerBean pager;

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getSpec_idTwo() {
            return spec_idTwo;
        }

        public void setSpec_idTwo(String spec_idTwo) {
            this.spec_idTwo = spec_idTwo;
        }

        public String getSpec_idOne() {
            return spec_idOne;
        }

        public void setSpec_idOne(String spec_idOne) {
            this.spec_idOne = spec_idOne;
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

        public String getSpec_idFour() {
            return spec_idFour;
        }

        public void setSpec_idFour(String spec_idFour) {
            this.spec_idFour = spec_idFour;
        }

        public String getClassify_id() {
            return classify_id;
        }

        public void setClassify_id(String classify_id) {
            this.classify_id = classify_id;
        }

        public String getParamete_name_id() {
            return paramete_name_id;
        }

        public void setParamete_name_id(String paramete_name_id) {
            this.paramete_name_id = paramete_name_id;
        }

        public String getClassify_num() {
            return classify_num;
        }

        public void setClassify_num(String classify_num) {
            this.classify_num = classify_num;
        }

        public String getClassify_name() {
            return classify_name;
        }

        public void setClassify_name(String classify_name) {
            this.classify_name = classify_name;
        }

        public String getClassify_grade() {
            return classify_grade;
        }

        public void setClassify_grade(String classify_grade) {
            this.classify_grade = classify_grade;
        }

        public String getPicture_url() {
            return picture_url;
        }

        public void setPicture_url(String picture_url) {
            this.picture_url = picture_url;
        }

        public String getSpec_idThree() {
            return spec_idThree;
        }

        public void setSpec_idThree(String spec_idThree) {
            this.spec_idThree = spec_idThree;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public int getRows() {
            return rows;
        }

        public void setRows(int rows) {
            this.rows = rows;
        }

        public PagerBean getPager() {
            return pager;
        }

        public void setPager(PagerBean pager) {
            this.pager = pager;
        }

        public static class PagerBean {
            /**
             * length : 6
             * endIndex : 0
             * pageId : 1
             * pageSize : 10
             * pageCount : 0
             * pageOffset : 0
             * pageTail : 0
             * startIndex : 1
             * orderField : 
             * indexs : []
             * rowCount : 0
             * orderCondition : 
             * orderDirection : true
             * mysqlQueryCondition :  limit 0,10
             */

            private int length;
            private int endIndex;
            private int pageId;
            private int pageSize;
            private int pageCount;
            private int pageOffset;
            private int pageTail;
            private int startIndex;
            private String orderField;
            private int rowCount;
            private String orderCondition;
            private boolean orderDirection;
            private String mysqlQueryCondition;
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

            public int getPageId() {
                return pageId;
            }

            public void setPageId(int pageId) {
                this.pageId = pageId;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getPageCount() {
                return pageCount;
            }

            public void setPageCount(int pageCount) {
                this.pageCount = pageCount;
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

            public int getStartIndex() {
                return startIndex;
            }

            public void setStartIndex(int startIndex) {
                this.startIndex = startIndex;
            }

            public String getOrderField() {
                return orderField;
            }

            public void setOrderField(String orderField) {
                this.orderField = orderField;
            }

            public int getRowCount() {
                return rowCount;
            }

            public void setRowCount(int rowCount) {
                this.rowCount = rowCount;
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

            public String getMysqlQueryCondition() {
                return mysqlQueryCondition;
            }

            public void setMysqlQueryCondition(String mysqlQueryCondition) {
                this.mysqlQueryCondition = mysqlQueryCondition;
            }

            public List<?> getIndexs() {
                return indexs;
            }

            public void setIndexs(List<?> indexs) {
                this.indexs = indexs;
            }
        }
    
}
