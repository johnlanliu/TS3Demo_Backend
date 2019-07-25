package com.anytrek.ts3.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.anytrek.ts3.dto.View;
import com.fasterxml.jackson.annotation.JsonView;

import tk.mybatis.mapper.annotation.KeySql;

//import ????

@Entity
@Table(name = "t_payment")
public class Payment implements java.io.Serializable {

	private static final long serialVersionUID = -5680559786861774469L;
	
	@Id
	@KeySql(useGeneratedKeys = true)
	@JsonView(View.Summary.class)
	@Column(name = "payment_id", nullable = false, length = 10)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer paymentId;

	@JsonView(View.Summary.class)
	@Column(name = "amount", nullable = true, length = 19)
	private Float amount;
	
	@JsonView(View.Summary.class)
	@Column(name = "invoice_no", nullable = true, length = 8)
	private Integer invoiceNo;
	
	@JsonView(View.Summary.class)
	@Column(name = "customer", nullable = true, length = 100)
	private String customer;
	
	@JsonView(View.Summary.class)
	@Column(name = "invoice_date", nullable = true, length = 19)
	private String invoiceDate;
	
	@JsonView(View.Summary.class)
	@Column(name = "due_date", nullable = true, length = 19)
	private String dueDate;
	
	@JsonView(View.Summary.class)
	@Column(name = "status", nullable = true, length = 7)
	private String status;
	
	@JsonView(View.Summary.class)
	@Column(name = "sales", nullable = true, length = 100)
	private String sales;
	
	@JsonView(View.Summary.class)
	@Column(name = "billing_company", nullable = true, length = 100)
	private String billingCompany;
	
	@JsonView(View.Summary.class)
	@Column(name = "billing_contact", nullable = true, length = 100)
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
	@Column(name = "billing_city", nullable = true, length = 100)
	private String billingCity;
	
	@JsonView(View.Summary.class)
	@Column(name = "billing_state", nullable = true, length = 100)
	private String billingState;
	
	@JsonView(View.Summary.class)
	@Column(name = "billing_country", nullable = true, length = 100)
	private String billingCountry;
	
	@JsonView(View.Summary.class)
	@Column(name = "billing_zip", nullable = true, length = 100)
	private Integer billingZip;
	
	@JsonView(View.Summary.class)
	@Column(name = "shipping_company", nullable = true, length = 100) 
	private String shippingCompany;
	
	@JsonView(View.Summary.class)
	@Column(name = "shipping_contact", nullable = true, length = 100) 
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
	@Column(name = "shipping_city", nullable = true, length = 100)
	private String shippingCity;
	
	@JsonView(View.Summary.class)
	@Column(name = "shipping_state", nullable = true, length = 100)
	private String shippingState;
	
	@JsonView(View.Summary.class)
	@Column(name = "shipping_country", nullable = true, length = 100)
	private String shippingCountry;
	
	@JsonView(View.Summary.class)
	@Column(name = "shipping_zip", nullable = true, length = 100)
	private Integer shippingZip;
	
	@JsonView(View.Summary.class)
	@Column(name = "note", nullable = true, length = 300)
	private String note;
	
	@JsonView(View.Summary.class)
	@Column(name = "shipping_via", nullable = true, length = 19)
	private String shippingVia;
	
	@JsonView(View.Summary.class)
	@Column(name = "payment_term", nullable = true, length = 19)
	private String paymentTerm;
	
	@JsonView(View.Summary.class)
	@Column(name = "invoice_type", nullable = true, length = 19)
	private String invoiceType;
	
	@JsonView(View.Summary.class)
	@Column(name = "shipping_fee", nullable = true, length = 19)
	private Float shippingFee;
	
	@JsonView(View.Summary.class)
	@Column(name = "tracking_no", nullable = true, length = 19)
	private String trackingNo;
	
	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Integer getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(Integer invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSales() {
		return sales;
	}

	public void setSales(String sales) {
		this.sales = sales;
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

	public String getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public Float getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(Float shippingFee) {
		this.shippingFee = shippingFee;
	}

	public String getTrackingNo() {
		return trackingNo;
	}

	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}

	public String getBillingCity() {
		return billingCity;
	}

	public void setBillingCity(String billingCity) {
		this.billingCity = billingCity;
	}

	public String getBillingState() {
		return billingState;
	}

	public void setBillingState(String billingState) {
		this.billingState = billingState;
	}

	public String getBillingCountry() {
		return billingCountry;
	}

	public void setBillingCountry(String billingCountry) {
		this.billingCountry = billingCountry;
	}

	public Integer getBillingZip() {
		return billingZip;
	}

	public void setBillingZip(Integer billingZip) {
		this.billingZip = billingZip;
	}

	public String getShippingCity() {
		return shippingCity;
	}

	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}

	public String getShippingState() {
		return shippingState;
	}

	public void setShippingState(String shippingState) {
		this.shippingState = shippingState;
	}

	public String getShippingCountry() {
		return shippingCountry;
	}

	public void setShippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
	}

	public Integer getShippingZip() {
		return shippingZip;
	}

	public void setShippingZip(Integer shippingZip) {
		this.shippingZip = shippingZip;
	}
	
	
}
