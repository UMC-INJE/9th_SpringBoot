package com.example.umc_9th_chiki.Domain.Store.Converter;

import com.example.umc_9th_chiki.Domain.Store.Dto.StoreRequestDTO;
import com.example.umc_9th_chiki.Domain.Store.Dto.StoreResponseDTO;
import com.example.umc_9th_chiki.Domain.Store.Entity.Location;
import com.example.umc_9th_chiki.Domain.Store.Entity.Store;

public class StoreConverter {

    // Store 엔티티 -> 응답 DTO 변환
    public static StoreResponseDTO.JoinResultDto toJoinResultDTO(Store store) {
        return StoreResponseDTO.JoinResultDto.builder()
                .storeId(store.getId())
                .createdAt(store.getCreatedAt())
                .build();
    }

    // 요청 DTO -> Store 엔티티 변환
    public static Store toStore(StoreRequestDTO.JoinDto request, Location location) {
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .detailAddress(request.getDetailAddress())
                .category(request.getCategory())
                .location(location)
                .build();
    }
}