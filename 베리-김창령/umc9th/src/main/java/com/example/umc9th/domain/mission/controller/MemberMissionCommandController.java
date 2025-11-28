package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.req.MemberMissionReqDTO;
import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.mission.exception.code.MemberMissionSuccessCode;
import com.example.umc9th.domain.mission.service.command.MemberMissionCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member-missions")
public class MemberMissionCommandController {

    private final MemberMissionCommandService memberMissionCommandService;

    @PostMapping("/challenge")
    public ApiResponse<MemberMissionResDTO.CreateDTO> challenge(
            @Valid @RequestBody MemberMissionReqDTO.CreateDTO dto
    ) {
        return ApiResponse.onSuccess(
                MemberMissionSuccessCode.CREATED,
                memberMissionCommandService.challenge(dto)
        );
    }

    @PatchMapping("/{memberMissionId}/complete")
    public ApiResponse<MemberMissionResDTO.CompleteDTO> completeMission(
            @PathVariable Long memberMissionId
    ) {
        return ApiResponse.onSuccess(
                MemberMissionSuccessCode.UPDATED,
                memberMissionCommandService.completeMission(memberMissionId)
        );
    }
}
