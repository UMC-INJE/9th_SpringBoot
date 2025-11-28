package com.example.umc9th.domain.review.service.command;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public ReviewResDTO.CreateDTO createReview(ReviewReqDTO.CreateDTO dto) {

        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("임시 member가 없습니다."));

        Store store = storeRepository.findById(dto.storeId())
                .orElseThrow(() -> new RuntimeException("가게가 존재하지 않습니다."));

        Review review = ReviewConverter.toReview(dto, member, store);
        reviewRepository.save(review);

        return ReviewConverter.toCreateDTO(review);
    }
}
