package com.ticket.managment.TicketManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TicketManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketManagementSystemApplication.class, args);
	}

}
