package com.example.umc_9th_chiki.Domain.Member.Controller;

import com.example.umc_9th_chiki.Domain.Member.Service.command.MemberCommandService;
import com.example.umc_9th_chiki.Domain.Member.Service.query.MemberQueryService;
import com.example.umc_9th_chiki.Domain.Mission.Dto.MissionResponseDTO;
import com.example.umc_9th_chiki.Domain.Review.Dto.ReviewResponseDTO;
import com.example.umc_9th_chiki.Domain.Review.Exception.code.ReviewSuccessCode;
import com.example.umc_9th_chiki.Global.apiPayload.ApiResponse;
import com.example.umc_9th_chiki.Global.apiPayload.code.GeneralSuccessCode;
import com.example.umc_9th_chiki.Global.validation.annotation.CheckPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated // @CheckPage 동작을 위해 필요
@RequestMapping("/members")
public class MemberController {

    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;

    @GetMapping("/{memberId}/reviews")
    // 구현 조건 4 : 반드시 모든 API에 대해 Swagger 명세를 해야 한다.
    @Operation(summary = "내가 쓴 리뷰 목록 조회 API", description = "멤버가 작성한 리뷰 목록 조회")
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디"),
            @Parameter(name = "page", description = "페이지 번호 (1 이상)")
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getMyReviewList(
            @PathVariable(name = "memberId") Long memberId,

            // 구현 조건 3 : 쿼리 스트링으로 받아오며, 커스텀 어노테이션(@CheckPage)으로 검증
            @CheckPage @RequestParam(name = "page") Integer page
    ){
        var result = memberQueryService.getMyReviewList(memberId, page - 1);
        return ApiResponse.onSuccess(ReviewSuccessCode.FOUND, result);
    }

    @GetMapping("/{memberId}/missions/challenging")
    @Operation(summary = "내가 진행 중인 미션 목록 조회 API", description = "멤버가 진행 중인 미션 목록 조회")
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디"),
            @Parameter(name = "page", description = "페이지 번호 (1 이상)")
    })
    public ApiResponse<MissionResponseDTO.MissionPreViewListDTO> getMyMissionList(
            @PathVariable(name = "memberId") Long memberId,
            @CheckPage @RequestParam(name = "page") Integer page
    ){
        var result = memberQueryService.getMyMissionList(memberId, page - 1);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    @PatchMapping("/missions/{memberMissionId}/complete")
    @Operation(summary = "미션 완료 처리 API", description = "진행 중인 미션을 완료(COMPLETE) 상태로 바꾼다")
    @Parameters({
            @Parameter(name = "memberMissionId", description = "미션 도전 기록의 ID")
    })
    public ApiResponse<String> completeMission(
            @PathVariable(name = "memberMissionId") Long memberMissionId
    ){
        memberCommandService.completeMission(memberMissionId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "미션 성공");
    }
}