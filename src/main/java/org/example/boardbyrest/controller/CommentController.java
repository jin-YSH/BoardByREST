package org.example.boardbyrest.controller;
import lombok.RequiredArgsConstructor;
import org.example.boardbyrest.domain.Comment;
import org.example.boardbyrest.domain.Post;
import org.example.boardbyrest.dto.CommentDto;
import org.example.boardbyrest.dto.PostDto;
import org.example.boardbyrest.service.CommentService;
import org.example.boardbyrest.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody CommentDto commentDto) {
        // TODO: 댓글 생성
        Comment createdComment = commentService.createComment(commentDto);
        return ResponseEntity.status(201).body(createdComment); // 201 Created
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getComments(
            @RequestParam Long postId) {
        // TODO: 댓글 목록 조회
        List<Comment> comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(comments); // 200 OK
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        // TODO: 댓글 소프트 삭제
        commentService.softDeleteComment(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}