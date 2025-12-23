package com.example.umc_9th_chiki.Domain.Mission.Controller;

import com.example.umc_9th_chiki.Domain.Mission.Dto.MissionRequestDTO;
import com.example.umc_9th_chiki.Domain.Mission.Dto.MissionResponseDTO;
import com.example.umc_9th_chiki.Global.apiPayload.ApiResponse;
import com.example.umc_9th_chiki.Global.Annotation.CheckPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface MissionControllerDocs {

    @Operation(summary = "미션 도전하기 API", description = "미션 도전")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    ApiResponse<MissionResponseDTO.ChallengeMissionResultDto> challengeMission(
            @PathVariable(name = "missionId") Long missionId,
            @RequestBody @Valid MissionRequestDTO.ChallengeMissionDto request
    );

    @Operation(summary = "가게 미션 목록 조회 API", description = "가게 미션 목록 조회")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디"),
            @Parameter(name = "page", description = "페이지 번호 (1 이상)")
    })
    ApiResponse<MissionResponseDTO.MissionPreViewListDTO> getMissionList(
            @PathVariable(name = "storeId") Long storeId,
            @CheckPage @RequestParam(name = "page") Integer page
    );
}