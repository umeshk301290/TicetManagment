package com.ticket.managment.TicketManagementSystem.restcontroller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.ticket.managment.TicketManagementSystem.entity.ServiceTicket;
import com.ticket.managment.TicketManagementSystem.service.ServiceTicketException;
import com.ticket.managment.TicketManagementSystem.service.ServiceTicketExceptionConverter;
import com.ticket.managment.TicketManagementSystem.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(value="Ticket Management" ,description="Ticket Management System")
public class ServiceTicketController {

	@Autowired
	TicketService ticketService;

	@PostMapping(value = "/tickets")
	@ResponseStatus(code=HttpStatus.CREATED)
	@ApiOperation(value="Add a ticket",response=ServiceTicket.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad request", response = ServiceTicketExceptionConverter.class),
            @ApiResponse(code = 401, message = "Not authorized", response = ServiceTicketExceptionConverter.class),
            @ApiResponse(code = 404, message = "Not Found", response = ServiceTicketExceptionConverter.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ServiceTicketExceptionConverter.class),
            @ApiResponse(code = 500, message = "Technical error", response = ServiceTicketExceptionConverter.class) })
	public ResponseEntity<ServiceTicket> addTicket(@Valid @RequestBody ServiceTicket ticket) {
         log.info("add ticket operation called");
         log.debug("ticket details are as {} ",ticket);
		ServiceTicket serviceTicket = ticketService.addTicket(ticket);
		return new ResponseEntity<ServiceTicket>(serviceTicket,HttpStatus.CREATED);

	}

	@GetMapping(value = "/tickets")
	@ApiOperation(value="Fetch all the ticket in the System",response=ServiceTicket.class,responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad request", response = ServiceTicketExceptionConverter.class),
            @ApiResponse(code = 401, message = "Not authorized", response = ServiceTicketExceptionConverter.class),
            @ApiResponse(code = 404, message = "Not Found", response = ServiceTicketExceptionConverter.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ServiceTicketExceptionConverter.class),
            @ApiResponse(code = 500, message = "Technical error", response = ServiceTicketExceptionConverter.class) })
	public ResponseEntity<List<ServiceTicket>> findAllTickets() {
        log.info("fetch all tickets operation called");
		List<ServiceTicket> serviceTicket = ticketService.findAllTickets();
		return ResponseEntity.ok().body(serviceTicket);

	}

	@GetMapping(value = "/tickets/{ticketId}")
	@ApiOperation(value="Fetch a  ticket in the System",response=ServiceTicket.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad request", response = ServiceTicketExceptionConverter.class),
            @ApiResponse(code = 401, message = "Not authorized", response = ServiceTicketExceptionConverter.class),
            @ApiResponse(code = 404, message = "Not Found", response = ServiceTicketExceptionConverter.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ServiceTicketExceptionConverter.class),
            @ApiResponse(code = 500, message = "Technical error", response = ServiceTicketExceptionConverter.class) })
	public ResponseEntity<ServiceTicket> findTicket(@PathVariable("ticketId") Long ticketId)
			throws ServiceTicketException {
        log.info("fetch a ticket operation called for ticket id {} ",ticketId);
		ServiceTicket serviceTicket = ticketService.findTicket(ticketId);
		return ResponseEntity.ok().body(serviceTicket);

		
	}
	@GetMapping(value = "/{userId}/tickets")
	@ApiOperation(value="Fetch all the ticket in the System for a specific user",response=ServiceTicket.class,responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad request", response = ServiceTicketExceptionConverter.class),
            @ApiResponse(code = 401, message = "Not authorized", response = ServiceTicketExceptionConverter.class),
            @ApiResponse(code = 404, message = "Not Found", response = ServiceTicketExceptionConverter.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ServiceTicketExceptionConverter.class),
            @ApiResponse(code = 500, message = "Technical error", response = ServiceTicketExceptionConverter.class) })
	public ResponseEntity<List<ServiceTicket>> findAllTicketsForSpecificUser(@PathVariable("userId") Long userId)
			throws ServiceTicketException {
        log.info("fetch tickets for a user {} operation called ", userId);
		List<ServiceTicket> serviceTicket = ticketService.findAllTicketsForSpecificUser(userId);
		return ResponseEntity.ok().body(serviceTicket);

	}

	@GetMapping(value = "/{userId}/tickets/{ticketId}")
	@ApiOperation(value="Fetch a  ticket in the System for the specific user",response=ServiceTicket.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad request", response = ServiceTicketExceptionConverter.class),
            @ApiResponse(code = 401, message = "Not authorized", response = ServiceTicketExceptionConverter.class),
            @ApiResponse(code = 404, message = "Not Found", response = ServiceTicketExceptionConverter.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ServiceTicketExceptionConverter.class),
            @ApiResponse(code = 500, message = "Technical error", response = ServiceTicketExceptionConverter.class) })
	public ResponseEntity<ServiceTicket> findTicketForSpecificUser(@PathVariable("userId") Long userId,
			@PathVariable("ticketId") Long ticketId) throws ServiceTicketException {
        log.info("fetch ticket for ticket id {} for userid {} operation called ",ticketId,userId);
		ServiceTicket serviceTicket = ticketService.findTicketForSpecificUser(userId, ticketId);
		return ResponseEntity.ok().body(serviceTicket);

	}
	
	@PutMapping(value = "/tickets/{ticketId}")
	@ApiOperation(value="update a ticket",response=ServiceTicket.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad request", response = ServiceTicketExceptionConverter.class),
            @ApiResponse(code = 401, message = "Not authorized", response = ServiceTicketExceptionConverter.class),
            @ApiResponse(code = 404, message = "Not Found", response = ServiceTicketExceptionConverter.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ServiceTicketExceptionConverter.class),
            @ApiResponse(code = 500, message = "Technical error", response = ServiceTicketExceptionConverter.class) })
	public ResponseEntity<ServiceTicket> updateTicket(@PathVariable("ticketId") Long ticketId,
			@Valid @RequestBody ServiceTicket ticket) throws ServiceTicketException {
        log.info("update ticket details called for ticket id {ticketId} ",ticketId);
		ServiceTicket serviceTicket = ticketService.updateTicketForSpecificUser(ticket);
		return ResponseEntity.ok().body(serviceTicket);

	}
	
	@DeleteMapping(value = "/tickets/{ticketId}")
	@ApiOperation(value="remove a ticket")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad request", response = ServiceTicketExceptionConverter.class),
            @ApiResponse(code = 401, message = "Not authorized", response = ServiceTicketExceptionConverter.class),
            @ApiResponse(code = 404, message = "Not Found", response = ServiceTicketExceptionConverter.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ServiceTicketExceptionConverter.class),
            @ApiResponse(code = 500, message = "Technical error", response = ServiceTicketExceptionConverter.class) })
	public ResponseEntity removeTicket(@PathVariable("ticketId") Long ticketId) throws ServiceTicketException {
        log.info("delete operation called for ticketId {} " , ticketId);
		ticketService.removeTicket(ticketId);
		return ResponseEntity.ok().build();

	}
}