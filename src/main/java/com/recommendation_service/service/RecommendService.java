package com.recommendation_service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class RecommendService {

    @Autowired
    private RestTemplate restTemplate;

    @Cacheable(value = "film", key = "'recommendation:' + #userId + ':' + #profileId + ':' + #limit + ':' + #offset")
    public List<Integer> getRecommendationFilms(int userId, int profileId, int limit, int offset) {
        log.info(1 +"");
        String url = "http://simulate-data/recommendation/film/" + userId + "?profileId=" + profileId + "&limit=" + limit + "&offset=" + offset;
        log.info(url);
        List<Integer> filmList = restTemplate.getForObject(url, List.class);
        return filmList;
    }

    @Cacheable(value = "video",  key = "'recommendation:' + #userId + ':' + #profileId + ':' + #limit + ':' + #offset")
    public List<Integer> getRecommendationVideos(int userId, int profileId, int limit, int offset) {
        String url = "http://simulate-data/recommendation/video/" + userId + "?profileId=" + profileId + "&limit=" + limit + "&offset=" + offset;
        List<Integer> videoList = restTemplate.getForObject(url, List.class);
        return videoList;
    }
}
