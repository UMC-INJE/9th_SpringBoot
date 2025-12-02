package com.example.umc_9th_chiki.Domain.Member.Dto;

import lombok.*;
import java.time.LocalDateTime;

public class MemberResDTO {

    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createAt
    ){}
}
