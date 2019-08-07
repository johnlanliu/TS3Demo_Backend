package com.anytrek.ts3.exception;

/**
 * 平台错误码， 
 * 前端也有一个ErrorCode.js 需同步维护
 * 
 * 具体模块的错误码命名规范： 
 * 前3位 http code 
 * 中1或2位 模块 
 * 后2位 错误顺序号
 * 
 * @author John date 2018 M09 28
 */
public class ErrorCode {

	/******************************************
	 * 全局错误
	 ******************************************/
	public static final String OK = "200"; // 正常
	public static final String BAD_REQUEST = "400"; // 参数错误
	public static final String UNAUTHORIZED = "403"; // 未授权
	public static final String NOT_LOGIN = "4031"; // 未登陆
	public static final String NOT_FOUND = "404"; // 未找到
	public static final String SERVER_ERROR = "500"; // Server Error
	public static final String DATABASE_ERROR = "5001"; // database Error
	public static final String REST_ERROR = "5002"; // rest api error
	public static final String JSON_ERROR = "5003"; // Server Error

	public static final String FILE_NOT_PIC = "40001"; // 非图片
	public static final String FILE_TOO_BIG = "40002"; // 文件过大

	public static final String EMAIL_FORMAT_ERROR = "40010"; // email格式错误
	public static final String CAPTCHA_ERROR = "40011"; // 验证码错误
	/******************************************
	 * 全局错误
	 ******************************************/

	/******************************************
	 * 1 User
	 ******************************************/
	/** 请求出现错误等 **/
	public static final String USER_PASSWORD_INCORRECT = "400100";
	public static final String USER_REPEAT = "400102"; // 账号已存在
	public static final String USER_EMAIL_REPEAT = "400103"; // email已存在
	public static final String USER_PARAMETER_ERROR = "400104"; // 参数错误
	public static final String USER_REGISTERED = "400106";
	public static final String REPEAT_BIND = "400107";
	public static final String USER_IS_DELETED = "400108";

	/** 没有权限 **/
	public static final String USER_NOT_CHANGE_SELF_STATUS = "403100"; // 不能改变自身角色
	public static final String USER_CAN_NOT_DELETE_SELF = "403101"; // 不能删除自身
	public static final String USER_NOT_OWN_DEVICE = "403102"; // 该设备不属于你
	public static final String USER_UNACTIVATED = "403103"; // 账号未激活
	
	/** 请求的内容不存在 **/
	public static final String USER_NOT_FOUND = "404100"; // 账号不存在
	public static final String USER_NO_BIND_TRACKER = "404101";
	public static final String USER_EMAIL_NOT_FOUND = "404102"; // email未找到
	/******************************************
	 * 1 User
	 ******************************************/

	/******************************************
	 * 2 Device
	 ******************************************/
	/** 请求出现错误等 **/
	public static final String DEVICE_PASSWORD_INCORRECT = "400200";
	public static final String DEVICE_NOT_ACTIVATED = "400201";
	public static final String DEVICE_REPEAT = "400202"; // IMEI重复
	public static final String DEVICE_MOBILE1_INCORRECT = "400203";
	public static final String DEVICE_SETTING_INCORRECT = "400204";
	public static final String DEVICE_IMEI_INCORRECT = "400205";
	public static final String DEVICE_FIRMWARE_FILE_TYPE_ERROR = "400206";
	public static final String DEVICE_FIRMWARE_VERSION_REPEAT_ERROR = "400207";
	public static final String DEVICE_PARAMETER_ERROR = "400208";
	public static final String DEVICE_MODEL_DIFFERENT_WEHN_UPDATE = "400209";
	public static final String DEVICE_HW_DIFFERENT_WEHN_UPDATE = "400210";  //硬件版本不匹配
	public static final String DEVICE_BIND_VEHICLE = "400211";  //设备已绑定车辆
	
