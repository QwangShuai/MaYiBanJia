package com.mingmen.mayi.mayibanjia.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/1/28.
 */

public class ChangYongBean {
    private String often_name_id;
    private String classify_id;
    private String classify_name;
    private boolean isShow;
    private List<ListBean> list;

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public String getOften_name_id() {
        return often_name_id;
    }

    public void setOften_name_id(String often_name_id) {
        this.often_name_id = often_name_id;
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

    public static class ListBean{
        private String sort_id;
        private String classify_name;
        private String classify_id;
        private String pack_standard_id;
        private String often_name_id;
        private boolean isShow;
        private boolean isSelect;


        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getSort_id() {
            return sort_id;
        }

        public void setSort_id(String sort_id) {
            this.sort_id = sort_id;
        }

        public String getClassify_name() {
            return classify_name;
        }

        public void setClassify_name(String classify_name) {
            this.classify_name = classify_name;
        }

        public String getPack_standard_id() {
            return pack_standard_id;
        }

        public void setPack_standard_id(String pack_standard_id) {
            this.pack_standard_id = pack_standard_id;
        }

        public String getClassify_id() {
            return classify_id;
        }

        public void setClassify_id(String classify_id) {
            this.classify_id = classify_id;
        }

        public boolean isShow() {
            return isShow;
        }

        public void setShow(boolean show) {
            isShow = show;
        }

        public String getOften_name_id() {
            return often_name_id;
        }

        public void setOften_name_id(String often_name_id) {
            this.often_name_id = often_name_id;
        }
    }
}
