package com.example.umc9th.domain.review.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public class ReviewReqDTO {

    @Builder
    public record CreateDTO(
            @NotNull Long storeId,
            @NotBlank String content,
            @NotNull Integer star
    ) { }
}
