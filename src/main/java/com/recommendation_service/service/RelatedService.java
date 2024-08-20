package com.recommendation_service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class RelatedService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${simulate-related.url}")
    private String simulateRelatedUrl;

    @Cacheable(value = "film", key = "'related:' + #filmId")
    public ArrayList<Integer> getRelatedFilm(int filmId, int limit, int offset) {
        StringBuilder url = new StringBuilder(simulateRelatedUrl).append("/film/").append(filmId);
        ArrayList<Integer> listID = restTemplate.getForObject(url.toString(), ArrayList.class);
        int end = Math.min(offset + limit, listID.size());
        return offset < end ? new ArrayList<>(listID.subList(offset, end)) : null;
    }

    @Cacheable(value = "video", key = "'related:' + #videoId")
    public ArrayList<Integer> getRelatedVideo(int videoId, int limit, int offset) {
        StringBuilder url = new StringBuilder(simulateRelatedUrl).append("/video/").append(videoId);
        ArrayList<Integer> listID = restTemplate.getForObject(url.toString(), ArrayList.class);
        int end = Math.min(offset + limit, listID.size());
        return offset < end ? new ArrayList<>(listID.subList(offset, end)) : null;
    }
}
