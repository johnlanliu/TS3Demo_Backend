package com.anytrek.ts3.controller.organization;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.anytrek.ts3.mapper.OrderItemsMapper;
import com.anytrek.ts3.mapper.OrderMapper;
import com.anytrek.ts3.model.User;
import com.fasterxml.jackson.annotation.JsonView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.anytrek.ts3.ControllerBase;
import com.anytrek.ts3.common.OrderStatusConstants;
import com.anytrek.ts3.model.Order;
import com.anytrek.ts3.model.OrderItems;

@RestController
@RequestMapping("/orgMOrder")
public class OrderController extends ControllerBase {

	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(OrderController.class);

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private OrderItemsMapper orderItemsMapper;

	/**
	 * 获取订单列表
	 * 
	 * @param invoiceNo
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("org:order:view")
	@JsonView(View.Summary.class)
	@RequestMapping(value = { "/getOrderList" }, method = RequestMethod.GET)
	public String getOrderList(@RequestParam(value = "invoiceNo", required = false) String invoiceNo,
			@RequestParam(value = "status", required = false) String status) throws Exception {
		HashMap<String, Object> params = new HashMap<>();
		if (!StringUtils.isEmpty(status)) {
			params.put("status", status);
		}
		if (!StringUtils.isEmpty(invoiceNo)) {
			params.put("invoiceNo", invoiceNo);
		}
		List<Order> viewList = orderMapper.getOrderListByParams(params);
		// 获取分页类数据
		return JSONArray.toJSON(viewList).toString();
	}

	/**
	 * 根据id查找订单
	 * 
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("org:order:view")
	@JsonView(View.Summary.class)
	@RequestMapping(value = { "/getOrderByOrderId" }, method = RequestMethod.GET)
	public Order getOrderByOrderId(@RequestParam(value = "orderId", required = true) Integer orderId) throws Exception {
		if (orderId == null || orderId == 0) {
			throw new WebException(ErrorCode.ORDER_PARAMETER_ERROR, "ORDER_PARAMETER_ERROR!");
		}

		Order order = orderMapper.selectByPrimaryKey(orderId);
		if (order == null) {
			throw new WebException(ErrorCode.ORDER_NOT_FOUND, "ORDER_NOT_FOUND!");
		}
		return order;
	}

	/**
	 * 增加订单
	 * 
	 * @param requestOrder
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("org:order:add")
	@JsonView(View.Summary.class)
	@RequestMapping(value = { "/addOrder" }, method = RequestMethod.POST)
	public Order addOrder(@RequestBody(required = true) String requestOrder) throws Exception {
		if (StringUtils.isEmpty(requestOrder)) {
			throw new WebException(ErrorCode.ORDER_PARAMETER_ERROR, "ORDER_PARAMETER_ERROR!");
		}
		JSONObject orderObj = JSON.parseObject(requestOrder);
		if (orderObj == null) {
			throw new WebException(ErrorCode.ORDER_PARAMETER_ERROR, "ORDER_PARAMETER_ERROR!");
		}
		Order order = JSON.parseObject(requestOrder, Order.class);
		order.setCreateTime(new Timestamp(new Date().getTime()));
		User loginUser = getUserByHeader();
		order.setSales(loginUser.getUsername());
		JSONArray itemArr = orderObj.getJSONArray("orderItems");

		if (itemArr != null) {
			String descs = "";
			for (int i = 0; i < itemArr.size(); i++) {
				descs += itemArr.getJSONObject(i).getString("description") + ", ";
			}
			order.setDescription(descs.substring(0, descs.length() - 1));
		}

		orderMapper.insertSelective(order);

		List<OrderItems> items = new ArrayList<OrderItems>();
		for (int i = 0; i < itemArr.size(); i++) {
			OrderItems item = JSON.parseObject(itemArr.getString(i), OrderItems.class);
			item.setOrderId(order.getOrderId());
			if (item != null)
				items.add(item);
		}
		if (items.size() != 0) {
			orderItemsMapper.insertList(items);
		}
		return order;
	}

	/**
	 * 取消订单
	 * 
	 * @param orderId
	 * @throws Exception
	 */
	@RequiresPermissions("org:order:edit")
	@JsonView(View.Summary.class)
	@RequestMapping(value = { "/cancelOrder" }, method = RequestMethod.GET)
	public Order cancelOrder(@RequestParam(value = "orderId", required = true) Integer orderId) throws Exception {
		if (orderId == null || orderId == 0) {
			throw new WebException(ErrorCode.ORDER_PARAMETER_ERROR, "ORDER_PARAMETER_ERROR!");
		}
		Order order = orderMapper.selectByPrimaryKey(orderId);
		if (order == null) {
			if (orderId == null || orderId == 0) {
				throw new WebException(ErrorCode.ORDER_NOT_FOUND, "ORDER_NOT_FOUND!");
			}
		}
		order.setStatus(OrderStatusConstants.CANCELLED);
		orderMapper.updateByPrimaryKey(order);
		return order;
	}

	/**
	 * 获取getLastInvoiceNo
	 * 
	 * @return
	 * @throws Exception
	 */
	// does not work
	@RequiresPermissions("org:order:view")
	@JsonView(View.Summary.class)
	@RequestMapping(value = { "/getLastInvoiceNo" }, method = RequestMethod.GET)
	public String getLastInvoiceNo() throws Exception {
		String invoiceNo = orderMapper.getLastOrder();
		if(StringUtils.isEmpty(invoiceNo)) {
			return "0";
		}
		return invoiceNo;
	}

	/**
	 * 编辑订单
	 * 
	 * @param requestOrder
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("org:order:edit")
	@JsonView(View.Summary.class)
	@RequestMapping(value = { "/editOrder" }, method = RequestMethod.POST)
	public Order editOrder(@RequestBody(required = true) String requestOrder, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (StringUtils.isEmpty(requestOrder)) {
			throw new WebException(ErrorCode.ORDER_PARAMETER_ERROR, "ORDER_PARAMETER_ERROR!");
		}
		JSONObject orderObj = JSON.parseObject(requestOrder);
		if (orderObj == null) {
			throw new WebException(ErrorCode.ORDER_PARAMETER_ERROR, "ORDER_PARAMETER_ERROR!");
		}
		Order order = JSON.parseObject(requestOrder, Order.class);
		order.setCreateTime(new Timestamp(new Date().getTime()));
		User loginUser = getUserByHeader();
		order.setSales(loginUser.getUsername());

		List<OrderItems> toEditItems = new ArrayList<OrderItems>();
		JSONArray itemArr = orderObj.getJSONArray("orderItems");

		if (itemArr != null) {
			String descs = "";
			for (int i = 0; i < itemArr.size(); i++) {
				OrderItems item = JSON.parseObject(itemArr.getString(i), OrderItems.class);
				item.setOrderId(order.getOrderId());
				descs += itemArr.getJSONObject(i).getString("description") + ", ";
				if (item != null)
					toEditItems.add(item);
			}
			order.setDescription(descs);
			orderItemsMapper.deleteOrderItems(order.getOrderId());
			orderItemsMapper.insertList(toEditItems);
		}
		return order;
	}

	/**
	 * 
	 * @param invoiceNo
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("org:order:view")
	@JsonView(View.Summary.class)
	@RequestMapping(value = { "/validInvoiceNo" }, method = RequestMethod.GET)
	public Boolean validInvoiceNo(@RequestParam(value = "invoiceNo", required = false) Integer invoiceNo)
			throws Exception {
		if (invoiceNo == null) {
			return false;
		}
		Order result = orderMapper.getOrderByInvoiceNo(invoiceNo);
		return result == null;
	}

}
