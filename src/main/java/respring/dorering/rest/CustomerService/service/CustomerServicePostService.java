package respring.dorering.rest.CustomerService.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import respring.dorering.rest.CustomerService.dto.CustomerServicePostDto;
import respring.dorering.rest.CustomerService.entity.CustomerServicePost;
import respring.dorering.rest.CustomerService.repository.CustomerServicePostRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServicePostService {

    private final CustomerServicePostRepository customerServicePostRepository;

    @Autowired
    public CustomerServicePostService(CustomerServicePostRepository customerServicePostRepository) {
        this.customerServicePostRepository = customerServicePostRepository;
    }

    // 수정할 부분: 서비스 메소드에 Pageable 파라미터 추가
    public Page<CustomerServicePost> findAllPosts(Pageable pageable) {
        return customerServicePostRepository.findAll(pageable);
    }

    public Optional<CustomerServicePost> findPostById(Integer id) {
        return customerServicePostRepository.findById(id);
    }

    // 게시글과 댓글을 포함하여 반환하는 메소드
    public Optional<CustomerServicePostDto> findPostWithCommentsById(Integer boardCode) {
        Optional<CustomerServicePost> postOptional = customerServicePostRepository.findById(boardCode);
        if (postOptional.isPresent()) {
            CustomerServicePost post = postOptional.get();
            CustomerServicePostDto postDto = convertToDto(post);
            // TODO: 댓글 정보를 postDto에 추가하는 로직 구현
            // 예: postDto.setComments(commentRepository.findByPostId(post.getBoardCode()));
            return Optional.of(postDto);
        }
        return Optional.empty();
    }

    @Transactional
    public CustomerServicePostDto createPost(CustomerServicePostDto postDto) {
        CustomerServicePost post = new CustomerServicePost();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setPostedDate(LocalDateTime.now()); // 날짜는 서버 시간으로 설정
        post.setUserCode(postDto.getUserCode());
        CustomerServicePost savedPost = customerServicePostRepository.save(post);

        return new CustomerServicePostDto(
                savedPost.getBoardCode(),
                savedPost.getTitle(),
                savedPost.getContent(),
                savedPost.getPostedDate(),
                savedPost.getUserCode()
        );
    }

    @Transactional
    public CustomerServicePost updatePost(Integer boardCode, CustomerServicePost postUpdateRequest) {
        CustomerServicePost existingPost = findPostById(boardCode)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다: " + boardCode));
        existingPost.setTitle(postUpdateRequest.getTitle());
        existingPost.setContent(postUpdateRequest.getContent());
        return customerServicePostRepository.save(existingPost);
    }

    @Transactional
    public void deletePost(Integer boardCode) {
        if (!customerServicePostRepository.existsById(boardCode)) {
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다: " + boardCode);
        }
        customerServicePostRepository.deleteById(boardCode);
    }


    /**
     * user_code를 기반으로 특정 게시글을 조회하는 메서드입니다.
     * @param userCode 조회할 게시글의 user_code
     * @return Optional 객체로 감싸진 CustomerServicePostDto
     */
    public Optional<CustomerServicePostDto> findPostByUserCode(Integer userCode) {
        return customerServicePostRepository.findByUserCode(userCode)
                .map(this::convertToDto);
    }

    /**
     * CustomerServicePost 엔티티를 CustomerServicePostDto DTO로 변환합니다.
     * @param post 변환할 게시글 엔티티
     * @return 변환된 CustomerServicePostDto
     */
    private CustomerServicePostDto convertToDto(CustomerServicePost post) {
        return new CustomerServicePostDto(
                post.getBoardCode(),
                post.getTitle(),
                post.getContent(),
                post.getPostedDate(),
                post.getUserCode()
        );
    }
}