package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;

    @Override
    public MissionResDTO.MissionPreViewListDTO getMissionsByStoreLocation(String locationName, Integer page) {

        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        Page<Mission> result = missionRepository
                .findByStore_Location_NameAndDeadlineGreaterThanEqualOrderByCreatedAtDesc(
                        locationName,
                        LocalDate.now(),
                        pageRequest
                );

        return MissionConverter.toMissionPreViewListDTO(result);
    }
}
