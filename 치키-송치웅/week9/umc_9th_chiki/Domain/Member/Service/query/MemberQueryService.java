package com.example.umc_9th_chiki.Domain.Member.Service.query;

import com.example.umc_9th_chiki.Domain.Member.Entity.Mapping.MemberMission;
import com.example.umc_9th_chiki.Domain.Member.Entity.Member;
import com.example.umc_9th_chiki.Domain.Member.Repository.MemberMissionRepository;
import com.example.umc_9th_chiki.Domain.Member.Repository.MemberRepository;
import com.example.umc_9th_chiki.Domain.Mission.Converter.MissionConverter;
import com.example.umc_9th_chiki.Domain.Mission.Dto.MissionResponseDTO;
import com.example.umc_9th_chiki.Domain.Mission.Enums.MissionStatus;
import com.example.umc_9th_chiki.Domain.Review.Converter.ReviewConverter;
import com.example.umc_9th_chiki.Domain.Review.Dto.ReviewResponseDTO;
import com.example.umc_9th_chiki.Domain.Review.Entity.Review;
import com.example.umc_9th_chiki.Domain.Review.Repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final MemberMissionRepository memberMissionRepository;

    public ReviewResponseDTO.ReviewPreViewListDTO getMyReviewList(Long memberId, Integer page) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("멤버를 찾을 수 없습니다."));

        // 구현 조건 1 : 반드시 Paging 처리를 할 것 (한 페이지에 10개씩 조회)
        PageRequest pageRequest = PageRequest.of(page, 10);

        Page<Review> result = reviewRepository.findAllByMember(member, pageRequest);

        return ReviewConverter.toReviewPreViewListDTO(result);
    }
        public MissionResponseDTO.MissionPreViewListDTO getMyMissionList(Long memberId, Integer page) {

            // 1. 멤버 확인
            Member member = memberRepository.findById(memberId)
                    .orElseThrow(() -> new RuntimeException("멤버를 찾을 수 없습니다."));

            // 2. 진행 중인 미션 조회 (페이징)
            PageRequest pageRequest = PageRequest.of(page, 10);
            Page<MemberMission> memberMissionList = memberMissionRepository.findAllByMemberAndStatus(member, MissionStatus.CHALLENGING, pageRequest);

            // 3. 변환 (Converter 필요 - 다음 단계에서 만듦)
            return MissionConverter.toMissionPreViewListDTOfromMemberMission(memberMissionList);
    }
}