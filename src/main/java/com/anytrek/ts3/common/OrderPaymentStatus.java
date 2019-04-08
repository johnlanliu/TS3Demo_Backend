package com.anytrek.ts3.common;

/**
 * 订单状态
 * 
 * @author John date 2018年10月18日
 */
public class OrderPaymentStatus {
	// //Pending
	public static final int PENDING = 0;
	// //Paid
	public static final int PAID = 5;
	// //Overdue
	public static final int OVERDUE = -1;

	public static String getName(int number) {
		switch (number) {
			case 0:
				return "Pending";
			case 5:
				return "Paid";
			case -1:
				return "overdue";
	}
		return "";
	}
}
