package com.example.umc_9th_chiki.Domain.Review.Converter;

import com.example.umc_9th_chiki.Domain.Review.Dto.ReviewRequestDTO;
import com.example.umc_9th_chiki.Domain.Review.Dto.ReviewResponseDTO;
import com.example.umc_9th_chiki.Domain.Review.Entity.Review;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResponseDTO.CreateResultDto toCreateResultDto(Review review) {
        // 구현 조건 6 :  무조건 빌더 패턴을 사용해야 한다.
        return ReviewResponseDTO.CreateResultDto.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static Review toReview(ReviewRequestDTO.JoinDto request) {
        // 구현 조건 6 : 무조건 빌더 패턴을 사용해야 한다.
        return Review.builder()
                .content(request.getBody())
                .star(request.getScore())
                .build();
    }

    public static ReviewResponseDTO.ReviewPreViewDTO toReviewPreViewDTO(Review review) {
        // 구현 조건 6 : 무조건 빌더 패턴을 사용해야 한다.
        return ReviewResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getStar())
                .body(review.getContent())
                .createdAt(review.getCreatedAt().toLocalDate())
                .storeName(review.getStore().getName()) // (가게 이름 추가됨)
                .build();
    }

    public static ReviewResponseDTO.ReviewPreViewListDTO toReviewPreViewListDTO(Page<Review> reviewList) {

        // 구현 조건 5 : Converter에서 절대로 for문을 사용해서는 안되며, 무조건 Java의 Stream을 사용해야 한다.
        List<ReviewResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(ReviewConverter::toReviewPreViewDTO)
                .collect(Collectors.toList());

        // 구현 조건 6 : 무조건 빌더 패턴을 사용해야 한다.
        return ReviewResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

    public static List<ReviewResponseDTO.ReviewPreViewDTO> toReviewPreViewDtoList(List<Review> reviews) {
        // 구현 조건 : 5 for문 대신 Stream 사용
        return reviews.stream()
                .map(ReviewConverter::toReviewPreViewDTO)
                .collect(Collectors.toList());
    }
}