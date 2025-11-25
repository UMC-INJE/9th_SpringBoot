package com.example.umc_9th_chiki.Domain.Review.Controller;

import com.example.umc_9th_chiki.Domain.Review.Converter.ReviewConverter;
import com.example.umc_9th_chiki.Domain.Review.Dto.ReviewResponseDTO;
import com.example.umc_9th_chiki.Domain.Review.Entity.Review;
import com.example.umc_9th_chiki.Domain.Review.Exception.code.ReviewSuccessCode;
import com.example.umc_9th_chiki.Domain.Review.Service.query.ReviewQueryService;
import com.example.umc_9th_chiki.Global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ReviewController implements ReviewControllerDocs {

    private final ReviewQueryService reviewQueryService;

    // 가게의 리뷰 목록 조회
    @Override
    @GetMapping("reviews")
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getReviewList(
            @RequestParam(name = "storeName") String storeName,
            @RequestParam(name = "page", defaultValue = "1") Integer page
    ){
        var result = reviewQueryService.findReview(storeName, page - 1);
        return ApiResponse.onSuccess(ReviewSuccessCode.FOUND, result);
    }
    // 리뷰 검색
    @GetMapping("/reviews/search")
    public ApiResponse<List<ReviewResponseDTO.ReviewPreViewDTO>> searchReview(
            @RequestParam String query,
            @RequestParam String type) {

        log.info("API 호출됨 (searchReview): query='{}', type='{}'", query, type);
        List<Review> reviewList = reviewQueryService.searchReview(query, type);
        return ApiResponse.onSuccess(ReviewSuccessCode.FOUND, ReviewConverter.toReviewPreViewDtoList(reviewList));
    }
}