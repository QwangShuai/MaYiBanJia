package com.mingmen.mayi.mayibanjia.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/18/018.
 */

public class GWCShangPinBean {

        private List<DianpuBean> dianpu;
        private List<ShoppingBean> shopping;

        public List<DianpuBean> getDianpu() {
            return dianpu;
        }

        public void setDianpu(List<DianpuBean> dianpu) {
            this.dianpu = dianpu;
        }

        public List<ShoppingBean> getShopping() {
            return shopping;
        }

        public void setShopping(List<ShoppingBean> shopping) {
            this.shopping = shopping;
        }

        public static class DianpuBean {
            /**
             * type : null
             * number : 3
             * company_id : 21
             * account_id : 53f5bd4150e74c4097b9d5ecf65fea59
             * pice : null
             * user_token : null
             * shopping_id : 1
             * commodity_id : 32
             * company_name : 古色古香
             * pack_standard : 3
             * commodity_name : null
             * pack_standard_tree : null
             */

            private String type;
            private int number;
            private String company_id;
            private String account_id;
            private String pice;
            private String user_token;
            private String shopping_id;
            private String commodity_id;
            private String company_name;
            private String pack_standard;
            private String commodity_name;
            private String pack_standard_tree;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public String getCompany_id() {
                return company_id;
            }

            public void setCompany_id(String company_id) {
                this.company_id = company_id;
            }

            public String getAccount_id() {
                return account_id;
            }

            public void setAccount_id(String account_id) {
                this.account_id = account_id;
            }

            public String getPice() {
                return pice;
            }

            public void setPice(String pice) {
                this.pice = pice;
            }

            public String getUser_token() {
                return user_token;
            }

            public void setUser_token(String user_token) {
                this.user_token = user_token;
            }

            public String getShopping_id() {
                return shopping_id;
            }

            public void setShopping_id(String shopping_id) {
                this.shopping_id = shopping_id;
            }

            public String getCommodity_id() {
                return commodity_id;
            }

            public void setCommodity_id(String commodity_id) {
                this.commodity_id = commodity_id;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public String getPack_standard() {
                return pack_standard;
            }

            public void setPack_standard(String pack_standard) {
                this.pack_standard = pack_standard;
            }

            public String getCommodity_name() {
                return commodity_name;
            }

            public void setCommodity_name(String commodity_name) {
                this.commodity_name = commodity_name;
            }

            public String getPack_standard_tree() {
                return pack_standard_tree;
            }

            public void setPack_standard_tree(String pack_standard_tree) {
                this.pack_standard_tree = pack_standard_tree;
            }
        }

        public static class ShoppingBean {
            /**
             * type : null
             * number : 3
             * company_id : 21
             * account_id : null
             * pice : 15.0
             * user_token : null
             * shopping_id : 1
             * commodity_id : 32
             * company_name : 古色古香
             * pack_standard : null
             * commodity_name : 五花肉
             * pack_standard_tree : 斤
             */

            private String type;
            private int number;
            private String company_id;
            private String account_id;
            private String price;
            private String user_token;
            private String shopping_id;
            private String commodity_id;
            private String company_name;
            private String pack_standard;
            private String commodity_name;
            private String pack_standard_tree;
            private boolean isSelected=false;
            private String url;
            private String inventory;
            private String ration_one;// 起订量1
            private String ration_two;// 起订量2
            private String ration_three;// 起订量3
            private String pice_one;// 单价1
            private String pice_two;// 单价2
            private String pice_three;// 单价3
            private String son_number;
            private String city;
            private String market_name;
            private String type_tree_id;
            private String classify_name;

            public String getType_tree_id() {
                return type_tree_id;
            }

            public void setType_tree_id(String type_tree_id) {
                this.type_tree_id = type_tree_id;
            }

            public String getClassify_name() {
                return classify_name;
            }

            public void setClassify_name(String classify_name) {
                this.classify_name = classify_name;
            }

            public String getSon_number() {
                return son_number;
            }

            public void setSon_number(String son_number) {
                this.son_number = son_number;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getMarket_name() {
                return market_name;
            }

            public void setMarket_name(String market_name) {
                this.market_name = market_name;
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

            public String getInventory() {
                return inventory;
            }

            public void setInventory(String inventory) {
                this.inventory = inventory;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isSelected() {
                return isSelected;
            }

            public void setSelected(boolean selected) {
                isSelected = selected;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public String getCompany_id() {
                return company_id;
            }

            public void setCompany_id(String company_id) {
                this.company_id = company_id;
            }

            public String getAccount_id() {
                return account_id;
            }

            public void setAccount_id(String account_id) {
                this.account_id = account_id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String pice) {
                this.price = pice;
            }

            public String getUser_token() {
                return user_token;
            }

            public void setUser_token(String user_token) {
                this.user_token = user_token;
            }

            public String getShopping_id() {
                return shopping_id;
            }

            public void setShopping_id(String shopping_id) {
                this.shopping_id = shopping_id;
            }

            public String getCommodity_id() {
                return commodity_id;
            }

            public void setCommodity_id(String commodity_id) {
                this.commodity_id = commodity_id;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public String getPack_standard() {
                return pack_standard;
            }

            public void setPack_standard(String pack_standard) {
                this.pack_standard = pack_standard;
            }

            public String getCommodity_name() {
                return commodity_name;
            }

            public void setCommodity_name(String commodity_name) {
                this.commodity_name = commodity_name;
            }

            public String getPack_standard_tree() {
                return pack_standard_tree;
            }

            public void setPack_standard_tree(String pack_standard_tree) {
                this.pack_standard_tree = pack_standard_tree;
            }
        }
    }
