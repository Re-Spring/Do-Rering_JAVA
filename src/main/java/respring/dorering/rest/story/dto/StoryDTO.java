package respring.dorering.rest.story.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoryDTO {
    private Integer fairytaleCode; // 'fairytaleFileCode'에서 변경됨
    private Integer userCode; // 새로운 속성
    private String fairytaleSummary; // 'summary'에서 변경됨
    private String fairytaleTitle; // 변경 필요 없음
    private String fairytaleGenre; // 변경 필요 없음
    private String fairytaleThumb; // 'fairytaleStory'에서 변경됨
    private String fairytaleImage; // 새로운 속성

    // 만약 @AllArgsConstructor를 사용하지 않고 직접 생성자를 정의하려면 다음과 같이 할 수 있습니다.
    public StoryDTO(Integer fairytaleCode, String fairytaleTitle, String fairytaleGenre, String fairytaleThumb, String fairytaleSummary) {
        this.fairytaleCode = fairytaleCode;
        this.fairytaleTitle = fairytaleTitle;
        this.fairytaleGenre = fairytaleGenre;
        this.fairytaleThumb = fairytaleThumb;
        this.fairytaleSummary = fairytaleSummary;
    }
}
