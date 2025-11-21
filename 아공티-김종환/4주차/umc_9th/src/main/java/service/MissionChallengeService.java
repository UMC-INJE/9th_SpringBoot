package service;

import dto.MissionChallengeReqDto;
import entity.Mission;
import entity.Store;
import entity.User;
import entity.UserMission;
import exception.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.MissionRepository;
import repository.StoreRepository;
import repository.UserMissionRepository;
import repository.UserRepository;
import response.ExceptionEnum;

@Service
@RequiredArgsConstructor
public class MissionChallengeService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    @Transactional
    public void challengeMission(Integer storeId, MissionChallengeReqDto dto) {

        // 유저 조회
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new CustomException(ExceptionEnum.NOT_FOUND));
        // 가게 조회
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new CustomException(ExceptionEnum.NOT_FOUND));
        // 미션 조회
        Mission mission = missionRepository.findById(dto.getMissionId())
                .orElseThrow(() -> new CustomException(ExceptionEnum.NOT_FOUND));
        // 미션이 해당 가게에 속하는지 검증
        if (!mission.getStore().getStoreId().equals(storeId)) {
            throw new CustomException(ExceptionEnum.BAD_REQUEST);
        }
        // 이미 같은 미션을 진행 중인지 확인
        boolean exists = userMissionRepository
                .existsByUser_UserIdAndMission_IdAndStatus(user.getUserId(), mission.getId(), "IN_PROGRESS");

        if (exists) {
            throw new CustomException(ExceptionEnum.CONFLICT);
        }

        // UserMission 생성
        UserMission userMission = UserMission.builder()
                .user(user)
                .mission(mission)
                .status("IN_PROGRESS")
                .build();

        userMissionRepository.save(userMission);
    }
}
