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
	@Column(name = "customer", nullable = true, length = 36)
	private String customer;

	@JsonView(View.Summary.class)
	@Column(name = "status", nullable = true, length = 7)
	private String status;

	@JsonView(View.Summary.class)
	@Column(name = "invoice_no", nullable = true, length = 20)
	private String invoiceNo;

	@JsonView(View.Summary.class)
	@Column(name = "invoice_date", nullable = true)
	private Timestamp invoiceDate;

	@JsonView(View.Summary.class)
	@Column(name = "due_date", nullable = true)
	private Timestamp dueDate;

	@JsonView(View.Summary.class)
	@Column(name = "device_id", nullable = true, length = 20)
	private Long device_id;

	@JsonView(View.Summary.class)
	@Column(name = "sales", nullable = true, length = 36)
	private String sales;

	@JsonView(View.Summary.class)
	@Column(name = "billing_company", nullable = true, length = 64)
	private String billingCompany;

	@JsonView(View.Summary.class)
	@Column(name = "billing_contact", nullable = true, length = 36)
	private String billingContact;

	@JsonView(View.Summary.class)
	@Column(name = "billing_number", nullable = true, length = 36)
	private String billingNumber;

	@JsonView(View.Summary.class)
	@Column(name = "billing_email", nullable = true, length = 36)
	private String billingEmail;

	@JsonView(View.Summary.class)
	@Column(name = "billing_address", nullable = true, length = 128)
	private String billingAddress;

	@JsonView(View.Summary.class)
	@Column(name = "billing_city", nullable = true, length = 18)
	private String billingCity;

	@JsonView(View.Summary.class)
	@Column(name = "billing_state", nullable = true, length = 18)
	private String billingState;

	@JsonView(View.Summary.class)
	@Column(name = "billing_country", nullable = true, length = 18)
	private String billingCountry;

	@JsonView(View.Summary.class)
	@Column(name = "billing_zip", nullable = true, length = 8)
	private String billingZip;

	@JsonView(View.Summary.class)
	@Column(name = "shipping_company", nullable = true, length = 64)
	private String shippingCompany;

	@JsonView(View.Summary.class)
	@Column(name = "shipping_contact", nullable = true, length = 36)
	private String shippingContact;

	@JsonView(View.Summary.class)
	@Column(name = "shipping_number", nullable = true, length = 36)
	private String shippingNumber;

	@JsonView(View.Summary.class)
	@Column(name = "shipping_email", nullable = true, length = 36)
	private String shippingEmail;

	@JsonView(View.Summary.class)
	@Column(name = "shipping_address", nullable = true, length = 128)
	private String shippingAddress;

	@JsonView(View.Summary.class)
	@Column(name = "shipping_city", nullable = true, length = 18)
	private String shippingCity;

	@JsonView(View.Summary.class)
	@Column(name = "shipping_state", nullable = true, length = 18)
	private String shippingState;

	@JsonView(View.Summary.class)
	@Column(name = "shipping_country", nullable = true, length = 18)
	private String shippingCountry;

	@JsonView(View.Summary.class)
	@Column(name = "shipping_zip", nullable = true, length = 8)
	private String shippingZip;

	@JsonView(View.Summary.class)
	@Column(name = "note", nullable = true, length = 100)
	private String note;

	@JsonView(View.Summary.class)
	@Column(name = "shipping_via", nullable = true, length = 64)
	private String shippingVia;

	@JsonView(View.Summary.class)
	@Column(name = "shipping_fee", nullable = true)
	private Float shippingFee;

	@JsonView(View.Summary.class)
	@Column(name = "payment_term", nullable = true, length = 255)
	private String paymentTerm;

	@JsonView(View.Summary.class)
	@Column(name = "amount", nullable = true)
	private Float amount;

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

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Timestamp getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Timestamp invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Timestamp getDueDate() {
		return dueDate;
	}

	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}

	public Long getDevice_id() {
		return device_id;
	}

	public void setDevice_id(Long device_id) {
		this.device_id = device_id;
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

	public String getBillingZip() {
		return billingZip;
	}

	public void setBillingZip(String billingZip) {
		this.billingZip = billingZip;
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

	public String getShippingZip() {
		return shippingZip;
	}

	public void setShippingZip(String shippingZip) {
		this.shippingZip = shippingZip;
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

	public String getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

}
