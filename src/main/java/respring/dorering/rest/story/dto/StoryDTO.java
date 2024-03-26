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
    private Integer videoFileCode; // 타입을 Integer로 변경

    public StoryDTO(Integer fairytaleCode, String fairytaleTitle, String fairytaleGenre, String fairytaleThumb, String fairytaleSummary, Integer videoFileCode) {
        this.fairytaleCode = fairytaleCode;
        this.fairytaleTitle = fairytaleTitle;
        this.fairytaleGenre = fairytaleGenre;
        this.fairytaleThumb = fairytaleThumb;
        this.fairytaleSummary = fairytaleSummary;
        this.videoFileCode = videoFileCode; // 초기화 변경
    }
}
