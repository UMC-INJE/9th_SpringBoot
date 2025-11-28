package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.mission.dto.res.MissionResDTO;

public interface MissionQueryService {
    MissionResDTO.MissionPreViewListDTO getMissionsByStoreLocation(String locationName, Integer page);
}
