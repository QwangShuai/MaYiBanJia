package com.mingmen.mayi.mayibanjia.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2018/8/3/003.
 */

public class XiTongTuiJianBean {
        /**
         * commodity_sales  : [{"count":null,"company_name":"津津乐道123","commodity_id":"1234bf9b89764110bdac7a3b39940804","ration_three":"6","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"641e9e9b291e41308ba10c9c0bc88960","ration_one":"2","pice_one":5,"ration_two":"4","pice_two":10,"pice_three":15,"market_id":"1","specific_address":null,"commodity_name":"肉类","pack_standard_one":null,"pack_standard_tree":null,"commodity_sales":"9","star_evaluation":null},{"count":null,"company_name":"哈市","commodity_id":"672292f16d564b089b11d392fb157913","ration_three":"","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"22","ration_one":null,"pice_one":0,"ration_two":null,"pice_two":0,"pice_three":0,"market_id":"1","specific_address":null,"commodity_name":"五花肉","pack_standard_one":null,"pack_standard_tree":null,"commodity_sales":"8","star_evaluation":null},{"count":null,"company_name":"古色古香","commodity_id":"62342b977e494e45a3219291e80409d7","ration_three":"","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"21","ration_one":null,"pice_one":202,"ration_two":null,"pice_two":0,"pice_three":0,"market_id":"1","specific_address":null,"commodity_name":"五花肉","pack_standard_one":null,"pack_standard_tree":null,"commodity_sales":"8","star_evaluation":"2"},{"count":null,"company_name":"古色古香","commodity_id":"61901cf8aa0c4119a0158ef737501426","ration_three":"","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"21","ration_one":"30","pice_one":12,"ration_two":null,"pice_two":0,"pice_three":0,"market_id":"1","specific_address":null,"commodity_name":"五花肉","pack_standard_one":null,"pack_standard_tree":null,"commodity_sales":"7","star_evaluation":"2"},{"count":null,"company_name":"津津乐道123","commodity_id":"553abf9b89764110bdac7a3b39940804","ration_three":"","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"641e9e9b291e41308ba10c9c0bc88960","ration_one":"30","pice_one":1,"ration_two":null,"pice_two":0,"pice_three":0,"market_id":"1","specific_address":null,"commodity_name":"五花肉","pack_standard_one":null,"pack_standard_tree":null,"commodity_sales":"6","star_evaluation":null},{"count":null,"company_name":"津津乐道123","commodity_id":"6039da4f478142e9812cbf4a54791246","ration_three":"","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"641e9e9b291e41308ba10c9c0bc88960","ration_one":null,"pice_one":202,"ration_two":null,"pice_two":0,"pice_three":0,"market_id":"1","specific_address":null,"commodity_name":"五花肉","pack_standard_one":null,"pack_standard_tree":null,"commodity_sales":"5","star_evaluation":null},{"count":null,"company_name":"津津乐道123","commodity_id":"be37214f478142e9812cbf4a5479149c","ration_three":"","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"641e9e9b291e41308ba10c9c0bc88960","ration_one":"30","pice_one":10,"ration_two":null,"pice_two":0,"pice_three":0,"market_id":"1","specific_address":null,"commodity_name":"肉类","pack_standard_one":null,"pack_standard_tree":null,"commodity_sales":"5","star_evaluation":null},{"count":null,"company_name":"哈市","commodity_id":"a127e56d051c4ba6bc63411cf8cc7b12","ration_three":"","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"22","ration_one":null,"pice_one":0,"ration_two":null,"pice_two":0,"pice_three":0,"market_id":"1","specific_address":null,"commodity_name":"五花肉","pack_standard_one":null,"pack_standard_tree":null,"commodity_sales":"5","star_evaluation":null},{"count":null,"company_name":"津津乐道123","commodity_id":"28beda4f478142e9812cbf4a5479149c","ration_three":"6","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"641e9e9b291e41308ba10c9c0bc88960","ration_one":"2","pice_one":10,"ration_two":"4","pice_two":9,"pice_three":8,"market_id":"1","specific_address":null,"commodity_name":"肉","pack_standard_one":null,"pack_standard_tree":"","commodity_sales":"3","star_evaluation":null},{"count":null,"company_name":"古色古香","commodity_id":"23","ration_three":"","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"21","ration_one":"30","pice_one":5,"ration_two":"","pice_two":0,"pice_three":0,"market_id":"1","specific_address":null,"commodity_name":"春桃","pack_standard_one":null,"pack_standard_tree":"48c795cbd3184b348a8ce7c618d4eca5","commodity_sales":"10","star_evaluation":"2"}]
         * pflist : [{"count":null,"company_name":"古色古香","commodity_id":"23","ration_three":"","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"21","ration_one":"30","pice_one":5,"ration_two":"","pice_two":0,"pice_three":0,"market_id":"1","specific_address":null,"commodity_name":"春桃","pack_standard_one":null,"pack_standard_tree":"48c795cbd3184b348a8ce7c618d4eca5","commodity_sales":"10","star_evaluation":"2"},{"count":null,"company_name":"古色古香","commodity_id":"61901cf8aa0c4119a0158ef737501426","ration_three":"","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"21","ration_one":"30","pice_one":12,"ration_two":null,"pice_two":0,"pice_three":0,"market_id":"1","specific_address":null,"commodity_name":"五花肉","pack_standard_one":null,"pack_standard_tree":null,"commodity_sales":"7","star_evaluation":"2"},{"count":null,"company_name":"古色古香","commodity_id":"62342b977e494e45a3219291e80409d7","ration_three":"","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"21","ration_one":null,"pice_one":202,"ration_two":null,"pice_two":0,"pice_three":0,"market_id":"1","specific_address":null,"commodity_name":"五花肉","pack_standard_one":null,"pack_standard_tree":null,"commodity_sales":"8","star_evaluation":"2"},{"count":null,"company_name":"古色古香","commodity_id":"661305ae68c04847acae9705692ab3eb","ration_three":"","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"21","ration_one":"","pice_one":202,"ration_two":"","pice_two":0,"pice_three":0,"market_id":"1","specific_address":null,"commodity_name":"羊肉","pack_standard_one":null,"pack_standard_tree":"","commodity_sales":null,"star_evaluation":"2"},{"count":null,"company_name":"津津乐道123","commodity_id":"1234bf9b89764110bdac7a3b39940804","ration_three":"6","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"641e9e9b291e41308ba10c9c0bc88960","ration_one":"2","pice_one":5,"ration_two":"4","pice_two":10,"pice_three":15,"market_id":"1","specific_address":null,"commodity_name":"肉类","pack_standard_one":null,"pack_standard_tree":null,"commodity_sales":"9","star_evaluation":null},{"count":null,"company_name":"津津乐道123","commodity_id":"6039da4f478142e9812cbf4a54791246","ration_three":"","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"641e9e9b291e41308ba10c9c0bc88960","ration_one":null,"pice_one":202,"ration_two":null,"pice_two":0,"pice_three":0,"market_id":"1","specific_address":null,"commodity_name":"五花肉","pack_standard_one":null,"pack_standard_tree":null,"commodity_sales":"5","star_evaluation":null},{"count":null,"company_name":"哈市","commodity_id":"672292f16d564b089b11d392fb157913","ration_three":"","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"22","ration_one":null,"pice_one":0,"ration_two":null,"pice_two":0,"pice_three":0,"market_id":"1","specific_address":null,"commodity_name":"五花肉","pack_standard_one":null,"pack_standard_tree":null,"commodity_sales":"8","star_evaluation":null},{"count":null,"company_name":"津津乐道123","commodity_id":"be37214f478142e9812cbf4a5479149c","ration_three":"","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"641e9e9b291e41308ba10c9c0bc88960","ration_one":"30","pice_one":10,"ration_two":null,"pice_two":0,"pice_three":0,"market_id":"1","specific_address":null,"commodity_name":"肉类","pack_standard_one":null,"pack_standard_tree":null,"commodity_sales":"5","star_evaluation":null},{"count":null,"company_name":"哈市","commodity_id":"68396eab073545b3bbf5b4035c925a4a","ration_three":"","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"22","ration_one":null,"pice_one":0,"ration_two":null,"pice_two":0,"pice_three":0,"market_id":"1","specific_address":null,"commodity_name":"五花肉","pack_standard_one":null,"pack_standard_tree":null,"commodity_sales":null,"star_evaluation":null},{"count":null,"company_name":"津津乐道123","commodity_id":"28beda4f478142e9812cbf4a5479149c","ration_three":"6","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"641e9e9b291e41308ba10c9c0bc88960","ration_one":"2","pice_one":10,"ration_two":"4","pice_two":9,"pice_three":8,"market_id":"1","specific_address":null,"commodity_name":"肉","pack_standard_one":null,"pack_standard_tree":"","commodity_sales":"3","star_evaluation":null}]
         * ccList : [{"count":null,"company_name":"哈市","commodity_id":"22","ration_three":"60","picture_url":null,"market_name":"哈达市场","company_id":"22","ration_one":"30","pice_one":10,"ration_two":"50","pice_two":20,"pice_three":30,"market_id":"1","specific_address":"测试地址","commodity_name":"春桃","pack_standard_one":"fd679c0559ef468e871710ba00c5da9f","pack_standard_tree":"","commodity_sales":"2","star_evaluation":null},{"count":null,"company_name":"古色古香","commodity_id":"23","ration_three":"","picture_url":"http://pbn4jedp4.bkt.clouddn.com/FjYKT87t3tHUIjXIjAMubhAfo-Lg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:RNVMc_zSZlaWL_sUtEw2bW5aY-Q=","market_name":"哈达市场","company_id":"21","ration_one":"30","pice_one":5,"ration_two":"","pice_two":0,"pice_three":0,"market_id":"1","specific_address":"测试街203号","commodity_name":"春桃","pack_standard_one":"7f6e635f04014b0ebf16759eb5b38318","pack_standard_tree":"48c795cbd3184b348a8ce7c618d4eca5","commodity_sales":"10","star_evaluation":"2"},{"count":null,"company_name":"津津乐道123","commodity_id":"1234bf9b89764110bdac7a3b39940804","ration_three":"6","picture_url":"http://pbn4jedp4.bkt.clouddn.com/FvHTbYSBTEA4B7J-3xVnY6CgpqZm?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:fXqV6hSATL2xlso9T7d2bUErks0=","market_name":"哈达市场","company_id":"641e9e9b291e41308ba10c9c0bc88960","ration_one":"2","pice_one":5,"ration_two":"4","pice_two":10,"pice_three":15,"market_id":"1","specific_address":"测试地址","commodity_name":"肉类","pack_standard_one":"桶","pack_standard_tree":null,"commodity_sales":"9","star_evaluation":null}]
         * type : 0
         * pice  : [{"count":null,"company_name":"哈市","commodity_id":"7a333e447deb49feb0975bad39238361","ration_three":"","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"22","ration_one":null,"pice_one":0,"ration_two":null,"pice_two":0,"pice_three":0,"market_id":"1","specific_address":null,"commodity_name":"肉类","pack_standard_one":null,"pack_standard_tree":null,"commodity_sales":null,"star_evaluation":null},{"count":null,"company_name":"哈市","commodity_id":"a127e56d051c4ba6bc63411cf8cc7b12","ration_three":"","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"22","ration_one":null,"pice_one":0,"ration_two":null,"pice_two":0,"pice_three":0,"market_id":"1","specific_address":null,"commodity_name":"五花肉","pack_standard_one":null,"pack_standard_tree":null,"commodity_sales":"5","star_evaluation":null},{"count":null,"company_name":"哈市","commodity_id":"672292f16d564b089b11d392fb157913","ration_three":"","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"22","ration_one":null,"pice_one":0,"ration_two":null,"pice_two":0,"pice_three":0,"market_id":"1","specific_address":null,"commodity_name":"五花肉","pack_standard_one":null,"pack_standard_tree":null,"commodity_sales":"8","star_evaluation":null},{"count":null,"company_name":"哈市","commodity_id":"68396eab073545b3bbf5b4035c925a4a","ration_three":"","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"22","ration_one":null,"pice_one":0,"ration_two":null,"pice_two":0,"pice_three":0,"market_id":"1","specific_address":null,"commodity_name":"五花肉","pack_standard_one":null,"pack_standard_tree":null,"commodity_sales":null,"star_evaluation":null},{"count":null,"company_name":"津津乐道123","commodity_id":"553abf9b89764110bdac7a3b39940804","ration_three":"","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"641e9e9b291e41308ba10c9c0bc88960","ration_one":"30","pice_one":1,"ration_two":null,"pice_two":0,"pice_three":0,"market_id":"1","specific_address":null,"commodity_name":"五花肉","pack_standard_one":null,"pack_standard_tree":null,"commodity_sales":"6","star_evaluation":null},{"count":null,"company_name":"津津乐道123","commodity_id":"1234bf9b89764110bdac7a3b39940804","ration_three":"6","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"641e9e9b291e41308ba10c9c0bc88960","ration_one":"2","pice_one":5,"ration_two":"4","pice_two":10,"pice_three":15,"market_id":"1","specific_address":null,"commodity_name":"肉类","pack_standard_one":null,"pack_standard_tree":null,"commodity_sales":"9","star_evaluation":null},{"count":null,"company_name":"古色古香","commodity_id":"23","ration_three":"","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"21","ration_one":"30","pice_one":5,"ration_two":"","pice_two":0,"pice_three":0,"market_id":"1","specific_address":null,"commodity_name":"春桃","pack_standard_one":null,"pack_standard_tree":"48c795cbd3184b348a8ce7c618d4eca5","commodity_sales":"10","star_evaluation":"2"},{"count":null,"company_name":"津津乐道123","commodity_id":"28beda4f478142e9812cbf4a5479149c","ration_three":"6","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"641e9e9b291e41308ba10c9c0bc88960","ration_one":"2","pice_one":10,"ration_two":"4","pice_two":9,"pice_three":8,"market_id":"1","specific_address":null,"commodity_name":"肉","pack_standard_one":null,"pack_standard_tree":"","commodity_sales":"3","star_evaluation":null},{"count":null,"company_name":"津津乐道123","commodity_id":"be37214f478142e9812cbf4a5479149c","ration_three":"","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"641e9e9b291e41308ba10c9c0bc88960","ration_one":"30","pice_one":10,"ration_two":null,"pice_two":0,"pice_three":0,"market_id":"1","specific_address":null,"commodity_name":"肉类","pack_standard_one":null,"pack_standard_tree":null,"commodity_sales":"5","star_evaluation":null},{"count":null,"company_name":"古色古香","commodity_id":"61901cf8aa0c4119a0158ef737501426","ration_three":"","picture_url":"http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=","market_name":null,"company_id":"21","ration_one":"30","pice_one":12,"ration_two":null,"pice_two":0,"pice_three":0,"market_id":"1","specific_address":null,"commodity_name":"五花肉","pack_standard_one":null,"pack_standard_tree":null,"commodity_sales":"7","star_evaluation":"2"}]
         * oreder_buy  : [{"count":null,"company_name":"津津乐道123","commodity_id":"28beda4f478142e9812cbf4a5479149c","ration_three":"6","picture_url":"6cf805c9a0854db198931bb87050fd80197895686880925581.jpg","market_name":null,"company_id":"641e9e9b291e41308ba10c9c0bc88960","ration_one":"2","pice_one":10,"ration_two":"4","pice_two":9,"pice_three":8,"market_id":null,"specific_address":null,"commodity_name":"肉","pack_standard_one":null,"pack_standard_tree":"","commodity_sales":"3","star_evaluation":null},{"count":null,"company_name":"津津乐道123","commodity_id":"6039da4f478142e9812cbf4a54791246","ration_three":"","picture_url":"6cf805c9a0854db198931bb87050fd80197895686880925581.jpg","market_name":null,"company_id":"641e9e9b291e41308ba10c9c0bc88960","ration_one":null,"pice_one":202,"ration_two":null,"pice_two":0,"pice_three":0,"market_id":null,"specific_address":null,"commodity_name":"五花肉","pack_standard_one":null,"pack_standard_tree":null,"commodity_sales":"5","star_evaluation":null},{"count":null,"company_name":"津津乐道123","commodity_id":"1234bf9b89764110bdac7a3b39940804","ration_three":"6","picture_url":"6cf805c9a0854db198931bb87050fd80197895686880925581.jpg","market_name":null,"company_id":"641e9e9b291e41308ba10c9c0bc88960","ration_one":"2","pice_one":5,"ration_two":"4","pice_two":10,"pice_three":15,"market_id":null,"specific_address":null,"commodity_name":"肉类","pack_standard_one":null,"pack_standard_tree":null,"commodity_sales":"9","star_evaluation":null}]
         */

