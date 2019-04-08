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

/**
 * t_role_menu
 * 
 * @author aleen date 2018 Oct 2
 */
@Entity
@Table(name = "sys_role_menu")
public class SysRoleMenu implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8506164311143366906L;
	@Id
	@KeySql(useGeneratedKeys = true)
	@JsonView(View.Summary.class)
	@Column(name = "id", nullable = false, length = 10)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonView(View.Summary.class)
	@Column(name = "role_id", nullable = true, length = 11)
	private Integer roleId;

	@JsonView(View.Summary.class)
	@Column(name = "menu_id", nullable = true, length = 11)
	private Integer menuId;

	@Column(name = "create_time", nullable = false)
	private Timestamp createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}