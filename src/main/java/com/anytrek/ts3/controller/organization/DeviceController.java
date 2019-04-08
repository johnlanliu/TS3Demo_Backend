package com.anytrek.ts3.controller.organization;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.anytrek.ts3.ControllerBase;
import com.anytrek.ts3.common.HWUnlimited;
import com.anytrek.ts3.dto.TrkInfoDetailDto;
import com.anytrek.ts3.dto.View;
import com.anytrek.ts3.exception.ErrorCode;
import com.anytrek.ts3.exception.WebException;
import com.anytrek.ts3.mapper.TrkInfoDeleteMapper;
import com.anytrek.ts3.mapper.TrkInfoMapper;
import com.anytrek.ts3.mapper.TrkSettingMapper;
import com.anytrek.ts3.model.TrkInfo;
import com.anytrek.ts3.model.TrkInfoDelete;
import com.anytrek.ts3.model.TrkModel;
import com.anytrek.ts3.model.TrkSetting;
import com.anytrek.util.StringTools;
import com.fasterxml.jackson.annotation.JsonView;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 组织管理模块--设备接口
 * 
 * @author John date 2018 M10 9
 */

@RestController("OrgMDeviceController")
@RequestMapping("/orgMDevice")
public class DeviceController extends ControllerBase {

	private static Logger logger = LogManager.getLogger(DeviceController.class);
	@Autowired
	private TrkInfoMapper trkInfoMapper;
	@Autowired
	private TrkInfoDeleteMapper trkInfoDeleteMapper;
	@Autowired
	private TrkSettingMapper trkSettingMapper;

