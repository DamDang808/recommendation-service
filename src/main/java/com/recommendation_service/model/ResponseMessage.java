package com.recommendation_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ResponseMessage {
    private String message;
    private int status;
    private List<Integer> data;
}
