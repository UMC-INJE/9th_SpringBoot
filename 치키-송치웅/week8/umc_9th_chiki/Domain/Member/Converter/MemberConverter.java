package com.example.umc_9th_chiki.Domain.Member.Converter;

import com.example.umc_9th_chiki.Domain.Member.Dto.MemberReqDTO;
import com.example.umc_9th_chiki.Domain.Member.Dto.MemberResDTO;
import com.example.umc_9th_chiki.Domain.Member.Entity.Member;

import java.util.ArrayList;

public class MemberConverter {

    // Entity -> DTO
    public static MemberResDTO.JoinDTO toJoinDTO(
            Member member
    ){
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createAt(member.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static Member toMember(
            MemberReqDTO.JoinDTO dto
    ){
        return Member.builder()
                .name(dto.name())
                .birth(dto.birth())
                .address(dto.address().getDescription())
                .detailAddress(dto.specAddress())
                .gender(dto.gender())
                .memberFoodList(new ArrayList<>())
                .memberTermList(new ArrayList<>())
                .reviewList(new ArrayList<>())
                /* List 초기화 이유 :
                 Member 엔티티 안에 있는 memberFoodList 등이
                 null이 되지 않도록 빈 리스트를 넣는 것이다.
                 추후 add()시 에러 방지 */
                .build();
    }
}