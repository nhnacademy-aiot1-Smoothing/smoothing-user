package live.smoothing.user.user.entity;

import live.smoothing.user.userauth.entity.UserAuth;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<UserAuth> userAuths = new ArrayList<>();

    @Builder
    public User(String userId, String userPassword, String userName, String userEmail) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userEmail = userEmail;
    }
}
