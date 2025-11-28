package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;

public class MissionConverter {

    public static Mission toMission(MissionReqDTO.CreateDTO dto, Store store) {
        return Mission.builder()
                .deadline(dto.deadline())
                .conditional(dto.conditional())
                .point(dto.point())
                .store(store)
                .build();
    }

    public static MissionResDTO.CreateDTO toCreateDTO(Mission mission) {
        return MissionResDTO.CreateDTO.builder()
                .missionId(mission.getId())
                .storeId(mission.getStore().getId())
                .build();
    }

    public static MissionResDTO.MissionPreViewDTO toMissionPreViewDTO(Mission mission) {
        return MissionResDTO.MissionPreViewDTO.builder()
                .missionId(mission.getId())
                .deadline(mission.getDeadline())
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .build();
    }

    public static MissionResDTO.MissionPreViewListDTO toMissionPreViewListDTO(Page<Mission> result) {
        return MissionResDTO.MissionPreViewListDTO.builder()
                .missionList(result.getContent().stream()
                        .map(MissionConverter::toMissionPreViewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }
}
