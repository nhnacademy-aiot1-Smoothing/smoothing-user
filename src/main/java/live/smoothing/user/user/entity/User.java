package live.smoothing.user.user.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@DynamicUpdate
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_state")
    @Enumerated(EnumType.STRING)
    private UserState userState;

    @Column(name = "user_point")
    private Long userPoint;

    @Column(name = "last_access")
    private LocalDateTime lastAccess;

    @Builder
    public User(String userId, String userPassword, String userName, String userEmail, UserState userState, Long userPoint, LocalDateTime lastAccess) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userState = userState;
        this.userPoint = userPoint;
        this.lastAccess = lastAccess;
    }

    public void modifyUserPassword(String userPassword) {

        this.userPassword = userPassword;
    }

    public void modifyUserName(String userName) {

        this.userName = userName;
    }

    public void modifyUserEmail(String userEmail) {

        this.userEmail = userEmail;
    }

    public void modifyUserState(UserState userState) {

        this.userState = userState;
    }

    public void modifyUserPoint(Long userPoint) {

        this.userPoint = userPoint;
    }

    public void updateLastAccess(LocalDateTime lastAccess) {

        this.lastAccess = lastAccess;
    }
}


