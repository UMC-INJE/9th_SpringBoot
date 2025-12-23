package com.example.umc_9th_chiki.Domain.Member.Service.query;

import com.example.umc_9th_chiki.Domain.Mission.Dto.MissionResponseDTO;
import com.example.umc_9th_chiki.Domain.Review.Dto.ReviewResponseDTO;

// [수정] interface 키워드 사용
public interface MemberQueryService {

    // 1. 내가 쓴 리뷰 목록 조회 (선언부만 남김)
    ReviewResponseDTO.ReviewPreViewListDTO getMyReviewList(Long memberId, Integer page);

    // 2. 내가 진행 중인 미션 목록 조회 (선언부만 남김)
    MissionResponseDTO.MissionPreViewListDTO getMyMissionList(Long memberId, Integer page);
}