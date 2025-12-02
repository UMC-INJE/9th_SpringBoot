package com.example.umc9th.domain.mission.dto.res;

import lombok.Builder;

public class MemberMissionResDTO {

    @Builder
    public record CreateDTO(
            Long memberMissionId,
            Long missionId,
            Long memberId
    ) { }

    @Builder
    public record MyMissionListDTO(
            java.util.List<MyMissionDTO> missions,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) { }

    @Builder
    public record MyMissionDTO(
            Long memberMissionId,
            Long missionId,
            String storeName,
            String conditional,
            Integer point,
            java.time.LocalDate deadline,
            java.time.LocalDate createdAt
    ) { }

    @Builder
    public record CompleteDTO(
            Long memberMissionId,
            Boolean isComplete
    ) { }
}
