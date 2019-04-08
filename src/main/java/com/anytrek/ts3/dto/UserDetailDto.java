package com.anytrek.ts3.dto;

import javax.persistence.Column;

import com.anytrek.ts3.model.User;
import com.fasterxml.jackson.annotation.JsonView;


/**
 * trkInfo扩展    增加一些关联表属性
 * @author John
 * date 2018 M10 8
 */
public class UserDetailDto extends User{
    /** 版本号 */
    private static final long serialVersionUID = 4034635723711775295L;

    

    @Column(name = "org_name")
    @JsonView(View.SummaryWithDetail.class) 
    private String orgName;
    
    @Column(name = "role_name")
    @JsonView(View.SummaryWithDetail.class) 
    private String roleName;
    
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	} 
    
}
