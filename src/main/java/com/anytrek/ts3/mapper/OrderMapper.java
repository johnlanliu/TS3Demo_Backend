package com.anytrek.ts3.mapper;

import java.util.HashMap;
import java.util.List;

import com.anytrek.ts3.model.Order;
import com.anytrek.ts3.util.MyMapper;

/**
 * 
 * @author jasonlai date 07/03/2019
 *
 */

public interface OrderMapper extends MyMapper<Order> {
	
	public List<Order> getOrderListByParams(HashMap<String, Object> params) throws Exception;
	
	public Integer insertOrder(Order order) throws Exception;
	
	public Order getOrderByOrderId(Integer orderId) throws Exception;
	
	public Order getLastOrder() throws Exception;
}
