package com.flower.tour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/info")
public class InfoController {

	@GetMapping("/traffic")
	public String traffic(Model model) {
		
		model.addAttribute("title", "교통 정보");
		
		return "/info/traffic";
	}
	
	@GetMapping("/weather")
	public String weather(Model model) {
		
		model.addAttribute("title", "서울 날씨");
		
		return "/info/weather";
	}
	
	@GetMapping("/tourbus")
	public String tourbus(Model model) {
		
		model.addAttribute("title", "투어 버스");
		
		return "/info/tourbus";
	}
	
	@GetMapping("/tourmap")
	public String tourmap(Model model) {
		
		model.addAttribute("title", "관광 지도");
		
		return "/info/tourmap";
	}
}
