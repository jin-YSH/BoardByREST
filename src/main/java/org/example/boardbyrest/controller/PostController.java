package org.example.boardbyrest.controller;

import lombok.RequiredArgsConstructor;
import org.example.boardbyrest.domain.Post;
import org.example.boardbyrest.domain.User;
import org.example.boardbyrest.dto.PostDto;
import org.example.boardbyrest.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        // TODO: 게시글 생성
        Post created = postService.createPost(postDto);
        URI location = URI.create("/posts/" + created.getId());
        return ResponseEntity.created(location).body(PostDto.fromEntity(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(
            @PathVariable Long id,
            @RequestBody PostDto postDto) {
        // TODO: 게시글 수정
        Post updated = postService.updatePost(id, postDto);
        return ResponseEntity.ok(PostDto.fromEntity(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id,@AuthenticationPrincipal User currentUser) {
        // TODO: 게시글 삭제
        postService.deletePost(id, currentUser);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<PostDto>> getPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        // TODO: 페이징 처리된 목록 반환
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "postedDate"));
        Page<Post> posts = postService.getPosts(pageable);
        Page<PostDto> dtoPage = posts.map(PostDto::fromEntity);
        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long id) {
        // TODO: 특정 게시글 조회
        Post post = postService.getPost(id);
        return ResponseEntity.ok(PostDto.fromEntity(post));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Post>> searchPosts(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        // TODO: 검색 API 구현
        Page<Post> result = postService.searchPosts(keyword, PageRequest.of(page, size));

        return ResponseEntity.ok(result);
    }
}
