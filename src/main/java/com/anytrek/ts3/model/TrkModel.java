package com.anytrek.ts3.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.KeySql;

/**
 * trk_model
 * 
 * @author aleen date 2018 Oct 2
 */
@Entity
@Table(name = "trk_model")
public class TrkModel implements java.io.Serializable {
	/** 版本号 */
	private static final long serialVersionUID = 2726794075667083260L;

	/** modelId */
	@Id
	@KeySql(useGeneratedKeys = true)
	@Column(name = "model_id", unique = true, nullable = false, length = 10)
	private Integer modelId;

	/** 型号名称 */
	@Column(name = "model_name", nullable = true, length = 16)
	private String modelName;

	/** 产地 */
	@Column(name = "product_origin", nullable = true, length = 32)
	private String productOrigin;

	/** 厂商 */
	@Column(name = "manufacturer", nullable = true, length = 32)
	private String manufacturer;

	/** 型号描述 */
	@Column(name = "description", nullable = true, length = 512)
	private String description;

	/** 产品照片 */
	@Column(name = "picture", nullable = true, length = 128)
	private String picture;

	@Column(name = "flag", nullable = true, length = 4)
	private Integer flag;

	/** 修改时间 */
	@Column(name = "modify_time", nullable = true)
	private Timestamp modifyTime;

	/** 创建时间 */
	@Column(name = "create_time", nullable = true)
	private Timestamp createTime;

	/**
	 * 获取modelId
	 * 
	 * @return modelId
	 */
	public Integer getModelId() {
		return this.modelId;
	}

	/**
	 * 设置modelId
	 * 
	 * @param modelId
	 */
	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

	/**
	 * 获取型号名称
	 * 
	 * @return 型号名称
	 */
	public String getModelName() {
		return this.modelName;
	}

	/**
	 * 设置型号名称
	 * 
	 * @param modelName
	 *            型号名称
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	/**
	 * 获取产地
	 * 
	 * @return 产地
	 */
	public String getProductOrigin() {
		return this.productOrigin;
	}

	/**
	 * 设置产地
	 * 
	 * @param productOrigin
	 *            产地
	 */
	public void setProductOrigin(String productOrigin) {
		this.productOrigin = productOrigin;
	}

	/**
	 * 获取厂商
	 * 
	 * @return 厂商
	 */
	public String getManufacturer() {
		return this.manufacturer;
	}

	/**
	 * 设置厂商
	 * 
	 * @param manufacturer
	 *            厂商
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * 获取型号描述
	 * 
	 * @return 型号描述
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * 设置型号描述
	 * 
	 * @param description
	 *            型号描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取产品照片
	 * 
	 * @return 产品照片
	 */
	public String getPicture() {
		return this.picture;
	}

	/**
	 * 设置产品照片
	 * 
	 * @param picture
	 *            产品照片
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

}