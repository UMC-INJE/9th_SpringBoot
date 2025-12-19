package com.example.umc_9th_chiki.Domain.Review.Dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ReviewPreviewListDTO(
        List<ReviewResponseDTO.ReviewPreViewDTO> reviewList,
        Integer listSize,
        Integer totalPage,
        Long totalElements,
        Boolean isFirst,
        Boolean isLast
){}
