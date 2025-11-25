package service;

import dto.MyMissionResponse;
import entity.UserMission;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserMissionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMissionQueryService {

    private final UserMissionRepository userMissionRepository;

    @Transactional(readOnly = true)
    public Page<MyMissionResponse> getMyInProgressMissions(
            Integer userId,
            int page,
            int size
    ) {
        // page는 1 이상 들어온다고 가정 → 0 기반으로 변환
        int safePage = Math.max(page - 1, 0);
        int pageSize = 10;   // 미션 조건: 한 페이지 10개

        Pageable pageable = PageRequest.of(
                safePage,
                pageSize,
                Sort.by("createdAt").descending()  // 유저가 미션에 참여한 날짜 순으로 정렬
        );

        Page<UserMission> result = userMissionRepository.findByUser_UserIdAndStatus(
                userId,
                "IN_PROGRESS",
                pageable
        );

        List<MyMissionResponse> mapped = result.getContent()
                .stream()
                .map(MyMissionResponse::from)
                .toList();

        return new PageImpl<>(mapped, pageable, result.getTotalElements());
    }
}
