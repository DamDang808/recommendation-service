package com.recommendation_service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class RelatedService {

    @Autowired
    private RestTemplate restTemplate;

    @Cacheable(value = "film", key = "'related:' + #filmId + ':' + #limit + ':' + #offset")
    public List<Integer> getRelatedFilm(long filmId, long limit, long offset) {
        String url = "http://simulate-data/related/film/" + filmId + "?limit=" + limit + "&offset=" + offset;
        List<Integer> content = restTemplate.getForObject(url, List.class);

        return content;
    }

    @Cacheable(value = "video", key = "'related:' + #videoId + ':' + #limit + ':' + #offset")
    public List<Integer> getRelatedVideo(long videoId, long limit, long offset) {
        String url = "http://simulate-data/related/video/" + videoId + "?limit=" + limit + "&offset=" + offset;
        List<Integer> content = restTemplate.getForObject(url, List.class);
        return content;
    }
}
