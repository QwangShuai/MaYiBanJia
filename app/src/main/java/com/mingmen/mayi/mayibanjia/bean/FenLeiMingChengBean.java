package com.mingmen.mayi.mayibanjia.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/8.
 */

public class FenLeiMingChengBean {

        /**
         * picture : null
         * parent_id : -1
         * spec_idOne : 7f6e635f04014b0ebf16759eb5b38318
         * spec_idTwo : 8cc245bd9d1541f58dbc71a8d1ac4016
         * picture_url : null
         * create_time : 2018-07-17 14:27:21
         * spec_idThree : 2c57599b81f64a29a7bcd5aee8a826c4
         * classify_id : 0104a16610f2457b8f92185e46eccfe9
         * paramete_name_id : null
         * classify_num : null
         * spec_idFour : 021fd0f6e88c48bfa268edd792dac6c6
         * classify_name : 132
         * classify_grade : 1
         * page : 1
         * order : null
         * sort : null
         * pager : {"length":6,"endIndex":0,"indexs":[],"pageTail":0,"pageId":1,"pageOffset":0,"pageSize":10,"startIndex":1,"orderField":"","rowCount":0,"pageCount":0,"mysqlQueryCondition":" limit 0,10","orderCondition":"","orderDirection":true}
         * rows : 10
         */

        private Object picture;
        private String parent_id;
        private String spec_idOne;
        private String spec_idTwo;
        private Object picture_url;
        private String create_time;
        private String spec_idThree;
        private String classify_id;
        private Object paramete_name_id;
        private Object classify_num;
        private String spec_idFour;
        private String classify_name;
        private String classify_grade;
        private int page;
        private Object order;
        private Object sort;
        private PagerBean pager;
        private int rows;

        public Object getPicture() {
            return picture;
        }

        public void setPicture(Object picture) {
            this.picture = picture;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getSpec_idOne() {
            return spec_idOne;
        }

        public void setSpec_idOne(String spec_idOne) {
            this.spec_idOne = spec_idOne;
        }

        public String getSpec_idTwo() {
            return spec_idTwo;
        }

        public void setSpec_idTwo(String spec_idTwo) {
            this.spec_idTwo = spec_idTwo;
        }

        public Object getPicture_url() {
            return picture_url;
        }

        public void setPicture_url(Object picture_url) {
            this.picture_url = picture_url;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getSpec_idThree() {
            return spec_idThree;
        }

        public void setSpec_idThree(String spec_idThree) {
            this.spec_idThree = spec_idThree;
        }

        public String getClassify_id() {
            return classify_id;
        }

        public void setClassify_id(String classify_id) {
            this.classify_id = classify_id;
        }

        public Object getParamete_name_id() {
            return paramete_name_id;
        }

        public void setParamete_name_id(Object paramete_name_id) {
            this.paramete_name_id = paramete_name_id;
        }

        public Object getClassify_num() {
            return classify_num;
        }

        public void setClassify_num(Object classify_num) {
            this.classify_num = classify_num;
        }

        public String getSpec_idFour() {
            return spec_idFour;
        }

        public void setSpec_idFour(String spec_idFour) {
            this.spec_idFour = spec_idFour;
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

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public Object getOrder() {
            return order;
        }

        public void setOrder(Object order) {
            this.order = order;
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
             * indexs : []
             * pageTail : 0
             * pageId : 1
             * pageOffset : 0
             * pageSize : 10
             * startIndex : 1
             * orderField :
             * rowCount : 0
             * pageCount : 0
             * mysqlQueryCondition :  limit 0,10
             * orderCondition :
             * orderDirection : true
             */

            private int length;
            private int endIndex;
            private int pageTail;
            private int pageId;
            private int pageOffset;
            private int pageSize;
            private int startIndex;
            private String orderField;
            private int rowCount;
            private int pageCount;
            private String mysqlQueryCondition;
            private String orderCondition;
            private boolean orderDirection;
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

            public int getPageCount() {
                return pageCount;
            }

            public void setPageCount(int pageCount) {
                this.pageCount = pageCount;
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

            public boolean isOrderDirection() {
                return orderDirection;
            }

            public void setOrderDirection(boolean orderDirection) {
                this.orderDirection = orderDirection;
            }

            public List<?> getIndexs() {
                return indexs;
            }

            public void setIndexs(List<?> indexs) {
                this.indexs = indexs;
            }
        }
    @Override
    public String toString() {
        //重写该方法，作为选择器显示的名称
        return classify_name;
    }
}
