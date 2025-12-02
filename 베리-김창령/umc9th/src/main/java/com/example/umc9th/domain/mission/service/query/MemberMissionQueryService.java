package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;

public interface MemberMissionQueryService {
    MemberMissionResDTO.MyMissionListDTO getMyProgressMissions(Long memberId, Integer page);
}

