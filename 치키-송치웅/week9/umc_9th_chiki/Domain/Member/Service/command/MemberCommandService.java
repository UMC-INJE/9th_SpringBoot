package com.example.umc_9th_chiki.Domain.Member.Service.command;

import com.example.umc_9th_chiki.Domain.Member.Entity.Mapping.MemberMission;

public interface MemberCommandService {
    void completeMission(Long memberMissionId);
}