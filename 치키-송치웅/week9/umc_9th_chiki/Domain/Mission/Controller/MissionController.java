package com.example.umc_9th_chiki.Domain.Mission.Controller;

import com.example.umc_9th_chiki.Domain.Member.Entity.Mapping.MemberMission;
import com.example.umc_9th_chiki.Domain.Mission.Converter.MissionConverter;
import com.example.umc_9th_chiki.Domain.Mission.Dto.MissionRequestDTO;
import com.example.umc_9th_chiki.Domain.Mission.Dto.MissionResponseDTO;
import com.example.umc_9th_chiki.Domain.Mission.Service.query.MissionQueryService;
import com.example.umc_9th_chiki.Global.apiPayload.ApiResponse;
import com.example.umc_9th_chiki.Global.apiPayload.code.GeneralSuccessCode;
import com.example.umc_9th_chiki.Domain.Mission.Service.command.MissionCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
public class MissionController implements MissionControllerDocs {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    // 1. 미션 도전하기 API
    @Override
    @PostMapping("/missions/{missionId}/challenges")
    public ApiResponse<MissionResponseDTO.ChallengeMissionResultDto> challengeMission(
            @PathVariable(name = "missionId") Long missionId,
            @RequestBody MissionRequestDTO.ChallengeMissionDto request
    ){
        MemberMission memberMission = missionCommandService.challengeMission(missionId, request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK,
                MissionConverter.toChallengeMissionResultDto(memberMission));
    }

    // 2. 미션 목록 조회 API
    @Override
    @GetMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResponseDTO.MissionPreViewListDTO> getMissionList(
            @PathVariable(name = "storeId") Long storeId,
            @RequestParam(name = "page") Integer page
    ){
        var result = missionQueryService.getMissionList(storeId, page - 1);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}