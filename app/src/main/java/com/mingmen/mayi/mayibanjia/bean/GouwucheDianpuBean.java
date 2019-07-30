package com.mingmen.mayi.mayibanjia.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/4/3.
 */

public class GouwucheDianpuBean {

    /**
     * commodity_id : 26e61989b07a430786e4e134770807a8
     * shopping_id : 1c7334ef517c4ffcaf844095bf8e7914
     * account_id : fafc38891b8a4a83a83889f697da69ad
     * company_id : 1e794e44445943e3a8aafd4a65576b9f
     * market_id : 1
     * classify_name : null
     * pack_standard_tree : null
     * user_token : null
     * price : null
     * goods : null
     * pice : null
     * pice_one : null
     * ration_one : null
     * ration_two : null
     * ration_three : null
     * pice_two : null
     * pice_three : null
     * inventory : null
     * pageNumber : null
     * picture_url : null
     * realtime : 0
     * type_four_id : null
     * type_tree_id : null
     * commodity_name : null
     * son_number : null
     * market_name : 哈达市场
     * url : null
     * company_name : 小小
     * city : null
     * commodity_state : null
     * real_time_state : null
     * splist : [{"commodity_id":"26e61989b07a430786e4e134770807a8","shopping_id":"1c7334ef517c4ffcaf844095bf8e7914","account_id":null,"company_id":"1e794e44445943e3a8aafd4a65576b9f","market_id":null,"classify_name":"[55]新盐酥鸡(120g/袋)","pack_standard_tree":"06099cc12074485da64dab7f5f63579e","user_token":null,"price":"50.00元/袋","goods":"0","pice":null,"pice_one":50,"ration_one":"5","ration_two":null,"ration_three":null,"pice_two":0,"pice_three":null,"inventory":"9989","pageNumber":null,"picture_url":"http://qiniu.canchengxiang.com/FgKsfIKAtOf2bzJQFH0XVxRATWVv?e=1554284443&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:Luqz-MkwJ_U_nOOvdNdCpCo9I5w=","realtime":null,"type_four_id":"188d16e9dd9e4da49f2c1172b6dba65d","type_tree_id":"18aa2103e0034f47a9bb4c00d24b0d9c","commodity_name":null,"son_number":"1","market_name":"哈达市场","url":"http://qiniu.canchengxiang.com/FgKsfIKAtOf2bzJQFH0XVxRATWVv?e=1554284443&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:Luqz-MkwJ_U_nOOvdNdCpCo9I5w=","company_name":"小小","city":"230100","commodity_state":"0","real_time_state":"0","splist":null,"pack_standard":"","specNameThree":"袋","specNameTwo":null,"type":null,"number":5}]
     * pack_standard :
     * specNameThree : null
     * specNameTwo : null
     * type : null
     * number : 5
     */

    private String commodity_id;
    private String shopping_id;
    private String account_id;
    private String company_id;
    private String market_id;
    private String realtime;
    private String market_name;
    private String company_name;
    private String pack_standard;
    private int number;
    private boolean isSelect;

    private String gold_supplier;//金牌
    private String profile_enterprise;//知名企业
    private String approval_state;//认证

    public String getGold_supplier() {
        return gold_supplier;
    }

    public void setGold_supplier(String gold_supplier) {
        this.gold_supplier = gold_supplier;
    }

    public String getProfile_enterprise() {
        return profile_enterprise;
    }

    public void setProfile_enterprise(String profile_enterprise) {
        this.profile_enterprise = profile_enterprise;
    }

    public String getApproval_state() {
        return approval_state;
    }

