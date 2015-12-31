package com.simpletech.wifisurfing.model;

import com.simpletech.wifisurfing.annotations.dbmodel.Column;
import com.simpletech.wifisurfing.annotations.dbmodel.Id;
import com.simpletech.wifisurfing.annotations.dbmodel.Table;
import com.simpletech.wifisurfing.model.base.ModelBase;

/**
 * 数据库表login
 * @author 树朾
 * @date 2015-12-09 18:44:02 中国标准时间
 */
@Table("login")
public class Login extends ModelBase{

	/**
	 * ID主键
	 */
	@Id
	private String id;
	/**
	 * 店铺ID
	 */
	private String idshop;
	/**
	 * wifi ID
	 */
	private String idwifi;
	/**
	 * 公众号ID
	 */
	private String appid;
	/**
	 * 用户开放ID
	 */
	private String openid;
	/**
	 * 微信店面ID
	 */
	private String wxshopid;
	/**
	 * 认证类型
	 */
	@Column("auth_type")
	private Integer authType;
	/**
	 * 设备Mac
	 */
	@Column("mac_device")
	private String macDevice;
	/**
	 * 上次登录到本次的时间（秒）
	 */
	@Column("time_from_last")
	private Integer timeFromLast;
	/**
	 * 是否新用户
	 */
	@Column("is_new_user")
	private Boolean isNewUser;
	/**
	 * 设备品牌
	 */
	@Column("end_brand")
	private String endBrand;
	/**
	 * 本地时间
	 */
	@Column("time_local")
	private java.util.Date timeLocal;
	/**
	 * 服务器时间
	 */
	@Column("create_time")
	private java.util.Date createTime;
	/**
	 * 更新时间
	 */
	@Column("update_time")
	private java.util.Date updateTime;

	public Login() {
	}
	
	public String getId(){
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getIdshop(){
		return this.idshop;
	}

	public void setIdshop(String idshop) {
		this.idshop = idshop;
	}
	
	public String getIdwifi(){
		return this.idwifi;
	}

	public void setIdwifi(String idwifi) {
		this.idwifi = idwifi;
	}
	
	public String getAppid(){
		return this.appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	public String getOpenid(){
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	public String getWxshopid(){
		return this.wxshopid;
	}

	public void setWxshopid(String wxshopid) {
		this.wxshopid = wxshopid;
	}
	
	public Integer getAuthType(){
		return this.authType;
	}

	public void setAuthType(Integer authType) {
		this.authType = authType;
	}
	
	public String getMacDevice(){
		return this.macDevice;
	}

	public void setMacDevice(String macDevice) {
		this.macDevice = macDevice;
	}
	
	public Integer getTimeFromLast(){
		return this.timeFromLast;
	}

	public void setTimeFromLast(Integer timeFromLast) {
		this.timeFromLast = timeFromLast;
	}
	
	public Boolean getIsNewUser(){
		return this.isNewUser;
	}

	public void setIsNewUser(Boolean isNewUser) {
		this.isNewUser = isNewUser;
	}
	
	public String getEndBrand(){
		return this.endBrand;
	}

	public void setEndBrand(String endBrand) {
		this.endBrand = endBrand;
	}
	
	public java.util.Date getTimeLocal(){
		return this.timeLocal;
	}

	public void setTimeLocal(java.util.Date timeLocal) {
		this.timeLocal = timeLocal;
	}
	
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
}