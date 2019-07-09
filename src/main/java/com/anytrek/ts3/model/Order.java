package com.anytrek.ts3.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.anytrek.ts3.dto.View;
import com.fasterxml.jackson.annotation.JsonView;

import tk.mybatis.mapper.annotation.KeySql;

@Entity
@Table(name = "t_order")
public class Order implements java.io.Serializable {

	private static final long serialVersionUID = 2594831637707167672L;

	@Id
	@KeySql(useGeneratedKeys = true)
	@JsonView(View.Summary.class)
	@Column(name = "order_id", nullable = false, length = 10)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	
	@JsonView(View.Summary.class)
	@Column(name = "type", nullable = true, length = 15)
	private String type;
	
	@JsonView(View.Summary.class)
	@Column(name = "customer", nullable = true, length = 100)
	private String customer;
	
	@JsonView(View.Summary.class)
	@Column(name = "status", nullable = true, length = 7)
	private String status;
	
	@JsonView(View.Summary.class)
	@Column(name = "invoice_no", nullable = true, length = 10)
	private Integer invoiceNo;
	
	@JsonView(View.Summary.class)
	@Column(name = "invoice_date", nullable = true, length = 19)
	private String invoiceDate;
	
	@JsonView(View.Summary.class)
	@Column(name = "due_date", nullable = true, length = 19)
	private String dueDate;
	
	@JsonView(View.Summary.class)
	@Column(name = "tracking_no", nullable = true, length = 20)
	private Integer trackingNo;
	
	@JsonView(View.Summary.class)
	@Column(name = "sales", nullable = true, length = 100)
	private String sales;
	
	@JsonView(View.Summary.class)
	@Column(name = "create_time", nullable = true, length = 19)
	private Timestamp createTime;
	
	@JsonView(View.Summary.class)
	@Column(name = "modify_time", nullable = true, length = 19)
	private String modifyTime;
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(Integer invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public Integer getTrackingNo() {
		return trackingNo;
	}

	public void setTrackingNo(Integer trackingNo) {
		this.trackingNo = trackingNo;
	}

	public String getSales() {
		return sales;
	}

	public void setSales(String sales) {
		this.sales = sales;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

}
