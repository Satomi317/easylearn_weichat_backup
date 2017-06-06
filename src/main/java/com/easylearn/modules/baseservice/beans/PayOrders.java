package com.easylearn.modules.baseservice.beans;

public class PayOrders {

	/**
	 * 公众号Id：必须
	 */
	private String appid;
	/**
	 * 商户号：必须
	 */
	private String mch_id;
	/**
	 * 设备号：非必须
	 */
	private String device_info;
	/**
	 * 随机码：必须
	 */
	private String nonce_str;
	/**
	 * 签名：必须
	 */
	private String sign;
	/**
	 * 商品描述：必须
	 */
	private String body;
	/**
	 * 商品详情：非必须
	 */
	private String detail;
	/**
	 * 附加数据：非必须
	 */
	private String attach;
	/**
	 * 商户订单号：必须
	 */
	private String out_trade_no;
	/**
	 * 货币类型：非必须
	 */
	private String fee_type;
	/**
	 * 总金额：必须
	 */
	private String total_fee;
	/**
	 * 终端IP：必须
	 */
	private String spbill_create_ip;
	/**
	 * 交易开始时间：非必须
	 */
	private String time_start;
	/**
	 * 交易结束时间：非必须
	 */
	private String time_exipre;
	/**
	 * 商品标记：非必须
	 */
	private String goods_tag;
	/**
	 * 通知地址：必须
	 */
	private String notify_url;
	/**
	 * 交易类型：必须
	 */
	private String trade_type;
	/**
	 * 商品Id：非必须
	 */
	private String product_id;
	/**
	 * 用户标示：非必须
	 */
	private String openid;


	public PayOrders(){
		
	}
	
	/**
	 * 统一下单接口
	 * 只包含必填项的构造方法
	 * @param appid 公众号id
	 * @param mch_id 商户号
	 * @param nonce_str 随机号
	 * @param sign 签名
	 * @param body 商品描述
	 * @param out_trade_no 商户订单号
	 * @param total_fee 订单总金额(单位待确认是)
	 * @param spbill_create_ip APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
	 * @param notify_url 接收微信支付异步通知回调地址
	 * @param trade_type 交易类型=“JSAPI”
	 */
	public PayOrders(String appid,String mch_id,String nonce_str,String sign,String body,String out_trade_no,String total_fee
			,String spbill_create_ip,String notify_url,String trade_type){
		this.appid = appid;
		this.mch_id = mch_id;
		this.nonce_str = nonce_str;
		this.sign = sign;
		this.body = body;
		this.out_trade_no = out_trade_no;
		this.total_fee = total_fee;
		this.spbill_create_ip = spbill_create_ip;
		this.notify_url = notify_url;
		this.trade_type = trade_type;
	}
	
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_exipre() {
		return time_exipre;
	}

	public void setTime_exipre(String time_exipre) {
		this.time_exipre = time_exipre;
	}

	public String getGoods_tag() {
		return goods_tag;
	}

	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

}
