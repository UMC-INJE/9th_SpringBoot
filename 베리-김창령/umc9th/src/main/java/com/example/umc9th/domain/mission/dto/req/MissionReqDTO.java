package com.example.umc9th.domain.mission.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

public class MissionReqDTO {

    @Builder
    public record CreateDTO(
            @NotNull Long storeId,
            @NotNull LocalDate deadline,
            @NotBlank String conditional,
            @NotNull Integer point
    ) { }
}
