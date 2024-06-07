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
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.flower.tour.service.SeoulTourService;


@Controller
public class SeoulTourController {
	
	@Autowired
	private SeoulTourService stService;
	
	@GetMapping("/Seoul/area/seoul")
	@ResponseBody
	public List<Map<String, Object>> getSelectedBySeoul()
										throws URISyntaxException, JsonProcessingException{		
		List<Map<String, Object>> sigunguCode = stService.getAreaCode(25, 1, 1);
        List<Map<String, Object>> allTourData = new ArrayList<>();
    	
    	for (Map<String, Object> sigungu : sigunguCode) {
    		int outerSigunguCode = Integer.parseInt(sigungu.get("code").toString());   	
    		List<Map<String, Object>> tourData = stService.getAreaData(1, "서울", 12, 1000, 1, outerSigunguCode);
    		
    		allTourData.addAll(tourData);
    	}
    	return allTourData;
    	
//		List<List<Map<String, Object>>> tourData = new ArrayList<>();
//		
//		for (int sigunguCode = 1; sigunguCode < 26; sigunguCode++) {
//			List<Map<String, Object>> list = stService.getAreaData(1, "서울", 12, 1000, 1, sigunguCode);
//			tourData.add(list);
//		}
//		return tourData; 
	}
	
	@GetMapping("/Seoul/area/{selectedSigungu}")
	@ResponseBody
	public List<Map<String, Object>> getSelectedBySigungu(@PathVariable("selectedSigungu") int sigunguCode)
										throws URISyntaxException, JsonProcessingException{		

		// 해당 지역 코드를 기반으로 해당 지역의 관광 정보 조회
		List<Map<String, Object>> tourData = stService.getAreaData(1, "서울", 12, 1000, 1, sigunguCode);
		
		return tourData; 
	}

    @GetMapping("/Seoul/area")
    public String getAllTourData(Model model) 
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
