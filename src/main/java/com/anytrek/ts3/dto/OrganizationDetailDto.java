package com.anytrek.ts3.dto;

import javax.persistence.Column;

import com.anytrek.ts3.model.Organization;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * organization扩展 增加一些关联表属性
 * 
 * @author Aleen date 2018 M12 11
 */
public class OrganizationDetailDto extends Organization {
	/** 版本号 */
	private static final long serialVersionUID = 4034635723711775295L;

	@Column(name = "parent_name")
    @JsonView(View.Summary.class) 
	private String parentName;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
}
