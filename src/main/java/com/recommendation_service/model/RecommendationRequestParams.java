package com.recommendation_service.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RecommendationRequestParams {
    @NotNull
    @Min(value = 1, message = "User Id must be greater than 0")
    private Integer userId;
    @NotNull
    @Min(value = 1, message = "Profile Id must be greater than 0")
    private Integer profileId;


    @NotNull
    private int zoneId;

    @NotNull
    private int contentFilter;


    @NotNull
    @Min(value = 1, message = "Limit must be greater than 0")
    private Integer limit;

    @NotNull
    @Min(value = 0, message = "Offset must be greater than or equal to 0")
    private Integer offset;
}
