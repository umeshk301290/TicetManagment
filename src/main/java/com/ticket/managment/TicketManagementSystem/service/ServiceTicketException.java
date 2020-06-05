package com.ticket.managment.TicketManagementSystem.service;

import lombok.Getter;
import lombok.Setter;

/**
 * @author umeshkumar01
 *
 */
@Getter
@Setter
public class ServiceTicketException extends Exception {

	private static final long serialVersionUID = -5485553075889191833L;
	String errorMessage;
	String errorCode;
	
	public ServiceTicketException(String errorMessage,String errorCode){
		this.errorMessage= errorMessage;
		this.errorCode = errorCode;
		
	}

}
