package com.flower.tour.controller;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.flower.tour.service.TravelEachRecommendService;

@Controller
public class TravelEachRecommendController {

	@Autowired
	private TravelEachRecommendService terService;
	// 한 페이지당 4페이지만 출력
	private final int numOfRows = 4;
	
	@GetMapping("/category/searchdetail")
	public String showSearchDetailInfo(@RequestParam("contentId") String defaultContentId,
								@RequestParam("contentTypeId") int contentTypeId, Model model) throws JsonMappingException, JsonProcessingException, URISyntaxException{
		
		// 컨텐트아이디 int 타입으로 parsing
		int contentId = Integer.parseInt(defaultContentId);
		
		List<Map<String, Object>> getinfoDetailByCul = terService.getDetailCommonByContentid(contentTypeId, contentId);

//		System.out.println(getinfoDetailByCul);
		model.addAttribute("detailData", getinfoDetailByCul);

		return "/category/searchdetail";
	}
	
	// search 페이지
	@GetMapping("/category/search")
	public String searchKeyword(@RequestParam(value = "page", defaultValue = "1") int page,
								@RequestParam(value = "search") String defaultKeyword,
									@RequestParam(value = "contentTypeId", defaultValue = "14") int contentTypeId, Model model) 
									throws JsonProcessingException, URISyntaxException, UnsupportedEncodingException{
		
		// 한글 검색어 URL 인코딩 (문자열을 인코딩안하면 서비스 키 오류가 발생함)
	    String keyword = URLEncoder.encode(defaultKeyword, StandardCharsets.UTF_8.toString());
		
	    // 서비스에서 호출한 API 메서드 사용
		List<Map<String, Object>> searchKeyword = terService.getSerachKeyword(keyword, contentTypeId, numOfRows, page);
//		System.out.println("data= "+ recommendTour);		
		System.out.println("data= "+ searchKeyword);
		
		// 전체 페이지 수 문자열 정수로 parsing 
    	int totalCount = Integer.parseInt(searchKeyword.get(searchKeyword.size() - 1).get("totalCount").toString());
//    	System.out.println("page= "+totalCount);
    	
    	// 전체 페이지 수 계산
    	int TotalPages = (int) Math.ceil((double) totalCount / numOfRows);
    	
    	String name = "";
    	// 선택된 카테고리 표기를 위해 객체에 문자열 할당
    	if(contentTypeId == 14) {
			name = "문화시설"; 
		} else if(contentTypeId == 32) {
			name = "숙박";
		} else if(contentTypeId == 38) {
			name = "쇼핑";
		} else if(contentTypeId == 25) {
			name = "여행코스";    
		} else {
			name = "레포츠";
		}
    	
    	// 마지막 요소 값에 포함된 totalCount 객체 지우기 마지막 인덱스는 동적으로 움직이기 때문에 size() 함수 사용
    	// remove 메서드는 삭제 후 값을 반환해주는 특징이 있음.
    	searchKeyword.remove(searchKeyword.size() - 1);
    	model.addAttribute("keyword", keyword);
    	model.addAttribute("selectedName", name);
    	model.addAttribute("totalPages", TotalPages);
    	model.addAttribute("curPage", page);
		model.addAttribute("searchKeyword", searchKeyword);
		
		return "/category/search";
	}
	
	// 자세히 보기
	@GetMapping("/category/showdetail")
	public String showDetailInfo(@RequestParam("contentId") String defaultContentId,
								@RequestParam("contentTypeId") int contentTypeId, Model model) throws JsonMappingException, JsonProcessingException, URISyntaxException{
		
		// 컨텐트아이디 int 타입으로 parsing
		int contentId = Integer.parseInt(defaultContentId);
		
		List<Map<String, Object>> getinfoDetailByCul = terService.getDetailCommonByContentid(contentTypeId, contentId);

//		System.out.println(getinfoDetailByCul);
		model.addAttribute("detailData", getinfoDetailByCul);

		return "/category/showdetail";
	}
	
	
	@GetMapping("/category/travel")
	public String contentidByCategory(@RequestParam(value = "page", defaultValue = "1") int page,
									@RequestParam(value = "contentTypeId", defaultValue = "14") int contentTypeId, Model model) 
									throws JsonProcessingException, URISyntaxException{
		
		List<Map<String, Object>> recommendTour = terService.getDataByAreaBased(contentTypeId, numOfRows, page);
//		System.out.println("data= "+ recommendTour);		
		
		// 전체 페이지 수 문자열 정수로 parsing 
    	int totalCount = Integer.parseInt(recommendTour.get(recommendTour.size() - 1).get("totalCount").toString());
//    	System.out.println("page= "+totalCount);
    	
    	// 전체 페이지 수 계산
    	int TotalPages = (int) Math.ceil((double) totalCount / numOfRows);
    	
    	String name = "";
    	// 선택된 카테고리 표기를 위해 객체에 문자열 할당
    	if(contentTypeId == 14) {
			name = "문화시설"; 
		} else if(contentTypeId == 32) {
			name = "숙박";
		} else if(contentTypeId == 38) {
			name = "쇼핑";
		} else if(contentTypeId == 25) {
			name = "여행코스";    
		} else {
			name = "레포츠";
		}
    	
    	// 마지막 요소 값에 포함된 totalCount 객체 지우기 마지막 인덱스는 동적으로 움직이기 때문에 size() 함수 사용
    	// remove 메서드는 삭제 후 값을 반환해주는 특징이 있음.
    	recommendTour.remove(recommendTour.size() - 1);
    	
    	model.addAttribute("selectedName", name);
    	model.addAttribute("totalPages", TotalPages);
    	model.addAttribute("curPage", page);
		model.addAttribute("recommendTour", recommendTour);
		
		return "/category/travel";
	}
	
}
