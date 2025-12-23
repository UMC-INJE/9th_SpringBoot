package com.example.umc_9th_chiki.Domain.Member.Service.command;

import com.example.umc_9th_chiki.Domain.Food.Exception.FoodException;
import com.example.umc_9th_chiki.Domain.Food.Exception.code.FoodErrorCode;
import com.example.umc_9th_chiki.Domain.Member.Converter.MemberConverter;
import com.example.umc_9th_chiki.Domain.Member.Converter.MemberFoodConverter;
import com.example.umc_9th_chiki.Domain.Member.Dto.MemberReqDTO;
import com.example.umc_9th_chiki.Domain.Member.Dto.MemberResDTO;
import com.example.umc_9th_chiki.Domain.Member.Entity.Mapping.MemberFood;
import com.example.umc_9th_chiki.Domain.Member.Entity.Mapping.MemberMission;
import com.example.umc_9th_chiki.Domain.Member.Entity.Member;
import com.example.umc_9th_chiki.Domain.Member.Service.command.MemberCommandService;
import com.example.umc_9th_chiki.Domain.Mission.Enums.MissionStatus;
import com.example.umc_9th_chiki.Domain.Store.Entity.Food;
import com.example.umc_9th_chiki.Global.Auth.Enums.Role;
import com.example.umc_9th_chiki.Global.Security.Jwt.JwtUtil;
import com.example.umc_9th_chiki.Domain.Food.Repository.FoodRepository;
import com.example.umc_9th_chiki.Domain.Member.Repository.MemberFoodRepository;
import com.example.umc_9th_chiki.Domain.Member.Repository.MemberRepository;
import com.example.umc_9th_chiki.Domain.Member.Repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final FoodRepository foodRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberMissionRepository memberMissionRepository;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public MemberResDTO.JoinDTO signup(MemberReqDTO.JoinDTO dto) {

        // 비밀번호 인코딩
        String encodedPassword = passwordEncoder.encode(dto.password());

        // DTO -> Entity 변환 (기본 권한 ROLE_USER 전달)
        Member newMember = MemberConverter.toMember(dto, encodedPassword, Role.ROLE_USER);

        // DB 저장
        Member savedMember = memberRepository.save(newMember);

        // 선호 음식 카테고리 저장 (Stream 사용)
        if (dto.preferCategory() != null && !dto.preferCategory().isEmpty()) {

            List<MemberFood> memberFoodList = dto.preferCategory().stream()
                    .map(foodId -> {
                        Food food = foodRepository.findById(foodId)
                                .orElseThrow(() -> new FoodException(FoodErrorCode.FOOD_NOT_FOUND));

                        return MemberFoodConverter.toMemberFood(savedMember, food);
                    })
                    .collect(Collectors.toList());

            memberFoodRepository.saveAll(memberFoodList);
        }

        // 응답 DTO 생성 및 반환
        return MemberConverter.toJoinDTO(savedMember);
    }


    @Override
    @Transactional
    public void completeMission(Long memberMissionId) {

        // 도전 기록 찾기
        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new RuntimeException("해당하는 미션 도전 기록이 없습니다."));

        // 상태 변경 (도전 중 -> 성공)
        memberMission.setStatus(MissionStatus.COMPLETE);
    }
}