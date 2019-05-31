package com.mingmen.mayi.mayibanjia.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/10/17.
 */

public class EditorShangPinBean implements Serializable{

    /**
     * xq : {"user_token":null,"packStandard":null,"packOneName":"斤","packTwoName":"g","packThreeName":"L","packFourName":"","specOneNum":1,"commodity_name":null,"picture_address":null,"specTwoNum":2,"specThreeNum":3,"price":null,"choose_specifications":1,"pice_one":12,"company_id":"22","ration_one":"12","ration_two":"13","pice_two":11,"ration_three":"14","pice_three":10,"number_views":null,"commodity_id":"54565595db574c6e80c8525cef90af59","comend_address":null,"sortOrder":null,"pageNumber":null,"evaluate":null,"shopping_id":null,"son_order_id":null,"company_name":null,"province":null,"city":null,"specific_address":null,"picture_url":null,"type_tree_id":"f39ee1526d194adabb800c77c400028b","bigCommodity_id":null,"smallCommodity_id":null,"collect_id":null,"hostphoto":null,"approval_state":null,"hostPicture":"http://pbn4jedp4.bkt.clouddn.com/eb7b6a3e3437470291d399a7f48c1b3d?e=1539658405&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:QRzAXq7-FmGR7_FnVnnzR_qt6P4=","pack_standard_tree":"48c795cbd3184b348a8ce7c618d4eca5","goods":"0","inventory":"10000","type_one_id":"343ad67bee889f873b45df78989cc870","apply":null,"proportion":null,"deputyPicture":"83ad4c68af8f456d8fa2956b3c07d636,f1cd5d54d11a411d9c0c3b6de22dd1d6","companyPhoto":null,"market_id":null,"companyAddress":null,"order_id":null,"type_two_id":"ca769ca97a3545b8803b8525d722c4d6","small":null,"son_number":null,"big":null,"file_path":null,"quote_price_id":null,"create_time":null,"acount":null,"commodity_sales":300,"spec_name":null,"pack_standard_one":"fd679c0559ef468e871710ba00c5da9f","pack_standard_two":"8cc245bd9d1541f58dbc71a8d1ac4016","total_weight":null,"classify_name":"牛肉","provName":null,"citName":null,"countName":null,"listsmall":null,"listjg":null,"star_evaluation":null,"shuliang":null,"pack_standard_one_name":null,"avgPrice":null,"sumGoodsSales":null,"deputyphoto":null,"hostPath":null,"pice":null,"avgNum":null,"hpicture":["http://pbn4jedp4.bkt.clouddn.com/eb7b6a3e3437470291d399a7f48c1b3d?e=1539658405&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:QRzAXq7-FmGR7_FnVnnzR_qt6P4="],"dpicture":["http://pbn4jedp4.bkt.clouddn.com/83ad4c68af8f456d8fa2956b3c07d636?e=1539658405&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:oW3XW6sHXKfiAUNY2DHDRYcIa1c=","http://pbn4jedp4.bkt.clouddn.com/f1cd5d54d11a411d9c0c3b6de22dd1d6?e=1539658405&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:qBTBTCQmO8FMDZAoA571BMsdhEE="],"companyName":null,"commodity_state":null,"whether_ration":null,"split_type":null,"creat_time":null,"creater":null,"change_time":null,"changer":null,"delivery_time":null,"recommended_categories":null,"specFourNum":0,"pack_standard_four":"","market_name":null,"pack_standard_tree_name":null,"level":null,"origin":null,"count":null,"number":null,"region":null}
     * parameteList : [{"commodity_id":"54565595db574c6e80c8525cef90af59","create_time":null,"paramete_name_id":"适用","paramete_id":null,"paramete_content":"刚回"},{"commodity_id":"54565595db574c6e80c8525cef90af59","create_time":null,"paramete_name_id":"比例","paramete_id":null,"paramete_content":"古"},{"commodity_id":"54565595db574c6e80c8525cef90af59","create_time":null,"paramete_name_id":"产地","paramete_id":null,"paramete_content":"12"},{"commodity_id":"54565595db574c6e80c8525cef90af59","create_time":null,"paramete_name_id":"等级","paramete_id":null,"paramete_content":"他也"}]
     */

