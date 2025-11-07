package org.example.boardbyrest.service;

import lombok.RequiredArgsConstructor;
import org.example.boardbyrest.domain.Comment;
import org.example.boardbyrest.domain.Post;
import org.example.boardbyrest.domain.User;
import org.example.boardbyrest.dto.CommentDto;
import org.example.boardbyrest.exception.CommentNotFoundException;
import org.example.boardbyrest.exception.PostNotFoundException;
import org.example.boardbyrest.exception.UserNotFoundException;
import org.example.boardbyrest.repository.CommentRepository;
import org.example.boardbyrest.repository.PostRepository;
import org.example.boardbyrest.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Comment createComment(CommentDto commentDto) {
        // TODO: Comment 객체 생성 및 저장
        // 게시글 확인
        Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("해당 게시글을 찾을 수 없습니다."));

        // 작성자 확인
        User user = userRepository.findById(commentDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("작성자를 찾을 수 없습니다."));

        // Comment 객체 생성
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setDelYn(false);
        comment.setRegdate(LocalDateTime.now());
        comment.setPost(post);
        comment.setUser(user);

        // 저장 후 반환
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        // TODO: 특정 게시글의 댓글 목록 조회
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("해당 게시글을 찾을 수 없습니다."));

        return commentRepository.findAll().stream()
                .filter(c -> c.getPost().equals(post) && !c.getDelYn())
                .toList();
    }

    public void softDeleteComment(Long id) {
        // TODO: 댓글 찾기
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("해당 댓글을 찾을 수 없습니다."));
        // TODO: del_yn을 true로 설정
        comment.setDelYn(true);
        // TODO: 저장
        commentRepository.save(comment);
    }
}