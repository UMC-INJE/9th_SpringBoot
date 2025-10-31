package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public List<Review> searchReview(String query, String type) {
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
                String locationQuery = split[0];
                String starQuery = split[1];
                builder.and(review.store.location.name.contains(locationQuery));
                builder.and(review.star.goe(Float.parseFloat(starQuery)));
            }
        }

        return reviewRepository.searchReview(builder);
    }
}