        private List<CcListBean> pflist;   //评分
        private List<CcListBean> commodity_sales ; //销量
        private List<CcListBean> pice ;//价格


    public List<CcListBean> getPflist() {
        return pflist;
    }

    public void setPflist(List<CcListBean> pflist) {
        this.pflist = pflist;
    }



    public List<CcListBean> getCommodity_sales() {
        return commodity_sales;
    }

    public void setCommodity_sales(List<CcListBean> commodity_sales) {
        this.commodity_sales = commodity_sales;
    }

    public List<CcListBean> getPice() {
        return pice;
    }

    public void setPice(List<CcListBean> pice) {
        this.pice = pice;
    }



    public class CtBuySonorderBean{
        private String type;
        private String count;
        private String classify_name;
        private String change_time;
        private String user_token;
        private String market_id;
        private String sort_id;
        private String commodity_id;
        private String purchase_id;
        private String picture_url;
        private String son_order_id;
        private String special_commodity;
        private String if_special_need;
        private String create_time;
        private String changer;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getClassify_name() {
            return classify_name;
        }

        public void setClassify_name(String classify_name) {
            this.classify_name = classify_name;
        }

        public String getChange_time() {
            return change_time;
        }

        public void setChange_time(String change_time) {
            this.change_time = change_time;
        }

