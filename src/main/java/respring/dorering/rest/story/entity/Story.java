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
    private Integer fairytaleFileCode;

    @Column(nullable = false, unique = true)
    private String fairytaleCode;

    @Column(nullable = false)
    private String fairytaleThumb;

    @Column(length = 10000) // 본문의 길이가 길 수 있으므로 적절한 길이 제한 설정
    private String fairytaleStory;

    @Column(length = 500) // 요약 글이므로 적절한 길이 제한 설정
    private String summary;

    @Column(nullable = false)
    private String fairytaleTitle;
}
