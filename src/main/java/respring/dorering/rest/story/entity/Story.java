package respring.dorering.rest.story.entity;



import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@Table(name = "fairytale_info")
@NoArgsConstructor
@AllArgsConstructor
public class Story{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fairytale_file_code")
    private Integer fairytaleFileCode;
    @Column(name = "fairytale_code")
    private String fairytaleCode;
    @Column(name = "fairytale_thumb")
    private String fairytaleThumb;
    @Column(name = "fairytale_story")
    private String fairytaleStory;
    @Column(name = "summary")
    private String summary;
    @Column(name = "fairytale_title")
    private String fairytaleTitle;

    // 생성자, getter 및 setter 생략

}
