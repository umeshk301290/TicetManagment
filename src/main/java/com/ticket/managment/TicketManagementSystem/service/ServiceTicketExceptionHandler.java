package com.ticket.managment.TicketManagementSystem.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author umeshkumar01
 *
 */
@ControllerAdvice
public class ServiceTicketExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	Environment env;

	/**
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(ServiceTicketException.class)
	public ResponseEntity<ServiceTicketExceptionConverter> displayServiceTicketException(ServiceTicketException ex) {

		ServiceTicketExceptionConverter serviceTicketExceptionConverter = new ServiceTicketExceptionConverter();
		serviceTicketExceptionConverter.setErrorCode(ex.getErrorCode());
		serviceTicketExceptionConverter.setErrorMessage(ex.getErrorMessage());
		serviceTicketExceptionConverter.setTimeStamp(new Date().getTime());
		if (ex.getErrorCode().equals("400")) {
			return new ResponseEntity<ServiceTicketExceptionConverter>(serviceTicketExceptionConverter,
					HttpStatus.BAD_REQUEST);

		}

		return new ResponseEntity<ServiceTicketExceptionConverter>(serviceTicketExceptionConverter,
				HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ServiceTicketExceptionConverter> displayDataIntegrityViolationException(DataIntegrityViolationException ex) {

		ServiceTicketExceptionConverter serviceTicketExceptionConverter = new ServiceTicketExceptionConverter();
		serviceTicketExceptionConverter.setErrorCode(env.getProperty("data.integrity.voilation.code"));
		serviceTicketExceptionConverter.setErrorMessage(env.getProperty("data.integrity.voilation.message"));
		serviceTicketExceptionConverter.setTimeStamp(new Date().getTime());

		return new ResponseEntity<ServiceTicketExceptionConverter>(serviceTicketExceptionConverter,
				HttpStatus.INTERNAL_SERVER_ERROR);

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleMethodArgumentNotValid(org.springframework.web.bind.MethodArgumentNotValidException, org.springframework.http.HttpHeaders, org.springframework.http.HttpStatus, org.springframework.web.context.request.WebRequest)
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> value = new HashMap<String, Object>();
		ServiceTicketExceptionConverter aPIBookInformationException = new ServiceTicketExceptionConverter();
		aPIBookInformationException.setTimeStamp(new Date().getTime());
		value.put("status", status.value());
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(error -> error.getDefaultMessage())
				.collect(Collectors.toList());
		value.put("errors", errors);
		aPIBookInformationException.setErrorMap(value);
		return new ResponseEntity<Object>(aPIBookInformationException, HttpStatus.BAD_REQUEST);
	}
	



}
