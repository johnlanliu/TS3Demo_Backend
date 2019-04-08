package com.anytrek.ts3;


//特别注意，下面的是 tk.MapperScan

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 运行主类
 * @author John
 * date 2018 M09 15
 */
@Controller
@EnableWebMvc
@SpringBootApplication
@tk.mybatis.spring.annotation.MapperScan(basePackages = "com.anytrek.ts3.mapper")
public class AdminServerStartup implements WebMvcConfigurer, CommandLineRunner { 
    private Logger logger = LoggerFactory.getLogger(AdminServerStartup.class);
    
    @Autowired
    Environment environment;

    public static void main(String[] args) {
    	SpringApplication.run(AdminServerStartup.class, args); 
    }
    
    /**
     * 用fastJSON替换jackson
     */
//    @Bean
//    public HttpMessageConverters fastJsonHttpMessageConverters(){
//        //创建FastJson信息转换对象
//        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
//        //创建Fastjosn对象并设定序列化规则
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        // 中文乱码解决方案
//        List<MediaType> mediaTypes = new ArrayList<>();
//        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);//设定json格式且编码为UTF-8
//        fastJsonHttpMessageConverter.setSupportedMediaTypes(mediaTypes);
//        //规则赋予转换对象
//        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
//        return new HttpMessageConverters(fastJsonHttpMessageConverter);
//    }
    
    @Override
    public void run(String... args) throws Exception { 
    	String port = environment.getProperty("local.server.port");
        logger.info("Server startup Success on:" + port);
    }
}
