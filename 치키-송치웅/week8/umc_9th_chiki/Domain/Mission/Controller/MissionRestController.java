package com.example.umc_9th_chiki.Domain.Mission.Controller;

import com.example.umc_9th_chiki.Domain.Member.Entity.Mapping.MemberMission;
import com.example.umc_9th_chiki.Domain.Mission.Converter.MissionConverter;
import com.example.umc_9th_chiki.Domain.Mission.Dto.MissionRequestDTO;
import com.example.umc_9th_chiki.Domain.Mission.Dto.MissionResponseDTO;
import com.example.umc_9th_chiki.Global.apiPayload.ApiResponse;
import com.example.umc_9th_chiki.Global.apiPayload.code.GeneralSuccessCode; // 성공 코드
import com.example.umc_9th_chiki.service.command.MissionCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/{missionId}/challenges")
    public ApiResponse<MissionResponseDTO.ChallengeMissionResultDto> challengeMission(
            @PathVariable(name = "missionId") Long missionId,
            @RequestBody @Valid MissionRequestDTO.ChallengeMissionDto request
    ){
        MemberMission memberMission = missionCommandService.challengeMission(missionId, request);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK,
                MissionConverter.toChallengeMissionResultDto(memberMission));
    }
}