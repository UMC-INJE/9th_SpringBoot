package com.example.umc_9th_chiki.Domain.Store.Service.command;

import com.example.umc_9th_chiki.Domain.Store.Dto.StoreRequestDTO;
import com.example.umc_9th_chiki.Domain.Store.Entity.Store;
import com.example.umc_9th_chiki.Domain.Review.Entity.Review;
import com.example.umc_9th_chiki.Domain.Review.Dto.ReviewRequestDTO;

public interface StoreCommandService {
    Store joinStore(StoreRequestDTO.JoinDto request);
    Review createReview(Long storeId, ReviewRequestDTO.JoinDto request);
}