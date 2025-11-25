package com.example.umc_9th_chiki.Domain.Review.Exception;

import com.example.umc_9th_chiki.Global.apiPayload.code.BaseErrorCode;
import com.example.umc_9th_chiki.Global.apiPayload.exception.GeneralException;

public class ReviewException extends GeneralException {
    public ReviewException(BaseErrorCode code) {
        super(code);
    }
}