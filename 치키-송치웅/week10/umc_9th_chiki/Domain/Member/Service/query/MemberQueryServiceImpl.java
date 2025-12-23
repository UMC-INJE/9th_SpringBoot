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
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public ReviewResponseDTO.ReviewPreViewListDTO getMyReviewList(Long memberId, Integer page) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("멤버를 찾을 수 없습니다."));

        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Review> result = reviewRepository.findAllByMember(member, pageRequest);

        return ReviewConverter.toReviewPreViewListDTO(result);
    }

    @Override
    public MissionResponseDTO.MissionPreViewListDTO getMyMissionList(Long memberId, Integer page) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("멤버를 찾을 수 없습니다."));

        PageRequest pageRequest = PageRequest.of(page, 10);

        Page<MemberMission> memberMissionList = memberMissionRepository.findAllByMemberAndStatus(member, MissionStatus.CHALLENGING, pageRequest);

        return MissionConverter.toMissionPreViewListDTOfromMemberMission(memberMissionList);
    }
}