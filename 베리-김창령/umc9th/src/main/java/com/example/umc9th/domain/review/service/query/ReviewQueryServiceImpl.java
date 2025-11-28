package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Override
    public ReviewResDTO.ReviewPreViewListDTO getReviews(String storeName, Integer page) {

        Store store = storeRepository.findByName(storeName)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        return ReviewConverter.toReviewPreViewListDTO(result);
    }

    @Override
    public ReviewResDTO.ReviewPreViewListDTO getMyReviews(Long memberId, Integer page) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        Page<Review> result = reviewRepository.findAllByMember(member, pageRequest);

        return ReviewConverter.toReviewPreViewListDTO(result);
    }

    @Override
    public ReviewResDTO.ReviewPreViewListDTO searchReview(String query, String type) {

        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        if (type.equals("location")) {
            builder.and(review.store.location.name.contains(query));
        }

        if (type.equals("star")) {
            builder.and(review.star.goe(Float.parseFloat(query)));
        }

        if (type.equals("both")) {
            String[] split = query.split("&");

            if (split.length == 2) {
                builder.and(review.store.location.name.contains(split[0]));
                builder.and(review.star.goe(Float.parseFloat(split[1])));
            }
        }

        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<Review> result = reviewRepository.searchReview(builder, pageRequest);

        return ReviewConverter.toReviewPreViewListDTO(result);
    }
}
