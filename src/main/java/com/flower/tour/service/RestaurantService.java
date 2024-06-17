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
public class RestaurantService {

	// 위치 기반 음식점 정보 조회 API 데이터 받아오기
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getRestaurantData(int areaCode, String address, int contentTypeId, int numOfRows,
			int pageNo, int sigunguCode) throws URISyntaxException, JsonProcessingException {

		String link = "https://apis.data.go.kr/B551011/KorService1/areaBasedList1";
		String MobileOS = "ETC";
		String MobileApp = "TEST";
		String _type = "json";
		String serviceKey = "TI1oFdCS1SPKKvp9WcBKVs8y7gKPOoRQKZDXzGfDsPsRXl9oleYMNRi%2BSU2am5ee%2BA02b3iUX2qKzGoec7xNAQ%3D%3D";

		String url = link + "?" + "numOfRows=" + numOfRows + "&pageNo=" + pageNo + "&MobileOS=" + MobileOS
				+ "&MobileApp=" + MobileApp + "&_type=" + _type + "&contentTypeId=" + contentTypeId + "&areaCode="
				+ areaCode + "&serviceKey=" + serviceKey;
		
		if(sigunguCode != 0)
			url += "&sigunguCode=" + sigunguCode;

		URI uri = new URI(url);

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		String response = restTemplate.getForObject(uri, String.class);

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode root = objectMapper.readTree(response);
		JsonNode items = root.path("response").path("body").path("items").path("item");
		JsonNode items2 = root.path("response").path("body");
		
		Map<String, Object> p = new HashMap<>();
		p.put("totalCount", items2.path("totalCount").asText());

		List<Map<String, Object>> filteredInfo = new ArrayList<>();
		if (items.isArray()) {
			for (JsonNode item : items) {
				if (item.has("addr1") && item.get("addr1").asText().contains(address)) {
					Map<String, Object> itemMap = objectMapper.convertValue(item, Map.class);
					filteredInfo.add(itemMap);
				}
			}
		}
		filteredInfo.add(p);
		
		return filteredInfo;
	}

	public List<Map<String, Object>> restaurantInfo(int contentId)
			throws URISyntaxException, JsonProcessingException {
		String link = "https://apis.data.go.kr/B551011/KorService1/detailCommon1";
		String MobileOS = "ETC";
		String MobileApp = "TEST";
		String _type = "json";
		int contentTypeId = 39;
		String info = "&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y";

		String serviceKey = "TI1oFdCS1SPKKvp9WcBKVs8y7gKPOoRQKZDXzGfDsPsRXl9oleYMNRi%2BSU2am5ee%2BA02b3iUX2qKzGoec7xNAQ%3D%3D";

		
		String url = link + "?" +
		    "&MobileOS=" + MobileOS +
		    "&MobileApp=" + MobileApp +
		    "&_type=" + _type + 
		    "&contentId=" + contentId +
		    "&contentTypeId=" + contentTypeId +
		     info +
		    "&serviceKey=" + serviceKey;
		
		System.out.println(url);
		
		URI uri = new URI(url);
//		System.out.println("uri: "+uri);
		RestTemplate restTemplate = new RestTemplate();
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		String response = restTemplate.getForObject(uri, String.class);
//		System.out.println("response: "+response);
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode root = objectMapper.readTree(response);
		JsonNode items = root.path("response").path("body").path("items").path("item");
//		System.out.println("items: "+items);
		List<Map<String, Object>> getDetailItem = new ArrayList<>();
		
		if(items.isArray()) {
			for(JsonNode item : items) {
				if(item.has("contentid")) {
					Map<String, Object> itemsByContentid = new HashMap<>();
					itemsByContentid.put("title", item.get("title").asText());
//					itemsByContentid.put("tel", item.get("tel").asText());
					itemsByContentid.put("homepage", item.get("homepage").asText());
					itemsByContentid.put("firstimage", item.get("firstimage").asText());
					itemsByContentid.put("addr1", item.get("addr1").asText());
					itemsByContentid.put("lon", item.get("mapx").asText());
					itemsByContentid.put("lat", item.get("mapy").asText());
					itemsByContentid.put("overview", item.get("overview").asText());
					getDetailItem.add(itemsByContentid);
				}
			}
		}
//		System.out.println(getContentId);
		return getDetailItem;
	}
	
	public List<Map<String, Object>> getSigunguCodeList() {
		List<Map<String, Object>> list = new ArrayList<>();
		String[] data = {"강남", "강동", "강북", "강서", "관악", "광진", "구로", "금천", "노원", "도봉",
				"동대문", "동작", "마포", "서대문", "서초", "성동", "성북", "송파", "양천", "영등포", "용산", "은평", "종로", "중", "중랑"};
		
		for(int i=1; i<=25; i++) {
			Map<String, Object> m = new HashMap<>();
			m.put("code", i);
			m.put("name", data[i-1]+"구");
			list.add(m);
		}
		
		return list;
	}

}
