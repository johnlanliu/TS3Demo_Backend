package com.anytrek.ts3.controller.organization;

import java.sql.Timestamp;
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

import com.anytrek.ts3.ControllerBase;
import com.anytrek.ts3.dto.PaymentDetailDto;
import com.anytrek.ts3.model.Payment;

@RestController
@RequestMapping("/orgMPayment")
public class PaymentController extends ControllerBase {

	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(PaymentController.class);

	@Autowired
	private PaymentMapper paymentMapper;
	
	@JsonView(View.Summary.class)
	@RequestMapping(value = { "/addPayment" }, method = RequestMethod.POST)
	public Payment addPayment(@RequestBody(required = false) Payment requestPayment) throws Exception {
		User loginUser = getUserByHeader();
		String username = loginUser.getUsername();
		String invDate = requestPayment.getInvoiceDate();
		String dDate = requestPayment.getDueDate();
		String newInvoiceDate = invDate.replaceAll("[a-zA-Z]", " ");
		String newDueDate = dDate.replaceAll("[a-zA-Z]", " ");
		requestPayment.setInvoiceDate(newInvoiceDate);
		requestPayment.setDueDate(newDueDate);
		requestPayment.setSales(username);
		paymentMapper.insert(requestPayment);
		logger.info("Insert Success!");
		return requestPayment;
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
	public Payment editPayment(@RequestBody(required = true) Payment editPayment) throws Exception {
		Payment curPayment = paymentMapper.selectByPrimaryKey(editPayment.getPaymentId());
		if (curPayment == null) {
			throw new WebException(ErrorCode.USER_NOT_FOUND);
		}	
		curPayment.setAmount(editPayment.getAmount());
		curPayment.setInvoiceNo(editPayment.getInvoiceNo());
		curPayment.setCustomer(editPayment.getCustomer());
		curPayment.setInvoiceDate(editPayment.getInvoiceDate());
		curPayment.setDueDate(editPayment.getDueDate());
		curPayment.setStatus(editPayment.getStatus());
		curPayment.setSales(editPayment.getSales());
		curPayment.setBillingCompany(editPayment.getBillingCompany());
		curPayment.setBillingContact(editPayment.getBillingContact());
		curPayment.setBillingNumber(editPayment.getBillingNumber());
		curPayment.setBillingEmail(editPayment.getBillingEmail());
		curPayment.setBillingAddress(editPayment.getBillingAddress());
		curPayment.setBillingCity(editPayment.getBillingCity());
		curPayment.setBillingState(editPayment.getBillingState());
		curPayment.setBillingCountry(editPayment.getBillingCountry());
		curPayment.setBillingZip(editPayment.getBillingZip());
		curPayment.setShippingCompany(editPayment.getShippingCompany());
		curPayment.setShippingContact(editPayment.getShippingContact());
		curPayment.setShippingNumber(editPayment.getShippingNumber());
		curPayment.setShippingEmail(editPayment.getShippingEmail());
		curPayment.setShippingAddress(editPayment.getShippingAddress());
		curPayment.setShippingCity(editPayment.getShippingCity());
		curPayment.setShippingState(editPayment.getShippingState());
		curPayment.setShippingCountry(editPayment.getShippingCountry());
		curPayment.setShippingZip(editPayment.getShippingZip());
		curPayment.setNote(editPayment.getNote());
		curPayment.setShippingVia(editPayment.getShippingVia());
		curPayment.setPaymentTerm(editPayment.getPaymentTerm());
		curPayment.setInvoiceType(editPayment.getInvoiceType());
		curPayment.setShippingFee(editPayment.getShippingFee());
		curPayment.setTrackingNo(editPayment.getTrackingNo());
		paymentMapper.updateByPrimaryKeySelective(curPayment);
		return curPayment;
		
	}
	
}
