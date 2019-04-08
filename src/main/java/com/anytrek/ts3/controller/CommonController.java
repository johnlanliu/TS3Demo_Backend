package com.anytrek.ts3.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.anytrek.ts3.ControllerBase;
import com.anytrek.ts3.conf.GlobalConfig;
import com.anytrek.ts3.exception.ErrorCode;
import com.anytrek.ts3.exception.WebException;
import com.anytrek.ts3.model.TrkModel;

/**
 * 
 * @author John
 * date 2018年10月11日
 */

@RestController
@RequestMapping("/common")
public class CommonController extends ControllerBase {
	private static Logger logger = LogManager.getLogger(CommonController.class);

    @Autowired
    private GlobalConfig  globalConfig;

//    @Autowired
//    private PolygonMapper polygonMapper;

//	@RequestMapping(value = { "/geoTest"}, produces = "application/json;charset=utf-8")
//    public String searchTest(@RequestParam(value = "orgId", required = false) Integer orgId) throws Exception {
//    	String result = "";
//    	Integer count = polygonMapper.selectCount(null);
//    	result+=("总共："+count+"个电子栅栏。 \n");
//    	String[] points = {"33.999687 -117.8657", "46.7635 -122.99339",  "36.59883 -79.34241", "34.067005 -117.46349", "33.43462 -112.180504", "34.01564 -117.537155", "46.76248033802032 -123.00092490045557", "46.7624651796464 -123.00114752380381"};
//    	if(orgId==null) orgId=1;
//    	for(int i=0; i<points.length; i++) {
//        	long t1 = System.currentTimeMillis();
//        	try {
//        		String itemResult = "";
//        		String point = points[i];
//        		Polygon param = new Polygon();
//        		param.setGeom(point);
//        		param.setOrgId(orgId);
//            	List<Polygon> list = polygonMapper.getPolygonTest(param);
//            	if(list.size()>0) {
//            		itemResult += "("+point+")触发 "+list.size()+" 个电子栅栏：";
//                	for(Polygon polygon : list) {
//                		itemResult += polygon.getName() + ";";
//                	}
//            	}else {
//            		itemResult += "("+point+")未处于任何电子栅栏中。";
//            	}
//            	itemResult = "总耗时"+(System.currentTimeMillis()-t1)+"毫秒"+"。 " + itemResult + " \n";
//            	result += itemResult;
//    		} catch (Exception e) {
//    			// TODO: handle exception
//    			logger.error("", e);
//    			result = "出错啦！ "+e.getMessage();
//    		}
//    	}
//    	logger.info(result);
//    	return result;
//    }


	/**
	 * 判断用户是否有菜单权限 check role menu 方法已抛弃
	 * 
	 */

	/**
	 * 获取guid（用做生成key使用）
	 * 
	 * @throws JSONException
	 * 
	 */
	@RequestMapping(value = { "/getguid" })
	public void getguid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject responseJSON = new JSONObject();
		String key = this.getUUID();
		responseJSON.put("key", key);
		sendJSON(responseJSON);
	}

	/**
	 * 上传图片  
	 * {"filename":"b55876625a2b4399bb5f6b93a084750e.jpg"}
	 */
	@RequestMapping(value = { "/uploadimage" }, method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody String uploadimage(@RequestParam("file") MultipartFile file, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject responseJSON = new JSONObject();
        String path = globalConfig.getImageUpfilePath();
		String fileName = file.getOriginalFilename();
		//获取后缀名
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        
        //通过后缀判断是否是图片
		if (!fileName.endsWith(".jpg") && !fileName.endsWith(".jpeg")  
                && !fileName.endsWith(".bmp") && !fileName.endsWith(".gif")  
                && !fileName.endsWith(".png")){
			throw new WebException(ErrorCode.FILE_NOT_PIC);
		}
		//限制上传图片大小
		if (file.getSize()>1024*1024) {
			throw new WebException(ErrorCode.FILE_TOO_BIG);
		}
        
		//改用uuid为文件名  uuid.png
        fileName = UUID.randomUUID().toString().replaceAll("-", "") +"."+suffix;  
        File fileDir = new File(new File(path).getAbsolutePath());
        //不存在则创建文件夹
        if(!fileDir.exists()) fileDir.mkdirs();
		File newFile = new File(fileDir, fileName);
		file.transferTo(newFile);
		
		//返回文件名
		responseJSON.put("filename", fileName);
        return responseJSON.toString();
	}
}
