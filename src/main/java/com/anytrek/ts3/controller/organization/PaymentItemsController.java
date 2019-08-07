package com.anytrek.ts3.controller.organization;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anytrek.ts3.dto.View;
import com.anytrek.ts3.exception.ErrorCode;
import com.anytrek.ts3.exception.WebException;
import com.anytrek.ts3.mapper.PaymentItemsMapper;
import com.fasterxml.jackson.annotation.JsonView;
import com.alibaba.fastjson.JSONArray;
import com.anytrek.ts3.ControllerBase;
import com.anytrek.ts3.model.PaymentItems;

@RestController
@RequestMapping("/orgMPaymentItem")
public class PaymentItemsController extends ControllerBase {
	@SuppressWarnings("unused")

	private static Logger logger = LogManager.getLogger(PaymentItemsController.class);

	@Autowired
	private PaymentItemsMapper paymentItemMapper;

	/**
	 * 获取paymentItem列表
	 * @param paymentId
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("org:payment:view")
	@JsonView(View.Summary.class)
	@RequestMapping(value = { "/getPaymentItemList" }, method = RequestMethod.GET)
	public String getPaymentItemList(@RequestParam(value = "paymentId", required = true) Integer paymentId)
			throws Exception {
		if (paymentId == null || paymentId == 0) {
			throw new WebException(ErrorCode.PAYMENT_PARAMETER_ERROR, "PAYMENT_PARAMETER_ERROR!");
		}
		List<PaymentItems> viewList = paymentItemMapper.getPaymentItemListByPaymentId(paymentId);
		return JSONArray.toJSON(viewList).toString();
	}

	/**
	 * 
	 * @param itemId
	 * @throws Exception
	 */
	@RequiresPermissions("org:payment:delete")
	@RequestMapping(value = { "/deletePaymentItem" }, method = RequestMethod.POST)
	public void deletePaymentItem(@RequestParam(value = "itemId", required = true) Integer itemId) throws Exception {
		if (itemId == null || itemId == 0) {
			throw new WebException(ErrorCode.PAYMENT_PARAMETER_ERROR, "PAYMENT_PARAMETER_ERROR!");
		}
		int row = paymentItemMapper.deleteByPrimaryKey(itemId);
		if (row == 0) {
			throw new WebException(ErrorCode.PAYMENT_NOT_FOUND, "PAYMENT_NOT_FOUND!");
		}
		sendOk();
	}
}
