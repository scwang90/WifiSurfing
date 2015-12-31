package com.simpletech.wifisurfing.model;

import com.simpletech.wifisurfing.annotations.dbmodel.Id;
import com.simpletech.wifisurfing.annotations.dbmodel.Table;
import com.simpletech.wifisurfing.model.base.ModelBase;

/**
 * 数据库表device_binding
 * @author 树朾
 * @date 2015-11-25 17:33:49 中国标准时间
 */
@Table("device_binding")
public class DeviceBinding extends ModelBase{

	/**
	 * ID主键
	 */
	@Id
	private String deviceID;
	/**
	 * 设备名称
	 */
	private String deviceName;
	/**
	 * MAC地址
	 */
	private String mac;
	/**
	 * 添加时间
	 */
	private java.util.Date addDate;

	public DeviceBinding() {
	}
	
	public String getDeviceID(){
		return this.deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	
	public String getDeviceName(){
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	public String getMac(){
		return this.mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
	
	public java.util.Date getAddDate(){
		return this.addDate;
	}

	public void setAddDate(java.util.Date addDate) {
		this.addDate = addDate;
	}
	
}