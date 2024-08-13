package com.recommendation_service.service;

import com.recommendation_service.model.RecommendationList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RecommendService {

    @Autowired
    private RestTemplate restTemplate;

    // Todo: Chỉ trả về list chứa ID của các film/video được recommend
    @Cacheable(value = "film", keyGenerator = "customKeyGenerator")
    public RecommendationList getRecommendationFilms(int userId, int profileId, int limit, int offset) {
        RecommendationList list = new RecommendationList();
        String url = "http://simulate-data/recommendation/film/" + userId + "?profileId=" + profileId + "&limit=" + limit + "&offset=" + offset;
        List<Object> filmList = restTemplate.getForObject(url, List.class);
        list.setList(filmList);

        return list;
    }

    @Cacheable(value = "video", keyGenerator = "customKeyGenerator")
    public RecommendationList getRecommendationVideos(int userId, int profileId, int limit, int offset) {
        RecommendationList list = new RecommendationList();
        String url = "http://simulate-data/recommendation/video/" + userId + "?profileId=" + profileId + "&limit=" + limit + "&offset=" + offset;
        List<Object> filmList = restTemplate.getForObject(url, List.class);
        list.setList(filmList);

        return list;
    }
}
