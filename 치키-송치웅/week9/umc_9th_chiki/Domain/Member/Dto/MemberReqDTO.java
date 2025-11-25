package com.example.umc_9th_chiki.Domain.Member.Dto;
import com.example.umc_9th_chiki.Domain.Member.Enums.Gender;
import com.example.umc_9th_chiki.Domain.Store.Enums.Address;
import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {
    public record JoinDTO(
            // recode형식으로 사용함.
            String name,
            Gender gender,
            LocalDate birth,
            Address address,
            String specAddress,
            List<Long> preferCategory
    ){}
}