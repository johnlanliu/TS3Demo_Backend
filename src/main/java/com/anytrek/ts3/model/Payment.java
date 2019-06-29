package com.anytrek.ts3.model;

import javax.persistence.Entity;
import javax.persistence.Table;
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
	
	
}
