package com.example.umc_9th_chiki.Global.Auth.Service;

import com.example.umc_9th_chiki.Domain.Member.Exception.code.MemberErrorCode;
import com.example.umc_9th_chiki.Domain.Member.Exception.MemberException;
import com.example.umc_9th_chiki.Domain.Member.Repository.MemberRepository;
import com.example.umc_9th_chiki.Global.Auth.Model.CustomUserDetails;
import com.example.umc_9th_chiki.Domain.Member.Entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(
            String username
    ) throws UsernameNotFoundException {
        // 검증할 Member 조회
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        // CustomUserDetails 반환
        return new CustomUserDetails(member);
    }
}
