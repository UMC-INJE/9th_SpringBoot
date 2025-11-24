package com.example.umc_9th_chiki.Domain.Member.Converter;

import com.example.umc_9th_chiki.Domain.Member.Entity.Mapping.MemberFood;
import com.example.umc_9th_chiki.Domain.Member.Entity.Member;
import com.example.umc_9th_chiki.Domain.Store.Entity.Food;

public class MemberFoodConverter {

    public static MemberFood toMemberFood(Member member, Food food){
        return MemberFood.builder()
                .member(member)
                .food(food)
                .build();
    }
}