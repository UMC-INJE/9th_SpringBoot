package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/reviews/search")
    public List<Review> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ) {
        return reviewQueryService.searchReview(query, type);
    }
}
