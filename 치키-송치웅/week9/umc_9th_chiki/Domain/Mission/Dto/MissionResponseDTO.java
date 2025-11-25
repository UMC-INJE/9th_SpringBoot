package com.example.umc_9th_chiki.Domain.Mission.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResponseDTO {

    // 미션 도전 결과
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChallengeMissionResultDto {
        private Long memberMissionId;
        private LocalDateTime createdAt;
    }

    // 미션 목록 조회 응답
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreViewListDTO {
        private List<MissionPreViewDTO> missionList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }

    // 미션 단건 정보
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreViewDTO {
        private Long missionId;
        private Integer point;      // 포인트
        private String conditional; // 미션 내용
        private LocalDate deadline; // 마감기한
    }
}