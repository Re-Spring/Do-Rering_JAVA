package respring.dorering.rest.story.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import respring.dorering.rest.auth.entity.User;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fairytale_info")
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fairytale_code")
    private Integer fairytaleCode;

    // 이 필드는 관계 매핑을 위해 유지됩니다. 실제로는 user 객체를 통해 관련된 사용자 정보에 접근할 수 있습니다.
    @ManyToOne
    @JoinColumn(name = "user_code", referencedColumnName = "user_code")
    private User userCode;

    @Column(name = "fairytale_summary")
    private String fairytaleSummary;

    @Column(name = "fairytale_title", nullable = false)
    private String fairytaleTitle;

    @Column(name = "fairytale_genre", nullable = false)
    private String fairytaleGenre;

    @Column(name = "fairytale_thumb")
    private String fairytaleThumb;

    @Column(name = "fairytale_image")
    private String fairytaleImage;



    // 여기서 userCode 필드를 제거하였습니다. 필요한 경우 user 엔티티를 통해 접근 가능합니다.
}
