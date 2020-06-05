package com.ticket.managment.TicketManagementSystem.restcontroller;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import com.ticket.managment.TicketManagementSystem.entity.ServiceTicket;
import com.ticket.managment.TicketManagementSystem.entity.User;
import com.ticket.managment.TicketManagementSystem.repository.ServiceTicketRepository;
import com.ticket.managment.TicketManagementSystem.repository.UserRepository;
import com.ticket.managment.TicketManagementSystem.service.ServiceTicketException;
import com.ticket.managment.TicketManagementSystem.service.TicketService;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceTest {
	
@InjectMocks
TicketService ticketService;

@Mock
ServiceTicketRepository serviceTicketRepository;

@Mock
Environment env;

@Mock
UserRepository userRepository;
	
	
@Test
public void addTicket() {
	
	ServiceTicket ticket = new ServiceTicket();
	ticket.setDepartment("hr");
	ticket.setDetails("organization");
	ticket.setUserId(1L);
	when(serviceTicketRepository.save(any())).thenReturn(ticket);
	assertTrue(ticketService.addTicket(any()) instanceof ServiceTicket);

	
	
}

@Test
public void testFetchAllTicket() {
	
	List<ServiceTicket> serviceList = new ArrayList();
	ServiceTicket ticket = new ServiceTicket();
	ticket.setDepartment("hr");
	ticket.setDetails("organization");
	ticket.setUserId(1L);
	serviceList.add(ticket);
	when(serviceTicketRepository.findAll()).thenReturn(serviceList);
	assertTrue(ticketService.findAllTickets() instanceof List);
	
}


@Test
public void testFetchTicket() throws ServiceTicketException {
	
	ServiceTicket ticket = new ServiceTicket();
	ticket.setDepartment("hr");
	ticket.setDetails("organization");
	ticket.setUserId(1L);
	when(serviceTicketRepository.findById(any())).thenReturn(Optional.of(ticket));
	assertTrue(ticketService.findTicket(any()) instanceof ServiceTicket);
	
}

@Test(expected=ServiceTicketException.class)
public void testFetchTicketNotFound() throws ServiceTicketException {
	
	ServiceTicket ticket = new ServiceTicket();
	ticket.setDepartment("hr");
	ticket.setDetails("organization");
	ticket.setUserId(1L);
	when(serviceTicketRepository.findById(any())).thenReturn(Optional.empty());
	ticketService.findTicket(1L);
	
}

@Test()
public void testTicketsForSpecificUser() throws ServiceTicketException {
	
	List<ServiceTicket> serviceList = new ArrayList();
	ServiceTicket ticket = new ServiceTicket();
	ticket.setDepartment("hr");
	ticket.setDetails("organization");
	ticket.setUserId(1L);
	serviceList.add(ticket);
	User user= new User();
	user.setEmail("hello@hello.com");
	user.setFirstname("ram");
	user.setId(1L);
	user.setTicket(Arrays.asList(new ServiceTicket()));
	
	when(userRepository.findById(any())).thenReturn(Optional.of(user));
	assertTrue(ticketService.findAllTicketsForSpecificUser(any()) instanceof List);

	
	
}

@Test(expected = ServiceTicketException.class)
public void testTicketsForSpecificUserException() throws ServiceTicketException {
	
	
	when(userRepository.findById(any())).thenReturn(Optional.empty());
	ticketService.findAllTicketsForSpecificUser(any());
		
}

@Test(expected=ServiceTicketException.class)
public void testRemoveTicketForException() throws ServiceTicketException {
	
	ServiceTicket ticket = new ServiceTicket();
	ticket.setDepartment("hr");
	ticket.setDetails("organization");
	ticket.setUserId(1L);
	
	when(serviceTicketRepository.findById(any())).thenReturn(Optional.empty());

	
	ticketService.removeTicket(any());

	
}

@Test()
public void testRemoveTicket() throws ServiceTicketException {
	
	ServiceTicket ticket = new ServiceTicket();
	ticket.setDepartment("hr");
	ticket.setDetails("organization");
	ticket.setUserId(1L);
	Mockito.doNothing()
    .when(serviceTicketRepository)
    .deleteById(Mockito.any());
	
	when(serviceTicketRepository.findById(any())).thenReturn(Optional.of(ticket));

	
	ticketService.removeTicket(any());

	
}

@Test()
public void testUpdateTicket() throws ServiceTicketException {
	
	ServiceTicket ticket = new ServiceTicket();
	ticket.setDepartment("hr");
	ticket.setDetails("organization");
	ticket.setUserId(1L);	
	when(serviceTicketRepository.findById(any())).thenReturn(Optional.of(ticket));
	when(serviceTicketRepository.save(any())).thenReturn(ticket);
	
	assertTrue(ticketService.updateTicketForSpecificUser(ticket) instanceof ServiceTicket);

	
}

@Test(expected=ServiceTicketException.class)
public void testUpdateTicketForException() throws ServiceTicketException {
	
	ServiceTicket ticket = new ServiceTicket();
	ticket.setDepartment("hr");
	ticket.setDetails("organization");
	ticket.setUserId(1L);	
	when(serviceTicketRepository.findById(any())).thenReturn(Optional.empty());	
	assertTrue(ticketService.updateTicketForSpecificUser(ticket) instanceof ServiceTicket);

	
}




}
