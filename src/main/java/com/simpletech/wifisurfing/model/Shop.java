package com.simpletech.wifisurfing.model;

import com.simpletech.wifisurfing.annotations.dbmodel.Column;
import com.simpletech.wifisurfing.annotations.dbmodel.Id;
import com.simpletech.wifisurfing.annotations.dbmodel.Table;
import com.simpletech.wifisurfing.model.base.ModelBase;

/**
 * 数据库表shop
 * @author 树朾
 * @date 2015-11-25 17:33:49 中国标准时间
 */
@Table("shop")
public class Shop extends ModelBase{

	/**
	 * ID主键
	 */
	@Id
	private String shopID;
	/**
	 * 名称
	 */
	private String shopName;
	/**
	 * 访问过期时间段（60分钟）
	 */
	@Column("config_wifi_visit_expired")
	private Integer configWifiVisitExpired;
	/**
	 * 用户过期时间段（365天）
	 */
	@Column("config_wifi_user_expired")
	private Integer configWifiUserExpired;
	/**
	 * 到访次数统计规则（1,2,5）次数
	 */
	@Column("config_wifi_api_visit_counts")
	private String configWifiApiVisitCounts;
	/**
	 * 到访时长统计规则（5,30,60,120）分钟
	 */
	@Column("config_wifi_api_visit_duration")
	private String configWifiApiVisitDuration;
	/**
	 * 到访时长达到深访的标准（40分钟）
	 */
	@Column("config_wifi_api_visit_duration_deep")
	private Double configWifiApiVisitDurationDeep;
	/**
	 * 到访时长达到跳出的标准（10分钟）
	 */
	@Column("config_wifi_api_visit_duration_jump")
	private Double configWifiApiVisitDurationJump;
	/**
	 * 到访周期统计规则（1,2,4,7,14）天
	 */
	@Column("config_wifi_api_visit_period")
	private String configWifiApiVisitPeriod;
	/**
	 * 活跃度统计(1,7,15,30天)
	 */
	@Column("config_wifi_api_liveness")
	private String configWifiApiLiveness;
	/**
	 * 服务器时间
	 */
	private java.util.Date createTime;

	public Shop() {
	}
	
	public String getShopID(){
		return this.shopID;
	}

	public void setShopID(String shopID) {
		this.shopID = shopID;
	}
	
	public String getShopName(){
		return this.shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	public Integer getConfigWifiVisitExpired(){
		return this.configWifiVisitExpired;
	}

	public void setConfigWifiVisitExpired(Integer configWifiVisitExpired) {
		this.configWifiVisitExpired = configWifiVisitExpired;
	}
	
	public Integer getConfigWifiUserExpired(){
		return this.configWifiUserExpired;
	}

	public void setConfigWifiUserExpired(Integer configWifiUserExpired) {
		this.configWifiUserExpired = configWifiUserExpired;
	}
	
	public String getConfigWifiApiVisitCounts(){
		return this.configWifiApiVisitCounts;
	}

	public void setConfigWifiApiVisitCounts(String configWifiApiVisitCounts) {
		this.configWifiApiVisitCounts = configWifiApiVisitCounts;
	}
	
	public String getConfigWifiApiVisitDuration(){
		return this.configWifiApiVisitDuration;
	}

	public void setConfigWifiApiVisitDuration(String configWifiApiVisitDuration) {
		this.configWifiApiVisitDuration = configWifiApiVisitDuration;
	}
	
	public Double getConfigWifiApiVisitDurationDeep(){
		return this.configWifiApiVisitDurationDeep;
	}

	public void setConfigWifiApiVisitDurationDeep(Double configWifiApiVisitDurationDeep) {
		this.configWifiApiVisitDurationDeep = configWifiApiVisitDurationDeep;
	}
	
	public Double getConfigWifiApiVisitDurationJump(){
		return this.configWifiApiVisitDurationJump;
	}

	public void setConfigWifiApiVisitDurationJump(Double configWifiApiVisitDurationJump) {
		this.configWifiApiVisitDurationJump = configWifiApiVisitDurationJump;
	}
	
	public String getConfigWifiApiVisitPeriod(){
		return this.configWifiApiVisitPeriod;
	}

	public void setConfigWifiApiVisitPeriod(String configWifiApiVisitPeriod) {
		this.configWifiApiVisitPeriod = configWifiApiVisitPeriod;
	}
	
	public String getConfigWifiApiLiveness(){
		return this.configWifiApiLiveness;
	}

	public void setConfigWifiApiLiveness(String configWifiApiLiveness) {
		this.configWifiApiLiveness = configWifiApiLiveness;
	}
	
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
}