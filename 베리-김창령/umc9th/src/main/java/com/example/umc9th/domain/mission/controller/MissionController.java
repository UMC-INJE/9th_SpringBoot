package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.service.command.MissionCommandService;
import com.example.umc9th.domain.mission.service.query.MissionQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.global.validation.RequiredPage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    @PostMapping("/create")
    public ApiResponse<MissionResDTO.CreateDTO> createMission(
            @Valid @RequestBody MissionReqDTO.CreateDTO dto
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionCommandService.createMission(dto)
        );
    }

    @GetMapping("/by-store")
    public ApiResponse<MissionResDTO.MissionPreViewListDTO> getMissionsByStore(
            @RequestParam String locationName,
            @RequiredPage @RequestParam Integer page
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionQueryService.getMissionsByStoreLocation(locationName, page)
        );
    }
}
