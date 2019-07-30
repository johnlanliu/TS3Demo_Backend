package com.anytrek.ts3.mapper;

import java.util.List;

import com.anytrek.ts3.model.PaymentItem;
import com.anytrek.ts3.util.MyMapper;

public interface PaymentItemMapper extends MyMapper<PaymentItem> {
	
	public List<PaymentItem> getPaymentItemListByPaymentId(Integer paymentId) throws Exception;

	public void deleteItem(Integer itemId) throws Exception;
	
	public Integer insertPaymentItem(PaymentItem paymentItem) throws Exception;
}