        public String getUser_token() {
            return user_token;
        }

        public void setUser_token(String user_token) {
            this.user_token = user_token;
        }

        public String getMarket_id() {
            return market_id;
        }

        public void setMarket_id(String market_id) {
            this.market_id = market_id;
        }

        public String getSort_id() {
            return sort_id;
        }

        public void setSort_id(String sort_id) {
            this.sort_id = sort_id;
        }

        public String getCommodity_id() {
            return commodity_id;
        }

        public void setCommodity_id(String commodity_id) {
            this.commodity_id = commodity_id;
        }

        public String getPurchase_id() {
            return purchase_id;
        }

        public void setPurchase_id(String purchase_id) {
            this.purchase_id = purchase_id;
        }

        public String getPicture_url() {
            return picture_url;
        }

        public void setPicture_url(String picture_url) {
            this.picture_url = picture_url;
        }

        public String getSon_order_id() {
            return son_order_id;
        }

        public void setSon_order_id(String son_order_id) {
            this.son_order_id = son_order_id;
        }

        public String getSpecial_commodity() {
            return special_commodity;
        }

        public void setSpecial_commodity(String special_commodity) {
            this.special_commodity = special_commodity;
        }

        public String getIf_special_need() {
            return if_special_need;
        }

        public void setIf_special_need(String if_special_need) {
            this.if_special_need = if_special_need;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getChanger() {
            return changer;
        }

        public void setChanger(String changer) {
            this.changer = changer;
        }
    }
    public static class CcListBean implements Serializable {
            /**
             * count : null
             * company_name : 津津乐道123
             * commodity_id : 1234bf9b89764110bdac7a3b39940804
             * ration_three : 6
             * picture_url : http://pbn4jedp4.bkt.clouddn.com/6cf805c9a0854db198931bb87050fd80197895686880925581.jpg?e=1533302024&token=EetUisYqZq45CDj_OHzI3G5FPRsPp4WCEl3FtAEu:yO8Nv_2PrskOwnPu3o5GTd628qs=
             * market_name : null
             * company_id : 641e9e9b291e41308ba10c9c0bc88960
             * ration_one : 2
             * pice_one : 5.0
             * ration_two : 4
             * pice_two : 10.0
             * pice_three : 15.0
             * market_id : 1
             * specific_address : null
             * commodity_name : 肉类
             * pack_standard_one : null
             * pack_standard_tree : null
             * commodity_sales : 9
             * star_evaluation : null
             */
            private	String company_id; //店铺id
        private	String company_name; //店铺名字
        private String commodity_id; // 商品id
        private String commodity_name; // 商品名称
        private String  pack_standard_one; //规格1
        private String  pack_standard_two; //规格2
        private String  pack_standard_tree; //规格3
        private String  pack_standard_two_name; //规格2
        private String  pack_standard_one_name; //规格1
        private String  pack_standard_tree_name; //规格3\
        private String  pack_name; //采购单规格
        private	String picture_url; // 分类2级图片
        private String commodity_sales; // 销售量
        private Integer count ;//数量
        private String  ration_one; //起订量1
        private	String ration_two; // 起订量2
        private String ration_three; // 起订量3
        private	double pice_one;//价格
        private	double pice_two;//价格
        private	double pice_three;//价格
        private String specific_address;//商家具体地址
        private String market_id;//市场id
        private String market_name;//市场name
        private float star_evaluation;//评价
        private String choose_specifications;//默认显示规格
        private String type_tree_id;
        private String price;
        private String pack_standard;
        private boolean isxianshi=true;
        private String biaoqian;
        private boolean xianshiqidingliang=false;
        private String son_order_id;
        private String price_one;// 单价1
        private String price_two;// 单价2
        private String price_three;// 单价3
        private String creat_time;
        private String end_time;
        private BigDecimal append_money;//附加费
        private String classify_name;

