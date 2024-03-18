package respring.dorering.rest.story.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoryDTO {
    private Integer fairytaleCode;
    private Integer userCode;
    private String fairytaleSummary;
    private String fairytaleTitle;
    private String fairytaleGenre;
    private String fairytaleThumb;
    private String fairytaleImage;

    // 만약 @AllArgsConstructor를 사용하지 않고 직접 생성자를 정의하려면 다음과 같이 할 수 있습니다.
    public StoryDTO(Integer fairytaleCode, String fairytaleTitle, String fairytaleGenre, String fairytaleThumb, String fairytaleSummary) {
        this.fairytaleCode = fairytaleCode;
        this.fairytaleTitle = fairytaleTitle;
        this.fairytaleGenre = fairytaleGenre;
        this.fairytaleThumb = fairytaleThumb;
        this.fairytaleSummary = fairytaleSummary;
    }
}
