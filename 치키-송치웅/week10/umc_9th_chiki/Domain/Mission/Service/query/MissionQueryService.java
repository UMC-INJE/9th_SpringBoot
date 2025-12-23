package com.example.umc_9th_chiki.Domain.Mission.Service.query;

import com.example.umc_9th_chiki.Domain.Mission.Converter.MissionConverter;
import com.example.umc_9th_chiki.Domain.Mission.Dto.MissionResponseDTO;
import com.example.umc_9th_chiki.Domain.Mission.Entity.Mission;
import com.example.umc_9th_chiki.Domain.Store.Entity.Store;
import com.example.umc_9th_chiki.Domain.Mission.Repository.MissionRepository;
import com.example.umc_9th_chiki.Domain.Store.Repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    public MissionResponseDTO.MissionPreViewListDTO getMissionList(Long storeId, Integer page) {

        // 가게 확인
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));

        // 미션 조회 (10개씩 페이징)
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Mission> missionList = missionRepository.findAllByStore(store, pageRequest);

        // 변환
        return MissionConverter.toMissionPreViewListDTO(missionList);
    }
}