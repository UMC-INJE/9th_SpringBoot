package com.example.umc_9th_chiki.Domain.Member.Service.command;

import com.example.umc_9th_chiki.Domain.Member.Entity.Mapping.MemberMission;
import com.example.umc_9th_chiki.Domain.Member.Repository.MemberMissionRepository;
import com.example.umc_9th_chiki.Domain.Mission.Enums.MissionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public void completeMission(Long memberMissionId) {
        // 도전 기록 찾기
        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new RuntimeException("해당하는 미션 도전 기록이 없습니다."));
        // 상태 변경 (도전 중 -> 성공)
        memberMission.setStatus(MissionStatus.COMPLETE);
        // 저장은 Transactional 덕분에 자동 업데이트
    }
}