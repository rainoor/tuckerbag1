package com.cbi.action;

import java.util.Random;

import com.opensymphony.xwork2.ActionSupport;

public class OrderAction extends ActionSupport {
	
	private static final String SECCESS = "success";
	private static final String ERROR = "error";
	
	private String orderID = "";

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String generateOrderID() throws Exception {
		Random ran = new Random();
		for(int i = 0; i < 7; i++){
			orderID += ran.nextInt(10);
		}
		
		return SUCCESS;
	}
	
}
