package com.recommendation_service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class RecommendService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${simulate-recommendation.url}")
    private String simulateRecommendationUrl;

    @Cacheable(value = "film", key = "'recommendation:' + #userId + ':' + #profileId")
    public List<Integer> getRecommendationFilms(int userId, int profileId, int limit, int offset) {
        StringBuilder url = new StringBuilder(simulateRecommendationUrl)
                .append("/film/").append(userId).append("?profileId=").append(profileId);
        List<Integer> listID = restTemplate.getForObject(url.toString(), List.class);
        int end = Math.min(offset + limit, listID.size());
        return offset < end ? listID.subList(offset, end) : List.of();
    }

    @Cacheable(value = "video", key = "'recommendation:' + #userId + ':' + #profileId")
    public List<Integer> getRecommendationVideos(int userId, int profileId, int limit, int offset) {
        StringBuilder url = new StringBuilder(simulateRecommendationUrl)
                .append("/video/").append(userId).append("?profileId=").append(profileId);
        List<Integer> listID = restTemplate.getForObject(url.toString(), List.class);
        int end = Math.min(offset + limit, listID.size());
        return offset < end ? listID.subList(offset, end) : List.of();
    }
}
