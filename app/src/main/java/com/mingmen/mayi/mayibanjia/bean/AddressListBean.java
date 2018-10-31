package com.mingmen.mayi.mayibanjia.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/20/020.
 */

public class AddressListBean {

        /**
         * region : 230103
         * specific_address : 和兴路103号
         * contact_type : 13800000000
         * province_name : 黑龙江省
         * region_name : 南岗区
         * street_name : 测试街道1
         * default_address : 1
         * create_time : 2018-5-6
         * account_id : d02613115cf2422ea9e88d2de23564dc
         * city : 230100
         * province : 230000
         * user_token : null
         * street : 23010301
         * city_name : 哈尔滨市
         * linkman : 曹操
         * address_id : 42899c4ecdbb439d829405d665147761
         * page : 1
         * order : null
         * sort : null
         * rows : 10
         * pager : {"length":6,"endIndex":0,"orderCondition":"","mysqlQueryCondition":" limit 0,10","orderDirection":true,"indexs":[],"startIndex":1,"pageCount":0,"orderField":"","pageId":1,"pageOffset":0,"pageSize":10,"pageTail":0,"rowCount":0}
         */

        private String region;
        private String specific_address;
        private String contact_type;
        private String province_name;
        private String region_name;
        private String street_name;
        private String default_address;
        private String create_time;
        private String account_id;
        private String city;
        private String province;
        private Object user_token;
        private String street;
        private String city_name;
        private String linkman;
        private String address_id;
        private int page;
        private Object order;
        private Object sort;
        private int rows;
        private PagerBean pager;

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getSpecific_address() {
            return specific_address;
        }

        public void setSpecific_address(String specific_address) {
            this.specific_address = specific_address;
        }

        public String getContact_type() {
            return contact_type;
        }

        public void setContact_type(String contact_type) {
            this.contact_type = contact_type;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public String getRegion_name() {
            return region_name;
        }

        public void setRegion_name(String region_name) {
            this.region_name = region_name;
        }

        public String getStreet_name() {
            return street_name;
        }

        public void setStreet_name(String street_name) {
            this.street_name = street_name;
        }

        public String getDefault_address() {
            return default_address;
        }

        public void setDefault_address(String default_address) {
            this.default_address = default_address;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getAccount_id() {
            return account_id;
        }

        public void setAccount_id(String account_id) {
            this.account_id = account_id;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public Object getUser_token() {
            return user_token;
        }

        public void setUser_token(Object user_token) {
            this.user_token = user_token;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getLinkman() {
            return linkman;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
        }

        public String getAddress_id() {
            return address_id;
        }

        public void setAddress_id(String address_id) {
            this.address_id = address_id;
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
             * orderCondition :
             * mysqlQueryCondition :  limit 0,10
             * orderDirection : true
             * indexs : []
             * startIndex : 1
             * pageCount : 0
             * orderField :
             * pageId : 1
             * pageOffset : 0
             * pageSize : 10
             * pageTail : 0
             * rowCount : 0
             */

            private int length;
            private int endIndex;
            private String orderCondition;
            private String mysqlQueryCondition;
            private boolean orderDirection;
            private int startIndex;
            private int pageCount;
            private String orderField;
            private int pageId;
            private int pageOffset;
            private int pageSize;
            private int pageTail;
            private int rowCount;
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

            public String getOrderCondition() {
                return orderCondition;
            }

            public void setOrderCondition(String orderCondition) {
                this.orderCondition = orderCondition;
            }

            public String getMysqlQueryCondition() {
                return mysqlQueryCondition;
            }

            public void setMysqlQueryCondition(String mysqlQueryCondition) {
                this.mysqlQueryCondition = mysqlQueryCondition;
            }

            public boolean isOrderDirection() {
                return orderDirection;
            }

            public void setOrderDirection(boolean orderDirection) {
                this.orderDirection = orderDirection;
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

            public int getRowCount() {
                return rowCount;
            }

            public void setRowCount(int rowCount) {
                this.rowCount = rowCount;
            }

            public List<?> getIndexs() {
                return indexs;
            }

            public void setIndexs(List<?> indexs) {
                this.indexs = indexs;
            }
        }
}
