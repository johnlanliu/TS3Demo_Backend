package com.anytrek.ts3.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @program: tool
 * @description:
 * @author: Mr.superbeyone
 * @create: 2018-10-16 12:12
 **/
public class ShapeModel implements Serializable {
   
	private static final long serialVersionUID = 1534826432673888264L;
	private String id;
    private String type;
    private List<Map<String,Object>> geometry;
    private List<Map<String,Object>> properties;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Map<String, Object>> getGeometry() {
		return geometry;
	}
	public void setGeometry(List<Map<String, Object>> geometry) {
		this.geometry = geometry;
	}
	public List<Map<String, Object>> getProperties() {
		return properties;
	}
	public void setProperties(List<Map<String, Object>> properties) {
		this.properties = properties;
	}
    
    
}
