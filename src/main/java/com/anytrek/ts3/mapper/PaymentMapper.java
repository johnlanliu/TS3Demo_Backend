package com.anytrek.ts3.mapper;

import java.util.HashMap;
import java.util.List;

import com.anytrek.ts3.model.Payment;
import com.anytrek.ts3.util.MyMapper;
import com.anytrek.ts3.dto.PaymentDetailDto;

/**
 * 
 * @author seanlam date 06/27/2019
 *
 */

public interface PaymentMapper extends MyMapper<Payment>{
	
//	public List<Payment> getPaymentListByInvoiceNo(Integer invoiceNo) throws Exception;
//	
//	public List<Payment> getPaymentListByCustomer(String customer) throws Exception;
//	
//	public List<Payment> getPaymentListByStatus(String status) throws Exception;
	public List<Payment> getPaymentListByParams(HashMap<String, Object> params) throws Exception;
}
