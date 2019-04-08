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
 * trk_setting
 * 
 * @author aleen date 2018 Oct 2
 */
@Entity
@Table(name = "trk_setting")
public class TrkSetting implements java.io.Serializable {
	/** 版本号 */
	private static final long serialVersionUID = 8242011989109220817L;

	/** deviceId */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(View.Summary.class)
	@Column(name = "device_id", nullable = false, length = 19)
	private Long deviceId;

	/** 上报功能0关闭 1打开 */
	@JsonView(View.Summary.class)
	@Column(name = "working_pulse_enable", nullable = false, length = 4)
	private Integer workingPulseEnable;

	/** 上报功能0关闭 1打开 */
	@JsonView(View.Summary.class)
	@Column(name = "working_report_enable", nullable = false, length = 4)
	private Integer workingReportEnable;

	/** 上报功能0关闭 1打开 */
	@JsonView(View.Summary.class)
	@Column(name = "battery_pulse_enable", nullable = false, length = 4)
	private Integer batteryPulseEnable;

	/** 上报功能0关闭 1打开 */
	@JsonView(View.Summary.class)
	@Column(name = "battery_report_enable", nullable = false, length = 4)
	private Integer batteryReportEnable;

	/** 上报功能0关闭 1打开 */
	@Column(name = "testmode_pulse_enable", nullable = false, length = 4)
	private Integer testmodePulseEnable;

	/** 上报功能0关闭 1打开 */
	@Column(name = "testmode_report_enable", nullable = false, length = 4)
	private Integer testmodeReportEnable;

	/** 上报频率0关闭 1打开 */
	@JsonView(View.Summary.class)
	@Column(name = "working_pulse_interval", nullable = false, length = 12)
	private Integer workingPulseInterval;

	/** 上报频率0关闭 1打开 */
	@JsonView(View.Summary.class)
	@Column(name = "working_report_interval", nullable = false, length = 12)
	private Integer workingReportInterval;

	/** 上报频率0关闭 1打开 */
	@JsonView(View.Summary.class)
	@Column(name = "battery_pulse_interval", nullable = false, length = 12)
	private Integer batteryPulseInterval;

	/** 上报频率0关闭 1打开 */
	@JsonView(View.Summary.class)
	@Column(name = "battery_report_interval", nullable = false, length = 12)
	private Integer batteryReportInterval;

	/** 上报频率0关闭 1打开 */
	@Column(name = "testmode_pulse_interval", nullable = false, length = 12)
	private Integer testmodePulseInterval;

	/** 上报频率0关闭 1打开 */
	@Column(name = "testmode_report_interval", nullable = false, length = 12)
	private Integer testmodeReportInterval;

	/** 1508D继电器的控制输出0关闭 1打开 */
	@JsonView(View.Summary.class)
	@Column(name = "relay_out", nullable = false, length = 12)
	private Integer relayOut;

	/** 位置包上报服务器地址 */
	@JsonView(View.Summary.class)
	@Column(name = "report_server", nullable = false, length = 64)
	private String reportServer;

	/** 上报测试服务器地址 */
	@Column(name = "test_server", nullable = false, length = 64)
	private String testServer;

	/** 位置偏移距离 */
	@JsonView(View.Summary.class)
	@Column(name = "distance_offset", nullable = false, length = 12)
	private Integer distanceOffset;

	/** 时区 */
	@Column(name = "time_zone", nullable = false, length = 12)
	private Integer timeZone;

	/** agps开关 */
	@Column(name = "agps_enabled", nullable = false, length = 12)
	private Integer agpsEnabled;
	

	/** gps超时 */
	@JsonView(View.Summary.class)
	@Column(name = "gps_weak_timeout", nullable = false, length = 12)
	private Integer gpsWeakTimeout;

	/** GPS抗漂移延时最大值   单位秒 */
	@JsonView(View.Summary.class)
	@Column(name = "gps_anti_drifting", nullable = false, length = 12)
	private Integer gpsAntiDrifting;

	/** 水平分量精度因子的阈值 */
	@JsonView(View.Summary.class)
	@Column(name = "gps_hdop_threshold", nullable = false, length = 12)
	private Float gpsHdopThreshold;

	/** gsensor灵敏度 */
	@JsonView(View.Summary.class)
	@Column(name = "gsensor_sensitivity", nullable = false, length = 12)
	private Integer gsensorSensitivity;

	/** 更新序号 */
	@JsonView(View.Summary.class)
	@Column(name = "update_index", nullable = false, length = 10)
	private Integer updateIndex;

	/** create_time */
	@Column(name = "create_time", nullable = true)
	private Timestamp createTime;

	/** modify_time */
	@JsonView(View.Summary.class)
	@Column(name = "modify_time", nullable = true)
	private Timestamp modifyTime;

	/**
	 * 获取tracker id，根据不同系统，可以是IMEI或MEID
	 * 
	 * @return tracker id
	 */
	public Long getDeviceId() {
		return this.deviceId;
	}

