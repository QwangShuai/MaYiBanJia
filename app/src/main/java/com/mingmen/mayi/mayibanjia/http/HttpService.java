package com.mingmen.mayi.mayibanjia.http;


import com.mingmen.mayi.mayibanjia.bean.AddressListBean;
import com.mingmen.mayi.mayibanjia.bean.AllShiChangBean;
import com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean;
import com.mingmen.mayi.mayibanjia.bean.CarsTypeBean;
import com.mingmen.mayi.mayibanjia.bean.ChePaiBean;
import com.mingmen.mayi.mayibanjia.bean.DdxqListBean;
import com.mingmen.mayi.mayibanjia.bean.DianMingChaXunBean;
import com.mingmen.mayi.mayibanjia.bean.DianPuBean;
import com.mingmen.mayi.mayibanjia.bean.DianPuZhanShiBean;
import com.mingmen.mayi.mayibanjia.bean.DingDanBean;
import com.mingmen.mayi.mayibanjia.bean.EditorShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.FCGGuige;
import com.mingmen.mayi.mayibanjia.bean.FCGName;
import com.mingmen.mayi.mayibanjia.bean.FCGSaveFanHuiBean;
import com.mingmen.mayi.mayibanjia.bean.FbspGuiGeBean;
import com.mingmen.mayi.mayibanjia.bean.FenLeiBean;
import com.mingmen.mayi.mayibanjia.bean.FenLeiMingChengBean;
import com.mingmen.mayi.mayibanjia.bean.GHOrderBean;
import com.mingmen.mayi.mayibanjia.bean.GWCShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.GetMenDianBean;
import com.mingmen.mayi.mayibanjia.bean.GetZiZhiBean;
import com.mingmen.mayi.mayibanjia.bean.JYMXBean;
import com.mingmen.mayi.mayibanjia.bean.JYMXItemBean;
import com.mingmen.mayi.mayibanjia.bean.JieSuanJirGuoBean;
import com.mingmen.mayi.mayibanjia.bean.JueSeBean;
import com.mingmen.mayi.mayibanjia.bean.LiShiJiLuBean;
import com.mingmen.mayi.mayibanjia.bean.LiuLanJiLuBean;
import com.mingmen.mayi.mayibanjia.bean.PhoneBean;
import com.mingmen.mayi.mayibanjia.bean.PingJiaLableBean;
import com.mingmen.mayi.mayibanjia.bean.PingJiaTypeListBean;
import com.mingmen.mayi.mayibanjia.bean.ProvinceBean;
import com.mingmen.mayi.mayibanjia.bean.QiYeGuiMoBean;
import com.mingmen.mayi.mayibanjia.bean.QiYeLeiBieBean;
import com.mingmen.mayi.mayibanjia.bean.QiYeLieBiaoBean;
import com.mingmen.mayi.mayibanjia.bean.QiangDanBean;
import com.mingmen.mayi.mayibanjia.bean.QuanBuShiChangBean;
import com.mingmen.mayi.mayibanjia.bean.QueRenDingDanShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.RoleBean;
import com.mingmen.mayi.mayibanjia.bean.SPTGBean;
import com.mingmen.mayi.mayibanjia.bean.SPXiangQingBean;
import com.mingmen.mayi.mayibanjia.bean.ShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.ShangPinGuanLiBean;
import com.mingmen.mayi.mayibanjia.bean.ShangPinSouSuoBean;
import com.mingmen.mayi.mayibanjia.bean.ShangPinSousuoMohuBean;
import com.mingmen.mayi.mayibanjia.bean.ShenPiQuanXuanBean;
import com.mingmen.mayi.mayibanjia.bean.ShiChangBean;
import com.mingmen.mayi.mayibanjia.bean.ShiChangSouSuoShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.ShouCangBean;
import com.mingmen.mayi.mayibanjia.bean.ShouYeBannerBean;
import com.mingmen.mayi.mayibanjia.bean.ShouYeLeiBean;
import com.mingmen.mayi.mayibanjia.bean.ShouYeShangChangBean;
import com.mingmen.mayi.mayibanjia.bean.ShouYeTeJiaBean;
import com.mingmen.mayi.mayibanjia.bean.SiJiWLXQBean;
import com.mingmen.mayi.mayibanjia.bean.SongDaShiJianBean;
import com.mingmen.mayi.mayibanjia.bean.TuiJianBean;
import com.mingmen.mayi.mayibanjia.bean.WXPayBean;
import com.mingmen.mayi.mayibanjia.bean.WeiYiQrCodeBean;
import com.mingmen.mayi.mayibanjia.bean.WoDeBean;
import com.mingmen.mayi.mayibanjia.bean.WuLiuBean;
import com.mingmen.mayi.mayibanjia.bean.WuLiuObjBean;
import com.mingmen.mayi.mayibanjia.bean.XQPingJiaBean;
import com.mingmen.mayi.mayibanjia.bean.XiTongTuiJianBean;
import com.mingmen.mayi.mayibanjia.bean.XuanZeYinHangKaBean;
import com.mingmen.mayi.mayibanjia.bean.YinHangKaBean;
import com.mingmen.mayi.mayibanjia.bean.YunFeiBean;
import com.mingmen.mayi.mayibanjia.bean.YunFeiJieSuanBean;
import com.mingmen.mayi.mayibanjia.bean.ZhangHuRenZhengBean;
import com.mingmen.mayi.mayibanjia.bean.ZhuCeChengGongBean;
import com.mingmen.mayi.mayibanjia.bean.ZiZhangHuBean;
import com.mingmen.mayi.mayibanjia.bean.ZiZhangHuDetailsBean;
import com.mingmen.mayi.mayibanjia.bean.ZouShiTuBean;
import com.mingmen.mayi.mayibanjia.http.result.ResultModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HttpService {
    //首页分类数据
    @POST("sy/list.do")
    Observable<ResultModel<List<ShouYeLeiBean>>> getShouYeFenLei(@Query("user_token") String user_token);

    //首页分类数据
    @POST("allCompany/account.do")
    Observable<ResultModel<ShouYeShangChangBean>> getShouYeShangJia();

    //首页轮播图
    @POST("allPhoto/show.do")
    Observable<ResultModel<List<ShouYeBannerBean>>> getbanner(@Query("user_token") String user_token);

    //首页特价商品
    @POST("sy/querytjgoods.do")
    Observable<ResultModel<List<ShouYeTeJiaBean>>> getShouYeTeJia(@Query("user_token") String user_token);

    //获取七牛token
    @POST("QiNiu/addPicture.do")
    Observable<ResultModel<String>> qiniushangchuan();

    //发送验证码
    @POST("allCompanyAccount/get_verification_code.do")
    Observable<ResultModel<String>> getcode(@Query("yzmType") String yzmType, @Query("telephone") String telephone);

    //确认验证码输入是否正确
    @POST("allCompanyAccount/updateByTelephone.do")
    Observable<ResultModel<String>> changePhone(@Query("user_token") String user_token, @Query("telephone") String telephone);

    //确认验证码输入是否正确
    @POST("allCompanyAccount/code.do")
    Observable<ResultModel<String>> yanzhengcode(@Query("telephone") String telephone, @Query("yzm") String yzm);

    //注册
    @POST("allCompanyAccount/save.do")
    Observable<ResultModel<ZhuCeChengGongBean>> zhuce(@Query("principal") String principal,//负责人名称
                                                      @Query("telephone") String telephone,//手机号
                                                      @Query("password") String password,//密码
                                                      @Query("business_license") String business_license,//营业执照
                                                      @Query("circulation_permit") String circulation_permit,//食品流通许可证
                                                      @Query("company_id") String company_id,//企业id
                                                      @Query("role") String role,//1餐厅端,2供应端
                                                      @Query("salesman_code") String salesman_code,//邀请码
                                                      @Query("province") String province,//省 供应角色时为必填项
                                                      @Query("city") String city,//市供 应角色时为必填项
                                                      @Query("district") String district,//区供 应角色时为必填项
                                                      @Query("son_number") String son_number,//市场 供应角色时为必填项
                                                      @Query("booth_number") String booth_number,//摊位号 供应角色时为必填项
                                                      @Query("yzm") String yzm//验证码
    );

    //门店名称模糊查询
    @POST("allCompany/getname.do")
    Observable<ResultModel<List<DianMingChaXunBean>>> dianpuchaxun(@Query("company_name") String company_name);

    //供货端获取省
    @POST("sysQuy/querychina.do")
    Observable<ResultModel<List<ProvinceBean>>> getsheng(@Query("quybm") String quybm);

    //供货端获取市场列表
    @POST("sysQuy/queryMarket.do")
    Observable<ResultModel<List<ShiChangBean>>> getshichang(@Query("quybm") String quybm);

    //登录
    @POST("allCompanyAccount/getLogin.do")
    Observable<ResultModel<ZhuCeChengGongBean>> login(@Query("telephone") String telephone,
                                                      @Query("password") String password,
                                                      @Query("yzm") String yzm,
                                                      @Query("login_type") String login_type
    );

    //市场搜索
    @POST("sy/queryMarket.do")
    Observable<ResultModel<List<ShiChangBean>>> shichangsousuo(@Query("user_token") String user_token,
                                                               @Query("market_name") String market_name);

    //市场搜索商品
    @POST("sy/csave.do")
    Observable<ResultModel<List<ShiChangSouSuoShangPinBean>>> shichangsousuoshangpin(@Query("type_tree_id") String type_tree_id);

    //店铺搜索
    @POST("sy/queryDp.do")
    Observable<ResultModel<List<DianPuBean>>> sousuodianpu(@Query("user_token") String user_token,
                                                           @Query("company_name") String company_name);

    //商品搜索
    @POST("gyCommodity/queryByCommodity_name.do")
    Observable<ResultModel<ShangPinSouSuoBean>> sousuoshangpin(@Query("user_token") String user_token,
                                                               @Query("commodity_name") String commodity_name,
                                                               @Query("city") String city,
                                                               @Query("region") String region,
                                                               @Query("type_one_id") String type_one_id,
                                                               @Query("type_two_id") String type_two_id,
                                                               @Query("type_tree_id") String type_tree_id,
                                                               @Query("market_id") String market_id,
                                                               @Query("big") String big,
                                                               @Query("small") String small,
                                                               @Query("sortOrder") String sortOrder,
                                                               @Query("pageNumber") Integer pageNumber);//0:默认查询（搜索），1：销量最高 2，评分最高 3.价格最高 4.价格最低

    //全部菜品默认list，搜索也调用此方法
    @POST("gyCommodity/queryByCommodity_name.do")
    Observable<ResultModel<ShangPinSouSuoBean>> sousuomoren(@Query("user_token") String user_token,
                                                            @Query("commodity_name") String commodity_name,
                                                            @Query("sortOrder") String sortOrder);

    //全部菜品默认list，搜索也调用此方法
    @POST("gyCommodity/queryByCommodity_name.do")
    Observable<ResultModel<ShangPinSouSuoBean>> shichangsousuoshangpin(@Query("type_tree_id") String type_tree_id,
                                                                       @Query("son_number") String son_number,
                                                                       @Query("sortOrder") String sortOrder);

    //查询购物车
    @POST("ctShoppingCart/list.do")
    Observable<ResultModel<GWCShangPinBean>> getgouwuche(@Query("user_token") String user_token);

    //查询购物车内选中的商品总价
    @POST("ctShoppingCart/conntMoney.do")
    Observable<ResultModel<String>> getgwcmoney(@Query("user_token") String user_token,
                                                @Query("shopping_id") String shopping_id);

    //删除购物车商品
    @POST("ctShoppingCart/delete.do")
    Observable<ResultModel<String>> delgwc(@Query("user_token") String user_token,
                                           @Query("type") String type,
                                           @Query("shopping_id") String shopping_id);

    //添加收货地址
    @POST("allAddress/save.do")
    Observable<ResultModel<String>> addAddress(@Query("user_token") String user_token,
                                               @Query("province") String province,
                                               @Query("city") String city,
                                               @Query("region") String region,
                                               @Query("street") String street,
                                               @Query("specific_address") String specific_address,
                                               @Query("linkman") String linkman,
                                               @Query("contact_type") String contact_type,
                                               @Query("default_address") String default_address
    );

    //修改收货地址
    @POST("allAddress/update.do")
    Observable<ResultModel<String>> editAddress(@Query("user_token") String user_token,
                                                @Query("province") String province,
                                                @Query("city") String city,
                                                @Query("region") String region,
                                                @Query("street") String street,
                                                @Query("specific_address") String specific_address,
                                                @Query("linkman") String linkman,
                                                @Query("contact_type") String contact_type,
                                                @Query("default_address") String default_address,
                                                @Query("address_id") String address_id
    );

    //查询收货地址
    @POST("allAddress/list.do")
    Observable<ResultModel<List<AddressListBean>>> getaddresslist(@Query("user_token") String user_token);

    //
    //删除收货地址
    @POST("allAddress/delete.do")
    Observable<ResultModel<String>> deladdress(@Query("user_token") String user_token,
                                               @Query("address_id") String address_id);

    //发布采购获取默认地区
    @POST("ctBuyHostorder/getMarket.do")
    Observable<ResultModel<List<ShiChangBean>>> getmorendiqu(@Query("user_token") String user_token, @Query("LevelState") String LevelState);

    //发采购商品名模糊查询
    @POST("ctBuyHostorder/getname.do")
    Observable<ResultModel<List<FCGName>>> getfcgname(@Query("user_token") String user_token,
                                                      @Query("name") String name);

    //发采购商品规格
    @POST("allSpec/list.do")
    Observable<ResultModel<List<FCGGuige>>> getfcgguige(@Query("classify_id") String classify_id);

    //发采购保存商品
    @POST("ctBuyHostorder/save.do")
    Observable<ResultModel<FCGSaveFanHuiBean>> getfcgsave(@Query("user_token") String user_token,
                                                          @Query("special_commodity") String special_commodity,
                                                          @Query("market_id") String market_id,
                                                          @Query("sort_id") String sort_id,
                                                          @Query("purchase_id") String purchase_id,
                                                          @Query("son_order_id") String son_order_id,
                                                          @Query("pack_standard_id") String pack_standard_id,
                                                          @Query("purchase_name") String purchase_name,
                                                          @Query("count") String count);

    //添加采购商品
    @POST("ctBuyHostorder/save.do")
    Observable<ResultModel<CaiGouDanBean.FllistBean.SonorderlistBean>> addfcg(@Query("user_token") String user_token,
                                                                              @Query("special_commodity") String special_commodity,
                                                                              @Query("market_id") String market_id,
                                                                              @Query("sort_id") String sort_id,
                                                                              @Query("purchase_id") String purchase_id,
                                                                              @Query("son_order_id") String son_order_id,
                                                                              @Query("pack_standard_id") String pack_standard_id,
                                                                              @Query("purchase_name") String purchase_name,
                                                                              @Query("count") String count);

    //企业类别
    @POST("allDictionary/getparent_number.do")
    Observable<ResultModel<List<QiYeLeiBieBean>>> getqylb();

    //企业规模
    @POST("allDictionary/getscale.do")
    Observable<ResultModel<List<QiYeGuiMoBean>>> getqygm();

    //企业录入(业务员)
    @POST("allCompany/add.do")
    Observable<ResultModel<String>> qiyeluru(@Query("user_token") String user_token,
                                             @Query("company_name") String company_name,
                                             @Query("province") String province,
                                             @Query("city") String city,
                                             @Query("region") String region,
                                             @Query("street") String street,
                                             @Query("specific_address") String specific_address,
                                             @Query("salesman_duty") String salesman_duty,
                                             @Query("photo") String photo,
                                             @Query("parent_number") String parent_number,
                                             @Query("scale") String scale,
                                             @Query("telephone") String telephone);

    //企业修改(业务员)
    @POST("allCompany/updateCompany.do")
    Observable<ResultModel<String>> qiyexiugai(@Query("user_token") String user_token,
                                               @Query("company_id") String company_id,
                                               @Query("company_name") String company_name,
                                               @Query("province") String province,
                                               @Query("city") String city,
                                               @Query("region") String region,
                                               @Query("street") String street,
                                               @Query("specific_address") String specific_address,
                                               @Query("photo") String photo,
                                               @Query("parent_number") String parent_number,
                                               @Query("scale") String scale,
                                               @Query("telephone") String telephone);

    //查询企业列表（业务员）
    @POST("allCompany/queryAll.do")
    Observable<ResultModel<List<QiYeLieBiaoBean>>> getqiyeliebiao(@Query("user_token") String user_token,
                                                                  @Query("type") String type,
                                                                  @Query("pageNumber") String pageNumber);

    //商家模糊查询
    @POST("allCompany/queryAll.do")
    Observable<ResultModel<List<QiYeLieBiaoBean>>> getShangjiaList(@Query("user_token") String user_token,
                                                                  @Query("type") String type,
                                                                  @Query("province") String province,
                                                                  @Query("city") String city,
                                                                  @Query("region") String region,
                                                                  @Query("street") String street,
                                                                  @Query("role") String role,
                                                                  @Query("parent_number") String parent_number,
                                                                  @Query("company_name") String company_name,
                                                                  @Query("pageNumber") String pageNumber);

    //查询。。企业列表（业务员）
    @POST("allCompany/selectType.do")
    Observable<ResultModel<List<QiYeLieBiaoBean>>> getqiyedaicanshu(@Query("user_token") String user_token,
                                                                    @Query("company_name") String company_name,
                                                                    @Query("parent_number") String parent_number,
                                                                    @Query("type") String type);

    //删除企业（业务员）
    @POST("allCompany/delete.do")
    Observable<ResultModel<String>> delqiye(@Query("user_token") String user_token,
                                            @Query("company_id") String company_id);

    //商品详情
    @POST("gyCommodity/getCommodity.do")
    Observable<ResultModel<SPXiangQingBean>> getspxiangqing(@Query("user_token") String user_token,
                                                            @Query("commodity_id") String commodity_id);

    //商品收藏
    @POST("ctCollectCommodity/save.do")
    Observable<ResultModel<String>> shoucang(@Query("user_token") String user_token,
                                             @Query("commodity_id") String company_id);

    //取消商品收藏
    @POST("ctCollectCommodity/delete.do")
    Observable<ResultModel<String>> quxiaoshoucang(@Query("user_token") String user_token,
                                                   @Query("collect_id") String collect_id);

    //订单
    @POST("Ordermain/list.do")
    Observable<ResultModel<List<DingDanBean>>> getdingdan(@Query("user_token") String user_token,
                                                          @Query("state") String state, @Query("pageNumber") Integer pageNumber);

    //    //需求单主表
//    @POST("ctBuyHostorder/queryByHost.do")
//    Observable<ResultModel<List<DingDanBean>>> getdingdan(@Query("user_token") String user_token,
//                                                    @Query("order_audit_state") String order_audit_state);
    //需求单系统推荐信息
    @POST("ctBuyHostorder/updatebybossall.do")
    Observable<ResultModel<XiTongTuiJianBean>> getshenpi(@Query("user_token") String user_token,
                                                         @Query("market_id") String market_id,
                                                         @Query("son_order_id") String son_order_id);

    //需求单历史收藏购买记录
    @POST("ctBuyHostorder/queryByGetListcs.do")
    Observable<ResultModel<LiShiJiLuBean>> getlishi(@Query("user_token") String user_token,
                                                    @Query("market_id") String market_id,
                                                    @Query("son_order_id") String son_order_id);

    //采购单列表
    @POST("ctBuyHostorder/queryByHost.do")
    Observable<ResultModel<List<CaiGouDanBean>>> getcaigoudanlist(@Query("user_token") String user_token,
                                                                  @Query("order_audit_state") String order_audit_state,
                                                                  @Query("purchase_id") String purchase_id);

    //推荐商品
    @POST("gyCommodity/queryCommend.do")
    Observable<ResultModel<List<TuiJianBean>>> getweinituijian(@Query("user_token") String user_token,
                                                               @Query("comend_address") String comend_address);

    //店铺展示
    @POST("allCompany/selectdpsy.do")
    Observable<ResultModel<DianPuZhanShiBean>> getdianpuzhanshi(@Query("user_token") String user_token,
                                                                @Query("company_id") String company_id,
                                                                @Query("pageNumber") String pageNumber,
                                                                @Query("sortOrder") String sortOrder);

    //分类名称
    @POST("ctObserver/chooseType.do")
    Observable<ResultModel<List<FenLeiMingChengBean>>> getfenleimingcheng();

    //规格
    @POST("allSpec/list.do")
    Observable<ResultModel<List<FbspGuiGeBean>>> getguige(@Query("classify_id") String classify_id);

    //添加商品
    @POST("gyCommodity/save.do")
    Observable<ResultModel<String>> fabushangpin(@Query("user_token") String user_token,
                                                 @Query("company_id") String company_id,
                                                 @Query("deputyPicture") String deputyPicture,
                                                 @Query("pack_standard_two") String pack_standard_two,
                                                 @Query("pack_standard_tree") String pack_standard_tree,
                                                 @Query("ration_one") String ration_one,
                                                 @Query("pice_one") String pice_one,
                                                 @Query("inventory") String inventory,
                                                 @Query("origin") String origin,
                                                 @Query("level") String level,
                                                 @Query("apply") String apply,
                                                 @Query("proportion") String proportion,
                                                 @Query("type_one_id") String type_one_id,
                                                 @Query("goods") String goods,
                                                 @Query("commodity_state") String commodity_state,
                                                 @Query("commodity_name") String commodity_name,
                                                 @Query("type_two_id") String type_two_id,
                                                 @Query("type_tree_id") String type_tree_id,
                                                 @Query("hostPicture") String hostPicture,
                                                 @Query("spec_describe") String spec_describe,
                                                 @Query("price") String price);

    //编辑商品
    @POST("gyCommodity/update.do")
    Observable<ResultModel<String>> updateshangpin(@Query("user_token") String user_token,
                                                   @Query("commodity_id") String commodity_id,
                                                   @Query("company_id") String company_id,
                                                   @Query("ftPicture") List<String> ftPicture,
                                                   @Query("pack_standard_two") String pack_standard_two,
                                                   @Query("pack_standard_tree") String pack_standard_tree,
                                                   @Query("ration_one") String ration_one,
                                                   @Query("pice_one") String pice_one,
                                                   @Query("inventory") String inventory,
                                                   @Query("origin") String origin,
                                                   @Query("level") String level,
                                                   @Query("apply") String apply,
                                                   @Query("proportion") String proportion,
                                                   @Query("type_one_id") String type_one_id,
                                                   @Query("goods") String goods,
                                                   @Query("commodity_state") String commodity_state,
                                                   @Query("commodity_name") String commodity_name,
                                                   @Query("type_two_id") String type_two_id,
                                                   @Query("type_tree_id") String type_tree_id,
                                                   @Query("hostPicture") String hostPicture,
                                                   @Query("spec_describe") String spec_describe,
                                                   @Query("price") String price);

    //添加购物车
    @POST("ctShoppingCart/save.do")
    Observable<ResultModel<String>> addcar(@Query("user_token") String user_token,
                                           @Query("commodity_id") String commodity_id,
                                           @Query("number") String number,
                                           @Query("company_id") String company_id,
                                           @Query("shopping_id") String shopping_id,
                                           @Query("pack_standard") String pack_standard);

    //修改购物车商品数量
    @POST("ctShoppingCart/addnum.do")
    Observable<ResultModel<String>> editcar(@Query("user_token") String user_token,
                                            @Query("shopping_id") String shopping_id,
                                            @Query("type") String type,
                                            @Query("number") String number
    );

    //送达时间
    @POST("allDictionary/selectByTime.do")
    Observable<ResultModel<List<SongDaShiJianBean>>> getsongdashijian();

    //提交订单
    @POST("Ordermain/addOrder.do")
    Observable<ResultModel<String>> tijiaodingdan(@Query("user_token") String user_token,//
                                                  @Query("total_price") String total_price,//支付金额
                                                  @Query("pay_type") String pay_type,//支付方式
                                                  @Query("freight_fee") String freight_fee,//运费
                                                  @Query("balance_pice") String balance_pice,//余额
                                                  @Query("deliver_address") String deliver_address,//地址id
                                                  @Query("arrival_time") String arrival_time,//到货时间
                                                  @Query("shopping_id") String shopping_id,//shopping_id
                                                  @Query("remarke") String remarke,//留言
                                                  @Query("feelist") String list);//市场数组

    //提交订单
    @POST("Ordermain/saveUserOrder.do")
    Observable<ResultModel<String>> caigoutijiaodingdan(@Query("user_token") String user_token,//
                                                        @Query("total_price") String total_price,//支付金额
                                                        @Query("pay_type") String pay_type,//支付方式
                                                        @Query("freight_fee") String freight_fee,//运费
                                                        @Query("balance_pice") String balance_pice,//余额
                                                        @Query("deliver_address") String deliver_address,//地址id
                                                        @Query("arrival_time") String arrival_time,//到货时间
                                                        @Query("son_order_id") String son_order_id,//son_order_id
                                                        @Query("commodity_id") String commodity_id,//commodity_id
                                                        @Query("remarke") String remarke,//留言
                                                        @Query("feelist") String list,//市场数组
                                                        @Query("special_commodity") String special_commodity);//特殊要求

    //查询余额
    @POST("payHistory/getBalance.do")
    Observable<ResultModel<Double>> chaxunyue(@Query("user_token") String user_token);

    // 获取采购单选择的商品的列表
    @POST("ctBuyHostorder/updatebyboss.do")
    Observable<ResultModel<ShenPiQuanXuanBean>> shenpitongguo(@Query("user_token") String user_token,
                                                              @Query("list") String list,
                                                              @Query("purchase_id") String purchase_id,
                                                              @Query("type") String type
    );

    /// /采购单审批失败
    @POST("ctBuyHostorder/updateByPurchaseId.do")
    Observable<ResultModel<String>> shenpishibai(@Query("user_token") String user_token,
                                                 @Query("audit_ps") String audit_ps,
                                                 @Query("purchase_id") String purchase_id
    );

    /// /获取更多商家
    @POST("ctBuyHostorder/queryByCompany.do")
    Observable<ResultModel<List<XiTongTuiJianBean.CcListBean>>> getgengduoshangjia(@Query("user_token") String user_token,
                                                                                   @Query("son_order_id") String son_order_id,
                                                                                   @Query("market_id") String market_id
    );

    /// /获取订单商品信息
    @POST("ctBuyHostorder/order.do")
    Observable<ResultModel<List<QueRenDingDanShangPinBean>>> getsplist(@Query("user_token") String user_token,
                                                                       @Query("son_order_id") String son_order_id,
                                                                       @Query("commodity_id") String commodity_id,
                                                                       @Query("type") String type,
                                                                       @Query("shopping_id") String shopping_id,
                                                                       @Query("company_id") String company_id
    );

    /// /采购单价格
    @POST("ctBuyHostorder/queryMoney.do")
    Observable<ResultModel<String>> getcaigoudanjiage(@Query("user_token") String user_token,
                                                      @Query("son_order_id") String son_order_id,
                                                      @Query("commodity_id") String commodity_id);

    /// /提交支付
    @POST("payHistory/save.do")
    Observable<ResultModel<String>> tijiaozhifu(@Query("user_token") String user_token,
                                                @Query("typeA") String typeA,//支付方式 1为余额支付 2微信支付 3支付宝支付 4当余额不足时支付
                                                @Query("order_id") String order_id,
                                                @Query("typeB") String typeB);//1为采购单 2为订单 /// /提交支付

    //重发抢单
    @POST("ctBuyHostorder/refresh.do")
    Observable<ResultModel<String>> chongfaqiangdan(@Query("son_order_id") String son_order_id,
                                                    @Query("user_token") String user_token,
                                                    @Query("market_id") String market_id);

    //查询可抢单信息
    @POST("ctBuyHostorder/companyBySonorder.do")
    Observable<ResultModel<List<QiangDanBean>>> keqiangdan(@Query("user_token") String user_token);

    //抢单列表
    @POST("ctBuyHostorder/userCompany.do")
    Observable<ResultModel<List<QiangDanBean>>> qiangdanlist(@Query("user_token") String user_token,
                                                             @Query("state_type") String state_type);

    //抢单
    @POST("ctBuyHostorder/getcompanyid.do")
    Observable<ResultModel<String>> qiangdan(@Query("user_token") String user_token,
                                             @Query("commodity_id") String commodity_id,
                                             @Query("quote_price_id") String quote_price_id,
                                             @Query("append_money") String append_money);

    //获取抢单商家商品
    @POST("gyCommodity/queryByTypeTreeId.do")
    Observable<ResultModel<List<ShangPinBean>>> qiangdanshangpin(@Query("company_id") String user_token,
                                                                 @Query("type_tree_id") String type_tree_id,
                                                                 @Query("son_order_id") String son_order_id);

    //获取全部分类
    @POST("ctObserver/Type.do")
    Observable<ResultModel<List<FenLeiBean>>> getfenlei();

    //获取市场
    @POST("sy/getMarket.do")
    Observable<ResultModel<AllShiChangBean>> getallshichang(@Query("province") String province,
                                                            @Query("city") String city);

    //关注店铺
    @POST("ctAttention/save.do")
    Observable<ResultModel<String>> guanzhudianpu(@Query("user_token") String user_token,
                                                  @Query("be_concerned_id") String be_concerned_id);

    //删除采购单子表信息
    @POST("ctBuySonorder/deleteById.do")
    Observable<ResultModel<String>> delsonorderid(@Query("user_token") String user_token,
                                                  @Query("son_order_id") String son_order_id);

    //删除需求单-单条信息
    @POST("ctBuyHostorder/deleteById.do")
    Observable<ResultModel<String>> delxuqiudan(@Query("user_token") String user_token,
                                                @Query("purchase_id") String son_order_id);

    //更新抢单结果
    @POST("ctBuyHostorder/tureorfalse.do")
    Observable<ResultModel<String>> gengxinqiangdan(@Query("user_token") String user_token,
                                                    @Query("son_order_id") String son_order_id,
                                                    @Query("commodity_id") String commodity_id
    );

    //我的首页数据
    @POST("allCompanyAccount/queryByuserToken.do")
    Observable<ResultModel<WoDeBean>> getwode(@Query("user_token") String user_token);

    //收藏商品列表
    @POST("ctCollectCommodity/queryCollectList.do")
    Observable<ResultModel<List<ShouCangBean>>> getshoucanglist(@Query("user_token") String user_token,
                                                                @Query("pageNumber") String pageNumber,
                                                                @Query("commodity_name") String commodity_name);

    //走势图
    @POST("sysStatistics/tjList.do")
    Observable<ResultModel<List<ZouShiTuBean>>> zoushitu(@Query("user_token") String user_token,
                                                         @Query("classify_id") String classify_id,
                                                         @Query("mark_id") String mark_id,
                                                         @Query("startDate") String startDate,
                                                         @Query("endDate") String endDate);

    //通过city查市场列表
    @POST("allMarket/markeList.do")
    Observable<ResultModel<List<ShiChangBean>>> getShiChangByCity(@Query("user_token") String user_token, @Query("city") String city);

    //查询店铺所有评价
    @POST("gyCommentReply/getGoodsXq.do")
    Observable<ResultModel<List<XQPingJiaBean>>> getpingjia(@Query("user_token") String user_token,
                                                            @Query("company_id") String company_id,
                                                            @Query("pageNumber") String pageNumber,
                                                            @Query("sortOrder") String sortOrder);

    //供货端商品列表
    @POST("gyCommodity/queryGysGoods.do")
    Observable<ResultModel<ShangPinGuanLiBean>> getshangpinguanli(@Query("user_token") String user_token,
                                                                  @Query("commodity_name") String commodity_name,
                                                                  @Query("goods") String goods,
                                                                  @Query("sortOrder") String sortOrder,
                                                                  @Query("pageNumber") int pageNumber);

    //供货端商品列表删除
    @POST("gyCommodity/delete.do")
    Observable<ResultModel<String>> ghdspdel(@Query("user_token") String user_token,
                                             @Query("commodity_id") String commodity_id,
                                             @Query("approval_state") String approval_state);

    //查询全部物流信息
    @POST("wl/list.do")
    Observable<ResultModel<WuLiuObjBean<WuLiuBean>>> getWuliu(@Query("user_token") String user_token,
                                                              @Query("wl_cars_state") String wl_cars_state,
                                                              @Query("pageNumber") String count,
                                                              @Query("wl_order_state") String wl_order_state);

    //查询车辆类型
    @POST("wl/carsTypeList.do")
    Observable<ResultModel<List<CarsTypeBean>>> getCarsType(@Query("cars_type") String cars_type);

    //分配物流车
    @POST("wl/allotCar.do")
    Observable<ResultModel<String>> fenpeiWuLiu(@Query("user_token") String user_token,
                                                @Query("new_driver_name") String new_driver_name,
                                                @Query("new_driver_phone") String new_driver_phone,
                                                @Query("new_plate_number") String new_plate_number,
                                                @Query("marketProvince") String marketProvince,
                                                @Query("marketCity") String marketCity,
                                                @Query("car_type_id") String car_type_id,
                                                @Query("wl_cars_order_number") String wl_cars_order_number);

    //变更物流车
    @POST("wl/modity.do")
    Observable<ResultModel<String>> changeWuLiu(@Query("user_token") String user_token,
                                                @Query("wl_cars_id") String wl_cars_id,
                                                @Query("new_driver_name") String new_driver_name,
                                                @Query("new_driver_phone") String new_driver_phone,
                                                @Query("new_plate_number") String new_plate_number,
                                                @Query("marketProvince") String marketProvince,
                                                @Query("marketCity") String marketCity,
                                                @Query("car_type_id") String car_type_id,
                                                @Query("wl_cars_order_number") String wl_cars_order_number);

    //查询供货订单列表
    @POST("gyOreder/queryByList.do")
    Observable<ResultModel<List<GHOrderBean>>> getGHOrderList(@Query("user_token") String user_token,
                                                              @Query("state") String state, @Query("pageNumber") Integer pageNumber);

    //删除供货订单
    @POST("gyOreder/delete.do")
    Observable<ResultModel<String>> delGHOrder(@Query("user_token") String user_token,
                                               @Query("gy_order_id") String gy_order_id);

    //运费查询
    @POST("Ordermain/qyeryByFreightFee.do")
    Observable<ResultModel<List<YunFeiBean>>> getYunFei(@Query("commodity_id") String commodity_id,
                                                        @Query("deliver_address") String deliver_address,
                                                        @Query("number") String number);

    //获取司机物流详情列表
    @POST("wl/wlByOrderNumber.do")
    Observable<ResultModel<List<SiJiWLXQBean>>> getSJWuLiuXQ(@Query("wl_cars_order_number") String wl_cars_order_number,
                                                             @Query("pageNumber") String pageNumber);

    //获取二维码列表
    @POST("twocode/list.do")
    Observable<ResultModel<GHOrderBean>> getQrCodeList(@Query("gy_order_id") String gy_order_id);

    //生成二维码
    @POST("twocode/save.do")
    Observable<ResultModel<String>> createQrCode(@Query("user_token") String user_token,
                                                 @Query("gy_order_id") String gy_order_id);

    //打包完成
    @POST("twocode/isTrue.do")
    Observable<ResultModel<String>> packageEnd(@Query("user_token") String user_token,
                                               @Query("gy_order_id") String gy_order_id);

    //二维码作废
    @POST("twocode/delete.do")
    Observable<ResultModel<String>> delQrCode(@Query("user_token") String user_token,
                                              @Query("twocode_id") String twocode_id);

    //更新二维码状态
    @POST("twocode/update.do")
    Observable<ResultModel<String>> updateQrCode(@Query("user_token") String user_token, @Query("twocode_id") String twocode_id,
                                                 @Query("gy_order_id") String gy_order_id, @Query("type") String type,
                                                 @Query("onlyCode") String onlyCode);

    //获取商品管理编辑信息
    @POST("gyCommodity/getId.do")
    Observable<ResultModel<EditorShangPinBean>> editorShangPin(@Query("commodity_id") String commodity_id);

    //意见反馈
    @POST("feedBack/save.do")
    Observable<ResultModel<String>> yijianfankui(@Query("user_token") String user_token, @Query("telephone") String telephone,
                                                 @Query("content") String content);

    //取消关注
    @POST("ctAttention/delete.do")
    Observable<ResultModel<String>> quxiaoguanzhudianpu(@Query("user_token") String user_token,
                                                        @Query("be_concerned_id") String be_concerned_id);

    //获取客服电话
    @POST("allCompany/selectphone.do")
    Observable<ResultModel<PhoneBean>> getPhone(@Query("user_token") String user_token,
                                                @Query("company_id") String company_id);

    //店铺关注列表
    @POST("ctAttention/selectbyname.do")
    Observable<ResultModel<List<DianPuBean>>> getDianPuGuanZhuList(@Query("user_token") String user_token, @Query("pageNumber") String pageNumber);

    //查询浏览记录
    @POST("ctBrowseHistory/list.do")
    Observable<ResultModel<List<LiuLanJiLuBean>>> getLiuLanJiLuList(@Query("user_token") String user_token, @Query("pageNumber") String pageNumber);

    //删除浏览记录
    @POST("ctBrowseHistory/delete.do")
    Observable<ResultModel<String>> delLiuLanJiLu(@Query("user_token") String user_token, @Query("browse_id") String browse_id);

    //获取二维码列表
    @POST("twocode/queryOnlyCode.do")
    Observable<ResultModel<List<WeiYiQrCodeBean>>> getWeiYiQrCodeList(@Query("user_token") String user_token, @Query("gy_order_id") String gy_order_id,
                                                                      @Query("order_id") String order_id);

    //修改需求单商品
    @POST("ctBuySonorder/updateBySonorder.do")
    Observable<ResultModel<CaiGouDanBean.FllistBean.SonorderlistBean>> editorXuqiudan(@Query("count") String count, @Query("user_token") String user_token, @Query("son_order_id") String son_order_id,
                                                                                      @Query("special_commodity") String special_commodity, @Query("pack_standard_id") String pack_standard_id);

    //获取审批单条数据
    @POST("ctBuyHostorder/queryByPurchase_id.do")
    Observable<ResultModel<List<CaiGouDanBean.FllistBean>>> getShenpiItem(@Query("purchase_id") String purchase_id);

    //我的银行卡列表
    @POST("qyBankCard/getaccount_person.do")
    Observable<ResultModel<List<YinHangKaBean>>> getMyBankCardList(@Query("user_token") String user_token);

    //选择银行卡列表
    @POST("allDictionary/YHKtype.do")
    Observable<ResultModel<List<XuanZeYinHangKaBean>>> getBankCardList();

    //资质认证
    @POST("allCompany/zizhi.do")
    Observable<ResultModel<String>> getZiZhi(@Query("user_token") String user_token);

    //添加银行卡
    @POST("qyBankCard/save.do")
    Observable<ResultModel<String>> addBankCard(@Query("user_token") String user_token,
                                                @Query("bank_account") String bank_account,
                                                @Query("account_person") String account_person,
                                                @Query("id_number") String id_number,
                                                @Query("phone") String phone,
                                                @Query("account_bank") String account_bank);

    //删除银行卡
    @POST("qyBankCard/delete.do")
    Observable<ResultModel<String>> delBankCard(@Query("user_token") String user_token,
                                                @Query("bank_id") String bank_id);

    //获取订单详情
    @POST("Ordermain/listDetal.do")
    Observable<ResultModel<DdxqListBean>> getOrderXiangqing(@Query("user_token") String user_token,
                                                            @Query("order_id") String order_id);

    //删除订单
    @POST("Ordermain/update.do")
    Observable<ResultModel<String>> delOrder(@Query("user_token") String user_token,
                                             @Query("order_id") String order_id);

    //确认订单
    @POST("payHistory/tureMoney.do")
    Observable<ResultModel<String>> confirmOrder(@Query("user_token") String user_token,
                                                 @Query("order_id") String order_id,
                                                 @Query("company_id") String company_id);

    //新建商品模糊查询
    @POST("gyCommodity/queryCommodityname.do")
    Observable<ResultModel<List<ShangPinSousuoMohuBean>>> searchSpname(@Query("user_token") String user_token,
                                                                       @Query("commodity_name") String commodity_name);

    //供应端退款
    @POST("gyOreder/returnMoney.do")
    Observable<ResultModel<String>> orderTuikuan(@Query("user_token") String user_token,
                                                 @Query("gy_order_id") String gy_order_id,
                                                 @Query("price") String price,
                                                 @Query("return_remarke") String return_remarke);

    //资质认证回显
    @POST("allCompany/getAptitude.do")
    Observable<ResultModel<GetZiZhiBean>> getZizhiShow(@Query("user_token") String user_token);

    //申请资质认证
    @POST("allCompany/referAptitude.do")
    Observable<ResultModel<String>> saveZizhi(@Query("user_token") String user_token,
                                              @Query("id_number") String id_number,
                                              @Query("legal_person") String legal_person,
                                              @Query("duty_paragraph") String duty_paragraph);

    //切换角色
    @POST("allCompanyAccount/updateByType.do")
    Observable<ResultModel<String>> qiehuan(@Query("user_token") String user_token);

    //获取账户信息认证资料
    @POST("allCompany/queryByCompany.do")
    Observable<ResultModel<ZhangHuRenZhengBean>> getGerenrenzheng(@Query("user_token") String user_token);

    //余额详情
    @POST("payHistory/getBalance.do")
    Observable<ResultModel<String>> getYue(@Query("user_token") String user_token);

    //交易明细列表
    @POST("payHistory/list.do")
    Observable<ResultModel<List<JYMXBean>>> getJYMXList(@Query("user_token") String user_token,

                                                        @Query("state") String state, @Query("timeState") String timeState,
                                                        @Query("pageNumber") Integer pageNumber);

    //交易明细
    @POST("payHistory/getId.do")
    Observable<ResultModel<JYMXItemBean>> getJYMXItem(@Query("user_token") String user_token,
                                                      @Query("history_id") String history_id);

    //提现
    @POST("payHistory/withdrawCash.do")
    Observable<ResultModel<String>> tixian(@Query("user_token") String user_token,
                                           @Query("pay_money") String pay_money,
                                           @Query("collect_bank_id") String collect_bank_id);

    //确认收货
    @POST("payHistory/ture.do")
    Observable<ResultModel<String>> querenshouhuo(@Query("user_token") String user_token,
                                                  @Query("order_id") String order_id);

    //获取经纬度
    @POST("Ordermain/jingweidu.do")
    Observable<ResultModel<String>> getJingweidu(@Query("addr") String addr);

    //获取运费结算列表
    @POST("wlSettlement/list.do")
    Observable<ResultModel<YunFeiJieSuanBean>> getYunfeiList(@Query("user_token") String user_token,
                                                             @Query("settle_accounts_state") String settle_accounts_state,
                                                             @Query("wl_cars_order_id") String wl_cars_order_id);

    //运费结算
    @POST("wlSettlement/settlement.do")
    Observable<ResultModel<String>> yunfeijiesuan(@Query("user_token") String user_token,
                                                  @Query("wl_cars_order_id") String wl_cars_order_id,
                                                  @Query("freight_fee") String freight_fee,
                                                  @Query("actual_freight") String actual_freight);

    //提交采购单
    @POST("ctBuyHostorder/saveTime.do")
    Observable<ResultModel<String>> postCaigoudan(@Query("purchase_id") String purchase_id);

    //获取订单号
    @POST("gyOreder/account.do")
    Observable<ResultModel<String>> getOrderNumber(@Query("user_token") String user_token);

    //采购单历史
    @POST("ctBuyHostorder/queryByPurchase_name.do")
    Observable<ResultModel<List<FCGSaveFanHuiBean>>> getCaigouming(@Query("user_token") String user_token,
                                                                   @Query("purchase_name") String purchase_name);

    //匹配车牌
    @POST("wl/matchPlate.do")
    Observable<ResultModel<List<ChePaiBean>>> getChepai(@Query("new_plate_number") String new_plate_number,
                                                        @Query("new_wl_cars_type") String new_wl_cars_type);

    //评价标签
    @POST("allDictionary/selectEvaluate.do")
    Observable<ResultModel<List<PingJiaLableBean>>> getPingjiaLable();

    //发表评价
    @POST("gyCommentReply/save.do")
    Observable<ResultModel<String>> postPingjia(@Query("user_token") String user_token,
                                                @Query("anonymous") String anonymous,
                                                @Query("order_id") String order_id,
                                                @Query("star_evaluation") String star_evaluation,
                                                @Query("comment_text") String comment_text,
                                                @Query("company_id") String company_id,
                                                @Query("shop_rowgu") String shop_rowgu);

    //查询我的所有评价
    @POST("gyCommentReply/getwdpj.do")
    Observable<ResultModel<List<XQPingJiaBean>>> getWodepingjia(@Query("user_token") String user_token,
                                                                @Query("pageNumber") String pageNumber);

    // 结算结果列表
    @POST("wlSettlement/dataList.do")
    Observable<ResultModel<List<JieSuanJirGuoBean>>> getJiesuanjieguoList(@Query("user_token") String user_token,
                                                                          @Query("pageNumber") String pageNumber);

    //采购单背景
    @POST("ctBuyHostorder/querycgurl.do")
    Observable<ResultModel<String>> getBg();

    //供应端个人中心
    @POST("allCompanyAccount/queryToken.do")
    Observable<ResultModel<WoDeBean>> getGydShouye(@Query("user_token") String user_token);

    //添加子账户
    @POST("allCompanyAccount/adds.do")
    Observable<ResultModel<String>> addZizhanghu(@Query("user_token") String user_token,
                                                 @Query("company_id") String company_id,
                                                 @Query("principal") String principal,
                                                 @Query("telephone") String telephone,
                                                 @Query("son_role_id") String son_role_id,
                                                 @Query("password") String password);

    //子账户角色权限
    @POST("appRoleRel/list.do")
    Observable<ResultModel<List<RoleBean>>> getRoleList(@Query("user_token") String user_token,
                                                        @Query("son_role_id") String son_role_id);

    //子账户角色
    @POST("sonAccountRole/list.do")
    Observable<ResultModel<List<JueSeBean>>> getJueseList(@Query("user_token") String user_token);

    //子账户未添加角色
    @POST("sonAccountRole/kyList.do")
    Observable<ResultModel<List<JueSeBean>>> getJuese(@Query("user_token") String user_token,
                                                      @Query("account_id") String account_id);

    //添加角色权限
    @POST("appRoleRel/save.do")
    Observable<ResultModel<String>> addJueseQuanxian(@Query("user_token") String user_token,
                                                     @Query("son_role_id") String son_role_id,
                                                     @Query("role_id") String role_id);

    //子账户列表
    @POST("allCompanyAccount/sonList.do")
    Observable<ResultModel<List<ZiZhangHuBean>>> getZizhanghuList(@Query("user_token") String user_token);

    //子账户角色权限
    @POST("sonAccountRole/queryRoleList.do")
    Observable<ResultModel<List<RoleBean>>> getRoleTwoList(@Query("user_token") String user_token,
                                                           @Query("son_role_id") String son_role_id);

    //子账户角色详情
    @POST("allCompanyAccount/queryAccountDetail.do")
    Observable<ResultModel<ZiZhangHuDetailsBean>> getJueseDetails(@Query("user_token") String user_token,
                                                                  @Query("account_id") String account_id);

    //修改子账户角色
    @POST("appRoleRel/updateSonAccount.do")
    Observable<ResultModel<String>> changeJuese(@Query("user_token") String user_token,
                                                @Query("account_id") String account_id,
                                                @Query("son_role_id") String son_role_id);

    //删除子账户角色
    @POST("appRoleRel/delete.do")
    Observable<ResultModel<String>> delJuese(@Query("user_token") String user_token,
                                             @Query("account_id") String account_id);

    //更改营业状态
    @POST("allCompany/update.do")
    Observable<ResultModel<String>> changeYingyeState(@Query("user_token") String user_token,
                                                      @Query("business_state") String business_state);

    //审核成功页面
    @POST("ctBuySonorder/queryByState.do")
    Observable<ResultModel<SPTGBean>> getShenhechenggong(@Query("user_token") String user_token,
                                                         @Query("purchase_id") String purchase_id);

    //修改密码
    @POST("allCompanyAccount/update_user.do")
    Observable<ResultModel<String>> changePwd(@Query("telephone") String telephone,
                                              @Query("password") String password,
                                              @Query("type") String type,
                                              @Query("new_password") String new_password,
                                              @Query("yzm") String yzm);

    //验证采购单是否重复
    @POST("ctBuyHostorder/queryonleName.do")
    Observable<ResultModel<String>> yanzhengCgd(@Query("user_token") String user_token,
                                                @Query("purchase_name") String purchase_name);

    //门店信息
    @POST("allCompanyAccount/queryBymd.do")
    Observable<ResultModel<GetMenDianBean>> mendianShow(@Query("user_token") String user_token);

    //门店商品列表
    @POST("allCompany/queryGoods.do")
    Observable<ResultModel<List<DianPuZhanShiBean.CompanyListBean>>> getDpspList(@Query("user_token") String user_token,
                                                                                 @Query("company_id") String company_id,
                                                                                 @Query("pageNumber") String pageNumber,
                                                                                 @Query("sortOrder") String sortOrder);

    //门店商品搜索列表
    @POST("allCompany/selectCt.do")
    Observable<ResultModel<List<DianPuZhanShiBean.CompanyListBean>>> getDpspListSearch(@Query("classify_name") String classify_name,
                                                                                       @Query("company_id") String company_id);

    //微信支付
    @POST("pay/getWXPay.do")
    Observable<ResultModel<WXPayBean>> getWXPay(@Query("order_id") String order_id,
                                                @Query("total_fee") String total_fee);

    //评价分类列表
    @POST("gyCommentReply/replyCount.do")
    Observable<ResultModel<PingJiaTypeListBean>> getPingjiaTypeNumber(@Query("company_id") String company_id);

    //合并采购单
    @POST("ctBuyHostorder/AddSingle.do")
    Observable<ResultModel<String>> hebingCaigoudan(@Query("user_token") String user_token,
                                                    @Query("purchase_id") String purchase_id,
                                                    @Query("purchase_name") String purchase_name);

    //获取全部市场
    @POST("sy/getRegisterMarket.do")
    Observable<ResultModel<List<QuanBuShiChangBean>>> getShichangList(@Query("province") String province,
                                                                      @Query("city") String city,
                                                                      @Query("region") String region,
                                                                      @Query("market_name") String market_name,
                                                                      @Query("pageNumber") String pageNumber);
}
