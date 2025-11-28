package com.example.umc9th.domain.mission.dto.res;

import lombok.Builder;

public class MissionResDTO {

    @Builder
    public record CreateDTO(
            Long missionId,
            Long storeId
    ) { }

    @Builder
    public record MissionPreViewDTO(
            Long missionId,
            java.time.LocalDate deadline,
            String conditional,
            Integer point
    ) { }

    @Builder
    public record MissionPreViewListDTO(
            java.util.List<MissionPreViewDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
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
}
