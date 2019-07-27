package com.anytrek.ts3.controller.organization;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anytrek.ts3.common.FlagConstants;
import com.anytrek.ts3.dto.View;
import com.anytrek.ts3.exception.ErrorCode;
import com.anytrek.ts3.exception.WebException;
import com.anytrek.ts3.mapper.PaymentMapper;
import com.anytrek.ts3.model.User;
import com.anytrek.util.PasswordUtil;
import com.fasterxml.jackson.annotation.JsonView;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.anytrek.ts3.ControllerBase;
import com.anytrek.ts3.dto.PaymentDetailDto;
import com.anytrek.ts3.model.OrderItem;
import com.anytrek.ts3.model.Payment;
import com.anytrek.ts3.model.PaymentItem;
import com.anytrek.ts3.mapper.PaymentItemMapper;

@RestController
@RequestMapping("/orgMPayment")
public class PaymentController extends ControllerBase {

	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(PaymentController.class);

	@Autowired
	private PaymentMapper paymentMapper;
	
	private PaymentItemMapper paymentItemMapper;
	
	@JsonView(View.Summary.class)
	@RequestMapping(value = { "/addPayment" }, method = RequestMethod.POST)
	public Payment addPayment(@RequestBody(required = false) JSONObject requestPayment) throws Exception {
		Payment payment = new Payment();
		User loginUser = getUserByHeader();
		String username = loginUser.getUsername();
		payment.setAmount(requestPayment.getFloat("amount"));
		payment.setInvoiceNo(requestPayment.getInteger("invoiceNo"));
		payment.setCustomer(requestPayment.getString("customer"));
		String inv = "";
		String due = "";
		if (requestPayment.getString("invoiceDate") != null) {
			inv = requestPayment.getString("invoiceDate").replaceAll("[a-zA-Z]", " ");
		}
		if (requestPayment.getString("dueDate") != null) {
			due = requestPayment.getString("dueDate").replaceAll("[a-zA-Z]", " ");
		}
		payment.setInvoiceDate(inv);
		payment.setDueDate(due);
		payment.setStatus(requestPayment.getString("status"));
		payment.setSales(username);
		payment.setBillingCompany(requestPayment.getString("billingCompany"));
		payment.setBillingContact(requestPayment.getString("billingContact"));
		payment.setBillingNumber(requestPayment.getString("billingNumber"));
		payment.setBillingEmail(requestPayment.getString("billingEmail"));
		payment.setBillingAddress(requestPayment.getString("billingAddress"));
		payment.setBillingCity(requestPayment.getString("billingCity"));
		payment.setBillingCountry(requestPayment.getString("billingCountry"));
		payment.setBillingState(requestPayment.getString("billingState"));
		payment.setBillingZip(requestPayment.getInteger("billingZip"));
		payment.setShippingCompany(requestPayment.getString("shippingCompany"));
		payment.setShippingContact(requestPayment.getString("shippingContact"));
		payment.setShippingNumber(requestPayment.getString("shippingNumber"));
		payment.setShippingEmail(requestPayment.getString("shippingEmail"));
		payment.setShippingAddress(requestPayment.getString("shippingAddress"));
		payment.setShippingCity(requestPayment.getString("shippingCity"));
		payment.setShippingCountry(requestPayment.getString("shippingCountry"));
		payment.setShippingState(requestPayment.getString("shippingState"));
		payment.setShippingZip(requestPayment.getInteger("shippingZip"));
		payment.setNote(requestPayment.getString("note"));
		payment.setShippingVia(requestPayment.getString("shippingVia"));
		payment.setPaymentTerm(requestPayment.getString("paymentTerm"));
		payment.setInvoiceType(requestPayment.getString("invoiceType"));
		payment.setShippingFee(requestPayment.getFloat("shippingFee"));
		payment.setTrackingNo(requestPayment.getString("trackingNo"));
		JSONArray itemArr = requestPayment.getJSONArray("paymentItems");
		
		paymentMapper.insertPayment(payment);
		List<PaymentItem> items = new ArrayList<PaymentItem>();
		for (int i = 0; i < itemArr.size(); i += 1) {
			if (itemArr.getJSONObject(i) == null) {
				logger.info("dafjasiofja");
			}
			PaymentItem item = new PaymentItem();
			item.setPaymentId(payment.getPaymentId());
			item.setProduct(itemArr.getJSONObject(i).getString("product"));
			item.setQuantity(itemArr.getJSONObject(i).getInteger("quantity"));
			item.setRate(itemArr.getJSONObject(i).getFloat("rate"));
			item.setAmount(itemArr.getJSONObject(i).getFloat("amount"));
			item.setTax(itemArr.getJSONObject(i).getString("tax"));
			item.setDescription(itemArr.getJSONObject(i).getString("description"));
			items.add(item);
		
		}
		if (items.size() != 0) {
			paymentItemMapper.insertList(items);
		}

		logger.info("Insert Success!");
		return payment;
	}
	
	@JsonView(View.Summary.class)
	@RequestMapping(value = { "/getPaymentList" }, method = RequestMethod.GET)
	public List<Payment> getPaymentList(@RequestParam(value = "invoiceNo", required = false) Integer invoiceNo,
			@RequestParam(value = "status", required = false) String status, 
			@RequestParam(value = "customer", required = false) String customer) throws Exception {
		HashMap<String, Object> params = new HashMap<>();
		List<Payment> contents = paymentMapper.selectAll();
		int state = 0;
		if (invoiceNo != null) {
			params.put("invoiceNo", invoiceNo);
			state = 1;
		}
		if (status != null & !"".equals(status)) {
			params.put("status", status);
			state = 1;
		}
		if (customer != null & !"".equals(customer)) {
			params.put("customer", customer);
			state = 1;
		}
		if (state == 1) {
			contents = paymentMapper.getPaymentListByParams(params);
		}
//		HashMap<String, Object> stuff = new HashMap<>();
//		stuff.put("list", contents);
		return contents;
		
	}
	
