package com.mingmen.mayi.mayibanjia.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/9/15.
 */

public class XQPingJiaBean {

    /**
     * anonymous : 1
     * order_id : 66db75035bec4679adc161b10487f55e
     * creater : 33e1e92ed949440ab85c2c4ef6c71462
     * approval_state : null
     * company_name : null
     * head_shot : null
     * company_id : 8ecb8de2f37c4f2bb544824c10f0c1c6
     * user_token : null
     * star_evaluation : 5
     * rowgu_id : 9a57a9c65d6941d1843351f90180d4ca
     * pjCompanyName : 光明
     * headPhoto : http://qiniu.canchengxiang.com/abc64f0a7ac343069a53f881dc4db7f512.jpg?e=1543314240&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:jFnc0oCNmM_J6VELdBPMXMeTkqk=
     * parentgu_id : null
     * comment_text : 1502,1503
     * file_name : null
     * create_time : 2018-11-27 09:59:10
     * replyList : [{"anonymous":null,"order_id":null,"creater":null,"approval_state":null,"company_name":null,"head_shot":null,"company_id":null,"user_token":null,"star_evaluation":null,"rowgu_id":null,"pjCompanyName":null,"headPhoto":null,"parentgu_id":null,"comment_text":"1601,1602","file_name":null,"create_time":"2018-11-27 17:12:41","replyList":null,"shop_rowgu":null,"satisfaction":null,"evaluate_state":null,"touser_name":null,"comment_date":null,"commentuserNameId":null,"orderList":null,"bpCompanyName":null,"pjList":[{"state":null,"log_url":null,"parent_number":null,"son_number":"1601","change_time":null,"remarks":null,"parent_name":null,"dictionary_id":null,"son_name":"谢谢买家的夸赞","create_time":null},{"state":null,"log_url":null,"parent_number":null,"son_number":"1602","change_time":null,"remarks":null,"parent_name":null,"dictionary_id":null,"son_name":"买家是骗子","create_time":null}],"page":1,"pager":{"length":6,"endIndex":0,"mysqlQueryCondition":" limit 0,10","startIndex":1,"indexs":[],"rowCount":0,"pageTail":0,"pageSize":10,"pageOffset":0,"pageId":1,"orderField":"","orderDirection":true,"orderCondition":"","pageCount":0},"rows":10,"order":null,"sort":null}]
     * shop_rowgu : null
     * satisfaction : null
     * evaluate_state : 1
     * touser_name : null
     * comment_date : null
     * commentuserNameId : 33e1e92ed949440ab85c2c4ef6c71462
     * orderList : null
     * bpCompanyName : 互传
     * pjList : [{"state":null,"log_url":null,"parent_number":null,"son_number":"1502","change_time":null,"remarks":null,"parent_name":null,"dictionary_id":null,"son_name":"中评","create_time":null},{"state":null,"log_url":null,"parent_number":null,"son_number":"1503","change_time":null,"remarks":null,"parent_name":null,"dictionary_id":null,"son_name":"差评","create_time":null}]
     * page : 1
     * pager : {"length":6,"endIndex":0,"mysqlQueryCondition":" limit 0,10","startIndex":1,"indexs":[],"rowCount":0,"pageTail":0,"pageSize":10,"pageOffset":0,"pageId":1,"orderField":"","orderDirection":true,"orderCondition":"","pageCount":0}
     * rows : 10
     * order : null
     * sort : null
     */

    private String anonymous;
    private String order_id;
    private String creater;
    private Object approval_state;
    private Object company_name;
    private Object head_shot;
    private String company_id;
    private Object user_token;
    private float star_evaluation;
    private String rowgu_id;
    private String pjCompanyName;
    private String headPhoto;
    private Object parentgu_id;
    private String comment_text;
    private Object file_name;
    private String create_time;
    private Object shop_rowgu;
    private Object satisfaction;
    private String evaluate_state;
    private Object touser_name;
    private Object comment_date;
    private String commentuserNameId;
    private Object orderList;
    private String bpCompanyName;
    private int page;
    private PagerBean pager;
    private int rows;
    private Object order;
    private Object sort;
    private List<ReplyListBean> replyList;
    private List<PjListBeanX> pjList;

