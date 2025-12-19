package com.example.umc_9th_chiki.Domain.Review.Service.query;

import com.example.umc_9th_chiki.Domain.Review.Converter.ReviewConverter;
import com.example.umc_9th_chiki.Domain.Review.Dto.ReviewResponseDTO;
import com.example.umc_9th_chiki.Domain.Review.Entity.QReview;
import com.example.umc_9th_chiki.Domain.Review.Entity.Review;
import com.example.umc_9th_chiki.Domain.Store.Entity.QStore;
import com.example.umc_9th_chiki.Domain.Store.Entity.QLocation;
import com.example.umc_9th_chiki.Domain.Review.Repository.ReviewRepository;
import com.example.umc_9th_chiki.Domain.Store.Entity.Store;
import com.example.umc_9th_chiki.Domain.Store.Exception.StoreException;
import com.example.umc_9th_chiki.Domain.Store.Exception.code.StoreErrorCode;
import com.example.umc_9th_chiki.Domain.Store.Repository.StoreRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private static final Logger log = LoggerFactory.getLogger(ReviewQueryService.class);

    public List<Review> searchReview(String query, String type) {
        // Q 클래스 준비
        QReview review = QReview.review;
        QStore store = QStore.store;
        QLocation location = QLocation.location;
        BooleanBuilder builder = new BooleanBuilder();

        if ("location".equals(type)) {
            builder.and(location.name.contains(query));
        } else if ("star".equals(type)) {
            builder.and(review.star.goe(Float.parseFloat(query)));
        } else if ("both".equals(type)) {
            String firstQuery = query.split(("&"))[0];
            String secondQuery = query.split(("&"))[1];
            builder.and(location.name.contains(firstQuery));
            builder.and(review.star.goe(Float.parseFloat(secondQuery)));
        }

        List<Review> reviewList = reviewRepository.searchReview(builder);
        return reviewList;
    }

    public List<Review> findMyReviews(Long memberId, String storeName, Integer rating) {
        QReview review = QReview.review;
        QStore store = QStore.store;
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(review.member.id.eq(memberId));

        if (storeName != null && !storeName.isEmpty()) {
            builder.and(review.store.name.contains(storeName));
        }

        if (rating != null) {
            float minStar = rating.floatValue();
            float maxStar = minStar + 1.0f;
            builder.and(review.star.goe(minStar));
            builder.and(review.star.lt(maxStar));
        }

        log.info("findMyReviews Predicate (CONTAINS 사용): {}", builder.toString());
        return reviewRepository.findReviewsByConditions(builder);
    }
    public ReviewResponseDTO.ReviewPreViewListDTO findReview(
            String storeName,
            Integer page
    ){
        // - 가게를 가져온다 (가게 존재 여부 검증)
        Store store = storeRepository.findByName(storeName)
                //    - 없으면 예외 터뜨린다
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        // - 가게에 맞는 리뷰를 가져온다 (Offset 페이징)
        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        //- 결과를 응답 DTO로 변환한다 (컨버터 이용)
        return ReviewConverter.toReviewPreViewListDTO(result);
    }
}