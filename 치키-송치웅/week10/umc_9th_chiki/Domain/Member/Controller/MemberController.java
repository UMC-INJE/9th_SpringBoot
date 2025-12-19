package com.example.umc_9th_chiki.Domain.Member.Controller;

import com.example.umc_9th_chiki.Domain.Member.Dto.MemberReqDTO;
import com.example.umc_9th_chiki.Domain.Member.Dto.MemberResDTO;
import com.example.umc_9th_chiki.Domain.Member.Service.command.MemberCommandService;
import com.example.umc_9th_chiki.Domain.Member.Service.query.MemberQueryService;
import com.example.umc_9th_chiki.Domain.Mission.Dto.MissionResponseDTO;
import com.example.umc_9th_chiki.Global.Auth.Model.CustomUserDetails;
import com.example.umc_9th_chiki.Global.apiPayload.ApiResponse;
import com.example.umc_9th_chiki.Global.apiPayload.code.GeneralSuccessCode;
import com.example.umc_9th_chiki.Global.Annotation.CheckPage;
import com.example.umc_9th_chiki.Global.Security.Jwt.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/members")
public class MemberController {

    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil; // 1. JwtUtil 의존성 주입 추가

    // 1. 회원가입 API
    @PostMapping("/signup")
    @Operation(summary = "회원가입 API", description = "신규 멤버를 등록합니다.")
    public ApiResponse<MemberResDTO.JoinDTO> signUp(@RequestBody @Valid MemberReqDTO.JoinDTO dto) {
        MemberResDTO.JoinDTO result = memberCommandService.signup(dto);
        return ApiResponse.onSuccess(
                GeneralSuccessCode.CREATED,
                result
        );
    }

    @PostMapping("/login")
    @Operation(summary = "로그인 API", description = "이메일과 비밀번호로 인증하고 실제 JWT 토큰을 반환합니다.")
    public ApiResponse<MemberResDTO.LoginResultDTO> login(@RequestBody @Valid MemberReqDTO.LoginDTO request) {

        // 1. 인증 토큰 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
        );

        // 2. 인증 수행
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        // 3. Principal 객체를 CustomUserDetails로 캐스팅
        com.example.umc_9th_chiki.Global.Auth.Model.CustomUserDetails userDetails =
                (com.example.umc_9th_chiki.Global.Auth.Model.CustomUserDetails) authentication.getPrincipal();

        // 4. [변수 선언 확인] jwtToken 변수를 생성합니다.
        String jwtToken = jwtUtil.createAccessToken(userDetails);

        // 5. 응답 반환 (위에서 선언한 jwtToken을 여기서 사용)
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                MemberResDTO.LoginResultDTO.builder()
                        .accessToken(jwtToken)
                        .build()
        );
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
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "미션을 성공적으로 완료했습니다!");
    }
}