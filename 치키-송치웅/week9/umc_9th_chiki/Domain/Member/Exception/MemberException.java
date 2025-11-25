package com.example.umc_9th_chiki.Domain.Member.Exception;

import com.example.umc_9th_chiki.Global.apiPayload.code.BaseErrorCode;
import com.example.umc_9th_chiki.Global.apiPayload.exception.GeneralException;

public class MemberException extends GeneralException {
    public MemberException(BaseErrorCode code) {
        super(code);
    }
}