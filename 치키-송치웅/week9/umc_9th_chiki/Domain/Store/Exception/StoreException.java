package com.example.umc_9th_chiki.Domain.Store.Exception;

import com.example.umc_9th_chiki.Global.apiPayload.exception.GeneralException;
import com.example.umc_9th_chiki.Global.apiPayload.code.BaseErrorCode;

public class StoreException extends GeneralException {

    public StoreException(BaseErrorCode code) {
        super(code);
    }
}