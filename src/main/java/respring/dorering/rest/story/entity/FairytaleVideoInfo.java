package respring.dorering.rest.story.entity;

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
    @Column(name = "video_file_code")
    private String videoFileCode;

    @Column(name = "video_path")
    private String videoPath;

    @ManyToOne // 또는 @OneToOne, 관계 타입에 따라
    @JoinColumn(name = "fairytale_code", referencedColumnName = "fairytale_code")
    private Story story;


    // Getter, Setter, 기타 필요한 메서드들...
}
