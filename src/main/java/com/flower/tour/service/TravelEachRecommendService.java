package com.flower.tour.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TravelEachRecommendService {

	@Value("${apikey.tourArea}")
	private String serviceKey;
	// 지역코드: 서울
	private final int areaCode = 1;
	
	// 키워드 검색 조회 API 데이터 받아오기
	public List<Map<String, Object>> getSerachKeyword(String keyword, int contentTypeId, int numOfRows, int pageNo) 
																throws URISyntaxException, JsonProcessingException {
		
		String link = "https://apis.data.go.kr/B551011/KorService1/searchKeyword1";
		String MobileOS = "ETC";
		String MobileApp = "TEST";
		String _type = "json";
	
		String url = link + "?" +
			"numOfRows=" + numOfRows +
			"&pageNo=" + pageNo +
		    "&MobileOS=" + MobileOS +
		    "&MobileApp=" + MobileApp +
		    "&_type=" + _type +
		    "&keyword=" + keyword +
		    "&contentTypeId=" + contentTypeId +
		    "&areaCode=" + areaCode +
		    "&serviceKey=" + serviceKey;
		
		URI uri = new URI(url);
//		System.out.println(uri);
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(uri, String.class);
		
		// JSON parsing 을 위한 객체 생성
		ObjectMapper objectMapper = new ObjectMapper();
		// Map 리스트 담을 객체 생성
		List<Map<String, Object>> searchingItems = new ArrayList<>();
		
		JsonNode root = objectMapper.readTree(response);
		JsonNode body = root.path("response").path("body");
		JsonNode items = body.path("items").path("item");

		// 페이지 구성할 객체를 리스트에 추가
		if(items.isArray()) {
		for(JsonNode item : items) {
			// 컨텐트아이디 필드(key)가 있는지 확인
			if(item.has("contentid")) { 
				Map<String, Object> searchMap= new HashMap<>();
				searchMap.put("contentid", item.get("contentid").asText());
				searchMap.put("contenttypeid", item.get("contenttypeid").asText());
				searchMap.put("title", item.get("title").asText());
				searchMap.put("firstimage", item.get("firstimage").asText());
				searchMap.put("lon", item.get("mapx").asText());
				searchMap.put("lat", item.get("mapy").asText());
				
				// List 에 Map 객체 추가
				searchingItems.add(searchMap);
				}	
			}
		}
		// pagination 을 위한 객체를 리스트에 추가
		Map<String, Object> searchPage = new HashMap<>();
		if(body.has("totalCount") && body.has("numOfRows")) {
			searchPage.put("totalCount", body.get("totalCount").asText());
			searchPage.put("numOfRows", body.get("numOfRows").asText());
			searchingItems.add(searchPage);
		}
//				System.out.println(filteredItems);
		return searchingItems;
	}
	
	
	// 관광지 contentId 값에 따라 공통 정보 가져오기
	public List<Map<String, Object>> getDetailCommonByContentid(int contentTypeId, int contentId) 
						throws JsonMappingException, JsonProcessingException, URISyntaxException{
		
		String link = "https://apis.data.go.kr/B551011/KorService1/detailCommon1";
		String MobileOS = "ETC";
		String MobileApp = "TEST";
		String _type = "json";
		String info = "&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y";
		
		String url = link + "?" +
		    "&MobileOS=" + MobileOS +
		    "&MobileApp=" + MobileApp +
		    "&_type=" + _type + 
		    "&contentId=" + contentId +
		    "&contentTypeId=" + contentTypeId +
		     info +
		    "&serviceKey=" + serviceKey;
		
		URI uri = new URI(url);
//		System.out.println("uri: "+uri);
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(uri, String.class);
		
		// JSON parsing 을 위한 객체 생성
		ObjectMapper objectMapper = new ObjectMapper();
		// Map 리스트 담을 객체 생성
		List<Map<String, Object>> getDetailItem = new ArrayList<>();
		
		JsonNode root = objectMapper.readTree(response);
		JsonNode items = root.path("response").path("body").path("items").path("item");
//		System.out.println("items: "+items);
		
		if(items.isArray()) {
			for(JsonNode item : items) {
				if(item.has("contentid")) {
					Map<String, Object> itemsByContentid = new HashMap<>();
					itemsByContentid.put("title", item.get("title").asText());
					itemsByContentid.put("tel", item.get("tel").asText());
					itemsByContentid.put("homepage", item.get("homepage").asText());
					itemsByContentid.put("firstimage", item.get("firstimage").asText());
					itemsByContentid.put("addr1", item.get("addr1").asText());
					itemsByContentid.put("lon", item.get("mapx").asText());
					itemsByContentid.put("lat", item.get("mapy").asText());
					itemsByContentid.put("overview", item.get("overview").asText());
					
					// List 에 Map 객체 추가
					getDetailItem.add(itemsByContentid);
				}
			}
		}
		return getDetailItem;
	}
	
	// 지역 기반 관광 정보 조회 API 데이터 받아오기
	public List<Map<String, Object>> getDataByAreaBased(int contentTypeId, int numOfRows, int pageNo) 
														throws URISyntaxException, JsonProcessingException {
		
		String link = "https://apis.data.go.kr/B551011/KorService1/areaBasedList1";
		String MobileOS = "ETC";
		String MobileApp = "TEST";
		String _type = "json";
		
	
		String url = link + "?" +
			"numOfRows=" + numOfRows +
			"&pageNo=" + pageNo +
		    "&MobileOS=" + MobileOS +
		    "&MobileApp=" + MobileApp +
		    "&_type=" + _type +
		    "&contentTypeId=" + contentTypeId +
		    "&areaCode=" + areaCode +
		    "&serviceKey=" + serviceKey;
		
		URI uri = new URI(url);
		
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(uri, String.class);
		
		// JSON parsing 을 위한 객체 생성
		ObjectMapper objectMapper = new ObjectMapper();
		// Map 리스트 담을 객체 생성
		List<Map<String, Object>> filteredItems = new ArrayList<>();
		
		JsonNode root = objectMapper.readTree(response);
		JsonNode body = root.path("response").path("body");
		JsonNode items = body.path("items").path("item");

		// 페이지 구성할 객체를 리스트에 추가
		if(items.isArray()) {
		for(JsonNode item : items) {
			// 주소와 컨텐트아이디 필드(key)가 있는지 확인
			if(item.has("addr1") && item.has("contentid")) { 
				Map<String, Object> itemMap= new HashMap<>();
				itemMap.put("contentid", item.get("contentid").asText());
				itemMap.put("contenttypeid", item.get("contenttypeid").asText());
				itemMap.put("firstimage", item.get("firstimage").asText());
				itemMap.put("addr1", item.get("addr1").asText());
				itemMap.put("title", item.get("title").asText());
				itemMap.put("title", item.get("title").asText());
				
				// List 에 Map 객체 추가
				filteredItems.add(itemMap);
				}	
			}
		}
			// pagination 을 위한 객체를 리스트에 추가
			Map<String, Object> page = new HashMap<>();
			if(body.has("totalCount") && body.has("numOfRows")) {
				page.put("totalCount", body.get("totalCount").asText());
				page.put("numOfRows", body.get("numOfRows").asText());
				filteredItems.add(page);
			}

//			System.out.println(filteredItems);
		return filteredItems;
	}
	
	
}
