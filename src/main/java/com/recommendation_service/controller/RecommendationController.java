package com.recommendation_service.controller;

import com.recommendation_service.model.RecommendationRequestParams;
import com.recommendation_service.model.ResponseMessage;
import com.recommendation_service.service.RecommendService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/recommendation")
public class RecommendationController {

    @Autowired
    private RecommendService recommendService;

    @Autowired
    private Environment env;

    @RequestMapping(value = "/{type}")
    public ResponseEntity<ResponseMessage> getRecommendation(
            @PathVariable String type,
            @Valid @RequestBody RecommendationRequestParams request) {
        List<Integer> recommendationList;

        if ("film".equals(type)) {
            recommendationList = recommendService.getRecommendationFilms(
                    request.getUserId(),
                    request.getProfileId(),
                    request.getLimit(),
                    request.getOffset());
        } else if ("video".equals(type)) {
            recommendationList = recommendService.getRecommendationVideos(
                    request.getUserId(),
                    request.getProfileId(),
                    request.getLimit(),
                    request.getOffset());
        } else {
            return ResponseEntity.badRequest().body(
                    ResponseMessage.builder()
                            .message("Invalid type")
                            .status(400)
                            .build()
            );
        }
        if (recommendationList == null) {
            return ResponseEntity.notFound().build();
        }

        ResponseMessage responseMessage = ResponseMessage.builder()
                .message("Success")
                .status(200)
                .data(recommendationList)
                .build();

        return ResponseEntity.ok(responseMessage);
    }
}


