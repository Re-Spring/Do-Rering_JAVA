package respring.dorering.rest.fairytale.dto;

import lombok.*;

// Lombok 어노테이션을 사용하여 getter, setter, 생성자를 자동으로 생성합니다.
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailDTO {
    // DTO는 클라이언트가 요청한 데이터를 전송하기 위한 객체입니다.
    // 여기서는 클라이언트에게 전달할 데이터 필드를 정의합니다.
    private Integer detailCode;       // 상세 정보 코드
    private String summary; //요약줄거리
    private String fairytaleTitle; //제목
    private String fairytaleThumb;    // 동화 표지 이미지 경로
    private String fairytaleGenre;    // 동화 장르
    // 다른 필드들도 필요에 따라 여기에 추가할 수 있습니다.
}
