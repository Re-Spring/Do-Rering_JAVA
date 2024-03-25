package respring.dorering.rest.CustomerService.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.time.LocalDateTime;

/**
 * CustomerServicePostDto는 고객 서비스 게시글의 생성과 조회를 위해 데이터 전송에 사용되는 객체입니다.
 * 이 DTO는 클라이언트와 서버 간의 데이터 교환을 위해 사용됩니다.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CustomerServicePostDto {
    private Integer boardCode; // 게시글의 고유 식별자입니다.
    private String title; // 게시글의 제목입니다.
    private String content; // 게시글의 내용입니다.
    private LocalDateTime postedDate; // 게시글이 게시된 날짜와 시간입니다.
    private Integer userCode; // 게시글을 작성한 사용자의 코드입니다.
    private LocalDateTime commentDate;
    private Integer comment;

    // 기본 생성자
    public CustomerServicePostDto(Integer boardCode, String title, String content, LocalDateTime postedDate, Integer userCode) {
    }


}
