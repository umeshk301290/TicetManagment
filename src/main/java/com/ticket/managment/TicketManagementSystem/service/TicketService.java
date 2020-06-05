package com.ticket.managment.TicketManagementSystem.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ticket.managment.TicketManagementSystem.entity.ServiceTicket;
import com.ticket.managment.TicketManagementSystem.entity.User;
import com.ticket.managment.TicketManagementSystem.repository.ServiceTicketRepository;
import com.ticket.managment.TicketManagementSystem.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TicketService {

	@Autowired
	ServiceTicketRepository serviceTicketRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	Environment env;
	
    @CachePut(value="ticketCache",key="#ticket.id")
	public ServiceTicket addTicket(ServiceTicket ticket) {
		// TODO Auto-generated method stub
		ServiceTicket serviceTicket = serviceTicketRepository.save(ticket);
		log.info("new ticket added in system");
		return serviceTicket;
	}
    public List<ServiceTicket> findAllTickets() {
		// TODO Auto-generated method stub
		List<ServiceTicket> serviceTickets = serviceTicketRepository.findAll();
		return serviceTickets;

	}
    @Cacheable(value="ticketCache" , key="#ticketId")
	public ServiceTicket findTicket(Long ticketId) throws ServiceTicketException {
		// TODO Auto-generated method stub
		ServiceTicket serviceTicket = serviceTicketRepository.findById(ticketId)
				.orElseThrow(() -> new ServiceTicketException(env.getProperty("no.ticket.found.message"),
						env.getProperty("no.ticket.found.code")));
		return serviceTicket;
	}
	public List<ServiceTicket> findAllTicketsForSpecificUser(Long userId) throws ServiceTicketException {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId).orElseThrow(() -> new ServiceTicketException(env.getProperty("no.user.found.message"),
						env.getProperty("no.user.found.code")));
		List<ServiceTicket> serviceTicket = user.getTicket();
		return serviceTicket;
	}

	public ServiceTicket findTicketForSpecificUser(Long userId, Long ticketId) throws ServiceTicketException {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId).orElseThrow(() -> new ServiceTicketException(env.getProperty("no.user.found.message"),
				env.getProperty("no.user.found.code")));
		List<ServiceTicket> serviceTicketList = user.getTicket();
		serviceTicketList= serviceTicketList.stream().filter(ticket -> ticket.getId().equals(ticketId)).collect(Collectors.toList());
		if(serviceTicketList.size()==0 || serviceTicketList.size()>1) {
		log.info("invalid ticket list size");
			throw new ServiceTicketException(env.getProperty("no.ticket.found.for.user.message"),
					env.getProperty("no.ticket.found.for.user.code"));
		}
		ServiceTicket ticket = serviceTicketList.get(0);
		return ticket;
	}
    @CachePut(value="ticketCache" , key="#ticket.id")
	public ServiceTicket updateTicketForSpecificUser(ServiceTicket ticket) throws ServiceTicketException {
		// TODO Auto-generated method stub
		ServiceTicket serviceTicket = serviceTicketRepository.findById(ticket.getId())
				.orElseThrow(() -> new ServiceTicketException(env.getProperty("no.ticket.found.message"),
						env.getProperty("no.ticket.found.code")));
		
		serviceTicket.setDepartment(ticket.getDepartment());
		serviceTicket.setDetails(ticket.getDetails());
		log.info("ticket updated");
		return serviceTicketRepository.save(serviceTicket);
		
			}
    @CacheEvict(value="ticketCache", key="#ticketId" ,allEntries=false)
	public void removeTicket(Long ticketId) throws ServiceTicketException {
		// TODO Auto-generated method stub
		ServiceTicket serviceTicket = serviceTicketRepository.findById(ticketId)
				.orElseThrow(() -> new ServiceTicketException(env.getProperty("no.ticket.found.message"),
						env.getProperty("no.ticket.found.code")));
		log.info("ticket deleted");
		serviceTicketRepository.deleteById(serviceTicket.getId());
	}

}
