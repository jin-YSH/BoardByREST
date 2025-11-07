package org.example.boardbyrest.repository;

import org.example.boardbyrest.domain.Comment;
import org.example.boardbyrest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    // 특정 게시글에 달린 댓글 전체 조회
    List<Comment> findByPostId(Long postId);

    // 특정 유저가 작성한 댓글 조회
    List<Comment> findByUserId(Long userId);


}
