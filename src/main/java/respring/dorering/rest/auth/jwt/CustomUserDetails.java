package respring.dorering.rest.auth.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Date;

public class CustomUserDetails implements UserDetails {

    private String userCode;
    private String userId;
    private String password;
    private String userName;
    private String userVoiceId;
    private String role;
    private String phone;
    private Date enrollDate;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(String userCode, String userName, String userId, String userVoiceId, String role,
                             String password, String phone, Date enrollDate, Collection<? extends GrantedAuthority> authorities) {
        this.userCode = userCode;
        this.userName = userName;
        this.userId = userId;
        this.userVoiceId = userVoiceId;
        this.role = role;
        this.password = password;
        this.phone = phone;
        this.enrollDate = enrollDate;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userCode;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public String getUserCode() {
        return userCode;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserVoiceId() {
        return userVoiceId;
    }

    public String getRole() {
        return role;
    }

    public String getPhone() { return phone; }

    public Date getEnrollDate() { return enrollDate; }
}
