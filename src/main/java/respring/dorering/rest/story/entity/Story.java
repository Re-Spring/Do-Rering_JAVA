package respring.dorering.rest.story.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fairytale_info")
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fairytale_file_code")
    private Integer fairytaleFileCode;

    @Column(name = "fairytale_code", nullable = false, unique = true)
    private String fairytaleCode;

    @Column(name = "fairytale_thumb", nullable = false)
    private String fairytaleThumb;

    @Column(name = "fairytale_story", length = 10000)
    private String fairytaleStory;

    @Column(name = "summary", length = 500)
    private String summary;

    @Column(name = "fairytale_title", nullable = false)
    private String fairytaleTitle;

    @Column(name = "fairytale_genre", nullable = false)
    private String fairytaleGenre;
}
