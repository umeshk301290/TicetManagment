package com.ticket.managment.TicketManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.Objects;
import javax.validation.Valid;
import com.ticket.managment.TicketManagementSystem.entity.User;
import com.ticket.managment.TicketManagementSystem.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserAccountController {

	@Autowired
	UserService userService;

	@Autowired
	Environment env;

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		User user = new User();
		ModelAndView model = new ModelAndView();
		model.addObject("user", user);
		model.setViewName("index");
		return model;
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		log.info("login method called. Checking whether user exists or not");
		User alreadyExistsUser = userService.checkForAlreadyExistsUser(user.getEmail());
		if (!Objects.nonNull(alreadyExistsUser)) {
			bindingResult.rejectValue(env.getProperty("email"), "error.user", env.getProperty("email.not.exists"));
		}
        if(Objects.nonNull(alreadyExistsUser) && !(user.getPassword().equals(alreadyExistsUser.getPassword()))) {
			bindingResult.rejectValue(env.getProperty("password"), "error.user", env.getProperty("credentials.not.matched"));

        }
		if (bindingResult.hasErrors()) {
			return new ModelAndView("index");
		}
		model.addObject("email", user.getEmail());
		model.setViewName("welcome");
		return model;
	}

	@RequestMapping(value = { "/signup" }, method = RequestMethod.GET)
	public ModelAndView signUpUser(@ModelAttribute("user") User user) {
		ModelAndView model = new ModelAndView();
		model.setViewName("signup");
		return model;
	}

	@RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
	public ModelAndView addUser(@Valid User user, BindingResult result) {
		log.info("signup method called. Checking for already existing user");
		User alreadyExistsUser = userService.checkForAlreadyExistsUser(user.getEmail());
		if (Objects.nonNull(alreadyExistsUser)) {
			result.rejectValue(env.getProperty("email"), "error.user", env.getProperty("email.already.exists"));
		}
		if (result.hasErrors()) {
			return new ModelAndView("signup");
		} else {
			userService.addUser(user);
			ModelAndView model = new ModelAndView("user-added");
			model.addObject("username", user.getFirstname());
			model.addObject("email", user.getEmail());

			return model;

		}

	}
}
