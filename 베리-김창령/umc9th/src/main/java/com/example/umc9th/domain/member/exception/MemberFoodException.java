package com.example.umc9th.domain.member.exception;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;

public class MemberFoodException extends GeneralException {
    public MemberFoodException(BaseErrorCode code) {super(code);}
}
