package respring.dorering.rest.CustomerService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import respring.dorering.rest.CustomerService.entity.CustomerServicePost;
import java.util.Optional;

public interface CustomerServicePostRepository extends JpaRepository<CustomerServicePost, Integer> {

    /**
     * user_code를 사용하여 게시글을 찾는 메서드입니다.
     * @param userCode 조회할 게시글의 user_code
     * @return Optional 타입의 CustomerServicePost
     */
    Optional<CustomerServicePost> findByUserCode(Integer userCode);
}
