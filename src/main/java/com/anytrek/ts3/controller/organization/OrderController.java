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
import com.anytrek.ts3.mapper.OrderMapper;
import com.anytrek.ts3.model.User;
import com.anytrek.util.PasswordUtil;
import com.fasterxml.jackson.annotation.JsonView;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.anytrek.ts3.ControllerBase;
import com.anytrek.ts3.model.Order;
import com.anytrek.ts3.model.OrderItem;

@RestController
@RequestMapping("/orgMOrder")
public class OrderController extends ControllerBase {
	
	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(OrderController.class);

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderItemMapper orderItemMapper;
	
	@JsonView(View.Summary.class)
	@RequestMapping(value = { "/addOrder" }, method = RequestMethod.POST)
	public String addOrder(@RequestBody(required = false) JSONObject requestOrder) throws Exception {
		Order order = new Order();
		order.setType(requestOrder.getString("type"));
		order.setCustomer(requestOrder.getString("customer"));
		order.setStatus(requestOrder.getString("status"));
		order.setInvoiceNo(requestOrder.getInteger("invoiceNo"));
		String inv = requestOrder.getString("invoiceDate").replaceAll("[a-zA-Z]", " ");
		String due = requestOrder.getString("dueDate").replaceAll("[a-zA-Z]", " ");
		order.setInvoiceDate(inv.substring(0, inv.length() - 5));
		order.setDueDate(due);
		order.setTrackingNo(requestOrder.getString("trackingNo"));
		order.setCreateTime(new Timestamp(new Date().getTime()));
		order.setBillingCompany(requestOrder.getString("billingCompany"));
		order.setBillingContact(requestOrder.getString("billingContact"));
		order.setBillingNumber(requestOrder.getString("billingNumber"));
		order.setBillingEmail(requestOrder.getString("billingEmail"));
		order.setBillingAddress(requestOrder.getString("billingAddress"));
		order.setShippingCompany(requestOrder.getString("shippingCompany"));
		order.setShippingContact(requestOrder.getString("shippingContact"));
		order.setShippingNumber(requestOrder.getString("shippingNumber"));
		order.setShippingEmail(requestOrder.getString("shippingEmail"));
		order.setShippingAddress(requestOrder.getString("shippingAddress"));
		User loginUser = getUserByHeader();
		String username = loginUser.getUsername();
		order.setSales(username);
		orderMapper.insertOrder(order);
		
		List<OrderItem> items = new ArrayList<OrderItem>();
		JSONArray itemArr = requestOrder.getJSONArray("orderItems");
		for(int i=0; i<itemArr.size(); i++) {
			OrderItem item = new OrderItem();
			item.setOrderId(order.getOrderId());
			item.setProduct(itemArr.getJSONObject(i).getString("product"));
			item.setQuantity(itemArr.getJSONObject(i).getInteger("quantity"));
			item.setRate(itemArr.getJSONObject(i).getFloat("rate"));
			item.setAmount(itemArr.getJSONObject(i).getFloat("amount"));
			item.setTax(itemArr.getJSONObject(i).getString("tax"));
			item.setDescription(itemArr.getJSONObject(i).getString("description"));
			items.add(item);
		}
		orderItemMapper.insertList(items);
		
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
	@JsonView(View.Summary.class)
	@RequestMapping(value= {"/getOrderByOrderId"}, method = RequestMethod.GET)
	public Order getOrderByOrderId(@RequestParam(value = "orderId", required = true) Integer orderId)
			throws Exception {
		Order result = new Order();
		result = orderMapper.getOrderByOrderId(orderId);
		return result;
	}
	
	@JsonView(View.Summary.class)
	@RequestMapping(value= {"/cancelOrder"}, method = RequestMethod.POST)
	public void cancelOrder(@RequestParam(value = "orderId", required = true) Integer orderId) throws Exception {
		
		Order toCancel = orderMapper.getOrderByOrderId(orderId);
		toCancel.setStatus("cancelled");
		
		orderMapper.updateByPrimaryKey(toCancel);
		
		logger.info("Cancel Success!");
	}
	
	@JsonView(View.Summary.class)
	@RequestMapping(value = {"getLastOrderId"}, method = RequestMethod.GET)
	public Integer getLastOrderId() throws Exception {
		Order last = orderMapper.getLastOrder();
		Integer lastId = last.getOrderId();
		return lastId;
	}
}
