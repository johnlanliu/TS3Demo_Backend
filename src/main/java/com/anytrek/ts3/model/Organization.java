package com.anytrek.ts3.model;

import java.sql.Timestamp;
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

/**
 * t_organization
 * 
 * @author aleen date 2018 Oct 2
 */
@Entity
@Table(name = "t_organization")
public class Organization implements java.io.Serializable {
	/** 版本号 */
	private static final long serialVersionUID = 2433151806249623161L;

	/** 只是前后端传输用， 数据库无对应字段 */
	@JsonView(View.Summary.class)
	@Transient
	private Integer parentId;

	/** 只是前后端传输用， 数据库无对应字段 */
	@JsonView(View.Summary.class)
	@Transient
	private String parentName;

	/** orgId */
	@Id
	@JsonView(View.Summary.class)
	@KeySql(useGeneratedKeys = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "org_id", unique = true, nullable = false, length = 11)
	private Integer orgId;

	/** 祖先樹 -1-12-13-15- */
	@Column(name = "parents", nullable = false, length = 11)
	private String parents;

	/** orgName */
	@JsonView(View.Summary.class)
	@Column(name = "org_name", nullable = false, length = 36)
	private String orgName;

	/** attribute: 0-根节点 1-普通节点 4-partner 5-客户 6-owner或子公司 */
	@JsonView(View.Summary.class)
	@Column(name = "attribute", nullable = true, length = 18)
	private Integer attribute;

	/** attribute: 0-根节点 1-普通节点 4-partner 5-客户 6-owner或子公司 */
	@JsonView(View.Summary.class)
	@Column(name = "activated", nullable = true, length = 18)
	private Integer activated;

	/** logo */
	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "logo", nullable = true, length = 256)
	private String logo;

	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "activation_key", nullable = true, length = 256)
	private String activationKey;

	/** accessKey */
	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "access_key", nullable = true, length = 32)
	private String accessKey;
    
    /** needRetry */
    @JsonView(View.SummaryWithDetail.class) 
    @Column(name = "need_retry", nullable = true, length = 256)
    private Integer needRetry;

    /** needPushOurServer */
    @JsonView(View.SummaryWithDetail.class) 
    @Column(name = "need_push_our_server", nullable = true, length = 256)
    private Integer needPushOurServer;

	/** pushurl */
	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "pushurl", nullable = true, length = 256)
	private String pushurl;

	/** contacts */
	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "contacts", nullable = true, length = 50)
	private String contacts;

	/** phone */
	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "phone", nullable = true, length = 18)
	private String phone;

	/** wechat */
	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "wechat", nullable = true, length = 18)
	private String wechat;

	/** streetAddress */
	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "street_address", nullable = true, length = 36)
	private String streetAddress;

	/** remark */
	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "remark", nullable = true, length = 50)
	private String remark;

	/** title */
	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "title", nullable = true, length = 50)
	private String title;

	/** email */
	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "email", nullable = true, length = 36)
	private String email;

	/** zip */
	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "zip", nullable = true, length = 8)
	private String zip;

	/** city */
	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "city", nullable = true, length = 18)
	private String city;

	/** state */
	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "state", nullable = true, length = 18)
	private String state;

	/** country */
	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "country", nullable = true, length = 18)
	private String country;

	@Column(name = "flag", nullable = false, length = 4)
	private Integer flag;

	/** modifyTime */
	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "modify_time", nullable = false)
	private Timestamp modifyTime;

	/** createTime */
	@Column(name = "create_time", nullable = false)
	@JsonView(View.SummaryWithDetail.class)
	private Timestamp createTime;

	/**
	 * 获取orgId
	 * 
	 * 
	 */
	public Integer getOrgId() {
		return orgId;
	}

	/**
	 * 设置orgId
	 * 
	 * 
	 */
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取orgName
	 * 
	 * @return orgName
	 */
	public String getOrgName() {
		return this.orgName;
	}

	/**
	 * 设置orgName
	 * 
	 * @param orgName
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getAttribute() {
		return attribute;
	}

	public void setAttribute(Integer attribute) {
		this.attribute = attribute;
	}

	/**
	 * 获取logo
	 * 
	 * @return logo
	 */
	public String getLogo() {
		return this.logo;
	}

	/**
	 * 设置logo
	 * 
	 * @param logo
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}

	/**
	 * 获取pushurl
	 * 
	 * @return pushurl
	 */
	public String getPushurl() {
		return this.pushurl;
	}

	/**
	 * 设置pushurl
	 * 
	 * @param pushurl
	 */
	public void setPushurl(String pushurl) {
		this.pushurl = pushurl;
	}

	/**
	 * 获取accessKey
	 * 
	 * @return accessKey
	 */
	public String getAccessKey() {
		return this.accessKey;
	}

	/**
	 * 设置accessKey
	 * 
	 * @param accessKey
	 */
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
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
	 * 获取wechat
	 * 
	 * @return wechat
	 */
	public String getWechat() {
		return this.wechat;
	}

	/**
	 * 设置wechat
	 * 
	 * @param wechat
	 */
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	/**
	 * 获取streetAddress
	 * 
	 * @return streetAddress
	 */
	public String getStreetAddress() {
		return this.streetAddress;
	}

	/**
	 * 设置streetAddress
	 * 
	 * @param streetAddress
	 */
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	/**
	 * 获取remark
	 * 
	 * @return remark
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 设置remark
	 * 
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取title
	 * 
	 * @return title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * 设置title
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
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

	/**
	 * 获取zip
	 * 
	 * @return zip
	 */
	public String getZip() {
		return this.zip;
	}

	/**
	 * 设置zip
	 * 
	 * @param zip
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * 获取city
	 * 
	 * @return city
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * 设置city
	 * 
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 获取state
	 * 
	 * @return state
	 */
	public String getState() {
		return this.state;
	}

	/**
	 * 设置state
	 * 
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 获取country
	 * 
	 * @return country
	 */
	public String getCountry() {
		return this.country;
	}

	/**
	 * 设置country
	 * 
	 * @param country
	 */
	public void setCountry(String country) {
		this.country = country;
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

	public String getParents() {
		return parents;
	}

	public void setParents(String parents) {
		this.parents = parents;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Integer getActivated() {
		return activated;
	}

	public void setActivated(Integer activated) {
		this.activated = activated;
	}

	
	
	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getNeedRetry() {
		return needRetry;
	}

	public void setNeedRetry(Integer needRetry) {
		this.needRetry = needRetry;
	}

	public Integer getNeedPushOurServer() {
		return needPushOurServer;
	}

	public void setNeedPushOurServer(Integer needPushOurServer) {
		this.needPushOurServer = needPushOurServer;
	}

}