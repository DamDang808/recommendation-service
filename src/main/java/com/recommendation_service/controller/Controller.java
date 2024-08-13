package com.recommendation_service.controller;

import com.recommendation_service.model.RecommendationList;
import com.recommendation_service.model.RequestParams;
import com.recommendation_service.model.ResponseMessage;
import com.recommendation_service.service.RecommendService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendation")
public class Controller {

    @Autowired
    private RecommendService recommendService;

    @Autowired
    private Environment env;

//    @RequestMapping("/")
//    public String home() {
//        // This is useful for debugging
//        // When having multiple instance of gallery service running at different ports.
//        // We load balance among them, and display which instance received the request.
//        return "Hello from Recommendation Service running at port: " + env.getProperty("local.server.port");
//    }

    @GetMapping(value = "/{type}")
    public ResponseEntity<ResponseMessage> getRecommendation(
            @PathVariable String type,
            @Valid @RequestBody RequestParams request) {
        RecommendationList recommendationList;
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


