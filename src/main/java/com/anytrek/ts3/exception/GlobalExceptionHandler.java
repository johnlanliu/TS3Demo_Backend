package com.anytrek.ts3.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.UnknownSessionException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * 拦截错误处理， 错误在aop中处理
 * @author John
 * date 2018 M10 1
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	private static Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);
	
	/**
	 * 自定义异常
	 * @param req
	 * @param e
	 */
	@ResponseBody
	@ExceptionHandler(value = WebException.class)
	public void webExceptionHandler(HttpServletRequest req, WebException e){
	}
	
	/**
     * 前端参数错误   400
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseEntity<String> methodArgumentTypeMismatchException(HttpServletRequest req, MethodArgumentTypeMismatchException e) {
		JSONObject result = new JSONObject();
		try {
			result.put("errorCode", ErrorCode.BAD_REQUEST);
			result.put("errorMsg", "Params error");
		}catch (JSONException je) {
			// TODO: handle exception
		}
		logger.error(result);
        return new ResponseEntity<String>(result.toString(), HttpStatus.BAD_REQUEST);
    }

	/**
	 * shiro 未受权异常
	 * @param req
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = UnauthorizedException.class)
	public @ResponseBody ResponseEntity<String> unauthorizedExceptionHandler(HttpServletRequest req, UnauthorizedException e){
		JSONObject result = new JSONObject();
		try {
			result.put("errorCode", ErrorCode.UNAUTHORIZED);
			result.put("errorMsg", req.getRequestURI());
		}catch (JSONException je) {
			// TODO: handle exception
		}
		logger.error(result);
        return new ResponseEntity<String>(result.toString(), HttpStatus.FORBIDDEN);
	}
	
	@ResponseBody
	@ExceptionHandler(value = UnknownSessionException.class)
	public void unknownSessionExceptionHandler(HttpServletRequest req, UnknownSessionException e){
	}

	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public void exceptionHandler(HttpServletRequest req, Exception e){
		logger.error("", e);
	}
}
