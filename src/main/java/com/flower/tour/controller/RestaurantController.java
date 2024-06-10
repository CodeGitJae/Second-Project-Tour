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
	public List<Map<String, Object>> getRestaurantDataBySigungu(@PathVariable("selectedSigungu") int sigunguCode)
										throws URISyntaxException, JsonProcessingException{		
		System.out.println(sigunguCode);
		// 해당 지역 코드를 기반으로 해당 지역의 음식점 정보 조회
		List<Map<String, Object>> restaurantData = rtrService.getRestaurantData(1, "서울", 39, 20, 1, sigunguCode);
		
		// 조회된 음식점 정보를 모델에 담아서 jsp 페이지로 전달
		return restaurantData; 
	}

    @GetMapping("/Restaurant/area")
    public String getTourData(Model model) 
                              throws URISyntaxException, JsonProcessingException {
    	List<Map<String, Object>> sigunguCode = rtrService.getRestaurantAreaCode(30, 1, 1);
        List<Map<String, Object>> allrestaurantData = new ArrayList<>();
    	
    	for (Map<String, Object> sigungu : sigunguCode) {
    		int outerRestaurantCode = Integer.parseInt(sigungu.get("code").toString());   	
    		String sigunguName = sigungu.get("name").toString();
    		List<Map<String, Object>> restaurantData = rtrService.getRestaurantData(1, "서울", 39, 20, 1, outerRestaurantCode);
       
    		Map<String, Object> sigunguInfo = new HashMap<>();
    		sigunguInfo.put("sigunguCode", outerRestaurantCode);
    		sigunguInfo.put("sigunguName", sigunguName);
    		sigunguInfo.put("restaurantData", restaurantData);
    		
    		allrestaurantData.add(sigunguInfo);
    	}
        model.addAttribute("allrestaurantData", allrestaurantData);
        
        return "Restaurant/restaurant_main"; 
    }

	 
}
