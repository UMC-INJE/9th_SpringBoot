package service;

import converter.UserConverter;
import dto.UserReqDto;
import dto.UserResDto;
import entity.FoodCategory;
import entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.FoodCategoryRepository;
import repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    @Transactional
    public UserResDto.SignUpDto signup(UserReqDto.SignUpDto dto) {

        // 카테고리 가져오기
        FoodCategory category = foodCategoryRepository.findById(dto.foodCategoryId())
                .orElseThrow(() -> new RuntimeException("해당 카테고리를 찾을 수 없음"));

        // DTO -> Entity 변환
        User user = UserConverter.toUser(dto, category);

        // DB 저장
        userRepository.save(user);

        // Entity -> DTO 변환하여 반환
        return UserConverter.toSignUpDTO(user);
    }
}
