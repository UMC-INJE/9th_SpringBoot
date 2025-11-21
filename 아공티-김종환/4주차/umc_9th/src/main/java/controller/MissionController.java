package controller;

import dto.MissionChallengeReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import service.MissionChallengeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionChallengeService missionChallengeService;

    @PostMapping("/{storeId}/missions/challenge")
    public String challengeMission(
            @PathVariable Integer storeId,
            @RequestBody MissionChallengeReqDto dto
    ) {
        missionChallengeService.challengeMission(storeId, dto);
        return "미션 도전 완료!";
    }
}
