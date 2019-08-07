package com.anytrek.ts3.mapper;

import java.util.List;

import com.anytrek.ts3.model.OrderItems;
import com.anytrek.ts3.util.MyMapper;

/**
 * 
 * @author jasonlai date 07/08/2019
 *
 */

public interface OrderItemsMapper extends MyMapper<OrderItems> {

	public List<OrderItems> getOrderItemsListByOrderId(Integer orderId) throws Exception;

	public int deleteOrderItems(Integer orderId);

}
