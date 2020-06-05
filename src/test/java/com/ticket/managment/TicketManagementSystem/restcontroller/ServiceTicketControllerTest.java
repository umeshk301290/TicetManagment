package com.ticket.managment.TicketManagementSystem.restcontroller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.ticket.managment.TicketManagementSystem.entity.ServiceTicket;
import com.ticket.managment.TicketManagementSystem.service.ServiceTicketException;
import com.ticket.managment.TicketManagementSystem.service.TicketService;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTicketControllerTest {
	
@InjectMocks
ServiceTicketController serviceTicketController;

@Mock
TicketService ticketService;

@Test
public void testAddTicket() {
	
	ServiceTicket ticket = new ServiceTicket();
	ticket.setDepartment("hr");
	ticket.setDetails("organization");
	ticket.setUserId(1L);
	when(ticketService.addTicket(any())).thenReturn(ticket);
	assertTrue(serviceTicketController.addTicket(any()) instanceof ResponseEntity);
	
}

@Test
public void testFetchAllTicket() {
	
	List<ServiceTicket> serviceList = new ArrayList();
	ServiceTicket ticket = new ServiceTicket();
	ticket.setDepartment("hr");
	ticket.setDetails("organization");
	ticket.setUserId(1L);
	serviceList.add(ticket);
	when(ticketService.findAllTickets()).thenReturn(serviceList);
	assertTrue(serviceTicketController.findAllTickets() instanceof ResponseEntity);
	
}

@Test
public void testFetchTicket() throws ServiceTicketException {
	
	ServiceTicket ticket = new ServiceTicket();
	ticket.setDepartment("hr");
	ticket.setDetails("organization");
	ticket.setUserId(1L);
	when(ticketService.findTicket(any())).thenReturn(ticket);
	assertTrue(serviceTicketController.findTicket(any()) instanceof ResponseEntity);
	
}



@Test(expected = ServiceTicketException.class)
public void testTicketNotFound() throws ServiceTicketException {
	
	ServiceTicket ticket = new ServiceTicket();
	ticket.setDepartment("hr");
	ticket.setDetails("organization");
	ticket.setUserId(1L);
	when(ticketService.findTicket(any())).thenThrow(new ServiceTicketException("error", "400"));
	serviceTicketController.findTicket(any());
	
}

@Test()
public void testTicketsForSpecificUser() throws ServiceTicketException {
	
	List<ServiceTicket> serviceList = new ArrayList();
	ServiceTicket ticket = new ServiceTicket();
	ticket.setDepartment("hr");
	ticket.setDetails("organization");
	ticket.setUserId(1L);
	serviceList.add(ticket);
	
	when(ticketService.findAllTicketsForSpecificUser(any())).thenReturn(serviceList);
	assertTrue(serviceTicketController.findAllTicketsForSpecificUser(any()) instanceof ResponseEntity);
	
	
}

@Test
public void testRemoveTicket() throws ServiceTicketException {
	
	ServiceTicket ticket = new ServiceTicket();
	ticket.setDepartment("hr");
	ticket.setDetails("organization");
	ticket.setUserId(1L);
	Mockito.doNothing()
    .when(ticketService)
    .removeTicket(Mockito.any());
	assertTrue(serviceTicketController.removeTicket(any()) instanceof ResponseEntity);

	
	
}
}