    private XqBean xq;
    private List<ParameteListBean> parameteList;

    public XqBean getXq() {
        return xq;
    }

    public void setXq(XqBean xq) {
        this.xq = xq;
    }

    public List<ParameteListBean> getParameteList() {
        return parameteList;
    }

    public void setParameteList(List<ParameteListBean> parameteList) {
        this.parameteList = parameteList;
    }

    public static class XqBean {
        /**
         * user_token : null
         * packStandard : null
         * packOneName : 斤
         * packTwoName : g
         * packThreeName : L
         * packFourName :
         * specOneNum : 1
         * commodity_name : null
         * picture_address : null
         * specTwoNum : 2
         * specThreeNum : 3
         * price : null
         * choose_specifications : 1
         * pice_one : 12
         * company_id : 22
         * ration_one : 12
         * ration_two : 13
         * pice_two : 11
         * ration_three : 14
         * pice_three : 10
         * number_views : null
         * commodity_id : 54565595db574c6e80c8525cef90af59
         * comend_address : null
         * sortOrder : null
         * pageNumber : null
         * evaluate : null
         * shopping_id : null
         * son_order_id : null
         * company_name : null
         * province : null
         * city : null
         * specific_address : null
         * picture_url : null
         * type_tree_id : f39ee1526d194adabb800c77c400028b
         * bigCommodity_id : null
         * smallCommodity_id : null
         * collect_id : null
         * hostphoto : null
         * approval_state : null
         * hostPicture : http://pbn4jedp4.bkt.clouddn.com/eb7b6a3e3437470291d399a7f48c1b3d?e=1539658405&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:QRzAXq7-FmGR7_FnVnnzR_qt6P4=
         * pack_standard_tree : 48c795cbd3184b348a8ce7c618d4eca5
         * goods : 0
         * inventory : 10000
         * type_one_id : 343ad67bee889f873b45df78989cc870
         * apply : null
         * proportion : null
         * deputyPicture : 83ad4c68af8f456d8fa2956b3c07d636,f1cd5d54d11a411d9c0c3b6de22dd1d6
         * companyPhoto : null
         * market_id : null
         * companyAddress : null
         * order_id : null
         * type_two_id : ca769ca97a3545b8803b8525d722c4d6
         * small : null
         * son_number : null
         * big : null
         * file_path : null
         * quote_price_id : null
         * create_time : null
         * acount : null
         * commodity_sales : 300
         * spec_name : null
         * pack_standard_one : fd679c0559ef468e871710ba00c5da9f
         * pack_standard_two : 8cc245bd9d1541f58dbc71a8d1ac4016
         * total_weight : null
         * classify_name : 牛肉
         * provName : null
         * citName : null
         * countName : null
         * listsmall : null
         * listjg : null
         * star_evaluation : null
         * shuliang : null
         * pack_standard_one_name : null
         * avgPrice : null
         * sumGoodsSales : null
         * deputyphoto : null
         * hostPath : null
         * pice : null
         * avgNum : null
         * hpicture : ["http://pbn4jedp4.bkt.clouddn.com/eb7b6a3e3437470291d399a7f48c1b3d?e=1539658405&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:QRzAXq7-FmGR7_FnVnnzR_qt6P4="]
         * dpicture : ["http://pbn4jedp4.bkt.clouddn.com/83ad4c68af8f456d8fa2956b3c07d636?e=1539658405&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:oW3XW6sHXKfiAUNY2DHDRYcIa1c=","http://pbn4jedp4.bkt.clouddn.com/f1cd5d54d11a411d9c0c3b6de22dd1d6?e=1539658405&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:qBTBTCQmO8FMDZAoA571BMsdhEE="]
         * companyName : null
         * commodity_state : null
         * whether_ration : null
         * split_type : null
         * creat_time : null
         * creater : null
         * change_time : null
         * changer : null
         * delivery_time : null
         * recommended_categories : null
         * specFourNum : 0
         * pack_standard_four :
         * market_name : null
         * pack_standard_tree_name : null
         * level : null
         * origin : null
         * count : null
         * number : null
         * region : null
         */

