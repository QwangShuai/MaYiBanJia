package com.mingmen.mayi.mayibanjia.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2018/9/8.
 */

public class ShangPinSouSuoBean  {

        private List<ZhengchangBean> zhengchang;

        public List<ZhengchangBean> getZhengchang() {
            return zhengchang;
        }

        public void setZhengchang(List<ZhengchangBean> zhengchang) {
            this.zhengchang = zhengchang;
        }

        public static class ZhengchangBean {
            /**
             * number : 13
             * region : null
             * level : null
             * count : null
             * origin : null
             * company_name : 古色古香
             * specific_address : null
             * commodity_sales : 6
             * packStandard : null
             * commodity_id : 10808e90c78147ebb845161d89eba6b0
             * shopping_id : 3ff7965c347343b68d5ff8da45488463,427a8e1674d9421cb8cf37a31af239b1,599e6977848a472ab4bddb480bfc0f4c,8fe27b8441094d27aa5983be0a2af222,9fa104153e35469d95b8022b44df08c1
             * ration_three : 26
             * star_evaluation : null
             * son_order_id : null
             * quote_price_id : null
             * create_time : null
             * commodity_name : 牛肉
             * specTwoNum : null
             * goods : null
             * apply : null
             * proportion : null
             * user_token : null
             * company_id : null
             * province : null
             * son_number : null
             * city : null
             * acount : null
             * price : null
             * order_id : null
             * inventory : 10000
             * pice_two : 25
             * pice_one : 23
             * evaluate : null
             * ration_two : 25
             * pice_three : 26
             * ration_one : null
             * market_id : null
             * specOneNum : null
             * specFourNum : null
             * companyAddress : null
             * delivery_time : null
             * pack_standard_one_name : 斤
             * companyName : null
             * market_name : null
             * sumGoodsSales : null
             * approval_state : null
             * commodity_state : null
             * whether_ration : null
             * change_time : null
             * recommended_categories : null
             * pack_standard_four : null
             * deputyphoto : null
             * packTwoName : null
             * packOneName : null
             * packThreeName : null
             * packFourName : null
             * choose_specifications : null
             * picture_address : null
             * type_two_id : null
             * pack_standard_one : null
             * pack_standard_two : null
             * pack_standard_tree : null
             * hostPicture : null
             * specThreeNum : null
             * type_one_id : null
             * comend_address : null
             * type_tree_id : null
             * deputyPicture : null
             * companyPhoto : null
             * number_views : null
             * pice : null
             * dPicture : null
             * listsmall : null
             * hPicture : null
             * split_type : null
             * hostPath : null
             * file_path : null
             * listjg : null
             * shuliang : null
             * avgPrice : null
             * avgNum : null
             * creat_time : null
             * changer : null
             * hostphoto : 0c844d07f7ba4bf0b5aaa1a6928c440210.jpg
             * creater : null
             * small : null
             * big : null
             * sortOrder : null
             */

            private int number;
            private String region;
            private String level;
            private String count;
            private String origin;
            private String company_name;
            private String specific_address;
            private int commodity_sales;
            private String packStandard;
            private String commodity_id;
            private String shopping_id;
            private String ration_three;
            private String star_evaluation;
            private String son_order_id;
            private String quote_price_id;
            private String create_time;
            private String commodity_name;
            private String specTwoNum;
            private String goods;
            private String apply;
            private String proportion;
            private String user_token;
            private String company_id;
            private String province;
            private String son_number;
            private String city;
            private String acount;
            private String price;
            private String order_id;
            private String inventory;
            private int pice_two;
            private String pice_one;
            private String evaluate;
            private String ration_two;
            private int pice_three;
            private String ration_one;
            private String market_id;
            private String specOneNum;
            private String specFourNum;
            private String companyAddress;
            private String delivery_time;
            private String pack_standard_one_name;
            private String companyName;
            private String market_name;
            private String sumGoodsSales;
            private String approval_state;
            private String commodity_state;
            private String whether_ration;
            private String change_time;
            private String recommended_categories;
            private String pack_standard_four;
            private String deputyphoto;
            private String packTwoName;
            private String packOneName;
            private String packThreeName;
            private String packFourName;
            private String choose_specifications;
            private String picture_address;
            private String type_two_id;
            private String pack_standard_one;
            private String pack_standard_two;
            private String pack_standard_tree;
            private String hostPicture;
            private String specThreeNum;
            private String type_one_id;
            private String comend_address;
            private String type_tree_id;
            private String deputyPicture;
            private String companyPhoto;
            private String number_views;
            private String pice;
            private String dPicture;
            private String listsmall;
            private String hPicture;
            private String split_type;
            private String hostPath;
            private String file_path;
            private String listjg;
            private String shuliang;
            private String avgPrice;
            private String avgNum;
            private String creat_time;
            private String changer;
            private String hostphoto;
            private String creater;
            private String small;
            private String big;
            private String sortOrder;
            private String picture_url;
            private String classify_name;
            private String spec_describe;//规格描述
            private String real_time_state;//食时达
            private String type_four_id;//四级分类id

