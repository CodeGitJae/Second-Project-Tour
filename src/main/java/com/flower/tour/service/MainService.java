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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MainService {

	@Value("${apikey.tour}")
    private String tourApikey;
	
	public List<Map<String, Object>> getLocationBasedList(String lat, String lon) throws URISyntaxException {
		
		String url = "https://apis.data.go.kr/B551011/KorService1/locationBasedList1";
		String mapX = lon;
		String mapY = lat;
		String radius = "10000";
		String row = "10";
		
		url = url + "?serviceKey=" + tourApikey + 
				"&numOfRows=" + row + 
				"&pageNo=1&MobileOS=ETC&MobileApp=AppTest&arrange=S&mapX=" + mapX + 
				"&mapY=" + mapY + 
				"&radius=" + radius + "&listYN=Y&_type=json&contentTypeId=12";
		
		System.out.println(url);
		
		URI uri = new URI(url);
		
		// RestTemplate 사용
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(uri, String.class);

        // 응답을 출력
        System.out.println("Response: " + response);
        
        List<Map<String, Object>> result = parseLocationBasedList(response);

        // 객체 잘 매핑된지 확인용 출력문
//        for (Map<String, Object> item : result) {
//            System.out.println("title: " + item.get("title"));
//            System.out.println("addr1: " + item.get("addr1"));
//            System.out.println("addr2: " + item.get("addr2"));
//            System.out.println("tel: " + item.get("tel"));
//            System.out.println("dist: " + item.get("dist"));
//            System.out.println("firstimage: " + item.get("firstimage"));
//            System.out.println("cat3: " + item.get("cat3"));
//            System.out.println("----------------------");
//        }
        
        // 결과 10개 중 5개만 리턴
        return result.subList(0, 5);
	}
	
	private List<Map<String, Object>> parseLocationBasedList(String response) {
		
		// JSON 파싱
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> result = new ArrayList<>();
        
        try {
	        JsonNode rootNode = objectMapper.readTree(response);
	        JsonNode itemsNode = rootNode.path("response").path("body").path("items").path("item");
	        if (itemsNode.isArray()) {
	            for (JsonNode itemNode : itemsNode) {
	                Map<String, Object> map = new HashMap<>();
	                map.put("title", itemNode.path("title").asText());
	                map.put("addr1", itemNode.path("addr1").asText());
	                map.put("addr2", itemNode.path("addr2").asText());
	                map.put("tel", itemNode.path("tel").asText());
	                map.put("dist", itemNode.path("dist").asText());
	                map.put("firstimage", itemNode.path("firstimage").asText());
	                map.put("cat3", itemNode.path("cat3").asText());
	               
	                result.add(map);
	            }
	        }
        } catch (Exception e) {
			e.printStackTrace();
		}
                
        return result;
	}
	
}
