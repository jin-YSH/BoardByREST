package org.example.boardbyrest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.boardbyrest.domain.Post;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime postedDate;
    private Long userId;

    // 엔티티 → DTO 변환
    public static PostDto fromEntity(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .postedDate(post.getPostedDate())
                .userId(post.getUser().getId())
                .build();
    }
}
