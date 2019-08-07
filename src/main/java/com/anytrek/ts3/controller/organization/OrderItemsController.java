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
import com.anytrek.ts3.mapper.OrderItemsMapper;
import com.fasterxml.jackson.annotation.JsonView;
import com.alibaba.fastjson.JSONArray;
import com.anytrek.ts3.ControllerBase;
import com.anytrek.ts3.model.OrderItems;

@RestController
@RequestMapping("/orgMOrderItem")
public class OrderItemsController extends ControllerBase {

	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(OrderItemsController.class);

	@Autowired
	private OrderItemsMapper orderItemMapper;

	@RequiresPermissions("org:order:view")
	@JsonView(View.Summary.class)
	@RequestMapping(value = { "/getOrderItemList" }, method = RequestMethod.GET)
	public String getOrderItemList(@RequestParam(value = "orderId", required = true) Integer orderId) throws Exception {
		if (orderId == null || orderId == 0) {
			throw new WebException(ErrorCode.ORDER_PARAMETER_ERROR, "ORDER_PARAMETER_ERROR!");
		}
		List<OrderItems> viewList = orderItemMapper.getOrderItemsListByOrderId(orderId);
		return JSONArray.toJSON(viewList).toString();
	}

	@RequiresPermissions("org:order:delete")
	@RequestMapping(value = { "/deleteOrderItem" }, method = RequestMethod.POST)
	public void deleteOrderItem(@RequestParam(value = "itemId", required = true) Integer itemId) throws Exception {
		if (itemId == null || itemId == 0) {
			throw new WebException(ErrorCode.ORDER_PARAMETER_ERROR, "ORDER_PARAMETER_ERROR!");
		}
		int row = orderItemMapper.deleteByPrimaryKey(itemId);
		if (row == 0)
			throw new WebException(ErrorCode.ORDER_NOT_FOUND, "ORDER_NOT_FOUND!");
		sendOk();
	}
}
