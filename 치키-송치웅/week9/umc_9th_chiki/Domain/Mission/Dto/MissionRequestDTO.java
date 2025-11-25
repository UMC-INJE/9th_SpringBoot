package com.example.umc_9th_chiki.Domain.Mission.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class MissionRequestDTO {

    @Getter
    public static class ChallengeMissionDto {
        @NotNull
        private Long memberId; // "하드 코딩"으로 받을 멤버 ID
    }
}