package com.example.umc_9th_chiki.Domain.Store.Dto;

import lombok.Getter;

public class StoreRequestDTO {

    @Getter
    public static class JoinDto {
        private String name;
        private String address;
        private Long regionId;
        private String category;
        private String detailAddress;
    }
}