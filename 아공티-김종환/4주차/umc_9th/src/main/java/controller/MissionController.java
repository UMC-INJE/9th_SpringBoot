package controller;

import dto.MissionChallengeReqDto;
import dto.MissionResponse;
import dto.MyMissionResponse;
import lombok.RequiredArgsConstructor;
import response.ApiResponse;
import response.SuccessEnum;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import service.MissionChallengeService;
import service.MissionQueryService;
import service.UserMissionQueryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionChallengeService missionChallengeService;
    private final MissionQueryService missionQueryService;
    private final UserMissionQueryService userMissionQueryService;

    @PostMapping("/{storeId}/missions/challenge")
    public String challengeMission(
            @PathVariable Integer storeId,
            @RequestBody MissionChallengeReqDto dto
    ) {
        missionChallengeService.challengeMission(storeId, dto);
        return "미션 도전 완료!";
    }
    
    @GetMapping("/{storeId}/missions")
    public ApiResponse<Page<MissionResponse>> getStoreMissions(
            @PathVariable Integer storeId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") int size
    ) {
        if (page == null || page < 1) {
            page = 1;
        }

        Page<MissionResponse> missions = missionQueryService.getStoreMissions(
                storeId,
                page,
                size
        );

        return ApiResponse.success(missions, SuccessEnum.OK);
    }
    @GetMapping("/missions/me")
    public ApiResponse<Page<MyMissionResponse>> getMyInProgressMissions(
            @RequestParam Integer userId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") int size
    ) {
        if (page == null || page < 1) {
            page = 1;
        }

        Page<MyMissionResponse> missions = userMissionQueryService.getMyInProgressMissions(
                userId,
                page,
                size
        );

        return ApiResponse.success(missions, SuccessEnum.OK);
    }
}
