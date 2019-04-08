package com.anytrek.ts3;

import java.util.UUID;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.util.WebUtils;
import org.crazycake.shiro.RedisSessionDAO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.anytrek.ts3.common.Bool;
import com.anytrek.ts3.exception.ErrorCode;
import com.anytrek.ts3.exception.WebException;
import com.anytrek.ts3.mapper.OrganizationMapper;
import com.anytrek.ts3.model.Organization;
import com.anytrek.ts3.model.TrkSetting;
import com.anytrek.ts3.model.User;


/**
 * 基类，用于继承
 * @author John
 * date 2018 M09 15
 */
public class ControllerBase {
	private static Logger logger = LogManager.getLogger(ControllerBase.class);
	
	@Autowired
    private RedisSessionDAO redisSessionDAO;  
	
	@Autowired
	private OrganizationMapper orgMapper;
	
	public User getUserByHeader() throws Exception{

		ServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		//前端ajax的headers中必须传入Authorization的值
		String token = WebUtils.toHttp(request).getHeader("Authorization"); 
		if(StringUtils.isEmpty(token)) {
			token = request.getParameter("token");
		}
		Session session = redisSessionDAO.readSession(token);
		Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
		SimplePrincipalCollection coll = (SimplePrincipalCollection) obj;
		String userStr = JSON.toJSON(coll.getPrimaryPrincipal()).toString();
		User user = JSON.parseObject(userStr, User.class);
        return user;
	}
	

	/**
	 * 判断登陆账号是否有该节点权限
	 * @param orgId
	 * @param loginUser
	 */
	protected void checkUserOrgPermission(Integer orgId) throws Exception {
		User loginUser = this.getUserByHeader();
		if (orgId==null || loginUser==null || loginUser.getOrgId()==null) {
			throw new WebException(ErrorCode.COMPANY_NOT_BELONG_TO_YOU);
		}
		Organization organization = orgMapper.selectByPrimaryKey(orgId);
		if (!orgId.equals(loginUser.getOrgId())  && organization.getParents().indexOf("-"+loginUser.getOrgId().toString()+"-") == -1) {
			throw new WebException(ErrorCode.COMPANY_NOT_BELONG_TO_YOU);
		}
	}
	
	/**
	 * 判断登陆账号是否有该节点权限
	 * @param orgId
	 * @param loginUser
	 */
	protected void checkUserOrgPermission(Integer orgId, User loginUser) {
		if (orgId==null || loginUser==null || loginUser.getOrgId()==null) {
			throw new WebException(ErrorCode.COMPANY_NOT_BELONG_TO_YOU);
		}
		Organization organization = orgMapper.selectByPrimaryKey(orgId);
		if (!orgId.equals(loginUser.getOrgId())  && organization.getParents().indexOf("-"+loginUser.getOrgId().toString()+"-") == -1) {
			throw new WebException(ErrorCode.COMPANY_NOT_BELONG_TO_YOU);
		}
	}
	
	
	protected String getUUID() {
		String key = UUID.randomUUID().toString().replace("-", "");// guid
		return key;
	}
	
	protected JSONObject getOkJSONObject() throws Exception {
		JSONObject o = new JSONObject();
		o.put("status", ErrorCode.OK);
		return o;
	}
	
	protected void sendOk() {
		try{
			ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletResponse response = requestAttributes.getResponse();
			response.setCharacterEncoding("UTF-8");
			JSONObject o = getOkJSONObject();
			response.setContentType("application/json");
			response.getWriter().write(o.toString());
		}catch(Exception e){
			logger.error("", e);
		}
	}
	
	protected void sendJSON(JSONObject result) {
		try{
			ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletResponse response = requestAttributes.getResponse();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			response.getWriter().write(result.toString());
		}catch(Exception e){
			logger.error("", e);
		}
	}
	protected void sendJSON(JSONArray result) {
		try{
			ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletResponse response = requestAttributes.getResponse();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			response.getWriter().write(result.toString());
		}catch(Exception e){
			logger.error("", e);
		}
	}
	



