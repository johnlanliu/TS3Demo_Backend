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
	
	/** invoice_no */
	@Id
	@KeySql(useGeneratedKeys = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(View.Summary.class)
	@Column(name = "invoice_no", unique = true, nullable = false, length = 8)
	private Integer invoiceNo;
	
	/** customer */
	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "customer", nullable = false, length = 30)
	private String customer;
	
	/** invoice_date */
	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "invoice_date", nullable = false, length = 18)
	private String invoiceDate;
	
	/** due_date */
	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "due_date", nullable = false, length = 18)
	private String dueDate;
	
	/** amount */
	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "amount", nullable = false, length = 20)
	private String amount;
	
	/** status */
	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "status", nullable = false, length = 7)
	private String status;
	
	/** sales */
	@JsonView(View.SummaryWithDetail.class)
	@Column(name = "sales", nullable = false, length = 100)
	private String sales;
	
	public Integer getInvoiceNo() {
		return this.invoiceNo;
	}
	
	public void setInvoiceNo(Integer invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	
	public String getInvoiceDate() {
		return this.invoiceDate;
	}
	
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	
	public String getDueDate() {
		return this.dueDate;
	}
	
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	
	public String getAmount() {
		return this.amount;
	}
	
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getSales() {
		return this.sales;
	}
	
	public void setSales(String sales) {
		this.sales = sales;
	}
}
