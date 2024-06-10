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
	
	private final int areaCode = 1;
	private final int contentTypeId = 12;
	private final int numOfRows = 8;
	
	@GetMapping("/Seoul/area/all")
	@ResponseBody
	public List<Map<String, Object>> getSelectedBySeoul()
										throws URISyntaxException, JsonProcessingException{		
//		List<Map<String, Object>> sigunguCode = stService.getAreaCode(25, 1, 1);
     	List<Map<String, Object>> tourData = stService.getAreaData(areaCode, contentTypeId, numOfRows, 1, 0);
    		
    	return tourData;
    	
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
		List<Map<String, Object>> tourData = stService.getAreaData(areaCode, contentTypeId, numOfRows, 1, sigunguCode);
		List<Map<String, Object>> totalCount = stService.getTotalCount(areaCode, contentTypeId, numOfRows, 1, sigunguCode);
    	System.out.println(totalCount);
		
		return tourData;
	}

    @GetMapping("/Seoul/area")
    public String getAllTourData(Model model) 
                              throws URISyntaxException, JsonProcessingException {
    	List<Map<String, Object>> sigunguCode = stService.getAreaCode(25, 1, 1);
    	List<Map<String, Object>> tourData = stService.getAreaData(areaCode, contentTypeId, numOfRows, 1, 0);
    	List<Map<String, Object>> totalCount = stService.getTotalCount(areaCode, contentTypeId, numOfRows, 1, 0);
    	System.out.println(totalCount);
    	
    	model.addAttribute("sigunguCode", sigunguCode);
        model.addAttribute("tourData", tourData);
        
        return "Seoul/area"; 
    }

	 
}
 	
    	//   	getSelectedBySigungu 클래스 처음 구현 했던 코드 
//        List<Integer> pageCount = new ArrayList<>();
        
//    	for (Map<String, Object> sigungu : sigunguCode) {
//    		int outerSigunguCode = Integer.parseInt(sigungu.get("code").toString());   	
//    		String sigunguName = sigungu.get("name").toString();
////    		int totalCount = Integer.parseInt(sigungu.get("totalCount").toString());
////    		int totalPages = totalCount / numOfRows;
////    		for (int pageNo = 1; pageNo <= totalPages; pageNo++) {
////    				pageCount.add(pageNo);
////    		}
//    		List<Map<String, Object>> tourData = stService.getAreaData(areaCode, contentTypeId, numOfRows, 1, outerSigunguCode);
//       
//    		Map<String, Object> sigunguInfo = new HashMap<>();
//    		sigunguInfo.put("sigunguCode", outerSigunguCode);
//    		sigunguInfo.put("sigunguName", sigunguName);
//    		sigunguInfo.put("tourData", tourData);
//    		
//    		allTourData.add(sigunguInfo);
//    	}
//        model.addAttribute("allTourData", allTourData);
        
