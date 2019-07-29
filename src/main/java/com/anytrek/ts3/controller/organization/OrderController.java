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
import com.anytrek.ts3.model.Payment;

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
		String inv = "";
		String due = "";
		if (requestOrder.getString("invoiceDate") != null) {
			inv = requestOrder.getString("invoiceDate").replaceAll("[a-zA-Z]", " ");
		}
		if (requestOrder.getString("dueDate") != null) {
			due = requestOrder.getString("dueDate").replaceAll("[a-zA-Z]", " ");
		}
		order.setInvoiceDate(inv);
		order.setDueDate(due);
		order.setTrackingNo(requestOrder.getString("trackingNo"));
		order.setCreateTime(new Timestamp(new Date().getTime()));
		order.setBillingCompany(requestOrder.getString("billingCompany"));
		order.setBillingContact(requestOrder.getString("billingContact"));
		order.setBillingNumber(requestOrder.getString("billingNumber"));
		order.setBillingEmail(requestOrder.getString("billingEmail"));
		order.setBillingAddress(requestOrder.getString("billingAddress"));
		order.setBillingCity(requestOrder.getString("billingCity"));
		order.setBillingState(requestOrder.getString("billingState"));
		order.setBillingCountry(requestOrder.getString("billingCountry"));
		order.setBillingZip(requestOrder.getInteger("billingZip"));
		if (requestOrder.getInteger("sameAsBilling") != 0) {
			order.setShippingCompany(requestOrder.getString("billingCompany"));
			order.setShippingContact(requestOrder.getString("billingContact"));
			order.setShippingNumber(requestOrder.getString("billingNumber"));
			order.setShippingEmail(requestOrder.getString("billingEmail"));
			order.setShippingAddress(requestOrder.getString("billingAddress"));
			order.setShippingCity(requestOrder.getString("billingCity"));
			order.setShippingState(requestOrder.getString("billingState"));
			order.setShippingCountry(requestOrder.getString("billingCountry"));
			order.setShippingZip(requestOrder.getInteger("billingZip"));
		} else {
			order.setShippingCompany(requestOrder.getString("shippingCompany"));
			order.setShippingContact(requestOrder.getString("shippingContact"));
			order.setShippingNumber(requestOrder.getString("shippingNumber"));
			order.setShippingEmail(requestOrder.getString("shippingEmail"));
			order.setShippingAddress(requestOrder.getString("shippingAddress"));
			order.setShippingCity(requestOrder.getString("shippingCity"));
			order.setShippingState(requestOrder.getString("shippingState"));
			order.setShippingCountry(requestOrder.getString("shippingCountry"));
			order.setShippingZip(requestOrder.getInteger("shippingZip"));
		}
		order.setNote(requestOrder.getString("note"));
		order.setShippingVia(requestOrder.getString("shippingVia"));
		order.setShippingFee(requestOrder.getFloat("shippingFee"));
		order.setSameAsBilling(requestOrder.getInteger("sameAsBilling"));
		order.setPaymentTerm(requestOrder.getString("paymentTerm"));
		User loginUser = getUserByHeader();
		String username = loginUser.getUsername();
		order.setSales(username);
		JSONArray itemArr = requestOrder.getJSONArray("orderItems");
		String descs = "";
		for(int i = 0; i < itemArr.size(); i++) {
			descs = descs + itemArr.getJSONObject(i).getString("description") + ", ";
		}
		order.setDescription(descs);
		orderMapper.insertOrder(order);
		
		List<OrderItem> items = new ArrayList<OrderItem>();
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
		if (items.size() != 0) {
			orderItemMapper.insertList(items);
		}
		
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
	
	// does not work
	@JsonView(View.Summary.class)
	@RequestMapping(value = {"/getLastInvoiceNo"}, method = RequestMethod.GET)
	public Integer getLastOrderId() throws Exception {
		Order last = orderMapper.getLastOrder();
		Integer lastInvoiceNo = last.getInvoiceNo();
		logger.info(lastInvoiceNo);
		return lastInvoiceNo;
	}
	
	@JsonView(View.Summary.class)
	@RequestMapping(value = {"/editOrder"}, method = RequestMethod.POST)
	public void editOrder(@RequestBody(required = false) JSONObject editedOrder) throws Exception {
		Order toEdit = orderMapper.selectByPrimaryKey(editedOrder.getInteger("orderId"));
		
		toEdit.setType(editedOrder.getString("type"));
		toEdit.setCustomer(editedOrder.getString("customer"));
		toEdit.setStatus(editedOrder.getString("status"));
		toEdit.setInvoiceNo(editedOrder.getInteger("invoiceNo"));
		String inv = "";
		String due = "";
		if (editedOrder.getString("invoiceDate") != null) {
			inv = editedOrder.getString("invoiceDate").replaceAll("[a-zA-Z]", " ");
		}
		if (editedOrder.getString("dueDate") != null) {
			due = editedOrder.getString("dueDate").replaceAll("[a-zA-Z]", " ");
		}
		toEdit.setInvoiceDate(inv);
		toEdit.setDueDate(due);
		toEdit.setTrackingNo(editedOrder.getString("trackingNo"));
		toEdit.setModifyTime(new Timestamp(new Date().getTime()));
		toEdit.setBillingCompany(editedOrder.getString("billingCompany"));
		toEdit.setBillingContact(editedOrder.getString("billingContact"));
		toEdit.setBillingNumber(editedOrder.getString("billingNumber"));
		toEdit.setBillingEmail(editedOrder.getString("billingEmail"));
		toEdit.setBillingAddress(editedOrder.getString("billingAddress"));
		toEdit.setBillingCity(editedOrder.getString("billingCity"));
		toEdit.setBillingState(editedOrder.getString("billingState"));
		toEdit.setBillingCountry(editedOrder.getString("billingCountry"));
		toEdit.setBillingZip(editedOrder.getInteger("billingZip"));
		toEdit.setShippingCompany(editedOrder.getString("shippingCompany"));
		toEdit.setShippingContact(editedOrder.getString("shippingContact"));
		toEdit.setShippingNumber(editedOrder.getString("shippingNumber"));
		toEdit.setShippingEmail(editedOrder.getString("shippingEmail"));
		toEdit.setShippingAddress(editedOrder.getString("shippingAddress"));
		toEdit.setShippingCity(editedOrder.getString("shippingCity"));
		toEdit.setShippingState(editedOrder.getString("shippingState"));
		toEdit.setShippingCountry(editedOrder.getString("shippingCountry"));
		toEdit.setShippingZip(editedOrder.getInteger("shippingZip"));
		toEdit.setNote(editedOrder.getString("note"));
		toEdit.setShippingVia(editedOrder.getString("shippingVia"));
		toEdit.setShippingFee(editedOrder.getFloat("shippingFee"));
		toEdit.setSameAsBilling(editedOrder.getInteger("sameAsBilling"));
		toEdit.setPaymentTerm(editedOrder.getString("paymentTerm"));
		User loginUser = getUserByHeader();
		String username = loginUser.getUsername();
		toEdit.setSales(username);
		
		List<OrderItem> toEditItems = new ArrayList<OrderItem>();
		JSONArray itemArr = editedOrder.getJSONArray("orderItems");
		if (itemArr != null && itemArr.size() > 0) {
			for(int i=0; i<itemArr.size(); i++) {
				OrderItem item = new OrderItem();
				item.setOrderId(toEdit.getOrderId());
				item.setProduct(itemArr.getJSONObject(i).getString("product"));
				item.setQuantity(itemArr.getJSONObject(i).getInteger("quantity"));
				item.setRate(itemArr.getJSONObject(i).getFloat("rate"));
				item.setAmount(itemArr.getJSONObject(i).getFloat("amount"));
				item.setTax(itemArr.getJSONObject(i).getString("tax"));
				item.setDescription(itemArr.getJSONObject(i).getString("description"));
				toEditItems.add(item);
			}
			orderItemMapper.insertList(toEditItems);
		}
		String descs = "";
		List<OrderItem> currentItems = orderItemMapper.getOrderItemListByOrderId(toEdit.getOrderId());
		if (currentItems != null && currentItems.size() > 0) {
			for(int i = 0; i < currentItems.size(); i += 1)  {
				descs = descs + currentItems.get(i).getDescription() + ", ";
			}
		}
		toEdit.setDescription(descs);
		orderMapper.updateByPrimaryKey(toEdit);
		logger.info("Edit Success!");
	}
	@JsonView(View.Summary.class)
	@RequestMapping(value= {"/validInvoiceNo"}, method = RequestMethod.GET)
	public Boolean validInvoiceNo(@RequestParam(value = "invoiceNo", required = false) Integer invoiceNo)
			throws Exception {
		if (invoiceNo == null) {
			return false;
		}
		Order result = new Order();
		result = orderMapper.getOrderByInvoiceNo(invoiceNo);
		return result == null;
	}
	
	
}
