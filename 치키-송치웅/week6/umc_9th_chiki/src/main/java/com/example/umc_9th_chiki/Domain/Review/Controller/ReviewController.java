package com.example.umc_9th_chiki.Domain.Review.Controller;

// Converter와 Dto import는 임시로 사용 안 함
// import com.example.umc_9th_chiki.Domain.Review.Converter.ReviewConverter;
// import com.example.umc_9th_chiki.Domain.Review.Dto.ReviewResponseDto;
import com.example.umc_9th_chiki.Domain.Review.Converter.ReviewConverter;
import com.example.umc_9th_chiki.Domain.Review.Dto.ReviewResponseDto;
import com.example.umc_9th_chiki.Domain.Review.Entity.Review; // Entity import
import com.example.umc_9th_chiki.service.ReviewQueryService;
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
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    // 기존 searchReview 메서드는 DTO 반환 유지 (선택 사항)
    @GetMapping("/reviews/search")
    public List<ReviewResponseDto> searchReview( // 여기는 DTO 유지하거나 List<Review>로 통일해도 됨
                                                 @RequestParam String query,
                                                 @RequestParam String type) {

        log.info("API 호출됨 (searchReview): query='{}', type='{}'", query, type);
        List<Review> reviewList = reviewQueryService.searchReview(query, type);
        return ReviewConverter.toReviewResponseDtoList(reviewList); // DTO 변환
    }

    /**
     * 내가 작성한 리뷰 목록 조회 API (테스트: Entity 직접 반환)
     */
    @GetMapping("/members/{memberId}/reviews")
    public List<Review> getMyReviews( // 👈 반환 타입을 List<Review>로 임시 변경
                                      @PathVariable Long memberId,
                                      @RequestParam(required = false) String storeName,
                                      @RequestParam(required = false) Integer rating
    ) {
        log.info("API 호출됨 (getMyReviews): memberId={}, storeName='{}', rating={}", memberId, storeName, rating);
        List<Review> reviewList = reviewQueryService.findMyReviews(memberId, storeName, rating);
        // return ReviewConverter.toReviewResponseDtoList(reviewList); // 👈 Converter 호출 임시 주석 처리
        return reviewList; // 👈 Entity List를 직접 반환
    }
}