        public String getClassify_name() {
            return classify_name;
        }

        public void setClassify_name(String classify_name) {
            this.classify_name = classify_name;
        }
        public BigDecimal getAppend_money() {
            return append_money;
        }

        public void setAppend_money(BigDecimal append_money) {
            this.append_money = append_money;
        }

        public String getCreat_time() {
            return creat_time;
        }

        public void setCreat_time(String creat_time) {
            this.creat_time = creat_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getPack_name() {
            return pack_name;
        }

        public void setPack_name(String pack_name) {
            this.pack_name = pack_name;
        }

        public String getPrice_one() {
            return price_one;
        }

        public void setPrice_one(String price_one) {
            this.price_one = price_one;
        }

        public String getPrice_two() {
            return price_two;
        }

        public void setPrice_two(String price_two) {
            this.price_two = price_two;
        }

        public String getPrice_three() {
            return price_three;
        }

        public void setPrice_three(String price_three) {
            this.price_three = price_three;
        }

        public String getSon_order_id() {
            return son_order_id;
        }

        public void setSon_order_id(String son_order_id) {
            this.son_order_id = son_order_id;
        }

        public boolean isXianshiqidingliang() {
            return xianshiqidingliang;
        }

        public void setXianshiqidingliang(boolean xianshiqidingliang) {
            this.xianshiqidingliang = xianshiqidingliang;
        }

        public String getBiaoqian() {
            return biaoqian;
        }

        public void setBiaoqian(String biaoqian) {
            this.biaoqian = biaoqian;
        }

        public boolean isxianshi() {
            return isxianshi;
        }

        public void setIsxianshi(boolean isyincang) {
            this.isxianshi = isyincang;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPack_standard() {
            return pack_standard;
        }

        public void setPack_standard(String pack_standard) {
            this.pack_standard = pack_standard;
        }

        public String getPack_standard_one_name() {
            return pack_standard_one_name;
        }

        public void setPack_standard_one_name(String pack_standard_one_name) {
            this.pack_standard_one_name = pack_standard_one_name;
        }

        public String getType_tree_id() {
            return type_tree_id;
        }

        public void setType_tree_id(String type_tree_id) {
            this.type_tree_id = type_tree_id;
        }

        public String getPack_standard_two() {
            return pack_standard_two;
        }
        public void setPack_standard_two(String pack_standard_two) {
            this.pack_standard_two = pack_standard_two;
        }
        public String getPack_standard_two_name() {
            return pack_standard_two_name;
        }
        public void setPack_standard_two_name(String pack_standard_two_name) {
            this.pack_standard_two_name = pack_standard_two_name;
        }
        public String getPack_standard_tree_name() {
            return pack_standard_tree_name;
        }
        public void setPack_standard_tree_name(String pack_standard_tree_name) {
            this.pack_standard_tree_name = pack_standard_tree_name;
        }
        public String getChoose_specifications() {
            return choose_specifications;
        }
        public void setChoose_specifications(String choose_specifications) {
            this.choose_specifications = choose_specifications;
        }
        public String getPack_standard_one() {
            return pack_standard_one;
        }
        public void setPack_standard_one(String pack_standard_one) {
            this.pack_standard_one = pack_standard_one;
        }
        public float getStar_evaluation() {
            return star_evaluation;
        }
        public void setStar_evaluation(float star_evaluation) {
            this.star_evaluation = star_evaluation;
        }
        public String getSpecific_address() {
            return specific_address;
        }
        public void setSpecific_address(String specific_address) {
            this.specific_address = specific_address;
        }
        public String getMarket_id() {
            return market_id;
        }
        public void setMarket_id(String market_id) {
            this.market_id = market_id;
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
        public double getPice_one() {
            return pice_one;
        }
        public void setPice_one(double pice_one) {
            this.pice_one = pice_one;
        }
        public double getPice_two() {
            return pice_two;
        }
        public void setPice_two(double pice_two) {
            this.pice_two = pice_two;
        }
        public double getPice_three() {
            return pice_three;
        }
        public void setPice_three(double pice_three) {
            this.pice_three = pice_three;
        }
        public String getCompany_id() {
            return company_id;
        }
        public void setCompany_id(String company_id) {
            this.company_id = company_id;
        }
        public Integer getCount() {
            return count;
        }
        public void setCount(Integer count) {
            this.count = count;
        }
        public String getCompany_name() {
            return company_name;
        }
        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }
        public String getCommodity_id() {
            return commodity_id;
        }
        public void setCommodity_id(String commodity_id) {
            this.commodity_id = commodity_id;
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
        public String getPicture_url() {
            return picture_url;
        }
        public void setPicture_url(String picture_url) {
            this.picture_url = picture_url;
        }
        public String getCommodity_sales() {
            return commodity_sales;
        }
        public void setCommodity_sales(String commodity_sales) {
            this.commodity_sales = commodity_sales;
        }

    }




}
