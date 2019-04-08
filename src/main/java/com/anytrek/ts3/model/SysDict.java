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
@Table(name = "sys_dict")
public class SysDict implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3796026921508565720L;
	@Id
	@KeySql(useGeneratedKeys = true)
	@JsonView(View.Summary.class)
	@Column(name = "dict_id", nullable = false, length = 20)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer dictId;
	@JsonView(View.Summary.class)
	@Column(name = "dict_value", nullable = false, length = 50)
	private String dictValue;
	@JsonView(View.Summary.class)
	@Column(name = "dict_name", nullable = false, length = 50)
	private String dictName;
	@JsonView(View.Summary.class)
	@Column(name = "type", nullable = true, length = 50)
	private String type;
	@JsonView(View.Summary.class)
	@Column(name = "description", nullable = true, length = 100)
	private String description;
	@JsonView(View.Summary.class)
	@Column(name = "order_num", nullable = true, length = 11)
	private Integer orderNum;
	@JsonView(View.Summary.class)
	@Column(name = "remarks", nullable = true, length = 255)
	private String remarks;
	@JsonView(View.Summary.class)
	@Column(name = "flag", nullable = false, length = 4)
	private Integer flag;
	@Column(name = "create_time", nullable = false)
	private Timestamp createTime;

	public Integer getDictId() {
		return dictId;
	}

	public void setDictId(Integer dictId) {
		this.dictId = dictId;
	}

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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