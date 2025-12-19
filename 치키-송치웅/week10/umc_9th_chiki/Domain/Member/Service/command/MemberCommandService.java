package com.example.umc_9th_chiki.Domain.Member.Service.command;

import com.example.umc_9th_chiki.Domain.Member.Dto.MemberReqDTO;
import com.example.umc_9th_chiki.Domain.Member.Dto.MemberResDTO;
import com.example.umc_9th_chiki.Domain.Member.Entity.Mapping.MemberMission;
import com.example.umc_9th_chiki.Domain.Member.Entity.Member;

public interface MemberCommandService {
    void completeMission(Long memberMissionId);
    MemberResDTO.JoinDTO signup(MemberReqDTO.JoinDTO dto);
}