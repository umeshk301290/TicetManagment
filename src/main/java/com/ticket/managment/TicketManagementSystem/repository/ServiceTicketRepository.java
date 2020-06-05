package com.ticket.managment.TicketManagementSystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ticket.managment.TicketManagementSystem.entity.ServiceTicket;

public interface ServiceTicketRepository extends JpaRepository<ServiceTicket, Long> {
	

}