    public String getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(String anonymous) {
        this.anonymous = anonymous;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Object getApproval_state() {
        return approval_state;
    }

    public void setApproval_state(Object approval_state) {
        this.approval_state = approval_state;
    }

    public Object getCompany_name() {
        return company_name;
    }

    public void setCompany_name(Object company_name) {
        this.company_name = company_name;
    }

    public Object getHead_shot() {
        return head_shot;
    }

    public void setHead_shot(Object head_shot) {
        this.head_shot = head_shot;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public Object getUser_token() {
        return user_token;
    }

    public void setUser_token(Object user_token) {
        this.user_token = user_token;
    }

    public float getStar_evaluation() {
        return star_evaluation;
    }

    public void setStar_evaluation(float star_evaluation) {
        this.star_evaluation = star_evaluation;
    }

    public String getRowgu_id() {
        return rowgu_id;
    }

    public void setRowgu_id(String rowgu_id) {
        this.rowgu_id = rowgu_id;
    }

    public String getPjCompanyName() {
        return pjCompanyName;
    }

    public void setPjCompanyName(String pjCompanyName) {
        this.pjCompanyName = pjCompanyName;
    }

    public String getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(String headPhoto) {
        this.headPhoto = headPhoto;
    }

    public Object getParentgu_id() {
        return parentgu_id;
    }

    public void setParentgu_id(Object parentgu_id) {
        this.parentgu_id = parentgu_id;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public Object getFile_name() {
        return file_name;
    }

    public void setFile_name(Object file_name) {
        this.file_name = file_name;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public Object getShop_rowgu() {
        return shop_rowgu;
    }

    public void setShop_rowgu(Object shop_rowgu) {
        this.shop_rowgu = shop_rowgu;
    }

    public Object getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(Object satisfaction) {
        this.satisfaction = satisfaction;
    }

    public String getEvaluate_state() {
        return evaluate_state;
    }

    public void setEvaluate_state(String evaluate_state) {
        this.evaluate_state = evaluate_state;
    }

    public Object getTouser_name() {
        return touser_name;
    }

    public void setTouser_name(Object touser_name) {
        this.touser_name = touser_name;
    }

    public Object getComment_date() {
        return comment_date;
    }

    public void setComment_date(Object comment_date) {
        this.comment_date = comment_date;
    }

    public String getCommentuserNameId() {
        return commentuserNameId;
    }

    public void setCommentuserNameId(String commentuserNameId) {
        this.commentuserNameId = commentuserNameId;
    }

    public Object getOrderList() {
        return orderList;
    }

    public void setOrderList(Object orderList) {
        this.orderList = orderList;
    }

    public String getBpCompanyName() {
        return bpCompanyName;
    }

    public void setBpCompanyName(String bpCompanyName) {
        this.bpCompanyName = bpCompanyName;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
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

    public List<ReplyListBean> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ReplyListBean> replyList) {
        this.replyList = replyList;
    }

    public List<PjListBeanX> getPjList() {
        return pjList;
    }

    public void setPjList(List<PjListBeanX> pjList) {
        this.pjList = pjList;
    }

    public static class PagerBean {
        /**
         * length : 6
         * endIndex : 0
         * mysqlQueryCondition :  limit 0,10
         * startIndex : 1
         * indexs : []
         * rowCount : 0
         * pageTail : 0
         * pageSize : 10
         * pageOffset : 0
         * pageId : 1
         * orderField :
         * orderDirection : true
         * orderCondition :
         * pageCount : 0
         */

        private int length;
        private int endIndex;
        private String mysqlQueryCondition;
        private int startIndex;
        private int rowCount;
        private int pageTail;
        private int pageSize;
        private int pageOffset;
        private int pageId;
        private String orderField;
        private boolean orderDirection;
        private String orderCondition;
        private int pageCount;
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

        public int getStartIndex() {
            return startIndex;
        }

        public void setStartIndex(int startIndex) {
            this.startIndex = startIndex;
        }

        public int getRowCount() {
            return rowCount;
        }

        public void setRowCount(int rowCount) {
            this.rowCount = rowCount;
        }

        public int getPageTail() {
            return pageTail;
        }

        public void setPageTail(int pageTail) {
            this.pageTail = pageTail;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageOffset() {
            return pageOffset;
        }

        public void setPageOffset(int pageOffset) {
            this.pageOffset = pageOffset;
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

        public boolean isOrderDirection() {
            return orderDirection;
        }

        public void setOrderDirection(boolean orderDirection) {
            this.orderDirection = orderDirection;
        }

        public String getOrderCondition() {
            return orderCondition;
        }

        public void setOrderCondition(String orderCondition) {
            this.orderCondition = orderCondition;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public List<?> getIndexs() {
            return indexs;
        }

        public void setIndexs(List<?> indexs) {
            this.indexs = indexs;
        }
    }

    public static class ReplyListBean {
        /**
         * anonymous : null
         * order_id : null
         * creater : null
         * approval_state : null
         * company_name : null
         * head_shot : null
         * company_id : null
         * user_token : null
         * star_evaluation : null
         * rowgu_id : null
         * pjCompanyName : null
         * headPhoto : null
         * parentgu_id : null
         * comment_text : 1601,1602
         * file_name : null
         * create_time : 2018-11-27 17:12:41
         * replyList : null
         * shop_rowgu : null
         * satisfaction : null
         * evaluate_state : null
         * touser_name : null
         * comment_date : null
         * commentuserNameId : null
         * orderList : null
         * bpCompanyName : null
         * pjList : [{"state":null,"log_url":null,"parent_number":null,"son_number":"1601","change_time":null,"remarks":null,"parent_name":null,"dictionary_id":null,"son_name":"谢谢买家的夸赞","create_time":null},{"state":null,"log_url":null,"parent_number":null,"son_number":"1602","change_time":null,"remarks":null,"parent_name":null,"dictionary_id":null,"son_name":"买家是骗子","create_time":null}]
         * page : 1
         * pager : {"length":6,"endIndex":0,"mysqlQueryCondition":" limit 0,10","startIndex":1,"indexs":[],"rowCount":0,"pageTail":0,"pageSize":10,"pageOffset":0,"pageId":1,"orderField":"","orderDirection":true,"orderCondition":"","pageCount":0}
         * rows : 10
         * order : null
         * sort : null
         */

        private Object anonymous;
        private Object order_id;
        private Object creater;
        private Object approval_state;
        private Object company_name;
        private Object head_shot;
        private Object company_id;
        private Object user_token;
        private Object star_evaluation;
        private Object rowgu_id;
        private Object pjCompanyName;
        private Object headPhoto;
        private Object parentgu_id;
        private String comment_text;
        private Object file_name;
        private String create_time;
        private Object replyList;
        private Object shop_rowgu;
        private Object satisfaction;
        private Object evaluate_state;
        private Object touser_name;
        private Object comment_date;
        private Object commentuserNameId;
        private Object orderList;
        private Object bpCompanyName;
        private int page;
        private PagerBeanX pager;
        private int rows;
        private Object order;
        private Object sort;
        private List<PjListBean> pjList;

        public Object getAnonymous() {
            return anonymous;
        }

        public void setAnonymous(Object anonymous) {
            this.anonymous = anonymous;
        }

        public Object getOrder_id() {
            return order_id;
        }

        public void setOrder_id(Object order_id) {
            this.order_id = order_id;
        }

        public Object getCreater() {
            return creater;
        }

        public void setCreater(Object creater) {
            this.creater = creater;
        }

        public Object getApproval_state() {
            return approval_state;
        }

        public void setApproval_state(Object approval_state) {
            this.approval_state = approval_state;
        }

        public Object getCompany_name() {
            return company_name;
        }

        public void setCompany_name(Object company_name) {
            this.company_name = company_name;
        }

        public Object getHead_shot() {
            return head_shot;
        }

        public void setHead_shot(Object head_shot) {
            this.head_shot = head_shot;
        }

        public Object getCompany_id() {
            return company_id;
        }

        public void setCompany_id(Object company_id) {
            this.company_id = company_id;
        }

        public Object getUser_token() {
            return user_token;
        }

        public void setUser_token(Object user_token) {
            this.user_token = user_token;
        }

        public Object getStar_evaluation() {
            return star_evaluation;
        }

        public void setStar_evaluation(Object star_evaluation) {
            this.star_evaluation = star_evaluation;
        }

        public Object getRowgu_id() {
            return rowgu_id;
        }

        public void setRowgu_id(Object rowgu_id) {
            this.rowgu_id = rowgu_id;
        }

        public Object getPjCompanyName() {
            return pjCompanyName;
        }

        public void setPjCompanyName(Object pjCompanyName) {
            this.pjCompanyName = pjCompanyName;
        }

        public Object getHeadPhoto() {
            return headPhoto;
        }

        public void setHeadPhoto(Object headPhoto) {
            this.headPhoto = headPhoto;
        }

        public Object getParentgu_id() {
            return parentgu_id;
        }

        public void setParentgu_id(Object parentgu_id) {
            this.parentgu_id = parentgu_id;
        }

        public String getComment_text() {
            return comment_text;
        }

        public void setComment_text(String comment_text) {
            this.comment_text = comment_text;
        }

        public Object getFile_name() {
            return file_name;
        }

        public void setFile_name(Object file_name) {
            this.file_name = file_name;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public Object getReplyList() {
            return replyList;
        }

        public void setReplyList(Object replyList) {
            this.replyList = replyList;
        }

        public Object getShop_rowgu() {
            return shop_rowgu;
        }

        public void setShop_rowgu(Object shop_rowgu) {
            this.shop_rowgu = shop_rowgu;
        }

        public Object getSatisfaction() {
            return satisfaction;
        }

        public void setSatisfaction(Object satisfaction) {
            this.satisfaction = satisfaction;
        }

        public Object getEvaluate_state() {
            return evaluate_state;
        }

        public void setEvaluate_state(Object evaluate_state) {
            this.evaluate_state = evaluate_state;
        }

        public Object getTouser_name() {
            return touser_name;
        }

        public void setTouser_name(Object touser_name) {
            this.touser_name = touser_name;
        }

        public Object getComment_date() {
            return comment_date;
        }

        public void setComment_date(Object comment_date) {
            this.comment_date = comment_date;
        }

        public Object getCommentuserNameId() {
            return commentuserNameId;
        }

        public void setCommentuserNameId(Object commentuserNameId) {
            this.commentuserNameId = commentuserNameId;
        }

        public Object getOrderList() {
            return orderList;
        }

        public void setOrderList(Object orderList) {
            this.orderList = orderList;
        }

        public Object getBpCompanyName() {
            return bpCompanyName;
        }

        public void setBpCompanyName(Object bpCompanyName) {
            this.bpCompanyName = bpCompanyName;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public PagerBeanX getPager() {
            return pager;
        }

        public void setPager(PagerBeanX pager) {
            this.pager = pager;
        }

        public int getRows() {
            return rows;
        }

        public void setRows(int rows) {
            this.rows = rows;
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

        public List<PjListBean> getPjList() {
            return pjList;
        }

        public void setPjList(List<PjListBean> pjList) {
            this.pjList = pjList;
        }

        public static class PagerBeanX {
            /**
             * length : 6
             * endIndex : 0
             * mysqlQueryCondition :  limit 0,10
             * startIndex : 1
             * indexs : []
             * rowCount : 0
             * pageTail : 0
             * pageSize : 10
             * pageOffset : 0
             * pageId : 1
             * orderField :
             * orderDirection : true
             * orderCondition :
             * pageCount : 0
             */

            private int length;
            private int endIndex;
            private String mysqlQueryCondition;
            private int startIndex;
            private int rowCount;
            private int pageTail;
            private int pageSize;
            private int pageOffset;
            private int pageId;
            private String orderField;
            private boolean orderDirection;
            private String orderCondition;
            private int pageCount;
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

            public int getStartIndex() {
                return startIndex;
            }

            public void setStartIndex(int startIndex) {
                this.startIndex = startIndex;
            }

            public int getRowCount() {
                return rowCount;
            }

            public void setRowCount(int rowCount) {
                this.rowCount = rowCount;
            }

            public int getPageTail() {
                return pageTail;
            }

            public void setPageTail(int pageTail) {
                this.pageTail = pageTail;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getPageOffset() {
                return pageOffset;
            }

            public void setPageOffset(int pageOffset) {
                this.pageOffset = pageOffset;
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

            public boolean isOrderDirection() {
                return orderDirection;
            }

            public void setOrderDirection(boolean orderDirection) {
                this.orderDirection = orderDirection;
            }

            public String getOrderCondition() {
                return orderCondition;
            }

            public void setOrderCondition(String orderCondition) {
                this.orderCondition = orderCondition;
            }

            public int getPageCount() {
                return pageCount;
            }

            public void setPageCount(int pageCount) {
                this.pageCount = pageCount;
            }

            public List<?> getIndexs() {
                return indexs;
            }

            public void setIndexs(List<?> indexs) {
                this.indexs = indexs;
            }
        }

        public static class PjListBean {
            /**
             * state : null
             * log_url : null
             * parent_number : null
             * son_number : 1601
             * change_time : null
             * remarks : null
             * parent_name : null
             * dictionary_id : null
             * son_name : 谢谢买家的夸赞
             * create_time : null
             */

            private Object state;
            private Object log_url;
            private Object parent_number;
            private String son_number;
            private Object change_time;
            private Object remarks;
            private Object parent_name;
            private Object dictionary_id;
            private String son_name;
            private Object create_time;

            public Object getState() {
                return state;
            }

            public void setState(Object state) {
                this.state = state;
            }

            public Object getLog_url() {
                return log_url;
            }

            public void setLog_url(Object log_url) {
                this.log_url = log_url;
            }

            public Object getParent_number() {
                return parent_number;
            }

            public void setParent_number(Object parent_number) {
                this.parent_number = parent_number;
            }

            public String getSon_number() {
                return son_number;
            }

            public void setSon_number(String son_number) {
                this.son_number = son_number;
            }

            public Object getChange_time() {
                return change_time;
            }

            public void setChange_time(Object change_time) {
                this.change_time = change_time;
            }

            public Object getRemarks() {
                return remarks;
            }

            public void setRemarks(Object remarks) {
                this.remarks = remarks;
            }

            public Object getParent_name() {
                return parent_name;
            }

            public void setParent_name(Object parent_name) {
                this.parent_name = parent_name;
            }

            public Object getDictionary_id() {
                return dictionary_id;
            }

            public void setDictionary_id(Object dictionary_id) {
                this.dictionary_id = dictionary_id;
            }

            public String getSon_name() {
                return son_name;
            }

            public void setSon_name(String son_name) {
                this.son_name = son_name;
            }

            public Object getCreate_time() {
                return create_time;
            }

            public void setCreate_time(Object create_time) {
                this.create_time = create_time;
            }
        }
    }

    public static class PjListBeanX {
        /**
         * state : null
         * log_url : null
         * parent_number : null
         * son_number : 1502
         * change_time : null
         * remarks : null
         * parent_name : null
         * dictionary_id : null
         * son_name : 中评
         * create_time : null
         */

        private Object state;
        private Object log_url;
        private Object parent_number;
        private String son_number;
        private Object change_time;
        private Object remarks;
        private Object parent_name;
        private Object dictionary_id;
        private String son_name;
        private Object create_time;

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }

        public Object getLog_url() {
            return log_url;
        }

        public void setLog_url(Object log_url) {
            this.log_url = log_url;
        }

        public Object getParent_number() {
            return parent_number;
        }

        public void setParent_number(Object parent_number) {
            this.parent_number = parent_number;
        }

        public String getSon_number() {
            return son_number;
        }

        public void setSon_number(String son_number) {
            this.son_number = son_number;
        }

        public Object getChange_time() {
            return change_time;
        }

        public void setChange_time(Object change_time) {
            this.change_time = change_time;
        }

        public Object getRemarks() {
            return remarks;
        }

        public void setRemarks(Object remarks) {
            this.remarks = remarks;
        }

        public Object getParent_name() {
            return parent_name;
        }

        public void setParent_name(Object parent_name) {
            this.parent_name = parent_name;
        }

        public Object getDictionary_id() {
            return dictionary_id;
        }

        public void setDictionary_id(Object dictionary_id) {
            this.dictionary_id = dictionary_id;
        }

        public String getSon_name() {
            return son_name;
        }

        public void setSon_name(String son_name) {
            this.son_name = son_name;
        }

        public Object getCreate_time() {
            return create_time;
        }

        public void setCreate_time(Object create_time) {
            this.create_time = create_time;
        }
    }
}
