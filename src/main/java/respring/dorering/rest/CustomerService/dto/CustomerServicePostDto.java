package respring.dorering.rest.CustomerService.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * CustomerServicePostDto는 고객 서비스 게시글의 생성과 조회를 위해 데이터 전송에 사용되는 객체입니다.
 * 이 DTO는 클라이언트와 서버 간의 데이터 교환을 위해 사용됩니다.
 */
@Getter
@Setter
@ToString
@Builder
public class CustomerServicePostDto {
    private Long boardCode; // 게시글의 고유 식별자입니다.
    private String title; // 게시글의 제목입니다.
    private String content; // 게시글의 내용입니다.
    private LocalDateTime postedDate; // 게시글이 게시된 날짜와 시간입니다.
    private int userCode; // 게시글을 작성한 사용자의 코드입니다.
    private LocalDateTime commentDate;
    private int comment;

    // 기본 생성자
    public CustomerServicePostDto(Integer boardCode, String title, String content, LocalDateTime postedDate, Integer userCode) {
    }

    // 모든 필드를 포함하는 생성자
    public CustomerServicePostDto(Long boardCode, String title, String content, LocalDateTime postedDate, int userCode, LocalDateTime commentDate, int comment) {
        this.boardCode = boardCode;
        this.title = title;
        this.content = content;
        this.postedDate = postedDate;
        this.userCode = userCode;
        this.commentDate = commentDate;
        this.comment = comment;
    }



}
