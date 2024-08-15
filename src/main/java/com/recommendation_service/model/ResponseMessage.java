package com.recommendation_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseMessage {
    private String message;
    private int status;
    private DataList data;
}
