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
	
	
	@GetMapping("/Restaurant/area/{selectedSigungu}")
	@ResponseBody
	public List<Map<String, Object>> getTourDataBySigungu(@PathVariable("selectedSigungu") int sigunguCode, Model model)
										throws URISyntaxException, JsonProcessingException{		
		System.out.println(sigunguCode);
		// 해당 지역 코드를 기반으로 해당 지역의 음식점 정보 조회
		List<Map<String, Object>> tourData = rtrService.getRestaurantData(1, "서울", 39, 15, 1000, 1, sigunguCode);
		
		// 조회된 음식점 정보를 모델에 담아서 jsp 페이지로 전달
		
		return tourData; 
	}

    @GetMapping("/Restaurant/area")
    public String getTourData(Model model) 
                              throws URISyntaxException, JsonProcessingException {
    	List<Map<String, Object>> RestaurantCode = rtrService.getRestaurantAreaCode(15, 1, 1);
        List<Map<String, Object>> allTourData = new ArrayList<>();
    	
    	for (Map<String, Object> sigungu : RestaurantCode) {
    		int outerRestaurantCode = Integer.parseInt(sigungu.get("code").toString());   	
    		String sigunguName = sigungu.get("name").toString();
    		List<Map<String, Object>> tourData = rtrService.getRestaurantData(1, 39, 15, 1000, 1, outerRestaurantCode);
       
    		Map<String, Object> RestaurantInfo = new HashMap<>();
    		RestaurantInfo.put("RestaurantCode", outerRestaurantCode);
    		RestaurantInfo.put("sigunguName", sigunguName);
    		RestaurantInfo.put("tourData", tourData);
    		
    		allTourData.add(RestaurantInfo);
    	}
        model.addAttribute("allTourData", allTourData);
        
        return "Restaurant/restaurant_main"; 
    }

	 
}
