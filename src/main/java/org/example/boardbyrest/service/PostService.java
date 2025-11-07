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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // ✅ 관리자/작성자 권한 확인 후 삭제
    @Transactional
    public void deletePost(Long id, User currentUser) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("게시글을 찾을 수 없습니다."));

        // ✅ 1️⃣ 관리자 권한 확인
        boolean isAdmin = currentUser.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        // ✅ 2️⃣ 작성자 본인 확인
        boolean isOwner = post.getUser().getId().equals(currentUser.getId());

        // ✅ 3️⃣ 둘 다 아니면 삭제 불가
        if (!isAdmin && !isOwner) {
            throw new AccessDeniedException("게시글 삭제 권한이 없습니다.");
        }

        postRepository.deleteById(id);
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

    // ✅ 제목으로 게시글 검색 (페이징 지원)
    public Page<Post> searchPosts(String keyword, Pageable pageable) {
        // TODO: 검색 로직 구현
        if (keyword == null || keyword.trim().isEmpty()) {
            return postRepository.findAll(pageable);
        }

        return postRepository.findByTitleContaining(keyword, pageable);
    }
}
