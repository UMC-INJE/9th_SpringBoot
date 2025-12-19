package com.example.umc_9th_chiki.Domain.test.exception;

import com.example.umc_9th_chiki.Global.apiPayload.code.BaseErrorCode;
import com.example.umc_9th_chiki.Global.apiPayload.exception.GeneralException;

public class TestException extends GeneralException {
    public TestException(BaseErrorCode code) {
        super(code);
    }
}

