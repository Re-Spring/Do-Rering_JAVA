package respring.dorering.rest.auth.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import java.util.Date;

@NoArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "user_info")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_code")
    private Long userCode;

    @Column(name = "id", unique = true)
    private String userId;

    @Column(name = "pwd")
    private String password;

    @Column(name = "name")
    private String userName;

    @Column(name = "phone", unique = true)
    private String phone;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "enroll_date")
    private Date enrollDate;

    @ColumnDefault("user")
    @Column(name = "role")
    private String userRole;

    @ColumnDefault("0")
    @Column(name = "voice_id")
    private String userVoiceId;

    @ColumnDefault("N")
    @Column(name = "withdrawal_status")
    private String withdrawalStatus;

    @Temporal(TemporalType.TIMESTAMP) // 날짜와 시간을 모두 저장
    @Column(name = "withdrawal_date")
    private Date withdrawalDate;

    @Builder
    public User(Long userCode, String userId, String password, String userName, String phone,
                Date enrollDate, String userRole, String userVoiceId, String withdrawalStatus, Date withdrawalDate) {
        this.userCode = userCode;
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.phone = phone;
        this.enrollDate = enrollDate;
        this.userRole = userRole;
        this.userVoiceId = userVoiceId;
        this.withdrawalStatus = withdrawalStatus;
        this.withdrawalDate = withdrawalDate;
    }

    // Setters
    public void setUserName(String userName) { this.userName = userName; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setPassword(String password) { this.password = password; }
    public void setWithdrawalStatus(String withdrawalStatus) { this.withdrawalStatus = withdrawalStatus; }
    public void setWithdrawalDate(Date withdrawalDate) { this.withdrawalDate = withdrawalDate; }
}
