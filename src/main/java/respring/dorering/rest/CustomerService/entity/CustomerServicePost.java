package respring.dorering.rest.CustomerService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * CustomerServicePost 클래스는 고객 서비스 게시글의 JPA 엔티티입니다.
 * 데이터베이스의 customer_service 테이블에 매핑됩니다.
 */
@Getter
@Setter
@NoArgsConstructor // 기본 생성자 자동 생성
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자 자동 생성
@Builder // 빌더 패턴을 사용한 객체 생성을 위한 자동 생성
@Entity // JPA 엔티티임을 나타냄
@Table(name = "customer_service") // 데이터베이스 테이블 지정
public class CustomerServicePost {

    @Id // 기본키임을 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성 전략
    private Integer boardCode; // 게시글의 고유 식별자

    @Column(length = 45) // 칼럼 속성 지정
    private String title; // 게시글 제목

    @Column(length = 255) // 칼럼 속성 지정
    private String content; // 게시글 내용

    @Column(name = "posted_date") // 칼럼 이름 지정
    private LocalDateTime postedDate; // 게시된 날짜와 시간

    @Column(name = "user_code") // 칼럼 이름 지정
    private Integer userCode; // 사용자 코드

    @Column(name = "comment_date") // 칼럼 이름 지정
    private LocalDateTime commentDate; // 댓글 달린 날짜와 시간

    @Column(name = "comment") // 칼럼 이름 지정
    private String comment; // 댓글 내용
    // 필요한 경우 추가 메소드 및 생성자를 여기에 추가
}
