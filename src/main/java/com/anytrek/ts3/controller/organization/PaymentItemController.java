package com.anytrek.ts3.controller.organization;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
import com.anytrek.ts3.mapper.PaymentItemMapper;
import com.anytrek.ts3.model.User;
import com.anytrek.util.PasswordUtil;
import com.fasterxml.jackson.annotation.JsonView;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.anytrek.ts3.ControllerBase;
import com.anytrek.ts3.model.PaymentItem;
import com.anytrek.ts3.model.PaymentItem;

@RestController
@RequestMapping("/orgMPaymentItem")
public class PaymentItemController extends ControllerBase {
	@SuppressWarnings("unused")
	
	private static Logger logger = LogManager.getLogger(PaymentItemController.class);
	
	@Autowired
	private PaymentItemMapper paymentItemMapper;
	
	@JsonView(View.Summary.class)
	@RequestMapping(value = { "/getPaymentItemList" }, method = RequestMethod.GET)
	public List<PaymentItem> getPaymentItemList(@RequestParam(value = "paymentId", required = true) Integer paymentId) throws Exception {
		List<PaymentItem> contents = new ArrayList<>();
		
		if (paymentId != null) {
			contents = paymentItemMapper.getPaymentItemListByPaymentId(paymentId);
		}
		
		return contents;
	}
	
	@RequestMapping(value = {"/deletePaymentItem"}, method = RequestMethod.POST)
	public void deletePaymentItem(@RequestParam(value = "itemId", required = true) Integer itemId) throws Exception {
		paymentItemMapper.deleteByPrimaryKey(itemId);
		logger.info("Delete Success!");
	}
}
