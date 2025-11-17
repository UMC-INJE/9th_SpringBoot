package service;

import dto.UserReqDto;
import dto.UserResDto;

public interface UserService {
    UserResDto.SignUpDto signup(UserReqDto.SignUpDto dto);
}