	/**
	 * 查询表trkinfo数据
	 * 
	 * @deviceIdStart 设备imei区间开始值
	 * @deviceIdEnd 设备imei区间结束值
	 * @modelId 设备型号id
	 * @state 设备状态
	 * @hwVer 设备版本号
	 * @fwVer
	 * @blVer
	 * @orgId 组织id（查询该id及其下属id的所有设备信息）
	 * @pageindex 第几页
	 * @pagesize 一页多少条 (默认20)
	 * 
	 */
	@RequiresPermissions("org:device:view")
	@RequestMapping(value = { "/getDeviceList" }, method = RequestMethod.GET)
	@JsonView(View.SummaryWithTrkAll.class)
	public String getDeviceList(@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "devices", required = false) String devices,
			@RequestParam(value = "deviceIdStart", required = false) String deviceIdStart,
			// @RequestParam(value = "deviceIdEnd", required = false) BigInteger
			// deviceIdEnd,
			@RequestParam(value = "modelId", required = false) Integer modelId,
			@RequestParam(value = "iccid", required = false) String iccid,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "hwVer", required = false) Integer hwVer,
			@RequestParam(value = "fwVer", required = false) Integer fwVer,
			@RequestParam(value = "ufFlag", required = false) Integer updateFlag,
			// @RequestParam(value = "blVer") Integer blVer,
			@RequestParam(value = "orgId", required = true) Long orgId,
			@RequestParam(value = "batchId", required = false) Integer batchId,
			@RequestParam(value = "speed", required = false) Integer speed,
			@RequestParam(value = "reportTime", required = false) String reportTime,
			@RequestParam(value = "updateId", required = false) Integer updateId,
			@RequestParam(value = "isUpdate", required = false) Integer isUpdate,
			@RequestParam(value = "ignoreAcc", required = false) Integer ignoreAcc,
			@RequestParam(value = "pageindex", required = true) Integer pageindex,
			@RequestParam(value = "pagesize", required = true) Integer pagesize, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// where条件参数
		HashMap<String, Object> params = new HashMap<>();

		if (!StringTools.isBlank(devices)) {
			String[] deviceIds = devices.trim().split("\n");
			params.put("deviceIds", deviceIds);
		}
		if (modelId != null) {
			params.put("modelId", modelId);
		}

		if (!StringUtils.isEmpty(state)) {
			params.put("state", state);
		}
		if (deviceIdStart != null) {
			params.put("deviceIdStart", deviceIdStart);
		}
		// if (deviceIdEnd != null) {
		// params.put("deviceIdEnd", deviceIdEnd);
		// }
		if (hwVer != null) {
			params.put("hwVer", hwVer);
		}
		if (fwVer != null) {
			params.put("fwVer", fwVer);
		}
		if (updateFlag != null) {
			params.put("updateFlag", updateFlag);
		}
		// params.put("blVer", blVer);
		if (batchId != null) {
			params.put("batchId", batchId);
		}
		params.put("orgId", orgId);
		if (!StringUtils.isEmpty(iccid)) {
			params.put("iccid", iccid);
		}
		if (speed != null) {
			params.put("speed", speed);
		}
		if (isUpdate != null) {
			params.put("isUpdate", isUpdate);
		}
		if (!StringUtils.isEmpty(reportTime)) {
			params.put("reportTime", reportTime);
		}
		if (updateId != null) {
			params.put("updateId", updateId);
		}
		if (isUpdate != null) {
			params.put("isUpdate", isUpdate);
		}
		if (ignoreAcc != null) {
			params.put("ignoreAcc", ignoreAcc);
		}
		// 排序
		String orderBy = null;
		if (!StringUtils.isEmpty(sort)) {
			switch (sort.substring(1)) {
			case "deviceId":
				orderBy = "device_id";
				break;
			case "hwVer":
				orderBy = "hw_ver";
				break;
			case "fwVer":
				orderBy = "fw_ver";
				break;
			case "blVer":
				orderBy = "bl_ver";
				break;
			case "battery":
				orderBy = "battery";
				break;
			case "totalMileage":
				orderBy = "total_mileage";
				break;
			case "expirationDate":
				orderBy = "expiration_date";
				break;
			case "reportTime":
				orderBy = "report_time";
				break;
			case "pulseTime":
				orderBy = "pulse_time";
				break;
			case "modifyTime":
				orderBy = "modify_time";
				break;
			case "createTime":
				orderBy = "create_time";
				break;
			}
			if (orderBy != null)
				orderBy += " " + ("+".equals(sort.substring(0, 1)) ? "asc" : "desc");
		}
		JSONObject result = new JSONObject();
		// 默认页码为20，页数为第一页
		int psize = 20;
		int pindex = 1;
		if (pagesize > 0)
			psize = pagesize;
		if (pageindex > 0)
			pindex = pageindex;
		// 获取当前分页设备列表
		PageHelper.startPage(pindex, psize, orderBy);
		List<TrkInfoDetailDto> viewList = trkInfoMapper.getDeviceListByParams(params);
		// 获取分页类数据
		PageInfo<TrkInfoDetailDto> appsPageInfo = new PageInfo<>(viewList);
		// 前端应急处理
		result.put("list", (JSONArray) JSONArray.toJSON(viewList));
		result.put("totalRows", appsPageInfo.getTotal());
		return result.toString();
	}

	/**
	 * 获取该组织设备总数
	 * 
	 * @orgId 组织id（查询该id及其下属id的所有设备信息）
	 * 
	 */
	// 访问路径 /orgMDevice/getDeviceCount
	@RequiresPermissions("org:summary")
	@RequestMapping(value = { "/getDeviceCount" }, method = RequestMethod.GET)
	public String getDeviceCount(@RequestParam(value = "orgId", required = true) Integer orgId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JSONObject result = new JSONObject();
		Integer count = trkInfoMapper.getDeviceCountByOrgId(orgId);
		result.put("count", count);
		return result.toString();
	}


	@RequiresPermissions("org:device:edit-setting")
	@RequestMapping(value = { "/getDeviceSettingById" }, method = RequestMethod.GET)
	@JsonView(View.Summary.class)
	public TrkSetting getDeviceSettingById(@RequestParam(value = "deviceId", required = true) Long deviceId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (deviceId == null || deviceId == 0) {
			throw new WebException(ErrorCode.DEVICE_IMEI_INCORRECT);
		}
		TrkSetting trkSetting = trkSettingMapper.selectByPrimaryKey(deviceId);
		if (trkSetting == null || trkSetting.getDeviceId() == 0) {
			throw new WebException(ErrorCode.DEVICE_NOT_FOUND);
		}
		return trkSetting;
	}



	@RequiresPermissions("org:device:add")
	@RequestMapping(value = { "/addDevice" }, method = RequestMethod.POST)
	public @ResponseBody TrkInfo addDevice(@RequestBody TrkInfo trkInfo, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (trkInfo.getDeviceId() == null || trkInfo.getDeviceId() == 0 || trkInfo.getModelId() == 0
				|| trkInfo.getModelId() == null || StringUtils.isEmpty(trkInfo.getPassword())
				|| trkInfo.getHwVer() == null || trkInfo.getHwVer() == 0) {
			throw new WebException(ErrorCode.DEVICE_PARAMETER_ERROR);
		}
		Long deviceId = trkInfo.getDeviceId();
		// 在trkInfo表中创建新纪录
		// 首先判断设备是否重复
		if (trkInfoMapper.selectByPrimaryKey(deviceId) != null) {
			throw new WebException(ErrorCode.DEVICE_REPEAT);
		}

		trkInfo.setCreateTime(new Timestamp(new Date().getTime()));
		// 根据行号判断是否创建成功
		int row = trkInfoMapper.insertSelective(trkInfo);
		if (row == 0) {
			throw new WebException(ErrorCode.SERVER_ERROR, "Error Data!");
		}

		return trkInfo;
	}

	@RequiresPermissions("org:device:edit")
	@RequestMapping(value = { "/editDevice" }, method = RequestMethod.POST)
	public @ResponseBody TrkInfo editDevice(@RequestBody TrkInfo trkInfo, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (trkInfo.getOrgId() == null || trkInfo.getOrgId() == 0) {
			throw new WebException(ErrorCode.COMPANY_PARAMETER_ERROR);
		}
		if (trkInfo.getUpdateIndex() == null) {
			trkInfo.setUpdateIndex(0);
		}
		trkInfo.setUpdateIndex(trkInfo.getUpdateIndex() + 1);
		trkInfoMapper.updateByPrimaryKeySelective(trkInfo);
		return trkInfo;
	}


	@RequiresPermissions("org:device:move-to")
	@RequestMapping(value = { "/batchMoveDevice" }, method = RequestMethod.POST)
	public void batchMoveDevice(@RequestBody String requestStr, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JSONObject requestJSON = JSON.parseObject(requestStr);
		String deviceIds = requestJSON.getString("devices");
		String[] deviceIdArr = deviceIds.split(",");
		for (int i = 0; i < deviceIdArr.length; i++) {
			Long deviceId = Long.valueOf(deviceIdArr[i]);
			TrkInfo trkInfo = trkInfoMapper.selectByPrimaryKey(deviceId);
			if (requestJSON.containsKey("orgId"))
				trkInfo.setOrgId(requestJSON.getInteger("orgId"));
			trkInfoMapper.updateByPrimaryKeySelective(trkInfo);
		}
		this.sendOk();
	}

	@RequiresPermissions("org:device:edit")
	@RequestMapping(value = { "/batchEditDevice" }, method = RequestMethod.POST)
	public void batchEditDevice(@RequestBody String requestStr, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JSONObject requestJSON = JSON.parseObject(requestStr);
		String deviceIds = requestJSON.getString("devices");
		String[] deviceIdArr = deviceIds.split(",");
		for (int i = 0; i < deviceIdArr.length; i++) {
			Long deviceId = Long.valueOf(deviceIdArr[i]);
			TrkInfo trkInfo = trkInfoMapper.selectByPrimaryKey(deviceId);
			if (requestJSON.containsKey("hwVer"))
				trkInfo.setHwVer(requestJSON.getInteger("hwVer"));
			if (requestJSON.containsKey("modelId"))
				trkInfo.setModelId(requestJSON.getInteger("modelId"));
			if (requestJSON.containsKey("orgId"))
				trkInfo.setOrgId(requestJSON.getInteger("orgId"));
			if (requestJSON.containsKey("updateId"))
				trkInfo.setUpdateId(requestJSON.getInteger("updateId"));
			if (requestJSON.containsKey("password"))
				trkInfo.setPassword(requestJSON.getString("password"));
			if (requestJSON.containsKey("state"))
				trkInfo.setState(requestJSON.getInteger("state"));
			if (requestJSON.containsKey("language"))
				trkInfo.setLanguage(requestJSON.getString("language"));
			if (requestJSON.containsKey("trailerId"))
				trkInfo.setTrailerId(requestJSON.getString("trailerId"));
			if (requestJSON.containsKey("timeZone"))
				trkInfo.setTimeZone(requestJSON.getInteger("timeZone"));
			if (trkInfo.getUpdateIndex() == null) {
				trkInfo.setUpdateIndex(0);
			}
			trkInfo.setUpdateIndex(trkInfo.getUpdateIndex() + 1);
			trkInfoMapper.updateByPrimaryKeySelective(trkInfo);
		}
		this.sendOk();
	}

	@RequiresPermissions("org:device:edit-setting")
	@RequestMapping(value = { "/batchEditDeviceSetting" }, method = RequestMethod.POST)
	public void batchEditDeviceSetting(@RequestBody String requestStr, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JSONObject requestJSON = JSON.parseObject(requestStr);
		String deviceIds = requestJSON.getString("devices");
		String[] deviceIdArr = deviceIds.split(",");
		for (int i = 0; i < deviceIdArr.length; i++) {
			Long deviceId = Long.valueOf(deviceIdArr[i]);
			TrkInfo info = trkInfoMapper.selectByPrimaryKey(deviceId);
			TrkSetting trkSetting = trkSettingMapper.selectByPrimaryKey(deviceId);

			if (info == null || trkSetting == null) {
				logger.info("batchEditDeviceSetting:  device is null" + deviceId);
				continue;
			}
			// 用于标记updateflag
			info.setUpdateFlag(1);

			// settingIndex自增1
			if (trkSetting.getUpdateIndex() == null) {
				trkSetting.setUpdateIndex(0);
			}
			trkSetting.setUpdateIndex(trkSetting.getUpdateIndex() + 1);

			// 处理setting参数
			if (requestJSON.containsKey("agpsEnabled"))
				trkSetting.setAgpsEnabled(requestJSON.getInteger("agpsEnabled"));
			if (requestJSON.containsKey("relayOut"))
				trkSetting.setRelayOut(requestJSON.getInteger("relayOut"));
			if (requestJSON.containsKey("distanceOffset"))
				trkSetting.setDistanceOffset(requestJSON.getInteger("distanceOffset"));
			if (requestJSON.containsKey("gpsWeakTimeout"))
				trkSetting.setGpsWeakTimeout(requestJSON.getInteger("gpsWeakTimeout"));
			if (requestJSON.containsKey("gpsAntiDrifting"))
				trkSetting.setGpsAntiDrifting(requestJSON.getInteger("gpsAntiDrifting"));
			if (requestJSON.containsKey("gpsHdopThreshold"))
				trkSetting.setGpsHdopThreshold(requestJSON.getFloat("gpsHdopThreshold"));
			if (requestJSON.containsKey("gsensorSensitivity"))
				trkSetting.setGsensorSensitivity(requestJSON.getInteger("gsensorSensitivity"));
			if (requestJSON.containsKey("reportServer"))
				trkSetting.setReportServer(requestJSON.getString("reportServer"));
			if (requestJSON.containsKey("testServer"))
				trkSetting.setTestServer(requestJSON.getString("testServer"));
			if (requestJSON.containsKey("timeZone"))
				trkSetting.setTimeZone(requestJSON.getInteger("timeZone"));

			if (requestJSON.containsKey("workingPulseEnable"))
				trkSetting.setWorkingPulseEnable(requestJSON.getInteger("workingPulseEnable"));
			if (requestJSON.containsKey("workingPulseInterval"))
				trkSetting.setWorkingPulseInterval(requestJSON.getInteger("workingPulseInterval"));
			if (requestJSON.containsKey("workingReportEnable"))
				trkSetting.setWorkingReportEnable(requestJSON.getInteger("workingReportEnable"));
			if (requestJSON.containsKey("workingReportInterval"))
				trkSetting.setWorkingReportInterval(requestJSON.getInteger("workingReportInterval"));

			if (requestJSON.containsKey("batteryPulseEnable"))
				trkSetting.setBatteryPulseEnable(requestJSON.getInteger("batteryPulseEnable"));
			if (requestJSON.containsKey("batteryPulseInterval"))
				trkSetting.setBatteryPulseInterval(requestJSON.getInteger("batteryPulseInterval"));
			if (requestJSON.containsKey("batteryReportEnable"))
				trkSetting.setBatteryReportEnable(requestJSON.getInteger("batteryReportEnable"));
			if (requestJSON.containsKey("batteryReportInterval"))
				trkSetting.setBatteryReportInterval(requestJSON.getInteger("batteryReportInterval"));

			if (requestJSON.containsKey("testmodePulseEnable"))
				trkSetting.setTestmodePulseEnable(requestJSON.getInteger("testmodePulseEnable"));
			if (requestJSON.containsKey("testmodePulseInterval"))
				trkSetting.setTestmodePulseInterval(requestJSON.getInteger("testmodePulseInterval"));
			if (requestJSON.containsKey("testmodeReportEnable"))
				trkSetting.setTestmodeReportEnable(requestJSON.getInteger("testmodeReportEnable"));
			if (requestJSON.containsKey("testmodeReportInterval"))
				trkSetting.setTestmodeReportInterval(requestJSON.getInteger("testmodeReportInterval"));
			trkSettingMapper.updateByPrimaryKeySelective(trkSetting);
			trkInfoMapper.updateByPrimaryKey(info);
		}
		this.sendOk();
	}

	@RequiresPermissions("org:device:view")
	@RequestMapping(value = { "/getDeviceById" }, method = RequestMethod.GET)
	public TrkInfoDetailDto getDeviceById(@RequestParam(value = "deviceId", required = true) Long deviceId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, Object> params = new HashMap<>();
		if (deviceId != null) {
			params.put("deviceIdStart", deviceId);
		}
		List<TrkInfoDetailDto> trkInfo = trkInfoMapper.getDeviceListByParams(params);
		return trkInfo.get(0);
	}

	@RequiresPermissions("org:device:delete")
	@RequestMapping(value = { "/deleteDevice" }, method = RequestMethod.GET)
	public void deleteDevice(@RequestParam(value = "deviceId", required = true) Long deviceId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (deviceId == null || deviceId == 0) {
			throw new WebException(ErrorCode.DEVICE_PARAMETER_ERROR);
		}
		TrkInfo info = trkInfoMapper.selectByPrimaryKey(deviceId);
		if (info == null) {
			throw new WebException(ErrorCode.DEVICE_NOT_FOUND);
		}
		TrkInfoDelete infoLog = this.changeToTrkInfoDelete(info);
		trkInfoDeleteMapper.insertSelective(infoLog);
		int row = trkInfoMapper.deleteByPrimaryKey(deviceId);
		if (row == 0) {
			throw new WebException(ErrorCode.DEVICE_NOT_FOUND);
		}
		sendOk();
	}

	private TrkInfoDelete changeToTrkInfoDelete(TrkInfo info) {
		TrkInfoDelete infoLog = new TrkInfoDelete();
		try {
			BeanUtils.copyProperties(infoLog, info);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return infoLog;
	}
}