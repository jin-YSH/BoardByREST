package org.example.boardbyrest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.boardbyrest.domain.User;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String loginId;
    private String password;
    private String email;
    private LocalDateTime joinedDate;

    // 엔티티 → DTO 변환
    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .loginId(user.getLoginId())
                .password(user.getPassword())
                .email(user.getEmail())
                .joinedDate(user.getJoinedDate())
                .build();
    }

    // DTO → 엔티티 변환
    public User toEntity() {
        User user = new User();
        user.setId(this.id);
        user.setName(this.name);
        user.setLoginId(this.loginId);
        user.setPassword(this.password);
        user.setEmail(this.email);
        user.setJoinedDate(this.joinedDate);
        return user;
    }
}
