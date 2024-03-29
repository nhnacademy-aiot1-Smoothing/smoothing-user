package live.smoothing.user.user.entity;

import live.smoothing.user.userauth.entity.UserAuth;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@DynamicUpdate
@Table(name = "users")
@Where(clause = "delete_state = false")
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

    @Setter
    @Column(name = "delete_state")
    private boolean deleteState;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<UserAuth> userAuths = new ArrayList<>();

    @Builder
    public User(String userId, String userPassword, String userName, String userEmail, boolean deleteState) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userEmail = userEmail;
        this.deleteState = deleteState;
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
}