	/** 没有权限 **/
	public static final String DEVICE_IS_LOCKED = "403200";
	public static final String DEVICE_NOT_FOR_SALES = "403201"; // 该设备不处于待售状态
	public static final String DEVICE_NOT_CHANGE_COMPANY_WITH_USING = "403202"; // 不能修改直属公司当状态为Using
	public static final String DEVICE_NOT_CHANGE_COMPANY_TO_CHILD = "403203"; // 公司必须是属性结构
	public static final String DEVICE_NOT_BELONG_TO_YOU = "403204"; //无权限操作此设备

	/** 请求的内容不存在 **/
	public static final String DEVICE_NOT_FOUND = "404200";
	public static final String DEVICE_TRACKER_NOT_FOUND = "404201"; // 该设备未录入到Tracker Server
	
	/******************************************
	 * 2 Device
	 ******************************************/

	/******************************************
	 * 4 权限 role
	 ******************************************/
	/** 请求出现错误等 **/
	public static final String ROLE_PARAMETER_ERROR = "400401"; // 参数错误 id为空等
	public static final String ROLE_REPEAT = "400402"; // 角色重复
	public static final String ROLE_ADD_MENU_IS_EMPTY = "400403"; // 增加角色时菜单权限为空
	public static final String ROLE_HAS_USER = "400404"; // 角色下属有用户
	public static final String ROLE_IS_DELETED = "400405";// 该角色已删除
	
	public static final String MENU_PARAMETER_ERROR = "400411"; // 参数错误 id为空等
	public static final String MENU_REPEAT = "400412"; // 菜单重复
	public static final String MENU_HAS_SON = "400413"; // 菜单下有子菜单未删除

	public static final String DICT_PARAMETER_ERROR = "400421"; // 参数错误 id为空等
	public static final String DICT_REPEAT = "400422"; // 数字字典名称重复

	/** 请求的内容不存在 **/
	public static final String ROLE_NOT_FOUND = "404400";
	public static final String MENU_NOT_FOUND = "404401";
	public static final String DICT_NOT_FOUND = "404402";
	/******************************************
	 * 4 权限 role
	 ******************************************/

	/******************************************
	 * 5 sim
	 ******************************************/
	/** 请求出现错误等 **/
	public static final String SIM_PASSWORD_INCORRECT = "400500";
	public static final String SIM_REPEAT = "400501"; // 账号已存在
	public static final String SIM_PARAM_ERROR = "400502"; // 参数错误
	public static final String SIM_RECHARGE_ERROR = "400504"; // 充值出错
	public static final String SIM_SEND_SMS_ERROR = "400505"; // 发送短信出错
	public static final String SIM_IS_DELETED = "400506"; // sim已删除
	
	/** 没有权限 **/
	public static final String SIM_TOPCONNECT_AUTH_FAILED = "403500"; //topconnect验证失败

	/** 请求的内容不存在 **/
	public static final String SIM_NOT_FOUND = "404500"; // SIM不存在
	/******************************************
	 * 5 sim
	 ******************************************/

	/******************************************
	 * 6 Company
	 ******************************************/
	/** 请求出现错误等 **/
	public static final String COMPANY_NOT_DELETE_SELF = "400600"; // 不能删除自己
	public static final String COMPANY_PARAMETER_ERROR = "400601"; // 参数错误 id为空等
	public static final String COMPANY_NOT_DELETE_HAVE_CHILD = "400602"; // 不能删除有下属公司的
	public static final String COMPANY_NOT_DELETE_HAVE_DEVICE = "400603"; // 不能删除有设备的公司
	public static final String COMPANY_NOT_DELETE_HAVE_USER = "400604"; // 不能删除有用户的公司
	public static final String COMPANY_REPEAT = "400605"; // 重复
	public static final String COMPANY_NOT_CHANGE_PARENT_TO_SUBCOMPANY = "400606"; // 不能修改一个公司的母公司为该公司自身或者子公司
	public static final String COMPANY_EMAIL_REPEAT = "400607"; // email已存在
	public static final String COMPANY_NODE_SETTING_OUT_OF_RANGE = "400608";
	public static final String COMPANY_REPEAT_ACTIVATED = "400609";
	public static final String COMPANY_IS_DELETED = "400610";
	
