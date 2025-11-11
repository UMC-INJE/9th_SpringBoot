package com.example.umc_9th_chiki.Global.apiPayload.exception;

import com.example.umc_9th_chiki.Global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private final BaseErrorCode code;
}