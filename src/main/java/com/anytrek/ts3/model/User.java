package com.anytrek.ts3.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.anytrek.ts3.dto.View;
import com.fasterxml.jackson.annotation.JsonView;

import tk.mybatis.mapper.annotation.KeySql;

/**
 * t_user
 * 
 * @author aleen date 2018 Oct 2
 */
@Entity
@Table(name = "t_user")
public class User implements java.io.Serializable {
	/** 版本号 */
	private static final long serialVersionUID = 4138791805869583432L;

	/** 只是前后端传输用， 数据库无对应字段 */
	// @Transient
	// private String token;

	/** userId */
	@Id
	@JsonView(View.Summary.class)
	@KeySql(useGeneratedKeys = true)
	@Column(name = "user_id", unique = true, nullable = false, length = 10)
	private Integer userId;

	/** roleId */
	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "role_id", nullable = true, length = 3)
	private Integer roleId;

	/** orgId */
	@JsonView(View.Summary.class)
	@Column(name = "org_id", nullable = false, length = 19)
	private Integer orgId;

	/** username */
	@JsonView(View.Summary.class)
	@Column(name = "username", nullable = false, length = 18)
	private String username;

	/** password */
	@Column(name = "password", nullable = false, length = 50)
	private String password;

	/** phone */
	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "phone", nullable = true, length = 18)
	private String phone;

	/** contacts */
	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "contacts", nullable = true, length = 50)
	private String contacts;

	/** email */
	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "email", nullable = true, length = 36)
	private String email;

	/** key */
	@Column(name = "`key`", nullable = true, length = 16)
	private String key;

	/** 0为未激活 1为激活 */
	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "activated", nullable = true, length = 3)
	private Integer activated;

	/** 过期日期 */
	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "expiration_date", nullable = true)
	private Timestamp expirationDate;

	/** 账号设置 */
//	@JsonView(View.Summary.class)
//	@Column(name = "setting", nullable = true, length = 64)
//	private String setting;

	@Column(name = "flag", nullable = true, length = 4)
	private Integer flag;

	/** createTime */
	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "create_time", nullable = false)
	private Timestamp createTime;

	/** modifyTime */
	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "modify_time", nullable = false)
	private Timestamp modifyTime;

	/**
	 * 获取userId
	 * 
	 * @return userId
	 */
	public Integer getUserId() {
		return this.userId;
	}

	/**
	 * 设置userId
	 * 
	 * @param userId
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 获取username
	 * 
	 * @return username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * 设置username
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 获取orgId
	 * 
	 * @return orgId
	 */
	public Integer getOrgId() {
		return this.orgId;
	}

	/**
	 * 设置orgId
	 * 
	 * @param orgId
	 */
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取password
	 * 
	 * @return password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * 设置password
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取phone
	 * 
	 * @return phone
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * 设置phone
	 * 
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取contacts
	 * 
	 * @return contacts
	 */
	public String getContacts() {
		return this.contacts;
	}

	/**
	 * 设置contacts
	 * 
	 * @param contacts
	 */
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	/**
	 * 获取email
	 * 
	 * @return email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * 设置email
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * 获取key
	 * 
	 * @return key
	 */
	public String getKey() {
		return this.key;
	}

	/**
	 * 设置key
	 * 
	 * @param key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 获取0为未激活 1为激活
	 * 
	 * @return 0为未激活 1为激活
	 */
	public Integer getActivated() {
		return this.activated;
	}

	/**
	 * 设置0为未激活 1为激活
	 * 
	 * @param activated
	 *            0为未激活 1为激活
	 */
	public void setActivated(Integer activated) {
		this.activated = activated;
	}

	/**
	 * 获取过期日期
	 * 
	 * @return 过期日期
	 */
	public Timestamp getExpirationDate() {
		return this.expirationDate;
	}

	/**
	 * 设置过期日期
	 * 
	 * @param expirationDate
	 *            过期日期
	 */
	public void setExpirationDate(Timestamp expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	/**
	 * 获取createTime
	 * 
	 * @return createTime
	 */
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	/**
	 * 设置createTime
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取modifyTime
	 * 
	 * @return modifyTime
	 */
	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	/**
	 * 设置modifyTime
	 * 
	 * @param modifyTime
	 */
	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}
//
//	public String getSetting() {
//		return setting;
//	}
//
//	public void setSetting(String setting) {
//		this.setting = setting;
//	}

}