        private Object user_token;
        private Object packStandard;
        private String packOneName;
        private String packTwoName;
        private String packThreeName;
        private String packFourName;
        private int specOneNum;
        private String commodity_name;
        private Object picture_address;
        private int specTwoNum;
        private int specThreeNum;
        private double price;
        private int choose_specifications;
        private double pice_one;
        private String company_id;
        private String ration_one;
        private String ration_two;
        private int pice_two;
        private String ration_three;
        private int pice_three;
        private Object number_views;
        private String commodity_id;
        private Object comend_address;
        private Object sortOrder;
        private Object pageNumber;
        private Object evaluate;
        private Object shopping_id;
        private Object son_order_id;
        private Object company_name;
        private Object province;
        private Object city;
        private Object specific_address;
        private Object picture_url;
        private String type_tree_id;
        private Object bigCommodity_id;
        private Object smallCommodity_id;
        private Object collect_id;
        private Object hostphoto;
        private Object approval_state;
        private String hostPicture;
        private String pack_standard_tree;
        private String goods;
        private String inventory;
        private String type_one_id;
        private Object apply;
        private Object proportion;
        private String deputyPicture;
        private Object companyPhoto;
        private Object market_id;
        private Object companyAddress;
        private Object order_id;
        private String type_two_id;
        private Object small;
        private Object son_number;
        private Object big;
        private Object file_path;
        private Object quote_price_id;
        private Object create_time;
        private Object acount;
        private int commodity_sales;
        private String spec_name;
        private String pack_standard_one;
        private String pack_standard_two;
        private Object total_weight;
        private String classify_name;
        private Object provName;
        private Object citName;
        private Object countName;
        private Object listsmall;
        private Object listjg;
        private Object star_evaluation;
        private Object shuliang;
        private Object pack_standard_one_name;
        private Object avgPrice;
        private Object sumGoodsSales;
        private Object deputyphoto;
        private Object hostPath;
        private double pice;
        private Object avgNum;
        private Object companyName;
        private Object commodity_state;
        private Object whether_ration;
        private Object split_type;
        private Object creat_time;
        private Object creater;
        private Object change_time;
        private Object changer;
        private Object delivery_time;
        private Object recommended_categories;
        private int specFourNum;
        private String pack_standard_four;
        private Object market_name;
        private String pack_standard_tree_name;
        private Object level;
        private Object origin;
        private Object count;
        private Object number;
        private Object region;
        private List<String> ftPicture;//副图图片名集合
        private List<String> hpicture;
        private List<String> dpicture;
        private String spec_describe;//描述
        private String type_one_name;//1级到3级展示
        private String affiliated_number;//数量
        private String affiliated_spec;//ID
        private String type_four_id;
        private String brand;
        private String spms;

        public String getSpms() {
            return spms;
        }

