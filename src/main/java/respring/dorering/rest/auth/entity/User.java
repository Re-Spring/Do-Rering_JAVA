package respring.dorering.rest.auth.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@NoArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "user_info")
public class User {

    @Id
    @Column(name = "user_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userCode;

    @NotNull
    @Column(name = "id", unique=true)
    private String userId;

    @NotNull
    @Column(name = "pwd")
    private String password;

    @NotNull
    @Column(name = "name")
    private String userName;

    @NotNull
    @Column(name = "phone", unique=true)
    private String phone;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "enroll_date")
    private Date enrollDate;

    // 이 어노테이션은 DDL 생성 시 사용되는 것이며, 실제 실행 시점에서 엔티티에 값을 설정하지 않으면 Hibernate가 자동으로 해당 값을 DB에 적용하지 않습니다.
    @ColumnDefault("user")
    @Column(name = "role")
    private String userRole;

    // 따라서 엔티티를 저장할 때 기본값을 적용하려면, 엔티티 또는 DTO에서 직접 값을 설정해야 합니다.
    @ColumnDefault("0")
    @Column(name = "voice_id")
    private String userVoiceId;

    @NotNull
    @ColumnDefault("N")
    @Column(name = "withdrawal_status")
    private String withdrawalStatus;

    @ColumnDefault("null")
    @Column(name = "withdrawal_date")
    private String withdrawalDate;



    @Builder
    public User(Long userCode, String userId, String password, String userName, String phone,
                Date enrollDate, String userRole, String userVoiceId, String withdrawalStatus, String withdrawalDate) {
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
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setWithdrawalStatus(String withdrawalStatus) {
        this.withdrawalStatus = withdrawalStatus;
    }

    public void setWithdrawalDate(String withdrawalDate) {
        this.withdrawalDate = withdrawalDate;
    }
}