            public String getType_four_id() {
                return type_four_id;
            }

            public void setType_four_id(String type_four_id) {
                this.type_four_id = type_four_id;
            }

            private List<ListGG> listgg;

            public String getReal_time_state() {
                return real_time_state;
            }

            public void setReal_time_state(String real_time_state) {
                this.real_time_state = real_time_state;
            }

            public String getdPicture() {
                return dPicture;
            }

            public void setdPicture(String dPicture) {
                this.dPicture = dPicture;
            }

            public String gethPicture() {
                return hPicture;
            }

            public void sethPicture(String hPicture) {
                this.hPicture = hPicture;
            }

            public List<ListGG> getListgg() {
                return listgg;
            }

            public void setListgg(List<ListGG> listgg) {
                this.listgg = listgg;
            }

            public String getSpec_describe() {
                return spec_describe;
            }

            public void setSpec_describe(String spec_describe) {
                this.spec_describe = spec_describe;
            }

            public String getClassify_name() {
                return classify_name;
            }

            public void setClassify_name(String classify_name) {
                this.classify_name = classify_name;
            }

            public String getPicture_url() {
                return picture_url;
            }

            public void setPicture_url(String picture_url) {
                this.picture_url = picture_url;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getOrigin() {
                return origin;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public String getSpecific_address() {
                return specific_address;
            }

            public void setSpecific_address(String specific_address) {
                this.specific_address = specific_address;
            }

            public int getCommodity_sales() {
                return commodity_sales;
            }

            public void setCommodity_sales(int commodity_sales) {
                this.commodity_sales = commodity_sales;
            }

            public String getPackStandard() {
                return packStandard;
            }

            public void setPackStandard(String packStandard) {
                this.packStandard = packStandard;
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

            public String getRation_three() {
                return ration_three;
            }

            public void setRation_three(String ration_three) {
                this.ration_three = ration_three;
            }

            public String getStar_evaluation() {
                return star_evaluation;
            }

            public void setStar_evaluation(String star_evaluation) {
                this.star_evaluation = star_evaluation;
            }

            public String getSon_order_id() {
                return son_order_id;
            }

            public void setSon_order_id(String son_order_id) {
                this.son_order_id = son_order_id;
            }

            public String getQuote_price_id() {
                return quote_price_id;
            }

            public void setQuote_price_id(String quote_price_id) {
                this.quote_price_id = quote_price_id;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getCommodity_name() {
                return commodity_name;
            }

            public void setCommodity_name(String commodity_name) {
                this.commodity_name = commodity_name;
            }

            public String getSpecTwoNum() {
                return specTwoNum;
            }

            public void setSpecTwoNum(String specTwoNum) {
                this.specTwoNum = specTwoNum;
            }

            public String getGoods() {
                return goods;
            }

            public void setGoods(String goods) {
                this.goods = goods;
            }

            public String getApply() {
                return apply;
            }

            public void setApply(String apply) {
                this.apply = apply;
            }

            public String getProportion() {
                return proportion;
            }

            public void setProportion(String proportion) {
                this.proportion = proportion;
            }

            public String getUser_token() {
                return user_token;
            }

            public void setUser_token(String user_token) {
                this.user_token = user_token;
            }

            public String getCompany_id() {
                return company_id;
            }

            public void setCompany_id(String company_id) {
                this.company_id = company_id;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
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

            public String getAcount() {
                return acount;
            }

            public void setAcount(String acount) {
                this.acount = acount;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getInventory() {
                return inventory;
            }

            public void setInventory(String inventory) {
                this.inventory = inventory;
            }

            public int getPice_two() {
                return pice_two;
            }

            public void setPice_two(int pice_two) {
                this.pice_two = pice_two;
            }

            public String getPice_one() {
                return pice_one;
            }

            public void setPice_one(String pice_one) {
                this.pice_one = pice_one;
            }

            public String getEvaluate() {
                return evaluate;
            }

            public void setEvaluate(String evaluate) {
                this.evaluate = evaluate;
            }

            public String getRation_two() {
                return ration_two;
            }

            public void setRation_two(String ration_two) {
                this.ration_two = ration_two;
            }

            public int getPice_three() {
                return pice_three;
            }

            public void setPice_three(int pice_three) {
                this.pice_three = pice_three;
            }

            public String getRation_one() {
                return ration_one;
            }

            public void setRation_one(String ration_one) {
                this.ration_one = ration_one;
            }

            public String getMarket_id() {
                return market_id;
            }

            public void setMarket_id(String market_id) {
                this.market_id = market_id;
            }

            public String getSpecOneNum() {
                return specOneNum;
            }

            public void setSpecOneNum(String specOneNum) {
                this.specOneNum = specOneNum;
            }

            public String getSpecFourNum() {
                return specFourNum;
            }

            public void setSpecFourNum(String specFourNum) {
                this.specFourNum = specFourNum;
            }

            public String getCompanyAddress() {
                return companyAddress;
            }

            public void setCompanyAddress(String companyAddress) {
                this.companyAddress = companyAddress;
            }

            public String getDelivery_time() {
                return delivery_time;
            }

            public void setDelivery_time(String delivery_time) {
                this.delivery_time = delivery_time;
            }

            public String getPack_standard_one_name() {
                return pack_standard_one_name;
            }

            public void setPack_standard_one_name(String pack_standard_one_name) {
                this.pack_standard_one_name = pack_standard_one_name;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getMarket_name() {
                return market_name;
            }

            public void setMarket_name(String market_name) {
                this.market_name = market_name;
            }

            public String getSumGoodsSales() {
                return sumGoodsSales;
            }

            public void setSumGoodsSales(String sumGoodsSales) {
                this.sumGoodsSales = sumGoodsSales;
            }

            public String getApproval_state() {
                return approval_state;
            }

            public void setApproval_state(String approval_state) {
                this.approval_state = approval_state;
            }

            public String getCommodity_state() {
                return commodity_state;
            }

            public void setCommodity_state(String commodity_state) {
                this.commodity_state = commodity_state;
            }

            public String getWhether_ration() {
                return whether_ration;
            }

            public void setWhether_ration(String whether_ration) {
                this.whether_ration = whether_ration;
            }

            public String getChange_time() {
                return change_time;
            }

            public void setChange_time(String change_time) {
                this.change_time = change_time;
            }

            public String getRecommended_categories() {
                return recommended_categories;
            }

            public void setRecommended_categories(String recommended_categories) {
                this.recommended_categories = recommended_categories;
            }

            public String getPack_standard_four() {
                return pack_standard_four;
            }

            public void setPack_standard_four(String pack_standard_four) {
                this.pack_standard_four = pack_standard_four;
            }

            public String getDeputyphoto() {
                return deputyphoto;
            }

            public void setDeputyphoto(String deputyphoto) {
                this.deputyphoto = deputyphoto;
            }

            public String getPackTwoName() {
                return packTwoName;
            }

            public void setPackTwoName(String packTwoName) {
                this.packTwoName = packTwoName;
            }

            public String getPackOneName() {
                return packOneName;
            }

            public void setPackOneName(String packOneName) {
                this.packOneName = packOneName;
            }

            public String getPackThreeName() {
                return packThreeName;
            }

            public void setPackThreeName(String packThreeName) {
                this.packThreeName = packThreeName;
            }

            public String getPackFourName() {
                return packFourName;
            }

            public void setPackFourName(String packFourName) {
                this.packFourName = packFourName;
            }

            public String getChoose_specifications() {
                return choose_specifications;
            }

            public void setChoose_specifications(String choose_specifications) {
                this.choose_specifications = choose_specifications;
            }

            public String getPicture_address() {
                return picture_address;
            }

            public void setPicture_address(String picture_address) {
                this.picture_address = picture_address;
            }

            public String getType_two_id() {
                return type_two_id;
            }

            public void setType_two_id(String type_two_id) {
                this.type_two_id = type_two_id;
            }

            public String getPack_standard_one() {
                return pack_standard_one;
            }

            public void setPack_standard_one(String pack_standard_one) {
                this.pack_standard_one = pack_standard_one;
            }

            public String getPack_standard_two() {
                return pack_standard_two;
            }

            public void setPack_standard_two(String pack_standard_two) {
                this.pack_standard_two = pack_standard_two;
            }

            public String getPack_standard_tree() {
                return pack_standard_tree;
            }

            public void setPack_standard_tree(String pack_standard_tree) {
                this.pack_standard_tree = pack_standard_tree;
            }

            public String getHostPicture() {
                return hostPicture;
            }

            public void setHostPicture(String hostPicture) {
                this.hostPicture = hostPicture;
            }

            public String getSpecThreeNum() {
                return specThreeNum;
            }

            public void setSpecThreeNum(String specThreeNum) {
                this.specThreeNum = specThreeNum;
            }

            public String getType_one_id() {
                return type_one_id;
            }

            public void setType_one_id(String type_one_id) {
                this.type_one_id = type_one_id;
            }

            public String getComend_address() {
                return comend_address;
            }

            public void setComend_address(String comend_address) {
                this.comend_address = comend_address;
            }

            public String getType_tree_id() {
                return type_tree_id;
            }

            public void setType_tree_id(String type_tree_id) {
                this.type_tree_id = type_tree_id;
            }

            public String getDeputyPicture() {
                return deputyPicture;
            }

            public void setDeputyPicture(String deputyPicture) {
                this.deputyPicture = deputyPicture;
            }

            public String getCompanyPhoto() {
                return companyPhoto;
            }

            public void setCompanyPhoto(String companyPhoto) {
                this.companyPhoto = companyPhoto;
            }

            public String getNumber_views() {
                return number_views;
            }

            public void setNumber_views(String number_views) {
                this.number_views = number_views;
            }

            public String getPice() {
                return pice;
            }

            public void setPice(String pice) {
                this.pice = pice;
            }

            public String getDPicture() {
                return dPicture;
            }

            public void setDPicture(String dPicture) {
                this.dPicture = dPicture;
            }

            public String getListsmall() {
                return listsmall;
            }

            public void setListsmall(String listsmall) {
                this.listsmall = listsmall;
            }

            public String getHPicture() {
                return hPicture;
            }

            public void setHPicture(String hPicture) {
                this.hPicture = hPicture;
            }

            public String getSplit_type() {
                return split_type;
            }

            public void setSplit_type(String split_type) {
                this.split_type = split_type;
            }

            public String getHostPath() {
                return hostPath;
            }

            public void setHostPath(String hostPath) {
                this.hostPath = hostPath;
            }

            public String getFile_path() {
                return file_path;
            }

            public void setFile_path(String file_path) {
                this.file_path = file_path;
            }

            public String getListjg() {
                return listjg;
            }

            public void setListjg(String listjg) {
                this.listjg = listjg;
            }

            public String getShuliang() {
                return shuliang;
            }

            public void setShuliang(String shuliang) {
                this.shuliang = shuliang;
            }

            public String getAvgPrice() {
                return avgPrice;
            }

            public void setAvgPrice(String avgPrice) {
                this.avgPrice = avgPrice;
            }

            public String getAvgNum() {
                return avgNum;
            }

            public void setAvgNum(String avgNum) {
                this.avgNum = avgNum;
            }

            public String getCreat_time() {
                return creat_time;
            }

            public void setCreat_time(String creat_time) {
                this.creat_time = creat_time;
            }

            public String getChanger() {
                return changer;
            }

            public void setChanger(String changer) {
                this.changer = changer;
            }

            public String getHostphoto() {
                return hostphoto;
            }

            public void setHostphoto(String hostphoto) {
                this.hostphoto = hostphoto;
            }

            public String getCreater() {
                return creater;
            }

            public void setCreater(String creater) {
                this.creater = creater;
            }

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getBig() {
                return big;
            }

            public void setBig(String big) {
                this.big = big;
            }

            public String getSortOrder() {
                return sortOrder;
            }

            public void setSortOrder(String sortOrder) {
                this.sortOrder = sortOrder;
            }

            public static class ListGG{
                private String commodity_id;
                private String price;
                private String commodity_name;
                private String ration_one;
                private String inventory;
                private String company_id;
                private String picture_url;

                public String getPicture_url() {
                    return picture_url;
                }

                public void setPicture_url(String picture_url) {
                    this.picture_url = picture_url;
                }

                public String getCompany_id() {
                    return company_id;
                }

                public void setCompany_id(String company_id) {
                    this.company_id = company_id;
                }

                public String getCommodity_id() {
                    return commodity_id;
                }

                public void setCommodity_id(String commodity_id) {
                    this.commodity_id = commodity_id;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getCommodity_name() {
                    return commodity_name;
                }

                public void setCommodity_name(String commodity_name) {
                    this.commodity_name = commodity_name;
                }

                public String getRation_one() {
                    return ration_one;
                }

                public void setRation_one(String ration_one) {
                    this.ration_one = ration_one;
                }

                public String getInventory() {
                    return inventory;
                }

                public void setInventory(String inventory) {
                    this.inventory = inventory;
                }
            }
        }
}
