package org.example.boardbyrest.service;

import lombok.RequiredArgsConstructor;
import org.example.boardbyrest.domain.User;
import org.example.boardbyrest.dto.UserDto;
import org.example.boardbyrest.exception.DuplicateUserException;
import org.example.boardbyrest.exception.UserNotFoundException;
import org.example.boardbyrest.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(UserDto userDto) {
        // TODO: loginId 중복 체크
        if (userRepository.existsByLoginId(userDto.getLoginId())) {
            throw new DuplicateUserException("이미 존재하는 로그인 ID입니다: " + userDto.getLoginId());
        }
        // TODO: User 객체 생성
        User user = new User();
        user.setName(userDto.getName());
        user.setLoginId(userDto.getLoginId());
        user.setPassword(userDto.getPassword()); // 실제로는 BCrypt로 암호화 권장
        user.setEmail(userDto.getEmail());

        // TODO: 저장 후 반환
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        // TODO: 존재 여부 확인
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 회원입니다. ID: " + id));
        // TODO: 삭제
        userRepository.delete(user);
    }
}
