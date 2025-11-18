package controller;

import dto.ReviewReqDto;
import dto.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import response.ApiResponse;
import response.SuccessEnum;
import service.ReviewQueryService;
import service.ReviewService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewQueryService reviewQueryService;
    private final ReviewService reviewService;

    @GetMapping("/reviews/me")
    public ApiResponse<Page<ReviewResponse>> myReviews(
            @RequestParam Integer userId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer rating,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<ReviewResponse> reviews = reviewQueryService.getMyReviews(userId, storeName, rating, page, size);
        return ApiResponse.success(reviews, SuccessEnum.OK);
    }

    @GetMapping("/reviews/search")
    public ApiResponse<Page<ReviewResponse>> searchReview(
            @RequestParam Integer userId,
            @RequestParam String type,
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<ReviewResponse> reviews = reviewQueryService.searchMyReviews(userId, type, query, page, size);
        return ApiResponse.success(reviews, SuccessEnum.OK);
    }
    
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<String> createReview(
            @PathVariable Integer storeId,
            @RequestBody ReviewReqDto requestDto
    ) {
        reviewService.createReview(storeId, requestDto);
        return ApiResponse.success("리뷰 등록 완료!", SuccessEnum.OK);
    }

}
