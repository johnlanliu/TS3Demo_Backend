package com.anytrek.ts3.controller.organization;

import java.sql.Timestamp;
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
import com.anytrek.ts3.mapper.OrderMapper;
import com.anytrek.ts3.model.User;
import com.anytrek.util.PasswordUtil;
import com.fasterxml.jackson.annotation.JsonView;

import com.anytrek.ts3.ControllerBase;
import com.anytrek.ts3.model.Order;

@RestController
@RequestMapping("/orgMOrder")
public class OrderController extends ControllerBase {
	
	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(OrderController.class);

	@Autowired
	private OrderMapper orderMapper;
	
	@JsonView(View.Summary.class)
	@RequestMapping(value = { "/addOrder" }, method = RequestMethod.POST)
	public String addOrder(@RequestBody(required = false) Order requestOrder) throws Exception {
		User loginUser = getUserByHeader();
		String username = loginUser.getUsername();
		requestOrder.setSales(username);
		orderMapper.insert(requestOrder);
		logger.info("Insert Success!");
		return "OK";
	}
	
	@JsonView(View.Summary.class)
	@RequestMapping(value = { "/getOrderList" }, method = RequestMethod.GET)
	public List<Order> getOrderList(@RequestParam(value = "invoiceNo", required = false) Integer invoiceNo,
			@RequestParam(value = "status", required = false) String status) throws Exception {
		HashMap<String, Object> params = new HashMap<>();
		List<Order> contents = orderMapper.selectAll();
		int state = 0;
		if (invoiceNo != null) {
			params.put("invoiceNo", invoiceNo);
			state = 1;
		}
		if (status != null & !"".equals(status)) {
			params.put("status", status);
			state = 1;
		}
		if (state == 1) {
			contents = orderMapper.getOrderListByParams(params);
		}
//		HashMap<String, Object> stuff = new HashMap<>();
//		stuff.put("list", contents);
		return contents;
		
	}
}
