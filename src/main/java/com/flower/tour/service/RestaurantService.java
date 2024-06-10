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
    //지역 코드 API 데이터 받아오기
	


    public List<Map<String, Object>> getRestaurantAreaCode(int numOfRows, int pageNo, int areaCode) throws URISyntaxException, JsonProcessingException {
        String link = "https://apis.data.go.kr/B551011/KorService1/areaCode1";
        String MobileOS = "ETC";
        String MobileApp = "TEST";
        String _type = "json";
        String serviceKey = "7I86%2BkwZRg7drfjl1VYsPjf87SUYpH9C8qiinq4yGhtdvzKDP26bRezIP%2FKNbTkTeKerSADF3S0Pxsllv9lS4w%3D%3D";

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

        List<Map<String, Object>> filteredRegion = new ArrayList<>();
        if (items.isArray()) {
            for (JsonNode item : items) {
                if (item.has("code") && item.has("name")) {
                    Map<String, Object> dregionMap = new HashMap<>();
                    dregionMap.put("code", item.get("code").asText());
                    dregionMap.put("name", item.get("name").asText());
                    filteredRegion.add(dregionMap);
                }
            }
        }

        return filteredRegion;
    }
    
    

    // 위치 기반 음식점 정보 조회 API 데이터 받아오기
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getRestaurantData(int areaCode, String address, int contentTypeId, int numOfRows, int pageNo, int sigunguCode)
            throws URISyntaxException, JsonProcessingException {

        String link = "https://apis.data.go.kr/B551011/KorService1/areaBasedList1";
        String MobileOS = "ETC";
        String MobileApp = "TEST";
        String _type = "json";
        String serviceKey = "7I86%2BkwZRg7drfjl1VYsPjf87SUYpH9C8qiinq4yGhtdvzKDP26bRezIP%2FKNbTkTeKerSADF3S0Pxsllv9lS4w%3D%3D";

        String url = link + "?" +
            "numOfRows=" + numOfRows +
            "&pageNo=" + pageNo +
            "&MobileOS=" + MobileOS +
            "&MobileApp=" + MobileApp +
            "&_type=" + _type +
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

        List<Map<String, Object>> filteredInfo = new ArrayList<>();
        if (items.isArray()) {
            for (JsonNode item : items) {
                if (item.has("addr1") && item.get("addr1").asText().contains(address)) {
                    Map<String, Object> itemMap = objectMapper.convertValue(item, Map.class);
                    filteredInfo.add(itemMap);
                }
            }
        }

        return filteredInfo;
    }

}
