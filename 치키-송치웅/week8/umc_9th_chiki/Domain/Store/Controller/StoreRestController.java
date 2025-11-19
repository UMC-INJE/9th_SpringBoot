package com.example.umc_9th_chiki.Domain.Store.Controller;

import com.example.umc_9th_chiki.Domain.Store.Converter.StoreConverter;
import com.example.umc_9th_chiki.Domain.Store.Dto.StoreRequestDTO;
import com.example.umc_9th_chiki.Domain.Store.Dto.StoreResponseDTO;
import com.example.umc_9th_chiki.Domain.Store.Entity.Store;
import com.example.umc_9th_chiki.Global.apiPayload.ApiResponse; // ApiResponse 위치 확인 필요!
import com.example.umc_9th_chiki.Domain.Store.Service.command.StoreCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.umc_9th_chiki.Global.apiPayload.code.GeneralSuccessCode;
import com.example.umc_9th_chiki.Domain.Review.Converter.ReviewConverter;
import com.example.umc_9th_chiki.Domain.Review.Dto.ReviewRequestDTO;
import com.example.umc_9th_chiki.Domain.Review.Dto.ReviewResponseDTO;
import com.example.umc_9th_chiki.Domain.Review.Entity.Review;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    @PostMapping("/")
    public ApiResponse<StoreResponseDTO.JoinResultDto> join(@RequestBody @Valid StoreRequestDTO.JoinDto request){
        Store store = storeCommandService.joinStore(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, StoreConverter.toJoinResultDTO(store));
    }
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResponseDTO.CreateResultDto> createReview(
            @PathVariable(name = "storeId") Long storeId,
            @RequestBody @Valid ReviewRequestDTO.JoinDto request
    ){
        Review review = storeCommandService.createReview(storeId, request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, ReviewConverter.toCreateResultDto(review));
    }
}