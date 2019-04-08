package com.anytrek.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {

	 /** 
    * Email正则表达式
    */  
   public static final String EMAIL = "^//w+([-+.]//w+)*@//w+([-.]//w+)*//.//w+([-.]//w+)*$";  
     
   /** 
    * 电话号码正则表达式 
    */  
   public static final String PHONE = "(^(//d{2,4}[-_－—]?)?//d{3,8}([-_－—]?//d{3,8})?([-_－—]?//d{1,7})?$)|(^0?1[35]//d{9}$)" ;  
   /** 
    * 手机号码正则表达式
    */  
   public static final String MOBILE ="^[0-9]{11}$";  
   
   /** 
    * 添加组织：Add  sub-node
1、Name:最大36位字符串，纯字母或纯数字或数字字母组合。^[a-zA-Z0-9]{1,36}$
2、Contact Person:最大50位字符串，包含数字字母和空格。^[a-zA-Z0-9\s]{0,49}$
3、Email:最大36位字符串，参考网上正则表达式。^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$
    */  
   public static final String Val_Org_Name ="^[a-zA-Z0-9]{1,36}$";  
   public static final String Val_Org_Person ="^[a-zA-Z0-9\\s]{0,49}$";
   
   /** 
    * 添加设备：Add tracker
1、IMEI:14位纯数字 ^[0-9]{14}$
2、Password:6位纯数字 ^[0-9]{6}
3、Hardware: 最大10位纯数字^[0-9]{0,10}$
    */  
   public static final String Val_Trk_IMEI ="^[0-9]{14}$"; 
   public static final String Val_Trk_Password ="^[0-9]{6}";  
   public static final String Val_Trk_Hardware ="^[0-9]{0,10}$";  
   
   /** 
    * 添加用户：Add user
1、phone:11位纯数字 ^[0-9]{10}$
2、Email:最大36位字符串，参考网上正则表达式。
    */  

   /** 
    * 添加订单:Add order
1、Invoice:最大16位纯数字 ^[0-9]{1,16}$
2、Delivery Date,Payment Due Date：时间格式2017-08-01 00:00:00
    */ 
   public static final String Val_Order_Invoice ="^[0-9]{1,16}$"; 
   
   /** 
    * 添加型号：Add model
1、model name:最大16位字符串，数字和字母组合 ^[a-zA-Z0-9]{1,16}$
2、product_origin:最大32位字符串，纯字母或纯数字或数字字母组合^[a-zA-Z0-9\s]{1,32}
3、manufacturer:最大32位字符串，纯字母或纯数字或数字字母组合^[a-zA-Z0-9\s]{1,32}
4、description: 对型号的描述，最大512位字符串
5、picture:图片存储路径名，最大128位字符串
    */ 
   public static final String Val_Model_Name ="^[a-zA-Z0-9]{1,16}$"; 
   public static final String Val_Model_origin ="^[a-zA-Z0-9\\s]{1,32}"; 
   public static final String Val_Model_manufacturer ="^[a-zA-Z0-9\\s]{1,32}";
   
   /** 
    * 添加软件：Add software
1、Version:最大10位纯数字 ^[0-9]{1,10}$
2、Update name:型号名字，最大32位字符串
3、Update details:型号描述，最大512位字符串
    */ 
   public static final String Val_software_Version ="^[0-9]{1,10}$"; 
   
   /** 
    * 添加批次：Add Batch
1、Quantity:最大11位纯数字^[0-9]{1,11}$
2、BL：最大10位纯数字^[0-9]{1,10}
    */ 
   public static final String Val_Batch_Quantity ="^[0-9]{1,11}$";
   public static final String Val_Batch_BL ="^[0-9]{1,10}"; 
   
   /** 
    * 用户注册：Register
1、Accout:字母开头，允许4-16字节，允许字母数字下划线^[a-zA-Z][a-zA-Z0-9_]{3,15}$
2、Password:6-11位字符串，纯字母或纯数字或数字字母组合^[a-zA-Z0-9]{6,11}$
3、验证码：4位纯数字
    */ 
   public static final String Val_Register_Accout ="^[a-zA-Z][a-zA-Z0-9_]{3,15}$";
   public static final String Val_Register_Password ="^[a-zA-Z0-9]{6,11}$";
   
   
   /** 
    * 匹配是否符合正则表达式pattern 匹配返回true 
    * @param str 匹配的字符串 
    * @param pattern 匹配模式 
    * @return boolean 
    */  
   public static  boolean Regular(String str,String pattern){  
//       System.out.println("pattern="+pattern);  
       if(null == str || str.trim().length()<=0)  
           return false;           
       Pattern p = Pattern.compile(pattern);  
       Matcher m = p.matcher(str);  
       return m.matches();  
   }  
}