package com.example.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
    
	
	private String MESSAGE = "asdf - kjhkjh  ";
	
	private String TITLE = "asdf";

	@RequestMapping("/app")
	public String welcome(Map<String, Object> model) {
		model.put("title", TITLE);
		model.put("message", MESSAGE);
		return "default";
	}

	//exception example
	@RequestMapping("/http500")
	public String ServiceUnavailable() {
		throw new RuntimeException("500 - internal server error");
	}

}