package respring.dorering.rest.story.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ManyToOne
    @JoinColumn(name = "user_code", referencedColumnName = "user_code")
    private User userCode;

    @OneToOne(mappedBy = "story", cascade = CascadeType.ALL)
    @JsonManagedReference
    private FairytaleVideoInfo videoInfo;

    @Column(name = "fairytale_summary")
    private String fairytaleSummary;

    @Column(name = "fairytale_title", nullable = false)
    private String fairytaleTitle;

    @Column(name = "fairytale_genre", nullable = false)
    private String fairytaleGenre;

    @Column(name = "fairytale_thumb")
    private String fairytaleThumb;
}