	@JsonView(View.Summary.class)
	@RequestMapping(value = {"/voidPayment"}, method = RequestMethod.POST)
	public void voidPayment(@RequestParam(value = "paymentId", required = true) Integer paymentId) throws Exception {
		Payment toVoid = paymentMapper.getPaymentByPaymentId(paymentId);
		toVoid.setStatus("void");
		Float zero = 0f;
		toVoid.setShippingFee(zero);
		toVoid.setAmount(zero);
		paymentMapper.updateByPrimaryKey(toVoid);
	}
	
	@JsonView(View.Summary.class) 
	@RequestMapping(value = {"/getPaymentbyInvoiceNo"}, method = RequestMethod.GET)
	public Payment getPaymentByInvoiceNo(@RequestParam(value = "invoiceNo", required = true) Integer invoiceNo)
			throws Exception {
		Payment p = paymentMapper.getPaymentByInvoiceNo(invoiceNo);
		return p;
	}
	
	@JsonView(View.Summary.class)
	@RequestMapping(value = {"/getPaymentByPaymentId"}, method = RequestMethod.GET)
	public Payment getPaymentByInvoiceId(@RequestParam(value = "paymentId", required = true) Integer PaymentId)
			throws Exception {
		Payment p = paymentMapper.getPaymentByPaymentId(PaymentId);
		return p;
	}
	
	@JsonView(View.Summary.class)
	@RequestMapping(value = {"/editPayment"}, method = RequestMethod.POST)
	public Payment editPayment(@RequestBody(required = true) JSONObject editPayment) throws Exception {
		Payment curPayment = paymentMapper.selectByPrimaryKey(editPayment.getInteger("paymentId"));
		User loginUser = getUserByHeader();
		String username = loginUser.getUsername();
		curPayment.setAmount(editPayment.getFloat("amount"));
		curPayment.setInvoiceNo(editPayment.getInteger("invoiceNo"));
		curPayment.setCustomer(editPayment.getString("customer"));
		String inv = "";
		String due = "";
		if (editPayment.getString("invoiceDate") != null) {
			inv = editPayment.getString("invoiceDate").replaceAll("[a-zA-Z]", " ");
		}
		if (editPayment.getString("dueDate") != null) {
			due = editPayment.getString("dueDate").replaceAll("[a-zA-Z]", " ");
		}
		curPayment.setInvoiceDate(inv);
		curPayment.setDueDate(due);
		curPayment.setStatus(editPayment.getString("status"));
		curPayment.setSales(username);
		curPayment.setBillingCompany(editPayment.getString("billingCompany"));
		curPayment.setBillingContact(editPayment.getString("billingContact"));
		curPayment.setBillingNumber(editPayment.getString("billingNumber"));
		curPayment.setBillingEmail(editPayment.getString("billingEmail"));
		curPayment.setBillingAddress(editPayment.getString("billingAddress"));
		curPayment.setBillingCity(editPayment.getString("billingCity"));
		curPayment.setBillingCountry(editPayment.getString("billingCountry"));
		curPayment.setBillingState(editPayment.getString("billingState"));
		curPayment.setBillingZip(editPayment.getInteger("billingZip"));
		curPayment.setShippingCompany(editPayment.getString("shippingCompany"));
		curPayment.setShippingContact(editPayment.getString("shippingContact"));
		curPayment.setShippingNumber(editPayment.getString("shippingNumber"));
		curPayment.setShippingEmail(editPayment.getString("shippingEmail"));
		curPayment.setShippingAddress(editPayment.getString("shippingAddress"));
		curPayment.setShippingCity(editPayment.getString("shippingCity"));
		curPayment.setShippingCountry(editPayment.getString("shippingCountry"));
		curPayment.setShippingState(editPayment.getString("shippingState"));
		curPayment.setShippingZip(editPayment.getInteger("shippingZip"));
		curPayment.setNote(editPayment.getString("note"));
		curPayment.setShippingVia(editPayment.getString("shippingVia"));
		curPayment.setPaymentTerm(editPayment.getString("paymentTerm"));
		curPayment.setInvoiceType(editPayment.getString("invoiceType"));
		curPayment.setShippingFee(editPayment.getFloat("shippingFee"));
		curPayment.setTrackingNo(editPayment.getString("trackingNo"));
		paymentMapper.updateByPrimaryKey(curPayment);
		JSONArray itemArr = editPayment.getJSONArray("paymentItems");
		List<PaymentItem> items = new ArrayList<>();
		if (itemArr != null && itemArr.size() > 0) {
			for (int i = 0; i < itemArr.size(); i += 1) {
				PaymentItem item = new PaymentItem();
				item.setPaymentId(curPayment.getPaymentId());
				item.setProduct(itemArr.getJSONObject(i).getString("product"));
				item.setQuantity(itemArr.getJSONObject(i).getInteger("quantity"));
				item.setRate(itemArr.getJSONObject(i).getFloat("rate"));
				item.setAmount(itemArr.getJSONObject(i).getFloat("amount"));
				item.setTax(itemArr.getJSONObject(i).getString("tax"));
				item.setDescription(itemArr.getJSONObject(i).getString("description"));
				items.add(item);
			}
		}
	
		if (items.size() != 0) {
			paymentItemMapper.insertList(items);
		}
		return curPayment;
	}
	
}
