package com.anytrek.ts3.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.anytrek.ts3.dto.View;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * trk_info
 * 
 * @author aleen date 2018 Oct 2
 */
@Entity
@Table(name = "trk_info")
public class TrkInfo implements java.io.Serializable {
	/** 版本号 */
	private static final long serialVersionUID = 4034635723711775295L;

	public void init() {
		this.setModelId(1);
		this.setPassword("000000");
		this.setUpdateIndex(-1);
		this.setBattery(0);
		this.setVoltage(0F);
		this.setMethod("gps");
		this.setLat(0f);
		this.setLng(0f);
		this.setUpdateIndex(-1);
		this.setTrailIndex(-1);
		this.setReportTime(new Timestamp(System.currentTimeMillis()));
	}

	@Id
	@JsonView(View.Summary.class)
	@Column(name = "device_id", nullable = false, length = 19)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long deviceId;

	@JsonView(View.Summary.class)
	@Column(name = "hw_ver", nullable = true, length = 10)
	private Integer hwVer;

	/*** Info信息 */
	@Column(name = "system", nullable = true, length = 3)
	private Integer system;

	@Column(name = "org_id", nullable = true, length = 19)
	private Integer orgId;

	@Column(name = "imsi", nullable = true, length = 19)
	private Long imsi;

	@JsonView(View.SummaryWithTrkInfo.class)
	@Column(name = "model_id", nullable = false, length = 10)
	private Integer modelId;

	@JsonView(View.SummaryWithTrkInfo.class)
	@Column(name = "update_id", nullable = true, length = 10)
	private Integer updateId;

	@JsonView(View.Summary.class)
	@Column(name = "batch_id", nullable = true, length = 10)
	private Integer batchId;

	@Column(name = "ignore_acc", nullable = true, length = 10)
	private Integer ignoreAcc; // ignoreAcc

	@JsonView(View.Summary.class)
	@Column(name = "state", nullable = true, length = 3)
	private Integer state; // state

	@JsonView(View.SummaryWithTrkInfo.class)
	@Column(name = "password", nullable = true, length = 6)
	private String password;

	@JsonView(View.SummaryWithTrkInfo.class)
	@Column(name = "trailer_id", nullable = true, length = 6)
	private String trailerId;

	@JsonView(View.SummaryWithTrkInfo.class)
	@Column(name = "timezone", nullable = true, length = 10)
	private Integer timeZone;

	@JsonView(View.SummaryWithTrkInfo.class)
	@Column(name = "language", nullable = true, length = 5)
	private String language;

	@Column(name = "expiration_date", nullable = true)
	private Timestamp expirationDate; // expirationDate

	@JsonView(View.SummaryWithTrkInfo.class)
	@Column(name = "modify_time", nullable = true)
	private Timestamp modifyTime; // modifyTime

	@JsonView(View.SummaryWithTrkInfo.class)
	@Column(name = "create_time", nullable = true)
	private Timestamp createTime; // createTime
	/*** Info信息 */

	@JsonView(View.SummaryWithTrkStatus.class)
	@Column(name = "pulse_time", nullable = true)
	private Timestamp pulseTime; // pulseTime

	@JsonView(View.SummaryWithTrkStatus.class)
	@Column(name = "report_time", nullable = true)
	private Timestamp reportTime; // 定位时间 （现在用的是服务器时间）

	@JsonView(View.SummaryWithTrkStatus.class)
	@Column(name = "iccid", nullable = true, length = 19)
	private String iccid;

	@JsonView(View.SummaryWithTrkStatus.class)
	@Column(name = "update_index", nullable = true, length = 10)
	private Integer updateIndex; // 当前配置版本号 用于同步

	@JsonView(View.SummaryWithTrkStatus.class)
	@Column(name = "trail_index", nullable = true, length = 10)
	private Integer trailIndex; // 该终端最新的尾迹点的index 默认为0

	/*** GPS信息 */
	@JsonView(View.SummaryWithTrkStatus.class)
	@Column(name = "accuracy", nullable = true)
	private Float accuracy; // 定位精度

