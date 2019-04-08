package com.anytrek.ts3.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.anytrek.ts3.dto.View;
import com.fasterxml.jackson.annotation.JsonView;

import tk.mybatis.mapper.annotation.KeySql;

@Entity
@Table(name = "sys_menu")
public class SysMenu implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8506164311143366906L;

	@Id
	@KeySql(useGeneratedKeys = true)
	@JsonView(View.Summary.class)
	@Column(name = "menu_id", nullable = false, length = 10)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer menuId;

	@JsonView(View.Summary.class)
	@Column(name = "parent_id", nullable = true, length = 19)
	private Integer parentId;

	@JsonView(View.Summary.class)
	@Column(name = "menu_name", nullable = true, length = 19)
	private String menuName;

	@JsonView(View.Summary.class)
	@Column(name = "title", nullable = true, length = 32)
	private String title;

	@JsonView(View.Summary.class)
	@Column(name = "component", nullable = true, length = 64)
	private String component;

	@JsonView(View.Summary.class)
	@Column(name = "perms", nullable = true, length = 19)
	private String perms;

	@JsonView(View.Summary.class)
	@Column(name = "url", nullable = true, length = 19)
	private String url;

	@JsonView(View.Summary.class)
	@Column(name = "remark", nullable = true, length = 19)
	private String remark;

	@JsonView(View.Summary.class)
	@Column(name = "type", nullable = true, length = 19)
	private Integer type;

	@JsonView(View.Summary.class)
	@Column(name = "icon", nullable = true, length = 19)
	private String icon;

	@JsonView(View.Summary.class)
	@Column(name = "order_num", nullable = true, length = 19)
	private Integer orderNum;

	@JsonView(View.Summary.class)
	@Column(name = "flag", nullable = true, length = 4)
	private Integer flag;

	@Column(name = "create_time", nullable = false)
	private Timestamp createTime;

	// 非数据库字段
	/** 只是前后端传输用， 数据库无对应字段 */
	@JsonView(View.Summary.class)
	@Transient
	private String parentName;
	// 非数据库字段
	/** 只是前后端传输用， 数据库无对应字段 */
	@JsonView(View.Summary.class)
	@Transient
	private Integer level;
	// 非数据库字段
	/** 只是前后端传输用， 数据库无对应字段 */
	@JsonView(View.Summary.class)
	@Transient
	private List<SysMenu> children;

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	// public String getRemark() {
	// return remark;
	// }
	//
	// public void setRemark(String remark) {
	// this.remark = remark;
	// }

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
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

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public List<SysMenu> getChildren() {
		return children;
	}

	public void setChildren(List<SysMenu> children) {
		this.children = children;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

}