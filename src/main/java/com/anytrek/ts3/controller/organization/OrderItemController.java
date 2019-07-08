package com.anytrek.ts3.controller.organization;

import java.sql.Timestamp;
import java.util.Date;
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
import com.anytrek.ts3.mapper.OrderItemMapper;
import com.anytrek.util.PasswordUtil;
import com.fasterxml.jackson.annotation.JsonView;

import com.anytrek.ts3.ControllerBase;
import com.anytrek.ts3.model.OrderItem;

@RestController
@RequestMapping("/orgMOrderItem")
public class OrderItemController extends ControllerBase {
	
	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(OrderItemController.class);
	
	@Autowired
	private OrderItemMapper orderItemMapper;
	
	@JsonView(View.Summary.class)
	@RequestMapping(value = { "/addOrderItem" }, method = RequestMethod.POST)
	public String addOrderItem(@RequestBody(required = false) OrderItem requestOrderItem) throws Exception {
		orderItemMapper.insert(requestOrderItem);
		logger.info("Insert Success!");
		return "OK";
	}
	
	@JsonView(View.Summary.class)
	@RequestMapping(value = { "/getOrderItemList" }, method = RequestMethod.GET)
	public List<OrderItem> getOrderItemList(@RequestParam(value = "orderId", required = false) Integer orderId) throws Exception {
		
		List<OrderItem> contents = null;
		
		if (orderId != null) {
			contents = orderItemMapper.getOrderItemListByOrderId(orderId);
		}

		return contents;
		
	}
}
