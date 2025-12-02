package service;

import dto.MissionResponse;
import entity.Mission;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.MissionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionQueryService {

    private final MissionRepository missionRepository;

    @Transactional(readOnly = true)
    public Page<MissionResponse> getStoreMissions(
            Integer storeId,
            int page,
            int size
    ) {
        // page는 1 이상 들어온다고 가정 → 0 기반으로 변환
        int safePage = Math.max(page - 1, 0);
        int pageSize = 10; // 미션 조건: 한 페이지에 10개씩 조회

        Pageable pageable = PageRequest.of(
                safePage,
                pageSize,
                Sort.by("deadline").ascending()   //마감일이 가까운 순
        );

        Page<Mission> result = missionRepository.findByStore_StoreId(storeId, pageable);

        List<MissionResponse> mapped = result.getContent()
                .stream()
                .map(MissionResponse::from)
                .toList();

        return new PageImpl<>(mapped, pageable, result.getTotalElements());
    }
}
