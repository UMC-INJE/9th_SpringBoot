package converter;

import dto.UserReqDto;
import dto.UserResDto;
import entity.FoodCategory;
import entity.SocialType;
import entity.User;

import java.time.LocalDateTime;

public class UserConverter {

    public static User toUser(UserReqDto.SignUpDto dto, FoodCategory category, String encodedPassword) {
        return User.builder()
                .foodCategory(category)
                .userName(dto.userName())
                .gender(dto.gender())
                .birthDate(dto.birthDate())
                .address(dto.address())
                .detailAddress(dto.detailAddress())
                .email(dto.email())
                .password(encodedPassword)
                .phone(dto.phone())
                .socialUid("LOCAL")      
                .socialType(SocialType.LOCAL) 
                .point(0)
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static UserResDto.SignUpDto toSignUpDTO(User user) {
        return UserResDto.SignUpDto.builder()
                .userId(user.getUserId())
                .createdAt(user.getUpdatedAt())
                .build();
    }

    public static UserResDto.LoginDto toLoginDto(User user) {
        return UserResDto.LoginDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .build();
    }
}
