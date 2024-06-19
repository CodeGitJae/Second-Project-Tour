package com.flower.tour.controller;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.http.HttpHeaders;
import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flower.tour.service.FestivalService;
import com.flower.tour.service.RestaurantService;

@Controller
public class FestivalController {

	@Autowired
	private FestivalService ftvService;

//	메인
	@GetMapping("/festival/festivalMain")
	public String gitFestivalData(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "0") Integer searchMonth)
			throws URISyntaxException, JsonProcessingException {
		
		List<Map<String, Object>> festivalList = new ArrayList<>();

		if(searchMonth == 0)
			festivalList = ftvService.festivalItems(20240101, 12, page);
		else
			festivalList = ftvService.festivalItems(20240101, 150, 1);
//		페이지 인덱스확인
//		festivalList.size();
//		System.out.println(festivalList.size());

		int totalCount = Integer.parseInt(festivalList.get(festivalList.size() - 1).get("totalCount").toString());
		int pages = (int) Math.ceil((double) totalCount / 12);
//		System.out.println(pages);

		festivalList.remove(festivalList.size() - 1);
		
		if(searchMonth != 0) {
			festivalList = ftvService.getMonthList(festivalList, searchMonth, page);
			model.addAttribute("searchMonth", searchMonth);
		}
		model.addAttribute("curPage", page);
		model.addAttribute("ftvList", festivalList);
		model.addAttribute("pages", pages);

		return "/festival/festival_main";
	}

////	월별 셀렉트박스 매핑
//	@GetMapping("/festival/mapping")
//	public String mappingMonth(Model model, @RequestParam Integer searchMonth,
//			@RequestParam(defaultValue = "1") int page) throws URISyntaxException, JsonProcessingException {
////		System.out.println("searchMonth:::::::::::::::"+searchMonth);
//
//		List<Map<String, Object>> festivalList = ftvService.festivalItems(20240101, 150, 1);
//
//		int totalCount = Integer.parseInt(festivalList.get(festivalList.size() - 1).get("totalCount").toString());
//		int pages = (int) Math.ceil((double) totalCount / 12);
//		System.out.println(pages);
//
//		festivalList.remove(festivalList.size() - 1);
//
//		
//
//		model.addAttribute("curPage", page);
//		model.addAttribute("pages", pages);
//
//		model.addAttribute("ftvList", r);
//		model.addAttribute("searchMonth", searchMonth);
//		System.out.println("======="+searchMonth);
//		return "/festival/festival_main";
//	}

	// 상세페이지
	@GetMapping("/festival/detail")
	public String festivalDetail(Model model, @RequestParam Integer contentid)
			throws URISyntaxException, JsonProcessingException {

		List<Map<String, Object>> festivalInfoList = ftvService.festivalInfo(1, 1, contentid);
//		System.out.println(contentid);
//		System.out.println(":::::::::::::::" + festivalInfoList);
		model.addAttribute("ftvInfoList", festivalInfoList);

		return "/festival/festival_detail";
	}

}
