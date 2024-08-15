package com.recommendation_service.service;

import com.recommendation_service.model.RelatedList;
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

    @Cacheable(value = "relatedFilm", key = "#filmId + ':' + #limit + ':' + #offset")
    public RelatedList getRelatedFilm(long filmId, long limit, long offset) {
        RelatedList list = new RelatedList();
        String url = "http://simulate-data/related/film/" + filmId + "?limit=" + limit + "&offset=" + offset;
        List<Object> content = restTemplate.getForObject(url, List.class);
        list.setList(content);

        return list;
    }

    @Cacheable(value = "relatedVideo", key = "#videoId + ':' + #limit + ':' + #offset")
    public RelatedList getRelatedVideo(long videoId, long limit, long offset) {
        RelatedList list = new RelatedList();
        String url = "http://simulate-data/related/video/" + videoId + "?limit=" + limit + "&offset=" + offset;
        List<Object> content = restTemplate.getForObject(url, List.class);
        list.setList(content);

        return list;
    }
}
