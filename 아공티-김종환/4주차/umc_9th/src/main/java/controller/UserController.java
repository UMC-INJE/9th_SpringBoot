package controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import dto.UserReqDto;
import dto.UserResDto;
import response.ApiResponse;
import response.SuccessEnum;
import service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ApiResponse<UserResDto.SignUpDto> signUp(
            @RequestBody UserReqDto.SignUpDto dto
    ) {
        return ApiResponse.success(
                userService.signup(dto),
                SuccessEnum.CREATED
        );
    }
}
