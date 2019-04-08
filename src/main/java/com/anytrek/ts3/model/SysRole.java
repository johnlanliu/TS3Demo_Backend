package com.anytrek.ts3.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.anytrek.ts3.dto.View;
import com.fasterxml.jackson.annotation.JsonView;

import tk.mybatis.mapper.annotation.KeySql;

@Entity
@Table(name = "sys_role")
public class SysRole implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8506164311143366906L;

	@Id
	@KeySql(useGeneratedKeys = true)
	@JsonView(View.Summary.class)
	@Column(name = "role_id", nullable = false, length = 10)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleId;

	@JsonView(View.Summary.class)
	@Column(name = "role_name", nullable = true, length = 100)
	private String roleName;

	@JsonView(View.Summary.class)
	@Column(name = "remark", nullable = true, length = 100)
	private String remark;

	@JsonView(View.Summary.class)
	@Column(name = "valid_org_types", nullable = true, length = 100)
	private String validOrgTypes;
	
	@JsonView(View.Summary.class)
	@Column(name = "valid_user_types", nullable = true, length = 100)
	private String validUserTypes;

	@JsonView(View.Summary.class)
	@Column(name = "flag", nullable = false, length = 4)
	private Integer flag;

	@Column(name = "create_time", nullable = false)
	private Timestamp createTime;
	
	


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getValidOrgTypes() {
		return validOrgTypes;
	}

	public void setValidOrgTypes(String validOrgTypes) {
		this.validOrgTypes = validOrgTypes;
	}

	public String getValidUserTypes() {
		return validUserTypes;
	}

	public void setValidUserTypes(String validUserTypes) {
		this.validUserTypes = validUserTypes;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}