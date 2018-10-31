package com.mingmen.mayi.mayibanjia.bean;

import java.math.BigDecimal;
import java.util.List;


/**
 * 补全注释
 */
public class ReUserOrder  {
	
	
	private String order_id;//   订单id
	private String order_number;//   订单号
	private String account_id;//   账户id
	private String company_id;//   企业id
	private String state;//   订单状态
	private BigDecimal total_price;//   支付金额
	private BigDecimal balance_pice;//   平台余额
	private String pay_type;//   支付方式
	private String freight_fee;//运费
	private String end_time;//   订单结束时间
	private String create_time;//   创建日期
	private String creater;//   创建人
	private String remarke;//   备注
	private String status_type;
	private List<ReUserOrderDetal> list;
	private String deliver_address;
	private String arrival_time;//到货时间
	private String shopping_id;//购物车id
	
	public String getShopping_id() {
		return shopping_id;
	}
	public void setShopping_id(String shopping_id) {
		this.shopping_id = shopping_id;
	}

	/*插入子表所需*/
	private Integer acount;//   数量
	private BigDecimal price;//   价格
	private BigDecimal all_price;//   商品总价
	
	public String getArrival_time() {
		return arrival_time;
	}
	public void setArrival_time(String arrival_time) {
		this.arrival_time = arrival_time;
	}
	public Integer getAcount() {
		return acount;
	}
	public void setAcount(Integer acount) {
		this.acount = acount;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getAll_price() {
		return all_price;
	}
	public void setAll_price(BigDecimal all_price) {
		this.all_price = all_price;
	}
	public String getFreight_fee() {
		return freight_fee;
	}
	public void setFreight_fee(String freight_fee) {
		this.freight_fee = freight_fee;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public BigDecimal getTotal_price() {
		return total_price;
	}
	public void setTotal_price(BigDecimal total_price) {
		this.total_price = total_price;
	}
	public BigDecimal getBalance_pice() {
		return balance_pice;
	}
	public void setBalance_pice(BigDecimal balance_pice) {
		this.balance_pice = balance_pice;
	}
	public String getDeliver_address() {
		return deliver_address;
	}
	public void setDeliver_address(String deliver_address) {
		this.deliver_address = deliver_address;
	}
	public List<ReUserOrderDetal> getList() {
		return list;
	}
	public void setList(List<ReUserOrderDetal> list) {
		this.list = list;
	}
	public String getStatus_type() {
		return status_type;
	}
	public void setStatus_type(String status_type) {
		this.status_type = status_type;
	}

	private String user_token;//  token
	
	public String getCommodity_id() {
		return company_id;
	}
	public void setCommodity_id(String company_id) {
		this.company_id = company_id;
	}
	public String getUser_token() {
		return user_token;
	}
	public void setUser_token(String user_token) {
		this.user_token = user_token;
	}
	public String getOrder_id() {
	    return this.order_id;
	}
	public void setOrder_id(String order_id) {
	    this.order_id=order_id;
	}
	public String getOrder_number() {
	    return this.order_number;
	}
	public void setOrder_number(String order_number) {
	    this.order_number=order_number;
	}
	public String getAccount_id() {
	    return this.account_id;
	}
	public void setAccount_id(String account_id) {
	    this.account_id=account_id;
	}
	public String getState() {
	    return this.state;
	}
	public void setState(String state) {
	    this.state=state;
	}
	public String getPay_type() {
	    return this.pay_type;
	}
	public void setPay_type(String pay_type) {
	    this.pay_type=pay_type;
	}
	public String getEnd_time() {
	    return this.end_time;
	}
	public void setEnd_time(String end_time) {
	    this.end_time=end_time;
	}
	public String getCreate_time() {
	    return this.create_time;
	}
	public void setCreate_time(String create_time) {
	    this.create_time=create_time;
	}
	public String getCreater() {
	    return this.creater;
	}
	public void setCreater(String creater) {
	    this.creater=creater;
	}

	public String getRemarke() {
	    return this.remarke;
	}
	public void setRemarke(String remarke) {
	    this.remarke=remarke;
	}

	private class ReUserOrderDetal {

		private java.lang.String order_details_id;//   订单明细id
		private java.lang.String commodity_id;//   企业id
		private java.lang.String order_id;//   订单id
		private java.lang.Integer acount;//   数量
		private BigDecimal price;//   价格
		private BigDecimal all_price;//   商品总价
		private String spec_name;//最小的规格名称
		private String total_weight;//总重量
		private java.lang.String create_time;//   操作时间
		private String company_id;//店铺名称
		private String company_name;//店铺名称
		private String commodity_name;//商品名称
		private String count;//商品件数
		private String url;//图片地址
		private String freight_fee;//运费
		private String remarke;


		public String getRemarke() {
			return remarke;
		}

		public void setRemarke(String remarke) {
			this.remarke = remarke;
		}

		public String getCompany_id() {
			return company_id;
		}

		public void setCompany_id(String company_id) {
			this.company_id = company_id;
		}

		public String getFreight_fee() {
			return freight_fee;
		}

		public void setFreight_fee(String freight_fee) {
			this.freight_fee = freight_fee;
		}

		public String getSpec_name() {
			return spec_name;
		}

		public void setSpec_name(String spec_name) {
			this.spec_name = spec_name;
		}

		public String getTotal_weight() {
			return total_weight;
		}

		public void setTotal_weight(String total_weight) {
			this.total_weight = total_weight;
		}

		public String getCompany_name() {
			return company_name;
		}

		public void setCompany_name(String company_name) {
			this.company_name = company_name;
		}

		public String getCommodity_name() {
			return commodity_name;
		}

		public void setCommodity_name(String commodity_name) {
			this.commodity_name = commodity_name;
		}

		public String getCount() {
			return count;
		}

		public void setCount(String count) {
			this.count = count;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public java.lang.String getOrder_details_id() {
			return this.order_details_id;
		}

		public void setOrder_details_id(java.lang.String order_details_id) {
			this.order_details_id = order_details_id;
		}

		public java.lang.String getCommodity_id() {
			return commodity_id;
		}

		public void setCommodity_id(java.lang.String commodity_id) {
			this.commodity_id = commodity_id;
		}

		public java.lang.String getOrder_id() {
			return this.order_id;
		}

		public void setOrder_id(java.lang.String order_id) {
			this.order_id = order_id;

		}
	}
}

