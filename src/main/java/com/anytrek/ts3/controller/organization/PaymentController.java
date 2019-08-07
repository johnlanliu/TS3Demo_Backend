package com.anytrek.ts3.controller.organization;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import com.anytrek.ts3.dto.View;
import com.anytrek.ts3.exception.ErrorCode;
import com.anytrek.ts3.exception.WebException;
import com.anytrek.ts3.mapper.PaymentMapper;
import com.anytrek.ts3.model.User;
import com.fasterxml.jackson.annotation.JsonView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.anytrek.ts3.ControllerBase;
import com.anytrek.ts3.model.Payment;
import com.anytrek.ts3.model.PaymentItems;
import com.anytrek.ts3.mapper.PaymentItemsMapper;

@RestController
@RequestMapping("/orgMPayment")
public class PaymentController extends ControllerBase {

	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(PaymentController.class);

	@Autowired
	private PaymentMapper paymentMapper;

	@Autowired
	private PaymentItemsMapper paymentItemMapper;

	/**
	 * 获取payment列表
	 * 
	 * @param invoiceNo
	 * @param status
	 * @param customer
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("org:payment:view")
	@JsonView(View.Summary.class)
	@RequestMapping(value = { "/getPaymentList" }, method = RequestMethod.GET)
	public String getPaymentList(@RequestParam(value = "invoiceNo", required = false) Integer invoiceNo,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "customer", required = false) String customer) throws Exception {
		HashMap<String, Object> params = new HashMap<>();
		if (!StringUtils.isEmpty(status)) {
			params.put("status", status);
		}
		if (!StringUtils.isEmpty(customer)) {
			params.put("customer", customer);
		}
		if (invoiceNo != null) {
			params.put("invoiceNo", invoiceNo);
		}
		List<Payment> viewList = paymentMapper.getPaymentListByParams(params);
		// 获取分页类数据
		return JSONArray.toJSON(viewList).toString();

	}

	/**
	 * 根据invoiceNo获取payment
	 * @param invoiceNo
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("org:payment:view")
	@JsonView(View.Summary.class)
	@RequestMapping(value = { "/getPaymentByInvoiceNo" }, method = RequestMethod.GET)
	public Payment getPaymentByInvoiceNo(@RequestParam(value = "invoiceNo", required = true) Integer invoiceNo)
			throws Exception {
		if (invoiceNo == null || invoiceNo == 0) {
			throw new WebException(ErrorCode.PAYMENT_PARAMETER_ERROR, "PAYMENT_PARAMETER_ERROR!");
		}
		Payment p = paymentMapper.getPaymentByInvoiceNo(invoiceNo);
		if (p == null) {
			throw new WebException(ErrorCode.PAYMENT_NOT_FOUND, "PAYMENT_NOT_FOUND!");
		}
		return p;
	}

	/**
	 * 根据paymentId获取payment
	 * @param paymentId
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("org:payment:view")
	@JsonView(View.Summary.class)
	@RequestMapping(value = { "/getPaymentByPaymentId" }, method = RequestMethod.GET)
	public Payment getPaymentByPaymentId(@RequestParam(value = "paymentId", required = true) Integer paymentId)
			throws Exception {
		if (paymentId == null || paymentId == 0) {
			throw new WebException(ErrorCode.PAYMENT_PARAMETER_ERROR, "PAYMENT_PARAMETER_ERROR!");
		}
		Payment payment = paymentMapper.selectByPrimaryKey(paymentId);
		if (payment == null)
			throw new WebException(ErrorCode.PAYMENT_NOT_FOUND, "PAYMENT_NOT_FOUND!");
		return payment;
	}

	/**
	 * 添加payment
	 * 
	 * @param requestPayment
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("org:payment:add")
	@JsonView(View.Summary.class)
	@RequestMapping(value = { "/addPayment" }, method = RequestMethod.POST)
	public Payment addPayment(@RequestBody(required = true) String requestPayment) throws Exception {
		if (StringUtils.isEmpty(requestPayment)) {
			throw new WebException(ErrorCode.PAYMENT_PARAMETER_ERROR, "PAYMENT_PARAMETER_ERROR!");
		}
		JSONObject payObj = JSON.parseObject(requestPayment);
		if (payObj == null) {
			throw new WebException(ErrorCode.PAYMENT_PARAMETER_ERROR, "PAYMENT_PARAMETER_ERROR!");
		}
		Payment payment = JSON.parseObject(requestPayment, Payment.class);
		User loginUser = getUserByHeader();
		payment.setSales(loginUser.getUsername());
		paymentMapper.insertSelective(payment);
		JSONArray itemArr = payObj.getJSONArray("paymentItems");
		List<PaymentItems> items = new ArrayList<PaymentItems>();
		for (int i = 0; i < itemArr.size(); i++) {
			PaymentItems item = JSON.parseObject(itemArr.getString(i), PaymentItems.class);
			item.setPaymentId(payment.getPaymentId());
			if (item != null)
				items.add(item);
		}
		if (items.size() != 0) {
			paymentItemMapper.insertList(items);
		}
		return payment;
	}

	/**
	 * 编辑payment
	 * @param requestPayment
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("org:payment:edit")
	@JsonView(View.Summary.class)
	@RequestMapping(value = { "/editPayment" }, method = RequestMethod.POST)
	public Payment editPayment(@RequestBody(required = true) String requestPayment) throws Exception {
		JSONObject payObj = JSON.parseObject(requestPayment);
		Payment payment = JSON.parseObject(requestPayment, Payment.class);
		User loginUser = getUserByHeader();
		payment.setSales(loginUser.getUsername());
		paymentMapper.updateByPrimaryKeySelective(payment);
		JSONArray itemArr = payObj.getJSONArray("paymentItems");
		List<PaymentItems> items = new ArrayList<PaymentItems>();
		if (itemArr != null && itemArr.size() > 0) {
			for (int i = 0; i < itemArr.size(); i++) {
				PaymentItems item = JSON.parseObject(itemArr.getString(i), PaymentItems.class);
				item.setPaymentId(payment.getPaymentId());
				if (item != null)
					items.add(item);
			}
			if (items.size() != 0) {
				paymentItemMapper.insertList(items);
			}
		}
		return payment;
	}

	/**
	 * payment无效
	 * 
	 * @param paymentId
	 * @throws Exception
	 */
	@RequiresPermissions("org:payment:view")
	@JsonView(View.Summary.class)
	@RequestMapping(value = { "/voidPayment" }, method = RequestMethod.GET)
	public void voidPayment(@RequestParam(value = "paymentId", required = true) Integer paymentId) throws Exception {
		if (paymentId == null || paymentId == 0) {
			throw new WebException(ErrorCode.PAYMENT_PARAMETER_ERROR, "PAYMENT_PARAMETER_ERROR!");
		}
		Payment toVoid = paymentMapper.selectByPrimaryKey(paymentId);
		if (toVoid == null) {
			throw new WebException(ErrorCode.PAYMENT_NOT_FOUND, "PAYMENT_NOT_FOUND!");
		}
		toVoid.setStatus("void");
		toVoid.setShippingFee(0f);
		toVoid.setAmount(0f);
		int row = paymentMapper.updateByPrimaryKey(toVoid);
		if (row == 0) {
			throw new WebException(ErrorCode.PAYMENT_NOT_FOUND, "PAYMENT_NOT_FOUND!");
		}
		sendOk();
	}
}
