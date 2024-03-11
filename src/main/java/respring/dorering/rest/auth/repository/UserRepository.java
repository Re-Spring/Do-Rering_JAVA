package respring.dorering.rest.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import respring.dorering.rest.auth.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUserId(String userId);
    boolean existsByPhone(String phone);

    @Query("SELECT u FROM User u WHERE u.userId = :userId AND u.withdrawalStatus IS NULL")
    Optional<User> findByUserId(@Param("userId") String userId);

    @Query("SELECT u FROM User u WHERE u.userName = :userName AND u.phone = :phoneNum")
    User findUserByUserNameAndPhone(@Param("userName") String userName, @Param("phoneNum") String phoneNum);

    @Query("SELECT u FROM User u WHERE u.userName = :userName AND u.userId = :userId AND u.phone = :phoneNum")
    User findUserByUserNameAndUserIdAndPhone(@Param("userName") String userName, @Param("userId") String userId, @Param("phoneNum") String phoneNum);
}