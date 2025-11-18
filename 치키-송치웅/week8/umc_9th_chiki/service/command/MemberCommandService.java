package com.example.umc_9th_chiki.service.command;

import com.example.umc_9th_chiki.Domain.Member.Dto.MemberReqDTO;
import com.example.umc_9th_chiki.Domain.Member.Dto.MemberResDTO;

public interface MemberCommandService {
    MemberResDTO.JoinDTO signup(MemberReqDTO.JoinDTO dto);
}