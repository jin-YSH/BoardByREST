package org.example.boardbyrest.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorResponse("USER_NOT_FOUND", ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePostNotFound(PostNotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorResponse("POST_NOT_FOUND", ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCommentNotFound(CommentNotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorResponse("COMMENT_NOT_FOUND", ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateUser(DuplicateUserException ex) {
        return new ResponseEntity<>(
                new ErrorResponse("DUPLICATE_USER", ex.getMessage()),
                HttpStatus.CONFLICT
        );
    }

    // 예외 처리 누락 방지용 (기타 예상치 못한 예외 처리)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        return new ResponseEntity<>(
                new ErrorResponse("INTERNAL_SERVER_ERROR", "서버 내부 오류가 발생했습니다."),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @Data
    @AllArgsConstructor
    public static class ErrorResponse {
        private String code;     // 예외 코드 (예: USER_NOT_FOUND)
        private String message;  // 사용자에게 보여줄 메시지
    }
}
