package com.ticket.managment.TicketManagementSystem.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.managment.TicketManagementSystem.entity.User;
import com.ticket.managment.TicketManagementSystem.repository.UserRepository;

@Service
public class UserService {
	
@Autowired
UserRepository userRepository;

public void addUser(@Valid User user) {
	// TODO Auto-generated method stub
	
	User addUser = userRepository.save(user);
}

public User checkForAlreadyExistsUser(String email) {
	// TODO Auto-generated method stub
	User alreadyExistsUser = userRepository.findByEmail(email);
	return alreadyExistsUser;
}

public boolean checkForUserDetails(User user) {
	// TODO Auto-generated method stub
	User alreadyExistsUser = userRepository.findByEmail(user.getEmail());

	return false;
}



}
