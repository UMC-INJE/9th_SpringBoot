package com.example.umc_9th_chiki.Domain.Member.Dto;

import lombok.*;
import java.time.LocalDateTime;

public class MemberResDTO {

    @Builder
    public record JoinDTO(
            Long memberId,
            String accessToken,
            LocalDateTime createAt
    ){}
    @Builder
    public record LoginDTO(
            Long memberId,
            String accessToken,
            LocalDateTime loginAt
    ){}
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginResultDTO {
        String accessToken;
    }
}
