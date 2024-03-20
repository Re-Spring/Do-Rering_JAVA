package respring.dorering.rest.admin.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "exp_voice_id")
public class Voice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    private int no;

    @Column(name = "voice_id", unique = true)
    private String voiceId;

    @Builder
    public Voice(Long no, String voiceId) {
        this.no = no;
        this.voiceId = voiceId;
    }

}
