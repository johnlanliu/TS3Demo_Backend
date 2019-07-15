package com.anytrek.ts3.mapper;

import java.util.List;

import com.anytrek.ts3.model.OrderItem;
import com.anytrek.ts3.util.MyMapper;

/**
 * 
 * @author jasonlai date 07/08/2019
 *
 */

public interface OrderItemMapper extends MyMapper<OrderItem> {
	
	public List<OrderItem> getOrderItemListByOrderId(Integer orderId) throws Exception;
	
	public void deleteItem(Integer itemId) throws Exception;
}
