package respring.dorering.rest.auth.repository;
// 사용자 정보에 접근하기 위한 JPA 리포지토리입니다.
// JpaRepository를 상속받아 기본적인 CRUD 연산과 함께 필요한 쿼리 메서드를 정의합니다.

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import respring.dorering.rest.auth.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 사용자 ID로 사용자 존재 여부를 확인합니다.
    boolean existsByUserId(String userId);
    // 전화번호로 사용자 존재 여부를 확인합니다.
    boolean existsByPhone(String phone);

    // 사용자 ID로 사용자 정보를 조회합니다. 탈퇴한 사용자는 조회에서 제외됩니다.
    @Query("SELECT u FROM User u WHERE u.userId = :userId AND (u.withdrawalStatus IS NULL or u.withdrawalStatus = 'N')")
    Optional<User> findByUserId(@Param("userId") String userId );

    // 사용자 이름과 전화번호로 사용자 정보를 조회합니다.
    @Query("SELECT u FROM User u WHERE u.userName = :userName AND u.phone = :phoneNum")
    User findUserByUserNameAndPhone(@Param("userName") String userName, @Param("phoneNum") String phoneNum);

    // 사용자 이름, ID, 전화번호로 사용자 정보를 조회합니다.
    @Query("SELECT u FROM User u WHERE u.userName = :userName AND u.userId = :userId AND u.phone = :phoneNum")
    User findUserByUserNameAndUserIdAndPhone(@Param("userName") String userName, @Param("userId") String userId, @Param("phoneNum") String phoneNum);

    // 탈퇴한 사용자 중 가장 최근에 탈퇴한 사용자를 전화번호로 조회합니다.
    @Query("SELECT u FROM User u WHERE u.phone = :phone AND u.withdrawalStatus = 'Y' ORDER BY u.withdrawalDate DESC")
    Optional<User> findLatestWithdrawnByPhone(@Param("phone") String phone);

    // 탈퇴한 지 30일이 넘은 사용자를 삭제합니다.
    @Modifying
    @Query(value = "DELETE FROM User u WHERE u.withdrawalStatus = 'Y' AND u.withdrawalDate <= DATE_SUB(CURRENT_DATE, INTERVAL 30 DAY)", nativeQuery = true)
    void deleteWithdrawnUsersOlderThanOneMonth();
}
