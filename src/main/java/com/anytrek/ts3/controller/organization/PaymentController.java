package com.anytrek.ts3.controller.organization;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

import com.anytrek.ts3.model.Payment;

@RestController
@RequestMapping("/orgMPayment")
public class PaymentController {

	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	private PaymentMapper paymentMapper;
	
	@JsonView(View.Summary.class)
	@RequestMapping(value = { "/addPayment" }, method = RequestMethod.POST)
	public String addPayment(@RequestBody(required = false) Payment requestPayment) throws Exception {
		paymentMapper.insert(requestPayment);
		logger.info("Insert Success!");
		return "OK";
	}
}