package com.example.umc_9th_chiki.repository;

import com.example.umc_9th_chiki.Domain.Review.Entity.QReview;
import com.example.umc_9th_chiki.Domain.Review.Entity.Review;
import com.example.umc_9th_chiki.Domain.Store.Entity.QStore;
import com.example.umc_9th_chiki.Domain.Store.Entity.QLocation; // QLocation import
import com.example.umc_9th_chiki.Domain.Member.Entity.QMember;   // QMember import
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Review> searchReview(Predicate predicate) {
        QReview review = QReview.review;
        QStore store = QStore.store;
        QLocation location = QLocation.location;

        // searchReview에서는 fetchJoin 유지 (성능상 필요할 수 있음)
        return queryFactory
                .selectFrom(review)
                .join(review.store, store).fetchJoin()
                .join(store.location, location).fetchJoin()
                .where(predicate)
                .fetch();
    }

    @Override
    public List<Review> searchReviewByLocation(String locationName) {
        QReview review = QReview.review;
        QStore store = QStore.store;
        QLocation location = QLocation.location;

        // searchReviewByLocation에서도 fetchJoin 유지
        return queryFactory
                .selectFrom(review)
                .join(review.store, store).fetchJoin()
                .join(store.location, location).fetchJoin()
                .where(location.name.contains(locationName))
                .fetch();
    }

    @Override
    public List<Review> findReviewsByConditions(Predicate predicate) {
        QReview review = QReview.review;
        QStore store = QStore.store;
        QMember member = QMember.member;
        QLocation location = QLocation.location; // location join을 위해 추가

        // fetchJoin 제거된 버전
        return queryFactory
                .selectFrom(review)
                .join(review.store, store) // .fetchJoin() 제거
                .join(store.location, location) // location join 추가
                .join(review.member, member) // .fetchJoin() 제거
                .where(predicate)
                .orderBy(review.createdAt.desc())
                .fetch();
    }
}