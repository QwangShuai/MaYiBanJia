package com.mingmen.mayi.mayibanjia.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/9/1.
 */

public class AllShiChangBean {
        private List<Bean> threeList;
        private List<Bean> oneList;
        private List<Bean> twoList;

        public List<Bean> getThreeList() {
            return threeList;
        }

        public void setThreeList(List<Bean> threeList) {
            this.threeList = threeList;
        }

        public List<Bean> getOneList() {
            return oneList;
        }

        public void setOneList(List<Bean> oneList) {
            this.oneList = oneList;
        }

        public List<Bean> getTwoList() {
            return twoList;
        }

        public void setTwoList(List<Bean> twoList) {
            this.twoList = twoList;
        }

        public static class Bean {
            /**
             * state : null
             * region : 230103
             * update_time : null
             * create_time : null
             * classify_id : 15f71aee85c74f2fb209de2d3f77317c,343ad67bee889f873b45df78989cc870
             * create_by : null
             * levelState : null
             * update_by : null
             * mark_id : 1
             * specific_address : 南岗区哈南205号
             * parent_number : null
             * market_name : 哈达市场
             * city : 230100
             * province : 230000
             */

            private String region;
            private String classify_id;
            private String mark_id;
            private String specific_address;
            private String market_name;
            private String city;
            private String province;


            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }


            public void setUpdate_time(Object update_time) {
            }



            public String getClassify_id() {
                return classify_id;
            }

            public void setClassify_id(String classify_id) {
                this.classify_id = classify_id;
            }



            public String getMark_id() {
                return mark_id;
            }

            public void setMark_id(String mark_id) {
                this.mark_id = mark_id;
            }

            public String getSpecific_address() {
                return specific_address;
            }

            public void setSpecific_address(String specific_address) {
                this.specific_address = specific_address;
            }

            public String getMarket_name() {
                return market_name;
            }

            public void setMarket_name(String market_name) {
                this.market_name = market_name;
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
        }

      
}
