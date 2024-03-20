package respring.dorering.rest.CustomerService.service;

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

    public List<CustomerServicePost> findAllPosts() {
        return customerServicePostRepository.findAll();
    }

    public Optional<CustomerServicePost> findPostById(Integer id) {
        return customerServicePostRepository.findById(id);
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
    public CustomerServicePost updatePost(Integer id, CustomerServicePost postUpdateRequest) {
        CustomerServicePost existingPost = customerServicePostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 게시글이 존재하지 않습니다: " + id));
        existingPost.setTitle(postUpdateRequest.getTitle());
        existingPost.setContent(postUpdateRequest.getContent());
        return customerServicePostRepository.save(existingPost);
    }

    @Transactional
    public void deletePost(Integer id) {
        customerServicePostRepository.deleteById(id);
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
