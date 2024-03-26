package respring.dorering.rest.story.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fairytale_video_info")
public class FairytaleVideoInfo {

    @Id
    private Integer videoFileCode;

    @Column(name = "video_path")
    private String videoPath;

    @OneToOne
    @JoinColumn(name = "fairytale_code", referencedColumnName = "fairytale_code")
    @JsonIgnore
    private Story story;


    // Getter, Setter, 기타 필요한 메서드들...
}
