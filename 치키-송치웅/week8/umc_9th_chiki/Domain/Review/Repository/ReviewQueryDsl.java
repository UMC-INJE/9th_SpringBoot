package com.example.umc_9th_chiki.Domain.Review.Repository;

import com.example.umc_9th_chiki.Domain.Review.Entity.Review;
import com.querydsl.core.types.Predicate;
import java.util.List;

public interface ReviewQueryDsl {

    // Predicate를 사용하는 동적 검색 메서드 선언
    List<Review> searchReview(Predicate predicate);

    // 지역명으로 검색하는 메서드 선언
    List<Review> searchReviewByLocation(String locationName);

    // "내가 작성한 리뷰 보기"를 위한 메서드 선언 추가
    List<Review> findReviewsByConditions(Predicate predicate);

}