	/** 没有权限 **/
	public static final String COMPANY_NOT_BELONG_TO_YOU = "403600"; //无权限操作此公司
	/** 请求的内容不存在 **/
	public static final String COMPANY_NOT_FOUND = "404600";
	/******************************************
	 * 6 Company
	 ******************************************/

	/******************************************
	 * 7 Model / Update / Batch
	 ******************************************/
	/** 请求出现错误等 **/
	public static final String UPDATE_REPEAT = "400701";
	public static final String UPDATE_PARAMETER_ERROR = "400702";
	public static final String MODEL_REPEAT = "400703";
	public static final String MODEL_HAS_DEVICE = "400704";
	public static final String MODEL_PARAMETER_ERROR = "400705";
	public static final String BATCH_REPEAT = "400706";
	public static final String BATCH_PARAMETER_ERROR = "400707";
	public static final String ERROR_EXCEL_FORMAT = "400708";
	public static final String BATCH_NAME_REPEAT_ERROR = "400709";
	public static final String BATCH_HAS_DEVICE = "400710";
	public static final String MODEL_IS_DELETED = "400711";
	public static final String UPDATE_IS_DELETED = "400712";
	public static final String BATCH_IS_DELETED = "400713";
	public static final String BAD_BATCH_DOCUMENT = "400714";
	public static final String MODEL_HAS_BATCH = "400715";
	public static final String MODEL_HAS_UPDATE = "400716";
	
	/** 没有权限 **/

	/** 请求的内容不存在 **/
	public static final String UPDATE_NOT_FOUND = "404701";
	public static final String MODEL_NOT_FOUND = "404702";
	public static final String BATCH_NOT_FOUND = "404703";
	/******************************************
	 * 7 Model / Update / Batch
	 ******************************************/

	/******************************************
	 * 8 Order / Payment
	 ******************************************/
	/** 请求出现错误等 **/
	public static final String ORDER_REPEAT = "400801";
	public static final String ORDER_PARAMETER_ERROR = "400802";
	public static final String PAYMENT_REPEAT = "400811";
	public static final String PAYMENT_PARAMETER_ERROR = "400812";
	/** 没有权限 **/

	/** 请求的内容不存在 **/
	public static final String ORDER_NOT_FOUND = "404800";
	public static final String PAYMENT_NOT_FOUND = "404810";
	/******************************************
	 * 8 Order
	 ******************************************/

	/******************************************
	 * 9 POI
	 ******************************************/
	/** 请求出现错误等 **/
	public static final String POI_GROUP_NOT_DELETE_HAVE_POI = "400900";

	/** 没有权限 **/
	public static final String POI_NOT_BELONG_TO_YOU = "403900";

	/** 请求的内容不存在 **/
	public static final String POI_NOT_FOUND = "404900";
	/******************************************
	 * 9 POI
	 ******************************************/

	/******************************************
	 * 10 Group
	 ******************************************/
	/** 请求出现错误等 **/
	public static final String GROUP_NOT_DELETE_HAVE_DEVICE = "4001000";
	public static final String GROUP_PARAMETER_ERROR = "4001001";
	public static final String GROUP_IS_DELETED = "4001002";
	/** 没有权限 **/
	public static final String GROUP_NOT_BELONG_TO_YOU = "4031001";

	/** 请求的内容不存在 **/
	public static final String GROUP_NOT_FOUND = "4041000";

	/******************************************
	 * 10 Group
	 ******************************************/

	/******************************************
	 * 11 Driver/Vehicle
	 ******************************************/
	/** 请求出现错误等 **/
	public static final String DRIVER_PARAMETER_ERROR = "4001100";
	public static final String VEHICLE_PARAMETER_ERROR = "4001101";
	public static final String VEHICLE_BIND_REPEAT = "4001102";
	public static final String DRIVER_IS_DELETED = "4001103";
	public static final String VEHICLE_IS_DELETED = "4001104";
	
