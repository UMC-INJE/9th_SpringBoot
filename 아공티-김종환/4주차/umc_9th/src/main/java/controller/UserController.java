package controller;

import dto.UserReqDto;
import dto.UserResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import response.ApiResponse;
import response.SuccessEnum;
import service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ApiResponse<UserResDto.SignUpDto> signUp(
            @RequestBody UserReqDto.SignUpDto dto
    ) {
        return ApiResponse.success(
                userService.signup(dto),
                SuccessEnum.CREATED
        );
    }

    // 세션 방식 로그인
    @PostMapping("/login")
    public ApiResponse<UserResDto.LoginDto> login(
            @RequestBody UserReqDto.LoginDto dto
    ) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.password())
        );

        // 여기까지 오면 세션에 인증 정보가 들어감
        // auth.getPrincipal() 에 CustomUserDetails
        return ApiResponse.success(
                (UserResDto.LoginDto) null,  // 필요하면 UserConverter로 응답 구성
                SuccessEnum.OK
        );
    }
}
