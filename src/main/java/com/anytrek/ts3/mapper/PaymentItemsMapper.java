package com.anytrek.ts3.mapper;

import java.util.List;

import com.anytrek.ts3.model.PaymentItems;
import com.anytrek.ts3.util.MyMapper;

public interface PaymentItemsMapper extends MyMapper<PaymentItems> {
	
	public List<PaymentItems> getPaymentItemListByPaymentId(Integer paymentId) throws Exception;

}
