package com.anytrek.ts3.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.UnknownSessionException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.anytrek.ts3.exception.ErrorCode;
import com.anytrek.ts3.exception.WebException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.Arrays;

/**
 * 
 * @author John date 2018 M10 1
 */
@Aspect
@Component
public class ControllerAspect {

	private static Logger logger = LogManager.getLogger(ControllerAspect.class);
	ThreadLocal<Long> startTime = new ThreadLocal<>();

//	@Autowired
//	private SysLogMapper sysLogMapper;

	/**
	 * 判断是否登陆
	 */
	@Pointcut("execution(public * com.anytrek.ts3.controller..*.*(..))"
			+ "&& !execution(public * com.anytrek.ts3.controller.CommonController.*(..))"
			+ "&& !execution(public * com.anytrek.ts3.controller.auth.AuthorizeController.toLogin(..))"
			+ "&& !execution(public * com.anytrek.ts3.controller.auth.AuthorizeController.toLogout(..))"
			+ "&& !execution(public * com.anytrek.ts3.controller.auth.AuthorizeController.getguid(..))"
			+ "&& !execution(public * com.anytrek.ts3.controller.auth.AuthorizeController.accountActive(..))"
			+ "&& !execution(public * com.anytrek.ts3.controller.auth.AuthorizeController.organizationActive(..))"
			+ "&& !execution(public * com.anytrek.ts3.controller.auth.AuthorizeController.sendResetPwdEmail(..))"
			+ "&& !execution(public * com.anytrek.ts3.controller.auth.AuthorizeController.resetPwd(..))"
			+ "&& !execution(public * com.anytrek.ts3.controller.organization.DeviceController.getDeviceByOrgIdAndId(..))"
			+ "&& !execution(public * com.anytrek.ts3.controller.organization.OrganizationController.getOrgIdByKey(..))"
			)
	public void auth() {
	}

	// 记录用户操作
//	@Around("auth()")
//	public Object around(ProceedingJoinPoint point) throws Throwable {
//		long beginTime = System.currentTimeMillis();
//		// 执行方法
//		Object result = point.proceed();
//		// 执行时长(毫秒)
//		long time = System.currentTimeMillis() - beginTime;
//		// 保存日志
//		saveSysLog(point, time);
//		return result;
//	}

	/**
	 * 鉴权
	 */
	@Before("auth()")
	public void doAuthBefore(JoinPoint joinPoint) throws Throwable {
		// 记录执行前时间
		startTime.set(System.currentTimeMillis());
		// this.authManager.auth();
	}

	/**
	 * 统一处理所有接口的日志及异常处理
	 */
	@Pointcut("execution(public * com.anytrek.ts3.controller..*.*(..))")
	public void controller() {
	}

	@Before("controller()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 记录执行前时间
		startTime.set(System.currentTimeMillis());
	}

	/**
	 * 统一处理接口的异常， 后置异常通知 {"errorCode":500, "errorMsg":""}
	 */
	@AfterThrowing(throwing = "e", pointcut = "controller()")
	public void exceptionHandler(JoinPoint joinPoint, Throwable e) {
		// 记录错误日志
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String url = request.getRequestURL() + "|" + request.getMethod();
		String ip = this.getIP(request);
		String params = Arrays.toString(joinPoint.getArgs());
		Long t = System.currentTimeMillis() - startTime.get();
		logger.error("elapsed time->" + t + "; ip->" + ip + "; url->" + url + "; params->" + params + "; error->"
				+ e.toString());

		// 返回错误信息
		if (e instanceof WebException) {
			WebException webException = (WebException) e;
			sendError(webException.errorCode, webException.errorMsg);
		} else if (e instanceof JSONException) {
			sendError(ErrorCode.JSON_ERROR, "JSON Error");
		} else if (e instanceof UnauthorizedException) {
			sendError(ErrorCode.UNAUTHORIZED, "Forbidden");
		} else if (e instanceof AuthorizationException) {
			sendError(ErrorCode.UNAUTHORIZED, "Forbidden");
		} else if (e instanceof UnknownSessionException) {
			sendError(ErrorCode.NOT_LOGIN, "Login please!");
		} else {
			logger.error("", e);
			sendError("500", "Server Error");
		}
	}

	/**
	 * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
	 */
	@AfterReturning(returning = "ret", pointcut = "controller()")
	public void after(JoinPoint joinPoint) {
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		response.setCharacterEncoding("UTF-8");
		// 记录错误日志
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String url = request.getRequestURL() + "|" + request.getMethod();
		String ip = this.getIP(request);
		String params = Arrays.toString(joinPoint.getArgs());
		Long t = System.currentTimeMillis() - startTime.get();
		logger.info("elapsed time->" + t + "; ip->" + ip + "; url->" + url + "; ip->" + ip + "; params->" + params);
	}

	// 环绕通知,环绕增强，相当于MethodInterceptor
	// @Around("controller()")
	// public Object arround(ProceedingJoinPoint pjp) {
	// try {
	// Object o = pjp.proceed();
	// return o;
	// } catch (Throwable e) {
	// e.printStackTrace();
	// return null;
	// }
	// }

	/**
	 * nginx反向代理后无法直接获取用户ip
	 * 
	 * @param request
	 * @return
	 */
	private String getIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	private void sendError(String errorCode, String errorMessage) {
		try {
			HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getResponse();
			response.reset();
			JSONObject result = new JSONObject();
			result.put("errorCode", errorCode);
			result.put("error", errorMessage);
			String responseStr = result.toString();
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Type", "text/plain;charset=UTF-8");
			response.setHeader("icop-content-type", "exception");
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Credentials", "true");
			PrintWriter writer = response.getWriter();
			writer.print(responseStr);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			logger.error("Aspect:sendError->" + e);
		}
	}

//	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
//		// 查询用户的权限
//		Session session = SecurityUtils.getSubject().getSession();
//		Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
//		SimplePrincipalCollection coll = (SimplePrincipalCollection) obj;
//		String userStr = JSON.toJSON(coll.getPrimaryPrincipal()).toString();
//		User loginUser = JSON.parseObject(userStr, User.class);
//		String userName = loginUser.getUsername();
//		if (joinPoint.getTarget() instanceof SysLogMapper) {
//			return;
//		}
//		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//		SysLog sysLog = new SysLog();
//
//		// 请求的方法名
//		String className = joinPoint.getTarget().getClass().getName();
//		String methodName = signature.getName();
//		sysLog.setMethod(className + "." + methodName + "()");
//
//		// 请求的参数
//			String params = Arrays.toString(joinPoint.getArgs());
//			if (params.length() > 200) {
//				params = params.substring(0, 200) + "...";
//			}
//			sysLog.setParams(params);
//
//		// 获取request
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//				.getRequest();
//		// 设置IP地址
//		sysLog.setIp(this.getIP(request));
//
//		// 用户名
//		sysLog.setUserName(userName);
//		sysLog.setCreateBy(userName);
//		// 执行时长(毫秒)
//		sysLog.setTime(time);
//
//		sysLog.setCreateTime(new Timestamp(new Date().getTime()));
//		// 保存系统日志
//		sysLogMapper.insert(sysLog);
//	}
}