package converter;



import dto.UserReqDto;
import dto.UserResDto;
import entity.FoodCategory;
import entity.User;

public class UserConverter {

    // DTO -> Entity
    public static User toUser(UserReqDto.SignUpDto dto, FoodCategory category) {
        return User.builder()
                .userName(dto.userName())
                .gender(dto.gender())
                .birthDate(dto.birthDate())
                .address(dto.address())
                .detailAddress(dto.detailAddress())
                .socialUid(dto.socialUid())
                .socialType(entity.SocialType.valueOf(dto.socialType()))
                .email(dto.email())
                .phone(dto.phone())
                .point(0)
                .foodCategory(category)
                .updatedAt(java.time.LocalDateTime.now())
                .build();
    }

    // Entity -> DTO
    public static UserResDto.SignUpDto toSignUpDTO(User user) {
        return UserResDto.SignUpDto.builder()
                .userId(user.getUserId())
                .createdAt(user.getUpdatedAt())
                .build();
    }
}
