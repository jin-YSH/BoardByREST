package org.example.boardbyrest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.boardbyrest.domain.Comment;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private Long id;
    private String content;
    private Boolean delYn;
    private LocalDateTime regdate;
    private Long postId;
    private Long userId;

    // 엔티티 → DTO 변환
    public static CommentDto fromEntity(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .delYn(comment.getDelYn())
                .regdate(comment.getRegdate())
                .postId(comment.getPost().getId())
                .userId(comment.getUser().getId())
                .build();
    }
}
