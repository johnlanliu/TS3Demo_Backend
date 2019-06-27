package com.anytrek.ts3.mapper;

import java.util.HashMap;
import java.util.List;

import com.anytrek.ts3.model.Payment;
import com.anytrek.ts3.util.MyMapper;

/**
 * 
 * @author seanlam date 2019 M06 27
 *
 */

public interface PaymentMapper extends MyMapper<Payment>{
	
	public List<PaymentDetailDto> getPaymentListByCustomer(HashMap <String, Object> params) throws Exception;
	
	public List<PaymentDetailDto> getPaymentListByStatus(HashMap <String, Object> params) throws Exception;
	
	public List<PaymentDetailDto> getPaymentListByInvoiceNo(HashMap <String, Object> params) throws Exception;
	

	
}
