package com.ticket.managment.TicketManagementSystem.service;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

/**
 * @author umeshkumar01
 *
 */
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceTicketExceptionConverter {

	String errorMessage;
	String errorCode;
	long timeStamp;
	Map<String,Object> errorMap;

}
