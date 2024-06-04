package com.flower.tour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/")
	public String test() {
		return "/index";
	}
	
	@GetMapping("/sample")
	public String sample() {
		return "/sample";
	}
}
