package com.example.umc_9th_chiki.service;

import com.example.umc_9th_chiki.Domain.Review.Entity.QReview;
import com.example.umc_9th_chiki.Domain.Review.Entity.Review;
import com.example.umc_9th_chiki.Domain.Store.Entity.QStore;
import com.example.umc_9th_chiki.Domain.Store.Entity.QLocation;
import com.example.umc_9th_chiki.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Expressions;
import lombok.RequiredArgsConstructor;
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
    private static final Logger log = LoggerFactory.getLogger(ReviewQueryService.class);

    public List<Review> searchReview(String query, String type) {
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
}