        public void setSpms(String spms) {
            this.spms = spms;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getType_four_id() {
            return type_four_id;
        }

        public void setType_four_id(String type_four_id) {
            this.type_four_id = type_four_id;
        }

        public String getAffiliated_spec() {
            return affiliated_spec;
        }

        public void setAffiliated_spec(String affiliated_spec) {
            this.affiliated_spec = affiliated_spec;
        }

        public String getType_one_name() {
            return type_one_name;
        }

        public void setType_one_name(String type_one_name) {
            this.type_one_name = type_one_name;
        }

        public String getAffiliated_number() {
            return affiliated_number;
        }

        public void setAffiliated_number(String affiliated_number) {
            this.affiliated_number = affiliated_number;
        }

        public String getSpec_describe() {
            return spec_describe;
        }

        public void setSpec_describe(String spec_describe) {
            this.spec_describe = spec_describe;
        }

        public List<String> getFtPicture() {
            return ftPicture;
        }

        public void setFtPicture(List<String> ftPicture) {
            this.ftPicture = ftPicture;
        }

        public Object getUser_token() {
            return user_token;
        }

        public void setUser_token(Object user_token) {
            this.user_token = user_token;
        }

        public Object getPackStandard() {
            return packStandard;
        }

        public void setPackStandard(Object packStandard) {
            this.packStandard = packStandard;
        }

        public String getPackOneName() {
            return packOneName;
        }

        public void setPackOneName(String packOneName) {
            this.packOneName = packOneName;
        }

        public String getPackTwoName() {
            return packTwoName;
        }

        public void setPackTwoName(String packTwoName) {
            this.packTwoName = packTwoName;
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

        public int getSpecOneNum() {
            return specOneNum;
        }

        public void setSpecOneNum(int specOneNum) {
            this.specOneNum = specOneNum;
        }

        public String getCommodity_name() {
            return commodity_name;
        }

        public void setCommodity_name(String commodity_name) {
            this.commodity_name = commodity_name;
        }

        public Object getPicture_address() {
            return picture_address;
        }

        public void setPicture_address(Object picture_address) {
            this.picture_address = picture_address;
        }

        public int getSpecTwoNum() {
            return specTwoNum;
        }

        public void setSpecTwoNum(int specTwoNum) {
            this.specTwoNum = specTwoNum;
        }

        public int getSpecThreeNum() {
            return specThreeNum;
        }

        public void setSpecThreeNum(int specThreeNum) {
            this.specThreeNum = specThreeNum;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getChoose_specifications() {
            return choose_specifications;
        }

        public void setChoose_specifications(int choose_specifications) {
            this.choose_specifications = choose_specifications;
        }

        public double getPice_one() {
            return pice_one;
        }

        public void setPice_one(double pice_one) {
            this.pice_one = pice_one;
        }

        public String getCompany_id() {
            return company_id;
        }

        public void setCompany_id(String company_id) {
            this.company_id = company_id;
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

        public int getPice_two() {
            return pice_two;
        }

        public void setPice_two(int pice_two) {
            this.pice_two = pice_two;
        }

        public String getRation_three() {
            return ration_three;
        }

        public void setRation_three(String ration_three) {
            this.ration_three = ration_three;
        }

        public int getPice_three() {
            return pice_three;
        }

        public void setPice_three(int pice_three) {
            this.pice_three = pice_three;
        }

        public Object getNumber_views() {
            return number_views;
        }

        public void setNumber_views(Object number_views) {
            this.number_views = number_views;
        }

        public String getCommodity_id() {
            return commodity_id;
        }

        public void setCommodity_id(String commodity_id) {
            this.commodity_id = commodity_id;
        }

        public Object getComend_address() {
            return comend_address;
        }

        public void setComend_address(Object comend_address) {
            this.comend_address = comend_address;
        }

        public Object getSortOrder() {
            return sortOrder;
        }

        public void setSortOrder(Object sortOrder) {
            this.sortOrder = sortOrder;
        }

        public Object getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(Object pageNumber) {
            this.pageNumber = pageNumber;
        }

        public Object getEvaluate() {
            return evaluate;
        }

        public void setEvaluate(Object evaluate) {
            this.evaluate = evaluate;
        }

        public Object getShopping_id() {
            return shopping_id;
        }

        public void setShopping_id(Object shopping_id) {
            this.shopping_id = shopping_id;
        }

        public Object getSon_order_id() {
            return son_order_id;
        }

        public void setSon_order_id(Object son_order_id) {
            this.son_order_id = son_order_id;
        }

        public Object getCompany_name() {
            return company_name;
        }

        public void setCompany_name(Object company_name) {
            this.company_name = company_name;
        }

        public Object getProvince() {
            return province;
        }

        public void setProvince(Object province) {
            this.province = province;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public Object getSpecific_address() {
            return specific_address;
        }

        public void setSpecific_address(Object specific_address) {
            this.specific_address = specific_address;
        }

        public Object getPicture_url() {
            return picture_url;
        }

        public void setPicture_url(Object picture_url) {
            this.picture_url = picture_url;
        }

        public String getType_tree_id() {
            return type_tree_id;
        }

        public void setType_tree_id(String type_tree_id) {
            this.type_tree_id = type_tree_id;
        }

        public Object getBigCommodity_id() {
            return bigCommodity_id;
        }

        public void setBigCommodity_id(Object bigCommodity_id) {
            this.bigCommodity_id = bigCommodity_id;
        }

        public Object getSmallCommodity_id() {
            return smallCommodity_id;
        }

        public void setSmallCommodity_id(Object smallCommodity_id) {
            this.smallCommodity_id = smallCommodity_id;
        }

        public Object getCollect_id() {
            return collect_id;
        }

        public void setCollect_id(Object collect_id) {
            this.collect_id = collect_id;
        }

        public Object getHostphoto() {
            return hostphoto;
        }

        public void setHostphoto(Object hostphoto) {
            this.hostphoto = hostphoto;
        }

        public Object getApproval_state() {
            return approval_state;
        }

        public void setApproval_state(Object approval_state) {
            this.approval_state = approval_state;
        }

        public String getHostPicture() {
            return hostPicture;
        }

        public void setHostPicture(String hostPicture) {
            this.hostPicture = hostPicture;
        }

        public String getPack_standard_tree() {
            return pack_standard_tree;
        }

        public void setPack_standard_tree(String pack_standard_tree) {
            this.pack_standard_tree = pack_standard_tree;
        }

        public String getGoods() {
            return goods;
        }

        public void setGoods(String goods) {
            this.goods = goods;
        }

        public String getInventory() {
            return inventory;
        }

        public void setInventory(String inventory) {
            this.inventory = inventory;
        }

        public String getType_one_id() {
            return type_one_id;
        }

        public void setType_one_id(String type_one_id) {
            this.type_one_id = type_one_id;
        }

        public Object getApply() {
            return apply;
        }

        public void setApply(Object apply) {
            this.apply = apply;
        }

        public Object getProportion() {
            return proportion;
        }

        public void setProportion(Object proportion) {
            this.proportion = proportion;
        }

        public String getDeputyPicture() {
            return deputyPicture;
        }

        public void setDeputyPicture(String deputyPicture) {
            this.deputyPicture = deputyPicture;
        }

        public Object getCompanyPhoto() {
            return companyPhoto;
        }

        public void setCompanyPhoto(Object companyPhoto) {
            this.companyPhoto = companyPhoto;
        }

        public Object getMarket_id() {
            return market_id;
        }

        public void setMarket_id(Object market_id) {
            this.market_id = market_id;
        }

        public Object getCompanyAddress() {
            return companyAddress;
        }

        public void setCompanyAddress(Object companyAddress) {
            this.companyAddress = companyAddress;
        }

        public Object getOrder_id() {
            return order_id;
        }

        public void setOrder_id(Object order_id) {
            this.order_id = order_id;
        }

        public String getType_two_id() {
            return type_two_id;
        }

        public void setType_two_id(String type_two_id) {
            this.type_two_id = type_two_id;
        }

        public Object getSmall() {
            return small;
        }

        public void setSmall(Object small) {
            this.small = small;
        }

        public Object getSon_number() {
            return son_number;
        }

        public void setSon_number(Object son_number) {
            this.son_number = son_number;
        }

        public Object getBig() {
            return big;
        }

        public void setBig(Object big) {
            this.big = big;
        }

        public Object getFile_path() {
            return file_path;
        }

        public void setFile_path(Object file_path) {
            this.file_path = file_path;
        }

        public Object getQuote_price_id() {
            return quote_price_id;
        }

        public void setQuote_price_id(Object quote_price_id) {
            this.quote_price_id = quote_price_id;
        }

        public Object getCreate_time() {
            return create_time;
        }

        public void setCreate_time(Object create_time) {
            this.create_time = create_time;
        }

        public Object getAcount() {
            return acount;
        }

        public void setAcount(Object acount) {
            this.acount = acount;
        }

        public int getCommodity_sales() {
            return commodity_sales;
        }

        public void setCommodity_sales(int commodity_sales) {
            this.commodity_sales = commodity_sales;
        }

        public String getSpec_name() {
            return spec_name;
        }

        public void setSpec_name(String spec_name) {
            this.spec_name = spec_name;
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

        public Object getTotal_weight() {
            return total_weight;
        }

        public void setTotal_weight(Object total_weight) {
            this.total_weight = total_weight;
        }

        public String getClassify_name() {
            return classify_name;
        }

        public void setClassify_name(String classify_name) {
            this.classify_name = classify_name;
        }

        public Object getProvName() {
            return provName;
        }

        public void setProvName(Object provName) {
            this.provName = provName;
        }

        public Object getCitName() {
            return citName;
        }

        public void setCitName(Object citName) {
            this.citName = citName;
        }

        public Object getCountName() {
            return countName;
        }

        public void setCountName(Object countName) {
            this.countName = countName;
        }

        public Object getListsmall() {
            return listsmall;
        }

        public void setListsmall(Object listsmall) {
            this.listsmall = listsmall;
        }

        public Object getListjg() {
            return listjg;
        }

        public void setListjg(Object listjg) {
            this.listjg = listjg;
        }

        public Object getStar_evaluation() {
            return star_evaluation;
        }

        public void setStar_evaluation(Object star_evaluation) {
            this.star_evaluation = star_evaluation;
        }

        public Object getShuliang() {
            return shuliang;
        }

        public void setShuliang(Object shuliang) {
            this.shuliang = shuliang;
        }

        public Object getPack_standard_one_name() {
            return pack_standard_one_name;
        }

        public void setPack_standard_one_name(Object pack_standard_one_name) {
            this.pack_standard_one_name = pack_standard_one_name;
        }

        public Object getAvgPrice() {
            return avgPrice;
        }

        public void setAvgPrice(Object avgPrice) {
            this.avgPrice = avgPrice;
        }

        public Object getSumGoodsSales() {
            return sumGoodsSales;
        }

        public void setSumGoodsSales(Object sumGoodsSales) {
            this.sumGoodsSales = sumGoodsSales;
        }

        public Object getDeputyphoto() {
            return deputyphoto;
        }

        public void setDeputyphoto(Object deputyphoto) {
            this.deputyphoto = deputyphoto;
        }

        public Object getHostPath() {
            return hostPath;
        }

        public void setHostPath(Object hostPath) {
            this.hostPath = hostPath;
        }

        public double getPice() {
            return pice;
        }

        public void setPice(double pice) {
            this.pice = pice;
        }

        public Object getAvgNum() {
            return avgNum;
        }

        public void setAvgNum(Object avgNum) {
            this.avgNum = avgNum;
        }

        public Object getCompanyName() {
            return companyName;
        }

        public void setCompanyName(Object companyName) {
            this.companyName = companyName;
        }

        public Object getCommodity_state() {
            return commodity_state;
        }

        public void setCommodity_state(Object commodity_state) {
            this.commodity_state = commodity_state;
        }

        public Object getWhether_ration() {
            return whether_ration;
        }

        public void setWhether_ration(Object whether_ration) {
            this.whether_ration = whether_ration;
        }

        public Object getSplit_type() {
            return split_type;
        }

        public void setSplit_type(Object split_type) {
            this.split_type = split_type;
        }

        public Object getCreat_time() {
            return creat_time;
        }

        public void setCreat_time(Object creat_time) {
            this.creat_time = creat_time;
        }

        public Object getCreater() {
            return creater;
        }

        public void setCreater(Object creater) {
            this.creater = creater;
        }

        public Object getChange_time() {
            return change_time;
        }

        public void setChange_time(Object change_time) {
            this.change_time = change_time;
        }

        public Object getChanger() {
            return changer;
        }

        public void setChanger(Object changer) {
            this.changer = changer;
        }

        public Object getDelivery_time() {
            return delivery_time;
        }

        public void setDelivery_time(Object delivery_time) {
            this.delivery_time = delivery_time;
        }

        public Object getRecommended_categories() {
            return recommended_categories;
        }

        public void setRecommended_categories(Object recommended_categories) {
            this.recommended_categories = recommended_categories;
        }

        public int getSpecFourNum() {
            return specFourNum;
        }

        public void setSpecFourNum(int specFourNum) {
            this.specFourNum = specFourNum;
        }

        public String getPack_standard_four() {
            return pack_standard_four;
        }

        public void setPack_standard_four(String pack_standard_four) {
            this.pack_standard_four = pack_standard_four;
        }

        public Object getMarket_name() {
            return market_name;
        }

        public void setMarket_name(Object market_name) {
            this.market_name = market_name;
        }

        public String getPack_standard_tree_name() {
            return pack_standard_tree_name;
        }

        public void setPack_standard_tree_name(String pack_standard_tree_name) {
            this.pack_standard_tree_name = pack_standard_tree_name;
        }

        public Object getLevel() {
            return level;
        }

        public void setLevel(Object level) {
            this.level = level;
        }

        public Object getOrigin() {
            return origin;
        }

        public void setOrigin(Object origin) {
            this.origin = origin;
        }

        public Object getCount() {
            return count;
        }

        public void setCount(Object count) {
            this.count = count;
        }

        public Object getNumber() {
            return number;
        }

        public void setNumber(Object number) {
            this.number = number;
        }

        public Object getRegion() {
            return region;
        }

        public void setRegion(Object region) {
            this.region = region;
        }

        public List<String> getHpicture() {
            return hpicture;
        }

        public void setHpicture(List<String> hpicture) {
            this.hpicture = hpicture;
        }

        public List<String> getDpicture() {
            return dpicture;
        }

        public void setDpicture(List<String> dpicture) {
            this.dpicture = dpicture;
        }
    }

    public static class ParameteListBean {
        /**
         * commodity_id : 54565595db574c6e80c8525cef90af59
         * create_time : null
         * paramete_name_id : 适用
         * paramete_id : null
         * paramete_content : 刚回
         */

        private String commodity_id;
        private Object create_time;
        private String paramete_name_id;
        private String paramete_name;
        private Object paramete_id;
        private String paramete_content;

        public String getParamete_name() {
            return paramete_name;
        }

        public void setParamete_name(String paramete_name) {
            this.paramete_name = paramete_name;
        }

        public String getCommodity_id() {
            return commodity_id;
        }

        public void setCommodity_id(String commodity_id) {
            this.commodity_id = commodity_id;
        }

        public Object getCreate_time() {
            return create_time;
        }

        public void setCreate_time(Object create_time) {
            this.create_time = create_time;
        }

        public String getParamete_name_id() {
            return paramete_name_id;
        }

        public void setParamete_name_id(String paramete_name_id) {
            this.paramete_name_id = paramete_name_id;
        }

        public Object getParamete_id() {
            return paramete_id;
        }

        public void setParamete_id(Object paramete_id) {
            this.paramete_id = paramete_id;
        }

        public String getParamete_content() {
            return paramete_content;
        }

        public void setParamete_content(String paramete_content) {
            this.paramete_content = paramete_content;
        }
    }
}