	public TrkSetting initTrkSetting(Long deviceId, String modelName) {
		TrkSetting trkSetting = new TrkSetting();
		// 全局设置
		trkSetting.setDeviceId(deviceId);
		trkSetting.setUpdateIndex(0); //
		trkSetting.setReportServer("ts2.anytrek.com:9999");
		trkSetting.setTestServer("ts2.anytrek.com:9999");
		trkSetting.setTimeZone(-700); // 时区设为美国太平洋时间
		trkSetting.setDistanceOffset(25); // 位置偏移阈值25m
//		trkSetting.setFlag(FlagConstants.FLAG_UNDELETED);

		// GPS控制参数
		trkSetting.setAgpsEnabled(0); // 禁用AGPS
		trkSetting.setGpsWeakTimeout(180); // GPS定位超时180s
		// GSensor控制参数
		trkSetting.setGsensorSensitivity(1); // GSensor灵敏度最高
		// GSensor控制参数
		trkSetting.setGpsAntiDrifting(20);	//GPS抗漂移延时最大值   单位秒
		// GSensor控制参数
		trkSetting.setGpsHdopThreshold(4.5f); //水平分量精度因子的阈值

		// 上报服务器控制参数
		if(modelName==null) modelName="";
		switch (modelName.toUpperCase()) {
		case "TR1401": // TR401E
		case "TR1401E": // TR401E
		case "TR1401F": // TR401F
			trkSetting.setWorkingPulseEnable(Bool.FALSE);
			trkSetting.setWorkingPulseInterval(3600); // Working mode pulse: 1 hour
			trkSetting.setWorkingReportEnable(Bool.FALSE);
			trkSetting.setWorkingReportInterval(60 * 5); // Working mode report: 5 minutes
			trkSetting.setBatteryPulseEnable(Bool.TRUE);
			trkSetting.setBatteryPulseInterval(3600); // Battery mode pulse: 1 hours
			trkSetting.setBatteryReportEnable(Bool.TRUE);
			trkSetting.setBatteryReportInterval(60 * 5); // Battery mode report: 5 minutes
			trkSetting.setTestmodePulseEnable(Bool.TRUE);
			trkSetting.setTestmodePulseInterval(60 * 5); // Test mode pulse: 5 minutes
			trkSetting.setTestmodeReportEnable(Bool.TRUE);
			trkSetting.setTestmodeReportInterval(60 * 5); // Test mode report: 5 minutes
			break;
		case "VT1508A": // VT1508A
		case "VT1508D": // VT1508D
		case "VT1501": // VT1501
		case "VT1501B": // VT1501B
		case "VT1501D": // VT1501D
			trkSetting.setWorkingPulseEnable(Bool.TRUE);
			trkSetting.setWorkingPulseInterval(3600); // Working mode pulse: 1 hour
			trkSetting.setWorkingReportEnable(Bool.TRUE);
			trkSetting.setWorkingReportInterval(60); // Working mode report: 60 seconds
			trkSetting.setBatteryPulseEnable(Bool.TRUE);
			trkSetting.setBatteryPulseInterval(3600 * 6); // Battery mode pulse: 6 hours
			trkSetting.setBatteryReportEnable(Bool.TRUE);
			trkSetting.setBatteryReportInterval(60 * 15); // Battery mode report: 15 minutes
			trkSetting.setTestmodePulseEnable(Bool.TRUE);
			trkSetting.setTestmodePulseInterval(60 * 5); // Test mode pulse: 5 minutes
			trkSetting.setTestmodeReportEnable(Bool.TRUE);
			trkSetting.setTestmodeReportInterval(60 * 5); // Test mode report: 5 minutes
			break;
		case "VT1611": // VT1611
		case "VT1711": // VT1711
			trkSetting.setWorkingPulseEnable(Bool.TRUE);
			trkSetting.setWorkingPulseInterval(3600); // Working mode pulse: 1 hour
			trkSetting.setWorkingReportEnable(Bool.TRUE);
			trkSetting.setWorkingReportInterval(60 * 5); // Working mode report: 5 minutes
			trkSetting.setBatteryPulseEnable(Bool.TRUE);
			trkSetting.setBatteryPulseInterval(3600 * 6); // Battery mode pulse: 6 hours
			trkSetting.setBatteryReportEnable(Bool.TRUE);
			trkSetting.setBatteryReportInterval(60 * 15); // Battery mode report: 15 minutes
			trkSetting.setTestmodePulseEnable(Bool.TRUE);
			trkSetting.setTestmodePulseInterval(60 * 5); // Test mode pulse: 5 minutes
			trkSetting.setTestmodeReportEnable(Bool.TRUE);
			trkSetting.setTestmodeReportInterval(60 * 5); // Test mode report: 5 minutes
			break;
		case "VT1702": // VT1702
		case "VT1802": // VT1802
			trkSetting.setWorkingPulseEnable(Bool.FALSE);
			trkSetting.setWorkingPulseInterval(3600); // Working mode pulse: 1 hour
			trkSetting.setWorkingReportEnable(Bool.FALSE);
			trkSetting.setWorkingReportInterval(60 * 5); // Working mode report: 5 minutes
			trkSetting.setBatteryPulseEnable(Bool.TRUE);
			trkSetting.setBatteryPulseInterval(3600); // Battery mode pulse: 1 hour
			trkSetting.setBatteryReportEnable(Bool.TRUE);
			trkSetting.setBatteryReportInterval(3600); // Battery mode report: 1 hour
			trkSetting.setTestmodePulseEnable(Bool.TRUE);
			trkSetting.setTestmodePulseInterval(60 * 5); // Test mode pulse: 5 minutes
			trkSetting.setTestmodeReportEnable(Bool.TRUE);
			trkSetting.setTestmodeReportInterval(60 * 5); // Test mode report: 5 minutes
			break;
		default:
			trkSetting.setWorkingPulseEnable(Bool.TRUE);
			trkSetting.setWorkingPulseInterval(3600); // Working mode pulse: 1 hour
			trkSetting.setWorkingReportEnable(Bool.TRUE);
			trkSetting.setWorkingReportInterval(60 * 5); // Working mode report: 5 minutes
			trkSetting.setBatteryPulseEnable(Bool.TRUE);
			trkSetting.setBatteryPulseInterval(3600 * 6); // Battery mode pulse: 6 hours
			trkSetting.setBatteryReportEnable(Bool.TRUE);
			trkSetting.setBatteryReportInterval(60 * 15); // Battery mode report: 15 minutes
			trkSetting.setTestmodePulseEnable(Bool.TRUE);
			trkSetting.setTestmodePulseInterval(60 * 5); // Test mode pulse: 5 minutes
			trkSetting.setTestmodeReportEnable(Bool.TRUE);
			trkSetting.setTestmodeReportInterval(60 * 5); // Test mode report: 5 minutes
			break;
		}
		return trkSetting;
	}
}
