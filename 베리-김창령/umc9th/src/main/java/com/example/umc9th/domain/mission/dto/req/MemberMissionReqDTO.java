package com.example.umc9th.domain.mission.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public class MemberMissionReqDTO {

    @Builder
    public record CreateDTO(
            @NotNull Long missionId
    ) {}
}