	@JsonView(View.SummaryWithTrkStatus.class)
	@Column(name = "alt", nullable = true, length = 10)
	private Integer alt; // 海拔

	@JsonView(View.SummaryWithTrkStatus.class)
	@Column(name = "lat", nullable = true)
	private Float lat; // 纬度

	@JsonView(View.SummaryWithTrkStatus.class)
	@Column(name = "lng", nullable = true)
	private Float lng; // 经度

	@JsonView(View.SummaryWithTrkStatus.class)
	@Column(name = "hdp", nullable = true)
	private Float hdp; // 水平精度 -1为无记录 （位置包目前无该值)

	@JsonView(View.SummaryWithTrkStatus.class)
	@Column(name = "heading", nullable = true, length = 10)
	private Integer heading; // 0-360 行进方向

	@JsonView(View.SummaryWithTrkStatus.class)
	@Column(name = "speed", nullable = true, length = 10)
	private Integer speed; // 当前速度

	@JsonView(View.SummaryWithTrkStatus.class)
	@Column(name = "gps_state", nullable = true, length = 3)
	private Integer gpsState; // gpsState

	@JsonView(View.SummaryWithTrkStatus.class)
	@Column(name = "address", nullable = true)
	private String address; // 大概地址 （中国为市一级， 美国，澳洲，加拿大，墨西哥为州一级， ）

	@JsonView(View.SummaryWithTrkStatus.class)
	@Column(name = "detail_address", nullable = true)
	private String detailAddress; // 详细地址

	@Column(name = "method", nullable = true, length = 4)
	private String method; // method

	@Column(name = "total_mileage", nullable = true, length = 10)
	private Integer totalMileage; // 总里程
	/*** GPS信息 */

	/*** Status信息 */
	@JsonView(View.SummaryWithTrkStatus.class)
	@Column(name = "fw_ver", nullable = true, length = 10)
	private Integer fwVer; // fwVer

	@JsonView(View.SummaryWithTrkStatus.class)
	@Column(name = "bl_ver", nullable = true, length = 10)
	private Integer blVer; // bootloader版本号

	@JsonView(View.SummaryWithTrkStatus.class)
	@Column(name = "update_flag", nullable = true)
	private Integer updateFlag; // 是否有更新， 1024为更新所有 0为无更新

	@JsonView(View.SummaryWithTrkStatus.class)
	@Column(name = "ext_in", nullable = true)
	private Integer extIn; // external input,有三个输入端口，对应0或者1的开关状态

	@JsonView(View.SummaryWithTrkStatus.class)
	@Column(name = "event", nullable = true, length = 3)
	private Integer event; // 事件， 0 静止， 1驾驶中, 2 熄火

	@JsonView(View.SummaryWithTrkStatus.class)
	@Column(name = "charging", nullable = true, length = 3)
	private Integer charging; // 是否正在充电

	@JsonView(View.SummaryWithTrkStatus.class)
	@Column(name = "battery", nullable = true)
	private Integer battery; // 剩余电量

	@Column(name = "voltage", nullable = true)
	@JsonView(View.Summary.class)
	private Float voltage; // 剩余电压

	@JsonView(View.SummaryWithTrkStatus.class)
	@Column(name = "signals", nullable = true, length = 3)
	private Integer signals; // 基站信号强度

	@JsonView(View.SummaryWithTrkStatus.class)
	@Column(name = "solar", nullable = true, length = 10)
	private Integer solar; // solar

	@JsonView(View.SummaryWithTrkStatus.class)
	@Column(name = "current", nullable = true, length = 10)
	private Integer current; // current

	@JsonView(View.SummaryWithTrkStatus.class)
	@Column(name = "temp", nullable = true)
	private Float temp; // 温度

	@Column(name = "protect", nullable = true, length = 3)
	private Integer protect; // 保护中 0未保护 1保护

	@Column(name = "acc", nullable = true, length = 3)
	private Integer acc; // 是否点火

	/*** Status信息 */

