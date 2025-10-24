package com.example.umc_9th_chiki.repository;

import com.example.umc_9th_chiki.Domain.Review.Entity.Review;
import com.example.umc_9th_chiki.Domain.Store.Entity.Store;
import com.example.umc_9th_chiki.Domain.Member.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 특정 가게(Store)에 달린 모든 리뷰 조회
    List<Review> findByStore(Store store);

    // 특정 회원(Member)이 작성한 모든 리뷰 조회
    List<Review> findByMember(Member member);

    // 특정 별점(star) 이상의 리뷰 조회
    List<Review> findByStarGreaterThanEqual(Float star);

    // 특정 리뷰를 조회하면서 사진 목록(reviewPhotoList)을 함께 로딩
    @Query("SELECT r FROM Review r JOIN FETCH r.reviewPhotoList WHERE r.id = :reviewId")
    Optional<Review> findByIdWithPhotos(@Param("reviewId") Long reviewId);

    // 특정 리뷰를 조회하면서 답글 목록(replyList)을 함께 로딩
    @Query("SELECT r FROM Review r JOIN FETCH r.replyList WHERE r.id = :reviewId")
    Optional<Review> findByIdWithReplies(@Param("reviewId") Long reviewId);

    // 특정 가게(Store)의 모든 리뷰를 조회하면서 회원(Member) 정보도 함께 로딩
    @Query("SELECT r FROM Review r JOIN FETCH r.member WHERE r.store = :store")
    List<Review> findByStoreWithMember(@Param("store") Store store);
}