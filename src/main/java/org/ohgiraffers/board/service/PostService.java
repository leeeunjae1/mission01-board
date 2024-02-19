package org.ohgiraffers.board.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.ohgiraffers.board.domain.dto.CreatePostRequest;
import org.ohgiraffers.board.domain.dto.CreatePostResponse;
import org.ohgiraffers.board.domain.dto.ReadPostResponse;
import org.ohgiraffers.board.domain.entity.Post;
import org.ohgiraffers.board.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Service 를 인터페이스와 구현체로 나누는 이유
 *  1. 다형성과 OCP 원칙을 지키기 위해서이다.
 *  인터페이스와 구현체가 나누어지면, 구현체는 외부로부터 독립되어, 구현체의 수정이나 확장이 자유로워진다.
 *  2. 관습적인 추상화 방식
 *  과거, Spring에서 AOP를 구현할 때 JDK Dynamic Proxy를 사용했는데, 이 때 인터페이스가 필수였다.
 *  지금은, CGLB를 기본적으로 포함하여 클래스 기반을 프록시 객체를 생성할 수 있게 되었다.
 */
/** @Transactional
 * 선언적으로 트랜젝션 관리를 가능하게 해준다.
 * 메서드가 실행되는 동안 모든 데이터베이스 연산을 하나의 트랜젝션으로 묶어 처리한다.
 * 이를 통해, 메서드 내에서 데이터베이스 상태를 변경하는 작업들이 모두 성공적으로 완료되면 그 변경사항을 commit 하고
 * 하나라도 실패하면 모든 변경사항을 rollback 시켜 관리한다.
 *
 * Transaction
 * 데이터베이스의 상태를 변화시키기 위해 수항하는 작업의 단위
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    @Transactional
    public CreatePostResponse createPost(CreatePostRequest request) {

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        Post savedPost = postRepository.save(post);

        return new CreatePostResponse(savedPost.getPostId(), savedPost.getTitle(), savedPost.getContent());
    }

    public ReadPostResponse readPostById(Long postId) {
        Post foundPost = postRepository.findById(postId) // postId가 진짜 있을지 없을지 모른다. 따라서 예외처리 생성
                .orElseThrow(() -> new EntityNotFoundException("해당 postId로 조회된 게시글이 없습니다."));

        return new ReadPostResponse(foundPost.getPostId(), foundPost.getTitle(), foundPost.getContent());
    }
}

