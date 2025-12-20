package controller;

import dto.UserReqDto;
import dto.UserResDto;
import entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import repository.UserRepository;
import response.ApiResponse;
import response.SuccessEnum;
import security.JwtTokenProvider;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    // JWT 로그인
    @PostMapping("/jwt-login")
    public ApiResponse<String> jwtLogin(
            @RequestBody UserReqDto.LoginDto dto
    ) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.password())
        );

        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        String token = jwtTokenProvider.createToken(
                user.getEmail(),
                auth.getAuthorities()
        );

        // 응답: 순수 토큰 문자열
        return ApiResponse.success(token, SuccessEnum.OK);
    }
}
