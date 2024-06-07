package com.flower.tour.controller;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@ResponseBody
	public List<Map<String, Object>> test(String lat, String lon) throws URISyntaxException {
		
		List<Map<String, Object>> locList = mainService.getLocationBasedList(lat, lon);
		
		return locList;		 
	}
	
	@GetMapping("/sample")
	public String sample() {
		return "/test/sample2";
	}
}
