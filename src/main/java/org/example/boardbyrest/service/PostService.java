package org.example.boardbyrest.service;

import lombok.RequiredArgsConstructor;
import org.example.boardbyrest.domain.Post;
import org.example.boardbyrest.domain.User;
import org.example.boardbyrest.dto.PostDto;
import org.example.boardbyrest.exception.PostNotFoundException;
import org.example.boardbyrest.exception.UserNotFoundException;
import org.example.boardbyrest.repository.PostRepository;
import org.example.boardbyrest.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Post createPost(PostDto postDto) {
        // TODO: Post 객체 생성 및 저장
        // 작성자 정보 확인
        User user = userRepository.findById(postDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("작성자를 찾을 수 없습니다."));

        // Post 객체 생성
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setUser(user);

        // 저장 및 반환
        return postRepository.save(post);

    }

    public Post updatePost(Long id, PostDto postDto) {
        // TODO: 게시글 찾기
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("해당 게시글을 찾을 수 없습니다."));
        // TODO: 제목, 내용 업데이트
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        // TODO: 저장
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        // TODO: 존재 여부 확인 후 삭제
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("해당 게시글을 찾을 수 없습니다."));
        postRepository.delete(post);
    }

    public Page<Post> getPosts(Pageable pageable) {
        // TODO: 페이징 처리된 목록 반환
        return postRepository.findAll(pageable);
    }

    public Post getPost(Long id) {
        // TODO: 특정 게시글 조회
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("해당 게시글을 찾을 수 없습니다."));
    }
}
