package com.recommendation_service.model;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RelatedRequestParams {
    @NotNull
    @Min(value = 1, message = "Id must be greater than 0")
    private Integer id;

    @NotNull
    @Min(value = 1, message = "Limit must be greater than 0")
    private Integer limit;

    @NotNull
    @Min(value = 0, message = "Offset must be greater than or equal to 0")
    private Integer offset;
}
