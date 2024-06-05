package com.flower.tour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.flower.tour.service.MainService;

@Controller
public class MainController {
		
	@Autowired
	private MainService mainService;

	@GetMapping("/")
	public String index(Model model) {
		
		return "/index";
	}
	
	@GetMapping("/test")
	public void test(String lat, String lon) {
		
		mainService.getLocationBasedList(lat, lon);
		 
	}
	
	@GetMapping("/sample")
	public String sample() {
		return "/test/sample2";
	}
}