	/**
	 * 获取system
	 * 
	 * @return system
	 */
	public Integer getSystem() {
		return this.system;
	}

	/**
	 * 设置system
	 * 
	 * @param system
	 */
	public void setSystem(Integer system) {
		this.system = system;
	}

	/**
	 * 获取orgId
	 * 
	 * @return orgId
	 */
	public Integer getOrgId() {
		return this.orgId;
	}

	/**
	 * 设置orgId
	 * 
	 * @param orgId
	 */
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取imsi
	 * 
	 * @return imsi
	 */
	public Long getImsi() {
		return this.imsi;
	}

	/**
	 * 设置imsi
	 * 
	 * @param imsi
	 */
	public void setImsi(Long imsi) {
		this.imsi = imsi;
	}

	/**
	 * 获取iccid
	 * 
	 * @return iccid
	 */
	public String getIccid() {
		return this.iccid;
	}

	/**
	 * 设置iccid
	 * 
	 * @param iccid
	 */
	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	/**
	 * 获取password
	 * 
	 * @return password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * 设置password
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取modelId
	 * 
	 * @return modelId
	 */
	public Integer getModelId() {
		return this.modelId;
	}

	/**
	 * 设置modelId
	 * 
	 * @param modelId
	 */
	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

	public Integer getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(Integer timeZone) {
		this.timeZone = timeZone;
	}

	/**
	 * 获取language
	 * 
	 * @return language
	 */
	public String getLanguage() {
		return this.language;
	}

	/**
	 * 设置language
	 * 
	 * @param language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * 获取updateId
	 * 
	 * @return updateId
	 */
	public Integer getUpdateId() {
		return this.updateId;
	}

