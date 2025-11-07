package org.example.boardbyrest.controller;

import lombok.RequiredArgsConstructor;
import org.example.boardbyrest.domain.User;
import org.example.boardbyrest.dto.UserDto;
import org.example.boardbyrest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        // TODO: 회원 생성
        User createdUser = userService.createUser(userDto);
        // TODO: 201 Created 응답 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        // TODO: 회원 삭제
        userService.deleteUser(id);
        // TODO: 204 No Content 응답 반환
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}