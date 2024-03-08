package respring.dorering.rest.story.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoryDTO {
    private Integer fairytaleFileCode; // 변경됨
    private String fairytaleCode; // 변경됨
    private String fairytaleThumb; // 변경됨
    private String fairytaleStory; // 변경됨
    private String summary;
    private String fairytaleTitle; // 변경됨
    private String fairytaleGenre; // 변경됨

}