	/**
	 * 设置updateId
	 * 
	 * @param updateId
	 */
	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}

	/**
	 * 获取hwVer
	 * 
	 * @return hwVer
	 */
	public Integer getHwVer() {
		return this.hwVer;
	}

	/**
	 * 设置hwVer
	 * 
	 * @param hwVer
	 */
	public void setHwVer(Integer hwVer) {
		this.hwVer = hwVer;
	}

	/**
	 * 获取ignoreAcc
	 * 
	 * @return ignoreAcc
	 */
	public Integer getIgnoreAcc() {
		return this.ignoreAcc;
	}

	/**
	 * 设置ignoreAcc
	 * 
	 * @param ignoreAcc
	 */
	public void setIgnoreAcc(Integer ignoreAcc) {
		this.ignoreAcc = ignoreAcc;
	}

	/**
	 * 获取state
	 * 
	 * @return state
	 */
	public Integer getState() {
		return this.state;
	}

	/**
	 * 设置state
	 * 
	 * @param state
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 获取expirationDate
	 * 
	 * @return expirationDate
	 */
	public Timestamp getExpirationDate() {
		return this.expirationDate;
	}

	/**
	 * 设置expirationDate
	 * 
	 * @param expirationDate
	 */
	public void setExpirationDate(Timestamp expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * 获取modifyTime
	 * 
	 * @return modifyTime
	 */
	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	/**
	 * 设置modifyTime
	 * 
	 * @param modifyTime
	 */
	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
	 * 获取createTime
	 * 
	 * @return createTime
	 */
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	/**
	 * 设置createTime
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取deviceId
	 * 
	 * @return deviceId
	 */
	public Long getDeviceId() {
		return this.deviceId;
	}

	/**
	 * 设置deviceId
	 * 
	 * @param deviceId
	 */
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * 获取纬度
	 * 
	 * @return 纬度
	 */
	public Float getLat() {
		return this.lat;
	}

	/**
	 * 设置纬度
	 * 
	 * @param lat
	 *            纬度
	 */
	public void setLat(Float lat) {
		this.lat = lat;
	}

	public Float getHdp() {
		return hdp;
	}

	public void setHdp(Float hdp) {
		this.hdp = hdp;
	}

	/**
	 * 获取经度
	 * 
	 * @return 经度
	 */
	public Float getLng() {
		return this.lng;
	}

	/**
	 * 设置经度
	 * 
	 * @param lng
	 *            经度
	 */
	public void setLng(Float lng) {
		this.lng = lng;
	}

	/**
	 * 获取0-360 行进方向
	 * 
	 * @return 0-360 行进方向
	 */
	public Integer getHeading() {
		return this.heading;
	}

	/**
	 * 设置0-360 行进方向
	 * 
	 * @param heading
	 *            0-360 行进方向
	 */
	public void setHeading(Integer heading) {
		this.heading = heading;
	}

	/**
	 * 获取当前速度
	 * 
	 * @return 当前速度
	 */
	public Integer getSpeed() {
		return this.speed;
	}

	/**
	 * 设置当前速度
	 * 
	 * @param speed
	 *            当前速度
	 */
	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	/**
	 * 获取是否正在充电
	 * 
	 * @return 是否正在充电
	 */
	public Integer getCharging() {
		return this.charging;
	}

	/**
	 * 设置是否正在充电
	 * 
	 * @param charging
	 *            是否正在充电
	 */
	public void setCharging(Integer charging) {
		this.charging = charging;
	}

	/**
	 * 获取acc
	 * 
	 * @return acc
	 */
	public Integer getAcc() {
		return this.acc;
	}

	/**
	 * 设置acc
	 * 
	 * @param acc
	 */
	public void setAcc(Integer acc) {
		this.acc = acc;
	}

	/**
	 * 获取剩余电量
	 * 
	 * @return 剩余电量
	 */
	public Integer getBattery() {
		return this.battery;
	}

	/**
	 * 设置剩余电量
	 * 
	 * @param battery
	 *            剩余电量
	 */
	public void setBattery(Integer battery) {
		this.battery = battery;
	}

	public Float getVoltage() {
		return voltage;
	}

	public void setVoltage(Float voltage) {
		this.voltage = voltage;
	}

	/**
	 * 获取gpsState
	 * 
	 * @return gpsState
	 */
	public Integer getGpsState() {
		return this.gpsState;
	}

	/**
	 * 设置gpsState
	 * 
	 * @param gpsState
	 */
	public void setGpsState(Integer gpsState) {
		this.gpsState = gpsState;
	}

	/**
	 * 获取基站信号强度
	 * 
	 * @return 基站信号强度
	 */
	public Integer getSignals() {
		return this.signals;
	}

	/**
	 * 设置基站信号强度
	 * 
	 * @param signals
	 *            基站信号强度
	 */
	public void setSignals(Integer signals) {
		this.signals = signals;
	}

	/**
	 * 获取solar
	 * 
	 * @return solar
	 */
	public Integer getSolar() {
		return this.solar;
	}

	/**
	 * 设置solar
	 * 
	 * @param solar
	 */
	public void setSolar(Integer solar) {
		this.solar = solar;
	}

	/**
	 * 获取protect
	 * 
	 * @return protect
	 */
	public Integer getProtect() {
		return this.protect;
	}

	/**
	 * 设置protect
	 * 
	 * @param protect
	 */
	public void setProtect(Integer protect) {
		this.protect = protect;
	}

	/**
	 * 获取总里程
	 * 
	 * @return 总里程
	 */
	public Integer getTotalMileage() {
		return this.totalMileage;
	}

	/**
	 * 设置总里程
	 * 
	 * @param totalMileage
	 *            总里程
	 */
	public void setTotalMileage(Integer totalMileage) {
		this.totalMileage = totalMileage;
	}

	/**
	 * 获取method
	 * 
	 * @return method
	 */
	public String getMethod() {
		return this.method;
	}

	/**
	 * 设置method
	 * 
	 * @param method
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * 获取定位精度
	 * 
	 * @return 定位精度
	 */
	public Float getAccuracy() {
		return this.accuracy;
	}

	/**
	 * 设置定位精度
	 * 
	 * @param accuracy
	 *            定位精度
	 */
	public void setAccuracy(Float accuracy) {
		this.accuracy = accuracy;
	}

	/**
	 * 获取海拔
	 * 
	 * @return 海拔
	 */
	public Integer getAlt() {
		return this.alt;
	}

	/**
	 * 设置海拔
	 * 
	 * @param alt
	 *            海拔
	 */
	public void setAlt(Integer alt) {
		this.alt = alt;
	}

	/**
	 * 获取事件， 0 静止， 1驾驶中, 2 熄火
	 * 
	 * @return 事件
	 */
	public Integer getEvent() {
		return this.event;
	}

	/**
	 * 设置事件， 0 静止， 1驾驶中, 2 熄火
	 * 
	 * @param event
	 *            事件
	 */
	public void setEvent(Integer event) {
		this.event = event;
	}

	/**
	 * 获取current
	 * 
	 * @return current
	 */
	public Integer getCurrent() {
		return this.current;
	}

	/**
	 * 设置current
	 * 
	 * @param current
	 */
	public void setCurrent(Integer current) {
		this.current = current;
	}

	/**
	 * 获取fwVer
	 * 
	 * @return fwVer
	 */
	public Integer getFwVer() {
		return this.fwVer;
	}

	/**
	 * 设置fwVer
	 * 
	 * @param fwVer
	 */
	public void setFwVer(Integer fwVer) {
		this.fwVer = fwVer;
	}

	/**
	 * 获取bootloader版本号
	 * 
	 * @return bootloader版本号
	 */
	public Integer getBlVer() {
		return this.blVer;
	}

	/**
	 * 设置bootloader版本号
	 * 
	 * @param blVer
	 *            bootloader版本号
	 */
	public void setBlVer(Integer blVer) {
		this.blVer = blVer;
	}

	/**
	 * 获取当前配置版本号 用于同步
	 * 
	 * @return 当前配置版本号 用于同步
	 */
	public Integer getUpdateIndex() {
		return this.updateIndex;
	}

	/**
	 * 设置当前配置版本号 用于同步
	 * 
	 * @param updateIndex
	 *            当前配置版本号 用于同步
	 */
	public void setUpdateIndex(Integer updateIndex) {
		this.updateIndex = updateIndex;
	}

	/**
	 * 获取该终端最新的尾迹点的index 默认为0
	 * 
	 * @return 该终端最新的尾迹点的index 默认为0
	 */
	public Integer getTrailIndex() {
		return this.trailIndex;
	}

	/**
	 * 设置该终端最新的尾迹点的index 默认为0
	 * 
	 * @param trailIndex
	 *            该终端最新的尾迹点的index 默认为0
	 */
	public void setTrailIndex(Integer trailIndex) {
		this.trailIndex = trailIndex;
	}

	/**
	 * 获取上报时间 （现在用的是服务器时间）
	 * 
	 * @return 上报时间 （现在用的是服务器时间）
	 */
	public Timestamp getReportTime() {
		return this.reportTime;
	}

	/**
	 * 设置上报时间 （现在用的是服务器时间）
	 * 
	 * @param reportTime
	 *            上报时间 （现在用的是服务器时间）
	 */
	public void setReportTime(Timestamp reportTime) {
		this.reportTime = reportTime;
	}

	/**
	 * 获取pulseTime
	 * 
	 * @return pulseTime
	 */
	public Timestamp getPulseTime() {
		return this.pulseTime;
	}

	/**
	 * 设置pulseTime
	 * 
	 * @param pulseTime
	 */
	public void setPulseTime(Timestamp pulseTime) {
		this.pulseTime = pulseTime;
	}

	public Float getTemp() {
		return temp;
	}

	public void setTemp(Float temp) {
		this.temp = temp;
	}

	public Integer getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(Integer updateFlag) {
		this.updateFlag = updateFlag;
	}

	public Integer getExtIn() {
		return extIn;
	}

	public void setExtIn(Integer extIn) {
		this.extIn = extIn;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public String getTrailerId() {
		return trailerId;
	}

	public void setTrailerId(String trailerId) {
		this.trailerId = trailerId;
	}

}