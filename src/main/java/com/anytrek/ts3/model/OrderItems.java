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

@Entity
@Table(name = "t_order_items")
public class OrderItems implements java.io.Serializable {

	private static final long serialVersionUID = -4840306256978869946L;

	@Id
	@KeySql(useGeneratedKeys = true)
	@JsonView(View.Summary.class)
	@Column(name = "item_id", nullable = false, length = 10)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer itemId;

	@JsonView(View.Summary.class)
	@Column(name = "order_id", nullable = false, length = 10)
	private Integer orderId;

	@JsonView(View.Summary.class)
	@Column(name = "model_id", nullable = true, length = 10)
	private Integer modelId;

	@JsonView(View.Summary.class)
	@Column(name = "quantity", nullable = true, length = 10)
	private Integer quantity;

	@JsonView(View.Summary.class)
	@Column(name = "rate", nullable = true, length = 10)
	private Float rate;

	@JsonView(View.Summary.class)
	@Column(name = "amount", nullable = true, length = 10)
	private Float amount;

	@JsonView(View.Summary.class)
	@Column(name = "tax", nullable = true, length = 10)
	private String tax;

	@JsonView(View.Summary.class)
	@Column(name = "description", nullable = true, length = 255)
	private String description;

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getModelId() {
		return modelId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
