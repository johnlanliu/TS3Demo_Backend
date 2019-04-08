package com.anytrek.ts3.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;


/**
 * 用于屏蔽路径不存在等错误信息
 * 拦截error路径（SpringBoot默认的错误页面跳转路径）
 * 对 new ResponseEntity 返回的错误不生效，  原因未知。
 * @author John
 * date 2018 M09 20
 */
@RestController
public class AppErrorController implements ErrorController {
	private static Logger logger = LogManager.getLogger(AppErrorController.class);

    private static final String PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(value = PATH,  produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> error(WebRequest webRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> error = errorAttributes.getErrorAttributes(webRequest, false);
        Integer httpCode=(Integer)error.get("status");
        String path=(String)error.get("path");
        String message=(String)error.get("message"); 
//        String trace=(String)error.get("trace");
        logger.error("Path:"+path+", Msg:"+message+", Code:" + httpCode);
        JSONObject result = new JSONObject();
        try {
            result.put("errorMsg", message);
			result.put("errorCode", httpCode);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new ResponseEntity<String>(result.toString(), HttpStatus.valueOf(httpCode));
    }


    @Override
    public String getErrorPath() {
        return PATH;
    }
}