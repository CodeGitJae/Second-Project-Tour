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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.flower.tour.service.SeoulTourService;


@Controller
public class SeoulTourController {
	
	@Autowired
	private SeoulTourService stService;

    @GetMapping("/Seoul/area")
    public String getTourData(Model model) 
                              throws URISyntaxException, JsonProcessingException {
    	List<Map<String, Object>> cigunguCode = stService.getAreaCode(25, 1, 1);
        List<Map<String, Object>> allTourData = new ArrayList<>();
    	
    	for (Map<String, Object> cigungu : cigunguCode) {
    		int sigunguCode = Integer.parseInt(cigungu.get("code").toString());   	
    		String sigunguName = cigungu.get("name").toString();
    		List<Map<String, Object>> tourData = stService.getAreaData(1, "서울", 12, 1000, 1, sigunguCode);
       
    		Map<String, Object> sigunguInfo = new HashMap<>();
    		sigunguInfo.put("sigunguCode", cigunguCode);
    		sigunguInfo.put("sigunguName", sigunguName);
    		sigunguInfo.put("tourData", tourData);
    		
    		allTourData.add(sigunguInfo);
    	}
        model.addAttribute("allTourData", allTourData);
        
        System.out.println(model);
        
        return "Seoul/area"; 
    }

	 
}
