package com.example.umc_9th_chiki.Domain.Food.Exception;

import com.example.umc_9th_chiki.Domain.Food.Exception.code.FoodErrorCode;
import com.example.umc_9th_chiki.Global.apiPayload.exception.GeneralException;

public class FoodException extends GeneralException {
    public FoodException(FoodErrorCode code) {
        super(code);
    }
}