	/** 没有权限 **/
	public static final String DRIVER_NOT_BELONG_TO_YOU = "4031101";
	public static final String VEHICLE_NOT_BELONG_TO_YOU = "4031102";
	
	public static final String VEHICLE_NOT_DELETE_HAVE_DEVICE = "4031103";
	public static final String VEHICLE_NOT_CHANGEORGID_HAVE_DEVICE_DRIVER = "4031104";
	public static final String VEHICLE_REPEAT_BIND_DEVICE = "4031105";
	/** 请求的内容不存在 **/
	public static final String DRIVER_NOT_FOUND = "4041100";
	public static final String VEHICLE_NOT_FOUND = "4041101";

	/******************************************
	 * 11 Driver/Vehicle
	 ******************************************/
	/******************************************
	 * 12 Area/Landmark
	 ******************************************/
	/** 请求出现错误等 **/
	public static final String LANDMARKER_PARAMETER_ERROR = "4001200";
	public static final String AREA_PARAMETER_ERROR = "4001201";
	/** 没有权限 **/
	
	/** 请求的内容不存在 **/
	public static final String LANDMARKER_NOT_FOUND = "4041200";
	public static final String AREA_NOT_FOUND = "4041201";

	/******************************************
	 * 12 Area/Landmark
	 ******************************************/
	/******************************************
	 * 13 ShareLocation
	 ******************************************/
	/** 请求出现错误等 **/
	public static final String SHARE_PARAMETER_ERROR = "4001300";
	public static final String SHARE_IS_DELETED = "4001301";
	/** 没有权限 **/
	public static final String SHARE_OUT_OF_DATE = "4031301";
	/** 请求的内容不存在 **/
	public static final String SHARE_NOT_FOUND = "4041300";

	/******************************************
	 * 13 ShareLocation
	 ******************************************/
	/******************************************
	 * 14 ALARM
	 ******************************************/
	/** 请求出现错误等 **/
	public static final String ALARM_PARAMETER_ERROR = "4001400";
	public static final String ALARM_RULE_PARAMETER_ERROR = "4001401";
	public static final String ALARM_RULE_IS_DELETED = "4001402";
	/** 没有权限 **/
	/** 请求的内容不存在 **/
	public static final String ALARM_NOT_FOUND = "4041400";
	public static final String ALARM_RULE_NOT_FOUND = "4041401";

	/******************************************
	 * 14 Alarm
	 ******************************************/
	/******************************************
	 * 15 CHECK
	 ******************************************/
	/** 请求出现错误等 **/
	public static final String CHECK_PARAMETER_ERROR = "4001500";
	/** 没有权限 **/
	public static final String CHECK_PASSWORD_ERROR = "4001501";
	/** 请求的内容不存在 **/
	public static final String CHECK_NOT_FOUND = "4041500";

	/******************************************
	 * 15 CHECK
	 ******************************************/
	/******************************************
	 * 16 MESSAGE
	 ******************************************/
	/** 请求出现错误等 **/
	public static final String MESSAGE_PARAMETER_ERROR = "4001600";
	/** 没有权限 **/
	public static final String MESSAGE_PASSWORD_ERROR = "4001601";
	/** 请求的内容不存在 **/
	public static final String MESSAGE_NOT_FOUND = "4041600";

	/******************************************
	 * 16 MESSAGE
	 ******************************************/
	/******************************************
	 * 17 FEEDBACK
	 ******************************************/
	/** 请求出现错误等 **/
	public static final String FEEDBACK_PARAMETER_ERROR = "4001700";
	/** 没有权限 **/
	/** 请求的内容不存在 **/
	public static final String FEEDBACK_NOT_FOUND = "4041700";

	/******************************************
	 * 17 FEEDBACK
	 ******************************************/
	/******************************************
	 * 
	 ******************************************/
	/** 请求出现错误等 **/

	/** 没有权限 **/

	/** 请求的内容不存在 **/

	/******************************************
	 * 
	 ******************************************/
}
