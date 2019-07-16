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
	@Column(name = "description", nullable = true, length = 100)
	private String description;
	
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
	private String trackingNo;
	
	@JsonView(View.Summary.class)
	@Column(name = "sales", nullable = true, length = 100)
	private String sales;
	
	@JsonView(View.Summary.class)
	@Column(name = "create_time", nullable = true, length = 19)
	private Timestamp createTime;
	
	@JsonView(View.Summary.class)
	@Column(name = "modify_time", nullable = true, length = 19)
	private String modifyTime;
	
	@JsonView(View.Summary.class)
	@Column(name = "billing_company", nullable = true, length = 100)
	private String billingCompany;
	
	@JsonView(View.Summary.class)
	@Column(name = "billing_contact", nullable = true, length = 19)
	private String billingContact;
	
	@JsonView(View.Summary.class)
	@Column(name = "billing_number", nullable = true, length = 19)
	private String billingNumber;
	
	@JsonView(View.Summary.class)
	@Column(name = "billing_email", nullable = true, length = 100)
	private String billingEmail;
	
	@JsonView(View.Summary.class)
	@Column(name = "billing_address", nullable = true, length = 100)
	private String billingAddress;
	
	@JsonView(View.Summary.class)
	@Column(name = "shipping_company", nullable = true, length = 100)
	private String shippingCompany;
	
	@JsonView(View.Summary.class)
	@Column(name = "shipping_contact", nullable = true, length = 19)
	private String shippingContact;
	
	@JsonView(View.Summary.class)
	@Column(name = "shipping_number", nullable = true, length = 19)
	private String shippingNumber;
	
	@JsonView(View.Summary.class)
	@Column(name = "shipping_email", nullable = true, length = 100)
	private String shippingEmail;
	
	@JsonView(View.Summary.class)
	@Column(name = "shipping_address", nullable = true, length = 100)
	private String shippingAddress;
	
	@JsonView(View.Summary.class)
	@Column(name = "note", nullable = true, length = 100)
	private String note;
	
	@JsonView(View.Summary.class)
	@Column(name = "shipping_via", nullable = true, length = 100)
	private String shippingVia;
	
	@JsonView(View.Summary.class)
	@Column(name = "shipping_fee", nullable = true, length = 100)
	private Float shippingFee;
	
	@JsonView(View.Summary.class)
	@Column(name = "same_as_billing", nullable = true, length = 100)
	private Integer sameAsBilling;

	@JsonView(View.Summary.class)
	@Column(name = "payment_term", nullable = true, length = 100)
	private String paymentTerm;

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

	public String getTrackingNo() {
		return trackingNo;
	}

	public void setTrackingNo(String trackingNo) {
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
	
	public String getBillingCompany() {
		return billingCompany;
	}

	public void setBillingCompany(String billingCompany) {
		this.billingCompany = billingCompany;
	}

	public String getBillingContact() {
		return billingContact;
	}

	public void setBillingContact(String billingContact) {
		this.billingContact = billingContact;
	}

	public String getBillingNumber() {
		return billingNumber;
	}

	public void setBillingNumber(String billingNumber) {
		this.billingNumber = billingNumber;
	}

	public String getBillingEmail() {
		return billingEmail;
	}

	public void setBillingEmail(String billingEmail) {
		this.billingEmail = billingEmail;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getShippingCompany() {
		return shippingCompany;
	}

	public void setShippingCompany(String shippingCompany) {
		this.shippingCompany = shippingCompany;
	}

	public String getShippingContact() {
		return shippingContact;
	}

	public void setShippingContact(String shippingContact) {
		this.shippingContact = shippingContact;
	}

	public String getShippingNumber() {
		return shippingNumber;
	}

	public void setShippingNumber(String shippingNumber) {
		this.shippingNumber = shippingNumber;
	}

	public String getShippingEmail() {
		return shippingEmail;
	}

	public void setShippingEmail(String shippingEmail) {
		this.shippingEmail = shippingEmail;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getShippingVia() {
		return shippingVia;
	}

	public void setShippingVia(String shippingVia) {
		this.shippingVia = shippingVia;
	}

	public Float getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(Float shippingFee) {
		this.shippingFee = shippingFee;
	}

	public Integer getSameAsBilling() {
		return sameAsBilling;
	}

	public void setSameAsBilling(Integer sameAsBilling) {
		this.sameAsBilling = sameAsBilling;
	}

	public String getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}
	

}
