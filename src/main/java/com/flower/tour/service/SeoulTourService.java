package com.flower.tour.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SeoulTourService {

	//지역 코드 API 데이터 받아오기
	public List<Map<String, Object>> getAreaCode(int numOfRows, int pageNo, int areaCode) throws URISyntaxException, JsonProcessingException{
		String link = "https://apis.data.go.kr/B551011/KorService1/areaCode1";
		String MobileOS = "ETC";
		String MobileApp = "TEST";
		String _type = "json";
		String serviceKey = "G34t4KEv8WaZXw02DVg%2BQLWymgFJ%2Fxrh%2BTJZM6Cz8kZse6qoFWUcAMQqL1xfiRmCeVinKefaKFLENM1naTfzgg%3D%3D";
	
		String url = link + "?" +
			"numOfRows=" + numOfRows +
			"&pageNo=" + pageNo +
		    "&MobileOS=" + MobileOS +
		    "&MobileApp=" + MobileApp +
		    "&areaCode=" + areaCode +
		    "&_type=" + _type + 
		    "&serviceKey=" + serviceKey;
		
		URI uri = new URI(url);
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		String response = restTemplate.getForObject(uri, String.class);
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode root = objectMapper.readTree(response);
		JsonNode items = root.path("response").path("body").path("items").path("item");

		
		List<Map<String, Object>> filteredSigungu = new ArrayList<>();
		if(items.isArray()) {
			for(JsonNode item : items) {
				if(item.has("code") && item.has("name")) {
					Map<String, Object> sigunguMap = new HashMap<>();
					sigunguMap.put("code", item.get("code").asText());
					sigunguMap.put("name", item.get("name").asText());
					filteredSigungu.add(sigunguMap);
				}
			}
		}

		return filteredSigungu;
		
	}
	
	// 지역 기반 관광 정보 조회 API 데이터 받아오기
	public List<Map<String, Object>> getAreaData(int areaCode, String address, int contentTypeId,
				int numOfRows, int pageNo, int sigunguCode) throws URISyntaxException, JsonProcessingException {


		String link = "https://apis.data.go.kr/B551011/KorService1/areaBasedList1";
		String MobileOS = "ETC";
		String MobileApp = "TEST";
		String _type = "json";
		//String arrange = "R";
		String serviceKey = "G34t4KEv8WaZXw02DVg%2BQLWymgFJ%2Fxrh%2BTJZM6Cz8kZse6qoFWUcAMQqL1xfiRmCeVinKefaKFLENM1naTfzgg%3D%3D";
		
		String url = link + "?" +
			"numOfRows=" + numOfRows +
			"&pageNo=" + pageNo +
		    "&MobileOS=" + MobileOS +
		    "&MobileApp=" + MobileApp +
		    "&_type=" + _type +
		//    "&arrange" + arrange + 
		    "&contentTypeId=" + contentTypeId +
		    "&areaCode=" + areaCode +
		    "&sigunguCode=" + sigunguCode +
		    "&serviceKey=" + serviceKey;
		
		URI uri = new URI(url);

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		String response = restTemplate.getForObject(uri, String.class);
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode root = objectMapper.readTree(response);
		JsonNode items = root.path("response").path("body").path("items").path("item");
		
		List<Map<String, Object>> filteredItems = new ArrayList<>();
		if(items.isArray()) {
		for(JsonNode item : items) {
			if(item.has("addr1") && item.get("addr1").asText().contains(address)) {
				Map<String, Object> itemMap= objectMapper.convertValue(item, Map.class);
				filteredItems.add(itemMap);
			}
		}
	}

	
	return filteredItems;
	//Map<String, Object> map = new ObjectMapper().readValue(response.toString(), Map.class);
	//Map<String, Object> responseMap = (Map<String, Object>) map.get("response");
	//Map<String, Object> bodyMap = (Map<String, Object>) responseMap.get("body");
	//Map<String, Object> itemsMap = (Map<String, Object>) bodyMap.get("items");
	//List<Map<String, Object>> itemMap = (List<Map<String, Object>>) itemsMap.get("item");
	//
	////address에 있는 정보만 들고오기
	//List<Map<String, Object>> testItemMap = itemMap.stream().filter(item -> {
	//        Object value = item.get("addr1");
	//        return value != null && value.toString().contains(address);
	//    }).collect(Collectors.toList());
	
	//return testItemMap;
	}
}
