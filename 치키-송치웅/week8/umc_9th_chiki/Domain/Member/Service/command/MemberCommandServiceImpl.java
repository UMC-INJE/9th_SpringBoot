package com.example.umc_9th_chiki.Domain.Member.Service.command;

import com.example.umc_9th_chiki.Domain.Food.Exception.FoodException;
import com.example.umc_9th_chiki.Domain.Food.Exception.code.FoodErrorCode;
import com.example.umc_9th_chiki.Domain.Member.Converter.MemberConverter;
import com.example.umc_9th_chiki.Domain.Member.Converter.MemberFoodConverter;
import com.example.umc_9th_chiki.Domain.Member.Dto.MemberReqDTO;
import com.example.umc_9th_chiki.Domain.Member.Dto.MemberResDTO;
import com.example.umc_9th_chiki.Domain.Member.Entity.Mapping.MemberFood;
import com.example.umc_9th_chiki.Domain.Member.Entity.Member;
import com.example.umc_9th_chiki.Domain.Store.Entity.Food;
import com.example.umc_9th_chiki.Domain.Food.Repository.FoodRepository;
import com.example.umc_9th_chiki.Domain.Member.Repository.MemberFoodRepository;
import com.example.umc_9th_chiki.Domain.Member.Repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final FoodRepository foodRepository;

    @Override
    @Transactional
    public MemberResDTO.JoinDTO signup(MemberReqDTO.JoinDTO dto) {

        // 사용자 생성
        Member member = MemberConverter.toMember(dto);
        // DB 적용
        memberRepository.save(member);

        // 선호 음식 존재 여부 확인 (Stream 적용)
        if (dto.preferCategory() != null && !dto.preferCategory().isEmpty()) {

            List<MemberFood> memberFoodList = dto.preferCategory().stream()
                    .map(foodId -> {
                        // ID로 Food 엔티티 조회
                        Food food = foodRepository.findById(foodId)
                                .orElseThrow(() -> new FoodException(FoodErrorCode.FOOD_NOT_FOUND));
                        return MemberFoodConverter.toMemberFood(member, food);
                    })
                    .collect(Collectors.toList()); // 리스트로 변환

            // 3. 일괄 저장
            memberFoodRepository.saveAll(memberFoodList);
        }

        // 4. 응답 DTO 반환
        return MemberConverter.toJoinDTO(member);
    }
}