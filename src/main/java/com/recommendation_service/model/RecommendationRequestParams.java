package com.recommendation_service.model;

import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;
import jakarta.validation.constraints.NotNull;


@Builder
@Data
public class RecommendationRequestParams {
    @NotNull
    @Min(value = 1, message = "User Id must be greater than 0")
    private Integer userId;
    @NotNull
    @Min(value = 1, message = "Profile Id must be greater than 0")
    private Integer profileId;

    @NotNull
    @Min(value = 1, message = "Limit must be greater than 0")
    private Integer limit;

    @NotNull
    @Min(value = 0, message = "Offset must be greater than or equal to 0")
    private Integer offset;
}
