package com.example.umc_9th_chiki.Domain.Food.Exception.code;

import com.example.umc_9th_chiki.Global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FoodErrorCode implements BaseErrorCode {
    FOOD_NOT_FOUND(HttpStatus.NOT_FOUND, "FOOD_404_1", "해당 음식 카테고리를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}