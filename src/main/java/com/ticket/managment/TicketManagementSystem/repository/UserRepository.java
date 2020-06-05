package com.ticket.managment.TicketManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticket.managment.TicketManagementSystem.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
 
 User findByEmail(String email);
}

