package com.example.controller;

import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
    
	
	private String MESSAGE = "hello";
	
	private String TITLE = "Spring Security LDAP test";

	@RequestMapping("/app")
	public String welcome(Map<String, Object> model) {
		model.put("title", TITLE);
		model.put("message", MESSAGE);
		model.put("username", SecurityContextHolder.getContext().getAuthentication().getName());
		model.put("roles", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		model.put("principal", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return "default";
	}

@RequestMapping("/logout")
	public String logout(Map<String, Object> model) {
		 SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
		return "default";
	}

	//exception example
	@RequestMapping("/http500")
	public String ServiceUnavailable() {
		throw new RuntimeException("500 - internal server error");
	}

}