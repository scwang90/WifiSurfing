package com.simpletech.wifisurfing.model;

import com.simpletech.wifisurfing.annotations.dbmodel.Column;
import com.simpletech.wifisurfing.annotations.dbmodel.Id;
import com.simpletech.wifisurfing.annotations.dbmodel.Table;
import com.simpletech.wifisurfing.model.base.ModelBase;

/**
 * 数据库表wifi_log
 * @author 树朾
 * @date 2015-11-25 17:33:49 中国标准时间
 */
@Table("wifi_log")
public class WifiLog extends ModelBase{

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
	 * 访问ID
	 */
	private String idvisit;
	/**
	 * 手机Mac
	 */
	@Column("mac_device")
	private String macDevice;
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

	public WifiLog() {
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
	
	public String getIdvisit(){
		return this.idvisit;
	}

	public void setIdvisit(String idvisit) {
		this.idvisit = idvisit;
	}
	
	public String getMacDevice(){
		return this.macDevice;
	}

	public void setMacDevice(String macDevice) {
		this.macDevice = macDevice;
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