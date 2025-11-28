package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.mission.entity.MemberMission;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public class MemberMissionConverter {

    public static MemberMission toEntity(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .isComplete(false)
                .build();
    }

    public static MemberMissionResDTO.CreateDTO toCreateDTO(MemberMission mm) {
        return MemberMissionResDTO.CreateDTO.builder()
                .memberMissionId(mm.getId())
                .missionId(mm.getMission().getId())
                .memberId(mm.getMember().getId())
                .build();
    }

    public static MemberMissionResDTO.MyMissionListDTO toMyMissionListDTO(Page<MemberMission> result) {
        return MemberMissionResDTO.MyMissionListDTO.builder()
                .missions(
                        result.getContent().stream()
                                .map(MemberMissionConverter::toMyMissionDTO)
                                .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static MemberMissionResDTO.MyMissionDTO toMyMissionDTO(MemberMission mm) {
        return MemberMissionResDTO.MyMissionDTO.builder()
                .memberMissionId(mm.getId())
                .missionId(mm.getMission().getId())
                .storeName(mm.getMission().getStore().getName())
                .conditional(mm.getMission().getConditional())
                .point(mm.getMission().getPoint())
                .deadline(mm.getMission().getDeadline())
                .createdAt(LocalDate.from(mm.getCreatedAt()))
                .build();
    }

    public static MemberMissionResDTO.CompleteDTO toCompleteDTO(MemberMission mm) {
        return MemberMissionResDTO.CompleteDTO.builder()
                .memberMissionId(mm.getId())
                .isComplete(mm.getIsComplete())
                .build();
    }
}
