package com.example.umc_9th_chiki.Global.validation.validator;

import com.example.umc_9th_chiki.Global.apiPayload.code.GeneralErrorCode;
import com.example.umc_9th_chiki.Global.validation.annotation.CheckPage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckpageValidator implements ConstraintValidator<CheckPage, Integer> {

    @Override
    public void initialize(CheckPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null || value < 1) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(GeneralErrorCode.PAGE_NOT_VALID.toString())
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}