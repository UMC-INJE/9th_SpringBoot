package com.example.umc_9th_chiki.Domain.Member.Dto;

import com.example.umc_9th_chiki.Domain.Store.Enums.Address;
import com.example.umc_9th_chiki.Domain.Member.Enums.Gender;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record JoinDTO(
            @NotBlank(message = "이름은 필수입니다.")
            String name,
            @Email(message = "이메일 형식이 아닙니다.")
            String email,
            @NotBlank(message = "비밀번호는 필수입니다.")
            String password,
            @NotNull(message = "성별은 필수입니다.")
            Gender gender,
            @NotNull(message = "생년월일은 필수입니다.")
            LocalDate birth,
            @NotNull(message = "주소는 필수입니다.")
            Address address,
            @NotNull(message = "상세 주소는 필수입니다.")
            String specAddress,
            List<Long> preferCategory
    ){}

    public record LoginDTO(
            @NotBlank(message = "이메일은 필수입니다.")
            String email,
            @NotBlank(message = "비밀번호는 필수입니다.")
            String password
    ){}
}