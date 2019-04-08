package com.anytrek.ts3.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 全局配置文件
 * @author John
 * date 2018 M09 20
 */
@ConfigurationProperties(prefix = "anytrek.global")
@Component
public class GlobalConfig {
    private String deviceUpdateFilePath;
    private Long tokenExpires;
    private String imageUpfilePath;
    private String upfileImageURL;
	public String getDeviceUpdateFilePath() {
		return deviceUpdateFilePath;
	}
	public void setDeviceUpdateFilePath(String deviceUpdateFilePath) {
		this.deviceUpdateFilePath = deviceUpdateFilePath;
	}
	public Long getTokenExpires() {
		return tokenExpires;
	}
	public void setTokenExpires(Long tokenExpires) {
		this.tokenExpires = tokenExpires;
	}
	public String getImageUpfilePath() {
		return imageUpfilePath;
	}
	public void setImageUpfilePath(String imageUpfilePath) {
		this.imageUpfilePath = imageUpfilePath;
	}
	public String getUpfileImageURL() {
		return upfileImageURL;
	}
	public void setUpfileImageURL(String upfileImageURL) {
		this.upfileImageURL = upfileImageURL;
	}
    
}
