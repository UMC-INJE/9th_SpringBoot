package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO;

public interface ReviewQueryService {

    ReviewResDTO.ReviewPreViewListDTO getReviews(
            String storeName,
            Integer page
    );

    ReviewResDTO.ReviewPreViewListDTO searchReview(
            String query,
            String type
    );

    ReviewResDTO.ReviewPreViewListDTO getMyReviews(
            Long memberId,
            Integer page
    );
}
