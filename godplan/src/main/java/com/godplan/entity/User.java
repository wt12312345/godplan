package com.godplan.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.wt.web.entity.AbstractEntity;

@Entity
public class User extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 导入此仓库的订单状态：香港仓的订单都是做记录，没有系统可以对接，不需要系统推送，也无法获取物流信息，直接把订单状态设为已发货。其他几个设为已付款。
	 * 1 标记为已付款，需要系统推送订单和获取物流。2 标记为已发货
	 */
	@Column(columnDefinition = "int default 0")
	private int orderStatus = 0;
	/** 密码 */
	@Column(length = 32)
	private String password = "";

	/** 昵称 */
	@Column(length = 30)
	private String nickName = "";

	/** 登录名 */
	@Column(unique = true, length = 30)
	private String loginName;
	/**
	 * 认证类型：1 普通用户 ；2 ：VIP用户
	 */
	@Column(columnDefinition = "int default 1")
	private int authType = 1;
	/**
	 * 组织名称（公司名、网店名等）
	 */
	@Column(length = 100)
	private String orgName = "";
	/**
	 * 用于API对接
	 */
	@Column(unique = true)
	private String appId;
	/**
	 * 用于API对接
	 */
	@Column(unique = true)
	private String appSecret;
	/**
	 * 真实姓名
	 */
	@Column(length = 20)
	private String realName = "";

	/** 手机号码 */
	@Column(unique = true, length = 11)
	private String mobile;

	/**
	 * 性别：0未知，1男，2女
	 */
	@Column(columnDefinition = "int default 0")
	private int sex = 0;

	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;

	/** 所在国家 */
	@Column(length = 20)
	private String country;

	/** 所在省份 */
	@Column(length = 20)
	private String province;

	/** 所在城市 */
	@Column(length = 20)
	private String city;

	/**
	 * 用户头像，最后一个数值代表正方形头像大小（有0,46,64,96,132 数值 可选，0代表640*640正方形头像），用户没有头像时该项为空
	 */
	@Column(length = 100)
	private String imgHead = "";
	@Column(length = 200)
	private String imgHeadWx = "";
	/** 地址 */
	@Column(length = 100)
	private String district;

	/** 街道 */
	@Column(length = 20)
	private String street;

	/** 邮箱 */
	@Column(unique = true, length = 50)
	private String email;

	/** qq号 */
	@Column(unique = true, length = 20)
	private String qq;

	/** 身份证 */
	@Column(unique = true, length = 18)
	private String idCard;

	@Column(unique = true, length = 100)
	private String openid;
	/**
	 * 状态：0无效，1有效
	 */
	@Column(columnDefinition = "int default 0")
	private int status = 1;
	/** 最后登录时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date lastLoginTime = new Date();
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date refreshInfoTime = new Date();

	/**
	 * 订单数
	 */
	@Column(columnDefinition = "int default 0")
	private int numOrder = 0;
	/**
	 * 订单包含商品总数
	 */
	@Column(columnDefinition = "int default 0")
	private int numSku = 0;
	/**
	 * 总价
	 */
	@Column(columnDefinition = "int default 0")
	private int totalPrice = 0;
	@Column(columnDefinition = "int default 0")
	private int payPrice = 0;
	@Column(columnDefinition = "int default 0")
	private int freePrice = 0;
	/**
	 * 订单包含的总重量（克）
	 */
	@Column(columnDefinition = "int default 0")
	private int weight = 0;
	@Column(columnDefinition = "int default 0")
	private int refundPrice = 0;
	/**
	 * 优惠券数
	 */
	@Column(columnDefinition = "int default 0")
	private int couponNum = 0;

	/**
	 * 用户类型：1 C端用户；2 B端用户，信息存在userVip中
	 */
	@Column(columnDefinition = "int default 0")
	private int userType = 0;

	public String getPassword() {
		return password;
	}

	public String getNickName() {
		return nickName;
	}

	public String getLoginName() {
		return loginName;
	}

	public String getOrgName() {
		return orgName;
	}

	public String getRealName() {
		return realName;
	}

	public String getMobile() {
		return mobile;
	}

	public int getSex() {
		return sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public String getCountry() {
		return country;
	}

	public String getProvince() {
		return province;
	}

	public String getCity() {
		return city;
	}

	public String getImgHead() {
		return imgHead;
	}

	public String getImgHeadWx() {
		return imgHeadWx;
	}

	public String getDistrict() {
		return district;
	}

	public String getStreet() {
		return street;
	}

	public String getEmail() {
		return email;
	}

	public String getQq() {
		return qq;
	}

	public String getIdCard() {
		return idCard;
	}

	public String getOpenid() {
		return openid;
	}

	public int getStatus() {
		return status;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public Date getRefreshInfoTime() {
		return refreshInfoTime;
	}

	public int getNumOrder() {
		return numOrder;
	}

	public int getNumSku() {
		return numSku;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public int getPayPrice() {
		return payPrice;
	}

	public int getFreePrice() {
		return freePrice;
	}

	public int getWeight() {
		return weight;
	}

	public int getRefundPrice() {
		return refundPrice;
	}

	public int getCouponNum() {
		return couponNum;
	}

	public int getUserType() {
		return userType;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setImgHead(String imgHead) {
		this.imgHead = imgHead;
	}

	public void setImgHeadWx(String imgHeadWx) {
		this.imgHeadWx = imgHeadWx;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public void setRefreshInfoTime(Date refreshInfoTime) {
		this.refreshInfoTime = refreshInfoTime;
	}

	public void setNumOrder(int numOrder) {
		this.numOrder = numOrder;
	}

	public void setNumSku(int numSku) {
		this.numSku = numSku;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setPayPrice(int payPrice) {
		this.payPrice = payPrice;
	}

	public void setFreePrice(int freePrice) {
		this.freePrice = freePrice;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void setRefundPrice(int refundPrice) {
		this.refundPrice = refundPrice;
	}

	public void setCouponNum(int couponNum) {
		this.couponNum = couponNum;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getAuthType() {
		return authType;
	}

	public String getAppId() {
		return appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAuthType(int authType) {
		this.authType = authType;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

}