	/**
	 * 设置tracker id，根据不同系统，可以是IMEI或MEID
	 * 
	 * @param deviceId
	 *            tracker id
	 */
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getWorkingPulseEnable() {
		return workingPulseEnable;
	}

	public void setWorkingPulseEnable(Integer workingPulseEnable) {
		this.workingPulseEnable = workingPulseEnable;
	}

	public Integer getWorkingReportEnable() {
		return workingReportEnable;
	}

	public void setWorkingReportEnable(Integer workingReportEnable) {
		this.workingReportEnable = workingReportEnable;
	}

	public Integer getBatteryPulseEnable() {
		return batteryPulseEnable;
	}

	public void setBatteryPulseEnable(Integer batteryPulseEnable) {
		this.batteryPulseEnable = batteryPulseEnable;
	}

	public Integer getBatteryReportEnable() {
		return batteryReportEnable;
	}

	public void setBatteryReportEnable(Integer batteryReportEnable) {
		this.batteryReportEnable = batteryReportEnable;
	}

	public Integer getTestmodePulseEnable() {
		return testmodePulseEnable;
	}

	public void setTestmodePulseEnable(Integer testmodePulseEnable) {
		this.testmodePulseEnable = testmodePulseEnable;
	}

	public Integer getTestmodeReportEnable() {
		return testmodeReportEnable;
	}

	public void setTestmodeReportEnable(Integer testmodeReportEnable) {
		this.testmodeReportEnable = testmodeReportEnable;
	}

	public Integer getWorkingPulseInterval() {
		return workingPulseInterval;
	}

	public void setWorkingPulseInterval(Integer workingPulseInterval) {
		this.workingPulseInterval = workingPulseInterval;
	}

	public Integer getWorkingReportInterval() {
		return workingReportInterval;
	}

	public void setWorkingReportInterval(Integer workingReportInterval) {
		this.workingReportInterval = workingReportInterval;
	}

	public Integer getBatteryPulseInterval() {
		return batteryPulseInterval;
	}

	public void setBatteryPulseInterval(Integer batteryPulseInterval) {
		this.batteryPulseInterval = batteryPulseInterval;
	}

	public Integer getBatteryReportInterval() {
		return batteryReportInterval;
	}

	public void setBatteryReportInterval(Integer batteryReportInterval) {
		this.batteryReportInterval = batteryReportInterval;
	}

	public Integer getTestmodePulseInterval() {
		return testmodePulseInterval;
	}

	public void setTestmodePulseInterval(Integer testmodePulseInterval) {
		this.testmodePulseInterval = testmodePulseInterval;
	}

	public Integer getTestmodeReportInterval() {
		return testmodeReportInterval;
	}

	public void setTestmodeReportInterval(Integer testmodeReportInterval) {
		this.testmodeReportInterval = testmodeReportInterval;
	}

	public String getReportServer() {
		return reportServer;
	}

	public void setReportServer(String reportServer) {
		this.reportServer = reportServer;
	}

	public String getTestServer() {
		return testServer;
	}

	public void setTestServer(String testServer) {
		this.testServer = testServer;
	}

	public Integer getDistanceOffset() {
		return distanceOffset;
	}

	public void setDistanceOffset(Integer distanceOffset) {
		this.distanceOffset = distanceOffset;
	}

	public Integer getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(Integer timeZone) {
		this.timeZone = timeZone;
	}

	/**
	 * 获取更新序号
	 * 
	 * @return 更新序号
	 */
	public Integer getUpdateIndex() {
		return this.updateIndex;
	}

	/**
	 * 设置更新序号
	 * 
	 * @param updateIndex
	 *            更新序号
	 */
	public void setUpdateIndex(Integer updateIndex) {
		this.updateIndex = updateIndex;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getAgpsEnabled() {
		return agpsEnabled;
	}

	public void setAgpsEnabled(Integer agpsEnabled) {
		this.agpsEnabled = agpsEnabled;
	}

	public Integer getGpsWeakTimeout() {
		return gpsWeakTimeout;
	}

	public void setGpsWeakTimeout(Integer gpsWeakTimeout) {
		this.gpsWeakTimeout = gpsWeakTimeout;
	}

	public Integer getGsensorSensitivity() {
		return gsensorSensitivity;
	}

	public void setGsensorSensitivity(Integer gsensorSensitivity) {
		this.gsensorSensitivity = gsensorSensitivity;
	}

	public Integer getRelayOut() {
		return relayOut;
	}

	public void setRelayOut(Integer relayOut) {
		this.relayOut = relayOut;
	}

	public Integer getGpsAntiDrifting() {
		return gpsAntiDrifting;
	}

	public void setGpsAntiDrifting(Integer gpsAntiDrifting) {
		this.gpsAntiDrifting = gpsAntiDrifting;
	}

	public Float getGpsHdopThreshold() {
		return gpsHdopThreshold;
	}

	public void setGpsHdopThreshold(Float gpsHdopThreshold) {
		this.gpsHdopThreshold = gpsHdopThreshold;
	}

}