package com.example.umc_9th_chiki.Domain.Store.Service.command;

import com.example.umc_9th_chiki.Domain.Review.Converter.ReviewConverter;
import com.example.umc_9th_chiki.Domain.Review.Dto.ReviewRequestDTO;
import com.example.umc_9th_chiki.Domain.Review.Entity.Review;
import com.example.umc_9th_chiki.Domain.Store.Converter.StoreConverter;
import com.example.umc_9th_chiki.Domain.Store.Dto.StoreRequestDTO;
import com.example.umc_9th_chiki.Domain.Store.Entity.Store;
import com.example.umc_9th_chiki.Domain.Store.Repository.LocationRepository;
import com.example.umc_9th_chiki.Domain.Member.Repository.MemberRepository;
import com.example.umc_9th_chiki.Domain.Review.Repository.ReviewRepository;
import com.example.umc_9th_chiki.Domain.Store.Repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final LocationRepository locationRepository;

    @Override
    @Transactional
    public Store joinStore(StoreRequestDTO.JoinDto request) {

        var location = locationRepository.findById(request.getRegionId())
                .orElseThrow(() -> new RuntimeException("지역(Region/Location)을 찾을 수 없습니다."));

        Store newStore = StoreConverter.toStore(request, location);
        return storeRepository.save(newStore);
    }

    @Override
    @Transactional
    public Review createReview(Long storeId, ReviewRequestDTO.JoinDto request) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));

        var member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("멤버를 찾을 수 없습니다."));

        Review review = ReviewConverter.toReview(request);

        review.setStore(store);
        review.setMember(member);

        return reviewRepository.save(review);
    }
}