    public void setApproval_state(String approval_state) {
        this.approval_state = approval_state;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    private List<SplistBean> splist;

    public String getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(String commodity_id) {
        this.commodity_id = commodity_id;
    }

    public String getShopping_id() {
        return shopping_id;
    }

    public void setShopping_id(String shopping_id) {
        this.shopping_id = shopping_id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getMarket_id() {
        return market_id;
    }

    public void setMarket_id(String market_id) {
        this.market_id = market_id;
    }

    public String getRealtime() {
        return realtime;
    }

    public void setRealtime(String realtime) {
        this.realtime = realtime;
    }

    public String getMarket_name() {
        return market_name;
    }

    public void setMarket_name(String market_name) {
        this.market_name = market_name;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<SplistBean> getSplist() {
        return splist;
    }

    public void setSplist(List<SplistBean> splist) {
        this.splist = splist;
    }

    public static class SplistBean {
        /**
         * commodity_id : 26e61989b07a430786e4e134770807a8
         * shopping_id : 1c7334ef517c4ffcaf844095bf8e7914
         * account_id : null
         * company_id : 1e794e44445943e3a8aafd4a65576b9f
         * market_id : null
         * classify_name : [55]新盐酥鸡(120g/袋)
         * pack_standard_tree : 06099cc12074485da64dab7f5f63579e
         * user_token : null
         * price : 50.00元/袋
         * goods : 0
         * pice : null
         * pice_one : 50.0
         * ration_one : 5
         * ration_two : null
         * ration_three : null
         * pice_two : 0.0
         * pice_three : null
         * inventory : 9989
         * pageNumber : null
         * picture_url : http://qiniu.canchengxiang.com/FgKsfIKAtOf2bzJQFH0XVxRATWVv?e=1554284443&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:Luqz-MkwJ_U_nOOvdNdCpCo9I5w=
         * realtime : null
         * type_four_id : 188d16e9dd9e4da49f2c1172b6dba65d
         * type_tree_id : 18aa2103e0034f47a9bb4c00d24b0d9c
         * commodity_name : null
         * son_number : 1
         * market_name : 哈达市场
         * url : http://qiniu.canchengxiang.com/FgKsfIKAtOf2bzJQFH0XVxRATWVv?e=1554284443&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:Luqz-MkwJ_U_nOOvdNdCpCo9I5w=
         * company_name : 小小
         * city : 230100
         * commodity_state : 0
         * real_time_state : 0
         * splist : null
         * pack_standard :
         * specNameThree : 袋
         * specNameTwo : null
         * type : null
         * number : 5
         */

        private String commodity_id;
        private String shopping_id;
        private String company_id;
        private String classify_name;
        private String pack_standard_tree;
        private String price;
        private String goods;
        private double pice_one;
        private String ration_one;
        private double pice_two;
        private String inventory;
        private String picture_url;
        private String type_four_id;
        private String type_tree_id;
        private String son_number;
        private String market_name;
        private String url;
        private String company_name;
        private String city;
        private String commodity_state;
        private String real_time_state;
        private String pack_standard;
        private String specNameThree;
        private int number;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getCommodity_id() {
            return commodity_id;
        }

        public void setCommodity_id(String commodity_id) {
            this.commodity_id = commodity_id;
        }

        public String getShopping_id() {
            return shopping_id;
        }

        public void setShopping_id(String shopping_id) {
            this.shopping_id = shopping_id;
        }

        public String getCompany_id() {
            return company_id;
        }

        public void setCompany_id(String company_id) {
            this.company_id = company_id;
        }

        public String getClassify_name() {
            return classify_name;
        }

        public void setClassify_name(String classify_name) {
            this.classify_name = classify_name;
        }

        public String getPack_standard_tree() {
            return pack_standard_tree;
        }

        public void setPack_standard_tree(String pack_standard_tree) {
            this.pack_standard_tree = pack_standard_tree;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getGoods() {
            return goods;
        }

        public void setGoods(String goods) {
            this.goods = goods;
        }

        public double getPice_one() {
            return pice_one;
        }

        public void setPice_one(double pice_one) {
            this.pice_one = pice_one;
        }

        public String getRation_one() {
            return ration_one;
        }

        public void setRation_one(String ration_one) {
            this.ration_one = ration_one;
        }

        public double getPice_two() {
            return pice_two;
        }

        public void setPice_two(double pice_two) {
            this.pice_two = pice_two;
        }

        public String getInventory() {
            return inventory;
        }

        public void setInventory(String inventory) {
            this.inventory = inventory;
        }

        public String getPicture_url() {
            return picture_url;
        }

        public void setPicture_url(String picture_url) {
            this.picture_url = picture_url;
        }

        public String getType_four_id() {
            return type_four_id;
        }

        public void setType_four_id(String type_four_id) {
            this.type_four_id = type_four_id;
        }

        public String getType_tree_id() {
            return type_tree_id;
        }

        public void setType_tree_id(String type_tree_id) {
            this.type_tree_id = type_tree_id;
        }

        public String getSon_number() {
            return son_number;
        }

        public void setSon_number(String son_number) {
            this.son_number = son_number;
        }

        public String getMarket_name() {
            return market_name;
        }

        public void setMarket_name(String market_name) {
            this.market_name = market_name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCommodity_state() {
            return commodity_state;
        }

        public void setCommodity_state(String commodity_state) {
            this.commodity_state = commodity_state;
        }

        public String getReal_time_state() {
            return real_time_state;
        }

        public void setReal_time_state(String real_time_state) {
            this.real_time_state = real_time_state;
        }

        public String getPack_standard() {
            return pack_standard;
        }

        public void setPack_standard(String pack_standard) {
            this.pack_standard = pack_standard;
        }

        public String getSpecNameThree() {
            return specNameThree;
        }

        public void setSpecNameThree(String specNameThree) {
            this.specNameThree = specNameThree;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }
}
