package respring.dorering.rest.story.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoryDTO {
    private Integer fairytale_file_code;
    private String fairytale_code;
    private String fairytale_thumb;
    private String fairytale_story;
    private String summary;
    private String fairytale_title;

}

