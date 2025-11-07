package org.example.boardbyrest.repository;

import org.example.boardbyrest.domain.Post;
import org.example.boardbyrest.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    // 특정 사용자의 게시글 목록
    List<Post> findByUserId(Long userId);

    // 제목으로 검색
    Page<Post> findByTitleContaining(String keyword, Pageable pageable);

}
