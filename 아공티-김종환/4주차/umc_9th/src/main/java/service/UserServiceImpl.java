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
        
        FoodCategory category = foodCategoryRepository.findById(dto.foodCategoryId())
                .orElseThrow(() -> new RuntimeException("해당 카테고리를 찾을 수 없음"));

        
        User user = UserConverter.toUser(dto, category);

        userRepository.save(user);

        return UserConverter.toSignUpDTO(user);
    }
}
