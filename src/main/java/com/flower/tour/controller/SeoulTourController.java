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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flower.tour.service.SeoulTourService;


@Controller
public class SeoulTourController {
	
	@Autowired
	private SeoulTourService stService;
	
	
	@GetMapping("/Seoul/area/{sigunguCode}")
	public String getTourDataBySigungu(@PathVariable int sigunguCode, Model model)
										throws URISyntaxException, JsonProcessingException{
		// 해당 지역 코드를 기반으로 해당 지역의 관광 정보 조회
		List<Map<String, Object>> tourData = stService.getAreaData(1, "서울", 12, 1000, 1, sigunguCode);
		
		// 조회된 관광 정보를 모델에 담아서 jsp 페이지로 전달
		model.addAttribute("allTourData", tourData);
		
		return "Seoul/area";
	}

    @GetMapping("/Seoul/area")
    public String getTourData(Model model) 
                              throws URISyntaxException, JsonProcessingException {
    	List<Map<String, Object>> sigunguCode = stService.getAreaCode(25, 1, 1);
        List<Map<String, Object>> allTourData = new ArrayList<>();
    	
    	for (Map<String, Object> sigungu : sigunguCode) {
    		int outerSigunguCode = Integer.parseInt(sigungu.get("code").toString());   	
    		String sigunguName = sigungu.get("name").toString();
    		List<Map<String, Object>> tourData = stService.getAreaData(1, "서울", 12, 1000, 1, outerSigunguCode);
       
    		Map<String, Object> sigunguInfo = new HashMap<>();
    		sigunguInfo.put("sigunguCode", outerSigunguCode);
    		sigunguInfo.put("sigunguName", sigunguName);
    		sigunguInfo.put("tourData", tourData);
    		
    		allTourData.add(sigunguInfo);
    	}
        model.addAttribute("allTourData", allTourData);
        
        return "Seoul/area"; 
    }

	 
}
