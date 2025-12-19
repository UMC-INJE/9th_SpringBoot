package com.example.umc_9th_chiki.Global.Validation;

import com.example.umc_9th_chiki.Global.Annotation.ExistFoods;
import com.example.umc_9th_chiki.Domain.Food.Repository.FoodRepository; // DB 접근을 위해 필요
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component // 👈 Spring Bean으로 등록하여 Repository를 주입받을 수 있게 함
@RequiredArgsConstructor
public class FoodExistValidator implements ConstraintValidator<ExistFoods, List<Long>> {
    // DTO의 List<Long> 필드를 검증합니다.

    private final FoodRepository foodRepository;

    @Override
    public void initialize(ExistFoods constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Long> foodIds, ConstraintValidatorContext context) {

        if (foodIds == null || foodIds.isEmpty()) {
            return true; // 값이 없으면 (필수가 아니라면) 통과
        }

        // [핵심 로직] DB에 존재하는 ID의 개수를 셉니다.
        Long existCount = foodRepository.countByIdIn(foodIds);

        // DTO로 넘어온 ID 개수와 DB에서 찾은 개수가 다르면 실패
        if (existCount != foodIds.size()) {
            // (커스텀 에러 처리 로직 - 필요 시 구현)
            return false;
        }

        return true;
    }
}