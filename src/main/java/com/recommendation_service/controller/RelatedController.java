package com.recommendation_service.controller;

import com.recommendation_service.model.RelatedRequestParams;
import com.recommendation_service.model.ResponseMessage;
import com.recommendation_service.service.RelatedService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/related")
public class RelatedController {

    @Autowired
    private RelatedService relatedService;

    @PostMapping(value = "/{type}")
    public ResponseEntity<ResponseMessage> getRelated(
            @PathVariable("type") String type,
            @Valid @RequestBody RelatedRequestParams requestParams) {
        log.info("1");
        List<Integer> relatedList;
        if ("film".equals(type)) {
            relatedList = relatedService.getRelatedFilm(requestParams.getId(),
                    requestParams.getLimit(),
                    requestParams.getOffset());
        } else if ("video".equals(type)) {
            relatedList = relatedService.getRelatedVideo(requestParams.getId(),
                    requestParams.getLimit(),
                    requestParams.getOffset());
        } else {
            return ResponseEntity.badRequest().body(
                    ResponseMessage.builder()
                            .message("Invalid type")
                            .status(HttpStatus.BAD_REQUEST.value())
                            .build()
            );
        }

        if (relatedList == null) {
            return ResponseEntity.notFound().build();
        }

        ResponseMessage responseMessage = ResponseMessage.builder()
                .message("Success")
                .status(HttpStatus.OK.value())
                .data(relatedList)
                .build();

        return ResponseEntity.ok(responseMessage);
    }
}
