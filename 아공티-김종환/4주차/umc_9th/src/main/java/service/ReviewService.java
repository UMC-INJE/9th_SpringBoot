package service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import dto.ReviewReqDto;
import entity.Review;
import entity.Store;
import entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import repository.ReviewRepository;
import repository.StoreRepository;
import repository.UserRepository;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public void createReview(Integer storeId, ReviewReqDto requestDto) {

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게 없음"));

        Review review = Review.builder()
                .user(user)
                .store(store)
                .rating(requestDto.getRating())
                .content(requestDto.getContent())
                .createdAt(LocalDateTime.now())
                .build();

        reviewRepository.save(review);
    }
}

