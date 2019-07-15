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
import com.anytrek.ts3.mapper.OrderItemMapper;
import com.anytrek.ts3.model.User;
import com.anytrek.util.PasswordUtil;
import com.fasterxml.jackson.annotation.JsonView;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
	@RequestMapping(value = { "/getOrderItemList" }, method = RequestMethod.GET)
	public List<OrderItem> getOrderItemList(@RequestParam(value = "orderId", required = true) Integer orderId) throws Exception {
		List<OrderItem> contents = new ArrayList<>();
		
		if (orderId != null) {
			contents = orderItemMapper.getOrderItemListByOrderId(orderId);
		}
		
		return contents;
		
	}
	
	@JsonView(View.Summary.class)
	@RequestMapping(value = {"/getOrderItemListByInvoiceNo"}, method = RequestMethod.GET)
	public List<OrderItem> getOrderItemListByInvoiceNo(@RequestParam(value = "invoiceNo", required = true) Integer invoiceNo)
		throws Exception {
		List<OrderItem> contents = new ArrayList<>();
		
		if(invoiceNo != null) {
			contents = orderItemMapper.getOrderItemListByInvoiceNo(invoiceNo);
		}
		return contents;
	}
	

}
