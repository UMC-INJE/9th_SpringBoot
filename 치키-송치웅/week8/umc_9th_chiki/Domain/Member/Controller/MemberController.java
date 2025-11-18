package com.example.umc_9th_chiki.Domain.Member.Controller;

import com.example.umc_9th_chiki.Domain.Member.Dto.MemberReqDTO;
import com.example.umc_9th_chiki.Domain.Member.Dto.MemberResDTO;
import com.example.umc_9th_chiki.Domain.Member.Exception.code.MemberSuccessCode;
import com.example.umc_9th_chiki.Global.apiPayload.ApiResponse;
import com.example.umc_9th_chiki.service.command.MemberCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;

    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponse<MemberResDTO.JoinDTO> signUp(
            @RequestBody MemberReqDTO.JoinDTO dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberCommandService.signup(dto));
    }
}