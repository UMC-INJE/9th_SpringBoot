package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.service.command.ReviewCommandService;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.global.validation.RequiredPage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController implements ReviewControllerDocs {

    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    @Override
    @GetMapping("")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @RequiredPage @RequestParam Integer page
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                reviewQueryService.getReviews(storeName, page)
        );
    }

    @GetMapping("/me")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getMyReviews(
            @RequestParam Long memberId,
            @RequiredPage @RequestParam Integer page
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                reviewQueryService.getMyReviews(memberId, page)
        );
    }

    @Override
    @GetMapping("/search")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                reviewQueryService.searchReview(query, type)
        );
    }

    @PostMapping("/create")
    public ApiResponse<ReviewResDTO.CreateDTO> createReview(
            @Valid @RequestBody ReviewReqDTO.CreateDTO dto
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                reviewCommandService.createReview(dto)
        );
    }
}
