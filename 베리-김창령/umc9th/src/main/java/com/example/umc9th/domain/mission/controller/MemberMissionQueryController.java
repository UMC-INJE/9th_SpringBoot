package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.mission.exception.code.MemberMissionSuccessCode;
import com.example.umc9th.domain.mission.service.query.MemberMissionQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.validation.RequiredPage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member-missions")
public class MemberMissionQueryController {

    private final MemberMissionQueryService memberMissionQueryService;

    @GetMapping("/{memberId}")
    public ApiResponse<MemberMissionResDTO.MyMissionListDTO> getMyMissions(
            @PathVariable Long memberId,
            @RequiredPage @RequestParam(defaultValue = "1") Integer page
    ) {
        return ApiResponse.onSuccess(
                MemberMissionSuccessCode.FOUND,
                memberMissionQueryService.getMyProgressMissions(memberId, page)
        );
    }
}

