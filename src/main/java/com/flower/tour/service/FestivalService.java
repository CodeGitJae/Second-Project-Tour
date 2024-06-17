package com.flower.tour.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FestivalService {

//	메인
	public List<Map<String, Object>> festivalItems(int eventstartdate, int numOfRows, int pageNo)
			throws URISyntaxException, JsonProcessingException {
		String link = "http://apis.data.go.kr/B551011/KorService1/searchFestival1?";
		String MobileOS = "ETC";
		String MobileApp = "TEST";
		String _type = "json";
		String listYN = "Y";
		String arrange = "O";
		int areaCode = 1;
		String serviceKey = "TI1oFdCS1SPKKvp9WcBKVs8y7gKPOoRQKZDXzGfDsPsRXl9oleYMNRi%2BSU2am5ee%2BA02b3iUX2qKzGoec7xNAQ%3D%3D";

		String url = link + "numOfRows=" + numOfRows + "&pageNo=" + pageNo + "&MobileOS=" + MobileOS + "&MobileApp="
				+ MobileApp + "&_type=" + _type + "&listYN=" + listYN + "&arrange=" + arrange + "&eventStartDate="
				+ eventstartdate + "&areaCode=" + areaCode + "&serviceKey=" + serviceKey;

		URI uri = new URI(url);
		System.out.println(uri);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		String response = restTemplate.getForObject(uri, String.class);
//		System.out.println(":::::::::::::::::::" + response);

		ObjectMapper objectMapper = new ObjectMapper();

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		JsonNode root = objectMapper.readTree(response);
		JsonNode body = root.path("response").path("body");
		JsonNode items = body.path("items").path("item");

		if (items.isArray()) {
			for (JsonNode item : items) {
				Map<String, Object> map = new HashMap<>();
				map.put("contentid", item.path("contentid").asText());
				map.put("title", item.path("title").asText());
				map.put("addr1", item.path("addr1").asText());
				map.put("addr2", item.path("addr2").asText());
				map.put("tel", item.path("tel").asText());

				String startDate = item.path("eventstartdate").asText();
				String startMonth = startDate.substring(4, 6);
				startDate = startDate.substring(0, 4) + "-" + startDate.substring(4, 6) + "-" + startDate.substring(6);
				map.put("eventstartdate", startDate);
				map.put("startMonth", startMonth);

				String endDate = item.path("eventenddate").asText();
				String endMonth = endDate.substring(4, 6);
				endDate = endDate.substring(0, 4) + "-" + endDate.substring(4, 6) + "-" + endDate.substring(6);
				map.put("eventenddate", endDate);
				map.put("endMonth", endMonth);

				map.put("firstimage", item.path("firstimage").asText());
				map.put("areacode", item.path("areacode").asText());

				result.add(map);
			}
		}

		Map<String, Object> map = new HashMap<>();
		map.put("numOfRows", body.path("numOfRows").asText());
		map.put("totalCount", body.path("totalCount").asText());

		result.add(map);
		System.out.println("result"+result);
		
		return result;
	}
	
	
//	상세페이지
	public List<Map<String, Object>> festivalInfo(int numOfRows, int pageNo, int contentId)
			throws URISyntaxException, JsonProcessingException {
		String link = "https://apis.data.go.kr/B551011/KorService1/detailCommon1?";
		String MobileOS = "ETC";
		String MobileApp = "TEST";
		String _type = "json";
		int contentTypeId = 15;
		String info = "&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y";

		String serviceKey = "TI1oFdCS1SPKKvp9WcBKVs8y7gKPOoRQKZDXzGfDsPsRXl9oleYMNRi%2BSU2am5ee%2BA02b3iUX2qKzGoec7xNAQ%3D%3D";

		String url = link + "numOfRows=" + numOfRows + "&pageNo=" + pageNo + "&MobileOS=" + MobileOS + "&MobileApp="
				+ MobileApp + "&_type=" + _type + "&contentId=" + contentId + "&contentTypeId=" + contentTypeId + info
				+ "&serviceKey=" + serviceKey;

		URI uri = new URI(url);
		System.out.println(uri);

		RestTemplate restTemplate = new RestTemplate();

		String response = restTemplate.getForObject(uri, String.class);
		System.out.println(":::::::::::::::::::response" + response);

		ObjectMapper objectMapper = new ObjectMapper();

		List<Map<String, Object>> infoResult = new ArrayList<>();

		JsonNode root = objectMapper.readTree(response);
		JsonNode items = root.path("response").path("body").path("items").path("item");
		System.out.println(items);

		if (items.isArray()) {
			for (JsonNode item : items) {
				Map<String, Object> map = new HashMap<>();
				map.put("contentid", item.path("contentid").asText());
				map.put("title", item.path("title").asText());
				map.put("addr1", item.path("addr1").asText());
				map.put("addr2", item.path("addr2").asText());
				map.put("tel", item.path("tel").asText());
				map.put("telname", item.path("telname").asText());
				map.put("homepage", item.path("homepage").asText());
				map.put("zipcode", item.path("zipcode").asText());
				map.put("areacode", item.path("areacode").asText());
				map.put("lon", item.path("mapx").asText());
				map.put("lat", item.path("mapy").asText());
				map.put("overview", item.path("overview").asText());
				map.put("firstimage", item.path("firstimage").asText());

				infoResult.add(map);
			}
		}

//		for (Map<String, Object> r : result) {
//			System.out.println("title:" + r.get("title"));
//			System.out.println("addr1:" + r.get("addr1"));
//			System.out.println("addr2:" + r.get("addr2"));
//			System.out.println("tel:" + r.get("tel"));
//			System.out.println("eventstartdate:" + r.get("eventstartdate"));
//			System.out.println("eventenddate:" + r.get("eventenddate"));
//			System.out.println("firstimage:" + r.get("firstimage"));
//			System.out.println("areacode:" + r.get("areacode"));
//			System.out.println("---------------------");
//		}

		return infoResult;
	}
	
	public List<Map<String, Object>> getMonthList(List<Map<String, Object>> festivalList, Integer searchMonth, int page) {
		// 필터링된 리스트를 저장할 새로운 리스트 생성
		List<Map<String, Object>> filterMonthList = new ArrayList<>();
	
		for (Map<String, Object> festival : festivalList) {
			//
			int ftvstratMonth = Integer.parseInt((String) festival.get("startMonth"));
			int ftvendMonth = Integer.parseInt((String) festival.get("endMonth"));
	//					System.out.println(":::::::::::::::ftvstratMonth"+ftvstratMonth);
	//					System.out.println(":::::::::::::::ftvendMonth"+ftvendMonth);
			if (ftvstratMonth <= searchMonth && ftvendMonth >= searchMonth) {
				filterMonthList.add(festival);
			}
			
			
			System.out.println(":::::::::::::::filterMonthList" + filterMonthList);
		}
		
		List<Map<String, Object>> r = new ArrayList<>();
		System.out.println("============"+filterMonthList.size());
		System.out.println("============"+page);
		
		for(int i=(page-1)*12; i<filterMonthList.size(); i++) {
			try {
				if(i == page*12)
					break;
				
				r.add(filterMonthList.get(i));
				
			} catch (Exception e) {
				break;
			}
		}
		
		return r;
	}
	

}
