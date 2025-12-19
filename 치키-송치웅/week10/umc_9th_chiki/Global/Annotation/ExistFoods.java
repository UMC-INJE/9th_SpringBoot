package com.example.umc_9th_chiki.Global.Annotation;

import com.example.umc_9th_chiki.Global.Validation.FoodExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FoodExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistFoods {

    //여기서 디폴트 메시지를 설정합니다.
    String message() default "해당 음식 카테고리 ID 중 존재하지 않는 항목이 있습니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}