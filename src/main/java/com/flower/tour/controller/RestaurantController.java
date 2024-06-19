package com.flower.tour.controller;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flower.tour.service.RestaurantService;

@SuppressWarnings("unused")
@Controller
public class RestaurantController {

	@Autowired
	private RestaurantService rtrService;
	
	// 메인
	@GetMapping("/restaurant/area")
	public String getTourData(Model model, @RequestParam(defaultValue = "1") int pageNo,
			@RequestParam(defaultValue = "8") int pageSize,@RequestParam(defaultValue = "0") int guCode) throws URISyntaxException, JsonProcessingException {

		List<Map<String, Object>> sigunguCode = rtrService.getSigunguCodeList();
		List<Map<String, Object>> allrestaurantData = new ArrayList<>();
		
		List<Map<String, Object>> restaurantData = rtrService.getRestaurantData(1, "서울", 39, 8, pageNo, guCode);
		
		String totalCount = (String) restaurantData.get(restaurantData.size()-1).get("totalCount");
		int endPage = Integer.parseInt(totalCount) / 8;
		
		if(Integer.parseInt(totalCount) % 8 != 0)
			endPage += 1;
		
//		System.out.println(totalCount);
		restaurantData.remove(restaurantData.size()-1);
		
		model.addAttribute("restaurantData", restaurantData);
		model.addAttribute("sigunguCode", sigunguCode);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("endPage", endPage);
		model.addAttribute("guCode", guCode);
		

		return "restaurant/restaurant_main";
	}
	
	// 셀럭된 도시별 음식점
	@GetMapping("/restaurant/area/{selectedSigungu}")
	@ResponseBody
	public List<Map<String, Object>> getRestaurantDataBySigungu(@PathVariable("selectedSigungu") int sigunguCode,
			@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "8") int pageSize)
			throws URISyntaxException, JsonProcessingException {
//		System.out.println(sigunguCode);

		// 해당 지역 코드를 기반으로 해당 지역의 음식점 정보 조회
		List<Map<String, Object>> restaurantData = rtrService.getRestaurantData(1, "서울", 39, 20, 1, sigunguCode);

		Map<String, Object> response = new HashMap<>();
		response.put("restaurantList", restaurantData);
		response.put("currentPage", pageNo);
		response.put("pageSize", pageSize);
		response.put("hasNextPage", restaurantData.size() == pageSize);

		// 조회된 음식점 정보를 모델에 담아서 jsp 페이지로 전달
		return restaurantData;
	}
	
	// 상세페이지
	@GetMapping("/restaurant/detail")
	public String restaurantDetail(Model model, @RequestParam Integer contentid)
			throws URISyntaxException, JsonProcessingException {

		List<Map<String, Object>> restaurantInfoList = rtrService.restaurantInfo(contentid);
		System.out.println(contentid);
		System.out.println(":::::::::restaurantInfoList:::::::::" + restaurantInfoList);
		model.addAttribute("detailData", restaurantInfoList);

		return "/restaurant/restaurant_detail";
	}

}
