package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;



    @Override
    @Transactional
    public MissionResDTO.CreateDTO createMission(MissionReqDTO.CreateDTO dto) {

        Store store = storeRepository.findById(dto.storeId())
                .orElseThrow(() -> new RuntimeException("가게 없음"));

        Mission mission = MissionConverter.toMission(dto, store);
        missionRepository.save(mission);

        return MissionConverter.toCreateDTO(mission);
    }
}

